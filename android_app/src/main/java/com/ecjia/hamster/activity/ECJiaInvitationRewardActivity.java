package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.t;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.clipviewpager.ECJiaClipViewPager;
import com.ecjia.component.view.clipviewpager.ECJiaScalePageTransformer;
import com.ecjia.hamster.adapter.ECJiaMyInvitationAdapter;
import com.ecjia.hamster.adapter.al;
import com.ecjia.hamster.model.ECJia_INVITE_RECORD;
import com.ecjia.hamster.model.ECJia_INVITE_REWARD;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;

public class ECJiaInvitationRewardActivity extends a implements a, ECJiaXListView.a {
    private ArrayList<ECJia_INVITE_REWARD> a = new ArrayList();
    private ArrayList<ECJia_INVITE_RECORD> b = new ArrayList();
    private ECJiaXListView c;
    private ECJiaErrorView d;
    private t e;
    private RelativeLayout k;
    private ECJiaClipViewPager l;
    private ECJiaMyInvitationAdapter m;
    private al n;
    private int o;
    private int p;

    class ECJiaInvitationRewardActivity_1 implements OnTouchListener {
        final /* synthetic */ ECJiaInvitationRewardActivity a;

        ECJiaInvitationRewardActivity_1(ECJiaInvitationRewardActivity eCJiaInvitationRewardActivity) {
            this.a = eCJiaInvitationRewardActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.a.l.dispatchTouchEvent(motionEvent);
        }
    }

    class ECJiaInvitationRewardActivity_2 implements OnPageChangeListener {
        final /* synthetic */ ECJiaInvitationRewardActivity a;

        ECJiaInvitationRewardActivity_2(ECJiaInvitationRewardActivity eCJiaInvitationRewardActivity) {
            this.a = eCJiaInvitationRewardActivity;
        }

        public void onPageScrolled(int i, float f, int i2) {
            this.a.p = i;
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0 && this.a.o != this.a.p) {
                this.a.l.setScrollble(false);
                this.a.e.a(((ECJia_INVITE_REWARD) this.a.a.get(this.a.p)).getInvite_data(), true);
            }
        }
    }

    class ECJiaInvitationRewardActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaInvitationRewardActivity a;

        ECJiaInvitationRewardActivity_3(ECJiaInvitationRewardActivity eCJiaInvitationRewardActivity) {
            this.a = eCJiaInvitationRewardActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invitation_reward);
        PushAgent.getInstance(this).onAppStart();
        ArrayList arrayList = (ArrayList) getIntent().getSerializableExtra("rewards");
        if (arrayList != null) {
            this.a.addAll(arrayList);
        }
        this.e = new t(this);
        this.e.a((a) this);
        b();
        this.e.a(((ECJia_INVITE_REWARD) this.a.get(this.a.size() - 1)).getInvite_data(), true);
    }

    private void b() {
        a();
        this.k = (RelativeLayout) findViewById(R.id.page_container);
        this.l = (ECJiaClipViewPager) findViewById(R.id.viewpager);
        this.l.setPageTransformer(true, new ECJiaScalePageTransformer());
        this.k.setOnTouchListener(new ECJiaInvitationRewardActivity_1(this));
        this.m = new ECJiaMyInvitationAdapter(this, this.a);
        this.l.setAdapter(this.m);
        this.l.setOffscreenPageLimit(this.a.size());
        this.l.setCurrentItem(this.a.size() - 1);
        this.m.notifyDataSetChanged();
        this.d = (ECJiaErrorView) findViewById(R.id.null_pager);
        this.c = (ECJiaXListView) findViewById(R.id.xlv_invitation);
        this.c.setPullLoadEnable(false);
        this.c.setPullRefreshEnable(true);
        this.c.setXListViewListener(this, 1);
        this.n = new al(this, this.e.b);
        this.c.setAdapter(this.n);
        this.l.addOnPageChangeListener(new ECJiaInvitationRewardActivity_2(this));
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.reward_topview);
        this.i.setTitleText((int) R.string.invitation_reward_detail);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaInvitationRewardActivity_3(this));
    }

    public void a(int i) {
        this.e.a(((ECJia_INVITE_REWARD) this.a.get(this.p)).getInvite_data(), false);
    }

    public void b(int i) {
        this.e.a(((ECJia_INVITE_REWARD) this.a.get(this.p)).getInvite_data());
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("invite/record")) {
            this.l.setScrollble(true);
            if (axVar.b() == 1) {
                this.o = this.p;
                this.c.setRefreshTime();
                this.c.stopRefresh();
                this.c.stopLoadMore();
                if (this.e.a.a() == 1) {
                    this.c.setPullLoadEnable(true);
                } else {
                    this.c.setPullLoadEnable(false);
                }
                if (this.e.b.size() > 0) {
                    this.c.setVisibility(0);
                    this.d.setVisibility(8);
                } else {
                    this.c.setVisibility(8);
                    this.d.setVisibility(0);
                }
                this.n.notifyDataSetChanged();
            }
        }
    }
}
