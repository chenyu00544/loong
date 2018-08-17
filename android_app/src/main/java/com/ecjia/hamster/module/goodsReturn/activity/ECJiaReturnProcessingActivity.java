package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.a.d;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_DETAIL;
import com.ecmoban.android.missmall.R;

public class ECJiaReturnProcessingActivity extends a implements com.ecjia.component.a.a.a {
    ECJia_RETURN_DETAIL a;
    ListView b;
    ImageView c;
    TextView d;
    TextView e;
    TextView k;
    View l;
    d m;
    com.ecjia.hamster.module.goodsReturn.a n;
    private View o;

    class ECJiaReturnProcessingActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnProcessingActivity a;

        ECJiaReturnProcessingActivity_1(ECJiaReturnProcessingActivity eCJiaReturnProcessingActivity) {
            this.a = eCJiaReturnProcessingActivity;
        }

        public void onClick(View view) {
            if (this.a.a != null) {
                final c cVar = new c(this.a, "", "确定取消本次售后申请吗？");
                cVar.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaReturnProcessingActivity_1 b;

                    public void onClick(View view) {
                        this.b.a.n.a(this.b.a.a.getReturn_id());
                        cVar.b();
                    }
                });
                cVar.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaReturnProcessingActivity_1 b;

                    public void onClick(View view) {
                        cVar.b();
                    }
                });
                cVar.a();
            }
        }
    }

    class ECJiaReturnProcessingActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReturnProcessingActivity a;

        ECJiaReturnProcessingActivity_2(ECJiaReturnProcessingActivity eCJiaReturnProcessingActivity) {
            this.a = eCJiaReturnProcessingActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_return_process);
        c();
        b();
    }

    private void c() {
        a();
        this.d = (TextView) findViewById(R.id.return_status);
        this.e = (TextView) findViewById(R.id.return_sn);
        this.k = (TextView) findViewById(R.id.apply_time);
        this.l = findViewById(R.id.finished_status);
        this.b = (ListView) findViewById(R.id.return_process_list);
        findViewById(R.id.cancel).setOnClickListener(new ECJiaReturnProcessingActivity_1(this));
        this.c = (ImageView) findViewById(R.id.return_status_image);
        this.o = findViewById(R.id.cancel_ll);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_process_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_audit_progress_title);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnProcessingActivity_2(this));
    }

    void b() {
        this.n = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.n.a((com.ecjia.component.a.a.a) this);
        this.a = (ECJia_RETURN_DETAIL) getIntent().getSerializableExtra("return_detail");
        if (this.a != null) {
            a(this.a);
            return;
        }
        Object stringExtra = getIntent().getStringExtra("return_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.n.b(stringExtra);
        }
    }

    void a(ECJia_RETURN_DETAIL eCJia_RETURN_DETAIL) {
        if (eCJia_RETURN_DETAIL.getReturn_status().equals("finished") || eCJia_RETURN_DETAIL.getReturn_status().equals("refunded")) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
        if (eCJia_RETURN_DETAIL.getReturn_status().equals("wait_check")) {
            this.o.setVisibility(0);
        } else {
            this.o.setVisibility(8);
        }
        a(eCJia_RETURN_DETAIL.getReturn_status());
        this.d.setText(eCJia_RETURN_DETAIL.getLabel_return_status());
        this.e.setText(eCJia_RETURN_DETAIL.getReturn_sn());
        this.k.setText(eCJia_RETURN_DETAIL.getCreate_time());
        this.m = new d(this, eCJia_RETURN_DETAIL.getReturn_log());
        this.b.setAdapter(this.m);
    }

    void a(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -1367724422:
                if (str.equals("cancel")) {
                    obj = 8;
                    break;
                }
                break;
            case -973954362:
                if (str.equals("wait_ship")) {
                    obj = 4;
                    break;
                }
                break;
            case -707924457:
                if (str.equals("refunded")) {
                    obj = 6;
                    break;
                }
                break;
            case -673660814:
                if (str.equals("finished")) {
                    obj = 7;
                    break;
                }
                break;
            case -646311515:
                if (str.equals("wait_process")) {
                    obj = 2;
                    break;
                }
                break;
            case -142594626:
                if (str.equals("wait_check")) {
                    obj = null;
                    break;
                }
                break;
            case 422194963:
                if (str.equals("processing")) {
                    obj = 3;
                    break;
                }
                break;
            case 547191841:
                if (str.equals("wait_return_ship")) {
                    obj = 5;
                    break;
                }
                break;
            case 1085547216:
                if (str.equals("refused")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
                this.c.setImageResource(R.drawable.icon_return_process_wait_check);
                return;
            case 2:
            case 3:
            case 4:
                this.c.setImageResource(R.drawable.icon_return_process_ing);
                return;
            case 5:
                this.c.setImageResource(R.drawable.icon_return_process_wait_ship);
                return;
            case 6:
            case 7:
                this.c.setImageResource(R.drawable.iconf_return_process_finished);
                return;
            case 8:
                this.c.setImageResource(R.drawable.icon_return_process_cancel);
                return;
            default:
                return;
        }
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/detail".equals(str)) {
            if (axVar.b() == 1) {
                this.a = this.n.c;
                a(this.a);
            }
        } else if ("order/return/cancel".equals(str) && axVar.b() == 1) {
            new k((Context) this, "申请成功").a();
            finish();
        }
    }
}
