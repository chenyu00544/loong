package com.ecjia.hamster.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ECJia_GOODS_COUPON;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaReceiveRedpaperAdapter */
public class ax extends BaseAdapter {
    public List<ECJia_GOODS_COUPON> a;
    a b;
    private Context c;

    /* compiled from: ECJiaReceiveRedpaperAdapter */
    public interface a {
        void a(int i, String str);
    }

    /* compiled from: ECJiaReceiveRedpaperAdapter */
    class b {
        public View a;
        public View b;
        final /* synthetic */ ax c;
        private TextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private TextView h;

        b(ax axVar) {
            this.c = axVar;
        }
    }

    public ax(Context context, List<ECJia_GOODS_COUPON> list) {
        this.c = context;
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
        b bVar;
        if (view == null) {
            bVar = new b(this);
            view = LayoutInflater.from(this.c).inflate(R.layout.layout_receive_redpaper_item, null);
            bVar.d = (TextView) view.findViewById(R.id.bonus_amount_left);
            bVar.e = (TextView) view.findViewById(R.id.bonus_amount_right);
            bVar.f = (TextView) view.findViewById(R.id.request_amount);
            bVar.g = (TextView) view.findViewById(R.id.activity_time);
            bVar.h = (TextView) view.findViewById(R.id.receiver_sure);
            bVar.a = view.findViewById(R.id.middle_line);
            bVar.b = view.findViewById(R.id.bottom_line);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        String[] split = ((ECJia_GOODS_COUPON) this.a.get(i)).getBonus_amount().split("\\.");
        bVar.d.setText(split[0]);
        bVar.e.setText("." + split[1] + "元");
        bVar.f.setText(((ECJia_GOODS_COUPON) this.a.get(i)).getRequest_amount());
        bVar.g.setText(((ECJia_GOODS_COUPON) this.a.get(i)).getFormatted_start_date() + "--" + ((ECJia_GOODS_COUPON) this.a.get(i)).getFormatted_end_date());
        if (this.a.size() == 1 || i == this.a.size() - 1) {
            bVar.b.setVisibility(0);
            bVar.a.setVisibility(8);
        } else {
            bVar.b.setVisibility(8);
            bVar.a.setVisibility(0);
        }
        if (((ECJia_GOODS_COUPON) this.a.get(i)).getReceived_coupon().equals("1")) {
            bVar.h.setBackgroundResource(R.drawable.shape_gray_stroke_white_bg_corner);
            bVar.h.setTextColor(Color.parseColor("#999999"));
            bVar.h.setText(R.string.receive_received);
            bVar.h.setEnabled(false);
        }
        bVar.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ax b;

            public void onClick(View view) {
                if (this.b.b != null) {
                    this.b.b.a(i, ((ECJia_GOODS_COUPON) this.b.a.get(i)).getBonus_type_id());
                    q.a("list.get(position).getBonus_id()" + ((ECJia_GOODS_COUPON) this.b.a.get(i)).getBonus_type_id());
                }
            }
        });
        return view;
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
