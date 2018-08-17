package com.ecjia.hamster.activity.goodsdetail.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.component.view.xlratingbar.ECJiaXLHRatingBar;
import com.ecjia.hamster.activity.ECJiaFullScreenViewPagerActivity;
import com.ecjia.hamster.adapter.b;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* compiled from: ECJiaGoodsdetailCommentAdapter */
public class a extends b<com.ecjia.hamster.activity.goodsdetail.a.a> {
    protected ImageLoader a = ImageLoader.getInstance();
    SimpleDateFormat b;
    private LayoutInflater f;

    /* compiled from: ECJiaGoodsdetailCommentAdapter */
    private static class a {
        private ImageView a;
        private TextView b;
        private ECJiaXLHRatingBar c;
        private TextView d;
        private TextView e;
        private TextView f;
        private LinearLayout g;
        private ImageView h;
        private ImageView i;
        private ImageView j;
        private ImageView k;
        private ImageView l;
        private View m;

        private a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, ArrayList<com.ecjia.hamster.activity.goodsdetail.a.a> arrayList) {
        super(context, arrayList);
        this.f = LayoutInflater.from(context);
        this.b = new SimpleDateFormat("yyyy-MM-dd");
    }

    protected com.ecjia.hamster.adapter.b.a a(View view) {
        return null;
    }

    protected View a(int i, View view, ViewGroup viewGroup, com.ecjia.hamster.adapter.b.a aVar) {
        return null;
    }

    public View a() {
        return null;
    }

    public int getCount() {
        return this.e.size();
    }

    public com.ecjia.hamster.activity.goodsdetail.a.a a(int i) {
        return (com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        Date parse;
        if (view == null) {
            aVar = new a();
            view = this.f.inflate(R.layout.comment_item, null);
            aVar.a = (ImageView) view.findViewById(R.id.avatar_img);
            aVar.b = (TextView) view.findViewById(R.id.user_name);
            aVar.c = (ECJiaXLHRatingBar) view.findViewById(R.id.comment_rank);
            aVar.d = (TextView) view.findViewById(R.id.comment_time);
            aVar.e = (TextView) view.findViewById(R.id.comment_content);
            aVar.f = (TextView) view.findViewById(R.id.goods_attr);
            aVar.g = (LinearLayout) view.findViewById(R.id.showorder_image_ll);
            aVar.h = (ImageView) view.findViewById(R.id.showorder_image1);
            aVar.i = (ImageView) view.findViewById(R.id.showorder_image2);
            aVar.j = (ImageView) view.findViewById(R.id.showorder_image3);
            aVar.k = (ImageView) view.findViewById(R.id.showorder_image4);
            aVar.l = (ImageView) view.findViewById(R.id.showorder_image5);
            aVar.m = view.findViewById(R.id.comment_div);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).e());
        aVar.c.setCountSelected(Integer.valueOf(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).c()).intValue());
        aVar.d.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).g());
        try {
            parse = this.b.parse(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).g());
        } catch (ParseException e) {
            e.printStackTrace();
            parse = null;
        }
        aVar.d.setText(this.b.format(parse));
        aVar.e.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).d());
        aVar.f.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).a());
        if (TextUtils.isEmpty(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).a())) {
            aVar.f.setVisibility(8);
        } else {
            aVar.f.setVisibility(0);
            aVar.f.setText(((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).a());
        }
        p.a().a(aVar.a, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).f(), 9003);
        if (((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().size() > 0) {
            aVar.g.setVisibility(0);
            int min = Math.min(5, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().size());
            if (min == 1) {
                aVar.h.setVisibility(0);
                aVar.i.setVisibility(4);
                aVar.j.setVisibility(4);
                aVar.k.setVisibility(4);
                aVar.l.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(0), aVar.h);
                a(aVar.h, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 0);
            } else if (min == 2) {
                aVar.h.setVisibility(0);
                aVar.i.setVisibility(0);
                aVar.j.setVisibility(4);
                aVar.k.setVisibility(4);
                aVar.l.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(0), aVar.h);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(1), aVar.i);
                a(aVar.h, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 0);
                a(aVar.i, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 1);
            } else if (min == 3) {
                aVar.h.setVisibility(0);
                aVar.i.setVisibility(0);
                aVar.j.setVisibility(0);
                aVar.k.setVisibility(4);
                aVar.l.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(0), aVar.h);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(1), aVar.i);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(2), aVar.j);
                a(aVar.h, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 0);
                a(aVar.i, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 1);
                a(aVar.j, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 2);
            } else if (min == 4) {
                aVar.h.setVisibility(0);
                aVar.i.setVisibility(0);
                aVar.j.setVisibility(0);
                aVar.k.setVisibility(0);
                aVar.l.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(0), aVar.h);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(1), aVar.i);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(2), aVar.j);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(3), aVar.k);
                a(aVar.h, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 0);
                a(aVar.i, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 1);
                a(aVar.j, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 2);
                a(aVar.k, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 3);
            } else if (min >= 5) {
                aVar.h.setVisibility(0);
                aVar.i.setVisibility(0);
                aVar.j.setVisibility(0);
                aVar.k.setVisibility(0);
                aVar.l.setVisibility(0);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(0), aVar.h);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(1), aVar.i);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(2), aVar.j);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(3), aVar.k);
                ImageLoader.getInstance().displayImage((String) ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b().get(4), aVar.l);
                a(aVar.h, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 0);
                a(aVar.i, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 1);
                a(aVar.j, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 2);
                a(aVar.k, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 3);
                a(aVar.l, ((com.ecjia.hamster.activity.goodsdetail.a.a) this.e.get(i)).b(), 4);
            } else {
                aVar.h.setVisibility(8);
                aVar.i.setVisibility(8);
                aVar.j.setVisibility(8);
                aVar.k.setVisibility(8);
                aVar.l.setVisibility(8);
            }
        } else {
            aVar.g.setVisibility(8);
        }
        if (i == this.e.size() - 1) {
            aVar.m.setVisibility(8);
        } else {
            aVar.m.setVisibility(0);
        }
        return view;
    }

    void a(ImageView imageView, final ArrayList<String> arrayList, final int i) {
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a c;

            public void onClick(View view) {
                Intent intent = new Intent(this.c.d, ECJiaFullScreenViewPagerActivity.class);
                intent.putExtra("position", i);
                Serializable arrayList = new ArrayList();
                int min = Math.min(5, arrayList.size());
                for (int i = 0; i < min; i++) {
                    arrayList.add(arrayList.get(i));
                }
                intent.putExtra("size", min);
                intent.putExtra("pictures", arrayList);
                this.c.d.startActivity(intent);
                ((Activity) this.c.d).overridePendingTransition(R.anim.my_scale_action, R.anim.my_scale_action);
            }
        });
    }
}
