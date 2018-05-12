package com.web.crawler.crawling;

import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLinkCrawler {
    //TODO implement better regex`s
    private static final String LINK_REGEX = "(href|src)=\\\"([\\w\\d-_:\\./]+)\\\"";

    public List<CrawledLink> find(Page page) {

        List<CrawledLink> crawledLinks = new ArrayList<>();

        Pattern p = Pattern.compile(LINK_REGEX);

        Matcher m = p.matcher(page.getBody());

        while (m.find()) {
            crawledLinks.add(new CrawledLink(m.group(0), m.group(2)));
        }
        return crawledLinks;
    }
}
