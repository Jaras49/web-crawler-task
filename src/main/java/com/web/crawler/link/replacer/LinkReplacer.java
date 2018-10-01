package com.web.crawler.link.replacer;

import com.web.crawler.model.Address;
import com.web.crawler.model.Page;
//TODO get better name for those classes
public interface LinkReplacer {

    String makeLocal(Address address);
}
