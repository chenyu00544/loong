package com.ecjia.hamster.activity.goodsdetail.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.i;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

@SuppressLint({"ValidFragment"})
public class ECJiaProductCommonListFragment extends ECJiaGoodsDetailBaseFragment implements a, ECJiaXListView.a {
    String d;
    private i e;
    private String f;
    private com.ecjia.hamster.activity.goodsdetail.adapter.a g;
    private FrameLayout h;
    private ECJiaXListView i;
    private View j;

    @SuppressLint({"ValidFragment"})
    public ECJiaProductCommonListFragment(String str) {
        this.d = str;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.j != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.j.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.j);
            }
        } else {
            this.j = layoutInflater.inflate(R.layout.fragment_goodsdetail_comment, null);
            c();
            a(this.j);
        }
        return this.j;
    }

    private void c() {
        e();
        d();
    }

    private void a(View view) {
        this.h = (FrameLayout) view.findViewById(R.id.no_comment);
        this.i = (ECJiaXListView) view.findViewById(R.id.comment_list);
        this.i.setPullLoadEnable(true);
        this.i.setRefreshTime();
        this.i.setXListViewListener(this, 1);
        this.g = new com.ecjia.hamster.activity.goodsdetail.adapter.a(getActivity(), this.e.j);
        this.i.setAdapter(this.g);
    }

    private void d() {
        this.e = new i(getActivity());
        this.e.a((a) this);
        this.e.a(this.f, this.d, false);
    }

    private void e() {
        this.f = getActivity().getIntent().getStringExtra("goods_id");
    }

    public void b() {
        if (this.e.j.size() > 0) {
            this.i.setVisibility(0);
            this.h.setVisibility(8);
            this.g.notifyDataSetChanged();
            return;
        }
        this.i.setVisibility(8);
        this.h.setVisibility(0);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
        this.e.h();
    }

    public void a(int i) {
        this.e.a(this.f, this.d, true);
    }

    public void b(int i) {
        this.e.b(this.f, this.d);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case 1707207783:
                if (str.equals("goods/comment/list")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.i.setRefreshTime();
                    this.i.stopRefresh();
                    this.i.stopLoadMore();
                    if (this.e.a.a() == 0) {
                        this.i.setPullLoadEnable(false);
                    } else {
                        this.i.setPullLoadEnable(true);
                    }
                    b();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
