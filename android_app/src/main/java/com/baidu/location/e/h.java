package com.baidu.location.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.location.Location;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.baidu.location.Jni;
import com.baidu.location.b.c;
import com.baidu.location.b.f;
import com.baidu.location.b.m;
import com.baidu.location.h.e;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.taobao.accs.ErrorCode;
import com.tencent.open.yyb.TitleBar;
import com.umeng.message.util.HttpRequest;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements f {
    private static final String g2 = "1";
    private static final String g6 = "%d_%02d_%02d";
    private static final long gM = 86400000;
    private static final String gW = "utf-8";
    private static final int gY = 400;
    private static final String hh = "http://loc.map.baidu.com/cc.php";
    private static final String hi = "0";
    private static final int hr = 10000;
    private static h hs = null;
    public static String hu = "0";
    private int g0;
    private long g1;
    private int g3;
    private int g4;
    private int g5;
    StringBuilder g7;
    private float g8;
    private double g9;
    private Handler gK;
    private int gL;
    private String gN;
    private double gO;
    private float gP;
    private int gQ;
    private boolean gR;
    private double gS;
    private int gT;
    private int gU;
    private boolean gV;
    private byte[] gX;
    private a gZ;
    Location ha;
    long hb;
    private int hc;
    private double hd;
    private List he;
    long hf;
    double hg;
    private boolean hj;
    private int hk;
    private int hl;
    private int hm;
    private long hn;
    double ho;
    int hp;
    private byte[] hq;
    Location ht;
    private int hv;

    class h_2 extends Thread {
        final /* synthetic */ h a;

        h_2(h hVar) {
            this.a = hVar;
        }

        public void run() {
            this.a.if(new File(Environment.getExternalStorageDirectory() + "/baidu/tempdata", "intime.dat"), "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
        }
    }

    class h_3 implements Runnable {
        final /* synthetic */ h a;

        h_3(h hVar) {
            this.a = hVar;
        }

        public void run() {
            Location location = new Location("GPS");
            h hVar = this.a;
            hVar.hg -= 1.123456789E-4d;
            hVar = this.a;
            hVar.ho -= 1.02340567E-5d;
            location.setTime(System.currentTimeMillis());
            location.setLongitude(this.a.hg);
            location.setLatitude(this.a.ho);
            location.setSpeed(TitleBar.BACKBTN_LEFT_MARGIN);
            location.setBearing(35.0f);
            hVar = this.a;
            hVar.hp++;
            this.a.for(location);
            if (this.a.hp < 20) {
                this.a.gK.postDelayed(this, 1000);
            }
        }
    }

    class a extends m {
        final /* synthetic */ h eJ;
        String eK;

        public a(h hVar) {
            this.eJ = hVar;
            this.eK = null;
            this.c7 = new ArrayList();
        }

        public void au() {
            this.c5 = h.hh;
            String H = Jni.H(this.eK);
            this.eK = null;
            this.c7.add(new BasicNameValuePair(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, H));
        }

        public void int(boolean z) {
            if (z && this.c6 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(EntityUtils.toString(this.c6, h.gW));
                    jSONObject.put("prod", c.bn);
                    jSONObject.put("uptime", System.currentTimeMillis());
                    this.eJ.s(jSONObject.toString());
                } catch (Exception e) {
                }
            }
            if (this.c7 != null) {
                this.c7.clear();
            }
        }

        public void void(String str) {
            this.eK = str;
            ao();
        }
    }

    private h() {
        this.g0 = 1;
        this.gS = 0.699999988079071d;
        this.gN = "3G|4G";
        this.g4 = 1;
        this.g5 = 307200;
        this.gL = 15;
        this.hm = 1;
        this.gO = 3.5d;
        this.g9 = 3.0d;
        this.hd = 0.5d;
        this.hv = ErrorCode.APP_NOT_BIND;
        this.gQ = 60;
        this.gU = 0;
        this.hl = 60;
        this.hc = 0;
        this.hn = 0;
        this.gZ = null;
        this.gR = false;
        this.gV = false;
        this.g3 = 0;
        this.g8 = 0.0f;
        this.gP = 0.0f;
        this.g1 = 0;
        this.gT = d_ResultType.SHORT_URL;
        this.hf = 0;
        this.ha = null;
        this.ht = null;
        this.g7 = null;
        this.hb = 0;
        this.gK = null;
        this.hq = new byte[4];
        this.gX = null;
        this.hk = 0;
        this.he = null;
        this.hj = false;
        this.hp = 0;
        this.hg = 116.22345545d;
        this.ho = 40.245667323d;
        this.gK = new Handler();
    }

    public static h bJ() {
        if (hs == null) {
            hs = new h();
        }
        return hs;
    }

    private void bM() {
        this.he = null;
        this.hb = 0;
        this.hk = 0;
        this.ha = null;
        this.ht = null;
        this.g8 = 0.0f;
        this.gP = 0.0f;
    }

    private void bN() {
    }

    private void bO() {
        int i = 0;
        this.he.add(Byte.valueOf((byte) 0));
        this.he.add(Byte.valueOf((byte) 0));
        if (hu.equals("0")) {
            this.he.add(Byte.valueOf((byte) 110));
        } else {
            this.he.add(Byte.valueOf((byte) 126));
        }
        this.he.add(Byte.valueOf((byte) 0));
        this.he.add(Byte.valueOf(this.hq[0]));
        this.he.add(Byte.valueOf(this.hq[1]));
        this.he.add(Byte.valueOf(this.hq[2]));
        this.he.add(Byte.valueOf(this.hq[3]));
        int length = this.gX.length;
        this.he.add(Byte.valueOf((byte) ((length + 1) & 255)));
        while (i < length) {
            this.he.add(Byte.valueOf(this.gX[i]));
            i++;
        }
    }

    private void bP() {
        String str = null;
        if (null == null) {
            str = f.bg;
        }
        String[] split = str.split("\\.");
        int length = split.length;
        this.hq[0] = (byte) 0;
        this.hq[1] = (byte) 0;
        this.hq[2] = (byte) 0;
        this.hq[3] = (byte) 0;
        if (length >= 4) {
            length = 4;
        }
        int i = 0;
        while (i < length) {
            try {
                this.hq[i] = (byte) (Integer.valueOf(split[i]).intValue() & 255);
                i++;
            } catch (Exception e) {
            }
        }
        this.gX = q(c.bn + ":" + c.N().bm);
    }

    private void bQ() {
        if (!this.hj) {
            this.hj = true;
            r(c.bn);
            bT();
            bP();
        }
    }

    private boolean bR() {
        if (this.gR) {
            if (this.gV) {
                if (((double) this.g8) < this.hd) {
                    this.g3 += this.gL;
                    if (this.g3 <= this.hv || System.currentTimeMillis() - this.g1 > ((long) (this.gQ * 1000))) {
                        return true;
                    }
                }
                this.g3 = 0;
                this.gV = false;
                return true;
            } else if (((double) this.g8) >= this.hd) {
                return true;
            } else {
                this.gV = true;
                this.g3 = 0;
                this.g3 += this.gL;
                return true;
            }
        } else if (((double) this.g8) >= this.gO || ((double) this.gP) >= this.g9) {
            this.gR = true;
            return true;
        } else if (this.gU == 1 && System.currentTimeMillis() - this.g1 > ((long) (this.hl * 1000))) {
            return true;
        }
        return false;
    }

    private void bS() {
        if (this.hb != 0 && System.currentTimeMillis() - this.hb >= ((long) (this.gL * 1000))) {
            if (com.baidu.location.f.getServiceContext().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false)) {
                bM();
            } else if (this.g4 == 1 && !bR()) {
                bM();
            } else if (!if(c.bn, com.baidu.location.f.getServiceContext())) {
                bM();
            } else if (this.he != null) {
                int size = this.he.size();
                this.he.set(0, Byte.valueOf((byte) (size & 255)));
                this.he.set(1, Byte.valueOf((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & size) >> 8)));
                this.he.set(3, Byte.valueOf((byte) (this.hk & 255)));
                byte[] bArr = new byte[size];
                for (int i = 0; i < size; i++) {
                    bArr[i] = ((Byte) this.he.get(i)).byteValue();
                }
                if (Environment.getExternalStorageState().equals("mounted")) {
                    File file = new File(Environment.getExternalStorageDirectory(), "baidu/tempdata");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (file.exists()) {
                        File file2 = new File(file, "intime.dat");
                        if (file2.exists()) {
                            file2.delete();
                        }
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                            bufferedOutputStream.write(bArr);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            new h_2(this).start();
                        } catch (Exception e) {
                        }
                    }
                }
                bM();
                this.g1 = System.currentTimeMillis();
            }
        }
    }

    private void bT() {
        if (System.currentTimeMillis() - this.hn > 86400000) {
            if (this.gZ == null) {
                this.gZ = new a(this);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(c.N().do(false));
            stringBuffer.append(c.bq().bt());
            this.gZ.void(stringBuffer.toString());
        }
        bN();
    }

    private void do(Location location) {
        new(location);
        bS();
    }

    private byte[] for(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((ViewCompat.MEASURED_STATE_MASK & i) >> 24)};
    }

    private String if(File file, String str) {
        String uuid = UUID.randomUUID().toString();
        String str2 = "--";
        String str3 = "\r\n";
        String str4 = "multipart/form-data";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(hr);
            httpURLConnection.setConnectTimeout(hr);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", gW);
            httpURLConnection.setRequestProperty("connection", "keep-alive");
            httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, str4 + ";boundary=" + uuid);
            if (file != null && file.exists()) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str2);
                stringBuffer.append(uuid);
                stringBuffer.append(str3);
                stringBuffer.append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + file.getName() + "\"" + str3);
                stringBuffer.append("Content-Type: application/octet-stream; charset=utf-8" + str3);
                stringBuffer.append(str3);
                dataOutputStream.write(stringBuffer.toString().getBytes());
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                dataOutputStream.write(str3.getBytes());
                dataOutputStream.write((str2 + uuid + str2 + str3).getBytes());
                dataOutputStream.flush();
                int responseCode = httpURLConnection.getResponseCode();
                outputStream.close();
                this.hc += gY;
                new(this.hc);
                if (responseCode == 200) {
                    return "1";
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e2) {
        }
        return "0";
    }

    private void if(Location location) {
        Object obj = null;
        this.hb = System.currentTimeMillis();
        int((int) (this.hb / 1000));
        int((int) (location.getLongitude() * 1000000.0d));
        int((int) (location.getLatitude() * 1000000.0d));
        if (location.hasBearing()) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        if (!location.hasSpeed()) {
            int i2 = 1;
        }
        if (obj2 > null) {
            this.he.add(Byte.valueOf((byte) 32));
        } else {
            this.he.add(Byte.valueOf((byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & -33)));
        }
        if (obj > null) {
            this.he.add(Byte.valueOf(Byte.MIN_VALUE));
        } else {
            this.he.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & TransportMediator.KEYCODE_MEDIA_PAUSE)));
        }
        this.ha = location;
    }

    private boolean if(String str, Context context) {
        boolean z = false;
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    boolean z2;
                    if (runningAppProcessInfo.processName.equals(str)) {
                        int i = runningAppProcessInfo.importance;
                        if (i == 200 || i == 100) {
                            z2 = true;
                            z = z2;
                        }
                    }
                    z2 = z;
                    z = z2;
                }
            }
        } catch (Exception e) {
        }
        return z;
    }

    private void int(int i) {
        byte[] bArr = for(i);
        for (int i2 = 0; i2 < 4; i2++) {
            this.he.add(Byte.valueOf(bArr[i2]));
        }
    }

    private void int(Location location) {
        int longitude = (int) ((location.getLongitude() - this.ha.getLongitude()) * 100000.0d);
        int latitude = (int) ((location.getLatitude() - this.ha.getLatitude()) * 100000.0d);
        if (location.hasBearing()) {
            Object obj = null;
        } else {
            int i = 1;
        }
        if (location.hasSpeed()) {
            Object obj2 = null;
        } else {
            int i2 = 1;
        }
        if (longitude > 0) {
            Object obj3 = null;
        } else {
            int i3 = 1;
        }
        int abs = Math.abs(longitude);
        if (latitude > 0) {
            Object obj4 = null;
        } else {
            int i4 = 1;
        }
        int abs2 = Math.abs(latitude);
        if (this.hk > 1) {
            this.ht = null;
            this.ht = this.ha;
        }
        this.ha = location;
        if (this.ha != null && this.ht != null && this.ha.getTime() > this.ht.getTime() && this.ha.getTime() - this.ht.getTime() < e.kg) {
            long time = this.ha.getTime() - this.ht.getTime();
            float[] fArr = new float[2];
            Location.distanceBetween(this.ha.getAltitude(), this.ha.getLongitude(), this.ht.getLatitude(), this.ht.getLongitude(), fArr);
            double speed = (double) ((2.0f * (fArr[0] - (this.ht.getSpeed() * ((float) time)))) / ((float) (time * time)));
            if (speed > ((double) this.gP)) {
                this.gP = (float) speed;
            }
        }
        this.he.add(Byte.valueOf((byte) (abs & 255)));
        this.he.add(Byte.valueOf((byte) (abs2 & 255)));
        byte b;
        if (obj > null) {
            b = (byte) 32;
            if (obj4 > null) {
                b = (byte) 96;
            }
            if (obj3 > null) {
                b = (byte) (b | -128);
            }
            this.he.add(Byte.valueOf(b));
        } else {
            b = (byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & 31);
            if (obj4 > null) {
                b = (byte) (b | 64);
            }
            if (obj3 > null) {
                b = (byte) (b | -128);
            }
            this.he.add(Byte.valueOf(b));
        }
        if (obj2 > null) {
            this.he.add(Byte.valueOf(Byte.MIN_VALUE));
            return;
        }
        this.he.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & TransportMediator.KEYCODE_MEDIA_PAUSE)));
    }

    private void new(int i) {
        if (i != 0) {
            try {
                RandomAccessFile randomAccessFile;
                File file = new File(com.baidu.location.b.e.int + "/grtcf.dat");
                if (!file.exists()) {
                    File file2 = new File(com.baidu.location.b.e.int);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (file.createNewFile()) {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(2);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.seek(8);
                        byte[] bytes = "1980_01_01:0".getBytes();
                        randomAccessFile.writeInt(bytes.length);
                        randomAccessFile.write(bytes);
                        randomAccessFile.seek(200);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.seek(800);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.close();
                    } else {
                        return;
                    }
                }
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(8);
                byte[] bytes2 = (o(g6) + ":" + i).getBytes();
                randomAccessFile.writeInt(bytes2.length);
                randomAccessFile.write(bytes2);
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
    }

    private void new(Location location) {
        if (System.currentTimeMillis() - this.hf >= ((long) this.gT) && location != null) {
            if (location != null && location.hasSpeed() && location.getSpeed() > this.g8) {
                this.g8 = location.getSpeed();
            }
            try {
                if (this.he == null) {
                    this.he = new ArrayList();
                    bO();
                    if(location);
                } else {
                    int(location);
                }
            } catch (Exception e) {
            }
            this.hk++;
        }
    }

    private String o(String str) {
        Calendar instance = Calendar.getInstance();
        return String.format(str, new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5))});
    }

    private void p(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("on")) {
                    this.g0 = jSONObject.getInt("on");
                }
                if (jSONObject.has("bash")) {
                    this.gS = jSONObject.getDouble("bash");
                }
                if (jSONObject.has("net")) {
                    this.gN = jSONObject.getString("net");
                }
                if (jSONObject.has("tcon")) {
                    this.g4 = jSONObject.getInt("tcon");
                }
                if (jSONObject.has("tcsh")) {
                    this.g5 = jSONObject.getInt("tcsh");
                }
                if (jSONObject.has("per")) {
                    this.gL = jSONObject.getInt("per");
                }
                if (jSONObject.has("chdron")) {
                    this.hm = jSONObject.getInt("chdron");
                }
                if (jSONObject.has("spsh")) {
                    this.gO = jSONObject.getDouble("spsh");
                }
                if (jSONObject.has("acsh")) {
                    this.g9 = jSONObject.getDouble("acsh");
                }
                if (jSONObject.has("stspsh")) {
                    this.hd = jSONObject.getDouble("stspsh");
                }
                if (jSONObject.has("drstsh")) {
                    this.hv = jSONObject.getInt("drstsh");
                }
                if (jSONObject.has("stper")) {
                    this.gQ = jSONObject.getInt("stper");
                }
                if (jSONObject.has("nondron")) {
                    this.gU = jSONObject.getInt("nondron");
                }
                if (jSONObject.has("nondrper")) {
                    this.hl = jSONObject.getInt("nondrper");
                }
                if (jSONObject.has("uptime")) {
                    this.hn = jSONObject.getLong("uptime");
                }
                bN();
            } catch (JSONException e) {
            }
        }
    }

    private byte[] q(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(255);
        byte nextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = nextInt;
        i2 = i + 1;
        bArr[i] = nextInt2;
        return bArr;
    }

    private void r(String str) {
        int i = 1;
        try {
            File file = new File(com.baidu.location.b.e.int + "/grtcf.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2);
                int readInt = randomAccessFile.readInt();
                randomAccessFile.seek(8);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.read(bArr, 0, readInt2);
                String str2 = new String(bArr);
                if (str2.contains(o(g6)) && str2.contains(":")) {
                    try {
                        String[] split = str2.split(":");
                        if (split.length > 1) {
                            this.hc = Integer.valueOf(split[1]).intValue();
                        }
                    } catch (Exception e) {
                    }
                }
                while (i <= readInt) {
                    randomAccessFile.seek((long) (i * 2048));
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    str2 = new String(bArr);
                    if (str != null && str2.contains(str)) {
                        p(str2);
                        break;
                    }
                    i++;
                }
                randomAccessFile.close();
            }
        } catch (Exception e2) {
        }
    }

    private void s(String str) {
        try {
            File file = new File(com.baidu.location.b.e.int + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(com.baidu.location.b.e.int);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(2);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.seek(8);
                    byte[] bytes = "1980_01_01:0".getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes);
                    randomAccessFile.seek(200);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.seek(800);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(2);
            int readInt = randomAccessFile2.readInt();
            int i = 1;
            while (i <= readInt) {
                randomAccessFile2.seek((long) (i * 2048));
                int readInt2 = randomAccessFile2.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile2.read(bArr, 0, readInt2);
                if (new String(bArr).contains(c.bn)) {
                    break;
                }
                i++;
            }
            if (i >= readInt) {
                randomAccessFile2.seek(2);
                randomAccessFile2.writeInt(i);
            }
            randomAccessFile2.seek((long) (i * 2048));
            byte[] bytes2 = str.getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception e) {
        }
    }

    public void bK() {
        if (this.hj) {
            this.hj = false;
            bM();
        }
    }

    public void bL() {
        this.gK.postDelayed(new h_3(this), 2000);
    }

    public void for(final Location location) {
        if (!this.hj) {
            bQ();
        }
        if (this.g0 != 1 || ((double) e.bw().bx()) >= this.gS * 100.0d || !this.gN.contains(com.baidu.location.h.c.a(com.baidu.location.h.c.a().cQ()))) {
            return;
        }
        if (this.g4 != 1 || this.hc <= this.g5) {
            this.gK.post(new Runnable(this) {
                final /* synthetic */ h a;

                public void run() {
                    this.a.do(location);
                }
            });
        }
    }
}
