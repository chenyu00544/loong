package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.hamster.adapter.at;
import com.ecjia.hamster.b.a;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;

public class ECJiaPaymentActivity extends a implements a {
    private TextView a;
    private ImageView b;
    private ListView c;
    private ListView d;
    private at e;
    private at k;
    private ArrayList<ECJia_PAYMENT> l;
    private ArrayList<ECJia_PAYMENT> m;
    private LinearLayout n;
    private LinearLayout o;

    class ECJiaPaymentActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaPaymentActivity a;

        ECJiaPaymentActivity_1(ECJiaPaymentActivity eCJiaPaymentActivity) {
            this.a = eCJiaPaymentActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_paymentlist);
        PushAgent.getInstance(this).onAppStart();
        c();
        b();
    }

    private void b() {
        Intent intent = getIntent();
        this.l = (ArrayList) intent.getSerializableExtra("payment_list_online");
        this.m = (ArrayList) intent.getSerializableExtra("payment_list_offline");
        if (this.l == null || this.l.size() <= 0) {
            this.n.setVisibility(8);
        } else {
            this.e = new at(this, this.l, 1);
            this.e.a((a) this);
            this.c.setAdapter(this.e);
        }
        if (this.m == null || this.m.size() <= 0) {
            this.o.setVisibility(8);
            return;
        }
        this.k = new at(this, this.m, 2);
        this.k.a((a) this);
        this.d.setAdapter(this.k);
    }

    private void c() {
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.a.setText(getBaseContext().getResources().getString(R.string.balance_pay));
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.b.setOnClickListener(new ECJiaPaymentActivity_1(this));
        this.c = (ListView) findViewById(R.id.payment_list);
        this.d = (ListView) findViewById(R.id.payment_list1);
        this.n = (LinearLayout) findViewById(R.id.payment_onlineitem);
        this.o = (LinearLayout) findViewById(R.id.payment_uplineitem);
    }

    public void a(int i, int i2, Object obj) {
        int i3;
        if (i == 1) {
            if (this.m != null && this.m.size() > 0) {
                for (i3 = 0; i3 < this.m.size(); i3++) {
                    ((ECJia_PAYMENT) this.m.get(i3)).setSelected(false);
                }
                this.k.notifyDataSetChanged();
            }
        } else if (i == 2 && this.l != null && this.l.size() > 0) {
            for (i3 = 0; i3 < this.l.size(); i3++) {
                ((ECJia_PAYMENT) this.l.get(i3)).setSelected(false);
            }
            this.e.notifyDataSetChanged();
        }
        if (obj instanceof ECJia_PAYMENT) {
            Intent intent = new Intent();
            intent.putExtra("payment", (ECJia_PAYMENT) obj);
            setResult(-1, intent);
            finish();
        }
    }
}
