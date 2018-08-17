package com.ecjia.component.a.a;

import android.text.TextUtils;
import android.widget.Toast;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.ecjia.a.aa;
import com.ecjia.a.n;
import com.ecjia.a.q;
import com.ecjia.component.view.k;
import com.ecjia.consts.a;
import com.ecjia.hamster.model.ECJia_DEVICE;
import com.ecmoban.android.missmall.ECJiaApplication;
import java.io.File;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

/* compiled from: ECJiaMyHttpUtil */
public class c implements b {
    private ArrayList<b> a = new ArrayList();
    private DbCookieStore b;
    private ECJia_DEVICE c = ((ECJia_DEVICE) aa.b(x.app(), "deviceInfo", "device"));

    /* compiled from: ECJiaMyHttpUtil */
    class c_5 implements CommonCallback<File> {
        final /* synthetic */ c a;

        c_5(c cVar) {
            this.a = cVar;
        }

        public void onSuccess(File file) {
            q.a("===============下载ADpic图片成功");
        }

        public void onError(Throwable th, boolean z) {
            q.a("===============下载ADpic图片失败=====" + th.getMessage());
        }

        public void onCancelled(CancelledException cancelledException) {
            Toast.makeText(x.app(), "cancelled", 1).show();
        }

        public void onFinished() {
        }
    }

    public static String a(String str) {
        String b = n.b();
        String a = n.a();
        return "&timestamp=" + b + "&signature=" + c(a, b, str) + "&nonce=" + a;
    }

    private static String c(String str, String str2, String str3) {
        return n.a("nonce" + str + "timestamp" + str2 + "url" + str3);
    }

    public void a(String str, String str2, String str3) {
        d(str, str2, str3);
    }

    public void b(String str, String str2) {
        d(str, str2, "");
    }

