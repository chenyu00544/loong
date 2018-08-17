package org.android.agoo.control;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import org.android.agoo.common.b;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
class a implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ String b;
    final /* synthetic */ AgooFactory c;

    a(AgooFactory agooFactory, byte[] bArr, String str) {
        this.c = agooFactory;
        this.a = bArr;
        this.b = str;
    }

    public void run() {
        CharSequence charSequence = null;
        try {
            String str = new String(this.a, "utf-8");
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (length == 1) {
                String str2 = null;
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        str2 = jSONObject.getString("i");
                        charSequence = jSONObject.getString("p");
                    }
                }
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i("AgooFactory", "saveMsg msgId:" + str2 + ",message=" + str + ",currentPack=" + charSequence + ",reportTimes=" + b.d(AgooFactory.mContext), new Object[0]);
                }
                if (!TextUtils.isEmpty(charSequence) && TextUtils.equals(charSequence, AgooFactory.mContext.getPackageName())) {
                    if (TextUtils.isEmpty(this.b)) {
                        this.c.messageService.a(str2, str, "0");
                    } else {
                        this.c.messageService.a(str2, str, this.b);
                    }
                }
            }
        } catch (Throwable th) {
            ALog.e("AgooFactory", "saveMsg fail:" + th.toString(), new Object[0]);
        }
    }
}
