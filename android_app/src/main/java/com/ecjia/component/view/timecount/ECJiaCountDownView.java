package com.ecjia.component.view.timecount;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.view.k;
import com.ecmoban.android.missmall.R;
import java.util.Timer;
import java.util.TimerTask;

public class ECJiaCountDownView extends LinearLayout {
    private Context context;
    private Handler handler = new ECJiaCountDownView_1(this);
    private long mDay;
    private long mHour;
    private long mMinute;
    private long mSecond;
    private Timer timer = null;
    TextView tvDay;
    TextView tvHour;
    TextView tvMinute;
    TextView tvSecond;
    View tv_day_ll;

    class ECJiaCountDownView_1 extends Handler {
        final /* synthetic */ ECJiaCountDownView a;

        ECJiaCountDownView_1(ECJiaCountDownView eCJiaCountDownView) {
            this.a = eCJiaCountDownView;
        }

        public void handleMessage(Message message) {
            this.a.countDown();
        }
    }

    class ECJiaCountDownView_2 extends TimerTask {
        final /* synthetic */ ECJiaCountDownView a;

        ECJiaCountDownView_2(ECJiaCountDownView eCJiaCountDownView) {
            this.a = eCJiaCountDownView;
        }

        public void run() {
            this.a.handler.sendEmptyMessage(0);
        }
    }

    public ECJiaCountDownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_countdown_layout, this);
        this.tv_day_ll = inflate.findViewById(R.id.tv_day_ll);
        this.tvDay = (TextView) inflate.findViewById(R.id.tv_day);
        this.tvHour = (TextView) inflate.findViewById(R.id.tv_hour);
        this.tvMinute = (TextView) inflate.findViewById(R.id.tv_minute);
        this.tvSecond = (TextView) inflate.findViewById(R.id.tv_second);
    }

    public void setTime(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 86400;
        long j4 = (j2 - ((3600 * j3) * 24)) / 3600;
        long j5 = ((j2 - ((3600 * j3) * 24)) - (3600 * j4)) / 60;
        setTime(j3, j4, j5, ((j2 - ((3600 * j3) * 24)) - (3600 * j4)) - (60 * j5));
    }

    public void setTime(long j, long j2, long j3, long j4) {
        if (j2 >= 60 || j3 >= 60 || j4 >= 60 || j2 < 0 || j3 < 0 || j4 < 0) {
            throw new RuntimeException("Time format is error");
        }
        this.mDay = j;
        this.mHour = j2;
        this.mMinute = j3;
        this.mSecond = j4;
        setTextTime();
    }

    public void start() {
        if (this.timer == null) {
            this.timer = new Timer();
            this.timer.schedule(new ECJiaCountDownView_2(this), 0, 1000);
        }
    }

    public void stop() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    private void setTextTime() {
        if (this.mDay <= 0) {
            this.tv_day_ll.setVisibility(8);
        } else {
            this.tv_day_ll.setVisibility(0);
            if (this.mDay <= 0 || this.mDay >= 10) {
                this.tvDay.setText(this.mDay + "");
            } else {
                this.tvDay.setText("0" + this.mDay);
            }
        }
        if (this.mHour < 10) {
            this.tvHour.setText("0" + this.mHour);
        } else {
            this.tvHour.setText(this.mHour + "");
        }
        if (this.mMinute < 10) {
            this.tvMinute.setText("0" + this.mMinute);
        } else {
            this.tvMinute.setText(this.mMinute + "");
        }
        if (this.mSecond < 10) {
            this.tvSecond.setText("0" + this.mSecond);
        } else {
            this.tvSecond.setText(this.mSecond + "");
        }
    }

    private void countDown() {
        if (isCarry4Unit(this.tvSecond) && isCarry4Unit(this.tvMinute) && isCarry4Unit(this.tvHour) && isCarry4Unit(this.tvDay)) {
            k kVar = new k(this.context, "倒计时结束了");
            kVar.a(17);
            kVar.a();
            stop();
        }
    }

    private boolean isCarry4Unit(TextView textView) {
        int intValue = Integer.valueOf(textView.getText().toString()).intValue() - 1;
        if (intValue < 0) {
            textView.setText(59 + "");
            return true;
        }
        if (intValue < 10) {
            textView.setText("0" + intValue + "");
        } else {
            textView.setText(intValue + "");
        }
        return false;
    }
}
