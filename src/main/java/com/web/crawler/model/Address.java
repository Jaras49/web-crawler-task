package com.web.crawler.model;

import java.util.Objects;

/**
 * This class represents address and crawled address of the website
 */

public final class Address {
    //TODO move address field from Page here
    private final String pageAddress;
    private final String parentPageAddress;
    private final String crawledEntity;
    /**
     * ' a href=" ' or ' src=" ' attribute
     */
    private final String head;
    /**
     * crawled entity without head
     */
    private final String crawledLink;
    private final String tail;
    private boolean isRoot;


    public Address(String pageAddress, String parentPageAddress, String crawledEntity, String head, String crawledLink) {
        this.pageAddress = pageAddress;
        this.parentPageAddress = parentPageAddress;
        this.crawledEntity = crawledEntity;
        this.head = head;
        this.crawledLink = crawledLink;
        this.tail = "\"";
        isRoot = false;
    }

    public Address(String pageAddress) {
        this.pageAddress = pageAddress;
        this.parentPageAddress = null;
        this.crawledEntity = null;
        this.head = null;
        this.crawledLink = null;
        this.tail = null;
        this.isRoot = true;
    }

    public String getPageAddress() {
        return pageAddress;
    }

    public String getParentPageAddress() {
        return parentPageAddress;
    }

    public String getCrawledEntity() {
        return crawledEntity;
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

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    @Override
    public String toString() {
        return "Address{" +
                "pageAddress='" + pageAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return isRoot == address.isRoot &&
                Objects.equals(pageAddress, address.pageAddress) &&
                Objects.equals(parentPageAddress, address.parentPageAddress) &&
                Objects.equals(crawledEntity, address.crawledEntity) &&
                Objects.equals(head, address.head) &&
                Objects.equals(crawledLink, address.crawledLink) &&
                Objects.equals(tail, address.tail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pageAddress, parentPageAddress, crawledEntity, head, crawledLink, tail, isRoot);
    }
}
