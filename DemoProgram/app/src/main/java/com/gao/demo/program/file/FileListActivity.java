package com.gao.demo.program.file;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gao.demo.program.R;
import com.gao.demo.program.base.BaseActivity;
import com.gz.glibrary.file.FileEntity;
import com.gz.glibrary.file.FileUtil;
import com.gz.glibrary.logutils.LogUtils;
import com.gz.glibrary.thread.GThreadPoolExecutor;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by gaoqian on 2018/4/8.
 */

public class FileListActivity extends BaseActivity {

  @BindView(R.id.activity_rv) RecyclerView recyclerView;
  @BindView(R.id.activity_pb) ProgressBar progressBar;

  private FileListAdapter fileListAdapter;
  static String path = "/sdcard/";
  GThreadPoolExecutor gThreadPoolExecutor;
  FHandler fHandler;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);
    ButterKnife.bind(this);
    fHandler = new FHandler(this);
    fileListAdapter = new FileListAdapter(this);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(fileListAdapter);
    init();
  }

  private void init() {
    path = FileUtil.getSDPath();
    gThreadPoolExecutor = GThreadPoolExecutor.newGThreadPoolExecutor();
    gThreadPoolExecutor.execute(new Runnable() {
      @Override public void run() {
        LogUtils.i("start scan file");
        getFileList();
      }
    });
  }

  private void getFileList() {
    List<FileEntity> fileEntities = FileUtil.getFileListByFilter(path, ".mp3");
    sendMessage(0, fileEntities);
  }

  private void sendMessage(int what, Object object) {
    Message message = new Message();
    message.what = what;
    message.obj = object;
    fHandler.sendMessage(message);
  }

  private void setAdapter(List<FileEntity> fileEntities) {
    fileListAdapter.setFileEntities(fileEntities);
  }

  static class FHandler extends Handler {
    WeakReference<FileListActivity> weakReference;

    public FHandler(FileListActivity fileListActivity) {
      this.weakReference = new WeakReference<FileListActivity>(fileListActivity);
    }

    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case 0:
          List<FileEntity> fileEntities = (List<FileEntity>) msg.obj;
          weakReference.get().setAdapter(fileEntities);
          weakReference.get().progressBar.setVisibility(View.INVISIBLE);
          break;
        default:
      }
    }
  }
}
