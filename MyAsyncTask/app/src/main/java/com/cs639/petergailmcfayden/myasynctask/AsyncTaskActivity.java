package com.cs639.petergailmcfayden.myasynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Petergail McFayden on 11/2/2014.
 */
public class AsyncTaskActivity extends Activity {

    private ImageDownloader imageDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageDownloader = new ImageDownloader();
        imageDownloader.execute("http://wanderingoak.net/bridge.png");
        Log.i("AsyncTaskActivity", "Starting the AsyncTask Image Fetching Activity!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (imageDownloader != null) {
            imageDownloader.cancel(true);
        }
    }

    private class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

        ProgressDialog message;

        @Override
        protected void onPreExecute() {
            message = ProgressDialog.show(AsyncTaskActivity.this, "Fetching image, please wait!", null);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.i("ImageDownloader", "Going to fetch image");
            publishProgress(1);
            try {
                URL url = new URL(params[0]); // "http://wanderingoak.net/bridge.png"
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

                if (httpCon.getResponseCode() != 200) {
                    throw new Exception("Failed to connect");
                }

                InputStream is = httpCon.getInputStream();
                publishProgress(0);
                return BitmapFactory.decodeStream(is);
            } catch (Exception exp) {
                Toast.makeText(AsyncTaskActivity.this, exp.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ImageDownloader", "Didn't work!", exp);
                Log.e("ImageDownloader", exp.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //super.onPostExecute(bitmap);
            ImageView iv = (ImageView) findViewById(R.id.fetched_image);
            if (iv != null && bitmap != null) {
                iv.setImageBitmap(bitmap);
            }
            message.dismiss();
            message = null;
            Log.i("ImageDownloader", "Image fetched, isn't the Golden Gate Beautiful!");
        }
    }
}
