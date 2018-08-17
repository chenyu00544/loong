package org.android.agoo.accs;

import android.text.TextUtils;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.a;
import com.taobao.accs.utl.b;
import java.nio.charset.Charset;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.CallBack;
import org.android.agoo.control.AgooFactory;

/* compiled from: Taobao */
public class AgooService extends TaoBaseService {
    public static CallBack a;
    public static CallBack b;
    private AgooFactory c;

    public void a() {
    }

    public void b() {
    }

    public void onCreate() {
        super.onCreate();
        ALog.d("AgooService", "into--[onCreate]", new Object[0]);
        this.c = new AgooFactory();
        this.c.init(getApplicationContext(), null, null);
    }

    public void onData(String str, String str2, String str3, byte[] bArr, ExtraInfo extraInfo) {
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("AgooService", "into--[onData]:serviceId:" + str + ",dataId=" + str3, new Object[0]);
            ALog.d("AgooService", "push data:" + new String(bArr, Charset.forName("UTF-8")), new Object[0]);
        }
        UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", a.b(getApplicationContext()), str3);
        try {
            this.c.saveMsg(bArr);
            this.c.msgRecevie(bArr, "accs", extraInfo);
        } catch (Throwable th) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", "onDataError", th);
            ALog.e("AgooService", "into--[onData,dealMessage]:error:" + th, new Object[0]);
        }
    }

    public void onBind(String str, int i, ExtraInfo extraInfo) {
        if (ALog.isPrintLog(Level.E)) {
            ALog.e("AgooService", "into--[onBind]:serviceId:" + str + ",errorCode=" + i, new Object[0]);
        }
        if (a != null && GlobalClientInfo.AGOO_SERVICE_ID.equals(str)) {
            if (i == 200) {
                a.onSuccess();
            } else {
                a.onFailure(String.valueOf(i), "bind Agoo service fail");
            }
        }
        a = null;
    }

    public void onUnbind(String str, int i, ExtraInfo extraInfo) {
        if (ALog.isPrintLog(Level.E)) {
            ALog.e("AgooService", "into--[onUnbind]:serviceId:" + str + ",errorCode=" + i, new Object[0]);
        }
        if (b != null && GlobalClientInfo.AGOO_SERVICE_ID.equals(str)) {
            if (i == 200) {
                b.onSuccess();
            } else {
                b.onFailure(String.valueOf(i), "unbind Agoo service fail");
            }
        }
        b = null;
    }

    public void onSendData(String str, String str2, int i, ExtraInfo extraInfo) {
        try {
            if (ALog.isPrintLog(Level.I)) {
                ALog.i("AgooService", "onSendData,dataId=" + str2 + ",errorCode=" + i + ",serviceId=" + str, new Object[0]);
            }
            if (i == 200) {
                if (TextUtils.equals(AgooConstants.AGOO_SERVICE_AGOOACK, str)) {
                    b.a("accs", BaseMonitor.COUNT_AGOO_SUCCESS_ACK, "8/9", 0.0d);
                }
                if (TextUtils.isEmpty(str) || !TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK) || Long.parseLong(str2) <= 300000000 || Long.parseLong(str2) >= 600000000) {
                    if (!TextUtils.isEmpty(str) && TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK) && Long.parseLong(str2) > 600000000 && ALog.isPrintLog(Level.I)) {
                        ALog.i("AgooService", "onSendData,reportData=" + str2 + ",serviceId=" + str, new Object[0]);
                        return;
                    }
                    return;
                } else if (ALog.isPrintLog(Level.I)) {
                    ALog.i("AgooService", "onSendData,AckData=" + str2 + ",serviceId=" + str, new Object[0]);
                    return;
                } else {
                    return;
                }
            }
            if (TextUtils.equals(AgooConstants.AGOO_SERVICE_AGOOACK, str)) {
                org.android.agoo.common.b.a(getApplicationContext(), 1);
                b.a("accs", BaseMonitor.COUNT_AGOO_FAIL_ACK, String.valueOf(i), 0.0d);
            }
            if (ALog.isPrintLog(Level.I)) {
                ALog.i("AgooService", "onSendData error,dataId=" + str2 + ",serviceId=" + str, new Object[0]);
                ALog.e("AgooService", "into--[parseError]", new Object[0]);
            }
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", a.b(getApplicationContext()), Constants.KEY_ERROR_CODE, str2 + ",serviceId=" + str + ",errorCode=" + i);
        } catch (Throwable th) {
            Throwable th2 = th;
            if (ALog.isPrintLog(Level.E)) {
                ALog.e("AgooService", "onSendData exception,e=" + th2.getMessage() + ",e.getStackMsg=" + a(th2), new Object[0]);
            }
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", a.b(getApplicationContext()), "onSendDataException", a(th2));
        }
    }

    public void onResponse(String str, String str2, int i, byte[] bArr, ExtraInfo extraInfo) {
        String str3;
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("AgooService", "onResponse,dataId=" + str2 + ",errorCode=" + i + ",data=" + bArr + ",serviceId=" + str, new Object[0]);
        }
        String str4 = null;
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    str3 = new String(bArr, "utf-8");
                    str4 = str3;
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d("AgooService", "onResponse,message=" + str4, new Object[0]);
                    }
                    if (i != 200 && TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK)) {
                        if (ALog.isPrintLog(Level.E)) {
                            ALog.e("AgooService", "request is success", Constants.KEY_DATA_ID, str2);
                        }
                        this.c.updateMsg(bArr, true);
                        return;
                    } else if (i == 200 && TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK)) {
                        if (ALog.isPrintLog(Level.E)) {
                            ALog.e("AgooService", "request is error", Constants.KEY_DATA_ID, str2, "errorid", Integer.valueOf(i));
                        }
                        org.android.agoo.common.b.a(getApplicationContext(), 1);
                        b.a("accs", BaseMonitor.COUNT_AGOO_FAIL_ACK, String.valueOf(i), 0.0d);
                        return;
                    } else if (ALog.isPrintLog(Level.E)) {
                        ALog.e("AgooService", "business request is error,message=" + str4, new Object[0]);
                    }
                }
            } catch (Throwable th) {
                ALog.e("AgooService", "onResponse get data error,e=" + th, new Object[0]);
            }
        }
        str3 = null;
        str4 = str3;
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("AgooService", "onResponse,message=" + str4, new Object[0]);
        }
        if (i != 200) {
        }
        if (i == 200) {
        }
        if (ALog.isPrintLog(Level.E)) {
            ALog.e("AgooService", "business request is error,message=" + str4, new Object[0]);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private String a(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement.toString() + "\n");
            }
        }
        return stringBuffer.toString();
    }
}
