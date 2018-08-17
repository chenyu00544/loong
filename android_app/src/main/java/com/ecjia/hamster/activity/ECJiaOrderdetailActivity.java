package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.k;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.a.j;
import com.ecjia.component.a.x;
import com.ecjia.component.view.ECJiaActionSheetDialog;
import com.ecjia.component.view.ECJiaActionSheetDialog.SheetItemColor;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecjia.hamster.adapter.aq;
import com.ecjia.hamster.adapter.ar;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.module.goodsReturn.activity.ECJiaReturnOrderGoodsActivity;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.util.ArrayList;

public class ECJiaOrderdetailActivity extends a implements OnClickListener, a {
    private ListView A;
    private ar B;
    private ArrayList<ECJia_ORDER_GOODS_LIST> C;
    private ArrayList<ECJia_GOODS_LIST> D;
    private String E;
    private ScrollView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private TextView J;
    private TextView K;
    private TextView L;
    private LinearLayout M;
    private LinearLayout N;
    private LinearLayout O;
    private LinearLayout P;
    private LinearLayout Q;
    private LinearLayout R;
    private TextView S;
    private ECJiaHorizontalListView T;
    private LinearLayout U;
    private aq V;
    private LinearLayout W;
    private TextView X;
    private TextView Y;
    private TextView Z;
    public String a;
    private ah aa;
    private int ab;
    private ArrayList<ECJia_ORDER_GOODS_LIST> ac;
    public boolean b = false;
    ECJiaActionSheetDialog c;
    String d = "";
    private ImageView e;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private TextView w;
    private TextView x;
    private x y;
    private j z;

    class ECJiaOrderdetailActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaOrderdetailActivity a;

        ECJiaOrderdetailActivity_1(ECJiaOrderdetailActivity eCJiaOrderdetailActivity) {
            this.a = eCJiaOrderdetailActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaOrderdetailActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaOrderdetailActivity a;

        ECJiaOrderdetailActivity_2(ECJiaOrderdetailActivity eCJiaOrderdetailActivity) {
            this.a = eCJiaOrderdetailActivity;
        }

        public void onClick(View view) {
            this.a.D.clear();
            for (int i = 0; i < this.a.C.size(); i++) {
                ECJia_GOODS_LIST eCJia_GOODS_LIST = new ECJia_GOODS_LIST();
                eCJia_GOODS_LIST.setGoods_id(Integer.valueOf(((ECJia_ORDER_GOODS_LIST) this.a.C.get(i)).getGoods_id()).intValue());
                eCJia_GOODS_LIST.setGoods_name(((ECJia_ORDER_GOODS_LIST) this.a.C.get(i)).getName());
                eCJia_GOODS_LIST.setImg(((ECJia_ORDER_GOODS_LIST) this.a.C.get(i)).getImg());
                eCJia_GOODS_LIST.setGoods_price(((ECJia_ORDER_GOODS_LIST) this.a.C.get(i)).getFormated_shop_price());
                eCJia_GOODS_LIST.setGoods_number(Integer.valueOf(((ECJia_ORDER_GOODS_LIST) this.a.C.get(i)).getGoods_number()).intValue());
                this.a.D.add(eCJia_GOODS_LIST);
            }
            Intent intent = new Intent(this.a, ECJiaShopGoodsActivity.class);
            intent.putExtra("goods_list", this.a.D);
            intent.putExtra("is_order", true);
            this.a.startActivity(intent);
        }
    }

    class ECJiaOrderdetailActivity_3 implements ECJiaActionSheetDialog.a {
        final /* synthetic */ ECJiaOrderdetailActivity a;

        ECJiaOrderdetailActivity_3(ECJiaOrderdetailActivity eCJiaOrderdetailActivity) {
            this.a = eCJiaOrderdetailActivity;
        }

        public void a(int i) {
            this.a.c.c();
            this.a.y.b(this.a.E + "");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_orderdetail);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        b();
        Intent intent = getIntent();
        this.a = intent.getStringExtra("order_type");
        if (TextUtils.isEmpty(this.a)) {
            this.a = "await_pay";
        }
        this.E = intent.getStringExtra("order_id");
        this.C = new ArrayList();
        this.D = new ArrayList();
        this.z = j.a();
        this.y = new x(this);
        this.y.a((a) this);
        this.aa = new ah(this);
        this.aa.a((a) this);
        this.y.a(this.E + "");
    }

