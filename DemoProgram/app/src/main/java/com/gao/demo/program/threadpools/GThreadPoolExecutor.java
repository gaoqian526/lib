package com.gao.demo.program.threadpools;

import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gaoqian on 2018/3/20.
 */

public class GThreadPoolExecutor extends ThreadPoolExecutor {

  static final String TAG = GThreadPoolExecutor.class.getSimpleName();

  static final int CORE_POOL_SIZE = 3;
  static final int MAX_MUM_POOL_SIZE = 50;
  static final long KEEP_ALIVE_TIME = 30;

  //===========control thread status==================
  ReentrantLock reentrantLock = new ReentrantLock();
  Condition condition = reentrantLock.newCondition();
  //=====================end==========================

  public static GThreadPoolExecutor newGThreadPoolExecutor() {
    return new GThreadPoolExecutor(CORE_POOL_SIZE, MAX_MUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>());
  }

  public GThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  public GThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
  }

  public GThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
  }

  public GThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
  }

  @Override protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    Log.e(TAG, "beforeExecute");
  }

  @Override protected void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    Log.e(TAG, "afterExecute");
  }

  @Override protected void terminated() {
    super.terminated();
    Log.e(TAG, "terminated");
  }
}
