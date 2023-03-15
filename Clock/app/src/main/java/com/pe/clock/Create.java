package com.pe.clock;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pe.clock.provider.CarContentProvider;

public class Create extends AppCompatActivity {
    private EditText nameEditText;
    private EditText priceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        nameEditText = findViewById(R.id.createNameEditText);
        priceEditText = findViewById(R.id.createPriceEditText);
    }

    public void create(View view) {
        try {
            String name = nameEditText.getText().toString().trim();
            int price = Integer.parseInt(priceEditText.getText().toString().trim());

            if (name.length() == 0) {
                Toast.makeText(this, "Invalid clock name!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (price < 0) {
                Toast.makeText(this, "Invalid clock price!", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues clock = new ContentValues();
            clock.put("name", name);
            clock.put("price", price);

            getContentResolver().insert(CarContentProvider.CONTENT_URI, clock);
            nameEditText.setText("");
            priceEditText.setText("");
            Toast.makeText(this, "Create success!", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException numberFormatException) {
            Toast.makeText(this, "Price is not valid!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createBackToHome(View view) {
        Intent home = new Intent(Create.this, Main.class);
        startActivity(home);
        finish();
    }
}