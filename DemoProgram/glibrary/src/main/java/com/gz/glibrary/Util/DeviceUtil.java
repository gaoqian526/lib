package com.gz.glibrary.Util;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import static com.gz.glibrary.logutils.Constant.TAG;

/**
 * Created by gaoqian on 2018/4/3.
 * 硬件信息
 */

public class DeviceUtil {
  public void getDeviceInfo(Context ctx) {
    try {
      PackageManager pm = ctx.getPackageManager();
      PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
      if (pi != null) {
        String versionName = pi.versionName == null ? "null" : pi.versionName;
        String versionCode = pi.versionCode + "";
      }
    } catch (PackageManager.NameNotFoundException e) {
      Log.e(TAG, "an error occured when collect package info", e);
    }
  }
}
