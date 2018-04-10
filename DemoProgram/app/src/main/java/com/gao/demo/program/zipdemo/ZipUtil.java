package com.gao.demo.program.zipdemo;

import com.gao.demo.program.threadpools.GThreadPoolExecutor;
import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/**
 * Created by gaoqian on 2018/3/17.
 */

public class ZipUtil {

  public static final int ERRO_CODE_INVALID = 0;//zip file invalid

  /**
   *
   * @param zipFilePath
   * @param unZipFilePath
   * @param deleteZip
   * @param onZipListener
   */
  public static final void unZipFile(String zipFilePath, final String unZipFilePath, final boolean deleteZip,
      final OnZipListener onZipListener) throws ZipException {
    final ZipFile zipFile = new ZipFile(zipFilePath);
    if (!zipFile.isValidZipFile()) {
      return;
    }
    File destFile = new File(unZipFilePath);
    if (destFile.isDirectory() && !destFile.exists()) {
      destFile.mkdirs();
    }
    final ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
    Runnable runnable = new Runnable() {
      @Override public void run() {
        boolean unzipping = true;
        int percent = 0;
        int sleepTime = 50;
        while (unzipping) {
          try {
            Thread.sleep(sleepTime);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          percent = progressMonitor.getPercentDone();
          onZipListener.onZipProgress(percent);
          if (percent >= 100) {
            unzipping = false;
          }
        }
        onZipListener.onZipSuccess();
        if (deleteZip) {
          zipFile.getFile().delete();
        }
      }
    };
    GThreadPoolExecutor.newGThreadPoolExecutor().execute(runnable);
    zipFile.setRunInThread(true);
    zipFile.extractAll(unZipFilePath);
  }
}

