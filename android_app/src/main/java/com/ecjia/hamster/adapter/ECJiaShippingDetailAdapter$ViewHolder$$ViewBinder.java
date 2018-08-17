package com.ecjia.hamster.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecmoban.android.missmall.R;

public class ECJiaShippingDetailAdapter$ViewHolder$$ViewBinder<T extends ViewHolder> implements ViewBinder<T> {

    /* compiled from: ECJiaShippingDetailAdapter$ViewHolder$$ViewBinder */
    protected static class a<T extends ViewHolder> implements Unbinder {
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
            t.tvRuName = null;
            t.tvRuShippingFee = null;
            t.balanceHorilistview = null;
            t.bottomShortLine = null;
            t.bottomLine = null;
        }
    }

    public Unbinder bind(Finder finder, T t, Object obj) {
        Unbinder createUnbinder = createUnbinder(t);
        t.tvRuName = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_ru_name, "field 'tvRuName'"), R.id.tv_ru_name, "field 'tvRuName'");
        t.tvRuShippingFee = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_ru_shipping_fee, "field 'tvRuShippingFee'"), R.id.tv_ru_shipping_fee, "field 'tvRuShippingFee'");
        t.balanceHorilistview = (ECJiaHorizontalListView) finder.castView((View) finder.findRequiredView(obj, R.id.balance_horilistview, "field 'balanceHorilistview'"), R.id.balance_horilistview, "field 'balanceHorilistview'");
        t.bottomShortLine = (View) finder.findRequiredView(obj, R.id.bottom_short_line, "field 'bottomShortLine'");
        t.bottomLine = (View) finder.findRequiredView(obj, R.id.bottom_line, "field 'bottomLine'");
        return createUnbinder;
    }

    protected a<T> createUnbinder(T t) {
        return new a(t);
    }
}
