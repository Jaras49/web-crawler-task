package com.web.crawler.replacer.replacement;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;

public interface Replacement {

    String replace(Page page, CrawledLink crawledLink);
    boolean supports(String link);
}
