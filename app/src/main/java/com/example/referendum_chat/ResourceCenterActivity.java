package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class ResourceCenterActivity extends AppCompatActivity {
    @BindView(R.id.resourceSpinner) Spinner mResourceSpinner;

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
        mResourceSpinner.setAdapter(adapter);

        GeoDBApiInterface client = GeoDBClient.getClient();

//        Call<GeoDBCitiesSearchResponse> call = client.getCities(country, "term");

    }
}
