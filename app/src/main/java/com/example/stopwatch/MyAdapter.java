package com.example.stopwatch;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Data> dataArrayList;
    private Context context;
    private int n;
    private MediaPlayer mediaPlayer = null;
    public MyAdapter(ArrayList<Data> dataArrayList, Context context, int n) {
        this.dataArrayList = dataArrayList;
        this.context = context;
        this.n = n;
    }

    @Override
    public int getItemViewType(int position) {
        if(n == 2){
            return R.layout.cell;
        } else if(n == 1) {
            return  R.layout.text;
        } else
            return R.layout.video;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        if(viewType == R.layout.text)
            return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
        else if(viewType == R.layout.cell)
            return new ImgHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
        else
            return new VideoHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            final MyHolder myholder = (MyHolder) holder;
            myholder.textView.setText((CharSequence) (dataArrayList.get(position).getTitle()));
            myholder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaPlayer mediaPlayer1 = null;
                    if(mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    mediaPlayer1 = MediaPlayer.create(context, Uri.fromFile(new File(dataArrayList.get(position).getPath())));
                    mediaPlayer = mediaPlayer1;
                    mediaPlayer.start();
                }
            });
            myholder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer = null;
                    }
                    return true;
                }
            });
        } else if(holder instanceof ImgHolder) {
            ImgHolder imgHolder = (ImgHolder)holder;
            Glide.with(context).load(new File(dataArrayList.get(position).getPath())).centerCrop().into(imgHolder.img);
            imgHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ImageViewer.class);
                    intent.putExtra("imgPath",dataArrayList.get(position).getPath());
                    context.startActivity(intent);
                }
            });
        } else {

            VideoHolder videoHolder = (VideoHolder)holder;
            Glide.with(context).load(new File(dataArrayList.get(position).getPath())).centerCrop().into(videoHolder.img);
            videoHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,VideoPlayer.class);
                    intent.putExtra("path",dataArrayList.get(position).getPath());
                    context.startActivity(intent);
                }
            });
            /*MediaController mediaController = new MediaController(context);
            mediaController.setAnchorView(videoHolder.videoView);
            videoHolder.videoView.setMediaController(mediaController);
            videoHolder.videoView.start();*/
        }

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

}
