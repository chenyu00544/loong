package org.xutils.view;

import android.text.TextUtils;
import android.view.View;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import org.xutils.common.util.DoubleKeyValueMap;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.Event;

final class EventListenerManager {
    private static final HashSet<String> a = new HashSet(2);
    private static final DoubleKeyValueMap<b, Class<?>, Object> b = new DoubleKeyValueMap();

    public static class DynamicHandler implements InvocationHandler {
        private static long c = 0;
        private WeakReference<Object> a;
        private final HashMap<String, Method> b = new HashMap(1);

        public DynamicHandler(Object obj) {
            this.a = new WeakReference(obj);
        }

        public void addMethod(String str, Method method) {
            this.b.put(str, method);
        }

        public Object getHandler() {
            return this.a.get();
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Object obj2 = this.a.get();
            if (obj2 != null) {
                String name = method.getName();
                if ("toString".equals(name)) {
                    return DynamicHandler.class.getSimpleName();
                }
                Method method2 = (Method) this.b.get(name);
                if (method2 == null && this.b.size() == 1) {
                    Iterator it = this.b.entrySet().iterator();
                    if (it.hasNext()) {
                        Entry entry = (Entry) it.next();
                        if (TextUtils.isEmpty((CharSequence) entry.getKey())) {
                            method2 = (Method) entry.getValue();
                        }
                    }
                }
                if (method2 != null) {
                    if (EventListenerManager.a.contains(name)) {
                        long currentTimeMillis = System.currentTimeMillis() - c;
                        if (currentTimeMillis < 300) {
                            LogUtil.d("onClick cancelled: " + currentTimeMillis);
                            return null;
                        }
                        c = System.currentTimeMillis();
                    }
                    try {
                        return method2.invoke(obj2, objArr);
                    } catch (Throwable th) {
                        RuntimeException runtimeException = new RuntimeException("invoke method error:" + obj2.getClass().getName() + "#" + method2.getName(), th);
                    }
                } else {
                    LogUtil.w("method not impl: " + name + "(" + obj2.getClass().getSimpleName() + ")");
                }
            }
            return null;
        }
    }

    static {
        a.add("onClick");
        a.add("onItemClick");
    }

    public static void a(a aVar, b bVar, Event event, Object obj, Method method) {
        boolean z = false;
        try {
            View a = aVar.a(bVar);
            if (a != null) {
                String str;
                Object obj2;
                Class type = event.type();
                CharSequence charSequence = event.setter();
                if (TextUtils.isEmpty(charSequence)) {
                    str = "set" + type.getSimpleName();
                } else {
                    CharSequence charSequence2 = charSequence;
                }
                String method2 = event.method();
                Object obj3 = b.get(bVar, type);
                if (obj3 != null) {
                    DynamicHandler dynamicHandler = (DynamicHandler) Proxy.getInvocationHandler(obj3);
                    boolean equals = obj.equals(dynamicHandler.getHandler());
                    if (equals) {
                        dynamicHandler.addMethod(method2, method);
                    }
                    z = equals;
                }
                if (z) {
                    obj2 = obj3;
                } else {
                    InvocationHandler dynamicHandler2 = new DynamicHandler(obj);
                    dynamicHandler2.addMethod(method2, method);
                    obj2 = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, dynamicHandler2);
                    b.put(bVar, type, obj2);
                }
                a.getClass().getMethod(str, new Class[]{type}).invoke(a, new Object[]{obj2});
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }
}
