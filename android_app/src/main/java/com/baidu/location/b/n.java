package com.baidu.location.b;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class n {
    static n if;
    int a = 20;
    int do = 0;
    int for = 3164;
    int int = 60;
    int new = 40;
    String try = "firll.dat";

    private long a(int i) {
        Throwable th;
        String ai = k.ai();
        if (ai == null) {
            return -1;
        }
        String str = ai + File.separator + this.try;
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2;
        try {
            randomAccessFile2 = new RandomAccessFile(str, "rw");
            try {
                randomAccessFile2.seek((long) i);
                int readInt = randomAccessFile2.readInt();
                long readLong = randomAccessFile2.readLong();
                if (readInt == randomAccessFile2.readInt()) {
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e) {
                        }
                    }
                    return readLong;
                } else if (randomAccessFile2 == null) {
                    return -1;
                } else {
                    try {
                        randomAccessFile2.close();
                        return -1;
                    } catch (IOException e2) {
                        return -1;
                    }
                }
            } catch (Exception e3) {
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    return -1;
                }
                try {
                    randomAccessFile.close();
                    return -1;
                } catch (IOException e4) {
                    return -1;
                }
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            if (randomAccessFile != null) {
                return -1;
            }
            randomAccessFile.close();
            return -1;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile2 = null;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }

    private void a(int i, long j) {
        String ai = k.ai();
        if (ai != null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(ai + File.separator + this.try, "rw");
                randomAccessFile.seek((long) i);
                randomAccessFile.writeInt(this.for);
                randomAccessFile.writeLong(j);
                randomAccessFile.writeInt(this.for);
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
    }

    public static n if() {
        if (if == null) {
            if = new n();
        }
        return if;
    }

    public long a() {
        return a(this.a);
    }

    public void a(long j) {
        a(this.int, j);
    }

    public long do() {
        return a(this.do);
    }

    public void do(long j) {
        a(this.a, j);
    }

    public long for() {
        return a(this.int);
    }

    public void for(long j) {
        a(this.do, j);
    }

    public void if(long j) {
        a(this.new, j);
    }

    public long int() {
        return a(this.new);
    }
}
