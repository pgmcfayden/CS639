package com.cs639.petergailmcfayden.materialdesign;

import android.app.Activity;
import android.app.ActivityOptions;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Petergail McFayden on 11/5/2014.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView hero = (ImageView)((View) view.getParent()).findViewById(R.id.photo);
        ((ViewGroup) hero.getParent()).setTransitionGroup(false);

        sPhotoCache.put(intent.getInExtra("photo", -1),((BitmapDrawable) hero.getDrawable()).getBitmap());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, hero, "photo_here");
        startActivity(intent, options.toBundle());
        getActivitye

    }
}
