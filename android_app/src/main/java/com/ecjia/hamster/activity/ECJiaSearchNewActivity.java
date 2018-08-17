package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ecjia.a.a.b;
import com.ecjia.a.e;
import com.ecjia.a.y;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ad;
import com.ecjia.component.view.ECJiaFlowLayout;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bc;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"CommitPrefEdits"})
public class ECJiaSearchNewActivity extends a implements OnClickListener, a {
    private ListView A;
    private bc B;
    private bc C;
    private List<String> D;
    private List<String> E;
    private ad F;
    private c G;
    private LinearLayout H;
    private ECJiaFlowLayout I;
    private EditText a;
    private TextView b;
    private RelativeLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private FrameLayout n;
    private LinearLayout o;
    private View p;
    private float q;
    private ECJia_FILTER r = new ECJia_FILTER();
    private View s;
    private LinearLayout t;
    private LinearLayout u;
    private LinearLayout v;
    private LinearLayout w;
    private TextView x;
    private TextView y;
    private ListView z;

    class ECJiaSearchNewActivity_10 implements OnClickListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_10(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onClick(View view) {
            this.a.G.b();
            Editor edit = this.a.getSharedPreferences("my_good_shared", 0).edit();
            edit.clear();
            edit.commit();
            this.a.c();
            this.a.f();
        }
    }

    class ECJiaSearchNewActivity_11 implements OnClickListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_11(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onClick(View view) {
            this.a.G.b();
        }
    }

    class ECJiaSearchNewActivity_12 implements OnClickListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_12(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onClick(View view) {
            this.a.G.b();
            Editor edit = this.a.getSharedPreferences("my_seller_shared", 0).edit();
            edit.clear();
            edit.commit();
            this.a.c();
            this.a.f();
        }
    }

