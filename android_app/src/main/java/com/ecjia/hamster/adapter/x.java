package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/* compiled from: ECJiaHomeNewGoodsAdapter */
public class x extends Adapter<a> {
    public List<au> a;
    protected ImageLoader b = ImageLoader.getInstance();
    private LayoutInflater c;
    private Context d;

    /* compiled from: ECJiaHomeNewGoodsAdapter */
    public static class a extends ViewHolder {
        private View a;
        private TextView b;
        private TextView c;
        private ECJiaSelectableRoundedImageView d;
        private View e;

        public a(View view) {
            super(view);
            this.a = view.findViewById(R.id.new_goods_putaway_ll);
            this.d = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.new_goods_putaway_photo);
            this.b = (TextView) view.findViewById(R.id.new_goods_putaway_name);
            this.c = (TextView) view.findViewById(R.id.new_goods_putaway_price);
            this.e = view.findViewById(R.id.right_empty);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public x(Context context, List<au> list) {
        this.c = LayoutInflater.from(context);
        this.d = context;
        this.a = list;
    }

    public a a(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_goods_putaway_item, viewGroup, false));
    }

    public void a(a aVar, final int i) {
        aVar.b.setText(((au) this.a.get(i)).g());
        if (TextUtils.isEmpty(((au) this.a.get(i)).d())) {
            aVar.c.setText(((au) this.a.get(i)).e());
        } else {
            aVar.c.setText(((au) this.a.get(i)).d());
        }
        this.b.displayImage(((au) this.a.get(i)).i().getThumb(), aVar.d);
        if (this.a.size() == 1 || i == this.a.size() - 1) {
            aVar.e.setVisibility(0);
        } else {
            aVar.e.setVisibility(8);
        }
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ x b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.d, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", ((au) this.b.a.get(i)).h() + "");
                this.b.d.startActivity(intent);
                ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.a.size();
    }
}
