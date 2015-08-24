package com.galexmanuel.knownrider.knownridersearchplaces.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galexmanuel.knownrider.knownridersearchplaces.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchResultsFragment extends Fragment {

    public static SearchResultsFragment newInstance() {
        SearchResultsFragment fragment = new SearchResultsFragment();
        return fragment;
    }

    public SearchResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, container, false);
    }
}
