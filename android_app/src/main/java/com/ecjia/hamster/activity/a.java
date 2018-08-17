package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.ecjia.component.a.b;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

@SuppressLint({"NewApi"})
/* compiled from: ECJiaBaseActivity */
public class a extends Activity implements Callback {
    public Handler f;
    public Resources g;
    public ECJiaApplication h;
    protected ECJiaTopView i;
    protected final int j = R.drawable.header_back_arrow;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = (ECJiaApplication) getApplicationContext();
        PushAgent.getInstance(this).onAppStart();
        this.g = getResources();
        this.f = new Handler(this);
        b.a(this);
    }

    protected void onStart() {
        super.onStart();
        b.c(this);
    }

    protected void onStop() {
        super.onStop();
        b.d(this);
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        b.b(this);
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void a() {
    }

    public int d() {
        return Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
    }

    protected void a(EditText editText) {
        editText.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
