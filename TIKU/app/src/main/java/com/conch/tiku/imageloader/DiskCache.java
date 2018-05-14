package com.conch.tiku.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.conch.tiku.utils.CacheUtils;
import com.conch.tiku.utils.Logs;
import com.conch.tiku.utils.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiskCache implements ImageCache {
    final String fileName = "disk_bitmap";
    final String CACHEDIR;

    public DiskCache(Context context) {
        CACHEDIR = CacheUtils.getDiskCacheDir(context, fileName).getAbsolutePath();
        File file = new File(CACHEDIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        Logs.info(CACHEDIR);
    }


    @Override
    public Bitmap get(String url) {
        String key = Tools.hashKeyForDisk(url);
        return BitmapFactory.decodeFile(CACHEDIR + File.separator + key);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        String key = Tools.hashKeyForDisk(url);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(CACHEDIR + File.separator + key);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
