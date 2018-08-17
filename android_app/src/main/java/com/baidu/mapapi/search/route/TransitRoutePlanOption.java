package com.baidu.mapapi.search.route;

public class TransitRoutePlanOption {
    PlanNode a = null;
    PlanNode b = null;
    String c = null;
    TransitPolicy d = TransitPolicy.EBUS_TIME_FIRST;

    public enum TransitPolicy {
        EBUS_TIME_FIRST(3),
        EBUS_TRANSFER_FIRST(4),
        EBUS_WALK_FIRST(5),
        EBUS_NO_SUBWAY(6);
        
        private int a;

        private TransitPolicy(int i) {
            this.a = 0;
            this.a = i;
        }

        public int getInt() {
            return this.a;
        }
    }

    public TransitRoutePlanOption city(String str) {
        this.c = str;
        return this;
    }

    public TransitRoutePlanOption from(PlanNode planNode) {
        this.a = planNode;
        return this;
    }

    public TransitRoutePlanOption policy(TransitPolicy transitPolicy) {
        this.d = transitPolicy;
        return this;
    }

    public TransitRoutePlanOption to(PlanNode planNode) {
        this.b = planNode;
        return this;
    }
}
