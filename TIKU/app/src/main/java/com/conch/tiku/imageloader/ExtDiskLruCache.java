package com.conch.tiku.imageloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.conch.tiku.utils.AppUtils;
import com.conch.tiku.utils.CacheUtils;
import com.conch.tiku.utils.Tools;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExtDiskLruCache implements ImageCache {
    final String DISKLRUName = "disklru_bitmap";

    DiskLruCache mDiskLruCache;

    public ExtDiskLruCache(Context context) {
        try {
            File cacheFile = CacheUtils.getDiskCacheDir(context, DISKLRUName);
            if (!cacheFile.exists())
                cacheFile.mkdirs();
            int appVersion = AppUtils.getAppVersion(context);
            int valueCount = 1;
            long maxSize = 10 * 1024 * 1024;
            mDiskLruCache = DiskLruCache.open(cacheFile, appVersion, valueCount, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Bitmap get(String url) {
        try {
            String key = Tools.hashKeyForDisk(url);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                return BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        try {
            String key = Tools.hashKeyForDisk(url);
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                outputStream.write(byteArrayOutputStream.toByteArray());
                editor.commit();
            }
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