    void b() {
        this.k = (TextView) findViewById(R.id.top_view_text);
        this.k.setText(this.g.getString(R.string.order_detail));
        this.e = (ImageView) findViewById(R.id.top_view_back);
        this.e.setOnClickListener(new ECJiaOrderdetailActivity_1(this));
        this.l = (TextView) findViewById(R.id.order_item_sno);
        this.t = (TextView) findViewById(R.id.order_fapiao);
        this.Z = (TextView) findViewById(R.id.order_fapiao_code);
        this.m = (TextView) findViewById(R.id.order_paystatus);
        this.n = (TextView) findViewById(R.id.order_createtime);
        this.o = (TextView) findViewById(R.id.order_cost);
        this.p = (TextView) findViewById(R.id.order_remove);
        this.p.setOnClickListener(this);
        this.q = (TextView) findViewById(R.id.order_pay);
        this.q.setOnClickListener(this);
        this.Y = (TextView) findViewById(R.id.order_createcomment);
        this.Y.setOnClickListener(this);
        this.S = (TextView) findViewById(R.id.order_checkshipinfo);
        this.S.setOnClickListener(this);
        findViewById(R.id.order_waitship_buyagain).setOnClickListener(this);
        findViewById(R.id.order_finished_buyagain).setOnClickListener(this);
        this.A = (ListView) findViewById(R.id.order_goods);
        this.L = (TextView) findViewById(R.id.order_goods_cost);
        this.W = (LinearLayout) findViewById(R.id.order_consultation);
        this.W.setOnClickListener(this);
        this.X = (TextView) findViewById(R.id.tv_fapiao);
        this.T = (ECJiaHorizontalListView) findViewById(R.id.balance_horilistview);
        this.U = (LinearLayout) findViewById(R.id.ll_goodslist);
        this.T.setOnClickListener(new ECJiaOrderdetailActivity_2(this));
        this.r = (TextView) findViewById(R.id.order_goods_totalcost);
        this.s = (TextView) findViewById(R.id.order_traffic_cost);
        this.G = (TextView) findViewById(R.id.tv_hongbao);
        this.H = (TextView) findViewById(R.id.tv_jifen);
        this.I = (TextView) findViewById(R.id.tv_youhui);
        this.J = (TextView) findViewById(R.id.tv_shippingtime);
        this.K = (TextView) findViewById(R.id.tv_postscript);
        this.M = (LinearLayout) findViewById(R.id.buttom_item);
        this.N = (LinearLayout) findViewById(R.id.order_waitpay_bottom);
        this.O = (LinearLayout) findViewById(R.id.order_waitship_bottom);
        this.P = (LinearLayout) findViewById(R.id.order_shiped_bottom);
        this.Q = (LinearLayout) findViewById(R.id.order_finished_bottom);
        this.F = (ScrollView) findViewById(R.id.context_item);
        this.R = (LinearLayout) findViewById(R.id.shippingtime_item);
        this.u = (TextView) findViewById(R.id.order_username);
        this.v = (TextView) findViewById(R.id.order_user_phone);
        this.w = (TextView) findViewById(R.id.order_user_address);
        this.x = (TextView) findViewById(R.id.order_paytype);
        findViewById(R.id.order_confirm_received).setOnClickListener(this);
        findViewById(R.id.order_finished_back_change).setOnClickListener(this);
        findViewById(R.id.order_waitship_back_change).setOnClickListener(this);
    }

