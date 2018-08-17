package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.List;

public class WalkingRouteLine extends RouteLine<WalkingStep> implements Parcelable {
    public static final Creator<WalkingRouteLine> CREATOR = new s();

    public static class WalkingStep extends RouteStep implements Parcelable {
        public static final Creator<WalkingStep> CREATOR = new t();
        private int c;
        private RouteNode d;
        private RouteNode e;
        private String f;
        private String g;
        private String h;
        private String i;

        protected WalkingStep(Parcel parcel) {
            super(parcel);
            this.c = parcel.readInt();
            this.d = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f = parcel.readString();
            this.g = parcel.readString();
            this.h = parcel.readString();
            this.i = parcel.readString();
        }

        void a(int i) {
            this.c = i;
        }

        void a(RouteNode routeNode) {
            this.d = routeNode;
        }

        void a(String str) {
            this.f = str;
        }

        void b(RouteNode routeNode) {
            this.e = routeNode;
        }

        void b(String str) {
            this.g = str;
        }

        void c(String str) {
            this.h = str;
        }

        void d(String str) {
            this.i = str;
        }

        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.c;
        }

        public RouteNode getEntrance() {
            return this.d;
        }

        public String getEntranceInstructions() {
            return this.g;
        }

        public RouteNode getExit() {
            return this.e;
        }

        public String getExitInstructions() {
            return this.h;
        }

        public String getInstructions() {
            return this.i;
        }

        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f);
            }
            return this.mWayPoints;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.c);
            parcel.writeParcelable(this.d, 1);
            parcel.writeParcelable(this.e, 1);
            parcel.writeString(this.f);
            parcel.writeString(this.g);
            parcel.writeString(this.h);
            parcel.writeString(this.i);
        }
    }

    WalkingRouteLine() {
    }

    protected WalkingRouteLine(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public List<WalkingStep> getAllStep() {
        return super.getAllStep();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.setType(TYPE.WALKSTEP);
        super.writeToParcel(parcel, 1);
    }
}
