package com.ecjia.hamster.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.model.ap;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;

public class ECJiaTabsFragment extends ECJiaBaseFragment {
    private static ECJiaTabsFragment F;
    public static boolean i;
    private SharedPreferences A;
    private Editor B;
    private String C;
    private LinearLayout D;
    private TextView E;
    ImageView d;
    ImageView e;
    ImageView f;
    ImageView g;
    ImageView h;
    ECJiaHomeFragment j;
    ECJiaSearchFragment k;
    ECJiaShoppingCartFragment l;
    ECJiaMineFragment m;
    ECJiaFoundFragment n;
    int o;
    int p;
    private FrameLayout q;
    private FrameLayout r;
    private FrameLayout s;
    private FrameLayout t;
    private FrameLayout u;
    private TextView v;
    private TextView w;
    private TextView x;
    private TextView y;
    private TextView z;

    public interface a {
        void addIgnoredView(View view);

        void c();

        void removeIgnoredView(View view);
    }

    class ECJiaTabsFragment_1 implements OnClickListener {
        final /* synthetic */ ECJiaTabsFragment a;

        ECJiaTabsFragment_1(ECJiaTabsFragment eCJiaTabsFragment) {
            this.a = eCJiaTabsFragment;
        }

        public void onClick(View view) {
            this.a.b("tab_one");
            ECJiaTabsFragment.i = true;
        }
    }

    class ECJiaTabsFragment_2 implements OnClickListener {
        final /* synthetic */ ECJiaTabsFragment a;

        ECJiaTabsFragment_2(ECJiaTabsFragment eCJiaTabsFragment) {
            this.a = eCJiaTabsFragment;
        }

        public void onClick(View view) {
            this.a.b("tab_two");
        }
    }

    class ECJiaTabsFragment_3 implements OnClickListener {
        final /* synthetic */ ECJiaTabsFragment a;

        ECJiaTabsFragment_3(ECJiaTabsFragment eCJiaTabsFragment) {
            this.a = eCJiaTabsFragment;
        }

        public void onClick(View view) {
            this.a.b("tab_three");
        }
    }

    class ECJiaTabsFragment_4 implements OnClickListener {
        final /* synthetic */ ECJiaTabsFragment a;

        ECJiaTabsFragment_4(ECJiaTabsFragment eCJiaTabsFragment) {
            this.a = eCJiaTabsFragment;
        }

        public void onClick(View view) {
            this.a.b("tab_four");
            if (ap.c() == null || "".equals(ap.c().a())) {
                this.a.startActivity(new Intent(this.a.getActivity(), ECJiaLoginActivity.class));
                this.a.getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
            }
        }
    }

    class ECJiaTabsFragment_5 implements OnClickListener {
        final /* synthetic */ ECJiaTabsFragment a;

        ECJiaTabsFragment_5(ECJiaTabsFragment eCJiaTabsFragment) {
            this.a = eCJiaTabsFragment;
        }

        public void onClick(View view) {
            this.a.b("tab_five");
        }
    }

    public ECJiaTabsFragment() {
        F = this;
    }

    public void onEvent(b bVar) {
    }

