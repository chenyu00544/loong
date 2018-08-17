package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaGoodsListAdapter */
public class s extends BaseAdapter {
    private final int a;
    private ArrayList<au> b;
    private Context c;
    private Resources d;

    /* compiled from: ECJiaGoodsListAdapter */
    static class a {
        private ECJiaSelectableRoundedImageView a;
        private LinearLayout b;
        private LinearLayout c;
        private ECJiaSelectableRoundedImageView d;
        private LinearLayout e;
        private LinearLayout f;
        private LinearLayout g;
        private View h;
        private ECJiaAutoReturnView i;
        private ECJiaAutoReturnView j;
        private TextView k;
        private TextView l;
        private TextView m;
        private TextView n;
        private TextView o;
        private TextView p;
        private LinearLayout q;
        private LinearLayout r;

        a() {
        }
    }

    public s(Context context, ArrayList<au> arrayList) {
        this.c = context;
        this.b = arrayList;
        this.a = (int) context.getResources().getDimension(R.dimen.good_list_distance);
        this.d = context.getResources();
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(ArrayList<au> arrayList) {
        this.b = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        au auVar;
        au auVar2;
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.c).inflate(R.layout.goods_list_item, null);
            aVar2.a = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.goods_img_left);
            aVar2.b = (LinearLayout) view.findViewById(R.id.ll_goods_img_left);
            aVar2.c = (LinearLayout) view.findViewById(R.id.ll_goods_item_left);
            aVar2.d = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.goods_img_right);
            aVar2.e = (LinearLayout) view.findViewById(R.id.ll_goods_img_right);
            aVar2.f = (LinearLayout) view.findViewById(R.id.ll_goods_item_right);
            aVar2.g = (LinearLayout) view.findViewById(R.id.ll_both_item);
            aVar2.h = view.findViewById(R.id.goods_item_top);
            aVar2.i = (ECJiaAutoReturnView) view.findViewById(R.id.goodlist_goodname_left);
            aVar2.j = (ECJiaAutoReturnView) view.findViewById(R.id.goodlist_goodname_right);
            aVar2.k = (TextView) view.findViewById(R.id.goodlist_origin_price_left);
            aVar2.l = (TextView) view.findViewById(R.id.goodlist_origin_price_right);
            aVar2.m = (TextView) view.findViewById(R.id.goodlist_shop_price_left);
            aVar2.n = (TextView) view.findViewById(R.id.goodlist_shop_price_right);
            aVar2.o = (TextView) view.findViewById(R.id.tv_saving_left);
            aVar2.p = (TextView) view.findViewById(R.id.tv_saving_right);
            aVar2.q = (LinearLayout) view.findViewById(R.id.ll_goodlist_mb_left);
            aVar2.r = (LinearLayout) view.findViewById(R.id.ll_goodlist_mb_right);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.h.setVisibility(0);
        } else {
            aVar.h.setVisibility(8);
        }
        LayoutParams layoutParams = aVar.b.getLayoutParams();
        layoutParams.width = (a() - this.a) / 2;
        layoutParams.height = layoutParams.width;
        aVar.b.setLayoutParams(layoutParams);
        aVar.e.setLayoutParams(layoutParams);
        if (i * 2 < this.b.size()) {
            auVar = (au) this.b.get(i * 2);
        } else {
            auVar = null;
        }
        if ((i * 2) + 1 < this.b.size()) {
            auVar2 = (au) this.b.get((i * 2) + 1);
        } else {
            auVar2 = null;
        }
        if (auVar == null) {
            aVar.g.setVisibility(8);
        } else {
            aVar.g.setVisibility(0);
            p.a(this.c).a(aVar.a, auVar.i().getThumb());
            if (k.a(auVar.d()) != 0.0f) {
                aVar.m.setText(auVar.d());
                aVar.k.setText(auVar.e());
            } else if (k.a(auVar.e()) == 0.0f) {
                aVar.m.setText("免费");
                aVar.k.setText("");
            } else {
                aVar.m.setText(auVar.e());
                aVar.k.setText(auVar.f());
            }
            if ("MOBILEBUY_GOODS".equals(auVar.a())) {
                aVar.q.setVisibility(0);
                aVar.o.setText(auVar.b());
            } else {
                aVar.q.setVisibility(8);
            }
            aVar.i.setContent(auVar.g());
            aVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ s b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.c, ECJiaGoodsDetailActivity.class);
                    int h = auVar.h();
                    if (h == 0) {
                        h = auVar.c();
                    }
                    intent.putExtra("goods_id", h + "");
                    this.b.c.startActivity(intent);
                    ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
            if (auVar2 == null) {
                aVar.f.setVisibility(4);
            } else {
                aVar.f.setVisibility(0);
                p.a(this.c).a(aVar.d, auVar2.i().getThumb());
                if (k.a(auVar2.d()) != 0.0f) {
                    aVar.n.setText(auVar2.d());
                    aVar.l.setText(auVar2.e());
                } else if (k.a(auVar2.e()) == 0.0f) {
                    aVar.n.setText("免费");
                    aVar.l.setText("");
                } else {
                    aVar.n.setText(auVar2.e());
                    aVar.l.setText(auVar2.f());
                }
                if ("MOBILEBUY_GOODS".equals(auVar2.a())) {
                    aVar.r.setVisibility(0);
                    aVar.p.setText(auVar2.b());
                } else {
                    aVar.r.setVisibility(8);
                }
                aVar.j.setContent(auVar2.g());
                aVar.f.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ s b;

                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(this.b.c, ECJiaGoodsDetailActivity.class);
                        int h = auVar2.h();
                        if (h == 0) {
                            h = auVar2.c();
                        }
                        intent.putExtra("goods_id", h + "");
                        this.b.c.startActivity(intent);
                        ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    }
                });
            }
        }
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.c).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.c).getWindowManager().getDefaultDisplay().getHeight());
    }
}
