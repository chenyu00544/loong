package org.apache.commons.lang3;

import ru.truba.touchgallery.BuildConfig;

/* compiled from: SystemUtils */
public class d {
    public static final String A = c("java.vm.specification.name");
    public static final String B = c("java.vm.specification.vendor");
    public static final String C = c("java.vm.specification.version");
    public static final String D = c("java.vm.vendor");
    public static final String E = c("java.vm.version");
    public static final String F = c("line.separator");
    public static final String G = c("os.arch");
    public static final String H = c("os.name");
    public static final String I = c("os.version");
    public static final String J = c("path.separator");
    public static final String K;
    public static final String L = c("user.dir");
    public static final String M = c("user.home");
    public static final String N = c("user.language");
    public static final String O = c("user.name");
    public static final String P = c("user.timezone");
    public static final boolean Q = a(BuildConfig.VERSION_NAME);
    public static final boolean R = a("1.2");
    public static final boolean S = a("1.3");
    public static final boolean T = a("1.4");
    public static final boolean U = a("1.5");
    public static final boolean V = a("1.6");
    public static final boolean W = a("1.7");
    public static final boolean X = b("AIX");
    public static final boolean Y = b("HP-UX");
    public static final boolean Z = b("Irix");
    public static final String a = c("awt.toolkit");
    public static final boolean aa;
    public static final boolean ab = b("Mac");
    public static final boolean ac = b("Mac OS X");
    public static final boolean ad = b("FreeBSD");
    public static final boolean ae = b("OpenBSD");
    public static final boolean af = b("NetBSD");
    public static final boolean ag = b("OS/2");
    public static final boolean ah = b("Solaris");
    public static final boolean ai = b("SunOS");
    public static final boolean aj;
    public static final boolean ak = b("Windows");
    public static final boolean al = c("Windows", "5.0");
    public static final boolean am = c("Windows", "5.2");
    public static final boolean an = c("Windows Server 2008", "6.1");
    public static final boolean ao = c("Windows 9", "4.0");
    public static final boolean ap = c("Windows 9", "4.1");
    public static final boolean aq = c("Windows", "4.9");
    public static final boolean ar = b("Windows NT");
    public static final boolean as = c("Windows", "5.1");
    public static final boolean at = c("Windows", "6.0");
    public static final boolean au = c("Windows", "6.1");
    private static final JavaVersion av = JavaVersion.a(t);
    public static final String b = c("file.encoding");
    public static final String c = c("file.separator");
    public static final String d = c("java.awt.fonts");
    public static final String e = c("java.awt.graphicsenv");
    public static final String f = c("java.awt.headless");
    public static final String g = c("java.awt.printerjob");
    public static final String h = c("java.class.path");
    public static final String i = c("java.class.version");
    public static final String j = c("java.compiler");
    public static final String k = c("java.endorsed.dirs");
    public static final String l = c("java.ext.dirs");
    public static final String m = c("java.home");
    public static final String n = c("java.io.tmpdir");
    public static final String o = c("java.library.path");
    public static final String p = c("java.runtime.name");
    public static final String q = c("java.runtime.version");
    public static final String r = c("java.specification.name");
    public static final String s = c("java.specification.vendor");
    public static final String t = c("java.specification.version");
    public static final String u = c("java.util.prefs.PreferencesFactory");
    public static final String v = c("java.vendor");
    public static final String w = c("java.vendor.url");
    public static final String x = c("java.version");
    public static final String y = c("java.vm.info");
    public static final String z = c("java.vm.name");

    static {
        String c;
        boolean z;
        boolean z2 = false;
        if (c("user.country") == null) {
            c = c("user.region");
        } else {
            c = c("user.country");
        }
        K = c;
        if (b("Linux") || b("LINUX")) {
            z = true;
        } else {
            z = false;
        }
        aa = z;
        if (X || Y || Z || aa || ac || ah || ai || ad || ae || af) {
            z2 = true;
        }
        aj = z2;
    }

    private static boolean a(String str) {
        return a(t, str);
    }

    private static boolean c(String str, String str2) {
        return a(H, I, str, str2);
    }

    private static boolean b(String str) {
        return b(H, str);
    }

    private static String c(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException e) {
            System.err.println("Caught a SecurityException reading the system property '" + str + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    static boolean a(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    static boolean a(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || !str.startsWith(str3) || !str2.startsWith(str4)) {
            return false;
        }
        return true;
    }

    static boolean b(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }
}
