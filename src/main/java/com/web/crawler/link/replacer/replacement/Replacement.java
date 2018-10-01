package com.web.crawler.link.replacer.replacement;

import com.web.crawler.model.Address;

public interface Replacement {

    String replace(Address crawledLink);
    boolean supports(String link);
}
