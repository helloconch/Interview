package com.conch.tiku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.conch.tiku.imageloader.DiskCache;
import com.conch.tiku.imageloader.ExtDiskLruCache;
import com.conch.tiku.imageloader.ImageCache;
import com.conch.tiku.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(new ExtDiskLruCache(this));
        imageLoader.displayImage("http://p3.wmpic.me/article/2016/01/02/1451705414_FCsmpfEP.jpg", imageView);


        List<String> datas=new ArrayList<>();

    }
}
