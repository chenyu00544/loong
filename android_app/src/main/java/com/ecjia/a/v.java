package com.ecjia.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.ecjia.a.a.b;
import com.ecjia.component.a.a.c;
import com.ecjia.consts.a;
import com.ecjia.hamster.activity.ECJiaAccountActivity;
import com.ecjia.hamster.activity.ECJiaAddressManageActivity;
import com.ecjia.hamster.activity.ECJiaBannerWebActivity;
import com.ecjia.hamster.activity.ECJiaChangePasswordActivity;
import com.ecjia.hamster.activity.ECJiaCustomercenterActivity;
import com.ecjia.hamster.activity.ECJiaGetCodeActivity;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaGoodsListActivity;
import com.ecjia.hamster.activity.ECJiaHelpListActivity;
import com.ecjia.hamster.activity.ECJiaLanguageActivity;
import com.ecjia.hamster.activity.ECJiaLastBrowseActivity;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.activity.ECJiaMainActivity;
import com.ecjia.hamster.activity.ECJiaMapActivity;
import com.ecjia.hamster.activity.ECJiaMyCaptureActivity;
import com.ecjia.hamster.activity.ECJiaMyFindActivity;
import com.ecjia.hamster.activity.ECJiaMyPurseActivity;
import com.ecjia.hamster.activity.ECJiaOrderListAllActivity;
import com.ecjia.hamster.activity.ECJiaOrderdetailActivity;
import com.ecjia.hamster.activity.ECJiaSearchAllActivity;
import com.ecjia.hamster.activity.ECJiaSettingActivity;
import com.ecjia.hamster.activity.ECJiaShareQRCodeActivity;
import com.ecjia.hamster.activity.ECJiaShoppingCartActivity;
import com.ecjia.hamster.activity.ECJiaWebViewActivity;
import com.ecjia.hamster.model.ECJia_DEVICE;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.z;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.umeng.message.MsgConstant;
import com.umeng.message.entity.UMessage;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

/* compiled from: ECJiaPushUtil */
public class v {
    public static void a(final ECJiaApplication eCJiaApplication, final String str) {
        ECJia_DEVICE eCJia_DEVICE = (ECJia_DEVICE) aa.b(x.app(), "deviceInfo", "device");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str2 = a.a() + "device/setDeviceToken";
        q.a("===" + str2 + "传入===" + jSONObject.toString());
        RequestParams requestParams = new RequestParams(str2 + c.a(str2));
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(jSONObject.toString());
        DbCookieStore dbCookieStore = DbCookieStore.INSTANCE;
        String str3 = "";
        if (dbCookieStore.getCookies() != null) {
            List cookies = dbCookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                HttpCookie httpCookie = (HttpCookie) cookies.get(i);
                if (!TextUtils.isEmpty(httpCookie.getName())) {
                    if (TextUtils.isEmpty(httpCookie.getValue()) || httpCookie.getValue().equals("deleted")) {
                        str3 = "";
                    } else {
                        str3 = httpCookie.getValue();
                    }
                    requestParams.addHeader("Cookie", httpCookie.getName() + "=" + str3);
                    q.b("===cookie传入===" + httpCookie.getName() + "=" + str3);
                }
            }
        }
        requestParams.addHeader("Device-client", eCJia_DEVICE.getClient());
        requestParams.addHeader("Device-code", eCJia_DEVICE.getCode());
        requestParams.addHeader("Device-udid", eCJia_DEVICE.getUdid());
        requestParams.addHeader("Api-vesion", "1.8.0");
        x.http().post(requestParams, new CommonCallback<String>() {
            public void onSuccess(String str) {
                DbCookieStore dbCookieStore = DbCookieStore.INSTANCE;
                List cookies = dbCookieStore.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    HttpCookie httpCookie = (HttpCookie) cookies.get(i);
                    if ((TextUtils.isEmpty(httpCookie.getValue()) || httpCookie.getValue().equals("deleted")) && dbCookieStore.getURIs().size() == cookies.size()) {
                        dbCookieStore.remove((URI) dbCookieStore.getURIs().get(i), httpCookie);
                    }
                }
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    q.a("===device/setDeviceToken返回===" + jSONObject.toString());
                    if (ax.a(jSONObject.optJSONObject("status")).b() == 1) {
                        eCJiaApplication.getSharedPreferences("device", 0).edit().putString(MsgConstant.KEY_DEVICE_TOKEN, str).putLong("time", System.currentTimeMillis()).commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onError(Throwable th, boolean z) {
                q.b("===HttpError-setDeviceToken===" + th.getMessage() + th);
            }

            public void onCancelled(CancelledException cancelledException) {
                Toast.makeText(x.app(), "cancelled", 1).show();
            }

            public void onFinished() {
            }
        });
    }

