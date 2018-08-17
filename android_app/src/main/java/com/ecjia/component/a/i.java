package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.activity.goodsdetail.a.a;
import com.ecjia.hamster.model.ECJia_ORDER_COMMENTS_LIST;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaCommentModel */
public class i extends e {
    public aa a;
    public int b;
    public int c;
    public int d;
    public int e;
    public ArrayList<ECJia_ORDER_COMMENTS_LIST> f = new ArrayList();
    public ArrayList<String> g = new ArrayList();
    public int h;
    public String i;
    public ArrayList<a> j = new ArrayList();
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    private boolean y;

    /* compiled from: ECJiaCommentModel */
    class i_1 implements OnCancelListener {
        final /* synthetic */ i a;

        i_1(i iVar) {
            this.a = iVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCommentModel */
    class i_2 implements OnCancelListener {
        final /* synthetic */ i a;

        i_2(i iVar) {
            this.a = iVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCommentModel */
    class i_3 implements OnCancelListener {
        final /* synthetic */ i a;

        i_3(i iVar) {
            this.a = iVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCommentModel */
    class i_4 implements OnCancelListener {
        final /* synthetic */ i a;

        i_4(i iVar) {
            this.a = iVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCommentModel */
    class i_5 implements OnCancelListener {
        final /* synthetic */ i a;

        i_5(i iVar) {
            this.a = iVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public i(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "orders/comment";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("order_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new i_1(this));
    }

    public void b(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "orders/comment/detail";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("rec_id", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new i_2(this));
    }

    public void a(String str, String str2, boolean z) {
        this.y = true;
        this.q = "goods/comment/list";
        if (z) {
            this.l.show();
        }
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put("goods_id", str);
            jSONObject.put("type", str2);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new i_3(this));
    }

    public void b(String str, String str2) {
        this.y = false;
        this.q = "goods/comment/list";
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b((this.j.size() / 10) + 1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put("goods_id", str);
            jSONObject.put("type", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new i_4(this));
    }

    public void a(String str, String str2, int i, int i2, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ap c = ap.c();
        this.l.show();
        this.q = "comment/create";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("rec_id", str);
            jSONObject.put("is_anonymous", i2);
            jSONObject.put("content", str2);
            jSONObject.put("comment_rank", i);
            jSONObject.put("comment_image", "upload_imgs");
        } catch (JSONException e) {
        }
        this.s.a(this.q, jSONObject.toString(), (ArrayList) arrayList2);
        this.l.setOnCancelListener(new i_5(this));
    }

    public static void a(String str, String str2, Bitmap bitmap) {
        q.a("filePath + picName  " + str + "/" + str2);
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str, str2));
            bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
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
                case -1819158219:
                    if (str.equals("orders/comment")) {
                        i2 = 0;
                        break;
                    }
                    break;
                case -39028052:
                    if (str.equals("comment/create")) {
                        i2 = 3;
                        break;
                    }
                    break;
                case 1051019883:
                    if (str.equals("orders/comment/detail")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 1707207783:
                    if (str.equals("goods/comment/list")) {
                        i2 = 2;
                        break;
                    }
                    break;
            }
            JSONObject optJSONObject;
            JSONArray optJSONArray;
            switch (i2) {
                case 0:
                    if (this.r.b() == 1) {
                        optJSONObject = jSONObject.optJSONObject("data");
                        this.b = optJSONObject.optInt("comment_conformity_of_goods");
                        this.c = optJSONObject.optInt("comment_merchant_service");
                        this.d = optJSONObject.optInt("comment_delivery");
                        this.e = optJSONObject.optInt("comment_delivery_sender");
                        optJSONArray = optJSONObject.optJSONArray("comment_order_list");
                        this.f.clear();
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.f.add(ECJia_ORDER_COMMENTS_LIST.fromJson(optJSONArray.getJSONObject(i)));
                                i++;
                            }
                            break;
                        }
                    }
                    break;
                case 1:
                    if (this.r.b() == 1) {
                        optJSONObject = jSONObject.optJSONObject("data");
                        this.h = optJSONObject.optInt("comment_goods");
                        this.i = optJSONObject.optString("comment_content");
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("comment_image");
                        this.g.clear();
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            for (i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                this.g.add((String) optJSONArray2.get(i2));
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    if (this.r.b() == 1) {
                        optJSONObject = jSONObject.optJSONObject("data");
                        this.t = optJSONObject.optInt("comment_count");
                        this.u = optJSONObject.optInt("comment_positive");
                        this.v = optJSONObject.optInt("comment_moderate");
                        this.w = optJSONObject.optInt("comment_negative");
                        this.x = optJSONObject.optInt("comment_showorder");
                        optJSONArray = optJSONObject.optJSONArray("comment_list");
                        if (this.y) {
                            this.j.clear();
                        }
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            while (i < optJSONArray.length()) {
                                this.j.add(a.a(optJSONArray.optJSONObject(i)));
                                i++;
                            }
                        }
                        this.a = aa.a(jSONObject.optJSONObject("paginated"));
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
