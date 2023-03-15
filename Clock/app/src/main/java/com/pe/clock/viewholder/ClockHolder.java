package com.pe.clock.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.clock.R;


public class ClockHolder extends RecyclerView.ViewHolder {

    public TextView idTextView;
    public TextView modelTextView;
    public TextView priceTextView;
    public Button deleteButton;

    public ClockHolder(@NonNull View itemView) {
        super(itemView);
        idTextView = itemView.findViewById(R.id.clockId);
        modelTextView = itemView.findViewById(R.id.clockName);
        priceTextView = itemView.findViewById(R.id.clockPrice);
        deleteButton = itemView.findViewById(R.id.deleteAction);
    }
}
