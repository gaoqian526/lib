package com.gao.demo.program.zipdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gao.demo.program.R;
import com.gao.demo.program.base.BaseActivity;
import com.gao.demo.program.threadpools.GThreadPoolExecutor;
import com.gz.glibrary.crash.GCrashHandler;
import com.gz.glibrary.logutils.LogUtils;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.exception.ZipException;

/**
 * Created by gaoqian on 2018/3/15.
 */

public class ZipActivity extends BaseActivity {
  static final String TAG = "zip_demo";
  static final int MSG_PROGRESS_PERCENT = 0;
  static final int MSG_COMPLETE = 1;
  ZipHandler zipHandler;

  @BindView(R.id.activity_zip_progress) TextView progressTxt;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zip);
    ButterKnife.bind(this);
    GCrashHandler.getInstance(this.getApplicationContext()).init();
    zipHandler = new ZipHandler(this);
    LogUtils.init(this, true, true);
  }

  @OnClick(R.id.activity_zip_start_btn) void startUnZip() {
    try {
      GThreadPoolExecutor gThreadPoolExecutor = GThreadPoolExecutor.newGThreadPoolExecutor();
      gThreadPoolExecutor.execute(new Runnable() {
        @Override public void run() {
          LogUtils.tag(TAG).e("ThreadPoolExecutor Run");
        }
      });
      gThreadPoolExecutor.awaitTermination(5L, TimeUnit.SECONDS);
      gThreadPoolExecutor.shutdown();
      String zipFile = "/sdcard/Expressive Art.zip";
      //Log.e(TAG, "file:" + zipFile);
      unzip(zipFile);
    } catch (ZipException zipException) {
      zipException.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void unzip(final String zipPath) throws ZipException {
    ZipUtil.unZipFile(zipPath, "/sdcard", false, new OnZipListener() {
      @Override public void onZipSuccess() {

      }

      @Override public void onZipFail() {

      }

      @Override public void onZipProgress(int percent) {
        Message message = new Message();
        message.what = MSG_PROGRESS_PERCENT;
        message.obj = percent;
        zipHandler.sendMessage(message);
      }
    });
  }

  static class ZipHandler extends Handler {

    WeakReference<ZipActivity> weakReference;

    public ZipHandler(ZipActivity zipActivity) {
      weakReference = new WeakReference<ZipActivity>(zipActivity);
    }

    @Override public void dispatchMessage(Message msg) {
      super.dispatchMessage(msg);
      switch (msg.what) {
        case MSG_PROGRESS_PERCENT:
          int percent = Integer.parseInt(msg.obj.toString());
          weakReference.get().progressTxt.setText(percent + "%");
          break;
        case MSG_COMPLETE:
          break;
      }
    }
  }
}
