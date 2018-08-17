package cn.itguy.zxingportrait.c;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

/* compiled from: InactivityTimer */
public final class f {
    private static final String a = f.class.getSimpleName();
    private final Activity b;
    private final cn.itguy.zxingportrait.a.a.a c = ((cn.itguy.zxingportrait.a.a.a) new cn.itguy.zxingportrait.a.a.b().a());
    private final BroadcastReceiver d = new b();
    private a e;

    /* compiled from: InactivityTimer */
    private final class a extends AsyncTask<Object, Object, Object> {
        final /* synthetic */ f a;

        private a(f fVar) {
            this.a = fVar;
        }

        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(300000);
                Log.i(f.a, "Finishing activity due to inactivity");
                this.a.b.finish();
            } catch (InterruptedException e) {
            }
            return null;
        }
    }

    /* compiled from: InactivityTimer */
    private final class b extends BroadcastReceiver {
        final /* synthetic */ f a;

        private b(f fVar) {
            this.a = fVar;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if ((intent.getIntExtra("plugged", -1) <= 0 ? 1 : null) != null) {
                    this.a.a();
                } else {
                    this.a.f();
                }
            }
        }
    }

    public f(Activity activity) {
        this.b = activity;
        a();
    }

    public synchronized void a() {
        f();
        this.e = new a();
        this.c.a(this.e, new Object[0]);
    }

    public void b() {
        f();
        this.b.unregisterReceiver(this.d);
    }

    public void c() {
        this.b.registerReceiver(this.d, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        a();
    }

    private synchronized void f() {
        AsyncTask asyncTask = this.e;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.e = null;
        }
    }

    public void d() {
        f();
    }
}
