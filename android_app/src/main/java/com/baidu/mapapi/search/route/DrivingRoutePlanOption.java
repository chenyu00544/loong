package com.baidu.mapapi.search.route;

import java.util.List;

public class DrivingRoutePlanOption {
    PlanNode a = null;
    PlanNode b = null;
    String c;
    DrivingPolicy d = DrivingPolicy.ECAR_TIME_FIRST;
    List<PlanNode> e = null;
    DrivingTrafficPolicy f = DrivingTrafficPolicy.ROUTE_PATH;

    public enum DrivingPolicy {
        ECAR_AVOID_JAM(-1),
        ECAR_TIME_FIRST(0),
        ECAR_DIS_FIRST(1),
        ECAR_FEE_FIRST(2);
        
        private int a;

        private DrivingPolicy(int i) {
            this.a = i;
        }

        public int getInt() {
            return this.a;
        }
    }

    public enum DrivingTrafficPolicy {
        ROUTE_PATH(0),
        ROUTE_PATH_AND_TRAFFIC(1);
        
        private int a;

        private DrivingTrafficPolicy(int i) {
            this.a = i;
        }

        public int getInt() {
            return this.a;
        }
    }

    public DrivingRoutePlanOption currentCity(String str) {
        this.c = str;
        return this;
    }

    public DrivingRoutePlanOption from(PlanNode planNode) {
        this.a = planNode;
        return this;
    }

    public DrivingRoutePlanOption passBy(List<PlanNode> list) {
        this.e = list;
        return this;
    }

    public DrivingRoutePlanOption policy(DrivingPolicy drivingPolicy) {
        this.d = drivingPolicy;
        return this;
    }

    public DrivingRoutePlanOption to(PlanNode planNode) {
        this.b = planNode;
        return this;
    }

    public DrivingRoutePlanOption trafficPolicy(DrivingTrafficPolicy drivingTrafficPolicy) {
        this.f = drivingTrafficPolicy;
        return this;
    }
}
