package cn.itguy.zxingportrait.c;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

/* compiled from: FinishListener */
public final class e implements OnCancelListener, OnClickListener {
    private final Activity a;

    public e(Activity activity) {
        this.a = activity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        a();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        a();
    }

    private void a() {
        this.a.finish();
    }
}
