package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;

public class ECJiaGalleryImageActivity extends a implements OnClickListener {
    boolean a;
    boolean b;
    private SharedPreferences c;
    private Editor d;
    private boolean e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.gallery_image);
        this.c = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.d = this.c.edit();
        this.b = this.c.getBoolean("isFirstRun", true);
        this.a = this.c.getBoolean("isSettingGo", true);
        if (TextUtils.isEmpty(this.c.getString("localString", ""))) {
            this.e = false;
        } else {
            this.e = true;
        }
        if (this.b) {
        }
        if (this.a) {
        }
        if (this.b) {
            this.d.putBoolean("isFirstRun", false);
            this.d.commit();
            startActivity(new Intent(this, ECJiaChooseCityActivity.class));
            finish();
        } else if (this.e) {
            startActivity(new Intent(this, ECJiaMainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, ECJiaChooseCityActivity.class));
            finish();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_ecjia:
            case R.id.welcome_intent1:
            case R.id.welcome_intent2:
            case R.id.welcome_intent3:
            case R.id.welcome_intent4:
                if (this.b) {
                    this.d.putBoolean("isFirstRun", false);
                    this.d.commit();
                    startActivity(new Intent(this, ECJiaChooseCityActivity.class));
                    finish();
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                }
                finish();
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.c.getBoolean("isSettingGo", true)) {
            this.d.putBoolean("isSettingGo", false);
            this.d.commit();
        }
    }
}
