package com.prm.mobile.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.mobile.R;
import com.prm.mobile.context.AppContentProvider;
import com.prm.mobile.context.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ItemHolder> {
    private static ListRecyclerViewAdapter instance;
    private List<Item> itemList;

    public static ListRecyclerViewAdapter getInstance(){
        if(instance == null){
            instance = new ListRecyclerViewAdapter();
        }
        return instance;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.scoreTextView.setText(String.valueOf(item.getScore()));
        holder.idTextView.setText(item.getId());

        View.OnClickListener handleDelete = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                ContentResolver contentResolver = context.getContentResolver();
                Item delete = new Item(
                       item.getId(),
                       item.getName(),
                       item.getScore()
                );
                String where = "id = ?";
                String[] whereClauses = {item.getId()};
                contentResolver.delete(AppContentProvider.CONTENT_URI, where, whereClauses);

                List<Item> itemList = new ArrayList<>();

                String[] selectColumns = {"id", "name", "score"};
                Cursor cursor = context.getContentResolver().query(AppContentProvider.CONTENT_URI, selectColumns, null, null, null);

                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    Item item = new Item(id, name, age);
                    itemList.add(item);
                }
                cursor.close();
                setItemList(itemList);

            }
        };
        holder.deleteButton.setOnClickListener(handleDelete);
    }

    @Override
    public int getItemCount() {
        if(itemList == null){
            return  0;
        }
        return itemList.size();
    }
}
