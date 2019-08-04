package com.example.stopwatch;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;

public class MyFrament extends Fragment {
    RecyclerView rv;
    TextView tv;
    Context context;
    ArrayList<Data> dataArrayList;

    public MyFrament(Context context) {
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.frament,container,false);
        rv = (RecyclerView) v.findViewById(R.id.fragrv);
            getAudio audio = new getAudio();
            audio.execute(getArguments().getString("message"));
            return v;
    }

    public class getAudio extends AsyncTask<String,Void,MyAdapter> {
        @Override
        protected void onPostExecute(MyAdapter adapter) {
            if(getArguments().getString("message") == "Music"){
                rv.setLayoutManager(new LinearLayoutManager(context));
                rv.setAdapter(adapter);
            } else {
                rv.setLayoutManager(new GridLayoutManager(context,2));
                rv.setAdapter(adapter);
            }
        }


        @Override
        protected MyAdapter doInBackground(String... strings) {
            dataArrayList = new ArrayList<>();
            int fileTitle,fileLocation, imgId;
            ContentResolver contentResolver =context.getContentResolver();

            if(strings[0]=="Music") {
                Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Cursor songcursor = contentResolver.query(songUri, null, null, null, null);

                if (songcursor != null && songcursor.moveToFirst()) {
                    fileTitle = songcursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    fileLocation = songcursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                    do {
                        String currentTitle = songcursor.getString(fileTitle);
                        String currentLocation = songcursor.getString(fileLocation);
                        Data data = new Data(currentTitle, currentLocation,true);
                        dataArrayList.add(data);
                    } while (songcursor.moveToNext());
                }
                Collections.sort(dataArrayList);
                MyAdapter adapter = new MyAdapter(dataArrayList, context,1);
                return adapter;

            }else if (strings[0] =="Photo") {
                Uri songUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Cursor songcursor = contentResolver.query(songUri, null, null, null, null);
                if (songcursor != null && songcursor.moveToFirst()) {
                    fileTitle = songcursor.getColumnIndex(MediaStore.Images.Media.TITLE);
                    fileLocation = songcursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    imgId = songcursor.getColumnIndex(MediaStore.Images.Media._ID);

                    do {
                        String currentTitle = songcursor.getString(fileTitle);
                        String currentLocation = songcursor.getString(fileLocation);
                        long id = songcursor.getLong(imgId);
                        Data data = new Data(currentTitle, currentLocation,false);
                        dataArrayList.add(data);

                    } while (songcursor.moveToNext());
                }
                Collections.sort(dataArrayList);
                MyAdapter adapter = new MyAdapter(dataArrayList, context,2);
                return adapter;
            } else {
                Uri songUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                Cursor songcursor = contentResolver.query(songUri, null, null, null, null);
                if (songcursor != null && songcursor.moveToFirst()) {
                    fileTitle = songcursor.getColumnIndex(MediaStore.Video.Media.TITLE);
                    fileLocation = songcursor.getColumnIndex(MediaStore.Video.Media.DATA);
                    imgId = songcursor.getColumnIndex(MediaStore.Video.Media._ID);

                    do {
                        String currentTitle = songcursor.getString(fileTitle);
                        String currentLocation = songcursor.getString(fileLocation);
                        long id = songcursor.getLong(imgId);
                        Data data = new Data(currentTitle, currentLocation,false);
                        dataArrayList.add(data);
                    } while (songcursor.moveToNext() );
                }
                Collections.sort(dataArrayList);
                MyAdapter adapter = new MyAdapter(dataArrayList, context,3);
                return adapter;
            }


        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
