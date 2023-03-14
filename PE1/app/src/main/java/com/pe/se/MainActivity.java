package com.pe.se;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.pe.se.context.Database;
import com.pe.se.context.Provider;
import com.pe.se.view.Item;
import com.pe.se.view.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> getItemsFromContentProvider() {
        List<Item> itemList = new ArrayList<>();


        Cursor cursor = getContentResolver().query(Provider.CONTENT_URI, Database.COLUMNS, null, null, null);

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
        RecyclerView recyclerView = findViewById(R.id.items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = getItemsFromContentProvider();
        ListViewAdapter listViewAdapter = ListViewAdapter.getInstance();
        listViewAdapter.setItemList(itemList);
        recyclerView.setAdapter(listViewAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    public void navigateUpdate(View view) {
        Intent update = new Intent(MainActivity.this, UpdateActivity.class);
        startActivity(update);
        finish();
    }

    public void navigateCreate(View view) {
        Intent create = new Intent(MainActivity.this, CreateActivity.class);
        startActivity(create);
        finish();
    }
}