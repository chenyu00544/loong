package com.baidu.location.c;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.baidu.location.Address;
import com.baidu.location.Address.Builder;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.b.b;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.h.h;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

final class c implements b {
    private static final String A = "-des";
    private static final String B = "CityCode";
    private static final String C = "City";
    private static final String[] D = new String[]{F, "Time", X, W, N, T, E, L, Q, z, C, B, P, y, V, G, O};
    private static final String E = "NetworkLocationType";
    private static final String F = "CoorType";
    private static final String G = "PoiList";
    private static final SimpleDateFormat H = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final String I = "-minap";
    private static final String J = "-rgc";
    private static final String K = "true";
    private static final String L = "Country";
    private static final String M = "-com";
    private static final String N = "Latitude";
    private static final String O = "LocationDescription";
    private static final String P = "District";
    private static final String Q = "CountryCode";
    private static final String R = "gcj02";
    private static final String S = "Time";
    private static final String T = "Radius";
    private static final String U = "-log";
    private static final String V = "StreetNumber";
    private static final String W = "Longitude";
    private static final String X = "LocType";
    private static final String Y = "-loc";
    private static final String Z = "-poi";
    private static final String aa = "%f;%f;%d;%s";
    private static final String y = "Street";
    private static final String z = "Province";

    static final class a {
        private static final String a = "cl=";
        private static final String case = "wf=";
        final boolean byte;
        final boolean char;
        final int do;
        final String else;
        final String for;
        final LinkedHashMap if;
        final boolean int;
        final boolean new;
        final BDLocation try;

        public a(String[] strArr) {
            if (strArr == null) {
                this.else = null;
                this.for = null;
                this.if = null;
                this.new = false;
                this.int = false;
                this.byte = false;
                this.try = null;
                this.char = false;
                this.do = 8;
                return;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            int i2 = 8;
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            Object obj = null;
            BDLocation bDLocation = null;
            String str = null;
            String str2 = null;
            while (i < strArr.length) {
                if (strArr[i].equals(c.Y)) {
                    str2 = strArr[i + 1];
                    String[] split = str2.split("&");
                    for (int i3 = 0; i3 < split.length; i3++) {
                        if (split[i3].startsWith(a)) {
                            str = split[i3].substring(3);
                        } else if (split[i3].startsWith(case)) {
                            String[] split2 = split[i3].substring(3).split("\\|");
                            for (String split3 : split2) {
                                String[] split4 = split3.split(";");
                                if (split4.length >= 2) {
                                    linkedHashMap.put(split4[0], Integer.valueOf(split4[1]));
                                }
                            }
                        }
                    }
                } else if (strArr[i].equals(c.M)) {
                    String[] split5 = strArr[i + 1].split(";");
                    if (split5.length > 0) {
                        BDLocation bDLocation2 = new BDLocation();
                        try {
                            bDLocation2.setLatitude(Double.valueOf(split5[0]).doubleValue());
                            bDLocation2.setLongitude(Double.valueOf(split5[1]).doubleValue());
                            bDLocation2.setLocType(Integer.valueOf(split5[2]).intValue());
                            bDLocation2.setNetworkLocationType(split5[3]);
                            bDLocation = bDLocation2;
                        } catch (Exception e) {
                            bDLocation = bDLocation2;
                        }
                    } else {
                        continue;
                    }
                } else if (strArr[i].equals(c.U)) {
                    if (strArr[i + 1].equals(c.K)) {
                        obj = 1;
                    }
                } else if (strArr[i].equals(c.J)) {
                    if (strArr[i + 1].equals(c.K)) {
                        z2 = true;
                    }
                } else if (strArr[i].equals(c.Z)) {
                    if (strArr[i + 1].equals(c.K)) {
                        z3 = true;
                    }
                } else if (strArr[i].equals(c.I)) {
                    try {
                        i2 = Integer.valueOf(strArr[i + 1]).intValue();
                    } catch (Exception e2) {
                    }
                } else {
                    try {
                        if (strArr[i].equals(c.A) && strArr[i + 1].equals(c.K)) {
                            z = true;
                        }
                    } catch (Exception e3) {
                    }
                }
                i += 2;
            }
            if (obj == null) {
                str2 = null;
            }
            boolean z4 = true;
            this.else = str2;
            this.for = str;
            this.if = linkedHashMap;
            this.new = z4;
            this.int = z3;
            this.byte = z2;
            this.do = i2;
            this.try = bDLocation;
            this.char = z;
            z4 = false;
            this.else = str2;
            this.for = str;
            this.if = linkedHashMap;
            this.new = z4;
            this.int = z3;
            this.byte = z2;
            this.do = i2;
            this.try = bDLocation;
            this.char = z;
        }
    }

    private c() {
        throw new AssertionError();
    }

