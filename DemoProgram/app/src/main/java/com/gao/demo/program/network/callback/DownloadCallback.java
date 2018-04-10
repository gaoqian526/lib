package com.gao.demo.program.network.callback;

/**
 * Created by gaoqian on 2018/3/26.
 */

public interface DownloadCallback {
  void onDownloadStart(String url, String filePath);

  void onDownloadComplete(String url, String filePath);

  void onDownloadProgress(String url, long downloadSize, long size);

  void onDownloadErro(String url, String erroMsg, int erroCode);
}
