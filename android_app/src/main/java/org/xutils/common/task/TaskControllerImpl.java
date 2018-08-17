package org.xutils.common.task;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.GroupCallback;
import org.xutils.common.TaskController;
import org.xutils.common.util.LogUtil;
import org.xutils.x.Ext;

public final class TaskControllerImpl implements TaskController {
    private static volatile TaskController a;

    private TaskControllerImpl() {
    }

    public static void registerInstance() {
        if (a == null) {
            synchronized (TaskController.class) {
                if (a == null) {
                    a = new TaskControllerImpl();
                }
            }
        }
        Ext.setTaskController(a);
    }

    public <T> AbsTask<T> start(AbsTask<T> absTask) {
        if (absTask instanceof b) {
            absTask = (b) absTask;
        } else {
            absTask = new b(absTask);
        }
        try {
            absTask.doBackground();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return absTask;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T startSync(org.xutils.common.task.AbsTask<T> r4) throws java.lang.Throwable {
        /*
        r3 = this;
        r1 = 0;
        r4.onWaiting();	 Catch:{ CancelledException -> 0x0012, Throwable -> 0x001d }
        r4.onStarted();	 Catch:{ CancelledException -> 0x0012, Throwable -> 0x001d }
        r0 = r4.doBackground();	 Catch:{ CancelledException -> 0x0012, Throwable -> 0x001d }
        r4.onSuccess(r0);	 Catch:{ CancelledException -> 0x0028, Throwable -> 0x001d }
        r4.onFinished();
    L_0x0011:
        return r0;
    L_0x0012:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r2;
    L_0x0016:
        r4.onCancelled(r1);	 Catch:{ all -> 0x0023 }
        r4.onFinished();
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        r1 = 0;
        r4.onError(r0, r1);	 Catch:{ all -> 0x0023 }
        throw r0;	 Catch:{ all -> 0x0023 }
    L_0x0023:
        r0 = move-exception;
        r4.onFinished();
        throw r0;
    L_0x0028:
        r1 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.common.task.TaskControllerImpl.startSync(org.xutils.common.task.AbsTask):T");
    }

    public <T extends AbsTask<?>> Cancelable startTasks(final GroupCallback<T> groupCallback, final T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("task must not be null");
        }
        final Runnable taskControllerImpl_1 = new Runnable(this) {
            final /* synthetic */ TaskControllerImpl c;
            private final int d = tArr.length;
            private final AtomicInteger e = new AtomicInteger(0);

            public void run() {
                if (this.e.incrementAndGet() == this.d && groupCallback != null) {
                    groupCallback.onAllFinished();
                }
            }
        };
        for (AbsTask absTask : tArr) {
            final GroupCallback<T> groupCallback2 = groupCallback;
            final AbsTask absTask2 = absTask;
            start(new b(this, absTask) {
                final /* synthetic */ TaskControllerImpl d;

                class TaskControllerImpl_2_1 implements Runnable {
                    final /* synthetic */ TaskControllerImpl_2 a;

                    TaskControllerImpl_2_1(TaskControllerImpl_2 taskControllerImpl_2) {
                        this.a = taskControllerImpl_2;
                    }

                    public void run() {
                        if (groupCallback2 != null) {
                            groupCallback2.onSuccess(absTask2);
                        }
                    }
                }

                class TaskControllerImpl_2_4 implements Runnable {
                    final /* synthetic */ TaskControllerImpl_2 a;

                    TaskControllerImpl_2_4(TaskControllerImpl_2 taskControllerImpl_2) {
                        this.a = taskControllerImpl_2;
                    }

                    public void run() {
                        if (groupCallback2 != null) {
                            groupCallback2.onFinished(absTask2);
                        }
                        taskControllerImpl_1.run();
                    }
                }

                protected void onSuccess(Object obj) {
                    super.onSuccess(obj);
                    this.d.post(new TaskControllerImpl_2_1(this));
                }

                protected void onCancelled(final CancelledException cancelledException) {
                    super.onCancelled(cancelledException);
                    this.d.post(new Runnable(this) {
                        final /* synthetic */ TaskControllerImpl_2 b;

                        public void run() {
                            if (groupCallback2 != null) {
                                groupCallback2.onCancelled(absTask2, cancelledException);
                            }
                        }
                    });
                }

                protected void onError(final Throwable th, final boolean z) {
                    super.onError(th, z);
                    this.d.post(new Runnable(this) {
                        final /* synthetic */ TaskControllerImpl_2 c;

                        public void run() {
                            if (groupCallback2 != null) {
                                groupCallback2.onError(absTask2, th, z);
                            }
                        }
                    });
                }

                protected void onFinished() {
                    super.onFinished();
                    this.d.post(new TaskControllerImpl_2_4(this));
                }
            });
        }
        return new Cancelable(this) {
            final /* synthetic */ TaskControllerImpl b;

            public void cancel() {
                for (AbsTask cancel : tArr) {
                    cancel.cancel();
                }
            }

            public boolean isCancelled() {
                boolean z = true;
                for (AbsTask isCancelled : tArr) {
                    if (!isCancelled.isCancelled()) {
                        z = false;
                    }
                }
                return z;
            }
        };
    }

    public void autoPost(Runnable runnable) {
        if (runnable != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                runnable.run();
            } else {
                b.e.post(runnable);
            }
        }
    }

    public void post(Runnable runnable) {
        if (runnable != null) {
            b.e.post(runnable);
        }
    }

    public void postDelayed(Runnable runnable, long j) {
        if (runnable != null) {
            b.e.postDelayed(runnable, j);
        }
    }

    public void run(Runnable runnable) {
        if (b.f.isBusy()) {
            new Thread(runnable).start();
        } else {
            b.f.execute(runnable);
        }
    }

    public void removeCallbacks(Runnable runnable) {
        b.e.removeCallbacks(runnable);
    }
}
