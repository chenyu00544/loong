package com.ecjia.hamster.module.goodsReturn.activity;

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
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.a.y;
import com.ecjia.component.a.i;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.component.view.b;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ECJia_ORDERDETAIL;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.ECJiaWidthChangeAbleRecyclerView;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_REASON;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.accs.ErrorCode;
import com.umeng.message.PushAgent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ECJiaReturnApplyFirstActivity extends a implements OnClickListener, com.ecjia.component.a.a.a, com.ecjia.hamster.module.goodsReturn.a.a.a {
    private String A;
    private File B;
    private TextView C;
    private int D = 0;
    private String E;
    private ECJia_RETURN_REASON F;
    private com.ecjia.hamster.module.goodsReturn.a G;
    private TextView H;
    com.ecjia.hamster.module.goodsReturn.a.a a;
    ECJia_ORDER_GOODS_LIST b;
    ECJia_ORDERDETAIL c;
    String d = "";
    ArrayList<String> e = new ArrayList();
    com.ecjia.hamster.module.goodsReturn.a k;
    public int l = 0;
    ArrayList<ECJia_RETURN_REASON> m = new ArrayList();
    private ImageView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private TextView w;
    private EditText x;
    private TextView y;
    private ECJiaWidthChangeAbleRecyclerView z;

    class ECJiaReturnApplyFirstActivity_10 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_10(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaReturnReasonActivity.class);
            intent.putExtra("return_reason", this.a.m);
            this.a.startActivityForResult(intent, 3);
            this.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }

    class ECJiaReturnApplyFirstActivity_11 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_11(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaReturnApplyFirstActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_1(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(this.a.E)) {
                k kVar = new k(this.a, this.a.g.getString(R.string.setting_call_cannot_empty));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            }
            final c cVar = new c(this.a, this.a.g.getString(R.string.setting_call_or_not), "是否拨打号码" + this.a.E);
            cVar.a(2);
            cVar.b(new OnClickListener(this) {
                final /* synthetic */ ECJiaReturnApplyFirstActivity_1 b;

                public void onClick(View view) {
                    cVar.b();
                    this.b.a.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + this.b.a.c.getService_phone())));
                }
            });
            cVar.c(new OnClickListener(this) {
                final /* synthetic */ ECJiaReturnApplyFirstActivity_1 b;

                public void onClick(View view) {
                    cVar.b();
                }
            });
            cVar.a();
        }
    }

    class ECJiaReturnApplyFirstActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_5(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            this.a.d = "service";
            this.a.s.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
            this.a.s.setBackgroundResource(R.drawable.shape_public_theme_stroke_white_bg);
            this.a.t.setTextColor(this.a.g.getColor(R.color.my_black));
            this.a.t.setBackgroundResource(R.drawable.shape_black_stroke_white_bg);
            this.a.H.setText(R.string.return_next);
        }
    }

    class ECJiaReturnApplyFirstActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_6(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            this.a.d = "return";
            this.a.t.setTextColor(this.a.g.getColor(R.color.public_theme_color_normal));
            this.a.t.setBackgroundResource(R.drawable.shape_public_theme_stroke_white_bg);
            this.a.s.setTextColor(this.a.g.getColor(R.color.my_black));
            this.a.s.setBackgroundResource(R.drawable.shape_black_stroke_white_bg);
            this.a.H.setText(R.string.return_commit);
        }
    }

    class ECJiaReturnApplyFirstActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_7(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            int parseInt = Integer.parseInt(this.a.v.getText().toString());
            if (parseInt > 1) {
                this.a.v.setText((parseInt - 1) + "");
            }
        }
    }

    class ECJiaReturnApplyFirstActivity_8 implements OnClickListener {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_8(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void onClick(View view) {
            int parseInt = Integer.parseInt(this.a.v.getText().toString());
            if (parseInt < Integer.parseInt(this.a.b.getGoods_number())) {
                this.a.v.setText((parseInt + 1) + "");
            }
        }
    }

    class ECJiaReturnApplyFirstActivity_9 implements TextWatcher {
        final /* synthetic */ ECJiaReturnApplyFirstActivity a;

        ECJiaReturnApplyFirstActivity_9(ECJiaReturnApplyFirstActivity eCJiaReturnApplyFirstActivity) {
            this.a = eCJiaReturnApplyFirstActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.a.y.setText(this.a.x.getText().length() + "/500");
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_service_sold);
        PushAgent.getInstance(this).onAppStart();
        de.greenrobot.event.c.a().a((Object) this);
        g();
        e();
        f();
        b();
        this.G = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.G.a((com.ecjia.component.a.a.a) this);
        this.G.a();
    }

    private void e() {
        this.c = (ECJia_ORDERDETAIL) getIntent().getSerializableExtra("order_detail");
        if (this.c != null) {
            this.E = this.c.getService_phone();
        }
        this.b = (ECJia_ORDER_GOODS_LIST) getIntent().getSerializableExtra("order_goods_list");
        if (this.b != null) {
            this.D = Integer.parseInt(this.b.getGoods_number());
        }
    }

    private void f() {
        ImageLoader.getInstance().displayImage(this.b.getImg().getUrl(), this.n);
        this.o.setText(this.b.getName());
        this.p.setText(this.b.getFormated_shop_price());
        this.q.setText(this.D + "");
        this.o.setText(this.b.getName());
        this.v.setText(this.D + "");
    }

    void b() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        new File(externalStorageDirectory, "/ecjia/b2b2c/DCIM/_return").mkdirs();
        this.A = externalStorageDirectory.toString() + "/ecjia/b2b2c/DCIM/_return";
        q.b("===initCameroPath===");
    }

    private void g() {
        a();
        this.n = (ImageView) findViewById(R.id.goods_image);
        this.o = (TextView) findViewById(R.id.goods_name);
        this.p = (TextView) findViewById(R.id.goods_price);
        this.q = (TextView) findViewById(R.id.goods_number);
        this.r = (TextView) findViewById(R.id.connect_seller);
        this.s = (TextView) findViewById(R.id.service_fix);
        this.r.setOnClickListener(new ECJiaReturnApplyFirstActivity_1(this));
        this.s.setOnClickListener(new ECJiaReturnApplyFirstActivity_5(this));
        this.t = (TextView) findViewById(R.id.service_return);
        this.t.setOnClickListener(new ECJiaReturnApplyFirstActivity_6(this));
        this.u = (TextView) findViewById(R.id.goods_reduce);
        this.u.setOnClickListener(new ECJiaReturnApplyFirstActivity_7(this));
        this.v = (TextView) findViewById(R.id.goods_apply_number);
        this.w = (TextView) findViewById(R.id.goods_plus);
        this.w.setOnClickListener(new ECJiaReturnApplyFirstActivity_8(this));
        this.x = (EditText) findViewById(R.id.service_desc);
        this.x.addTextChangedListener(new ECJiaReturnApplyFirstActivity_9(this));
        this.y = (TextView) findViewById(R.id.text_number);
        this.n = (ImageView) findViewById(R.id.goods_image);
        int b = ((y.b(this) - y.a(this, 20)) / 5) - y.a(this, 10);
        this.z = (ECJiaWidthChangeAbleRecyclerView) findViewById(R.id.image_recyclerview);
        this.z.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.a = new com.ecjia.hamster.module.goodsReturn.a.a(this, this.e, y.a(this, 10) + b);
        this.z.setAdapter(this.a);
        this.z.setOritation(0);
        this.H = (TextView) findViewById(R.id.next);
        this.H.setOnClickListener(this);
        View findViewById = findViewById(R.id.image_0);
        LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
        layoutParams.height = b;
        layoutParams.width = b;
        this.z.getLayoutParams().height = b + y.a(this, 10);
        findViewById.setOnClickListener(this);
        this.C = (TextView) findViewById(R.id.reason);
        findViewById(R.id.reason_ll).setOnClickListener(new ECJiaReturnApplyFirstActivity_10(this));
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.service_sold_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_apply_service);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnApplyFirstActivity_11(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goods_apply_number:
                final com.ecjia.component.view.a aVar = new com.ecjia.component.view.a(this, this.v.getText().toString());
                aVar.a.setSelection(aVar.a.length());
                aVar.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaReturnApplyFirstActivity b;

                    public void onClick(View view) {
                        aVar.b();
                    }
                });
                aVar.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaReturnApplyFirstActivity b;

                    public void onClick(View view) {
                        if (TextUtils.isEmpty(aVar.a.getText().toString())) {
                            new k(this.b, "数量不能小于0或者大于已购商品数量").a();
                            return;
                        }
                        int parseInt = Integer.parseInt(aVar.a.getText().toString());
                        if (parseInt > this.b.D || parseInt <= 0) {
                            new k(this.b, "数量不能小于0或者大于已购商品数量").a();
                            return;
                        }
                        this.b.v.setText(parseInt + "");
                        aVar.b();
                    }
                });
                aVar.a();
                return;
            case R.id.image_0:
                h();
                return;
            case R.id.next:
                if (!l()) {
                    return;
                }
                if (this.d.equals("service")) {
                    j();
                    return;
                } else if (this.d.equals("return")) {
                    k();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void h() {
        final b bVar = new b(this);
        bVar.e.setVisibility(0);
        bVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ECJiaReturnApplyFirstActivity b;

            public void onClick(View view) {
                this.b.c();
                bVar.b();
            }
        });
        bVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ECJiaReturnApplyFirstActivity b;

            public void onClick(View view) {
                this.b.i();
                bVar.b();
            }
        });
        bVar.a();
    }

    private void i() {
        startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), 732);
    }

    public void c() {
        this.B = new File(this.A, this.b.getRec_id() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_order_return.jpg");
        if (this.B.isFile()) {
            this.B.delete();
        }
        Parcelable fromFile = Uri.fromFile(this.B);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", fromFile);
        startActivityForResult(intent, 731);
    }

    private void j() {
        Intent intent = new Intent(this, ECJiaReturnApplySecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("return_type", this.d);
        bundle.putString("rec_id", this.b.getRec_id());
        bundle.putString("number", this.v.getText().toString());
        bundle.putString("return_description", this.x.getText().toString());
        bundle.putStringArrayList("return_images", this.e);
        bundle.putString("return_reason", this.F.getReason_id());
        bundle.putSerializable("address", this.c.getAddress());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void k() {
        this.k = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.k.a((com.ecjia.component.a.a.a) this);
        this.k.a(this.d, this.b.getRec_id(), this.F.getReason_id(), this.x.getText().toString(), this.v.getText().toString(), this.e, null);
    }

    private boolean l() {
        if (TextUtils.isEmpty(this.d)) {
            new k((Context) this, "请选择一个服务类型").a();
            return false;
        } else if (this.F != null) {
            return true;
        } else {
            new k((Context) this, "请选择退换货原因").a();
            return false;
        }
    }

    private void a(String str) {
        this.l++;
        q.a("imageFilePath " + str);
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
        String str2 = "image_" + this.l;
        i.a(this.A, str2 + ".jpg", decodeFile);
        this.e.add(this.A + "/" + str2 + ".jpg");
        this.a.notifyDataSetChanged();
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
            a(this.B.getPath());
        } else if (i == 3 && i2 == -1) {
            this.F = (ECJia_RETURN_REASON) intent.getSerializableExtra("return_reason");
            this.C.setText(this.F.getReason_name());
        }
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void a(String str, int i) {
        this.e.remove(str);
        this.a.notifyDataSetChanged();
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if ("RETURN_APPLY_SUCCESS".equals(bVar.c())) {
            finish();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/reason".equals(str)) {
            if (axVar.b() == 1) {
                this.m.clear();
                this.m.addAll(this.G.f);
            }
        } else if ("order/return/apply".equals(str) && axVar.b() == 1) {
            finish();
            Intent intent = new Intent(this, ECJiaReturnSuccessActivity.class);
            intent.putExtra("return_type", this.d);
            intent.putExtra("return_id", this.k.b);
            intent.putExtra("apply_time", this.k.a);
            startActivity(intent);
            de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("RETURN_APPLY_SUCCESS"));
        }
    }
}
