package com.ecjia.hamster.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.k;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.c;
import com.ecjia.hamster.activity.ECJiaAddressAddActivity;
import com.ecjia.hamster.activity.ECJiaBalanceActivity;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.adapter.bq;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecjia.hamster.model.ECJia_NEWGOODITEM;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class ECJiaShoppingCartFragment extends ECJiaBaseFragment implements OnClickListener, a, ECJiaXListView.a, bq.a {
    private LinearLayout A;
    private CheckBox B;
    private StringBuffer C = new StringBuffer();
    private String D;
    private ECJiaTabsFragment.a E;
    private c F;
    private Resources G;
    ArrayList<String> d = new ArrayList();
    private TextView e;
    private TextView f;
    private FrameLayout g;
    private FrameLayout h;
    private ECJiaXListView i;
    private bq j;
    private ah k;
    private ImageView l;
    private com.ecjia.component.a.c m;
    private TextView n;
    private LinearLayout o;
    private LinearLayout p;
    private LinearLayout q;
    private LinearLayout r;
    private SharedPreferences s;
    private boolean t = false;
    private TextView u;
    private TextView v;
    private TextView w;
    private ArrayList<ECJia_GOODS_LIST> x = new ArrayList();
    private ArrayList<ECJia_GOODS_LIST> y = new ArrayList();
    private ArrayList<ECJia_GOODS_LIST> z = new ArrayList();

    class ECJiaShoppingCartFragment_3 implements OnClickListener {
        final /* synthetic */ ECJiaShoppingCartFragment a;

        ECJiaShoppingCartFragment_3(ECJiaShoppingCartFragment eCJiaShoppingCartFragment) {
            this.a = eCJiaShoppingCartFragment;
        }

        public void onClick(View view) {
            this.a.F.b();
        }
    }

    class ECJiaShoppingCartFragment_4 implements OnClickListener {
        final /* synthetic */ ECJiaShoppingCartFragment a;

        ECJiaShoppingCartFragment_4(ECJiaShoppingCartFragment eCJiaShoppingCartFragment) {
            this.a = eCJiaShoppingCartFragment;
        }

        public void onClick(View view) {
            this.a.F.b();
            this.a.k();
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ECJiaTabsFragment.a) {
            this.E = (ECJiaTabsFragment.a) activity;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.G = getResources();
    }

    public void onEvent(b bVar) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.shop_car, null);
        this.q = (LinearLayout) inflate.findViewById(R.id.shop_car_buttomleft);
        this.r = (LinearLayout) inflate.findViewById(R.id.shop_car_buttomright);
        this.v = (TextView) inflate.findViewById(R.id.shopcar_go_home);
        this.w = (TextView) inflate.findViewById(R.id.shop_car_footer_delete);
        this.A = (LinearLayout) inflate.findViewById(R.id.cart_checkall_item);
        this.B = (CheckBox) inflate.findViewById(R.id.cart_checkall_check);
        this.A.setOnClickListener(this);
        this.s = getActivity().getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.g = (FrameLayout) inflate.findViewById(R.id.shop_car_null);
        this.h = (FrameLayout) inflate.findViewById(R.id.shop_car_isnot);
        this.i = (ECJiaXListView) inflate.findViewById(R.id.shop_car_list);
        this.i.setPullLoadEnable(false);
        this.i.setPullRefreshEnable(false);
        this.i.setRefreshTime();
        this.i.setXListViewListener(this, 1);
        if (this.k == null) {
            this.k = new ah(getActivity());
            this.k.a((a) this);
        }
        if (this.j == null) {
            this.j = new bq(getActivity(), this.k.y, 1);
        }
        this.j.a((bq.a) this);
        this.i.setAdapter(this.j);
        this.e = (TextView) inflate.findViewById(R.id.shop_car_footer_total);
        this.f = (TextView) inflate.findViewById(R.id.shop_car_footer_balance);
        this.n = (TextView) inflate.findViewById(R.id.shop_car_totalno);
        this.o = (LinearLayout) inflate.findViewById(R.id.ll_shop_car_totalno);
        this.p = (LinearLayout) inflate.findViewById(R.id.shop_car_buttomitem);
        this.m = new com.ecjia.component.a.c(getActivity());
        this.m.a((a) this);
        this.f.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.l = (ImageView) inflate.findViewById(R.id.top_view_back);
        this.D = getResources().getString(R.string.yuan_unit);
        this.l.setVisibility(8);
        this.u = (TextView) inflate.findViewById(R.id.shopcar_edit);
        this.u.setOnClickListener(this);
        this.w.setOnClickListener(this);
        return inflate;
    }

    private void d() {
        int i;
        int size = this.x.size();
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < size; i++) {
            arrayList.add(((ECJia_GOODS_LIST) this.x.get(i)).getRec_id());
        }
        this.k.a(arrayList);
        i = 0;
        while (i < this.j.a().size()) {
            ArrayList a = this.j.a();
            ArrayList arrayList2 = ((ECJia_NEWGOODITEM) a.get(i)).children;
            for (size = 0; size < arrayList2.size(); size++) {
                ArrayList b = ((ECJia_NEWGOODITEM.a) arrayList2.get(size)).b();
                int i2 = 0;
                while (i2 < b.size()) {
                    if (((ECJia_GOODS_LIST) b.get(i2)).getIscheckDelete().booleanValue()) {
                        ((ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.a().get(i)).children.get(size)).b().remove(i2);
                        i2--;
                    }
                    i2++;
                }
            }
            if (((ECJia_NEWGOODITEM) a.get(i)).getIscheckDelete().booleanValue()) {
                this.j.a().remove(i);
                i--;
            }
            i++;
        }
        this.j.notifyDataSetChanged();
        e();
    }

    public void a() {
        if (this.k.y.size() == 0) {
            this.g.setVisibility(0);
            this.h.setVisibility(8);
            this.p.setVisibility(8);
            this.u.setVisibility(8);
        } else {
            this.i.setPullRefreshEnable(true);
            this.p.setVisibility(0);
            this.h.setVisibility(0);
            this.g.setVisibility(8);
            this.u.setVisibility(0);
            this.j.a(this.k.y);
            this.j.notifyDataSetChanged();
        }
        e();
    }

    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(this.s.getString("uid", ""))) {
            this.v.setText(R.string.click_to_login);
            this.g.setVisibility(0);
            this.h.setVisibility(8);
            this.p.setVisibility(8);
        } else {
            this.v.setText(R.string.shopcar_add);
            this.k.a(true);
        }
        this.E.addIgnoredView(this.i);
        MobclickAgent.onPageStart("ShopCart");
    }

    public void onPause() {
        super.onPause();
        g();
        this.E.removeIgnoredView(this.i);
        MobclickAgent.onPageEnd("ShopCart");
    }

    public void a(int i) {
        g();
        this.k.a(false);
    }

    public void b(int i) {
    }

    @SuppressLint({"NewApi"})
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            this.k.a(true);
        } else if (i == 3 && i2 == -1) {
            this.m.a();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void e() {
        this.y.clear();
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        ArrayList a = this.j.a();
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
                        i4 += eCJia_GOODS_LIST.getGoods_number();
                        f2 += ((float) eCJia_GOODS_LIST.getGoods_number()) * Float.valueOf(eCJia_GOODS_LIST.getGoods_price()).floatValue();
                    }
                    i += eCJia_GOODS_LIST.getGoods_number();
                }
            }
            i3++;
            f = f2;
            i2 = i;
            i = i4;
        }
        if (new BigDecimal(f + "").setScale(2, 4).floatValue() < 0.0f) {
            this.e.setText(this.D + "0.00");
            this.o.setVisibility(8);
        } else if (k.a(this.k.a.a()) > 0.0f) {
            this.o.setVisibility(0);
            this.e.setText(this.D + String.format("%.2f", new Object[]{Float.valueOf(r0 - k.a(this.k.a.a()))}));
            this.n.setText(this.k.a.c() + this.c.getString(R.string.cart_reduce) + this.k.a.b());
        } else {
            this.o.setVisibility(8);
            this.e.setText(this.D + String.format("%.2f", new Object[]{Float.valueOf(r0)}));
        }
        ECJiaTabsFragment.a().c();
    }

    private void f() {
        this.x.clear();
        ArrayList a = this.j.a();
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ArrayList goodslist = ((ECJia_NEWGOODITEM) a.get(i)).getGoodslist();
            for (int i2 = 0; i2 < goodslist.size(); i2++) {
                ECJia_GOODS_LIST eCJia_GOODS_LIST = (ECJia_GOODS_LIST) goodslist.get(i2);
                if (eCJia_GOODS_LIST.getIscheckDelete().booleanValue()) {
                    this.x.add(eCJia_GOODS_LIST);
                }
            }
        }
    }

    private void g() {
        ArrayList a = this.j.a();
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
            this.k.c(jSONArray.toString(), "cartGoods");
            return;
        }
        this.k.c("", "cartGoods");
    }

    private boolean h() {
        if (this.k.y.size() > 0) {
            int size = this.k.y.size();
            for (int i = 0; i < size; i++) {
                if (!((ECJia_NEWGOODITEM) this.k.y.get(i)).getIsCheckedbuy().booleanValue()) {
                    return false;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean i() {
        if (this.k.y.size() > 0) {
            int size = this.k.y.size();
            for (int i = 0; i < size; i++) {
                if (!((ECJia_NEWGOODITEM) this.k.y.get(i)).getIscheckDelete().booleanValue()) {
                    return false;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void j() {
        this.z.clear();
        this.C.setLength(0);
        ArrayList a = this.j.a();
        if (a.size() > 0) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                ArrayList goodslist = ((ECJia_NEWGOODITEM) a.get(i)).getGoodslist();
                for (int i2 = 0; i2 < goodslist.size(); i2++) {
                    ECJia_GOODS_LIST eCJia_GOODS_LIST = (ECJia_GOODS_LIST) goodslist.get(i2);
                    if (eCJia_GOODS_LIST.getIsCheckedbuy().booleanValue()) {
                        this.z.add(eCJia_GOODS_LIST);
                        this.C.append(eCJia_GOODS_LIST.getRec_id());
                        this.C.append(",");
                    }
                }
            }
            if (this.C.length() > 0) {
                this.C.deleteCharAt(this.C.length() - 1);
            }
        }
    }

    private void k() {
        Intent intent = new Intent(getActivity(), ECJiaAddressAddActivity.class);
        intent.putExtra("isfirst", true);
        startActivityForResult(intent, 3);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void a(ECJia_GOODS_LIST eCJia_GOODS_LIST, int i, int i2, int i3, int i4) {
        if (i == this.j.b || i == this.j.d || i == this.j.c) {
            this.k.b(eCJia_GOODS_LIST.getRec_id(), eCJia_GOODS_LIST.getGoods_number());
        } else if (i == this.j.e) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(eCJia_GOODS_LIST.getRec_id());
            this.k.a(arrayList);
            ((ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.a().get(i2)).children.get(i3)).b().remove(i4);
            if (((ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.a().get(i2)).children.get(i3)).b().size() == 0) {
                ((ECJia_NEWGOODITEM) this.j.a().get(i2)).children.remove(i3);
                if (((ECJia_NEWGOODITEM) this.j.a().get(i2)).children.size() == 0) {
                    this.j.a().remove(i2);
                }
            }
            this.j.notifyDataSetChanged();
            e();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopcar_go_home:
                if (TextUtils.isEmpty(this.s.getString("uid", ""))) {
                    b();
                    return;
                } else {
                    a("0");
                    return;
                }
            case R.id.shopcar_edit:
                this.w.setClickable(false);
                CharSequence string = this.G.getString(R.string.shopcaritem_done);
                if (this.t) {
                    this.t = false;
                } else {
                    this.t = true;
                }
                if (this.t) {
                    this.j.f = 0;
                    this.B.setChecked(i());
                    this.i.setPullRefreshEnable(false);
                    this.q.setVisibility(4);
                    this.f.setVisibility(8);
                    this.w.setVisibility(0);
                    this.u.setText(string);
                    this.w.setClickable(true);
                } else {
                    this.j.f = 1;
                    this.B.setChecked(h());
                    this.i.setPullRefreshEnable(true);
                    this.q.setVisibility(0);
                    this.f.setVisibility(0);
                    this.w.setVisibility(8);
                    this.u.setText(this.G.getString(R.string.collect_compile));
                    this.w.setClickable(false);
                }
                this.j.notifyDataSetChanged();
                return;
            case R.id.cart_checkall_item:
                if (this.B.isChecked()) {
                    this.B.setChecked(false);
                    this.j.c();
                    return;
                }
                this.B.setChecked(true);
                this.j.b();
                return;
            case R.id.shop_car_footer_balance:
                j();
                if (this.z.size() > 0) {
                    g();
                    this.m.a();
                    return;
                }
                new com.ecjia.component.view.k(getActivity(), this.G.getString(R.string.choose_nothing)).a();
                return;
            case R.id.shop_car_footer_delete:
                f();
                Resources resources = getResources();
                String string2 = resources.getString(R.string.collect_delete);
                String string3 = resources.getString(R.string.shopcar_deletes);
                final String string4 = resources.getString(R.string.collect_delete_success);
                if (this.x.size() > 0) {
                    final c cVar = new c(getActivity(), string2, string3);
                    cVar.a();
                    cVar.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShoppingCartFragment c;

                        public void onClick(View view) {
                            new com.ecjia.component.view.k(this.c.getActivity(), string4).a();
                            this.c.d();
                            cVar.b();
                        }
                    });
                    cVar.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaShoppingCartFragment b;

                        public void onClick(View view) {
                            cVar.b();
                        }
                    });
                    return;
                }
                new com.ecjia.component.view.k(getActivity(), this.G.getString(R.string.choose_nothing)).a();
                return;
            default:
                return;
        }
    }

    void b() {
        startActivity(new Intent(getActivity(), ECJiaLoginActivity.class));
        getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }

    public void a(String str) {
        if (str == null || "0".equals(str)) {
            ECJiaTabsFragment.a().b();
            return;
        }
        Intent intent = new Intent(this.b, ECJiaShopListActivity.class);
        intent.putExtra("merchant_id", str);
        startActivity(intent);
    }

    public void a(int i, boolean z, ArrayList<String> arrayList) {
        if (i == 1) {
            if (z) {
                this.k.a((List) arrayList, 1);
            } else {
                this.k.a((List) arrayList, 0);
            }
        } else if (i != 0) {
        }
    }

    public void b(int i, boolean z, ArrayList<String> arrayList) {
        if (i == 1) {
            if (z) {
                this.k.a((List) arrayList, 1);
                this.B.setChecked(true);
                return;
            }
            this.k.a((List) arrayList, 0);
            this.B.setChecked(false);
        } else if (i != 0) {
        }
    }

    public void a(int i, boolean z, String str) {
        if (i == 1) {
            if (z) {
                this.k.a(str, 1);
            } else {
                this.k.a(str, 0);
            }
        } else if (i != 0) {
        }
    }

    public void c() {
        e();
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
                if (this.m.a.size() == 0) {
                    this.F = new c(getActivity(), getActivity().getResources().getString(R.string.point), getActivity().getResources().getString(R.string.address_add_first));
                    this.F.a(2);
                    this.F.c(new ECJiaShoppingCartFragment_3(this));
                    this.F.b(new ECJiaShoppingCartFragment_4(this));
                    this.F.a();
                    return;
                }
                Intent intent = new Intent(getActivity(), ECJiaBalanceActivity.class);
                if (this.m.a.size() == 1) {
                    intent.putExtra("address_id", ((ECJia_ADDRESS) this.m.a.get(0)).getId() + "");
                } else {
                    Object string = this.s.getString("last_addressid", "");
                    if (TextUtils.isEmpty(string)) {
                        int i;
                        Iterator it = this.m.a.iterator();
                        while (it.hasNext()) {
                            ECJia_ADDRESS eCJia_ADDRESS = (ECJia_ADDRESS) it.next();
                            if (eCJia_ADDRESS.getDefault_address() == 1) {
                                intent.putExtra("address_id", eCJia_ADDRESS.getId() + "");
                                i = 1;
                                if (i == 0) {
                                    intent.putExtra("address_id", ((ECJia_ADDRESS) this.m.a.get(0)).getId() + "");
                                }
                            }
                        }
                        z = false;
                        if (i == 0) {
                            intent.putExtra("address_id", ((ECJia_ADDRESS) this.m.a.get(0)).getId() + "");
                        }
                    } else {
                        intent.putExtra("address_id", string);
                    }
                }
                intent.putExtra("rec_ids", this.C.toString());
                startActivityForResult(intent, 1);
                return;
            case true:
                if (axVar.b() == 1) {
                    this.i.stopRefresh();
                    this.i.setRefreshTime();
                    if (!this.t) {
                        this.B.setChecked(h());
                    }
                    a();
                    ax axVar2 = null;
                    try {
                        axVar2 = ax.a(this.k.H.optJSONObject("status"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (axVar2.c() == 100) {
                        this.g.setVisibility(0);
                        this.h.setVisibility(8);
                        this.p.setVisibility(8);
                    }
                    ECJiaTabsFragment.a().c();
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    this.k.a(false);
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    ECJiaTabsFragment.a().c();
                    if (this.k.y.size() == 0) {
                        this.t = false;
                        this.j.f = 1;
                        this.u.setText(getResources().getString(R.string.collect_compile));
                        this.u.setVisibility(8);
                        this.g.setVisibility(0);
                        this.h.setVisibility(8);
                        this.p.setVisibility(8);
                        this.w.setClickable(false);
                        this.i.setVisibility(8);
                    }
                    this.k.a(false);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
