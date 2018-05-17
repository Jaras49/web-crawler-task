package com.web.crawler.model;

public class LinkReplacement {

    private final String original;
    private final String replacement;

    public LinkReplacement(String original, String replacement) {
        this.original = original;
        this.replacement = replacement;
    }

    public String getOriginal() {
        return original;
    }

    public String getReplacement() {
        return replacement;
    }
}
