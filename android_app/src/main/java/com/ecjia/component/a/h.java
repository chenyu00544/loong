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
import com.ecjia.hamster.model.j;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaCollectListModel */
public class h extends e {
    public ArrayList<j> a = new ArrayList();
    public aa b;
    private boolean c;

    /* compiled from: ECJiaCollectListModel */
    class h_1 implements OnCancelListener {
        final /* synthetic */ h a;

        h_1(h hVar) {
            this.a = hVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCollectListModel */
    class h_2 implements OnCancelListener {
        final /* synthetic */ h a;

        h_2(h hVar) {
            this.a = hVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCollectListModel */
    class h_3 implements OnCancelListener {
        final /* synthetic */ h a;

        h_3(h hVar) {
            this.a = hVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public h(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(boolean z) {
        this.c = true;
        ap c = ap.c();
        this.q = "user/collect/list";
        if (z) {
            this.l.show();
        }
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new h_1(this));
    }

    public void a() {
        this.c = false;
        ap c = ap.c();
        this.q = "user/collect/list";
        ab abVar = new ab();
        abVar.b((this.a.size() / 10) + 1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new h_2(this));
    }

    public void a(String str) {
        ap c = ap.c();
        this.q = "user/collect/delete";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("rec_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new h_3(this));
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
                case 349137140:
                    if (str.equals("user/collect/delete")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 1658704423:
                    if (str.equals("user/collect/list")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (this.c) {
                            this.a.clear();
                        }
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.a.add(j.a(optJSONArray.getJSONObject(i)));
                                i++;
                            }
                        }
                        this.b = aa.a(jSONObject.optJSONObject("paginated"));
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
