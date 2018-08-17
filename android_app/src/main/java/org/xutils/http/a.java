package org.xutils.http;

import android.os.Parcelable.Creator;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;

/* compiled from: RequestParamsHelper */
final class a {
    private static final ClassLoader a = String.class.getClassLoader();

    /* compiled from: RequestParamsHelper */
    interface a {
        void onParseKV(String str, Object obj);
    }

    static void a(Object obj, Class<?> cls, a aVar) {
        if (obj != null && cls != null && cls != RequestParams.class && cls != Object.class) {
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null && classLoader != a) {
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields != null && declaredFields.length > 0) {
                    for (Field field : declaredFields) {
                        if (!(Modifier.isTransient(field.getModifiers()) || field.getType() == Creator.class)) {
                            field.setAccessible(true);
                            try {
                                String name = field.getName();
                                Object obj2 = field.get(obj);
                                if (obj2 != null) {
                                    aVar.onParseKV(name, obj2);
                                }
                            } catch (Throwable e) {
                                LogUtil.e(e.getMessage(), e);
                            }
                        }
                    }
                }
                a(obj, cls.getSuperclass(), aVar);
            }
        }
    }

    static Object a(Object obj) throws JSONException {
        if (obj == null) {
            return null;
        }
        Class cls = obj.getClass();
        JSONArray jSONArray;
        if (cls.isArray()) {
            jSONArray = new JSONArray();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                jSONArray.put(a(Array.get(obj, i)));
            }
            return jSONArray;
        } else if (obj instanceof Iterable) {
            jSONArray = new JSONArray();
            for (Object a : (Iterable) obj) {
                jSONArray.put(a(a));
            }
            return jSONArray;
        } else if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            for (Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (!(key == null || value == null)) {
                    jSONObject.put(String.valueOf(key), a(value));
                }
            }
            return jSONObject;
        } else {
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader == null || classLoader == a) {
                return obj;
            }
            final JSONObject jSONObject2 = new JSONObject();
            a(obj, cls, new a() {
                public void onParseKV(String str, Object obj) {
                    try {
                        jSONObject2.put(str, a.a(obj));
                    } catch (Throwable e) {
                        throw new IllegalArgumentException("parse RequestParams to json failed", e);
                    }
                }
            });
            return jSONObject2;
        }
    }
}
