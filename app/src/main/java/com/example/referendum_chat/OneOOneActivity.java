package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneOOneActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView mListView;

    private String[] basics = new Strin[]{ "Definition: a general vote by the electorate on a single political question which has been referred to them for a direct decision.", "Topics: in Kenya a referendum is limited to the following:" "The supremacy of the Constitution", "The territory of Kenya", "The sovereignty of the people", "The national values and principles of governance", "The bill of rights", "The term of office of the President", "The independence of the judiciary, the commissions, and independent offices in the Consitution", "The objects, priplciples ans structure of devolved government", "Amendment of the Constitution", "Approval: A referendum is approved by at least 20% of the registered voters in at least half of the counties vote in the referendum", "The amendment is supported by a simple majority of the citizens voting in a referendum", "Proposal - an amendment to the Constitution may be proposed by:", "a) bill introduced in either House of Parliament", " b) a popular initiative signed by at least one million registered voters."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_oone);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, basics);
        mListView.setAdapter(adapter);
    }


}
