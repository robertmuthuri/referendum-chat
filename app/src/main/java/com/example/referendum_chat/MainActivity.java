package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.oneOOneButton) Button mFindOneOOneButton;
    @BindView(R.id.findResourceButton) Button mFindResourceButton;
    @BindView(R.id.findRefeButton) Button mFindRefeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindOneOOneButton.setOnClickListener(this);
        mFindResourceButton.setOnClickListener(this);
        mFindRefeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mFindOneOOneButton) {
//            Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, OneOOneActivity.class);
            startActivity(intent);
        }
        else if (v == mFindResourceButton) {
            Intent intent = new Intent(MainActivity.this, ResourceCenterActivity.class);
            startActivity(intent);
        }
        else if (v == mFindRefeButton) {Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();}
    }
}
