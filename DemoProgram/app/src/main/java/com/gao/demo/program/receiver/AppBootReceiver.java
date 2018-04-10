package com.gao.demo.program.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.gao.demo.program.MainActivity;

/**
 * Created by gaoqian on 2018/3/21.
 */

public class AppBootReceiver extends BroadcastReceiver {

  static final String TAG = AppBootReceiver.class.getSimpleName();

  @Override public void onReceive(Context context, Intent intent) {
    if (intent == null || TextUtils.isEmpty(intent.getAction())) {
      return;
    }
    String action = intent.getAction();
    Log.e(TAG, action);
    if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
      startLauncher(context);
    }
  }

  void startLauncher(Context context) {
    context.startActivity(new Intent(context, MainActivity.class));
  }
}
