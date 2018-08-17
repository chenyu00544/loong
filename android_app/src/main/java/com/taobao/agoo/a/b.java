package com.taobao.agoo.a;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.utl.d;
import com.taobao.agoo.ICallback;
import com.taobao.agoo.a.a.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Taobao */
public class b extends AccsAbstractDataListener {
    public static a b;
    public Map<String, ICallback> a = new HashMap();

    public void onResponse(java.lang.String r8, java.lang.String r9, int r10, byte[] r11, com.taobao.accs.base.TaoBaseService.ExtraInfo r12) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.base/java.util.HashMap$HashIterator.nextNode(Unknown Source)
	at java.base/java.util.HashMap$KeyIterator.next(Unknown Source)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
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
        r0 = "AgooDeviceCmd";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = r0.equals(r8);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r0 == 0) goto L_0x0123;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0008:
        r0 = r7.a;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = r0.get(r9);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = (com.taobao.agoo.ICallback) r0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r10 != r1) goto L_0x0118;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0014:
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = "utf-8";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1.<init>(r11, r2);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = "RequestListener";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = "RequestListener onResponse";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4 = 6;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r5 = 0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r6 = "dataId";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r5 = 1;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4[r5] = r9;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r5 = 2;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r6 = "listener";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r5 = 3;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4[r5] = r0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r5 = 4;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r6 = "json";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r5 = 5;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4[r5] = r1;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        com.taobao.accs.utl.ALog.i(r2, r3, r4);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = "resultCode";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = 0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = com.taobao.accs.utl.d.a(r2, r1, r3);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = "cmd";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4 = 0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = com.taobao.accs.utl.d.a(r2, r3, r4);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4 = "success";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r4 = r4.equals(r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r4 != 0) goto L_0x0071;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0058:
        if (r0 == 0) goto L_0x0063;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x005a:
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = "agoo server error";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0.onFailure(r1, r2);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0063:
        r0 = "AgooDeviceCmd";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x0070;
    L_0x006b:
        r0 = r7.a;
        r0.remove(r9);
    L_0x0070:
        return;
    L_0x0071:
        r1 = "register";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = r1.equals(r3);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r1 == 0) goto L_0x00d6;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0079:
        r1 = "deviceId";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = 0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = com.taobao.accs.utl.d.a(r2, r1, r3);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r2 == 0) goto L_0x009d;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0086:
        if (r0 == 0) goto L_0x008f;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0088:
        r1 = "";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = "agoo server error deviceid null";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0.onFailure(r1, r2);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x008f:
        r0 = "AgooDeviceCmd";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x0070;
    L_0x0097:
        r0 = r7.a;
        r0.remove(r9);
        goto L_0x0070;
    L_0x009d:
        if (r0 == 0) goto L_0x00a8;
    L_0x009f:
        r2 = r0 instanceof com.taobao.agoo.IRegister;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r2 == 0) goto L_0x00a8;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x00a3:
        r0 = (com.taobao.agoo.IRegister) r0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0.onSuccess(r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x00a8:
        r0 = com.taobao.accs.client.GlobalClientInfo.getContext();	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        org.android.agoo.common.b.b(r0, r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = b;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = com.taobao.accs.client.GlobalClientInfo.getContext();	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = r1.getPackageName();	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0.a(r1);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        goto L_0x008f;
    L_0x00bd:
        r0 = move-exception;
        r1 = "RequestListener";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = "onResponse";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = 0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = "AgooDeviceCmd";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x0070;
    L_0x00d0:
        r0 = r7.a;
        r0.remove(r9);
        goto L_0x0070;
    L_0x00d6:
        r1 = "setAlias";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = r1.equals(r3);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r1 == 0) goto L_0x00ef;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x00de:
        r7.a(r2, r0);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = "AgooDeviceCmd";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x0070;
    L_0x00e9:
        r0 = r7.a;
        r0.remove(r9);
        goto L_0x0070;
    L_0x00ef:
        r1 = "removeAlias";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r1 = r1.equals(r3);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r1 == 0) goto L_0x0123;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x00f7:
        r1 = com.taobao.accs.client.GlobalClientInfo.getContext();	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = 0;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        org.android.agoo.common.b.c(r1, r2);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        if (r0 == 0) goto L_0x0104;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0101:
        r0.onSuccess();	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0104:
        r0 = b;	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0.a();	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0 = "AgooDeviceCmd";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x0070;
    L_0x0111:
        r0 = r7.a;
        r0.remove(r9);
        goto L_0x0070;
    L_0x0118:
        if (r0 == 0) goto L_0x0123;
    L_0x011a:
        r1 = java.lang.String.valueOf(r10);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r2 = "accs channel error";	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
        r0.onFailure(r1, r2);	 Catch:{ Throwable -> 0x00bd, all -> 0x0132 }
    L_0x0123:
        r0 = "AgooDeviceCmd";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x0070;
    L_0x012b:
        r0 = r7.a;
        r0.remove(r9);
        goto L_0x0070;
    L_0x0132:
        r0 = move-exception;
        r1 = "AgooDeviceCmd";
        r1 = r1.equals(r8);
        if (r1 == 0) goto L_0x0140;
    L_0x013b:
        r1 = r7.a;
        r1.remove(r9);
    L_0x0140:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.agoo.a.b.onResponse(java.lang.String, java.lang.String, int, byte[], com.taobao.accs.base.TaoBaseService$ExtraInfo):void");
    }

    public b(Context context) {
        if (b == null) {
            b = new a(context.getApplicationContext());
        }
    }

    public void onData(String str, String str2, String str3, byte[] bArr, ExtraInfo extraInfo) {
    }

    public void onBind(String str, int i, ExtraInfo extraInfo) {
    }

    public void onUnbind(String str, int i, ExtraInfo extraInfo) {
    }

    public void onSendData(String str, String str2, int i, ExtraInfo extraInfo) {
    }

    private void a(JSONObject jSONObject, ICallback iCallback) throws JSONException {
        Object a = d.a(jSONObject, a.JSON_PUSH_USER_TOKEN, null);
        if (!TextUtils.isEmpty(a)) {
            org.android.agoo.common.b.c(GlobalClientInfo.getContext(), a);
            if (iCallback != null) {
                iCallback.onSuccess();
                b.c(iCallback.extra);
            }
        } else if (iCallback != null) {
            iCallback.onFailure("", "agoo server error-pushtoken null");
        }
    }
}
