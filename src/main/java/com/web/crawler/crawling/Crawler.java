package com.web.crawler.crawling;

import com.web.crawler.crawling.builder.FullLinkBuilder;
import com.web.crawler.crawling.strategy.RegexCrawler;
import com.web.crawler.crawling.strategy.RegexLinkCrawler;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Address;
import com.web.crawler.model.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler implements WebCrawler {

    private final List<RegexCrawler> regexCrawlers;
    private final PageExtractor pageExtractor;
    private final FullLinkBuilder fullLinkBuilder;

    public Crawler(RegexLinkCrawler regexLinkCrawler, PageExtractor pageExtractor, FullLinkBuilder fullLinkBuilder) {

        regexCrawlers = new ArrayList<>();
        regexCrawlers.add(new RegexLinkCrawler());
        this.pageExtractor = pageExtractor;
        this.fullLinkBuilder = fullLinkBuilder;
    }

    @Override
    public Collection<Page> crawl(Page page) {

        List<Address> addresses = chooseCrawlerStrategy(page).find(page);
//TODO ZAPYTAC O ZAPIS
        return addresses.stream()
                .map(crawledLink -> pageExtractor.extractPage(buildFullLinks(crawledLink), crawledLink))
                .collect(Collectors.toList());
    }

    private String buildFullLinks(Address address) {
        return fullLinkBuilder.checkLinks(address.getParentPageAddress(), address.getCrawledLink());
    }

    private RegexCrawler chooseCrawlerStrategy(Page page) {

        for (RegexCrawler regexCrawler : regexCrawlers) {
            if (regexCrawler.supports(page)) {

                return regexCrawler;
            }
        }
        throw new IllegalStateException("there is no crawler strategy for specified page " + page.getAddress());
    }
}
