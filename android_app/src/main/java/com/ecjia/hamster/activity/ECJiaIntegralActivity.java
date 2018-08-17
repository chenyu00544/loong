package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaIntegralActivity extends a implements a {
    private ImageView a;
    private TextView b;
    private TextView c;
    private EditText d;
    private String e;
    private String k;
    private ah l;
    private int m;

    class ECJiaIntegralActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaIntegralActivity a;

        ECJiaIntegralActivity_1(ECJiaIntegralActivity eCJiaIntegralActivity) {
            this.a = eCJiaIntegralActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaIntegralActivity_2 extends TimerTask {
        final /* synthetic */ ECJiaIntegralActivity a;

        ECJiaIntegralActivity_2(ECJiaIntegralActivity eCJiaIntegralActivity) {
            this.a = eCJiaIntegralActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.d.getContext().getSystemService("input_method")).showSoftInput(this.a.d, 0);
        }
    }

    class ECJiaIntegralActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaIntegralActivity a;

        ECJiaIntegralActivity_3(ECJiaIntegralActivity eCJiaIntegralActivity) {
            this.a = eCJiaIntegralActivity;
        }

        public void onClick(View view) {
            Resources resources = this.a.getBaseContext().getResources();
            String string = resources.getString(R.string.integral_enter_score);
            resources.getString(R.string.integral_score_not_zero);
            if (TextUtils.isEmpty(this.a.d.getText().toString())) {
                this.a.setResult(-1, new Intent());
                this.a.finish();
            } else if (Integer.parseInt(this.a.d.getText().toString()) > this.a.m) {
                k kVar = new k(this.a, string);
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (Integer.valueOf(this.a.d.getText().toString()).intValue() > 0) {
                this.a.l.b(this.a.d.getText().toString());
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.integral);
        PushAgent.getInstance(this).onAppStart();
        try {
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("integral"));
            this.e = jSONObject.get("your_integral").toString();
            this.k = jSONObject.get("order_max_integral").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.m = Math.min(Integer.valueOf(this.e).intValue(), Integer.valueOf(this.k).intValue());
        this.l = new ah(this);
        this.l.a((a) this);
        this.a = (ImageView) findViewById(R.id.integral_back);
        this.a.setOnClickListener(new ECJiaIntegralActivity_1(this));
        this.d = (EditText) findViewById(R.id.integral_input);
        this.d.setFocusable(true);
        this.d.setFocusableInTouchMode(true);
        this.d.requestFocus();
        new Timer().schedule(new ECJiaIntegralActivity_2(this), 300);
        this.c = (TextView) findViewById(R.id.integral_num);
        this.b = (TextView) findViewById(R.id.integral_submit);
        this.b.setOnClickListener(new ECJiaIntegralActivity_3(this));
        Resources resources = getBaseContext().getResources();
        String string = resources.getString(R.string.integral_all_of_you);
        String string2 = resources.getString(R.string.integral_can_use);
        String string3 = resources.getString(R.string.integral_integral);
        this.c.setText(string + this.e + string3);
        this.d.setHint(string2 + this.m + string3);
        this.d.setText(getIntent().getStringExtra("used_integral"));
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("validate/integral") && axVar.b() == 1) {
            try {
                JSONObject jSONObject = this.l.F.getJSONObject("data");
                String optString = jSONObject.optString("bonus");
                String optString2 = jSONObject.optString("bonus_formated");
                Intent intent = new Intent();
                intent.putExtra("input", this.d.getText().toString());
                intent.putExtra("bonus", optString);
                intent.putExtra("bonus_formated", optString2);
                setResult(-1, intent);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