    public void c() {
        float f = 0.0f;
        this.a = this.y.e.getStatus_code();
        this.L.setText(this.y.e.getGoods_total_fee());
        this.G.setText(this.y.e.getFormated_bonus());
        this.H.setText(this.y.e.getFormated_integral_money());
        this.I.setText(this.y.e.getFormated_discount());
        if (TextUtils.isEmpty(this.y.e.getShipping_time())) {
            this.J.setText("无");
        } else {
            this.J.setText(this.y.e.getShipping_time());
        }
        if (TextUtils.isEmpty(this.y.e.getPostscript())) {
            this.K.setText("无");
        } else {
            this.K.setText(this.y.e.getPostscript());
        }
        this.X.setText(this.y.e.getFormated_tax());
        this.l.setText(this.y.e.getOrder_sn());
        if (TextUtils.isEmpty(this.y.e.getInv_payee())) {
            this.t.setText("无");
        } else {
            this.t.setText(this.y.e.getInv_payee());
        }
        if (TextUtils.isEmpty(this.y.e.getInv_tax_no())) {
            this.Z.setText("无");
        } else {
            this.Z.setText(this.y.e.getInv_tax_no());
        }
        this.n.setText(this.y.e.getOrder_time());
        if (this.a.equals("await_pay")) {
            this.N.setVisibility(0);
            this.O.setVisibility(8);
            this.P.setVisibility(8);
            this.Q.setVisibility(8);
            this.m.setText(this.g.getString(R.string.order_await_pay));
        } else if (this.a.equals("await_ship")) {
            this.N.setVisibility(8);
            this.O.setVisibility(0);
            this.P.setVisibility(8);
            this.Q.setVisibility(8);
            this.m.setText(this.g.getString(R.string.order_await_ship));
        } else if (this.a.equals("shipped")) {
            this.N.setVisibility(8);
            this.O.setVisibility(8);
            this.P.setVisibility(0);
            this.Q.setVisibility(8);
            this.m.setText(this.g.getString(R.string.order_shipped));
            if (TextUtils.isEmpty(this.y.i) || TextUtils.isEmpty(this.y.h)) {
                this.S.setVisibility(0);
            } else {
                this.S.setVisibility(0);
                this.S.setBackgroundResource(R.drawable.shape_white_stroke_corner_unpress);
            }
        } else if (this.a.equals("finished")) {
            this.N.setVisibility(8);
            this.O.setVisibility(8);
            this.P.setVisibility(8);
            this.Q.setVisibility(0);
            this.m.setText(this.g.getString(R.string.order_history));
            this.p.setVisibility(8);
            if (this.y.e.getIs_commented() == 0 || (this.y.e.getIs_commented() == 1 && this.y.e.getIs_showorder() == 0)) {
                this.Y.setVisibility(0);
            } else {
                this.Y.setVisibility(8);
            }
        } else if (this.a.equals("allow_comment")) {
            this.N.setVisibility(8);
            this.O.setVisibility(8);
            this.P.setVisibility(8);
            this.Q.setVisibility(0);
            this.m.setText(this.g.getString(R.string.order_await_comment));
        } else if (this.a.equals("canceled")) {
            this.m.setText(this.g.getString(R.string.order_type_canceled));
            this.M.setVisibility(8);
        }
        float a = (k.a(this.y.e.getOrder_amount()) + k.a(this.y.e.getSurplus())) + k.a(this.y.e.getMoney_paid());
        if (a >= 0.0f) {
            f = a;
        }
        this.d = k.b(k.a(f));
        this.o.setText(this.y.e.getFormated_total_fee());
        this.s.setText(this.y.e.getFormated_shipping_fee());
        this.r.setText(this.d);
        this.u.setText(this.y.e.getAddress().getConsignee());
        this.v.setText(this.y.e.getAddress().getMobile());
        this.w.setText(this.y.e.getAddress().getAddress());
        this.x.setText("(" + this.y.e.getPay_name() + ")");
        this.C = this.y.f;
        if (this.C.size() == 1) {
            this.B = new ar(this, this.C, this.a);
            this.A.setAdapter(this.B);
            a(this.A);
            this.U.setVisibility(8);
        } else if (this.C.size() > 1) {
            this.V = new aq(this, this.C);
            this.T.setAdapter(this.V);
            this.A.setAdapter(null);
            this.U.setVisibility(0);
        }
    }

