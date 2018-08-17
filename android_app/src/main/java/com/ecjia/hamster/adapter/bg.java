package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.ac;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaSechillListViewAdapter */
public class bg extends BaseAdapter {
    public ArrayList<ac> a;
    public String b;
    private Context c;
    private b d = null;

    /* compiled from: ECJiaSechillListViewAdapter */
    class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        LinearLayout g;
        ImageView h;
        ImageView i;
        ECJiaAutoReturnView j;
        ProgressBar k;
        final /* synthetic */ bg l;

        a(bg bgVar) {
            this.l = bgVar;
        }
    }

    /* compiled from: ECJiaSechillListViewAdapter */
    public interface b {
    }

    public bg(Context context, ArrayList<ac> arrayList, String str) {
        this.c = context;
        this.a = arrayList;
        this.b = str;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        Object replace;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.c).inflate(R.layout.sechill_list_item, null);
            aVar.a = (TextView) view.findViewById(R.id.sechill_volume);
            aVar.b = (TextView) view.findViewById(R.id.sechill_shop_price);
            aVar.c = (TextView) view.findViewById(R.id.sechill_promote_price);
            aVar.d = (TextView) view.findViewById(R.id.seckill_immediately);
            aVar.h = (ImageView) view.findViewById(R.id.sechill_img);
            aVar.j = (ECJiaAutoReturnView) view.findViewById(R.id.sechill_goodname);
            aVar.i = (ImageView) view.findViewById(R.id.sechill_circular);
            aVar.e = (TextView) view.findViewById(R.id.sechill_soldout);
            aVar.g = (LinearLayout) view.findViewById(R.id.goodlist_item);
            aVar.k = (ProgressBar) view.findViewById(R.id.seckill_bar);
            aVar.f = (TextView) view.findViewById(R.id.seckill_count);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (((ac) this.a.get(i)).f().equals("0")) {
            aVar.d.setText("已售完");
            aVar.d.setTextColor(com.ecjia.consts.b.a(this.c).getColor(R.color.white));
            aVar.d.setBackgroundResource(R.drawable.selector_login_button_two);
            aVar.i.setVisibility(0);
            aVar.e.setVisibility(0);
        } else {
            if (this.b.equals("进行中")) {
                aVar.d.setText("立即秒杀");
            } else {
                aVar.d.setText("即将开始");
            }
            aVar.d.setTextColor(com.ecjia.consts.b.a(this.c).getColor(R.color.white));
            aVar.d.setBackgroundResource(R.drawable.selector_login_button);
            aVar.i.setVisibility(8);
            aVar.e.setVisibility(8);
        }
        aVar.a.setText("销量" + ((ac) this.a.get(i)).i() + "件");
        p.a(this.c).a(aVar.h, ((ac) this.a.get(i)).b().getUrl());
        aVar.j.setContent(((ac) this.a.get(i)).g());
        aVar.b.setText(((ac) this.a.get(i)).d());
        float parseFloat = Float.parseFloat(((ac) this.a.get(i)).i()) / Float.parseFloat(((ac) this.a.get(i)).f());
        q.a("parseFloat==" + parseFloat);
        int intValue = new Float(Math.rint((double) (parseFloat * 100.0f))).intValue();
        q.a("parseFloat==" + intValue);
        aVar.k.setProgress(intValue);
        aVar.f.setText("已售" + intValue + "%");
        if (((ac) this.a.get(i)).e() != null) {
            replace = ((ac) this.a.get(i)).e().replace("￥", "").replace("元", "").replace("yuan", "").replace("¥", "");
        } else {
            replace = null;
        }
        if ("免费".equals(replace) || "0.00".equals(replace) || replace == null || "".equals(replace) || "0".equals(replace)) {
            aVar.c.setVisibility(4);
        } else {
            aVar.c.setVisibility(0);
            aVar.c.setText(((ac) this.a.get(i)).e());
        }
        aVar.c.getPaint().setAntiAlias(true);
        aVar.c.getPaint().setFlags(17);
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bg c;

            public void onClick(View view) {
                if (!((ac) this.c.a.get(i)).f().equals("0")) {
                    Intent intent;
                    if (this.c.b.equals("进行中")) {
                        intent = new Intent(this.c.c, ECJiaGoodsDetailActivity.class);
                        intent.putExtra("goods_id", ((ac) this.c.a.get(i)).c() + "");
                        intent.putExtra("object_id", ((ac) this.c.a.get(i)).h());
                        intent.putExtra("seckill", aVar.d.getText().toString());
                        intent.putExtra("rec_type", "SPIKE_GOODS");
                        this.c.c.startActivity(intent);
                        ((Activity) this.c.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        q.b("type1===SPIKE_GOODS");
                        return;
                    }
                    intent = new Intent(this.c.c, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((ac) this.c.a.get(i)).c() + "");
                    this.c.c.startActivity(intent);
                    ((Activity) this.c.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    q.b("type1===GENERAL_GOODS");
                }
            }
        });
        aVar.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bg c;

            public void onClick(View view) {
                if (!((ac) this.c.a.get(i)).f().equals("0")) {
                    Intent intent;
                    if (this.c.b.equals("进行中")) {
                        intent = new Intent(this.c.c, ECJiaGoodsDetailActivity.class);
                        intent.putExtra("goods_id", ((ac) this.c.a.get(i)).c() + "");
                        intent.putExtra("object_id", ((ac) this.c.a.get(i)).h());
                        intent.putExtra("seckill", aVar.d.getText().toString());
                        intent.putExtra("rec_type", "SPIKE_GOODS");
                        this.c.c.startActivity(intent);
                        ((Activity) this.c.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        q.b("type2===SPIKE_GOODS");
                        return;
                    }
                    intent = new Intent(this.c.c, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((ac) this.c.a.get(i)).c() + "");
                    this.c.c.startActivity(intent);
                    ((Activity) this.c.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    q.b("type2===GENERAL_GOODS");
                }
            }
        });
        return view;
    }
}
