package com.galexmanuel.knownrider.knownridersearchplaces.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.galexmanuel.knownrider.knownridersearchplaces.R;
import com.galexmanuel.knownrider.knownridersearchplaces.adapters.PlaceAutocompleteAdapter;
import com.galexmanuel.knownrider.knownridersearchplaces.fragments.SearchFragment;
import com.galexmanuel.knownrider.knownridersearchplaces.views.PlacesAutoCompleteSearchView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Main activity where users can type searches in the toolbar.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private PlaceAutocompleteAdapter mAdapter;
    private PlacesAutoCompleteSearchView mAutoCompleteTextView;
    private MenuItem mSearchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
        setupToolbar();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void setSearchFragmentView() {
        mToolbar.setTitle(getString(R.string.main_cta));
        if (mGoogleApiClient != null && !mResolvingGoogleError) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, SearchFragment.newInstance(), SearchFragment.class.getSimpleName());
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchMenuItem = menu.findItem(R.id.search);

        mAutoCompleteTextView = (PlacesAutoCompleteSearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        mAutoCompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mAutoCompleteTextView.setQueryHint(getString(R.string.search_hint));

        // LatLngBounds used to seed the Autocomplete buffer
        // North America - general results
        //final LatLngBounds bounds = new LatLngBounds(new LatLng(28.70, -127.50), new LatLng(48.85, -55.90));
        // Hotel Tonight office - fine results for SF
        final LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(37.783736, -122.408066)).build();

        mAdapter = new PlaceAutocompleteAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, bounds, null);
        mAutoCompleteTextView.setAdapter(mAdapter);
        return true;
    }

    @Override
    public void onConnected(Bundle bundle) {
        setSearchFragmentView();
    }

    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data API
     * to retrieve more details about the place.
     */
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);

            // reset the search view
            mSearchMenuItem.collapseActionView();

            Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
            intent.putExtra(PLACE_ID_KEY, item.placeId);
            startActivity(intent);
        }
    };

}
