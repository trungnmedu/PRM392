package com.pe.se.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.se.R;

public class ItemHolder extends RecyclerView.ViewHolder{

    public TextView firstTextView;
    public TextView secondTextView;
    public TextView thirdTextView;
    public Button deleteButton;

    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        firstTextView = itemView.findViewById(R.id.firstProperty);
        secondTextView = itemView.findViewById(R.id.secondProperty);
        thirdTextView = itemView.findViewById(R.id.thirdProperty);
        deleteButton = itemView.findViewById(R.id.deleteAction);
    }
}
