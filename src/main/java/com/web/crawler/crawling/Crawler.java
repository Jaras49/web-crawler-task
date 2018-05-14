package com.web.crawler.crawling;

import com.web.crawler.crawling.builder.FullLinkBuilder;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler implements WebCrawler {

    private final RegexLinkCrawler regexLinkCrawler;
    private final PageExtractor pageExtractor;
    private final FullLinkBuilder fullLinkBuilder;

    public Crawler(RegexLinkCrawler regexLinkCrawler, PageExtractor pageExtractor, FullLinkBuilder fullLinkBuilder) {

        this.regexLinkCrawler = regexLinkCrawler;
        this.pageExtractor = pageExtractor;
        this.fullLinkBuilder = fullLinkBuilder;
    }

    @Override
    public Collection<Page> crawl(Page page) {

        List<CrawledLink> crawledLinks = regexLinkCrawler.find(page);
//TODO ZAPYTAC O ZAPIS
        return crawledLinks.stream()
                .map(crawledLink -> pageExtractor.extractPage(fullLinkBuilder.checkLinks(page.getAddress(), crawledLink.getCrawledLink()), crawledLink))
                .collect(Collectors.toList());
    }
}
