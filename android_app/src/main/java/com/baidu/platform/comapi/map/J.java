package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import com.baidu.mapapi.UIMsg.m_AppUI;

class j extends Handler {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.a.g == null || ((Long) message.obj).longValue() != this.a.g.h) {
            return;
        }
        if (message.what == m_AppUI.MSG_APP_SAVESCREEN) {
            for (k kVar : this.a.g.f) {
                Bitmap bitmap = null;
                if (message.arg2 == 1) {
                    int[] iArr = new int[(this.a.d * this.a.e)];
                    int[] iArr2 = new int[(this.a.d * this.a.e)];
                    if (this.a.g.g != null) {
                        int[] a = this.a.g.g.a(iArr, this.a.d, this.a.e);
                        for (int i = 0; i < this.a.e; i++) {
                            for (int i2 = 0; i2 < this.a.d; i2++) {
                                int i3 = a[(this.a.d * i) + i2];
                                iArr2[(((this.a.e - i) - 1) * this.a.d) + i2] = ((i3 & -16711936) | ((i3 << 16) & 16711680)) | ((i3 >> 16) & 255);
                            }
                        }
                        bitmap = Bitmap.createBitmap(iArr2, this.a.d, this.a.e, Config.ARGB_8888);
                    } else {
                        return;
                    }
                }
                kVar.a(bitmap);
            }
        } else if (message.what == 39) {
            if (this.a.g != null) {
                if (message.arg1 == 100) {
                    this.a.g.A();
                } else if (message.arg1 == 200) {
                    this.a.g.K();
                } else if (message.arg1 == 1) {
                    this.a.requestRender();
                } else if (message.arg1 == 0) {
                    this.a.requestRender();
                    if (!(this.a.g.c() || this.a.getRenderMode() == 0)) {
                        this.a.setRenderMode(0);
                    }
                } else if (message.arg1 == 2) {
                    for (k kVar2 : this.a.g.f) {
                        kVar2.c();
                    }
                }
                if (!this.a.g.i && this.a.e > 0 && this.a.d > 0 && this.a.g.b(0, 0) != null) {
                    this.a.g.i = true;
                    for (k kVar22 : this.a.g.f) {
                        kVar22.b();
                    }
                }
                for (k kVar222 : this.a.g.f) {
                    kVar222.a();
                }
            }
        } else if (message.what == 41) {
            if (this.a.g == null) {
                return;
            }
            if (this.a.g.l || this.a.g.m) {
                for (k kVar2222 : this.a.g.f) {
                    kVar2222.b(this.a.g.D());
                }
            }
        } else if (message.what == 999) {
            for (k kVar22222 : this.a.g.f) {
                kVar22222.e();
            }
        } else if (message.what == 50) {
            for (k kVar222222 : this.a.g.f) {
                if (message.arg1 == 0) {
                    kVar222222.a(false);
                } else if (message.arg1 == 1) {
                    kVar222222.a(true);
                }
            }
        }
    }
}
