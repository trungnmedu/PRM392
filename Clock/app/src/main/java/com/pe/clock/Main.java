package com.pe.clock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.clock.database.Database;
import com.pe.clock.entity.Clock;
import com.pe.clock.provider.CarContentProvider;
import com.pe.clock.viewholder.ClockListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private List<Clock> getAllCar() {
        List<Clock> clockList = new ArrayList<>();


        Cursor cursor = getContentResolver().query(CarContentProvider.CONTENT_URI, Database.COLUMNS, null, null, null);

        if (cursor == null) {
            return clockList;
        }

        while (cursor.moveToNext()) {
            int first = cursor.getInt(0);
            String second = cursor.getString(1);
            int third = cursor.getInt(2);
            Clock clock = new Clock(first, second, third);
            clockList.add(clock);
        }
        cursor.close();
        return clockList;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.Clocks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Clock> Clocks = getAllCar();
        ClockListAdapter clockListAdapter = ClockListAdapter.getInstance();
        clockListAdapter.setItemList(Clocks);
        recyclerView.setAdapter(clockListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initRecyclerView();
    }

    public void navigateUpdate(View view) {
        Intent update = new Intent(Main.this, Update.class);
        startActivity(update);
        finish();
    }

    public void navigateCreate(View view) {
        Intent create = new Intent(Main.this, Create.class);
        startActivity(create);
        finish();
    }
}