package com.android.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";
    private MyTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (MyTextView)findViewById(R.id.text);
        textView.setOnClickListener(this);
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
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
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                Log.d(TAG,"MyTextview click");
                break;
            default:
                break;
        }
    }
}
/*
若是只返回super 打印结果：

 D/MainActivity: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> onInterceptTouchEvent ACTION_DOWN
 D/MyTextView: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyTextView: >>weiyandong>>> onTouchEvent ACTION_DOWN
 D/MainActivity: >>weiyandong>>> dispatchTouchEvent ACTION_UP
 D/MyRelativeLayout: >>weiyandong>>> dispatchTouchEvent ACTION_UP
 D/MyRelativeLayout: >>weiyandong>>> onInterceptTouchEvent ACTION_UP
 D/MyTextView: >>weiyandong>>> dispatchTouchEvent ACTION_UP
 D/MyTextView: >>weiyandong>>> onTouchEvent ACTION_UP

onInterceptTouchEvent是ViewGroup中的方法，作用是系统向该ViewGroup及其各个ChildView触发onTouchEvent
方法之前对相关事件的一次拦截。

ACTION_DOWN事件会首先传递到ViewGroup中的onInterceptTouchEvent方法中
如果onInterceptTouchEvent处理完ACTION_DOWN事件返回false，则系统还会将该ACTION_DOWN事件传到目标View当中
如果onInterceptTouchEvent处理完ACTION_DOWN事件返回true，则系统不会将该ACTION_DOWN事件传到目标View中
的onTouchEvent方法中，就会调用ViewGroup中自己的onTouchEvent方法处理。

2、前提：ViewGroup中的onInterceptTouchEvent处理完ACTION_DOWN事件返回false，后续的ACTION_MOVE事件同样先传递到onInterceptTouchEvent中
如果onInterceptTouchEvent处理完ACTION_MOVE返回false，则系统还会将ACTION_MOVE事件传递到目标View中的onTouchEvent方法中。
如果onInterceptTouchEvent处理完ACTION_MOVE返回true，则系统不会将ACTION_MOVE事件传递到目标View中的onTouchEvent方法中，
而是传递ACTION_CANCEL事件到目标View的onTouchEvent方法中，只传递一次，此后与目标View没有任何关系（包括最后的ACTION_UP事件），只调用ViewGroup中的onTouchEvent方法。

3、前提：ViewGroup中的onInterceptTouchEvent处理完ACTION_DOWN事件返回true
后续的ACTION_MOVE、ACTION_UP事件将不再调用该ViewGroup中的onInterceptTouchEvent方法，
直接将这些事件传递给该ViewGroup中的onTouchEvent处理。

4、View的onTouchEvent返回false,则系统将该事件传递至上一层View的onTouchEvent处理。
5、View的onTouchEvent返回true,则后续事件可以继续传递给该View的onTouchEvent处理。
针对5打印结果：
 D/MainActivity: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> onInterceptTouchEvent ACTION_DOWN
 D/MyTextView: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyTextView: >>weiyandong>>> onTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> onTouchEvent ACTION_DOWN
 D/MainActivity: >>weiyandong>>> onTouchEvent ACTION_DOWN
 D/MainActivity: >>weiyandong>>> dispatchTouchEvent ACTION_UP
 D/MainActivity: >>weiyandong>>> onTouchEvent ACTION_UP

若是viewgroup中onInterceptTouchEvent 返回true，也就是拦截了事件，打印结果如下：

 D/MainActivity: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> dispatchTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> onInterceptTouchEvent ACTION_DOWN
 D/MyRelativeLayout: >>weiyandong>>> onTouchEvent ACTION_DOWN
 D/MainActivity: >>weiyandong>>> onTouchEvent ACTION_DOWN
 D/MainActivity: >>weiyandong>>> dispatchTouchEvent ACTION_UP
 D/MainActivity: >>weiyandong>>> onTouchEvent ACTION_UP

 */
