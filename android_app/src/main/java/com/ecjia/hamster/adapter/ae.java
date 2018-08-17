package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mapapi.search.core.PoiInfo;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaLocationPOIAdapter */
public class ae extends BaseAdapter {
    private List<PoiInfo> a;
    private Context b;

    /* compiled from: ECJiaLocationPOIAdapter */
    static class a {
        TextView a;
        TextView b;
        LinearLayout c;
        View d;

        a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public ae(Context context, List<PoiInfo> list) {
        this.b = context;
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public PoiInfo a(int i) {
        return (PoiInfo) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        PoiInfo poiInfo = (PoiInfo) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.poi_item, null);
            a aVar2 = new a();
            aVar2.a = (TextView) view.findViewById(R.id.tv_poi_name);
            aVar2.b = (TextView) view.findViewById(R.id.tv_poi_address);
            aVar2.c = (LinearLayout) view.findViewById(R.id.poi_item);
            aVar2.d = view.findViewById(R.id.poi_bottom_line);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == this.a.size() - 1) {
            aVar.d.setVisibility(8);
        } else {
            aVar.d.setVisibility(0);
        }
        aVar.a.setText(poiInfo.name);
        aVar.b.setText(poiInfo.address);
        return view;
    }
}
