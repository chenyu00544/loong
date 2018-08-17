package com.baidu.location.e;

import android.support.v4.media.session.PlaybackStateCompat;
import com.baidu.location.Jni;
import com.baidu.location.b.c;
import com.baidu.location.b.e;
import com.baidu.location.b.k;
import com.baidu.location.b.m;
import com.baidu.location.b.n;
import com.tencent.stat.DeviceInfo;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class f implements com.baidu.location.b.f {
    private static final int gC = 128;
    private static f gE = null;
    private static int gH = -1;
    private static int gJ = 0;
    private static final String gx = (e.int + "/conlts.dat");
    private static int gz = -1;
    public boolean gA = true;
    public boolean gB = true;
    public boolean gD = true;
    public boolean gF = false;
    public boolean gG = false;
    public boolean gI = true;
    public boolean gv = true;
    public boolean gw = true;
    private a gy = null;

    class a extends m {
        final /* synthetic */ f eF;
        boolean eG;
        String eH;
        boolean eI;

        public a(f fVar) {
            this.eF = fVar;
            this.eH = null;
            this.eG = false;
            this.eI = false;
            this.c7 = new ArrayList();
        }

        public void au() {
            this.c5 = k.Z();
            this.dg = 2;
            String H = Jni.H(this.eH);
            this.eH = null;
            if (this.eG) {
                this.c7.add(new BasicNameValuePair("qt", "grid"));
            } else {
                this.c7.add(new BasicNameValuePair("qt", "conf"));
            }
            this.c7.add(new BasicNameValuePair("req", H));
        }

        public void if(String str, boolean z) {
            if (!this.eI) {
                this.eI = true;
                this.eH = str;
                this.eG = z;
                if (z) {
                    ap();
                } else {
                    ao();
                }
            }
        }

        public void int(boolean z) {
            if (!z || this.c6 == null) {
                this.eF.do(null);
            } else if (this.eG) {
                this.eF.if(this.c6);
            } else {
                this.eF.do(this.c6);
            }
            if (this.c7 != null) {
                this.c7.clear();
            }
            this.eI = false;
        }
    }

    private f() {
    }

    private void bA() {
        String str = e.int + "/config.dat";
        int i = k.bR ? 1 : 0;
        int i2 = k.bK ? 1 : 0;
        byte[] bytes = String.format(Locale.CHINA, "{\"ver\":\"%d\",\"gps\":\"%.1f|%.1f|%.1f|%.1f|%d|%d|%d|%d|%d|%d|%d\",\"up\":\"%.1f|%.1f|%.1f|%.1f\",\"wf\":\"%d|%.1f|%d|%.1f\",\"ab\":\"%.2f|%.2f|%d|%d\",\"gpc\":\"%d|%d|%d|%d|%d|%d\",\"zxd\":\"%.1f|%.1f|%d|%d|%d\",\"shak\":\"%d|%d|%.1f\",\"dmx\":%d}", new Object[]{Integer.valueOf(k.b0), Float.valueOf(k.cQ), Float.valueOf(k.bW), Float.valueOf(k.ck), Float.valueOf(k.cA), Integer.valueOf(k.b7), Integer.valueOf(k.cq), Integer.valueOf(k.cC), Integer.valueOf(k.bJ), Integer.valueOf(k.bH), Integer.valueOf(k.cI), Integer.valueOf(k.bQ), Float.valueOf(k.ch), Float.valueOf(k.cg), Float.valueOf(k.cO), Float.valueOf(k.cx), Integer.valueOf(k.cD), Float.valueOf(k.bL), Integer.valueOf(k.cy), Float.valueOf(k.bF), Float.valueOf(k.cb), Float.valueOf(k.ca), Integer.valueOf(k.b9), Integer.valueOf(k.b8), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(k.cB), Integer.valueOf(k.cr), Long.valueOf(k.cH), Integer.valueOf(k.cL), Float.valueOf(k.cd), Float.valueOf(k.cz), Integer.valueOf(k.cc), Integer.valueOf(k.cK), Integer.valueOf(k.bO), Integer.valueOf(k.bI), Integer.valueOf(k.bM), Float.valueOf(k.cn), Integer.valueOf(k.bT)}).getBytes();
        try {
            RandomAccessFile randomAccessFile;
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(e.int);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeBoolean(true);
            randomAccessFile.seek(2);
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes);
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    public static f bB() {
        if (gE == null) {
            gE = new f();
        }
        return gE;
    }

    private void bC() {
        try {
            File file = new File(gx);
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
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(128);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    private void bD() {
        try {
            File file = new File(e.int + "/config.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(2);
                    int readInt = randomAccessFile.readInt();
                    byte[] bArr = new byte[readInt];
                    randomAccessFile.read(bArr, 0, readInt);
                    n(new String(bArr));
                }
                randomAccessFile.seek(1);
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
                    k.bG = randomAccessFile.readDouble();
                    k.b4 = randomAccessFile.readDouble();
                    k.cM = randomAccessFile.readBoolean();
                    if (k.cM) {
                        k.b2 = new byte[625];
                        randomAccessFile.read(k.b2, 0, 625);
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
        do(null);
    }

    private void bF() {
        String str = "&ver=" + k.b0 + "&usr=" + c.N().L() + "&app=" + c.bn + "&prod=" + c.bj;
        if (this.gy == null) {
            this.gy = new a(this);
        }
        this.gy.if(str, false);
    }

    private void bG() {
        try {
            RandomAccessFile randomAccessFile;
            File file = new File(e.int + "/config.dat");
            if (!file.exists()) {
                File file2 = new File(e.int);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(1);
            randomAccessFile.writeBoolean(true);
            randomAccessFile.seek(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
            randomAccessFile.writeDouble(k.bG);
            randomAccessFile.writeDouble(k.b4);
            randomAccessFile.writeBoolean(k.cM);
            if (k.cM && k.b2 != null) {
                randomAccessFile.write(k.b2);
            }
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    private void bI() {
        int i = 0;
        try {
            File file = new File(gx);
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(4);
                int readInt = randomAccessFile.readInt();
                if (readInt > b.REQUEST_MERGE_PERIOD) {
                    randomAccessFile.close();
                    gJ = 0;
                    bC();
                    return;
                }
                int readInt2 = randomAccessFile.readInt();
                randomAccessFile.seek(128);
                byte[] bArr = new byte[readInt];
                while (i < readInt2) {
                    randomAccessFile.seek((long) ((readInt * i) + 128));
                    int readInt3 = randomAccessFile.readInt();
                    if (readInt3 > 0 && readInt3 < readInt) {
                        randomAccessFile.read(bArr, 0, readInt3);
                        if (bArr[readInt3 - 1] == (byte) 0) {
                            String str = new String(bArr, 0, readInt3 - 1);
                            c.N();
                            if (str.equals(c.bn)) {
                                gH = randomAccessFile.readInt();
                                gJ = i;
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (i == readInt2) {
                    gJ = readInt2;
                }
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    private void do(int i) {
        boolean z = true;
        this.gB = (i & 1) == 1;
        this.gD = (i & 2) == 2;
        this.gG = (i & 4) == 4;
        this.gv = (i & 8) == 8;
        this.gw = (i & 65536) == 65536;
        if ((i & 131072) != 131072) {
            z = false;
        }
        this.gA = z;
        if ((i & 16) == 16) {
            this.gI = false;
        }
    }

    private void do(HttpEntity httpEntity) {
        String str = null;
        gz = -1;
        if (httpEntity != null) {
            try {
                str = EntityUtils.toString(httpEntity, "utf-8");
                if (n(str)) {
                    bA();
                }
            } catch (Exception e) {
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("ctr")) {
                    gz = Integer.parseInt(jSONObject.getString("ctr"));
                }
            } catch (Exception e2) {
            }
        }
        try {
            int i;
            bI();
            if (gz != -1) {
                i = gz;
                if(gz);
            } else {
                i = gH != -1 ? gH : -1;
            }
            if (i != -1) {
                do(i);
            }
            if (com.baidu.location.f.isServing) {
                try {
                    if (this.gG && !k.cl) {
                    }
                } catch (Exception e3) {
                }
            }
        } catch (Exception e4) {
        }
    }

    private void if(int i) {
        File file = new File(gx);
        if (!file.exists()) {
            bC();
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            randomAccessFile.seek((long) ((readInt * gJ) + 128));
            byte[] bytes = (c.bn + '\u0000').getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.writeInt(i);
            if (readInt2 == gJ) {
                randomAccessFile.seek(8);
                randomAccessFile.writeInt(readInt2 + 1);
            }
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    private void if(HttpEntity httpEntity) {
        int i = 0;
        try {
            byte[] toByteArray = EntityUtils.toByteArray(httpEntity);
            if (toByteArray != null) {
                if (toByteArray.length < 640) {
                    k.cM = false;
                    k.b4 = k.cE + 0.025d;
                    k.bG = k.cp - 0.025d;
                    i = 1;
                } else {
                    k.cM = true;
                    k.bG = Double.longBitsToDouble(((((((((((long) toByteArray[7]) & 255) << 56) | ((((long) toByteArray[6]) & 255) << 48)) | ((((long) toByteArray[5]) & 255) << 40)) | ((((long) toByteArray[4]) & 255) << 32)) | ((((long) toByteArray[3]) & 255) << 24)) | ((((long) toByteArray[2]) & 255) << 16)) | ((((long) toByteArray[1]) & 255) << 8)) | (((long) toByteArray[0]) & 255));
                    k.b4 = Double.longBitsToDouble(((((((((((long) toByteArray[15]) & 255) << 56) | ((((long) toByteArray[14]) & 255) << 48)) | ((((long) toByteArray[13]) & 255) << 40)) | ((((long) toByteArray[12]) & 255) << 32)) | ((((long) toByteArray[11]) & 255) << 24)) | ((((long) toByteArray[10]) & 255) << 16)) | ((((long) toByteArray[9]) & 255) << 8)) | (((long) toByteArray[8]) & 255));
                    k.b2 = new byte[625];
                    while (i < 625) {
                        k.b2[i] = toByteArray[i + 16];
                        i++;
                    }
                    i = 1;
                }
            }
            if (i != 0) {
                bG();
            }
        } catch (Exception e) {
        }
    }

    private boolean n(String str) {
        boolean z = true;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int parseInt = Integer.parseInt(jSONObject.getString(DeviceInfo.TAG_VERSION));
                if (parseInt > k.b0) {
                    String[] split;
                    k.b0 = parseInt;
                    if (jSONObject.has("gps")) {
                        split = jSONObject.getString("gps").split("\\|");
                        if (split.length > 10) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                k.cQ = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                k.bW = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.ck = Float.parseFloat(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                k.cA = Float.parseFloat(split[3]);
                            }
                            if (!(split[4] == null || split[4].equals(""))) {
                                k.b7 = Integer.parseInt(split[4]);
                            }
                            if (!(split[5] == null || split[5].equals(""))) {
                                k.cq = Integer.parseInt(split[5]);
                            }
                            if (!(split[6] == null || split[6].equals(""))) {
                                k.cC = Integer.parseInt(split[6]);
                            }
                            if (!(split[7] == null || split[7].equals(""))) {
                                k.bJ = Integer.parseInt(split[7]);
                            }
                            if (!(split[8] == null || split[8].equals(""))) {
                                k.bH = Integer.parseInt(split[8]);
                            }
                            if (!(split[9] == null || split[9].equals(""))) {
                                k.cI = Integer.parseInt(split[9]);
                            }
                            if (!(split[10] == null || split[10].equals(""))) {
                                k.bQ = Integer.parseInt(split[10]);
                            }
                        }
                    }
                    if (jSONObject.has("up")) {
                        split = jSONObject.getString("up").split("\\|");
                        if (split.length > 3) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                k.ch = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                k.cg = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.cO = Float.parseFloat(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                k.cx = Float.parseFloat(split[3]);
                            }
                        }
                    }
                    if (jSONObject.has("wf")) {
                        split = jSONObject.getString("wf").split("\\|");
                        if (split.length > 3) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                k.cD = Integer.parseInt(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                k.bL = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.cy = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                k.bF = Float.parseFloat(split[3]);
                            }
                        }
                    }
                    if (jSONObject.has("ab")) {
                        split = jSONObject.getString("ab").split("\\|");
                        if (split.length > 3) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                k.cb = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                k.ca = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.b9 = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                k.b8 = Integer.parseInt(split[3]);
                            }
                        }
                    }
                    if (jSONObject.has("zxd")) {
                        split = jSONObject.getString("zxd").split("\\|");
                        if (split.length > 4) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                k.cd = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                k.cz = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.cc = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                k.cK = Integer.parseInt(split[3]);
                            }
                            if (!(split[4] == null || split[4].equals(""))) {
                                k.bO = Integer.parseInt(split[4]);
                            }
                        }
                    }
                    if (jSONObject.has("gpc")) {
                        split = jSONObject.getString("gpc").split("\\|");
                        if (split.length > 5) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                if (Integer.parseInt(split[0]) > 0) {
                                    k.bR = true;
                                } else {
                                    k.bR = false;
                                }
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                if (Integer.parseInt(split[1]) > 0) {
                                    k.bK = true;
                                } else {
                                    k.bK = false;
                                }
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.cB = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                k.cr = Integer.parseInt(split[3]);
                            }
                            if (!(split[4] == null || split[4].equals(""))) {
                                int parseInt2 = Integer.parseInt(split[4]);
                                if (parseInt2 > 0) {
                                    k.cH = (long) parseInt2;
                                    k.cs = (k.cH * 1000) * 60;
                                    k.cP = k.cs >> 2;
                                } else {
                                    k.cl = false;
                                }
                            }
                            if (!(split[5] == null || split[5].equals(""))) {
                                k.cL = Integer.parseInt(split[5]);
                            }
                        }
                    }
                    if (jSONObject.has("shak")) {
                        split = jSONObject.getString("shak").split("\\|");
                        if (split.length > 2) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                k.bI = Integer.parseInt(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                k.bM = Integer.parseInt(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                k.cn = Float.parseFloat(split[2]);
                            }
                        }
                    }
                    if (jSONObject.has("dmx")) {
                        k.bT = jSONObject.getInt("dmx");
                    }
                    return z;
                }
            } catch (Exception e) {
                return false;
            }
        }
        z = false;
        return z;
    }

    public void bE() {
        if (System.currentTimeMillis() - n.if().for() > 86400000) {
            n.if().a(System.currentTimeMillis());
            bF();
        }
        bD();
    }

    public void bH() {
    }

    public void m(String str) {
        if (this.gy == null) {
            this.gy = new a(this);
        }
        this.gy.if(str, true);
    }
}
