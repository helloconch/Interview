package com.conch.tiku.event;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conch.tiku.R;
import com.conch.tiku.utils.Logs;

public class EventActivity extends AppCompatActivity {

    ViewGroup myLayout;
    Button button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        myLayout = findViewById(R.id.my_layout);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logs.info("点击了ViewGroup");
            }
        });

        // 2. 为按钮1设置监听事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logs.info("点击了button1");
            }
        });

        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Logs.info("onTouch --button1");
                return true;
            }
        });


        // 3. 为按钮2设置监听事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logs.info("点击了button2");
            }
        });


    }
}
