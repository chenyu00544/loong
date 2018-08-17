package com.ecjia.hamster.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.ECJiaXListView.a;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bu;
import com.ecjia.hamster.adapter.p;
import com.ecmoban.android.missmall.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaLastBrowseActivity extends a implements a {
    private ECJiaXListView a;
    private p b;
    private View c;
    private c d;
    private ProgressDialog e;
    private bu k;
    private ArrayList<com.ecjia.hamster.model.p> l = new ArrayList();

    class ECJiaLastBrowseActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaLastBrowseActivity a;

        ECJiaLastBrowseActivity_1(ECJiaLastBrowseActivity eCJiaLastBrowseActivity) {
            this.a = eCJiaLastBrowseActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaLastBrowseActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaLastBrowseActivity a;

        ECJiaLastBrowseActivity_2(ECJiaLastBrowseActivity eCJiaLastBrowseActivity) {
            this.a = eCJiaLastBrowseActivity;
        }

        public void onClick(View view) {
            this.a.e();
        }
    }

    class ECJiaLastBrowseActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaLastBrowseActivity a;

        ECJiaLastBrowseActivity_3(ECJiaLastBrowseActivity eCJiaLastBrowseActivity) {
            this.a = eCJiaLastBrowseActivity;
        }

        public void onClick(View view) {
            this.a.d.b();
            this.a.e = new ProgressDialog(this.a);
            this.a.e.show();
            if (this.a.k == null) {
                this.a.k = new bu(this.a);
            }
            this.a.k.b();
            this.a.l.clear();
            this.a.i.getRightTextView().setVisibility(8);
            this.a.a(1);
            this.a.e.dismiss();
        }
    }

    class ECJiaLastBrowseActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaLastBrowseActivity a;

        ECJiaLastBrowseActivity_4(ECJiaLastBrowseActivity eCJiaLastBrowseActivity) {
            this.a = eCJiaLastBrowseActivity;
        }

        public void onClick(View view) {
            this.a.d.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_last_browse);
        a();
        this.c = findViewById(R.id.null_pager);
        this.a = (ECJiaXListView) findViewById(R.id.trade_list);
        this.a.setPullLoadEnable(false);
        this.a.setPullRefreshEnable(false);
        this.a.setRefreshTime();
        this.a.setXListViewListener(this, 1);
        c();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.last_browse_topview);
        this.i.setTitleText((int) R.string.lastbrowse);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaLastBrowseActivity_1(this));
        this.i.setRightType(11);
        this.i.setRightText((int) R.string.top_clean, new ECJiaLastBrowseActivity_2(this));
    }

    private void c() {
        this.k = new bu(this);
        Cursor a = this.k.a();
        while (a.moveToNext()) {
            a.getInt(1);
            Object string = a.getString(2);
            Object obj = null;
            if (!TextUtils.isEmpty(string)) {
                try {
                    obj = com.ecjia.hamster.model.p.a(new JSONObject(string));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.l.add(obj);
        }
        b();
    }

    public void b() {
        String string = getBaseContext().getResources().getString(R.string.lastbrowse_no_browse);
        if (this.l.size() == 0) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
            this.c.setVisibility(0);
            this.i.getRightTextView().setVisibility(8);
            this.a.setVisibility(8);
            return;
        }
        this.c.setVisibility(8);
        this.a.setVisibility(0);
        if (this.b == null) {
            this.b = new p(this, this.l);
            this.a.setAdapter(this.b);
            return;
        }
        this.b.a = this.l;
        this.b.notifyDataSetChanged();
    }

    public void a(int i) {
        c();
    }

    public void b(int i) {
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        bu.a.close();
        MobclickAgent.onPause(this);
    }

    private void e() {
        Resources resources = getBaseContext().getResources();
        this.d = new c(this, resources.getString(R.string.lastbrowse_delete), resources.getString(R.string.lasebrowse_delete_sure));
        this.d.a(2);
        this.d.b(new ECJiaLastBrowseActivity_3(this));
        this.d.c(new ECJiaLastBrowseActivity_4(this));
        this.d.a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return true;
    }
}
