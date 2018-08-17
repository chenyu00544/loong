package com.ecjia.hamster.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;

public class ECJiaBaseFragmentActivity extends FragmentActivity {
    public Resources a;
    public ECJiaApplication b;
    protected final int c = R.drawable.header_back_arrow;
    public ECJiaTopView d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = (ECJiaApplication) getApplication();
        this.a = getResources();
    }

    protected void a() {
    }
}
