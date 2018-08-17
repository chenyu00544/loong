package com.ecjia.hamster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.ac;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaSechillAdapter */
public class bf extends Adapter<a> {
    private LayoutInflater a;
    private ArrayList<ac> b;
    private Context c;
    private b d = null;

    /* compiled from: ECJiaSechillAdapter */
    public interface b {
        void a(View view, int i);
    }

    /* compiled from: ECJiaSechillAdapter */
    public class a extends ViewHolder {
        TextView a;
        TextView b;
        LinearLayout c;
        View d;
        final /* synthetic */ bf e;

        public a(bf bfVar, View view) {
            this.e = bfVar;
            super(view);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public bf(Context context, ArrayList<ac> arrayList) {
        this.a = LayoutInflater.from(context);
        this.b = arrayList;
        this.c = context;
    }

    public a a(ViewGroup viewGroup, int i) {
        View inflate = this.a.inflate(R.layout.seckill_recycler_item, viewGroup, false);
        a aVar = new a(this, inflate);
        aVar.a = (TextView) inflate.findViewById(R.id.sechill_itme);
        aVar.b = (TextView) inflate.findViewById(R.id.sechill_txt);
        aVar.c = (LinearLayout) inflate.findViewById(R.id.seckill_itme);
        aVar.d = inflate.findViewById(R.id.sechill_line);
        return aVar;
    }

    public void a(a aVar, final int i) {
        aVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bf b;

            public void onClick(View view) {
                if (this.b.d != null) {
                    this.b.d.a(view, i);
                }
            }
        });
        if (((ac) this.b.get(i)).a()) {
            aVar.a.setTextColor(com.ecjia.consts.b.a(this.c).getColor(R.color.public_theme_color_normal));
            aVar.b.setTextColor(com.ecjia.consts.b.a(this.c).getColor(R.color.public_theme_color_normal));
            aVar.a.setTextSize(16.0f);
            aVar.b.setTextSize(16.0f);
            aVar.d.setVisibility(0);
        } else {
            aVar.a.setTextColor(com.ecjia.consts.b.a(this.c).getColor(R.color.my_black));
            aVar.b.setTextColor(com.ecjia.consts.b.a(this.c).getColor(R.color.my_black));
            aVar.a.setTextSize(14.0f);
            aVar.b.setTextSize(14.0f);
            aVar.d.setVisibility(8);
        }
        if (((ac) this.b.get(i)).m().equals("finished")) {
            aVar.a.setText("明日" + ((ac) this.b.get(i)).k());
            aVar.b.setText("即将开始");
            return;
        }
        aVar.a.setText(((ac) this.b.get(i)).k());
        aVar.b.setText(((ac) this.b.get(i)).l());
    }

    public int getItemCount() {
        return this.b.size();
    }

    public void a(b bVar) {
        this.d = bVar;
    }
}
