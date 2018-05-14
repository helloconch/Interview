package com.conch.tiku.test;

import android.graphics.Bitmap;

public class Test {
    MyLru<String, Bitmap> myLru = new MyLru<String, Bitmap>() {

        @Override
        public void print() {
            super.print();
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return super.sizeOf(key, value);
        }
    };
}
