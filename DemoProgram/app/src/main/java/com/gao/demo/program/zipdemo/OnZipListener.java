package com.gao.demo.program.zipdemo;

/**
 * Created by gaoqian on 2018/3/17.
 */

public interface OnZipListener {

  public void onZipSuccess();

  public void onZipFail();

  public void onZipProgress(int percent);
}
