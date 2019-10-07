package com.example.referendum_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    @Override
    public void onClick(View v){
        if(v == mFindOneOOneButton) {
         //
        }
    }
}
