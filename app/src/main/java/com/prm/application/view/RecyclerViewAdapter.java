package com.prm.application.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.application.R;
import com.prm.application.context.AppContentProvider;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ItemHolder> {
    private static RecyclerViewAdapter instance;
    private List<Item> itemList;

    public static RecyclerViewAdapter getInstance(){
        if(instance == null){
            instance = new RecyclerViewAdapter();
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemList.get(position);
        holder.firstTextView.setText(String.valueOf(item.getFirst()));
        holder.secondTextView.setText(item.getSecond());
        holder.thirdTextView.setText(String.valueOf(item.getThird()));

        View.OnClickListener handleDelete = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                ContentResolver contentResolver = context.getContentResolver();
                Item delete = new Item(
                       item.getFirst(),
                       item.getSecond(),
                       item.getThird()
                );
                String where = "id = ?";
                String[] whereClauses = {String.valueOf(item.getFirst())};
                contentResolver.delete(AppContentProvider.CONTENT_URI, where, whereClauses);

                //Reload list and re-render
                List<Item> itemList = new ArrayList<>();

                String[] selectColumns = {"id", "model", "price"};
                Cursor cursor = context.getContentResolver().query(AppContentProvider.CONTENT_URI, selectColumns, null, null, null);

                while (cursor.moveToNext()) {
                    int first = cursor.getInt(0);
                    String second = cursor.getString(1);
                    int third = cursor.getInt(2);
                    Item item = new Item(first, second, third);
                    itemList.add(item);
                }
                cursor.close();
                setItemList(itemList);
            }
        };
        holder.deleteAction.setOnClickListener(handleDelete);
    }

    @Override
    public int getItemCount() {
        if(itemList == null){
            return  0;
        }
        return itemList.size();
    }
}
