package com.ecjia.hamster.module.goodsReturn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.a.c;
import com.ecmoban.android.missmall.R;

public class ECJiaReturnOrderListActivity extends a implements com.ecjia.component.a.a.a, ECJiaXListView.a, com.ecjia.hamster.b.a {
    ECJiaErrorView a;
    String b;
    private ECJiaXListView c;
    private com.ecjia.hamster.module.goodsReturn.a d;
    private c e;
    private String k = "";
    private View l;
    private View m;
    private View n;

    class ECJiaReturnOrderListActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaReturnOrderListActivity a;

        class ECJiaReturnOrderListActivity_1_1 implements AnimationListener {
            final /* synthetic */ ECJiaReturnOrderListActivity_1 a;

            ECJiaReturnOrderListActivity_1_1(ECJiaReturnOrderListActivity_1 eCJiaReturnOrderListActivity_1) {
                this.a = eCJiaReturnOrderListActivity_1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.a.startActivityForResult(new Intent(this.a.a, ECJiaSearchInputActivity.class), 1);
                this.a.a.overridePendingTransition(R.anim.animation_2, 0);
            }
        }

        ECJiaReturnOrderListActivity_1(ECJiaReturnOrderListActivity eCJiaReturnOrderListActivity) {
            this.a = eCJiaReturnOrderListActivity;
        }

        public void onClick(View view) {
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -this.a.g.getDimension(R.dimen.dp_48));
            Animation scaleAnimation = new ScaleAnimation(1.0f, 0.82f, 1.0f, 1.0f);
            Animation translateAnimation2 = new TranslateAnimation(0.0f, (float) ((((-this.a.getWindowManager().getDefaultDisplay().getWidth()) / 2) + (((int) this.a.getResources().getDimension(R.dimen.ten_margin)) * 2)) + (this.a.m.getWidth() / 2)), 0.0f, 0.0f);
            translateAnimation.setDuration(300);
            scaleAnimation.setDuration(300);
            translateAnimation2.setDuration(300);
            translateAnimation.setFillAfter(true);
            scaleAnimation.setFillAfter(true);
            translateAnimation2.setFillAfter(true);
            translateAnimation.setAnimationListener(new ECJiaReturnOrderListActivity_1_1(this));
            this.a.l.startAnimation(translateAnimation);
            this.a.n.startAnimation(scaleAnimation);
            this.a.m.startAnimation(translateAnimation2);
        }
    }

    class ECJiaReturnOrderListActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaReturnOrderListActivity a;

        ECJiaReturnOrderListActivity_2(ECJiaReturnOrderListActivity eCJiaReturnOrderListActivity) {
            this.a = eCJiaReturnOrderListActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_back_change);
        de.greenrobot.event.c.a().a((Object) this);
        c();
        b();
        this.d = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.d.a((com.ecjia.component.a.a.a) this);
        this.d.c(this.k);
    }

    private void b() {
        this.b = getIntent().getStringExtra("return_id");
    }

    private void c() {
        a();
        this.l = findViewById(R.id.ll_search);
        this.m = findViewById(R.id.order_list_searchlayout_in);
        this.n = findViewById(R.id.order_list_searchlayout_bg);
        findViewById(R.id.order_list_search).setOnClickListener(new ECJiaReturnOrderListActivity_1(this));
        this.c = (ECJiaXListView) findViewById(R.id.return_list);
        this.c.setXListViewListener(this, R.id.return_list);
        this.c.setPullLoadEnable(false);
        this.a = (ECJiaErrorView) findViewById(R.id.no_info);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.return_list_topview);
        this.i.setTitleType(TitleType.TEXT);
        this.i.setTitleText((int) R.string.return_change_return);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaReturnOrderListActivity_2(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            int width = getWindowManager().getDefaultDisplay().getWidth();
            int dimension = (int) getResources().getDimension(R.dimen.ten_margin);
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -this.g.getDimension(R.dimen.dp_48), 0.0f);
            Animation scaleAnimation = new ScaleAnimation(0.85f, 1.0f, 1.0f, 1.0f);
            Animation translateAnimation2 = new TranslateAnimation((float) ((((-width) / 2) + (dimension * 2)) + (this.m.getWidth() / 2)), 0.0f, 0.0f, 0.0f);
            translateAnimation.setDuration(300);
            scaleAnimation.setDuration(300);
            translateAnimation2.setDuration(300);
            translateAnimation.setFillAfter(true);
            scaleAnimation.setFillAfter(true);
            translateAnimation2.setFillAfter(true);
            this.l.startAnimation(translateAnimation);
            this.n.startAnimation(scaleAnimation);
            this.m.startAnimation(translateAnimation2);
        }
    }

    protected void onDestroy() {
        de.greenrobot.event.c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
        if ("UPDATE_RETURN_DETAIL".equals(aVar.c())) {
            this.d.c(this.k);
        }
    }

    public void a(int i) {
        this.d.c(this.k);
    }

    public void b(int i) {
        this.d.d(this.k);
    }

    public void a(int i, int i2, final Object obj) {
        if (obj instanceof String) {
            final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c(this, "", "确定取消本次售后申请吗？");
            cVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaReturnOrderListActivity c;

                public void onClick(View view) {
                    this.c.d.a((String) obj);
                    cVar.b();
                }
            });
            cVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaReturnOrderListActivity b;

                public void onClick(View view) {
                    cVar.b();
                }
            });
            cVar.a();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/list".equals(str)) {
            if (axVar.b() == 1) {
                if (this.e == null) {
                    this.e = new c(this, this.d.e);
                    this.e.a((com.ecjia.hamster.b.a) this);
                    this.c.setAdapter(this.e);
                } else {
                    this.e.notifyDataSetChanged();
                }
                if (this.d.a(this.d.d)) {
                    this.c.setPullLoadEnable(true);
                } else {
                    this.c.setPullLoadEnable(false);
                }
                if (this.d.e.size() > 0) {
                    this.a.setVisibility(8);
                    this.c.setVisibility(0);
                    return;
                }
                this.a.setVisibility(0);
                this.c.setVisibility(8);
            }
        } else if ("order/return/cancel".equals(str)) {
            this.d.c(this.k);
        }
    }
}
