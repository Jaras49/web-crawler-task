package com.web.crawler.crawling;

import com.web.crawler.model.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLinkCrawler {
    //TODO implement better regex`s
    private static final String LINK_REGEX = "(href|src)=\\\"([\\w\\d-_:\\./]+)\\\"";

    public List<String> find(Page page) {

        List<String> list = new ArrayList<>();

        Pattern p = Pattern.compile(LINK_REGEX);

        Matcher m = p.matcher(page.getBody());

        while (m.find()) {
            list.add(m.group(2));
        }
        return list;
    }
}
