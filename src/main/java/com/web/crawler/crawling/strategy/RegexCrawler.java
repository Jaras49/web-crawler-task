package com.web.crawler.crawling.strategy;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;

import java.util.List;

public interface RegexCrawler {
    List<CrawledLink> find(Page page);

    boolean supports(Page page);
}
