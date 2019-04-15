package com.example.grp20_app;

import android.net.Uri;
import android.util.Pair;

import java.io.Serializable;

class PairWikiPage implements Serializable {
    Pair<WikiPage, Uri> wikiPages;
    public PairWikiPage(Pair<WikiPage, Uri> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public Pair<WikiPage, Uri> getWikiPages() {
        return wikiPages;
    }
}
