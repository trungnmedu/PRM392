package com.prm.mobile.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.mobile.R;

public class ItemHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView idTextView;
    public TextView scoreTextView;
    public Button deleteButton;



    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.itemStudentName);
        scoreTextView = itemView.findViewById(R.id.itemStudentScore);
        idTextView = itemView.findViewById(R.id.itemStudentId);
        deleteButton = itemView.findViewById(R.id.studentDelete);
    }


}
