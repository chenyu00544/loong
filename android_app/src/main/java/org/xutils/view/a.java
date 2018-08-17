package org.xutils.view;

import android.app.Activity;
import android.view.View;

/* compiled from: ViewFinder */
final class a {
    private View a;
    private Activity b;

    public a(View view) {
        this.a = view;
    }

    public a(Activity activity) {
        this.b = activity;
    }

    public View a(int i) {
        if (this.a != null) {
            return this.a.findViewById(i);
        }
        if (this.b != null) {
            return this.b.findViewById(i);
        }
        return null;
    }

    public View a(b bVar) {
        return a(bVar.a, bVar.b);
    }

    public View a(int i, int i2) {
        View view = null;
        if (i2 > 0) {
            view = a(i2);
        }
        if (view != null) {
            return view.findViewById(i);
        }
        return a(i);
    }
}
