package com.baidu.mapapi.search.route;

import com.baidu.location.b.g;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.i;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption.DrivingPolicy;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption.DrivingTrafficPolicy;
import com.baidu.mapapi.search.route.TransitRoutePlanOption.TransitPolicy;
import com.baidu.platform.comapi.search.b;
import com.baidu.platform.comapi.search.d;
import com.baidu.platform.comapi.search.f;
import java.util.ArrayList;

public final class RoutePlanSearch extends i {
    private d a;
    private OnGetRoutePlanResultListener b;
    private boolean c;
    private int d;
    private int e;
    private int f;

    private class a implements b {
        final /* synthetic */ RoutePlanSearch a;

        private a(RoutePlanSearch routePlanSearch) {
            this.a = routePlanSearch;
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
                    case 12:
                        errorno = ERRORNO.NOT_SUPPORT_BUS;
                        break;
                    case 13:
                        errorno = ERRORNO.NOT_SUPPORT_BUS_2CITY;
                        break;
                    case 14:
                        errorno = ERRORNO.ST_EN_TOO_NEAR;
                        break;
                    case 107:
                        errorno = ERRORNO.PERMISSION_UNFINISHED;
                        break;
                    case d_ResultType.SHORT_URL /*500*/:
                        errorno = ERRORNO.KEY_ERROR;
                        break;
                    case g.I /*701*/:
                        errorno = ERRORNO.MASS_TRANSIT_SERVER_ERROR;
                        break;
                    case 702:
                        errorno = ERRORNO.MASS_TRANSIT_OPTION_ERROR;
                        break;
                    case 703:
                        errorno = ERRORNO.MASS_TRANSIT_NO_POI_ERROR;
                        break;
                }
                if (errorno != null) {
                    switch (this.a.e) {
                        case 0:
                            this.a.b.onGetTransitRouteResult(new TransitRouteResult(errorno));
                            return;
                        case 1:
                            this.a.b.onGetWalkingRouteResult(new WalkingRouteResult(errorno));
                            return;
                        case 2:
                            this.a.b.onGetDrivingRouteResult(new DrivingRouteResult(errorno));
                            return;
                        case 3:
                            this.a.b.onGetBikingRouteResult(new BikingRouteResult(errorno));
                            return;
                        case 4:
                            this.a.b.onGetIndoorRouteResult(new IndoorRouteResult(errorno));
                            return;
                        case 5:
                            this.a.b.onGetMassTransitRouteResult(new MassTransitRouteResult(errorno));
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
            if (!this.a.c && str != null && str.length() != 0 && this.a.b != null) {
                switch (this.a.e) {
                    case 0:
                        TransitRouteResult transitRouteResult = new TransitRouteResult(ERRORNO.AMBIGUOUS_ROURE_ADDR);
                        transitRouteResult.a(n.j(str));
                        this.a.b.onGetTransitRouteResult(transitRouteResult);
                        return;
                    case 1:
                        WalkingRouteResult walkingRouteResult = new WalkingRouteResult(ERRORNO.AMBIGUOUS_ROURE_ADDR);
                        walkingRouteResult.a(n.j(str));
                        this.a.b.onGetWalkingRouteResult(walkingRouteResult);
                        return;
                    case 2:
                        DrivingRouteResult drivingRouteResult = new DrivingRouteResult(ERRORNO.AMBIGUOUS_ROURE_ADDR);
                        drivingRouteResult.a(n.j(str));
                        if (drivingRouteResult.getSuggestAddrInfo().getSuggestEndCity() == null && drivingRouteResult.getSuggestAddrInfo().getSuggestEndNode() == null && drivingRouteResult.getSuggestAddrInfo().getSuggestStartCity() == null && drivingRouteResult.getSuggestAddrInfo().getSuggestStartNode() == null && drivingRouteResult.getSuggestAddrInfo().getSuggestWpCity() == null && drivingRouteResult.getSuggestAddrInfo().getSuggestWpNode() == null) {
                            drivingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
                        }
                        this.a.b.onGetDrivingRouteResult(drivingRouteResult);
                        return;
                    case 3:
                        BikingRouteResult bikingRouteResult = new BikingRouteResult(ERRORNO.AMBIGUOUS_ROURE_ADDR);
                        bikingRouteResult.a(n.j(str));
                        this.a.b.onGetBikingRouteResult(bikingRouteResult);
                        return;
                    default:
                        return;
                }
            }
        }

        public void d(String str) {
        }

        public void e(String str) {
        }

        public void f(String str) {
            if (!this.a.c && str != null && str.length() != 0 && this.a.b != null) {
                this.a.b.onGetDrivingRouteResult(n.c(str));
            }
        }

        public void g(String str) {
            if (!this.a.c && str != null && str.length() != 0 && this.a.b != null) {
                this.a.b.onGetIndoorRouteResult(n.d(str));
            }
        }

        public void h(String str) {
            if (!this.a.c && str != null && str.length() != 0 && this.a.b != null) {
                this.a.b.onGetWalkingRouteResult(n.e(str));
            }
        }

        public void i(String str) {
            if (!this.a.c && str != null && str.length() != 0 && this.a.b != null) {
                this.a.b.onGetTransitRouteResult(n.a(str));
            }
        }

        public void j(String str) {
            this.a.b.onGetMassTransitRouteResult(n.b(str));
        }

        public void k(String str) {
            if (!this.a.c && str != null && str.length() != 0 && this.a.b != null) {
                this.a.b.onGetBikingRouteResult(n.f(str));
            }
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

    RoutePlanSearch() {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = 0;
        this.a = new d();
        this.a.a(new a());
    }

    private ArrayList<f> a(DrivingRoutePlanOption drivingRoutePlanOption) {
        if (drivingRoutePlanOption.e == null) {
            return null;
        }
        ArrayList<f> arrayList = new ArrayList();
        for (PlanNode planNode : drivingRoutePlanOption.e) {
            if (planNode != null && (planNode.getLocation() != null || (planNode.getName() != null && planNode.getCity() != null && planNode.getName().length() > 0 && planNode.getCity().length() > 0))) {
                f fVar = new f();
                if (planNode.getName() != null) {
                    fVar.b = planNode.getName();
                }
                if (planNode.getLocation() != null) {
                    fVar.a = CoordUtil.ll2point(planNode.getLocation());
                }
                if (planNode.getCity() == null) {
                    fVar.c = "";
                } else {
                    fVar.c = planNode.getCity();
                }
                arrayList.add(fVar);
            }
        }
        return arrayList;
    }

    public static RoutePlanSearch newInstance() {
        BMapManager.init();
        return new RoutePlanSearch();
    }

    public boolean bikingSearch(BikingRoutePlanOption bikingRoutePlanOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (bikingRoutePlanOption == null || bikingRoutePlanOption.b == null || bikingRoutePlanOption.a == null) {
            throw new IllegalArgumentException("route plan option , origin or destination can not be null");
        } else {
            com.baidu.platform.comapi.search.a aVar = new com.baidu.platform.comapi.search.a();
            if (bikingRoutePlanOption.a.getName() != null) {
                aVar.d = bikingRoutePlanOption.a.getName();
            }
            if (bikingRoutePlanOption.a.getLocation() != null) {
                aVar.c = bikingRoutePlanOption.a.getLocation();
                aVar.a = 1;
            }
            com.baidu.platform.comapi.search.a aVar2 = new com.baidu.platform.comapi.search.a();
            if (bikingRoutePlanOption.b.getName() != null) {
                aVar2.d = bikingRoutePlanOption.b.getName();
            }
            if (bikingRoutePlanOption.b.getLocation() != null) {
                aVar2.c = bikingRoutePlanOption.b.getLocation();
                aVar2.a = 1;
            }
            this.d = this.e;
            this.e = 3;
            return this.a.a(aVar, aVar2, bikingRoutePlanOption.a.getCity(), bikingRoutePlanOption.b.getCity());
        }
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

    public boolean drivingSearch(DrivingRoutePlanOption drivingRoutePlanOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (drivingRoutePlanOption == null || drivingRoutePlanOption.b == null || drivingRoutePlanOption.a == null) {
            throw new IllegalArgumentException("route plan option , origin or destination can not be null");
        } else {
            if (drivingRoutePlanOption.d == null) {
                drivingRoutePlanOption.d = DrivingPolicy.ECAR_TIME_FIRST;
            }
            com.baidu.platform.comapi.search.a aVar = new com.baidu.platform.comapi.search.a();
            if (drivingRoutePlanOption.a.getName() != null) {
                aVar.d = drivingRoutePlanOption.a.getName();
            }
            if (drivingRoutePlanOption.a.getLocation() != null) {
                aVar.b = CoordUtil.ll2point(drivingRoutePlanOption.a.getLocation());
                aVar.a = 1;
            }
            com.baidu.platform.comapi.search.a aVar2 = new com.baidu.platform.comapi.search.a();
            if (drivingRoutePlanOption.b.getName() != null) {
                aVar2.d = drivingRoutePlanOption.b.getName();
            }
            if (drivingRoutePlanOption.b.getLocation() != null) {
                aVar2.b = CoordUtil.ll2point(drivingRoutePlanOption.b.getLocation());
                aVar2.a = 1;
            }
            this.d = this.e;
            this.e = 2;
            int i = DrivingTrafficPolicy.ROUTE_PATH.getInt();
            if (drivingRoutePlanOption.f != null) {
                i = drivingRoutePlanOption.f.getInt();
            }
            return this.a.a(aVar, aVar2, drivingRoutePlanOption.c, drivingRoutePlanOption.a.getCity(), drivingRoutePlanOption.b.getCity(), null, 12, drivingRoutePlanOption.d.getInt(), i, a(drivingRoutePlanOption), null);
        }
    }

    public boolean masstransitSearch(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (massTransitRoutePlanOption == null || massTransitRoutePlanOption.b == null || massTransitRoutePlanOption.a == null) {
            throw new IllegalArgumentException("route plan option,origin or destination can not be null");
        } else if (massTransitRoutePlanOption.a.getLocation() == null && (massTransitRoutePlanOption.a.getName() == null || massTransitRoutePlanOption.a.getCity() == null)) {
            throw new IllegalArgumentException("route plan option,origin is illegal");
        } else if (massTransitRoutePlanOption.b.getLocation() == null && (massTransitRoutePlanOption.b.getName() == null || massTransitRoutePlanOption.b.getCity() == null)) {
            throw new IllegalArgumentException("route plan option,destination is illegal");
        } else {
            this.d = this.e;
            this.e = 5;
            this.f = massTransitRoutePlanOption.h;
            this.a.a(massTransitRoutePlanOption.g);
            return this.a.a(massTransitRoutePlanOption.a, massTransitRoutePlanOption.b, massTransitRoutePlanOption.c, massTransitRoutePlanOption.d.getInt(), massTransitRoutePlanOption.e.getInt(), massTransitRoutePlanOption.f.getInt(), this.f);
        }
    }

    public void setOnGetRoutePlanResultListener(OnGetRoutePlanResultListener onGetRoutePlanResultListener) {
        this.b = onGetRoutePlanResultListener;
    }

    public boolean transitSearch(TransitRoutePlanOption transitRoutePlanOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (transitRoutePlanOption == null || transitRoutePlanOption.c == null || transitRoutePlanOption.b == null || transitRoutePlanOption.a == null) {
            throw new IllegalArgumentException("route plan option,origin or destination or city can not be null");
        } else {
            if (transitRoutePlanOption.d == null) {
                transitRoutePlanOption.d = TransitPolicy.EBUS_TIME_FIRST;
            }
            com.baidu.platform.comapi.search.a aVar = new com.baidu.platform.comapi.search.a();
            if (transitRoutePlanOption.a.getName() != null) {
                aVar.d = transitRoutePlanOption.a.getName();
            }
            if (transitRoutePlanOption.a.getLocation() != null) {
                aVar.b = CoordUtil.ll2point(transitRoutePlanOption.a.getLocation());
                aVar.a = 1;
            }
            com.baidu.platform.comapi.search.a aVar2 = new com.baidu.platform.comapi.search.a();
            if (transitRoutePlanOption.b.getName() != null) {
                aVar2.d = transitRoutePlanOption.b.getName();
            }
            if (transitRoutePlanOption.b.getLocation() != null) {
                aVar2.b = CoordUtil.ll2point(transitRoutePlanOption.b.getLocation());
                aVar2.a = 1;
            }
            this.d = this.e;
            this.e = 0;
            return this.a.a(aVar, aVar2, transitRoutePlanOption.c, null, 12, transitRoutePlanOption.d.getInt(), null);
        }
    }

    public boolean walkingIndoorSearch(IndoorRoutePlanOption indoorRoutePlanOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (indoorRoutePlanOption == null || indoorRoutePlanOption.b == null || indoorRoutePlanOption.a == null) {
            throw new IllegalArgumentException("option , origin or destination can not be null");
        } else {
            GeoPoint ll2mc = CoordUtil.ll2mc(indoorRoutePlanOption.a.a());
            GeoPoint ll2mc2 = CoordUtil.ll2mc(indoorRoutePlanOption.b.a());
            String replaceAll = (String.format("%f,%f", new Object[]{Double.valueOf(ll2mc.getLongitudeE6()), Double.valueOf(ll2mc.getLatitudeE6())}) + "|" + indoorRoutePlanOption.a.b()).replaceAll(" ", "");
            String replaceAll2 = (String.format("%f,%f", new Object[]{Double.valueOf(ll2mc2.getLongitudeE6()), Double.valueOf(ll2mc2.getLatitudeE6())}) + "|" + indoorRoutePlanOption.b.b()).replaceAll(" ", "");
            this.d = this.e;
            this.e = 4;
            return this.a.a(replaceAll, replaceAll2, null);
        }
    }

    public boolean walkingSearch(WalkingRoutePlanOption walkingRoutePlanOption) {
        if (this.a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (walkingRoutePlanOption == null || walkingRoutePlanOption.b == null || walkingRoutePlanOption.a == null) {
            throw new IllegalArgumentException("option , origin or destination can not be null");
        } else {
            com.baidu.platform.comapi.search.a aVar = new com.baidu.platform.comapi.search.a();
            if (walkingRoutePlanOption.a.getName() != null) {
                aVar.d = walkingRoutePlanOption.a.getName();
            }
            if (walkingRoutePlanOption.a.getLocation() != null) {
                aVar.b = CoordUtil.ll2point(walkingRoutePlanOption.a.getLocation());
                aVar.a = 1;
            }
            com.baidu.platform.comapi.search.a aVar2 = new com.baidu.platform.comapi.search.a();
            if (walkingRoutePlanOption.b.getName() != null) {
                aVar2.d = walkingRoutePlanOption.b.getName();
            }
            if (walkingRoutePlanOption.b.getLocation() != null) {
                aVar2.b = CoordUtil.ll2point(walkingRoutePlanOption.b.getLocation());
                aVar2.a = 1;
            }
            this.d = this.e;
            this.e = 1;
            return this.a.a(aVar, aVar2, null, walkingRoutePlanOption.a.getCity(), walkingRoutePlanOption.b.getCity(), null, 12, null);
        }
    }
}
