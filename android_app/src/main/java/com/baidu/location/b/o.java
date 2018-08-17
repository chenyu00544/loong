package com.baidu.location.b;

import com.baidu.location.Jni;
import com.baidu.location.e.c;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class o implements f {
    private static final String e0 = (e.int + "/llg.dat");
    private static o e1 = null;
    private static final int e3 = 128;
    private static String e4 = "LogSDK";
    private static int e5 = 1024;
    private static final int e6 = 32;
    private static int e9 = 5;
    private static final int fa = 1040;
    private static final String fc = (e.int + "/ller.dat");
    private static final int fd = 1000;
    public static final String fe = (e.int + "/llin.dat");
    private l e2 = null;
    private long e7 = 0;
    private SimpleDateFormat e8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private a fb = null;

    private class a extends m {
        final /* synthetic */ o dj;
        private String dk;
        private boolean dl;

        a(o oVar) {
            this.dj = oVar;
            this.dk = null;
            this.dl = false;
            this.c7 = new ArrayList();
        }

        public void au() {
            this.c7.clear();
            this.c7.add(new BasicNameValuePair("qt", "stat"));
            this.c7.add(new BasicNameValuePair("req", this.dk));
            this.c5 = "http://loc.map.baidu.com/statloc";
        }

        public boolean ay() {
            return this.dl;
        }

        public void goto(String str) {
            this.dk = str;
            if (this.dk != null) {
                ao();
                this.dl = true;
            }
        }

        public void int(boolean z) {
            this.dl = false;
            if (!z || this.c6 == null) {
                this.dj.e7 = System.currentTimeMillis();
                return;
            }
            try {
                EntityUtils.toString(this.c6, "utf-8");
            } catch (Exception e) {
            }
        }
    }

    private o() {
        if (this.e2 == null) {
            this.e2 = new l();
        }
    }

    public static o aX() {
        if (e1 == null) {
            synchronized (o.class) {
                if (e1 == null) {
                    e1 = new o();
                }
            }
        }
        return e1;
    }

    public static synchronized void for(String str, String str2) {
        synchronized (o.class) {
            File file = new File(str);
            if (!file.exists()) {
                i(str);
            }
            try {
                int i;
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(4);
                int readInt = randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                int readInt3 = randomAccessFile.readInt();
                int readInt4 = randomAccessFile.readInt();
                int readInt5 = randomAccessFile.readInt();
                if (readInt3 < readInt) {
                    randomAccessFile.seek((long) ((readInt2 * readInt3) + 128));
                    byte[] bytes = (str2 + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    i = readInt3 + 1;
                } else {
                    randomAccessFile.seek((long) ((readInt4 * readInt2) + 128));
                    byte[] bytes2 = (str2 + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes2.length);
                    randomAccessFile.write(bytes2, 0, bytes2.length);
                    readInt4++;
                    if (readInt4 > readInt3) {
                        readInt4 = 0;
                        i = readInt3;
                    } else {
                        i = readInt3;
                    }
                }
                randomAccessFile.seek(12);
                randomAccessFile.writeInt(i);
                randomAccessFile.writeInt(readInt4);
                randomAccessFile.writeInt(readInt5);
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
    }

    private static void i(String str) {
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
                randomAccessFile.writeInt(1000);
                randomAccessFile.writeInt(fa);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static boolean if(String str, List list) {
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
            byte[] bArr = new byte[e5];
            int i = readInt2;
            readInt2 = e9 + 1;
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

    public l aU() {
        return this.e2;
    }

    public void aV() {
        if (this.fb == null) {
            this.fb = new a(this);
        }
        if (System.currentTimeMillis() - this.e7 >= com.umeng.analytics.a.j && !this.fb.ay()) {
            try {
                Object obj;
                Object obj2;
                List arrayList = new ArrayList();
                if(fc, arrayList);
                if (arrayList.size() > 0) {
                    obj = null;
                    obj2 = 1;
                } else {
                    if(e0, arrayList);
                    if (arrayList.size() == 0) {
                        if(fe, arrayList);
                        int i = 1;
                        obj2 = null;
                    } else {
                        obj = null;
                        obj2 = null;
                    }
                }
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                if (arrayList.size() > 0) {
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        jSONArray.put((String) arrayList.get(i2));
                    }
                    if (obj2 != null) {
                        jSONObject.put("locpt", jSONArray);
                    } else if (obj != null) {
                        jSONObject.put("locup", jSONArray);
                    } else {
                        jSONObject.put("loctc", jSONArray);
                    }
                    this.fb.goto(jSONObject.toString());
                }
            } catch (Exception e) {
            }
        }
    }

    public void aW() {
        if (this.e2 != null) {
            for(e0, Jni.H(this.e2.ak()));
            this.e2.aj();
        }
    }

    public void if(l lVar) {
        if (lVar != null) {
            for(e0, Jni.H(lVar.ak()));
        }
    }

    public void j(String str) {
        if (str != null) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                String format = this.e8.format(new Date());
                stringBuffer.append("&time=");
                stringBuffer.append(format);
                stringBuffer.append("&err=");
                stringBuffer.append(str);
                stringBuffer.append(c.N().do(false));
                stringBuffer.append(c.bq().bt());
                for(fc, Jni.H(stringBuffer.toString()));
            } catch (Exception e) {
            }
        }
    }
}
