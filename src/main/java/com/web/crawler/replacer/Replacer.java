package com.web.crawler.replacer;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;
//TODO get better name for those classes
public interface Replacer {

    String makeLocal(Page page, CrawledLink crawledLink);
}
