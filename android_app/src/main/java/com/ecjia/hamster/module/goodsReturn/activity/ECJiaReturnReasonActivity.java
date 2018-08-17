package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import com.ecjia.component.view.wheel.d;
import com.ecjia.component.view.wheel.f;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.module.goodsReturn.model.ECJia_RETURN_REASON;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaReturnReasonActivity extends a {
    private f<ECJia_RETURN_REASON> a;
    private View b;
    private TextView c;
    private ArrayList<ECJia_RETURN_REASON> d;

    class ECJiaReturnReasonActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnReasonActivity a;

        ECJiaReturnReasonActivity_1(ECJiaReturnReasonActivity eCJiaReturnReasonActivity) {
            this.a = eCJiaReturnReasonActivity;
        }

        public void onClick(View view) {
            this.a.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = LayoutInflater.from(this).inflate(R.layout.settle_single_scroll, null);
        setContentView(this.b);
        Window window = getWindow();
        window.setGravity(80);
        window.setLayout(-1, -2);
        this.c = (TextView) this.b.findViewById(R.id.tv_finish);
        this.d = (ArrayList) getIntent().getSerializableExtra("return_reason");
        d dVar = new d(this);
        this.a = new f(this.b);
        this.a.a = dVar.a();
        this.a.a(this.d);
        this.c.setOnClickListener(new ECJiaReturnReasonActivity_1(this));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && (motionEvent.getX() < -10.0f || motionEvent.getY() < -10.0f || motionEvent.getY() >= ((float) (this.b.getHeight() + 20)))) {
            finish();
        }
        return true;
    }

    private void b() {
        Intent intent = new Intent();
        intent.putExtra("return_reason", this.a.b(this.d));
        setResult(-1, intent);
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return true;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }
}
