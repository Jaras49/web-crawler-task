package com.web.crawler.link.replacer.replacement.relative.address;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.link.replacer.replacement.Replacement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelativeAddressWithoutExtension implements Replacement {
//TODO check if this regex works in all cases
    private static final String RELATIVE_WITHOUT_EXTENSION_REGEX = "(/?[\\w/\\-]*/?)+([\\w\\-]+)?";

    @Override
    public String replace(CrawledLink crawledLink, String address) {

        String link = crawledLink.getCrawledLink();

        if(link.startsWith("/")) {
            link = link.substring(1, link.length());
        }

        if(link.endsWith("/")) {
            link = link.substring(0, link.length() - 1);
        }

        return crawledLink.getHead() + evaluateDepth(address) + link + ".html" + crawledLink.getTail();
    }

    @Override
    public boolean supports(String link) {

        Matcher m = Pattern.compile(RELATIVE_WITHOUT_EXTENSION_REGEX).matcher(link);

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
