package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.io.File;

public class ImageViewer extends AppCompatActivity {

    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        Intent intent = getIntent();
        path = intent.getStringExtra("imgPath");
        ImageView imageView = findViewById(R.id.viewImg);
        Glide.with(this).load(new File(path)).into(imageView);
    }
}