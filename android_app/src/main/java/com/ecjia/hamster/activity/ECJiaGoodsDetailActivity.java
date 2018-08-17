package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.a.z;
import com.ecjia.component.a.a.a;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaGoodsViewPager;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.goodsdetail.adapter.ECJiaProductDetailPagerAdapter;
import com.ecjia.hamster.activity.goodsdetail.fragment.ECJiaProductCommonFragment;
import com.ecjia.hamster.activity.goodsdetail.fragment.ECJiaProductDetailFragment;
import com.ecjia.hamster.activity.goodsdetail.fragment.ECJiaProductFragment;
import com.ecjia.hamster.adapter.n;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.util.ArrayList;
import java.util.Arrays;

public class ECJiaGoodsDetailActivity extends ECJiaBaseFragmentActivity implements OnClickListener, a, com.ecjia.hamster.activity.goodsdetail.fragment.a {
    private final int A = 10017;
    private final int B = 10018;
    private ArrayList<Fragment> C;
    private ECJiaProductDetailPagerAdapter D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String[] I;
    public ImageView e;
    public ImageView f;
    Resources g;
    protected ImageLoader h = ImageLoader.getInstance();
    public boolean i = false;
    int j;
    public ECJiaGoodsViewPager k;
    ECJiaProductFragment l;
    ECJiaErrorView m;
    private TextView n;
    private TextView o;
    private ImageView p;
    private SharedPreferences q;
    private TextView r;
    private ECJiaApplication s;
    private final int t = 10010;
    private final int u = 10011;
    private final int v = 10012;
    private final int w = 10013;
    private final int x = 10014;
    private final int y = 10015;
    private final int z = 10016;

    class ECJiaGoodsDetailActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsDetailActivity a;

        ECJiaGoodsDetailActivity_1(ECJiaGoodsDetailActivity eCJiaGoodsDetailActivity) {
            this.a = eCJiaGoodsDetailActivity;
        }

