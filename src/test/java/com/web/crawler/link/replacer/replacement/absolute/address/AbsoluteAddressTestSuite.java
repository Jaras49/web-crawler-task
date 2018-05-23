package com.web.crawler.link.replacer.replacement.absolute.address;

import com.web.crawler.model.CrawledLink;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class AbsoluteAddressTestSuite {

    private static final String TEST_ADDRESS_1 = "https://www.testCase.org";

    @RunWith(Parameterized.class)
    public static class ReplaceMethodTest {

        private AbsoluteAddress absoluteAddress = new AbsoluteAddress();


        @Parameters
        public static Collection data() {
            return Arrays.asList(new Object[][]{
                    {new CrawledLink("", "href=\"", "http://www.icann.org"), "href=\"../icann.org\""},
                    {new CrawledLink("", "href=\"", "http://pti.icann.org"), "href=\"../pti.icann.org\""}
            });
        }

        @Parameter
        public CrawledLink crawledLink;
        @Parameter(1)
        public String expectedResult;

        @Test
        public void shouldBuildLocalLinks() {
            assertEquals(expectedResult, absoluteAddress.replace(crawledLink, TEST_ADDRESS_1));
        }

    }

    @RunWith(Parameterized.class)
    public static class SupportsMethodTest {

        private AbsoluteAddress absoluteAddress = new AbsoluteAddress();

        @Parameters
        public static Collection data() {
            return Arrays.asList(new Object[][]{
                    {"about/", false},
                    {"/domains", false},
                    {"/domains/root", false},
                    {"/domains/root/db", false},
                    {"/domains/idn-tables", false},
                    {"/time-zones", false},
                    {"http://www.icann.org/", true},
                    {"/_css/2015.1/screen.css", false},
                    {"about", false},
                    {"/about.html", false},
                    {"/domains/root/db", false},
                    {"/_js/2013.1/jquery.js", false},
                    {"/_img/bookmark_icon.ico", false},
                    {"http://www.icann.org/domains-zones/times/xYz", true}

            });
        }

        @Parameter
        public String link;
        @Parameter(1)
        public boolean expectedResult;

        @Test
        public void shouldTestSupports() {
            assertEquals(expectedResult, absoluteAddress.supports(link));
        }
    }
}