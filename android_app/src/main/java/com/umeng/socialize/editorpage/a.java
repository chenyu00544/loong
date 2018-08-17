package com.umeng.socialize.editorpage;

import com.umeng.socialize.editorpage.KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener;
import com.umeng.socialize.utils.Log;

/* compiled from: ShareActivity */
class a implements IOnKeyboardStateChangedListener {
    final /* synthetic */ ShareActivity a;

    a(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public void a(int i) {
        this.a.D = i;
        Log.d("ShareActivity", "onKeyboardStateChanged  now state is " + i);
    }
}
