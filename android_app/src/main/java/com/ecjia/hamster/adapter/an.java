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
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/* compiled from: ECJiaNewGoodlistAdapter */
public class an extends BaseAdapter {
    public List<au> a;
    protected ImageLoader b = ImageLoader.getInstance();
    private Context c;

    /* compiled from: ECJiaNewGoodlistAdapter */
    class a {
        public View a;
        public View b;
        final /* synthetic */ an c;
        private ImageView d;
        private ECJiaAutoReturnView e;
        private TextView f;
        private TextView g;
        private TextView h;
        private View i;
        private LinearLayout j;
        private LinearLayout k;

        a(an anVar) {
            this.c = anVar;
        }
    }

    public an(Context context, List<au> list) {
        this.c = context;
        this.a = list;
    }

    public void a(List<au> list) {
        this.a = list;
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        Object replace;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.c).inflate(R.layout.goodlist_good_item, null);
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
            aVar.a = view.findViewById(R.id.goodlist_middel_line);
            aVar.b = view.findViewById(R.id.goodlist_bottom_line);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.e.setContent(((au) this.a.get(i)).g());
        String replace2;
        if (((au) this.a.get(i)).d() != null && ((au) this.a.get(i)).d().length() > 0) {
            replace2 = ((au) this.a.get(i)).d().replace("￥", "").replace("元", "").replace("yuan", "").replace("¥", "");
            if ("免费".equals(replace2) || "0.00".equals(replace2)) {
                aVar.f.setText("免费");
            } else {
                aVar.f.setText(((au) this.a.get(i)).d());
            }
        } else if (((au) this.a.get(i)).e() != null && ((au) this.a.get(i)).e().length() > 0) {
            replace2 = ((au) this.a.get(i)).e().replace("￥", "").replace("元", "").replace("yuan", "").replace("¥", "");
            if ("免费".equals(replace2) || "0.00".equals(replace2)) {
                aVar.f.setText("免费");
            } else {
                aVar.f.setText(((au) this.a.get(i)).e());
            }
        }
        if (((au) this.a.get(i)).f() != null) {
            replace = ((au) this.a.get(i)).f().replace("￥", "").replace("元", "").replace("yuan", "").replace("¥", "");
        } else {
            replace = null;
        }
        if ("免费".equals(replace) || "0.00".equals(replace) || replace == null || "".equals(replace) || "0".equals(replace)) {
            aVar.g.setVisibility(4);
        } else {
            aVar.g.setVisibility(0);
            aVar.g.setText(((au) this.a.get(i)).f());
        }
        if ("MOBILEBUY_GOODS".equals(((au) this.a.get(i)).a())) {
            aVar.k.setVisibility(0);
            aVar.h.setText(((au) this.a.get(i)).b());
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
            final /* synthetic */ an b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaGoodsDetailActivity.class);
                int h = ((au) this.b.a.get(i)).h();
                if (h == 0) {
                    h = ((au) this.b.a.get(i)).c();
                }
                intent.putExtra("goods_id", h + "");
                this.b.c.startActivity(intent);
                ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        p.a(this.c).a(aVar.d, ((au) this.a.get(i)).i().getThumb());
        return view;
    }
}
