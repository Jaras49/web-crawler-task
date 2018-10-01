package com.web.crawler.link.replacer.replacement.relative.address;

import com.web.crawler.link.replacer.replacement.Replacement;
import com.web.crawler.model.Address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelativeAddressWithExtension implements Replacement {
//TODO check if this regex works in all cases
    private static final String RELATIVE_WITH_EXTENSION_REGEX = "/?[\\w/\\.]*/(\\w+\\.\\w+)";

    @Override
    public String replace(Address crawledLink) {

        String link = crawledLink.getCrawledLink();

        if (link.startsWith("/")) {
            link = link.substring(1, link.length());
        }
        return crawledLink.getHead() + evaluateDepth(crawledLink.getParentPageAddress()) + link + crawledLink.getTail();
    }

    @Override
    public boolean supports(String link) {

        Matcher m = Pattern.compile(RELATIVE_WITH_EXTENSION_REGEX).matcher(link);

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
