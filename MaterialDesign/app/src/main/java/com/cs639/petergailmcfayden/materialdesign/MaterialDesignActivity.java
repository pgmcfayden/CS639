package com.cs639.petergailmcfayden.materialdesign;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Petergail McFayden on 11/5/2014.
 */
public class MaterialDesignActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getEnterTransition().addListener(new TransitionAdapter() {

        });
    }
}
