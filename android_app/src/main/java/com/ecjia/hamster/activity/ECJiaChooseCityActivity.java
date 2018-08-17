package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ecjia.a.aa;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.component.view.ECJiaMyLetterListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.z;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.i;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class ECJiaChooseCityActivity extends a implements com.ecjia.component.a.a.a {
    private String A = "";
    private EditText B;
    private ProgressBar C;
    private TextView D;
    private Location E = null;
    private ImageView F;
    com.ecjia.hamster.adapter.d a;
    WindowManager b;
    ECJiaMyGridView c;
    z d;
    public d e = new d(this);
    public LocationClient k;
    public LocationClientOption l;
    public boolean m = true;
    Comparator n = new ECJiaChooseCityActivity_7(this);
    c o;
    private ListView p;
    private ECJiaMyLetterListView q;
    private Handler r;
    private TextView s;
    private TextView t;
    private e u;
    private View v;
    private LinearLayout w;
    private com.ecjia.component.a.c x;
    private SharedPreferences y;
    private Editor z;

    class ECJiaChooseCityActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_1(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void onClick(View view) {
            this.a.x.a("2");
        }
    }

    class ECJiaChooseCityActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_2(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void onClick(View view) {
            if (this.a.b != null) {
                this.a.b.removeView(this.a.s);
            }
            this.a.finish();
        }
    }

    class ECJiaChooseCityActivity_3 implements OnEditorActionListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_3(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 3) {
                if (TextUtils.isEmpty(this.a.B.getText().toString())) {
                    new k(this.a, this.a.getResources().getString(R.string.search_input)).a();
                } else {
                    int size = this.a.x.c.size();
                    int i2 = 0;
                    while (i2 < size) {
                        if (this.a.B.getText().toString().equals(((i) this.a.x.c.get(i2)).b())) {
                            try {
                                this.a.z.putString("localString", ((i) this.a.x.c.get(i2)).d().toString());
                                this.a.z.putString("sendArea", ((i) this.a.x.c.get(i2)).b());
                                this.a.z.putString("area_id", ((i) this.a.x.c.get(i2)).a());
                                this.a.z.commit();
                                aa.a(this.a, "location", "area_id", ((i) this.a.x.c.get(i2)).a());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if ("chooseagain".equals(this.a.A)) {
                                this.a.setResult(100, new Intent());
                                this.a.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                            } else {
                                this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
                                this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                            }
                            if (this.a.b != null) {
                                this.a.b.removeView(this.a.s);
                            }
                            this.a.b();
                            this.a.finish();
                        } else {
                            i2++;
                        }
                    }
                }
            }
            return false;
        }
    }

    class ECJiaChooseCityActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_4(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.a.D.getText().toString())) {
                Iterator it = this.a.x.c.iterator();
                while (it.hasNext()) {
                    i iVar = (i) it.next();
                    if (this.a.D.getText().toString().contains(iVar.b())) {
                        try {
                            this.a.z.putString("localString", iVar.d().toString());
                            this.a.z.putString("sendArea", iVar.b());
                            this.a.z.putString("area_id", iVar.a());
                            this.a.z.commit();
                            aa.a(this.a, "location", "area_id", iVar.a());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if ("chooseagain".equals(this.a.A)) {
                            this.a.setResult(100, new Intent());
                            this.a.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                        } else {
                            this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
                            this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }
                        if (this.a.b != null) {
                            this.a.b.removeView(this.a.s);
                        }
                        this.a.finish();
                        return;
                    }
                }
            }
        }
    }

    class ECJiaChooseCityActivity_5 implements OnItemClickListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_5(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                this.a.z.putString("localString", ((i) this.a.x.d.get(i)).d().toString());
                this.a.z.putString("sendArea", ((i) this.a.x.d.get(i)).b());
                this.a.z.putString("area_id", ((i) this.a.x.d.get(i)).a());
                aa.a(this.a, "location", "area_id", ((i) this.a.x.d.get(i)).a());
                this.a.z.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if ("chooseagain".equals(this.a.A)) {
                this.a.setResult(100, new Intent());
                this.a.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            } else {
                this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
                this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
            if (this.a.b != null) {
                this.a.b.removeView(this.a.s);
            }
            this.a.finish();
        }
    }

    class ECJiaChooseCityActivity_6 implements OnItemClickListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_6(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != 0) {
                try {
                    this.a.z.putString("sendArea", ((i) this.a.x.c.get(i - 1)).b());
                    this.a.z.putString("localString", ((i) this.a.x.c.get(i - 1)).d().toString());
                    this.a.z.putString("area_id", ((i) this.a.x.c.get(i - 1)).a());
                    aa.a(this.a, "location", "area_id", ((i) this.a.x.c.get(i - 1)).a());
                    this.a.z.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("chooseagain".equals(this.a.A)) {
                    this.a.setResult(100, new Intent());
                    this.a.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                } else {
                    this.a.startActivity(new Intent(this.a, ECJiaMainActivity.class));
                    this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
                if (this.a.b != null) {
                    this.a.b.removeView(this.a.s);
                }
                this.a.finish();
            }
        }
    }

    class ECJiaChooseCityActivity_7 implements Comparator<i> {
        final /* synthetic */ ECJiaChooseCityActivity a;

        ECJiaChooseCityActivity_7(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((i) obj, (i) obj2);
        }

        public int a(i iVar, i iVar2) {
            String substring = iVar.c().substring(0, 1);
            String substring2 = iVar2.c().substring(0, 1);
            int compareTo = substring.compareTo(substring2);
            if (compareTo == 0) {
                return substring.compareTo(substring2);
            }
            return compareTo;
        }
    }

    public interface c {
    }

    private class a implements c {
        final /* synthetic */ ECJiaChooseCityActivity a;

        private a(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }
    }

    private class b implements com.ecjia.component.view.ECJiaMyLetterListView.a {
        final /* synthetic */ ECJiaChooseCityActivity a;

        private b(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void a(String str) {
            if (this.a.a.c.get(str) != null) {
                int intValue = ((Integer) this.a.a.c.get(str)).intValue();
                this.a.p.setSelection(intValue);
                this.a.s.setText(this.a.a.b[intValue]);
                this.a.s.setVisibility(0);
                this.a.r.removeCallbacks(this.a.u);
                this.a.r.postDelayed(this.a.u, 1500);
            }
        }
    }

    public class d implements BDLocationListener {
        final /* synthetic */ ECJiaChooseCityActivity a;

        public d(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void onReceiveLocation(BDLocation bDLocation) {
            if (bDLocation == null) {
                this.a.c();
            } else if (this.a.m) {
                this.a.m = false;
                this.a.D.setVisibility(0);
                if (bDLocation.getCity() != null) {
                    this.a.D.setText(bDLocation.getCity().replace("市", ""));
                    if ("".equals(this.a.y.getString("sendArea", ""))) {
                        Iterator it = this.a.x.c.iterator();
                        while (it.hasNext()) {
                            i iVar = (i) it.next();
                            if (bDLocation.getCity().contains(iVar.b())) {
                                this.a.z.putString("sendArea", bDLocation.getAddrStr());
                                this.a.z.putString("area_id", iVar.a());
                                aa.a(this.a, "location", "area_id", iVar.a());
                                try {
                                    this.a.z.putString("localString", iVar.d().toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                this.a.z.commit();
                                break;
                            }
                        }
                    }
                } else {
                    this.a.D.setVisibility(8);
                }
                this.a.C.setVisibility(8);
                this.a.a.notifyDataSetChanged();
            }
        }
    }

    private class e implements Runnable {
        final /* synthetic */ ECJiaChooseCityActivity a;

        private e(ECJiaChooseCityActivity eCJiaChooseCityActivity) {
            this.a = eCJiaChooseCityActivity;
        }

        public void run() {
            this.a.s.setVisibility(8);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_city);
        this.y = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.z = this.y.edit();
        this.A = getIntent().getStringExtra("chooseagain");
        this.t = (TextView) findViewById(R.id.tv_refresh);
        this.t.setOnClickListener(new ECJiaChooseCityActivity_1(this));
        this.x = new com.ecjia.component.a.c(this);
        this.x.a((com.ecjia.component.a.a.a) this);
        this.x.a("2");
    }

    private void e() {
        g();
        this.F = (ImageView) findViewById(R.id.choosecity_back);
        if ("chooseagain".equals(this.A)) {
            this.F.setVisibility(0);
        } else {
            this.F.setVisibility(8);
        }
        this.F.setOnClickListener(new ECJiaChooseCityActivity_2(this));
        this.B = (EditText) findViewById(R.id.et_city_input);
        this.B.setOnEditorActionListener(new ECJiaChooseCityActivity_3(this));
        this.v = getLayoutInflater().inflate(R.layout.choosecity_localitem, null);
        this.C = (ProgressBar) this.v.findViewById(R.id.city_progressBar);
        this.D = (TextView) this.v.findViewById(R.id.local_city_text);
        this.D.setOnClickListener(new ECJiaChooseCityActivity_4(this));
        this.w = (LinearLayout) this.v.findViewById(R.id.hot_city_item);
        if (this.x.d.size() > 0) {
            this.w.setVisibility(0);
            this.c = (ECJiaMyGridView) this.v.findViewById(R.id.hot_topview);
            this.d = new z(this, this.x.d);
            this.c.setAdapter(this.d);
            this.c.setOnItemClickListener(new ECJiaChooseCityActivity_5(this));
        } else {
            this.w.setVisibility(8);
        }
        this.p = (ListView) findViewById(R.id.list_view);
        this.p.addHeaderView(this.v);
        this.q = (ECJiaMyLetterListView) findViewById(R.id.MyLetterListView);
        this.q.setOnTouchingLetterChangedListener(new b());
        this.p.setOnItemClickListener(new ECJiaChooseCityActivity_6(this));
        a(new a());
        this.r = new Handler();
        this.u = new e();
        f();
        a(this.x.c);
    }

    private void a(List<i> list) {
        if (this.a == null) {
            this.a = new com.ecjia.hamster.adapter.d(this, list);
        }
        this.p.setAdapter(this.a);
    }

    private void f() {
        this.s = (TextView) LayoutInflater.from(this).inflate(R.layout.choosecity_overlay, null);
        this.s.setVisibility(4);
        LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2, 24, -3);
        this.b = (WindowManager) getSystemService("window");
        this.b.addView(this.s, layoutParams);
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("shop/region")) {
            return;
        }
        if (axVar.b() == 1) {
            this.t.setVisibility(8);
            e();
        } else if (axVar.b() == 0) {
            k kVar = new k((Context) this, this.g.getString(R.string.main_no_network));
            kVar.a(17, 0, 0);
            kVar.a();
            this.t.setVisibility(0);
        }
    }

    public void a(c cVar) {
        this.o = cVar;
    }

    public void finish() {
        super.finish();
        b();
        if ("chooseagain".equals(this.A)) {
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        } else {
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (TextUtils.isEmpty(this.A) || !"chooseagain".equals(this.A)) {
                return false;
            }
            if (this.b != null) {
                this.b.removeView(this.s);
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void b() {
        this.B.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.B.getWindowToken(), 0);
    }

    private void g() {
        this.k = new LocationClient(this);
        this.l = new LocationClientOption();
        this.l.setAddrType("all");
        this.l.setLocationNotify(true);
        this.l.setCoorType("bd09ll");
        this.l.setOpenGps(true);
        this.l.setScanSpan(1000);
        this.k.setLocOption(this.l);
        this.k.registerLocationListener(this.e);
        this.k.start();
    }

    public boolean c() {
        LocationManager locationManager = (LocationManager) getSystemService("location");
        List providers = locationManager.getProviders(true);
        for (int size = providers.size() - 1; size >= 0; size--) {
            this.E = locationManager.getLastKnownLocation((String) providers.get(size));
            if (this.E != null) {
                break;
            }
        }
        com.ecjia.consts.b.g = new double[2];
        if (this.E != null) {
            com.ecjia.consts.b.g[0] = this.E.getLongitude();
            com.ecjia.consts.b.g[1] = this.E.getLatitude();
        } else {
            q.a("定位失败");
        }
        if (com.ecjia.consts.b.g[0] != 0.0d) {
            return true;
        }
        return false;
    }

    protected void onDestroy() {
        if (this.k != null) {
            this.k.unRegisterLocationListener(this.e);
            this.k.stop();
            this.k = null;
        }
        this.e = null;
        super.onDestroy();
    }
}
