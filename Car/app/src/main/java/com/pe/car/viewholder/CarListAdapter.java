package com.pe.car.viewholder;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.car.R;
import com.pe.car.database.Database;
import com.pe.car.entity.Car;
import com.pe.car.provider.CarContentProvider;


import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarHolder> {
    private static CarListAdapter instance;
    private List<Car> carList;


    public static CarListAdapter getInstance() {
        if (instance == null) {
            instance = new CarListAdapter();
        }
        return instance;
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car, parent, false);
        return new CarHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, int position) {
        Car car = carList.get(position);
        holder.idTextView.setText(String.valueOf(car.getFirst()));
        holder.modelTextView.setText(String.valueOf(car.getSecond()));
        holder.priceTextView.setText(String.valueOf(car.getThird()));

        View.OnClickListener handleDelete = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                ContentResolver contentResolver = context.getContentResolver();

                //Delete
                String where = "id = ?";
                String[] whereClauses = {String.valueOf(car.getFirst())};
                contentResolver.delete(CarContentProvider.CONTENT_URI, where, whereClauses);

                //Reload list
                List<Car> cars = new ArrayList<>();
                Cursor cursor = context.getContentResolver().query(CarContentProvider.CONTENT_URI, Database.COLUMNS, null, null, null);

                while (cursor.moveToNext()) {
                    int first = cursor.getInt(0);
                    String second = cursor.getString(1);
                    int third = cursor.getInt(2);
                    Car car = new Car(first, second, third);
                    cars.add(car);
                }

                cursor.close();
                setItemList(cars);
            }
        };
        holder.deleteButton.setOnClickListener(handleDelete);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<Car> carList) {
        this.carList = carList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (carList == null) {
            return 0;
        }
        return carList.size();
    }
}
