package com.conch.tiku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.conch.tiku.imageloader.DiskCache;
import com.conch.tiku.imageloader.ImageCache;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageCache imageCache = new DiskCache();
    }
}
