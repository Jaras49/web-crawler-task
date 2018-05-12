package com.web.crawler.replacer.replacement.primitive;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;
import com.web.crawler.replacer.replacement.Replacement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddExtension implements Replacement {
//TODO check if this regex works in all cases
    private static final String ADD_EXTENSION_REGEX = "/?[\\w/\\.]*/(\\w+\\w+)?";

    @Override
    public String replace(Page page, CrawledLink crawledLink) {

        String link = crawledLink.getCrawledLink();

        if(link.startsWith("/")) {
            link = link.substring(1, link.length());
        }

        if(link.endsWith("/")) {
            link = link.substring(0, link.length() - 1);
        }

        return crawledLink.getHead() + evaluateDepth(page.getAddress()) + link + ".html" + crawledLink.getTail();
    }

    @Override
    public boolean supports(String link) {

        Matcher m = Pattern.compile(ADD_EXTENSION_REGEX).matcher(link);

        if (m.matches()) {
            return true;
        }
        return false;
    }

    private String evaluateDepth(String address) {

        String result = "";
        int count = ((int) address.chars()
                .filter(n -> n == '/')
                .count()) - 3;
        for (int i = 0; i < count; i++) {
            result += "../";
        }

        return result;
    }
}
