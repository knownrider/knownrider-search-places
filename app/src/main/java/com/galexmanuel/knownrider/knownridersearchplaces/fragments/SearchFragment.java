package com.galexmanuel.knownrider.knownridersearchplaces.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.galexmanuel.knownrider.knownridersearchplaces.R;
import com.galexmanuel.knownrider.knownridersearchplaces.adapters.PlaceAutocompleteAdapter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;
    private PlaceAutocompleteAdapter mAdapter;

    @Bind(R.id.autocomplete_places)
    protected AutoCompleteTextView mAutoCompleteTextView;

    public static SearchFragment newInstance(final GoogleApiClient googleApiClient) {
        SearchFragment fragment = new SearchFragment();
        fragment.setReferenceParameters(googleApiClient);
        return fragment;
    }

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        mAutoCompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        // North America
        //final LatLngBounds bounds = new LatLngBounds(new LatLng(28.70, -127.50), new LatLng(48.85, -55.90));
        final LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(37.783736, -122.408066)).build();
        mAdapter = new PlaceAutocompleteAdapter(getActivity(), android.R.layout.simple_list_item_1,
                mGoogleApiClient, bounds, null);
        mAutoCompleteTextView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    private void setReferenceParameters(final GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
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
            final String placeId = String.valueOf(item.placeId);
            Log.i(TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            Toast.makeText(getActivity(), "Clicked: " + item.description,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);


            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };

}
