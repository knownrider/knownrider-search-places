package com.galexmanuel.knownrider.knownridersearchplaces.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galexmanuel.knownrider.knownridersearchplaces.R;

/**
 * A fragment with a progress spinner while background tasks take place
 */
public class ProgressFragment extends Fragment {

    public static final ProgressFragment newInstance() {
        ProgressFragment fragment = new ProgressFragment();
        return fragment;
    }

    public ProgressFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }
}
