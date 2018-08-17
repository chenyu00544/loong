package com.baidu.location.e;

import android.location.Location;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.e;
import com.baidu.location.b.f;
import com.baidu.location.b.g;
import com.baidu.location.b.k;
import com.baidu.location.b.m;
import com.baidu.location.c.d;
import com.baidu.location.c.d.c;
import com.baidu.location.h.h;
import com.baidu.location.h.l;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.message.BasicNameValuePair;

public class o implements f {
    private static File iA = null;
    private static o iB = null;
    private static final int iC = 1040;
    private static final int iD = 32;
    private static int iE = 16;
    private static final String iG = (e.int + "/yom.dat");
    private static double iH = 100.0d;
    private static double iI = 0.0d;
    private static ArrayList iJ = new ArrayList();
    private static final int iK = 128;
    private static String iL = (e.int + "/yo.dat");
    private static int iM = 8;
    private static int iO = 1024;
    private static double ij = 0.1d;
    private static final String ik = (e.int + "/yol.dat");
    private static Location il = null;
    private static ArrayList im = new ArrayList();
    private static com.baidu.location.h.f in = null;
    private static ArrayList io = new ArrayList();
    private static int ip = 128;
    private static int iq = 0;
    private static Location ir = null;
    private static int is = 8;
    private static final String it = (e.int + "/yoh.dat");
    private static final int iu = 2048;
    private static Location iw = null;
    private static double ix = 30.0d;
    private static int iy = 64;
    private static final String iz = (e.int + "/yor.dat");
    private int iF;
    private b iN;
    long iv;

    private class a extends m {
        final /* synthetic */ o eN;
        private String eO;

        public a(o oVar, String str) {
            this.eN = oVar;
            this.eO = str;
            this.c7 = new ArrayList();
        }

        public void aS() {
            ao();
        }

        public void au() {
            this.c5 = k.Z();
            this.c7.add(new BasicNameValuePair("cldc[0]", this.eO));
        }

        public void int(boolean z) {
            if (!z || this.c6 != null) {
            }
        }
    }

    private class b extends m {
        final /* synthetic */ o eP;
        boolean eQ;
        private ArrayList eR;
        int eS;
        int eT;

        public b(o oVar) {
            this.eP = oVar;
            this.eQ = false;
            this.eT = 0;
            this.eS = 0;
            this.eR = null;
            this.c7 = new ArrayList();
        }

        public void aT() {
            if (!this.eQ) {
                if (de <= 4 || this.eS >= de) {
                    this.eS = 0;
                    this.eQ = true;
                    this.eT = 0;
                    if (this.eR == null || this.eR.size() < 1) {
                        if (this.eR == null) {
                            this.eR = new ArrayList();
                        }
                        this.eT = 0;
                        int i = 0;
                        do {
                            String cm = this.eT < 2 ? o.cm() : null;
                            if (cm != null || this.eT == 1) {
                                this.eT = 1;
                            } else {
                                this.eT = 2;
                                try {
                                    cm = k.b8();
                                } catch (Exception e) {
                                    cm = null;
                                }
                            }
                            if (cm == null) {
                                break;
                            }
                            this.eR.add(cm);
                            i += cm.length();
                        } while (i < g.Y);
                    }
                    if (this.eR == null || this.eR.size() < 1) {
                        this.eR = null;
                        this.eQ = false;
                        return;
                    }
                    ao();
                    return;
                }
                this.eS++;
            }
        }

