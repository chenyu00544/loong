package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;

final class d implements TextWatcher {
    final /* synthetic */ af a;
    private boolean b = true;
    private int c;
    private boolean d = false;

    d(af afVar) {
        this.a = afVar;
    }

    private String a(CharSequence charSequence, int i) {
        int i2 = 0;
        int length = charSequence.length();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = charSequence.charAt(i3);
            if (charAt != ' ') {
                i2++;
                if (i3 != 0 && (i2 & 3) == 1) {
                    stringBuffer.append(' ');
                }
            }
            if (i3 == i) {
                this.c = stringBuffer.length();
            }
            if (charAt != ' ') {
                stringBuffer.append(charAt);
            }
        }
        if (i == length) {
            this.c = stringBuffer.length();
        }
        return stringBuffer.toString();
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i2 == 1 && i3 == 0 && charSequence.charAt(i) == ' ') {
            this.d = true;
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.b) {
            CharSequence subSequence;
            if (this.d) {
                subSequence = charSequence.subSequence(0, i - 1);
                if (i < charSequence.length()) {
                    subSequence = subSequence.toString() + charSequence.subSequence(i, charSequence.length());
                }
                i--;
                this.d = false;
            } else {
                subSequence = charSequence;
            }
            this.b = false;
            this.a.b.c(a(subSequence, i + i3));
            this.a.b.b(this.c <= 23 ? this.c : 23);
            this.b = true;
        }
    }
}
