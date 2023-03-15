package com.pe.clock.viewholder;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.clock.R;
import com.pe.clock.database.Database;
import com.pe.clock.entity.Clock;
import com.pe.clock.provider.CarContentProvider;


import java.util.ArrayList;
import java.util.List;

public class ClockListAdapter extends RecyclerView.Adapter<ClockHolder> {
    private static ClockListAdapter instance;
    private List<Clock> clockList;


    public static ClockListAdapter getInstance() {
        if (instance == null) {
            instance = new ClockListAdapter();
        }
        return instance;
    }

    @NonNull
    @Override
    public ClockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.clock, parent, false);
        return new ClockHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ClockHolder holder, int position) {
        Clock clock = clockList.get(position);
        holder.idTextView.setText(String.valueOf(clock.getId()));
        holder.modelTextView.setText(String.valueOf(clock.getName()));
        holder.priceTextView.setText(String.valueOf(clock.getPrice()));

        View.OnClickListener handleDelete = view -> {
            Context context = view.getContext();
            ContentResolver contentResolver = context.getContentResolver();

            //Delete
            String where = "id = ?";
            String[] whereClauses = {String.valueOf(clock.getId())};
            contentResolver.delete(CarContentProvider.CONTENT_URI, where, whereClauses);

            //Reload list
            List<Clock> Clocks = new ArrayList<>();
            Cursor cursor = context.getContentResolver().query(CarContentProvider.CONTENT_URI, Database.COLUMNS, null, null, null);

            while (cursor.moveToNext()) {
                int first = cursor.getInt(0);
                String second = cursor.getString(1);
                int third = cursor.getInt(2);
                Clock clock1 = new Clock(first, second, third);
                Clocks.add(clock1);
            }

            cursor.close();
            setItemList(Clocks);
        };
        holder.deleteButton.setOnClickListener(handleDelete);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<Clock> clockList) {
        this.clockList = clockList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (clockList == null) {
            return 0;
        }
        return clockList.size();
    }
}
