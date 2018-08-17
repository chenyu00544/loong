package com.baidu.location;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.baidu.location.Address.Builder;
import com.baidu.location.b.f;
import com.baidu.platform.comapi.location.CoordinateType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class BDLocation implements Parcelable, f {
    public static final String BDLOCATION_BD09LL_TO_GCJ02 = "bd09ll2gcj";
    public static final String BDLOCATION_BD09_TO_GCJ02 = "bd092gcj";
    public static final String BDLOCATION_GCJ02_TO_BD09 = "bd09";
    public static final String BDLOCATION_GCJ02_TO_BD09LL = "bd09ll";
    public static final Creator CREATOR = new BDLocation_1();
    public static final int LOCATION_WHERE_IN_CN = 1;
    public static final int LOCATION_WHERE_OUT_CN = 0;
    public static final int LOCATION_WHERE_UNKNOW = 2;
    public static final int OPERATORS_TYPE_MOBILE = 1;
    public static final int OPERATORS_TYPE_TELECOMU = 3;
    public static final int OPERATORS_TYPE_UNICOM = 2;
    public static final int OPERATORS_TYPE_UNKONW = 0;
    public static final int TypeCacheLocation = 65;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerError = 167;
    private int k0;
    private double k1;
    private String k2;
    private boolean k3;
    private float k4;
    private String k5;
    private String k6;
    private int k7;
    private String kD;
    private int kE;
    private double kF;
    private String kG;
    private boolean kH;
    private boolean kI;
    private boolean kJ;
    private String kK;
    private String kL;
    private float kM;
    private String kN;
    private double kO;
    private boolean kP;
    private Address kQ;
    private boolean kR;
    private String kS;
    private String kT;
    private int kU;
    private int kV;
    private float kW;
    private boolean kX;
    private List kY;
    private String kZ;

    final class BDLocation_1 implements Creator {
        BDLocation_1() {
        }

        public BDLocation a(Parcel parcel) {
            return new BDLocation(parcel);
        }

        public BDLocation[] a(int i) {
            return new BDLocation[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    }

    public BDLocation() {
        this.k0 = 0;
        this.k5 = null;
        this.kF = Double.MIN_VALUE;
        this.k1 = Double.MIN_VALUE;
        this.kH = false;
        this.kO = Double.MIN_VALUE;
        this.kI = false;
        this.k4 = 0.0f;
        this.kR = false;
        this.kW = 0.0f;
        this.k3 = false;
        this.kE = -1;
        this.kM = -1.0f;
        this.k6 = null;
        this.kX = false;
        this.kD = null;
        this.kK = null;
        this.kN = null;
        this.k2 = null;
        this.kP = false;
        this.kQ = new Builder().build();
        this.kG = null;
        this.kS = null;
        this.kZ = null;
        this.kJ = false;
        this.kV = 0;
        this.kU = 1;
        this.kT = null;
        this.kL = "";
        this.kY = null;
    }

    private BDLocation(Parcel parcel) {
        this.k0 = 0;
        this.k5 = null;
        this.kF = Double.MIN_VALUE;
        this.k1 = Double.MIN_VALUE;
        this.kH = false;
        this.kO = Double.MIN_VALUE;
        this.kI = false;
        this.k4 = 0.0f;
        this.kR = false;
        this.kW = 0.0f;
        this.k3 = false;
        this.kE = -1;
        this.kM = -1.0f;
        this.k6 = null;
        this.kX = false;
        this.kD = null;
        this.kK = null;
        this.kN = null;
        this.k2 = null;
        this.kP = false;
        this.kQ = new Builder().build();
        this.kG = null;
        this.kS = null;
        this.kZ = null;
        this.kJ = false;
        this.kV = 0;
        this.kU = 1;
        this.kT = null;
        this.kL = "";
        this.kY = null;
        this.k0 = parcel.readInt();
        this.k5 = parcel.readString();
        this.kF = parcel.readDouble();
        this.k1 = parcel.readDouble();
        this.kO = parcel.readDouble();
        this.k4 = parcel.readFloat();
        this.kW = parcel.readFloat();
        this.kE = parcel.readInt();
        this.kM = parcel.readFloat();
        this.kG = parcel.readString();
        this.kV = parcel.readInt();
        this.kS = parcel.readString();
        this.kZ = parcel.readString();
        this.kT = parcel.readString();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        String readString3 = parcel.readString();
        String readString4 = parcel.readString();
        String readString5 = parcel.readString();
        String readString6 = parcel.readString();
        parcel.readString();
        String readString7 = parcel.readString();
        this.kQ = new Builder().country(readString7).countryCode(parcel.readString()).province(readString).city(readString2).cityCode(readString6).district(readString3).street(readString4).streetNumber(readString5).build();
        boolean[] zArr = new boolean[7];
        this.k7 = parcel.readInt();
        this.kL = parcel.readString();
        this.kK = parcel.readString();
        this.kN = parcel.readString();
        this.k2 = parcel.readString();
        this.kU = parcel.readInt();
        try {
            parcel.readBooleanArray(zArr);
            this.kH = zArr[0];
            this.kI = zArr[1];
            this.kR = zArr[2];
            this.k3 = zArr[3];
            this.kX = zArr[4];
            this.kP = zArr[5];
            this.kJ = zArr[6];
        } catch (Exception e) {
        }
        List arrayList = new ArrayList();
        parcel.readList(arrayList, Poi.class.getClassLoader());
        if (arrayList.size() == 0) {
            this.kY = null;
        } else {
            this.kY = arrayList;
        }
    }

    public BDLocation(BDLocation bDLocation) {
        this.k0 = 0;
        this.k5 = null;
        this.kF = Double.MIN_VALUE;
        this.k1 = Double.MIN_VALUE;
        this.kH = false;
        this.kO = Double.MIN_VALUE;
        this.kI = false;
        this.k4 = 0.0f;
        this.kR = false;
        this.kW = 0.0f;
        this.k3 = false;
        this.kE = -1;
        this.kM = -1.0f;
        this.k6 = null;
        this.kX = false;
        this.kD = null;
        this.kK = null;
        this.kN = null;
        this.k2 = null;
        this.kP = false;
        this.kQ = new Builder().build();
        this.kG = null;
        this.kS = null;
        this.kZ = null;
        this.kJ = false;
        this.kV = 0;
        this.kU = 1;
        this.kT = null;
        this.kL = "";
        this.kY = null;
        this.k0 = bDLocation.k0;
        this.k5 = bDLocation.k5;
        this.kF = bDLocation.kF;
        this.k1 = bDLocation.k1;
        this.kH = bDLocation.kH;
        this.kO = bDLocation.kO;
        this.kI = bDLocation.kI;
        this.k4 = bDLocation.k4;
        this.kR = bDLocation.kR;
        this.kW = bDLocation.kW;
        this.k3 = bDLocation.k3;
        this.kE = bDLocation.kE;
        this.kM = bDLocation.kM;
        this.k6 = bDLocation.k6;
        this.kX = bDLocation.kX;
        this.kD = bDLocation.kD;
        this.kP = bDLocation.kP;
        this.kQ = new Builder().country(bDLocation.kQ.country).countryCode(bDLocation.kQ.countryCode).province(bDLocation.kQ.province).city(bDLocation.kQ.city).cityCode(bDLocation.kQ.cityCode).district(bDLocation.kQ.district).street(bDLocation.kQ.street).streetNumber(bDLocation.kQ.streetNumber).build();
        this.kG = bDLocation.kG;
        this.kS = bDLocation.kS;
        this.kZ = bDLocation.kZ;
        this.kU = bDLocation.kU;
        this.kV = bDLocation.kV;
        this.kJ = bDLocation.kJ;
        this.kT = bDLocation.kT;
        this.k7 = bDLocation.k7;
        this.kL = bDLocation.kL;
        this.kK = bDLocation.kK;
        this.kN = bDLocation.kN;
        this.k2 = bDLocation.k2;
        if (bDLocation.kY == null) {
            this.kY = null;
            return;
        }
        List arrayList = new ArrayList();
        for (int i = 0; i < bDLocation.kY.size(); i++) {
            Poi poi = (Poi) bDLocation.kY.get(i);
            arrayList.add(new Poi(poi.getId(), poi.getName(), poi.getRank()));
        }
        this.kY = arrayList;
    }

    public BDLocation(String str) {
        String str2 = null;
        this.k0 = 0;
        this.k5 = null;
        this.kF = Double.MIN_VALUE;
        this.k1 = Double.MIN_VALUE;
        this.kH = false;
        this.kO = Double.MIN_VALUE;
        this.kI = false;
        this.k4 = 0.0f;
        this.kR = false;
        this.kW = 0.0f;
        this.k3 = false;
        this.kE = -1;
        this.kM = -1.0f;
        this.k6 = null;
        this.kX = false;
        this.kD = null;
        this.kK = null;
        this.kN = null;
        this.k2 = null;
        this.kP = false;
        this.kQ = new Builder().build();
        this.kG = null;
        this.kS = null;
        this.kZ = null;
        this.kJ = false;
        this.kV = 0;
        this.kU = 1;
        this.kT = null;
        this.kL = "";
        this.kY = null;
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                int parseInt = Integer.parseInt(jSONObject2.getString("error"));
                setLocType(parseInt);
                setTime(jSONObject2.getString("time"));
                JSONObject jSONObject3;
                if (parseInt == 61) {
                    jSONObject3 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject3.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject3.getString("radius")));
                    setSpeed(Float.parseFloat(jSONObject3.getString("s")));
                    setDirection(Float.parseFloat(jSONObject3.getString("d")));
                    setSatelliteNumber(Integer.parseInt(jSONObject3.getString("n")));
                    if (jSONObject3.has("h")) {
                        try {
                            setAltitude(jSONObject3.getDouble("h"));
                        } catch (Exception e) {
                        }
                    }
                    try {
                        if (jSONObject3.has("in_cn")) {
                            setLocationWhere(Integer.parseInt(jSONObject3.getString("in_cn")));
                        } else {
                            setLocationWhere(1);
                        }
                    } catch (Exception e2) {
                    }
                    if (this.kU == 0) {
                        setCoorType(CoordinateType.WGS84);
                    } else {
                        setCoorType(CoordinateType.GCJ02);
                    }
                } else if (parseInt == TypeNetWorkLocation) {
                    JSONObject jSONObject4 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject4.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject4.getString("radius")));
                    if (jSONObject4.has("sema")) {
                        Object string;
                        jSONObject2 = jSONObject4.getJSONObject("sema");
                        if (jSONObject2.has("aptag")) {
                            string = jSONObject2.getString("aptag");
                            if (TextUtils.isEmpty(string)) {
                                this.kK = "";
                            } else {
                                this.kK = string;
                            }
                        }
                        if (jSONObject2.has("aptagd")) {
                            JSONArray jSONArray = jSONObject2.getJSONObject("aptagd").getJSONArray("pois");
                            List arrayList = new ArrayList();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject5 = jSONArray.getJSONObject(i);
                                arrayList.add(new Poi(jSONObject5.getString("pid"), jSONObject5.getString("pname"), jSONObject5.getDouble("pr")));
                            }
                            this.kY = arrayList;
                        }
                        if (jSONObject2.has("poiregion")) {
                            string = jSONObject2.getString("poiregion");
                            if (!TextUtils.isEmpty(string)) {
                                this.kN = string;
                            }
                        }
                        if (jSONObject2.has("regular")) {
                            string = jSONObject2.getString("regular");
                            if (!TextUtils.isEmpty(string)) {
                                this.k2 = string;
                            }
                        }
                    }
                    if (jSONObject4.has("addr")) {
                        String[] split = jSONObject4.getString("addr").split(",");
                        int length = split.length;
                        String str3 = length > 0 ? split[0] : null;
                        String str4 = length > 1 ? split[1] : null;
                        String str5 = length > 2 ? split[2] : null;
                        String str6 = length > 3 ? split[3] : null;
                        String str7 = length > 4 ? split[4] : null;
                        String str8 = length > 5 ? split[5] : null;
                        String str9 = length > 6 ? split[6] : null;
                        if (length > 7) {
                            str2 = split[7];
                        }
                        this.kQ = new Builder().country(str9).countryCode(str2).province(str3).city(str4).cityCode(str8).district(str5).street(str6).streetNumber(str7).build();
                        this.kX = true;
                    } else {
                        this.kX = false;
                        setAddrStr(null);
                    }
                    if (jSONObject4.has("floor")) {
                        this.kG = jSONObject4.getString("floor");
                        if (TextUtils.isEmpty(this.kG)) {
                            this.kG = null;
                        }
                    }
                    if (jSONObject4.has("loctp")) {
                        this.kT = jSONObject4.getString("loctp");
                        if (TextUtils.isEmpty(this.kT)) {
                            this.kT = null;
                        }
                    }
                    if (jSONObject4.has("bldgid")) {
                        this.kS = jSONObject4.getString("bldgid");
                        if (TextUtils.isEmpty(this.kS)) {
                            this.kS = null;
                        }
                    }
                    if (jSONObject4.has("bldg")) {
                        this.kZ = jSONObject4.getString("bldg");
                        if (TextUtils.isEmpty(this.kZ)) {
                            this.kZ = null;
                        }
                    }
                    if (jSONObject4.has("ibav")) {
                        str2 = jSONObject4.getString("ibav");
                        if (TextUtils.isEmpty(str2)) {
                            this.kV = 0;
                        } else if (str2.equals("0")) {
                            this.kV = 0;
                        } else {
                            this.kV = Integer.valueOf(str2).intValue();
                        }
                    }
                    try {
                        if (jSONObject4.has("in_cn")) {
                            setLocationWhere(Integer.parseInt(jSONObject4.getString("in_cn")));
                        } else {
                            setLocationWhere(1);
                        }
                    } catch (Exception e3) {
                    }
                    if (this.kU == 0) {
                        setCoorType(CoordinateType.WGS84);
                    } else {
                        setCoorType(CoordinateType.GCJ02);
                    }
                } else if (parseInt == 66 || parseInt == 68) {
                    jSONObject3 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject3.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject3.getString("radius")));
                    if(Boolean.valueOf(Boolean.parseBoolean(jSONObject3.getString("isCellChanged"))));
                    setCoorType(CoordinateType.GCJ02);
                } else if (parseInt == TypeServerError) {
                    setLocationWhere(2);
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                this.k0 = 0;
                this.kX = false;
            }
        }
    }

    private String dD() {
        return this.kN;
    }

    private String dE() {
        return this.kL;
    }

    private String dF() {
        return this.k2;
    }

    private static String dG() {
        return Build.MODEL;
    }

    private void if(Boolean bool) {
        this.kP = bool.booleanValue();
    }

    public int describeContents() {
        return 0;
    }

    public String getAdUrl(String str) {
        String valueOf = String.valueOf(this.kF);
        String valueOf2 = String.valueOf(this.k1);
        String dE = dE();
        return "http://lba.baidu.com/" + "?a=" + Jni.E("ak=" + str + "&" + "lat=" + valueOf + "&" + "lng=" + valueOf2 + "&" + "cu=" + dE + "&" + "mb=" + dG());
    }

    public String getAddrStr() {
        return this.kQ.address;
    }

    public Address getAddress() {
        return this.kQ;
    }

    public double getAltitude() {
        return this.kO;
    }

    public String getBuildingID() {
        return this.kS;
    }

    public String getBuildingName() {
        return this.kZ;
    }

    public String getCity() {
        return this.kQ.city;
    }

    public String getCityCode() {
        return this.kQ.cityCode;
    }

    public String getCoorType() {
        return this.k6;
    }

    public String getCountry() {
        return this.kQ.country;
    }

    public String getCountryCode() {
        return this.kQ.countryCode;
    }

    public float getDerect() {
        return this.kM;
    }

    public float getDirection() {
        return this.kM;
    }

    public String getDistrict() {
        return this.kQ.district;
    }

    public String getFloor() {
        return this.kG;
    }

    public double getLatitude() {
        return this.kF;
    }

    public int getLocType() {
        return this.k0;
    }

    public String getLocationDescribe() {
        return this.kK;
    }

    public int getLocationWhere() {
        return this.kU;
    }

    public double getLongitude() {
        return this.k1;
    }

    public String getNetworkLocationType() {
        return this.kT;
    }

    public int getOperators() {
        return this.k7;
    }

    public List getPoiList() {
        return this.kY;
    }

    public String getProvince() {
        return this.kQ.province;
    }

    public float getRadius() {
        return this.kW;
    }

    public int getSatelliteNumber() {
        this.k3 = true;
        return this.kE;
    }

    public String getSemaAptag() {
        return this.kK;
    }

    public float getSpeed() {
        return this.k4;
    }

    public String getStreet() {
        return this.kQ.street;
    }

    public String getStreetNumber() {
        return this.kQ.streetNumber;
    }

    public String getTime() {
        return this.k5;
    }

    public boolean hasAddr() {
        return this.kX;
    }

    public boolean hasAltitude() {
        return this.kH;
    }

    public boolean hasRadius() {
        return this.kR;
    }

    public boolean hasSateNumber() {
        return this.k3;
    }

    public boolean hasSpeed() {
        return this.kI;
    }

    public void internalSet(int i, String str) {
        if (str != null && i == 0) {
            this.kL = str;
        }
    }

    public boolean isCellChangeFlag() {
        return this.kP;
    }

    public boolean isIndoorLocMode() {
        return this.kJ;
    }

    public int isParkAvailable() {
        return this.kV;
    }

    public void setAddr(Address address) {
        if (address != null) {
            this.kQ = address;
            this.kX = true;
        }
    }

    public void setAddrStr(String str) {
        this.kD = str;
        if (str == null) {
            this.kX = false;
        } else {
            this.kX = true;
        }
    }

    public void setAltitude(double d) {
        this.kO = d;
        this.kH = true;
    }

    public void setBuildingID(String str) {
        this.kS = str;
    }

    public void setBuildingName(String str) {
        this.kZ = str;
    }

    public void setCoorType(String str) {
        this.k6 = str;
    }

    public void setDirection(float f) {
        this.kM = f;
    }

    public void setFloor(String str) {
        this.kG = str;
    }

    public void setIndoorLocMode(boolean z) {
        this.kJ = z;
    }

    public void setLatitude(double d) {
        this.kF = d;
    }

    public void setLocType(int i) {
        this.k0 = i;
    }

    public void setLocationDescribe(String str) {
        this.kK = str;
    }

    public void setLocationWhere(int i) {
        this.kU = i;
    }

    public void setLongitude(double d) {
        this.k1 = d;
    }

    public void setNetworkLocationType(String str) {
        this.kT = str;
    }

    public void setOperators(int i) {
        this.k7 = i;
    }

    public void setParkAvailable(int i) {
        this.kV = i;
    }

    public void setPoiList(List list) {
        this.kY = list;
    }

    public void setRadius(float f) {
        this.kW = f;
        this.kR = true;
    }

    public void setSatelliteNumber(int i) {
        this.kE = i;
    }

    public void setSpeed(float f) {
        this.k4 = f;
        this.kI = true;
    }

    public void setTime(String str) {
        this.k5 = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.k0);
        parcel.writeString(this.k5);
        parcel.writeDouble(this.kF);
        parcel.writeDouble(this.k1);
        parcel.writeDouble(this.kO);
        parcel.writeFloat(this.k4);
        parcel.writeFloat(this.kW);
        parcel.writeInt(this.kE);
        parcel.writeFloat(this.kM);
        parcel.writeString(this.kG);
        parcel.writeInt(this.kV);
        parcel.writeString(this.kS);
        parcel.writeString(this.kZ);
        parcel.writeString(this.kT);
        parcel.writeString(this.kQ.province);
        parcel.writeString(this.kQ.city);
        parcel.writeString(this.kQ.district);
        parcel.writeString(this.kQ.street);
        parcel.writeString(this.kQ.streetNumber);
        parcel.writeString(this.kQ.cityCode);
        parcel.writeString(this.kQ.address);
        parcel.writeString(this.kQ.country);
        parcel.writeString(this.kQ.countryCode);
        parcel.writeInt(this.k7);
        parcel.writeString(this.kL);
        parcel.writeString(this.kK);
        parcel.writeString(this.kN);
        parcel.writeString(this.k2);
        parcel.writeInt(this.kU);
        parcel.writeBooleanArray(new boolean[]{this.kH, this.kI, this.kR, this.k3, this.kX, this.kP, this.kJ});
        parcel.writeList(this.kY);
    }
}
