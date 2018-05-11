package com.web.crawler.replacer.replacement;

public interface Replacement {

    String replace(String link);
    boolean supports(String link);
}
