package com.ecjia.hamster.activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.h;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.consts.b;
import com.ecjia.hamster.adapter.e;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.j;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaCollectActivity extends a implements a, ECJiaXListView.a {
    j a;
    public Handler b;
    public Resources c;
    private ImageView d;
    private TextView e;
    private ECJiaXListView k;
    private e l;
    private boolean m = false;
    private h n;
    private ProgressDialog o;
    private View p;

    class ECJiaCollectActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaCollectActivity a;

        ECJiaCollectActivity_1(ECJiaCollectActivity eCJiaCollectActivity) {
            this.a = eCJiaCollectActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaCollectActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaCollectActivity a;

        ECJiaCollectActivity_2(ECJiaCollectActivity eCJiaCollectActivity) {
            this.a = eCJiaCollectActivity;
        }

        public void onClick(View view) {
            this.a.c = this.a.getBaseContext().getResources();
            CharSequence string = this.a.c.getString(R.string.collect_done);
            String string2 = this.a.c.getString(R.string.collect_cancel_collect);
            String string3 = this.a.c.getString(R.string.collect_cancel_sure);
            this.a.c.getString(R.string.collect_delete_success);
            final CharSequence string4 = this.a.c.getString(R.string.collect_compile);
            if (this.a.m) {
                this.a.k.setPullRefreshEnable(true);
                this.a.m = false;
                if (this.a.e()) {
                    for (int i = 0; i < b.c.size(); i++) {
                        q.a(i + "需要删除====" + ((String) ((HashMap) b.c.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true"));
                    }
                    final c cVar = new c(this.a, string2, string3);
                    cVar.a();
                    cVar.b.setOnClickListener(new OnClickListener(this) {
                        Resources a = this.e.a.getBaseContext().getResources();
                        String b = this.a.getString(R.string.collect_delete_success);
                        final /* synthetic */ ECJiaCollectActivity_2 e;

                        public void onClick(View view) {
                            cVar.b();
                            this.e.a.b();
                            de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("userinfo_refresh"));
                            k kVar = new k(this.e.a, this.b);
                            kVar.a(17, 0, 0);
                            kVar.a();
                            this.e.a.l.a = 1;
                            this.e.a.l.notifyDataSetChanged();
                            this.e.a.e.setText(string4);
                        }
                    });
                    cVar.d.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaCollectActivity_2 c;

                        public void onClick(View view) {
                            this.c.a.f();
                            this.c.a.l.a = 1;
                            this.c.a.l.notifyDataSetChanged();
                            this.c.a.e.setText(string4);
                            cVar.b();
                        }
                    });
                    return;
                }
                this.a.l.a = 1;
                this.a.l.notifyDataSetChanged();
                this.a.e.setText(string4);
                return;
            }
            this.a.l.a = 2;
            this.a.l.notifyDataSetChanged();
            this.a.k.setPullRefreshEnable(false);
            this.a.m = true;
            this.a.e.setText(string);
        }
    }

    class ECJiaCollectActivity_3 extends Handler {
        final /* synthetic */ ECJiaCollectActivity a;

        ECJiaCollectActivity_3(ECJiaCollectActivity eCJiaCollectActivity) {
            this.a = eCJiaCollectActivity;
        }

        public void handleMessage(Message message) {
            this.a.c = this.a.getBaseContext().getResources();
            CharSequence string = this.a.c.getString(R.string.collect_done);
            CharSequence string2 = this.a.c.getString(R.string.collect_delete);
            if (message.arg1 == 0) {
                this.a.e.setText(string);
            } else if (message.arg1 == 1) {
                this.a.e.setText(string2);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.collect);
        PushAgent.getInstance(this).onAppStart();
        de.greenrobot.event.c.a().a((Object) this);
        this.d = (ImageView) findViewById(R.id.collect_back);
        this.d.setOnClickListener(new ECJiaCollectActivity_1(this));
        if (b.c == null) {
            b.c = new ArrayList();
        }
        this.p = findViewById(R.id.null_pager);
        this.e = (TextView) findViewById(R.id.collect_edit);
        this.e.setEnabled(false);
        this.k = (ECJiaXListView) findViewById(R.id.collect_list);
        this.k.setPullLoadEnable(true);
        this.k.setRefreshTime();
        this.k.setXListViewListener(this, 1);
        this.e.setOnClickListener(new ECJiaCollectActivity_2(this));
        this.b = new ECJiaCollectActivity_3(this);
        this.n = new h(this);
        this.n.a((a) this);
        this.n.a(true);
    }

    private boolean e() {
        for (int i = 0; i < b.c.size(); i++) {
            if (((String) ((HashMap) b.c.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true")) {
                return true;
            }
        }
        return false;
    }

    void b() {
        if (this.o == null) {
            this.o = new ProgressDialog(this);
        }
        this.o.show();
        int i = 0;
        while (i < b.c.size()) {
            if (((String) ((HashMap) b.c.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true")) {
                try {
                    this.a = j.a(new JSONObject((String) ((HashMap) b.c.get(i)).get("content")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.n.a(this.a.i() + "");
                b.c.remove(i);
                i--;
            }
            i++;
        }
        this.l.b = b.c;
        this.o.dismiss();
    }

    public void c() {
        getBaseContext().getResources();
        if (this.n.a.size() == 0) {
            this.e.setEnabled(false);
            this.e.setVisibility(8);
            if (this.l != null) {
                this.l.b = b.c;
                this.l.notifyDataSetChanged();
            }
            this.p.setVisibility(0);
            this.k.setVisibility(8);
            return;
        }
        this.k.setVisibility(0);
        this.p.setVisibility(8);
        a(this.n.a);
        this.e.setEnabled(true);
        this.e.setVisibility(0);
        if (this.l == null) {
            this.l = new e(this, b.c, 1);
            this.k.setAdapter(this.l);
        } else {
            this.l.b = b.c;
            this.l.notifyDataSetChanged();
        }
        this.l.d = this.b;
    }

    private void a(ArrayList<j> arrayList) {
        int i;
        HashMap hashMap;
        if (b.c.size() == 0) {
            for (i = 0; i < arrayList.size(); i++) {
                hashMap = new HashMap();
                try {
                    hashMap.put("content", ((j) arrayList.get(i)).j().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hashMap.put(AgooConstants.MESSAGE_FLAG, "false");
                b.c.add(hashMap);
            }
            return;
        }
        for (i = b.c.size() + 0; i < arrayList.size(); i++) {
            hashMap = new HashMap();
            try {
                hashMap.put("content", ((j) arrayList.get(i)).j().toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            hashMap.put(AgooConstants.MESSAGE_FLAG, "false");
            b.c.add(hashMap);
        }
    }

    private void f() {
        for (int i = 0; i < b.c.size(); i++) {
            if (((String) ((HashMap) b.c.get(i)).get(AgooConstants.MESSAGE_FLAG)).equals("true")) {
                ((HashMap) b.c.get(i)).put(AgooConstants.MESSAGE_FLAG, "false");
            }
        }
    }

    public void a(int i) {
        this.c = getBaseContext().getResources();
        CharSequence string = this.c.getString(R.string.collect_compile);
        b.c.clear();
        this.l.a = 1;
        this.e.setText(string);
        this.n.a(false);
    }

    public void b(int i) {
        this.n.a();
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        b.c.clear();
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
    }

    public void a(String str, String str2, ax axVar) {
        if (str == "user/collect/list" && axVar.b() == 1) {
            this.k.setRefreshTime();
            this.k.stopRefresh();
            this.k.stopLoadMore();
            if (this.n.b.a() == 0) {
                this.k.setPullLoadEnable(false);
            } else {
                this.k.setPullLoadEnable(true);
            }
            c();
        }
        if (str == "user/collect/delete" && b.c.size() == 0) {
            this.e.setEnabled(false);
            this.p.setVisibility(0);
            this.k.setVisibility(8);
        }
    }
}
