package com.gz.glibrary.logutils.file;

import com.gz.glibrary.logutils.LogLevel;

/**
 * Created by pengwei on 2017/3/31.
 * 日志过滤器
 */

public interface LogFileFilter {
  boolean accept(@LogLevel.LogLevelType int level, String tag, String logContent);
}