package com.ecjia.hamster.activity.goodsdetail.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.i;
import com.ecjia.hamster.activity.goodsdetail.view.ECJiaCommentViewPager;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

@SuppressLint({"ValidFragment"})
public class ECJiaProductCommonFragment extends ECJiaGoodsDetailBaseFragment implements a {
    Activity d;
    private i e;
    private String f;
    private ArrayList<Fragment> g = new ArrayList();
    private ECJiaCommentViewPager h;
    private ArrayList<String> i = new ArrayList();
    private ArrayList<String> j = new ArrayList();
    private LinearLayout k;
    private b l;
    private Fragment m;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.d = activity;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_common, null);
        b();
        a(inflate);
        return inflate;
    }

    private void b() {
        e();
        d();
    }

    private void a(View view) {
        this.h = (ECJiaCommentViewPager) view.findViewById(R.id.comment_list_vierpager);
        this.k = (LinearLayout) view.findViewById(R.id.comment_list_tablayout);
        c();
        this.l.b(1);
    }

    private void c() {
        this.l = new b(this, this, this.k) {
            final /* synthetic */ ECJiaProductCommonFragment a;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.a((Fragment) this.a.g.get(0), "one");
                        return;
                    case 2:
                        this.a.a((Fragment) this.a.g.get(1), "two");
                        return;
                    case 3:
                        this.a.a((Fragment) this.a.g.get(2), "three");
                        return;
                    case 4:
                        this.a.a((Fragment) this.a.g.get(3), "four");
                        return;
                    case 5:
                        this.a.a((Fragment) this.a.g.get(4), "five");
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void a(Fragment fragment, String str) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (this.m == null) {
            beginTransaction.add((int) R.id.comment_list_frame, (Fragment) this.g.get(0)).commit();
        } else if (this.m != fragment) {
            if (fragment.isAdded()) {
                beginTransaction.hide(this.m).show(fragment).commit();
            } else {
                beginTransaction.hide(this.m).add(R.id.comment_list_frame, fragment, str).commit();
            }
        }
        this.m = fragment;
    }

    private void d() {
        this.e = new i(getActivity());
        this.e.a((a) this);
        this.e.a(this.f, "all", false);
    }

    private void e() {
        this.f = getActivity().getIntent().getStringExtra("goods_id");
        this.g.add(new ECJiaProductCommonListFragment("all"));
        this.g.add(new ECJiaProductCommonListFragment("positive"));
        this.g.add(new ECJiaProductCommonListFragment("moderate"));
        this.g.add(new ECJiaProductCommonListFragment("negative"));
        this.g.add(new ECJiaProductCommonListFragment("showorder"));
    }

    public void onPause() {
        super.onPause();
        this.e.h();
    }

    public void onResume() {
        super.onResume();
        this.e.a((a) this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("goods/comment/list") && axVar.b() == 1) {
            this.i.clear();
            this.i.add(this.d.getResources().getString(R.string.comment_type_all));
            this.i.add(this.d.getResources().getString(R.string.comment_type_positive));
            this.i.add(this.d.getResources().getString(R.string.comment_type_moderate));
            this.i.add(this.d.getResources().getString(R.string.comment_type_negative));
            this.i.add(this.d.getResources().getString(R.string.comment_type_showorder));
            this.j.clear();
            this.j.add(this.e.t + "");
            this.j.add(this.e.u + "");
            this.j.add(this.e.v + "");
            this.j.add(this.e.w + "");
            this.j.add(this.e.x + "");
            this.l.a(this.i, this.j);
        }
    }
}
