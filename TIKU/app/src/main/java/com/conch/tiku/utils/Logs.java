package com.conch.tiku.utils;

import android.util.Log;

public class Logs {
    private static final String TAG = "TIKU";

    public static void info(String message) {
        Log.d(TAG, message);
    }
}
