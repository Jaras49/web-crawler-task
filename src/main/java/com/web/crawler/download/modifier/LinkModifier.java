package com.web.crawler.download.modifier;

import com.web.crawler.model.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class LinkModifier implements Modifier {
//TODO need to test it and improve those REGEX`s in case they dont work in some conditions
    private static final String LINKS_TO_DELETE_SLASH = "((src|href)=\\\")/";
    private static final String LINKS_TO_ADD_EXTENSION = "(href=\")(([\\w-]+/)*)([\\w-]+\")";
//TODO need to implement an algorithm that will recognize which link modifying strategy to use
    @Override
    public String modifyLinks(Page page) {

        Pattern p = Pattern.compile(LINKS_TO_DELETE_SLASH);
        Matcher m = p.matcher(page.getBody());

        StringBuffer sb = new StringBuffer(page.getBody().length());

        while (m.find()) {
            String text = m.group(1);
            m.appendReplacement(sb, Matcher.quoteReplacement(text));
        }
        m.appendTail(sb);

        return process(page.getAddress(), sb.toString());
    }
    private String process(String address,String websiteSource) {

        Pattern p = Pattern.compile(LINKS_TO_ADD_EXTENSION);
        Matcher m = p.matcher(websiteSource);

        StringBuffer sb = new StringBuffer(websiteSource.length());

        while (m.find()) {
            m.appendReplacement(sb, Matcher.quoteReplacement(m.group(1) + evaluateDepth(address) + m.group(2) + processTheGroup(m.group(4))));
        }
        m.appendTail(sb);

        return sb.toString();

    }

    private String processTheGroup(String group) {

        return group.substring(0, group.length() - 1) + ".html\"";
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

    public static void main(String[] args) {
        LinkModifier linkModifier = new LinkModifier();
        System.out.println(linkModifier.evaluateDepth("https://www.iana.org/domains/root"));
    }
}
