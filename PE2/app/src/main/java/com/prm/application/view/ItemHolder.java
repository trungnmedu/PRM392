package com.prm.application.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.application.R;


public class ItemHolder extends RecyclerView.ViewHolder {
    public TextView firstTextView;
    public TextView secondTextView;
    public TextView thirdTextView;
    public Button deleteAction;

    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        firstTextView = itemView.findViewById(R.id.itemFirstProperty);
        secondTextView = itemView.findViewById(R.id.itemSecondProperty);
        thirdTextView = itemView.findViewById(R.id.itemThirdProperty);
        deleteAction = itemView.findViewById(R.id.actionDelete);
    }
}
