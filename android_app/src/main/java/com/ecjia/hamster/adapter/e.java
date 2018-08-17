package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.consts.b;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.j;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaCollectAdapter */
public class e extends BaseAdapter {
    public int a;
    public ArrayList<HashMap<String, String>> b;
    j c;
    public Handler d;
    protected ImageLoader e = ImageLoader.getInstance();
    int f;
    private Context g;
    private LayoutInflater h;

    /* compiled from: ECJiaCollectAdapter */
    class a {
        LinearLayout a;
        LinearLayout b;
        LinearLayout c;
        ImageView d;
        ECJiaAutoReturnView e;
        TextView f;
        TextView g;
        View h;
        View i;
        View j;
        CheckBox k;
        TextView l;
        LinearLayout m;
        final /* synthetic */ e n;

        a(e eVar) {
            this.n = eVar;
        }
    }

    public e(Context context, ArrayList<HashMap<String, String>> arrayList, int i) {
        this.g = context;
        this.b = arrayList;
        this.a = i;
        this.h = LayoutInflater.from(context);
        this.f = arrayList.size() - 1;
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public int getCount() {
        return this.b.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        try {
            this.c = j.a(new JSONObject((String) ((HashMap) this.b.get(i)).get("content")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (view == null) {
            aVar = new a(this);
            view = this.h.inflate(R.layout.collect_item, null);
            aVar.d = (ImageView) view.findViewById(R.id.collect_img);
            aVar.e = (ECJiaAutoReturnView) view.findViewById(R.id.collect_goodname);
            aVar.f = (TextView) view.findViewById(R.id.collect_goodprice);
            aVar.k = (CheckBox) view.findViewById(R.id.collect_item_del);
            aVar.a = (LinearLayout) view.findViewById(R.id.collect_item);
            aVar.h = view.findViewById(R.id.collect_top_line);
            aVar.i = view.findViewById(R.id.collect_buttom_line);
            aVar.j = view.findViewById(R.id.collect_short_line);
            aVar.b = (LinearLayout) view.findViewById(R.id.collect_check_item);
            aVar.c = (LinearLayout) view.findViewById(R.id.collect_rightitem);
            aVar.g = (TextView) view.findViewById(R.id.collect_market_price);
            aVar.g.getPaint().setFlags(17);
            aVar.l = (TextView) view.findViewById(R.id.tv_saving);
            aVar.m = (LinearLayout) view.findViewById(R.id.ll_goodlist_mb);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.h.setVisibility(0);
        } else {
            aVar.h.setVisibility(8);
        }
        if (i != this.f) {
            aVar.j.setVisibility(0);
            aVar.i.setVisibility(8);
        } else {
            aVar.j.setVisibility(8);
            aVar.i.setVisibility(0);
        }
        if ("MOBILEBUY_GOODS".equals(this.c.a())) {
            aVar.m.setVisibility(0);
            aVar.l.setText(this.c.b());
        } else {
            aVar.m.setVisibility(8);
        }
        if ("免费".equals(this.c.c())) {
            aVar.g.setVisibility(4);
        } else {
            aVar.g.setVisibility(0);
            aVar.g.setText(this.c.d());
        }
        if (this.a == 1) {
            aVar.b.setVisibility(8);
            aVar.a.setBackgroundResource(R.drawable.new_nothing_bg);
        } else if (this.a == 2) {
            aVar.b.setVisibility(0);
        }
        aVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e c;

            public void onClick(View view) {
                j a;
                try {
                    a = j.a(new JSONObject((String) ((HashMap) this.c.b.get(i)).get("content")));
                } catch (JSONException e) {
                    e.printStackTrace();
                    a = null;
                }
                if (aVar.b.getVisibility() != 0) {
                    Intent intent = new Intent(this.c.g, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", a.f() + "");
                    this.c.g.startActivity(intent);
                    ((Activity) this.c.g).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            }
        });
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e c;

            public void onClick(View view) {
                if (aVar.k.isChecked()) {
                    aVar.k.setChecked(false);
                    ((HashMap) this.c.b.get(i)).put(AgooConstants.MESSAGE_FLAG, "false");
                } else {
                    aVar.k.setChecked(true);
                    ((HashMap) this.c.b.get(i)).put(AgooConstants.MESSAGE_FLAG, "true");
                }
                Message message;
                if (this.c.a()) {
                    message = new Message();
                    message.arg1 = 1;
                    this.c.d.handleMessage(message);
                } else {
                    message = new Message();
                    message.arg1 = 0;
                    this.c.d.handleMessage(message);
                }
                this.c.notifyDataSetChanged();
                q.a("====是否选中===" + ((String) ((HashMap) this.c.b.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true"));
            }
        });
        aVar.k.setChecked(((String) ((HashMap) this.b.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true"));
        if (aVar.b.getVisibility() != 0) {
            aVar.c.setBackgroundResource(R.drawable.selecter_newitem_press);
        } else if (((String) ((HashMap) this.b.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true")) {
            aVar.a.setBackgroundResource(R.drawable.new_good_distance);
            aVar.c.setBackgroundResource(R.drawable.new_good_distance);
        } else {
            aVar.a.setBackgroundResource(R.drawable.new_nothing_bg);
            aVar.c.setBackgroundResource(R.drawable.new_nothing_bg);
        }
        aVar.e.setContent(this.c.e());
        if (k.a(this.c.h()) != 0.0f) {
            aVar.f.setText(this.c.h());
            aVar.g.setText(this.c.c());
        } else if (k.a(this.c.c()) == 0.0f) {
            aVar.f.setText("免费");
            aVar.g.setText("");
        } else {
            aVar.f.setText(this.c.c());
            aVar.g.setText(this.c.d());
        }
        p.a(this.g).a(aVar.d, this.c.g().getSmall());
        return view;
    }

    private boolean a() {
        for (int i = 0; i < b.c.size(); i++) {
            if (((String) ((HashMap) b.c.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true")) {
                return true;
            }
        }
        return false;
    }
}
