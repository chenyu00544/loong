package com.ecjia.hamster.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;

public class ECJiaVerifyScheduleActivity$$ViewBinder<T extends ECJiaVerifyScheduleActivity> implements ViewBinder<T> {

    /* compiled from: ECJiaVerifyScheduleActivity$$ViewBinder */
    protected static class a<T extends ECJiaVerifyScheduleActivity> implements Unbinder {
        View a;
        private T b;

        protected a(T t) {
            this.b = t;
        }

        public final void unbind() {
            if (this.b == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            a(this.b);
            this.b = null;
        }

        protected void a(T t) {
            t.lineToStep3 = null;
            t.ivStep3 = null;
            t.tvStep3 = null;
            t.tvStep3Desc = null;
            t.tvCredentialName = null;
            t.tvCredentialNumber = null;
            t.tvBankName = null;
            t.tvBankCard = null;
            t.tvVerifyRemark = null;
            t.llVerifyRemark = null;
            this.a.setOnClickListener(null);
            t.btnChangeInfo = null;
            t.topviewRealNameVerify = null;
        }
    }

    public Unbinder bind(Finder finder, final T t, Object obj) {
        Unbinder createUnbinder = createUnbinder(t);
        t.lineToStep3 = (View) finder.findRequiredView(obj, R.id.line_to_step3, "field 'lineToStep3'");
        t.ivStep3 = (ImageView) finder.castView((View) finder.findRequiredView(obj, R.id.iv_step3, "field 'ivStep3'"), R.id.iv_step3, "field 'ivStep3'");
        t.tvStep3 = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_step3, "field 'tvStep3'"), R.id.tv_step3, "field 'tvStep3'");
        t.tvStep3Desc = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_step3_desc, "field 'tvStep3Desc'"), R.id.tv_step3_desc, "field 'tvStep3Desc'");
        t.tvCredentialName = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_credential_name, "field 'tvCredentialName'"), R.id.tv_credential_name, "field 'tvCredentialName'");
        t.tvCredentialNumber = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_credential_number, "field 'tvCredentialNumber'"), R.id.tv_credential_number, "field 'tvCredentialNumber'");
        t.tvBankName = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_bank_name, "field 'tvBankName'"), R.id.tv_bank_name, "field 'tvBankName'");
        t.tvBankCard = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_bank_card, "field 'tvBankCard'"), R.id.tv_bank_card, "field 'tvBankCard'");
        t.tvVerifyRemark = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_verify_remark, "field 'tvVerifyRemark'"), R.id.tv_verify_remark, "field 'tvVerifyRemark'");
        t.llVerifyRemark = (LinearLayout) finder.castView((View) finder.findRequiredView(obj, R.id.ll_verify_remark, "field 'llVerifyRemark'"), R.id.ll_verify_remark, "field 'llVerifyRemark'");
        View view = (View) finder.findRequiredView(obj, R.id.btn_change_info, "field 'btnChangeInfo' and method 'onClick'");
        t.btnChangeInfo = (Button) finder.castView(view, R.id.btn_change_info, "field 'btnChangeInfo'");
        createUnbinder.a = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ECJiaVerifyScheduleActivity$$ViewBinder b;

            public void doClick(View view) {
                t.onClick(view);
            }
        });
        t.topviewRealNameVerify = (ECJiaTopView) finder.castView((View) finder.findRequiredView(obj, R.id.topview_real_name_verify, "field 'topviewRealNameVerify'"), R.id.topview_real_name_verify, "field 'topviewRealNameVerify'");
        return createUnbinder;
    }

    protected a<T> createUnbinder(T t) {
        return new a(t);
    }
}
