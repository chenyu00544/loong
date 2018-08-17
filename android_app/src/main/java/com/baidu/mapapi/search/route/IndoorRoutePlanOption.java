package com.baidu.mapapi.search.route;

public class IndoorRoutePlanOption {
    IndoorPlanNode a = null;
    IndoorPlanNode b = null;

    public IndoorRoutePlanOption from(IndoorPlanNode indoorPlanNode) {
        this.a = indoorPlanNode;
        return this;
    }

    public IndoorRoutePlanOption to(IndoorPlanNode indoorPlanNode) {
        this.b = indoorPlanNode;
        return this;
    }
}
