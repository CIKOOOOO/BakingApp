package com.andrew.bakingapp.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;


public class SingleTextViewHolder extends RecyclerView.ViewHolder {
    private TextView tvItem;

    public SingleTextViewHolder(@NonNull View itemView) {
        super(itemView);
        tvItem = itemView.findViewById(R.id.recycler_tv_single_tv);
    }

    public void setTextView(String data) {
        tvItem.setText(data);
    }
}
