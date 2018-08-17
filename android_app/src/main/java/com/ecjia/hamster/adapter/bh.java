package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.activity.ECJiaMyPurseActivity;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.util.List;

/* compiled from: ECJiaShakeHistoryAdapter */
public class bh extends BaseAdapter {
    public List<com.ecjia.component.a.w.a> a;
    public Context b;
    String c = this.b.getResources().getString(R.string.shake_reach);
    String d = this.b.getResources().getString(R.string.shake_use);

    /* compiled from: ECJiaShakeHistoryAdapter */
    class bh_1 implements OnClickListener {
        final /* synthetic */ bh a;

        bh_1(bh bhVar) {
            this.a = bhVar;
        }

        public void onClick(View view) {
            this.a.b.startActivity(new Intent(this.a.b, ECJiaMyPurseActivity.class));
        }
    }

    /* compiled from: ECJiaShakeHistoryAdapter */
    class a extends ad {
        public TextView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public View f;
        public View g;
        final /* synthetic */ bh h;

        a(bh bhVar) {
            this.h = bhVar;
        }
    }

    public bh(Context context, List<com.ecjia.component.a.w.a> list) {
        this.b = context;
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

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.item_shake_record, null);
            aVar = new a(this);
            aVar.a = (TextView) view.findViewById(R.id.integral);
            aVar.b = (TextView) view.findViewById(R.id.bonus_amount);
            aVar.c = (TextView) view.findViewById(R.id.request_amount);
            aVar.d = (TextView) view.findViewById(R.id.start_end_date);
            aVar.e = (TextView) view.findViewById(R.id.bonus_name);
            aVar.f = view.findViewById(R.id.integral_layout);
            aVar.g = view.findViewById(R.id.bonus_layout);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (((com.ecjia.component.a.w.a) this.a.get(i)).c().equals("integral")) {
            aVar.f.setVisibility(0);
            aVar.g.setVisibility(8);
            aVar.a.setText(((com.ecjia.component.a.w.a) this.a.get(i)).f());
        } else {
            aVar.f.setVisibility(8);
            aVar.g.setVisibility(0);
            aVar.e.setText(((com.ecjia.component.a.w.a) this.a.get(i)).d().a());
            aVar.b.setText(((com.ecjia.component.a.w.a) this.a.get(i)).d().b());
            aVar.c.setText(this.c + ((com.ecjia.component.a.w.a) this.a.get(i)).d().c() + this.d);
            aVar.d.setText(((com.ecjia.component.a.w.a) this.a.get(i)).d().d().replaceAll(SocializeConstants.OP_DIVIDER_MINUS, ".") + SocializeConstants.OP_DIVIDER_MINUS + ((com.ecjia.component.a.w.a) this.a.get(i)).d().e().replaceAll(SocializeConstants.OP_DIVIDER_MINUS, "."));
        }
        view.setOnClickListener(new bh_1(this));
        return view;
    }
}
