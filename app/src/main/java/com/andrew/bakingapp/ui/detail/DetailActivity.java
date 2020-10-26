package com.andrew.bakingapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.adapter.SingleTextAdapter;
import com.andrew.bakingapp.adapter.StepAdapter;
import com.andrew.bakingapp.database.entity.BakeEntity;
import com.andrew.bakingapp.model.Bake;
import com.andrew.bakingapp.video.VideoDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements StepAdapter.onItemClickVideo, View.OnClickListener, DetailCallback {

    public static final String DATA = "data";

    private DetailViewModel viewModel;
    private Bake bake;
    private ImageButton imgBtnFavorite;
    private VideoDialog videoDialog;

    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initVar();
    }

    private void initVar() {
        videoDialog = new VideoDialog(this);

        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        viewModel.setCallback(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            String data = intent.getStringExtra(DATA);
            Gson gson = new Gson();
            Bake bake = gson.fromJson(data, Bake.class);
            setData(bake);
        }
    }

    private void setData(Bake data) {
        this.bake = data;
        TextView tvName = findViewById(R.id.tv_name_detail);
        TextView tvServing = findViewById(R.id.tv_amount_servings_detail);
        RecyclerView recyclerIngredient = findViewById(R.id.recycler_ingredient_detail);
        RecyclerView recyclerStep = findViewById(R.id.recycler_step_detail);
        ImageView imageView = findViewById(R.id.img_detail);

        imgBtnFavorite = findViewById(R.id.img_btn_star_detail);

        SingleTextAdapter singleTextAdapter = new SingleTextAdapter(data.getIngredientList());
        StepAdapter stepAdapter = new StepAdapter(data.getStepList(), this);

        BakeEntity entity = new BakeEntity();
        entity.setId(data.getId());

        viewModel.getFavoriteBake(entity);

        recyclerIngredient.setLayoutManager(new LinearLayoutManager(this));
        recyclerStep.setLayoutManager(new LinearLayoutManager(this));

        recyclerIngredient.setAdapter(singleTextAdapter);
        recyclerStep.setAdapter(stepAdapter);

        String serving = "Number of servings : " + data.getServings();

        tvName.setText(data.getName());
        tvServing.setText(serving);

        if (!data.getImage().isEmpty())
            Picasso.get()
                    .load(data.getImage())
                    .into(imageView);

        imgBtnFavorite.setOnClickListener(this);
    }

    @Override
    public void onVideoClick(Bake.Step step) {
        videoDialog.releasePlayer();
        if (step.getVideoURL().isEmpty()) {
            Toast.makeText(this, "There is no video at the moment", Toast.LENGTH_SHORT).show();
        } else {
            videoDialog.setURL(step.getVideoURL());
            videoDialog.setDescription(step.getDescription());
            videoDialog.show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_btn_star_detail) {
            if (isFavorite) {
                viewModel.deleteData(bake);
            } else {
                viewModel.insertData(bake);
            }
        }
    }

    @Override
    public void onFavoriteChanges(boolean isFavorite) {
        this.isFavorite = isFavorite;
        int drawable = isFavorite ? R.drawable.ic_star_orange : R.drawable.ic_star_orange_borderline;
        imgBtnFavorite.setImageResource(drawable);
    }
}