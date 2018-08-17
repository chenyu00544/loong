package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.s;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaHomeGroupGoodsAdapter */
public class w extends Adapter<a> {
    private Context a;
    private ArrayList<s> b;
    private p c;

    /* compiled from: ECJiaHomeGroupGoodsAdapter */
    public class a extends ViewHolder {
        final /* synthetic */ w a;
        private LinearLayout b;
        private View c;
        private ECJiaSelectableRoundedImageView d;
        private TextView e;
        private TextView f;
        private TextView g;

        public a(w wVar, View view) {
            this.a = wVar;
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.groupgood_item);
            this.d = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.img_groupgood);
            this.f = (TextView) view.findViewById(R.id.homefragment_hot_des);
            this.g = (TextView) view.findViewById(R.id.group_good_name);
            this.e = (TextView) view.findViewById(R.id.goods_promoteprice);
            this.c = view.findViewById(R.id.right_empty);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public w(Context context, ArrayList<s> arrayList) {
        this.a = context;
        this.b = arrayList;
        this.c = p.a(context);
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_groupgood_item, viewGroup, false));
    }

    public void a(a aVar, final int i) {
        s sVar = (s) this.b.get(i);
        this.c.a(aVar.d, sVar.l().getThumb());
        aVar.g.setText(sVar.h());
        aVar.e.setText(sVar.k());
        aVar.f.setText(sVar.b());
        if (i == this.b.size() - 1 || this.b.size() == 1) {
            aVar.c.setVisibility(0);
        } else {
            aVar.c.setVisibility(8);
        }
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ w b;

            public void onClick(View view) {
                q.a("===" + ((s) this.b.b.get(i)).e());
                q.a("===" + ((s) this.b.b.get(i)).f());
                Intent intent = new Intent(this.b.a, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", ((s) this.b.b.get(i)).g() + "");
                intent.putExtra("object_id", ((s) this.b.b.get(i)).e());
                intent.putExtra("rec_type", ((s) this.b.b.get(i)).f());
                this.b.a.startActivity(intent);
                ((Activity) this.b.a).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.b.size();
    }
}
