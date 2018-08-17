package com.ecjia.component.view.wheel;

import android.view.View;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaWheelString */
public class f<T extends ECJiaWheelData> {
    public int a;
    private View b;
    private ECJiaWheelTimeView c;

    public void a(View view) {
        this.b = view;
    }

    public f(View view) {
        this.b = view;
        a(view);
    }

    public void a(ArrayList<T> arrayList) {
        this.c = (ECJiaWheelTimeView) this.b.findViewById(R.id.reason);
        this.c.setAdapter(new a(arrayList));
        this.c.setCyclic(false);
        this.c.setLabel("");
        this.c.setCurrentItem(0);
        this.c.TEXT_SIZE = (this.a / 100) * 3;
    }

    public T b(ArrayList<T> arrayList) {
        return (ECJiaWheelData) arrayList.get(this.c.getCurrentItem());
    }
}
