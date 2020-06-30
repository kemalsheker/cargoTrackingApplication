package com.example.drn20;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutUsPage  extends AppCompatActivity {

    Toolbar toolbarAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_layout);

        toolbarAboutUs = findViewById(R.id.toolBar_aboutUs);
        setSupportActionBar(toolbarAboutUs);
        getSupportActionBar().setTitle(null);


    }


}
