package com.conch.tiku.imageloader;


import android.graphics.Bitmap;

public class DoubleCache implements ImageCache {
    ImageCache memoryCache = new MemoryCache();

    ImageCache diskCache = new MemoryCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
