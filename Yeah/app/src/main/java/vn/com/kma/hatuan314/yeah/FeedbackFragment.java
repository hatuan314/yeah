package vn.com.kma.hatuan314.yeah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {
    WebView wvFeedback;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        wvFeedback = view.findViewById(R.id.wvFeedback);
        wvFeedback.setWebViewClient(new WebViewClient());
        wvFeedback.loadUrl("https://drive.google.com/open?id=1AZIyhf_joef7MEqgnvkIii73-ZqUGWWtBkLJGxKWObE");
        wvFeedback.getSettings().setJavaScriptEnabled(true);
        return view;
    }

}
