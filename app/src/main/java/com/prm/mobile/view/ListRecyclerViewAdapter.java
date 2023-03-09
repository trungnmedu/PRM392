package com.prm.mobile.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.mobile.R;

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
    }

    @Override
    public int getItemCount() {
        if(itemList == null){
            return  0;
        }
        return itemList.size();
    }
}
