package org.android.agoo.control;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.b;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

/* compiled from: Taobao */
class d implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ boolean b;
    final /* synthetic */ AgooFactory c;

    d(AgooFactory agooFactory, byte[] bArr, boolean z) {
        this.c = agooFactory;
        this.a = bArr;
        this.b = z;
    }

    public void run() {
        try {
            String str = new String(this.a, "utf-8");
            if (TextUtils.isEmpty(str)) {
                b.a("accs", BaseMonitor.COUNT_AGOO_FAIL_ACK, "msg==null", 0.0d);
                return;
            }
            ALog.i("AgooFactory", "message = " + str, new Object[0]);
            JSONObject jSONObject = new JSONObject(str);
            CharSequence charSequence = null;
            CharSequence string = jSONObject.getString("api");
            String string2 = jSONObject.getString("id");
            if (TextUtils.equals(string, "agooReport")) {
                charSequence = jSONObject.getString("status");
            }
            if (TextUtils.equals(string, AgooConstants.AGOO_SERVICE_AGOOACK)) {
                b.a("accs", BaseMonitor.COUNT_AGOO_SUCCESS_ACK, "handlerACKMessage", 0.0d);
            }
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(charSequence)) {
                b.a("accs", BaseMonitor.COUNT_AGOO_FAIL_ACK, "json key null", 0.0d);
                return;
            }
            if (ALog.isPrintLog(Level.I)) {
                ALog.i("AgooFactory", "updateMsg data begin,api=" + string + ",id=" + string2 + ",status=" + charSequence + ",reportTimes=" + org.android.agoo.common.b.d(AgooFactory.mContext), new Object[0]);
            }
            if (TextUtils.equals(string, "agooReport")) {
                if (TextUtils.equals(charSequence, "4") && this.b) {
                    this.c.messageService.a(string2, "1");
                } else if ((TextUtils.equals(charSequence, "8") || TextUtils.equals(charSequence, "9")) && this.b) {
                    this.c.messageService.a(string2, MessageService.MSG_DB_COMPLETE);
                }
                b.a("accs", BaseMonitor.COUNT_AGOO_SUCCESS_ACK, charSequence, 0.0d);
            }
        } catch (Throwable th) {
            ALog.e("AgooFactory", "updateMsg get data error,e=" + th, new Object[0]);
            b.a("accs", BaseMonitor.COUNT_AGOO_FAIL_ACK, "json exception", 0.0d);
        }
    }
}
