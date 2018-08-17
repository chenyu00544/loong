package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaRedPapperAdapter */
public class ba extends BaseAdapter {
    LayoutInflater a;
    ArrayList<ECJia_BONUS> b;
    private Context c;

    /* compiled from: ECJiaRedPapperAdapter */
    class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        final /* synthetic */ ba g;

        a(ba baVar) {
            this.g = baVar;
        }
    }

    public ba(ArrayList<ECJia_BONUS> arrayList, Context context) {
        this.b = arrayList;
        this.c = context;
        this.a = LayoutInflater.from(context);
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
        ECJia_BONUS eCJia_BONUS = (ECJia_BONUS) this.b.get(i);
        if (view == null) {
            aVar = new a(this);
            view = this.a.inflate(R.layout.redpapper_list_item, null);
            aVar.a = (TextView) view.findViewById(R.id.red_type);
            aVar.b = (TextView) view.findViewById(R.id.red_money);
            aVar.c = (TextView) view.findViewById(R.id.red_goodsfee);
            aVar.d = (TextView) view.findViewById(R.id.red_starttime);
            aVar.e = (TextView) view.findViewById(R.id.red_endtime);
            aVar.f = (TextView) view.findViewById(R.id.red_status);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((ECJia_BONUS) this.b.get(i)).getBonus_name());
        aVar.b.setText(((ECJia_BONUS) this.b.get(i)).getFormatted_bonus_amount());
        if (0.0f == k.a(((ECJia_BONUS) this.b.get(i)).getRequest_amount())) {
            aVar.c.setText("");
        } else {
            Resources resources = this.c.getResources();
            String string = resources.getString(R.string.redpapper_youneed);
            aVar.c.setText(string + ((ECJia_BONUS) this.b.get(i)).getFormatted_request_amount() + resources.getString(R.string.redpapper_goods));
        }
        aVar.d.setText(((ECJia_BONUS) this.b.get(i)).getFormatted_start_date());
        aVar.e.setText(((ECJia_BONUS) this.b.get(i)).getFormatted_end_date());
        aVar.f.setText(((ECJia_BONUS) this.b.get(i)).getLabel_status());
        return view;
    }
}
