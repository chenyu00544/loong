package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.media.WeiXinShareContent;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.aa;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.ae;
import com.unionpay.mobile.android.widgets.af;
import com.unionpay.mobile.android.widgets.ah;
import com.unionpay.mobile.android.widgets.aj;
import com.unionpay.mobile.android.widgets.an;
import com.unionpay.mobile.android.widgets.ao;
import com.unionpay.mobile.android.widgets.ap;
import com.unionpay.mobile.android.widgets.at;
import com.unionpay.mobile.android.widgets.au;
import com.unionpay.mobile.android.widgets.av;
import com.unionpay.mobile.android.widgets.bd;
import com.unionpay.mobile.android.widgets.e;
import com.unionpay.mobile.android.widgets.f;
import com.unionpay.mobile.android.widgets.g;
import com.unionpay.mobile.android.widgets.p;
import com.unionpay.mobile.android.widgets.u;
import com.unionpay.mobile.android.widgets.y;
import com.unionpay.mobile.android.widgets.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a extends LinearLayout implements com.unionpay.mobile.android.upwidget.q.a, com.unionpay.mobile.android.widgets.aa.a, com.unionpay.mobile.android.widgets.ah.a, com.unionpay.mobile.android.widgets.aj.a, com.unionpay.mobile.android.widgets.ap.a {
    private Context a;
    private an b;
    private ArrayList<z> c;
    private long d;
    private b e;
    private boolean f;
    private boolean g;
    private JSONObject h;
    private String i;

    public interface b {
        void a(a aVar);

        void a(boolean z);

        void c(String str);

        void c(String str, String str2);

        void u();
    }

    public class a {
        public int a = 0;
        public String b;
        final /* synthetic */ a c;

        a(a aVar, String str) {
            this.c = aVar;
            this.b = str;
        }

        public final void a(int i, String str) {
            this.b = str;
            this.a = i;
        }

        public final boolean a() {
            return this.a == 0;
        }
    }

    public a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2) {
        this(context, jSONArray, j, bVar, str, z, str2, (byte) 0);
    }

    private a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2, byte b) {
        this(context, jSONArray, j, bVar, str, z, str2, '\u0000');
    }

    private a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2, char c) {
        this(context, jSONArray, j, bVar, str, z, false, null, null, str2);
    }

    public a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, boolean z2, z zVar, JSONArray jSONArray2, String str2) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = true;
        this.h = null;
        this.i = "";
        this.a = context;
        this.d = j;
        this.e = bVar;
        this.f = z2;
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.d.a.f;
        layoutParams.bottomMargin = i;
        layoutParams.topMargin = i;
        setLayoutParams(layoutParams);
        setPadding(0, 0, 0, 0);
        setOrientation(1);
        setBackgroundColor(0);
        a(jSONArray, str, z, zVar, jSONArray2, str2);
    }

    public a(Context context, JSONArray jSONArray, b bVar, String str) {
        this(context, jSONArray, -1, bVar, null, true, str);
    }

    private a a(boolean z) {
        z zVar;
        a aVar = new a(this, "");
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                zVar = (z) it.next();
                if (zVar instanceof af) {
                    if (!zVar.c()) {
                        aVar.a(-1, String.format(c.bD.aC, new Object[]{c.bD.aE}));
                    } else if (!zVar.b()) {
                        aVar.a(-1, String.format(c.bD.aD, new Object[]{c.bD.aE}));
                    }
                } else if (!zVar.c()) {
                    aVar.a(-1, String.format(c.bD.aC, new Object[]{zVar.r()}));
                    break;
                } else if (!zVar.b()) {
                    aVar.a(-1, String.format(c.bD.aD, new Object[]{zVar.r()}));
                    break;
                }
            }
        }
        if (!aVar.a()) {
            return aVar;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (this.c != null) {
            int i = 0;
            while (i < this.c.size()) {
                zVar = (z) this.c.get(i);
                if (!(zVar instanceof ad) && ((!(zVar instanceof UPWidget) || z) && !TextUtils.isEmpty(((z) this.c.get(i)).h()) && ((z) this.c.get(i)).f())) {
                    stringBuffer.append(",");
                    stringBuffer.append(((z) this.c.get(i)).h());
                }
                i++;
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            stringBuffer2 = stringBuffer2.substring(1);
        }
        aVar.a(0, stringBuffer2);
        return aVar;
    }

    private static z a(List<z> list, String str) {
        for (z zVar : list) {
            if (zVar.n().equalsIgnoreCase(str)) {
                return zVar;
            }
        }
        return null;
    }

    public final a a() {
        a aVar = new a(this, "");
        z a = a(this.c, "pin");
        if (a != null) {
            if (!a.c()) {
                aVar.a(-1, String.format(c.bD.aC, new Object[]{a.r()}));
            } else if (!a.b()) {
                aVar.a(-1, String.format(c.bD.aD, new Object[]{a.r()}));
            }
            if (aVar.a()) {
                aVar.b = a.a();
            }
        }
        return aVar;
    }

    public final String a(String str) {
        z a = a(this.c, str);
        String str2 = "";
        if (a != null) {
            str2 = a.h();
        }
        k.a("uppay", " name:" + str + ", value:" + str2);
        return str2;
    }

    public final void a(int i) {
        z a = a(this.c, "sms");
        if (a != null) {
            ((ap) a).a(i);
        }
    }

    public final void a(OnClickListener onClickListener) {
        z c = c("promotion");
        if (c != null && (c instanceof aj)) {
            ((aj) c).b(onClickListener);
        }
    }

    public final void a(an anVar, JSONObject jSONObject) {
        this.b = anVar;
        this.h = jSONObject;
    }

    public final void a(u uVar, String str) {
        if (this.e != null) {
            boolean z;
            if (str == null || str.length() <= 0) {
                z = true;
            } else {
                if (this.c != null) {
                    Iterator it = this.c.iterator();
                    while (it.hasNext()) {
                        z zVar = (z) it.next();
                        if ((zVar instanceof aa) && !((aa) zVar).a(uVar) && !((aa) zVar).c()) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
            }
            this.e.a(z);
        }
    }

    public final void a(z zVar) {
        boolean z = zVar instanceof ap;
        if (this.e != null && z) {
            d();
            a aVar = new a(this, "");
            z a = a(this.c, "mobile");
            z a2 = a(this.c, "pan");
            z a3 = a(this.c, "card");
            z a4 = a(this.c, "area_code");
            String str = "";
            if (a2 != null) {
                if (!a2.c()) {
                    aVar.a(-1, String.format(c.bD.aC, new Object[]{c.bD.aE}));
                } else if (a2.b()) {
                    str = str + a2.h();
                } else {
                    aVar.a(-1, String.format(c.bD.aD, new Object[]{c.bD.aE}));
                }
            }
            if (aVar.a()) {
                if (a != null) {
                    if (!a.c()) {
                        aVar.a(-1, String.format(c.bD.aC, new Object[]{a.r()}));
                    } else if (a.b()) {
                        str = (str + (str.length() == 0 ? "" : ",")) + a.h();
                    } else {
                        aVar.a(-1, String.format(c.bD.aD, new Object[]{a.r()}));
                    }
                }
                if (aVar.a()) {
                    if (a3 != null && a3.h().length() > 0) {
                        str = (str + (str.length() == 0 ? "" : ",")) + a3.h();
                    }
                    if (a4 != null && a4.h().length() > 0) {
                        str = (str + (str.length() == 0 ? "" : ",")) + a4.h();
                    }
                    aVar.a(0, str);
                    this.e.a(aVar);
                    return;
                }
                this.e.a(aVar);
                return;
            }
            this.e.a(aVar);
        }
    }

    public final void a(String str, String str2) {
        if (this.e != null) {
            d();
            this.e.c(str, str2);
        }
    }

    public final void a(String str, boolean z) {
        String str2 = "promotion".equalsIgnoreCase(str) ? "instalment" : "promotion";
        z c = c(str);
        z c2 = c(str2);
        if (c != null) {
            if (c instanceof aj) {
                ((aj) c).a(z);
                if (c2 != null && ((p) c2).g()) {
                    Toast.makeText(this.a, this.i, 1).show();
                    ((p) c2).b(false);
                }
            } else if (c instanceof p) {
                if (z) {
                    this.e.u();
                }
                ((p) c).b(z);
            }
        }
    }

    public final void a(JSONArray jSONArray) {
        z c = c("promotion");
        if (c != null && (c instanceof aj)) {
            ((aj) c).a(jSONArray);
        }
    }

    public final void a(JSONArray jSONArray, String str) {
        z c = c("promotion");
        if (c != null && (c instanceof aj)) {
            ((aj) c).a(jSONArray, str);
        }
    }

    public final void a(JSONArray jSONArray, String str, boolean z, z zVar, JSONArray jSONArray2, String str2) {
        View pVar;
        String str3;
        if (jSONArray != null && jSONArray.length() > 0) {
            if (this.c == null) {
                this.c = new ArrayList(1);
            } else {
                this.c.clear();
            }
            removeAllViews();
            setBackgroundColor(-1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            int i = com.unionpay.mobile.android.d.a.f;
            int i2 = com.unionpay.mobile.android.d.a.I - (com.unionpay.mobile.android.d.a.f * 4);
            int i3 = 0;
            LayoutParams layoutParams2 = null;
            String str4 = "";
            while (i3 < jSONArray.length()) {
                View view;
                LayoutParams layoutParams3;
                String str5;
                LayoutParams layoutParams4;
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i3);
                    String a = j.a(jSONObject, "type");
                    try {
                        Context context = this.a;
                        view = null;
                        String a2 = j.a(jSONObject, "type");
                        if ("pan".equalsIgnoreCase(a2)) {
                            view = new af(context, i2, jSONObject, str2);
                        } else if ("mobile".equalsIgnoreCase(a2)) {
                            view = new ah(context, i2, jSONObject, str2);
                        } else if ("sms".equalsIgnoreCase(a2)) {
                            view = new ap(context, i2, jSONObject, str2, (byte) 0);
                        } else if ("cvn2".equalsIgnoreCase(a2)) {
                            view = new e(context, i2, jSONObject, str2);
                        } else if ("expire".equalsIgnoreCase(a2)) {
                            view = new av(context, i2, jSONObject, str2);
                        } else if ("pwd".equalsIgnoreCase(a2)) {
                            view = new UPWidget(context, this.d, i2, jSONObject, str2);
                        } else if (WeiXinShareContent.TYPE_TEXT.equalsIgnoreCase(a2)) {
                            view = new at(context, i2, jSONObject, str2);
                        } else if ("string".equalsIgnoreCase(a2)) {
                            view = new ad(context, jSONObject, str2);
                        } else if ("cert_id".equalsIgnoreCase(a2)) {
                            view = new f(context, i2, jSONObject, str2);
                        } else if ("cert_type".equalsIgnoreCase(a2)) {
                            view = new g(context, jSONObject, str2);
                        } else if ("name".equalsIgnoreCase(a2)) {
                            view = new ae(context, i2, jSONObject, str2);
                        } else if ("hidden".equalsIgnoreCase(a2)) {
                            view = new y(context, jSONObject, str2);
                        } else if ("user_name".equalsIgnoreCase(a2)) {
                            view = new au(context, i2, jSONObject, str2);
                        } else if ("password".equalsIgnoreCase(a2)) {
                            view = new ao(context, i2, jSONObject, str2);
                        } else if ("point".equalsIgnoreCase(a2)) {
                            view = new bd(context, i2, jSONObject, str2);
                        } else if ("instalment".equalsIgnoreCase(a2)) {
                            pVar = new p(this.a, jSONObject, str2);
                            ((p) pVar).a((com.unionpay.mobile.android.upwidget.q.a) this);
                            view = pVar;
                        } else if ("promotion".equalsIgnoreCase(a2)) {
                            pVar = new aj(this.a, jSONObject, str2, this);
                            ((aj) pVar).a((com.unionpay.mobile.android.upwidget.q.a) this);
                            view = pVar;
                        } else if ("area_code".equalsIgnoreCase(a2)) {
                            view = new com.unionpay.mobile.android.widgets.a(this.a, jSONObject, jSONArray2, str2);
                        }
                        try {
                            layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                            pVar = view;
                            str5 = a;
                        } catch (JSONException e) {
                            str3 = a;
                            k.c("uppay", "json parser exception!!! - UPRuleView");
                            str5 = str3;
                            layoutParams3 = layoutParams2;
                            pVar = view;
                            if (pVar == null) {
                                if (!(pVar instanceof UPWidget)) {
                                    ((UPWidget) pVar).a(this.d);
                                    ((UPWidget) pVar).b(str);
                                    ((UPWidget) pVar).b(z);
                                } else if (!(pVar instanceof ap)) {
                                    ((ap) pVar).a((com.unionpay.mobile.android.widgets.ap.a) this);
                                } else if (pVar instanceof ah) {
                                    ((ah) pVar).a((com.unionpay.mobile.android.widgets.ah.a) this);
                                }
                                ((aa) pVar).a((com.unionpay.mobile.android.widgets.aa.a) this);
                                if (!"instalment".equals(str5)) {
                                    view = new LinearLayout(this.a);
                                    view.setBackgroundColor(-3419943);
                                    layoutParams4 = new LinearLayout.LayoutParams(-1, 1);
                                    if (i3 == 0) {
                                        layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                                    } else if (this.f) {
                                        layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                                        setPadding(0, 0, 0, 0);
                                    }
                                    if (!this.f) {
                                    }
                                    if (pVar.getVisibility() == 0) {
                                        addView(view, layoutParams4);
                                        addView(pVar, layoutParams3);
                                        addView(zVar, layoutParams3);
                                    }
                                    view = new LinearLayout(this.a);
                                    view.setBackgroundColor(-3419943);
                                    addView(view, new LinearLayout.LayoutParams(-1, 1));
                                }
                                this.c.add(pVar);
                            }
                            i3++;
                            layoutParams2 = layoutParams3;
                            str4 = str5;
                        }
                    } catch (JSONException e2) {
                        view = null;
                        str3 = a;
                        k.c("uppay", "json parser exception!!! - UPRuleView");
                        str5 = str3;
                        layoutParams3 = layoutParams2;
                        pVar = view;
                        if (pVar == null) {
                            if (!(pVar instanceof UPWidget)) {
                                ((UPWidget) pVar).a(this.d);
                                ((UPWidget) pVar).b(str);
                                ((UPWidget) pVar).b(z);
                            } else if (!(pVar instanceof ap)) {
                                ((ap) pVar).a((com.unionpay.mobile.android.widgets.ap.a) this);
                            } else if (pVar instanceof ah) {
                                ((ah) pVar).a((com.unionpay.mobile.android.widgets.ah.a) this);
                            }
                            ((aa) pVar).a((com.unionpay.mobile.android.widgets.aa.a) this);
                            if ("instalment".equals(str5)) {
                                view = new LinearLayout(this.a);
                                view.setBackgroundColor(-3419943);
                                layoutParams4 = new LinearLayout.LayoutParams(-1, 1);
                                if (i3 == 0) {
                                    layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                                } else if (this.f) {
                                    layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                                    setPadding(0, 0, 0, 0);
                                }
                                if (!this.f) {
                                }
                                if (pVar.getVisibility() == 0) {
                                    addView(view, layoutParams4);
                                    addView(pVar, layoutParams3);
                                    addView(zVar, layoutParams3);
                                }
                                view = new LinearLayout(this.a);
                                view.setBackgroundColor(-3419943);
                                addView(view, new LinearLayout.LayoutParams(-1, 1));
                            }
                            this.c.add(pVar);
                        }
                        i3++;
                        layoutParams2 = layoutParams3;
                        str4 = str5;
                    }
                } catch (JSONException e3) {
                    str3 = str4;
                    view = null;
                    k.c("uppay", "json parser exception!!! - UPRuleView");
                    str5 = str3;
                    layoutParams3 = layoutParams2;
                    pVar = view;
                    if (pVar == null) {
                        if (!(pVar instanceof UPWidget)) {
                            ((UPWidget) pVar).a(this.d);
                            ((UPWidget) pVar).b(str);
                            ((UPWidget) pVar).b(z);
                        } else if (!(pVar instanceof ap)) {
                            ((ap) pVar).a((com.unionpay.mobile.android.widgets.ap.a) this);
                        } else if (pVar instanceof ah) {
                            ((ah) pVar).a((com.unionpay.mobile.android.widgets.ah.a) this);
                        }
                        ((aa) pVar).a((com.unionpay.mobile.android.widgets.aa.a) this);
                        if ("instalment".equals(str5)) {
                            view = new LinearLayout(this.a);
                            view.setBackgroundColor(-3419943);
                            layoutParams4 = new LinearLayout.LayoutParams(-1, 1);
                            if (i3 == 0) {
                                layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                            } else if (this.f) {
                                layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                                setPadding(0, 0, 0, 0);
                            }
                            if (!this.f) {
                            }
                            if (pVar.getVisibility() == 0) {
                                addView(view, layoutParams4);
                                addView(pVar, layoutParams3);
                                addView(zVar, layoutParams3);
                            }
                            view = new LinearLayout(this.a);
                            view.setBackgroundColor(-3419943);
                            addView(view, new LinearLayout.LayoutParams(-1, 1));
                        }
                        this.c.add(pVar);
                    }
                    i3++;
                    layoutParams2 = layoutParams3;
                    str4 = str5;
                }
                if (pVar == null) {
                    if (!(pVar instanceof UPWidget)) {
                        ((UPWidget) pVar).a(this.d);
                        ((UPWidget) pVar).b(str);
                        ((UPWidget) pVar).b(z);
                    } else if (!(pVar instanceof ap)) {
                        ((ap) pVar).a((com.unionpay.mobile.android.widgets.ap.a) this);
                    } else if (pVar instanceof ah) {
                        ((ah) pVar).a((com.unionpay.mobile.android.widgets.ah.a) this);
                    }
                    if ((pVar instanceof aa) && !(pVar instanceof bd)) {
                        ((aa) pVar).a((com.unionpay.mobile.android.widgets.aa.a) this);
                    }
                    if ("instalment".equals(str5)) {
                        view = new LinearLayout(this.a);
                        view.setBackgroundColor(-3419943);
                        layoutParams4 = new LinearLayout.LayoutParams(-1, 1);
                        if (i3 == 0) {
                            layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                        } else if (this.f) {
                            layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
                            setPadding(0, 0, 0, 0);
                        }
                        if (!this.f && i3 == 0 && zVar != null) {
                            addView(zVar, layoutParams3);
                            if (pVar.getVisibility() == 0) {
                                addView(view, layoutParams4);
                            }
                            addView(pVar, layoutParams3);
                        } else if (pVar.getVisibility() == 0) {
                            addView(view, layoutParams4);
                            addView(pVar, layoutParams3);
                            if ((pVar instanceof af) && zVar != null) {
                                addView(zVar, layoutParams3);
                            }
                        }
                        if (i3 == jSONArray.length() - 1 || (pVar instanceof aj)) {
                            view = new LinearLayout(this.a);
                            view.setBackgroundColor(-3419943);
                            addView(view, new LinearLayout.LayoutParams(-1, 1));
                        }
                    }
                    this.c.add(pVar);
                }
                i3++;
                layoutParams2 = layoutParams3;
                str4 = str5;
            }
        }
    }

    public final void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            CharSequence a = j.a(jSONObject, "instalment_empty_info");
            if (TextUtils.isEmpty(a)) {
                ((p) c("instalment")).a(true);
                ((p) c("instalment")).b(true);
                ((p) a(this.c, "instalment_policy")).a(j.d(jSONObject, "new_instalments"));
                return;
            }
            ((p) c("instalment")).a(false);
            ((p) c("instalment")).b(false);
            Toast.makeText(this.a, a, 1).show();
        }
    }

    public final a b() {
        return a(true);
    }

    public final String b(String str) {
        z a = a(this.c, str);
        return a != null ? a.a() : "";
    }

    public final void b(OnClickListener onClickListener) {
        z c = c("promotion");
        if (c != null && (c instanceof aj)) {
            ((aj) c).c(onClickListener);
        }
    }

    public final z c(String str) {
        if (this.c == null || this.c.size() <= 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            z zVar = (z) it.next();
            if (zVar.o().equalsIgnoreCase(str)) {
                return zVar;
            }
        }
        return null;
    }

    public final HashMap<String, String> c() {
        if (!a(false).a()) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap();
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); i++) {
                z zVar = (z) this.c.get(i);
                if (!((zVar instanceof ad) || (zVar instanceof UPWidget) || TextUtils.isEmpty(zVar.a()))) {
                    hashMap.put(zVar.n(), zVar.a());
                }
            }
        }
        return hashMap;
    }

    public final void c(OnClickListener onClickListener) {
        z c = c("promotion");
        if (c != null && (c instanceof aj)) {
            ((aj) c).a(onClickListener);
        }
    }

    public final void d(String str) {
        this.i = str;
    }

    public final boolean d() {
        boolean z;
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                z zVar = (z) it.next();
                if ((zVar instanceof UPWidget) && ((UPWidget) zVar).j()) {
                    ((UPWidget) zVar).k();
                    z = true;
                    break;
                }
            }
        }
        z = false;
        ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        return z;
    }

    public final void e(String str) {
        if (this.e != null) {
            this.e.c(str);
        }
    }

    public final boolean e() {
        Object obj;
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                z zVar = (z) it.next();
                if ((zVar instanceof aa) && !((aa) zVar).c()) {
                    obj = 1;
                    break;
                }
            }
        }
        obj = null;
        return obj == null;
    }

    public final void f() {
        if (this.c != null && this.c.size() > 0) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                z zVar = (z) it.next();
                if ((zVar instanceof UPWidget) || (zVar instanceof e) || (zVar instanceof av)) {
                    ((aa) zVar).g();
                }
            }
        }
    }

    public final void g() {
        z c = c("instalment");
        if (c != null && ((p) c).g()) {
            Toast.makeText(this.a, this.i, 1).show();
            ((p) c).b(false);
        }
    }
}
