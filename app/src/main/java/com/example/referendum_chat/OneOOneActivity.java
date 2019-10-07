package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneOOneActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;

    private String[] basics = new String[]{ "a general vote by the electorate on a single political question which has been referred to them for a direct decision.", "Topics: in Kenya a referendum is limited to the following:", "The supremacy of the Constitution, The territory of Kenya, The sovereignty of the people, The national values and principles of governance, The bill of rights, The term of office of the President, The independence of the judiciary, the commissions, and independent offices in the Constitution, The objects, principles and structure of devolved government, and Amendment of the Constitution", " a) A bill introduced in either House of Parliament", " b) A popular initiative signed by at least one million registered voters.", " a) At least 20% of the registered voters in at least half of the counties vote in the referendum, and b) The amendment is supported by a simple majority of the citizens voting in a referendum",};

    private String[] headers = new String[] {"Definition:", "Topics for which a referendum can be held:", "Proposal - an amendment to the Constitution may be proposed by: ", "Approval: A referendum is approved by: "}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_oone);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, basics);
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