    public void onClick(View view) {
        Resources resources = getBaseContext().getResources();
        resources.getString(R.string.order_cancel);
        String string = resources.getString(R.string.order_cancel_sure);
        Intent intent;
        switch (view.getId()) {
            case R.id.order_consultation:
                Intent intent2;
                if (com.ecjia.consts.a.b) {
                    String str = ("http://www.missmall.com/mobile/index.php?m=chat&goods_id=" + this.y.e.getOrder_id()) + "&origin=app&openid=" + this.h.e().r() + "&token=" + this.h.e().b();
                    intent2 = new Intent(this, ECJiaWebViewActivity.class);
                    intent2.putExtra("url", str);
                    intent2.putExtra("title", "");
                    startActivity(intent2);
                    return;
                }
                intent2 = new Intent(this, ECJiaConsultActivity.class);
                intent2.putExtra("type", "order_consult");
                intent2.putExtra("order_id", this.y.e.getOrder_id());
                intent2.putExtra("order_sn", this.y.e.getOrder_sn());
                intent2.putExtra("order_price", this.d);
                intent2.putExtra("order_time", this.y.e.getOrder_time());
                if (!(((ECJia_ORDER_GOODS_LIST) this.y.f.get(0)).getImg() == null || ((ECJia_ORDER_GOODS_LIST) this.y.f.get(0)).getImg().getThumb() == null)) {
                    intent2.putExtra("order_goodsImg", ((ECJia_ORDER_GOODS_LIST) this.y.f.get(0)).getImg().getThumb());
                }
                startActivity(intent2);
                return;
            case R.id.order_remove:
                this.c = new ECJiaActionSheetDialog(this);
                this.c.a().a(string).a(false).b(true).a(resources.getString(R.string.dialog_ensure), SheetItemColor.Red, new ECJiaOrderdetailActivity_3(this)).b();
                return;
            case R.id.order_pay:
                intent = new Intent(this, ECJiaChoosePayActivity.class);
                intent.putExtra("pay_type", "order_id");
                intent.putExtra("pay_id", this.y.e.getPay_id());
                intent.putExtra("order_id", this.E + "");
                intent.putExtra("pay_is_create", false);
                intent.putExtra("pay_amount", this.y.e.getFormated_order_amount() + "");
                intent.putExtra("pay_body", e());
                startActivity(intent);
                return;
            case R.id.order_waitship_back_change:
            case R.id.order_finished_back_change:
                intent = new Intent(this, ECJiaReturnOrderGoodsActivity.class);
                intent.putExtra("order_detail", this.y.e);
                intent.putExtra("goods_list", this.y.f);
                startActivity(intent);
                return;
            case R.id.order_waitship_buyagain:
            case R.id.order_finished_buyagain:
                this.ac = new ArrayList();
                this.ac.addAll(this.y.f);
                this.ab = this.ac.size();
                if (this.ab > 0) {
                    this.aa.a(k.f(((ECJia_ORDER_GOODS_LIST) this.ac.get(0)).getGoods_id()) + "", new ArrayList(), k.f(((ECJia_ORDER_GOODS_LIST) this.ac.get(0)).getGoods_number()), null, null);
                    return;
                }
                return;
            case R.id.order_checkshipinfo:
                intent = new Intent(this, ECJiaLogisticsActivity.class);
                intent.putExtra("order_status", this.m.getText().toString());
                intent.putExtra("shippingname", this.y.h);
                intent.putExtra("shipping_number", this.y.i);
                intent.putExtra("order_id", this.E + "");
                startActivity(intent);
                return;
            case R.id.order_confirm_received:
                this.y.c(this.E + "");
                return;
            case R.id.order_createcomment:
                intent = new Intent(this, ECJiaOrderDetailCommentListActivity.class);
                intent.putExtra("order_id", this.y.e.getOrder_id());
                startActivityForResult(intent, 2);
                return;
            default:
                return;
        }
    }

    private String e() {
        String string = this.g.getString(R.string.balance_order_incloud);
        String string2 = this.g.getString(R.string.balance_deng);
        return string + ((ECJia_ORDER_GOODS_LIST) this.y.f.get(0)).getName() + string2 + this.y.f.size() + this.g.getString(R.string.balance_zhong_goods);
    }

