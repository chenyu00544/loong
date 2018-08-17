package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

final class q extends FutureTask<y> {
    final /* synthetic */ u a;
    final /* synthetic */ p b;

    q(p pVar, Callable callable, u uVar) {
        this.b = pVar;
        this.a = uVar;
        super(callable);
    }

    protected final void done() {
        x a = this.a.a();
        if (a.f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a.h()) {
                a.g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (CancellationException e3) {
            a.g();
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("An error occured while executing http request", th);
        }
    }
}
