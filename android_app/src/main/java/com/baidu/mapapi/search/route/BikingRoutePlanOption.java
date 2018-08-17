package com.baidu.mapapi.search.route;

public class BikingRoutePlanOption {
    PlanNode a = null;
    PlanNode b = null;

    public BikingRoutePlanOption from(PlanNode planNode) {
        this.a = planNode;
        return this;
    }

    public BikingRoutePlanOption to(PlanNode planNode) {
        this.b = planNode;
        return this;
    }
}
