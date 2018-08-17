package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.hamster.activity.ECJiaCommentCreateActivity;
import com.ecjia.hamster.model.ECJia_ORDER_COMMENTS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaOrderDetailCommentAdapter */
public class ap extends BaseAdapter {
    public ArrayList<ECJia_ORDER_COMMENTS_LIST> a;
    private Resources b;
    private Context c;
    private LayoutInflater d;
    private int e;

    /* compiled from: ECJiaOrderDetailCommentAdapter */
    class a {
        final /* synthetic */ ap a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private View f;
        private View g;

        a(ap apVar) {
            this.a = apVar;
        }
    }

    public ap(Context context, ArrayList<ECJia_ORDER_COMMENTS_LIST> arrayList) {
        this.c = context;
        this.a = arrayList;
        this.d = LayoutInflater.from(context);
        this.b = context.getResources();
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object getItem(int i) {
        if (this.a == null) {
            return null;
        }
        return this.a.get(i);
    }

    public long getItemId(int i) {
        if (this.a == null) {
            return 0;
        }
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.d.inflate(R.layout.order_detail_comment, null);
            aVar.b = (ImageView) view.findViewById(R.id.comment_goods_img);
            aVar.c = (TextView) view.findViewById(R.id.comment_goods_title);
            aVar.d = (TextView) view.findViewById(R.id.comment_goods_price);
            aVar.e = (TextView) view.findViewById(R.id.comment_item_edit);
            aVar.f = view.findViewById(R.id.comment_item_below_long);
            aVar.g = view.findViewById(R.id.comment_item_below);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == this.a.size() - 1) {
            aVar.g.setVisibility(8);
            aVar.f.setVisibility(0);
        } else {
            aVar.g.setVisibility(0);
            aVar.f.setVisibility(8);
        }
        ImageLoader.getInstance().displayImage(((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).getImg().getThumb(), aVar.b);
        aVar.c.setText(((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).getGoods_name());
        if (k.a(((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).getGoods_price()) == 0.0f) {
            aVar.d.setText("免费");
        } else {
            aVar.d.setText(((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).getGoods_price());
        }
        aVar.e.setVisibility(0);
        if (((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).getIs_commented() == 0) {
            aVar.e.setText(this.b.getString(R.string.comment_create));
            this.e = 1;
        } else if (((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).getIs_showorder() == 0) {
            aVar.e.setText(this.b.getString(R.string.comment_create_showorder));
            this.e = 2;
        } else {
            aVar.e.setText(this.b.getString(R.string.see_comment));
            this.e = 0;
        }
        ((ECJia_ORDER_COMMENTS_LIST) this.a.get(i)).setType(this.e);
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ap b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaCommentCreateActivity.class);
                intent.putExtra("rec_id", ((ECJia_ORDER_COMMENTS_LIST) this.b.a.get(i)).getRec_id());
                intent.putExtra("goods_price", ((ECJia_ORDER_COMMENTS_LIST) this.b.a.get(i)).getGoods_price());
                intent.putExtra("goods_name", ((ECJia_ORDER_COMMENTS_LIST) this.b.a.get(i)).getGoods_name());
                intent.putExtra("type", ((ECJia_ORDER_COMMENTS_LIST) this.b.a.get(i)).getType());
                intent.putExtra("goods_img", ((ECJia_ORDER_COMMENTS_LIST) this.b.a.get(i)).getImg().getThumb());
                ((Activity) this.b.c).startActivityForResult(intent, 1);
            }
        });
        return view;
    }
}
