package com.gz.glibrary.file;

/**
 * Created by gaoqian on 2018/4/9.
 */

public class FileEntity {
  private String name;
  private String path;
  private boolean directory;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public boolean isDirectory() {
    return directory;
  }

  public void setDirectory(boolean directory) {
    this.directory = directory;
  }
}
