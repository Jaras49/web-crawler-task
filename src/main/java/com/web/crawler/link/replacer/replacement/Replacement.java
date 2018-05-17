package com.web.crawler.link.replacer.replacement;

import com.web.crawler.model.CrawledLink;

public interface Replacement {

    String replace(CrawledLink crawledLink, String address);
    boolean supports(String link);
}
