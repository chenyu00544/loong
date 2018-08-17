package com.ecjia.hamster.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.an;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaShopCollectHorizLVAdapter */
public class bj extends BaseAdapter {
    public ArrayList<an> a;
    protected ImageLoader b = ImageLoader.getInstance();
    private LayoutInflater c;
    private Context d;

    /* compiled from: ECJiaShopCollectHorizLVAdapter */
    private static class a {
        private TextView a;
        private ImageView b;
        private ImageView c;
        private LinearLayout d;
        private RelativeLayout e;

        private a() {
        }
    }

    public bj(Context context, ArrayList<an> arrayList) {
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
            view = this.c.inflate(R.layout.goods_collecthoriz_item, null);
            aVar.b = (ImageView) view.findViewById(R.id.iv_new_goods);
            aVar.c = (ImageView) view.findViewById(R.id.iv_mb);
            aVar.a = (TextView) view.findViewById(R.id.new_goods_price);
            aVar.d = (LinearLayout) view.findViewById(R.id.ll_new_goods);
            aVar.e = (RelativeLayout) view.findViewById(R.id.rl_goods_more);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        CharSequence c = ((an) this.a.get(i)).c();
        if (TextUtils.isEmpty(c) || !"+1".equals(c)) {
            aVar.b.setVisibility(0);
            aVar.e.setVisibility(8);
        } else {
            aVar.b.setVisibility(8);
            aVar.e.setVisibility(0);
        }
        if ("MOBILEBUY_GOODS".equals(((an) this.a.get(i)).a())) {
            aVar.c.setVisibility(0);
        } else {
            aVar.c.setVisibility(8);
        }
        aVar.a.setText(((an) this.a.get(i)).f());
        this.b.displayImage(((an) this.a.get(i)).g().getThumb(), aVar.b);
        return view;
    }
}
