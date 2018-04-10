package com.gz.glibrary.logutils;

import android.content.Context;
import com.gz.glibrary.logutils.file.DefaultLogFileEngine;

/**
 * Created by pengwei08 on 2015/7/16.
 * 日志管理器
 */
public final class LogUtils {

  private static Logger printer = new Logger();
  private static LogConfigImpl logConfig = LogConfigImpl.getInstance();
  private static Log2FileConfigImpl log2FileConfig = Log2FileConfigImpl.getInstance();

  public static void init(Context context, boolean logAble, boolean log2file) {
    getLogConfig().configAllowLog(logAble);
    getLog2FileConfig().configLog2FileEnable(log2file);
    if (log2file) {
      String path = context.getExternalFilesDir("log").getAbsolutePath();
      getLog2FileConfig().configLog2FilePath(path)
          .configLog2FileNameFormat("%d{yyyyMMdd}.txt")
          .configLogFileEngine(new DefaultLogFileEngine());
    }
  }

  /**
   * 选项配置
   */
  public static LogConfig getLogConfig() {
    return logConfig;
  }

  /**
   * 日志写入文件相关配置
   */
  public static Log2FileConfig getLog2FileConfig() {
    return log2FileConfig;
  }

  public static Printer tag(String tag) {
    return printer.setTag(tag);
  }

  /**
   * verbose输出
   */
  public static void v(String msg, Object... args) {
    printer.v(msg, args);
  }

  public static void v(Object object) {
    printer.v(object);
  }

  /**
   * debug输出
   */
  public static void d(String msg, Object... args) {
    printer.d(msg, args);
  }

  public static void d(Object object) {
    printer.d(object);
  }

  /**
   * info输出
   */
  public static void i(String msg, Object... args) {
    printer.i(msg, args);
  }

  public static void i(Object object) {
    printer.i(object);
  }

  /**
   * warn输出
   */
  public static void w(String msg, Object... args) {
    printer.w(msg, args);
  }

  public static void w(Object object) {
    printer.w(object);
  }

  /**
   * error输出
   */
  public static void e(String msg, Object... args) {
    printer.e(msg, args);
  }

  public static void e(Object object) {
    printer.e(object);
  }

  /**
   * assert输出
   */
  public static void wtf(String msg, Object... args) {
    printer.wtf(msg, args);
  }

  public static void wtf(Object object) {
    printer.wtf(object);
  }

  /**
   * 打印json
   */
  public static void json(String json) {
    printer.json(json);
  }

  /**
   * 输出xml
   */
  public static void xml(String xml) {
    printer.xml(xml);
  }
}
