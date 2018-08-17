package com.ecjia.hamster.module.goodsReturn.a;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_DETAIL.RETURN_LOG;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaReturnProcessAdapter */
public class d extends BaseAdapter {
    public ArrayList<RETURN_LOG> a;
    private Context b;
    private LayoutInflater c;
    private Resources d;

    /* compiled from: ECJiaReturnProcessAdapter */
    static class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        View e;
        View f;
        View g;
        View h;
        View i;
        View j;
        View k;

        a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public d(Context context, ArrayList<RETURN_LOG> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = LayoutInflater.from(context);
        this.d = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public RETURN_LOG a(int i) {
        return (RETURN_LOG) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        RETURN_LOG return_log = (RETURN_LOG) this.a.get(i);
        if (view == null) {
            view = this.c.inflate(R.layout.item_return_process, null);
            a aVar2 = new a();
            aVar2.a = (TextView) view.findViewById(R.id.log_description);
            aVar2.b = (TextView) view.findViewById(R.id.action_time);
            aVar2.c = (TextView) view.findViewById(R.id.action_user_left);
            aVar2.d = (TextView) view.findViewById(R.id.action_user);
            aVar2.e = view.findViewById(R.id.last_action);
            aVar2.f = view.findViewById(R.id.right_now_action);
            aVar2.g = view.findViewById(R.id.top_status_line_1);
            aVar2.h = view.findViewById(R.id.bottom_status_line_1);
            aVar2.i = view.findViewById(R.id.bottom_status_line_2);
            aVar2.j = view.findViewById(R.id.middle_line);
            aVar2.k = view.findViewById(R.id.bottom_line);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0 || this.a.size() == 1) {
            aVar.g.setVisibility(4);
        } else {
            aVar.g.setVisibility(0);
        }
        if (this.a.size() == 1 || i == this.a.size() - 1) {
            aVar.h.setVisibility(4);
            aVar.i.setVisibility(4);
            aVar.j.setVisibility(8);
            aVar.k.setVisibility(0);
        } else {
            aVar.h.setVisibility(0);
            aVar.i.setVisibility(0);
            aVar.j.setVisibility(0);
            aVar.k.setVisibility(8);
        }
        if (i == 0) {
            aVar.a.setTextColor(this.b.getResources().getColor(R.color.my_black));
            aVar.b.setTextColor(this.b.getResources().getColor(R.color.my_black));
            aVar.c.setTextColor(this.b.getResources().getColor(R.color.my_black));
            aVar.d.setTextColor(this.b.getResources().getColor(R.color.my_black));
            aVar.f.setVisibility(0);
            aVar.e.setVisibility(8);
        } else {
            aVar.a.setTextColor(this.b.getResources().getColor(R.color.my_dark));
            aVar.b.setTextColor(this.b.getResources().getColor(R.color.my_dark));
            aVar.c.setTextColor(this.b.getResources().getColor(R.color.my_dark));
            aVar.d.setTextColor(this.b.getResources().getColor(R.color.my_dark));
            aVar.f.setVisibility(8);
            aVar.e.setVisibility(0);
        }
        aVar.a.setText(return_log.getLog_description());
        aVar.b.setText(return_log.getFormatted_action_time());
        aVar.d.setText(return_log.getAction_user());
        return view;
    }
}
