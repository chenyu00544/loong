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
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaShopgoodsAdapter */
public class bo extends BaseAdapter {
    protected ImageLoader a = ImageLoader.getInstance();
    boolean b;
    private Context c;
    private ArrayList<ECJia_GOODS_LIST> d;

    /* compiled from: ECJiaShopgoodsAdapter */
    class a {
        final /* synthetic */ bo a;
        private View b;
        private View c;
        private ImageView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private LinearLayout h;
        private LinearLayout i;

        a(bo boVar) {
            this.a = boVar;
        }
    }

    public bo(Context context, ArrayList<ECJia_GOODS_LIST> arrayList, boolean z) {
        this.c = context;
        this.d = arrayList;
        this.b = z;
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.shopgoods_item, null);
            aVar = new a(this);
            aVar.h = (LinearLayout) view.findViewById(R.id.shopgoods_item);
            aVar.b = view.findViewById(R.id.shopgoods_item_top);
            aVar.c = view.findViewById(R.id.shopgoods_item_buttom);
            aVar.d = (ImageView) view.findViewById(R.id.shopgoods_body_image);
            aVar.e = (TextView) view.findViewById(R.id.shopgoods_body_text);
            aVar.f = (TextView) view.findViewById(R.id.shopgoods_body_total);
            aVar.g = (TextView) view.findViewById(R.id.shopgoods_body_num);
            aVar.i = (LinearLayout) view.findViewById(R.id.shopgoods_item);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.b.setVisibility(0);
        }
        this.a.displayImage(((ECJia_GOODS_LIST) this.d.get(i)).getImg().getThumb(), aVar.d);
        aVar.e.setText(((ECJia_GOODS_LIST) this.d.get(i)).getGoods_name());
        aVar.f.setText(((ECJia_GOODS_LIST) this.d.get(i)).getFormated_goods_price());
        aVar.g.setText("x " + ((ECJia_GOODS_LIST) this.d.get(i)).getGoods_number());
        aVar.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bo b;

            public void onClick(View view) {
                if (this.b.b) {
                    Intent intent = new Intent(this.b.c, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((ECJia_GOODS_LIST) this.b.d.get(i)).getGoods_id() + "");
                    this.b.c.startActivity(intent);
                    ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            }
        });
        return view;
    }
}
