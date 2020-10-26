package com.andrew.bakingapp.video;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.andrew.bakingapp.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoDialog extends DialogFragment {

    private SimpleExoPlayer simpleExoPlayer;
    private PlayerView playerView;
    private Context mContext;
    private String URL;
    private String description;

    public VideoDialog(Context mContext) {
        this.mContext = mContext;
        URL = "";
        description = "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();

        TextView tvDescription = view.findViewById(R.id.tv_description_dialog);
        playerView = view.findViewById(R.id.pv_dialog);

        tvDescription.setText(description);
        initPlayer();
    }

    private void initPlayer() {
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(URL));

        simpleExoPlayer = new SimpleExoPlayer.Builder(mContext).build();
        playerView.setPlayer(simpleExoPlayer);

        simpleExoPlayer.addMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.setPlayWhenReady(true);
    }

    public void releasePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
