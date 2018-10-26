package com.vcvb.chenyu.shop.tools;

import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {
    private static Timer countdownTimer;
    private static Long count;

    /**
     * 开始计时
     */
    public static void startCountdown(Long time, final CallBack callback) {
        count = time;
        if (countdownTimer == null) {
            countdownTimer = new Timer();
            countdownTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    count++;
                    callback.time(count);
                }
            }, 0, 1000);
        }
    }

    public interface CallBack{
        void time(Long time);
    }
}
