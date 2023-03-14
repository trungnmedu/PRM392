package com.pe.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pe.se.context.Database;
import com.pe.se.context.Provider;

public class CreateActivity extends AppCompatActivity {
    private EditText firstEditText;
    private EditText secondEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        firstEditText = findViewById(R.id.createFirstEditText);
        secondEditText = findViewById(R.id.createSecondEditText);
    }

    public void create(View view) {
        try {
            String first = firstEditText.getText().toString().trim();
            int second = Integer.parseInt(secondEditText.getText().toString().trim());

            ContentValues item = new ContentValues();
            item.put("model", first);
            item.put("price", second);

            getContentResolver().insert(Provider.CONTENT_URI, item);
            firstEditText.setText("");
            secondEditText.setText("");
            Toast.makeText(this, "Create success!", Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException numberFormatException){
            Toast.makeText(this, "Price is not valid!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createBackToHome(View view) {
        Intent home = new Intent(CreateActivity.this, MainActivity.class);
        startActivity(home);
        finish();
    }
}