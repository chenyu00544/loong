package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView.BufferType;
import com.unionpay.mobile.android.f.c;
import java.math.BigDecimal;

public class bd extends aa implements com.unionpay.mobile.android.widgets.u.a {
    private static final String c = bd.class.getSimpleName();
    private String o = null;
    private String p = null;
    private String q = null;
    private String r = null;
    private String s = null;

    public class a implements InputFilter {
        final /* synthetic */ bd a;
        private u b = null;
        private BigDecimal c = null;
        private BigDecimal d = null;

        public a(bd bdVar, u uVar, BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            this.a = bdVar;
            this.b = uVar;
            this.c = bigDecimal;
            this.d = bigDecimal2;
            if (this.c.compareTo(BigDecimal.ZERO) == 1) {
                c(this.c.toString());
            }
        }

        private static int a(String str) {
            return (str == null || !b(str)) ? 0 : (str.length() - str.indexOf(".")) - 1;
        }

        private CharSequence a(String str, boolean z, boolean z2) {
            String bigDecimal = this.d.toString();
            String bigDecimal2 = this.c.toString();
            try {
                BigDecimal bigDecimal3 = new BigDecimal(str);
                bigDecimal3.setScale(2, 6);
                if (z && z2 && bigDecimal3.compareTo(this.c) == -1) {
                    c(bigDecimal2);
                }
                if (bigDecimal3.compareTo(this.d) == 1) {
                    c(bigDecimal);
                }
                return null;
            } catch (Exception e) {
                return "";
            }
        }

        private static boolean b(String str) {
            return str != null && str.contains(".");
        }

        private void c(String str) {
            this.b.c(str);
            Selection.setSelection(this.b.c(), this.b.b().length());
        }

