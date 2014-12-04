package com.cs639.petergailmcfayden.cs639midtermexecr1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == button1){
            Toast.makeText(MainActivity.this, "Button 1", Toast.LENGTH_LONG).show();
        }
        if(v == button2){
            Toast.makeText(MainActivity.this, "Button 2", Toast.LENGTH_LONG).show();
        }
        if(v == button3){
            Toast.makeText(MainActivity.this, "Button 3", Toast.LENGTH_LONG).show();
        }
        if(v == button4){
            Toast.makeText(MainActivity.this, "Button 4", Toast.LENGTH_LONG).show();
        }
    }
}
