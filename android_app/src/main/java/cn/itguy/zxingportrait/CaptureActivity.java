package cn.itguy.zxingportrait;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.itguy.zxingportrait.a.e;
import cn.itguy.zxingportrait.a.h;
import cn.itguy.zxingportrait.b.b;
import cn.itguy.zxingportrait.c.a;
import cn.itguy.zxingportrait.c.f;
import cn.itguy.zxingportrait.camera.c;
import cn.itguy.zxingportrait.view.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.g;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class CaptureActivity extends Activity implements Callback {
    public ImageView a;
    protected boolean b = false;
    protected b c;
    public TextView d;
    public TextView e;
    public FrameLayout f;
    private TextView g;
    private c h;
    private a i;
    private g j;
    private ViewfinderView k;
    private boolean l;
    private Collection<BarcodeFormat> m;
    private Map<DecodeHintType, ?> n;
    private String o;
    private f p;
    private cn.itguy.zxingportrait.b.a q;

    class CaptureActivity_1 implements OnClickListener {
        final /* synthetic */ CaptureActivity a;

        CaptureActivity_1(CaptureActivity captureActivity) {
            this.a = captureActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            this.a.overridePendingTransition(a.a.push_left_in, a.a.push_left_out);
        }
    }

    public ViewfinderView a() {
        return this.k;
    }

    public Handler b() {
        return this.i;
    }

    public c c() {
        return this.h;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.l = false;
        this.p = new f(this);
        this.c = new b(this);
        this.q = new cn.itguy.zxingportrait.b.a(this);
        setContentView(a.f.capture);
        d();
    }

    protected void d() {
        this.f = (FrameLayout) findViewById(e.capture_topview);
        this.a = (ImageView) findViewById(e.btn_back);
        this.d = (TextView) findViewById(e.top_home_back);
        this.a.setOnClickListener(new CaptureActivity_1(this));
        this.e = (TextView) findViewById(e.right_text);
        this.g = (TextView) findViewById(e.top_view_text);
    }

    public void finish() {
        overridePendingTransition(a.a.push_left_in, a.a.push_left_out);
        super.finish();
    }

    protected void onResume() {
        super.onResume();
        this.h = new c(getApplication());
        this.k = (ViewfinderView) findViewById(e.viewfinder_view);
        this.k.setCameraManager(this.h);
        this.i = null;
        h();
        SurfaceHolder holder = ((SurfaceView) findViewById(e.preview_view)).getHolder();
        if (this.l) {
            a(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.c.a();
        this.q.a(this.h);
        this.p.c();
        this.m = null;
        this.o = null;
    }

    protected void onPause() {
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        this.p.b();
        this.q.a();
        this.h.b();
        if (!this.l) {
            ((SurfaceView) findViewById(e.preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    protected void onDestroy() {
        this.p.d();
        this.k.recycleLineDrawable();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            overridePendingTransition(a.a.push_left_in, a.a.push_left_out);
            finish();
            return true;
        }
        switch (i) {
            case 27:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    private void a(Bitmap bitmap, g gVar) {
        if (this.i == null) {
            this.j = gVar;
            return;
        }
        if (gVar != null) {
            this.j = gVar;
        }
        if (this.j != null) {
            this.i.sendMessage(Message.obtain(this.i, e.decode_succeeded, this.j));
        }
        this.j = null;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!this.l) {
            this.l = true;
            a(surfaceHolder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.l = false;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void a(g gVar, Bitmap bitmap, float f) {
        this.p.a();
        this.c.b();
        String a = gVar.a();
        if (a == null || "".equals(a)) {
            a = "没有结果";
        }
    }

    private void a(SurfaceHolder surfaceHolder) {
        if (surfaceHolder != null && !this.h.a()) {
            try {
                this.h.a(surfaceHolder);
                if (this.i == null) {
                    this.i = new a(this, this.m, this.n, this.o, this.h);
                }
                a(null, null);
            } catch (IOException e) {
                f();
            } catch (RuntimeException e2) {
                g();
            }
        }
    }

    private void f() {
        Resources resources = getBaseContext().getResources();
        Builder builder = new Builder(this);
        builder.setTitle(resources.getString(h.scanning_unusual));
        builder.setMessage(resources.getString(h.scanning_exist_unusual));
        builder.setPositiveButton(resources.getString(h.scanning_finish), new cn.itguy.zxingportrait.c.e(this));
        builder.setOnCancelListener(new cn.itguy.zxingportrait.c.e(this));
        builder.show();
    }

    private void g() {
        Resources resources = getBaseContext().getResources();
        Builder builder = new Builder(this);
        builder.setTitle(resources.getString(h.scanning_notify));
        builder.setMessage(resources.getString(h.scanning_notify_content));
        builder.setPositiveButton(resources.getString(h.scanning_comfirm), new cn.itguy.zxingportrait.c.e(this));
        builder.setOnCancelListener(new cn.itguy.zxingportrait.c.e(this));
        builder.show();
    }

    private void h() {
        this.k.setVisibility(0);
    }

    public void e() {
        this.k.drawViewfinder();
    }
}
