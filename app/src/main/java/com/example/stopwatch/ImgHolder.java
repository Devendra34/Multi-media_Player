package com.example.stopwatch;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class ImgHolder extends RecyclerView.ViewHolder {
    ImageView img;

    public ImgHolder(View itemView) {
        super(itemView);
        img =(ImageView)itemView.findViewById(R.id.img);
    }
}
