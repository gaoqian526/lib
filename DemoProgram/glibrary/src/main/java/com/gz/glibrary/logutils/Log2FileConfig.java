package com.gz.glibrary.logutils;

import com.gz.glibrary.logutils.file.LogFileEngine;
import com.gz.glibrary.logutils.file.LogFileFilter;
import java.io.File;

/**
 * Created by pengwei on 2017/3/30.
 */

public interface Log2FileConfig {
  Log2FileConfig configLog2FileEnable(boolean enable);

  Log2FileConfig configLog2FilePath(String logPath);

  Log2FileConfig configLog2FileNameFormat(String formatName);

  Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level);

  Log2FileConfig configLogFileEngine(LogFileEngine engine);

  Log2FileConfig configLogFileFilter(LogFileFilter fileFilter);

  File getLogFile();
}
