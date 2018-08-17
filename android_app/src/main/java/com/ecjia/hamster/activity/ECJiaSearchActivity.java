package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ecjia.a.e;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.bb;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;

@SuppressLint({"CommitPrefEdits"})
public class ECJiaSearchActivity extends a implements OnClickListener {
    ECJia_FILTER a = new ECJia_FILTER();
    private EditText b;
    private TextView c;
    private TextView d;
    private TextView e;
    private ListView k;
    private RelativeLayout l;
    private LinearLayout m;
    private LinearLayout n;
    private FrameLayout o;
    private FrameLayout p;
    private bb q;
    private List<String> r;
    private float s;
    private boolean t = false;

    class ECJiaSearchActivity_1 implements OnEditorActionListener {
        final /* synthetic */ ECJiaSearchActivity a;

        ECJiaSearchActivity_1(ECJiaSearchActivity eCJiaSearchActivity) {
            this.a = eCJiaSearchActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            String string = this.a.getBaseContext().getResources().getString(R.string.search_please_input);
            this.a.b();
            if (i == 3) {
                String obj = this.a.b.getText().toString();
                e.a().a(this.a, obj);
                this.a.r = e.a().a(this.a);
                Intent intent = new Intent(this.a, ECJiaGoodsListActivity.class);
                if (obj == null || "".equals(obj)) {
                    k kVar = new k(this.a, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else {
                    try {
                        intent.putExtra("filter", this.a.a.toJson().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("keyword", obj);
                    this.a.startActivity(intent);
                }
            }
            return false;
        }
    }

    class ECJiaSearchActivity_2 implements AnimationListener {
        final /* synthetic */ ECJiaSearchActivity a;

        ECJiaSearchActivity_2(ECJiaSearchActivity eCJiaSearchActivity) {
            this.a = eCJiaSearchActivity;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.a.finish();
            this.a.overridePendingTransition(R.anim.animation_4, 0);
        }
    }

    class ECJiaSearchActivity_3 extends TimerTask {
        final /* synthetic */ ECJiaSearchActivity a;

        ECJiaSearchActivity_3(ECJiaSearchActivity eCJiaSearchActivity) {
            this.a = eCJiaSearchActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.b.getContext().getSystemService("input_method")).showSoftInput(this.a.b, 0);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search);
        PushAgent.getInstance(this).onAppStart();
        this.l = (RelativeLayout) findViewById(R.id.rl_search);
        this.p = (FrameLayout) findViewById(R.id.fl_search_top);
        this.b = (EditText) findViewById(R.id.et_search_input);
        this.b.setOnClickListener(this);
        this.c = (TextView) findViewById(R.id.tv_search_cancel);
        this.c.setOnClickListener(this);
        this.e = (TextView) findViewById(R.id.clear_history);
        this.e.setOnClickListener(this);
        this.d = (TextView) findViewById(R.id.tv_search_history);
        this.k = (ListView) findViewById(R.id.lv_history);
        this.q = new bb(this.r, this);
        this.m = (LinearLayout) findViewById(R.id.layout_search);
        this.n = (LinearLayout) findViewById(R.id.clear_history_layout);
        this.o = (FrameLayout) findViewById(R.id.search_null);
        this.b.setFocusable(true);
        this.b.setFocusableInTouchMode(true);
        this.b.requestFocus();
        this.b.setOnEditorActionListener(new ECJiaSearchActivity_1(this));
        this.r = e.a().a(this);
        this.q.a(this.r);
        this.q.notifyDataSetChanged();
        this.k.setAdapter(this.q);
    }

    @TargetApi(11)
    public void onClick(View view) {
        getBaseContext().getResources();
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                b();
                this.s = this.p.getY();
                Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -this.s);
                translateAnimation.setDuration(300);
                translateAnimation.setFillAfter(true);
                translateAnimation.setAnimationListener(new ECJiaSearchActivity_2(this));
                this.l.startAnimation(translateAnimation);
                return;
            case R.id.search_back:
                b();
                finish();
                return;
            case R.id.clear_history:
                this.q.a(null);
                Editor edit = getSharedPreferences("my_shared", 0).edit();
                edit.clear();
                edit.commit();
                this.q.notifyDataSetChanged();
                b();
                this.n.setVisibility(8);
                this.d.setVisibility(8);
                this.m.setVisibility(8);
                this.o.setVisibility(0);
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        ((InputMethodManager) this.b.getContext().getSystemService("input_method")).showSoftInput(this.b, 0);
        new Timer().schedule(new ECJiaSearchActivity_3(this), 600);
        if (this.t) {
            this.r = e.a().a(this);
            this.q.a(this.r);
            this.q.notifyDataSetChanged();
        }
        if (this.r == null || "".equals(this.r.get(0))) {
            this.n.setVisibility(8);
            this.d.setVisibility(8);
            this.m.setVisibility(8);
            this.o.setVisibility(0);
            return;
        }
        this.n.setVisibility(0);
        this.d.setVisibility(0);
        this.m.setVisibility(0);
        this.o.setVisibility(8);
    }

    protected void onPause() {
        super.onPause();
        this.t = true;
    }

    public void b() {
        this.b.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        b();
        finish();
        overridePendingTransition(R.anim.animation_4, 0);
        return false;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
