package com.example.grp20_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*
Displays the actual page stored in WikiPage.class

 */
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
        //Make sure we have something to display
        if (bundle != null) {
            //Get's the wikipage sent from whoever called me
            WikiPage wikipage = (WikiPage) bundle.getSerializable("wiki");

            //Displayed the wikipage class to the UI
            TextView title = view.findViewById(R.id.tvTitle);
            title.setText(wikipage.getTitle());
            TextView year = view.findViewById(R.id.tvYear);
            year.setText("must fix");
            TextView wiki = view.findViewById(R.id.tvWiki);

            //Removes HTML links
            String fixed = wikipage.getText().replaceAll("<a[^>]*>(.*?)</a>", "$1");
            //Since there are no links you can't use the references/footnotes, so we remove them
            fixed = fixed.replaceAll("<span.*?</span>", "");
            /*
            fixed = fixed.replaceAll("<div.*?</div>", "");
            fixed = fixed.replaceAll("<li.*?</li>", "");
            fixed = fixed.replaceAll("<ul.*?</ul>", "");
            fixed = fixed.replaceAll("<td.*?</td>", "");
            fixed = fixed.replaceAll("<tbody.*?</tbody>", "");
            */
            wiki.setText(Html.fromHtml(fixed));//Set the text to be the customized text
            image = view.findViewById(R.id.ivWW1);
            image.setImageBitmap(wikipage.getImage());


        }

        return view;
    }
}

