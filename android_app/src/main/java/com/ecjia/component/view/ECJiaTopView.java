package com.ecjia.component.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import anet.channel.strategy.dispatch.c;
import com.ecjia.a.q;
import com.ecjia.a.y;
import com.ecmoban.android.missmall.R;
import com.nineoldandroids.a.b;
import com.nineoldandroids.a.i;

public class ECJiaTopView extends FrameLayout {
    public static final int LEFT_TYPE_BACKIMAGE = 1;
    public static final int LEFT_TYPE_DEFAULT = 5;
    public static final int LEFT_TYPE_GONE = 4;
    public static final int LEFT_TYPE_PHOTOIMAGE = 2;
    public static final int LEFT_TYPE_TEXT = 3;
    public static final int RIGHT_TYPE_DEFAULT = 14;
    public static final int RIGHT_TYPE_GONE = 13;
    public static final int RIGHT_TYPE_IMAGE = 12;
    public static final int RIGHT_TYPE_TEXT = 11;
    private View TouchView;
    boolean isTouchEventConsumeAble;
    private ImageView leftBackImage;
    private FrameLayout leftLayout;
    private ImageView leftPhotoImage;
    private TextView leftTextView;
    boolean mAnimationEnable;
    a mAnimationListener;
    private Context mContext;
    private ImageView rightImage;
    private FrameLayout rightLayout;
    private TextView rightTextView;
    TabLayout tabLayout;
    private ImageView titleImageView;
    private TextView titleTextView;

    class ECJiaTopView_1 implements OnTouchListener {
        final /* synthetic */ ECJiaTopView a;

        ECJiaTopView_1(ECJiaTopView eCJiaTopView) {
            this.a = eCJiaTopView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.a.isTouchEventConsumeAble;
        }
    }

    class ECJiaTopView_2 implements Runnable {
        final /* synthetic */ ECJiaTopView a;

        ECJiaTopView_2(ECJiaTopView eCJiaTopView) {
            this.a = eCJiaTopView;
        }

        public void run() {
            this.a.titleTextView.setVisibility(4);
            i.a(this.a.titleTextView, "translationY", 0.0f, (float) y.a(this.a.mContext, 40)).a();
            this.a.titleTextView.setVisibility(4);
        }
    }

    class ECJiaTopView_3 extends b {
        final /* synthetic */ ECJiaTopView a;

        ECJiaTopView_3(ECJiaTopView eCJiaTopView) {
            this.a = eCJiaTopView;
        }

        public void a(com.nineoldandroids.a.a aVar) {
            super.a(aVar);
            if (this.a.mAnimationListener != null) {
                this.a.mAnimationListener.b();
            }
        }
    }

    class ECJiaTopView_4 extends b {
        final /* synthetic */ ECJiaTopView a;

        ECJiaTopView_4(ECJiaTopView eCJiaTopView) {
            this.a = eCJiaTopView;
        }

        public void a(com.nineoldandroids.a.a aVar) {
            super.a(aVar);
            if (this.a.mAnimationListener != null) {
                this.a.mAnimationListener.c();
            }
        }
    }

    public enum TitleType {
        TEXT,
        IMAGE,
        TABLAYOUT,
        NONE
    }

    public interface a {
        void a();

        void b();

        void c();
    }

    public ECJiaTopView(Context context) {
        this(context, null);
    }

