package com.andrew.bakingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.delegate.OnItemClickListener;
import com.andrew.bakingapp.model.Bake;
import com.andrew.bakingapp.viewholder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private static final int EMPTY = 0;

    private List<Bake> bakeList;
    private OnItemClickListener itemClickListener;

    public RecyclerAdapter(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        bakeList = new ArrayList<>();
    }

    public void setBakeList(List<Bake> bakeList) {
        this.bakeList = bakeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == EMPTY ? R.layout.recycler_item_empty : R.layout.recycler_main;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) != EMPTY){
            RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
            final Bake bake = bakeList.get(position);
            if(bake != null){
                viewHolder.setData(bake);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(bake);
                    }
                });

                viewHolder.tvDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(bake);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return bakeList.size() == EMPTY ? 1 : bakeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return bakeList.size() == EMPTY ? EMPTY : 2;
    }
}
