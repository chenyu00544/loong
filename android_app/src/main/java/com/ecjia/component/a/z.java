package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.bg;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaRealNameVerifyModel */
public class z extends e {
    public bg a = new bg();

    /* compiled from: ECJiaRealNameVerifyModel */
    class z_1 implements OnCancelListener {
        final /* synthetic */ z a;

        z_1(z zVar) {
            this.a = zVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public z(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.q = " user/account/realname/verify";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put(SocializeConstants.TENCENT_UID, str);
            if (!(TextUtils.isEmpty(str2) || "0".equals(str2))) {
                jSONObject.put("real_id", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("real_name", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("identity_number", str4);
            }
            if (!TextUtils.isEmpty(str5)) {
                jSONObject.put("bank_name", str5);
            }
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("bank_card", str6);
            }
            if (!(TextUtils.isEmpty(str7) || str7.equals("isEditPic"))) {
                arrayList2.add("identity_pic_front");
                arrayList.add(str7);
            }
            if (!(TextUtils.isEmpty(str8) || str8.equals("isEditPic"))) {
                arrayList2.add("identity_pic_back");
                arrayList.add(str8);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.a(this.q, jSONObject.toString(), arrayList2, arrayList);
        this.l.setOnCancelListener(new z_1(this));
    }

    public void a(String str, String str2) {
        super.a(str, str2);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            q.a("===" + str + "返回===" + jSONObject.toString());
            this.r = ax.a(jSONObject.optJSONObject("status"));
            Object obj = -1;
            switch (str.hashCode()) {
                case -1646184935:
                    if (str.equals(" user/account/realname/verify")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    if (this.r.b() == 1) {
                        this.a = bg.a(jSONObject.optJSONObject("data"));
                        break;
                    }
                    break;
            }
            g();
            a(str, str2, this.r);
        } catch (JSONException e) {
            e.printStackTrace();
            q.a("===" + str + "返回===" + str2);
        }
        e(str2);
    }
}
