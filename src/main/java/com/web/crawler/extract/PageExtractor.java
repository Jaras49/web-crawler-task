package com.web.crawler.extract;

import com.web.crawler.model.Page;

public interface PageExtractor {

    /**
     * Extracts {@link Page} from given <code>url</code>
     */
    Page extractPage(String url);
    Page extractPage(String url, String crawledUrl);
}
