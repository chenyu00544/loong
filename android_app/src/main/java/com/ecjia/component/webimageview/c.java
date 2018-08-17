package com.ecjia.component.webimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ECJiaWebImageManagerRetriever */
public class c extends AsyncTask<Void, Void, Bitmap> {
    private static final String a = c.class.getSimpleName();
    private static a b = new a();
    private Context c;
    private String d;
    private int e = -1;
    private b f;

    /* compiled from: ECJiaWebImageManagerRetriever */
    public interface b {
        void b();

        void b(String str, Bitmap bitmap);
    }

    /* compiled from: ECJiaWebImageManagerRetriever */
    static class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j2 = skip + j2;
            }
            return j2;
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap) obj);
    }

    public c(Context context, String str, int i, b bVar) {
        this.c = context;
        this.d = str;
        this.e = i;
        this.f = bVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected android.graphics.Bitmap a(java.lang.Void... r7) {
        /*
        r6 = this;
        r0 = b;
        r1 = r6.d;
        r0 = r0.a(r1);
        if (r0 != 0) goto L_0x001d;
    L_0x000a:
        r0 = b;
        r1 = r6.c;
        r2 = r6.d;
        r3 = r6.e;
        r0 = r0.a(r1, r2, r3);
        r1 = b;
        r2 = r6.d;
        r1.a(r2, r0);
    L_0x001d:
        if (r0 != 0) goto L_0x0049;
    L_0x001f:
        r2 = 0;
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x004a }
        r3 = r6.d;	 Catch:{ Exception -> 0x004a }
        r1.<init>(r3);	 Catch:{ Exception -> 0x004a }
        r1 = r1.openConnection();	 Catch:{ Exception -> 0x004a }
        r2 = r1.getInputStream();	 Catch:{ Exception -> 0x004a }
        r2.available();	 Catch:{ Exception -> 0x004a }
        r1 = new com.ecjia.component.webimageview.c$a;	 Catch:{ Exception -> 0x004a }
        r1.<init>(r2);	 Catch:{ Exception -> 0x004a }
        r0 = android.graphics.BitmapFactory.decodeStream(r1);	 Catch:{ Exception -> 0x004a }
        if (r0 == 0) goto L_0x0046;
    L_0x003d:
        r1 = b;	 Catch:{ Exception -> 0x004a }
        r3 = r6.c;	 Catch:{ Exception -> 0x004a }
        r4 = r6.d;	 Catch:{ Exception -> 0x004a }
        r1.a(r3, r4, r0);	 Catch:{ Exception -> 0x004a }
    L_0x0046:
        r2.close();	 Catch:{ Exception -> 0x007e }
    L_0x0049:
        return r0;
    L_0x004a:
        r1 = move-exception;
        r3 = a;	 Catch:{ all -> 0x0079 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0079 }
        r4.<init>();	 Catch:{ all -> 0x0079 }
        r5 = "Error loading image from URL ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0079 }
        r5 = r6.d;	 Catch:{ all -> 0x0079 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0079 }
        r5 = ": ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0079 }
        r1 = r1.toString();	 Catch:{ all -> 0x0079 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x0079 }
        r1 = r1.toString();	 Catch:{ all -> 0x0079 }
        android.util.Log.e(r3, r1);	 Catch:{ all -> 0x0079 }
        r2.close();	 Catch:{ Exception -> 0x0077 }
        goto L_0x0049;
    L_0x0077:
        r1 = move-exception;
        goto L_0x0049;
    L_0x0079:
        r0 = move-exception;
        r2.close();	 Catch:{ Exception -> 0x0080 }
    L_0x007d:
        throw r0;
    L_0x007e:
        r1 = move-exception;
        goto L_0x0049;
    L_0x0080:
        r1 = move-exception;
        goto L_0x007d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.webimageview.c.a(java.lang.Void[]):android.graphics.Bitmap");
    }

    protected void a(Bitmap bitmap) {
        if (this.f == null) {
            return;
        }
        if (bitmap == null) {
            this.f.b();
        } else {
            this.f.b(this.d, bitmap);
        }
    }
}
