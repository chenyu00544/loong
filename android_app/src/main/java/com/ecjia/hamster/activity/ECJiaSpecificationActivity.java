package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ecjia.a.a.b;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.n;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bs;
import com.ecjia.hamster.model.aw;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaSpecificationActivity extends a implements a, bs.a {
    private String A;
    private TextView B;
    private TextView C;
    private n D;
    protected ImageLoader a = ImageLoader.getInstance();
    String b = "";
    private ListView c;
    private TextView d;
    private TextView e;
    private ImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private SharedPreferences p;
    private View q;
    private int r;
    private boolean s = false;
    private bs t;
    private TextView u;
    private float v = 0.0f;
    private final int w = 10016;
    private final int x = 10017;
    private ImageView y;
    private String z;

    class ECJiaSpecificationActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaSpecificationActivity a;

        ECJiaSpecificationActivity_1(ECJiaSpecificationActivity eCJiaSpecificationActivity) {
            this.a = eCJiaSpecificationActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaSpecificationActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaSpecificationActivity a;

        ECJiaSpecificationActivity_2(ECJiaSpecificationActivity eCJiaSpecificationActivity) {
            this.a = eCJiaSpecificationActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("isbynow", false);
            this.a.setResult(10016, intent);
            this.a.finish();
        }
    }

    class ECJiaSpecificationActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaSpecificationActivity a;

        ECJiaSpecificationActivity_3(ECJiaSpecificationActivity eCJiaSpecificationActivity) {
            this.a = eCJiaSpecificationActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("isbynow", true);
            this.a.setResult(10017, intent);
            this.a.finish();
        }
    }

    class ECJiaSpecificationActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaSpecificationActivity a;

        ECJiaSpecificationActivity_4(ECJiaSpecificationActivity eCJiaSpecificationActivity) {
            this.a = eCJiaSpecificationActivity;
        }

        public void onClick(View view) {
            if (com.ecjia.hamster.adapter.n.a().c - 1 > 0) {
                com.ecjia.hamster.adapter.n a = com.ecjia.hamster.adapter.n.a();
                a.c--;
                this.a.l.setText(String.valueOf(com.ecjia.hamster.adapter.n.a().c));
            }
        }
    }

    class ECJiaSpecificationActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaSpecificationActivity a;

        ECJiaSpecificationActivity_5(ECJiaSpecificationActivity eCJiaSpecificationActivity) {
            this.a = eCJiaSpecificationActivity;
        }

        public void onClick(View view) {
            if (com.ecjia.hamster.adapter.n.a().c > this.a.r - 1) {
                k kVar = new k(this.a, this.a.getBaseContext().getResources().getString(R.string.understock));
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (TextUtils.isEmpty(this.a.A)) {
                r0 = com.ecjia.hamster.adapter.n.a();
                r0.c++;
                this.a.l.setText(String.valueOf(com.ecjia.hamster.adapter.n.a().c));
            } else if (com.ecjia.hamster.adapter.n.a().c < Integer.valueOf(this.a.A).intValue()) {
                r0 = com.ecjia.hamster.adapter.n.a();
                r0.c++;
                this.a.l.setText(String.valueOf(com.ecjia.hamster.adapter.n.a().c));
            } else {
                Toast.makeText(this.a, "超过限购数量", 1).show();
            }
        }
    }

    class ECJiaSpecificationActivity_6 implements TextWatcher {
        final /* synthetic */ ECJiaSpecificationActivity a;

        ECJiaSpecificationActivity_6(ECJiaSpecificationActivity eCJiaSpecificationActivity) {
            this.a = eCJiaSpecificationActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String obj = editable.toString();
            if (obj.length() > 0) {
                com.ecjia.hamster.adapter.n.a().c = Integer.valueOf(obj).intValue();
                this.a.b();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.specification_activity);
        c.a().a((Object) this);
        PushAgent.getInstance(this).onAppStart();
        Resources resources = getBaseContext().getResources();
        this.D = new n(this);
        this.D.a((a) this);
        this.D.b(getIntent().getStringExtra("goods_id"), getIntent().getStringExtra("area_id"));
        resources.getString(R.string.please_select);
        resources.getString(R.string.specific_classify);
        resources.getString(R.string.specific_inventory);
        Window window = getWindow();
        window.setGravity(80);
        window.setLayout(-1, -2);
        Intent intent = getIntent();
        this.z = intent.getStringExtra("object_id");
        this.p = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.r = intent.getIntExtra("num", 0);
        this.k = (ImageView) findViewById(R.id.spec_dismiss);
        this.k.setOnClickListener(new ECJiaSpecificationActivity_1(this));
        this.c = (ListView) findViewById(R.id.specification_list);
        this.y = (ImageView) findViewById(R.id.spec_img);
        this.a.displayImage(intent.getStringExtra("imgurl"), this.y);
        this.u = (TextView) findViewById(R.id.spec_fee);
        this.B = (TextView) findViewById(R.id.spec_stock);
        try {
            this.u.setText(com.ecjia.a.k.e(intent.getStringExtra("price")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("免费".equals(intent.getStringExtra("price"))) {
            this.v = 0.0f;
        } else {
            this.v = com.ecjia.a.k.a(intent.getStringExtra("price"));
            q.a("baseprice==" + this.v);
        }
        this.A = intent.getStringExtra("promote_limit");
        this.q = LayoutInflater.from(this).inflate(R.layout.add_item_component, null);
        this.c.addFooterView(this.q);
        this.t = new bs(this, com.ecjia.hamster.adapter.n.a().a.b, com.ecjia.hamster.adapter.n.a().a.a, this);
        this.c.setAdapter(this.t);
        a(this.c);
        this.n = (TextView) findViewById(R.id.spec_add_to_cart);
        if (!TextUtils.isEmpty(this.z)) {
            this.n.setEnabled(false);
            this.n.setBackgroundResource(R.drawable.shape_gray_bg);
        }
        this.C = (TextView) this.q.findViewById(R.id.goods_promote_limit);
        if (TextUtils.isEmpty(this.A)) {
            this.C.setVisibility(8);
        } else {
            this.C.setText("每人限购" + this.A + "件");
            this.C.setVisibility(0);
        }
        this.n.setOnClickListener(new ECJiaSpecificationActivity_2(this));
        this.o = (TextView) findViewById(R.id.spec_buy_now);
        this.o.setOnClickListener(new ECJiaSpecificationActivity_3(this));
        this.m = (TextView) this.q.findViewById(R.id.good_total_price);
        this.d = (TextView) this.q.findViewById(R.id.shop_car_item_min);
        this.d.setOnClickListener(new ECJiaSpecificationActivity_4(this));
        this.e = (TextView) this.q.findViewById(R.id.shop_car_item_sum);
        this.e.setOnClickListener(new ECJiaSpecificationActivity_5(this));
        this.l = (TextView) this.q.findViewById(R.id.shop_car_item_editNum);
        this.l.setText(String.valueOf(com.ecjia.hamster.adapter.n.a().c));
        this.l.addTextChangedListener(new ECJiaSpecificationActivity_6(this));
        b();
    }

    void b() {
        this.m.setText(getBaseContext().getResources().getString(R.string.total_price) + com.ecjia.hamster.adapter.n.a().c());
    }

    public void onEvent(Object obj) {
        if (obj.getClass() == aw.class) {
            b();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return true;
    }

    public void a(ListView listView) {
        int height = getWindowManager().getDefaultDisplay().getHeight();
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            if (height - i < ((int) ((((double) height) * 14.0d) / 25.0d))) {
                layoutParams.height = height - ((int) ((((double) height) * 14.0d) / 25.0d));
            }
            listView.setLayoutParams(layoutParams);
        }
    }

    public void a(float f, float f2) {
        try {
            this.b = com.ecjia.hamster.adapter.n.a().d();
            if (this.D.u.get(this.b) != null) {
                this.B.setText(this.g.getString(R.string.specific_inventory) + "：" + ((n.a) this.D.u.get(this.b)).a() + "");
            } else {
                this.B.setText("暂无库存");
            }
            if (com.ecjia.hamster.adapter.n.a().a.b.size() > 0 && f != -1.0f) {
                this.u.setText(com.ecjia.a.k.e(f + ""));
                c.a().c(new b(f + "", f2 + "", 1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (com.ecjia.hamster.adapter.n.a().b.size() > 0) {
            if (this.D.u.get(this.b) != null) {
                this.B.setText(this.g.getString(R.string.specific_inventory) + "：" + ((n.a) this.D.u.get(this.b)).a() + "");
            } else {
                this.B.setText("暂无库存");
            }
        } else if (com.ecjia.a.k.a(com.ecjia.hamster.adapter.n.a().a.m()) > 0.0f) {
            this.B.setText(this.g.getString(R.string.specific_inventory) + "：" + com.ecjia.hamster.adapter.n.a().a.m());
        } else {
            this.B.setText("暂无库存");
        }
    }
}
