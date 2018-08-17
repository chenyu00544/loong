package com.alipay.android.phone.mrpc.core;

import com.umeng.message.util.HttpRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public final class n extends a {
    private k g;

    public n(k kVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, HttpRequest.CONTENT_TYPE_FORM, z);
        this.g = kVar;
    }

    public final Object a() {
        Throwable e;
        x sVar = new s(this.g.a());
        sVar.a(this.b);
        sVar.a(this.e);
        sVar.a(this.f);
        sVar.a("id", String.valueOf(this.d));
        sVar.a("operationType", this.c);
        sVar.a("gzip", String.valueOf(this.g.d()));
        sVar.a(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> b = this.g.c().b();
        if (!(b == null || b.isEmpty())) {
            for (Header a : b) {
                sVar.a(a);
            }
        }
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(sVar.toString());
        try {
            y yVar = (y) this.g.b().a(sVar).get();
            if (yVar != null) {
                return yVar.b();
            }
            throw new RpcException(Integer.valueOf(9), "response is null");
        } catch (Throwable e2) {
            throw new RpcException(Integer.valueOf(13), "", e2);
        } catch (Throwable e22) {
            Throwable th = e22;
            e22 = th.getCause();
            if (e22 == null || !(e22 instanceof HttpException)) {
                throw new RpcException(Integer.valueOf(9), "", th);
            }
            HttpException httpException = (HttpException) e22;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        } catch (Throwable e222) {
            throw new RpcException(Integer.valueOf(13), "", e222);
        }
    }
}
