package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaOrderGoodsHoriLVAdapter */
public class aq extends BaseAdapter {
    public ArrayList<ECJia_ORDER_GOODS_LIST> a;
    protected ImageLoader b = ImageLoader.getInstance();
    private LayoutInflater c;
    private Context d;

    /* compiled from: ECJiaOrderGoodsHoriLVAdapter */
    private static class a {
        private ImageView a;
        private LinearLayout b;

        private a() {
        }
    }

    public aq(Context context, ArrayList<ECJia_ORDER_GOODS_LIST> arrayList) {
        this.c = LayoutInflater.from(context);
        this.d = context;
        this.a = arrayList;
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

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = this.c.inflate(R.layout.goods_hori_item, null);
            aVar.a = (ImageView) view.findViewById(R.id.iv_new_goods);
            aVar.b = (LinearLayout) view.findViewById(R.id.ll_new_goods);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        this.b.displayImage(((ECJia_ORDER_GOODS_LIST) this.a.get(i)).getImg().getThumb(), aVar.a);
        return view;
    }
}