        public void au() {
            this.c5 = k.Z();
            this.dg = 2;
            if (this.eR != null) {
                for (int i = 0; i < this.eR.size(); i++) {
                    if (this.eT == 1) {
                        this.c7.add(new BasicNameValuePair("cldc[" + i + "]", (String) this.eR.get(i)));
                    } else {
                        this.c7.add(new BasicNameValuePair("cltr[" + i + "]", (String) this.eR.get(i)));
                    }
                }
                this.c7.add(new BasicNameValuePair("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())})));
            }
        }

        public void int(boolean z) {
            if (!(!z || this.c6 == null || this.eR == null)) {
                this.eR.clear();
            }
            if (this.c7 != null) {
                this.c7.clear();
            }
            this.eQ = false;
        }
    }

    private o() {
        this.iN = null;
        this.iF = 0;
        this.iv = 0;
        this.iN = new b(this);
        this.iF = 0;
    }

    private static void A(String str) {
        w(str);
    }

    public static String byte(int i) {
        String str;
        List list;
        String str2 = null;
        if (i == 1) {
            str = it;
            list = im;
        } else if (i == 2) {
            str = iG;
            list = iJ;
        } else if (i == 3) {
            str = ik;
            list = io;
        } else {
            if (i == 4) {
                str = iz;
                list = io;
            }
            return str2;
        }
        if (list != null) {
            if (list.size() < 1) {
                for(str, list);
            }
            synchronized (o.class) {
                int size = list.size();
                if (size > 0) {
                    str2 = (String) list.get(size - 1);
                    list.remove(size - 1);
                }
            }
        }
        return str2;
    }

    private static boolean byte(Location location) {
        if (location == null) {
            return false;
        }
        if (ir == null || iw == null) {
            ir = location;
            return true;
        }
        double distanceTo = (double) location.distanceTo(ir);
        return ((double) location.distanceTo(iw)) > ((distanceTo * ((double) k.ca)) + ((((double) k.cb) * distanceTo) * distanceTo)) + ((double) k.b9);
    }

    public static void ci() {
        is = 0;
        if(1, false);
        if(2, false);
        if(3, false);
        is = 8;
    }

    public static String cj() {
        RandomAccessFile randomAccessFile;
        int readInt;
        File file = new File(iG);
        if (file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20);
                readInt = randomAccessFile.readInt();
                if (readInt > 128) {
                    String str = "&p1=" + readInt;
                    randomAccessFile.seek(20);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return str;
                }
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
        file = new File(ik);
        if (file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20);
                readInt = randomAccessFile.readInt();
                if (readInt > 256) {
                    str = "&p2=" + readInt;
                    randomAccessFile.seek(20);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return str;
                }
                randomAccessFile.close();
            } catch (Exception e2) {
            }
        }
        file = new File(iz);
        if (!file.exists()) {
            return null;
        }
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(20);
            readInt = randomAccessFile.readInt();
            if (readInt > 512) {
                str = "&p3=" + readInt;
                randomAccessFile.seek(20);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
                return str;
            }
            randomAccessFile.close();
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static String cm() {
        return co();
    }

    public static o cn() {
        if (iB == null) {
            iB = new o();
        }
        return iB;
    }

    public static String co() {
        String str = null;
        for (int i = 1; i < 5; i++) {
            str = byte(i);
            if (str != null) {
                return str;
            }
        }
        if(io, iy);
        if (io.size() > 0) {
            str = (String) io.get(0);
            io.remove(0);
        }
        if (str != null) {
            return str;
        }
        if(io, iq);
        if (io.size() > 0) {
            str = (String) io.get(0);
            io.remove(0);
        }
        if (str != null) {
            return str;
        }
        if(io, ip);
        if (io.size() <= 0) {
            return str;
        }
        str = (String) io.get(0);
        io.remove(0);
        return str;
    }

