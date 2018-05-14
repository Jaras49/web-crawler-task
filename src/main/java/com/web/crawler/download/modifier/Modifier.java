package com.web.crawler.download.modifier;

import com.web.crawler.model.Page;
/**
 * @deprecated
 * This class is deprecated due to its refactor
 */
@Deprecated
public interface Modifier {

    String modifyLinks(Page page);
}
