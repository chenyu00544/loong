package com.baidu.location.e;

import android.location.Location;
import com.baidu.location.Jni;
import com.baidu.location.b.c;
import com.baidu.location.b.e;
import com.baidu.location.b.f;
import com.baidu.mapapi.UIMsg.m_AppUI;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

public class k implements f {
    private static final int h0 = 5;
    private static final int h1 = 750;
    private static final int h2 = 1000;
    private static final int h4 = 100;
    private static int h6 = 0;
    private static k h7 = null;
    private static long h8 = 0;
    private static final int hK = 12;
    private static StringBuffer hL = null;
    private static final int hM = 5;
    private static File hN = new File(e.int, hX);
    private static final int hO = 3600;
    private static int hP = 0;
    private static long hQ = 0;
    private static long hR = 0;
    private static boolean hS = true;
    private static final int hT = 1024;
    private static int hU = 0;
    private static double hV = 0.0d;
    private static double hW = 0.0d;
    private static String hX = "Temp_in.dat";
    private static int hY = 0;
    private static int hZ = 0;
    private String h3 = null;
    private boolean h5 = true;

    private k(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.h3 = str;
    }

    private static boolean b3() {
        if (hN.exists()) {
            hN.delete();
        }
        if (!hN.getParentFile().exists()) {
            hN.getParentFile().mkdirs();
        }
        try {
            hN.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(hN, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            b5();
            return hN.exists();
        } catch (IOException e) {
            return false;
        }
    }

    private void b4() {
        if (hL != null && hL.length() >= 100) {
            u(hL.toString());
        }
        b5();
    }

    private static void b5() {
        hS = true;
        hL = null;
        hY = 0;
        h6 = 0;
        hR = 0;
        hQ = 0;
        h8 = 0;
        hV = 0.0d;
        hW = 0.0d;
        hP = 0;
        hU = 0;
        hZ = 0;
    }

    private void b6() {
    }

    public static k b7() {
        if (h7 == null) {
            h7 = new k(c.N().K());
        }
        return h7;
    }

    public static String b8() {
        if (hN == null) {
            return null;
        }
        if (!hN.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(hN, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (!if(readInt, readInt2, readInt3)) {
                randomAccessFile.close();
                b3();
                return null;
            } else if (readInt2 == 0 || readInt2 == readInt3) {
                randomAccessFile.close();
                return null;
            } else {
                long j = 0 + ((long) (((readInt2 - 1) * 1024) + 12));
                randomAccessFile.seek(j);
                int readInt4 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt4];
                randomAccessFile.seek(j + 4);
                for (readInt3 = 0; readInt3 < readInt4; readInt3++) {
                    bArr[readInt3] = randomAccessFile.readByte();
                }
                String str = new String(bArr);
                readInt3 = readInt < com.baidu.location.b.k.cr ? readInt2 + 1 : readInt2 == com.baidu.location.b.k.cr ? 1 : readInt2 + 1;
                randomAccessFile.seek(4);
                randomAccessFile.writeInt(readInt3);
                randomAccessFile.close();
                return str;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private boolean b9() {
        if (hN.exists()) {
            hN.delete();
        }
        b5();
        return !hN.exists();
    }

    private static boolean if(int i, int i2, int i3) {
        return (i < 0 || i > com.baidu.location.b.k.cr) ? false : (i2 < 0 || i2 > i + 1) ? false : i3 >= 1 && i3 <= i + 1 && i3 <= com.baidu.location.b.k.cr;
    }

    private boolean if(Location location, int i, int i2) {
        if (location == null || !com.baidu.location.b.k.bR || !this.h5 || !f.bB().gD) {
            return false;
        }
        if (com.baidu.location.b.k.cB < 5) {
            com.baidu.location.b.k.cB = 5;
        } else if (com.baidu.location.b.k.cB > 1000) {
            com.baidu.location.b.k.cB = 1000;
        }
        if (com.baidu.location.b.k.cF < 5) {
            com.baidu.location.b.k.cF = 5;
        } else if (com.baidu.location.b.k.cF > hO) {
            com.baidu.location.b.k.cF = hO;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        long time = location.getTime() / 1000;
        if (hS) {
            hY = 1;
            hL = new StringBuffer("");
            hL.append(String.format(Locale.CHINA, "&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.h3, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
            h6 = hL.length();
            hR = time;
            hV = longitude;
            hW = latitude;
            hQ = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            h8 = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            hS = false;
            return true;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(latitude, longitude, hW, hV, fArr);
        long j = time - hR;
        if (fArr[0] < ((float) com.baidu.location.b.k.cB) && j < ((long) com.baidu.location.b.k.cF)) {
            return false;
        }
        if (hL == null) {
            hY++;
            h6 = 0;
            hL = new StringBuffer("");
            hL.append(String.format(Locale.CHINA, "&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.h3, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
            h6 = hL.length();
            hR = time;
            hV = longitude;
            hW = latitude;
            hQ = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            h8 = (long) Math.floor((latitude * 100000.0d) + 0.5d);
        } else {
            hV = longitude;
            hW = latitude;
            long floor = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            long floor2 = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            hP = (int) (time - hR);
            hU = (int) (floor - hQ);
            hZ = (int) (floor2 - h8);
            hL.append(String.format(Locale.CHINA, "%d,%d,%d|", new Object[]{Integer.valueOf(hP), Integer.valueOf(hU), Integer.valueOf(hZ)}));
            h6 = hL.length();
            hR = time;
            hQ = floor;
            h8 = floor2;
        }
        if (h6 + 15 > h1) {
            u(hL.toString());
            hL = null;
        }
        if (hY >= com.baidu.location.b.k.cr) {
            this.h5 = false;
        }
        return true;
    }

    private String try(int i) {
        if (!hN.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(hN, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            if (!if(readInt, randomAccessFile.readInt(), randomAccessFile.readInt())) {
                randomAccessFile.close();
                b3();
                return null;
            } else if (i == 0 || i == readInt + 1) {
                randomAccessFile.close();
                return null;
            } else {
                long j = (12 + 0) + ((long) ((i - 1) * 1024));
                randomAccessFile.seek(j);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.seek(j + 4);
                for (readInt = 0; readInt < readInt2; readInt++) {
                    bArr[readInt] = randomAccessFile.readByte();
                }
                randomAccessFile.close();
                return new String(bArr);
            }
        } catch (IOException e) {
            return null;
        }
    }

    private boolean u(String str) {
        if (str == null || !str.startsWith("&nr")) {
            return false;
        }
        if (!hN.exists() && !b3()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(hN, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (if(readInt, readInt2, readInt3)) {
                if (com.baidu.location.b.k.bK) {
                    if (readInt == com.baidu.location.b.k.cr) {
                        if (str.equals(try(readInt3 == 1 ? com.baidu.location.b.k.cr : readInt3 - 1))) {
                            randomAccessFile.close();
                            return false;
                        }
                    } else if (readInt3 > 1 && str.equals(try(readInt3 - 1))) {
                        randomAccessFile.close();
                        return false;
                    }
                }
                randomAccessFile.seek(((long) (((readInt3 - 1) * 1024) + 12)) + 0);
                if (str.length() > h1) {
                    randomAccessFile.close();
                    return false;
                }
                String H = Jni.H(str);
                int length = H.length();
                if (length > m_AppUI.MSG_GET_GL_OK) {
                    randomAccessFile.close();
                    return false;
                }
                randomAccessFile.writeInt(length);
                randomAccessFile.writeBytes(H);
                if (readInt == 0) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(1);
                    randomAccessFile.writeInt(1);
                    randomAccessFile.writeInt(2);
                } else if (readInt < com.baidu.location.b.k.cr - 1) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(readInt + 1);
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(readInt + 2);
                } else if (readInt == com.baidu.location.b.k.cr - 1) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(com.baidu.location.b.k.cr);
                    if (readInt2 == 0 || readInt2 == 1) {
                        randomAccessFile.writeInt(2);
                    }
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(1);
                } else if (readInt3 == readInt2) {
                    readInt = readInt3 == com.baidu.location.b.k.cr ? 1 : readInt3 + 1;
                    r2 = readInt == com.baidu.location.b.k.cr ? 1 : readInt + 1;
                    randomAccessFile.seek(4);
                    randomAccessFile.writeInt(r2);
                    randomAccessFile.writeInt(readInt);
                } else {
                    readInt = readInt3 == com.baidu.location.b.k.cr ? 1 : readInt3 + 1;
                    if (readInt == readInt2) {
                        r2 = readInt == com.baidu.location.b.k.cr ? 1 : readInt + 1;
                        randomAccessFile.seek(4);
                        randomAccessFile.writeInt(r2);
                    }
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(readInt);
                }
                randomAccessFile.close();
                return true;
            }
            randomAccessFile.close();
            b3();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void ca() {
        b4();
    }

    public boolean try(Location location) {
        return if(location, com.baidu.location.b.k.cB, com.baidu.location.b.k.cF);
    }
}
