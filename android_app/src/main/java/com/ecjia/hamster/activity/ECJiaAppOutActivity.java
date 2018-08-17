package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import org.android.agoo.common.AgooConstants;

public class ECJiaAppOutActivity extends a {
    private ImageView a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.appout);
        PushAgent.getInstance(this).onAppStart();
        this.a = (ImageView) findViewById(R.id.bg);
        if (getIntent().getIntExtra(AgooConstants.MESSAGE_FLAG, 0) == 1) {
            this.a.setBackgroundResource(R.drawable.closed_ip4);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return true;
    }

    protected void onStop() {
        super.onStop();
        finish();
    }
}