        public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            boolean z = true;
            boolean z2 = false;
            String str;
            if (charSequence == null || !charSequence.equals("")) {
                int i5 = i2 - i;
                boolean b = b(spanned.toString());
                if (1 != i5) {
                    boolean b2 = b(charSequence.toString());
                    if (b2 && b) {
                        return "";
                    }
                    if (b2 && (a(charSequence.toString()) > 2 || spanned.toString().length() != i4)) {
                        return "";
                    }
                } else if (!b && spanned.length() == 1 && spanned.charAt(0) == '0') {
                    return ".";
                } else {
                    if ('0' == charSequence.charAt(0) && i3 == 0 && i4 == 0 && spanned.length() != 0) {
                        return "";
                    }
                    if ('.' == charSequence.charAt(0)) {
                        if (spanned.length() == 0) {
                            return "";
                        }
                        if (spanned.length() != 0 && spanned.length() - i3 > 2) {
                            return "";
                        }
                    }
                    if (b && i3 > spanned.toString().indexOf(".") && a(spanned.toString()) > 2 - i5) {
                        return "";
                    }
                }
                str = spanned.subSequence(0, i3).toString() + charSequence.subSequence(i, i2).toString() + spanned.subSequence(i4, spanned.length());
                if (a(str) != 2) {
                    z = false;
                }
                return a(str, false, z);
            }
            str = spanned.subSequence(0, i3).toString() + charSequence.subSequence(i, i2).toString() + spanned.subSequence(i4, spanned.length());
            if (i4 - i3 != 1) {
                z2 = true;
            }
            return a(str, true, z2);
        }
    }

    public bd(android.content.Context r8, int r9, org.json.JSONObject r10, java.lang.String r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unexpected register number in merge insn: ?: MERGE  (r5_7 java.lang.String) = (r5_6 java.lang.String), (r5_11 java.lang.String)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:84)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r7 = this;
        r4 = 6;
        r6 = 2;
        r5 = 0;
        r7.<init>(r8, r9, r10, r11);
        r7.o = r5;
        r7.p = r5;
        r7.q = r5;
        r7.r = r5;
        r7.s = r5;
        r0 = "point";
        r0 = com.unionpay.mobile.android.utils.j.a(r10, r0);
        r7.r = r0;
        r0 = "max_use_point";
        r0 = com.unionpay.mobile.android.utils.j.a(r10, r0);
        r7.q = r0;
        r0 = "min_use_point";
        r0 = com.unionpay.mobile.android.utils.j.a(r10, r0);
        r7.p = r0;
        r0 = "ratio";
        r0 = com.unionpay.mobile.android.utils.j.a(r10, r0);
        r7.s = r0;
        r0 = "ordr_amnt";
        r0 = com.unionpay.mobile.android.utils.j.a(r10, r0);
        r7.o = r0;
        r0 = r7.b;
        r1 = 8194; // 0x2002 float:1.1482E-41 double:4.0484E-320;
        r0.a(r1);
        r0 = r7.b;
        r1 = "0123456789.";
        r1 = android.text.method.DigitsKeyListener.getInstance(r1);
        r0.a(r1);
        r0 = new java.math.BigDecimal;	 Catch:{ Exception -> 0x00a0 }
        r1 = r7.p;	 Catch:{ Exception -> 0x00a0 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00a0 }
        r0 = r0.setScale(r6, r4);
        r1 = r0;
    L_0x0056:
        r0 = new java.math.BigDecimal;	 Catch:{ Exception -> 0x00ae }
        r2 = r7.q;	 Catch:{ Exception -> 0x00ae }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00ae }
        r0 = r0.setScale(r6, r4);
    L_0x0061:
        r2 = r7.b;
        r3 = new com.unionpay.mobile.android.widgets.bd$a;
        r4 = r7.b;
        r3.<init>(r7, r4, r1, r0);
        r2.a(r3);
        r7.a(r5, r5);
        r0 = r7.q();
        if (r0 == 0) goto L_0x0080;
    L_0x0076:
        r0 = r7.q();
        r0 = r0.length();
        if (r0 != 0) goto L_0x009a;
    L_0x0080:
        r7.u();
        r0 = com.unionpay.mobile.android.f.c.bD;
        r0 = r0.ay;
        r1 = new java.lang.Object[r6];
        r2 = 0;
        r3 = r7.r;
        r1[r2] = r3;
        r2 = 1;
        r3 = r7.s;
        r1[r2] = r3;
        r0 = java.lang.String.format(r0, r1);
        r7.c(r0);
    L_0x009a:
        r0 = r7.b;
        r0.a(r7);
        return;
    L_0x00a0:
        r0 = move-exception;
        r0 = java.math.BigDecimal.ZERO;	 Catch:{ all -> 0x00a9 }
        r0 = r0.setScale(r6, r4);
        r1 = r0;
        goto L_0x0056;
    L_0x00a9:
        r0 = move-exception;
        r5.setScale(r6, r4);
        throw r0;
    L_0x00ae:
        r0 = move-exception;
        r0 = new java.math.BigDecimal;	 Catch:{ all -> 0x00be }
        r2 = 5183643170566569984; // 0x47efffffe0000000 float:-3.6893488E19 double:3.4028234663852886E38;	 Catch:{ all -> 0x00be }
        r0.<init>(r2);	 Catch:{ all -> 0x00be }
        r0 = r0.setScale(r6, r4);
        goto L_0x0061;
    L_0x00be:
        r0 = move-exception;
        r5.setScale(r6, r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.widgets.bd.<init>(android.content.Context, int, org.json.JSONObject, java.lang.String):void");
    }

    private void a(String str, String str2) {
        if (p() == null || p().length() == 0) {
            CharSequence spannableString;
            t();
            if (str == null) {
                Object format = String.format(c.bD.aw, new Object[]{this.q});
                spannableString = new SpannableString(format);
                spannableString.setSpan(new ForegroundColorSpan(-15958150), 0, format.length(), 0);
            } else {
                String format2 = String.format(c.bD.aw + str, new Object[]{this.q, str2});
                spannableString = new SpannableString(format2);
                spannableString.setSpan(new ForegroundColorSpan(-15958150), 0, format2.length() - (str2 + "元").length(), 0);
                spannableString.setSpan(new ForegroundColorSpan(-44462), format2.length() - (str2 + "元").length(), format2.length(), 0);
            }
            v();
            a(spannableString, BufferType.SPANNABLE);
        }
    }

    public final String a() {
        String str = null;
        String a = super.a();
        if (!(a == null || a.length() == 0)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(a);
                bigDecimal.setScale(2, 6);
                if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                    str = bigDecimal.toString();
                }
            } catch (Exception e) {
            }
        }
        return str;
    }

    public final void a(Editable editable) {
        super.a(editable);
        if (p() == null || p().length() == 0) {
            try {
                a(c.bD.ax, new BigDecimal(this.o).setScale(2, 6).subtract(new BigDecimal(a()).setScale(2, 6).multiply(new BigDecimal(this.s).setScale(2, 6)).setScale(2, 6)).toString());
            } catch (Exception e) {
                a(null, null);
            }
        }
    }

    public final void a(boolean z) {
        if (z) {
            a(this.l);
        }
    }

    public final boolean b() {
        return true;
    }

    public final boolean c() {
        return true;
    }

    public final String h() {
        String a = a();
        return (a == null || a.length() == 0) ? null : super.h();
    }
}
