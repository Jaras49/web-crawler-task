package com.web.crawler.replacer.replacement.primitive;

import com.web.crawler.replacer.replacement.Replacement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DontAddExtension implements Replacement {

    private static final String DONT_ADD_EXTENSION_REGEX = "/?[\\w/\\.]*/(\\w+\\.\\w+)";

    @Override
    public String replace(String link) {

        if (link.startsWith("/")) {
            link = link.substring(1, link.length());
        }
        return link;
    }

    @Override
    public boolean supports(String link) {

        Matcher m = Pattern.compile(DONT_ADD_EXTENSION_REGEX).matcher(link);

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
