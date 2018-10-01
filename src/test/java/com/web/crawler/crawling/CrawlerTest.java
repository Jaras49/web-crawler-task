package com.web.crawler.crawling;

import com.web.crawler.crawling.builder.FullLinkBuilder;
import com.web.crawler.crawling.strategy.RegexLinkCrawler;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Address;
import com.web.crawler.model.Page;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CrawlerTest {

    private RegexLinkCrawler regexLinkCrawler;
    private PageExtractor pageExtractor;
    private FullLinkBuilder fullLinkBuilder;
    private Crawler crawler;

    @Before
    public void setUp() throws Exception {

        regexLinkCrawler = Mockito.mock(RegexLinkCrawler.class);
        pageExtractor = Mockito.mock(PageExtractor.class);
        fullLinkBuilder = Mockito.mock(FullLinkBuilder.class);

        crawler = new Crawler(regexLinkCrawler, pageExtractor, fullLinkBuilder);
    }

    @Test
    public void crawl() {

        //Given
        Page page = new Page("test address", "testBody");
        Address address = new Address("href=\"www.example.com\"", "href=\"", "www.example.com");
        Page expectedPage = new Page("expected address", address, "expectedBody");

        when(regexLinkCrawler.find(page)).thenReturn(Collections.singletonList(address));
        when(fullLinkBuilder.checkLinks(page.getAddress(), address.getCrawledLink())).thenReturn("www.example.com");
        when(pageExtractor.extractPage("www.example.com", address)).thenReturn(expectedPage);

        Collection<Page> expected = new ArrayList<>();
        expected.add(expectedPage);

        //When
        Collection<Page> pages = crawler.crawl(page);

        //Then
        assertEquals(expected, pages);
    }
}
