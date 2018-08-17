package org.xutils.common.task;

import java.util.concurrent.Executor;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;

public abstract class AbsTask<ResultType> implements Cancelable {
    private b a;
    private final Cancelable b;
    private volatile boolean c;
    private volatile State d;
    private ResultType e;

    public enum State {
        IDLE(0),
        WAITING(1),
        STARTED(2),
        SUCCESS(3),
        CANCELLED(4),
        ERROR(5);
        
        private final int a;

        private State(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    protected abstract ResultType doBackground() throws Throwable;

    protected abstract void onError(Throwable th, boolean z);

    protected abstract void onSuccess(ResultType resultType);

    public AbsTask() {
        this(null);
    }

    public AbsTask(Cancelable cancelable) {
        this.a = null;
        this.c = false;
        this.d = State.IDLE;
        this.b = cancelable;
    }

    protected void onWaiting() {
    }

    protected void onStarted() {
    }

    protected void onUpdate(int i, Object... objArr) {
    }

    protected void onCancelled(CancelledException cancelledException) {
    }

    protected void onFinished() {
    }

    public Priority getPriority() {
        return null;
    }

    public Executor getExecutor() {
        return null;
    }

    protected final void update(int i, Object... objArr) {
        if (this.a != null) {
            this.a.onUpdate(i, objArr);
        }
    }

    protected void cancelWorks() {
    }

    protected boolean isCancelFast() {
        return false;
    }

    public final synchronized void cancel() {
        if (!this.c) {
            this.c = true;
            cancelWorks();
            if (!(this.b == null || this.b.isCancelled())) {
                this.b.cancel();
            }
            if (this.d == State.WAITING || (this.d == State.STARTED && isCancelFast())) {
                if (this.a != null) {
                    this.a.onCancelled(new CancelledException("cancelled by user"));
                    this.a.onFinished();
                } else if (this instanceof b) {
                    onCancelled(new CancelledException("cancelled by user"));
                    onFinished();
                }
            }
        }
    }

    public final boolean isCancelled() {
        return this.c || this.d == State.CANCELLED || (this.b != null && this.b.isCancelled());
    }

    public final boolean isFinished() {
        return this.d.value() > State.STARTED.value();
    }

    public final State getState() {
        return this.d;
    }

    public final ResultType getResult() {
        return this.e;
    }

    void a(State state) {
        this.d = state;
    }

    final void a(b bVar) {
        this.a = bVar;
    }

    final void a(ResultType resultType) {
        this.e = resultType;
    }
}
