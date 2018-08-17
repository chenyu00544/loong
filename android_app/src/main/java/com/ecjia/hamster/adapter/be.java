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
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.activity.ECJiaGoodsListActivity;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaSearchRightAdapter */
public class be extends BaseAdapter {
    public ArrayList<ECJia_CATEGORY> a;
    private Context b;
    private p c;
    private int d;

    /* compiled from: ECJiaSearchRightAdapter */
    private class a {
        final /* synthetic */ be a;
        private TextView b;
        private ImageView c;
        private LinearLayout d;

        private a(be beVar) {
            this.a = beVar;
        }
    }

    public be(Context context, ArrayList<ECJia_CATEGORY> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = p.a(context);
        this.d = (int) context.getResources().getDimension(R.dimen.seven_dp);
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
        final ECJia_CATEGORY eCJia_CATEGORY = (ECJia_CATEGORY) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.gridview_item, null);
            a aVar2 = new a();
            aVar2.b = (TextView) view.findViewById(R.id.tv_text);
            aVar2.c = (ImageView) view.findViewById(R.id.iv_img);
            aVar2.d = (LinearLayout) view.findViewById(R.id.ll_item);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(eCJia_CATEGORY.getName());
        aVar.c.setLayoutParams(new LayoutParams(((a() * 5) / 14) - this.d, ((a() * 5) / 14) - this.d));
        this.c.a(aVar.c, eCJia_CATEGORY.getImage());
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ be b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.b, ECJiaGoodsListActivity.class);
                intent.putExtra("category_id", eCJia_CATEGORY.getId() + "");
                intent.putExtra("search_content", eCJia_CATEGORY.getName());
                this.b.b.startActivity(intent);
                ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.b).getWindowManager().getDefaultDisplay().getHeight());
    }
}
