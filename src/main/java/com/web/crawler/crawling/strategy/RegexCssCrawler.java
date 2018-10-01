package com.web.crawler.crawling.strategy;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;

import java.util.List;
//TODO
public class RegexCssCrawler implements RegexCrawler {

    @Override
    public List<CrawledLink> find(Page page) {
        return null;
    }

    @Override
    public boolean supports(Page page) {
        return false;
    }
}
