package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

public class BikingRouteLine extends RouteLine<BikingStep> implements Parcelable {
    public static final Creator<BikingRouteLine> CREATOR = new a();

    public static class BikingStep extends RouteStep implements Parcelable {
        public static final Creator<BikingStep> CREATOR = new b();
        private int c;
        private RouteNode d;
        private RouteNode e;
        private String f;
        private String g;
        private String h;
        private String i;

        protected BikingStep(Parcel parcel) {
            super(parcel);
            this.c = parcel.readInt();
            this.d = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f = parcel.readString();
            this.g = parcel.readString();
            this.h = parcel.readString();
            this.i = parcel.readString();
        }

        private List<LatLng> e(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            List<LatLng> arrayList = new ArrayList();
            String[] split = str.split(";");
            if (split == null || split.length == 0) {
                return null;
            }
            for (String split2 : split) {
                String[] split3 = split2.split(",");
                if (split3 != null && split3.length >= 2) {
                    arrayList.add(new LatLng(Double.valueOf(split3[1]).doubleValue(), Double.valueOf(split3[0]).doubleValue()));
                }
            }
            return arrayList;
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
                this.mWayPoints = e(this.f);
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

    BikingRouteLine() {
    }

    protected BikingRouteLine(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public List<BikingStep> getAllStep() {
        return super.getAllStep();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.setType(TYPE.BIKINGSTEP);
        super.writeToParcel(parcel, 1);
    }
}
