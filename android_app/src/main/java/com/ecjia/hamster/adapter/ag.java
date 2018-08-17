package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.activity.ECJiaInfoWebActivity;
import com.ecjia.hamster.model.at;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaMineHelpListAdapter */
public class ag extends BaseAdapter {
    public ArrayList<at> a = new ArrayList();
    private Context b;

    /* compiled from: ECJiaMineHelpListAdapter */
    class a {
        TextView a;
        ImageView b;
        LinearLayout c;
        View d;
        View e;
        View f;
        final /* synthetic */ ag g;

        a(ag agVar) {
            this.g = agVar;
        }
    }

    public ag(Context context, ArrayList<at> arrayList) {
        this.a = arrayList;
        this.b = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.mine_my_helpcell, null);
            aVar.a = (TextView) view.findViewById(R.id.shopinfo_content);
            aVar.c = (LinearLayout) view.findViewById(R.id.shopinfo_item);
            aVar.b = (ImageView) view.findViewById(R.id.iv_shopinfo);
            aVar.d = view.findViewById(R.id.info_first_line);
            aVar.e = view.findViewById(R.id.info_end_line);
            aVar.f = view.findViewById(R.id.info_middle_line_top);
            aVar.d.setVisibility(8);
            if (i == this.a.size() - 1) {
                aVar.f.setVisibility(8);
            } else {
                aVar.f.setVisibility(0);
            }
            aVar.e.setVisibility(8);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((at) this.a.get(i)).a());
        p.a(this.b).a(aVar.b, ((at) this.a.get(i)).b(), 9002);
        aVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ag b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.b, ECJiaInfoWebActivity.class);
                intent.putExtra("id", ((at) this.b.a.get(i)).c());
                intent.putExtra("title", ((at) this.b.a.get(i)).a());
                this.b.b.startActivity(intent);
                ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        return view;
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
