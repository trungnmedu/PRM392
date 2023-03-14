package com.pe.se.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.se.R;
import com.pe.se.context.Database;
import com.pe.se.context.Provider;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ItemHolder>{
    private static ListViewAdapter instance;
    private List<Item> itemList;


    public static ListViewAdapter getInstance(){
        if(instance == null){
            instance = new ListViewAdapter();
        }
        return instance;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemList.get(position);
        holder.firstTextView.setText(String.valueOf(item.getFirst()));
        holder.secondTextView.setText(String.valueOf(item.getSecond()));
        holder.thirdTextView.setText(String.valueOf(item.getThird()));

        View.OnClickListener handleDelete = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                ContentResolver contentResolver = context.getContentResolver();

                //Delete
                String where = "id = ?";
                String[] whereClauses = {String.valueOf(item.getFirst())};
                contentResolver.delete(Provider.CONTENT_URI, where, whereClauses);

                //Reload list
                List<Item> items = new ArrayList<>();
                Cursor cursor = context.getContentResolver().query(Provider.CONTENT_URI, Database.COLUMNS, null, null, null);

                while (cursor.moveToNext()) {
                    int first = cursor.getInt(0);
                    String second = cursor.getString(1);
                    int third = cursor.getInt(2);
                    Item item = new Item(first, second, third);
                    items.add(item);
                }

                cursor.close();
                setItemList(items);
            }
        };
        holder.deleteButton.setOnClickListener(handleDelete);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(itemList == null){
            return  0;
        }
        return itemList.size();
    }
}
