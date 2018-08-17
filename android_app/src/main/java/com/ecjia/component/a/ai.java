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
import com.ecjia.hamster.model.az;
import com.umeng.message.MsgConstant;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaSuggestListModel */
public class ai extends e {
    public ArrayList<az> a = new ArrayList();
    public aa b;
    private boolean c;

    /* compiled from: ECJiaSuggestListModel */
    class ai_1 implements OnCancelListener {
        final /* synthetic */ ai a;

        ai_1(ai aiVar) {
            this.a = aiVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public ai(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        this.c = true;
        this.q = "goods/suggestlist";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put(MsgConstant.KEY_ACTION_TYPE, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ai_1(this));
    }

    public void b(String str) {
        this.c = false;
        this.q = "goods/suggestlist";
        ab abVar = new ab();
        abVar.b((this.a.size() / 8) + 1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put(MsgConstant.KEY_ACTION_TYPE, str);
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
                case -1494392599:
                    if (str.equals("goods/suggestlist")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        if (this.c) {
                            this.a.clear();
                        }
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.a.add(az.a(optJSONArray.optJSONObject(i)));
                                i++;
                            }
                        }
                    }
                    this.b = aa.a(jSONObject.optJSONObject("paginated"));
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