        public void onClick(View view) {
            if (this.a.l.p) {
                this.a.finish();
            } else if (this.a.k.getCurrentItem() != 0) {
                this.a.k.setCurrentItem(0);
            } else {
                this.a.finish();
            }
        }
    }

    class ECJiaGoodsDetailActivity_2 implements ECJiaTopView.a {
        final /* synthetic */ ECJiaGoodsDetailActivity a;

        ECJiaGoodsDetailActivity_2(ECJiaGoodsDetailActivity eCJiaGoodsDetailActivity) {
            this.a = eCJiaGoodsDetailActivity;
        }

        public void a() {
            this.a.d.getTabLayout().setupWithViewPager(this.a.k);
        }

        public void b() {
            this.a.d.getTabLayout().setupWithViewPager(null);
        }

        public void c() {
        }
    }

    class ECJiaGoodsDetailActivity_3 implements OnPageChangeListener {
        final /* synthetic */ ECJiaGoodsDetailActivity a;

        ECJiaGoodsDetailActivity_3(ECJiaGoodsDetailActivity eCJiaGoodsDetailActivity) {
            this.a = eCJiaGoodsDetailActivity;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            this.a.j = i;
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    class ECJiaGoodsDetailActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaGoodsDetailActivity a;

        ECJiaGoodsDetailActivity_4(ECJiaGoodsDetailActivity eCJiaGoodsDetailActivity) {
            this.a = eCJiaGoodsDetailActivity;
        }

        public void onClick(View view) {
            this.a.k();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        this.s = (ECJiaApplication) getApplication();
        setContentView(R.layout.activity_goods_detial_new);
        c.a().a((Object) this);
        this.q = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.g = getResources();
        g();
        i();
    }

    private void g() {
        this.E = getIntent().getStringExtra("goods_id");
        this.F = getIntent().getStringExtra("object_id");
        this.G = getIntent().getStringExtra("seckill");
        this.H = getIntent().getStringExtra("rec_type");
        this.j = getIntent().getIntExtra("tab_id", 0);
    }

    protected void a() {
        super.a();
        this.d = (ECJiaTopView) findViewById(R.id.goodslist_navbar);
        this.d.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaGoodsDetailActivity_1(this));
        this.d.setTitleType(TitleType.TABLAYOUT);
        this.d.getTitleTextView().setEllipsize(TruncateAt.END);
        this.d.getTitleTextView().setTextSize(1, 16.0f);
        this.d.setTitleText("图文详情");
        this.d.setRightType(13);
        this.d.setTitleAnimationEnable(true);
        this.d.setOnTitleAnimationEndListener(new ECJiaGoodsDetailActivity_2(this));
    }

    private void h() {
        this.e = (ImageView) findViewById(R.id.collection_button);
        this.e.setOnClickListener(this);
        this.f = (ImageView) findViewById(R.id.consult_button);
        this.f.setOnClickListener(this);
        this.n = (TextView) findViewById(R.id.add_to_cart);
        this.n.setOnClickListener(this);
        this.o = (TextView) findViewById(R.id.buy_now);
        this.o.setOnClickListener(this);
        if (this.H != null) {
            if (this.H.equals("SPIKE_GOODS")) {
                this.o.setText("立即秒杀");
            } else {
                this.o.setText(getResources().getString(R.string.gooddetail_buy));
            }
        }
        this.p = (ImageView) findViewById(R.id.good_detail_shopping_cart);
        this.p.setOnClickListener(this);
        this.r = (TextView) findViewById(R.id.shopping_cart_num);
        if (!TextUtils.isEmpty(this.F)) {
            this.n.setEnabled(false);
            this.n.setBackgroundResource(R.drawable.shape_gray_bg);
        }
    }

    private void i() {
        this.m = (ECJiaErrorView) findViewById(R.id.no_goods_undercarriage);
        a();
        h();
        j();
        d();
    }

    private void j() {
        this.k = (ECJiaGoodsViewPager) findViewById(R.id.viewPager);
        this.C = new ArrayList();
        this.l = new ECJiaProductFragment();
        this.C.add(this.l);
        this.C.add(new ECJiaProductDetailFragment());
        this.C.add(new ECJiaProductCommonFragment());
        this.I = new String[]{getResources().getString(R.string.goods_tab_goods), getResources().getString(R.string.goods_tab_details), getResources().getString(R.string.goods_tab_comment)};
        this.D = new ECJiaProductDetailPagerAdapter(getSupportFragmentManager(), this.C, Arrays.asList(this.I));
        this.k.setOffscreenPageLimit(this.D.getCount());
        this.k.setAdapter(this.D);
        this.k.setCurrentItem(0);
        this.k.addOnPageChangeListener(new ECJiaGoodsDetailActivity_3(this));
        this.d.setupWithViewPager(this.k);
    }

    public void b() {
        this.k.setScanScroll(true);
        this.d.startDownAnimation();
    }

    public void c() {
        this.k.setScanScroll(false);
        this.d.startUpAnimation();
    }

    public void d() {
        if (this.s.e() == null || this.s.g() == 0) {
            this.r.setVisibility(8);
            return;
        }
        this.r.setVisibility(0);
        if (this.s.g() < 100) {
            this.r.setText(this.s.g() + "");
        } else if (this.s.g() >= 100) {
            this.r.setText("99+");
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        if (this.l.p) {
            finish();
        }
        if (this.k.getCurrentItem() != 0) {
            this.k.setCurrentItem(0);
            return true;
        }
        finish();
        return false;
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        d();
        b(this.j);
    }

    protected void onDestroy() {
        c.a().b(this);
        n.a().b();
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.l.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void k() {
        new z(this, this.l.f).b();
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
        if ("refresh_price".equals(aVar.c())) {
            this.i = true;
        }
    }

    void a(int i) {
        startActivityForResult(new Intent(this, ECJiaLoginActivity.class), i);
        overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        k kVar = new k((Context) this, this.g.getString(R.string.no_login));
        kVar.a(17, 0, 0);
        kVar.a();
    }

    public void e() {
        startActivityForResult(new Intent(this, ECJiaShoppingCartActivity.class), 6);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.consult_button:
                this.l.k();
                return;
            case R.id.collection_button:
                if (this.s.e() == null || TextUtils.isEmpty(this.s.e().m())) {
                    a(10010);
                    return;
                } else if (this.l.y) {
                    this.l.g();
                    return;
                } else {
                    this.l.f();
                    return;
                }
            case R.id.good_detail_shopping_cart:
                if (this.s.e() == null || TextUtils.isEmpty(this.s.e().m())) {
                    a(10014);
                    return;
                } else {
                    e();
                    return;
                }
            case R.id.add_to_cart:
                if (this.s.e() == null || TextUtils.isEmpty(this.s.e().m())) {
                    a(10012);
                    return;
                } else {
                    this.l.a(false);
                    return;
                }
            case R.id.buy_now:
                if (this.s.e() == null || TextUtils.isEmpty(this.s.e().m())) {
                    a(10013);
                    return;
                } else {
                    this.l.a(true);
                    return;
                }
            default:
                return;
        }
    }

    public void b(int i) {
        this.k.setCurrentItem(i);
    }

    public void a(String str) {
        q.a("errorView 显示了");
        this.m.setErrorText((CharSequence) str);
        this.m.setVisibility(0);
    }

    public void f() {
        findViewById(R.id.viewPager_parent).setVisibility(0);
        this.m.setVisibility(8);
        this.d.setRightType(12);
        this.d.setRightImage((int) R.drawable.gooddetail_share, new ECJiaGoodsDetailActivity_4(this));
    }

    public void a(String str, String str2, ax axVar) {
        switch (str.hashCode()) {
            case -1895000790:
                if (!str.equals("goods/detail")) {
                    return;
                }
                return;
            default:
                return;
        }
    }
}
