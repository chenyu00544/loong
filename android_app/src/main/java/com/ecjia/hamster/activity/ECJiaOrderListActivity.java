package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import de.greenrobot.event.c;
import java.util.ArrayList;

public class ECJiaOrderListActivity extends a implements a, ECJiaXListView.a {
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

    class ECJiaOrderListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaOrderListActivity a;

        ECJiaOrderListActivity_1(ECJiaOrderListActivity eCJiaOrderListActivity) {
            this.a = eCJiaOrderListActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaOrderListActivity_2 implements b {
        final /* synthetic */ ECJiaOrderListActivity a;

        ECJiaOrderListActivity_2(ECJiaOrderListActivity eCJiaOrderListActivity) {
            this.a = eCJiaOrderListActivity;
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
                            final /* synthetic */ ECJiaOrderListActivity_2 c;

                            public void onClick(View view) {
                                eVar.b();
                                this.c.a.e.b(this.c.a.c.a(i).getOrder_id());
                            }
                        });
                        eVar.a.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ ECJiaOrderListActivity_2 b;

                            public void onClick(View view) {
                                eVar.b();
                            }
                        });
                        eVar.a();
                        return;
                    } else if (this.a.c.a(i).getOrder_status_code().equals("await_ship")) {
                        this.a.e.d(this.a.c.a(i).getOrder_id(), "");
                        return;
                    } else if (this.a.c.a(i).getOrder_status_code().equals("shipped")) {
                        this.a.k = new Intent(this.a, ECJiaLogisticsActivity.class);
                        this.a.k.putExtra("shippingname", this.a.e.h);
                        this.a.k.putExtra("shipping_number", this.a.e.i);
                        this.a.k.putExtra("order_id", this.a.c.a(i).getOrder_id());
                        this.a.startActivity(this.a.k);
                        return;
                    } else if (!this.a.c.a(i).getOrder_status_code().equals("finished")) {
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
        setContentView(R.layout.act_order_list);
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
    }

    void c() {
        this.p = getIntent().getStringExtra("order_type");
        if (TextUtils.isEmpty(this.p)) {
            this.p = "";
        }
        q.b("===orderType===" + this.p);
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
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaOrderListActivity_1(this));
        String str = this.p;
        Object obj = -1;
        switch (str.hashCode()) {
            case -673660814:
                if (str.equals("finished")) {
                    obj = null;
                    break;
                }
                break;
            case 200269673:
                if (str.equals("allow_comment")) {
                    obj = 4;
                    break;
                }
                break;
            case 1016249919:
                if (str.equals("await_pay")) {
                    obj = 3;
                    break;
                }
                break;
            case 1439072133:
                if (str.equals("await_ship")) {
                    obj = 2;
                    break;
                }
                break;
            case 2061557075:
                if (str.equals("shipped")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.i.setTitleText((int) R.string.order_history);
                return;
            case 1:
                this.i.setTitleText((int) R.string.order_shipped);
                return;
            case 2:
                this.i.setTitleText((int) R.string.order_await_ship);
                return;
            case 3:
                this.i.setTitleText((int) R.string.order_await_pay);
                return;
            case 4:
                this.i.setTitleText((int) R.string.profile_await_comment);
                return;
            default:
                return;
        }
    }

    public void f() {
        if (this.c == null) {
            this.c = new com.ecjia.hamster.c.a.a(this, this.e.b);
            this.c.a(new ECJiaOrderListActivity_2(this));
            this.b.setAdapter(this.c);
        } else {
            this.c.notifyDataSetChanged();
        }
        if (this.e.b.size() == 0) {
            this.d.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            this.d.setVisibility(8);
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
            this.e.a(this.p, "", true);
        } else if (i == 1001 && i2 == -1) {
            this.e.a(this.p, "", true);
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
                    this.e.a(this.p, "", true);
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
                if (axVar.b() == 1) {
                    kVar = new com.ecjia.component.view.k((Context) this, this.g.getString(R.string.order_canceled));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    this.e.a(this.p, "", false);
                    return;
                }
                kVar = new com.ecjia.component.view.k((Context) this, axVar.d());
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            default:
                return;
        }
    }
}
