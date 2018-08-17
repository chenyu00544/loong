package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.i;
import com.baidu.platform.comapi.search.b;
import com.baidu.platform.comapi.search.d;

public class GeoCoder extends i {
    private d a;
    private OnGetGeoCoderResultListener b;
    private boolean c;
    private int d;
    private int e;

    private class a implements b {
        final /* synthetic */ GeoCoder a;

        private a(GeoCoder geoCoder) {
            this.a = geoCoder;
        }

        public void a(int i) {
            if (!this.a.c && this.a.b != null) {
                ERRORNO errorno = null;
                switch (i) {
                    case 2:
                        errorno = ERRORNO.NETWORK_ERROR;
                        break;
                    case 8:
                        errorno = ERRORNO.NETWORK_TIME_OUT;
                        break;
                    case 11:
                        errorno = ERRORNO.RESULT_NOT_FOUND;
                        break;
                    case 107:
                        errorno = ERRORNO.PERMISSION_UNFINISHED;
                        break;
                    case d_ResultType.SHORT_URL /*500*/:
                        errorno = ERRORNO.KEY_ERROR;
                        break;
                }
                if (errorno != null) {
                    switch (this.a.e) {
                        case 1:
                            this.a.b.onGetGeoCodeResult(new GeoCodeResult(errorno));
                            return;
                        case 2:
                            this.a.b.onGetReverseGeoCodeResult(new ReverseGeoCodeResult(errorno));
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        public void a(String str) {
        }

        public void b(String str) {
        }

        public void c(String str) {
        }

        public void d(String str) {
        }

        public void e(String str) {
        }

        public void f(String str) {
        }

        public void g(String str) {
        }

        public void h(String str) {
        }

        public void i(String str) {
        }

        public void j(String str) {
        }

        public void k(String str) {
        }

        public void l(String str) {
            if (!this.a.c && this.a.b != null && str != null && str.length() > 0) {
                switch (this.a.e) {
                    case 1:
                        this.a.b.onGetGeoCodeResult(b.b(str));
                        return;
                    case 2:
                        this.a.b.onGetReverseGeoCodeResult(b.a(str));
                        return;
                    default:
                        return;
                }
            }
        }

        public void m(String str) {
        }

        public void n(String str) {
        }

        public void o(String str) {
        }

        public void p(String str) {
        }
    }

    GeoCoder() {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = 0;
        this.a = new d();
        this.a.a(new a());
    }

    public static GeoCoder newInstance() {
        BMapManager.init();
        return new GeoCoder();
    }

    public void destroy() {
        if (!this.c) {
            this.c = true;
            this.b = null;
            this.a.a();
            this.a = null;
            BMapManager.destroy();
        }
    }

    public boolean geocode(GeoCodeOption geoCodeOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (geoCodeOption == null || geoCodeOption.b == null || geoCodeOption.a == null) {
            throw new IllegalArgumentException("option or address or city can not be null");
        } else {
            this.d = this.e;
            this.e = 1;
            return this.a.b(geoCodeOption.b, geoCodeOption.a);
        }
    }

    public boolean reverseGeoCode(ReverseGeoCodeOption reverseGeoCodeOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (reverseGeoCodeOption == null || reverseGeoCodeOption.a == null) {
            throw new IllegalArgumentException("option or mLocation can not be null");
        } else {
            this.d = this.e;
            this.e = 2;
            return this.a.a(CoordUtil.ll2point(reverseGeoCodeOption.a));
        }
    }

    public void setOnGetGeoCodeResultListener(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        this.b = onGetGeoCoderResultListener;
    }
}
