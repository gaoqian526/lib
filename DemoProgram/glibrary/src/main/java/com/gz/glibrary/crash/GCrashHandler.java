package com.gz.glibrary.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.gz.glibrary.file.FileUtil;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gaoqian on 2018/4/3.
 */

public class GCrashHandler implements Thread.UncaughtExceptionHandler {

  private volatile static GCrashHandler instance;

  private Context context;
  private Thread.UncaughtExceptionHandler defaultHandler;

  private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

  private GCrashHandler() {
  }

  private GCrashHandler(Context context) {
    this.context = context.getApplicationContext();
  }

  public static GCrashHandler getInstance(Context context) {

    if (instance == null) {
      synchronized (GCrashHandler.class) {
        if (instance == null) {
          instance = new GCrashHandler(context);
        }
      }
    }
    return instance;
  }

  public void init() {
    defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  @Override public void uncaughtException(Thread t, Throwable e) {
    try {
      getThrowableInfo(e);
    } catch (PackageManager.NameNotFoundException e1) {
      e1.printStackTrace();
    }
    if (defaultHandler != null) {
      defaultHandler.uncaughtException(t, e);
    }
  }

  private void getThrowableInfo(Throwable ex) throws PackageManager.NameNotFoundException {
    Writer writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    ex.printStackTrace(printWriter);
    Throwable cause = ex.getCause();
    while (cause != null) {
      cause.printStackTrace(printWriter);
      cause = cause.getCause();
    }
    printWriter.close();
    String result = writer.toString();
    StringBuilder stringBuffer = new StringBuilder();
    String time = formatter.format(new Date());
    String fileName = "crash" + System.currentTimeMillis() + ".txt";
    String brand = Build.BRAND;
    String model = Build.MODEL;
    int sdkVersion = Build.VERSION.SDK_INT;
    stringBuffer.append("\nsdkVersion：" + sdkVersion);
    stringBuffer.append("\nbrand：" + brand);
    stringBuffer.append("\nmodel：" + model);
    stringBuffer.append("\ntime：" + time);
    PackageManager packageManager = context.getPackageManager();
    PackageInfo pi = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
    if (pi != null) {
      String versionName = pi.versionName == null ? "null" : pi.versionName;
      String versionCode = pi.versionCode + "";
      stringBuffer.append("\nversion:" + versionName);
      stringBuffer.append("\nversion:" + versionCode);
      stringBuffer.append("\nfilename:" + fileName);
      stringBuffer.append("\nfilepath:" + FileUtil.isExist(FileUtil.getAppSDDataPath(context, "crash")));
    }
    stringBuffer.append("\ncrash：" + result);
    String crashFilePath = FileUtil.getAppSDDataPath(context, "crash") + File.separator + fileName;
    FileUtil.writeStr2File(crashFilePath, stringBuffer.toString());
  }
}
