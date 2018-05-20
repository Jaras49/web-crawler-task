package com.web.crawler.link.replacer;

import com.web.crawler.link.replacer.replacement.Replacement;
import com.web.crawler.link.replacer.replacement.absolute.address.AbsoluteAddress;
import com.web.crawler.link.replacer.replacement.relative.address.RelativeAddressWithoutExtension;
import com.web.crawler.link.replacer.replacement.relative.address.RelativeAddressWithExtension;
import com.web.crawler.model.CrawledLink;
import com.web.crawler.model.Page;
//TODO TEST it ?
import java.util.ArrayList;

public class LinkReplacerService implements LinkReplacer {

    private ArrayList<Replacement> replacements;

    public LinkReplacerService() {
        replacements = new ArrayList<>();
        replacements.add(new RelativeAddressWithoutExtension());
        replacements.add(new RelativeAddressWithExtension());
        replacements.add(new AbsoluteAddress());
    }

    @Override
    public String makeLocal(Page page, CrawledLink crawledLink) {

        Replacement replacement = getOperation(crawledLink.getCrawledLink());
        if (replacement != null) {
            return replacement.replace(crawledLink, page.getAddress());
        }
        return crawledLink.getCrawledFullLink();
    }

    private Replacement getOperation(String link) {

        for (Replacement replacement : replacements) {
            if (replacement.supports(link)) {
                return replacement;
            }
        }
        return null;
    }
}
