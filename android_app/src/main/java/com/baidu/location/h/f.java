package com.baidu.location.h;

import android.net.wifi.ScanResult;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.baidu.location.b.k;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class f implements com.baidu.location.b.f {
    private static final long kn = 10;
    private long ko = 0;
    private boolean kp = false;
    public List kq = null;
    private long kr = 0;
    private boolean ks;

    public f(f fVar) {
        if (fVar != null) {
            this.kq = fVar.kq;
            this.ko = fVar.ko;
            this.kr = fVar.kr;
            this.kp = fVar.kp;
        }
    }

    public f(List list, long j) {
        this.ko = j;
        this.kq = list;
        this.kr = System.currentTimeMillis();
        dh();
    }

    private void dh() {
        if (dm() >= 1) {
            Object obj = 1;
            for (int size = this.kq.size() - 1; size >= 1 && r2 != null; size--) {
                int i = 0;
                obj = null;
                while (i < size) {
                    Object obj2;
                    if (((ScanResult) this.kq.get(i)).level < ((ScanResult) this.kq.get(i + 1)).level) {
                        ScanResult scanResult = (ScanResult) this.kq.get(i + 1);
                        this.kq.set(i + 1, this.kq.get(i));
                        this.kq.set(i, scanResult);
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    i++;
                    obj = obj2;
                }
            }
        }
    }

    public static String if(int i, List list) {
        if (list.size() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(512);
        int size = list.size();
        if (size <= i) {
            i = size;
        }
        int i2 = 0;
        int i3 = 1;
        while (i2 < i) {
            if (((ScanResult) list.get(i2)).level == 0) {
                size = i3;
            } else {
                if (i3 != 0) {
                    i3 = 0;
                } else {
                    stringBuffer.append("|");
                }
                stringBuffer.append(((ScanResult) list.get(i2)).BSSID.replace(":", ""));
                size = ((ScanResult) list.get(i2)).level;
                if (size < 0) {
                    size = -size;
                }
                stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(size)}));
                size = i3;
            }
            i2++;
            i3 = size;
        }
        return i3 == 0 ? stringBuffer.toString() : null;
    }

    public String d(int i) {
        if (dm() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(512);
        int size = this.kq.size();
        if (size <= i) {
            i = size;
        }
        int i2 = 0;
        int i3 = 1;
        while (i2 < i) {
            if (((ScanResult) this.kq.get(i2)).level == 0) {
                size = i3;
            } else {
                if (i3 != 0) {
                    i3 = 0;
                } else {
                    stringBuffer.append("|");
                }
                stringBuffer.append(((ScanResult) this.kq.get(i2)).BSSID.replace(":", ""));
                size = ((ScanResult) this.kq.get(i2)).level;
                if (size < 0) {
                    size = -size;
                }
                stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(size)}));
                size = i3;
            }
            i2++;
            i3 = size;
        }
        return i3 == 0 ? stringBuffer.toString() : null;
    }

    public int dg() {
        for (int i = 0; i < dm(); i++) {
            int i2 = -((ScanResult) this.kq.get(i)).level;
            if (i2 > 0) {
                return i2;
            }
        }
        return 0;
    }

    public boolean di() {
        return this.kp;
    }

    public String dj() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("wifi=");
        if (this.kq == null) {
            return stringBuilder.toString();
        }
        for (int i = 0; i < this.kq.size(); i++) {
            int i2 = ((ScanResult) this.kq.get(i)).level;
            stringBuilder.append(((ScanResult) this.kq.get(i)).BSSID.replace(":", ""));
            stringBuilder.append(String.format(Locale.CHINA, ",%d;", new Object[]{Integer.valueOf(i2)}));
        }
        return stringBuilder.toString();
    }

    public boolean dk() {
        return System.currentTimeMillis() - this.ko < 3000;
    }

    public String dl() {
        try {
            return e(15);
        } catch (Exception e) {
            return null;
        }
    }

    public int dm() {
        return this.kq == null ? 0 : this.kq.size();
    }

    public boolean dn() {
        return System.currentTimeMillis() - this.kr < e.kg;
    }

    public String do(int i, boolean z) {
        if (dm() < 1) {
            return null;
        }
        int i2;
        int i3;
        int size;
        Object obj;
        int i4 = 0;
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer(512);
        List<Long> arrayList = new ArrayList();
        String c8 = l.a().c8();
        Object obj2 = null;
        if (VERSION.SDK_INT >= 17) {
            long elapsedRealtimeNanos;
            try {
                elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
            } catch (Error e) {
                elapsedRealtimeNanos = 0;
            }
            if (elapsedRealtimeNanos > 0) {
                obj2 = 1;
            }
        }
        if (obj2 == null || obj2 == null || z) {
            i2 = 0;
            i3 = 0;
            size = this.kq.size();
            obj = 1;
        } else {
            i2 = 0;
            i3 = 0;
            size = this.kq.size();
            obj = 1;
        }
        if (size <= i) {
            i = size;
        }
        int i5 = 0;
        while (i5 < i) {
            int i6;
            int i7;
            if (((ScanResult) this.kq.get(i5)).level == 0) {
                obj2 = obj;
                i6 = i2;
                i7 = i3;
                i3 = i4;
                i4 = i7;
            } else {
                if (obj != null) {
                    obj = null;
                    stringBuffer.append("&wf=");
                } else {
                    stringBuffer.append("|");
                }
                String replace = ((ScanResult) this.kq.get(i5)).BSSID.replace(":", "");
                stringBuffer.append(replace);
                size = ((ScanResult) this.kq.get(i5)).level;
                if (size < 0) {
                    size = -size;
                }
                stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(size)}));
                i2++;
                if (c8 != null && c8.equals(replace)) {
                    this.ks = l.a().D(((ScanResult) this.kq.get(i5)).capabilities);
                    l.a().db();
                    i3 = i2;
                }
                if (i4 == 0) {
                    try {
                        if (random.nextInt(10) == 2 && ((ScanResult) this.kq.get(i5)).SSID != null && ((ScanResult) this.kq.get(i5)).SSID.length() < 30) {
                            stringBuffer.append(((ScanResult) this.kq.get(i5)).SSID);
                            size = 1;
                        }
                        size = i4;
                    } catch (Exception e2) {
                        obj2 = obj;
                        i6 = i2;
                        i7 = i3;
                        i3 = i4;
                        i4 = i7;
                    }
                } else {
                    if (i4 == 1) {
                        if (random.nextInt(20) == 1 && ((ScanResult) this.kq.get(i5)).SSID != null && ((ScanResult) this.kq.get(i5)).SSID.length() < 30) {
                            stringBuffer.append(((ScanResult) this.kq.get(i5)).SSID);
                            size = 2;
                        }
                    }
                    size = i4;
                }
                i4 = i3;
                i3 = size;
                obj2 = obj;
                i6 = i2;
            }
            i5++;
            i2 = i6;
            obj = obj2;
            i7 = i4;
            i4 = i3;
            i3 = i7;
        }
        if (obj != null) {
            return null;
        }
        stringBuffer.append("&wf_n=" + i3);
        if (0 > kn && arrayList.size() > 0) {
            StringBuffer stringBuffer2 = new StringBuffer(128);
            stringBuffer2.append("&wf_ut=");
            Long l = (Long) arrayList.get(0);
            obj = 1;
            for (Long l2 : arrayList) {
                Object obj3;
                if (obj != null) {
                    stringBuffer2.append(l2.longValue());
                    obj3 = null;
                } else {
                    long longValue = l2.longValue() - l.longValue();
                    if (longValue != 0) {
                        stringBuffer2.append("" + longValue);
                    }
                    obj3 = obj;
                }
                stringBuffer2.append("|");
                obj = obj3;
            }
            stringBuffer.append(stringBuffer2.toString());
        }
        stringBuffer.append("&wf_st=");
        stringBuffer.append(this.ko);
        stringBuffer.append("&wf_et=");
        stringBuffer.append(this.kr);
        stringBuffer.append("&wf_vt=");
        stringBuffer.append(e.kb);
        if (i3 > 0) {
            this.kp = true;
            stringBuffer.append("&wf_en=");
            stringBuffer.append(this.ks ? 1 : 0);
        }
        return stringBuffer.toString();
    }

    public String dp() {
        StringBuffer stringBuffer = new StringBuffer(512);
        stringBuffer.append("wifi info:");
        if (dm() < 1) {
            return stringBuffer.toString();
        }
        int size = this.kq.size();
        if (size > 10) {
            size = 10;
        }
        int i = 0;
        int i2 = 1;
        while (i < size) {
            int i3;
            if (((ScanResult) this.kq.get(i)).level == 0) {
                i3 = i2;
            } else if (i2 != 0) {
                stringBuffer.append("wifi=");
                stringBuffer.append(((ScanResult) this.kq.get(i)).BSSID.replace(":", ""));
                i3 = ((ScanResult) this.kq.get(i)).level;
                stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(i3)}));
                i3 = 0;
            } else {
                stringBuffer.append(";");
                stringBuffer.append(((ScanResult) this.kq.get(i)).BSSID.replace(":", ""));
                i3 = ((ScanResult) this.kq.get(i)).level;
                stringBuffer.append(String.format(Locale.CHINA, ",%d;", new Object[]{Integer.valueOf(i3)}));
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return stringBuffer.toString();
    }

    public String dq() {
        try {
            return do(k.cD, true);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean dr() {
        return System.currentTimeMillis() - this.kr < 3000;
    }

    public String e(int i) {
        return do(i, false);
    }

    public String h(int i) {
        StringBuffer stringBuffer = new StringBuffer(512);
        if (dm() < 1) {
            return stringBuffer.toString();
        }
        int size = this.kq.size();
        if (size <= i) {
            i = size;
        }
        int i2 = 0;
        int i3 = 1;
        while (i2 < i) {
            if (((ScanResult) this.kq.get(i2)).level == 0) {
                size = i3;
            } else if (i3 != 0) {
                stringBuffer.append(((ScanResult) this.kq.get(i2)).BSSID.replace(":", ""));
                size = -((ScanResult) this.kq.get(i2)).level;
                stringBuffer.append(String.format(Locale.CHINA, ";%d|", new Object[]{Integer.valueOf(size)}));
                size = 0;
            } else {
                stringBuffer.append(((ScanResult) this.kq.get(i2)).BSSID.replace(":", ""));
                size = -((ScanResult) this.kq.get(i2)).level;
                stringBuffer.append(String.format(Locale.CHINA, ";%d|", new Object[]{Integer.valueOf(size)}));
                size = i3;
            }
            i2++;
            i3 = size;
        }
        return stringBuffer.toString();
    }

    public String i(int i) {
        int i2 = 0;
        try {
            if (dm() < 1) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this.kq.size();
            if (size <= i) {
                i = size;
            }
            while (i2 < i) {
                if (((ScanResult) this.kq.get(i2)).level != 0) {
                    stringBuffer.append(String.format(Locale.CHINA, "<access-point>\n<mac>%s</mac>\n<signal-strength>%d</signal-strength>\n</access-point>\n", new Object[]{((ScanResult) this.kq.get(i2)).BSSID.replace(":", ""), Integer.valueOf(((ScanResult) this.kq.get(i2)).level)}));
                }
                i2++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean int(f fVar) {
        if (this.kq == null || fVar == null || fVar.kq == null) {
            return false;
        }
        int size = this.kq.size() < fVar.kq.size() ? this.kq.size() : fVar.kq.size();
        for (int i = 0; i < size; i++) {
            String str = ((ScanResult) this.kq.get(i)).BSSID;
            int i2 = ((ScanResult) this.kq.get(i)).level;
            String str2 = ((ScanResult) fVar.kq.get(i)).BSSID;
            int i3 = ((ScanResult) fVar.kq.get(i)).level;
            if (!str.equals(str2) || i2 != i3) {
                return false;
            }
        }
        return true;
    }

    public String j(int i) {
        int i2 = 0;
        if (i == 0 || dm() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        int size = this.kq.size();
        int i3 = size > k.cD ? k.cD : size;
        int i4 = 1;
        int i5 = 0;
        while (i5 < i3) {
            if ((i4 & i) != 0) {
                if (i2 == 0) {
                    stringBuffer.append("&ssid=");
                } else {
                    stringBuffer.append("|");
                }
                stringBuffer.append(((ScanResult) this.kq.get(i5)).BSSID.replace(":", ""));
                stringBuffer.append(";");
                stringBuffer.append(((ScanResult) this.kq.get(i5)).SSID);
                size = i2 + 1;
            } else {
                size = i2;
            }
            i4 <<= 1;
            i5++;
            i2 = size;
        }
        return stringBuffer.toString();
    }

    public boolean new(f fVar) {
        if (this.kq == null || fVar == null || fVar.kq == null) {
            return false;
        }
        int size = this.kq.size() < fVar.kq.size() ? this.kq.size() : fVar.kq.size();
        for (int i = 0; i < size; i++) {
            if (!((ScanResult) this.kq.get(i)).BSSID.equals(((ScanResult) fVar.kq.get(i)).BSSID)) {
                return false;
            }
        }
        return true;
    }

    public boolean try(f fVar) {
        return e.if(fVar, this, k.bF);
    }
}
