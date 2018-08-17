package com.baidu.location.h;

import android.os.Build.VERSION;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class b extends j implements f {
    private static Method j0 = null;
    private static Class j1 = null;
    public static final int j2 = 3;
    private static Method j5 = null;
    private static b j6 = null;
    private static Method j7 = null;
    private static Method jR = null;
    private static Method jS = null;
    public static int jV = 0;
    public static int jX = 0;
    public static final long jZ = 3000;
    private List j3 = null;
    private a j4 = null;
    private TelephonyManager jQ = null;
    private h jT = null;
    private boolean jU = false;
    private h jW = new h();
    private boolean jY = false;

    private class a extends PhoneStateListener {
        final /* synthetic */ b a;

        public a(b bVar) {
            this.a = bVar;
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            if (cellLocation != null) {
                try {
                    this.a.cU();
                } catch (Exception e) {
                }
            }
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (this.a.jW == null) {
                return;
            }
            if (this.a.jW.kz == 'g') {
                this.a.jW.ky = signalStrength.getGsmSignalStrength();
            } else if (this.a.jW.kz == 'c') {
                this.a.jW.ky = signalStrength.getCdmaDbm();
            }
        }
    }

    private b() {
    }

    private String byte(h hVar) {
        StringBuilder stringBuilder = new StringBuilder();
        if (Integer.valueOf(VERSION.SDK_INT).intValue() >= 17) {
            try {
                List<CellInfo> allCellInfo = this.jQ.getAllCellInfo();
                if (allCellInfo != null && allCellInfo.size() > 0) {
                    stringBuilder.append("&nc=");
                    for (CellInfo cellInfo : allCellInfo) {
                        if (!cellInfo.isRegistered()) {
                            h hVar2 = if(cellInfo);
                            if (!(hVar2 == null || hVar2.kt == -1 || hVar2.kx == -1)) {
                                if (hVar.kt != hVar2.kt) {
                                    stringBuilder.append(hVar2.kt + "|" + hVar2.kx + "|" + hVar2.ky + ";");
                                } else {
                                    stringBuilder.append("|" + hVar2.kx + "|" + hVar2.ky + ";");
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
            } catch (NoSuchMethodError e2) {
            }
        }
        return stringBuilder.toString();
    }

    private void cT() {
        String ah = k.ah();
        if (ah != null) {
            File file = new File(ah + File.separator + "lcvif.dat");
            if (file.exists()) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    if (System.currentTimeMillis() - randomAccessFile.readLong() > 60000) {
                        randomAccessFile.close();
                        file.delete();
                        return;
                    }
                    randomAccessFile.readInt();
                    for (int i = 0; i < 3; i++) {
                        long readLong = randomAccessFile.readLong();
                        int readInt = randomAccessFile.readInt();
                        int readInt2 = randomAccessFile.readInt();
                        int readInt3 = randomAccessFile.readInt();
                        int readInt4 = randomAccessFile.readInt();
                        int readInt5 = randomAccessFile.readInt();
                        char c = '\u0000';
                        if (readInt5 == 1) {
                            c = 'g';
                        }
                        if (readInt5 == 2) {
                            c = 'c';
                        }
                        if (readLong != 0) {
                            h hVar = new h(readInt3, readInt4, readInt, readInt2, 0, c);
                            hVar.kv = readLong;
                            if (hVar.dt()) {
                                this.jY = true;
                                this.j3.add(hVar);
                            }
                        }
                    }
                    randomAccessFile.close();
                } catch (Exception e) {
                    file.delete();
                }
            }
        }
    }

    private void cU() {
        h cY = cY();
        if (cY != null) {
            try(cY);
        }
        if (cY == null || !cY.dt()) {
            do(this.jQ.getCellLocation());
        }
    }

    public static b cV() {
        if (j6 == null) {
            j6 = new b();
        }
        return j6;
    }

    private void cX() {
        int i = 0;
        if (this.j3 != null || this.jT != null) {
            if (this.j3 == null && this.jT != null) {
                this.j3 = new LinkedList();
                this.j3.add(this.jT);
            }
            String ah = k.ah();
            if (ah != null) {
                File file = new File(ah + File.separator + "lcvif.dat");
                int size = this.j3.size();
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeLong(((h) this.j3.get(size - 1)).kv);
                    randomAccessFile.writeInt(size);
                    for (int i2 = 0; i2 < 3 - size; i2++) {
                        randomAccessFile.writeLong(0);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(2);
                    }
                    while (i < size) {
                        randomAccessFile.writeLong(((h) this.j3.get(i)).kv);
                        randomAccessFile.writeInt(((h) this.j3.get(i)).kA);
                        randomAccessFile.writeInt(((h) this.j3.get(i)).kB);
                        randomAccessFile.writeInt(((h) this.j3.get(i)).kt);
                        randomAccessFile.writeInt(((h) this.j3.get(i)).kx);
                        if (((h) this.j3.get(i)).kz == 'g') {
                            randomAccessFile.writeInt(1);
                        } else if (((h) this.j3.get(i)).kz == 'c') {
                            randomAccessFile.writeInt(2);
                        } else {
                            randomAccessFile.writeInt(3);
                        }
                        i++;
                    }
                    randomAccessFile.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.location.h.h cY() {
        /*
        r5 = this;
        r1 = 0;
        r0 = android.os.Build.VERSION.SDK_INT;
        r0 = java.lang.Integer.valueOf(r0);
        r0 = r0.intValue();
        r2 = 17;
        if (r0 >= r2) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r0 = r5.jQ;	 Catch:{ Exception -> 0x004f, NoSuchMethodError -> 0x004d }
        r0 = r0.getAllCellInfo();	 Catch:{ Exception -> 0x004f, NoSuchMethodError -> 0x004d }
        if (r0 == 0) goto L_0x000f;
    L_0x0018:
        r2 = r0.size();	 Catch:{ Exception -> 0x004f, NoSuchMethodError -> 0x004d }
        if (r2 <= 0) goto L_0x000f;
    L_0x001e:
        r3 = r0.iterator();	 Catch:{ Exception -> 0x004f, NoSuchMethodError -> 0x004d }
        r2 = r1;
    L_0x0023:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x0051, NoSuchMethodError -> 0x004d }
        if (r0 == 0) goto L_0x0054;
    L_0x0029:
        r0 = r3.next();	 Catch:{ Exception -> 0x0051, NoSuchMethodError -> 0x004d }
        r0 = (android.telephony.CellInfo) r0;	 Catch:{ Exception -> 0x0051, NoSuchMethodError -> 0x004d }
        r4 = r0.isRegistered();	 Catch:{ Exception -> 0x0051, NoSuchMethodError -> 0x004d }
        if (r4 == 0) goto L_0x0023;
    L_0x0035:
        r0 = r5.if(r0);	 Catch:{ Exception -> 0x0051, NoSuchMethodError -> 0x004d }
        if (r0 != 0) goto L_0x003d;
    L_0x003b:
        r2 = r0;
        goto L_0x0023;
    L_0x003d:
        r2 = r0.dt();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x004d }
        if (r2 != 0) goto L_0x0046;
    L_0x0043:
        r0 = r1;
    L_0x0044:
        r1 = r0;
        goto L_0x000f;
    L_0x0046:
        r0.ds();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x004d }
        goto L_0x0044;
    L_0x004a:
        r1 = move-exception;
        r1 = r0;
        goto L_0x000f;
    L_0x004d:
        r0 = move-exception;
        goto L_0x000f;
    L_0x004f:
        r0 = move-exception;
        goto L_0x000f;
    L_0x0051:
        r0 = move-exception;
        r1 = r2;
        goto L_0x000f;
    L_0x0054:
        r1 = r2;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.h.b.cY():com.baidu.location.h.h");
    }

    private h do(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation == null || this.jQ == null) {
            return null;
        }
        h hVar = new h();
        hVar.kv = System.currentTimeMillis();
        try {
            String networkOperator = this.jQ.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                if (networkOperator.length() >= 3) {
                    int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    if (intValue < 0) {
                        intValue = this.jW.kA;
                    }
                    hVar.kA = intValue;
                }
                String substring = networkOperator.substring(3);
                if (substring != null) {
                    char[] toCharArray = substring.toCharArray();
                    while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                        i++;
                    }
                }
                i = Integer.valueOf(substring.substring(0, i)).intValue();
                if (i < 0) {
                    i = this.jW.kB;
                }
                hVar.kB = i;
            }
            jX = this.jQ.getSimState();
        } catch (Exception e) {
            jV = 1;
        }
        if (cellLocation instanceof GsmCellLocation) {
            hVar.kt = ((GsmCellLocation) cellLocation).getLac();
            hVar.kx = ((GsmCellLocation) cellLocation).getCid();
            hVar.kz = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            hVar.kz = 'c';
            if (Integer.valueOf(VERSION.SDK_INT).intValue() < 5) {
                return hVar;
            }
            if (j1 == null) {
                try {
                    j1 = Class.forName("android.telephony.cdma.CdmaCellLocation");
                    j5 = j1.getMethod("getBaseStationId", new Class[0]);
                    jS = j1.getMethod("getNetworkId", new Class[0]);
                    j0 = j1.getMethod("getSystemId", new Class[0]);
                    jR = j1.getMethod("getBaseStationLatitude", new Class[0]);
                    j7 = j1.getMethod("getBaseStationLongitude", new Class[0]);
                } catch (Exception e2) {
                    j1 = null;
                    jV = 2;
                    return hVar;
                }
            }
            if (j1 != null && j1.isInstance(cellLocation)) {
                try {
                    int intValue2 = ((Integer) j0.invoke(cellLocation, new Object[0])).intValue();
                    if (intValue2 < 0) {
                        intValue2 = this.jW.kB;
                    }
                    hVar.kB = intValue2;
                    hVar.kx = ((Integer) j5.invoke(cellLocation, new Object[0])).intValue();
                    hVar.kt = ((Integer) jS.invoke(cellLocation, new Object[0])).intValue();
                    Object invoke = jR.invoke(cellLocation, new Object[0]);
                    if (((Integer) invoke).intValue() < ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) {
                        hVar.kC = ((Integer) invoke).intValue();
                    }
                    invoke = j7.invoke(cellLocation, new Object[0]);
                    if (((Integer) invoke).intValue() < ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) {
                        hVar.kw = ((Integer) invoke).intValue();
                    }
                } catch (Exception e3) {
                    jV = 3;
                    return hVar;
                }
            }
        }
        try(hVar);
        return hVar;
    }

    private h if(CellInfo cellInfo) {
        int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
        if (intValue < 17) {
            return null;
        }
        h hVar = new h();
        Object obj = null;
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            hVar.kA = void(cellIdentity.getMcc());
            hVar.kB = void(cellIdentity.getMnc());
            hVar.kt = void(cellIdentity.getLac());
            hVar.kx = void(cellIdentity.getCid());
            hVar.kz = 'g';
            hVar.ky = ((CellInfoGsm) cellInfo).getCellSignalStrength().getAsuLevel();
            obj = 1;
        } else if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            hVar.kC = cellIdentity2.getLatitude();
            hVar.kw = cellIdentity2.getLongitude();
            hVar.kB = void(cellIdentity2.getSystemId());
            hVar.kt = void(cellIdentity2.getNetworkId());
            hVar.kx = void(cellIdentity2.getBasestationId());
            hVar.kz = 'c';
            hVar.ky = ((CellInfoCdma) cellInfo).getCellSignalStrength().getCdmaDbm();
            r2 = 1;
        } else if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            hVar.kA = void(cellIdentity3.getMcc());
            hVar.kB = void(cellIdentity3.getMnc());
            hVar.kt = void(cellIdentity3.getTac());
            hVar.kx = void(cellIdentity3.getCi());
            hVar.kz = 'g';
            hVar.ky = ((CellInfoLte) cellInfo).getCellSignalStrength().getAsuLevel();
            r2 = 1;
        }
        if (intValue >= 18 && r2 == null) {
            try {
                if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    hVar.kA = void(cellIdentity4.getMcc());
                    hVar.kB = void(cellIdentity4.getMnc());
                    hVar.kt = void(cellIdentity4.getLac());
                    hVar.kx = void(cellIdentity4.getCid());
                    hVar.kz = 'g';
                    hVar.ky = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getAsuLevel();
                }
            } catch (Exception e) {
            }
        }
        try {
            hVar.kv = System.currentTimeMillis() - ((SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000);
        } catch (Error e2) {
            hVar.kv = System.currentTimeMillis();
        }
        return hVar;
    }

    private void try(h hVar) {
        if (!hVar.dt()) {
            return;
        }
        if (this.jW == null || !this.jW.case(hVar)) {
            this.jW = hVar;
            if (hVar.dt()) {
                int size = this.j3.size();
                h hVar2 = size == 0 ? null : (h) this.j3.get(size - 1);
                if (hVar2 == null || hVar2.kx != this.jW.kx || hVar2.kt != this.jW.kt) {
                    this.j3.add(this.jW);
                    if (this.j3.size() > 3) {
                        this.j3.remove(0);
                    }
                    cX();
                    this.jY = false;
                }
            } else if (this.j3 != null) {
                this.j3.clear();
            }
        }
    }

    private int void(int i) {
        return i == ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED ? -1 : i;
    }

    public h cN() {
        if (!((this.jW != null && this.jW.dv() && this.jW.dt()) || this.jQ == null)) {
            try {
                cU();
            } catch (Exception e) {
            }
        }
        if (this.jW.dz()) {
            this.jT = null;
            this.jT = new h(this.jW.kt, this.jW.kx, this.jW.kA, this.jW.kB, this.jW.ky, this.jW.kz);
        }
        if (this.jW.dC() && this.jT != null && this.jW.kz == 'g') {
            this.jW.kB = this.jT.kB;
            this.jW.kA = this.jT.kA;
        }
        return this.jW;
    }

    public synchronized void cO() {
        if (!this.jU) {
            if (com.baidu.location.f.isServing) {
                this.jQ = (TelephonyManager) com.baidu.location.f.getServiceContext().getSystemService("phone");
                this.j3 = new LinkedList();
                this.j4 = new a(this);
                cT();
                if (!(this.jQ == null || this.j4 == null)) {
                    try {
                        this.jQ.listen(this.j4, 272);
                    } catch (Exception e) {
                    }
                    this.jU = true;
                }
            }
        }
    }

    public String cP() {
        return null;
    }

    public int cQ() {
        int i = 0;
        if (this.jQ != null) {
            try {
                i = this.jQ.getNetworkType();
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int cR() {
        String subscriberId;
        try {
            subscriberId = ((TelephonyManager) com.baidu.location.f.getServiceContext().getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            subscriberId = null;
        }
        if (subscriberId != null) {
            if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002") || subscriberId.startsWith("46007")) {
                return 1;
            }
            if (subscriberId.startsWith("46001")) {
                return 2;
            }
            if (subscriberId.startsWith("46003")) {
                return 3;
            }
        }
        return 0;
    }

    public synchronized void cS() {
        if (this.jU) {
            if (!(this.j4 == null || this.jQ == null)) {
                this.jQ.listen(this.j4, 0);
            }
            this.j4 = null;
            this.jQ = null;
            this.j3.clear();
            this.j3 = null;
            cX();
            this.jU = false;
        }
    }

    public boolean cW() {
        return this.jY;
    }

    public String int(h hVar) {
        String str = "";
        try {
            str = byte(hVar);
            if (str != null && !str.equals("") && !str.equals("&nc=")) {
                return str;
            }
            List<NeighboringCellInfo> neighboringCellInfo = this.jQ.getNeighboringCellInfo();
            if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                String str2 = "&nc=";
                int i = 0;
                for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                    int lac = neighboringCellInfo2.getLac();
                    str = (lac == -1 || neighboringCellInfo2.getCid() == -1) ? str2 : hVar.kt != lac ? str2 + lac + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() + ";" : str2 + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() + ";";
                    int i2 = i + 1;
                    if (i2 >= 8) {
                        break;
                    }
                    i = i2;
                    str2 = str;
                }
                str = str2;
            }
            return (str == null || !str.equals("&nc=")) ? str : null;
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
    }

    public String new(h hVar) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(hVar.kz);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", new Object[]{Integer.valueOf(hVar.kA), Integer.valueOf(hVar.kB), Integer.valueOf(hVar.kt), Integer.valueOf(hVar.kx), Integer.valueOf(hVar.ky)}));
        if (hVar.kC < ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED && hVar.kw < ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) {
            stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", new Object[]{Double.valueOf(((double) hVar.kw) / 14400.0d), Double.valueOf(((double) hVar.kC) / 14400.0d)}));
        }
        stringBuffer.append("&cl_t=");
        stringBuffer.append(hVar.kv);
        if (this.j3 != null && this.j3.size() > 0) {
            int size = this.j3.size();
            stringBuffer.append("&clt=");
            for (int i = 0; i < size; i++) {
                h hVar2 = (h) this.j3.get(i);
                if (hVar2.kA != hVar.kA) {
                    stringBuffer.append(hVar2.kA);
                }
                stringBuffer.append("|");
                if (hVar2.kB != hVar.kB) {
                    stringBuffer.append(hVar2.kB);
                }
                stringBuffer.append("|");
                if (hVar2.kt != hVar.kt) {
                    stringBuffer.append(hVar2.kt);
                }
                stringBuffer.append("|");
                if (hVar2.kx != hVar.kx) {
                    stringBuffer.append(hVar2.kx);
                }
                stringBuffer.append("|");
                stringBuffer.append((System.currentTimeMillis() - hVar2.kv) / 1000);
                stringBuffer.append(";");
            }
        }
        if (jX > 100) {
            jX = 0;
        }
        stringBuffer.append("&cs=" + ((jV << 8) + jX));
        return stringBuffer.toString();
    }
}
