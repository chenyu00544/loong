package com.ecjia.hamster.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;

public class ECJiaWithdrawDetaiActivity$$ViewBinder<T extends ECJiaWithdrawDetaiActivity> implements ViewBinder<T> {

    /* compiled from: ECJiaWithdrawDetaiActivity$$ViewBinder */
    protected static class a<T extends ECJiaWithdrawDetaiActivity> implements Unbinder {
        private T a;

        protected a(T t) {
            this.a = t;
        }

        public final void unbind() {
            if (this.a == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            a(this.a);
            this.a = null;
        }

        protected void a(T t) {
            t.topviewWithdrawDetail = null;
            t.tvWithdrawScheduleTime = null;
        }
    }

    public Unbinder bind(Finder finder, T t, Object obj) {
        Unbinder createUnbinder = createUnbinder(t);
        t.topviewWithdrawDetail = (ECJiaTopView) finder.castView((View) finder.findRequiredView(obj, R.id.topview_withdraw_detail, "field 'topviewWithdrawDetail'"), R.id.topview_withdraw_detail, "field 'topviewWithdrawDetail'");
        t.tvWithdrawScheduleTime = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_withdraw_schedule_time, "field 'tvWithdrawScheduleTime'"), R.id.tv_withdraw_schedule_time, "field 'tvWithdrawScheduleTime'");
        return createUnbinder;
    }

    protected a<T> createUnbinder(T t) {
        return new a(t);
    }
}
