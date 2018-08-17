package com.ecjia.hamster.c.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaTradeGoodsAdapter */
public class b extends com.ecjia.hamster.adapter.b<ECJia_ORDER_GOODS_LIST> {
    private b a = null;

    /* compiled from: ECJiaTradeGoodsAdapter */
    public class a {
        ImageView a;
        TextView b;
        View c;
        View d;
        FrameLayout e;
        FrameLayout f;
        final /* synthetic */ b g;

        public a(b bVar) {
            this.g = bVar;
        }
    }

    /* compiled from: ECJiaTradeGoodsAdapter */
    public interface b {
    }

    public b(Context context, ArrayList<ECJia_ORDER_GOODS_LIST> arrayList) {
        super(context, arrayList);
    }

    protected com.ecjia.hamster.adapter.b.a a(View view) {
        return null;
    }

    protected View a(int i, View view, ViewGroup viewGroup, com.ecjia.hamster.adapter.b.a aVar) {
        return null;
    }

    public View a() {
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.d).inflate(R.layout.trade_goods_item, null);
            aVar.a = (ImageView) view.findViewById(R.id.iv_trade_goods);
            aVar.b = (TextView) view.findViewById(R.id.tv_trade_goods_num);
            aVar.c = view.findViewById(R.id.first_side);
            aVar.d = view.findViewById(R.id.last_side);
            aVar.e = (FrameLayout) view.findViewById(R.id.fl_trade_goods_num);
            aVar.f = (FrameLayout) view.findViewById(R.id.trade_goods_item);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        ECJia_ORDER_GOODS_LIST eCJia_ORDER_GOODS_LIST = (ECJia_ORDER_GOODS_LIST) this.e.get(i);
        if (i == 0) {
            aVar.c.setVisibility(0);
        } else {
            aVar.c.setVisibility(8);
        }
        if (i == this.e.size() - 1) {
            aVar.d.setVisibility(0);
        } else {
            aVar.d.setVisibility(8);
        }
        if (TextUtils.isEmpty(eCJia_ORDER_GOODS_LIST.getGoods_id())) {
            aVar.a.setVisibility(4);
        } else {
            aVar.a.setVisibility(0);
            ImageLoader.getInstance().displayImage(eCJia_ORDER_GOODS_LIST.getImg().getThumb(), aVar.a);
            if (k.f(eCJia_ORDER_GOODS_LIST.getGoods_number()) > 1) {
                aVar.e.setVisibility(0);
                CharSequence goods_number = eCJia_ORDER_GOODS_LIST.getGoods_number();
                if (k.f(goods_number) > 99) {
                    goods_number = "99+";
                }
                aVar.b.setText(goods_number);
            } else {
                aVar.e.setVisibility(8);
            }
        }
        return view;
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
