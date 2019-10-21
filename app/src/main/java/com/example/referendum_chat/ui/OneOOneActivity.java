package com.example.referendum_chat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.referendum_chat.adapters.MyOneOOneArrayAdapter;
import com.example.referendum_chat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneOOneActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.imageView) ImageView mImageView;

    @BindView(R.id.refWebsiteTextView) TextView mRefWebsiteLabel;

    private String[] basics = new String[]{ "A referendum is general vote by the electorate on a single political question which has been referred to them for a direct decision.\n", "The supremacy of the Constitution\n\nThe territory of Kenya \n\nThe sovereignty of the people\n\nThe national values and principles of governance\n\nThe bill of rights\n\nThe term of office of the President\n\nThe independence of the Judiciary, the commissions, and independent offices in the Constitution\n\nThe objects, principles and structure of devolved government, and Amendment of the Constitution \n", "a) A bill introduced in either House of Parliament\n\nb) A popular initiative signed by at least one million registered voters.\n", "\na) At least 20% of the registered voters in at least half of the counties vote in the referendum, and\n\nb) The amendment is supported by a simple majority of the citizens voting in a referendum\n"};

    private String[] headers = new String[] {"DEFINITION:\n", "REFERENDUM TOPICS:\n", "WHO CAN PROPOSE A REFERENDUM:\n", "APPROVAL CRITERIA:\n"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_oone);
        ButterKnife.bind(this);
//        Picasso.with(this).load(R.drawable.kenya).into(mImageView);

        mRefWebsiteLabel.setOnClickListener(this);

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
    @Override public void onClick(View v) {
        if (v == mRefWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kenyalaw.org:8181/exist/kenyalex/actview.xql?actid=Const2010#KE/CON/Const2010/chap_16"));
            startActivity(webIntent);
        }
    }

}
