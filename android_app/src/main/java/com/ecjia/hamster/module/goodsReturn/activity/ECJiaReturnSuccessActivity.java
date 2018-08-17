package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.hamster.activity.a;
import com.ecmoban.android.missmall.R;

public class ECJiaReturnSuccessActivity extends a {

    class ECJiaReturnSuccessActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnSuccessActivity a;

        ECJiaReturnSuccessActivity_1(ECJiaReturnSuccessActivity eCJiaReturnSuccessActivity) {
            this.a = eCJiaReturnSuccessActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            Intent intent = new Intent(this.a, ECJiaReturnProcessingActivity.class);
            intent.putExtra("return_id", this.a.getIntent().getStringExtra("return_id"));
            this.a.startActivity(intent);
        }
    }

    class ECJiaReturnSuccessActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReturnSuccessActivity a;

        ECJiaReturnSuccessActivity_2(ECJiaReturnSuccessActivity eCJiaReturnSuccessActivity) {
            this.a = eCJiaReturnSuccessActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            Intent intent = new Intent(this.a, ECJiaReturnOrderListActivity.class);
            intent.setFlags(67108864);
            this.a.startActivity(intent);
        }
    }

    class ECJiaReturnSuccessActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaReturnSuccessActivity a;

        ECJiaReturnSuccessActivity_3(ECJiaReturnSuccessActivity eCJiaReturnSuccessActivity) {
            this.a = eCJiaReturnSuccessActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_return_success);
        b();
    }

    private void b() {
        a();
        findViewById(R.id.return_process).setOnClickListener(new ECJiaReturnSuccessActivity_1(this));
        findViewById(R.id.return_list).setOnClickListener(new ECJiaReturnSuccessActivity_2(this));
        TextView textView = (TextView) findViewById(R.id.return_type);
        TextView textView2 = (TextView) findViewById(R.id.apply_time);
        String stringExtra = getIntent().getStringExtra("return_type");
        if (stringExtra.equals("service")) {
            textView.setText(R.string.return_repair);
        } else if (stringExtra.equals("return")) {
            textView.setText(R.string.return_return);
        }
        textView2.setText(getIntent().getStringExtra("apply_time"));
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_success_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_commit_succeed);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnSuccessActivity_3(this));
    }
}
