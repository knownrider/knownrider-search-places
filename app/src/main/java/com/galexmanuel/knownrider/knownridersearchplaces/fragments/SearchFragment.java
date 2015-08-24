package com.galexmanuel.knownrider.knownridersearchplaces.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.galexmanuel.knownrider.knownridersearchplaces.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @Bind(R.id.tvName)
    protected TextView mTvName;

    @Bind(R.id.tvDescription)
    protected TextView mTvDescription;

    @Bind(R.id.tvEmail)
    protected TextView mTvEmail;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);
        mTvName.setText(getString(R.string.my_name));
        mTvDescription.setText(getString(R.string.my_description));
        mTvEmail.setText(getString(R.string.my_email));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
