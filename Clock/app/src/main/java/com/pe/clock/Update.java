package com.pe.clock;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pe.clock.database.Database;
import com.pe.clock.provider.CarContentProvider;

public class Update extends AppCompatActivity {
    private EditText idEditText;
    private EditText modelEditText;
    private EditText priceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        idEditText = findViewById(R.id.updateIdEditText);
        modelEditText = findViewById(R.id.updateNameEditText);
        priceEditText = findViewById(R.id.updatePriceEditText);
    }

    public void update(View view) {
        try {
            int id = Integer.parseInt(idEditText.getText().toString().trim());
            String name = modelEditText.getText().toString().trim();
            int price = Integer.parseInt(priceEditText.getText().toString().trim());

            if (price < 0) {
                Toast.makeText(this, "Invalid clock price!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (name.length() == 0) {
                Toast.makeText(this, "Clock name invalid!", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues carUpdate = new ContentValues();
            carUpdate.put("id", id);
            carUpdate.put("name", name);
            carUpdate.put("price", price);

            boolean isExist = false;
            String where = "id = ?";
            String[] whereClauses = {String.valueOf(id)};
            Cursor cursor = getContentResolver().query(
                    CarContentProvider.CONTENT_URI,
                    Database.COLUMNS,
                    where,
                    whereClauses,
                    null
            );
            isExist = cursor.moveToFirst();
            cursor.close();

            if (!isExist) {
                String message = String.format("%s %s %s", "Clock ID:", id, "not exist!");
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                return;
            }

            getContentResolver().update(CarContentProvider.CONTENT_URI, carUpdate, where, whereClauses);
            Toast.makeText(this, "Update clock success!", Toast.LENGTH_SHORT).show();
            idEditText.setText("");
            modelEditText.setText("");
            priceEditText.setText("");
        } catch (NumberFormatException numberFormatException) {
            Toast.makeText(this, "Invalid number format!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateBackToHome(View view) {
        Intent home = new Intent(Update.this, Main.class);
        startActivity(home);
        finish();
    }
}