package com.web.crawler.crawling;

import com.web.crawler.crawling.builder.FullLinkBuilder;
import com.web.crawler.extract.PageExtractor;
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

        List<String> links = regexLinkCrawler.find(page);

        return links.stream()
                .map(link -> pageExtractor.extractPage(
                        fullLinkBuilder.checkLinks(page.getAddress(), link)))
                .collect(Collectors.toList());
    }
}
