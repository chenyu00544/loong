package com.ecjia.hamster.module.goodsReturn.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.module.goodsReturn.activity.ECJiaReturnDetailActivity;
import com.ecjia.hamster.module.goodsReturn.activity.ECJiaReturnFeeDetailActivity;
import com.ecjia.hamster.module.goodsReturn.activity.ECJiaReturnProcessingActivity;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaReturnOrderListAdapter */
public class c extends BaseAdapter {
    public ArrayList<com.ecjia.hamster.module.goodsReturn.model.a> a;
    com.ecjia.hamster.b.a b;
    private Context c;
    private LayoutInflater d;
    private Resources e;

    /* compiled from: ECJiaReturnOrderListAdapter */
    static class a {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        TextView i;
        TextView j;
        LinearLayout k;
        LinearLayout l;
        View m;

        a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public c(Context context, ArrayList<com.ecjia.hamster.module.goodsReturn.model.a> arrayList) {
        this.c = context;
        this.a = arrayList;
        this.d = LayoutInflater.from(context);
        this.e = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public com.ecjia.hamster.module.goodsReturn.model.a a(int i) {
        return (com.ecjia.hamster.module.goodsReturn.model.a) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        final com.ecjia.hamster.module.goodsReturn.model.a aVar2 = (com.ecjia.hamster.module.goodsReturn.model.a) this.a.get(i);
        if (view == null) {
            view = this.d.inflate(R.layout.item_back_change, null);
            a aVar3 = new a();
            aVar3.a = (TextView) view.findViewById(R.id.return_sn);
            aVar3.b = (TextView) view.findViewById(R.id.return_status);
            aVar3.k = (LinearLayout) view.findViewById(R.id.return_item_ll);
            aVar3.d = (TextView) view.findViewById(R.id.seller_name);
            aVar3.c = (ImageView) view.findViewById(R.id.goods_image);
            aVar3.e = (TextView) view.findViewById(R.id.goods_name);
            aVar3.f = (TextView) view.findViewById(R.id.apply_time);
            aVar3.g = (TextView) view.findViewById(R.id.return_fee_detail);
            aVar3.h = (TextView) view.findViewById(R.id.return_detail);
            aVar3.i = (TextView) view.findViewById(R.id.cancel);
            aVar3.j = (TextView) view.findViewById(R.id.process);
            aVar3.l = (LinearLayout) view.findViewById(R.id.seller_ll);
            aVar3.m = view.findViewById(R.id.right_arrow);
            view.setTag(aVar3);
            aVar = aVar3;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(aVar2.d());
        aVar.b.setText(aVar2.h());
        aVar.d.setText(aVar2.b());
        ImageLoader.getInstance().displayImage(aVar2.i().getUrl(), aVar.c);
        aVar.e.setText(aVar2.e());
        aVar.f.setText(aVar2.f());
        if (TextUtils.isEmpty(aVar2.a()) || "0".equals(aVar2.a())) {
            aVar.m.setVisibility(4);
        } else {
            aVar.m.setVisibility(0);
        }
        aVar.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                if (!TextUtils.isEmpty(aVar2.a()) && !"0".equals(aVar2.a())) {
                    Intent intent = new Intent(this.b.c, ECJiaShopListActivity.class);
                    intent.putExtra("merchant_id", aVar2.a());
                    this.b.c.startActivity(intent);
                }
            }
        });
        aVar.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaReturnDetailActivity.class);
                intent.putExtra("return_id", aVar2.c());
                this.b.c.startActivity(intent);
            }
        });
        String g = aVar2.g();
        int i2 = -1;
        switch (g.hashCode()) {
            case -1367724422:
                if (g.equals("cancel")) {
                    i2 = 7;
                    break;
                }
                break;
            case -973954362:
                if (g.equals("wait_ship")) {
                    i2 = 5;
                    break;
                }
                break;
            case -707924457:
                if (g.equals("refunded")) {
                    i2 = 4;
                    break;
                }
                break;
            case -673660814:
                if (g.equals("finished")) {
                    i2 = 6;
                    break;
                }
                break;
            case -646311515:
                if (g.equals("wait_process")) {
                    i2 = 2;
                    break;
                }
                break;
            case -142594626:
                if (g.equals("wait_check")) {
                    i2 = 0;
                    break;
                }
                break;
            case 422194963:
                if (g.equals("processing")) {
                    i2 = 3;
                    break;
                }
                break;
            case 547191841:
                if (g.equals("wait_return_ship")) {
                    i2 = 1;
                    break;
                }
                break;
        }
        switch (i2) {
            case 0:
                aVar.g.setVisibility(8);
                aVar.h.setVisibility(8);
                aVar.i.setVisibility(8);
                aVar.j.setVisibility(0);
                break;
            case 1:
            case 2:
            case 3:
                aVar.g.setVisibility(8);
                aVar.h.setVisibility(8);
                aVar.i.setVisibility(0);
                aVar.j.setVisibility(0);
                break;
            case 4:
                aVar.g.setVisibility(0);
                aVar.h.setVisibility(8);
                aVar.i.setVisibility(8);
                aVar.j.setVisibility(0);
                break;
            default:
                aVar.g.setVisibility(8);
                aVar.h.setVisibility(0);
                aVar.i.setVisibility(8);
                aVar.j.setVisibility(8);
                break;
        }
        aVar.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaReturnFeeDetailActivity.class);
                intent.putExtra("return_id", aVar2.c());
                this.b.c.startActivity(intent);
            }
        });
        aVar.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaReturnDetailActivity.class);
                intent.putExtra("return_id", aVar2.c());
                this.b.c.startActivity(intent);
            }
        });
        aVar.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaReturnProcessingActivity.class);
                intent.putExtra("return_id", aVar2.c());
                this.b.c.startActivity(intent);
            }
        });
        aVar.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c c;

            public void onClick(View view) {
                if (this.c.b != null) {
                    this.c.b.a(0, i, aVar2.c());
                }
            }
        });
        return view;
    }

    public void a(com.ecjia.hamster.b.a aVar) {
        this.b = aVar;
    }
}
