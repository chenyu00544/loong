package com.ecjia.hamster.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.l;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaConsultViewAdapter */
public class g extends BaseAdapter {
    public Bitmap a;
    private ArrayList<l> b;
    private Context c;
    private LayoutInflater d;
    private ECJiaApplication e;

    /* compiled from: ECJiaConsultViewAdapter */
    static class a {
        public TextView a;
        public TextView b;
        public LinearLayout c;
        public LinearLayout d;
        public ImageView e;
        public ImageView f;

        a() {
        }
    }

    public g(Context context, ArrayList<l> arrayList, Bitmap bitmap) {
        this.c = context;
        this.e = (ECJiaApplication) context.getApplicationContext();
        this.a = bitmap;
        this.b = arrayList;
        this.d = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get((this.b.size() - 1) - i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        if (1 == Integer.valueOf(((l) this.b.get((this.b.size() - 1) - i)).a()).intValue()) {
            return 1;
        }
        return 0;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.b.size() == 0) {
            return null;
        }
        a aVar;
        View view2;
        l lVar = (l) this.b.get((this.b.size() - 1) - i);
        int intValue = Integer.valueOf(lVar.a()).intValue();
        if (view == null) {
            aVar = new a();
            view = this.d.inflate(R.layout.consult_item, null);
            aVar.c = (LinearLayout) view.findViewById(R.id.consult_item_business);
            aVar.d = (LinearLayout) view.findViewById(R.id.consult_item_custom);
            aVar.b = (TextView) view.findViewById(R.id.tv_chatcontent_custom);
            aVar.f = (ImageView) view.findViewById(R.id.iv_userhead_custom);
            aVar.a = (TextView) view.findViewById(R.id.tv_chatcontent_business);
            aVar.e = (ImageView) view.findViewById(R.id.iv_userhead_business);
            view.setTag(aVar);
            view2 = view;
        } else {
            aVar = (a) view.getTag();
            view2 = view;
        }
        if (intValue == 1) {
            aVar.d.setVisibility(0);
            aVar.c.setVisibility(8);
            aVar.b.setText(lVar.b());
            a(aVar.f, intValue);
        } else {
            aVar.d.setVisibility(8);
            aVar.c.setVisibility(0);
            aVar.a.setText(lVar.b());
            a(aVar.e, intValue);
        }
        return view2;
    }

    public void a(ImageView imageView, int i) {
        if (i == 1) {
            if (this.e.e() == null || TextUtils.isEmpty(this.e.e().m())) {
                imageView.setImageResource(R.drawable.profile_no_avarta_icon);
            } else if (this.e.e().q() == null || this.a == null) {
                imageView.setImageResource(R.drawable.profile_no_avarta_icon_light);
            } else {
                imageView.setImageBitmap(this.a);
            }
        }
        if (i == 0) {
            imageView.setImageResource(R.drawable.ecmoban_logo);
        }
    }
}
