package com.gz.glibrary.file;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoqian
 * @date 2018/3/26
 */

public class FileUtil {

  private static String DIR_LOG = "glog";
  private static String FILE_LOG = "glog.txt";
  private static List<FileEntity> fileEntities = new ArrayList<>();

  public static String getAppSDDataPath(Context context, String dirName) {
    return context.getExternalFilesDir(dirName).getAbsolutePath();
  }

  public static String getSDPath() {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }

  public static List<FileEntity> getFileListByFilter(String path, String fileType) {
    fileEntities = new ArrayList<>();
    if (!isExist(path)) {
      return null;
    }
    File rootFile = new File(path);
    File[] paths = rootFile.listFiles();
    if (paths == null || paths.length == 0) {
      return null;
    }
    scanAllFile(rootFile, fileType);
    return fileEntities;
  }

  private static void scanAllFile(File path, final String type) {
    File[] farr = path.listFiles(new FileFilter() {
      @Override public boolean accept(File pathname) {
        String sname = pathname.getName();
        if (pathname.isDirectory()) {
          return true;
        } else if (sname.endsWith(type)) {
          return true;
        }
        return false;
      }
    });
    if (farr != null) {
      for (File file : farr) {
        if (file.isDirectory()) {
          scanAllFile(file, type);
        } else {
          FileEntity fileEntity = new FileEntity();
          fileEntity.setName(file.getName());
          fileEntity.setPath(file.getAbsolutePath());
          fileEntity.setDirectory(file.isDirectory());
          fileEntities.add(fileEntity);
        }
      }
    }
  }

  public static void mkdirs(String path) {
    File file = new File(path);
    if (!file.exists()) {
      file.mkdirs();
    }
  }

  public static boolean isExist(String path) {
    File file = new File(path);
    if (file.exists()) {
      return true;
    }
    return false;
  }

  public static boolean deleteFile(String path) {
    if (TextUtils.isEmpty(path)) {
      return false;
    }
    File file = new File(path);
    if (!file.exists()) {
      return false;
    }

    if (file.isFile()) {
      return file.delete();
    }

    if (!file.isDirectory()) {
      return false;
    }

    String[] childs = file.list();
    if (childs == null || childs.length == 0) {
      return file.delete();
    }
    for (String child : childs) {
      deleteFile(child);
    }
    return false;
  }

  public static void writeStr2File(String path, String content) {
    if (TextUtils.isEmpty(path)) {
      path = getLogPath();
    }
    File file = new File(path);
    if (!file.isFile()) {
      file.delete();
    }
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
      writer.write(content);
      writer.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static String getLogPath() {
    return Environment.getDownloadCacheDirectory().getAbsolutePath() + File.separator + DIR_LOG + File.separator + FILE_LOG;
  }

  static class GFilter implements FilenameFilter {

    private String type;

    public GFilter(String type) {
      this.type = type;
    }

    @Override public boolean accept(File file, String name) {
      return name.endsWith(type);
    }
  }
}
