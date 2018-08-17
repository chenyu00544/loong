package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaCommentAdapter */
public class f extends BaseAdapter {
    public List<com.ecjia.hamster.activity.goodsdetail.a.a> a;
    private Context b;
    private LayoutInflater c;

    /* compiled from: ECJiaCommentAdapter */
    class a {
        final /* synthetic */ f a;
        private TextView b;
        private TextView c;
        private TextView d;
        private RatingBar e;
        private View f;

        a(f fVar) {
            this.a = fVar;
        }
    }

    public f(Context context, List<com.ecjia.hamster.activity.goodsdetail.a.a> list) {
        this.b = context;
        this.a = list;
        this.c = LayoutInflater.from(context);
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
            aVar = new a(this);
            view = this.c.inflate(R.layout.comment_item, null);
            aVar.e = (RatingBar) view.findViewById(R.id.comment_rank);
            aVar.b = (TextView) view.findViewById(R.id.user_name);
            aVar.c = (TextView) view.findViewById(R.id.comment_time);
            aVar.d = (TextView) view.findViewById(R.id.comment_content);
            aVar.f = view.findViewById(R.id.comment_div);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.f.setVisibility(0);
        } else {
            aVar.f.setVisibility(8);
        }
        aVar.e.setRating(Float.valueOf(((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).c()).floatValue());
        if (((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).e().length() == 11) {
            try {
                long longValue = Long.valueOf(((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).e()).longValue();
                aVar.b.setText(((longValue + "").substring(0, 3) + "****" + (longValue + "").substring(7, 11)) + ":");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                aVar.b.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).e() + ":");
            }
        } else {
            aVar.b.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).e() + ":");
        }
        aVar.d.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).d());
        q.a("获取时间:" + ((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).g());
        aVar.c.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.a.get(i)).g());
        return view;
    }
}