    public static void do(h hVar, com.baidu.location.h.f fVar, Location location, String str) {
        if (!f.bB().gB) {
            return;
        }
        if (k.co != 3 || if(location, fVar) || if(location, false)) {
            BDLocation bDLocation;
            String str2;
            if (com.baidu.location.c.b.aZ().byte(true).getLocType() == 66) {
                str = str + String.format(Locale.CHINA, "&ofrt=%f|%f|%d", new Object[]{Double.valueOf(r0.getLongitude()), Double.valueOf(r0.getLatitude()), Integer.valueOf((int) r0.getRadius())});
            }
            if (k.for(com.baidu.location.f.getServiceContext())) {
                bDLocation = d.try().if(hVar, fVar, null, c.IS_MIX_MODE, com.baidu.location.c.d.b.NO_NEED_TO_LOG);
            } else {
                bDLocation = d.try().if(hVar, fVar, null, c.IS_NOT_MIX_MODE, com.baidu.location.c.d.b.NO_NEED_TO_LOG);
            }
            if (bDLocation == null || bDLocation.getLocType() == 67) {
                str2 = str + String.format(Locale.CHINA, "&ofl=%s|0", new Object[]{"1"});
            } else {
                int i = 0;
                if (bDLocation.getNetworkLocationType().equals("cl")) {
                    i = 1;
                } else if (bDLocation.getNetworkLocationType().equals("wf")) {
                    i = 2;
                }
                str2 = str + String.format(Locale.CHINA, "&ofl=%s|%d|%f|%f|%d", new Object[]{"1", Integer.valueOf(i), Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius())});
            }
            if (hVar != null && hVar.dv()) {
                if (!if(location, fVar)) {
                    fVar = null;
                }
                str2 = k.if(hVar, fVar, location, str2, 1);
                if (str2 != null) {
                    y(Jni.H(str2));
                    ir = location;
                    iw = location;
                    if (fVar != null) {
                        in = fVar;
                    }
                }
            } else if (fVar != null && fVar.dk() && if(location, fVar)) {
                if (!byte(location) && !com.baidu.location.h.b.cV().cW()) {
                    str2 = "&cfr=1" + str2;
                } else if (!byte(location) && com.baidu.location.h.b.cV().cW()) {
                    str2 = "&cfr=3" + str2;
                } else if (com.baidu.location.h.b.cV().cW()) {
                    str2 = "&cfr=2" + str2;
                }
                str2 = k.if(hVar, fVar, location, str2, 2);
                if (str2 != null) {
                    x(Jni.H(str2));
                    il = location;
                    iw = location;
                    if (fVar != null) {
                        in = fVar;
                    }
                }
            } else {
                if (!byte(location) && !com.baidu.location.h.b.cV().cW()) {
                    str2 = "&cfr=1" + str2;
                } else if (!byte(location) && com.baidu.location.h.b.cV().cW()) {
                    str2 = "&cfr=3" + str2;
                } else if (com.baidu.location.h.b.cV().cW()) {
                    str2 = "&cfr=2" + str2;
                }
                if (!if(location, fVar)) {
                    fVar = null;
                }
                if (hVar != null || fVar != null) {
                    str2 = k.if(hVar, fVar, location, str2, 3);
                    if (str2 != null) {
                        A(Jni.H(str2));
                        iw = location;
                        if (fVar != null) {
                            in = fVar;
                        }
                    }
                }
            }
        }
    }

