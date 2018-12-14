package com.vcvb.chenyu.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class LaunchActivity extends Activity {
    private static int TOTAL_TIME = 2000;
    private static int ONECE_TIME = 1000;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        btn = findViewById(R.id.skip_bt);
        countDownTimer.start();
    }

    public void onClickToMainActivity(View view) {
        countDownTimer.cancel();
        startMain();
    }

    private CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONECE_TIME) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            String str = "%s 跳过";
            btn.setText(String.format(Locale.CHINA, str, value));
        }

        @Override
        public void onFinish() {
            btn.setText("0 跳过");
            startMain();
        }
    };

    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        LaunchActivity.this.finish();
    }
}