    class ECJiaSearchNewActivity_1 implements TextWatcher {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_1(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.length() == 0) {
                this.a.d.setVisibility(8);
                return;
            }
            this.a.d.setVisibility(0);
            this.a.l.setText(editable);
            this.a.m.setText(editable);
        }
    }

    class ECJiaSearchNewActivity_2 extends TimerTask {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_2(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.getSystemService("input_method")).showSoftInput(this.a.a, 0);
        }
    }

    class ECJiaSearchNewActivity_5 implements OnEditorActionListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_5(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            String string = this.a.getBaseContext().getResources().getString(R.string.search_please_input);
            this.a.c();
            if (i == 3) {
                String obj = this.a.a.getText().toString();
                if (obj == null || "".equals(obj)) {
                    k kVar = new k(this.a, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else {
                    Intent intent = new Intent(this.a, ECJiaSearchAllActivity.class);
                    intent.putExtra("keyword", obj);
                    this.a.startActivity(intent);
                    this.a.a.setText("");
                }
            }
            return true;
        }
    }

    class ECJiaSearchNewActivity_6 implements OnItemClickListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_6(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.c();
            Intent intent = new Intent(this.a, ECJiaGoodsListActivity.class);
            intent.putExtra("filter", this.a.r);
            intent.putExtra("keyword", (String) this.a.C.a.get(i));
            this.a.startActivity(intent);
        }
    }

    class ECJiaSearchNewActivity_7 implements OnItemClickListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_7(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.c();
            Intent intent = new Intent(this.a, ECJiaSearchSellerGoodsActivity.class);
            intent.putExtra("keyword", (String) this.a.B.a.get(i));
            intent.putExtra("searchseller", true);
            this.a.startActivity(intent);
        }
    }

    class ECJiaSearchNewActivity_8 implements AnimationListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_8(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.a.finish();
        }
    }

    class ECJiaSearchNewActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaSearchNewActivity a;

        ECJiaSearchNewActivity_9(ECJiaSearchNewActivity eCJiaSearchNewActivity) {
            this.a = eCJiaSearchNewActivity;
        }

        public void onClick(View view) {
            this.a.G.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        de.greenrobot.event.c.a().a((Object) this);
        setContentView(R.layout.activity_search_new);
        PushAgent.getInstance(this).onAppStart();
        this.F = new ad(this);
        this.F.a((a) this);
        this.F.c();
        this.c = (RelativeLayout) findViewById(R.id.rl_search);
        this.o = (LinearLayout) findViewById(R.id.fl_search_top);
        this.a = (EditText) findViewById(R.id.et_search_input);
        this.a.setOnClickListener(this);
        this.b = (TextView) findViewById(R.id.tv_search_cancel);
        this.b.setOnClickListener(this);
        this.n = (FrameLayout) findViewById(R.id.search_null);
        this.p = findViewById(R.id.banner_blank);
        this.p.setOnClickListener(this);
        this.v = (LinearLayout) findViewById(R.id.ll_good);
        this.w = (LinearLayout) findViewById(R.id.ll_shop);
        this.d = (LinearLayout) findViewById(R.id.ll_search_type);
        this.e = (LinearLayout) findViewById(R.id.ll_type_goods);
        this.e.setOnClickListener(this);
        this.k = (LinearLayout) findViewById(R.id.ll_type_sellers);
        this.k.setOnClickListener(this);
        this.l = (TextView) findViewById(R.id.tv_content_goods);
        this.m = (TextView) findViewById(R.id.tv_content_sellers);
        this.a.addTextChangedListener(new ECJiaSearchNewActivity_1(this));
        this.a.setOnEditorActionListener(new ECJiaSearchNewActivity_5(this));
        this.H = (LinearLayout) findViewById(R.id.hot_search_tablayout);
        this.I = (ECJiaFlowLayout) findViewById(R.id.hot_search_flowlayout);
        e();
    }

    private void e() {
        this.s = findViewById(R.id.fl_search_notnull);
        this.x = (TextView) findViewById(R.id.tv_seller_history);
        this.y = (TextView) findViewById(R.id.tv_good_history);
        this.u = (LinearLayout) findViewById(R.id.clean_good_history);
        this.u.setOnClickListener(this);
        this.t = (LinearLayout) findViewById(R.id.clean_seller_history);
        this.t.setOnClickListener(this);
        this.D = e.a().b(this);
        this.E = e.a().c(this);
        this.A = (ListView) findViewById(R.id.lv_good_history);
        this.z = (ListView) findViewById(R.id.lv_seller_history);
        this.B = new bc(this.D, this);
        this.C = new bc(this.E, this);
        this.B.a(this.D);
        this.C.a(this.E);
        this.A.setAdapter(this.C);
        this.z.setAdapter(this.B);
        this.A.setOnItemClickListener(new ECJiaSearchNewActivity_6(this));
        this.z.setOnItemClickListener(new ECJiaSearchNewActivity_7(this));
        b();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 66) {
            return super.dispatchKeyEvent(keyEvent);
        }
        String string = getBaseContext().getResources().getString(R.string.search_please_input);
        c();
        String obj = this.a.getText().toString();
        Intent intent = new Intent(this, ECJiaSearchAllActivity.class);
        if (obj == null || "".equals(obj)) {
            k kVar = new k((Context) this, string);
            kVar.a(17, 0, 0);
            kVar.a();
        } else {
            intent.putExtra("keyword", obj);
            startActivity(intent);
            this.a.setText("");
        }
        return true;
    }

    @TargetApi(11)
    public void onClick(View view) {
        String string = this.g.getString(R.string.search_please_input);
        String string2 = this.g.getString(R.string.lastbrowse_delete);
        String string3 = this.g.getString(R.string.lasebrowse_delete_sure);
        String string4 = this.g.getString(R.string.lasebrowse_delete_sure2);
        k kVar;
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                c();
                this.q = this.o.getY();
                Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -this.q);
                translateAnimation.setDuration(300);
                translateAnimation.setFillAfter(true);
                translateAnimation.setAnimationListener(new ECJiaSearchNewActivity_8(this));
                this.c.startAnimation(translateAnimation);
                return;
            case R.id.clean_good_history:
                this.G = new c(this, string2, string3);
                this.G.a();
                this.G.d.setOnClickListener(new ECJiaSearchNewActivity_9(this));
                this.G.b.setOnClickListener(new ECJiaSearchNewActivity_10(this));
                return;
            case R.id.clean_seller_history:
                this.G = new c(this, string2, string4);
                this.G.a();
                this.G.d.setOnClickListener(new ECJiaSearchNewActivity_11(this));
                this.G.b.setOnClickListener(new ECJiaSearchNewActivity_12(this));
                return;
            case R.id.ll_type_goods:
                c();
                string2 = this.a.getText().toString();
                e.a().c(this, string2);
                if (string2 == null || "".equals(string2)) {
                    kVar = new k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                intent = new Intent(this, ECJiaGoodsListActivity.class);
                intent.putExtra("filter", this.r);
                intent.putExtra("keyword", string2);
                startActivity(intent);
                this.a.setText("");
                return;
            case R.id.ll_type_sellers:
                c();
                string2 = this.a.getText().toString();
                if (string2 == null || "".equals(string2)) {
                    kVar = new k((Context) this, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                intent = new Intent(this, ECJiaSearchSellerGoodsActivity.class);
                intent.putExtra("keyword", string2);
                intent.putExtra("searchseller", true);
                startActivity(intent);
                this.a.setText("");
                return;
            case R.id.banner_blank:
                this.a.setText("");
                return;
            default:
                return;
        }
    }

    private void f() {
        new Timer().schedule(new ECJiaSearchNewActivity_2(this), 600);
        this.D = e.a().b(this);
        this.E = e.a().c(this);
        if (this.y != null) {
            this.y.setText("(" + this.E.size() + this.g.getString(R.string.have_history) + ")");
        }
        if (this.x != null) {
            this.x.setText("(" + this.D.size() + this.g.getString(R.string.have_history) + ")");
        }
        if (this.B != null) {
            this.B.a(this.D);
            this.B.notifyDataSetChanged();
        }
        if (this.C != null) {
            this.C.a(this.E);
            this.C.notifyDataSetChanged();
        }
        a(this.A);
        a(this.z);
        b();
    }

    void b() {
        if ((this.D == null || this.D.size() <= 0) && (this.E == null || this.E.size() <= 0)) {
            this.I.setVisibility(0);
            this.H.setVisibility(8);
            findViewById(R.id.hot_search_horizontal).setBackgroundColor(Color.parseColor("#EEEEEE"));
        } else {
            this.I.setVisibility(8);
            this.H.setVisibility(0);
            findViewById(R.id.hot_search_horizontal).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if ((this.D == null || this.D.size() == 0) && (this.E == null || this.E.size() == 0)) {
            this.s.setVisibility(8);
            this.n.setVisibility(0);
            return;
        }
        if (this.D == null || this.D.size() == 0) {
            this.w.setVisibility(8);
        } else {
            this.w.setVisibility(0);
        }
        if (this.E == null || this.E.size() == 0) {
            this.v.setVisibility(8);
        } else {
            this.v.setVisibility(0);
        }
        this.s.setVisibility(0);
        this.n.setVisibility(8);
    }

    public void a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int count = adapter.getCount();
            int i = 0;
            for (int i2 = 0; i2 < count; i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    protected void onResume() {
        super.onResume();
        f();
    }

    protected void onPause() {
        super.onPause();
    }

    public void c() {
        this.a.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        c();
        finish();
        return false;
    }

    public void onEvent(b bVar) {
        if (bVar.c().equals("search_back")) {
            finish();
        }
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        a(this.a);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("mobile/hot_keywords")) {
            this.H.removeAllViews();
            this.I.removeAllViews();
            int min = Math.min(10, this.F.b.size());
            for (int i = 0; i < min; i++) {
                View relativeLayout = new RelativeLayout(this);
                relativeLayout.setLayoutParams(new LayoutParams(-2, -2));
                relativeLayout.setPadding(y.a(this, 5), y.a(this, 5), y.a(this, 5), y.a(this, 5));
                View textView = new TextView(this);
                textView.setBackgroundColor(Color.parseColor("#EEEEEE"));
                textView.setLayoutParams(new RelativeLayout.LayoutParams(-2, y.a(this, 30)));
                textView.setPadding(y.a(this, 10), y.a(this, 0), y.a(this, 10), y.a(this, 0));
                textView.setGravity(17);
                textView.setText((CharSequence) this.F.b.get(i));
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaSearchNewActivity b;

                    public void onClick(View view) {
                        Intent intent = new Intent(this.b, ECJiaGoodsListActivity.class);
                        intent.putExtra("keyword", (String) this.b.F.b.get(i));
                        this.b.startActivity(intent);
                    }
                });
                relativeLayout.addView(textView);
                this.H.addView(relativeLayout);
                relativeLayout = new RelativeLayout(this);
                relativeLayout.setLayoutParams(new MarginLayoutParams(-2, -2));
                relativeLayout.setPadding(y.a(this, 5), y.a(this, 5), y.a(this, 5), y.a(this, 5));
                textView = new TextView(this);
                textView.setBackgroundColor(Color.parseColor("#EEEEEE"));
                textView.setLayoutParams(new RelativeLayout.LayoutParams(-2, y.a(this, 30)));
                textView.setPadding(y.a(this, 10), y.a(this, 0), y.a(this, 10), y.a(this, 0));
                textView.setGravity(17);
                textView.setText((CharSequence) this.F.b.get(i));
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaSearchNewActivity b;

                    public void onClick(View view) {
                        Intent intent = new Intent(this.b, ECJiaGoodsListActivity.class);
                        intent.putExtra("keyword", (String) this.b.F.b.get(i));
                        this.b.startActivity(intent);
                    }
                });
                relativeLayout.addView(textView);
                this.I.addView(relativeLayout);
            }
            if ((this.D == null || this.D.size() <= 0) && (this.E == null || this.E.size() <= 0)) {
                this.I.setVisibility(0);
                this.H.setVisibility(8);
                findViewById(R.id.hot_search_horizontal).setBackgroundColor(Color.parseColor("#EEEEEE"));
                return;
            }
            this.I.setVisibility(8);
            this.H.setVisibility(0);
            findViewById(R.id.hot_search_horizontal).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }
}
