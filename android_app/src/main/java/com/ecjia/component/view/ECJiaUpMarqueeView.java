package com.ecjia.component.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.a.y;
import com.ecmoban.android.missmall.R;
import com.nineoldandroids.a.c;
import com.nineoldandroids.a.i;
import java.util.ArrayList;

public class ECJiaUpMarqueeView extends LinearLayout implements OnClickListener {
    private static final int ANIMATION_DURATION = 800;
    private static final String TAG = "UpMarqueeTextView";
    private final long DURATION;
    LinearLayout animotionItem;
    int currentOfTopline;
    LinearLayout dataItem;
    private ArrayList<b> datas;
    private Handler handler;
    private float height;
    private boolean isAutoPlay;
    a itemClick;
    private c mAnimatorEndSet;
    private c mAnimatorStartSet;
    private long mDuration;
    b mMainData;
    Runnable mRunnable;
    b mSecondData;
    LinearLayout nodataItem;
    LinearLayout primaryLayout;
    TextView tvFirstContent1;
    TextView tvFirstContent2;
    TextView tvFirstTag1;
    TextView tvFirstTag2;
    TextView tvMainContent1;
    TextView tvMainContent2;
    TextView tvMainTag1;
    TextView tvMainTag2;
    TextView tvSecondContent1;
    TextView tvSecondContent2;
    TextView tvSecondTag1;
    TextView tvSecondTag2;

    class ECJiaUpMarqueeView_1 extends com.nineoldandroids.a.b {
        final /* synthetic */ ECJiaUpMarqueeView a;

        ECJiaUpMarqueeView_1(ECJiaUpMarqueeView eCJiaUpMarqueeView) {
            this.a = eCJiaUpMarqueeView;
        }

        public void b(com.nineoldandroids.a.a aVar) {
            this.a.dataItem.setVisibility(4);
            this.a.tvMainTag1.setText(this.a.mSecondData.a());
            this.a.tvMainTag2.setText(this.a.mSecondData.b());
            if (TextUtils.isEmpty(this.a.mSecondData.a())) {
                this.a.tvMainTag1.setVisibility(4);
            } else {
                this.a.tvMainTag1.setVisibility(0);
                this.a.tvMainTag1.setText(this.a.mSecondData.a());
            }
            if (TextUtils.isEmpty(this.a.mSecondData.b())) {
                this.a.tvMainTag2.setVisibility(4);
            } else {
                this.a.tvMainTag2.setVisibility(0);
                this.a.tvMainTag2.setText(this.a.mSecondData.b());
            }
            this.a.tvMainContent1.setText(this.a.mSecondData.c());
            this.a.tvMainContent2.setText(this.a.mSecondData.d());
        }

        public void a(com.nineoldandroids.a.a aVar) {
            this.a.tvMainTag1.setText(this.a.mSecondData.a());
            this.a.tvMainTag2.setText(this.a.mSecondData.b());
            if (TextUtils.isEmpty(this.a.mSecondData.a())) {
                this.a.tvMainTag1.setVisibility(4);
            } else {
                this.a.tvMainTag1.setVisibility(0);
                this.a.tvMainTag1.setText(this.a.mSecondData.a());
            }
            if (TextUtils.isEmpty(this.a.mSecondData.b())) {
                this.a.tvMainTag2.setVisibility(4);
            } else {
                this.a.tvMainTag2.setVisibility(0);
                this.a.tvMainTag2.setText(this.a.mSecondData.b());
            }
            this.a.tvMainContent1.setText(this.a.mSecondData.c());
            this.a.tvMainContent2.setText(this.a.mSecondData.d());
            this.a.dataItem.setVisibility(0);
            if (this.a.mAnimatorEndSet == null) {
                this.a.initEndAnimation();
            }
            this.a.mAnimatorEndSet.a();
        }
    }

    class ECJiaUpMarqueeView_2 extends com.nineoldandroids.a.b {
        final /* synthetic */ ECJiaUpMarqueeView a;

        ECJiaUpMarqueeView_2(ECJiaUpMarqueeView eCJiaUpMarqueeView) {
            this.a = eCJiaUpMarqueeView;
        }

        public void b(com.nineoldandroids.a.a aVar) {
            this.a.animotionItem.setVisibility(4);
        }

        public void a(com.nineoldandroids.a.a aVar) {
            this.a.animotionItem.setVisibility(0);
        }
    }

