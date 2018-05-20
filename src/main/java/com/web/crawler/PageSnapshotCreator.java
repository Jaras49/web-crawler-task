package com.web.crawler;

import com.web.crawler.crawling.RegexLinkCrawler;
import com.web.crawler.crawling.WebCrawler;
import com.web.crawler.model.CrawledLink;
import com.web.crawler.link.replacer.LinkReplacer;
import com.web.crawler.link.replacer.LinkReplacerService;
import com.web.crawler.extract.PageExtractor;
import com.web.crawler.model.Page;
import com.web.crawler.model.PageSnapshot;
import com.web.crawler.model.LinkReplacement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

public class PageSnapshotCreator {

    private final WebCrawler webCrawler;
    private final PageExtractor pageExtractor;
    private final Set<String> visitedPage;
    private final LinkReplacer linkReplacer;
    private final List<CrawledLink> allLinks;

    public PageSnapshotCreator(
            WebCrawler webCrawler,
            PageExtractor pageExtractor) {

        this.webCrawler = webCrawler;
        this.pageExtractor = pageExtractor;
        this.visitedPage = new HashSet<>();
        this.linkReplacer = new LinkReplacerService();
        this.allLinks = new ArrayList<>();
    }

    public PageSnapshot createPageNode(String url, int depth) {
        visitedPage.add(url);
        return getPage(pageExtractor.extractPage(url), depth);
    }

    private PageSnapshot getPage(Page page, int depth) {
//TODO This is test POC, zapytać Kamila o najlepsze rozwiązanie
        RegexLinkCrawler regexLinkCrawler = new RegexLinkCrawler();
        List<CrawledLink> crawledLinks = regexLinkCrawler.find(page);
        allLinks.addAll(crawledLinks);
        if (depth == 0) {
            return new PageSnapshot(makeLinksLocal(page, allLinks), emptyList());
        }

        Set<PageSnapshot> links = getLinks(page, depth);

        Page pageWithLocalLinks = makeLinksLocal(page, allLinks);

        return new PageSnapshot(pageWithLocalLinks, links);
    }

    private Page makeLinksLocal(Page page, List<CrawledLink> links) {
        String updatedBody = page.getBody();

        //TODO need to localize links, split it to new class ?
        Set<LinkReplacement> linkReplacements = links.stream()
                .map(link -> new LinkReplacement(link.getCrawledFullLink(), linkReplacer.makeLocal(page, link)))
                .collect(toSet());

        for (LinkReplacement linkReplacement : linkReplacements) {
            updatedBody = updatedBody.replace(linkReplacement.getOriginal(), linkReplacement.getReplacement());
        }

        return new Page(page.getAddress(),page.getCrawledLink(), updatedBody);
    }

    private Set<PageSnapshot> getLinks(Page root, int depth) {
        return webCrawler.crawl(root)
                .stream()
                .filter(p -> !visitedPage.contains(p.getAddress()))
                .peek(p -> visitedPage.add(p.getAddress()))
                .map(p -> getPage(p, depth - 1))
                .collect(toSet());
    }
}