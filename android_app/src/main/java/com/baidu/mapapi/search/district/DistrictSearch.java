package com.baidu.mapapi.search.district;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.i;
import com.baidu.platform.comapi.search.b;
import com.baidu.platform.comapi.search.d;

public class DistrictSearch extends i {
    private d a;
    private boolean b;
    private OnGetDistricSearchResultListener c;

    private class a implements b {
        final /* synthetic */ DistrictSearch a;

        private a(DistrictSearch districtSearch) {
            this.a = districtSearch;
        }

        public void a(int i) {
            if (!this.a.b && this.a.c != null) {
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
                    this.a.c.onGetDistrictResult(new DistrictResult(errorno));
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
        }

        public void m(String str) {
        }

        public void n(String str) {
        }

        public void o(String str) {
            if (!this.a.b && str != null && str.length() > 0 && this.a.c != null) {
                this.a.c.onGetDistrictResult(b.a(str));
            }
        }

        public void p(String str) {
        }
    }

    DistrictSearch() {
        this.a = null;
        this.b = false;
        this.a = new d();
        this.a.a(new a());
    }

    public static DistrictSearch newInstance() {
        BMapManager.init();
        return new DistrictSearch();
    }

    public void destroy() {
        if (!this.b) {
            this.b = true;
            this.c = null;
            this.a.a();
            this.a = null;
            BMapManager.destroy();
        }
    }

    public boolean searchDistrict(DistrictSearchOption districtSearchOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (districtSearchOption != null && districtSearchOption.a != null) {
            return this.a.c(districtSearchOption.a, districtSearchOption.b);
        } else {
            throw new IllegalArgumentException("option or city name can not be null");
        }
    }

    public void setOnDistrictSearchListener(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        this.c = onGetDistricSearchResultListener;
    }
}
