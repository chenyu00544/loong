package com.ecjia.hamster.module.goodsReturn.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/* compiled from: ECJiaReturnGoodsAdapter */
public class b extends BaseAdapter {
    private Context a;
    private List<ECJia_ORDER_GOODS_LIST> b;
    private com.ecjia.hamster.b.a c;

    /* compiled from: ECJiaReturnGoodsAdapter */
    class a {
        final /* synthetic */ b a;
        private View b;
        private ImageView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private LinearLayout g;
        private LinearLayout h;

        a(b bVar) {
            this.a = bVar;
        }
    }

    public b(Context context, List<ECJia_ORDER_GOODS_LIST> list) {
        this.a = context;
        this.b = list;
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.item_return_goods_list, null);
            aVar = new a(this);
            aVar.g = (LinearLayout) view.findViewById(R.id.shopgoods_item);
            aVar.b = view.findViewById(R.id.shopgoods_item_top);
            aVar.c = (ImageView) view.findViewById(R.id.shopgoods_body_image);
            aVar.d = (TextView) view.findViewById(R.id.shopgoods_body_text);
            aVar.e = (TextView) view.findViewById(R.id.shopgoods_body_total);
            aVar.f = (TextView) view.findViewById(R.id.shopgoods_body_num);
            aVar.h = (LinearLayout) view.findViewById(R.id.shopgoods_item);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setVisibility(0);
        ImageLoader.getInstance().displayImage(((ECJia_ORDER_GOODS_LIST) this.b.get(i)).getImg().getThumb(), aVar.c);
        aVar.d.setText(((ECJia_ORDER_GOODS_LIST) this.b.get(i)).getName());
        aVar.f.setText("x " + ((ECJia_ORDER_GOODS_LIST) this.b.get(i)).getGoods_number());
        if (((ECJia_ORDER_GOODS_LIST) this.b.get(i)).getAllow_return() == 1) {
            aVar.e.setBackgroundResource(R.drawable.shape_black_stroke_white_bg_corner);
            aVar.e.setTextColor(this.a.getResources().getColor(R.color.my_black));
        } else {
            aVar.e.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
            aVar.e.setTextColor(this.a.getResources().getColor(R.color.line_long_dark));
        }
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                if (((ECJia_ORDER_GOODS_LIST) this.b.b.get(i)).getAllow_return() == 1 && this.b.c != null) {
                    this.b.c.a(0, i, this.b.b.get(i));
                }
            }
        });
        return view;
    }

    public void a(com.ecjia.hamster.b.a aVar) {
        this.c = aVar;
    }
}
