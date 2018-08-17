package com.ecjia.hamster.module.goodsReturn.activity;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ecjia.a.y;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_DETAIL;
import com.ecmoban.android.missmall.R;

public class ECJiaReturnFeeDetailActivity extends a implements com.ecjia.component.a.a.a {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    String e;
    com.ecjia.hamster.module.goodsReturn.a k;
    ECJia_RETURN_DETAIL l;

    class ECJiaReturnFeeDetailActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnFeeDetailActivity a;

        ECJiaReturnFeeDetailActivity_1(ECJiaReturnFeeDetailActivity eCJiaReturnFeeDetailActivity) {
            this.a = eCJiaReturnFeeDetailActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_return_fee_detial);
        c();
        b();
    }

    private void b() {
        this.l = (ECJia_RETURN_DETAIL) getIntent().getSerializableExtra("return_detail");
        if (this.l != null) {
            a(this.l);
            return;
        }
        this.e = getIntent().getStringExtra("return_id");
        this.k = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.k.a((com.ecjia.component.a.a.a) this);
        this.k.b(this.e);
    }

    void a(ECJia_RETURN_DETAIL eCJia_RETURN_DETAIL) {
        this.a.setText(eCJia_RETURN_DETAIL.getFormatted_refund_price());
        this.b.setText(eCJia_RETURN_DETAIL.getFormatted_refund_price());
        this.c.setText(eCJia_RETURN_DETAIL.getCreate_time());
        if (eCJia_RETURN_DETAIL.getReturn_status().equals("refunded") || eCJia_RETURN_DETAIL.getReturn_status().equals("finished")) {
            String label_return_status = eCJia_RETURN_DETAIL.getLabel_return_status();
            String replace = this.g.getString(R.string.return_fee_detail_process_success_desc).replace("#replace#", eCJia_RETURN_DETAIL.getService_phone());
            CharSequence spannableString = new SpannableString(label_return_status + replace);
            spannableString.setSpan(new AbsoluteSizeSpan(y.a(this, 14)), 0, label_return_status.length(), 17);
            spannableString.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), 0, label_return_status.length(), 17);
            spannableString.setSpan(new AbsoluteSizeSpan(y.a(this, 12)), label_return_status.length(), label_return_status.length() + replace.length(), 17);
            spannableString.setSpan(new ForegroundColorSpan(-7829368), label_return_status.length(), label_return_status.length() + replace.length(), 17);
            this.d.setText(spannableString);
            return;
        }
        label_return_status = eCJia_RETURN_DETAIL.getLabel_return_status();
        replace = this.g.getString(R.string.return_fee_detail_process_ing_desc).replace("#replace#", eCJia_RETURN_DETAIL.getService_phone());
        spannableString = new SpannableString(label_return_status + replace);
        spannableString.setSpan(new AbsoluteSizeSpan(y.a(this, 14)), 0, label_return_status.length(), 17);
        spannableString.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), 0, label_return_status.length(), 17);
        spannableString.setSpan(new AbsoluteSizeSpan(y.a(this, 12)), label_return_status.length(), label_return_status.length() + replace.length(), 17);
        spannableString.setSpan(new ForegroundColorSpan(-7829368), label_return_status.length(), label_return_status.length() + replace.length(), 17);
        this.d.setText(spannableString);
    }

    private void c() {
        a();
        this.a = (TextView) findViewById(R.id.return_fee);
        this.b = (TextView) findViewById(R.id.return_fee_2);
        this.c = (TextView) findViewById(R.id.apply_time);
        this.d = (TextView) findViewById(R.id.apply_status);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_fee_detail_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_refund_details_title);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnFeeDetailActivity_1(this));
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/detail".equals(str) && axVar.b() == 1) {
            this.l = this.k.c;
            a(this.l);
        }
    }
}
