package com.vcvb.chenyu.shop.tools;

import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {
    private static Timer countdownTimer;

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

    public interface CallBack{
        void time();
    }
}
