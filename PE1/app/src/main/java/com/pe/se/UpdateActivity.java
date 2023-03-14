package com.pe.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pe.se.context.Database;
import com.pe.se.context.Provider;

public class UpdateActivity extends AppCompatActivity {
    private EditText firstEditText;
    private EditText secondEditText;
    private EditText thirdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        firstEditText = findViewById(R.id.firstUpdateEditText);
        secondEditText = findViewById(R.id.secondUpdateEditText);
        thirdEditText = findViewById(R.id.thirdUpdateEditText);
    }

    public void update(View view) {
        try {
            int first = Integer.parseInt(firstEditText.getText().toString().trim());
            String second = secondEditText.getText().toString().trim();
            int third = Integer.parseInt(thirdEditText.getText().toString().trim());

            ContentValues update = new ContentValues();
            update.put("id", first);
            update.put("model", second);
            update.put("price", third);

            boolean isExist = false;
            String where = "id = ?";
            String[] whereClauses = {String.valueOf(first)};
            Cursor cursor = getContentResolver().query(
                    Provider.CONTENT_URI,
                    Database.COLUMNS,
                    where,
                    whereClauses,
                    null
            );
            isExist = cursor.moveToFirst();
            cursor.close();

            if(isExist){
                getContentResolver().update(Provider.CONTENT_URI, update, where, whereClauses);
                Toast.makeText(this, "Update success!", Toast.LENGTH_SHORT).show();
                firstEditText.setText("");
                secondEditText.setText("");
                thirdEditText.setText("");
            }else {
                Toast.makeText(this, "ID not exist!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateBackToHome(View view) {
        Intent home = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(home);
        finish();
    }
}