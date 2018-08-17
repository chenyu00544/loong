package cn.itguy.zxingportrait.a.a;

import android.os.AsyncTask;

/* compiled from: DefaultAsyncTaskExecInterface */
public final class c implements a {
    public <T> void a(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        asyncTask.execute(tArr);
    }
}
