package com.ecjia.hamster.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import com.ecjia.a.q;
import com.ecjia.a.y;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.l;
import com.ecjia.component.view.ECJiaScrollView_Main;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.hamster.activity.ECJiaFunctionSettingActivity;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.activity.ECJiaMyCaptureActivity;
import com.ecjia.hamster.activity.ECJiaShareQRCodeActivity;
import com.ecjia.hamster.activity.ECJiaTopicListActivity;
import com.ecjia.hamster.adapter.m;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.o;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

public class ECJiaFoundFragment extends ECJiaBaseFragment implements OnClickListener, a {
    View d;
    ArrayList<o> e = new ArrayList();
    ArrayList<o> f = new ArrayList();
    private l g;
    private GridView h;
    private GridView i;
    private com.ecjia.hamster.adapter.l j;
    private m k;
    private View l;
    private ImageView m;
    private SharedPreferences n;
    private ECJiaScrollView_Main o;
    private Boolean p;

    class ECJiaFoundFragment_1 implements OnClickListener {
        final /* synthetic */ ECJiaFoundFragment a;

        ECJiaFoundFragment_1(ECJiaFoundFragment eCJiaFoundFragment) {
            this.a = eCJiaFoundFragment;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a.b, ECJiaFunctionSettingActivity.class));
            this.a.b.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaFoundFragment_2 implements OnClickListener {
        final /* synthetic */ ECJiaFoundFragment a;

        ECJiaFoundFragment_2(ECJiaFoundFragment eCJiaFoundFragment) {
            this.a = eCJiaFoundFragment;
        }

        public void onClick(View view) {
            this.a.b.a.open();
        }
    }

    class ECJiaFoundFragment_3 implements OnTouchListener {
        final /* synthetic */ ECJiaFoundFragment a;

