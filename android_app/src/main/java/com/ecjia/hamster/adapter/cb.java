package com.ecjia.hamster.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaYuepaytypeAdapter */
public class cb extends BaseAdapter {
    public Handler a;
    private Context b;
    private ArrayList<ECJia_PAYMENT> c;

    /* compiled from: ECJiaYuepaytypeAdapter */
    class a {
        TextView a;
        View b;
        View c;
        LinearLayout d;
        final /* synthetic */ cb e;

        a(cb cbVar) {
            this.e = cbVar;
        }
    }

    public int getCount() {
        return this.c.size();
    }

    public cb(Context context, ArrayList<ECJia_PAYMENT> arrayList, Handler handler) {
        this.b = context;
        this.c = arrayList;
        this.a = handler;
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.yue_item, null);
            aVar.d = (LinearLayout) view.findViewById(R.id.pay_item);
            aVar.a = (TextView) view.findViewById(R.id.pay_text);
            aVar.b = view.findViewById(R.id.shortline);
            aVar.c = view.findViewById(R.id.longline);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((ECJia_PAYMENT) this.c.get(i)).getPay_name());
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ cb b;

            public void onClick(View view) {
                Message message = new Message();
                message.arg1 = i;
                q.a("payid==" + i);
                this.b.a.sendMessage(message);
            }
        });
        if (i < this.c.size() - 1) {
            aVar.c.setVisibility(8);
            aVar.b.setVisibility(0);
        } else {
            aVar.c.setVisibility(0);
            aVar.b.setVisibility(8);
        }
        return view;
    }
}
