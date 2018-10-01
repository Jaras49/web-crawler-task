package com.web.crawler.crawling.strategy;

import com.web.crawler.model.Address;
import com.web.crawler.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLinkCrawler implements RegexCrawler {
    //TODO implement better regex`s
    private static final String LINK_REGEX = "((href|src)=\\\")([\\w\\d-_:\\./]+)\\\"";

    public List<Address> find(Page page) {

        List<Address> addresses = new ArrayList<>();

        Pattern p = Pattern.compile(LINK_REGEX);

        Matcher m = p.matcher(page.getBody());

        while (m.find()) {
            addresses.add(new Address(m.group(3), page.getAddress().getPageAddress(), m.group(0), m.group(1), m.group(3)));
        }
        return addresses;
    }

    @Override
    public boolean supports(Page page) {
        return true;
    }
//TODO THIS IS POC OF THE USE OF JSOUP LIBRARY
    public static void main(String[] args) throws MalformedURLException, IOException {

        URL url = new URL("https://www.iana.org/domains/reserved");

        Document document = Jsoup.connect("https://www.iana.org/domains/reserved").get();

        Elements select = document.select("a[href]");

        for (Element element : select) {

            Attributes attributes = element.attributes();
            System.out.println("ATTRIBUTES>>>>>>>>>>>>>>");

            for (Attribute attribute : attributes) {

                if (attribute.getKey().equals("href")) {
                    System.out.println(attribute.getValue());
                    attribute.setValue("jarek");
                }
                System.out.println(attribute.getKey());
            }
            System.out.println("ELEMENT >>>>>");
            System.out.println(element);
        }

        System.out.println("CONTENT>>>>>>>>>>>");
        System.out.println(document);
    }
}
