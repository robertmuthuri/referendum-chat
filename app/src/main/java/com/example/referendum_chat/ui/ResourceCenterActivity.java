package com.example.referendum_chat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.referendum_chat.Constants;
import com.example.referendum_chat.models.GeoDBCitiesSearchResponse;
import com.example.referendum_chat.R;
import com.example.referendum_chat.models.ResourceCenter;
import com.example.referendum_chat.adapters.ResourceCenterListAdapter;
import com.example.referendum_chat.models.Datum;
import com.example.referendum_chat.network.GeoDBApiInterface;
import com.example.referendum_chat.network.GeoDBClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceCenterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ResourceCenterActivity";
    @BindView(R.id.resourceSpinner) Spinner mResourceSpinner;
//    @BindView(R.id.kenyanCitiesListView) ListView mKenyanCitiesListView;
//    @BindView(R.id.rcImageView) ImageView mCenterImageView;
//    @BindView(R.id.rcNameTextView) TextView mCenterTextView;
//    @BindView(R.id.rcLocationTextView) TextView mCenterLocationTextView;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

//    @BindView(R.id.rcLocationTextView) TextView mLocationLabel;
//    @BindView(R.id.rcWebsiteTextView) TextView mWebLabel;

    private ResourceCenter mResourceCenter;
    private String mRecentAddress;

    // Instantiate adapter
    private ResourceCenterListAdapter mResourceCenterListAdapter;
    public List<ResourceCenter> resourceCenters = new ArrayList<>();
    ResourceCenter cipit = new ResourceCenter(R.drawable.cipit_logo,"Center for Intellectual Property and Information Technology", "https://goo.gl/maps/4UVcThYGBbqdmagR7", "https://cipit.org/");
    ResourceCenter kictaNet = new ResourceCenter(R.drawable.kictanet_logo, "Kenya ICT Action Network", "https://www.kictanet.or.ke/?page_id=28888", "https://www.kictanet.or.ke/");
    ResourceCenter iHub = new ResourceCenter(R.drawable.ihub_logo, "iHub Kenya", "6th Floor, Senteu Plaza, Galana / Lenana Road Junction, Nairobi Kenya.", "https://ihub.co.ke/");
    ResourceCenter uraia = new ResourceCenter(R.drawable.uraia_logo, "Uraia","https://goo.gl/maps/v5YxJfSXccMJ6FEQ8","https://uraia.or.ke/");
    ResourceCenter katibaInstitutte = new ResourceCenter(R.drawable.katiba_logo, "Katiba Institute", "Off Arwings Kodhek Road, Hurlingham, Rose Ave, Nairobi", "https://www.katibainstitute.org/");
    ResourceCenter knchr = new ResourceCenter(R.drawable.knchr_logo, "Kenya National Commission on Human Rights", "Ist Floor,CVS Plaza,Lenana Road, P.O Box 74359-00200, Nairobi", "https://www.knchr.org/");
    ResourceCenter khrc = new ResourceCenter(R.drawable.khrc_logo, "Kenya Human Rights Commission", "Gitanga Road opp. Valley Arcade Shopping Center,\n" +
            "P.O Box 41079-00100, Nairobi, Kenya", "https://www.khrc.or.ke/");

    // add member variables to store reference and edit it.
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    // add variable to hold selected city
    private  static String location;

    // add db reference
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_center);
        ButterKnife.bind(this);

       mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mResourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected, you can retrieve the selected item using
                location = String.valueOf(parent.getItemAtPosition(position));
                addToSharedPreferences(location);
                // retrieve shared preference
                mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
                Log.d(TAG, mRecentAddress);
//        Log.e("Item Selected", "onItemSelected: " + parent.getItemAtPosition(pos));
                String city = parent.getItemAtPosition(position).toString();
                Log.e("Item Selected", "onItemSelected: " + city);
                if (city.equals("Nairobi")) {
//            Picasso.get().load(R.drawable.cipit_logo).into(mCenterImageView);
//            Log.e("Image tagged", "image set: " + mCenterImageView);
//            mCenterTextView.setText("Cipit");
//            Log.e("text set", "text set: " + mCenterTextView);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Set relative Url parameters
        GeoDBApiInterface client = GeoDBClient.getClient();
        String countryIds = "ke";
        int limit = 10;
        int minPopulation = 65000;
        String sort = "-population";
        Call<GeoDBCitiesSearchResponse> call = client.getCities(countryIds, limit, minPopulation, sort);
        Log.e("CALL", "I have: "+call.toString() );

        call.enqueue(new Callback<GeoDBCitiesSearchResponse>() {
            @Override
            public void onResponse(Call<GeoDBCitiesSearchResponse> call, Response<GeoDBCitiesSearchResponse> response) {
                if(response.isSuccessful()) {
                    List<Datum> datumList = response.body().getData();
                    String[] cities = new String[datumList.size()];

                    for (int i = 0; i < cities.length; i++) {
                        cities[i] = datumList.get(i).getName();
                    }
                    ArrayAdapter citiesAdapter = new ArrayAdapter(ResourceCenterActivity.this, android.R.layout.simple_list_item_1, cities);
                    mResourceSpinner.setAdapter(citiesAdapter);
                    mResourceSpinner.setPrompt("Select your City");

                    resourceCenters.add(cipit);
                    resourceCenters.add(kictaNet);
                    resourceCenters.add(iHub);
                    resourceCenters.add(uraia);
                    resourceCenters.add(katibaInstitutte);
                    resourceCenters.add(knchr);
                    resourceCenters.add(khrc);

                    // Add resource centers to db
                    ref.child("Resource Centers").push().setValue(resourceCenters);

                    // Associate adapter with recycler view
                    mResourceCenterListAdapter = new ResourceCenterListAdapter(ResourceCenterActivity.this, resourceCenters);
                    mRecyclerView.setAdapter(mResourceCenterListAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ResourceCenterActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<GeoDBCitiesSearchResponse> call, Throwable t) {

            }
        });
    }
    // Define the selection event handler for the spinner with the corresponding callback method.
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }
    // Method to add to shared preferences, takes city as argument.
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}
