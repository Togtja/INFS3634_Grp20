package com.example.grp20_app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WikiPageFragment extends Fragment {
    ImageView image;

    public WikiPageFragment() {
    }

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wiki_page, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            WikiPage wikipage = (WikiPage) bundle.getSerializable("wiki");

            TextView title = view.findViewById(R.id.tvTitle);
            title.setText(wikipage.getTitle());
            TextView year = view.findViewById(R.id.tvYear);
            year.setText("must fix");
            TextView wiki = view.findViewById(R.id.tvWiki);
            //String fixed = wikipage.getText().replaceAll( "<a\b[^>]+>([^<]*(?:(?!</a)<[^<]*)*)</a>", "");
            //fixed = fixed.replaceAll("<a.*?>|</a>","");

            String fixed = wikipage.getText().replaceAll("<a[^>]*>(.*?)</a>", "$1");

            fixed =  fixed.replaceAll("<div.*?</div>", "");
            fixed =  fixed.replaceAll("<li.*?</li>", "");
            fixed =  fixed.replaceAll("<ul.*?</ul>", "");
            fixed =  fixed.replaceAll("<span.*?</span>", "");
            fixed =  fixed.replaceAll("<td.*?</td>", "");
            fixed =  fixed.replaceAll("<tbody.*?</tbody>", "");
            wiki.setText(Html.fromHtml(fixed));
            image = view.findViewById(R.id.ivWW1);
            image.setImageBitmap(wikipage.getImage());


        }

        return view;
    }
}

