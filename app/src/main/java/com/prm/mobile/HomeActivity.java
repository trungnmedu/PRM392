package com.prm.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent loginIntent = getIntent();
        String username = loginIntent.getStringExtra("username");

        TextView usernameTextView = findViewById(R.id.homeUsername);
        String welcome = "Welcome " + username;
        usernameTextView.setText(welcome);
    }
}