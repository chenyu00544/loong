package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: ECJiaBaseSoftInputActivity */
public class b extends a {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void finish() {
        super.finish();
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 2);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void b(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 0);
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ b b;

            public void run() {
                ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 0);
            }
        }, 400);
    }

    public void a(EditText editText) {
        editText.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
