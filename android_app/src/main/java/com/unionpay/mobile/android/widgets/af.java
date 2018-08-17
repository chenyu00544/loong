package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public final class af extends aa {
    private TextWatcher c = new d(this);
    private ArrayList<a> o = null;

    class a {
        final /* synthetic */ af a;
        private String b = null;
        private String c = null;
        private String d = null;

        public a(af afVar, JSONObject jSONObject) {
            this.a = afVar;
            this.b = j.a(jSONObject, "pattern");
            this.c = j.a(jSONObject, "prefix");
            this.d = j.a(jSONObject, "isCheck");
        }

        public final String a() {
            return this.b;
        }

        public final String b() {
            return this.c;
        }

        public final boolean c() {
            return this.d == null || !"false".equalsIgnoreCase(this.d);
        }
    }

    public af(Context context, int i, JSONObject jSONObject, String str) {
        int i2 = 0;
        super(context, i, jSONObject, str, (byte) 0);
        this.b.a(this.c);
        this.b.a(new LengthFilter(23));
        this.b.a(2);
        if (this.i) {
            this.b.setEnabled(false);
        }
        JSONArray d = j.d(jSONObject, "regex");
        if (d != null) {
            if (this.o == null) {
                this.o = new ArrayList();
            }
            while (i2 < d.length()) {
                JSONObject jSONObject2 = (JSONObject) j.b(d, i2);
                if (jSONObject2 != null) {
                    this.o.add(new a(this, jSONObject2));
                }
                i2++;
            }
        }
    }

    private static boolean b(String str) {
        int length = str.length();
        int i = 0;
        int i2 = length - 2;
        int i3 = 0;
        while (i2 >= 0) {
            int charAt = str.charAt(i2) - 48;
            if (i % 2 == 0) {
                charAt *= 2;
                charAt = (charAt % 10) + (charAt / 10);
            }
            i3 += charAt;
            i2--;
            i++;
        }
        return (i3 % 10 == 0 ? '0' : (char) ((10 - (i3 % 10)) + 48)) == str.charAt(length + -1);
    }

    public final String a() {
        return (this.i ? i() : this.b.b()).replace(" ", "");
    }

    public final boolean b() {
        if (this.i) {
            return true;
        }
        String a = a();
        if (this.o != null && this.o.size() > 0) {
            boolean z = false;
            for (int i = 0; i < this.o.size(); i++) {
                a aVar = (a) this.o.get(i);
                if (aVar.a() != null) {
                    z = a.matches(aVar.a());
                }
                if (z) {
                    boolean b = aVar.c() ? b(aVar.b() + a) : 13 <= a.length() && 19 >= a.length();
                    return b;
                }
            }
        }
        return 13 <= a.length() && 19 >= a.length() && b(a);
    }

    protected final String d() {
        return "_input_cardNO";
    }
}
