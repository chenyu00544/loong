package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaRedPacketsAdapter */
public class az extends b {
    private int a = -1;

    /* compiled from: ECJiaRedPacketsAdapter */
    public class a extends com.ecjia.hamster.adapter.b.a {
        public TextView c;
        public TextView d;
        public ImageView e;
        final /* synthetic */ az f;

        public a(az azVar) {
            this.f = azVar;
            super(azVar);
        }
    }

    public az(Context context, ArrayList arrayList) {
        super(context, arrayList);
    }

    protected com.ecjia.hamster.adapter.b.a a(View view) {
        com.ecjia.hamster.adapter.b.a aVar = new a(this);
        aVar.c = (TextView) view.findViewById(R.id.red_code);
        aVar.d = (TextView) view.findViewById(R.id.change_money);
        aVar.e = (ImageView) view.findViewById(R.id.red_packet_check);
        return aVar;
    }

    public void a(int i) {
        this.a = i;
    }

    protected View a(int i, View view, ViewGroup viewGroup, com.ecjia.hamster.adapter.b.a aVar) {
        ECJia_BONUS eCJia_BONUS = (ECJia_BONUS) this.e.get(i);
        a aVar2 = (a) aVar;
        aVar2.c.setText(eCJia_BONUS.getType_name());
        aVar2.d.setText(eCJia_BONUS.getBonus_money_formated());
        if (this.a - 1 == i) {
            aVar2.e.setBackgroundResource(R.drawable.payment_selected);
        } else {
            aVar2.e.setBackgroundResource(R.drawable.payment_unselected);
        }
        return null;
    }

    public View a() {
        return LayoutInflater.from(this.d).inflate(R.layout.red_packets_cell, null);
    }
}
