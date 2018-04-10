package com.gz.glibrary.network.callback;

import java.util.List;

/**
 * @author gaoqian
 * @date 2018/3/26
 */

public interface HttpCallback {

  void onSuccess(String url, int statusCode, String response);

  void onCookies(String url, List<String> cookies);

  void onError(String url, String errorMsg);
}
