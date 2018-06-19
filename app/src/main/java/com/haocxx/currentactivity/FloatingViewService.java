package com.haocxx.currentactivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * by Haocxx
 * 2018.6.14
 */

public class FloatingViewService extends Service implements WindowChangeDetectingService.callback {
    private WindowManager mWindowManager;
    private LinearLayout linearLayout;
    private TextView packegeNameTextView;
    private TextView activityNameTextView;

    public FloatingViewService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WindowChangeDetectingService.setCallback(this);
        mWindowManager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        initView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * from blog https://www.jianshu.com/p/ac63c57d2555
     */
    private void initView() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        linearLayout = (LinearLayout) inflater.inflate(R.layout.layout_floating_view, null);
        packegeNameTextView = linearLayout.findViewById(R.id.package_name);
        activityNameTextView = linearLayout.findViewById(R.id.activity_name);
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 0;
        params.y = 0;
        params.width = WindowManager.LayoutParams. WRAP_CONTENT;
        params.height = WindowManager.LayoutParams. WRAP_CONTENT;
        mWindowManager.addView(linearLayout, params);
    }

    private void setText(String packageName, String activityName) {
        packegeNameTextView.setText(packageName);
        activityNameTextView.setText(activityName);
    }

    private void removeView() {
        mWindowManager.removeView(linearLayout);
    }

    @Override
    public void onChange() {
        setText(ActivityManagerUtils.topPackageName, ActivityManagerUtils.topActivityName);
    }
}
