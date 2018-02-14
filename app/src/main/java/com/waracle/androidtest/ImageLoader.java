package com.waracle.androidtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidParameterException;

/**
 * Created by Riad on 20/05/2015.
 */
public class ImageLoader {

    private static final String TAG = ImageLoader.class.getSimpleName();
    private LruCache<String, Bitmap> mMemoryCache;

    public ImageLoader() { /**/


        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(key!=null && bitmap !=null) {
            if (getBitmapFromMemCache(key) == null) {
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        if(key!=null)
        return mMemoryCache.get(key);
        return null;
    }


    private class LoadImage extends AsyncTask<ImageObject, Void, ImageObject> {

        @Override
        protected ImageObject doInBackground(ImageObject... images) {
            ImageObject imageInfo = images[0];

                try {
                    Bitmap bitmap=convertToBitmap(loadImageData(imageInfo.getUrl()));
                    addBitmapToMemoryCache(imageInfo.getUrl(), bitmap);
                    imageInfo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }

            return imageInfo;
        }

        @Override
        protected void onPostExecute(ImageObject imageObjectInfo) {
            setImageView(imageObjectInfo.getImageView(), imageObjectInfo.getImageBitmap());

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

    /**
     * Simple function for loading a bitmap image from the web
     *
     * @param url       image url
     * @param imageView view to set image too.
     */


    public Bitmap load(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            throw new InvalidParameterException("URL is empty!");
        }
        Bitmap bmp=getBitmapFromMemCache(url);
        if ( bmp == null) {
        // Can you think of a way to improve loading of bitmaps
        // that have already been loaded previously??
            clearImageViewBackground(imageView);
            new LoadImage().execute(new ImageObject(url, imageView));
        } else {
            setImageView(imageView, bmp);
        }
        return bmp;
    }


    private static byte[] loadImageData(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        InputStream inputStream = null;
        try {
            try {
                // Read data from workstation
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                // Read the error from the workstation
                inputStream = connection.getErrorStream();
            }

            // Can you think of a way to make the entire
            // HTTP more efficient using HTTP headers??

            return StreamUtils.readUnknownFully(inputStream);
        } finally {
            // Close the input stream if it exists.
            StreamUtils.close(inputStream);

            // Disconnect the connection
            connection.disconnect();
        }
    }

    private static Bitmap convertToBitmap(byte[] byteArray) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 100;
        options.outHeight = 100;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);

        return bitmap;
    }
    static void clearImageViewBackground(ImageView imageView){
        if(imageView!=null)
        imageView.setVisibility(View.INVISIBLE);

    }
    private static void setImageView(ImageView imageView, Bitmap bitmap) {
        if(imageView!=null) {
            imageView.setVisibility(View.VISIBLE);
            if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            else
                clearImageViewBackground(imageView);
        }
    }

    private class ImageObject {
        public ImageObject(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        String url;
        ImageView imageView;
        Bitmap imageBitmap;


        public Bitmap getImageBitmap() {
            return imageBitmap;
        }

        public void setImageBitmap(Bitmap imageBitmap) {
            this.imageBitmap = imageBitmap;
        }


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

    }

}
