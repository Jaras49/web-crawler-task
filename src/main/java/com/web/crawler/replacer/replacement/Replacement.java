package com.web.crawler.replacer.replacement;

import com.web.crawler.model.CrawledLink;

public interface Replacement {

    String replace(CrawledLink crawledLink);
    boolean supports(String link);
}
