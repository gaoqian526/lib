package com.gz.glibrary.network.callback;

/**
 * @author gaoqian
 * @date 2018/3/26
 */

public interface DownloadCallback {
  void onDownloadStart(String url, String filePath);

  void onDownloadProgress(String url, long downSize, long size);

  void onDownloadComplete(String url, String filePath);

  void onDownloadError(String url, String errorMsg);
}
