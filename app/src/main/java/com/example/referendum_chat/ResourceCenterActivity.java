package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceCenterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.resourceSpinner) Spinner mResourceSpinner;
//    @BindView(R.id.kenyanCitiesListView) ListView mKenyanCitiesListView;
    @BindView(R.id.centerImageView) ImageView mCenterImageView;
    @BindView(R.id.centerTextView) TextView mCenterTextView;
    @BindView(R.id.centerLocationTextView) TextView mCenterLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_center);
        ButterKnife.bind(this);

    // Create an ArrayAdapter using the string array and a default spinner layout.
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kenyan_towns_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        // mResourceSpinner.setAdapter(adapter);
        mResourceSpinner.setOnItemSelectedListener(this);

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
                }
            }

            @Override
            public void onFailure(Call<GeoDBCitiesSearchResponse> call, Throwable t) {

            }
        });
    }
    // Define the selection event handler for the spinner with the corresponding callback method.
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected, you can retrieve the selected item using
//        parent.getItemAtPosition(pos);
//        Log.e("Item Selected", "onItemSelected: " + parent.getItemAtPosition(pos));
        String city = parent.getItemAtPosition(pos).toString();
        Log.e("Item Selected", "onItemSelected: " + city);
        if (city.equals("Nairobi")) {
            Picasso.get().load(R.drawable.cipit_logo).into(mCenterImageView);
            Log.e("Image tagged", "image set: " + mCenterImageView);
            mCenterTextView.setText("Cipit");
            Log.e("text set", "text set: " + mCenterTextView);

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }
}
