package com.pe.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.pe.car.database.Database;
import com.pe.car.entity.Car;
import com.pe.car.provider.CarContentProvider;
import com.pe.car.viewholder.CarListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    private List<Car> getAllCar() {
        List<Car> carList = new ArrayList<>();


        Cursor cursor = getContentResolver().query(CarContentProvider.CONTENT_URI, Database.COLUMNS, null, null, null);

        if (cursor == null) {
            return carList;
        }

        while (cursor.moveToNext()) {
            int first = cursor.getInt(0);
            String second = cursor.getString(1);
            int third = cursor.getInt(2);
            Car car = new Car(first, second, third);
            carList.add(car);
        }
        cursor.close();
        return carList;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Car> cars = getAllCar();
        CarListAdapter carListAdapter = CarListAdapter.getInstance();
        carListAdapter.setItemList(cars);
        recyclerView.setAdapter(carListAdapter);
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