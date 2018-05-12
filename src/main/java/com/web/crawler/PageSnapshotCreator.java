package com.web.crawler;

import com.web.crawler.crawling.WebCrawler;
import com.web.crawler.model.CrawledLink;
import com.web.crawler.replacer.Replacer;
import com.web.crawler.replacer.ReplacerProcessor;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import com.web.crawler.model.PageSnapshot;
import com.web.crawler.utils.LinkReplacement;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

public class PageSnapshotCreator {

    private final WebCrawler webCrawler;
    private final PageExtractor pageExtractor;
    private final Set<String> visitedPage;
    private final Replacer replacer;
    private final Set<CrawledLink> allLinks;

    public PageSnapshotCreator(
            WebCrawler webCrawler,
            PageExtractor pageExtractor) {

        this.webCrawler = webCrawler;
        this.pageExtractor = pageExtractor;
        this.visitedPage = new HashSet<>();
        this.replacer = new ReplacerProcessor();
        this.allLinks = new HashSet<>();
    }

    public PageSnapshot createPageNode(String url, int depth) {
        visitedPage.add(url);
        return getPage(pageExtractor.extractPage(url), depth);
    }

    private PageSnapshot getPage(Page page, int depth) {

        if (depth == 0) {
            return new PageSnapshot(makeLinksLocal(page, allLinks), emptyList());
        }

        Set<PageSnapshot> links = getLinks(page, depth);

        Page pageWithLocalLinks = makeLinksLocal(page, allLinks);

        return new PageSnapshot(pageWithLocalLinks, links);
    }

    private Page makeLinksLocal(Page page, Set<CrawledLink> links) {
        String updatedBody = page.getBody();

        //TODO need to localize links, split it to new class ?
        Set<LinkReplacement> linkReplacements = links.stream()
                .map(link -> new LinkReplacement(link.getCrawledFullLink(), replacer.makeLocal(page, link)))
                .collect(toSet());

        for (LinkReplacement linkReplacement : linkReplacements) {
            updatedBody = updatedBody.replace(linkReplacement.getOriginal(), linkReplacement.getReplacement());
        }

        return new Page(page.getAddress(),page.getCrawledLink(), updatedBody);
    }

    private Set<PageSnapshot> getLinks(Page root, int depth) {
        return webCrawler.crawl(root)
                .stream()
                .peek(p -> allLinks.add(p.getCrawledLink()))
                .filter(p -> !visitedPage.contains(p.getAddress()))
                .peek(p -> visitedPage.add(p.getAddress()))
                .map(p -> getPage(p, depth - 1))
                .collect(toSet());
    }
}