    static Cursor if(BDLocation bDLocation) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String format = H.format(new Date(System.currentTimeMillis()));
        Object matrixCursor = new MatrixCursor(D);
        Object[] objArr = new Object[D.length];
        objArr[matrixCursor.getColumnIndex(F)] = "gcj02";
        objArr[matrixCursor.getColumnIndex("Time")] = format;
        objArr[matrixCursor.getColumnIndex(X)] = Integer.valueOf(bDLocation.getLocType());
        objArr[matrixCursor.getColumnIndex(W)] = Double.valueOf(bDLocation.getLongitude());
        objArr[matrixCursor.getColumnIndex(N)] = Double.valueOf(bDLocation.getLatitude());
        objArr[matrixCursor.getColumnIndex(T)] = Float.valueOf(bDLocation.getRadius());
        objArr[matrixCursor.getColumnIndex(E)] = bDLocation.getNetworkLocationType();
        Address address = bDLocation.getAddress();
        if (address != null) {
            str = address.country;
            str2 = address.countryCode;
            str3 = address.province;
            str4 = address.city;
            str5 = address.cityCode;
            str6 = address.district;
            str7 = address.street;
            format = address.streetNumber;
        } else {
            format = null;
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
        }
        objArr[matrixCursor.getColumnIndex(L)] = str;
        objArr[matrixCursor.getColumnIndex(Q)] = str2;
        objArr[matrixCursor.getColumnIndex(z)] = str3;
        objArr[matrixCursor.getColumnIndex(C)] = str4;
        objArr[matrixCursor.getColumnIndex(B)] = str5;
        objArr[matrixCursor.getColumnIndex(P)] = str6;
        objArr[matrixCursor.getColumnIndex(y)] = str7;
        objArr[matrixCursor.getColumnIndex(V)] = format;
        List poiList = bDLocation.getPoiList();
        if (poiList == null) {
            objArr[matrixCursor.getColumnIndex(G)] = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < poiList.size(); i++) {
                Poi poi = (Poi) poiList.get(i);
                stringBuffer.append(poi.getId()).append(";").append(poi.getName()).append(";").append(poi.getRank()).append(";|");
            }
            objArr[matrixCursor.getColumnIndex(G)] = stringBuffer.toString();
        }
        objArr[matrixCursor.getColumnIndex(O)] = bDLocation.getLocationDescribe();
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    static BDLocation if(Cursor cursor) {
        BDLocation bDLocation = new BDLocation();
        if (cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
            bDLocation.setLocType(67);
        } else {
            Integer num = null;
            Double d = null;
            Double d2 = null;
            String str = null;
            String str2 = null;
            Float f = null;
            String str3 = null;
            if (cursor.getColumnIndex(X) != -1) {
                num = Integer.valueOf(cursor.getInt(cursor.getColumnIndex(X)));
            }
            if (cursor.getColumnIndex(N) != -1) {
                d = Double.valueOf(cursor.getDouble(cursor.getColumnIndex(N)));
            }
            if (cursor.getColumnIndex(W) != -1) {
                d2 = Double.valueOf(cursor.getDouble(cursor.getColumnIndex(W)));
            }
            if (cursor.getColumnIndex(F) != -1) {
                str = cursor.getString(cursor.getColumnIndex(F));
            }
            if (cursor.getColumnIndex(E) != -1) {
                str2 = cursor.getString(cursor.getColumnIndex(E));
            }
            if (cursor.getColumnIndex(T) != -1) {
                f = Float.valueOf(cursor.getFloat(cursor.getColumnIndex(T)));
            }
            if (cursor.getColumnIndex("Time") != -1) {
                str3 = cursor.getString(cursor.getColumnIndex("Time"));
            }
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            String str11 = null;
            if (cursor.getColumnIndex(L) != -1) {
                str4 = cursor.getString(cursor.getColumnIndex(L));
            }
            if (cursor.getColumnIndex(Q) != -1) {
                str5 = cursor.getString(cursor.getColumnIndex(Q));
            }
            if (cursor.getColumnIndex(z) != -1) {
                str6 = cursor.getString(cursor.getColumnIndex(z));
            }
            if (cursor.getColumnIndex(C) != -1) {
                str7 = cursor.getString(cursor.getColumnIndex(C));
            }
            if (cursor.getColumnIndex(B) != -1) {
                str8 = cursor.getString(cursor.getColumnIndex(B));
            }
            if (cursor.getColumnIndex(P) != -1) {
                str9 = cursor.getString(cursor.getColumnIndex(P));
            }
            if (cursor.getColumnIndex(y) != -1) {
                str10 = cursor.getString(cursor.getColumnIndex(y));
            }
            if (cursor.getColumnIndex(V) != -1) {
                str11 = cursor.getString(cursor.getColumnIndex(V));
            }
            Address build = new Builder().country(str4).countryCode(str5).province(str6).city(str7).cityCode(str8).district(str9).street(str10).streetNumber(str11).build();
            List list = null;
            if (cursor.getColumnIndex(G) != -1) {
                list = new ArrayList();
                str5 = cursor.getString(cursor.getColumnIndex(G));
                if (str5 != null) {
                    try {
                        String[] split = str5.split("\\|");
                        for (String str82 : split) {
                            String[] split2 = str82.split(";");
                            if (split2.length >= 3) {
                                list.add(new Poi(split2[0], split2[1], Double.valueOf(split2[2]).doubleValue()));
                            }
                        }
                    } catch (Exception e) {
                        if (list.size() == 0) {
                            list = null;
                        }
                    } catch (Throwable th) {
                        if (list.size() == 0) {
                        }
                    }
                }
                if (list.size() == 0) {
                    list = null;
                }
            }
            str5 = null;
            if (cursor.getColumnIndex(O) != -1) {
                str5 = cursor.getString(cursor.getColumnIndex(O));
            }
            bDLocation.setTime(str3);
            bDLocation.setRadius(f.floatValue());
            bDLocation.setLocType(num.intValue());
            bDLocation.setCoorType(str);
            bDLocation.setLatitude(d.doubleValue());
            bDLocation.setLongitude(d2.doubleValue());
            bDLocation.setNetworkLocationType(str2);
            bDLocation.setAddr(build);
            bDLocation.setPoiList(list);
            bDLocation.setLocationDescribe(str5);
        }
        return bDLocation;
    }

