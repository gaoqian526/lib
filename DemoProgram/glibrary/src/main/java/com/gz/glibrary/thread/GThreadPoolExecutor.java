package com.gz.glibrary.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoqian
 * @date 2018/3/26
 */

public class GThreadPoolExecutor extends ThreadPoolExecutor {

  static final String TAG = GThreadPoolExecutor.class.getSimpleName();

  public static GThreadPoolExecutor newGThreadPoolExecutor() {
    GThreadPoolExecutor poolExecutor = new GThreadPoolExecutor(ThreadPoolConfig.CORE_THREAD_COUNT, ThreadPoolConfig.MAX_MIN_COUNT,
        ThreadPoolConfig.KEEP_LIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    return poolExecutor;
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
  }

  @Override protected void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
  }

  @Override protected void terminated() {
    super.terminated();
  }
}