    public static boolean for(String str, List list) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(8);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            byte[] bArr = new byte[iO];
            int i = readInt2;
            readInt2 = is + 1;
            boolean z = false;
            while (readInt2 > 0 && i > 0) {
                if (i < readInt3) {
                    readInt3 = 0;
                }
                try {
                    randomAccessFile.seek((long) (((i - 1) * readInt) + 128));
                    int readInt4 = randomAccessFile.readInt();
                    if (readInt4 > 0 && readInt4 < readInt) {
                        randomAccessFile.read(bArr, 0, readInt4);
                        if (bArr[readInt4 - 1] == (byte) 0) {
                            list.add(0, new String(bArr, 0, readInt4 - 1));
                            z = true;
                        }
                    }
                    readInt2--;
                    i--;
                } catch (Exception e) {
                    return z;
                }
            }
            randomAccessFile.seek(12);
            randomAccessFile.writeInt(i);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.close();
            return z;
        } catch (Exception e2) {
            return false;
        }
    }

    private static int if(List list, int i) {
        if (list == null || i > 256 || i < 0) {
            return -1;
        }
        try {
            if (iA == null) {
                iA = new File(iL);
                if (!iA.exists()) {
                    iA = null;
                    return -2;
                }
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(iA, "rw");
            if (randomAccessFile.length() < 1) {
                randomAccessFile.close();
                return -3;
            }
            randomAccessFile.seek((long) i);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            int readInt4 = randomAccessFile.readInt();
            long readLong = randomAccessFile.readLong();
            if (!if(readInt, readInt2, readInt3, readInt4, readLong) || readInt2 < 1) {
                randomAccessFile.close();
                return -4;
            }
            byte[] bArr = new byte[iO];
            int i2 = readInt2;
            readInt2 = iM;
            while (readInt2 > 0 && i2 > 0) {
                randomAccessFile.seek(((long) ((((readInt + i2) - 1) % readInt3) * readInt4)) + readLong);
                int readInt5 = randomAccessFile.readInt();
                if (readInt5 > 0 && readInt5 < readInt4) {
                    randomAccessFile.read(bArr, 0, readInt5);
                    if (bArr[readInt5 - 1] == (byte) 0) {
                        list.add(new String(bArr, 0, readInt5 - 1));
                    }
                }
                readInt2--;
                i2--;
            }
            randomAccessFile.seek((long) i);
            randomAccessFile.writeInt(readInt);
            randomAccessFile.writeInt(i2);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.writeInt(readInt4);
            randomAccessFile.writeLong(readLong);
            randomAccessFile.close();
            return iM - readInt2;
        } catch (Exception e) {
            e.printStackTrace();
            return -5;
        }
    }

    public static String if(h hVar, com.baidu.location.h.f fVar, Location location, String str, String str2) {
        return !f.bB().gB ? null : k.if(hVar, fVar, location, str) + str2;
    }

    public static void if(int i, boolean z) {
        String str;
        Object obj;
        String str2;
        if (i == 1) {
            str2 = it;
            if (!z) {
                str = str2;
                List list = im;
            } else {
                return;
            }
        } else if (i == 2) {
            str2 = iG;
            if (z) {
                str = str2;
                obj = im;
            } else {
                str = str2;
                obj = iJ;
            }
        } else if (i == 3) {
            str2 = ik;
            if (z) {
                str = str2;
                obj = iJ;
            } else {
                str = str2;
                obj = io;
            }
        } else if (i == 4) {
            str2 = iz;
            if (z) {
                str = str2;
                obj = io;
            } else {
                return;
            }
        } else {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            v(str);
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            int readInt4 = randomAccessFile.readInt();
            int readInt5 = randomAccessFile.readInt();
            int size = list.size();
            int i2 = readInt5;
            while (size > is) {
                readInt5 = z ? i2 + 1 : i2;
                byte[] bytes;
                if (readInt3 >= readInt) {
                    if (!z) {
                        obj = 1;
                        i2 = readInt5;
                        break;
                    }
                    randomAccessFile.seek((long) ((readInt4 * readInt2) + 128));
                    bytes = (((String) list.get(0)) + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    list.remove(0);
                    i2 = readInt4 + 1;
                    if (i2 > readInt3) {
                        i2 = 0;
                    }
                    readInt4 = readInt3;
                } else {
                    randomAccessFile.seek((long) ((readInt2 * readInt3) + 128));
                    bytes = (((String) list.get(0)) + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    list.remove(0);
                    int i3 = readInt4;
                    readInt4 = readInt3 + 1;
                    i2 = i3;
                }
                size--;
                readInt3 = readInt4;
                readInt4 = i2;
                i2 = readInt5;
            }
            obj = null;
            randomAccessFile.seek(12);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.writeInt(readInt4);
            randomAccessFile.writeInt(i2);
            randomAccessFile.close();
            if (obj != null && i < 4) {
                if(i + 1, true);
            }
        } catch (Exception e) {
        }
    }

    private static boolean if(int i, int i2, int i3, int i4, long j) {
        return i >= 0 && i < i3 && i2 >= 0 && i2 <= i3 && i3 >= 0 && i3 <= 1024 && i4 >= 128 && i4 <= 1024;
    }

    private static boolean if(Location location, com.baidu.location.h.f fVar) {
        if (location == null || fVar == null || fVar.kq == null || fVar.kq.isEmpty() || fVar.int(in)) {
            return false;
        }
        if (il != null) {
            return true;
        }
        il = location;
        return true;
    }

    public static boolean if(Location location, boolean z) {
        return com.baidu.location.h.a.if(iw, location, z);
    }

    public static void new(double d, double d2, double d3, double d4) {
        if (d <= 0.0d) {
            d = iI;
        }
        iI = d;
        ij = d2;
        if (d3 <= 20.0d) {
            d3 = ix;
        }
        ix = d3;
        iH = d4;
    }

    public static void v(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(e.int);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    file = null;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0);
                randomAccessFile.writeInt(32);
                randomAccessFile.writeInt(2048);
                randomAccessFile.writeInt(iC);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static synchronized void w(String str) {
        synchronized (o.class) {
            List list;
            int i = k.b3;
            if (i == 1) {
                list = im;
            } else if (i == 2) {
                list = iJ;
            } else {
                if (i == 3) {
                    list = io;
                }
            }
            if (list != null) {
                if (list.size() <= iE) {
                    list.add(str);
                }
                if (list.size() >= iE) {
                    if(i, false);
                }
                while (list.size() > iE) {
                    list.remove(0);
                }
            }
        }
    }

    private static void x(String str) {
        w(str);
    }

    private static void y(String str) {
        w(str);
    }

    public void ck() {
        if (e.bw().bu() && this.iv != 0 && System.currentTimeMillis() - this.iv > 1200000) {
            cl();
        }
    }

    public void cl() {
    }

    public void cp() {
        if (l.a().da()) {
            this.iN.aT();
        }
    }

    public void z(String str) {
        new a(this, str).aS();
    }
}
