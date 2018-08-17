package com.ecjia.hamster.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.z;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;

public class ECJiaVerifyScheduleActivity extends a implements a {
    private z a;
    private String b;
    @BindView(2131558611)
    Button btnChangeInfo;
    @BindView(2131560021)
    ImageView ivStep3;
    @BindView(2131560020)
    View lineToStep3;
    @BindView(2131558609)
    LinearLayout llVerifyRemark;
    @BindView(2131558546)
    ECJiaTopView topviewRealNameVerify;
    @BindView(2131558608)
    TextView tvBankCard;
    @BindView(2131558607)
    TextView tvBankName;
    @BindView(2131558605)
    TextView tvCredentialName;
    @BindView(2131558606)
    TextView tvCredentialNumber;
    @BindView(2131560022)
    TextView tvStep3;
    @BindView(2131560023)
    TextView tvStep3Desc;
    @BindView(2131558610)
    TextView tvVerifyRemark;

    class ECJiaVerifyScheduleActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaVerifyScheduleActivity a;

        ECJiaVerifyScheduleActivity_1(ECJiaVerifyScheduleActivity eCJiaVerifyScheduleActivity) {
            this.a = eCJiaVerifyScheduleActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_wait_verify);
        ButterKnife.bind((Activity) this);
        this.a = new z(this);
        this.a.a((a) this);
        this.b = getIntent().getStringExtra("real_id");
        b();
        this.a.a(this.h.e().m(), this.b, "", "", "", "", "", "");
    }

    private void b() {
        this.topviewRealNameVerify.setTitleText((int) R.string.real_name_verify);
        this.topviewRealNameVerify.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaVerifyScheduleActivity_1(this));
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals(" user/account/realname/verify")) {
            return;
        }
        if (axVar.b() == 1) {
            c();
            return;
        }
        k kVar = new k((Context) this, axVar.d());
        kVar.a(17, 0, 0);
        kVar.a();
    }

    private void c() {
        this.tvCredentialName.setText(this.a.a.b());
        this.tvCredentialNumber.setText(this.a.a.d());
        this.tvBankName.setText(this.a.a.e());
        this.tvBankCard.setText(this.a.a.f());
        switch (this.a.a.c()) {
            case 2:
                this.lineToStep3.setVisibility(0);
                this.tvStep3.setVisibility(8);
                this.ivStep3.setVisibility(0);
                this.tvStep3Desc.setText(R.string.verify_failed);
                this.llVerifyRemark.setVisibility(0);
                this.btnChangeInfo.setVisibility(0);
                this.tvVerifyRemark.setText(this.a.a.i());
                return;
            case 3:
                this.lineToStep3.setVisibility(0);
                this.tvStep3.setBackgroundResource(R.drawable.oval_theme);
                this.tvStep3.setVisibility(0);
                this.ivStep3.setVisibility(8);
                return;
            default:
                return;
        }
    }

    @OnClick({2131558611})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_info:
                finish();
                Intent intent = new Intent(this, ECJiaRealNameVerifyActivity.class);
                intent.putExtra("real_id", this.b);
                startActivity(intent);
                return;
            default:
                return;
        }
    }
}
