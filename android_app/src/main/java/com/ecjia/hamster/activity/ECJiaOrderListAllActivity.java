package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.a.x;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.e;
import com.ecjia.hamster.c.a.a.b;
import com.ecjia.hamster.fragment.ECJiaTabsFragment;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import de.greenrobot.event.c;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;

public class ECJiaOrderListAllActivity extends a implements a, ECJiaXListView.a {
    public boolean a = false;
    private ECJiaXListView b;
    private com.ecjia.hamster.c.a.a c;
    private ECJiaErrorView d;
    private x e;
    private Intent k;
    private boolean l = false;
    private ah m;
    private int n;
    private ArrayList<ECJia_ORDER_GOODS_LIST> o;
    private String p;
    private int q = 0;
    private View r;
    private View s;
    private View t;
    private LinearLayout u;
    private TextView v;

    class ECJiaOrderListAllActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaOrderListAllActivity a;

        ECJiaOrderListAllActivity_1(ECJiaOrderListAllActivity eCJiaOrderListAllActivity) {
            this.a = eCJiaOrderListAllActivity;
        }

        public void onClick(View view) {
            ECJiaTabsFragment.a().b();
            Intent intent = new Intent();
            intent.setClass(this.a, ECJiaMainActivity.class);
            intent.setFlags(536870912);
            this.a.startActivity(intent);
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaOrderListAllActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaOrderListAllActivity a;

        class ECJiaOrderListAllActivity_2_1 implements AnimationListener {
            final /* synthetic */ ECJiaOrderListAllActivity_2 a;

            ECJiaOrderListAllActivity_2_1(ECJiaOrderListAllActivity_2 eCJiaOrderListAllActivity_2) {
                this.a = eCJiaOrderListAllActivity_2;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(this.a.a, ECJiaSearchOrderActivity.class);
                intent.putExtra(AgooConstants.MESSAGE_FLAG, this.a.a.p);
                this.a.a.startActivityForResult(intent, 100);
                this.a.a.overridePendingTransition(R.anim.animation_2, 0);
            }
        }

        ECJiaOrderListAllActivity_2(ECJiaOrderListAllActivity eCJiaOrderListAllActivity) {
            this.a = eCJiaOrderListAllActivity;
        }

        public void onClick(View view) {
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -this.a.g.getDimension(R.dimen.dp_48));
            Animation scaleAnimation = new ScaleAnimation(1.0f, 0.82f, 1.0f, 1.0f);
            Animation translateAnimation2 = new TranslateAnimation(0.0f, (float) ((((-this.a.getWindowManager().getDefaultDisplay().getWidth()) / 2) + (((int) this.a.getResources().getDimension(R.dimen.ten_margin)) * 2)) + (this.a.r.getWidth() / 2)), 0.0f, 0.0f);
            translateAnimation.setDuration(300);
            scaleAnimation.setDuration(300);
            translateAnimation2.setDuration(300);
            translateAnimation.setFillAfter(true);
            scaleAnimation.setFillAfter(true);
            translateAnimation2.setFillAfter(true);
            translateAnimation.setAnimationListener(new ECJiaOrderListAllActivity_2_1(this));
            this.a.t.startAnimation(translateAnimation);
            this.a.s.startAnimation(scaleAnimation);
            this.a.r.startAnimation(translateAnimation2);
        }
    }

    class ECJiaOrderListAllActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaOrderListAllActivity a;

        ECJiaOrderListAllActivity_3(ECJiaOrderListAllActivity eCJiaOrderListAllActivity) {
            this.a = eCJiaOrderListAllActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaOrderListAllActivity_4 implements b {
        final /* synthetic */ ECJiaOrderListAllActivity a;

        ECJiaOrderListAllActivity_4(ECJiaOrderListAllActivity eCJiaOrderListAllActivity) {
            this.a = eCJiaOrderListAllActivity;
        }

        public void a(View view, final int i) {
            this.a.a(this.a.c.a(i).getOrder_status_code());
            switch (view.getId()) {
                case R.id.ll_trade_item:
                    if (this.a.q != 0) {
                        Intent intent = new Intent(this.a, ECJiaOrderdetailActivity.class);
                        intent.putExtra("order_id", this.a.c.a(i).getOrder_id());
                        intent.putExtra("pay_code", this.a.c.a(i).getOrder_info().getPay_code());
                        intent.putExtra("order_type", this.a.q);
                        q.a("===flag===" + this.a.q);
                        this.a.startActivityForResult(intent, 1);
                        return;
                    }
                    return;
                case R.id.tv_trade_receive:
                    if (this.a.c.a(i).getOrder_status_code().equals("await_pay")) {
                        final e eVar = new e(this.a);
                        eVar.b.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ ECJiaOrderListAllActivity_4 c;

                            public void onClick(View view) {
                                eVar.b();
                                this.c.a.e.b(this.c.a.c.a(i).getOrder_id());
                            }
                        });
                        eVar.a.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ ECJiaOrderListAllActivity_4 b;

                            public void onClick(View view) {
                                eVar.b();
                            }
                        });
                        eVar.a();
                        return;
                    } else if (this.a.c.a(i).getOrder_status_code().equals("await_ship")) {
                        this.a.e.d(this.a.c.a(i).getOrder_id(), "");
                        return;
                    } else if (!this.a.c.a(i).getOrder_status_code().equals("shipped") && this.a.c.a(i).getOrder_status_code().equals("finished")) {
                        return;
                    } else {
                        return;
                    }
                case R.id.tv_trade_comment:
                    if (!this.a.c.a(i).isToComment()) {
                        return;
                    }
                    if (this.a.e.f.size() == 1) {
                        this.a.k = new Intent(this.a, ECJiaCommentCreateActivity.class);
                        this.a.k.putExtra("goods_id", ((ECJia_ORDER_GOODS_LIST) this.a.c.a(i).getGoods_list().get(0)).getGoods_id());
                        this.a.k.putExtra("goods_price", ((ECJia_ORDER_GOODS_LIST) this.a.c.a(i).getGoods_list().get(0)).getFormated_shop_price());
                        this.a.k.putExtra("goods_name", ((ECJia_ORDER_GOODS_LIST) this.a.c.a(i).getGoods_list().get(0)).getName());
                        this.a.k.putExtra("goods_img", ((ECJia_ORDER_GOODS_LIST) this.a.c.a(i).getGoods_list().get(0)).getImg().getThumb());
                        this.a.k.putExtra("order_id", this.a.c.a(i).getOrder_id());
                        this.a.startActivityForResult(this.a.k, 1);
                        return;
                    }
                    this.a.k = new Intent(this.a, ECJiaOrderDetailCommentListActivity.class);
                    this.a.k.putExtra("order_id", this.a.c.a(i).getOrder_id());
                    this.a.startActivityForResult(this.a.k, 2);
                    return;
                case R.id.tv_trade_action:
                    if (this.a.c.a(i).getOrder_status_code().equals("await_pay")) {
                        String string = this.a.g.getString(R.string.balance_order_incloud);
                        String string2 = this.a.g.getString(R.string.balance_deng);
                        String string3 = this.a.g.getString(R.string.balance_zhong_goods);
                        this.a.k = new Intent(this.a, ECJiaChoosePayActivity.class);
                        this.a.k.putExtra("pay_type", "order_id");
                        this.a.k.putExtra("order_id", this.a.c.a(i).getOrder_id());
                        this.a.k.putExtra("pay_code", this.a.c.a(i).order_info.getPay_code());
                        this.a.k.putExtra("pay_is_create", false);
                        this.a.k.putExtra("pay_body", string + ((ECJia_ORDER_GOODS_LIST) this.a.c.a(i).getGoods_list().get(0)).getName() + string2 + this.a.c.a(i).getGoods_list().size() + string3);
                        this.a.k.putExtra("pay_amount", this.a.c.a(i).getFormated_total_fee() + "");
                        this.a.startActivityForResult(this.a.k, 1001);
                        return;
                    } else if (this.a.c.a(i).getOrder_status_code().equals("shipped")) {
                        this.a.e.c(this.a.c.a(i).getOrder_id());
                        return;
                    } else if (this.a.c.a(i).getOrder_status_code().equals("finished")) {
                        this.a.o = new ArrayList();
                        this.a.o.addAll(this.a.c.a(i).getGoods_list());
                        this.a.n = this.a.o.size();
                        if (this.a.n > 0) {
                            this.a.m.a(k.f(((ECJia_ORDER_GOODS_LIST) this.a.o.get(0)).getGoods_id()) + "", new ArrayList(), k.f(((ECJia_ORDER_GOODS_LIST) this.a.o.get(0)).getGoods_number()), null, null);
                            return;
                        }
                        return;
                    } else if (this.a.c.a(i).getOrder_status_code().equals("canceled")) {
                        this.a.o = new ArrayList();
                        this.a.o.addAll(this.a.c.a(i).getGoods_list());
                        this.a.n = this.a.o.size();
                        if (this.a.n > 0) {
                            this.a.m.a(k.f(((ECJia_ORDER_GOODS_LIST) this.a.o.get(0)).getGoods_id()) + "", new ArrayList(), k.f(((ECJia_ORDER_GOODS_LIST) this.a.o.get(0)).getGoods_number()), null, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                case R.id.tv_trade_action2:
                    this.a.o = new ArrayList();
                    this.a.o.addAll(this.a.c.a(i).getGoods_list());
                    this.a.n = this.a.o.size();
                    if (this.a.n > 0) {
                        this.a.m.a(k.f(((ECJia_ORDER_GOODS_LIST) this.a.o.get(0)).getGoods_id()) + "", new ArrayList(), k.f(((ECJia_ORDER_GOODS_LIST) this.a.o.get(0)).getGoods_number()), null, null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_order_list_all);
        c.a().a((Object) this);
        c();
        b();
        e();
    }

    void b() {
        a();
        this.d = (ECJiaErrorView) findViewById(R.id.null_pager);
        this.b = (ECJiaXListView) findViewById(R.id.trade_list);
        this.b.setPullLoadEnable(true);
        this.b.setRefreshTime();
        this.b.setXListViewListener(this, 1);
        this.t = findViewById(R.id.ll_search);
        this.r = findViewById(R.id.order_list_searchlayout_in);
        this.s = findViewById(R.id.order_list_searchlayout_bg);
        this.u = (LinearLayout) findViewById(R.id.shopcar_go_home_layout);
        this.v = (TextView) findViewById(R.id.shopcar_go_home);
        this.v.setOnClickListener(new ECJiaOrderListAllActivity_1(this));
        findViewById(R.id.order_list_search).setOnClickListener(new ECJiaOrderListAllActivity_2(this));
    }

    void c() {
        this.p = getIntent().getStringExtra(AgooConstants.MESSAGE_FLAG);
        if (TextUtils.isEmpty(this.p)) {
            this.p = "";
        }
    }

    void e() {
        this.e = new x(this);
        this.e.a((a) this);
        this.e.a(this.p, "", true);
        this.m = new ah(this);
        this.m.a((a) this);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.orderlist_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaOrderListAllActivity_3(this));
        this.i.setTitleText((int) R.string.order_all_orders);
    }

    public void f() {
        if (this.c == null) {
            this.c = new com.ecjia.hamster.c.a.a(this, this.e.b);
            this.c.a(new ECJiaOrderListAllActivity_4(this));
            this.b.setAdapter(this.c);
        } else {
            this.c.notifyDataSetChanged();
        }
        if (this.e.b.size() == 0) {
            this.u.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            this.u.setVisibility(8);
            this.b.setVisibility(0);
        }
        if (this.l) {
            this.b.setSelection(0);
            this.l = false;
        }
    }

    private void a(String str) {
        if (str.equals("await_pay")) {
            this.q = 1;
        } else if (str.equals("await_ship")) {
            this.q = 2;
        } else if (str.equals("shipped")) {
            this.q = 3;
        } else if (str.equals("finished")) {
            this.q = 4;
        } else if (str.equals("canceled")) {
            this.q = 5;
        }
    }

    public void a(int i) {
        this.e.a(this.p, "", false);
    }

    public void b(int i) {
        this.e.b(this.p, "");
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        this.a = bVar.a();
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        q.a("===requestCode===" + i + "===resultCode===" + i2);
        if (i == 1 && i2 == -1) {
            if (intent != null) {
                this.e.a(this.p, "", true);
            }
        } else if (i == 1001 && i2 == -1) {
            this.e.a(this.p, "", true);
        } else if (i == 100) {
            int width = getWindowManager().getDefaultDisplay().getWidth();
            int dimension = (int) getResources().getDimension(R.dimen.ten_margin);
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -this.g.getDimension(R.dimen.dp_48), 0.0f);
            Animation scaleAnimation = new ScaleAnimation(0.85f, 1.0f, 1.0f, 1.0f);
            Animation translateAnimation2 = new TranslateAnimation((float) ((((-width) / 2) + (dimension * 2)) + (this.r.getWidth() / 2)), 0.0f, 0.0f, 0.0f);
            translateAnimation.setDuration(300);
            scaleAnimation.setDuration(300);
            translateAnimation2.setDuration(300);
            translateAnimation.setFillAfter(true);
            scaleAnimation.setFillAfter(true);
            translateAnimation2.setFillAfter(true);
            this.t.startAnimation(translateAnimation);
            this.s.startAnimation(scaleAnimation);
            this.r.startAnimation(translateAnimation2);
        }
    }

    public void a(String str, String str2, ax axVar) {
        this.b.stopRefresh();
        this.b.stopLoadMore();
        boolean z = true;
        switch (str.hashCode()) {
            case -1569635637:
                if (str.equals("cart/create")) {
                    z = true;
                    break;
                }
                break;
            case -948047341:
                if (str.equals("order/reminder")) {
                    z = true;
                    break;
                }
                break;
            case 278583237:
                if (str.equals("order/affirmReceived")) {
                    z = true;
                    break;
                }
                break;
            case 711842495:
                if (str.equals("order/list")) {
                    z = false;
                    break;
                }
                break;
            case 915625051:
                if (str.equals("order/cancel")) {
                    z = true;
                    break;
                }
                break;
        }
        com.ecjia.component.view.k kVar;
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.b.setRefreshTime();
                    if (this.e.a.a() == 0) {
                        this.b.setPullLoadEnable(false);
                    } else {
                        this.b.setPullLoadEnable(true);
                    }
                    f();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    this.n--;
                    if (this.n > 0) {
                        this.m.a(k.f(((ECJia_ORDER_GOODS_LIST) this.o.get(this.o.size() - this.n)).getGoods_id()) + "", new ArrayList(), k.f(((ECJia_ORDER_GOODS_LIST) this.o.get(this.o.size() - this.n)).getGoods_number()), null, null);
                        return;
                    } else {
                        startActivity(new Intent(this, ECJiaShoppingCartActivity.class));
                        return;
                    }
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    kVar = new com.ecjia.component.view.k((Context) this, (int) R.string.tradeitem_receive);
                    kVar.b(b.REQUEST_MERGE_PERIOD);
                    kVar.a();
                    this.e.a(this.p, this.p, true);
                    return;
                }
                kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case true:
                if (axVar.b() == 1) {
                    kVar = new com.ecjia.component.view.k((Context) this, this.g.getString(R.string.orderdetail_remind_success));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                kVar = new com.ecjia.component.view.k((Context) this, this.g.getString(R.string.orderdetail_remind_failed));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case true:
                this.e.a(this.p, "", false);
                return;
            default:
                return;
        }
    }
}
