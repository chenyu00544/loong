package cn.itguy.zxingportrait.view;

import com.google.zxing.h;
import com.google.zxing.i;

/* compiled from: ViewfinderResultPointCallback */
public final class a implements i {
    private final ViewfinderView a;

    public a(ViewfinderView viewfinderView) {
        this.a = viewfinderView;
    }

    public void a(h hVar) {
        this.a.addPossibleResultPoint(hVar);
    }
}