    public ECJiaTopView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public ECJiaTopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimationEnable = false;
        this.isTouchEventConsumeAble = true;
        this.mContext = context;
        init();
    }

    public int getStatusBarHeight() {
        int i = 0;
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", c.ANDROID);
        if (identifier > 0) {
            i = getResources().getDimensionPixelSize(identifier);
        }
        q.a("baseresult =" + i);
        return i;
    }

    void init() {
        LayoutInflater.from(this.mContext).inflate(R.layout.layout_ecjiatopview, this, true);
        setBackgroundResource(R.color.public_theme_color_normal);
        this.titleTextView = (TextView) findViewById(R.id.top_view_title);
        this.leftLayout = (FrameLayout) findViewById(R.id.top_view_left);
        this.rightLayout = (FrameLayout) findViewById(R.id.top_view_right);
        this.leftBackImage = (ImageView) findViewById(R.id.top_view_left_back);
        this.leftPhotoImage = (ImageView) findViewById(R.id.top_view_left_photo);
        this.rightImage = (ImageView) findViewById(R.id.top_view_right_image);
        this.leftTextView = (TextView) findViewById(R.id.top_view_left_text);
        this.rightTextView = (TextView) findViewById(R.id.top_view_right_text);
        this.titleImageView = (ImageView) findViewById(R.id.top_view_title_image);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.TouchView = findViewById(R.id.touch_view);
        this.TouchView.setOnTouchListener(new ECJiaTopView_1(this));
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setLeftBackImageSize(int i) {
        this.leftBackImage.getLayoutParams().height = this.mContext.getResources().getDimensionPixelOffset(i);
        this.leftBackImage.getLayoutParams().width = this.mContext.getResources().getDimensionPixelOffset(i);
    }

    public void setRightImageSize(int i) {
        this.rightImage.getLayoutParams().height = this.mContext.getResources().getDimensionPixelOffset(i);
        this.rightImage.getLayoutParams().width = this.mContext.getResources().getDimensionPixelOffset(i);
    }

    public void setTitleType(TitleType titleType) {
        switch (titleType) {
            case TEXT:
                this.titleTextView.setVisibility(0);
                this.titleImageView.setVisibility(8);
                this.tabLayout.setVisibility(8);
                return;
            case IMAGE:
                this.titleTextView.setVisibility(8);
                this.titleImageView.setVisibility(0);
                this.tabLayout.setVisibility(8);
                return;
            case TABLAYOUT:
                this.titleTextView.setVisibility(8);
                this.titleImageView.setVisibility(8);
                this.tabLayout.setVisibility(0);
                return;
            case NONE:
                this.titleTextView.setVisibility(8);
                this.titleImageView.setVisibility(8);
                this.tabLayout.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setLeftBackImageBackground(int i) {
        this.leftBackImage.setBackgroundResource(i);
    }

    public TextView getTitleTextView() {
        return this.titleTextView;
    }

    public void setRightImageBackground(int i) {
        this.rightImage.setBackgroundResource(i);
    }

    public void setLeftType(int i) {
        switch (i) {
            case 1:
                this.leftLayout.setVisibility(0);
                this.leftBackImage.setVisibility(0);
                this.leftBackImage.setImageResource(R.drawable.header_back_arrow);
                this.leftPhotoImage.setVisibility(8);
                this.leftTextView.setVisibility(8);
                return;
            case 2:
                this.leftLayout.setVisibility(0);
                this.leftBackImage.setVisibility(8);
                this.leftPhotoImage.setVisibility(0);
                this.leftTextView.setVisibility(8);
                return;
            case 3:
                this.leftLayout.setVisibility(0);
                this.leftBackImage.setVisibility(8);
                this.leftPhotoImage.setVisibility(8);
                this.leftTextView.setVisibility(0);
                return;
            case 4:
                this.leftLayout.setVisibility(8);
                this.leftBackImage.setVisibility(8);
                this.leftPhotoImage.setVisibility(8);
                this.leftTextView.setVisibility(8);
                return;
            case 5:
                this.leftLayout.setVisibility(0);
                this.leftBackImage.setVisibility(0);
                this.leftBackImage.setImageResource(R.drawable.header_back_arrow);
                this.leftPhotoImage.setVisibility(8);
                this.leftTextView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setTopViewBackground(int i) {
        setBackgroundResource(i);
    }

    public void setRightType(int i) {
        switch (i) {
            case 11:
                this.rightLayout.setVisibility(0);
                this.rightTextView.setVisibility(0);
                this.rightImage.setVisibility(8);
                return;
            case 12:
                this.rightLayout.setVisibility(0);
                this.rightTextView.setVisibility(8);
                this.rightImage.setVisibility(0);
                return;
            case 13:
                this.rightLayout.setVisibility(8);
                this.rightTextView.setVisibility(8);
                this.rightImage.setVisibility(8);
                return;
            case 14:
                this.rightLayout.setVisibility(8);
                this.rightTextView.setVisibility(8);
                this.rightImage.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setTitleText(String str) {
        this.titleTextView.setText(str);
    }

    public void setTitleText(int i) {
        this.titleTextView.setText(i);
    }

    public void setLeftOnClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.leftLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightOnClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.rightLayout.setOnClickListener(onClickListener);
        }
    }

    public void setLeftText(String str, OnClickListener onClickListener) {
        this.leftTextView.setText(str);
        if (onClickListener != null) {
            this.leftTextView.setOnClickListener(onClickListener);
        }
    }

    public void setLeftText(int i, OnClickListener onClickListener) {
        this.leftTextView.setText(i);
        if (onClickListener != null) {
            this.leftTextView.setOnClickListener(onClickListener);
        }
    }

    public void setRightText(String str, OnClickListener onClickListener) {
        this.rightTextView.setText(str);
        if (onClickListener != null) {
            this.rightTextView.setOnClickListener(onClickListener);
        }
    }

    public void setRightText(int i, OnClickListener onClickListener) {
        this.rightTextView.setText(i);
        if (onClickListener != null) {
            this.rightTextView.setOnClickListener(onClickListener);
        }
    }

    public void setRightText(int i) {
        this.rightTextView.setText(i);
    }

    public void setRightText(String str) {
        this.rightTextView.setText(str);
    }

    public void setRightTextEnable(boolean z) {
        this.rightTextView.setEnabled(z);
    }

    public void setLeftBackImage(int i, OnClickListener onClickListener) {
        this.leftBackImage.setImageResource(i);
        if (onClickListener != null) {
            this.leftBackImage.setOnClickListener(onClickListener);
        }
    }

    public void setLeftBackImage(Bitmap bitmap, OnClickListener onClickListener) {
        this.leftBackImage.setImageBitmap(bitmap);
        if (onClickListener != null) {
            this.leftBackImage.setOnClickListener(onClickListener);
        }
    }

    public void setLeftPhotoImage(int i, OnClickListener onClickListener) {
        this.leftPhotoImage.setImageResource(i);
        if (onClickListener != null) {
            this.leftPhotoImage.setOnClickListener(onClickListener);
        }
    }

    public void setLeftPhotoImage(Bitmap bitmap, OnClickListener onClickListener) {
        this.leftPhotoImage.setImageBitmap(bitmap);
        if (onClickListener != null) {
            this.leftPhotoImage.setOnClickListener(onClickListener);
        }
    }

    public void setRightImage(int i, OnClickListener onClickListener) {
        this.rightImage.setImageResource(i);
        if (onClickListener != null) {
            this.rightImage.setOnClickListener(onClickListener);
        }
    }

    public void setRightImage(Bitmap bitmap, OnClickListener onClickListener) {
        this.rightImage.setImageBitmap(bitmap);
        if (onClickListener != null) {
            this.rightImage.setOnClickListener(onClickListener);
        }
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setTitleImage(int i) {
        this.titleImageView.setImageResource(i);
    }

    public void setTitleImage(Bitmap bitmap) {
        this.titleImageView.setImageBitmap(bitmap);
    }

    public TextView getRightTextView() {
        return this.rightTextView;
    }

    public void setupWithViewPager(ViewPager viewPager) {
        this.tabLayout.setupWithViewPager(viewPager);
    }

    public void setTitleAnimationEnable(boolean z) {
        this.mAnimationEnable = z;
    }

    public void setOnTitleAnimationEndListener(a aVar) {
        this.mAnimationListener = aVar;
        postDelayed(new ECJiaTopView_2(this), 500);
    }

    public void startUpAnimation() {
        if (this.mAnimationEnable) {
            this.titleTextView.setVisibility(0);
            i a = i.a(this.tabLayout, "translationY", 0.0f, (float) (-this.tabLayout.getMeasuredHeight()));
            i a2 = i.a(this.titleTextView, "translationY", (float) this.titleTextView.getMeasuredHeight(), 0.0f);
            com.nineoldandroids.a.c cVar = new com.nineoldandroids.a.c();
            cVar.a(new ECJiaTopView_3(this));
            cVar.b(800).a(a, a2);
            cVar.a();
        }
    }

    public void startDownAnimation() {
        if (this.mAnimationEnable) {
            if (this.mAnimationListener != null) {
                this.mAnimationListener.a();
            }
            this.titleTextView.setVisibility(0);
            i a = i.a(this.tabLayout, "translationY", (float) (-this.tabLayout.getMeasuredHeight()), 0.0f);
            i a2 = i.a(this.titleTextView, "translationY", 0.0f, (float) this.titleTextView.getMeasuredHeight());
            com.nineoldandroids.a.c cVar = new com.nineoldandroids.a.c();
            cVar.a(new ECJiaTopView_4(this));
            cVar.b(800).a(a2, a);
            cVar.a();
        }
    }

    public TabLayout getTabLayout() {
        return this.tabLayout;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && this.isTouchEventConsumeAble) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setTouchEventConsumeAble(boolean z) {
        this.isTouchEventConsumeAble = z;
    }
}
