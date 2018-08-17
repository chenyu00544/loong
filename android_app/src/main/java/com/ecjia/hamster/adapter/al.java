package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_INVITE_RECORD;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaMyRecordAdapter */
public class al extends BaseAdapter {
    public ArrayList<ECJia_INVITE_RECORD> a;
    private Context b;

    /* compiled from: ECJiaMyRecordAdapter */
    class a {
        TextView a;
        TextView b;
        TextView c;
        View d;
        View e;
        final /* synthetic */ al f;

        a(al alVar) {
            this.f = alVar;
        }
    }

    public al(Context context, ArrayList<ECJia_INVITE_RECORD> arrayList) {
        this.a = arrayList;
        this.b = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.my_record_item, null);
            aVar.a = (TextView) view.findViewById(R.id.tv_my_reward);
            aVar.b = (TextView) view.findViewById(R.id.tv_my_reward_time);
            aVar.c = (TextView) view.findViewById(R.id.tv_my_reward_points);
            aVar.e = view.findViewById(R.id.end_line);
            aVar.d = view.findViewById(R.id.middle_line);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.d.setVisibility(8);
        } else {
            aVar.d.setVisibility(0);
        }
        if (i == this.a.size() - 1) {
            aVar.e.setVisibility(0);
        } else {
            aVar.e.setVisibility(8);
        }
        aVar.a.setText(((ECJia_INVITE_RECORD) this.a.get(i)).getLabel_award_type());
        aVar.b.setText(((ECJia_INVITE_RECORD) this.a.get(i)).getAward_time());
        aVar.c.setText(((ECJia_INVITE_RECORD) this.a.get(i)).getGive_award());
        return view;
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
