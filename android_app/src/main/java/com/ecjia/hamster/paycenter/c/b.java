package com.ecjia.hamster.paycenter.c;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import anet.channel.util.HttpConstant;
import com.ecjia.a.t;
import com.ecjia.hamster.paycenter.base.ECJiaOnPaySucceedListener.PaymentType;
import com.tencent.mm.sdk.constants.ConstantsAPI.WXApp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.message.proguard.f;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: ECJiaWXpayHelper */
public class b extends com.ecjia.hamster.paycenter.base.a<a> {
    Map<String, String> a;
    StringBuffer i = new StringBuffer();
    PayReq j = new PayReq();
    final IWXAPI k = WXAPIFactory.createWXAPI(this.h, null);
    private String l;

    /* compiled from: ECJiaWXpayHelper */
    private class a extends AsyncTask<Void, Void, Map<String, String>> {
        a a;
        final /* synthetic */ b b;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Map) obj);
        }

        public a(b bVar, a aVar) {
            this.b = bVar;
            this.a = aVar;
        }

        protected void onPreExecute() {
        }

        protected void a(Map<String, String> map) {
            this.b.i.append("prepay_id\n" + ((String) map.get("prepay_id")) + "\n\n");
            this.b.a = map;
            this.b.d(this.a);
        }

        protected void onCancelled() {
            super.onCancelled();
        }

        protected Map<String, String> a(Void... voidArr) {
            String format = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder", new Object[0]);
            String b = this.b.b(this.a);
            Log.e("orion", b);
            b = new String(com.ecjia.hamster.paycenter.c.a.a.a(format, b));
            if (b.contains(HttpConstant.SUCCESS)) {
                Log.e("orionf", b);
            } else {
                Log.e("orionf", b);
            }
            return this.b.a(b);
        }
    }

    public b(Activity activity) {
        super(activity);
        this.k.registerApp("wx38b6c8356cdad915");
    }

    public void a(a aVar) {
        new a(this, aVar).execute(new Void[0]);
    }

    public Map<String, String> a(String str) {
        try {
            Map<String, String> hashMap = new HashMap();
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(new StringReader(str));
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                String name = newPullParser.getName();
                switch (eventType) {
                    case 2:
                        if (!"xml".equals(name)) {
                            hashMap.put(name, newPullParser.nextText());
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
            return hashMap;
        } catch (Exception e) {
            Log.e("orion", e.toString());
            return null;
        }
    }

    private String b(a aVar) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String b = b();
            stringBuffer.append("</xml>");
            List linkedList = new LinkedList();
            linkedList.add(new BasicNameValuePair("appid", "wx38b6c8356cdad915"));
            linkedList.add(new BasicNameValuePair("body", aVar.d()));
            linkedList.add(new BasicNameValuePair("mch_id", aVar.b()));
            linkedList.add(new BasicNameValuePair("nonce_str", b));
            linkedList.add(new BasicNameValuePair("notify_url", aVar.e()));
            linkedList.add(new BasicNameValuePair("out_trade_no", c(aVar)));
            linkedList.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));
            linkedList.add(new BasicNameValuePair("total_fee", ((int) new BigDecimal(aVar.c()).multiply(new BigDecimal(100)).doubleValue()) + ""));
            linkedList.add(new BasicNameValuePair("trade_type", "APP"));
            linkedList.add(new BasicNameValuePair("sign", a(linkedList, aVar)));
            return a(linkedList);
        } catch (Exception e) {
            Log.e("WXPAYTAG", "genProductArgs fail, ex = " + e.getMessage());
            return null;
        }
    }

    private String b() {
        return t.a(String.valueOf(new Random().nextInt(10000)).getBytes());
    }

    private long c() {
        return System.currentTimeMillis() / 1000;
    }

    private String c(a aVar) {
        return aVar.f();
    }

    private String a(List<NameValuePair> list, a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(((NameValuePair) list.get(i)).getName());
            stringBuilder.append('=');
            stringBuilder.append(((NameValuePair) list.get(i)).getValue());
            stringBuilder.append('&');
        }
        stringBuilder.append("key=");
        stringBuilder.append(aVar.a());
        String toUpperCase = t.a(stringBuilder.toString().getBytes()).toUpperCase();
        Log.e("orion", toUpperCase);
        return toUpperCase;
    }

    private String b(List<NameValuePair> list, a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(((NameValuePair) list.get(i)).getName());
            stringBuilder.append('=');
            stringBuilder.append(((NameValuePair) list.get(i)).getValue());
            stringBuilder.append('&');
        }
        stringBuilder.append("key=");
        stringBuilder.append(aVar.a());
        this.i.append("sign str\n" + stringBuilder.toString() + "\n\n");
        String toUpperCase = t.a(stringBuilder.toString().getBytes()).toUpperCase();
        Log.e("orion", toUpperCase);
        return toUpperCase;
    }

    private String a(List<NameValuePair> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>");
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append("<" + ((NameValuePair) list.get(i)).getName() + ">");
            stringBuilder.append(((NameValuePair) list.get(i)).getValue());
            stringBuilder.append("</" + ((NameValuePair) list.get(i)).getName() + ">");
        }
        stringBuilder.append("</xml>");
        try {
            this.l = new String(stringBuilder.toString().getBytes("UTF-8"), f.a);
        } catch (Exception e) {
        }
        Log.e("orion", stringBuilder.toString());
        return this.l;
    }

    private void d(a aVar) {
        this.j.appId = "wx38b6c8356cdad915";
        this.j.partnerId = aVar.b();
        this.j.prepayId = (String) this.a.get("prepay_id");
        this.j.packageValue = "Sign=WXPay";
        this.j.nonceStr = b();
        this.j.timeStamp = String.valueOf(c());
        List linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("appid", this.j.appId));
        linkedList.add(new BasicNameValuePair("noncestr", this.j.nonceStr));
        linkedList.add(new BasicNameValuePair(com.umeng.message.common.a.c, this.j.packageValue));
        linkedList.add(new BasicNameValuePair("partnerid", this.j.partnerId));
        linkedList.add(new BasicNameValuePair("prepayid", this.j.prepayId));
        linkedList.add(new BasicNameValuePair("timestamp", this.j.timeStamp));
        Log.e("orions", linkedList.toString());
        this.j.sign = b(linkedList, aVar);
        this.k.registerApp("wx38b6c8356cdad915");
        this.k.sendReq(this.j);
    }

    public boolean a() {
        return a(this.h);
    }

    public boolean a(Context context) {
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            if (WXApp.WXAPP_PACKAGE_NAME.equals(((PackageInfo) installedPackages.get(i)).packageName)) {
                return true;
            }
        }
        return false;
    }

    public void a(PaymentType paymentType, String str) {
        if (this.g != null) {
            this.g.a(paymentType, str);
        }
    }
}
