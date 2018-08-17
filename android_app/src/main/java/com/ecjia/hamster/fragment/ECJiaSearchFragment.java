package com.ecjia.hamster.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ad;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.hamster.activity.ECJiaGoodsListActivity;
import com.ecjia.hamster.activity.ECJiaSearchNewActivity;
import com.ecjia.hamster.adapter.bd;
import com.ecjia.hamster.adapter.bd.b;
import com.ecjia.hamster.adapter.be;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;

public class ECJiaSearchFragment extends Fragment implements a {
    private View a;
    private EditText b;
    private SharedPreferences c;
    private ListView d;
    private ECJiaMyGridView e;
    private bd f;
    private be g;
    private RelativeLayout h;
    private ImageView i;
    private LinearLayout j;
    private TextView k;
    private ArrayList<ECJia_CATEGORY> l;
    private ad m;
    private String n;
    private String o;
    private String p;
    private int q;
    private int r;

    class ECJiaSearchFragment_1 implements OnClickListener {
        final /* synthetic */ ECJiaSearchFragment a;

        ECJiaSearchFragment_1(ECJiaSearchFragment eCJiaSearchFragment) {
            this.a = eCJiaSearchFragment;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a.getActivity(), ECJiaGoodsListActivity.class);
            intent.putExtra("category_id", this.a.q + "");
            this.a.startActivity(intent);
            this.a.getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaSearchFragment_2 implements b {
        final /* synthetic */ ECJiaSearchFragment a;

        ECJiaSearchFragment_2(ECJiaSearchFragment eCJiaSearchFragment) {
            this.a = eCJiaSearchFragment;
        }

        @TargetApi(11)
        public void a(View view, int i) {
            if (view.getId() == R.id.ll_item) {
                if (this.a.f.a.size() <= 0) {
                    this.a.h.setVisibility(8);
                    this.a.j.setVisibility(8);
                } else if (((ECJia_CATEGORY) this.a.f.a.get(i)).getChildren().size() == 0) {
                    Intent intent = new Intent(this.a.getActivity(), ECJiaGoodsListActivity.class);
                    intent.putExtra("category_id", String.valueOf(((ECJia_CATEGORY) this.a.f.a.get(i)).getId()));
                    this.a.startActivity(intent);
                    this.a.getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                } else {
                    for (int i2 = 0; i2 < this.a.f.a.size(); i2++) {
                        ((ECJia_CATEGORY) this.a.f.a.get(i2)).setChoose(false);
                    }
                    ((ECJia_CATEGORY) this.a.f.a.get(i)).setChoose(true);
                    this.a.g.a.clear();
                    this.a.g.a.addAll(((ECJia_CATEGORY) this.a.f.a.get(i)).getChildren());
                    this.a.h.setVisibility(0);
                    this.a.k.setText(((ECJia_CATEGORY) this.a.f.a.get(i)).getName());
                    this.a.n = ((ECJia_CATEGORY) this.a.f.a.get(i)).getName();
                    this.a.j.setVisibility(0);
                    p.a(this.a.getActivity()).a(this.a.i, ((ECJia_CATEGORY) this.a.f.a.get(i)).getImage());
                    this.a.o = ((ECJia_CATEGORY) this.a.f.a.get(i)).getImage();
                    this.a.q = ((ECJia_CATEGORY) this.a.f.a.get(i)).getId();
                    this.a.p = ((ECJia_CATEGORY) this.a.f.a.get(i)).getName();
                }
                this.a.f.notifyDataSetChanged();
                this.a.g.notifyDataSetChanged();
                this.a.d.smoothScrollToPositionFromTop(i, 0);
            }
        }
    }

    class ECJiaSearchFragment_3 implements OnClickListener {
        final /* synthetic */ ECJiaSearchFragment a;

        ECJiaSearchFragment_3(ECJiaSearchFragment eCJiaSearchFragment) {
            this.a = eCJiaSearchFragment;
        }

        @TargetApi(11)
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(this.a.getActivity().getApplicationContext(), ECJiaSearchNewActivity.class);
            this.a.startActivityForResult(intent, 100);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.search, null);
        this.c = getActivity().getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.r = (int) getActivity().getResources().getDimension(R.dimen.searchtop_dp);
        if (this.m == null) {
            this.m = new ad(getActivity());
            this.m.b();
        }
        this.m.a((a) this);
        b();
        return this.a;
    }

    private void b() {
        this.j = (LinearLayout) this.a.findViewById(R.id.ll_category_top);
        this.i = (ImageView) this.a.findViewById(R.id.iv_category_top);
        this.i.setLayoutParams(new LayoutParams(((a() * 5) / 7) - this.r, ((((a() * 5) / 7) - this.r) * 3) / 7));
        this.h = (RelativeLayout) this.a.findViewById(R.id.rl_choose);
        this.k = (TextView) this.a.findViewById(R.id.tv_choose);
        if (!TextUtils.isEmpty(this.n)) {
            this.h.setVisibility(0);
            this.k.setText(this.n);
        }
        if (!TextUtils.isEmpty(this.o)) {
            this.j.setVisibility(0);
            p.a(getActivity()).a(this.i, this.o);
        }
        this.j.setOnClickListener(new ECJiaSearchFragment_1(this));
        this.d = (ListView) this.a.findViewById(R.id.list);
        this.e = (ECJiaMyGridView) this.a.findViewById(R.id.gv);
        if (this.f == null) {
            this.f = new bd(this.m.c, getActivity());
        }
        this.d.setAdapter(this.f);
        if (this.m.c.size() > 0) {
            this.l = ((ECJia_CATEGORY) this.m.c.get(0)).getChildren();
        } else {
            this.l = new ArrayList();
        }
        if (this.g == null) {
            this.g = new be(getActivity(), this.l);
        }
        this.e.setAdapter(this.g);
        this.f.a(new ECJiaSearchFragment_2(this));
        this.b = (EditText) this.a.findViewById(R.id.search_input);
        this.b.setFocusable(false);
        this.b.setOnClickListener(new ECJiaSearchFragment_3(this));
    }

    public int a() {
        return Math.min(getActivity().getWindowManager().getDefaultDisplay().getWidth(), getActivity().getWindowManager().getDefaultDisplay().getHeight());
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("Search");
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Search");
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("goods/category") && axVar.b() == 1) {
            if (this.m.c.size() > 0) {
                ((ECJia_CATEGORY) this.m.c.get(0)).setChoose(true);
                this.j.setVisibility(0);
                this.o = ((ECJia_CATEGORY) this.m.c.get(0)).getImage();
                this.q = ((ECJia_CATEGORY) this.m.c.get(0)).getId();
                this.p = ((ECJia_CATEGORY) this.m.c.get(0)).getName();
                p.a(getActivity()).a(this.i, ((ECJia_CATEGORY) this.m.c.get(0)).getImage());
                this.h.setVisibility(0);
                this.n = ((ECJia_CATEGORY) this.m.c.get(0)).getName();
                this.k.setText(this.n);
                this.l.clear();
                this.l.addAll(((ECJia_CATEGORY) this.m.c.get(0)).getChildren());
            } else {
                this.h.setVisibility(8);
                this.j.setVisibility(8);
            }
            this.f.notifyDataSetChanged();
            this.g.notifyDataSetChanged();
        }
    }
}
