package com.baidu.mapapi.search.share;

import com.baidu.mapapi.search.route.PlanNode;

public class RouteShareURLOption {
    PlanNode a = null;
    PlanNode b = null;
    RouteShareMode c;
    int d = 0;
    int e = -1;

    public enum RouteShareMode {
        CAR_ROUTE_SHARE_MODE(0),
        FOOT_ROUTE_SHARE_MODE(1),
        CYCLE_ROUTE_SHARE_MODE(2),
        BUS_ROUTE_SHARE_MODE(3);
        
        private int a;

        private RouteShareMode(int i) {
            this.a = -1;
            this.a = i;
        }

        public int getRouteShareMode() {
            return this.a;
        }
    }

    public RouteShareURLOption cityCode(int i) {
        this.e = i;
        return this;
    }

    public RouteShareURLOption from(PlanNode planNode) {
        this.a = planNode;
        return this;
    }

    public RouteShareMode getmMode() {
        return this.c;
    }

    public RouteShareURLOption pn(int i) {
        this.d = i;
        return this;
    }

    public RouteShareURLOption routMode(RouteShareMode routeShareMode) {
        this.c = routeShareMode;
        return this;
    }

    public RouteShareURLOption to(PlanNode planNode) {
        this.b = planNode;
        return this;
    }
}
