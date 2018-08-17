package com.baidu.mapapi.search.route;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.BusInfo;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.CoachInfo;
import com.baidu.mapapi.search.core.PlaneInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.PriceInfo;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import com.baidu.mapapi.search.core.VehicleInfo;
import com.baidu.mapapi.search.core.k;
import com.baidu.mapapi.search.route.BikingRouteLine.BikingStep;
import com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep;
import com.baidu.mapapi.search.route.IndoorRouteLine.IndoorRouteStep;
import com.baidu.mapapi.search.route.IndoorRouteLine.IndoorRouteStep.IndoorStepNode;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep.StepVehicleInfoType;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep.TrafficCondition;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class n {
    private static TransitStep a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        TransitStep transitStep = new TransitStep();
        transitStep.setDistance((int) jSONObject.optDouble("distance"));
        transitStep.setDuration((int) jSONObject.optDouble("duration"));
        transitStep.a(jSONObject.optString("instructions"));
        transitStep.b(jSONObject.optString("path"));
        transitStep.setTrafficConditions(b(jSONObject.optJSONArray("traffic_condition")));
        JSONObject optJSONObject = jSONObject.optJSONObject("start_location");
        transitStep.a(new LatLng(optJSONObject.optDouble("lat"), optJSONObject.optDouble("lng")));
        optJSONObject = jSONObject.optJSONObject("end_location");
        transitStep.b(new LatLng(optJSONObject.optDouble("lat"), optJSONObject.optDouble("lng")));
        optJSONObject = jSONObject.optJSONObject("vehicle_info");
        int optInt = optJSONObject.optInt("type");
        optJSONObject = optJSONObject.optJSONObject("detail");
        switch (optInt) {
            case 1:
                transitStep.a(StepVehicleInfoType.ESTEP_TRAIN);
                if (optJSONObject == null) {
                    return transitStep;
                }
                k kVar = new k();
                kVar.setName(optJSONObject.optString("name"));
                kVar.a(optJSONObject.optDouble("price"));
                kVar.a(optJSONObject.optString("booking"));
                kVar.setDepartureStation(optJSONObject.optString("departure_station"));
                kVar.setArriveStation(optJSONObject.optString("arrive_station"));
                kVar.setDepartureTime(optJSONObject.optString("departure_time"));
                kVar.setArriveTime(optJSONObject.optString("arrive_time"));
                transitStep.setTrainInfo(kVar);
                return transitStep;
            case 2:
                transitStep.a(StepVehicleInfoType.ESTEP_PLANE);
                if (optJSONObject == null) {
                    return transitStep;
                }
                PlaneInfo planeInfo = new PlaneInfo();
                planeInfo.setName(optJSONObject.optString("name"));
                planeInfo.setPrice(optJSONObject.optDouble("price"));
                planeInfo.setDiscount(optJSONObject.optDouble("discount"));
                planeInfo.setAirlines(optJSONObject.optString("airlines"));
                planeInfo.setBooking(optJSONObject.optString("booking"));
                planeInfo.setDepartureStation(optJSONObject.optString("departure_station"));
                planeInfo.setArriveStation(optJSONObject.optString("arrive_station"));
                planeInfo.setDepartureTime(optJSONObject.optString("departure_time"));
                planeInfo.setArriveTime(optJSONObject.optString("arrive_time"));
                transitStep.setPlaneInfo(planeInfo);
                return transitStep;
            case 3:
                transitStep.a(StepVehicleInfoType.ESTEP_BUS);
                if (optJSONObject == null) {
                    return transitStep;
                }
                BusInfo busInfo = new BusInfo();
                busInfo.setName(optJSONObject.optString("name"));
                busInfo.setType(optJSONObject.optInt("type"));
                busInfo.setStopNum(optJSONObject.optInt("stop_num"));
                busInfo.setDepartureStation(optJSONObject.optString("on_station"));
                busInfo.setArriveStation(optJSONObject.optString("off_station"));
                busInfo.setDepartureTime(optJSONObject.optString("first_time"));
                busInfo.setArriveTime(optJSONObject.optString("last_time"));
                transitStep.setBusInfo(busInfo);
                return transitStep;
            case 4:
                transitStep.a(StepVehicleInfoType.ESTEP_DRIVING);
                return transitStep;
            case 5:
                transitStep.a(StepVehicleInfoType.ESTEP_WALK);
                return transitStep;
            case 6:
                transitStep.a(StepVehicleInfoType.ESTEP_COACH);
                if (optJSONObject == null) {
                    return transitStep;
                }
                CoachInfo coachInfo = new CoachInfo();
                coachInfo.setName(optJSONObject.optString("name"));
                coachInfo.setPrice(optJSONObject.optDouble("price"));
                coachInfo.setBooking(optJSONObject.optString("booking"));
                coachInfo.setProviderName(optJSONObject.optString("provider_name"));
                coachInfo.setProviderUrl(optJSONObject.optString("provider_url"));
                coachInfo.setDepartureStation(optJSONObject.optString("departure_station"));
                coachInfo.setArriveStation(optJSONObject.optString("arrive_station"));
                coachInfo.setDepartureTime(optJSONObject.optString("departure_time"));
                coachInfo.setArriveTime(optJSONObject.optString("arrive_time"));
                transitStep.setCoachInfo(coachInfo);
                return transitStep;
            default:
                return transitStep;
        }
    }

    public static TransitRouteResult a(String str) {
        TransitRouteResult transitRouteResult = new TransitRouteResult();
        if (str == null || str.length() <= 0) {
            transitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return transitRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optJSONObject("taxi") != null) {
                transitRouteResult.a(h(jSONObject.optString("taxi")));
            }
            RouteNode c = c(jSONObject, "start_point");
            RouteNode c2 = c(jSONObject, "end_point");
            JSONArray optJSONArray = jSONObject.optJSONArray("routes");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                transitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
                return transitRouteResult;
            }
            List arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                jSONObject = ((JSONObject) optJSONArray.opt(i)).optJSONObject("legs");
                if (jSONObject != null) {
                    TransitRouteLine transitRouteLine = new TransitRouteLine();
                    transitRouteLine.setDistance(jSONObject.optInt("distance"));
                    transitRouteLine.setDuration(jSONObject.optInt("time"));
                    transitRouteLine.setStarting(c);
                    transitRouteLine.setTerminal(c2);
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("steps");
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        List arrayList2 = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            JSONArray optJSONArray3 = optJSONArray2.optJSONObject(i2).optJSONArray("busline");
                            if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                                JSONObject optJSONObject = optJSONArray3.optJSONObject(0);
                                TransitRouteLine.TransitStep transitStep = new TransitRouteLine.TransitStep();
                                transitStep.a(RouteNode.location(CoordUtil.decodeLocation(optJSONObject.optString("start_location"))));
                                transitStep.b(RouteNode.location(CoordUtil.decodeLocation(optJSONObject.optString("end_location"))));
                                if (optJSONObject.optInt("type") == 5) {
                                    transitStep.a(TransitRouteStepType.WAKLING);
                                } else {
                                    transitStep.a(TransitRouteStepType.WAKLING);
                                }
                                transitStep.a(k(optJSONObject.optString("instructions")));
                                transitStep.setDistance(optJSONObject.optInt("distance"));
                                transitStep.setDuration(optJSONObject.optInt("duration"));
                                transitStep.b(optJSONObject.optString("path_geo"));
                                if (optJSONObject.has("vehicle")) {
                                    transitStep.a(i(optJSONObject.optString("vehicle")));
                                    optJSONObject = optJSONObject.optJSONObject("vehicle");
                                    transitStep.getEntrance().setUid(optJSONObject.optString("start_uid"));
                                    transitStep.getEntrance().setTitle(optJSONObject.optString("start_name"));
                                    transitStep.getExit().setUid(optJSONObject.optString("end_uid"));
                                    transitStep.getExit().setTitle(optJSONObject.optString("end_name"));
                                    Integer valueOf = Integer.valueOf(optJSONObject.optInt("type"));
                                    if (valueOf == null) {
                                        transitStep.a(TransitRouteStepType.BUSLINE);
                                    } else if (valueOf.intValue() == 1) {
                                        transitStep.a(TransitRouteStepType.SUBWAY);
                                    } else {
                                        transitStep.a(TransitRouteStepType.BUSLINE);
                                    }
                                }
                                arrayList2.add(transitStep);
                            }
                        }
                        transitRouteLine.setSteps(arrayList2);
                        arrayList.add(transitRouteLine);
                    }
                }
            }
            transitRouteResult.a(arrayList);
            return transitRouteResult;
        } catch (JSONException e) {
            transitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            e.printStackTrace();
            return transitRouteResult;
        }
    }

    private static List<List<TransitStep>> a(JSONArray jSONArray) {
        List<List<TransitStep>> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() < 0) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                JSONArray optJSONArray = optJSONObject.optJSONArray("scheme");
                List arrayList2 = new ArrayList();
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            arrayList2.add(a(optJSONObject2));
                        }
                    }
                }
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    public static List<CityInfo> a(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || str.equals("")) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.num = jSONObject2.optInt("num");
                cityInfo.city = jSONObject2.optString("name");
                arrayList.add(cityInfo);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    private static List<PoiInfo> a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            List<PoiInfo> arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                if (jSONObject2 != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    PlanNode.withCityNameAndPlaceName(str2, jSONObject2.optString("name"));
                    poiInfo.address = jSONObject2.optString("addr");
                    poiInfo.uid = jSONObject2.optString("uid");
                    poiInfo.name = jSONObject2.optString("name");
                    poiInfo.location = CoordUtil.decodeLocation(jSONObject2.optString("geo"));
                    poiInfo.city = str2;
                    arrayList.add(poiInfo);
                }
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
        }
        return null;
    }

    private static TransitResultNode b(JSONObject jSONObject) {
        LatLng latLng = null;
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("wd");
        String optString2 = jSONObject.optString("city_name");
        int optInt = jSONObject.optInt("city_code");
        JSONObject optJSONObject = jSONObject.optJSONObject("location");
        if (optJSONObject != null) {
            latLng = new LatLng(optJSONObject.optDouble("lat"), optJSONObject.optDouble("lng"));
        }
        return new TransitResultNode(optInt, optString2, latLng, optString);
    }

    public static MassTransitRouteResult b(String str) {
        MassTransitRouteResult massTransitRouteResult = new MassTransitRouteResult();
        if (str == null || str.length() <= 0) {
            massTransitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return massTransitRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("type");
            jSONObject = jSONObject.optJSONObject("result");
            if (jSONObject != null) {
                if (optInt == 1) {
                    massTransitRouteResult.a(b(jSONObject.optJSONObject("origin_info")));
                    massTransitRouteResult.b(b(jSONObject.optJSONObject("destination_info")));
                    massTransitRouteResult.a(c(jSONObject));
                    massTransitRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
                } else if (optInt == 2) {
                    TransitResultNode b = b(jSONObject.optJSONObject("origin"));
                    massTransitRouteResult.a(b);
                    TransitResultNode b2 = b(jSONObject.optJSONObject("destination"));
                    massTransitRouteResult.b(b2);
                    massTransitRouteResult.a(jSONObject.optInt("total"));
                    massTransitRouteResult.setTaxiInfo(h(jSONObject.optString("taxi")));
                    JSONArray optJSONArray = jSONObject.optJSONArray("routes");
                    if (optJSONArray == null || optJSONArray.length() <= 0) {
                        massTransitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
                        return massTransitRouteResult;
                    }
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        MassTransitRouteLine massTransitRouteLine = new MassTransitRouteLine();
                        massTransitRouteLine.setDistance(optJSONObject.optInt("distance"));
                        massTransitRouteLine.setDuration(optJSONObject.optInt("duration"));
                        massTransitRouteLine.a(optJSONObject.optString("arrive_time"));
                        massTransitRouteLine.a(optJSONObject.optDouble("price"));
                        massTransitRouteLine.a(c(optJSONObject.optJSONArray("price_detail")));
                        RouteNode routeNode = new RouteNode();
                        routeNode.setLocation(b.getLocation());
                        massTransitRouteLine.setStarting(routeNode);
                        routeNode = new RouteNode();
                        routeNode.setLocation(b2.getLocation());
                        massTransitRouteLine.setTerminal(routeNode);
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("steps");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            massTransitRouteLine.setNewSteps(a(optJSONArray2));
                            arrayList.add(massTransitRouteLine);
                        }
                    }
                    massTransitRouteResult.a(arrayList);
                    massTransitRouteResult.error = ERRORNO.NO_ERROR;
                }
                return massTransitRouteResult;
            }
            massTransitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return massTransitRouteResult;
        } catch (JSONException e) {
            massTransitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            e.printStackTrace();
            return massTransitRouteResult;
        }
    }

    private static List<TrafficCondition> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 0) {
            return null;
        }
        List<TrafficCondition> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                TrafficCondition trafficCondition = new TrafficCondition();
                trafficCondition.setTrafficStatus(optJSONObject.optInt("status"));
                trafficCondition.setTrafficGeoCnt(optJSONObject.optInt("geo_cnt"));
                arrayList.add(trafficCondition);
            }
        }
        return arrayList;
    }

    public static List<CityInfo> b(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || str.equals("")) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.num = jSONObject2.optInt("number");
                cityInfo.city = jSONObject2.optString("name");
                arrayList.add(cityInfo);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    private static List<PoiInfo> b(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            List<PoiInfo> arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                if (jSONObject2 != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    if (jSONObject2.has("address")) {
                        poiInfo.address = jSONObject2.optString("address");
                    }
                    poiInfo.uid = jSONObject2.optString("uid");
                    poiInfo.name = jSONObject2.optString("name");
                    poiInfo.location = new LatLng(jSONObject2.optDouble("lat"), jSONObject2.optDouble("lng"));
                    poiInfo.city = str2;
                    arrayList.add(poiInfo);
                }
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
        }
        return null;
    }

    private static RouteNode c(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(optJSONObject.optString("name"));
        routeNode.setUid(optJSONObject.optString("uid"));
        routeNode.setLocation(CoordUtil.decodeLocation(optJSONObject.optString("geo")));
        return routeNode;
    }

    public static DrivingRouteResult c(String str) {
        DrivingRouteResult drivingRouteResult = new DrivingRouteResult();
        if (str == null || "".equals(str)) {
            drivingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return drivingRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            List arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("routes");
            if (optJSONArray == null) {
                return drivingRouteResult;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                DrivingRouteLine drivingRouteLine = new DrivingRouteLine();
                drivingRouteLine.setStarting(e(jSONObject, "start_point"));
                drivingRouteLine.setTerminal(e(jSONObject, "end_point"));
                drivingRouteLine.a(f(jSONObject, "waypoints"));
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    optJSONObject = optJSONObject.optJSONObject("legs");
                    if (optJSONObject == null) {
                        return drivingRouteResult;
                    }
                    drivingRouteLine.setDistance(optJSONObject.optInt("distance"));
                    drivingRouteLine.setDuration(optJSONObject.optInt("duration"));
                    drivingRouteLine.setCongestionDistance(optJSONObject.optInt("congestion_length"));
                    drivingRouteLine.setLightNum(optJSONObject.optInt("light_num"));
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("steps");
                    if (optJSONArray2 == null) {
                        return drivingRouteResult;
                    }
                    List arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        DrivingStep drivingStep = new DrivingStep();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            JSONArray optJSONArray3 = optJSONObject2.optJSONArray("paths");
                            if (optJSONArray3 == null) {
                                return drivingRouteResult;
                            }
                            if (optJSONArray3.length() >= 2) {
                                List arrayList3 = new ArrayList();
                                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                                    JSONObject optJSONObject3 = optJSONArray3.optJSONObject(i3);
                                    GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
                                    geoPoint.setLongitudeE6((double) optJSONObject3.optInt("loc_x"));
                                    geoPoint.setLatitudeE6((double) optJSONObject3.optInt("loc_y"));
                                    if (i3 == 0) {
                                        drivingStep.a(RouteNode.location(CoordUtil.mc2ll(geoPoint)));
                                    } else if (i3 == optJSONArray3.length() - 1) {
                                        drivingStep.b(RouteNode.location(CoordUtil.mc2ll(geoPoint)));
                                    }
                                    arrayList3.add(CoordUtil.mc2ll(geoPoint));
                                }
                                drivingStep.a(arrayList3);
                            }
                            JSONArray optJSONArray4 = optJSONObject2.optJSONArray("traffics");
                            if (optJSONArray4 != null && optJSONArray4.length() > 0) {
                                int length = optJSONArray4.length();
                                int[] iArr = new int[length];
                                for (int i4 = 0; i4 < length; i4++) {
                                    iArr[i4] = Integer.parseInt((String) optJSONArray4.opt(i4));
                                }
                                drivingStep.a(iArr);
                            }
                            drivingStep.a(optJSONObject2.optInt("direction") * 30);
                            drivingStep.setDistance(optJSONObject2.optInt("distance"));
                            drivingStep.setDuration(optJSONObject2.optInt("duration"));
                            drivingStep.c(optJSONObject2.optString("description"));
                            drivingStep.a(optJSONObject2.optString("start_desc"));
                            drivingStep.b(optJSONObject2.optString("end_desc"));
                            drivingStep.b(optJSONObject2.optInt("turn"));
                            arrayList2.add(drivingStep);
                        }
                    }
                    drivingRouteLine.setSteps(arrayList2);
                    arrayList.add(drivingRouteLine);
                }
            }
            drivingRouteResult.a(arrayList);
            drivingRouteResult.setTaxiInfos(g(jSONObject.optString("taxis")));
            return drivingRouteResult;
        } catch (JSONException e) {
            e.printStackTrace();
            drivingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return drivingRouteResult;
        }
    }

    private static SuggestAddrInfo c(JSONObject jSONObject) {
        SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
        suggestAddrInfo.a(d(jSONObject.optJSONArray("origin_list")));
        suggestAddrInfo.b(d(jSONObject.optJSONArray("destination_list")));
        return suggestAddrInfo;
    }

    private static List<PriceInfo> c(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<PriceInfo> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            PriceInfo priceInfo = new PriceInfo();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                priceInfo.setTicketType(optJSONObject.optInt("ticket_type"));
                priceInfo.setTicketPrice(optJSONObject.optDouble("ticket_price"));
            }
            arrayList.add(priceInfo);
        }
        return arrayList;
    }

    private static RouteNode d(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(optJSONObject.optString("name"));
        routeNode.setUid(optJSONObject.optString("uid"));
        routeNode.setLocation(new LatLng(optJSONObject.optDouble("lat"), optJSONObject.optDouble("lng")));
        return routeNode;
    }

    public static IndoorRouteResult d(String str) {
        IndoorRouteResult indoorRouteResult = new IndoorRouteResult();
        if (str == null || "".equals(str)) {
            indoorRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return indoorRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (jSONObject.optInt("error")) {
                case 0:
                    jSONObject = jSONObject.optJSONArray("routes").optJSONObject(0);
                    if (jSONObject == null) {
                        return indoorRouteResult;
                    }
                    List arrayList = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray("legs");
                    if (optJSONArray == null) {
                        return indoorRouteResult;
                    }
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        IndoorRouteLine indoorRouteLine = new IndoorRouteLine();
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            GeoPoint geoPoint;
                            RouteNode routeNode;
                            indoorRouteLine.setDistance(optJSONObject.optInt("distance"));
                            indoorRouteLine.setDuration(optJSONObject.optInt("duration"));
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("sstart_location");
                            if (optJSONArray2 != null) {
                                geoPoint = new GeoPoint(0.0d, 0.0d);
                                geoPoint.setLatitudeE6(optJSONArray2.optDouble(1));
                                geoPoint.setLongitudeE6(optJSONArray2.optDouble(0));
                                routeNode = new RouteNode();
                                routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
                                indoorRouteLine.setStarting(routeNode);
                            }
                            optJSONArray2 = optJSONObject.optJSONArray("send_location");
                            if (optJSONArray2 != null) {
                                geoPoint = new GeoPoint(0.0d, 0.0d);
                                geoPoint.setLatitudeE6(optJSONArray2.optDouble(1));
                                geoPoint.setLongitudeE6(optJSONArray2.optDouble(0));
                                routeNode = new RouteNode();
                                routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
                                indoorRouteLine.setTerminal(routeNode);
                            }
                            JSONArray optJSONArray3 = optJSONObject.optJSONArray("steps");
                            if (optJSONArray3 != null) {
                                List arrayList2 = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                                    IndoorRouteStep indoorRouteStep = new IndoorRouteStep();
                                    JSONObject optJSONObject2 = optJSONArray3.optJSONObject(i2);
                                    if (optJSONObject2 != null) {
                                        indoorRouteStep.setDistance(optJSONObject2.optInt("distance"));
                                        indoorRouteStep.setDuration(optJSONObject2.optInt("duration"));
                                        indoorRouteStep.b(optJSONObject2.optString("buildingid"));
                                        indoorRouteStep.c(optJSONObject2.optString("floorid"));
                                        optJSONArray2 = optJSONObject2.optJSONArray("sstart_location");
                                        if (optJSONArray2 != null) {
                                            geoPoint = new GeoPoint(0.0d, 0.0d);
                                            geoPoint.setLatitudeE6(optJSONArray2.optDouble(1));
                                            geoPoint.setLongitudeE6(optJSONArray2.optDouble(0));
                                            routeNode = new RouteNode();
                                            routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
                                            indoorRouteStep.setEntrace(routeNode);
                                        }
                                        optJSONArray2 = optJSONObject2.optJSONArray("send_location");
                                        if (optJSONArray2 != null) {
                                            geoPoint = new GeoPoint(0.0d, 0.0d);
                                            geoPoint.setLatitudeE6(optJSONArray2.optDouble(1));
                                            geoPoint.setLongitudeE6(optJSONArray2.optDouble(0));
                                            routeNode = new RouteNode();
                                            routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
                                            indoorRouteStep.setExit(routeNode);
                                        }
                                        JSONArray optJSONArray4 = optJSONObject2.optJSONArray("spath");
                                        if (optJSONArray4 != null) {
                                            int i3;
                                            ArrayList arrayList3 = new ArrayList();
                                            if (optJSONArray4.length() > 6) {
                                                double optDouble = optJSONArray4.optDouble(6);
                                                double optDouble2 = optJSONArray4.optDouble(5);
                                                GeoPoint geoPoint2 = new GeoPoint(0.0d, 0.0d);
                                                geoPoint2.setLatitudeE6(optDouble);
                                                geoPoint2.setLongitudeE6(optDouble2);
                                                LatLng mc2ll = CoordUtil.mc2ll(geoPoint2);
                                                arrayList3.add(Double.valueOf(mc2ll.latitude));
                                                arrayList3.add(Double.valueOf(mc2ll.longitude));
                                                for (i3 = 7; i3 < optJSONArray4.length(); i3 += 2) {
                                                    optDouble += optJSONArray4.optDouble(i3 + 1);
                                                    optDouble2 += optJSONArray4.optDouble(i3);
                                                    geoPoint2.setLatitudeE6(optDouble);
                                                    geoPoint2.setLongitudeE6(optDouble2);
                                                    LatLng mc2ll2 = CoordUtil.mc2ll(geoPoint2);
                                                    arrayList3.add(Double.valueOf(mc2ll2.latitude));
                                                    arrayList3.add(Double.valueOf(mc2ll2.longitude));
                                                }
                                            }
                                            indoorRouteStep.setPath(arrayList3);
                                            indoorRouteStep.a(optJSONObject2.optString("instructions"));
                                            JSONArray optJSONArray5 = optJSONObject2.optJSONArray("pois");
                                            if (optJSONArray5 != null) {
                                                List arrayList4 = new ArrayList();
                                                for (i3 = 0; i3 < optJSONArray5.length(); i3++) {
                                                    JSONObject optJSONObject3 = optJSONArray5.optJSONObject(i3);
                                                    if (optJSONObject3 != null) {
                                                        IndoorStepNode indoorStepNode = new IndoorStepNode();
                                                        indoorStepNode.setDetail(optJSONObject3.optString("detail"));
                                                        indoorStepNode.setName(optJSONObject3.optString("name"));
                                                        indoorStepNode.setType(optJSONObject3.optInt("type"));
                                                        JSONArray optJSONArray6 = optJSONObject3.optJSONArray("location");
                                                        if (optJSONArray6 != null) {
                                                            GeoPoint geoPoint3 = new GeoPoint(0.0d, 0.0d);
                                                            geoPoint3.setLatitudeE6(optJSONArray6.optDouble(1));
                                                            geoPoint3.setLongitudeE6(optJSONArray6.optDouble(0));
                                                            indoorStepNode.setLocation(CoordUtil.mc2ll(geoPoint3));
                                                        }
                                                        arrayList4.add(indoorStepNode);
                                                    }
                                                }
                                                indoorRouteStep.setStepNodes(arrayList4);
                                            }
                                            arrayList2.add(indoorRouteStep);
                                        }
                                    }
                                }
                                if (arrayList2.size() > 0) {
                                    indoorRouteLine.setSteps(arrayList2);
                                }
                            }
                            arrayList.add(indoorRouteLine);
                        }
                    }
                    indoorRouteResult.a(arrayList);
                    return indoorRouteResult;
                case 6:
                    indoorRouteResult.error = ERRORNO.INDOOR_ROUTE_NO_IN_BUILDING;
                    return indoorRouteResult;
                case 7:
                    indoorRouteResult.error = ERRORNO.INDOOR_ROUTE_NO_IN_SAME_BUILDING;
                    return indoorRouteResult;
                default:
                    indoorRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
                    return indoorRouteResult;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            indoorRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return indoorRouteResult;
        }
    }

    private static SuggestAddrInfo d(JSONObject jSONObject) {
        SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
        JSONObject optJSONObject = jSONObject.optJSONObject("start_sug");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("end_sug");
        if (optJSONObject != null) {
            int optInt = optJSONObject.optInt("listType");
            String optString = optJSONObject.optString("cityName");
            if (optInt == 1) {
                suggestAddrInfo.d(b(optJSONObject, "list"));
            } else if (optInt == 0) {
                suggestAddrInfo.a(b(optJSONObject, "list", optString));
            }
        }
        if (optJSONObject2 != null) {
            int optInt2 = optJSONObject2.optInt("listType");
            String optString2 = optJSONObject2.optString("cityName");
            if (optInt2 == 1) {
                suggestAddrInfo.e(b(optJSONObject2, "list"));
            } else if (optInt2 == 0) {
                suggestAddrInfo.b(b(optJSONObject2, "list", optString2));
            }
        }
        return suggestAddrInfo;
    }

    private static List<PoiInfo> d(JSONArray jSONArray) {
        if (jSONArray != null) {
            List<PoiInfo> arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
                if (jSONObject != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.address = jSONObject.optString("address");
                    poiInfo.uid = jSONObject.optString("uid");
                    poiInfo.name = jSONObject.optString("name");
                    jSONObject = jSONObject.optJSONObject("location");
                    poiInfo.location = new LatLng(jSONObject.optDouble("lat"), jSONObject.optDouble("lng"));
                    arrayList.add(poiInfo);
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return null;
    }

    private static RouteNode e(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(optJSONObject.optString("name"));
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        geoPoint.setLongitudeE6((double) optJSONObject.optInt("loc_x"));
        geoPoint.setLatitudeE6((double) optJSONObject.optInt("loc_y"));
        routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
        return routeNode;
    }

    public static WalkingRouteResult e(String str) {
        WalkingRouteResult walkingRouteResult = new WalkingRouteResult();
        if (str == null || "".equals(str)) {
            walkingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return walkingRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("taxi")) {
                walkingRouteResult.a(h(jSONObject.optString("taxi")));
            }
            WalkingRouteLine walkingRouteLine = new WalkingRouteLine();
            walkingRouteLine.setStarting(c(jSONObject, "start_point"));
            walkingRouteLine.setTerminal(c(jSONObject, "end_point"));
            jSONObject = jSONObject.optJSONObject("routes");
            if (jSONObject == null) {
                return walkingRouteResult;
            }
            jSONObject = jSONObject.optJSONObject("legs");
            if (jSONObject == null) {
                return walkingRouteResult;
            }
            walkingRouteLine.setDistance(jSONObject.optInt("distance"));
            walkingRouteLine.setDuration(jSONObject.optInt("duration"));
            JSONArray optJSONArray = jSONObject.optJSONArray("steps");
            if (optJSONArray != null) {
                List arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    WalkingStep walkingStep = new WalkingStep();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        walkingStep.a(optJSONObject.optInt("direction") * 30);
                        walkingStep.setDistance(optJSONObject.optInt("distance"));
                        walkingStep.setDuration(optJSONObject.optInt("duration"));
                        walkingStep.a(RouteNode.location(CoordUtil.decodeLocation(optJSONObject.optString("start_loc"))));
                        walkingStep.b(RouteNode.location(CoordUtil.decodeLocation(optJSONObject.optString("end_loc"))));
                        walkingStep.d(optJSONObject.optString("description"));
                        walkingStep.b(optJSONObject.optString("start_desc"));
                        walkingStep.c(optJSONObject.optString("end_desc"));
                        walkingStep.a(optJSONObject.optString("path"));
                        arrayList.add(walkingStep);
                    }
                }
                if (arrayList.size() > 0) {
                    walkingRouteLine.setSteps(arrayList);
                }
            }
            List arrayList2 = new ArrayList();
            arrayList2.add(walkingRouteLine);
            walkingRouteResult.a(arrayList2);
            return walkingRouteResult;
        } catch (JSONException e) {
            e.printStackTrace();
            walkingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return walkingRouteResult;
        }
    }

    public static BikingRouteResult f(String str) {
        BikingRouteResult bikingRouteResult = new BikingRouteResult();
        if (str == null || "".equals(str)) {
            bikingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return bikingRouteResult;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("type");
            if (optInt == 1) {
                bikingRouteResult.a(d(jSONObject));
                bikingRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
            } else if (optInt == 2) {
                JSONArray optJSONArray = jSONObject.optJSONArray("routes");
                List arrayList = new ArrayList();
                optInt = 0;
                while (optInt < optJSONArray.length()) {
                    BikingRouteLine bikingRouteLine = new BikingRouteLine();
                    try {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(optInt);
                        bikingRouteLine.setStarting(d(jSONObject, "start"));
                        bikingRouteLine.setTerminal(d(jSONObject, "end"));
                        if (optJSONObject == null) {
                            return bikingRouteResult;
                        }
                        bikingRouteLine.setDistance(optJSONObject.optInt("distance"));
                        bikingRouteLine.setDuration(optJSONObject.optInt("duration"));
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("steps");
                        if (optJSONArray2 != null) {
                            List arrayList2 = new ArrayList();
                            for (int i = 0; i < optJSONArray2.length(); i++) {
                                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i);
                                BikingStep bikingStep = new BikingStep();
                                if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                                    bikingStep.a(optJSONObject2.optInt("direction") * 30);
                                    bikingStep.setDistance(optJSONObject2.optInt("distance"));
                                    bikingStep.setDuration(optJSONObject2.optInt("duration"));
                                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("start_pt");
                                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("end_pt");
                                    bikingStep.a(RouteNode.location(new LatLng(optJSONObject3.optDouble("lat"), optJSONObject3.optDouble("lng"))));
                                    bikingStep.b(RouteNode.location(new LatLng(optJSONObject4.optDouble("lat"), optJSONObject4.optDouble("lng"))));
                                    bikingStep.d(optJSONObject2.optString("instructions"));
                                    bikingStep.b(optJSONObject2.optString("start_instructions"));
                                    bikingStep.c(optJSONObject2.optString("end_instructions"));
                                    bikingStep.a(optJSONObject2.optString("path"));
                                    arrayList2.add(bikingStep);
                                }
                            }
                            if (arrayList2.size() > 0) {
                                bikingRouteLine.setSteps(arrayList2);
                            }
                        }
                        arrayList.add(bikingRouteLine);
                        optInt++;
                    } catch (Exception e) {
                    }
                }
                bikingRouteResult.a(arrayList);
                return bikingRouteResult;
            } else {
                bikingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            }
            return bikingRouteResult;
        } catch (JSONException e2) {
            e2.printStackTrace();
            bikingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
            return bikingRouteResult;
        }
    }

    private static List<RouteNode> f(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            RouteNode routeNode = new RouteNode();
            try {
                routeNode.setTitle(optJSONArray.getJSONObject(i).optString("name"));
                routeNode.setUid(optJSONArray.getJSONObject(i).optString("uid"));
                GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
                geoPoint.setLongitudeE6((double) optJSONArray.getJSONObject(i).optInt("loc_x"));
                geoPoint.setLatitudeE6((double) optJSONArray.getJSONObject(i).optInt("loc_y"));
                routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
                arrayList.add(routeNode);
            } catch (JSONException e) {
            }
        }
        return arrayList;
    }

    public static List<TaxiInfo> g(String str) {
        List<TaxiInfo> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray == null) {
                return null;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    TaxiInfo taxiInfo = new TaxiInfo();
                    String optString = jSONObject.optString("total_price");
                    if (optString == null || optString.equals("")) {
                        taxiInfo.setTotalPrice(0.0f);
                    } else {
                        taxiInfo.setTotalPrice(Float.parseFloat(optString));
                    }
                    arrayList.add(taxiInfo);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public static TaxiInfo h(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        TaxiInfo taxiInfo = new TaxiInfo();
        taxiInfo.setDesc(jSONObject.optString("remark"));
        taxiInfo.setDistance(jSONObject.optInt("distance"));
        taxiInfo.setDuration(jSONObject.optInt("duration"));
        taxiInfo.setTotalPrice((float) jSONObject.optDouble("total_price"));
        taxiInfo.setStartPrice((float) jSONObject.optDouble("start_price"));
        taxiInfo.setPerKMPrice((float) jSONObject.optDouble("km_price"));
        return taxiInfo;
    }

    public static VehicleInfo i(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setZonePrice(jSONObject.optInt("zone_price"));
        vehicleInfo.setTotalPrice(jSONObject.optInt("total_price"));
        vehicleInfo.setTitle(jSONObject.optString("name"));
        vehicleInfo.setPassStationNum(jSONObject.optInt("stop_num"));
        vehicleInfo.setUid(jSONObject.optString("uid"));
        return vehicleInfo;
    }

    public static SuggestAddrInfo j(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("address_info");
            if (optJSONObject == null) {
                return null;
            }
            SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
            String optString = optJSONObject.optString("st_cityname");
            String optString2 = optJSONObject.optString("en_cityname");
            if (jSONObject.optBoolean("hasAddrList")) {
                suggestAddrInfo.d(a(jSONObject, "startcitys"));
                suggestAddrInfo.a(a(jSONObject, "startpoints", optString));
                suggestAddrInfo.e(a(jSONObject, "endcitys"));
                suggestAddrInfo.b(a(jSONObject, "endpoints", optString2));
                JSONArray optJSONArray = jSONObject.optJSONArray("waypoints_result");
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    return suggestAddrInfo;
                }
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    List a = a(jSONObject2, "waypointcitys");
                    if (a != null) {
                        arrayList.add(a);
                    }
                    List a2 = a(jSONObject2, "waypoints", "");
                    if (a2 != null) {
                        arrayList2.add(a2);
                    }
                }
                if (arrayList.size() > 0) {
                    suggestAddrInfo.f(arrayList);
                }
                if (arrayList2.size() > 0) {
                    suggestAddrInfo.c(arrayList2);
                }
            }
            return suggestAddrInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String k(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] toCharArray = str.toCharArray();
        Object obj = null;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == '<') {
                obj = 1;
            } else if (toCharArray[i] == '>') {
                obj = null;
            } else if (obj == null) {
                stringBuilder.append(toCharArray[i]);
            }
        }
        return stringBuilder.toString();
    }
}
