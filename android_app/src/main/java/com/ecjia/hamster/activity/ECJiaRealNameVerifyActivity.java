package com.ecjia.hamster.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ecjia.a.p;
import com.ecjia.a.w;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.a.z;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.component.view.m;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ECJiaRealNameVerifyActivity extends b implements TextWatcher, a {
    private z a;
    private al b;
    @BindView(2131558557)
    Button btnSubmit;
    private c c;
    private m d;
    private Bitmap e;
    @BindView(2131558551)
    EditText etBankCard;
    @BindView(2131558550)
    EditText etBankName;
    @BindView(2131558548)
    EditText etCredentialName;
    @BindView(2131558549)
    EditText etCredentialNumber;
    @BindView(2131558555)
    ImageView ivCredentialBack;
    @BindView(2131558553)
    ImageView ivCredentialFront;
    private Bitmap k;
    private String l;
    @BindView(2131558554)
    FrameLayout llCredentialBack;
    @BindView(2131558552)
    FrameLayout llCredentialFront;
    private String m;
    private int n;
    private String o;
    private File p;
    private String q;
    private String r;
    private String s;
    private String t;
    @BindView(2131558546)
    ECJiaTopView topviewRealNameVerify;
    private String u;
    private boolean v;
    private boolean w;
    private boolean x;

    class ECJiaRealNameVerifyActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRealNameVerifyActivity a;

        ECJiaRealNameVerifyActivity_1(ECJiaRealNameVerifyActivity eCJiaRealNameVerifyActivity) {
            this.a = eCJiaRealNameVerifyActivity;
        }

        public void onClick(View view) {
            this.a.i();
        }
    }

    class ECJiaRealNameVerifyActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaRealNameVerifyActivity a;

        ECJiaRealNameVerifyActivity_2(ECJiaRealNameVerifyActivity eCJiaRealNameVerifyActivity) {
            this.a = eCJiaRealNameVerifyActivity;
        }

        public void onClick(View view) {
            this.a.g();
            this.a.d.b();
        }
    }

    class ECJiaRealNameVerifyActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaRealNameVerifyActivity a;

        ECJiaRealNameVerifyActivity_3(ECJiaRealNameVerifyActivity eCJiaRealNameVerifyActivity) {
            this.a = eCJiaRealNameVerifyActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), 102);
            this.a.d.b();
        }
    }

    class ECJiaRealNameVerifyActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaRealNameVerifyActivity a;

        ECJiaRealNameVerifyActivity_4(ECJiaRealNameVerifyActivity eCJiaRealNameVerifyActivity) {
            this.a = eCJiaRealNameVerifyActivity;
        }

        public void onClick(View view) {
            this.a.d.b();
        }
    }

    class ECJiaRealNameVerifyActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaRealNameVerifyActivity a;

        ECJiaRealNameVerifyActivity_5(ECJiaRealNameVerifyActivity eCJiaRealNameVerifyActivity) {
            this.a = eCJiaRealNameVerifyActivity;
        }

        public void onClick(View view) {
            this.a.c.b();
        }
    }

    class ECJiaRealNameVerifyActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaRealNameVerifyActivity a;

        ECJiaRealNameVerifyActivity_6(ECJiaRealNameVerifyActivity eCJiaRealNameVerifyActivity) {
            this.a = eCJiaRealNameVerifyActivity;
        }

        public void onClick(View view) {
            this.a.c.b();
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_real_name_verify);
        ButterKnife.bind((Activity) this);
        this.a = new z(this);
        this.a.a((a) this);
        this.b = new al(this);
        this.b.a((a) this);
        b();
        c();
        e();
    }

    private void c() {
        this.etBankName.addTextChangedListener(this);
        this.etBankCard.addTextChangedListener(this);
        this.etCredentialName.addTextChangedListener(this);
        this.etCredentialNumber.addTextChangedListener(this);
        this.topviewRealNameVerify.setTitleText((int) R.string.real_name_verify);
        this.topviewRealNameVerify.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaRealNameVerifyActivity_1(this));
    }

    public void a(String str, String str2, ax axVar) {
        k kVar;
        if (str.equals(" user/account/realname/verify")) {
            if (axVar.b() != 1) {
                kVar = new k((Context) this, axVar.d());
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (this.v) {
                this.q = this.a.a.b();
                this.r = this.a.a.d();
                this.s = this.a.a.e();
                this.t = this.a.a.f();
                this.etCredentialName.setText(this.a.a.b());
                this.etCredentialNumber.setText(this.a.a.d());
                this.etBankName.setText(this.a.a.e());
                this.etBankCard.setText(this.a.a.f());
                if (!TextUtils.isEmpty(this.a.a.g())) {
                    this.w = true;
                    this.l = "isEditPic";
                    p.a((Context) this).a(this.ivCredentialFront, this.a.a.g());
                    this.ivCredentialFront.setVisibility(0);
                    this.llCredentialFront.setVisibility(8);
                }
                if (!TextUtils.isEmpty(this.a.a.h())) {
                    this.x = true;
                    this.m = "isEditPic";
                    p.a((Context) this).a(this.ivCredentialBack, this.a.a.h());
                    this.ivCredentialBack.setVisibility(0);
                    this.llCredentialBack.setVisibility(8);
                }
                this.v = false;
            } else {
                this.b.a(true);
            }
        } else if (!str.equals("user/info")) {
        } else {
            if (axVar.b() == 1) {
                finish();
                Intent intent = new Intent(this, ECJiaVerifyScheduleActivity.class);
                intent.putExtra("real_id", this.a.a.a());
                startActivity(intent);
                return;
            }
            kVar = new k((Context) this, axVar.d());
            kVar.a(17, 0, 0);
            kVar.a();
        }
    }

    void b() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        new File(externalStorageDirectory, "/ecjia/b2b2c/DCIM/Camera").mkdirs();
        this.o = externalStorageDirectory.toString() + "/ecjia/b2b2c/DCIM/Camera";
    }

    private void e() {
        this.u = getIntent().getStringExtra("real_id");
        if (!TextUtils.isEmpty(this.u)) {
            this.v = true;
            this.a.a(this.h.e().m(), this.u, "", "", "", "", "", "");
        }
    }

    private void f() {
        k kVar;
        if (TextUtils.isEmpty(this.q)) {
            kVar = new k((Context) this, this.g.getString(R.string.credential_name_is_empty));
            kVar.a(17, 0, 0);
            kVar.a();
        } else if (!w.b(this.r)) {
            kVar = new k((Context) this, this.g.getString(R.string.credential_number_is_empty));
            kVar.a(17, 0, 0);
            kVar.a();
        } else if (TextUtils.isEmpty(this.s)) {
            kVar = new k((Context) this, this.g.getString(R.string.bank_name_is_empty));
            kVar.a(17, 0, 0);
            kVar.a();
        } else if (!w.c(this.t)) {
            kVar = new k((Context) this, this.g.getString(R.string.bank_card_is_empty));
            kVar.a(17, 0, 0);
            kVar.a();
        } else if (TextUtils.isEmpty(this.l) || TextUtils.isEmpty(this.m)) {
            kVar = new k((Context) this, this.g.getString(R.string.upload_credential_is_empty));
            kVar.a(17, 0, 0);
            kVar.a();
        } else {
            this.a.a(this.h.e().m(), this.u, this.q, this.r, this.s, this.t, this.l, this.m);
        }
    }

    @OnClick({2131558553, 2131558552, 2131558555, 2131558554, 2131558557})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_credential_front:
                this.n = 1001;
                a((int) R.id.ll_credential_front);
                return;
            case R.id.iv_credential_front:
                this.n = 1001;
                a((int) R.id.iv_credential_front);
                return;
            case R.id.ll_credential_back:
                this.n = 1002;
                a((int) R.id.ll_credential_back);
                return;
            case R.id.iv_credential_back:
                this.n = 1002;
                a((int) R.id.iv_credential_back);
                return;
            case R.id.btn_submit:
                f();
                return;
            default:
                return;
        }
    }

    private void a(int i) {
        this.d = new m(this);
        switch (i) {
            case R.id.ll_credential_front:
                this.d.f.setText(this.g.getString(R.string.upload_idcard_front));
                this.d.b.setVisibility(0);
                this.d.a.setImageResource(R.drawable.idcard_front);
                break;
            case R.id.iv_credential_front:
                this.d.f.setText(this.g.getString(R.string.upload_idcard_front));
                this.d.b.setVisibility(8);
                if (!this.w) {
                    this.d.a.setImageBitmap(this.e);
                    break;
                } else {
                    this.d.a.setImageResource(R.drawable.idcard_front);
                    break;
                }
            case R.id.ll_credential_back:
                this.d.f.setText(this.g.getString(R.string.upload_idcard_back));
                this.d.b.setVisibility(0);
                this.d.a.setImageResource(R.drawable.idcard_back);
                break;
            case R.id.iv_credential_back:
                this.d.f.setText(this.g.getString(R.string.upload_idcard_back));
                this.d.b.setVisibility(8);
                if (!this.x) {
                    this.d.a.setImageBitmap(this.k);
                    break;
                } else {
                    this.d.a.setImageResource(R.drawable.idcard_back);
                    break;
                }
        }
        this.d.a();
        this.d.d.setOnClickListener(new ECJiaRealNameVerifyActivity_2(this));
        this.d.e.setOnClickListener(new ECJiaRealNameVerifyActivity_3(this));
        this.d.c.setOnClickListener(new ECJiaRealNameVerifyActivity_4(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1) {
            a(this.p.getPath());
        } else if (i == 102 && i2 == -1 && intent != null) {
            Cursor managedQuery = managedQuery(intent.getData(), new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = managedQuery.getColumnIndexOrThrow("_data");
            managedQuery.moveToFirst();
            a(managedQuery.getString(columnIndexOrThrow));
        }
    }

    private void g() {
        this.p = new File(this.o, "verify _" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg");
        if (this.p.isFile()) {
            this.p.delete();
        }
        Parcelable fromFile = Uri.fromFile(this.p);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", fromFile);
        startActivityForResult(intent, 101);
    }

    private void a(String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int ceil = (int) Math.ceil((double) (options.outWidth / 700));
        int ceil2 = (int) Math.ceil((double) (options.outHeight / 1085));
        if (ceil > 1 || ceil2 > 1) {
            if (ceil >= ceil2) {
                options.inSampleSize = ceil;
            } else {
                options.inSampleSize = ceil2;
            }
        }
        options.inJustDecodeBounds = false;
        a(BitmapFactory.decodeFile(str, options), str);
    }

    private void a(Bitmap bitmap, String str) {
        if (bitmap != null) {
            switch (this.n) {
                case 1001:
                    if (this.w) {
                        this.w = false;
                    }
                    this.e = bitmap;
                    this.ivCredentialFront.setVisibility(0);
                    this.e = a(this.e);
                    this.l = str;
                    this.ivCredentialFront.setImageBitmap(this.e);
                    this.llCredentialFront.setVisibility(8);
                    h();
                    return;
                case 1002:
                    if (this.x) {
                        this.x = false;
                    }
                    this.k = bitmap;
                    this.ivCredentialBack.setVisibility(0);
                    this.k = a(this.k);
                    this.m = str;
                    this.ivCredentialBack.setImageBitmap(this.k);
                    this.llCredentialBack.setVisibility(8);
                    h();
                    return;
                default:
                    return;
            }
        }
    }

    private Bitmap a(Bitmap bitmap) {
        if (bitmap.getWidth() < bitmap.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) 90, (float) bitmap.getWidth(), (float) bitmap.getHeight());
            try {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void h() {
        if (TextUtils.isEmpty(this.q) || TextUtils.isEmpty(this.r) || TextUtils.isEmpty(this.s) || TextUtils.isEmpty(this.t) || TextUtils.isEmpty(this.l) || TextUtils.isEmpty(this.m)) {
            this.btnSubmit.setEnabled(false);
            this.btnSubmit.setTextColor(this.g.getColor(R.color.actionsheet_gray));
            return;
        }
        this.btnSubmit.setEnabled(true);
        this.btnSubmit.setTextColor(this.g.getColor(R.color.my_white));
    }

    private void i() {
        CharSequence obj = this.etCredentialName.getText().toString();
        CharSequence obj2 = this.etCredentialNumber.getText().toString();
        if (TextUtils.isEmpty(obj) && TextUtils.isEmpty(obj2) && TextUtils.isEmpty(this.m) && TextUtils.isEmpty(this.l)) {
            finish();
            return;
        }
        this.c = new c(this, this.g.getString(R.string.public_tips), this.g.getString(R.string.identify_finish_tips));
        this.c.a();
        this.c.d.setOnClickListener(new ECJiaRealNameVerifyActivity_5(this));
        this.c.b.setOnClickListener(new ECJiaRealNameVerifyActivity_6(this));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            i();
        }
        return true;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.q = this.etCredentialName.getText().toString();
        this.r = this.etCredentialNumber.getText().toString();
        this.t = this.etBankCard.getText().toString();
        this.s = this.etBankName.getText().toString();
        h();
    }
}
