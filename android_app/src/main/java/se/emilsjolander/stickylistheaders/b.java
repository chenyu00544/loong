package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

/* compiled from: CheckableWrapperView */
class b extends WrapperView implements Checkable {
    public b(Context context) {
        super(context);
    }

    public boolean isChecked() {
        return ((Checkable) this.mItem).isChecked();
    }

    public void setChecked(boolean z) {
        ((Checkable) this.mItem).setChecked(z);
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
