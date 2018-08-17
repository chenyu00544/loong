package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.i;
import com.baidu.platform.comapi.search.b;
import com.baidu.platform.comapi.search.d;

public class SuggestionSearch extends i {
    private d a;
    private OnGetSuggestionResultListener b;
    private boolean c;

    private class a implements b {
        final /* synthetic */ SuggestionSearch a;

        private a(SuggestionSearch suggestionSearch) {
            this.a = suggestionSearch;
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
                    this.a.b.onGetSuggestionResult(new SuggestionResult(errorno));
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
            if (!this.a.c && str != null && str.length() > 0 && this.a.b != null) {
                this.a.b.onGetSuggestionResult(c.a(str));
            }
        }

        public void o(String str) {
        }

        public void p(String str) {
        }
    }

    SuggestionSearch() {
        this.a = null;
        this.b = null;
        this.c = false;
        this.a = new d();
        this.a.a(new a());
    }

    public static SuggestionSearch newInstance() {
        BMapManager.init();
        return new SuggestionSearch();
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

    public boolean requestSuggestion(SuggestionSearchOption suggestionSearchOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (suggestionSearchOption != null && suggestionSearchOption.b != null && suggestionSearchOption.a != null) {
            return this.a.a(suggestionSearchOption.b, 0, suggestionSearchOption.a, null, 12, CoordUtil.ll2point(suggestionSearchOption.c));
        } else {
            throw new IllegalArgumentException("option or keyword or city can not be null");
        }
    }

    public void setOnGetSuggestionResultListener(OnGetSuggestionResultListener onGetSuggestionResultListener) {
        this.b = onGetSuggestionResultListener;
    }
}
