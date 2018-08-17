package com.ecjia.hamster.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.i;
import com.ecjia.component.view.b;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.accs.ErrorCode;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ECJiaCommentCreateActivity extends a implements OnClickListener, a {
    private ImageView A;
    private ImageView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private ImageView I;
    private ImageView J;
    private ImageView K;
    private ImageView L;
    private ImageView M;
    private ArrayList<ImageView> N = new ArrayList();
    private ArrayList<ImageView> O = new ArrayList();
    private ArrayList<TextView> P = new ArrayList();
    private b Q;
    private String R;
    private ArrayList<Bitmap> S = new ArrayList();
    private ArrayList<String> T = new ArrayList();
    private ArrayList<String> U = new ArrayList();
    String a;
    private TextView b;
    private ImageView c;
    private String d;
    private i e;
    private EditText k;
    private RatingBar l;
    private TextView m;
    private Button n;
    private ImageView o;
    private TextView p;
    private TextView q;
    private boolean r = false;
    private int s = 0;
    private int t = 0;
    private LinearLayout u;
    private TextView v;
    private File w;
    private ImageView x;
    private ImageView y;
    private ImageView z;

    class ECJiaCommentCreateActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaCommentCreateActivity a;

        ECJiaCommentCreateActivity_1(ECJiaCommentCreateActivity eCJiaCommentCreateActivity) {
            this.a = eCJiaCommentCreateActivity;
        }

        public void onClick(View view) {
            this.a.g();
            this.a.finish();
        }
    }

    class ECJiaCommentCreateActivity_2 implements OnFocusChangeListener {
        final /* synthetic */ ECJiaCommentCreateActivity a;

        ECJiaCommentCreateActivity_2(ECJiaCommentCreateActivity eCJiaCommentCreateActivity) {
            this.a = eCJiaCommentCreateActivity;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.a.v.setVisibility(0);
                this.a.v.setEnabled(true);
                return;
            }
            this.a.v.setVisibility(8);
            this.a.v.setEnabled(false);
        }
    }

    class ECJiaCommentCreateActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaCommentCreateActivity a;

        ECJiaCommentCreateActivity_3(ECJiaCommentCreateActivity eCJiaCommentCreateActivity) {
            this.a = eCJiaCommentCreateActivity;
        }

        public void onClick(View view) {
            this.a.f();
            this.a.Q.b();
        }
    }

    class ECJiaCommentCreateActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaCommentCreateActivity a;

        ECJiaCommentCreateActivity_4(ECJiaCommentCreateActivity eCJiaCommentCreateActivity) {
            this.a = eCJiaCommentCreateActivity;
        }

        public void onClick(View view) {
            this.a.h();
            this.a.Q.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        q.b("===onCreate===");
        setContentView(R.layout.activity_comment_create);
        PushAgent.getInstance(this).onAppStart();
        e();
        Intent intent = getIntent();
        this.d = intent.getStringExtra("rec_id");
        this.s = intent.getIntExtra("type", 0);
        q.a("rec_id========" + this.d);
        q.a("intentType========" + this.s);
        this.e = new i(this);
        this.e.a((a) this);
        b();
        if (this.s == 2 || this.s == 0) {
            this.e.b(this.d);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void b() {
        this.b = (TextView) findViewById(R.id.top_view_text);
        this.b.setText(getBaseContext().getResources().getString(R.string.gooddetail_commit));
        this.c = (ImageView) findViewById(R.id.top_view_back);
        this.c.setOnClickListener(new ECJiaCommentCreateActivity_1(this));
        this.v = (TextView) findViewById(R.id.comment_create_close_keyboard);
        this.v.setOnClickListener(this);
        this.k = (EditText) findViewById(R.id.comment_edit);
        this.k.setOnFocusChangeListener(new ECJiaCommentCreateActivity_2(this));
        this.l = (RatingBar) findViewById(R.id.comment_ratingbar);
        this.m = (TextView) findViewById(R.id.comment_send);
        this.n = (Button) findViewById(R.id.comment_checkbox);
        this.o = (ImageView) findViewById(R.id.comment_goods_img);
        this.p = (TextView) findViewById(R.id.comment_goods_title);
        this.q = (TextView) findViewById(R.id.comment_goods_price);
        this.u = (LinearLayout) findViewById(R.id.ll_comment_create_bottom);
        this.p.setText(getIntent().getStringExtra("goods_name"));
        if (k.a(getIntent().getStringExtra("goods_price")) == 0.0f) {
            this.q.setText("免费");
        } else {
            this.q.setText(getIntent().getStringExtra("goods_price"));
        }
        ImageLoader.getInstance().displayImage(getIntent().getStringExtra("goods_img"), this.o);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.H = (TextView) findViewById(R.id.tv_first_upload);
        this.x = (ImageView) findViewById(R.id.iv_upload1);
        this.C = (TextView) findViewById(R.id.tv_upload1);
        this.I = (ImageView) findViewById(R.id.iv_del_pic1);
        this.C.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.N.add(this.x);
        this.P.add(this.C);
        this.O.add(this.I);
        this.y = (ImageView) findViewById(R.id.iv_upload2);
        this.D = (TextView) findViewById(R.id.tv_upload2);
        this.J = (ImageView) findViewById(R.id.iv_del_pic2);
        this.D.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.N.add(this.y);
        this.P.add(this.D);
        this.O.add(this.J);
        this.z = (ImageView) findViewById(R.id.iv_upload3);
        this.E = (TextView) findViewById(R.id.tv_upload3);
        this.K = (ImageView) findViewById(R.id.iv_del_pic3);
        this.E.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.N.add(this.z);
        this.P.add(this.E);
        this.O.add(this.K);
        this.A = (ImageView) findViewById(R.id.iv_upload4);
        this.F = (TextView) findViewById(R.id.tv_upload4);
        this.L = (ImageView) findViewById(R.id.iv_del_pic4);
        this.F.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.N.add(this.A);
        this.P.add(this.F);
        this.O.add(this.L);
        this.B = (ImageView) findViewById(R.id.iv_upload5);
        this.G = (TextView) findViewById(R.id.tv_upload5);
        this.M = (ImageView) findViewById(R.id.iv_del_pic5);
        this.G.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.N.add(this.B);
        this.P.add(this.G);
        this.O.add(this.M);
    }

    @TargetApi(9)
    public void c() {
        String obj = this.k.getText().toString();
        int rating = (int) this.l.getRating();
        com.ecjia.component.view.k kVar;
        if (rating == 0) {
            kVar = new com.ecjia.component.view.k((Context) this, (int) R.string.comment_toast_no_star);
            kVar.a(17, 0, 0);
            kVar.a();
        } else if (obj.trim().isEmpty()) {
            kVar = new com.ecjia.component.view.k((Context) this, (int) R.string.comment_toast_no_content);
            kVar.a(17, 0, 0);
            kVar.a();
            this.k.setText("");
        } else {
            if (this.r) {
                this.t = 1;
            } else {
                this.t = 0;
            }
            this.e.a(this.d, obj, rating, this.t, this.U, this.T);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_upload1:
                a(1);
                return;
            case R.id.iv_del_pic1:
                b(1);
                return;
            case R.id.tv_upload2:
                a(2);
                return;
            case R.id.iv_del_pic2:
                b(2);
                return;
            case R.id.tv_upload3:
                a(3);
                return;
            case R.id.iv_del_pic3:
                b(3);
                return;
            case R.id.tv_upload4:
                a(4);
                return;
            case R.id.iv_del_pic4:
                b(4);
                return;
            case R.id.tv_upload5:
                a(5);
                return;
            case R.id.iv_del_pic5:
                b(5);
                return;
            case R.id.comment_create_close_keyboard:
                g();
                return;
            case R.id.comment_checkbox:
                a(this.r);
                return;
            case R.id.comment_send:
                c();
                return;
            case R.id.top_view_back:
                finish();
                return;
            default:
                return;
        }
    }

    private void a(int i) {
        this.Q = new b(this);
        this.Q.e.setVisibility(0);
        this.Q.a.setOnClickListener(new ECJiaCommentCreateActivity_3(this));
        this.Q.b.setOnClickListener(new ECJiaCommentCreateActivity_4(this));
        this.Q.a();
    }

    private void h() {
        q.b("===getPictureFromPhotos===");
        startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), 732);
    }

    void e() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        new File(externalStorageDirectory, "/ecjia/b2b2c/DCIM/Camera").mkdirs();
        this.a = externalStorageDirectory.toString() + "/ecjia/b2b2c/DCIM/Camera";
        q.b("===initCameroPath===");
    }

    public void f() {
        this.R = this.d + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_order_comment.jpg";
        this.w = new File(this.a, this.R);
        if (this.w.isFile()) {
            this.w.delete();
        }
        Parcelable fromFile = Uri.fromFile(this.w);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", fromFile);
        startActivityForResult(intent, 731);
    }

    private void b(int i) {
        if (i <= this.S.size()) {
            this.S.remove(i - 1);
            this.T.remove(i - 1);
            this.U.remove(i - 1);
        }
        i();
        for (int size = this.N.size() - 1; size > this.S.size() - 1; size--) {
            ((ImageView) this.N.get(size)).setVisibility(4);
            ((ImageView) this.O.get(size)).setVisibility(4);
            if (size == 0) {
                ((ImageView) this.N.get(size)).setVisibility(4);
                ((ImageView) this.O.get(size)).setVisibility(4);
                ((TextView) this.P.get(size)).setVisibility(0);
            } else {
                ((ImageView) this.N.get(size)).setVisibility(4);
                ((ImageView) this.O.get(size)).setVisibility(4);
                ((TextView) this.P.get(size)).setVisibility(4);
                ((TextView) this.P.get(this.S.size())).setVisibility(0);
            }
        }
    }

    private void i() {
        int size = this.S.size();
        for (int i = 0; i < size; i++) {
            ((ImageView) this.N.get(i)).setImageBitmap((Bitmap) this.S.get(i));
            ((ImageView) this.N.get(i)).setVisibility(0);
            ((ImageView) this.O.get(i)).setVisibility(0);
            ((TextView) this.P.get(i)).setVisibility(4);
        }
        if (size < this.P.size()) {
            ((TextView) this.P.get(size)).setVisibility(0);
        }
        if (size == 0) {
            this.H.setVisibility(0);
        } else {
            this.H.setVisibility(8);
        }
    }

    public void a(boolean z) {
        if (z) {
            this.n.setBackgroundResource(R.drawable.comment_checkbox_false);
            this.r = false;
            return;
        }
        this.n.setBackgroundResource(R.drawable.comment_checkbox_true);
        this.r = true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 732 && i2 == -1) {
            q.b("===REQUEST_CODE_PHOTOS===");
            Cursor managedQuery = managedQuery(intent.getData(), new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = managedQuery.getColumnIndexOrThrow("_data");
            managedQuery.moveToFirst();
            a(managedQuery.getString(columnIndexOrThrow));
        } else if (i == 731 && i2 == -1) {
            a(this.w.getPath());
        }
    }

    private void a(String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int ceil = (int) Math.ceil((double) (options.outWidth / ErrorCode.APP_NOT_BIND));
        int ceil2 = (int) Math.ceil((double) (options.outHeight / ErrorCode.APP_NOT_BIND));
        if (ceil > 1 || ceil2 > 1) {
            if (ceil >= ceil2) {
                options.inSampleSize = ceil;
            } else {
                options.inSampleSize = ceil2;
            }
        }
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        this.S.add(decodeFile);
        String str2 = "image_" + this.T.size();
        i.a(this.a, str2 + ".jpg", decodeFile);
        this.U.add(str2);
        this.T.add(this.a + "/" + str2 + ".jpg");
        i();
    }

    public void g() {
        this.k.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.k.getWindowToken(), 0);
    }

    public void a(String str, String str2, ax axVar) {
        com.ecjia.component.view.k kVar;
        if (str.equals("comment/create")) {
            if (axVar.b() == 1) {
                c.a().c(new com.ecjia.a.a.b("comment_succeed"));
                kVar = new com.ecjia.component.view.k((Context) this, (int) R.string.comment_create_succeed);
                kVar.a(17, 0, 0);
                kVar.b(1000);
                kVar.a();
                g();
                setResult(-1, new Intent());
                finish();
                return;
            }
            kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
            kVar.a(17, 0, 0);
            kVar.b(1000);
            kVar.a();
        } else if (!str.equals("orders/comment/detail")) {
        } else {
            if (axVar.b() == 1) {
                this.k.setText(this.e.i);
                this.k.setEnabled(false);
                this.l.setRating(Float.valueOf((float) this.e.h).floatValue());
                this.l.setIsIndicator(true);
                if (this.e.g.size() > 0) {
                    for (int i = 0; i < this.e.g.size(); i++) {
                        ((ImageView) this.N.get(i)).setVisibility(0);
                        ((ImageView) this.N.get(i)).setClickable(false);
                        ((TextView) this.P.get(i)).setVisibility(8);
                        ImageLoader.getInstance().displayImage((String) this.e.g.get(i), (ImageView) this.N.get(i));
                    }
                    this.u.setVisibility(8);
                    this.H.setVisibility(8);
                    return;
                }
                return;
            }
            kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
            kVar.a(17, 0, 0);
            kVar.b(1000);
            kVar.a();
            finish();
        }
    }
}
