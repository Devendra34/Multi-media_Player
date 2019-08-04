package com.example.stopwatch;

import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoHolder extends RecyclerView.ViewHolder {

    ImageView img;
    public VideoHolder(@NonNull View itemView) {
        super(itemView);
        img = (ImageView)itemView.findViewById(R.id.video);
    }
}