    static String if(BDLocation bDLocation, int i) {
        if (bDLocation == null || bDLocation.getLocType() == 67) {
            return String.format(Locale.CHINA, "&ofl=%s|%d", new Object[]{"1", Integer.valueOf(i)});
        }
        String format = String.format(Locale.CHINA, "&ofl=%s|%d|%f|%f|%d", new Object[]{"1", Integer.valueOf(i), Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius())});
        String str = bDLocation.getAddress() != null ? format + "&ofaddr=" + bDLocation.getAddress().address : format;
        if (bDLocation.getPoiList() != null && bDLocation.getPoiList().size() > 0) {
            Poi poi = (Poi) bDLocation.getPoiList().get(0);
            str = str + String.format(Locale.US, "&ofpoi=%s|%s", new Object[]{poi.getId(), poi.getName()});
        }
        if (com.baidu.location.b.c.bn == null) {
            return str;
        }
        return str + String.format(Locale.US, "&pack=%s&sdk=%.3f", new Object[]{com.baidu.location.b.c.bn, Float.valueOf(f.bi)});
    }

    static String if(BDLocation bDLocation, BDLocation bDLocation2, a aVar) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bDLocation2 == null) {
            stringBuffer.append("&ofcl=0");
        } else {
            stringBuffer.append(String.format(Locale.US, "&ofcl=1|%f|%f|%d", new Object[]{Double.valueOf(bDLocation2.getLongitude()), Double.valueOf(bDLocation2.getLatitude()), Integer.valueOf((int) bDLocation2.getRadius())}));
        }
        if (bDLocation == null) {
            stringBuffer.append("&ofwf=0");
        } else {
            stringBuffer.append(String.format(Locale.US, "&ofwf=1|%f|%f|%d", new Object[]{Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius())}));
        }
        if (aVar == null || !aVar.byte) {
            stringBuffer.append("&rgcn=0");
        } else {
            stringBuffer.append("&rgcn=1");
        }
        if (aVar == null || !aVar.int) {
            stringBuffer.append("&poin=0");
        } else {
            stringBuffer.append("&poin=1");
        }
        if (aVar == null || !aVar.char) {
            stringBuffer.append("&desc=0");
        } else {
            stringBuffer.append("&desc=1");
        }
        if (aVar != null) {
            stringBuffer.append(String.format(Locale.US, "&aps=%d", new Object[]{Integer.valueOf(aVar.do)}));
        }
        return stringBuffer.toString();
    }

    static String[] if(h hVar, com.baidu.location.h.f fVar, BDLocation bDLocation, String str, boolean z, int i) {
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        if (hVar != null) {
            stringBuffer.append(com.baidu.location.h.b.cV().new(hVar));
        }
        if (fVar != null) {
            stringBuffer.append(fVar.e(30));
        }
        if (stringBuffer.length() > 0) {
            if (str != null) {
                stringBuffer.append(str);
            }
            arrayList.add(Y);
            arrayList.add(stringBuffer.toString());
        }
        if (bDLocation != null) {
            String format = String.format(Locale.US, aa, new Object[]{Double.valueOf(bDLocation.getLatitude()), Double.valueOf(bDLocation.getLongitude()), Integer.valueOf(bDLocation.getLocType()), bDLocation.getNetworkLocationType()});
            arrayList.add(M);
            arrayList.add(format);
        }
        if (z) {
            arrayList.add(U);
            arrayList.add(K);
        }
        if (k.cf.equals("all")) {
            arrayList.add(J);
            arrayList.add(K);
        }
        if (k.bP) {
            arrayList.add(Z);
            arrayList.add(K);
        }
        if (k.bX) {
            arrayList.add(A);
            arrayList.add(K);
        }
        arrayList.add(I);
        arrayList.add(Integer.toString(i));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }
}
