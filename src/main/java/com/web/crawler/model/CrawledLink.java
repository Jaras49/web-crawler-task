package com.web.crawler.model;

import java.util.Objects;

public final class CrawledLink {

    private final String crawledFullLink;
    private final String head;
    private final String crawledLink;
    private final String tail;

    public CrawledLink(String crawledFullLink, String head, String crawledLink) {
        this.crawledFullLink = crawledFullLink;
        this.head = head;
        this.crawledLink = crawledLink;
        this.tail = "\"";
    }

    public String getCrawledFullLink() {
        return crawledFullLink;
    }

    public String getHead() {
        return head;
    }

    public String getCrawledLink() {
        return crawledLink;
    }

    public String getTail() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrawledLink)) return false;
        CrawledLink that = (CrawledLink) o;
        return Objects.equals(crawledFullLink, that.crawledFullLink) &&
                Objects.equals(head, that.head) &&
                Objects.equals(crawledLink, that.crawledLink) &&
                Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(crawledFullLink, head, crawledLink, tail);
    }
}
