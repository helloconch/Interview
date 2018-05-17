package com.conch.tiku.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.conch.tiku.utils.Logs;


public class EventGroupView extends LinearLayout {
    public EventGroupView(Context context) {
        super(context);
    }

    public EventGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Logs.info("ViewGroup>>>>onTouchEvent--" + result);
        return result;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean result = super.onInterceptTouchEvent(ev);
        Logs.info("ViewGroup>>>>onInterceptTouchEvent--" + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Logs.info("ViewGroup>>>>dispatchTouchEvent--" + result);
        return result;
    }
}
