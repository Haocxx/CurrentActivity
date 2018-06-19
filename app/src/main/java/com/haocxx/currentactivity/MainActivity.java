package com.haocxx.currentactivity;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * by Haocxx
 * 2018.6.14
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ACCESS_ACCESSIBILIT_PERMISSION = 1001;
    private static final int ACTION_MANAGE_OVERLAY_PERMISSION = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityManagerUtils.prepare(getApplicationContext());
        run();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == ACCESS_ACCESSIBILIT_PERMISSION) {
//            if (false) {
//                noPermissionFinish();
//            } else {
//                run();
//            }
//            return;
//        }
        if(requestCode == ACTION_MANAGE_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (Settings.canDrawOverlays(MainActivity.this)) {
                    startWorkService();
                }
            } else {
                noPermissionFinish();
            }
            return;
        }
    }

    /**
     * from blog https://www.jianshu.com/p/ac63c57d2555
     *
     * if SDK version above 23, need access permission
     */
    private void run() {
        Log.d(TAG, "run");
        getAccessibilityPermission();
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(MainActivity.this)) {
                startWorkService();
            } else {
                // if no permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Toast.makeText(MainActivity.this, "Need permission!", Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION);
            }
        } else {
            // if SDK version under 23
            startWorkService();
        }
    }

    private void noPermissionFinish() {
        Toast.makeText(this, "No permission!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void startWorkService() {
        Log.d(TAG, "startWorkService");
        Toast.makeText(MainActivity.this,"Start.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, FloatingViewService.class);
        startService(intent);

        /**
         * AccessibilityService don not need start by you.
         * While turn on the app accessibility permission, it will auto-run by system.
         * So fxcking nice design.
         */
//        intent = new Intent(MainActivity.this, WindowChangeDetectingService.class);
//        startService(intent);
        finish();
    }

    private boolean getAccessibilityPermission() {
        Log.d(TAG, "ACCESS_ACCESSIBILIT_PERMISSION");
        Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
        return false;
    }
}
