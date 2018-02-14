package com.waracle.androidtest.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by arslanlodhi on 2/14/18.
 */

public class ImageUtils {

    /**
     * Converts Byte array into Bitmap
     * @param byteArray Byte Array
     */
    public  static Bitmap convertToBitmap(byte[] byteArray) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 100;
        options.outHeight = 100;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        return bitmap;
    }

    /**
     * Hide imageview
     * @param imageView ImageView reference
     */
    public static void clearImageViewBackground(ImageView imageView){
        if(imageView!=null)
            imageView.setVisibility(View.INVISIBLE);

    }

    /**
     * set Bitmap into ImageView
     * @param imageView ImageView reference
     *  @param bitmap bitmap reference
     */
    public  static void setImageView(ImageView imageView, Bitmap bitmap) {
        if(imageView!=null) {
            imageView.setVisibility(View.VISIBLE);
            if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            else
                clearImageViewBackground(imageView);
        }
    }
}
