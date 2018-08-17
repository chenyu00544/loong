package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.model.an;
import com.ecjia.hamster.model.ao;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaShopSearchListAdapter */
public class bn extends BaseAdapter {
    public Handler a;
    private Resources b;
    private Context c;
    private ArrayList<ao> d;
    private p e;
    private int f = b();
    private int g;

    /* compiled from: ECJiaShopSearchListAdapter */
    private class a {
        final /* synthetic */ bn a;
        private ImageView b;
        private ImageView c;
        private ImageView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private ImageView h;
        private TextView i;
        private LinearLayout j;
        private TextView k;
        private TextView l;

        private a(bn bnVar) {
            this.a = bnVar;
        }
    }

    public bn(Context context, ArrayList<ao> arrayList) {
        this.c = context;
        this.d = arrayList;
        this.b = context.getResources();
        this.g = (int) this.b.getDimension(R.dimen.dp_5);
        this.e = p.a(context);
    }

    public ArrayList<ao> a() {
        return this.d;
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        return this.d.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        final ao aoVar = (ao) this.d.get(i);
        if (view == null) {
            aVar = new a();
            view = LayoutInflater.from(this.c).inflate(R.layout.shop_search_item, null);
            aVar.j = (LinearLayout) view.findViewById(R.id.shoplist_item);
            aVar.h = (ImageView) view.findViewById(R.id.seller_logo);
            aVar.k = (TextView) view.findViewById(R.id.seller_item_shopname);
            aVar.l = (TextView) view.findViewById(R.id.seller_item_shopinfo);
            aVar.i = (TextView) view.findViewById(R.id.tv_enter);
            aVar.e = (TextView) view.findViewById(R.id.tv_goods_price1);
            aVar.f = (TextView) view.findViewById(R.id.tv_goods_price2);
            aVar.g = (TextView) view.findViewById(R.id.tv_goods_price3);
            aVar.b = (ImageView) view.findViewById(R.id.goodimg_1);
            aVar.c = (ImageView) view.findViewById(R.id.goodimg_2);
            aVar.d = (ImageView) view.findViewById(R.id.goodimg_3);
            a(aVar.b);
            a(aVar.c);
            a(aVar.d);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (TextUtils.isEmpty(aoVar.i())) {
            aVar.h.setVisibility(4);
        } else {
            aVar.h.setVisibility(0);
            this.e.a(aVar.h, aoVar.i());
        }
        aVar.k.setText(aoVar.h());
        aVar.l.setText(aoVar.g() + this.b.getString(R.string.follower_num) + "  ");
        aVar.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bn b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaShopListActivity.class);
                intent.putExtra("merchant_id", aoVar.e());
                this.b.c.startActivity(intent);
                ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        int size = aoVar.f().size();
        if (size > 0) {
            aVar.b.setVisibility(0);
            this.e.a(aVar.b, ((an) aoVar.f().get(0)).g().getThumb());
            aVar.e.setVisibility(0);
            if (TextUtils.isEmpty(((an) aoVar.f().get(0)).h())) {
                aVar.e.setText(((an) aoVar.f().get(0)).f());
            } else {
                aVar.e.setText(((an) aoVar.f().get(0)).h());
            }
            aVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ bn b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.c, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(0)).c());
                    this.b.c.startActivity(intent);
                    ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.b.setVisibility(4);
            aVar.e.setVisibility(4);
        }
        if (size > 1) {
            aVar.c.setVisibility(0);
            this.e.a(aVar.c, ((an) aoVar.f().get(1)).g().getThumb());
            aVar.f.setVisibility(0);
            if (TextUtils.isEmpty(((an) aoVar.f().get(1)).h())) {
                aVar.f.setText(((an) aoVar.f().get(1)).f());
            } else {
                aVar.f.setText(((an) aoVar.f().get(1)).h());
            }
            aVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ bn b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.c, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(1)).c());
                    this.b.c.startActivity(intent);
                    ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.c.setVisibility(4);
            aVar.f.setVisibility(4);
        }
        if (size > 2) {
            aVar.d.setVisibility(0);
            this.e.a(aVar.d, ((an) aoVar.f().get(2)).g().getThumb());
            aVar.g.setVisibility(0);
            if (TextUtils.isEmpty(((an) aoVar.f().get(2)).h())) {
                aVar.g.setText(((an) aoVar.f().get(2)).f());
            } else {
                aVar.g.setText(((an) aoVar.f().get(2)).h());
            }
            aVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ bn b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.c, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(2)).c());
                    this.b.c.startActivity(intent);
                    ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.d.setVisibility(4);
            aVar.g.setVisibility(4);
        }
        return view;
    }

    void a(ImageView imageView) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams((this.f / 3) - (this.g * 2), (this.f / 3) - (this.g * 2));
        layoutParams.height = (this.f / 3) - (this.g * 2);
        imageView.setLayoutParams(layoutParams);
    }

    public int b() {
        return Math.min(((Activity) this.c).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.c).getWindowManager().getDefaultDisplay().getHeight());
    }
}
