package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.bd;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaToplineModel */
public class ak extends e {
    public aa a;
    public ArrayList<bd> b = new ArrayList();
    private boolean c;

    /* compiled from: ECJiaToplineModel */
    class ak_1 implements OnCancelListener {
        final /* synthetic */ ak a;

        ak_1(ak akVar) {
            this.a = akVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public ak(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(boolean z) {
        this.c = true;
        this.q = "mobile/toutiao";
        if (z) {
            this.l.show();
        }
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ak_1(this));
    }

    public void a() {
        this.c = false;
        this.q = "mobile/toutiao";
        ab abVar = new ab();
        abVar.b((this.b.size() / 10) + 1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str, String str2) {
        int i = 0;
        super.a(str, str2);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            q.a("===" + str + "返回===" + jSONObject.toString());
            this.r = ax.a(jSONObject.optJSONObject("status"));
            int i2 = -1;
            switch (str.hashCode()) {
                case -154934960:
                    if (str.equals("mobile/toutiao")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        if (this.c) {
                            this.b.clear();
                        }
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            int length = optJSONArray.length();
                            while (i < length) {
                                this.b.add(bd.a(optJSONArray.optJSONObject(i)));
                                i++;
                            }
                        }
                    }
                    this.a = aa.a(jSONObject.optJSONObject("paginated"));
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
