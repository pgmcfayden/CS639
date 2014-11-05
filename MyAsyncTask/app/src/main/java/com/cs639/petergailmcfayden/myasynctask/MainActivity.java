package com.cs639.petergailmcfayden.myasynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Petergail McFayden on 11/2/2014.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String FETCHED_IMAGE_URL = "http://wanderingoak.net/bridge.png";

    private Button fetchedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchedImage = (Button) findViewById(R.id.download_image_button);
        fetchedImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final Intent intent;

        if (v == fetchedImage) {
            intent = new Intent(this, com.cs639.petergailmcfayden.myasynctask.AsyncTaskActivity.class);
            Log.i("MainActivity", "Button clicked to fetch image!");

        } else {
            intent = null;
        }

        if (intent != null) {
            intent.putExtra("url", FETCHED_IMAGE_URL);
            startActivity(intent);
        }
    }
}

