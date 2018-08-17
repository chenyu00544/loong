package com.ecjia.component.webimageview;

import android.content.Context;
import android.graphics.Bitmap;
import com.ecjia.a.q;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: ECJiaWebImageManager */
public class b implements com.ecjia.component.webimageview.c.b {
    private static b a = null;
    private Map<String, c> b = new HashMap();
    private Map<c, Set<ECJiaWebImageView>> c = new HashMap();
    private Set<ECJiaWebImageView> d = new HashSet();

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(Context context, String str, ECJiaWebImageView eCJiaWebImageView, int i) {
        c cVar = (c) this.b.get(str);
        if (this.b.get(str) == null) {
            cVar = new c(context, str, i, this);
            this.b.put(str, cVar);
            this.d.add(eCJiaWebImageView);
            Set hashSet = new HashSet();
            hashSet.add(eCJiaWebImageView);
            this.c.put(cVar, hashSet);
            cVar.execute(new Void[0]);
            return;
        }
        ((Set) this.c.get(cVar)).add(eCJiaWebImageView);
        this.d.add(eCJiaWebImageView);
    }

    public void a(String str, Bitmap bitmap) {
        if (this.b.containsKey(str)) {
            c cVar = (c) this.b.get(str);
            for (ECJiaWebImageView eCJiaWebImageView : (Set) this.c.get(cVar)) {
                if (this.d.contains(eCJiaWebImageView)) {
                    eCJiaWebImageView.setImageBitmap(bitmap);
                    this.d.remove(eCJiaWebImageView);
                } else {
                    q.a("************error********");
                }
            }
            this.b.remove(str);
            this.c.remove(cVar);
        }
    }

    public void a(ECJiaWebImageView eCJiaWebImageView) {
        String str = eCJiaWebImageView.urlString;
        c cVar = (c) this.b.get(str);
        Set set = (Set) this.c.get(cVar);
        if (set == null) {
            this.b.remove(str);
            this.c.remove(cVar);
            this.d.remove(eCJiaWebImageView);
        } else if (set.size() > 1) {
            set.remove(eCJiaWebImageView);
            this.c.put(cVar, set);
            this.d.remove(eCJiaWebImageView);
        } else if (this.d.contains(eCJiaWebImageView)) {
            this.b.remove(str);
            this.c.remove(cVar);
            this.d.remove(eCJiaWebImageView);
            cVar.cancel(true);
        }
    }

    public void b(String str, Bitmap bitmap) {
        a(str, bitmap);
    }

    public void b() {
    }
}
