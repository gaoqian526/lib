package com.gz.glibrary.logutils.file;

import android.util.Log;
import com.gz.glibrary.file.FileUtil;
import java.io.File;

/**
 * Created by gaoqian on 2018/4/3.
 */

public class DefaultLogFileEngine implements LogFileEngine {
  @Override public void writeToFile(File logFile, String logContent, LogFileParam params) {
    Log.e("log",logContent);
    FileUtil.writeStr2File(logFile.getPath(), logContent);
  }
}
