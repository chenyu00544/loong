package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ecjia.a.d;
import com.ecjia.a.q;
import com.ecjia.a.s;
import com.ecjia.a.y;
import com.ecjia.component.a.j;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecmoban.android.missmall.R;

public class ECJiaMapActivity extends a implements OnGetGeoCoderResultListener {
    MapView a;
    BaiduMap b;
    boolean c = true;
    GeoCoder d = null;
    private LocationMode e;
    private String k;
    private String l;
    private boolean m;
    private String n;
    private String o;
    private String p;

    class ECJiaMapActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaMapActivity a;

        ECJiaMapActivity_1(ECJiaMapActivity eCJiaMapActivity) {
            this.a = eCJiaMapActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaMapActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaMapActivity a;

        ECJiaMapActivity_2(ECJiaMapActivity eCJiaMapActivity) {
            this.a = eCJiaMapActivity;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(this.a.o) || TextUtils.isEmpty(this.a.p) || "null".equals(this.a.p) || "null".equals(this.a.o)) {
                k kVar = new k(this.a, "未找到目的地");
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            }
            s.a(this.a, this.a.l, this.a.n, this.a.o, this.a.p);
        }
    }

    class ECJiaMapActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaMapActivity a;

        ECJiaMapActivity_3(ECJiaMapActivity eCJiaMapActivity) {
            this.a = eCJiaMapActivity;
        }

        public void onClick(View view) {
            s.a(this.a, this.a.l, this.a.n, this.a.o, this.a.p);
        }
    }

    class ECJiaMapActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaMapActivity a;

        ECJiaMapActivity_4(ECJiaMapActivity eCJiaMapActivity) {
            this.a = eCJiaMapActivity;
        }

        public void onClick(View view) {
            s.a(this.a, this.a.l, this.a.n, this.a.o, this.a.p);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_map);
        b();
        c();
        String string = this.g.getString(R.string.main_no_network);
        if (!d.a(this)) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
        }
        this.d = GeoCoder.newInstance();
        this.d.setOnGetGeoCodeResultListener(this);
        if (this.m) {
            q.a("address1====" + this.l);
            this.d.geocode(new GeoCodeOption().city(" ").address(this.l + ""));
        } else if (j.a().a != null) {
            this.l = j.a().a.g();
            q.a("address====" + this.l);
            this.d.geocode(new GeoCodeOption().city(" ").address(this.l + ""));
        }
        this.e = LocationMode.NORMAL;
        this.b.setMapStatus(MapStatusUpdateFactory.zoomTo(17.0f));
    }

    private void b() {
        this.m = getIntent().getBooleanExtra("isGuide", false);
        this.k = getIntent().getStringExtra("shop_name");
        this.l = getIntent().getStringExtra("name");
        this.n = getIntent().getStringExtra("distance");
        this.o = getIntent().getStringExtra("lat");
        this.p = getIntent().getStringExtra("lng");
    }

    private void c() {
        a();
        this.a = (MapView) findViewById(R.id.bmapView);
        this.b = this.a.getMap();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.map_topview);
        this.i.setTitleText((int) R.string.quick_map);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaMapActivity_1(this));
        if (this.m) {
            this.i.setRightType(12);
            this.i.setRightImage((int) R.drawable.icon_shop_guide, new ECJiaMapActivity_2(this));
        }
    }

    protected void onPause() {
        this.a.onPause();
        super.onPause();
    }

    protected void onResume() {
        this.a.onResume();
        super.onResume();
    }

    protected void onDestroy() {
        this.b.setMyLocationEnabled(false);
        this.a.onDestroy();
        super.onDestroy();
    }

    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult == null || geoCodeResult.error != ERRORNO.NO_ERROR) {
            Toast.makeText(this, getResources().getString(R.string.map_nofind), 1).show();
            return;
        }
        this.b.clear();
        this.b.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map_location)));
        if (this.m) {
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_map_guide, null);
            ((TextView) inflate.findViewById(R.id.shop_name)).setText(this.k);
            ((TextView) inflate.findViewById(R.id.shop_location)).setText(geoCodeResult.getAddress());
            inflate.findViewById(R.id.shop_guide).setOnClickListener(new ECJiaMapActivity_3(this));
            this.b.showInfoWindow(new InfoWindow(inflate, geoCodeResult.getLocation(), -y.a(this, 30)));
        }
        this.b.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation()));
    }

    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        CharSequence string = getResources().getString(R.string.map_nofind);
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != ERRORNO.NO_ERROR) {
            Toast.makeText(this, string, 1).show();
            return;
        }
        this.b.clear();
        this.b.addOverlay(new MarkerOptions().position(reverseGeoCodeResult.getLocation()).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map_location)));
        if (this.m) {
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_map_guide, null);
            ((TextView) inflate.findViewById(R.id.shop_name)).setText(this.k);
            ((TextView) inflate.findViewById(R.id.shop_location)).setText(reverseGeoCodeResult.getAddress());
            inflate.findViewById(R.id.shop_guide).setOnClickListener(new ECJiaMapActivity_4(this));
            this.b.showInfoWindow(new InfoWindow(inflate, reverseGeoCodeResult.getLocation(), -y.a(this, 30)));
        }
        this.b.setMapStatus(MapStatusUpdateFactory.newLatLng(reverseGeoCodeResult.getLocation()));
        Toast.makeText(this, reverseGeoCodeResult.getAddress(), 1).show();
    }
}
