package com.unionpay.mobile.android.j;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.NinePatchDrawable;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.taobao.accs.flowcontrol.FlowControl;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.k;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public final class c {
    private static c c = null;
    private HashMap<Integer, WeakReference<ConstantState>> a = new HashMap();
    private Context b = null;

    private c(Context context) {
        this.b = context;
    }

    public static c a(Context context) {
        if (c == null) {
            c = new c(context);
        }
        return c;
    }

    public final Drawable a(int i) {
        return a(i, -1, -1);
    }

    public final Drawable a(int i, int i2, int i3) {
        if (i < 0) {
            return null;
        }
        Drawable a;
        WeakReference weakReference = (WeakReference) this.a.get(Integer.valueOf(i));
        if (weakReference != null) {
            ConstantState constantState = (ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable();
            }
            this.a.remove(Integer.valueOf(i));
        }
        int i4 = (i / 1000) * 1000;
        int i5 = i - i4;
        switch (i4) {
            case m_AppUI.MSG_APP_DATA_OK /*2000*/:
                int[] iArr = b.a[i5];
                a = h.a(a(iArr[0], i2, i3), a(iArr[1], i2, i3), a(iArr[2], i2, i3), a(iArr[3], i2, i3));
                break;
            case b.REQUEST_MERGE_PERIOD /*3000*/:
                i4 = b.e[i5];
                int[] iArr2 = b.d[i5];
                float[] fArr = b.b[i5];
                float[] fArr2 = b.c[i5];
                a = h.a(i4, iArr2, fArr, fArr2[0], fArr2[1], fArr2[2], fArr2[3]);
                break;
            case m_AppUI.MSG_APP_SAVESCREEN /*4000*/:
                a = h.a(b.f[i5], b.g[i5], b.h[i5]);
                break;
            default:
                a = null;
                break;
        }
        if (a == null) {
            InputStream resourceAsStream = a.class.getClassLoader().getResourceAsStream("assets/data.bin");
            InputStream dataInputStream = new DataInputStream(resourceAsStream);
            int i6 = i + FlowControl.DELAY_MAX_BRUSH;
            i4 = i6 * 8;
            while (true) {
                try {
                    long skip = dataInputStream.skip((long) i4);
                    if (skip < ((long) i4)) {
                        i4 = (int) (((long) i4) - skip);
                    } else {
                        i4 = dataInputStream.readInt();
                        int readInt = dataInputStream.readInt();
                        i4 -= (i6 * 8) + 8;
                        while (true) {
                            long skip2 = dataInputStream.skip((long) i4);
                            if (skip2 < ((long) i4)) {
                                i4 = (int) (((long) i4) - skip2);
                            } else {
                                dataInputStream.mark(readInt);
                                Bitmap decodeStream = BitmapFactory.decodeStream(dataInputStream);
                                Rect rect = new Rect();
                                if (decodeStream.getNinePatchChunk() != null) {
                                    a = new NinePatchDrawable(this.b.getResources(), decodeStream, decodeStream.getNinePatchChunk(), rect, null);
                                } else if (-1 != i3 && -1 != i2) {
                                    r1 = Bitmap.createScaledBitmap(decodeStream, i2, i3, true);
                                    if (r1 != decodeStream) {
                                        decodeStream.recycle();
                                    }
                                    a = new BitmapDrawable(this.b.getResources(), r1);
                                } else if (-1 != i3 && -1 == i2) {
                                    i4 = (int) ((((float) decodeStream.getWidth()) / ((float) decodeStream.getHeight())) * ((float) i3));
                                    k.a("img", "w=" + i4 + ",h=" + i3);
                                    r1 = Bitmap.createScaledBitmap(decodeStream, i4, i3, true);
                                    if (r1 != decodeStream) {
                                        decodeStream.recycle();
                                    }
                                    a = new BitmapDrawable(this.b.getResources(), r1);
                                } else if (-1 == i2 || -1 != i3) {
                                    a = new BitmapDrawable(this.b.getResources(), decodeStream);
                                } else {
                                    r1 = Bitmap.createScaledBitmap(decodeStream, i2, (int) ((((float) decodeStream.getHeight()) / ((float) decodeStream.getWidth())) * ((float) i2)), true);
                                    if (r1 != decodeStream) {
                                        decodeStream.recycle();
                                    }
                                    a = new BitmapDrawable(this.b.getResources(), r1);
                                }
                                dataInputStream.close();
                                resourceAsStream.close();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    a = null;
                }
            }
        }
        if (a == null) {
            return a;
        }
        this.a.put(Integer.valueOf(i), new WeakReference(a.getConstantState()));
        return a;
    }

    public final void a() {
        for (WeakReference weakReference : this.a.values()) {
            ConstantState constantState = (ConstantState) weakReference.get();
            if (constantState != null) {
                Drawable newDrawable = constantState.newDrawable();
                if (newDrawable instanceof BitmapDrawable) {
                    ((BitmapDrawable) newDrawable).getBitmap().recycle();
                }
            }
        }
        this.a.clear();
        c = null;
    }
}
