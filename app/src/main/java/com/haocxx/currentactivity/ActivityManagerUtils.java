package com.haocxx.currentactivity;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import java.util.List;

/**
 * by Haocxx
 * 2018.6.14
 */

public class ActivityManagerUtils {
    public static String topPackageName;
    public static String topActivityName;
//    private static String packageName;
    private static ActivityManager mActivityManager;
//    private static AppOpsManager mAppOpsManager;
//    private static UsageStatsManager mUsageStatsManager;

    static void prepare(Context mContext) {
//        packageName = mContext.getPackageName();
//        if (Build.VERSION.SDK_INT >= 21) {
//            mAppOpsManager = (AppOpsManager)mContext.getSystemService(Context.APP_OPS_SERVICE);
//            mUsageStatsManager = (UsageStatsManager) mContext.getSystemService(Context.USAGE_STATS_SERVICE);
//        }
        mActivityManager = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
    }

    /**
     * from blog http://blog.csdn.net/yishifu/article/details/52104129
     *
     * If SDK version is above 21, this fucking method only can get package name
     */
//    public static void getInfo() {
//        if (Build.VERSION.SDK_INT >= 21) {
//            if (mUsageStatsManager != null) {
//                long now = System.currentTimeMillis();
//                // get app data during 60s
//                List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, now - 60 * 1000, now);
//                // get present app
//                if ((stats != null) && (!stats.isEmpty())) {
//                    int j = 0;
//                    for (int i = 0; i < stats.size(); i++) {
//                        if (stats.get(i).getLastTimeUsed() > stats.get(j).getLastTimeUsed()) {
//                            j = i;
//                        }
//                    }
//                    topPackageName = stats.get(j).getPackageName();
//                    topActivityName = "";
//                }
//            }
//        } else {
//            // getRunningTasks() is deprecated since API Level 21 (Android 5.0)
//            ComponentName localRunningTaskInfo = mActivityManager.getRunningTasks(1).get(0).topActivity;
//            topPackageName = localRunningTaskInfo.getPackageName();
//            topActivityName = localRunningTaskInfo.getClassName();
//        }
//    }
//
//    public static boolean hasPermission() {
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//            int mode;
//            mode = mAppOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
//                    android.os.Process.myUid(), packageName);
//            return mode == AppOpsManager.MODE_ALLOWED;
//        } else {
//            return true;
//        }
//    }
}
