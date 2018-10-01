package com.web.crawler.link.replacer;

import com.web.crawler.link.replacer.replacement.Replacement;
import com.web.crawler.link.replacer.replacement.absolute.address.AbsoluteAddress;
import com.web.crawler.link.replacer.replacement.relative.address.RelativeAddressWithoutExtension;
import com.web.crawler.link.replacer.replacement.relative.address.RelativeAddressWithExtension;
import com.web.crawler.model.Address;
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
    public String makeLocal(Address address) {

        Replacement replacement = getOperation(address.getCrawledLink());
        if (replacement != null) {
            return replacement.replace(address);
        }
        return address.getCrawledEntity();
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
