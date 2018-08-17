package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.c;
import com.ecjia.hamster.adapter.bq;
import com.ecjia.hamster.fragment.ECJiaTabsFragment;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecjia.hamster.model.ECJia_NEWGOODITEM;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class ECJiaShoppingCartActivity extends a implements OnClickListener, a, ECJiaXListView.a, bq.a {
    private LinearLayout A;
    private CheckBox B;
    private SharedPreferences C;
    private ArrayList<ECJia_GOODS_LIST> D = new ArrayList();
    private StringBuffer E = new StringBuffer();
    private String F;
    private c G;
    ArrayList<String> a = new ArrayList();
    private TextView b;
    private TextView c;
    private TextView d;
    private LinearLayout e;
    private FrameLayout k;
    private FrameLayout l;
    private LinearLayout m;
    private LinearLayout n;
    private LinearLayout o;
    private ECJiaXListView p;
    private bq q;
    private ah r;
    private ImageView s;
    private com.ecjia.component.a.c t;
    private boolean u = false;
    private TextView v;
    private TextView w;
    private TextView x;
    private ArrayList<ECJia_GOODS_LIST> y = new ArrayList();
    private ArrayList<ECJia_GOODS_LIST> z = new ArrayList();

    class ECJiaShoppingCartActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShoppingCartActivity a;

        ECJiaShoppingCartActivity_1(ECJiaShoppingCartActivity eCJiaShoppingCartActivity) {
            this.a = eCJiaShoppingCartActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaShoppingCartActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaShoppingCartActivity a;

        ECJiaShoppingCartActivity_4(ECJiaShoppingCartActivity eCJiaShoppingCartActivity) {
            this.a = eCJiaShoppingCartActivity;
        }

        public void onClick(View view) {
            this.a.G.b();
        }
    }

    class ECJiaShoppingCartActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaShoppingCartActivity a;

        ECJiaShoppingCartActivity_5(ECJiaShoppingCartActivity eCJiaShoppingCartActivity) {
            this.a = eCJiaShoppingCartActivity;
        }

        public void onClick(View view) {
            this.a.G.b();
            this.a.m();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.shop_car);
        PushAgent.getInstance(this).onAppStart();
        this.C = getSharedPreferences(Constants.KEY_USER_ID, 0);
        e();
        this.A.setOnClickListener(this);
        this.r = new ah(this);
        this.r.a((a) this);
        this.q = new bq(this, this.r.y, 1);
        this.q.a((bq.a) this);
        this.p.setAdapter(this.q);
        this.t = new com.ecjia.component.a.c(this);
        this.t.a((a) this);
        this.c.setOnClickListener(this);
        if (this.C.getString("uid", "").equals("")) {
            this.k.setVisibility(0);
            this.l.setVisibility(8);
        } else {
            this.r.a(true);
        }
        this.w.setOnClickListener(this);
        this.s = (ImageView) findViewById(R.id.top_view_back);
        this.s.setVisibility(0);
        this.s.setOnClickListener(new ECJiaShoppingCartActivity_1(this));
        this.F = getResources().getString(R.string.yuan_unit);
        this.v = (TextView) findViewById(R.id.shopcar_edit);
        this.v.setOnClickListener(this);
        this.x.setOnClickListener(this);
    }

    private void e() {
        this.n = (LinearLayout) findViewById(R.id.shop_car_buttomleft);
        this.o = (LinearLayout) findViewById(R.id.shop_car_buttomright);
        this.w = (TextView) findViewById(R.id.shopcar_go_home);
        this.x = (TextView) findViewById(R.id.shop_car_footer_delete);
        this.A = (LinearLayout) findViewById(R.id.cart_checkall_item);
        this.B = (CheckBox) findViewById(R.id.cart_checkall_check);
        this.k = (FrameLayout) findViewById(R.id.shop_car_null);
        this.l = (FrameLayout) findViewById(R.id.shop_car_isnot);
        this.p = (ECJiaXListView) findViewById(R.id.shop_car_list);
        this.p.setPullLoadEnable(false);
        this.p.setPullRefreshEnable(false);
        this.p.setRefreshTime();
        this.p.setXListViewListener(this, 1);
        this.b = (TextView) findViewById(R.id.shop_car_footer_total);
        this.c = (TextView) findViewById(R.id.shop_car_footer_balance);
        this.d = (TextView) findViewById(R.id.shop_car_totalno);
        this.e = (LinearLayout) findViewById(R.id.ll_shop_car_totalno);
        this.m = (LinearLayout) findViewById(R.id.shop_car_buttomitem);
    }

    private void f() {
        int i;
        int size = this.y.size();
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < size; i++) {
            arrayList.add(((ECJia_GOODS_LIST) this.y.get(i)).getRec_id());
        }
        this.r.a(arrayList);
        i = 0;
        while (i < this.q.a().size()) {
            ArrayList a = this.q.a();
            ArrayList arrayList2 = ((ECJia_NEWGOODITEM) a.get(i)).children;
            for (size = 0; size < arrayList2.size(); size++) {
                ArrayList b = ((ECJia_NEWGOODITEM.a) arrayList2.get(size)).b();
                int i2 = 0;
                while (i2 < b.size()) {
                    if (((ECJia_GOODS_LIST) b.get(i2)).getIscheckDelete().booleanValue()) {
                        ((ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.q.a().get(i)).children.get(size)).b().remove(i2);
                        i2--;
                    }
                    i2++;
                }
            }
            if (((ECJia_NEWGOODITEM) a.get(i)).getIscheckDelete().booleanValue()) {
                this.q.a().remove(i);
                i--;
            }
            i++;
        }
        this.q.notifyDataSetChanged();
        g();
    }

    @SuppressLint({"NewApi"})
    public void b() {
        if (this.r.y.size() == 0) {
            this.k.setVisibility(0);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.v.setVisibility(8);
        } else {
            this.p.setPullRefreshEnable(true);
            this.m.setVisibility(0);
            this.l.setVisibility(0);
            this.k.setVisibility(8);
            this.v.setVisibility(0);
            this.q.a(this.r.y);
            this.q.notifyDataSetChanged();
        }
        g();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        i();
        super.onPause();
    }

    public void a(int i) {
        i();
        this.r.a(false);
    }

    public void b(int i) {
    }

    @SuppressLint({"NewApi"})
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            this.r.a(true);
        } else if (i == 3 && i2 == -1) {
            this.t.a();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void g() {
        this.z.clear();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        ArrayList a = this.q.a();
        int size = a.size();
        int i3 = 0;
        while (i3 < size) {
            ArrayList arrayList = ((ECJia_NEWGOODITEM) a.get(i3)).children;
            int i4 = i;
            i = i2;
            float f2 = f;
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                ArrayList b = ((ECJia_NEWGOODITEM.a) arrayList.get(i5)).b();
                for (int i6 = 0; i6 < b.size(); i6++) {
                    ECJia_GOODS_LIST eCJia_GOODS_LIST = (ECJia_GOODS_LIST) b.get(i6);
                    if (eCJia_GOODS_LIST.getIsCheckedbuy().booleanValue()) {
                        i4 += Integer.valueOf(eCJia_GOODS_LIST.getGoods_number()).intValue();
                        f2 += ((float) Integer.valueOf(eCJia_GOODS_LIST.getGoods_number()).intValue()) * Float.valueOf(eCJia_GOODS_LIST.getGoods_price()).floatValue();
                    }
                    i += Integer.valueOf(eCJia_GOODS_LIST.getGoods_number()).intValue();
                }
            }
            i3++;
            f = f2;
            i2 = i;
            i = i4;
        }
        if (new BigDecimal(f + "").setScale(2, 4).floatValue() < 0.0f) {
            this.b.setText(this.F + "0.00");
            this.e.setVisibility(8);
        } else if (k.a(this.r.a.a()) > 0.0f) {
            this.e.setVisibility(0);
            this.b.setText(this.F + String.format("%.2f", new Object[]{Float.valueOf(r0 - k.a(this.r.a.a()))}));
            this.d.setText(this.r.a.c() + this.g.getString(R.string.cart_reduce) + this.r.a.b());
        } else {
            this.e.setVisibility(8);
            this.b.setText(this.F + String.format("%.2f", new Object[]{Float.valueOf(r0)}));
        }
        ECJiaTabsFragment.a().c();
    }

    private void h() {
        this.y.clear();
        ArrayList a = this.q.a();
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ArrayList goodslist = ((ECJia_NEWGOODITEM) a.get(i)).getGoodslist();
            for (int i2 = 0; i2 < goodslist.size(); i2++) {
                ECJia_GOODS_LIST eCJia_GOODS_LIST = (ECJia_GOODS_LIST) goodslist.get(i2);
                if (eCJia_GOODS_LIST.getIscheckDelete().booleanValue()) {
                    this.y.add(eCJia_GOODS_LIST);
                }
            }
        }
    }

    private void i() {
        ArrayList a = this.q.a();
        if (a.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            int size = a.size();
            for (int i = 0; i < size; i++) {
                ArrayList goodslist = ((ECJia_NEWGOODITEM) a.get(i)).getGoodslist();
                for (int i2 = 0; i2 < goodslist.size(); i2++) {
                    ECJia_GOODS_LIST eCJia_GOODS_LIST = (ECJia_GOODS_LIST) goodslist.get(i2);
                    if (!eCJia_GOODS_LIST.getIsCheckedbuy().booleanValue()) {
                        try {
                            jSONArray.put(eCJia_GOODS_LIST.toJson());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            this.r.c(jSONArray.toString(), "cartGoods");
            return;
        }
        this.r.c("", "cartGoods");
    }

    private boolean j() {
        if (this.r.y.size() > 0) {
            int size = this.r.y.size();
            for (int i = 0; i < size; i++) {
                if (!((ECJia_NEWGOODITEM) this.r.y.get(i)).getIsCheckedbuy().booleanValue()) {
                    return false;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean k() {
        if (this.r.y.size() > 0) {
            int size = this.r.y.size();
            for (int i = 0; i < size; i++) {
                if (!((ECJia_NEWGOODITEM) this.r.y.get(i)).getIscheckDelete().booleanValue()) {
                    return false;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void l() {
        this.D.clear();
        this.E.setLength(0);
        ArrayList a = this.q.a();
        if (a.size() > 0) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                ArrayList goodslist = ((ECJia_NEWGOODITEM) a.get(i)).getGoodslist();
                for (int i2 = 0; i2 < goodslist.size(); i2++) {
                    ECJia_GOODS_LIST eCJia_GOODS_LIST = (ECJia_GOODS_LIST) goodslist.get(i2);
                    if (eCJia_GOODS_LIST.getIsCheckedbuy().booleanValue()) {
                        this.D.add(eCJia_GOODS_LIST);
                        this.E.append(eCJia_GOODS_LIST.getRec_id());
                        this.E.append(",");
                    }
                }
            }
            if (this.E.length() > 0) {
                this.E.deleteCharAt(this.E.length() - 1);
            }
        }
    }

    private void m() {
        Intent intent = new Intent(this, ECJiaAddressAddActivity.class);
        intent.putExtra("isfirst", true);
        startActivityForResult(intent, 3);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void a(ECJia_GOODS_LIST eCJia_GOODS_LIST, int i, int i2, int i3, int i4) {
        if (i == this.q.b || i == this.q.d || i == this.q.c) {
            this.r.b(eCJia_GOODS_LIST.getRec_id(), eCJia_GOODS_LIST.getGoods_number());
        } else if (i == this.q.e) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(eCJia_GOODS_LIST.getRec_id());
            this.r.a(arrayList);
            ((ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.q.a().get(i2)).children.get(i3)).b().remove(i4);
            if (((ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.q.a().get(i2)).children.get(i3)).b().size() == 0) {
                ((ECJia_NEWGOODITEM) this.q.a().get(i2)).children.remove(i3);
                if (((ECJia_NEWGOODITEM) this.q.a().get(i2)).children.size() == 0) {
                    this.q.a().remove(i2);
                }
            }
            this.q.notifyDataSetChanged();
            g();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopcar_go_home:
                a("0");
                return;
            case R.id.shopcar_edit:
                this.x.setClickable(false);
                CharSequence string = this.g.getString(R.string.shopcaritem_done);
                if (this.u) {
                    this.u = false;
                } else {
                    this.u = true;
                }
                if (this.u) {
                    this.q.f = 0;
                    this.B.setChecked(k());
                    this.p.setPullRefreshEnable(false);
                    this.n.setVisibility(4);
                    this.c.setVisibility(8);
                    this.x.setVisibility(0);
                    this.v.setText(string);
                    this.x.setClickable(true);
                } else {
                    this.q.f = 1;
                    this.B.setChecked(j());
                    this.p.setPullRefreshEnable(true);
                    this.n.setVisibility(0);
                    this.c.setVisibility(0);
                    this.x.setVisibility(8);
                    this.v.setText(this.g.getString(R.string.collect_compile));
                    this.x.setClickable(false);
                }
                this.q.notifyDataSetChanged();
                return;
            case R.id.cart_checkall_item:
                if (this.B.isChecked()) {
                    this.B.setChecked(false);
                    this.q.c();
                    return;
                }
                this.B.setChecked(true);
                this.q.b();
                return;
            case R.id.shop_car_footer_balance:
                l();
                if (this.D.size() > 0) {
                    i();
                    this.t.a();
                    return;
                }
                new com.ecjia.component.view.k((Context) this, this.g.getString(R.string.choose_nothing)).a();
                return;
            case R.id.shop_car_footer_delete:
                h();
                Resources resources = getResources();
                String string2 = resources.getString(R.string.collect_delete);
                String string3 = resources.getString(R.string.shopcar_deletes);
                final String string4 = resources.getString(R.string.collect_delete_success);
                if (this.y.size() > 0) {
                    final c cVar = new c(this, string2, string3);
                    cVar.a();
                    cVar.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShoppingCartActivity c;

                        public void onClick(View view) {
                            this.c.f();
                            cVar.b();
                            new com.ecjia.component.view.k(this.c, string4).a();
                        }
                    });
                    cVar.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShoppingCartActivity b;

                        public void onClick(View view) {
                            cVar.b();
                        }
                    });
                    return;
                }
                new com.ecjia.component.view.k((Context) this, resources.getString(R.string.choose_nothing)).a();
                return;
            default:
                return;
        }
    }

    public void a(String str) {
        if (str == null || "0".equals(str)) {
            ECJiaTabsFragment.a().b();
            Intent intent = new Intent();
            intent.setClass(this, ECJiaMainActivity.class);
            intent.setFlags(536870912);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            return;
        }
        intent = new Intent(this, ECJiaShopListActivity.class);
        intent.putExtra("merchant_id", str);
        startActivity(intent);
    }

    public void a(int i, boolean z, ArrayList<String> arrayList) {
        if (i == 1) {
            if (z) {
                this.r.a((List) arrayList, 1);
            } else {
                this.r.a((List) arrayList, 0);
            }
        } else if (i != 0) {
        }
    }

    public void b(int i, boolean z, ArrayList<String> arrayList) {
        if (i == 1) {
            if (z) {
                this.r.a((List) arrayList, 1);
            } else {
                this.r.a((List) arrayList, 0);
            }
        } else if (i != 0) {
        }
    }

    public void a(int i, boolean z, String str) {
        if (i == 1) {
            if (z) {
                this.r.a(str, 1);
            } else {
                this.r.a(str, 0);
            }
        } else if (i != 0) {
        }
    }

    public void c() {
        g();
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1552799878:
                if (str.equals("cart/delete")) {
                    z = true;
                    break;
                }
                break;
            case -1056187752:
                if (str.equals("cart/update")) {
                    z = true;
                    break;
                }
                break;
            case 168458797:
                if (str.equals("cart/list")) {
                    z = true;
                    break;
                }
                break;
            case 219725273:
                if (str.equals("address/list")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() != 1) {
                    return;
                }
                if (this.t.a.size() == 0) {
                    this.G = new c(this, this.g.getString(R.string.point), this.g.getString(R.string.address_add_first));
                    this.G.a(2);
                    this.G.c(new ECJiaShoppingCartActivity_4(this));
                    this.G.b(new ECJiaShoppingCartActivity_5(this));
                    this.G.a();
                    return;
                }
                Intent intent = new Intent(this, ECJiaBalanceActivity.class);
                if (this.t.a.size() == 1) {
                    intent.putExtra("address_id", ((ECJia_ADDRESS) this.t.a.get(0)).getId() + "");
                } else {
                    Object string = this.C.getString("last_addressid", "");
                    if (TextUtils.isEmpty(string)) {
                        Iterator it = this.t.a.iterator();
                        while (it.hasNext()) {
                            ECJia_ADDRESS eCJia_ADDRESS = (ECJia_ADDRESS) it.next();
                            if (eCJia_ADDRESS.getDefault_address() == 1) {
                                intent.putExtra("address_id", eCJia_ADDRESS.getId() + "");
                                z = true;
                                if (!z) {
                                    intent.putExtra("address_id", ((ECJia_ADDRESS) this.t.a.get(0)).getId() + "");
                                }
                            }
                        }
                        z = false;
                        if (z) {
                            intent.putExtra("address_id", ((ECJia_ADDRESS) this.t.a.get(0)).getId() + "");
                        }
                    } else {
                        intent.putExtra("address_id", string);
                    }
                }
                intent.putExtra("rec_ids", this.E.toString());
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case true:
                if (axVar.b() == 1) {
                    this.p.stopRefresh();
                    this.p.setRefreshTime();
                    if (!this.u) {
                        this.B.setChecked(j());
                    }
                    b();
                    ax axVar2 = null;
                    try {
                        axVar2 = ax.a(this.r.H.optJSONObject("status"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (axVar2.c() == 100) {
                        this.k.setVisibility(0);
                        this.l.setVisibility(8);
                        this.m.setVisibility(8);
                    }
                    ECJiaTabsFragment.a().c();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    this.r.a(true);
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    ECJiaTabsFragment.a().c();
                    if (this.r.y.size() == 0) {
                        this.u = true;
                        this.q.f = 1;
                        this.v.setText(getResources().getString(R.string.collect_compile));
                        this.v.setVisibility(8);
                        this.k.setVisibility(0);
                        this.l.setVisibility(8);
                        this.m.setVisibility(8);
                        this.x.setClickable(false);
                        this.p.setVisibility(8);
                    }
                    this.r.a(true);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
