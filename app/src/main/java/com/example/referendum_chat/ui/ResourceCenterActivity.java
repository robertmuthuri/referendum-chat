package com.example.referendum_chat.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.referendum_chat.Constants;
import com.example.referendum_chat.models.GeoDBCitiesSearchResponse;
import com.example.referendum_chat.R;
import com.example.referendum_chat.models.ResourceCenter;
import com.example.referendum_chat.adapters.ResourceCenterListAdapter;
import com.example.referendum_chat.models.Datum;
import com.example.referendum_chat.network.GeoDBApiInterface;
import com.example.referendum_chat.network.GeoDBClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceCenterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ResourceCenterActivity";
    @BindView(R.id.resourceSpinner) Spinner mResourceSpinner;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

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
    private String mRecentAddress;

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

        getCities();
        // call item touch helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    private void getCities() {
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

    // inflate the menu search layout and retrieve search view
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
//                getCities(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
    // ensure parent class functionality applies despite manually overriding portiong of the menu functionality.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // swipe gesture
    List<ResourceCenter> archivedCenters = new ArrayList<>();
    ResourceCenter deleteCenter = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deleteCenter = resourceCenters.get(position);
                    resourceCenters.remove(position);
                    mResourceCenterListAdapter.notifyItemRemoved(position);
                    Snackbar.make(mRecyclerView, "Resource Center Archived", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    resourceCenters.add(position, deleteCenter);
                                    mResourceCenterListAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    final ResourceCenter center = resourceCenters.get(position);
                    archivedCenters.add(center);

                    resourceCenters.remove(position);
                    mResourceCenterListAdapter.notifyItemRemoved(position);
                    Snackbar.make(mRecyclerView, "Resource Center Archived", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    archivedCenters.remove(archivedCenters.lastIndexOf(center));
                                    resourceCenters.add(position, center);
                                    mResourceCenterListAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;
            }
        }
    // Decorator
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark))
                .addSwipeLeftActionIcon(R.drawable.ic_delete_black_40dp)
                .addSwipeRightBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default))
                .addSwipeRightActionIcon(R.drawable.ic_archive_black_40dp)
                .create()
                .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}
