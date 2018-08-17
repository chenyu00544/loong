package de.greenrobot.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: SubscriberMethodFinder */
class j {
    private static final Map<String, List<i>> a = new HashMap();
    private static final Map<Class<?>, Class<?>> b = new ConcurrentHashMap();

    j() {
    }

    List<i> a(Class<?> cls, String str) {
        String str2 = cls.getName() + '.' + str;
        synchronized (a) {
            List<i> list = (List) a.get(str2);
        }
        if (list != null) {
            return list;
        }
        List<i> arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name = cls2.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getDeclaredMethods()) {
                String name2 = method.getName();
                if (name2.startsWith(str)) {
                    Class[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        ThreadMode threadMode;
                        name = name2.substring(str.length());
                        if (name.length() == 0) {
                            threadMode = ThreadMode.PostThread;
                        } else if (name.equals("MainThread")) {
                            threadMode = ThreadMode.MainThread;
                        } else if (name.equals("BackgroundThread")) {
                            threadMode = ThreadMode.BackgroundThread;
                        } else if (name.equals("Async")) {
                            threadMode = ThreadMode.Async;
                        } else if (!b.containsKey(cls2)) {
                            throw new EventBusException("Illegal onEvent method, check for typos: " + method);
                        }
                        Class cls3 = parameterTypes[0];
                        stringBuilder.setLength(0);
                        stringBuilder.append(name2);
                        stringBuilder.append('>').append(cls3.getName());
                        if (hashSet.add(stringBuilder.toString())) {
                            arrayList.add(new i(method, threadMode, cls3));
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new EventBusException("Subscriber " + cls + " has no methods called " + str);
        }
        synchronized (a) {
            a.put(str2, arrayList);
        }
        return arrayList;
    }
}
