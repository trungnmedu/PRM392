package com.pe.car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pe.car.provider.CarContentProvider;

public class Create extends AppCompatActivity {

    private EditText modelEditText;
    private EditText priceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        modelEditText = findViewById(R.id.createModelEditText);
        priceEditText = findViewById(R.id.createPriceEditText);
    }

    public void create(View view) {
        try {
            String model = modelEditText.getText().toString().trim();
            int price = Integer.parseInt(priceEditText.getText().toString().trim());

            if (model.length() == 0) {
                Toast.makeText(this, "Invalid car model!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (price < 0) {
                Toast.makeText(this, "Invalid car price!", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues car = new ContentValues();
            car.put("model", model);
            car.put("price", price);

            getContentResolver().insert(CarContentProvider.CONTENT_URI, car);
            modelEditText.setText("");
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