package com.web.crawler.model;

import java.util.Objects;

/**
 * This class contains page address and their body
 */
public class Page {

    private final String address;
    private final String crawledAddress;
    private final String body;

    public Page(String address, String crawledAddress, String body) {
        this.address = address;
        this.crawledAddress = crawledAddress;
        this.body = body;
    }

    public Page(String address, String body) {
        this.address = address;
        this.body = body;
        this.crawledAddress = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("address='").append(address).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getAddress() {
        return address;
    }

    public String getBody() {
        return body;
    }

    public String getCrawledAddress() {
        return crawledAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;
        Page page = (Page) o;
        return Objects.equals(address, page.address) &&
                Objects.equals(crawledAddress, page.crawledAddress) &&
                Objects.equals(body, page.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(address, crawledAddress, body);
    }
}
