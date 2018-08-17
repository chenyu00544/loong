package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.view.k;
import com.ecjia.consts.ECJiaClassName.ActivityName;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.activity.ECJiaWebViewActivity;
import com.ecjia.hamster.model.bf;
import com.ecjia.hamster.model.o;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaFoundLocalFunctionAdapter */
public class m extends b<o> {
    Activity a;
    LayoutParams b;
    int f;
    private Boolean g;
    private bf h;

    /* compiled from: ECJiaFoundLocalFunctionAdapter */
    class a {
        public View a;
        public TextView b;
        public ImageView c;
        public ImageView d;
        public View e;
        final /* synthetic */ m f;

        a(m mVar) {
            this.f = mVar;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public m(Activity activity, ArrayList<o> arrayList, Boolean bool, bf bfVar) {
        super(activity, arrayList);
        this.a = activity;
        this.f = this.a.getWindowManager().getDefaultDisplay().getWidth();
        this.b = new LayoutParams(-1, -1);
        this.b.height = this.f / 3;
        this.g = bool;
        this.h = bfVar;
    }

    protected com.ecjia.hamster.adapter.b.a a(View view) {
        return null;
    }

    protected View a(int i, View view, ViewGroup viewGroup, com.ecjia.hamster.adapter.b.a aVar) {
        return null;
    }

    public View a() {
        return null;
    }

    public int getCount() {
        if (this.e.size() <= 0 || this.e.size() % 3 == 0) {
            return this.e.size();
        }
        return ((this.e.size() / 3) + 1) * 3;
    }

    public o a(int i) {
        if (i >= this.e.size()) {
            return (o) this.e.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (null == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.layout_foundadapter_item, null);
            aVar.a = view.findViewById(R.id.found_item);
            aVar.c = (ImageView) view.findViewById(R.id.found_item_image);
            aVar.b = (TextView) view.findViewById(R.id.found_item_name);
            aVar.d = (ImageView) view.findViewById(R.id.found_item_add);
            aVar.e = view.findViewById(R.id.found_item_rightline);
            view.setLayoutParams(this.b);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i < this.e.size()) {
            if (i != this.e.size()) {
                aVar.d.setVisibility(8);
                aVar.b.setVisibility(0);
                aVar.c.setVisibility(0);
                aVar.c.setImageResource(((o) this.e.get(i)).e());
                aVar.b.setText(((o) this.e.get(i)).f());
                aVar.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ m b;

                    public void onClick(View view) {
                        if (((o) this.b.e.get(i)).h() && (((ECJiaApplication) this.b.a.getApplication()).e() == null || TextUtils.isEmpty(((ECJiaApplication) this.b.a.getApplication()).e().m()))) {
                            this.b.a.startActivityForResult(new Intent(this.b.a, ECJiaLoginActivity.class), ((o) this.b.e.get(i)).b());
                            this.b.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                            return;
                        }
                        try {
                            q.a("functionStart4===" + ((o) this.b.e.get(i)).g().getActivityName());
                            Intent intent;
                            if (!((o) this.b.e.get(i)).g().getActivityName().equals("com.ecjia.hamster.activity.ECJiaConsultActivity")) {
                                intent = new Intent();
                                intent.setClass(this.b.a, Class.forName(((o) this.b.e.get(i)).g().getActivityName()));
                                if (((o) this.b.e.get(i)).g().equals(ActivityName.QRSHARE)) {
                                    intent.putExtra("startType", 1);
                                }
                                if (((o) this.b.e.get(i)).a().equals("promotion")) {
                                    intent.putExtra("type", "promotion");
                                }
                                this.b.a.startActivity(intent);
                                this.b.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                            } else if (!com.ecjia.consts.a.b) {
                                intent = new Intent();
                                intent.setClass(this.b.a, Class.forName(((o) this.b.e.get(i)).g().getActivityName()));
                                if (((o) this.b.e.get(i)).g().equals(ActivityName.QRSHARE)) {
                                    intent.putExtra("startType", 1);
                                }
                                if (((o) this.b.e.get(i)).a().equals("promotion")) {
                                    intent.putExtra("type", "promotion");
                                }
                                this.b.a.startActivity(intent);
                                this.b.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                            } else if (this.b.g.booleanValue()) {
                                String str = "http://www.missmall.com/mobile/index.php?m=chat" + "&origin=app&openid=" + this.b.h.r() + "&token=" + this.b.h.b();
                                intent = new Intent(this.b.a, ECJiaWebViewActivity.class);
                                intent.putExtra("url", str);
                                intent.putExtra("title", "");
                                this.b.a.startActivity(intent);
                                this.b.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                            } else {
                                this.b.a.startActivityForResult(new Intent(this.b.a, ECJiaLoginActivity.class), 1111);
                                this.b.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                                k kVar = new k(this.b.a, "您还没有登录，请先登录");
                                kVar.a(17);
                                kVar.a();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (i % 3 == 2) {
                    aVar.e.setVisibility(8);
                }
            } else {
                aVar.d.setVisibility(0);
                aVar.b.setVisibility(8);
                aVar.c.setVisibility(8);
            }
        }
        return view;
    }
}
