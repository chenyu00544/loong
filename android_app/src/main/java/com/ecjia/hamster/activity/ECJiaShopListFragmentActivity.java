package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.p;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.af;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.au;
import com.ecjia.hamster.adapter.bl;
import com.ecjia.hamster.adapter.bp;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.c;
import java.util.ArrayList;

public class ECJiaShopListFragmentActivity extends a implements a, ECJiaXListView.a {
    private PopupWindow A;
    private View B;
    private ECJiaHorizontalListView C;
    private TextView D;
    private ImageView E;
    private ImageView F;
    private LinearLayout G;
    private ImageView H;
    private ArrayList<ECJia_CATEGORY> I = new ArrayList();
    private p J;
    private ImageView K;
    private ECJiaXListView a;
    private FrameLayout b;
    private bl c;
    private af d;
    private SharedPreferences e;
    private c k;
    private Handler l;
    private int m = -1;
    private View n;
    private View o;
    private ECJiaHorizontalListView p;
    private bp q;
    private String r = "0";
    private TextView s;
    private LinearLayout t;
    private LinearLayout u;
    private ImageView v;
    private boolean w = true;
    private LinearLayout x;
    private ECJiaMyGridView y;
    private au z;

    class ECJiaShopListFragmentActivity_10 implements OnItemClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_10(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.r = ((ECJia_CATEGORY) this.a.q.a().get(i)).getId() + "";
            this.a.q.a(i);
            this.a.d.a(this.a.r);
        }
    }

    class ECJiaShopListFragmentActivity_11 implements OnClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_11(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onClick(View view) {
            this.a.A.showAsDropDown(this.a.u, 0, 0);
            this.a.s.setVisibility(0);
            this.a.p.setVisibility(8);
            this.a.v.setBackgroundResource(R.drawable.search_hidden);
        }
    }

    class ECJiaShopListFragmentActivity_12 implements OnItemClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_12(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.r = ((ECJia_CATEGORY) this.a.q.a().get(i)).getId() + "";
            this.a.q.a(i);
            this.a.d.a(this.a.r);
        }
    }

    class ECJiaShopListFragmentActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_1(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaShopListFragmentActivity_2 implements OnItemClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_2(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.r = ((ECJia_CATEGORY) this.a.q.a().get(i + 1)).getId() + "";
            this.a.q.a(i + 1);
            this.a.d.a(this.a.r);
            this.a.A.dismiss();
        }
    }

    class ECJiaShopListFragmentActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_3(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onClick(View view) {
            this.a.A.dismiss();
        }
    }

    class ECJiaShopListFragmentActivity_4 implements OnDismissListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_4(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onDismiss() {
            this.a.s.setVisibility(8);
            this.a.p.setVisibility(0);
            this.a.v.setBackgroundResource(R.drawable.search_showchild);
            this.a.D.setVisibility(8);
            this.a.C.setVisibility(0);
            this.a.E.setBackgroundResource(R.drawable.search_showchild);
        }
    }

    class ECJiaShopListFragmentActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_5(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onClick(View view) {
            if (this.a.d.d.size() > 0) {
                com.ecjia.a.b.a.a().a(this.a, ((com.ecjia.hamster.model.c) this.a.d.d.get(0)).a());
            }
        }
    }

    class ECJiaShopListFragmentActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_6(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(this.a, ECJiaSearchNewActivity.class);
            this.a.startActivity(intent);
            this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaShopListFragmentActivity_7 extends Handler {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_7(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.m = message.arg1;
                this.a.d.c(((ao) this.a.c.a().get(message.arg1)).e());
                this.a.k.c(new b("add_collect_seller", ((ao) this.a.c.a().get(message.arg1)).e()));
            }
            if (message.what == 2) {
                this.a.m = message.arg1;
                this.a.d.d(((ao) this.a.c.a().get(message.arg1)).e());
                this.a.k.c(new b("minus_collect_seller", ((ao) this.a.c.a().get(message.arg1)).e()));
            }
        }
    }

    class ECJiaShopListFragmentActivity_8 implements OnScrollListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_8(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i <= 1 || this.a.d.c.size() <= 1) {
                this.a.x.setVisibility(8);
            } else {
                this.a.x.setVisibility(0);
            }
        }
    }

    class ECJiaShopListFragmentActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaShopListFragmentActivity a;

        ECJiaShopListFragmentActivity_9(ECJiaShopListFragmentActivity eCJiaShopListFragmentActivity) {
            this.a = eCJiaShopListFragmentActivity;
        }

        public void onClick(View view) {
            this.a.A.showAsDropDown(this.a.G, 0, 0);
            this.a.D.setVisibility(0);
            this.a.C.setVisibility(8);
            this.a.E.setBackgroundResource(R.drawable.search_hidden);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.shoplist_fragment);
        this.e = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.k = c.a();
        this.k.a((Object) this);
        this.J = p.a((Context) this);
        e();
    }

    public void onResume() {
        super.onResume();
        if (this.d == null) {
            this.d = new af(this);
        }
        MobclickAgent.onPageStart("ShopList");
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ShopList");
    }

    void a(ImageView imageView) {
        int c = c();
        imageView.setLayoutParams(new LayoutParams(c, (c * 1) / 3));
    }

    private void e() {
        this.K = (ImageView) findViewById(R.id.view_back);
        this.K.setVisibility(0);
        this.K.setOnClickListener(new ECJiaShopListFragmentActivity_1(this));
        if (this.d == null) {
            this.d = new af(this);
        }
        this.n = View.inflate(this, R.layout.shoplist_headview1, null);
        this.H = (ImageView) this.n.findViewById(R.id.headview1_bg);
        a(this.H);
        this.H.setOnClickListener(new ECJiaShopListFragmentActivity_5(this));
        this.o = View.inflate(this, R.layout.shoplist_headview2, null);
        this.F = (ImageView) findViewById(R.id.shoplist_iv_search);
        this.F.setOnClickListener(new ECJiaShopListFragmentActivity_6(this));
        g();
        f();
        h();
        this.l = new ECJiaShopListFragmentActivity_7(this);
        this.a = (ECJiaXListView) findViewById(R.id.shoplist_xlist);
        this.a.setXListViewListener(this, 1);
        this.a.setRefreshTime();
        this.a.setPullLoadEnable(false);
        this.a.setPullRefreshEnable(true);
        this.a.addHeaderView(this.n);
        this.a.addHeaderView(this.o);
        this.a.setOnScrollListener(new ECJiaShopListFragmentActivity_8(this));
        this.c = new bl(this, this.d.a, b());
        this.c.a = this.l;
        this.a.setAdapter(this.c);
        this.d.a((a) this);
        this.b = (FrameLayout) findViewById(R.id.null_pager);
        this.d.a();
        this.d.j();
    }

    private void f() {
        this.x = (LinearLayout) findViewById(R.id.body_title_item);
        this.C = (ECJiaHorizontalListView) findViewById(R.id.body_categorylist);
        if (this.q == null) {
            this.q = new bp(this, this.d.c);
        }
        this.G = (LinearLayout) findViewById(R.id.body_show_more);
        this.G.setOnClickListener(new ECJiaShopListFragmentActivity_9(this));
        this.D = (TextView) findViewById(R.id.body_category_num);
        this.E = (ImageView) findViewById(R.id.body_show_more_img);
        this.C.setAdapter(this.q);
        this.C.setOnItemClickListener(new ECJiaShopListFragmentActivity_10(this));
    }

    private void g() {
        this.p = (ECJiaHorizontalListView) this.o.findViewById(R.id.headview_categorylist);
        this.t = (LinearLayout) this.o.findViewById(R.id.headview2_item);
        this.u = (LinearLayout) this.o.findViewById(R.id.headview2_show_more);
        this.u.setOnClickListener(new ECJiaShopListFragmentActivity_11(this));
        this.s = (TextView) this.o.findViewById(R.id.category_num);
        this.v = (ImageView) this.o.findViewById(R.id.show_more_img);
        this.q = new bp(this, this.d.c);
        this.p.setAdapter(this.q);
        this.p.setOnItemClickListener(new ECJiaShopListFragmentActivity_12(this));
    }

    private void h() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.shoplist_popupwindow, null, true);
        this.B = inflate.findViewById(R.id.buttom_view);
        this.y = (ECJiaMyGridView) inflate.findViewById(R.id.popup_gridview);
        this.I.clear();
        this.z = new au(this, this.I);
        this.y.setAdapter(this.z);
        this.y.setOnItemClickListener(new ECJiaShopListFragmentActivity_2(this));
        this.A = new PopupWindow(findViewById(R.id.shoplist_content), -1, -1, true);
        this.A.setContentView(inflate);
        this.A.setOutsideTouchable(true);
        this.A.setFocusable(true);
        this.A.setBackgroundDrawable(new ColorDrawable(100));
        this.B.setOnClickListener(new ECJiaShopListFragmentActivity_3(this));
        this.A.setOnDismissListener(new ECJiaShopListFragmentActivity_4(this));
    }

    public void onDestroy() {
        this.k.b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
        if ("collectrefresh".equals(bVar.c()) && this.d != null) {
            this.d.a(this.r);
        }
    }

    private void i() {
        if (this.d.a.size() == 0) {
            this.a.setVisibility(8);
            this.b.setVisibility(0);
        } else {
            this.a.setVisibility(0);
            this.b.setVisibility(8);
            this.c.a(this.d.a);
        }
        this.q.notifyDataSetChanged();
        this.c.notifyDataSetChanged();
    }

    public int b() {
        return Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()) - (((int) getResources().getDimension(R.dimen.eight_margin)) * 2);
    }

    public int c() {
        return Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
    }

    public void a(int i) {
        this.d.a(this.r);
    }

    public void b(int i) {
        this.d.b(this.r);
    }

    public void a(String str, String str2, ax axVar) {
        int i = 1;
        boolean z = true;
        switch (str.hashCode()) {
            case -2073837039:
                if (str.equals("seller/collect/create")) {
                    z = true;
                    break;
                }
                break;
            case -2057001280:
                if (str.equals("seller/collect/delete")) {
                    z = true;
                    break;
                }
                break;
            case 449564046:
                if (str.equals("seller/category")) {
                    z = true;
                    break;
                }
                break;
            case 1203407150:
                if (str.equals("seller/list")) {
                    z = false;
                    break;
                }
                break;
            case 1559134282:
                if (str.equals("seller/home/data")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.a.setRefreshTime();
                    this.a.stopRefresh();
                    this.a.stopLoadMore();
                    if (this.d.e.a() == 1) {
                        this.a.setPullLoadEnable(true);
                    } else {
                        this.a.setPullLoadEnable(false);
                    }
                    i();
                    return;
                }
                this.a.setVisibility(8);
                this.b.setVisibility(0);
                return;
            case true:
                if (this.d.c.size() > 1) {
                    while (i < this.d.c.size()) {
                        this.I.add(this.d.c.get(i));
                        i++;
                    }
                    this.D.setText(getResources().getString(R.string.at_all) + this.I.size() + getResources().getString(R.string.category_for_chioce));
                    this.s.setText(getResources().getString(R.string.at_all) + this.I.size() + getResources().getString(R.string.category_for_chioce));
                    this.t.setVisibility(0);
                } else {
                    this.t.setVisibility(8);
                }
                this.q.notifyDataSetChanged();
                this.d.a("");
                return;
            case true:
                if (this.d.d.size() > 0) {
                    this.J.a(this.H, ((com.ecjia.hamster.model.c) this.d.d.get(0)).b());
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    new k((Context) this, getResources().getString(R.string.collection_success)).a();
                    ((ao) this.c.a().get(this.m)).a("1");
                    ((ao) this.c.a().get(this.m)).a(Integer.valueOf(((ao) this.c.a().get(this.m)).g().intValue() + 1));
                    this.c.notifyDataSetChanged();
                    return;
                } else if (axVar.c() == 100) {
                    startActivity(new Intent(this, ECJiaLoginActivity.class));
                    overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else {
                    new k((Context) this, axVar.d()).a();
                    return;
                }
            case true:
                if (axVar.b() == 1) {
                    new k((Context) this, getResources().getString(R.string.del_collection_success)).a();
                    ((ao) this.c.a().get(this.m)).a("0");
                    ((ao) this.c.a().get(this.m)).a(Integer.valueOf(((ao) this.c.a().get(this.m)).g().intValue() - 1));
                    this.c.notifyDataSetChanged();
                    return;
                } else if (axVar.c() == 100) {
                    startActivity(new Intent(this, ECJiaLoginActivity.class));
                    overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else {
                    new k((Context) this, axVar.d()).a();
                    return;
                }
            default:
                return;
        }
    }
}
