package com.umeng.socialize.shareboard;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.utils.ShareBoardlistener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ShareBoard */
public class a extends PopupWindow {
    private final ResContainer a;
    private Context b = null;
    private c c = null;
    private a d;
    private ShareBoardlistener e;
    private com.umeng.socialize.shareboard.b.a f;
    private List<SnsPlatform> g = new ArrayList();

    /* compiled from: ShareBoard */
    interface a {
        void a(SHARE_MEDIA share_media);
    }

    public a(Context context, List<SnsPlatform> list) {
        super(context);
        setWindowLayoutMode(-1, -1);
        this.a = ResContainer.get(context);
        this.b = context;
        this.c = a(context);
        setContentView(this.c);
        this.g = list;
        this.f = new com.umeng.socialize.shareboard.a.a(this.b, list, this);
        this.c.a(this.f);
        setAnimationStyle(this.a.style("umeng_socialize_shareboard_animation"));
        setFocusable(true);
    }

    public ShareBoardlistener a() {
        return this.e;
    }

    public void a(ShareBoardlistener shareBoardlistener) {
        this.e = shareBoardlistener;
    }

    private c a(Context context) {
        c cVar = new c(context);
        cVar.setLayoutParams(new LayoutParams(-1, -1));
        cVar.a(new b(this));
        return cVar;
    }
}
