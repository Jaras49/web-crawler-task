package com.web.crawler.crawling.builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class FullLinkBuilderTestSuite {

    @Test
    public void shouldBuildFullLinks() {

        //Given
        FullLinkBuilder fullLinkBuilder = new FullLinkBuilder();
        String url = "https://example.com/domains/reserved";
        String testLink1 = "/_css/2015.1/screen.css";
        String testLink2 = "/_js/2013.1/jquery.js";
        String testLink3 = "/domains";
        String testLink4 = "/time-zones/";
        String testLink5 = "time-zones";
        String testLink6 = "time-zones/";
        String testLink7 = "http://www.icann.org/";
        String testLink8 = "http://www.icann.org/domains/reserved";

        String expected1 = "https://example.com/_css/2015.1/screen.css";
        String expected2 = "https://example.com/_js/2013.1/jquery.js";
        String expected3 = "https://example.com/domains";
        String expected4 = "https://example.com/time-zones";
        String expected5 = "https://example.com/time-zones";
        String expected6 = "https://example.com/time-zones";
        String expected7 = "https://www.icann.org";
        String expected8 = "https://www.icann.org/domains/reserved";


        //When
        String result1 = fullLinkBuilder.checkLinks(url, testLink1);
        String result2 = fullLinkBuilder.checkLinks(url, testLink2);
        String result3 = fullLinkBuilder.checkLinks(url, testLink3);
        String result4 = fullLinkBuilder.checkLinks(url, testLink4);
        String result5 = fullLinkBuilder.checkLinks(url, testLink5);
        String result6 = fullLinkBuilder.checkLinks(url, testLink6);
        String result7 = fullLinkBuilder.checkLinks(url, testLink7);
        String result8 = fullLinkBuilder.checkLinks(url, testLink8);

        //Then
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);
        assertEquals(expected4, result4);
        assertEquals(expected5, result5);
        assertEquals(expected6, result6);
        assertEquals(expected7, result7);
        assertEquals(expected8, result8);
    }
}