    private void a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.b) {
            this.y.a(this.E + "");
            this.b = false;
        }
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
        if ("RETURN_APPLY_SUCCESS".equals(bVar.c())) {
            this.b = true;
        }
        if (bVar.c().equals("UPDATE_ORDER")) {
            this.b = true;
        }
        q.a("运行========");
        if (bVar.c().equals("comment_succeed")) {
            this.b = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r8, java.lang.String r9, com.ecjia.hamster.model.ax r10) {
        /*
        r7 = this;
        r4 = 0;
        r5 = 17;
        r2 = -1;
        r3 = 1;
        r1 = 0;
        r0 = r8.hashCode();
        switch(r0) {
            case -1569635637: goto L_0x0026;
            case 278583237: goto L_0x001c;
            case 915625051: goto L_0x0030;
            case 948125234: goto L_0x0012;
            default: goto L_0x000d;
        };
    L_0x000d:
        r0 = r2;
    L_0x000e:
        switch(r0) {
            case 0: goto L_0x003a;
            case 1: goto L_0x0044;
            case 2: goto L_0x005e;
            case 3: goto L_0x00c6;
            default: goto L_0x0011;
        };
    L_0x0011:
        return;
    L_0x0012:
        r0 = "order/detail";
        r0 = r8.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x001a:
        r0 = r1;
        goto L_0x000e;
    L_0x001c:
        r0 = "order/affirmReceived";
        r0 = r8.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x0024:
        r0 = r3;
        goto L_0x000e;
    L_0x0026:
        r0 = "cart/create";
        r0 = r8.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x002e:
        r0 = 2;
        goto L_0x000e;
    L_0x0030:
        r0 = "order/cancel";
        r0 = r8.equals(r0);
        if (r0 == 0) goto L_0x000d;
    L_0x0038:
        r0 = 3;
        goto L_0x000e;
    L_0x003a:
        r0 = r10.b();
        if (r0 != r3) goto L_0x0011;
    L_0x0040:
        r7.c();
        goto L_0x0011;
    L_0x0044:
        r0 = r10.b();
        if (r0 != r3) goto L_0x0011;
    L_0x004a:
        r0 = new com.ecjia.component.view.k;
        r1 = 2131100732; // 0x7f06043c float:1.7813854E38 double:1.0529036595E-314;
        r0.<init>(r7, r1);
        r1 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r0.b(r1);
        r0.a();
        r7.finish();
        goto L_0x0011;
    L_0x005e:
        r0 = r7.ab;
        r0 = r0 + -1;
        r7.ab = r0;
        r0 = r7.ab;
        if (r0 <= 0) goto L_0x00ba;
    L_0x0068:
        r0 = r7.aa;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r7.ac;
        r3 = r7.ac;
        r3 = r3.size();
        r5 = r7.ab;
        r3 = r3 - r5;
        r1 = r1.get(r3);
        r1 = (com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST) r1;
        r1 = r1.getGoods_id();
        r1 = com.ecjia.a.k.f(r1);
        r1 = r2.append(r1);
        r2 = "";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.util.ArrayList;
        r2.<init>();
        r3 = r7.ac;
        r5 = r7.ac;
        r5 = r5.size();
        r6 = r7.ab;
        r5 = r5 - r6;
        r3 = r3.get(r5);
        r3 = (com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST) r3;
        r3 = r3.getGoods_number();
        r3 = com.ecjia.a.k.f(r3);
        r5 = r4;
        r0.a(r1, r2, r3, r4, r5);
        goto L_0x0011;
    L_0x00ba:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaShoppingCartActivity.class;
        r0.<init>(r7, r1);
        r7.startActivity(r0);
        goto L_0x0011;
    L_0x00c6:
        r0 = r10.b();
        if (r0 != r3) goto L_0x00e8;
    L_0x00cc:
        r0 = new com.ecjia.component.view.k;
        r3 = r7.g;
        r4 = 2131100312; // 0x7f060298 float:1.7813002E38 double:1.052903452E-314;
        r3 = r3.getString(r4);
        r0.<init>(r7, r3);
        r0.a(r5, r1, r1);
        r0.a();
        r7.setResult(r2);
        r7.finish();
        goto L_0x0011;
    L_0x00e8:
        r0 = new com.ecjia.component.view.k;
        r2 = r10.d();
        r0.<init>(r7, r2);
        r0.a(r5, r1, r1);
        r0.a();
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.hamster.activity.ECJiaOrderdetailActivity.a(java.lang.String, java.lang.String, com.ecjia.hamster.model.ax):void");
    }
}
