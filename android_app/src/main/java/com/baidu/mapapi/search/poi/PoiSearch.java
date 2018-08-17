package com.baidu.mapapi.search.poi;

import com.baidu.location.b.g;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.MapBound;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.i;
import com.baidu.platform.comapi.search.b;
import com.baidu.platform.comapi.search.d;
import java.util.HashMap;
import java.util.Map;

public class PoiSearch extends i {
    private d a;
    private OnGetPoiSearchResultListener b;
    private boolean c;
    private int d;
    private int e;
    private boolean f;
    private int g;

    private class a implements b {
        final /* synthetic */ PoiSearch a;

        private a(PoiSearch poiSearch) {
            this.a = poiSearch;
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
                    case g.x /*601*/:
                        errorno = ERRORNO.POIINDOOR_BID_ERROR;
                        break;
                    case 602:
                        errorno = ERRORNO.POIINDOOR_FLOOR_ERROR;
                        break;
                    case 603:
                        errorno = ERRORNO.POIINDOOR_SERVER_ERROR;
                        break;
                }
                if (errorno == null) {
                    return;
                }
                if (this.a.e == 4) {
                    this.a.b.onGetPoiDetailResult(new PoiDetailResult(errorno));
                } else if (this.a.e == 5) {
                    this.a.b.onGetPoiIndoorResult(new PoiIndoorResult(errorno));
                } else {
                    this.a.b.onGetPoiResult(new PoiResult(errorno));
                }
            }
        }

        public void a(String str) {
            if (!this.a.c && str != null && str.length() > 0 && this.a.b != null) {
                this.a.b.onGetPoiResult(d.a(str, this.a.g, this.a.a.b()));
            }
        }

        public void b(String str) {
            if (str != null && str.length() > 0 && this.a.b != null) {
                this.a.b.onGetPoiResult(d.a(str));
            }
        }

        public void c(String str) {
        }

        public void d(String str) {
            if (!this.a.c && str != null && str.length() > 0 && this.a.b != null) {
                PoiDetailResult poiDetailResult = new PoiDetailResult();
                if (poiDetailResult.a(str)) {
                    this.a.b.onGetPoiDetailResult(poiDetailResult);
                } else {
                    this.a.b.onGetPoiDetailResult(new PoiDetailResult(ERRORNO.RESULT_NOT_FOUND));
                }
            }
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
        }

        public void m(String str) {
        }

        public void n(String str) {
        }

        public void o(String str) {
        }

        public void p(String str) {
            if (!this.a.c && str != null && str.length() > 0 && this.a.b != null) {
                this.a.b.onGetPoiIndoorResult(d.b(str));
            }
        }
    }

    PoiSearch() {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = 0;
        this.g = 0;
        this.a = new d();
        this.a.a(new a());
    }

    public static PoiSearch newInstance() {
        BMapManager.init();
        return new PoiSearch();
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

    public boolean searchInBound(PoiBoundSearchOption poiBoundSearchOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiBoundSearchOption == null || poiBoundSearchOption.a == null || poiBoundSearchOption.b == null) {
            throw new IllegalArgumentException("option or bound or keyworld can not be null");
        } else {
            this.d = this.e;
            this.e = 2;
            this.g = poiBoundSearchOption.d;
            this.a.a(poiBoundSearchOption.e);
            MapBound mapBound = new MapBound();
            mapBound.setPtRT(CoordUtil.ll2point(poiBoundSearchOption.a.northeast));
            mapBound.setPtLB(CoordUtil.ll2point(poiBoundSearchOption.a.southwest));
            return this.a.a(poiBoundSearchOption.b, 1, poiBoundSearchOption.d, mapBound, (int) poiBoundSearchOption.c, null, null);
        }
    }

    public boolean searchInCity(PoiCitySearchOption poiCitySearchOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiCitySearchOption == null || poiCitySearchOption.a == null || poiCitySearchOption.b == null) {
            throw new IllegalArgumentException("option or city or keyworld can not be null");
        } else {
            this.d = this.e;
            this.e = 1;
            this.g = poiCitySearchOption.e;
            this.a.a(poiCitySearchOption.f);
            return this.a.a(poiCitySearchOption.b, poiCitySearchOption.a, poiCitySearchOption.e, null, (int) poiCitySearchOption.c, null);
        }
    }

    public boolean searchNearby(PoiNearbySearchOption poiNearbySearchOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiNearbySearchOption == null || poiNearbySearchOption.b == null || poiNearbySearchOption.a == null) {
            throw new IllegalArgumentException("option or location or keyworld can not be null");
        } else if (poiNearbySearchOption.c <= 0) {
            return false;
        } else {
            this.d = this.e;
            this.e = 3;
            this.g = poiNearbySearchOption.e;
            this.a.a(poiNearbySearchOption.f);
            Point ll2point = CoordUtil.ll2point(poiNearbySearchOption.b);
            Point point = new Point(ll2point.x - poiNearbySearchOption.c, ll2point.y - poiNearbySearchOption.c);
            Point point2 = new Point(ll2point.x + poiNearbySearchOption.c, ll2point.y + poiNearbySearchOption.c);
            MapBound mapBound = new MapBound();
            mapBound.setPtLB(point);
            mapBound.setPtRT(point2);
            Map hashMap = new HashMap();
            hashMap.put("distance", Integer.valueOf(poiNearbySearchOption.c));
            return this.a.a(poiNearbySearchOption.a, 1, poiNearbySearchOption.e, (int) poiNearbySearchOption.d, mapBound, mapBound, hashMap, poiNearbySearchOption.g.ordinal());
        }
    }

    public boolean searchPoiDetail(PoiDetailSearchOption poiDetailSearchOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiDetailSearchOption == null || poiDetailSearchOption.a == null) {
            throw new IllegalArgumentException("option or uid can not be null");
        } else {
            this.d = this.e;
            this.e = 4;
            this.f = poiDetailSearchOption.b;
            return this.a.a(poiDetailSearchOption.a);
        }
    }

    public boolean searchPoiIndoor(PoiIndoorOption poiIndoorOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiIndoorOption == null || poiIndoorOption.a == null || poiIndoorOption.b == null) {
            throw new IllegalArgumentException("option or indoor bid or keyword can not be null");
        } else {
            this.d = this.e;
            this.e = 5;
            return this.a.a(poiIndoorOption.a, poiIndoorOption.b, poiIndoorOption.d, poiIndoorOption.e, poiIndoorOption.c);
        }
    }

    public void setOnGetPoiSearchResultListener(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        this.b = onGetPoiSearchResultListener;
    }
}
