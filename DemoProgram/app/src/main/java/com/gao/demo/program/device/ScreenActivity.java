package com.gao.demo.program.device;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.gao.demo.program.base.BaseActivity;

/**
 * Created by gaoqian on 2018/3/10
 *
 * <p>
 * 屏幕相关信息,目前只有屏幕密度相关计算
 *
 * 手机/电脑屏幕 像素密度计算公式
 *
 * 屏幕多少英寸指的是对角线的长度。
 * 像素密度是指（以1920×1080，5英寸为例），1920和1080的平方和开根号（就是直角三角形斜边长的算法），开出来等于2202.9，除以5英寸就得到ppi441左右
 *
 * 例："HTC One（32GB/单卡/国际版）
 *
 * 4.7英寸屏幕，分辨率1920x1080 求解像素密度？
 *
 * 解：√（1920^2+1080^2）=2202.9071
 *
 * 2202.9/5=468.7021（ppi)≈469ppi
 *
 * 答：此屏幕像素密度约是469ppi.
 *
 * px dp转换公式
 * px = dp * (dpi / 160)，原来这里的dpi是归一化后的dpi
 *
 * 需要注意的是，在一个低密度的小屏手机上，仅靠上面的代码是不能获取正确的尺寸的。
 * 比如说，一部240x320像素的低密度手机，如果运行上述代码，获取到的屏幕尺寸是320x427。
 * 因此，研究之后发现，若没有设定多分辨率支持的话，Android系统会将240x320的低密度（120）尺寸转换为中等密度（160）对应的尺寸，
 * 这样的话就大大影响了程序的编码。所以，需要在工程的AndroidManifest.xml文件中，加入supports-screens节点，具体的内容如下：
 * <supports-screens
 * android:smallScreens="true"
 * android:normalScreens="true"
 * android:largeScreens="true"
 * android:resizeable="true"
 * android:anyDensity="true"/>
 *
 * 这样的话，当前的Android程序就支持了多种分辨率，那么就可以得到正确的物理尺寸了
 * </p>
 */

public class ScreenActivity extends BaseActivity {

  static final String TAG = ScreenActivity.class.getSimpleName();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getScreenInfoMethod1();
    getScreenInfoMethod2();
    getScreenInfoMethod3();
  }

  public void getScreenInfoMethod1() {
    // 屏幕宽（像素，如：480px）
    int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    // 屏幕高（像素，如：800p）
    int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

    Log.e(TAG + "  getDefaultDisplay", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
  }

  public void getScreenInfoMethod2() {
    int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    // 屏幕高（像素，如：800p）
    int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
    DisplayMetrics dm = new DisplayMetrics();
    dm = getResources().getDisplayMetrics();

    // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
    float density = dm.density;
    // 屏幕密度（每寸像素：120/160/240/320）
    int densityDPI = dm.densityDpi;
    float xdpi = dm.xdpi;
    float ydpi = dm.ydpi;

    Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
    Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI=" + densityDPI);
    // 屏幕宽（像素，如：480px）
    screenWidth = dm.widthPixels;
    // 屏幕高（像素，如：800px）
    screenHeight = dm.heightPixels;

    Log.e(TAG + "  DisplayMetrics(111)", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
  }

  public void getScreenInfoMethod3() {
    int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    // 屏幕高（像素，如：800p）
    int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
    DisplayMetrics dm = new DisplayMetrics();
    dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
    float density = dm.density;

    // 屏幕密度（每寸像素：120/160/240/320）
    int densityDPI = dm.densityDpi;
    float xdpi = dm.xdpi;
    float ydpi = dm.ydpi;

    Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
    Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI=" + densityDPI);
    // 屏幕宽（dip，如：320dip）
    int screenWidthDip = dm.widthPixels;
    // 屏幕宽（dip，如：533dip）
    int screenHeightDip = dm.heightPixels;

    Log.e(TAG + "  DisplayMetrics(222)", "screenWidthDip=" + screenWidthDip + "; screenHeightDip=" + screenHeightDip);
    // 屏幕宽（px，如：480px）
    screenWidth = (int) (dm.widthPixels * density + 0.5f);
    // 屏幕高（px，如：800px）
    screenHeight = (int) (dm.heightPixels * density + 0.5f);
    Log.e(TAG + "  DisplayMetrics(222)", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
  }
}
