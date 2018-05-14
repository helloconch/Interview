package com.conch.tiku.imageloader;


import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


public class MemoryCache implements ImageCache {

    //图片缓存

    LruCache<String, Bitmap> mLruCache;


    public MemoryCache() {
        //最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            /**
             * 计算缓存每一项数据的Size
             * 已缓存的数据Size会根据这个方法将这个数据Size加入进来
             * 便于统计当前使用了多少内存，当使用内存大小超过maxSize就会进行清除操作
             * @param key
             * @param bitmap
             * @return
             */
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };

    }


    @Override
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }
}
