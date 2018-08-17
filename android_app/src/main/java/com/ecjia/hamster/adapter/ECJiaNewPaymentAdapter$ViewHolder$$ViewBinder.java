package com.ecjia.hamster.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import com.ecmoban.android.missmall.R;

public class ECJiaNewPaymentAdapter$ViewHolder$$ViewBinder<T extends ViewHolder> implements ViewBinder<T> {

    /* compiled from: ECJiaNewPaymentAdapter$ViewHolder$$ViewBinder */
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
            t.tvName = null;
        }
    }

    public Unbinder bind(Finder finder, T t, Object obj) {
        Unbinder createUnbinder = createUnbinder(t);
        t.tvName = (TextView) finder.castView((View) finder.findRequiredView(obj, R.id.tv_name, "field 'tvName'"), R.id.tv_name, "field 'tvName'");
        return createUnbinder;
    }

    protected a<T> createUnbinder(T t) {
        return new a(t);
    }
}
