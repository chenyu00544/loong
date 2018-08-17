package com.ecjia.hamster.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.model.h;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;

/* compiled from: ECJiaSigninAdapter */
public class br extends BaseAdapter {
    private Context a;
    private ArrayList<h> b;
    private LayoutInflater c;

    /* compiled from: ECJiaSigninAdapter */
    class a {
        public View a;
        final /* synthetic */ br b;
        private ImageView c;
        private TextView d;
        private TextView e;
        private TextView f;

        a(br brVar) {
            this.b = brVar;
        }
    }

    public br(Context context, ArrayList<h> arrayList) {
        this.a = context;
        this.b = arrayList;
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.c.inflate(R.layout.layout_signin_adpter, viewGroup, false);
            aVar.c = (ImageView) view.findViewById(R.id.signin_userimage);
            aVar.d = (TextView) view.findViewById(R.id.signin_username);
            aVar.e = (TextView) view.findViewById(R.id.signin_signtime);
            aVar.f = (TextView) view.findViewById(R.id.signin_award);
            aVar.a = view.findViewById(R.id.signin_bottomline);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (TextUtils.isEmpty(((h) this.b.get(i)).d())) {
            aVar.c.setImageResource(R.drawable.profile_no_avarta_icon_light);
        } else {
            p.a(this.a).a(aVar.c, ((h) this.b.get(i)).d(), 9003);
        }
        CharSequence c = ((h) this.b.get(i)).c();
        if (c.length() == 11) {
            try {
                Long.valueOf(c);
                StringBuffer stringBuffer = new StringBuffer();
                int i2 = 0;
                while (i2 < c.length()) {
                    if (i2 < 3 || i2 > 8) {
                        stringBuffer.append("*");
                    } else {
                        stringBuffer.append(c.charAt(i2));
                    }
                    i2++;
                }
                aVar.d.setText(stringBuffer.toString());
            } catch (NumberFormatException e) {
                aVar.d.setText(c);
            }
        } else {
            aVar.d.setText(((h) this.b.get(i)).c());
        }
        aVar.d.setText(((h) this.b.get(i)).c());
        aVar.e.setText(((h) this.b.get(i)).b());
        aVar.f.setText(SocializeConstants.OP_DIVIDER_PLUS + ((h) this.b.get(i)).e());
        if (this.b.size() == 1 || i == this.b.size() - 1) {
            aVar.a.setVisibility(4);
        } else {
            aVar.a.setVisibility(0);
        }
        return view;
    }
}
