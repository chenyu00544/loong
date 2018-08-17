package com.ecjia.a.b;

/* compiled from: ECJiaOpenTypeRulerManager */
public class d implements c {
    private static final Object a = new Object();
    private static d b;
    private String c;

    private d() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r10, java.lang.String r11) {
        /*
        r9 = this;
        r4 = -1;
        r5 = 2;
        r3 = 0;
        r2 = 1;
        r9.c = r11;
        r1 = java.lang.System.out;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "opentype==";
        r6 = r6.append(r7);
        r6 = r6.append(r11);
        r6 = r6.toString();
        r1.println(r6);
        r1 = android.text.TextUtils.isEmpty(r11);
        if (r1 == 0) goto L_0x002c;
    L_0x0024:
        r1 = java.lang.System.out;
        r2 = "please set url and do not null";
        r1.println(r2);
    L_0x002b:
        return;
    L_0x002c:
        r7 = com.ecjia.a.b.b.a(r11);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r7 == 0) goto L_0x0283;
    L_0x0032:
        r1 = r7.size();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 <= 0) goto L_0x002b;
    L_0x0038:
        r1 = 0;
        r1 = r7.get(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = (java.lang.String[]) r1;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r6 = 1;
        r8 = r1[r6];	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = com.ecjia.a.b.a.a(r8);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r6 = android.text.TextUtils.isEmpty(r8);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r6 == 0) goto L_0x0055;
    L_0x004c:
        com.ecjia.a.b.a.c(r10, r11);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x0050:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002b;
    L_0x0055:
        r6 = r8.hashCode();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        switch(r6) {
            case -925244243: goto L_0x008a;
            case 2088263399: goto L_0x0076;
            case 2088263773: goto L_0x0080;
            default: goto L_0x005c;
        };	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x005c:
        r6 = r4;
    L_0x005d:
        switch(r6) {
            case 0: goto L_0x0094;
            case 1: goto L_0x00d5;
            case 2: goto L_0x011d;
            default: goto L_0x0060;
        };	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x0060:
        r6 = com.ecjia.a.b.a.a(r10, r8);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r6 == 0) goto L_0x015e;
    L_0x0066:
        r2 = java.lang.System.out;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = "需要登录";
        r2.println(r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        com.ecjia.a.b.a.b(r10, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x0071:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002b;
    L_0x0076:
        r6 = "sign_in";
        r6 = r8.equals(r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r6 == 0) goto L_0x005c;
    L_0x007e:
        r6 = r3;
        goto L_0x005d;
    L_0x0080:
        r6 = "sign_up";
        r6 = r8.equals(r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r6 == 0) goto L_0x005c;
    L_0x0088:
        r6 = r2;
        goto L_0x005d;
    L_0x008a:
        r6 = "forget_password";
        r6 = r8.equals(r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r6 == 0) goto L_0x005c;
    L_0x0092:
        r6 = r5;
        goto L_0x005d;
    L_0x0094:
        r2 = com.ecjia.a.b.a.a(r10);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r2 == 0) goto L_0x00a0;
    L_0x009a:
        r1 = "user_center";
        r1 = com.ecjia.a.b.a.a(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x00a0:
        r2 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2.<init>(r10, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r10.startActivity(r2);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r0 = r10;
        r0 = (android.app.Activity) r0;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r0;
        r2 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = "push_right_in";
        r4 = "anim";
        r5 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2 = r2.getIdentifier(r3, r4, r5);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "push_right_out";
        r5 = "anim";
        r6 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r3.getIdentifier(r4, r5, r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.overridePendingTransition(r2, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x00d5:
        r1 = com.ecjia.a.b.a.a(r10);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x0116;
    L_0x00db:
        r1 = "user_center";
        r1 = com.ecjia.a.b.a.a(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x00e1:
        r2 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2.<init>(r10, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r10.startActivity(r2);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r0 = r10;
        r0 = (android.app.Activity) r0;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r0;
        r2 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = "push_right_in";
        r4 = "anim";
        r5 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2 = r2.getIdentifier(r3, r4, r5);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "push_right_out";
        r5 = "anim";
        r6 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r3.getIdentifier(r4, r5, r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.overridePendingTransition(r2, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x0116:
        r1 = "sign_up_mobile";
        r1 = com.ecjia.a.b.a.a(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x00e1;
    L_0x011d:
        r2 = com.ecjia.a.b.a.a(r10);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r2 == 0) goto L_0x0129;
    L_0x0123:
        r1 = "user_center";
        r1 = com.ecjia.a.b.a.a(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x0129:
        r2 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2.<init>(r10, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r10.startActivity(r2);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r0 = r10;
        r0 = (android.app.Activity) r0;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r0;
        r2 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = "push_right_in";
        r4 = "anim";
        r5 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2 = r2.getIdentifier(r3, r4, r5);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "push_right_out";
        r5 = "anim";
        r6 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r3.getIdentifier(r4, r5, r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.overridePendingTransition(r2, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x015e:
        r6 = "get_integral";
        r6 = r8.equals(r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r6 == 0) goto L_0x019b;
    L_0x0166:
        r2 = new com.ecjia.a.b;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2.<init>(r10, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r10.startActivity(r2);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r0 = r10;
        r0 = (android.app.Activity) r0;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r0;
        r2 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = "push_right_in";
        r4 = "anim";
        r5 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2 = r2.getIdentifier(r3, r4, r5);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "push_right_out";
        r5 = "anim";
        r6 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r3.getIdentifier(r4, r5, r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.overridePendingTransition(r2, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x019b:
        r6 = new android.content.Intent;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r6.<init>(r10, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r8.hashCode();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        switch(r1) {
            case -1467255178: goto L_0x01d4;
            case -951532658: goto L_0x01de;
            case -906014849: goto L_0x01e8;
            case 3343801: goto L_0x01fc;
            case 273184745: goto L_0x01f2;
            default: goto L_0x01ab;
        };	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x01ab:
        r1 = r4;
    L_0x01ac:
        switch(r1) {
            case 0: goto L_0x0206;
            case 1: goto L_0x020d;
            case 2: goto L_0x0214;
            case 3: goto L_0x0237;
            case 4: goto L_0x0247;
            default: goto L_0x01af;
        };	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x01af:
        r1 = r7.size();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 <= r2) goto L_0x0257;
    L_0x01b5:
        r1 = r7.size();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r2 >= r1) goto L_0x0257;
    L_0x01bb:
        r1 = r7.get(r2);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = (java.lang.String[]) r1;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = 0;
        r3 = r1[r3];	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r7.get(r2);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = (java.lang.String[]) r1;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = 1;
        r1 = r1[r4];	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r6.putExtra(r3, r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x01b5;
    L_0x01d4:
        r1 = "goods_comment";
        r1 = r8.equals(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x01ab;
    L_0x01dc:
        r1 = r3;
        goto L_0x01ac;
    L_0x01de:
        r1 = "qrcode";
        r1 = r8.equals(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x01ab;
    L_0x01e6:
        r1 = r2;
        goto L_0x01ac;
    L_0x01e8:
        r1 = "seller";
        r1 = r8.equals(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x01ab;
    L_0x01f0:
        r1 = r5;
        goto L_0x01ac;
    L_0x01f2:
        r1 = "discover";
        r1 = r8.equals(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x01ab;
    L_0x01fa:
        r1 = 3;
        goto L_0x01ac;
    L_0x01fc:
        r1 = "main";
        r1 = r8.equals(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x01ab;
    L_0x0204:
        r1 = 4;
        goto L_0x01ac;
    L_0x0206:
        r1 = "tab_id";
        r3 = 2;
        r6.putExtra(r1, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x01af;
    L_0x020d:
        r1 = "startType";
        r3 = 1;
        r6.putExtra(r1, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x01af;
    L_0x0214:
        r3 = new com.ecjia.a.a.b;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = "OPENTYPE_SELLER";
        r3.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r7.size();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 != r5) goto L_0x022e;
    L_0x0221:
        r1 = 1;
        r1 = r7.get(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = (java.lang.String[]) r1;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = 1;
        r1 = r1[r4];	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3.a(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
    L_0x022e:
        r1 = de.greenrobot.event.c.a();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.c(r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x01af;
    L_0x0237:
        r1 = de.greenrobot.event.c.a();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = new com.ecjia.a.a.b;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "ECJIAMAIN_FIND";
        r3.<init>(r4);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.c(r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x01af;
    L_0x0247:
        r1 = de.greenrobot.event.c.a();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = new com.ecjia.a.a.b;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "ECJIAMAIN_MAIN";
        r3.<init>(r4);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.c(r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x01af;
    L_0x0257:
        r10.startActivity(r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r0 = r10;
        r0 = (android.app.Activity) r0;	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1 = r0;
        r2 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = "push_right_in";
        r4 = "anim";
        r5 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r2 = r2.getIdentifier(r3, r4, r5);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r10.getResources();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r4 = "push_right_out";
        r5 = "anim";
        r6 = r10.getPackageName();	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r3 = r3.getIdentifier(r4, r5, r6);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        r1.overridePendingTransition(r2, r3);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x0283:
        r1 = "token=token";
        r1 = r11.contains(r1);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        if (r1 == 0) goto L_0x0290;
    L_0x028b:
        com.ecjia.a.b.a.d(r10, r11);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
    L_0x0290:
        com.ecjia.a.b.a.c(r10, r11);	 Catch:{ ClassNotFoundException -> 0x0050, Exception -> 0x0071 }
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.a.b.d.a(android.content.Context, java.lang.String):void");
    }

    public String a() {
        return this.c;
    }

    public static void b() {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        a.a = b;
    }
}
