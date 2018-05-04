package com.web.crawler.download.modifier;

import com.web.crawler.model.Page;

public interface Modifier {

    String modifyLinks(Page page);
}
