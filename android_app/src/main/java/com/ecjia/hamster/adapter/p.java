package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaGoodbrowseAdapter */
public class p extends BaseAdapter {
    public List<com.ecjia.hamster.model.p> a;
    private Context b;
    private LayoutInflater c;
    private Bitmap d = null;

    /* compiled from: ECJiaGoodbrowseAdapter */
    class a {
        public View a;
        public View b;
        final /* synthetic */ p c;
        private ImageView d;
        private ECJiaAutoReturnView e;
        private TextView f;
        private TextView g;
        private TextView h;
        private View i;
        private LinearLayout j;
        private LinearLayout k;

        a(p pVar) {
            this.c = pVar;
        }
    }

    public p(Context context, List<com.ecjia.hamster.model.p> list) {
        this.b = context;
        this.a = list;
        this.c = LayoutInflater.from(context);
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public int getCount() {
        return this.a.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.lastbrowse_item, null);
            aVar.d = (ImageView) view.findViewById(R.id.goodlist_img);
            aVar.e = (ECJiaAutoReturnView) view.findViewById(R.id.goodlist_goodname);
            aVar.f = (TextView) view.findViewById(R.id.goodlist_shop_price);
            aVar.g = (TextView) view.findViewById(R.id.goodlist_promote_price);
            aVar.j = (LinearLayout) view.findViewById(R.id.goodlist_item);
            aVar.h = (TextView) view.findViewById(R.id.tv_saving);
            aVar.k = (LinearLayout) view.findViewById(R.id.ll_goodlist_mb);
            aVar.g.getPaint().setAntiAlias(true);
            aVar.g.getPaint().setFlags(17);
            aVar.i = view.findViewById(R.id.goodlist_top_line);
            aVar.a = view.findViewById(R.id.goodlist_buttom_margin_line);
            aVar.b = view.findViewById(R.id.goodlist_buttom_line);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.e.setContent(((com.ecjia.hamster.model.p) this.a.get(i)).p());
        if (k.a(((com.ecjia.hamster.model.p) this.a.get(i)).s()) != 0.0f) {
            aVar.f.setText(((com.ecjia.hamster.model.p) this.a.get(i)).s());
            aVar.g.setText(((com.ecjia.hamster.model.p) this.a.get(i)).k());
        } else if (k.a(((com.ecjia.hamster.model.p) this.a.get(i)).k()) == 0.0f) {
            aVar.f.setText("免费");
            aVar.g.setText("");
        } else {
            aVar.f.setText(((com.ecjia.hamster.model.p) this.a.get(i)).k());
            aVar.g.setText(((com.ecjia.hamster.model.p) this.a.get(i)).v());
        }
        if ("MOBILEBUY_GOODS".equals(((com.ecjia.hamster.model.p) this.a.get(i)).h())) {
            aVar.k.setVisibility(0);
            aVar.h.setText(((com.ecjia.hamster.model.p) this.a.get(i)).i());
        } else {
            aVar.k.setVisibility(8);
        }
        if (i == 0) {
            aVar.i.setVisibility(0);
        } else {
            aVar.i.setVisibility(8);
        }
        if (i == this.a.size() - 1) {
            aVar.a.setVisibility(8);
            aVar.b.setVisibility(0);
        } else {
            aVar.a.setVisibility(0);
            aVar.b.setVisibility(8);
        }
        aVar.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ p b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.b, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", ((com.ecjia.hamster.model.p) this.b.a.get(i)).t() + "");
                this.b.b.startActivity(intent);
                ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        com.ecjia.a.p.a(this.b).a(aVar.d, ((com.ecjia.hamster.model.p) this.a.get(i)).n().getThumb());
        return view;
    }
}
