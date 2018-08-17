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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.an;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaTopicInfoAdapter */
public class by extends BaseAdapter {
    private final int a;
    private Context b;
    private ArrayList<an> c;
    private p d;

    /* compiled from: ECJiaTopicInfoAdapter */
    private class a {
        final /* synthetic */ by a;
        private ECJiaSelectableRoundedImageView b;
        private LinearLayout c;
        private LinearLayout d;
        private ECJiaSelectableRoundedImageView e;
        private LinearLayout f;
        private LinearLayout g;
        private LinearLayout h;
        private View i;
        private ECJiaAutoReturnView j;
        private ECJiaAutoReturnView k;
        private TextView l;
        private TextView m;
        private TextView n;
        private TextView o;
        private TextView p;
        private TextView q;
        private LinearLayout r;
        private LinearLayout s;

        private a(by byVar) {
            this.a = byVar;
        }
    }

    public by(Context context, ArrayList<an> arrayList) {
        this.b = context;
        this.c = arrayList;
        this.d = p.a(context);
        this.a = (int) context.getResources().getDimension(R.dimen.good_list_distance);
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

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        an anVar;
        an anVar2;
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.b).inflate(R.layout.goods_list_item, null);
            aVar2.b = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.goods_img_left);
            aVar2.c = (LinearLayout) view.findViewById(R.id.ll_goods_img_left);
            aVar2.d = (LinearLayout) view.findViewById(R.id.ll_goods_item_left);
            aVar2.e = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.goods_img_right);
            aVar2.f = (LinearLayout) view.findViewById(R.id.ll_goods_img_right);
            aVar2.g = (LinearLayout) view.findViewById(R.id.ll_goods_item_right);
            aVar2.h = (LinearLayout) view.findViewById(R.id.ll_both_item);
            aVar2.i = view.findViewById(R.id.goods_item_top);
            aVar2.j = (ECJiaAutoReturnView) view.findViewById(R.id.goodlist_goodname_left);
            aVar2.k = (ECJiaAutoReturnView) view.findViewById(R.id.goodlist_goodname_right);
            aVar2.l = (TextView) view.findViewById(R.id.goodlist_origin_price_left);
            aVar2.m = (TextView) view.findViewById(R.id.goodlist_origin_price_right);
            aVar2.n = (TextView) view.findViewById(R.id.goodlist_shop_price_left);
            aVar2.o = (TextView) view.findViewById(R.id.goodlist_shop_price_right);
            aVar2.p = (TextView) view.findViewById(R.id.tv_saving_left);
            aVar2.q = (TextView) view.findViewById(R.id.tv_saving_right);
            aVar2.r = (LinearLayout) view.findViewById(R.id.ll_goodlist_mb_left);
            aVar2.s = (LinearLayout) view.findViewById(R.id.ll_goodlist_mb_right);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.i.setVisibility(0);
        } else {
            aVar.i.setVisibility(8);
        }
        LayoutParams layoutParams = aVar.c.getLayoutParams();
        layoutParams.width = (a() - this.a) / 2;
        layoutParams.height = layoutParams.width;
        aVar.c.setLayoutParams(layoutParams);
        aVar.f.setLayoutParams(layoutParams);
        if (i * 2 < this.c.size()) {
            anVar = (an) this.c.get(i * 2);
        } else {
            anVar = null;
        }
        if ((i * 2) + 1 < this.c.size()) {
            anVar2 = (an) this.c.get((i * 2) + 1);
        } else {
            anVar2 = null;
        }
        if (anVar == null) {
            aVar.h.setVisibility(8);
        } else {
            aVar.h.setVisibility(0);
            p.a(this.b).a(aVar.b, anVar.g().getThumb());
            if (k.a(anVar.h()) != 0.0f) {
                aVar.n.setText(anVar.h());
                aVar.l.setText(anVar.f());
            } else if (k.a(anVar.f()) == 0.0f) {
                aVar.n.setText("免费");
                aVar.l.setText("");
            } else {
                aVar.n.setText(anVar.f());
                aVar.l.setText(anVar.e());
            }
            if ("MOBILEBUY_GOODS".equals(anVar.a())) {
                aVar.r.setVisibility(0);
                aVar.p.setText(anVar.b());
            } else {
                aVar.r.setVisibility(8);
            }
            aVar.j.setContent(anVar.d());
            aVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ by b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.b, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", anVar.c());
                    this.b.b.startActivity(intent);
                    ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
            if (anVar2 == null) {
                aVar.g.setVisibility(4);
            } else {
                aVar.g.setVisibility(0);
                p.a(this.b).a(aVar.e, anVar2.g().getThumb());
                if (k.a(anVar2.h()) != 0.0f) {
                    aVar.o.setText(anVar2.h());
                    aVar.m.setText(anVar2.f());
                } else if (k.a(anVar2.f()) == 0.0f) {
                    aVar.o.setText("免费");
                    aVar.m.setText("");
                } else {
                    aVar.o.setText(anVar2.f());
                    aVar.m.setText(anVar2.e());
                }
                if ("MOBILEBUY_GOODS".equals(anVar2.a())) {
                    aVar.s.setVisibility(0);
                    aVar.q.setText(anVar2.b());
                } else {
                    aVar.s.setVisibility(8);
                }
                aVar.k.setContent(anVar2.d());
                aVar.g.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ by b;

                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(this.b.b, ECJiaGoodsDetailActivity.class);
                        intent.putExtra("goods_id", anVar2.c());
                        this.b.b.startActivity(intent);
                        ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    }
                });
            }
        }
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.b).getWindowManager().getDefaultDisplay().getHeight());
    }
}
