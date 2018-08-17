package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.adapter.ae;
import com.ecmoban.android.missmall.R;

public class ECJiaLocationPOIActivity extends a implements OnGetPoiSearchResultListener {
    ListView a;
    public LocationClient b = null;
    public BDLocationListener c = new a(this);
    private ae d;
    private PoiSearch e;
    private String k;
    private String l;

    class ECJiaLocationPOIActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaLocationPOIActivity a;

        ECJiaLocationPOIActivity_1(ECJiaLocationPOIActivity eCJiaLocationPOIActivity) {
            this.a = eCJiaLocationPOIActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaLocationPOIActivity_2 implements OnItemClickListener {
        final /* synthetic */ ECJiaLocationPOIActivity a;

        ECJiaLocationPOIActivity_2(ECJiaLocationPOIActivity eCJiaLocationPOIActivity) {
            this.a = eCJiaLocationPOIActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Intent intent = new Intent();
            intent.putExtra("address_name", this.a.d.a(i).name);
            intent.putExtra("address_address", this.a.d.a(i).address);
            intent.putExtra("address_city", this.a.d.a(i).city);
            intent.putExtra("lat", this.a.k);
            intent.putExtra("lng", this.a.l);
            this.a.setResult(-1, intent);
            this.a.finish();
        }
    }

    public class a implements BDLocationListener {
        final /* synthetic */ ECJiaLocationPOIActivity a;

        public a(ECJiaLocationPOIActivity eCJiaLocationPOIActivity) {
            this.a = eCJiaLocationPOIActivity;
        }

        public void onReceiveLocation(BDLocation bDLocation) {
            this.a.e = PoiSearch.newInstance();
            this.a.e.setOnGetPoiSearchResultListener(this.a);
            StringBuffer stringBuffer = new StringBuffer(256);
            stringBuffer.append("time : ");
            stringBuffer.append(bDLocation.getTime());
            stringBuffer.append("\nerror code : ");
            stringBuffer.append(bDLocation.getLocType());
            stringBuffer.append("\nlatitude : ");
            stringBuffer.append(bDLocation.getLatitude());
            stringBuffer.append("\nlontitude : ");
            stringBuffer.append(bDLocation.getLongitude());
            stringBuffer.append("\nradius : ");
            stringBuffer.append(bDLocation.getRadius());
            if (bDLocation.getLocType() == 61) {
                stringBuffer.append("\nspeed : ");
                stringBuffer.append(bDLocation.getSpeed());
                stringBuffer.append("\nsatellite : ");
                stringBuffer.append(bDLocation.getSatelliteNumber());
                stringBuffer.append("\nheight : ");
                stringBuffer.append(bDLocation.getAltitude());
                stringBuffer.append("\ndirection : ");
                stringBuffer.append(bDLocation.getDirection());
                stringBuffer.append("\naddr : ");
                stringBuffer.append(bDLocation.getAddrStr());
                stringBuffer.append("\ndescribe : ");
                stringBuffer.append("gps定位成功");
            } else if (bDLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                stringBuffer.append("\naddr : ");
                stringBuffer.append(bDLocation.getAddrStr());
                stringBuffer.append("\noperationers : ");
                stringBuffer.append(bDLocation.getOperators());
                stringBuffer.append("\ndescribe : ");
                stringBuffer.append("网络定位成功");
            } else if (bDLocation.getLocType() == 66) {
                stringBuffer.append("\ndescribe : ");
                stringBuffer.append("离线定位成功，离线定位结果也是有效的");
            } else if (bDLocation.getLocType() == BDLocation.TypeServerError) {
                stringBuffer.append("\ndescribe : ");
                stringBuffer.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (bDLocation.getLocType() == 63) {
                stringBuffer.append("\ndescribe : ");
                stringBuffer.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (bDLocation.getLocType() == 62) {
                stringBuffer.append("\ndescribe : ");
                stringBuffer.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            stringBuffer.append("\nlocationdescribe : ");
            stringBuffer.append(bDLocation.getLocationDescribe());
            q.a("BaiduLocationApiDem" + stringBuffer.toString());
            this.a.k = bDLocation.getLatitude() + "";
            this.a.l = bDLocation.getLongitude() + "";
            if (bDLocation.getCity() != null && bDLocation.getLocationDescribe() != null) {
                this.a.e.searchInCity(new PoiCitySearchOption().city(bDLocation.getCity()).keyword(bDLocation.getLocationDescribe().substring(1, bDLocation.getLocationDescribe().length() - 2)).pageNum(0));
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_location_poi);
        c();
        b();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.topview_poi);
        this.i.setTitleText((int) R.string.location_poi);
        this.i.setLeftType(1);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaLocationPOIActivity_1(this));
    }

    private void b() {
        this.b = new LocationClient(this);
        this.b.registerLocationListener(this.c);
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationMode.Hight_Accuracy);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(0);
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setOpenGps(true);
        locationClientOption.setLocationNotify(false);
        locationClientOption.setIsNeedLocationDescribe(false);
        locationClientOption.setIsNeedLocationPoiList(true);
        locationClientOption.setIgnoreKillProcess(false);
        locationClientOption.SetIgnoreCacheException(false);
        locationClientOption.setEnableSimulateGps(false);
        this.b.setLocOption(locationClientOption);
        this.b.start();
    }

    private void c() {
        a();
        this.a = (ListView) findViewById(R.id.mlv_poi);
        this.a.setOnItemClickListener(new ECJiaLocationPOIActivity_2(this));
    }

    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult.error != ERRORNO.NO_ERROR) {
            return;
        }
        if (this.d == null) {
            this.d = new ae(this, poiResult.getAllPoi());
            this.a.setAdapter(this.d);
            return;
        }
        this.d.notifyDataSetChanged();
    }

    protected void onDestroy() {
        this.e.destroy();
        this.b.unRegisterLocationListener(this.c);
        super.onDestroy();
    }

    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }

    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
    }
}
