package com.web.crawler.link.replacer.replacement.relative.address;

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
public class RelativeWithExtensionTestSuite {

    @RunWith(Parameterized.class)
    public static class ReplaceMethodTest {

        private CrawledLink crawledLink;
        private String expected;
        private RelativeWithExtension relativeWithExtension;

        public ReplaceMethodTest(CrawledLink crawledLink, String expected) {
            this.crawledLink = crawledLink;
            this.expected = expected;
            relativeWithExtension = new RelativeWithExtension();
        }

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new CrawledLink("", "src=\"", "/_css/2015.1/screen.css"),
                            "src=\"_css/2015.1/screen.css\""},
            });
        }

        @Test
        public void shouldBuildLocalLink() {
            assertEquals(expected, relativeWithExtension.replace(crawledLink, "https://www.testCase.org/"));
        }
    }

    @RunWith(Parameterized.class)
    public static class SupportsMethodTest {

        private RelativeWithExtension relativeWithExtension = new RelativeWithExtension();

        @Parameters
        public static Collection data() {
            return Arrays.asList(new Object[][]{
                    {"about/", false},
                    {"/domains", false},
                    {"/domains/root", false},
                    {"/domains/root/db", false},
                    {"/domains/idn-tables", false},
                    {"/time-zones", false},
                    {"http://www.icann.org/", false},
                    {"/_css/2015.1/screen.css", true},
                    {"about", false},
                    {"about.html", false},
                    {"/domains/root/db", false},
                    {"/_js/2013.1/jquery.js", true},
                    {"/_img/bookmark_icon.ico", true}

            });
        }

        @Parameter
        public String link;
        @Parameter(1)
        public boolean expectedResult;

        @Test
        public void shouldTestSupports() {
            assertEquals(expectedResult, relativeWithExtension.supports(link));
        }
    }
}