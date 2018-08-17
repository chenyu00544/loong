package com.ecjia.component.view.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ECJia_PHOTO;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class ECJiaBannerView<T> extends FrameLayout implements OnPageChangeListener {
    @Deprecated
    public static final int CENTER = 6;
    @Deprecated
    public static final int CIRCLE_INDICATOR = 1;
    @Deprecated
    public static final int CIRCLE_INDICATOR_TITLE = 4;
    @Deprecated
    public static final int LEFT = 5;
    @Deprecated
    public static final int NOT_INDICATOR = 0;
    @Deprecated
    public static final int NUM_INDICATOR = 2;
    @Deprecated
    public static final int NUM_INDICATOR_TITLE = 3;
    @Deprecated
    public static final int RIGHT = 7;
    private a adapter;
    boolean autoPlayEnable;
    private int bannerStyle;
    private TextView bannerTitle;
    private Context context;
    private int count;
    private int currentItem;
    private int defaultImage;
    private int delayTime;
    private int gravity;
    private Handler handler;
    private c imageListener;
    private List<ImageView> imageViews;
    private LinearLayout indicator;
    private List<ImageView> indicatorImages;
    private LinearLayout indicatorInside;
    private boolean isAutoPlay;
    private int lastPosition;
    private b listener;
    private int mIndicatorMargin;
    private int mIndicatorSelectedResId;
    private int mIndicatorUnselectedResId;
    private OnPageChangeListener mOnPageChangeListener;
    private int mRadius;
    private TextView numIndicator;
    private TextView numIndicatorInside;
    private final Runnable task;
    private int titleBackground;
    private int titleHeight;
    private int titleTextColor;
    private int titleTextSize;
    private LinearLayout titleView;
    private String[] titles;
    private ViewPager viewPager;

    class ECJiaBannerView_1 implements Runnable {
        final /* synthetic */ ECJiaBannerView a;

        ECJiaBannerView_1(ECJiaBannerView eCJiaBannerView) {
            this.a = eCJiaBannerView;
        }

        public void run() {
            if (this.a.isAutoPlay && this.a.count > 1) {
                this.a.currentItem = (this.a.currentItem % (this.a.count + 1)) + 1;
                if (this.a.currentItem == 1) {
                    this.a.viewPager.setCurrentItem(this.a.currentItem, true);
                } else {
                    this.a.viewPager.setCurrentItem(this.a.currentItem, true);
                }
                this.a.handler.postDelayed(this.a.task, (long) this.a.delayTime);
            }
        }
    }

    class a extends PagerAdapter {
        final /* synthetic */ ECJiaBannerView a;

        a(ECJiaBannerView eCJiaBannerView) {
            this.a = eCJiaBannerView;
        }

        public int getCount() {
            return this.a.imageViews.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            viewGroup.addView((View) this.a.imageViews.get(i));
            ImageView imageView = (ImageView) this.a.imageViews.get(i);
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.a.listener != null) {
                        this.b.a.listener.a(view, i - 1);
                    }
                }
            });
            return imageView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) this.a.imageViews.get(i));
        }
    }

    public interface b {
        void a(View view, int i);
    }

    public interface c<T> {
        void a(ImageView imageView, T t);
    }

    public ECJiaBannerView(Context context) {
        this(context, null);
    }

    public ECJiaBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIndicatorMargin = 5;
        this.mRadius = 10;
        this.bannerStyle = 0;
        this.delayTime = 5000;
        this.isAutoPlay = true;
        this.defaultImage = -1;
        this.count = 0;
        this.gravity = -1;
        this.lastPosition = 1;
        this.handler = new Handler();
        this.task = new ECJiaBannerView_1(this);
        this.context = context;
        this.imageViews = new ArrayList();
        this.indicatorImages = new ArrayList();
        initView(context, attributeSet);
    }

    private void handleTypedArray(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Banner);
            this.mRadius = obtainStyledAttributes.getDimensionPixelSize(10, 15);
            this.mIndicatorMargin = obtainStyledAttributes.getDimensionPixelSize(9, 5);
            this.mIndicatorSelectedResId = obtainStyledAttributes.getResourceId(11, R.drawable.point_press_bg);
            this.mIndicatorUnselectedResId = obtainStyledAttributes.getResourceId(12, R.drawable.point_normal_bg);
            this.defaultImage = obtainStyledAttributes.getResourceId(0, this.defaultImage);
            this.delayTime = obtainStyledAttributes.getDimensionPixelSize(1, 5000);
            this.isAutoPlay = obtainStyledAttributes.getBoolean(2, true);
            this.titleBackground = obtainStyledAttributes.getColor(3, -1);
            this.titleHeight = obtainStyledAttributes.getDimensionPixelSize(6, -1);
            this.titleTextColor = obtainStyledAttributes.getColor(4, -1);
            this.titleTextSize = obtainStyledAttributes.getDimensionPixelSize(5, -1);
            obtainStyledAttributes.recycle();
        }
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.imageViews.clear();
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true);
        this.viewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        this.titleView = (LinearLayout) inflate.findViewById(R.id.titleView);
        this.indicator = (LinearLayout) inflate.findViewById(R.id.indicator);
        this.indicatorInside = (LinearLayout) inflate.findViewById(R.id.indicatorInside);
        this.bannerTitle = (TextView) inflate.findViewById(R.id.bannerTitle);
        this.numIndicator = (TextView) inflate.findViewById(R.id.numIndicator);
        this.numIndicatorInside = (TextView) inflate.findViewById(R.id.numIndicatorInside);
        handleTypedArray(context, attributeSet);
    }

    public void setAutoPlayEnable(boolean z) {
        this.autoPlayEnable = z;
    }

    public void isAutoPlay(boolean z) {
        this.isAutoPlay = z;
        startAutoPlay();
    }

    public void setDelayTime(int i) {
        this.delayTime = i;
    }

    public void setIndicatorGravity(int i) {
        switch (i) {
            case 5:
                this.gravity = 19;
                return;
            case 6:
                this.gravity = 17;
                return;
            case 7:
                this.gravity = 21;
                return;
            default:
                return;
        }
    }

    public void setBannerTitleList(List<String> list) {
        setBannerTitle((String[]) list.toArray(new String[list.size()]));
    }

    public void setBannerAnimation(int i) {
        switch (i) {
            case 10:
                setPageTransformer(true, new ECJiaZoomOutPageTransformer());
                return;
            case 11:
                setPageTransformer(true, new ECJiaDepthPageTransformer());
                return;
            case 12:
                setPageTransformer(true, new ECJiaRotateDownPageTransformer());
                return;
            default:
                return;
        }
    }

    public void setPageTransformer(boolean z, PageTransformer pageTransformer) {
        this.viewPager.setPageTransformer(z, pageTransformer);
    }

    public void setBannerTitle(String[] strArr) {
        this.titles = strArr;
        if (this.bannerStyle == 4 || this.bannerStyle == 3 || this.bannerStyle == 5) {
            if (this.titleBackground != -1) {
                this.titleView.setBackgroundColor(this.titleBackground);
            }
            if (this.titleHeight != -1) {
                this.titleView.setLayoutParams(new LayoutParams(-1, this.titleHeight));
            }
            if (this.titleTextColor != -1) {
                this.bannerTitle.setTextColor(this.titleTextColor);
            }
            if (this.titleTextSize != -1) {
                this.bannerTitle.setTextSize((float) this.titleTextSize);
            }
            if (strArr != null && strArr.length > 0) {
                this.bannerTitle.setText(strArr[0]);
                this.bannerTitle.setVisibility(0);
                this.titleView.setVisibility(0);
            }
        }
    }

    public void setBannerStyle(int i) {
        this.bannerStyle = i;
        switch (i) {
            case 1:
                this.indicator.setVisibility(0);
                return;
            case 2:
                this.numIndicator.setVisibility(0);
                return;
            case 3:
                this.numIndicatorInside.setVisibility(0);
                return;
            case 4:
                this.indicator.setVisibility(0);
                return;
            case 5:
                this.indicatorInside.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void setImages(ArrayList<T> arrayList) {
        setImageList((ArrayList) arrayList, null);
    }

    public void setImages(T[] tArr) {
        setImageArray(tArr, null);
    }

    public void setImages(T[] tArr, c<T> cVar) {
        setImageArray(tArr, cVar);
    }

    public void setImages(List<String> list) {
        setImageList((List) list, null);
    }

    public void setImages(List<String> list, c cVar) {
        setImageList((List) list, cVar);
    }

    private void initImages() {
        this.imageViews.clear();
        if (this.bannerStyle == 1 || this.bannerStyle == 4 || this.bannerStyle == 5) {
            createIndicator();
        } else if (this.bannerStyle == 3) {
            this.numIndicatorInside.setText("1/" + this.count);
        } else if (this.bannerStyle == 2) {
            this.numIndicator.setText("1/" + this.count);
        }
    }

    private void setImageArray(T[] tArr, c<T> cVar) {
        if (tArr != null && tArr.length > 0) {
            this.count = tArr.length;
            initImages();
            for (int i = 0; i <= this.count + 1; i++) {
                Object obj;
                ImageView imageView = new ImageView(this.context);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                if (i == 0) {
                    obj = tArr[this.count - 1];
                } else if (i == this.count + 1) {
                    obj = tArr[0];
                } else {
                    obj = tArr[i - 1];
                }
                this.imageViews.add(imageView);
                q.a(((ECJia_PHOTO) obj).getUrl() + "=====((ECJia_PHOTO) url).getUrl()=======");
                if (cVar != null) {
                    cVar.a(imageView, obj);
                } else if (this.defaultImage != -1) {
                    ImageLoader.getInstance().displayImage(((ECJia_PHOTO) obj).getUrl(), imageView);
                } else {
                    ImageLoader.getInstance().displayImage(((ECJia_PHOTO) obj).getUrl(), imageView);
                }
            }
            setData();
        }
    }

    private void setImageList(ArrayList<T> arrayList, c<T> cVar) {
        if (arrayList != null && arrayList.size() > 0) {
            this.count = arrayList.size();
            initImages();
            for (int i = 0; i <= this.count + 1; i++) {
                Object obj;
                ImageView imageView = new ImageView(this.context);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                if (i == 0) {
                    obj = arrayList.get(this.count - 1);
                } else if (i == this.count + 1) {
                    obj = arrayList.get(0);
                } else {
                    obj = arrayList.get(i - 1);
                }
                this.imageViews.add(imageView);
                q.a(((ECJia_PHOTO) obj).getUrl() + "=====((ECJia_PHOTO) url).getUrl()=======");
                if (cVar != null) {
                    cVar.a(imageView, obj);
                } else if (this.defaultImage != -1) {
                    ImageLoader.getInstance().displayImage(((ECJia_PHOTO) obj).getUrl(), imageView);
                } else {
                    ImageLoader.getInstance().displayImage(((ECJia_PHOTO) obj).getUrl(), imageView);
                }
            }
            setData();
        }
    }

    private void setImageList(List<String> list, c cVar) {
        if (list != null && list.size() > 0) {
            this.count = list.size();
            initImages();
            for (int i = 0; i <= this.count + 1; i++) {
                Object obj;
                ImageView imageView = new ImageView(this.context);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                if (i == 0) {
                    obj = list.get(this.count - 1);
                } else if (i == this.count + 1) {
                    obj = list.get(0);
                } else {
                    obj = list.get(i - 1);
                }
                this.imageViews.add(imageView);
                if (cVar != null) {
                    cVar.a(imageView, obj);
                } else if (this.defaultImage != -1) {
                    ImageLoader.getInstance().displayImage(((ECJia_PHOTO) obj).getUrl(), imageView);
                } else {
                    ImageLoader.getInstance().displayImage(((ECJia_PHOTO) obj).getUrl(), imageView);
                }
            }
            setData();
        }
    }

    private void createIndicator() {
        this.indicatorImages.clear();
        this.indicator.removeAllViews();
        this.indicatorInside.removeAllViews();
        for (int i = 0; i < this.count; i++) {
            View imageView = new ImageView(this.context);
            imageView.setScaleType(ScaleType.CENTER_INSIDE);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mRadius, this.mRadius);
            layoutParams.leftMargin = this.mIndicatorMargin;
            layoutParams.rightMargin = this.mIndicatorMargin;
            if (i == 0) {
                imageView.setImageResource(this.mIndicatorSelectedResId);
            } else {
                imageView.setImageResource(this.mIndicatorUnselectedResId);
            }
            this.indicatorImages.add(imageView);
            if (this.bannerStyle == 1 || this.bannerStyle == 4) {
                this.indicator.addView(imageView, layoutParams);
            } else if (this.bannerStyle == 5) {
                this.indicatorInside.addView(imageView, layoutParams);
            }
        }
    }

    private void setData() {
        this.currentItem = 1;
        if (this.adapter == null) {
            this.adapter = new a(this);
            this.viewPager.setAdapter(this.adapter);
        } else {
            this.adapter.notifyDataSetChanged();
        }
        this.viewPager.setFocusable(true);
        this.viewPager.setCurrentItem(1);
        this.viewPager.setOnPageChangeListener(this);
        if (this.gravity != -1) {
            this.indicator.setGravity(this.gravity);
        }
        startAutoPlay();
    }

    private void startAutoPlay() {
        if (this.autoPlayEnable && this.isAutoPlay) {
            this.handler.removeCallbacks(this.task);
            this.handler.postDelayed(this.task, (long) this.delayTime);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.count > 1) {
            switch (motionEvent.getAction()) {
                case 0:
                    isAutoPlay(false);
                    break;
                case 1:
                case 3:
                    isAutoPlay(true);
                    break;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onPageScrollStateChanged(int i) {
        switch (i) {
            case 0:
                if (this.viewPager.getCurrentItem() == 0) {
                    this.viewPager.setCurrentItem(this.count, false);
                } else if (this.viewPager.getCurrentItem() == this.count + 1) {
                    this.viewPager.setCurrentItem(1, false);
                }
                this.currentItem = this.viewPager.getCurrentItem();
                this.isAutoPlay = true;
                break;
            case 1:
                this.isAutoPlay = false;
                break;
            case 2:
                this.isAutoPlay = true;
                break;
        }
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(i);
        }
        if (this.bannerStyle == 1 || this.bannerStyle == 4 || this.bannerStyle == 5) {
            ((ImageView) this.indicatorImages.get(((this.lastPosition - 1) + this.count) % this.count)).setImageResource(this.mIndicatorUnselectedResId);
            ((ImageView) this.indicatorImages.get(((i - 1) + this.count) % this.count)).setImageResource(this.mIndicatorSelectedResId);
            this.lastPosition = i;
        }
        if (i == 0) {
            i = 1;
        }
        switch (this.bannerStyle) {
            case 2:
                if (i > this.count) {
                    i = this.count;
                }
                this.numIndicator.setText(i + "/" + this.count);
                return;
            case 3:
                if (i > this.count) {
                    i = this.count;
                }
                this.numIndicatorInside.setText(i + "/" + this.count);
                if (this.titles != null && this.titles.length > 0) {
                    if (i > this.titles.length) {
                        i = this.titles.length;
                    }
                    this.bannerTitle.setText(this.titles[i - 1]);
                    return;
                }
                return;
            case 4:
                if (this.titles != null && this.titles.length > 0) {
                    if (i > this.titles.length) {
                        i = this.titles.length;
                    }
                    this.bannerTitle.setText(this.titles[i - 1]);
                    return;
                }
                return;
            case 5:
                if (this.titles != null && this.titles.length > 0) {
                    if (i > this.titles.length) {
                        i = this.titles.length;
                    }
                    this.bannerTitle.setText(this.titles[i - 1]);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setOnBannerClickListener(b bVar) {
        this.listener = bVar;
    }

    public void setOnBannerImageListener(c cVar) {
        this.imageListener = cVar;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }
}
