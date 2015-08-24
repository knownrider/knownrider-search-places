package com.galexmanuel.knownrider.knownridersearchplaces.views;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by amanuel on 8/24/15.
 */
public class PlacesAutoCompleteSearchView extends SearchView {

    private SearchView.SearchAutoComplete mSearchAutoComplete;

    public PlacesAutoCompleteSearchView(Context context) {
        super(context);
        initialize();
    }

    public PlacesAutoCompleteSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void initialize() {
        mSearchAutoComplete = (SearchAutoComplete) findViewById(android.support.v7.appcompat.R.id.search_src_text);
        this.setAdapter(null);
        this.setOnItemClickListener(null);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mSearchAutoComplete.setOnItemClickListener(listener);
    }

    public void setAdapter(ArrayAdapter<?> adapter) {
        mSearchAutoComplete.setAdapter(adapter);
    }
}
