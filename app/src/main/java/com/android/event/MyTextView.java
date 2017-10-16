package com.android.event;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by wei on 17-10-16.
 */

public class MyTextView extends TextView {
    // view 只有onTouchEvent  dispatchTouchEvent
    //拦截只存在于ViewGroup以及子类中，在Activity、view中不存在的。
    private static String TAG = "MyTextView";


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,">>weiyandong>>> onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,">>weiyandong>>> onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,">>weiyandong>>> onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,">>weiyandong>>> onTouchEvent ACTION_CANCEL");
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //return true 代表消费，不往下传递。
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,">>weiyandong>>> dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,">>weiyandong>>> dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,">>weiyandong>>> dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,">>weiyandong>>> dispatchTouchEvent ACTION_CANCEL");
                break;

        }
        return super.dispatchTouchEvent(event);
    }

}
