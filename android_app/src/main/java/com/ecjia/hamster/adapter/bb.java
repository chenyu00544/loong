package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.activity.ECJiaGoodsListActivity;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecmoban.android.missmall.R;
import java.util.List;
import org.json.JSONException;

/* compiled from: ECJiaSearchHistoryAdapter */
public class bb extends BaseAdapter {
    public List<String> a;
    private Context b;
    private LayoutInflater c;

    /* compiled from: ECJiaSearchHistoryAdapter */
    class a {
        TextView a;
        LinearLayout b;
        final /* synthetic */ bb c;

        a(bb bbVar) {
            this.c = bbVar;
        }
    }

    public bb(List<String> list, Context context) {
        this.a = list;
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    public void a(List<String> list) {
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        String str = (String) this.a.get(i);
        if (view == null) {
            a aVar2 = new a(this);
            view = this.c.inflate(R.layout.search_history_item, null);
            aVar2.a = (TextView) view.findViewById(R.id.history_name);
            aVar2.b = (LinearLayout) view.findViewById(R.id.search_item);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(str);
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bb b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.b, ECJiaGoodsListActivity.class);
                try {
                    intent.putExtra("filter", new ECJia_FILTER().toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                intent.putExtra("keyword", ((String) this.b.a.get(i)).toString());
                this.b.b.startActivity(intent);
            }
        });
        return view;
    }
}
