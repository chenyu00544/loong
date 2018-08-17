package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.v;
import com.ecjia.hamster.model.z;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/* compiled from: ECJiaMessageAdapter */
public class af extends BaseAdapter {
    public ArrayList<z> a;
    Resources b;
    SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
    Date e = new Date();
    String f = this.d.format(this.e);
    a g;
    private Context h;

    /* compiled from: ECJiaMessageAdapter */
    public interface a {
        void a(String str);
    }

    /* compiled from: ECJiaMessageAdapter */
    class b {
        LinearLayout a;
        TextView b;
        TextView c;
        TextView d;
        final /* synthetic */ af e;

        b(af afVar) {
            this.e = afVar;
        }
    }

    public af(Context context, ArrayList<z> arrayList) {
        this.h = context;
        this.a = arrayList;
        this.b = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            b bVar2 = new b(this);
            view = View.inflate(this.h, R.layout.layout_item_message, null);
            bVar2.a = (LinearLayout) view.findViewById(R.id.message_content_layout);
            bVar2.b = (TextView) view.findViewById(R.id.message_date);
            bVar2.c = (TextView) view.findViewById(R.id.message_content);
            bVar2.d = (TextView) view.findViewById(R.id.message_time);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        final z zVar = (z) this.a.get((this.a.size() - 1) - i);
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(this.c.parse(zVar.h() + " " + zVar.p()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (zVar.a() == 1) {
            bVar.c.setTextColor(Color.parseColor("#111111"));
        } else {
            bVar.c.setTextColor(Color.parseColor("#999999"));
        }
        bVar.c.setText(zVar.o());
        bVar.d.setText(zVar.p());
        if (i == 0) {
            bVar.b.setText(a(instance));
        } else if (!zVar.h().equals(this.f)) {
            bVar.b.setText(a(instance));
        } else if (zVar.h().equals(((z) this.a.get(this.a.size() - i)).h())) {
            bVar.b.setText(zVar.p().substring(0, 5));
        } else {
            bVar.b.setText(a(instance));
        }
        bVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ af c;

            public void onClick(View view) {
                v.a(this.c.h, zVar);
                zVar.a(0);
                bVar.c.setTextColor(Color.parseColor("#999999"));
                if (this.c.g != null) {
                    this.c.g.a(zVar.i());
                }
            }
        });
        return view;
    }

    public String a(Calendar calendar) {
        if (calendar.get(2) + 1 < 10) {
        }
        if (Calendar.getInstance().get(1) != calendar.get(1)) {
            return calendar.get(1) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(2) + 1) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(5));
        }
        return a(calendar.get(1)) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(2) + 1) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(5)) + " " + a(calendar.get(11)) + ":" + a(calendar.get(12));
    }

    private String a(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

    public void a(a aVar) {
        this.g = aVar;
    }
}
