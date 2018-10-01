package com.web.crawler.crawling.strategy;

import com.web.crawler.model.Address;
import com.web.crawler.model.Page;

import java.util.List;

public interface RegexCrawler {
    List<Address> find(Page page);

    boolean supports(Page page);
}
