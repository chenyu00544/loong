package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.hamster.activity.ECJiaTopicDetailActivity;
import com.ecjia.hamster.model.bb;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaTopiclistAdapter */
public class bz extends BaseAdapter {
    private Context a;
    private ArrayList<bb> b;
    private int c = a();
    private int d;

    /* compiled from: ECJiaTopiclistAdapter */
    private class a {
        final /* synthetic */ bz a;
        private ImageView b;
        private TextView c;

        private a(bz bzVar) {
            this.a = bzVar;
        }
    }

    public bz(Context context, ArrayList<bb> arrayList) {
        this.a = context;
        this.b = arrayList;
        this.d = (int) context.getResources().getDimension(R.dimen.dp_15);
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        final bb bbVar = (bb) this.b.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.topic_list_item, null);
            a aVar2 = new a();
            aVar2.b = (ImageView) view.findViewById(R.id.img_topic);
            aVar2.c = (TextView) view.findViewById(R.id.text_topic);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.width = this.c - (this.d * 2);
            layoutParams.height = ((this.c - (this.d * 2)) * 2) / 5;
            aVar2.b.setLayoutParams(layoutParams);
            layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.height = ((this.c - (this.d * 2)) * 2) / 5;
            layoutParams.width = (layoutParams.height * 3) / 4;
            layoutParams.gravity = 21;
            aVar2.c.setLayoutParams(layoutParams);
            aVar2.c.setGravity(17);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        ImageLoader.getInstance().displayImage(bbVar.c(), aVar.b);
        aVar.c.setText(bbVar.b());
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bz b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.a, ECJiaTopicDetailActivity.class);
                intent.putExtra("topic_id", bbVar.a());
                this.b.a.startActivity(intent);
            }
        });
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.a).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.a).getWindowManager().getDefaultDisplay().getHeight());
    }
}
