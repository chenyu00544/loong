package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.ecjia.component.view.k;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.Timer;
import java.util.TimerTask;

public class ECJiaRemarkActivity extends a implements TextWatcher {
    private EditText a;
    private ImageView b;
    private Button c;
    private Resources d;

    class ECJiaRemarkActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRemarkActivity a;

        ECJiaRemarkActivity_1(ECJiaRemarkActivity eCJiaRemarkActivity) {
            this.a = eCJiaRemarkActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaRemarkActivity_2 extends TimerTask {
        final /* synthetic */ ECJiaRemarkActivity a;

        ECJiaRemarkActivity_2(ECJiaRemarkActivity eCJiaRemarkActivity) {
            this.a = eCJiaRemarkActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.a.getContext().getSystemService("input_method")).showSoftInput(this.a.a, 0);
        }
    }

    class ECJiaRemarkActivity_3 implements OnFocusChangeListener {
        final /* synthetic */ ECJiaRemarkActivity a;

        ECJiaRemarkActivity_3(ECJiaRemarkActivity eCJiaRemarkActivity) {
            this.a = eCJiaRemarkActivity;
        }

        public void onFocusChange(View view, boolean z) {
            EditText editText = (EditText) view;
            if (z) {
                editText.setTag(editText.getHint().toString());
                editText.setHint("");
                return;
            }
            editText.setHint(editText.getTag().toString());
        }
    }

    class ECJiaRemarkActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaRemarkActivity a;

        ECJiaRemarkActivity_4(ECJiaRemarkActivity eCJiaRemarkActivity) {
            this.a = eCJiaRemarkActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("remark", this.a.a.getText().toString());
            this.a.setResult(-1, intent);
            this.a.finish();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (a(currentFocus, motionEvent)) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(motionEvent);
        } else if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        } else {
            return onTouchEvent(motionEvent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_remark);
        PushAgent.getInstance(this).onAppStart();
        this.b = (ImageView) findViewById(R.id.remark_back);
        this.b.setOnClickListener(new ECJiaRemarkActivity_1(this));
        CharSequence stringExtra = getIntent().getStringExtra("remark");
        this.a = (EditText) findViewById(R.id.remark_content);
        this.a.setText(stringExtra);
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.requestFocus();
        new Timer().schedule(new ECJiaRemarkActivity_2(this), 300);
        this.a.addTextChangedListener(this);
        this.a.setOnFocusChangeListener(new ECJiaRemarkActivity_3(this));
        this.a.setFilters(new InputFilter[]{new LengthFilter(255)});
        this.c = (Button) findViewById(R.id.remark_save);
        this.c.setOnClickListener(new ECJiaRemarkActivity_4(this));
    }

    public void afterTextChanged(Editable editable) {
        this.d = getBaseContext().getResources();
        String string = this.d.getString(R.string.remark_content_over_limit);
        if (255 - this.a.getText().toString().trim().length() == 0) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public boolean a(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = new int[]{0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int height = view.getHeight() + i2;
        int width = view.getWidth() + i;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) width) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) height)) {
            return true;
        }
        return false;
    }
}
