package org.xutils;

import android.app.Application;
import android.content.Context;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import org.xutils.DbManager.DaoConfig;
import org.xutils.common.TaskController;
import org.xutils.common.task.TaskControllerImpl;
import org.xutils.db.DbManagerImpl;
import org.xutils.http.HttpManagerImpl;
import org.xutils.image.ImageManagerImpl;
import org.xutils.view.ViewInjectorImpl;

public final class x {

    public static class Ext {
        private static boolean a;
        private static Application b;
        private static TaskController c;
        private static HttpManager d;
        private static ImageManager e;
        private static ViewInjector f;

        private Ext() {
        }

        public static void init(Application application) {
            TaskControllerImpl.registerInstance();
            if (b == null) {
                b = application;
            }
        }

        public static void setDebug(boolean z) {
            a = z;
        }

        public static void setTaskController(TaskController taskController) {
            if (c == null) {
                c = taskController;
            }
        }

        public static void setHttpManager(HttpManager httpManager) {
            d = httpManager;
        }

        public static void setImageManager(ImageManager imageManager) {
            e = imageManager;
        }

        public static void setViewInjector(ViewInjector viewInjector) {
            f = viewInjector;
        }

        public static void setDefaultHostnameVerifier(HostnameVerifier hostnameVerifier) {
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        }
    }

    private static class a extends Application {
        public a(Context context) {
            attachBaseContext(context);
        }
    }

    private x() {
    }

    public static boolean isDebug() {
        return Ext.a;
    }

    public static Application app() {
        if (Ext.b == null) {
            try {
                Ext.b = new a((Context) Class.forName("com.android.layoutlib.bridge.impl.RenderAction").getDeclaredMethod("getCurrentContext", new Class[0]).invoke(null, new Object[0]));
            } catch (Throwable th) {
                RuntimeException runtimeException = new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate() and register your Application in manifest.");
            }
        }
        return Ext.b;
    }

    public static TaskController task() {
        return Ext.c;
    }

    public static HttpManager http() {
        if (Ext.d == null) {
            HttpManagerImpl.registerInstance();
        }
        return Ext.d;
    }

    public static ImageManager image() {
        if (Ext.e == null) {
            ImageManagerImpl.registerInstance();
        }
        return Ext.e;
    }

    public static ViewInjector view() {
        if (Ext.f == null) {
            ViewInjectorImpl.registerInstance();
        }
        return Ext.f;
    }

    public static DbManager getDb(DaoConfig daoConfig) {
        return DbManagerImpl.getInstance(daoConfig);
    }
}
