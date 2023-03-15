package com.pe.car.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.car.R;


public class CarHolder extends RecyclerView.ViewHolder {

    public TextView idTextView;
    public TextView modelTextView;
    public TextView priceTextView;
    public Button deleteButton;

    public CarHolder(@NonNull View itemView) {
        super(itemView);
        idTextView = itemView.findViewById(R.id.carId);
        modelTextView = itemView.findViewById(R.id.carModel);
        priceTextView = itemView.findViewById(R.id.carPrice);
        deleteButton = itemView.findViewById(R.id.deleteAction);
    }
}
