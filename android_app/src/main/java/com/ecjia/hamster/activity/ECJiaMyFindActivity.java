package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.l;
import com.ecjia.hamster.adapter.ak;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.ECJiaPushActivity;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaMyFindActivity extends a implements OnClickListener, a {
    private ImageView a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private LinearLayout n;
    private TextView o;
    private ScrollView p;
    private l q;
    private ListView r;
    private ak s;
    private LinearLayout t;
    private LinearLayout u;

    class ECJiaMyFindActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_1(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaMyFindActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_2(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaGroupbuyGoodsActivity.class));
        }
    }

    class ECJiaMyFindActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_3(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMobilebuyGoodsActivity.class));
        }
    }

    class ECJiaMyFindActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_4(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaFindHotNewsActivity.class));
        }
    }

    class ECJiaMyFindActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_5(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMyCaptureActivity.class));
        }
    }

    class ECJiaMyFindActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_6(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaLastBrowseActivity.class));
        }
    }

    class ECJiaMyFindActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_7(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaPushActivity.class));
        }
    }

    class ECJiaMyFindActivity_8 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_8(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaMapActivity.class));
        }
    }

    class ECJiaMyFindActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaMyFindActivity a;

        ECJiaMyFindActivity_9(ECJiaMyFindActivity eCJiaMyFindActivity) {
            this.a = eCJiaMyFindActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaHelpListActivity.class));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_find);
        PushAgent.getInstance(this).onAppStart();
        b();
        this.q.j();
    }

    private void b() {
        this.r = (ListView) findViewById(R.id.customer_find_list);
        if (this.q == null) {
            this.q = new l(this);
        }
        this.s = new ak(this, this.q.a);
        this.q.a((a) this);
        this.r.setAdapter(this.s);
        this.o = (TextView) findViewById(R.id.top_view_text);
        this.p = (ScrollView) findViewById(R.id.scroll);
        this.a = (ImageView) findViewById(R.id.top_view_back);
        this.b = (LinearLayout) findViewById(R.id.myfind_zxing_item);
        this.c = (LinearLayout) findViewById(R.id.myfind_lastbrowse);
        this.d = (LinearLayout) findViewById(R.id.myfind_push);
        this.e = (LinearLayout) findViewById(R.id.myfind_map);
        this.k = (LinearLayout) findViewById(R.id.myfind_help);
        this.l = (LinearLayout) findViewById(R.id.myfind_groupbuy_item);
        this.m = (LinearLayout) findViewById(R.id.myfind_hot_item);
        this.n = (LinearLayout) findViewById(R.id.myfind_mobile_item);
        this.t = (LinearLayout) findViewById(R.id.myfind_message_item);
        this.u = (LinearLayout) findViewById(R.id.myfind_consult_item);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.o.setText(getBaseContext().getResources().getString(R.string.find_find));
        this.a.setOnClickListener(new ECJiaMyFindActivity_1(this));
        this.l.setOnClickListener(new ECJiaMyFindActivity_2(this));
        this.n.setOnClickListener(new ECJiaMyFindActivity_3(this));
        this.m.setOnClickListener(new ECJiaMyFindActivity_4(this));
        this.b.setOnClickListener(new ECJiaMyFindActivity_5(this));
        this.c.setOnClickListener(new ECJiaMyFindActivity_6(this));
        this.d.setOnClickListener(new ECJiaMyFindActivity_7(this));
        this.e.setOnClickListener(new ECJiaMyFindActivity_8(this));
        this.k.setOnClickListener(new ECJiaMyFindActivity_9(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myfind_message_item:
                startActivity(new Intent(this, ECJiaPushActivity.class));
                return;
            case R.id.myfind_consult_item:
                Intent intent = new Intent(this, ECJiaWebViewActivity.class);
                intent.putExtra("url", "http://www.missmall.com/mobile/index.php?m=chat");
                intent.putExtra("title", "");
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (!"home/discover".equals(str)) {
            return;
        }
        if (axVar.b() != 1) {
            this.r.setVisibility(8);
        } else if (this.q.a.size() > 0) {
            this.r.setVisibility(0);
            this.s.notifyDataSetChanged();
            this.p.smoothScrollTo(0, 0);
        } else {
            this.r.setVisibility(8);
        }
    }
}
