package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.ab;
import com.ecjia.a.u;
import com.ecjia.hamster.activity.ECJiaRechargeDetailActivity;
import com.ecjia.hamster.model.aj;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONException;

/* compiled from: ECJiaRechargeListAdapter */
public class ay extends BaseAdapter {
    public ArrayList<aj> a;
    Calendar b;
    Calendar c;
    Calendar d;
    Calendar e;
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Resources g;
    ECJiaApplication h;
    public Bitmap i;
    private Context j;

    /* compiled from: ECJiaRechargeListAdapter */
    class a {
        ImageView a;
        View b;
        View c;
        View d;
        LinearLayout e;
        LinearLayout f;
        TextView g;
        TextView h;
        TextView i;
        TextView j;
        TextView k;
        final /* synthetic */ ay l;

        a(ay ayVar) {
            this.l = ayVar;
        }
    }

    public ay(Context context, ArrayList<aj> arrayList) {
        this.j = context;
        this.h = (ECJiaApplication) this.j.getApplicationContext();
        this.a = arrayList;
        this.g = this.j.getResources();
        this.b = Calendar.getInstance();
        this.c = Calendar.getInstance();
        this.d = Calendar.getInstance();
        this.e = Calendar.getInstance();
        this.i = u.a().b(this.h.e().m());
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
        if (view == null) {
            aVar = new a(this);
            view = View.inflate(this.j, R.layout.rechargeitem, null);
            aVar.b = view.findViewById(R.id.top_line);
            aVar.d = view.findViewById(R.id.buttom_line);
            aVar.c = view.findViewById(R.id.top_short_line);
            aVar.a = (ImageView) view.findViewById(R.id.head_img);
            aVar.e = (LinearLayout) view.findViewById(R.id.title_item);
            aVar.g = (TextView) view.findViewById(R.id.title_time);
            aVar.h = (TextView) view.findViewById(R.id.process_type);
            aVar.j = (TextView) view.findViewById(R.id.recharge_amount);
            aVar.i = (TextView) view.findViewById(R.id.create_time);
            aVar.k = (TextView) view.findViewById(R.id.pay_status);
            aVar.f = (LinearLayout) view.findViewById(R.id.re_item);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        try {
            this.c.setTime(this.f.parse(((aj) this.a.get(i)).c()));
            if (i > 0) {
                this.d.setTime(this.f.parse(((aj) this.a.get(i - 1)).c()));
            }
            if (i < this.a.size() - 1) {
                this.e.setTime(this.f.parse(((aj) this.a.get(i + 1)).c()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (this.a.size() == 1) {
            aVar.e.setVisibility(0);
            aVar.b.setVisibility(0);
            aVar.c.setVisibility(8);
            aVar.d.setVisibility(0);
        } else if (i == 0) {
            aVar.e.setVisibility(0);
            aVar.b.setVisibility(0);
            aVar.c.setVisibility(8);
            if (this.c.get(2) == this.e.get(2) && this.e.get(1) == this.c.get(1)) {
                aVar.d.setVisibility(8);
            } else {
                aVar.d.setVisibility(0);
            }
        } else if (i == this.a.size() - 1) {
            aVar.d.setVisibility(0);
            if (this.c.get(2) == this.d.get(2) && this.d.get(1) == this.c.get(1)) {
                aVar.c.setVisibility(0);
                aVar.b.setVisibility(8);
                aVar.e.setVisibility(8);
            } else {
                aVar.c.setVisibility(8);
                aVar.b.setVisibility(0);
                aVar.e.setVisibility(0);
            }
        } else {
            if (this.c.get(2) == this.d.get(2) && this.d.get(1) == this.c.get(1)) {
                aVar.c.setVisibility(0);
                aVar.b.setVisibility(8);
                aVar.e.setVisibility(8);
            } else {
                aVar.c.setVisibility(8);
                aVar.b.setVisibility(0);
                aVar.e.setVisibility(0);
            }
            if (this.c.get(2) == this.e.get(2) && this.e.get(1) == this.c.get(1)) {
                aVar.d.setVisibility(8);
            } else {
                aVar.d.setVisibility(0);
            }
        }
        aVar.g.setText(ab.a(this.c));
        aVar.i.setText(ab.a(this.c, this.j));
        aVar.h.setText(this.h.e().p() + SocializeConstants.OP_DIVIDER_MINUS + ((aj) this.a.get(i)).e());
        a(aVar.a);
        if ("deposit".equals(((aj) this.a.get(i)).d())) {
            aVar.j.setText(((aj) this.a.get(i)).b());
        } else if ("raply".equals(((aj) this.a.get(i)).d())) {
            aVar.j.setText(((aj) this.a.get(i)).b());
        }
        if ("1".equals(((aj) this.a.get(i)).h())) {
            aVar.k.setTextColor(Color.parseColor("#999999"));
            aVar.k.setText(((aj) this.a.get(i)).i());
        } else {
            aVar.k.setTextColor(Color.parseColor("#999999"));
            aVar.k.setText(this.j.getResources().getString(R.string.running));
        }
        aVar.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ay b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.j, ECJiaRechargeDetailActivity.class);
                try {
                    intent.putExtra("data", ((aj) this.b.a.get(i)).j().toString());
                    this.b.j.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void a(ImageView imageView) {
        if (this.i != null) {
            imageView.setImageBitmap(this.i);
        } else {
            imageView.setImageResource(R.drawable.profile_no_avarta_icon_light);
        }
    }
}
