package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceCenterActivity extends AppCompatActivity {
    @BindView(R.id.resourceSpinner) Spinner mResourceSpinner;
    @BindView(R.id.kenyanCitiesListView) ListView mKenyanCitiesListView;

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
//        mResourceSpinner.setAdapter(adapter);

        GeoDBApiInterface client = GeoDBClient.getClient();
        String country = "countryIds";
        Call<GeoDBCitiesSearchResponse> call = client.getCities(country, "ke");

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

}
