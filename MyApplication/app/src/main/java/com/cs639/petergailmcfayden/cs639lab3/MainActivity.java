package com.cs639.petergailmcfayden.cs639lab3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Petergail McFayden on 11/2/2014.
 */
public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liner_layout);

        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("My Activity", "Next button was pressed");
                Toast.makeText(getBaseContext(), "You pressed on Next", Toast.LENGTH_LONG).show();
            }
        });

        Button prev = (Button)findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("My Activity", "Previous button was pressed");
                Toast.makeText(getBaseContext(), "You pressed on Prev", Toast.LENGTH_LONG).show();
            }
        });

    }
}
