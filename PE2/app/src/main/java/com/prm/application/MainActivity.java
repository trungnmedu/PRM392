package com.prm.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.prm.application.context.AppContentProvider;
import com.prm.application.view.Item;
import com.prm.application.view.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText firstEditText;
    private EditText secondEditText;
    private EditText thirdEditText;

    private List<Item> getItemsFromContentProvider() {
        List<Item> itemList = new ArrayList<>();

        String[] selectColumns = {"id", "model", "price"};
        Cursor cursor = getContentResolver().query(AppContentProvider.CONTENT_URI, selectColumns, null, null, null);

        if (cursor == null) {
            return itemList;
        }

        while (cursor.moveToNext()) {
            int first = cursor.getInt(0);
            String second = cursor.getString(1);
            int third = cursor.getInt(2);

            Item item = new Item(first, second, third);
            itemList.add(item);
        }
        cursor.close();
        return itemList;
    }

    private void initRecyclerView (){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = getItemsFromContentProvider();
        RecyclerViewAdapter listRecyclerViewAdapter = RecyclerViewAdapter.getInstance();
        listRecyclerViewAdapter.setItemList(itemList);
        recyclerView.setAdapter(listRecyclerViewAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstEditText = findViewById(R.id.firstInput);
        secondEditText = findViewById(R.id.secondInput);
        thirdEditText = findViewById(R.id.thirdInput);
        initRecyclerView();
    }

    public void add(View view) {
        try {
            String second = secondEditText.getText().toString().trim();
            int third = Integer.parseInt(thirdEditText.getText().toString().trim());

            if(third <= 0){
                Toast.makeText(this, "Value is not valid!", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues item = new ContentValues();
            item.put("model", second);
            item.put("price", third);
            getContentResolver().insert(AppContentProvider.CONTENT_URI, item);
            RecyclerViewAdapter.getInstance().setItemList(getItemsFromContentProvider());
        }catch (NumberFormatException formatException){
            Toast.makeText(this, "Value is not valid!", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
        try {
            int first = Integer.parseInt(firstEditText.getText().toString().trim());
            String second = secondEditText.getText().toString().trim();
            int third = Integer.parseInt(thirdEditText.getText().toString().trim());

            ContentValues student = new ContentValues();
            student.put("id", first);
            student.put("model", second);
            student.put("price", third);

            boolean isExist = false;
            String[] columns = {"id", "model", "price"};
            String where = "id = ?";
            String[] whereClauses = {String.valueOf(first)};

            Cursor cursor = getContentResolver().query(
                    AppContentProvider.CONTENT_URI,
                    columns,
                    where,
                    whereClauses,
                    null
            );
            isExist = cursor.moveToFirst();
            cursor.close();
            if(isExist){
                getContentResolver().update(AppContentProvider.CONTENT_URI, student, where, whereClauses);
                RecyclerViewAdapter.getInstance().setItemList(getItemsFromContentProvider());
            }else {
                Toast.makeText(this, "Student ID not exist!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}