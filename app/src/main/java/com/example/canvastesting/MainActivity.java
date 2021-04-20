package com.example.canvastesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.canvastesting.views.CustomView;

public class MainActivity extends AppCompatActivity {
    private CustomView mCustomView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView) findViewById(R.id.customView);

        //first constructor is called

        findViewById(R.id.btn_swap_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCustomView.animateObject();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}