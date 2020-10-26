package com.andrew.bakingapp.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.model.Bake;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView tvDetail;

    private RoundedImageView roundedImageView;
    private TextView tvTitle;
    private TextView tvBody;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        roundedImageView = itemView.findViewById(R.id.recycler_img_view_item);
        tvTitle = itemView.findViewById(R.id.recycler_tv_title);
        tvBody = itemView.findViewById(R.id.recycler_tv_body);
        tvDetail = itemView.findViewById(R.id.recycler_tv_details);
    }

    public void setData(Bake data) {
        String body = "Servings : " + data.getServings();

        if (!data.getImage().isEmpty()){
            Picasso.get()
                    .load(data.getImage())
                    .fit()
                    .into(roundedImageView);
        }

        tvTitle.setText(data.getName());
        tvBody.setText(body);
    }
}