    private void d(final String str, String str2, String str3) {
        String str4;
        if (TextUtils.isEmpty(str3)) {
            str4 = a.a() + str;
        } else {
            str4 = str3 + str;
        }
        q.a("===" + str4 + "传入===" + str2);
        RequestParams requestParams = new RequestParams(str4 + a(str4));
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(str2);
        requestParams.setConnectTimeout(m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT);
        this.b = DbCookieStore.INSTANCE;
        if (this.b.getCookies() != null) {
            List cookies = this.b.getCookies();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < cookies.size(); i++) {
                HttpCookie httpCookie = (HttpCookie) cookies.get(i);
                if (!TextUtils.isEmpty(httpCookie.getName())) {
                    String str5;
                    String value = httpCookie.getValue();
                    if (TextUtils.isEmpty(httpCookie.getValue()) || httpCookie.getValue().equals("deleted")) {
                        str5 = "";
                    } else {
                        str5 = value;
                    }
                    if (httpCookie.getName().equals("ecjia_token") && ((ECJiaApplication) x.app()).c) {
                        str5 = "";
                    }
                    stringBuilder.append(httpCookie.getName()).append("=").append(str5).append(";");
                }
            }
            ((ECJiaApplication) x.app()).c = false;
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            requestParams.setHeader("Cookie", stringBuilder.toString());
            q.b("===HttpPost===" + str + "-cookie传入===" + stringBuilder.toString());
        }
        requestParams.addHeader("Device-client", this.c.getClient());
        requestParams.addHeader("Device-code", this.c.getCode());
        requestParams.addHeader("Device-udid", this.c.getUdid());
        requestParams.addHeader("Api-vesion", "1.8.0");
        x.http().post(requestParams, new CommonCallback<String>(this) {
            final /* synthetic */ c b;

            public void onSuccess(String str) {
                this.b.b = DbCookieStore.INSTANCE;
                List cookies = this.b.b.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    q.b("===HttpPost===" + str + "-cookie返回===" + ((HttpCookie) cookies.get(i)).toString());
                }
                this.b.a(str, str);
            }

            public void onError(Throwable th, boolean z) {
                if (th instanceof HttpException) {
                    HttpException httpException = (HttpException) th;
                    int code = httpException.getCode();
                    String message = httpException.getMessage();
                    q.b("===HttpError===" + str + "-error返回===" + code + message + "--" + httpException.getResult());
                    k kVar = new k(x.app(), "网络状况不是很给力哦！");
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                q.b("===HttpError===" + str + "-error返回===" + th.getMessage() + th);
            }

            public void onCancelled(CancelledException cancelledException) {
                Toast.makeText(x.app(), "cancelled", 1).show();
            }

            public void onFinished() {
            }
        });
    }

    public void b(final String str, String str2, String str3) {
        q.a("===" + str + "传入===" + str3);
        RequestParams requestParams = new RequestParams(str2);
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(str3);
        this.b = DbCookieStore.INSTANCE;
        if (this.b.getCookies() != null) {
            List cookies = this.b.getCookies();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < cookies.size(); i++) {
                HttpCookie httpCookie = (HttpCookie) cookies.get(i);
                if (!TextUtils.isEmpty(httpCookie.getName())) {
                    String str4;
                    String value = httpCookie.getValue();
                    if (TextUtils.isEmpty(httpCookie.getValue()) || httpCookie.getValue().equals("deleted")) {
                        str4 = "";
                    } else {
                        str4 = value;
                    }
                    if (httpCookie.getName().equals("ecjia_token") && ((ECJiaApplication) x.app()).c) {
                        str4 = "";
                    }
                    stringBuilder.append(httpCookie.getName()).append("=").append(str4).append(";");
                }
            }
            ((ECJiaApplication) x.app()).c = false;
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            requestParams.setHeader("Cookie", stringBuilder.toString());
            q.b("===HttpGet1===" + str + "-cookie传入===" + stringBuilder.toString());
        }
        requestParams.addHeader("Device-client", this.c.getClient());
        requestParams.addHeader("Device-code", this.c.getCode());
        requestParams.addHeader("Device-udid", this.c.getUdid());
        requestParams.addHeader("Api-vesion", "1.8.0");
        x.http().get(requestParams, new CommonCallback<String>(this) {
            final /* synthetic */ c b;

            public void onSuccess(String str) {
                this.b.b = DbCookieStore.INSTANCE;
                List cookies = this.b.b.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    q.b("===HttpGet2===" + str + "-cookie返回===" + ((HttpCookie) cookies.get(i)).toString());
                }
                q.b("===HttpGet3===" + str);
                this.b.a(str, str);
            }

            public void onError(Throwable th, boolean z) {
                if (th instanceof HttpException) {
                    HttpException httpException = (HttpException) th;
                    int code = httpException.getCode();
                    String message = httpException.getMessage();
                    q.b("===HttpError3===" + str + "-error返回===" + code + message + "--" + httpException.getResult());
                    k kVar = new k(x.app(), "网络状况不是很给力哦！");
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                q.b("===HttpError4===" + str + "-error返回===" + th.getMessage() + th);
            }

            public void onCancelled(CancelledException cancelledException) {
                Toast.makeText(x.app(), "cancelled", 1).show();
            }

            public void onFinished() {
            }
        });
    }

    public void a(final String str, String str2, ArrayList<String> arrayList) {
        int i = 0;
        String str3 = a.a() + str;
        q.a("===" + str3 + "传入===" + str2);
        RequestParams requestParams = new RequestParams(str3);
        requestParams.addBodyParameter("json", str2);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            requestParams.addBodyParameter("name[" + i2 + "]", new File((String) arrayList.get(i2)), "image/png");
        }
        this.b = DbCookieStore.INSTANCE;
        if (this.b.getCookies() != null) {
            List cookies = this.b.getCookies();
            StringBuilder stringBuilder = new StringBuilder();
            while (i < cookies.size()) {
                HttpCookie httpCookie = (HttpCookie) cookies.get(i);
                if (!TextUtils.isEmpty(httpCookie.getName())) {
                    String str4;
                    if (TextUtils.isEmpty(httpCookie.getValue()) || httpCookie.getValue().equals("deleted")) {
                        str4 = "";
                    } else {
                        str4 = httpCookie.getValue();
                    }
                    stringBuilder.append(httpCookie.getName()).append("=").append(str4).append(";");
                }
                i++;
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            requestParams.setHeader("Cookie", stringBuilder.toString());
            q.b("===" + str + "-cookie传入===" + stringBuilder.toString());
        }
        requestParams.addHeader("Device-client", this.c.getClient());
        requestParams.addHeader("Device-code", this.c.getCode());
        requestParams.addHeader("Device-udid", this.c.getUdid());
        requestParams.addHeader("Api-vesion", "1.8.0");
        x.http().post(requestParams, new CommonCallback<String>(this) {
            final /* synthetic */ c b;

            public void onSuccess(String str) {
                this.b.b = DbCookieStore.INSTANCE;
                List cookies = this.b.b.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    q.b("===" + str + "-cookie返回===" + ((HttpCookie) cookies.get(i)).toString());
                }
                this.b.a(str, str);
            }

            public void onError(Throwable th, boolean z) {
                if (th instanceof HttpException) {
                    HttpException httpException = (HttpException) th;
                    int code = httpException.getCode();
                    String message = httpException.getMessage();
                    q.b("===HttpError===" + code + message + "--" + httpException.getResult());
                    k kVar = new k(x.app(), "网络状况不是很给力哦！");
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                q.b("===HttpError===" + th.getMessage() + th);
            }

            public void onCancelled(CancelledException cancelledException) {
                Toast.makeText(x.app(), "cancelled", 1).show();
            }

            public void onFinished() {
            }
        });
    }

    public void a(final String str, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        int i = 0;
        String str3 = a.a() + str;
        q.a("===" + str3 + "传入===" + str2);
        RequestParams requestParams = new RequestParams(str3);
        requestParams.addBodyParameter("json", str2);
        if (arrayList2.size() == 1) {
            requestParams.addBodyParameter((String) arrayList.get(0), new File((String) arrayList2.get(0)), "image/png");
        } else {
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                requestParams.addBodyParameter((String) arrayList.get(i2), new File((String) arrayList2.get(i2)), "image/png");
            }
        }
        this.b = DbCookieStore.INSTANCE;
        if (this.b.getCookies() != null) {
            List cookies = this.b.getCookies();
            StringBuilder stringBuilder = new StringBuilder();
            while (i < cookies.size()) {
                HttpCookie httpCookie = (HttpCookie) cookies.get(i);
                if (!TextUtils.isEmpty(httpCookie.getName())) {
                    String str4;
                    if (TextUtils.isEmpty(httpCookie.getValue()) || httpCookie.getValue().equals("deleted")) {
                        str4 = "";
                    } else {
                        str4 = httpCookie.getValue();
                    }
                    stringBuilder.append(httpCookie.getName()).append("=").append(str4).append(";");
                }
                i++;
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            requestParams.setHeader("Cookie", stringBuilder.toString());
            q.b("===" + str + "-cookie传入===" + stringBuilder.toString());
        }
        requestParams.addHeader("Device-client", this.c.getClient());
        requestParams.addHeader("Device-code", this.c.getCode());
        requestParams.addHeader("Device-udid", this.c.getUdid());
        requestParams.addHeader("Api-vesion", "1.8.0");
        x.http().post(requestParams, new CommonCallback<String>(this) {
            final /* synthetic */ c b;

            public void onSuccess(String str) {
                this.b.b = DbCookieStore.INSTANCE;
                List cookies = this.b.b.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    q.b("===" + str + "-cookie返回===" + ((HttpCookie) cookies.get(i)).toString());
                }
                this.b.a(str, str);
            }

            public void onError(Throwable th, boolean z) {
                if (th instanceof HttpException) {
                    HttpException httpException = (HttpException) th;
                    int code = httpException.getCode();
                    String message = httpException.getMessage();
                    q.b("===HttpError===" + code + message + "--" + httpException.getResult());
                    k kVar = new k(x.app(), "网络状况不是很给力哦！");
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                q.b("===HttpError===" + th.getMessage() + th);
            }

            public void onCancelled(CancelledException cancelledException) {
                Toast.makeText(x.app(), "cancelled", 1).show();
            }

            public void onFinished() {
            }
        });
    }

    public void b(String str) {
    }

    public void c(String str, String str2) {
        RequestParams requestParams = new RequestParams(str);
        requestParams.setAutoRename(true);
        requestParams.setSaveFilePath(str2);
        x.http().get(requestParams, new c_5(this));
    }

    public void a(b bVar) {
        if (!this.a.contains(bVar)) {
            this.a.add(bVar);
        }
    }

    public void a(String str, String str2) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(str, str2);
        }
    }
}
