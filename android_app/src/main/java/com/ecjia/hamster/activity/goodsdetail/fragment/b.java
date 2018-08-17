package com.ecjia.hamster.activity.goodsdetail.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaProductCommonTabHelper */
public abstract class b implements OnClickListener, com.ecjia.hamster.b.b {
    private final Context a;
    private ArrayList<Fragment> b = new ArrayList();
    private ArrayList<String> c = new ArrayList();
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private int s;
    private int t;
    private int u = -1;

    /* compiled from: ECJiaProductCommonTabHelper */
    class b_1 implements OnClickListener {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.b(1);
        }
    }

    /* compiled from: ECJiaProductCommonTabHelper */
    class b_2 implements OnClickListener {
        final /* synthetic */ b a;

        b_2(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.b(2);
        }
    }

    /* compiled from: ECJiaProductCommonTabHelper */
    class b_3 implements OnClickListener {
        final /* synthetic */ b a;

        b_3(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.b(3);
        }
    }

    /* compiled from: ECJiaProductCommonTabHelper */
    class b_4 implements OnClickListener {
        final /* synthetic */ b a;

        b_4(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.b(4);
        }
    }

    /* compiled from: ECJiaProductCommonTabHelper */
    class b_5 implements OnClickListener {
        final /* synthetic */ b a;

        b_5(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.b(5);
        }
    }

    public b(ECJiaProductCommonFragment eCJiaProductCommonFragment, ViewGroup viewGroup) {
        this.a = eCJiaProductCommonFragment.getActivity();
        View inflate = LayoutInflater.from(eCJiaProductCommonFragment.getActivity()).inflate(R.layout.fragment_goods_comment_top, null);
        a(inflate);
        viewGroup.addView(inflate);
    }

    private void a(View view) {
        this.t = this.a.getResources().getColor(R.color.public_theme_color_normal);
        this.s = this.a.getResources().getColor(R.color.my_dark);
        this.d = view.findViewById(R.id.comment_count_ll);
        this.e = view.findViewById(R.id.comment_positive_ll);
        this.f = view.findViewById(R.id.comment_moderate_ll);
        this.g = view.findViewById(R.id.comment_negative_ll);
        this.h = view.findViewById(R.id.comment_showorder_ll);
        this.i = (TextView) view.findViewById(R.id.comment_count_name);
        this.j = (TextView) view.findViewById(R.id.comment_positive_name);
        this.k = (TextView) view.findViewById(R.id.comment_moderate_name);
        this.l = (TextView) view.findViewById(R.id.comment_negative_name);
        this.m = (TextView) view.findViewById(R.id.comment_showorder_name);
        this.n = (TextView) view.findViewById(R.id.comment_count);
        this.o = (TextView) view.findViewById(R.id.comment_positive);
        this.p = (TextView) view.findViewById(R.id.comment_moderate);
        this.q = (TextView) view.findViewById(R.id.comment_negative);
        this.r = (TextView) view.findViewById(R.id.comment_showorder);
        this.d.setOnClickListener(new b_1(this));
        this.e.setOnClickListener(new b_2(this));
        this.f.setOnClickListener(new b_3(this));
        this.g.setOnClickListener(new b_4(this));
        this.h.setOnClickListener(new b_5(this));
    }

    public void a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this.i.setText((CharSequence) arrayList.get(0));
        this.j.setText((CharSequence) arrayList.get(1));
        this.k.setText((CharSequence) arrayList.get(2));
        this.l.setText((CharSequence) arrayList.get(3));
        this.m.setText((CharSequence) arrayList.get(4));
        this.n.setText((CharSequence) arrayList2.get(0));
        this.o.setText((CharSequence) arrayList2.get(1));
        this.p.setText((CharSequence) arrayList2.get(2));
        this.q.setText((CharSequence) arrayList2.get(3));
        this.r.setText((CharSequence) arrayList2.get(4));
    }

    public void onClick(View view) {
    }

    public void b(int i) {
        c(i);
        a(i);
    }

    public void c(int i) {
        if (i != this.u) {
            switch (this.u) {
                case 1:
                    this.i.setTextColor(this.s);
                    this.n.setTextColor(this.s);
                    break;
                case 2:
                    this.j.setTextColor(this.s);
                    this.o.setTextColor(this.s);
                    break;
                case 3:
                    this.k.setTextColor(this.s);
                    this.p.setTextColor(this.s);
                    break;
                case 4:
                    this.l.setTextColor(this.s);
                    this.q.setTextColor(this.s);
                    break;
                case 5:
                    this.m.setTextColor(this.s);
                    this.r.setTextColor(this.s);
                    break;
            }
            switch (i) {
                case 1:
                    this.i.setTextColor(this.t);
                    this.n.setTextColor(this.t);
                    break;
                case 2:
                    this.j.setTextColor(this.t);
                    this.o.setTextColor(this.t);
                    break;
                case 3:
                    this.k.setTextColor(this.t);
                    this.p.setTextColor(this.t);
                    break;
                case 4:
                    this.l.setTextColor(this.t);
                    this.q.setTextColor(this.t);
                    break;
                case 5:
                    this.m.setTextColor(this.t);
                    this.r.setTextColor(this.t);
                    break;
            }
        }
        this.u = i;
    }
}