    public static ECJiaTabsFragment a() {
        if (F == null) {
            synchronized (ECJiaTabsFragment.class) {
                if (F == null) {
                    F = new ECJiaTabsFragment();
                }
            }
        }
        return F;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.toolbar, viewGroup, false);
        this.o = getActivity().getResources().getColor(R.color.public_theme_color_normal);
        this.p = getActivity().getResources().getColor(R.color.filter_text_color);
        a(inflate);
        this.A = getActivity().getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.B = this.A.edit();
        return inflate;
    }

    @TargetApi(11)
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setRetainInstance(true);
    }

    void a(View view) {
        this.E = (TextView) view.findViewById(R.id.shopping_cart_num);
        this.D = (LinearLayout) view.findViewById(R.id.shopping_cart_num_bg_one);
        this.d = (ImageView) view.findViewById(R.id.toolbar_tabone);
        this.q = (FrameLayout) view.findViewById(R.id.tabitemone);
        this.v = (TextView) view.findViewById(R.id.toolbar_textone);
        this.q.setOnClickListener(new ECJiaTabsFragment_1(this));
        this.e = (ImageView) view.findViewById(R.id.toolbar_tabtwo);
        this.r = (FrameLayout) view.findViewById(R.id.tabitemtwo);
        this.w = (TextView) view.findViewById(R.id.toolbar_texttwo);
        this.r.setOnClickListener(new ECJiaTabsFragment_2(this));
        this.f = (ImageView) view.findViewById(R.id.toolbar_tabthree);
        this.s = (FrameLayout) view.findViewById(R.id.tabitemthree);
        this.x = (TextView) view.findViewById(R.id.toolbar_textthree);
        this.s.setOnClickListener(new ECJiaTabsFragment_3(this));
        this.g = (ImageView) view.findViewById(R.id.toolbar_tabfour);
        this.t = (FrameLayout) view.findViewById(R.id.tabitemfour);
        this.y = (TextView) view.findViewById(R.id.toolbar_textfour);
        this.t.setOnClickListener(new ECJiaTabsFragment_4(this));
        this.h = (ImageView) view.findViewById(R.id.toolbar_tabfive);
        this.u = (FrameLayout) view.findViewById(R.id.tabitemfive);
        this.z = (TextView) view.findViewById(R.id.toolbar_textfive);
        this.u.setOnClickListener(new ECJiaTabsFragment_5(this));
        b("tab_one");
    }

    public void b() {
        if (this.j == null) {
            this.j = new ECJiaHomeFragment();
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, this.j);
        beginTransaction.commitAllowingStateLoss();
        this.C = "tab_one";
        this.d.setBackgroundResource(R.drawable.footer_home_active_icon);
        this.e.setBackgroundResource(R.drawable.footer_search_icon);
        this.f.setBackgroundResource(R.drawable.footer_mine_icon);
        this.g.setBackgroundResource(R.drawable.footer_shoppingcart_icon);
        this.h.setBackgroundResource(R.drawable.footer_find_icon);
        this.v.setTextColor(this.o);
        this.w.setTextColor(this.p);
        this.x.setTextColor(this.p);
        this.y.setTextColor(this.p);
        this.z.setTextColor(this.p);
    }

    public void a(String str) {
        if (this.j == null) {
            this.j = new ECJiaHomeFragment();
        }
        new Bundle().putString("category_id", str);
        this.j.d = str;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, this.j);
        beginTransaction.commitAllowingStateLoss();
        this.C = "tab_one";
        this.d.setBackgroundResource(R.drawable.footer_home_active_icon);
        this.e.setBackgroundResource(R.drawable.footer_search_icon);
        this.f.setBackgroundResource(R.drawable.footer_mine_icon);
        this.g.setBackgroundResource(R.drawable.footer_shoppingcart_icon);
        this.h.setBackgroundResource(R.drawable.footer_find_icon);
        this.v.setTextColor(this.o);
        this.w.setTextColor(this.p);
        this.x.setTextColor(this.p);
        this.y.setTextColor(this.p);
        this.z.setTextColor(this.p);
    }

    public void b(String str) {
        FragmentTransaction beginTransaction;
        if (str == "tab_one" && !str.equals(this.C)) {
            this.C = "tab_one";
            if (this.j == null) {
                this.j = new ECJiaHomeFragment();
            }
            beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, this.j, "tab_one");
            beginTransaction.commit();
            this.d.setBackgroundResource(R.drawable.footer_home_active_icon);
            this.e.setBackgroundResource(R.drawable.footer_search_icon);
            this.f.setBackgroundResource(R.drawable.footer_mine_icon);
            this.g.setBackgroundResource(R.drawable.footer_shoppingcart_icon);
            this.h.setBackgroundResource(R.drawable.footer_find_icon);
            this.v.setTextColor(this.o);
            this.w.setTextColor(this.p);
            this.x.setTextColor(this.p);
            this.y.setTextColor(this.p);
            this.z.setTextColor(this.p);
        } else if (str == "tab_two" && !str.equals(this.C)) {
            this.C = "tab_two";
            if (this.k == null) {
                this.k = new ECJiaSearchFragment();
            }
            beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, this.k, "tab_two");
            beginTransaction.commit();
            this.d.setBackgroundResource(R.drawable.footer_home_icon);
            this.e.setBackgroundResource(R.drawable.footer_search_active_icon);
            this.f.setBackgroundResource(R.drawable.footer_mine_icon);
            this.g.setBackgroundResource(R.drawable.footer_shoppingcart_icon);
            this.h.setBackgroundResource(R.drawable.footer_find_icon);
            this.v.setTextColor(this.p);
            this.w.setTextColor(this.o);
            this.x.setTextColor(this.p);
            this.y.setTextColor(this.p);
            this.z.setTextColor(this.p);
        } else if (str == "tab_three" && !str.equals(this.C)) {
            this.C = "tab_three";
            if (this.m == null) {
                this.m = new ECJiaMineFragment();
            }
            beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, this.m, "tab_three");
            beginTransaction.commit();
            this.d.setBackgroundResource(R.drawable.footer_home_icon);
            this.e.setBackgroundResource(R.drawable.footer_search_icon);
            this.f.setBackgroundResource(R.drawable.footer_mine_active_icon);
            this.g.setBackgroundResource(R.drawable.footer_shoppingcart_icon);
            this.h.setBackgroundResource(R.drawable.footer_find_icon);
            this.v.setTextColor(this.p);
            this.w.setTextColor(this.p);
            this.x.setTextColor(this.o);
            this.y.setTextColor(this.p);
            this.z.setTextColor(this.p);
        } else if (str == "tab_four" && !str.equals(this.C)) {
            this.C = "tab_four";
            this.l = new ECJiaShoppingCartFragment();
            beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, this.l, "tab_four");
            beginTransaction.commit();
            this.d.setBackgroundResource(R.drawable.footer_home_icon);
            this.e.setBackgroundResource(R.drawable.footer_search_icon);
            this.f.setBackgroundResource(R.drawable.footer_mine_icon);
            this.g.setBackgroundResource(R.drawable.footer_shoppingcart_active_icon);
            this.h.setBackgroundResource(R.drawable.footer_find_icon);
            this.v.setTextColor(this.p);
            this.w.setTextColor(this.p);
            this.x.setTextColor(this.p);
            this.y.setTextColor(this.o);
            this.z.setTextColor(this.p);
        } else if (str == "tab_five" && !str.equals(this.C)) {
            this.C = "tab_five";
            if (this.n == null) {
                this.n = new ECJiaFoundFragment();
            }
            beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, this.n, "tab_five");
            beginTransaction.commit();
            this.d.setBackgroundResource(R.drawable.footer_home_icon);
            this.e.setBackgroundResource(R.drawable.footer_search_icon);
            this.f.setBackgroundResource(R.drawable.footer_mine_icon);
            this.g.setBackgroundResource(R.drawable.footer_shoppingcart_icon);
            this.h.setBackgroundResource(R.drawable.footer_find_active_icon);
            this.v.setTextColor(this.p);
            this.w.setTextColor(this.p);
            this.x.setTextColor(this.p);
            this.y.setTextColor(this.p);
            this.z.setTextColor(this.o);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && intent != null) {
            if (this.l == null) {
                this.l = new ECJiaShoppingCartFragment();
            }
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.fragment_container, this.l, "tab_four");
            beginTransaction.commit();
            this.d.setBackgroundResource(R.drawable.footer_home_icon);
            this.e.setBackgroundResource(R.drawable.footer_search_icon);
            this.f.setBackgroundResource(R.drawable.footer_shoplist_icon);
            this.g.setBackgroundResource(R.drawable.footer_shoppingcart_active_icon);
        }
    }

    public void onResume() {
        super.onResume();
        c();
    }

    public void c() {
        if ("".equals(this.A.getString("uid", "")) || this.a.g() == 0) {
            this.D.setVisibility(8);
            this.E.setVisibility(8);
            return;
        }
        this.D.setVisibility(0);
        this.E.setVisibility(0);
        if (this.a.g() < 10) {
            this.E.setText(this.a.g() + "");
        } else if (this.a.g() < 100 && this.a.g() > 9) {
            this.E.setText(this.a.g() + "");
        } else if (this.a.g() > 99) {
            this.E.setText("99+");
        }
    }
}
