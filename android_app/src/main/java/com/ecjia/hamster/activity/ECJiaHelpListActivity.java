package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.q;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.adapter.v;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ECJiaHelpListActivity extends a implements a {
    q a;
    StickyListHeadersListView b;
    v c;

    class ECJiaHelpListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaHelpListActivity a;

        ECJiaHelpListActivity_1(ECJiaHelpListActivity eCJiaHelpListActivity) {
            this.a = eCJiaHelpListActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.help_list);
        PushAgent.getInstance(this).onAppStart();
        a();
        this.b = (StickyListHeadersListView) findViewById(R.id.help_listview);
        this.b.setDivider(ContextCompat.getDrawable(this, R.drawable.stick_list_diver));
        this.a = new q(this);
        this.a.a((a) this);
        this.a.b();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.helplist_topview);
        this.i.setTitleText((int) R.string.main_help);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaHelpListActivity_1(this));
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("shop/help") && axVar.b() == 1) {
            this.c = new v(this, this.a.a);
            this.b.setAdapter(this.c);
            this.c.notifyDataSetChanged();
        }
    }
}
