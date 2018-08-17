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
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaOrderdetailActivityAdapter */
public class ar extends BaseAdapter {
    public ArrayList<ECJia_ORDER_GOODS_LIST> a;
    ArrayList<ECJia_ORDER_GOODS_LIST> b;
    public String c;
    protected ImageLoader d = ImageLoader.getInstance();
    private Context e;
    private LayoutInflater f;

    /* compiled from: ECJiaOrderdetailActivityAdapter */
    class a {
        final /* synthetic */ ar a;
        private View b;
        private View c;
        private ImageView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private LinearLayout h;

        a(ar arVar) {
            this.a = arVar;
        }
    }

    public ar(Context context, ArrayList<ECJia_ORDER_GOODS_LIST> arrayList, String str) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() > 1) {
            for (int i = 0; i < 1; i++) {
                arrayList2.add(arrayList.get(i));
            }
        } else {
            arrayList2 = arrayList;
        }
        this.e = context;
        this.a = arrayList2;
        this.b = arrayList;
        this.c = str;
        this.f = LayoutInflater.from(context);
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
        if (view == null) {
            view = this.f.inflate(R.layout.orderdetail_item, null);
            aVar = new a(this);
            aVar.h = (LinearLayout) view.findViewById(R.id.order_item);
            aVar.b = view.findViewById(R.id.trade_item_top);
            aVar.b.setVisibility(8);
            aVar.c = view.findViewById(R.id.trade_item_buttom);
            aVar.d = (ImageView) view.findViewById(R.id.trade_body_image);
            aVar.e = (TextView) view.findViewById(R.id.trade_body_text);
            aVar.f = (TextView) view.findViewById(R.id.trade_body_total);
            aVar.g = (TextView) view.findViewById(R.id.trade_body_num);
        } else {
            aVar = (a) view.getTag();
        }
        this.d.displayImage(((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getImg().getThumb(), aVar.d);
        aVar.e.setText(((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getName());
        aVar.f.setText(((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getFormated_shop_price());
        if (0.0f == k.a(((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getFormated_shop_price())) {
            aVar.f.setText("免费");
        } else {
            aVar.f.setText(((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getFormated_shop_price());
        }
        aVar.g.setText("X " + ((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getGoods_number());
        if (i == this.a.size() - 1) {
            aVar.c.setVisibility(8);
        }
        aVar.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ar b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.e, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", ((ECJia_ORDER_GOODS_LIST) this.b.a.get(i)).getGoods_id());
                intent.putExtra("rec_type", ((ECJia_ORDER_GOODS_LIST) this.b.a.get(i)).getActivity_type());
                this.b.e.startActivity(intent);
                ((Activity) this.b.e).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        return view;
    }
}
