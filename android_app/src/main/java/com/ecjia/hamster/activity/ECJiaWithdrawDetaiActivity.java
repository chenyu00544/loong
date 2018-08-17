package com.ecjia.hamster.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ecjia.a.ab;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;

public class ECJiaWithdrawDetaiActivity extends a {
    @BindView(2131558612)
    ECJiaTopView topviewWithdrawDetail;
    @BindView(2131558613)
    TextView tvWithdrawScheduleTime;

    class ECJiaWithdrawDetaiActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaWithdrawDetaiActivity a;

        ECJiaWithdrawDetaiActivity_1(ECJiaWithdrawDetaiActivity eCJiaWithdrawDetaiActivity) {
            this.a = eCJiaWithdrawDetaiActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_withdraw_detail);
        ButterKnife.bind((Activity) this);
        b();
    }

    private void b() {
        this.topviewWithdrawDetail.setTitleText((int) R.string.withdraw_result_detail);
        this.topviewWithdrawDetail.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaWithdrawDetaiActivity_1(this));
        this.tvWithdrawScheduleTime.setText(ab.b("yyyy-MM-dd HH:mm:ss"));
    }
}
