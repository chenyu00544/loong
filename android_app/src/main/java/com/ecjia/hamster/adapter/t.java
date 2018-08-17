package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.s;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaGroupBuyListAdapter */
public class t extends BaseAdapter {
    private final int a;
    private Context b;
    private ArrayList<s> c = new ArrayList();

    /* compiled from: ECJiaGroupBuyListAdapter */
    private class a {
        final /* synthetic */ t a;
        private LinearLayout b;
        private FrameLayout c;
        private ECJiaSelectableRoundedImageView d;
        private LinearLayout e;
        private TextView f;
        private TextView g;
        private TextView h;
        private TextView i;
        private TextView j;
        private TextView k;
        private TextView l;
        private View m;

        private a(t tVar) {
            this.a = tVar;
        }
    }

    public t(Context context, ArrayList<s> arrayList) {
        this.b = context;
        this.c = arrayList;
        this.a = (int) context.getResources().getDimension(R.dimen.dp_10);
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(ArrayList<s> arrayList) {
        this.c = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        final s sVar = (s) this.c.get(i);
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.b).inflate(R.layout.groupbuy_list_item, null);
            aVar2.b = (LinearLayout) view.findViewById(R.id.ll_groupbuy_item);
            aVar2.c = (FrameLayout) view.findViewById(R.id.fl_groupbuy_item);
            aVar2.d = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.iv_groupbuy);
            aVar2.e = (LinearLayout) view.findViewById(R.id.ll_groupbuy_time);
            aVar2.f = (TextView) view.findViewById(R.id.tv_groupbuy_time_day);
            aVar2.g = (TextView) view.findViewById(R.id.tv_groupbuy_time_hour);
            aVar2.h = (TextView) view.findViewById(R.id.tv_groupbuy_time_min);
            aVar2.i = (TextView) view.findViewById(R.id.tv_groupbuy_time_sec);
            aVar2.j = (TextView) view.findViewById(R.id.tv_groupbuy_goods_name);
            aVar2.k = (TextView) view.findViewById(R.id.tv_groupbuy_goods_price);
            aVar2.l = (TextView) view.findViewById(R.id.tv_groupbuy_goods_origin_price);
            aVar2.l.getPaint().setFlags(17);
            aVar2.m = view.findViewById(R.id.bottom_view);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == this.c.size() - 1) {
            aVar.m.setVisibility(0);
        } else {
            aVar.m.setVisibility(8);
        }
        LayoutParams layoutParams = aVar.c.getLayoutParams();
        layoutParams.width = a() - (this.a * 2);
        layoutParams.height = (int) (((double) layoutParams.width) * 0.8d);
        aVar.c.setLayoutParams(layoutParams);
        aVar.j.setText(sVar.h());
        p.a(this.b).a(aVar.d, sVar.l().getThumb());
        if (k.a(sVar.k()) != 0.0f) {
            aVar.k.setText(sVar.k());
            aVar.l.setText(sVar.j());
        } else if (k.a(sVar.j()) == 0.0f) {
            aVar.k.setText("免费");
            aVar.l.setText("");
        } else {
            aVar.k.setText(sVar.j());
            aVar.l.setText(sVar.i());
        }
        if (sVar.a() == null) {
            aVar.f.setText("");
            aVar.g.setText("00");
            aVar.h.setText("00");
            aVar.i.setText("00");
        } else {
            aVar.f.setText(sVar.a().a());
            aVar.g.setText(sVar.a().b());
            aVar.h.setText(sVar.a().c());
            aVar.i.setText(sVar.a().d());
        }
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ t b;

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(this.b.b, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", sVar.g());
                intent.putExtra("object_id", sVar.e());
                intent.putExtra("rec_type", sVar.f());
                this.b.b.startActivity(intent);
                ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.b).getWindowManager().getDefaultDisplay().getHeight());
    }
}
