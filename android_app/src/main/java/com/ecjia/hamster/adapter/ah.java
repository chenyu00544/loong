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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.y;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaMobileBuyGridAdapter */
public class ah extends BaseAdapter {
    Resources a;
    private final int b;
    private Context c;
    private ArrayList<y> d;
    private p e;

    /* compiled from: ECJiaMobileBuyGridAdapter */
    private class a {
        public LinearLayout a;
        public FrameLayout b;
        public ECJiaSelectableRoundedImageView c;
        public TextView d;
        public TextView e;
        public TextView f;
        public TextView g;
        final /* synthetic */ ah h;

        private a(ah ahVar) {
            this.h = ahVar;
        }
    }

    public ah(Context context) {
        this.c = context;
        this.a = context.getResources();
        this.e = p.a(context);
        this.b = (int) context.getResources().getDimension(R.dimen.mobilebuy_dp);
    }

    public void a(ArrayList<y> arrayList) {
        this.d = arrayList;
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = LayoutInflater.from(this.c).inflate(R.layout.layout_homefragment_mb, null);
            aVar.a = (LinearLayout) view.findViewById(R.id.homefragment_mb_item);
            aVar.b = (FrameLayout) view.findViewById(R.id.fl_mb);
            aVar.c = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.homefragment_mb_img);
            aVar.d = (TextView) view.findViewById(R.id.homefragment_mb_des);
            aVar.e = (TextView) view.findViewById(R.id.homefragment_mb_price);
            aVar.f = (TextView) view.findViewById(R.id.homefragment_mb_marketprice);
            aVar.g = (TextView) view.findViewById(R.id.homefragment_mb_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        LayoutParams layoutParams = aVar.b.getLayoutParams();
        layoutParams.width = (int) ((((double) (a() - this.b)) * WeightedLatLng.DEFAULT_INTENSITY) / 2.0d);
        layoutParams.height = layoutParams.width;
        aVar.b.setLayoutParams(layoutParams);
        aVar.f.setVisibility(8);
        this.e.a(aVar.c, ((y) this.d.get(i)).h().getThumb());
        aVar.e.setText(((y) this.d.get(i)).g());
        aVar.f.setText(((y) this.d.get(i)).e());
        aVar.f.getPaint().setAntiAlias(true);
        aVar.f.getPaint().setFlags(17);
        aVar.g.setText(((y) this.d.get(i)).d());
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ah b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", ((y) this.b.d.get(i)).c() + "");
                this.b.c.startActivity(intent);
                ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.c).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.c).getWindowManager().getDefaultDisplay().getHeight());
    }
}
