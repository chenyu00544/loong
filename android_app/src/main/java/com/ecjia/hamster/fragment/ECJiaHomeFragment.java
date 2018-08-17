package com.ecjia.hamster.fragment;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.a.u;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.af;
import com.ecjia.component.view.ECJiaGoodsViewPager;
import com.ecjia.consts.b;
import com.ecjia.hamster.activity.ECJiaChooseCityActivity;
import com.ecjia.hamster.activity.ECJiaSearchNewActivity;
import com.ecjia.hamster.activity.ECJiaShopCategoryActivity;
import com.ecjia.hamster.adapter.aj;
import com.ecjia.hamster.fragment.homefragment.adapter.ECJiaHomeFragmentPageAdapter;
import com.ecjia.hamster.fragment.homefragment.fragment.ECJiaGoodShopFragment;
import com.ecjia.hamster.fragment.homefragment.fragment.ECJiaShopListFragment;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.i;
import com.ecmoban.android.missmall.ECJiaPushActivity;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaHomeFragment extends ECJiaBaseFragment implements a, b.a {
    private TextView A;
    private ECJiaGoodShopFragment B;
    String d;
    public ImageView e;
    boolean f = false;
    ECJiaTabsFragment.a g;
    public ECJiaGoodsViewPager h;
    List<Fragment> i = new ArrayList();
    TabLayout j;
    ECJiaHomeFragmentPageAdapter k;
    String[] l;
    public boolean m = false;
    ArrayList<ECJia_CATEGORY> n = new ArrayList();
    private SharedPreferences o;
    private String p;
    private View q;
    private ImageView r;
    private RelativeLayout s;
    private TextView t;
    private i u;
    private LinearLayout v;
    private String w = "hot";
    private Activity x;
    private FrameLayout y;
    private af z;

    class ECJiaHomeFragment_1 implements OnClickListener {
        final /* synthetic */ ECJiaHomeFragment a;

        ECJiaHomeFragment_1(ECJiaHomeFragment eCJiaHomeFragment) {
            this.a = eCJiaHomeFragment;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a.x, ECJiaShopCategoryActivity.class);
            Serializable arrayList = new ArrayList();
            for (int i = 0; i < this.a.z.c.size(); i++) {
                arrayList.add(((ECJia_CATEGORY) this.a.z.c.get(i)).getName());
            }
            arrayList.add(0, "精选");
            intent.putExtra("seller_category", arrayList);
            intent.putExtra("position", this.a.j.getSelectedTabPosition());
            this.a.startActivityForResult(intent, 11001);
            this.a.x.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaHomeFragment_2 implements OnPageChangeListener {
        final /* synthetic */ ECJiaHomeFragment a;

        ECJiaHomeFragment_2(ECJiaHomeFragment eCJiaHomeFragment) {
            this.a = eCJiaHomeFragment;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (i == 0) {
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    class ECJiaHomeFragment_3 implements OnClickListener {
        final /* synthetic */ ECJiaHomeFragment a;

        ECJiaHomeFragment_3(ECJiaHomeFragment eCJiaHomeFragment) {
            this.a = eCJiaHomeFragment;
        }

        public void onClick(View view) {
            this.a.g.c();
        }
    }

    class ECJiaHomeFragment_4 implements OnClickListener {
        final /* synthetic */ ECJiaHomeFragment a;

        ECJiaHomeFragment_4(ECJiaHomeFragment eCJiaHomeFragment) {
            this.a = eCJiaHomeFragment;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a.x, ECJiaChooseCityActivity.class);
            intent.putExtra("chooseagain", "chooseagain");
            this.a.startActivityForResult(intent, 100);
            this.a.x.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }

    class ECJiaHomeFragment_5 implements OnClickListener {
        final /* synthetic */ ECJiaHomeFragment a;

        ECJiaHomeFragment_5(ECJiaHomeFragment eCJiaHomeFragment) {
            this.a = eCJiaHomeFragment;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(this.a.x, ECJiaSearchNewActivity.class);
            intent.putExtra("filter", new ECJia_FILTER());
            this.a.startActivityForResult(intent, 100);
            this.a.x.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaHomeFragment_6 implements OnClickListener {
        final /* synthetic */ ECJiaHomeFragment a;

        ECJiaHomeFragment_6(ECJiaHomeFragment eCJiaHomeFragment) {
            this.a = eCJiaHomeFragment;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a.getActivity(), ECJiaPushActivity.class));
            this.a.x.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public void onAttach(Activity activity) {
        c.a().a((Object) this);
        this.x = activity;
        this.g = (ECJiaTabsFragment.a) activity;
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.q != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.q.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.q);
            }
        } else {
            this.q = layoutInflater.inflate(R.layout.home_fragment, null);
            this.B = new ECJiaGoodShopFragment(this);
            f();
            a();
            d();
            e();
        }
        return this.q;
    }

    private void d() {
        this.q.findViewById(R.id.shop_category_more).setOnClickListener(new ECJiaHomeFragment_1(this));
    }

    void a() {
        this.j = (TabLayout) this.q.findViewById(R.id.home_tablayout);
        this.j.setSelectedTabIndicatorColor(getResources().getColor(R.color.public_theme_color_normal));
        this.j.setTabTextColors(getResources().getColor(R.color.my_black), getResources().getColor(R.color.public_theme_color_normal));
        this.j.setTabMode(0);
        this.h = (ECJiaGoodsViewPager) this.q.findViewById(R.id.home_viewpager);
        this.i.clear();
        this.i.add(new ECJiaGoodShopFragment(this));
        this.h.addOnPageChangeListener(new ECJiaHomeFragment_2(this));
    }

    private void e() {
        if (this.z == null) {
            this.z = new af(getActivity());
            this.z.a((a) this);
        }
        this.z.a();
    }

    private void f() {
        getResources().getString(R.string.ecmoban);
        this.o = this.x.getSharedPreferences(Constants.KEY_USER_ID, 0);
        a(this.o.getString("localString", ""));
        this.s = (RelativeLayout) this.q.findViewById(R.id.home_topvoew);
        this.s.setBackgroundColor(getActivity().getResources().getColor(R.color.public_theme_color_normal_2));
        this.e = (ImageView) this.q.findViewById(R.id.top_view_list);
        this.e.setVisibility(0);
        this.e.setOnClickListener(new ECJiaHomeFragment_3(this));
        this.v = (LinearLayout) this.q.findViewById(R.id.city_item);
        this.t = (TextView) this.q.findViewById(R.id.city_name);
        this.t.setText(this.u.b());
        this.v.setOnClickListener(new ECJiaHomeFragment_4(this));
        this.r = (ImageView) this.q.findViewById(R.id.search_input);
        this.r.setOnClickListener(new ECJiaHomeFragment_5(this));
        this.y = (FrameLayout) this.q.findViewById(R.id.search_frame_edit);
        this.y.setOnClickListener(new ECJiaHomeFragment_6(this));
        this.A = (TextView) this.q.findViewById(R.id.message_num);
    }

    public void onResume() {
        super.onResume();
        if (!this.m) {
            this.m = true;
            b.a((b.a) this);
        }
        MobclickAgent.onPageStart("Home");
        this.g.addIgnoredView(this.h);
        this.g.addIgnoredView(this.j);
        this.g.addIgnoredView(this.q.findViewById(R.id.home_good_shop));
        c();
        g();
        if (!TextUtils.isEmpty(this.d)) {
            q.a("seller size()" + this.z.c.size());
            if (this.z.c.size() > 0) {
                for (int i = 0; i < this.z.c.size(); i++) {
                    q.a("i==" + i + "  " + ((ECJia_CATEGORY) this.z.c.get(i)).getName() + "==" + ((ECJia_CATEGORY) this.z.c.get(i)).getId());
                    if (this.d.equals(((ECJia_CATEGORY) this.z.c.get(i)).getId() + "")) {
                        q.a("category_id == tab的categoryid i==" + i);
                        this.j.getTabAt(i + 1).select();
                        break;
                    }
                }
            }
            this.d = null;
        }
        if (this.i.size() == 1) {
            this.q.findViewById(R.id.home_good_shop).setVisibility(0);
            this.h.setVisibility(8);
            getChildFragmentManager().beginTransaction().replace(R.id.home_good_shop, this.B).commit();
            Tab newTab = this.j.newTab();
            newTab.setText((CharSequence) "精选");
            this.j.removeAllTabs();
            this.j.addTab(newTab);
            return;
        }
        this.q.findViewById(R.id.home_good_shop).setVisibility(8);
        this.h.setVisibility(0);
    }

    private void g() {
        int b = aj.a(this.b).b();
        if (b == 0) {
            this.A.setVisibility(8);
            return;
        }
        this.A.setVisibility(0);
        this.A.setText(b + "");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            a(this.o.getString("localString", ""));
            this.t.setText(this.u.b());
        }
        if (i == 11001 && i2 == -1 && this.j.getTabCount() > 0) {
            this.j.getTabAt(intent.getIntExtra("position", 0)).select();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
        this.g.removeIgnoredView(this.h);
        this.g.removeIgnoredView(this.j);
        this.g.removeIgnoredView(this.q.findViewById(R.id.home_good_shop));
        MobclickAgent.onPageEnd("Home");
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if ("refresh_sendarea".equals(bVar.c())) {
            a(this.o.getString("localString", ""));
            this.t.setText(this.u.b());
        }
        if (bVar.c().equals("USER_LOGIN_SUCCESS")) {
            q.a("ECJiaHomeFragment 收到消息");
            c();
        }
        if (bVar.c().equals("USER_PHOTO_DOWNLOAD_SUCCESS")) {
            q.a("ECJiaHomeFragment 收到消息");
            c();
        }
        if (bVar.c().equals("exsit")) {
            q.a("ECJiaHomeFragment 收到消息");
            c();
        }
        if (bVar.c().equals("USER_CHANGE_PHOTO")) {
            q.a("ECJiaHomeFragment 收到消息");
            c();
        }
        if (bVar.c().equals("ECJia_MESSAGE")) {
            this.A.setText(bVar.b() + "");
        }
        if (bVar.c().equals("UPDATE_MESSAGE")) {
            g();
        }
    }

    public void onStop() {
        super.onStop();
        if (!b()) {
            this.m = false;
        }
    }

    public void onDetach() {
        super.onDetach();
    }

    public boolean b() {
        ActivityManager activityManager = (ActivityManager) this.x.getApplicationContext().getSystemService("activity");
        String packageName = this.x.getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.u = new i();
            this.u.d(this.x.getResources().getString(R.string.please_select));
            return;
        }
        try {
            this.u = i.a(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        c.a().b(this);
        super.onDestroyView();
    }

    void c() {
        this.p = this.o.getString("uid", "");
        if (TextUtils.isEmpty(this.p)) {
            this.e.setImageResource(R.drawable.profile_no_avarta_icon);
        } else if (u.a().a(this.p)) {
            this.e.setImageBitmap(u.a().b(this.p));
        } else {
            this.e.setImageResource(R.drawable.profile_no_avarta_icon_light);
        }
    }

    public void a(String str, String str2, ax axVar) {
        if ("seller/category".equals(str)) {
            this.j.removeAllTabs();
            if (this.z.c.size() > 0) {
                getChildFragmentManager().beginTransaction().remove(this.B).commit();
                for (int i = 1; i < this.z.c.size(); i++) {
                    this.n.add(this.z.c.get(i));
                }
                this.l = new String[(this.z.c.size() + 1)];
                for (int i2 = 0; i2 < this.z.c.size(); i2++) {
                    this.l[i2 + 1] = ((ECJia_CATEGORY) this.z.c.get(i2)).getName();
                    this.i.add(new ECJiaShopListFragment(((ECJia_CATEGORY) this.z.c.get(i2)).getId() + ""));
                }
                this.l[0] = "精选";
                this.k = new ECJiaHomeFragmentPageAdapter(getFragmentManager(), this.i, Arrays.asList(this.l));
                this.h.setAdapter(this.k);
                this.j.setupWithViewPager(this.h);
                this.h.setVisibility(0);
                this.q.findViewById(R.id.home_good_shop).setVisibility(8);
                return;
            }
            this.h.setVisibility(8);
            this.q.findViewById(R.id.home_good_shop).setVisibility(0);
            getChildFragmentManager().beginTransaction().replace(R.id.home_good_shop, this.B).commit();
            Tab newTab = this.j.newTab();
            newTab.setText((CharSequence) "精选");
            this.j.addTab(newTab);
        }
    }
}
