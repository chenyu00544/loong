package org.xutils.common.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.Executor;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.task.AbsTask.State;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

/* compiled from: TaskProxy */
class b<ResultType> extends AbsTask<ResultType> {
    static final b e = new b();
    static final PriorityExecutor f = new PriorityExecutor(true);
    private final AbsTask<ResultType> a;
    private final Executor b;
    private volatile boolean c = false;
    private volatile boolean d = false;

    /* compiled from: TaskProxy */
    class b_1 implements Runnable {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public void run() {
            try {
                if (this.a.c || this.a.isCancelled()) {
                    throw new CancelledException("");
                }
                this.a.onStarted();
                if (this.a.isCancelled()) {
                    throw new CancelledException("");
                }
                this.a.a.a(this.a.a.doBackground());
                this.a.a(this.a.a.getResult());
                if (this.a.isCancelled()) {
                    throw new CancelledException("");
                }
                this.a.onSuccess(this.a.a.getResult());
            } catch (CancelledException e) {
                this.a.onCancelled(e);
            } catch (Throwable th) {
                this.a.onError(th, false);
            } finally {
                this.a.onFinished();
            }
        }
    }

    /* compiled from: TaskProxy */
    private static class a {
        final b a;
        final Object[] b;

        public a(b bVar, Object... objArr) {
            this.a = bVar;
            this.b = objArr;
        }
    }

    /* compiled from: TaskProxy */
    static final class b extends Handler {
        static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());

        private b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            Throwable th;
            b bVar = null;
            if (message.obj == null) {
                throw new IllegalArgumentException("msg must not be null");
            }
            Object[] objArr;
            if (message.obj instanceof b) {
                bVar = (b) message.obj;
                objArr = null;
            } else if (message.obj instanceof a) {
                a aVar = (a) message.obj;
                bVar = aVar.a;
                objArr = aVar.b;
            } else {
                objArr = null;
            }
            if (bVar == null) {
                throw new RuntimeException("msg.obj not instanceof TaskProxy");
            }
            try {
                switch (message.what) {
                    case 1000000001:
                        bVar.a.onWaiting();
                        return;
                    case 1000000002:
                        bVar.a.onStarted();
                        return;
                    case 1000000003:
                        bVar.a.onSuccess(bVar.getResult());
                        return;
                    case 1000000004:
                        if (a || objArr != null) {
                            th = (Throwable) objArr[0];
                            LogUtil.d(th.getMessage(), th);
                            bVar.a.onError(th, false);
                            return;
                        }
                        throw new AssertionError();
                    case 1000000005:
                        bVar.a.onUpdate(message.arg1, objArr);
                        return;
                    case 1000000006:
                        if (!bVar.c) {
                            bVar.c = true;
                            if (a || objArr != null) {
                                bVar.a.onCancelled((CancelledException) objArr[0]);
                                return;
                            }
                            throw new AssertionError();
                        }
                        return;
                    case 1000000007:
                        if (!bVar.d) {
                            bVar.d = true;
                            bVar.a.onFinished();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Throwable th2) {
                bVar.a(State.ERROR);
                if (message.what != 1000000004) {
                    bVar.a.onError(th2, true);
                } else if (x.isDebug()) {
                    RuntimeException runtimeException = new RuntimeException(th2);
                }
            }
            bVar.a(State.ERROR);
            if (message.what != 1000000004) {
                bVar.a.onError(th2, true);
            } else if (x.isDebug()) {
                RuntimeException runtimeException2 = new RuntimeException(th2);
            }
        }
    }

    b(AbsTask<ResultType> absTask) {
        super(absTask);
        this.a = absTask;
        this.a.a(this);
        a(null);
        Executor executor = absTask.getExecutor();
        if (executor == null) {
            executor = f;
        }
        this.b = executor;
    }

    protected final ResultType doBackground() throws Throwable {
        onWaiting();
        this.b.execute(new a(this.a.getPriority(), new b_1(this)));
        return null;
    }

    protected void onWaiting() {
        a(State.WAITING);
        e.obtainMessage(1000000001, this).sendToTarget();
    }

    protected void onStarted() {
        a(State.STARTED);
        e.obtainMessage(1000000002, this).sendToTarget();
    }

    protected void onSuccess(ResultType resultType) {
        a(State.SUCCESS);
        e.obtainMessage(1000000003, this).sendToTarget();
    }

    protected void onError(Throwable th, boolean z) {
        a(State.ERROR);
        e.obtainMessage(1000000004, new a(this, th)).sendToTarget();
    }

    protected void onUpdate(int i, Object... objArr) {
        e.obtainMessage(1000000005, i, i, new a(this, objArr)).sendToTarget();
    }

    protected void onCancelled(CancelledException cancelledException) {
        a(State.CANCELLED);
        e.obtainMessage(1000000006, new a(this, cancelledException)).sendToTarget();
    }

    protected void onFinished() {
        e.obtainMessage(1000000007, this).sendToTarget();
    }

    final void a(State state) {
        super.a(state);
        this.a.a(state);
    }

    public final Priority getPriority() {
        return this.a.getPriority();
    }

    public final Executor getExecutor() {
        return this.b;
    }
}
