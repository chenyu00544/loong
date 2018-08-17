package com.ecjia.hamster.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;

public class ECJiaRealNameVerifyActivity$$ViewBinder<T extends ECJiaRealNameVerifyActivity> implements ViewBinder<T> {

    /* compiled from: ECJiaRealNameVerifyActivity$$ViewBinder */
    protected static class a<T extends ECJiaRealNameVerifyActivity> implements Unbinder {
        View a;
        View b;
        View c;
        View d;
        View e;
        private T f;

        protected a(T t) {
            this.f = t;
        }

        public final void unbind() {
            if (this.f == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            a(this.f);
            this.f = null;
        }

        protected void a(T t) {
            t.etCredentialName = null;
            t.etCredentialNumber = null;
            t.etBankName = null;
            t.etBankCard = null;
            this.a.setOnClickListener(null);
            t.llCredentialFront = null;
            this.b.setOnClickListener(null);
            t.ivCredentialFront = null;
            this.c.setOnClickListener(null);
            t.llCredentialBack = null;
            this.d.setOnClickListener(null);
            t.ivCredentialBack = null;
            this.e.setOnClickListener(null);
            t.btnSubmit = null;
            t.topviewRealNameVerify = null;
        }
    }

    public Unbinder bind(Finder finder, final T t, Object obj) {
        Unbinder createUnbinder = createUnbinder(t);
        t.etCredentialName = (EditText) finder.castView((View) finder.findRequiredView(obj, R.id.et_credential_name, "field 'etCredentialName'"), R.id.et_credential_name, "field 'etCredentialName'");
        t.etCredentialNumber = (EditText) finder.castView((View) finder.findRequiredView(obj, R.id.et_credential_number, "field 'etCredentialNumber'"), R.id.et_credential_number, "field 'etCredentialNumber'");
        t.etBankName = (EditText) finder.castView((View) finder.findRequiredView(obj, R.id.et_bank_name, "field 'etBankName'"), R.id.et_bank_name, "field 'etBankName'");
        t.etBankCard = (EditText) finder.castView((View) finder.findRequiredView(obj, R.id.et_bank_card, "field 'etBankCard'"), R.id.et_bank_card, "field 'etBankCard'");
        View view = (View) finder.findRequiredView(obj, R.id.ll_credential_front, "field 'llCredentialFront' and method 'onClick'");
        t.llCredentialFront = (FrameLayout) finder.castView(view, R.id.ll_credential_front, "field 'llCredentialFront'");
        createUnbinder.a = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ECJiaRealNameVerifyActivity$$ViewBinder b;

            public void doClick(View view) {
                t.onClick(view);
            }
        });
        view = (View) finder.findRequiredView(obj, R.id.iv_credential_front, "field 'ivCredentialFront' and method 'onClick'");
        t.ivCredentialFront = (ImageView) finder.castView(view, R.id.iv_credential_front, "field 'ivCredentialFront'");
        createUnbinder.b = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ECJiaRealNameVerifyActivity$$ViewBinder b;

            public void doClick(View view) {
                t.onClick(view);
            }
        });
        view = (View) finder.findRequiredView(obj, R.id.ll_credential_back, "field 'llCredentialBack' and method 'onClick'");
        t.llCredentialBack = (FrameLayout) finder.castView(view, R.id.ll_credential_back, "field 'llCredentialBack'");
        createUnbinder.c = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ECJiaRealNameVerifyActivity$$ViewBinder b;

            public void doClick(View view) {
                t.onClick(view);
            }
        });
        view = (View) finder.findRequiredView(obj, R.id.iv_credential_back, "field 'ivCredentialBack' and method 'onClick'");
        t.ivCredentialBack = (ImageView) finder.castView(view, R.id.iv_credential_back, "field 'ivCredentialBack'");
        createUnbinder.d = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ECJiaRealNameVerifyActivity$$ViewBinder b;

            public void doClick(View view) {
                t.onClick(view);
            }
        });
        view = (View) finder.findRequiredView(obj, R.id.btn_submit, "field 'btnSubmit' and method 'onClick'");
        t.btnSubmit = (Button) finder.castView(view, R.id.btn_submit, "field 'btnSubmit'");
        createUnbinder.e = view;
        view.setOnClickListener(new DebouncingOnClickListener(this) {
            final /* synthetic */ ECJiaRealNameVerifyActivity$$ViewBinder b;

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
