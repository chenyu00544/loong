package com.vcvb.chenyu.shop.tools;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {
    private static Timer countdownTimer;
    private static Integer t;
    private static TextView textView;

    /**
     * 开始计时
     */
    public static void startCountdown(final CallBack callback) {
        if (countdownTimer == null) {
            countdownTimer = new Timer();
            countdownTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    callback.time();
                }
            }, 0, 1000);
        }
    }

    public interface CallBack {
        void time();
    }

    public static void startCountdownByCount(Integer time, TextView tv) {
        if (t == null || t <= 0) {
            t = time;
        }
        if(textView == null){
            textView = tv;
        }
        if (countdownTimer == null) {
            countdownTimer = new Timer();
            countdownTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    t--;
                    Message message = new Message();
                    message.what = t;
                    mHandler.sendMessage(message);
                }
            }, 0, 1000);
        }
    }

    private static Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what <= 0){
                textView.setText(R.string.get_qrcode);
            }else{
                String str = "　(%ds)　";
                textView.setText(String.format(Locale.CHINA, str, msg.what));
            }
            return false;
        }
    });
}
