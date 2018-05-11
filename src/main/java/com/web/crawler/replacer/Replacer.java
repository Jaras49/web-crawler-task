package com.web.crawler.replacer;

import com.web.crawler.model.Page;

public interface Replacer {

    String makeLocal(Page page, String link);
}