    public static void a(Context context, UMessage uMessage) {
        a(context, uMessage, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r6, com.umeng.message.entity.UMessage r7, boolean r8) {
        /*
        r3 = 2;
        r2 = 1;
        r5 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        if (r8 != 0) goto L_0x0013;
    L_0x0006:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaMainActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
    L_0x0013:
        r0 = r7.extra;
        if (r0 == 0) goto L_0x05b8;
    L_0x0017:
        r0 = r7.extra;
        r1 = "open_type";
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x05a4;
    L_0x0027:
        r1 = -1;
        r4 = r0.hashCode();
        switch(r4) {
            case -1767830088: goto L_0x0118;
            case -1613589672: goto L_0x00a1;
            case -1467255178: goto L_0x00e8;
            case -951532658: goto L_0x005a;
            case -906336856: goto L_0x00c4;
            case -902467678: goto L_0x0100;
            case -902467304: goto L_0x010c;
            case -622062775: goto L_0x0160;
            case -191501435: goto L_0x0078;
            case -53241107: goto L_0x0130;
            case 107868: goto L_0x0082;
            case 3046176: goto L_0x00b8;
            case 3198785: goto L_0x00d0;
            case 3343801: goto L_0x0046;
            case 273184745: goto L_0x0050;
            case 292878311: goto L_0x00dc;
            case 348332473: goto L_0x0148;
            case 377959296: goto L_0x013c;
            case 581824094: goto L_0x0064;
            case 926934164: goto L_0x006e;
            case 954925063: goto L_0x008c;
            case 1224424441: goto L_0x00ac;
            case 1267537039: goto L_0x0154;
            case 1689622251: goto L_0x0124;
            case 1985941072: goto L_0x0096;
            case 2050470234: goto L_0x00f4;
            default: goto L_0x002f;
        };
    L_0x002f:
        r0 = r1;
    L_0x0030:
        switch(r0) {
            case 0: goto L_0x016c;
            case 1: goto L_0x017b;
            case 2: goto L_0x0195;
            case 3: goto L_0x01a9;
            case 4: goto L_0x01ef;
            case 5: goto L_0x01fe;
            case 6: goto L_0x0218;
            case 7: goto L_0x0227;
            case 8: goto L_0x023b;
            case 9: goto L_0x024a;
            case 10: goto L_0x0259;
            case 11: goto L_0x0277;
            case 12: goto L_0x02b8;
            case 13: goto L_0x02d6;
            case 14: goto L_0x02e5;
            case 15: goto L_0x0303;
            case 16: goto L_0x0339;
            case 17: goto L_0x036a;
            case 18: goto L_0x039c;
            case 19: goto L_0x03ce;
            case 20: goto L_0x040f;
            case 21: goto L_0x045f;
            case 22: goto L_0x04a0;
            case 23: goto L_0x04e1;
            case 24: goto L_0x0522;
            case 25: goto L_0x0563;
            default: goto L_0x0033;
        };
    L_0x0033:
        r0 = new android.content.Intent;
        r1 = com.ecmoban.android.missmall.ECJiaPushActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r1 = "refresh";
        r0.putExtra(r1, r2);
        r6.startActivity(r0);
    L_0x0045:
        return;
    L_0x0046:
        r4 = "main";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x004e:
        r0 = 0;
        goto L_0x0030;
    L_0x0050:
        r4 = "discover";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0058:
        r0 = r2;
        goto L_0x0030;
    L_0x005a:
        r4 = "qrcode";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0062:
        r0 = r3;
        goto L_0x0030;
    L_0x0064:
        r4 = "qrshare";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x006c:
        r0 = 3;
        goto L_0x0030;
    L_0x006e:
        r4 = "history";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0076:
        r0 = 4;
        goto L_0x0030;
    L_0x0078:
        r4 = "feedback";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0080:
        r0 = 5;
        goto L_0x0030;
    L_0x0082:
        r4 = "map";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x008a:
        r0 = 6;
        goto L_0x0030;
    L_0x008c:
        r4 = "message";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0094:
        r0 = 7;
        goto L_0x0030;
    L_0x0096:
        r4 = "setting";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x009e:
        r0 = 8;
        goto L_0x0030;
    L_0x00a1:
        r4 = "language";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00a9:
        r0 = 9;
        goto L_0x0030;
    L_0x00ac:
        r4 = "webview";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00b4:
        r0 = 10;
        goto L_0x0030;
    L_0x00b8:
        r4 = "cart";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00c0:
        r0 = 11;
        goto L_0x0030;
    L_0x00c4:
        r4 = "search";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00cc:
        r0 = 12;
        goto L_0x0030;
    L_0x00d0:
        r4 = "help";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00d8:
        r0 = 13;
        goto L_0x0030;
    L_0x00dc:
        r4 = "goods_list";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00e4:
        r0 = 14;
        goto L_0x0030;
    L_0x00e8:
        r4 = "goods_comment";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00f0:
        r0 = 15;
        goto L_0x0030;
    L_0x00f4:
        r4 = "goods_detail";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x00fc:
        r0 = 16;
        goto L_0x0030;
    L_0x0100:
        r4 = "signin";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0108:
        r0 = 17;
        goto L_0x0030;
    L_0x010c:
        r4 = "signup";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0114:
        r0 = 18;
        goto L_0x0030;
    L_0x0118:
        r4 = "orders_list";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0120:
        r0 = 19;
        goto L_0x0030;
    L_0x0124:
        r4 = "orders_detail";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x012c:
        r0 = 20;
        goto L_0x0030;
    L_0x0130:
        r4 = "user_wallet";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0138:
        r0 = 21;
        goto L_0x0030;
    L_0x013c:
        r4 = "user_address";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0144:
        r0 = 22;
        goto L_0x0030;
    L_0x0148:
        r4 = "user_account";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0150:
        r0 = 23;
        goto L_0x0030;
    L_0x0154:
        r4 = "user_password";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x015c:
        r0 = 24;
        goto L_0x0030;
    L_0x0160:
        r4 = "user_center";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x002f;
    L_0x0168:
        r0 = 25;
        goto L_0x0030;
    L_0x016c:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaMainActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x017b:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaMainActivity.class;
        r0.<init>(r6, r1);
        r6.startActivity(r0);
        r0 = de.greenrobot.event.c.a();
        r1 = new com.ecjia.a.a.b;
        r2 = "ECJIAMAIN_FIND";
        r1.<init>(r2);
        r0.c(r1);
        goto L_0x0045;
    L_0x0195:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaMyCaptureActivity.class;
        r0.<init>(r6, r1);
        r1 = "startType";
        r0.putExtra(r1, r2);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x01a9:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x01d1;
    L_0x01af:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x01d1;
    L_0x01bd:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaShareQRCodeActivity.class;
        r0.<init>(r6, r1);
        r1 = "startType";
        r0.putExtra(r1, r2);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x01d1:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x01ef:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaLastBrowseActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x01fe:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaWebViewActivity.class;
        r0.<init>(r6, r1);
        r1 = "url";
        r2 = "http://www.missmall.com/mobile/index.php?m=chat";
        r0.putExtra(r1, r2);
        r1 = "title";
        r2 = "";
        r0.putExtra(r1, r2);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0218:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaMapActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0227:
        r0 = new android.content.Intent;
        r1 = com.ecmoban.android.missmall.ECJiaPushActivity.class;
        r0.<init>(r6, r1);
        r1 = "refresh";
        r0.putExtra(r1, r2);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x023b:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaSettingActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x024a:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaLanguageActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0259:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaWebViewActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "url";
        r0 = r7.extra;
        r3 = "url";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x0277:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x029a;
    L_0x027d:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x029a;
    L_0x028b:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaShoppingCartActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x029a:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x02b8:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaSearchAllActivity.class;
        r1.<init>(r6, r0);
        r2 = "keyword";
        r0 = r7.extra;
        r3 = "keyword";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r1.setFlags(r5);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x02d6:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaHelpListActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x02e5:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaGoodsListActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "category_id";
        r0 = r7.extra;
        r3 = "category_id";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x0303:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaGoodsDetailActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "goods_id";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r7.extra;
        r5 = "goods_id";
        r0 = r0.get(r5);
        r0 = (java.lang.String) r0;
        r0 = r4.append(r0);
        r4 = "";
        r0 = r0.append(r4);
        r0 = r0.toString();
        r1.putExtra(r2, r0);
        r0 = "tab_id";
        r1.putExtra(r0, r3);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x0339:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaGoodsDetailActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "goods_id";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r0 = r7.extra;
        r4 = "goods_id";
        r0 = r0.get(r4);
        r0 = (java.lang.String) r0;
        r0 = r3.append(r0);
        r3 = "";
        r0 = r0.append(r3);
        r0 = r0.toString();
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x036a:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x038d;
    L_0x0370:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x038d;
    L_0x037e:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaCustomercenterActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x038d:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x039c:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x03bf;
    L_0x03a2:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x03bf;
    L_0x03b0:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaCustomercenterActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x03bf:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaGetCodeActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x03ce:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x03f1;
    L_0x03d4:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x03f1;
    L_0x03e2:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaOrderListAllActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x03f1:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r1.setFlags(r5);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x040f:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x0441;
    L_0x0415:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0441;
    L_0x0423:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaOrderdetailActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "order_id";
        r0 = r7.extra;
        r3 = "order_id";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x0441:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x045f:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x0482;
    L_0x0465:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0482;
    L_0x0473:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaMyPurseActivity.class;
        r0.<init>(r6, r1);
        r0.addFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0482:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x04a0:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x04c3;
    L_0x04a6:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x04c3;
    L_0x04b4:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaAddressManageActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x04c3:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x04e1:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x0504;
    L_0x04e7:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0504;
    L_0x04f5:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaAccountActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0504:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x0522:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x0545;
    L_0x0528:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0545;
    L_0x0536:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaChangePasswordActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0545:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x0563:
        r0 = com.ecjia.hamster.model.ap.c();
        if (r0 == 0) goto L_0x0586;
    L_0x0569:
        r0 = com.ecjia.hamster.model.ap.c();
        r0 = r0.b();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0586;
    L_0x0577:
        r0 = new android.content.Intent;
        r1 = com.ecjia.hamster.activity.ECJiaCustomercenterActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x0586:
        r1 = new android.content.Intent;
        r0 = com.ecjia.hamster.activity.ECJiaLoginActivity.class;
        r1.<init>(r6, r0);
        r1.setFlags(r5);
        r2 = "from";
        r0 = r7.extra;
        r3 = "open_type";
        r0 = r0.get(r3);
        r0 = (java.lang.String) r0;
        r1.putExtra(r2, r0);
        r6.startActivity(r1);
        goto L_0x0045;
    L_0x05a4:
        r0 = new android.content.Intent;
        r1 = com.ecmoban.android.missmall.ECJiaPushActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r1 = "refresh";
        r0.putExtra(r1, r2);
        r6.startActivity(r0);
        goto L_0x0045;
    L_0x05b8:
        r0 = new android.content.Intent;
        r1 = com.ecmoban.android.missmall.ECJiaPushActivity.class;
        r0.<init>(r6, r1);
        r0.setFlags(r5);
        r1 = "refresh";
        r0.putExtra(r1, r2);
        r6.startActivity(r0);
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.a.v.a(android.content.Context, com.umeng.message.entity.UMessage, boolean):void");
    }

    public static void a(Context context, z zVar) {
        String g = zVar.g();
        if (TextUtils.isEmpty(g)) {
            q.a("其他动作，未做处理");
            return;
        }
        int i = -1;
        switch (g.hashCode()) {
            case -1767830088:
                if (g.equals("orders_list")) {
                    i = 19;
                    break;
                }
                break;
            case -1613589672:
                if (g.equals(com.umeng.analytics.pro.x.F)) {
                    i = 12;
                    break;
                }
                break;
            case -1467255178:
                if (g.equals("goods_comment")) {
                    i = 17;
                    break;
                }
                break;
            case -951532658:
                if (g.equals("qrcode")) {
                    i = 4;
                    break;
                }
                break;
            case -906336856:
                if (g.equals("search")) {
                    i = 14;
                    break;
                }
                break;
            case -902467678:
                if (g.equals("signin")) {
                    i = 1;
                    break;
                }
                break;
            case -902467304:
                if (g.equals("signup")) {
                    i = 2;
                    break;
                }
                break;
            case -622062775:
                if (g.equals("user_center")) {
                    i = 21;
                    break;
                }
                break;
            case -191501435:
                if (g.equals("feedback")) {
                    i = 7;
                    break;
                }
                break;
            case -53241107:
                if (g.equals("user_wallet")) {
                    i = 22;
                    break;
                }
                break;
            case 107868:
                if (g.equals("map")) {
                    i = 8;
                    break;
                }
                break;
            case 3046176:
                if (g.equals("cart")) {
                    i = 13;
                    break;
                }
                break;
            case 3198785:
                if (g.equals("help")) {
                    i = 15;
                    break;
                }
                break;
            case 3343801:
                if (g.equals("main")) {
                    i = 0;
                    break;
                }
                break;
            case 273184745:
                if (g.equals("discover")) {
                    i = 3;
                    break;
                }
                break;
            case 292878311:
                if (g.equals("goods_list")) {
                    i = 16;
                    break;
                }
                break;
            case 348332473:
                if (g.equals("user_account")) {
                    i = 24;
                    break;
                }
                break;
            case 377959296:
                if (g.equals("user_address")) {
                    i = 23;
                    break;
                }
                break;
            case 581824094:
                if (g.equals("qrshare")) {
                    i = 5;
                    break;
                }
                break;
            case 926934164:
                if (g.equals("history")) {
                    i = 6;
                    break;
                }
                break;
            case 954925063:
                if (g.equals("message")) {
                    i = 9;
                    break;
                }
                break;
            case 1224424441:
                if (g.equals("webview")) {
                    i = 10;
                    break;
                }
                break;
            case 1267537039:
                if (g.equals("user_password")) {
                    i = 25;
                    break;
                }
                break;
            case 1689622251:
                if (g.equals("orders_detail")) {
                    i = 20;
                    break;
                }
                break;
            case 1985941072:
                if (g.equals("setting")) {
                    i = 11;
                    break;
                }
                break;
            case 2050470234:
                if (g.equals("goods_detail")) {
                    i = 18;
                    break;
                }
                break;
        }
        Intent intent;
        switch (i) {
            case 0:
                context.startActivity(new Intent(context, ECJiaMainActivity.class));
                return;
            case 1:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                } else {
                    intent = new Intent(context, ECJiaCustomercenterActivity.class);
                }
                context.startActivity(intent);
                return;
            case 2:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaGetCodeActivity.class);
                } else {
                    intent = new Intent(context, ECJiaCustomercenterActivity.class);
                }
                context.startActivity(intent);
                return;
            case 3:
                de.greenrobot.event.c.a().c(new b("ECJIAMAIN_FIND"));
                context.startActivity(new Intent(context, ECJiaMainActivity.class));
                return;
            case 4:
                intent = new Intent(context, ECJiaMyCaptureActivity.class);
                intent.putExtra("startType", 1);
                context.startActivity(intent);
                return;
            case 5:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                    context.startActivity(intent);
                    return;
                }
                intent = new Intent(context, ECJiaShareQRCodeActivity.class);
                intent.putExtra("startType", 1);
                context.startActivity(intent);
                return;
            case 6:
                context.startActivity(new Intent(context, ECJiaLastBrowseActivity.class));
                return;
            case 7:
                intent = new Intent(context, ECJiaWebViewActivity.class);
                intent.putExtra("url", "http://www.missmall.com/mobile/index.php?m=chat");
                intent.putExtra("title", "");
                context.startActivity(intent);
                return;
            case 8:
                context.startActivity(new Intent(context, ECJiaMapActivity.class));
                return;
            case 9:
                return;
            case 10:
                intent = new Intent(context, ECJiaBannerWebActivity.class);
                intent.putExtra("url", zVar.d());
                context.startActivity(intent);
                return;
            case 11:
                context.startActivity(new Intent(context, ECJiaSettingActivity.class));
                return;
            case 12:
                context.startActivity(new Intent(context, ECJiaLanguageActivity.class));
                return;
            case 13:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                    context.startActivity(intent);
                    return;
                }
                context.startActivity(new Intent(context, ECJiaShoppingCartActivity.class));
                return;
            case 14:
                intent = new Intent(context, ECJiaSearchAllActivity.class);
                intent.putExtra("keyword", zVar.q());
                intent.setClass(context, ECJiaMyFindActivity.class);
                context.startActivity(intent);
                return;
            case 15:
                context.startActivity(new Intent(context, ECJiaHelpListActivity.class));
                return;
            case 16:
                intent = new Intent(context, ECJiaGoodsListActivity.class);
                intent.putExtra("category_id", zVar.c());
                context.startActivity(intent);
                return;
            case 17:
                intent = new Intent(context, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", zVar.e() + "");
                intent.putExtra("tab_id", 2);
                context.startActivity(intent);
                return;
            case 18:
                intent = new Intent(context, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", zVar.b() + "");
                context.startActivity(intent);
                return;
            case 19:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                } else {
                    intent = new Intent(context, ECJiaOrderListAllActivity.class);
                }
                context.startActivity(intent);
                return;
            case 20:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                } else {
                    intent = new Intent(context, ECJiaOrderdetailActivity.class);
                    intent.putExtra("order_id", zVar.f());
                }
                context.startActivity(intent);
                return;
            case 21:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                } else {
                    intent = new Intent(context, ECJiaCustomercenterActivity.class);
                }
                context.startActivity(intent);
                return;
            case 22:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                } else {
                    intent = new Intent(context, ECJiaMyPurseActivity.class);
                }
                context.startActivity(intent);
                return;
            case 23:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                } else {
                    intent = new Intent(context, ECJiaAddressManageActivity.class);
                }
                context.startActivity(intent);
                return;
            case 24:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                    context.startActivity(intent);
                } else {
                    intent = new Intent(context, ECJiaAccountActivity.class);
                    context.startActivity(intent);
                }
                context.startActivity(intent);
                return;
            case 25:
                if (ap.c() == null || TextUtils.isEmpty(ap.c().b())) {
                    intent = new Intent(context, ECJiaLoginActivity.class);
                    intent.putExtra("from", g);
                } else {
                    intent = new Intent(context, ECJiaChangePasswordActivity.class);
                }
                context.startActivity(intent);
                return;
            default:
                q.a("其他动作，未做处理");
                return;
        }
    }
}
