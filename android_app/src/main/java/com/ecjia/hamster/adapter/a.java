package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.hamster.activity.ECJiaAddressEditActivity;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ECJiaAddressManageAdapter */
public class a extends BaseAdapter {
    public Handler a;
    public Map<Integer, Boolean> b;
    private Context c;
    private ArrayList<ECJia_ADDRESS> d;
    private LayoutInflater e;
    private int f = 0;
    private int g;
    private StringBuffer h;
    private b i = null;

    /* compiled from: ECJiaAddressManageAdapter */
    public interface b {
        void a(View view, int i);
    }

    /* compiled from: ECJiaAddressManageAdapter */
    class a {
        public View a;
        LinearLayout b;
        LinearLayout c;
        LinearLayout d;
        LinearLayout e;
        final /* synthetic */ a f;
        private TextView g;
        private ECJiaAutoReturnView h;
        private View i;
        private View j;
        private LinearLayout k;
        private TextView l;
        private TextView m;

        a(a aVar) {
            this.f = aVar;
        }
    }

    public a(Context context, ArrayList<ECJia_ADDRESS> arrayList, int i, int i2) {
        this.c = context;
        this.d = arrayList;
        this.g = i;
        this.f = i2;
        this.e = LayoutInflater.from(context);
        this.h = new StringBuffer();
    }

    private void a(int i) {
        this.b = new HashMap();
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (i2 == i) {
                this.b.put(Integer.valueOf(i2), Boolean.valueOf(true));
            } else {
                this.b.put(Integer.valueOf(i2), Boolean.valueOf(false));
            }
        }
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        return this.d.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return i;
    }

    public int getViewTypeCount() {
        return this.d != null ? this.d.size() : 0;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (this.h.length() > 0) {
            this.h.delete(0, this.h.length());
        }
        if (view == null) {
            aVar = new a(this);
            view = this.e.inflate(R.layout.address_manage_item, viewGroup, false);
            aVar.g = (TextView) view.findViewById(R.id.address_manage_item_name);
            aVar.h = (ECJiaAutoReturnView) view.findViewById(R.id.address_manage_item_detail);
            aVar.i = view.findViewById(R.id.address_manage_lastline);
            aVar.a = view.findViewById(R.id.address_isdefault);
            aVar.b = (LinearLayout) view.findViewById(R.id.item_left);
            aVar.c = (LinearLayout) view.findViewById(R.id.item_right);
            aVar.k = (LinearLayout) view.findViewById(R.id.address_edit);
            aVar.m = (TextView) view.findViewById(R.id.address_mobile);
            aVar.d = (LinearLayout) view.findViewById(R.id.address_delete);
            aVar.e = (LinearLayout) view.findViewById(R.id.address_setdefault);
            aVar.l = (TextView) view.findViewById(R.id.andress_isdefault);
            aVar.j = view.findViewById(R.id.address_view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setLayoutParams(new LayoutParams(-1, -1));
        aVar.c.setLayoutParams(new LayoutParams(this.f, -1));
        if (i == this.d.size() - 1) {
            aVar.i.setVisibility(0);
        }
        ECJia_ADDRESS eCJia_ADDRESS = (ECJia_ADDRESS) this.d.get(i);
        if (eCJia_ADDRESS.getDefault_address() == 1) {
            a(i);
        }
        aVar.g.setText(eCJia_ADDRESS.getConsignee());
        aVar.m.setText(eCJia_ADDRESS.getMobile());
        this.h.append(eCJia_ADDRESS.getProvince_name());
        if (!eCJia_ADDRESS.getCity_name().equals("null")) {
            this.h.append(eCJia_ADDRESS.getCity_name());
        }
        if (!eCJia_ADDRESS.getDistrict_name().equals("null")) {
            this.h.append(eCJia_ADDRESS.getDistrict_name());
        }
        if (!eCJia_ADDRESS.getAddress().equals("null")) {
            this.h.append(eCJia_ADDRESS.getAddress());
        }
        aVar.h.setContent(this.h.toString());
        if (this.b == null || this.b.get(Integer.valueOf(i)) == null || !((Boolean) this.b.get(Integer.valueOf(i))).booleanValue()) {
            aVar.a.setVisibility(8);
            aVar.l.setVisibility(8);
            aVar.j.setVisibility(0);
        } else {
            aVar.a.setVisibility(0);
            aVar.l.setVisibility(0);
            aVar.j.setVisibility(8);
        }
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.i != null) {
                    this.b.i.a(view, i);
                }
            }
        });
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.i != null) {
                    this.b.i.a(view, i);
                }
            }
        });
        aVar.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.c, ECJiaAddressEditActivity.class);
                intent.putExtra("address_id", ((ECJia_ADDRESS) this.b.d.get(i)).getId() + "");
                this.b.c.startActivity(intent);
            }
        });
        if (this.g == 1) {
            aVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = ((ECJia_ADDRESS) this.b.d.get(i)).getId();
                    this.b.a.sendMessage(message);
                }
            });
        }
        return view;
    }

    public void a(b bVar) {
        this.i = bVar;
    }
}
