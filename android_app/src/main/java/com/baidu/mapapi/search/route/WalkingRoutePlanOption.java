package com.baidu.mapapi.search.route;

public class WalkingRoutePlanOption {
    PlanNode a = null;
    PlanNode b = null;

    public WalkingRoutePlanOption from(PlanNode planNode) {
        this.a = planNode;
        return this;
    }

    public WalkingRoutePlanOption to(PlanNode planNode) {
        this.b = planNode;
        return this;
    }
}
