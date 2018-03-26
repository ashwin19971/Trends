package com.example.ashwingiri.trends.NavigationDrawerActivites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.ashwingiri.trends.R;

public class AboutDeveloper extends AppCompatActivity  {

    ImageButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);

        mButton = findViewById(R.id.ibMenu);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDeveloper.super.onBackPressed();
            }
        });

    }
}