package com.prm.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void handleLogin(View view) {
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        String username  = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(username.equals("admin") && password.equals("123456789")){
            Intent homeIntent = new Intent(this, CrudBasicActivity.class);
            homeIntent.putExtra("username", username);
            startActivity(homeIntent);
            finish();
        }else {
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
        }
    }
}