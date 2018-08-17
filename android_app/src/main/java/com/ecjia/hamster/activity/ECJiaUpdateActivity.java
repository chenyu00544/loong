package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ecjia.a.i;
import com.ecjia.a.q;
import com.ecjia.component.a.am;
import com.ecjia.component.a.am.b;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ECJia_VERSION;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ECJiaUpdateActivity extends a {
    private final int A = 2;
    private final int B = 3;
    private final int C = 4;
    private final int D = 5;
    private int E;
    private TextView F;
    private final String G = "/mnt/sdcard/";
    ECJia_VERSION a;
    File b = Environment.getExternalStorageDirectory();
    int c = 0;
    String d = null;
    public boolean e = false;
    Handler k = new ECJiaUpdateActivity_1(this);
    a l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private FrameLayout t;
    private ProgressBar u;
    private TextView v;
    private boolean w = false;
    private boolean x = false;
    private int y = 5;
    private final int z = 1;

    class ECJiaUpdateActivity_1 extends Handler {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_1(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.obj.equals("filesize")) {
                if (this.a.E == 0) {
                    this.a.s.setText(this.a.getResources().getString(R.string.update_size_left) + i.a((long) message.what, 3) + "M)");
                    this.a.F.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
                    this.a.F.setEnabled(false);
                    this.a.F.setVisibility(8);
                    this.a.r.setVisibility(8);
                    this.a.r.setText(R.string.update_delete_file);
                    this.a.y = 2;
                } else if (this.a.E < message.what) {
                    this.a.t.setVisibility(0);
                    this.a.u.setProgress((int) (((float) (this.a.E * 100)) / ((float) message.what)));
                    this.a.s.setText(R.string.update_continue);
                    this.a.s.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    this.a.s.setBackgroundResource(R.drawable.shape_gray_stroke_transparent_bg_corner);
                    this.a.F.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
                    this.a.F.setEnabled(false);
                    this.a.F.setVisibility(8);
                    this.a.r.setVisibility(0);
                    this.a.r.setText(R.string.update_delete_file);
                    this.a.y = 2;
                } else if (this.a.E == message.what) {
                    this.a.s.setText(R.string.update_re_check);
                    this.a.F.setEnabled(true);
                    this.a.F.setBackgroundResource(R.drawable.shape_public_bg);
                    this.a.F.setTextColor(-1);
                    this.a.F.setVisibility(0);
                    this.a.r.setVisibility(0);
                    this.a.r.setText(R.string.update_delete_file);
                    this.a.y = 1;
                }
            } else if (message.obj.equals("progress")) {
                this.a.v.setText(message.what + "%");
            }
        }
    }

    class ECJiaUpdateActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_2(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void onClick(View view) {
            this.a.a(new File(this.a.d));
        }
    }

    class ECJiaUpdateActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_3(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void onClick(View view) {
            this.a.E = 0;
            if (this.a.l == null) {
                ECJiaUpdateActivity.a(this.a.d);
                k kVar = new k(this.a, (int) R.string.update_have_deleted);
                kVar.a(17, 0, 0);
                kVar.a();
                this.a.u.setProgress(0);
                this.a.v.setText("");
                this.a.t.setVisibility(4);
                this.a.s.setText(this.a.getResources().getString(R.string.update_size_left) + i.a((long) this.a.c, 3) + "M)");
                this.a.s.setBackgroundResource(R.drawable.shape_public_bg);
                this.a.s.setTextColor(-1);
                this.a.F.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
                this.a.F.setTextColor(Color.parseColor("#666666"));
                this.a.F.setEnabled(false);
                this.a.F.setVisibility(8);
                this.a.r.setVisibility(8);
                this.a.y = 2;
            } else if (this.a.l.c) {
                ECJiaUpdateActivity.a(this.a.d);
                this.a.u.setProgress(0);
                this.a.v.setText("");
                this.a.t.setVisibility(4);
                this.a.s.setText(this.a.getResources().getString(R.string.update_size_left) + i.a((long) this.a.c, 3) + "M)");
                this.a.s.setBackgroundResource(R.drawable.shape_public_bg);
                this.a.s.setTextColor(-1);
                this.a.F.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
                this.a.F.setTextColor(Color.parseColor("#666666"));
                this.a.F.setEnabled(false);
                this.a.F.setVisibility(8);
                this.a.r.setVisibility(8);
                this.a.y = 2;
            } else {
                this.a.e = true;
                this.a.w = false;
                this.a.x = true;
                this.a.r.setVisibility(8);
            }
        }
    }

    class ECJiaUpdateActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_4(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void onClick(View view) {
            this.a.e = false;
            switch (this.a.y) {
                case 1:
                    this.a.s.setText(R.string.update_re_check);
                    am.a(this.a).c(this.a);
                    return;
                case 2:
                    this.a.s.setBackgroundResource(R.drawable.shape_gray_stroke_transparent_bg_corner);
                    this.a.s.setTextColor(Color.parseColor("#000000"));
                    this.a.s.setText("");
                    this.a.v.setVisibility(0);
                    this.a.w = false;
                    this.a.x = false;
                    this.a.l = null;
                    this.a.l = new a(this.a);
                    this.a.l.execute(new String[]{"0"});
                    this.a.r.setVisibility(0);
                    this.a.r.setText(R.string.update_cancel);
                    this.a.y = 3;
                    return;
                case 3:
                    this.a.w = true;
                    this.a.s.setText(R.string.update_continue);
                    this.a.r.setVisibility(0);
                    this.a.r.setText(R.string.update_cancel);
                    this.a.v.setVisibility(4);
                    this.a.y = 4;
                    return;
                case 4:
                    if (this.a.l.isCancelled()) {
                        this.a.l = new a(this.a);
                        this.a.l.execute(new String[]{"0"});
                    }
                    this.a.w = false;
                    this.a.v.setVisibility(0);
                    this.a.s.setText("");
                    this.a.r.setVisibility(0);
                    this.a.r.setText(R.string.update_cancel);
                    this.a.y = 3;
                    return;
                case 5:
                    this.a.s.setText(R.string.update_re_check);
                    return;
                default:
                    return;
            }
        }
    }

    class ECJiaUpdateActivity_5 implements b {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_5(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void a(int i, ECJia_VERSION eCJia_VERSION) {
            switch (i) {
                case -1:
                case 0:
                    this.a.y = 1;
                    this.a.p.setText(R.string.update_now_is_newest);
                    this.a.findViewById(R.id.new_version_fearture).setVisibility(8);
                    this.a.q.setVisibility(8);
                    this.a.findViewById(R.id.update_allrealy_new).setVisibility(0);
                    return;
                case 1:
                    this.a.a = eCJia_VERSION;
                    this.a.p.setText("(" + this.a.getResources().getString(R.string.update_have_checked_new) + eCJia_VERSION.getVersion() + ")");
                    this.a.q.setText(eCJia_VERSION.getChangelog());
                    this.a.q.setVisibility(0);
                    this.a.findViewById(R.id.update_allrealy_new).setVisibility(8);
                    this.a.findViewById(R.id.new_version_fearture).setVisibility(0);
                    this.a.d = "/mnt/sdcard/" + com.ecjia.consts.a.a + "-v" + eCJia_VERSION.getVersion() + SocializeConstants.OP_DIVIDER_MINUS + eCJia_VERSION.getUpdate_time() + ".apk";
                    this.a.s.setBackgroundResource(R.drawable.shape_public_bg);
                    this.a.s.setTextColor(-1);
                    this.a.e();
                    return;
                default:
                    return;
            }
        }

        public void a() {
            this.a.y = 1;
            this.a.s.setText(R.string.update_re_check);
            this.a.findViewById(R.id.update_allrealy_new).setVisibility(0);
        }
    }

    class ECJiaUpdateActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_6(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaUpdateActivity_7 extends Thread {
        final /* synthetic */ ECJiaUpdateActivity a;

        ECJiaUpdateActivity_7(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.a = eCJiaUpdateActivity;
        }

        public void run() {
            synchronized ("") {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a.a.getInstall_url()).openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    this.a.c = httpURLConnection.getContentLength();
                    httpURLConnection.disconnect();
                    File file = new File(this.a.d);
                    if (file.exists()) {
                        this.a.E = i.a(file);
                    } else {
                        this.a.E = 0;
                    }
                    q.a("localfileSize == " + this.a.E);
                    Message message;
                    if (this.a.E == 0) {
                        message = new Message();
                        message.what = this.a.c;
                        message.obj = "filesize";
                        this.a.k.sendMessage(message);
                    } else if (this.a.E < this.a.c) {
                        message = new Message();
                        message.what = this.a.c;
                        message.obj = "filesize";
                        this.a.k.sendMessage(message);
                    } else if (this.a.E == this.a.c) {
                        message = new Message();
                        message.what = this.a.c;
                        message.obj = "filesize";
                        this.a.k.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    class a extends AsyncTask<String, String, String> {
        boolean a = false;
        final /* synthetic */ ECJiaUpdateActivity b;
        private boolean c = false;

        a(ECJiaUpdateActivity eCJiaUpdateActivity) {
            this.b = eCJiaUpdateActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            b((String[]) objArr);
        }

        protected void onCancelled() {
            super.onCancelled();
            this.b.u.setProgress(0);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.b.t.setVisibility(0);
            this.b.u.setVisibility(0);
            this.b.v.setVisibility(0);
            this.b.v.setText(((int) ((((float) this.b.E) * 100.0f) / ((float) this.b.c))) + "%");
            this.b.u.setProgress((int) ((((float) this.b.E) * 100.0f) / ((float) this.b.c)));
            this.b.r.setBackgroundResource(R.drawable.shape_white_stroke_corner_press);
            this.b.r.setEnabled(true);
        }

        protected String a(String... strArr) {
            RandomAccessFile randomAccessFile;
            Exception exception;
            HttpURLConnection httpURLConnection;
            Exception exception2;
            Throwable th;
            HttpURLConnection httpURLConnection2 = null;
            this.c = false;
            InputStream inputStream;
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.b.a.getInstall_url()).openConnection();
                try {
                    httpURLConnection3.setRequestMethod("GET");
                    String str = "bytes=" + this.b.E + SocializeConstants.OP_DIVIDER_MINUS + this.b.c;
                    q.a("range==" + str);
                    httpURLConnection3.setRequestProperty("Range", str);
                    httpURLConnection3.setConnectTimeout(5000);
                    q.a("length==" + this.b.c);
                    httpURLConnection3.connect();
                    inputStream = httpURLConnection3.getInputStream();
                } catch (Exception e) {
                    randomAccessFile = null;
                    inputStream = null;
                    exception = e;
                    httpURLConnection = httpURLConnection3;
                    exception2 = exception;
                    try {
                        Log.d("test", exception2.getMessage());
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection2 = httpURLConnection;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    randomAccessFile = null;
                    inputStream = null;
                    httpURLConnection2 = httpURLConnection3;
                    th = th3;
                    if (inputStream != null) {
                        inputStream.close();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    int a = this.b.E;
                    randomAccessFile = new RandomAccessFile(this.b.d, "rw");
                    try {
                        randomAccessFile.seek((long) this.b.E);
                        while (!this.b.x) {
                            while (this.b.w) {
                                Thread.sleep(1000);
                                if (this.a) {
                                    break;
                                }
                                q.a("sleep中");
                            }
                            if (!this.a) {
                                int read = inputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                randomAccessFile.write(bArr, 0, read);
                                a += read;
                                publishProgress(new String[]{"" + ((int) ((((float) a) * 100.0f) / ((float) this.b.c)))});
                                if (a == this.b.c) {
                                    this.b.x = true;
                                    this.b.E = this.b.c;
                                    this.c = true;
                                    break;
                                }
                            }
                            break;
                        }
                        inputStream.close();
                        randomAccessFile.close();
                        httpURLConnection3.disconnect();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                }
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Exception e4) {
                        exception = e4;
                        httpURLConnection = httpURLConnection3;
                        exception2 = exception;
                    } catch (Throwable th32) {
                        httpURLConnection2 = httpURLConnection3;
                        th = th32;
                    }
                } catch (Exception e42) {
                    randomAccessFile = null;
                    HttpURLConnection httpURLConnection4 = httpURLConnection3;
                    exception2 = e42;
                    httpURLConnection = httpURLConnection4;
                    Log.d("test", exception2.getMessage());
                    if (inputStream != null) {
                        inputStream.close();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                    return null;
                } catch (Throwable th322) {
                    randomAccessFile = null;
                    httpURLConnection2 = httpURLConnection3;
                    th = th322;
                    if (inputStream != null) {
                        inputStream.close();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                exception2 = e5;
                httpURLConnection = null;
                randomAccessFile = null;
                inputStream = null;
                Log.d("test", exception2.getMessage());
                if (inputStream != null) {
                    inputStream.close();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                randomAccessFile = null;
                inputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                }
                throw th;
            }
            return null;
        }

        protected void b(String... strArr) {
            this.b.u.setProgress(Integer.parseInt(strArr[0]));
            this.b.v.setText(strArr[0] + "%");
        }

        protected void a(String str) {
            this.b.t.setVisibility(8);
            this.b.s.setText(R.string.update_re_check);
            this.b.s.setBackgroundResource(R.drawable.shape_public_bg);
            this.b.s.setTextColor(-1);
            this.b.y = 1;
            if (this.b.c == this.b.E) {
                this.b.a(new File(this.b.d));
                this.b.x = true;
                this.b.r.setText(R.string.update_delete_file);
                this.b.r.setVisibility(0);
                this.b.F.setTextColor(-1);
                this.b.F.setBackgroundResource(R.drawable.shape_public_bg);
                this.b.F.setEnabled(true);
                this.b.F.setVisibility(0);
            } else if (this.b.e) {
                ECJiaUpdateActivity.a(this.b.d);
                this.b.u.setProgress(0);
                this.b.v.setText("");
                this.b.t.setVisibility(4);
                this.b.s.setText(this.b.getResources().getString(R.string.update_size_left) + "(" + i.a((long) this.b.c, 3) + "M)");
                this.b.s.setBackgroundResource(R.drawable.shape_public_bg);
                this.b.s.setTextColor(-1);
                this.b.F.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
                this.b.F.setTextColor(Color.parseColor("#666666"));
                this.b.F.setEnabled(false);
                this.b.r.setVisibility(8);
                this.b.y = 2;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_update);
        b();
        c();
    }

    private void c() {
        a();
        this.u = (ProgressBar) findViewById(R.id.progress_bar);
        this.v = (TextView) findViewById(R.id.progress_tex);
        this.t = (FrameLayout) findViewById(R.id.progress_ll);
        this.m = (TextView) findViewById(R.id.name);
        this.n = (TextView) findViewById(R.id.size);
        this.o = (TextView) findViewById(R.id.old_version);
        this.p = (TextView) findViewById(R.id.new_version);
        this.q = (TextView) findViewById(R.id.updeta_log);
        this.F = (TextView) findViewById(R.id.btn_install);
        this.F.setOnClickListener(new ECJiaUpdateActivity_2(this));
        this.r = (TextView) findViewById(R.id.btn_delete);
        this.s = (TextView) findViewById(R.id.update_ok);
        this.m.setText(getResources().getString(R.string.app_name));
        this.o.setText(am.d(this));
        this.r.setOnClickListener(new ECJiaUpdateActivity_3(this));
        this.s.setOnClickListener(new ECJiaUpdateActivity_4(this));
        am.a((Context) this).a(new ECJiaUpdateActivity_5(this));
    }

    protected void onResume() {
        super.onResume();
        if (this.l == null) {
            if (this.a != null) {
                this.n.setText(this.a.getBinary().getFsize());
                this.p.setText("(" + getResources().getString(R.string.update_have_checked_new) + this.a.getVersion() + ")");
                this.q.setText(this.a.getChangelog());
                this.q.setVisibility(0);
                findViewById(R.id.update_allrealy_new).setVisibility(8);
                findViewById(R.id.new_version_fearture).setVisibility(0);
                this.d = "/mnt/sdcard/" + com.ecjia.consts.a.a + "-v" + this.a.getVersion() + SocializeConstants.OP_DIVIDER_MINUS + this.a.getUpdate_time() + ".apk";
                e();
                this.s.setBackgroundResource(R.drawable.shape_public_bg);
                this.s.setTextColor(-1);
                return;
            }
            am.a((Context) this).c(this);
        }
    }

    private void a(File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
        overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.update_topview);
        this.i.setTitleText((int) R.string.update_title);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaUpdateActivity_6(this));
    }

    void b() {
        this.a = (ECJia_VERSION) getIntent().getSerializableExtra("version");
    }

    private void e() {
        new ECJiaUpdateActivity_7(this).start();
    }

    protected void onPause() {
        this.w = true;
        if (this.l != null) {
            this.l.a = true;
        }
        super.onPause();
    }

    public static boolean a(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return b(str);
        }
        return false;
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile() && file.delete()) {
            return true;
        }
        return false;
    }

    protected void onDestroy() {
        this.l = null;
        super.onDestroy();
    }
}
