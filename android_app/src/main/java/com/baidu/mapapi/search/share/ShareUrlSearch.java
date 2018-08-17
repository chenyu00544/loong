package com.baidu.mapapi.search.share;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.i;
import com.baidu.mapapi.search.share.RouteShareURLOption.RouteShareMode;
import com.baidu.platform.comapi.search.b;
import com.baidu.platform.comapi.search.d;

public class ShareUrlSearch extends i {
    private d a;
    private OnGetShareUrlResultListener b;
    private boolean c;
    private int d;
    private int e;

    private class a implements b {
        final /* synthetic */ ShareUrlSearch a;

        private a(ShareUrlSearch shareUrlSearch) {
            this.a = shareUrlSearch;
        }

        public void a(int i) {
            if (!this.a.c && this.a.b != null) {
                ERRORNO errorno = null;
                switch (i) {
                    case 2:
                        errorno = ERRORNO.NETWORK_ERROR;
                        break;
                    case 3:
                        errorno = ERRORNO.RESULT_NOT_FOUND;
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
                            this.a.b.onGetPoiDetailShareUrlResult(new ShareUrlResult(errorno));
                            return;
                        case 2:
                            this.a.b.onGetLocationShareUrlResult(new ShareUrlResult(errorno));
                            return;
                        case 3:
                            this.a.b.onGetRouteShareUrlResult(new ShareUrlResult(errorno));
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
            if (!this.a.c && this.a.b != null) {
                switch (this.a.e) {
                    case 1:
                        this.a.b.onGetPoiDetailShareUrlResult(b.a(str));
                        return;
                    case 2:
                        this.a.b.onGetLocationShareUrlResult(b.a(str));
                        return;
                    case 3:
                        this.a.b.onGetRouteShareUrlResult(b.a(str));
                        return;
                    default:
                        return;
                }
            }
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

    ShareUrlSearch() {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = 0;
        this.a = new d();
        this.a.a(new a());
    }

    private boolean a(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static ShareUrlSearch newInstance() {
        BMapManager.init();
        return new ShareUrlSearch();
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

    public boolean requestLocationShareUrl(LocationShareURLOption locationShareURLOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (locationShareURLOption == null || locationShareURLOption.a == null || locationShareURLOption.b == null || locationShareURLOption.c == null) {
            throw new IllegalArgumentException("option or name or snippet  can not be null");
        } else {
            this.d = this.e;
            this.e = 2;
            return this.a.a(CoordUtil.ll2point(locationShareURLOption.a), locationShareURLOption.b, locationShareURLOption.c);
        }
    }

    public boolean requestPoiDetailShareUrl(PoiDetailShareURLOption poiDetailShareURLOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiDetailShareURLOption == null || poiDetailShareURLOption.a == null) {
            throw new IllegalArgumentException("option or uid can not be null");
        } else {
            this.d = this.e;
            this.e = 1;
            return this.a.b(poiDetailShareURLOption.a);
        }
    }

    public boolean requestRouteShareUrl(RouteShareURLOption routeShareURLOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (routeShareURLOption == null) {
            throw new IllegalStateException("option is null");
        } else if (routeShareURLOption.getmMode().ordinal() < 0) {
            return false;
        } else {
            if (routeShareURLOption.a == null || routeShareURLOption.b == null) {
                throw new IllegalArgumentException("start or end point can not be null");
            }
            boolean a;
            this.d = this.e;
            this.e = 3;
            if (routeShareURLOption.c == RouteShareMode.BUS_ROUTE_SHARE_MODE) {
                if ((routeShareURLOption.a.getLocation() == null || routeShareURLOption.b.getLocation() == null) && routeShareURLOption.e < 0) {
                    throw new IllegalArgumentException("city code can not be null if don't set start or end point");
                }
                a = this.a.a(CoordUtil.ll2point(routeShareURLOption.a.getLocation()), CoordUtil.ll2point(routeShareURLOption.b.getLocation()), routeShareURLOption.a.getName(), routeShareURLOption.b.getName(), -1, -1, routeShareURLOption.c.ordinal(), routeShareURLOption.e, routeShareURLOption.d);
            } else if (routeShareURLOption.a.getLocation() == null && !a(routeShareURLOption.a.getCity())) {
                throw new IllegalArgumentException("start cityCode must be set if not set start location");
            } else if (routeShareURLOption.b.getLocation() != null || a(routeShareURLOption.b.getCity())) {
                a = this.a.a(CoordUtil.ll2point(routeShareURLOption.a.getLocation()), CoordUtil.ll2point(routeShareURLOption.b.getLocation()), routeShareURLOption.a.getName(), routeShareURLOption.b.getName(), b(routeShareURLOption.a.getCity()), b(routeShareURLOption.b.getCity()), routeShareURLOption.c.ordinal(), 0, 0);
            } else {
                throw new IllegalArgumentException("end cityCode must be set if not set end location");
            }
            return a;
        }
    }

    public void setOnGetShareUrlResultListener(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        this.b = onGetShareUrlResultListener;
    }
}
