package com.galexmanuel.knownrider.knownridersearchplaces.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import com.galexmanuel.knownrider.knownridersearchplaces.R;
import com.galexmanuel.knownrider.knownridersearchplaces.fragments.SearchResultsFragment;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

/**
 * An activity to host the results of the user's place search.
 */
public class SearchResultsActivity extends BaseActivity {

    private static final String TAG = SearchResultsActivity.class.getSimpleName();

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar);
        setupToolbar();

        handleIntent(getIntent());
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void setResultsFragmentView(final Place place) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, SearchResultsFragment.newInstance(place), SearchResultsFragment.class.getSimpleName());
        ft.commit();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    protected void onNewIntent(final Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(final Intent intent) {
        if (intent != null) {
            final String placeId = intent.getStringExtra(PLACE_ID_KEY);
            if (!TextUtils.isEmpty(placeId)) {
                // get place
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            }
        }
    }

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer placeBuffer) {
            if (!placeBuffer.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + placeBuffer.getStatus().toString());
                placeBuffer.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = placeBuffer.get(0);

            mToolbar.setTitle(place.getName());
            setResultsFragmentView(place);

            placeBuffer.release();
        }
    };

}
