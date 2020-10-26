package com.andrew.bakingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.model.Bake;
import com.andrew.bakingapp.utils.Utils;
import com.andrew.bakingapp.viewholder.SingleTextViewHolder;

import java.util.List;

public class SingleTextAdapter extends RecyclerView.Adapter {

    private List<Bake.Ingredient> ingredientList;

    public SingleTextAdapter(List<Bake.Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_single_tv, parent, false);
        return new SingleTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SingleTextViewHolder viewHolder = (SingleTextViewHolder) holder;
        Bake.Ingredient ingredient = ingredientList.get(position);
        String data = "- " + ingredient.getIngredient() + " : " + Utils.removeZeroFromFloat(ingredient.getQuantity()) + " " + ingredient.getMeasure();
        viewHolder.setTextView(data);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
