package com.ecjia.hamster.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;

public class ECJiaPaymentAndShippingActivity$$ViewBinder<T extends ECJiaPaymentAndShippingActivity> implements ViewBinder<T> {

    /* compiled from: ECJiaPaymentAndShippingActivity$$ViewBinder */
    protected static class a<T extends ECJiaPaymentAndShippingActivity> implements Unbinder {
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
            t.topviewPaymentAndShipping = null;
            t.mgvPaymentOnline = null;
            t.llPaymentOnline = null;
            t.mgvPaymentOffline = null;
            t.llPaymentOffline = null;
            t.mlvShipping = null;
        }
    }

    public Unbinder bind(Finder finder, T t, Object obj) {
        Unbinder createUnbinder = createUnbinder(t);
        t.topviewPaymentAndShipping = (ECJiaTopView) finder.castView((View) finder.findRequiredView(obj, R.id.topview_payment_and_shipping, "field 'topviewPaymentAndShipping'"), R.id.topview_payment_and_shipping, "field 'topviewPaymentAndShipping'");
        t.mgvPaymentOnline = (ECJiaMyGridView) finder.castView((View) finder.findRequiredView(obj, R.id.mgv_payment_online, "field 'mgvPaymentOnline'"), R.id.mgv_payment_online, "field 'mgvPaymentOnline'");
        t.llPaymentOnline = (LinearLayout) finder.castView((View) finder.findRequiredView(obj, R.id.ll_payment_online, "field 'llPaymentOnline'"), R.id.ll_payment_online, "field 'llPaymentOnline'");
        t.mgvPaymentOffline = (ECJiaMyGridView) finder.castView((View) finder.findRequiredView(obj, R.id.mgv_payment_offline, "field 'mgvPaymentOffline'"), R.id.mgv_payment_offline, "field 'mgvPaymentOffline'");
        t.llPaymentOffline = (LinearLayout) finder.castView((View) finder.findRequiredView(obj, R.id.ll_payment_offline, "field 'llPaymentOffline'"), R.id.ll_payment_offline, "field 'llPaymentOffline'");
        t.mlvShipping = (ECJiaMyListView) finder.castView((View) finder.findRequiredView(obj, R.id.mlv_shipping, "field 'mlvShipping'"), R.id.mlv_shipping, "field 'mlvShipping'");
        return createUnbinder;
    }

    protected a<T> createUnbinder(T t) {
        return new a(t);
    }
}