        ECJiaFoundFragment_3(ECJiaFoundFragment eCJiaFoundFragment) {
            this.a = eCJiaFoundFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_found, null, false);
        a(inflate);
        if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
            this.p = Boolean.valueOf(false);
        } else {
            this.p = Boolean.valueOf(true);
        }
        return inflate;
    }

    private void a(final View view) {
        final ECJiaTopView eCJiaTopView = (ECJiaTopView) view.findViewById(R.id.found_topview);
        eCJiaTopView.setTouchEventConsumeAble(true);
        eCJiaTopView.setLeftType(1);
        eCJiaTopView.setRightType(12);
        eCJiaTopView.setRightImage((int) R.drawable.icon_found_set, new ECJiaFoundFragment_1(this));
        eCJiaTopView.setLeftBackImage((int) R.drawable.icon_main_list_white, new ECJiaFoundFragment_2(this));
        view.findViewById(R.id.found_top_scan).setOnClickListener(this);
        view.findViewById(R.id.found_top_shop_streets).setOnClickListener(this);
        view.findViewById(R.id.found_top_topic).setOnClickListener(this);
        if (this.b.getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            eCJiaTopView.setTitleImage((int) R.drawable.icon_title_found);
        } else {
            eCJiaTopView.setTitleImage((int) R.drawable.icon_title_found_english);
        }
        this.m = (ImageView) view.findViewById(R.id.found_treasure_title);
        if (this.b.getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            this.m.setImageResource(R.drawable.icon_title_discover_box);
        } else {
            this.m.setImageResource(R.drawable.icon_title_discover_box_english);
        }
        this.d = view.findViewById(R.id.treasure_layout);
        this.l = view.findViewById(R.id.found_noresult);
        this.l.setOnTouchListener(new ECJiaFoundFragment_3(this));
        this.h = (GridView) view.findViewById(R.id.found_gridview_local);
        this.h.setVerticalSpacing(0);
        this.h.setHorizontalSpacing(0);
        this.i = (GridView) view.findViewById(R.id.found_gridview_online);
        this.i.setVerticalSpacing(0);
        this.i.setHorizontalSpacing(0);
        view.findViewById(R.id.found_scan).setOnClickListener(this);
        view.findViewById(R.id.found_shop_streets).setOnClickListener(this);
        view.findViewById(R.id.found_topic).setOnClickListener(this);
        if (this.g == null) {
            this.g = new l(getActivity());
        }
        this.g.a((a) this);
        this.g.j();
        this.o = (ECJiaScrollView_Main) view.findViewById(R.id.found_scrollview);
        this.o.setOnScrollListener(new ECJiaScrollView_Main.a(this) {
            final /* synthetic */ ECJiaFoundFragment c;

            public void a(int i, int i2, int i3, int i4) {
                if (i2 < y.a(this.c.b, 110)) {
                    view.findViewById(R.id.found_top_function_icon_ll).setVisibility(8);
                    eCJiaTopView.setTitleType(TitleType.IMAGE);
                    return;
                }
                view.findViewById(R.id.found_top_function_icon_ll).setVisibility(0);
                eCJiaTopView.setTitleType(TitleType.NONE);
            }
        });
    }

    public void onResume() {
        super.onResume();
        com.ecjia.a.l.b().c();
        this.e = com.ecjia.a.l.b().d();
        q.a("functionses===原始数据的长度" + this.e.size());
        this.f.clear();
        this.n = this.b.getSharedPreferences("function_setting", 0);
        if (this.n.getBoolean("isfirst", true)) {
            a();
        } else {
            b();
        }
        this.k = new m(this.b, this.f, this.p, this.a.e());
        this.h.setAdapter(this.k);
    }

    void a() {
        String[] strArr = new String[]{"promotion", "seckill", "shake", "groupbuy", "todayhot", "message", "feedback", "map", "newgoods", "checkin"};
        for (Object obj : strArr) {
            this.f.add(com.ecjia.a.l.b().a().get(obj));
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.f.size(); i++) {
            jSONArray.put(((o) this.f.get(i)).a());
        }
        q.a("isfirst true");
        this.n.edit().putString("support", jSONArray.toString()).commit();
        this.n.edit().putBoolean("isfirst", false).commit();
    }

    void b() {
        JSONArray jSONArray;
        JSONException e;
        q.a("isfirst false");
        try {
            jSONArray = new JSONArray(this.n.getString("support", new JSONArray().toString()));
            try {
                q.a("array===" + jSONArray.toString());
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                if (jSONArray != null) {
                }
                return;
            }
        } catch (JSONException e3) {
            e = e3;
            jSONArray = null;
            e.printStackTrace();
            if (jSONArray != null) {
                return;
            }
        }
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                this.f.add(com.ecjia.a.l.b().a(jSONArray.optString(i)));
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.found_scan:
            case R.id.found_top_scan:
                startActivity(new Intent(this.b, ECJiaMyCaptureActivity.class));
                return;
            case R.id.found_shop_streets:
            case R.id.found_top_shop_streets:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    getActivity().startActivityForResult(new Intent(this.b, ECJiaLoginActivity.class), 259);
                    return;
                } else {
                    startActivity(new Intent(this.b, ECJiaShareQRCodeActivity.class));
                    return;
                }
            case R.id.found_topic:
            case R.id.found_top_topic:
                startActivity(new Intent(this.b, ECJiaTopicListActivity.class));
                return;
            default:
                return;
        }
    }

    public void onDestroyView() {
        com.ecjia.a.l.b().e();
        super.onDestroyView();
    }

    public void a(String str, String str2, ax axVar) {
        if ("home/discover".equals(str)) {
            this.l.setVisibility(8);
            if (axVar.b() == 1) {
                q.a("=======" + this.g.a.size());
                if (this.g.a.size() > 0) {
                    this.d.setVisibility(0);
                    this.j = new com.ecjia.hamster.adapter.l(getActivity(), this.g.a);
                    this.i.setAdapter(this.j);
                    return;
                }
                this.d.setVisibility(8);
                return;
            }
            this.d.setVisibility(8);
        }
    }
}
