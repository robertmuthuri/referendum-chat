package com.example.referendum_chat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.referendum_chat.Constants;
import com.example.referendum_chat.R;
import com.google.firebase.auth.FirebaseAuth;

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
    // inflate logout menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // define logout action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // define logout method
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
