package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneOOneActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.imageView) ImageView mImageView;

    private String[] basics = new String[]{ "A referendum is general vote by the electorate on a single political question which has been referred to them for a direct decision.\n", "The supremacy of the Constitution\n\nThe territory of Kenya \n\nThe sovereignty of the people\n\nThe national values and principles of governance\n\nThe bill of rights\n\nThe term of office of the President\n\nThe independence of the Judiciary, the commissions, and independent offices in the Constitution\n\nThe objects, principles and structure of devolved government, and Amendment of the Constitution \n", "a) A bill introduced in either House of Parliament\n\nb) A popular initiative signed by at least one million registered voters.\n", "\na) At least 20% of the registered voters in at least half of the counties vote in the referendum, and\n\nb) The amendment is supported by a simple majority of the citizens voting in a referendum\n"};

    private String[] headers = new String[] {"DEFINITION:\n", "REFERENDUM TOPICS:\n", "WHO CAN PROPOSE A REFERENDUM:\n", "APPROVAL CRITERIA:\n"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_oone);
        ButterKnife.bind(this);
//        Picasso.with(this).load(R.drawable.kenya).into(mImageView);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, basics);
        MyOneOOneArrayAdapter adapter = new MyOneOOneArrayAdapter(this, android.R.layout.simple_list_item_1, headers, basics);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(OneOOneActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

    }

}
