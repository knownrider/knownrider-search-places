package com.galexmanuel.knownrider.knownridersearchplaces.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.galexmanuel.knownrider.knownridersearchplaces.R;
import com.google.android.gms.location.places.Place;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchResultsFragment extends Fragment {

    private static final String NAME_KEY = "name-key";
    private static final String ADDRESS_KEY = "address-key";
    private static final String PHONE_NUMBER_KEY = "phone-number-key";
    private static final String RATING_KEY = "rating-key";
    private static final String WEBSITE_KEY = "website-key";

    @Bind(R.id.tvPlaceName)
    protected TextView mTvPlaceName;

    @Bind(R.id.tvPlaceAddress)
    protected TextView mTvPlaceAddress;

    @Bind(R.id.tvPlacePhoneNumber)
    protected TextView mTvPlacePhoneNumber;

    @Bind(R.id.tvPlaceWebsite)
    protected TextView mTvPlaceWebsite;

    @Bind(R.id.webVwebView)
    protected WebView mWebVwebView;

    public static SearchResultsFragment newInstance(final Place place) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putCharSequence(NAME_KEY, place.getName());
        args.putCharSequence(ADDRESS_KEY, place.getAddress());
        args.putCharSequence(PHONE_NUMBER_KEY, place.getPhoneNumber());
        args.putFloat(RATING_KEY, place.getRating());
        args.putParcelable(WEBSITE_KEY, place.getWebsiteUri());
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_results, container, false);
        ButterKnife.bind(this, rootView);
        setPlaceName();
        setPlaceAddress();
        setPlacePhoneNumber();
        setPlaceWebsite();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    private void setPlaceName() {
        final String name = getArguments().getString(NAME_KEY);
        if (!TextUtils.isEmpty(name)) {
            mTvPlaceName.setText(name);
        } else {
            mTvPlaceName.setVisibility(View.INVISIBLE);
        }
    }

    private void setPlaceAddress() {
        final String address = getArguments().getString(ADDRESS_KEY);
        if (!TextUtils.isEmpty(address)) {
            mTvPlaceAddress.setText(address);
        } else {
            mTvPlaceAddress.setVisibility(View.INVISIBLE);
        }
    }

    private void setPlacePhoneNumber() {
        final String number = getArguments().getString(PHONE_NUMBER_KEY);
        if (!TextUtils.isEmpty(number)) {
            mTvPlacePhoneNumber.setText(number);
        } else {
            mTvPlacePhoneNumber.setVisibility(View.INVISIBLE);
        }
    }

    private void setPlaceWebsite() {
        final Uri webUri = getArguments().getParcelable(WEBSITE_KEY);
        final String weburl = (webUri != null && webUri.isHierarchical()) ? webUri.toString() : null;
        if (!TextUtils.isEmpty(weburl)) {
            mTvPlaceWebsite.setText(weburl);
            mWebVwebView.setWebViewClient(new PlaceWebViewClient());
            mWebVwebView.loadUrl(weburl);
        } else {
            mTvPlaceAddress.setVisibility(View.INVISIBLE);
        }
    }

    private class PlaceWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
    }

}
