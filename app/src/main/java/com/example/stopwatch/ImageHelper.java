package com.example.stopwatch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageHelper {

    public static int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width >reqWidth){
            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while ((halfHeight / inSampleSize) >=reqHeight && (halfWidth/inSampleSize) >= reqWidth){
                inSampleSize+=2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampleBitmapFromPath(String path, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        options.inSampleSize = calculateSampleSize(options,reqWidth,reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path,options);
    }
}
