package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ah;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaQuickEnterAdapter */
public class aw extends BaseAdapter {
    private ArrayList<ah> a;
    private Context b;

    /* compiled from: ECJiaQuickEnterAdapter */
    private class a {
        final /* synthetic */ aw a;
        private TextView b;
        private ImageView c;
        private LinearLayout d;

        private a(aw awVar) {
            this.a = awVar;
        }
    }

    public aw(Context context, ArrayList<ah> arrayList) {
        this.b = context;
        this.a = arrayList;
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
        final ah ahVar = (ah) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.quick_gridview_item, null);
            a aVar2 = new a();
            aVar2.b = (TextView) view.findViewById(R.id.new_quick_text);
            aVar2.c = (ImageView) view.findViewById(R.id.new_quick_img);
            aVar2.d = (LinearLayout) view.findViewById(R.id.quick_item);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(ahVar.c());
        ImageLoader.getInstance().displayImage(ahVar.a(), aVar.c);
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ aw b;

            public void onClick(View view) {
                com.ecjia.a.b.a.a().a(this.b.b, ahVar.b());
                q.a("ECJiaOpenType===" + ahVar.b());
            }
        });
        return view;
    }
}