    class ECJiaUpMarqueeView_3 implements Runnable {
        final /* synthetic */ ECJiaUpMarqueeView a;

        ECJiaUpMarqueeView_3(ECJiaUpMarqueeView eCJiaUpMarqueeView) {
            this.a = eCJiaUpMarqueeView;
        }

        public void run() {
            ECJiaUpMarqueeView eCJiaUpMarqueeView = this.a;
            eCJiaUpMarqueeView.currentOfTopline++;
            if (this.a.currentOfTopline >= this.a.datas.size()) {
                this.a.currentOfTopline = 0;
            }
            q.a("currentOfTopline" + this.a.currentOfTopline);
            this.a.changData(this.a.currentOfTopline);
            this.a.handler.postDelayed(this.a.mRunnable, this.a.mDuration);
        }
    }

    public enum PlayState {
        STOP,
        PLAY
    }

    public interface a {
        void a();

        void a(int i);

        void b();
    }

    public static class b {
        String a;
        String b;
        String c;
        String d;

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public b(String str, String str2, String str3, String str4) {
            this.a = str;
            this.c = str2;
            this.b = str3;
            this.d = str4;
        }
    }

    public ECJiaUpMarqueeView(Context context) {
        this(context, null);
    }

    public ECJiaUpMarqueeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public ECJiaUpMarqueeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.datas = new ArrayList();
        this.handler = new Handler();
        this.currentOfTopline = -1;
        this.DURATION = 3000;
        this.mDuration = 3000;
        this.isAutoPlay = true;
        this.mRunnable = new ECJiaUpMarqueeView_3(this);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dispatch_layout_main_grabview_parent, this);
        this.tvMainTag1 = (TextView) inflate.findViewById(R.id.tv_main_tag_1);
        this.tvMainContent1 = (TextView) inflate.findViewById(R.id.tv_main_content_1);
        this.tvMainTag2 = (TextView) inflate.findViewById(R.id.tv_main_tag_2);
        this.tvMainContent2 = (TextView) inflate.findViewById(R.id.tv_main_content_2);
        this.dataItem = (LinearLayout) inflate.findViewById(R.id.data_item);
        this.dataItem.setOnClickListener(this);
        this.tvFirstTag1 = (TextView) inflate.findViewById(R.id.tv_first_tag_1);
        this.tvFirstContent1 = (TextView) inflate.findViewById(R.id.tv_first_content_1);
        this.tvFirstTag2 = (TextView) inflate.findViewById(R.id.tv_first_tag_2);
        this.tvFirstContent2 = (TextView) inflate.findViewById(R.id.tv_first_content_2);
        this.tvSecondTag1 = (TextView) inflate.findViewById(R.id.tv_second_tag_1);
        this.tvSecondContent1 = (TextView) inflate.findViewById(R.id.tv_second_content_1);
        this.tvSecondTag2 = (TextView) inflate.findViewById(R.id.tv_second_tag_2);
        this.tvSecondContent2 = (TextView) inflate.findViewById(R.id.tv_second_content_2);
        this.animotionItem = (LinearLayout) inflate.findViewById(R.id.animotion_item);
        this.nodataItem = (LinearLayout) inflate.findViewById(R.id.nodata_item);
        this.nodataItem.setOnClickListener(this);
        this.primaryLayout = (LinearLayout) inflate.findViewById(R.id.primary_layout);
        this.primaryLayout.setOnClickListener(this);
        this.height = (float) y.a(context, 60);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.w(TAG, "--- onDraw ---");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    private void initStartAnimation() {
        com.nineoldandroids.a.a a = i.a(this.animotionItem, "translationY", 0.0f, -this.height);
        this.mAnimatorStartSet = new c();
        this.mAnimatorStartSet.a(new AccelerateInterpolator());
        this.mAnimatorStartSet.a(new ECJiaUpMarqueeView_1(this));
        this.mAnimatorStartSet.b(800);
        this.mAnimatorStartSet.a(a);
    }

    private void initEndAnimation() {
        com.nineoldandroids.a.a a = i.a(this.animotionItem, "translationY", this.height, 0.0f);
        this.mAnimatorEndSet = new c();
        this.mAnimatorEndSet.a(new ECJiaUpMarqueeView_2(this));
        this.mAnimatorEndSet.a(a);
    }

    public void setData(ArrayList<b> arrayList) {
        this.datas = arrayList;
        startPlay();
    }

    private void updateData() {
        if (this.datas == null || this.datas.size() == 0) {
            this.isAutoPlay = false;
            this.nodataItem.setVisibility(0);
            return;
        }
        this.nodataItem.setVisibility(8);
        if (this.datas != null && this.datas.size() == 1) {
            this.mMainData = (b) this.datas.get(0);
            this.mSecondData = (b) this.datas.get(0);
        } else if (this.datas.size() > 1) {
            this.mMainData = (b) this.datas.get(0);
            this.mSecondData = (b) this.datas.get(1);
        }
    }

    void updateUI() {
        if (this.mMainData != null) {
            this.tvMainTag1.setText(this.mMainData.a());
            this.tvMainTag2.setText(this.mMainData.b());
            if (TextUtils.isEmpty(this.mMainData.a())) {
                this.tvMainTag1.setVisibility(4);
            } else {
                this.tvMainTag1.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.mMainData.b())) {
                this.tvMainTag2.setVisibility(4);
            } else {
                this.tvMainTag2.setVisibility(0);
            }
            this.tvMainContent1.setText(this.mMainData.c());
            this.tvMainContent2.setText(this.mMainData.d());
            this.tvFirstTag1.setText(this.mMainData.a());
            this.tvFirstTag2.setText(this.mMainData.b());
            if (TextUtils.isEmpty(this.mMainData.a())) {
                this.tvFirstTag1.setVisibility(4);
            } else {
                this.tvFirstTag1.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.mMainData.b())) {
                this.tvFirstTag2.setVisibility(4);
            } else {
                this.tvFirstTag2.setVisibility(0);
            }
            this.tvFirstContent1.setText(this.mMainData.c());
            this.tvFirstContent2.setText(this.mMainData.d());
        }
        if (this.mSecondData != null) {
            this.tvSecondTag1.setText(this.mSecondData.a());
            this.tvSecondTag2.setText(this.mSecondData.b());
            if (TextUtils.isEmpty(this.mSecondData.a())) {
                this.tvSecondTag1.setVisibility(4);
            } else {
                this.tvSecondTag1.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.mSecondData.b())) {
                this.tvSecondTag2.setVisibility(4);
            } else {
                this.tvSecondTag2.setVisibility(0);
            }
            this.tvSecondContent1.setText(this.mSecondData.c());
            this.tvSecondContent2.setText(this.mSecondData.d());
        }
    }

    private void changData(int i) {
        if (this.datas.get(i) != null) {
            this.mMainData = (b) this.datas.get(i);
            if (i == this.datas.size() - 1) {
                this.mSecondData = (b) this.datas.get(0);
            } else {
                this.mSecondData = (b) this.datas.get(i + 1);
            }
            updateUI();
            if (this.mAnimatorStartSet == null) {
                initStartAnimation();
            }
            this.mAnimatorStartSet.a();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.data_item) {
            if (this.itemClick != null) {
                this.itemClick.a(this.currentOfTopline);
            }
        } else if (view.getId() == R.id.primary_layout) {
            if (this.itemClick != null) {
                this.itemClick.a();
            }
        } else if (view.getId() == R.id.nodata_item && this.itemClick != null) {
            this.itemClick.b();
        }
    }

    public void setTime(long j) {
        if (j >= 3000) {
            this.mDuration = j;
        }
    }

    public void setMarqueeViewItemClickListener(a aVar) {
        this.itemClick = aVar;
    }

    private void setPlayState(PlayState playState) {
        if (playState == PlayState.STOP) {
            this.isAutoPlay = false;
        }
        if (playState == PlayState.PLAY) {
            this.isAutoPlay = true;
        }
    }

    public boolean isAutoPlay() {
        return this.isAutoPlay;
    }

    public void onResume() {
    }

    public void onDestroy() {
        this.handler.removeCallbacks(this.mRunnable);
    }

    public void onRefresh() {
    }

    public void onDetach() {
        this.handler.removeCallbacks(this.mRunnable);
    }

    public void startPlay() {
        this.currentOfTopline = -1;
        this.handler.removeCallbacks(this.mRunnable);
        updateData();
        updateUI();
        if (this.datas == null || this.datas.size() == 0) {
            this.isAutoPlay = false;
        } else {
            this.isAutoPlay = true;
        }
        if (this.isAutoPlay) {
            this.handler.postDelayed(this.mRunnable, this.mDuration);
        }
    }
}
