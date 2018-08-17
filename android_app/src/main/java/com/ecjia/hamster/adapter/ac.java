package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

/* compiled from: ECJiaLanguageAdapter */
public class ac extends BaseAdapter {
    public Boolean[] a;
    public Handler b;
    Resources c;
    private Context d;
    private String[] e;

    /* compiled from: ECJiaLanguageAdapter */
    class a {
        TextView a;
        ImageView b;
        View c;
        LinearLayout d;
        final /* synthetic */ ac e;

        a(ac acVar) {
            this.e = acVar;
        }
    }

    public ac(Context context, String[] strArr) {
        this.d = context;
        this.e = strArr;
        this.c = context.getResources();
    }

    public int getCount() {
        return this.e.length;
    }

    public Object getItem(int i) {
        return this.e[i];
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a(this);
            view = View.inflate(this.d, R.layout.language_item, null);
            aVar2.a = (TextView) view.findViewById(R.id.lanitem_text);
            aVar2.b = (ImageView) view.findViewById(R.id.lanitem_selected);
            aVar2.c = view.findViewById(R.id.topline);
            aVar2.d = (LinearLayout) view.findViewById(R.id.lan_item);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(this.e[i]);
        if (this.a[i].booleanValue()) {
            aVar.b.setVisibility(0);
        } else {
            aVar.b.setVisibility(8);
        }
        if ("zh".equalsIgnoreCase(this.e[i])) {
            aVar.a.setText(this.c.getString(R.string.Chinese));
        } else if (SocializeProtocolConstants.PROTOCOL_KEY_EN.equalsIgnoreCase(this.e[i])) {
            aVar.a.setText(this.c.getString(R.string.English));
        } else {
            aVar.a.setText(this.c.getString(R.string.local));
        }
        if (i == this.e.length - 1 || this.e.length == 0) {
            aVar.c.setVisibility(8);
        } else {
            aVar.c.setVisibility(0);
        }
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ac b;

            public void onClick(View view) {
                for (int i = 0; i < this.b.a.length; i++) {
                    if (i == i) {
                        this.b.a[i] = Boolean.valueOf(true);
                    } else {
                        this.b.a[i] = Boolean.valueOf(false);
                    }
                }
                this.b.b.sendMessage(new Message());
            }
        });
        return view;
    }
}
