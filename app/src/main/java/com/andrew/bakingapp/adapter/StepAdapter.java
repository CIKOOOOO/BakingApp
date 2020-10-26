package com.andrew.bakingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.model.Bake;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.Holder> {

    private List<Bake.Step> stepList;
    private onItemClickVideo onItemClickVideo;

    public interface onItemClickVideo {
        void onVideoClick(Bake.Step step);
    }

    public StepAdapter(List<Bake.Step> stepList, StepAdapter.onItemClickVideo onItemClickVideo) {
        this.stepList = stepList;
        this.onItemClickVideo = onItemClickVideo;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_step_detail, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Bake.Step step = stepList.get(position);
        if (step != null) {
            String data = position == 0 ? step.getShortDescription() : position + ". " + step.getShortDescription();

            holder.textView.setText(data);
            holder.imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickVideo.onVideoClick(step);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageButton imgBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_tv_short_description);
            imgBtn = itemView.findViewById(R.id.recycler_img_btn_video);
        }
    }
}
