package com.gao.demo.program;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gao.demo.program.file.FileListActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    startActivity(new Intent(this, FileListActivity.class));
    finish();
  }

  @OnClick(R.id.activity_main_txt) void startIntent() {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
    intent.setData(Uri.parse("http://www.baidu.com"));
    startActivity(intent);
  }
}
