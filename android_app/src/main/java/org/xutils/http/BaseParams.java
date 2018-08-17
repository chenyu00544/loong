package org.xutils.http;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.http.body.BodyItemWrapper;
import org.xutils.http.body.FileBody;
import org.xutils.http.body.InputStreamBody;
import org.xutils.http.body.MultipartBody;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.StringBody;
import org.xutils.http.body.UrlEncodedParamsBody;

abstract class BaseParams {
    private String a = "UTF-8";
    private HttpMethod b;
    private String c;
    private boolean d = false;
    private boolean e = false;
    private RequestBody f;
    private final List<Header> g = new ArrayList();
    private final List<KeyValue> h = new ArrayList();
    private final List<KeyValue> i = new ArrayList();
    private final List<KeyValue> j = new ArrayList();

    public static final class ArrayItem extends KeyValue {
        public ArrayItem(String str, Object obj) {
            super(str, obj);
        }
    }

    public static final class Header extends KeyValue {
        public final boolean setHeader;

        public Header(String str, String str2, boolean z) {
            super(str, str2);
            this.setHeader = z;
        }
    }

    BaseParams() {
    }

    public void setCharset(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a = str;
        }
    }

    public String getCharset() {
        return this.a;
    }

    public void setMethod(HttpMethod httpMethod) {
        this.b = httpMethod;
    }

    public HttpMethod getMethod() {
        return this.b;
    }

    public boolean isMultipart() {
        return this.d;
    }

    public void setMultipart(boolean z) {
        this.d = z;
    }

    public boolean isAsJsonContent() {
        return this.e;
    }

    public void setAsJsonContent(boolean z) {
        this.e = z;
    }

    public void setHeader(String str, String str2) {
        Header header = new Header(str, str2, true);
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            if (str.equals(((KeyValue) it.next()).key)) {
                it.remove();
            }
        }
        this.g.add(header);
    }

    public void addHeader(String str, String str2) {
        this.g.add(new Header(str, str2, false));
    }

    public void addParameter(String str, Object obj) {
        int i = 0;
        if (obj != null) {
            int length;
            if (this.b == null || HttpMethod.permitsRequestBody(this.b)) {
                if (TextUtils.isEmpty(str)) {
                    this.c = obj.toString();
                } else if ((obj instanceof File) || (obj instanceof InputStream) || (obj instanceof byte[])) {
                    this.j.add(new KeyValue(str, obj));
                } else if (obj instanceof Iterable) {
                    for (Object arrayItem : (Iterable) obj) {
                        this.i.add(new ArrayItem(str, arrayItem));
                    }
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    length = jSONArray.length();
                    while (i < length) {
                        this.i.add(new ArrayItem(str, jSONArray.opt(i)));
                        i++;
                    }
                } else if (obj.getClass().isArray()) {
                    length = Array.getLength(obj);
                    while (i < length) {
                        this.i.add(new ArrayItem(str, Array.get(obj, i)));
                        i++;
                    }
                } else {
                    this.i.add(new KeyValue(str, obj));
                }
            } else if (!TextUtils.isEmpty(str)) {
                if (obj instanceof Iterable) {
                    for (Object arrayItem2 : (Iterable) obj) {
                        this.h.add(new ArrayItem(str, arrayItem2));
                    }
                } else if (obj.getClass().isArray()) {
                    length = Array.getLength(obj);
                    while (i < length) {
                        this.h.add(new ArrayItem(str, Array.get(obj, i)));
                        i++;
                    }
                } else {
                    this.h.add(new KeyValue(str, obj));
                }
            }
        }
    }

    public void addQueryStringParameter(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.h.add(new KeyValue(str, str2));
        }
    }

    public void addBodyParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            this.c = str2;
        } else {
            this.i.add(new KeyValue(str, str2));
        }
    }

    public void addBodyParameter(String str, File file) {
        addBodyParameter(str, file, null, null);
    }

    public void addBodyParameter(String str, Object obj, String str2) {
        addBodyParameter(str, obj, str2, null);
    }

    public void addBodyParameter(String str, Object obj, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            this.j.add(new KeyValue(str, obj));
        } else {
            this.j.add(new KeyValue(str, new BodyItemWrapper(obj, str2, str3)));
        }
    }

    public void setBodyContent(String str) {
        this.c = str;
    }

    public String getBodyContent() {
        a();
        return this.c;
    }

    public List<Header> getHeaders() {
        return new ArrayList(this.g);
    }

    public List<KeyValue> getQueryStringParams() {
        a();
        return new ArrayList(this.h);
    }

    public List<KeyValue> getBodyParams() {
        a();
        return new ArrayList(this.i);
    }

    public List<KeyValue> getFileParams() {
        a();
        return new ArrayList(this.j);
    }

    public List<KeyValue> getStringParams() {
        List<KeyValue> arrayList = new ArrayList(this.h.size() + this.i.size());
        arrayList.addAll(this.h);
        arrayList.addAll(this.i);
        return arrayList;
    }

    public String getStringParameter(String str) {
        for (KeyValue keyValue : this.h) {
            if (str == null && keyValue.key == null) {
                return keyValue.getValueStr();
            }
            if (str != null && str.equals(keyValue.key)) {
                return keyValue.getValueStr();
            }
        }
        for (KeyValue keyValue2 : this.i) {
            if (str == null && keyValue2.key == null) {
                return keyValue2.getValueStr();
            }
            if (str != null && str.equals(keyValue2.key)) {
                return keyValue2.getValueStr();
            }
        }
        return null;
    }

    public List<KeyValue> getParams(String str) {
        List<KeyValue> arrayList = new ArrayList();
        for (KeyValue keyValue : this.h) {
            if (str == null && keyValue.key == null) {
                arrayList.add(keyValue);
            } else if (str != null && str.equals(keyValue.key)) {
                arrayList.add(keyValue);
            }
        }
        for (KeyValue keyValue2 : this.i) {
            if (str == null && keyValue2.key == null) {
                arrayList.add(keyValue2);
            } else if (str != null && str.equals(keyValue2.key)) {
                arrayList.add(keyValue2);
            }
        }
        for (KeyValue keyValue22 : this.j) {
            if (str == null && keyValue22.key == null) {
                arrayList.add(keyValue22);
            } else if (str != null && str.equals(keyValue22.key)) {
                arrayList.add(keyValue22);
            }
        }
        return arrayList;
    }

    public void clearParams() {
        this.h.clear();
        this.i.clear();
        this.j.clear();
        this.c = null;
        this.f = null;
    }

    public void removeParameter(String str) {
        if (TextUtils.isEmpty(str)) {
            this.c = null;
            return;
        }
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            if (str.equals(((KeyValue) it.next()).key)) {
                it.remove();
            }
        }
        it = this.i.iterator();
        while (it.hasNext()) {
            if (str.equals(((KeyValue) it.next()).key)) {
                it.remove();
            }
        }
        it = this.j.iterator();
        while (it.hasNext()) {
            if (str.equals(((KeyValue) it.next()).key)) {
                it.remove();
            }
        }
    }

    public void setRequestBody(RequestBody requestBody) {
        this.f = requestBody;
    }

    public RequestBody getRequestBody() throws IOException {
        a();
        if (this.f != null) {
            return this.f;
        }
        if (!TextUtils.isEmpty(this.c)) {
            return new StringBody(this.c, this.a);
        }
        if (this.d || this.j.size() > 0) {
            if (this.d || this.j.size() != 1) {
                this.d = true;
                return new MultipartBody(this.j, this.a);
            }
            Iterator it = this.j.iterator();
            if (!it.hasNext()) {
                return null;
            }
            String contentType;
            Object obj = ((KeyValue) it.next()).value;
            if (obj instanceof BodyItemWrapper) {
                BodyItemWrapper bodyItemWrapper = (BodyItemWrapper) obj;
                Object value = bodyItemWrapper.getValue();
                contentType = bodyItemWrapper.getContentType();
                obj = value;
            } else {
                contentType = null;
            }
            if (obj instanceof File) {
                return new FileBody((File) obj, contentType);
            }
            if (obj instanceof InputStream) {
                return new InputStreamBody((InputStream) obj, contentType);
            }
            if (obj instanceof byte[]) {
                return new InputStreamBody(new ByteArrayInputStream((byte[]) obj), contentType);
            }
            if (obj instanceof String) {
                RequestBody stringBody = new StringBody((String) obj, this.a);
                stringBody.setContentType(contentType);
                return stringBody;
            }
            LogUtil.w("Some params will be ignored for: " + toString());
            return null;
        } else if (this.i.size() > 0) {
            return new UrlEncodedParamsBody(this.i, this.a);
        } else {
            return null;
        }
    }

    public String toJSONString() {
        List arrayList = new ArrayList(this.h.size() + this.i.size());
        arrayList.addAll(this.h);
        arrayList.addAll(this.i);
        try {
            JSONObject jSONObject;
            if (TextUtils.isEmpty(this.c)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(this.c);
            }
            a(jSONObject, arrayList);
            return jSONObject.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        a();
        StringBuilder stringBuilder = new StringBuilder();
        if (!this.h.isEmpty()) {
            for (KeyValue keyValue : this.h) {
                stringBuilder.append(keyValue.key).append("=").append(keyValue.value).append("&");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (HttpMethod.permitsRequestBody(this.b)) {
            stringBuilder.append("<");
            if (!TextUtils.isEmpty(this.c)) {
                stringBuilder.append(this.c);
            } else if (!this.i.isEmpty()) {
                for (KeyValue keyValue2 : this.i) {
                    stringBuilder.append(keyValue2.key).append("=").append(keyValue2.value).append("&");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            stringBuilder.append(">");
        }
        return stringBuilder.toString();
    }

    private synchronized void a() {
        if (!this.i.isEmpty()) {
            if (!(HttpMethod.permitsRequestBody(this.b) && TextUtils.isEmpty(this.c) && this.f == null)) {
                this.h.addAll(this.i);
                this.i.clear();
            }
            if (!this.i.isEmpty() && (this.d || this.j.size() > 0)) {
                this.j.addAll(this.i);
                this.i.clear();
            }
            if (this.e && !this.i.isEmpty()) {
                try {
                    JSONObject jSONObject;
                    if (TextUtils.isEmpty(this.c)) {
                        jSONObject = new JSONObject();
                    } else {
                        jSONObject = new JSONObject(this.c);
                    }
                    a(jSONObject, this.i);
                    this.c = jSONObject.toString();
                    this.i.clear();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void a(JSONObject jSONObject, List<KeyValue> list) throws JSONException {
        HashSet hashSet = new HashSet(list.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        for (int i = 0; i < list.size(); i++) {
            KeyValue keyValue = (KeyValue) list.get(i);
            CharSequence charSequence = keyValue.key;
            if (!TextUtils.isEmpty(charSequence)) {
                JSONArray jSONArray;
                if (linkedHashMap.containsKey(charSequence)) {
                    jSONArray = (JSONArray) linkedHashMap.get(charSequence);
                } else {
                    jSONArray = new JSONArray();
                    linkedHashMap.put(charSequence, jSONArray);
                }
                jSONArray.put(a.a(keyValue.value));
                if (keyValue instanceof ArrayItem) {
                    hashSet.add(charSequence);
                }
            }
        }
        for (Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            JSONArray jSONArray2 = (JSONArray) entry.getValue();
            if (jSONArray2.length() > 1 || hashSet.contains(str)) {
                jSONObject.put(str, jSONArray2);
            } else {
                jSONObject.put(str, jSONArray2.get(0));
            }
        }
    }
}
