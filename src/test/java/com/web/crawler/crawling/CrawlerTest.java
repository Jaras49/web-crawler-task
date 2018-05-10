package com.web.crawler.crawling;

import com.web.crawler.crawling.builder.FullLinkBuilder;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        Page page = new Page("expected adress", "expectedBody");

        when(regexLinkCrawler.find(page)).thenReturn(Arrays.asList("www.example.com"));
        when(fullLinkBuilder.checkLinks(page.getAddress(), "www.example.com")).thenReturn("www.example.com");
        when(pageExtractor.extractPage("www.example.com")).thenReturn(new Page("expected adress", "expectedBody"));

        Collection<Page> expected = new ArrayList<>();
        expected.add(new Page("expected adress", "expectedBody"));

        //When
        Collection<Page> pages = crawler.crawl(page);

        //Then
        assertEquals(expected, pages);
    }
}
