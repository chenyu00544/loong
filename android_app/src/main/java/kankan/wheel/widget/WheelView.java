package kankan.wheel.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;
import kankan.wheel.widget.f.a;

public class WheelView extends View {
    private static final int DEF_VISIBLE_ITEMS = 5;
    private static final int ITEM_OFFSET_PERCENT = 0;
    private static final int PADDING = 0;
    private GradientDrawable bottomShadow;
    private Drawable centerDrawable;
    private List<b> changingListeners = new LinkedList();
    private List<c> clickingListeners = new LinkedList();
    private int currentItem = 0;
    private DataSetObserver dataObserver = new WheelView_2(this);
    private boolean drawShadows = false;
    private int firstItem;
    boolean isCyclic = false;
    private boolean isScrollingPerformed;
    private int itemHeight = 0;
    private LinearLayout itemsLayout;
    private e recycle = new e(this);
    private f scroller;
    a scrollingListener = new WheelView_1(this);
    private List<d> scrollingListeners = new LinkedList();
    private int scrollingOffset;
    private GradientDrawable topShadow;
    private kankan.wheel.widget.a.a viewAdapter;
    private int visibleItems = 5;
    private int wheelBackground = kankan.wheel.a.a.wheel_bg;
    private int wheelForeground = kankan.wheel.a.a.wheel_val;

    class WheelView_1 implements a {
        final /* synthetic */ WheelView a;

        WheelView_1(WheelView wheelView) {
            this.a = wheelView;
        }

        public void a() {
            this.a.isScrollingPerformed = true;
            this.a.notifyScrollingListenersAboutStart();
        }

        public void a(int i) {
            this.a.doScroll(i);
            int height = this.a.getHeight();
            if (this.a.scrollingOffset > height) {
                this.a.scrollingOffset = height;
                this.a.scroller.a();
            } else if (this.a.scrollingOffset < (-height)) {
                this.a.scrollingOffset = -height;
                this.a.scroller.a();
            }
        }

        public void b() {
            if (this.a.isScrollingPerformed) {
                this.a.notifyScrollingListenersAboutEnd();
                this.a.isScrollingPerformed = false;
            }
            this.a.scrollingOffset = 0;
            this.a.invalidate();
        }

        public void c() {
            if (Math.abs(this.a.scrollingOffset) > 1) {
                this.a.scroller.a(this.a.scrollingOffset, 0);
            }
        }
    }

    class WheelView_2 extends DataSetObserver {
        final /* synthetic */ WheelView a;

        WheelView_2(WheelView wheelView) {
            this.a = wheelView;
        }

        public void onChanged() {
            this.a.invalidateWheel(false);
        }

        public void onInvalidated() {
            this.a.invalidateWheel(true);
        }
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initData(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData(context);
    }

    public WheelView(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        this.scroller = new f(getContext(), this.scrollingListener);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.scroller.a(interpolator);
    }

    public int getVisibleItems() {
        return this.visibleItems;
    }

    public void setVisibleItems(int i) {
        this.visibleItems = i;
    }

    public kankan.wheel.widget.a.a getViewAdapter() {
        return this.viewAdapter;
    }

    public void setViewAdapter(kankan.wheel.widget.a.a aVar) {
        if (this.viewAdapter != null) {
            this.viewAdapter.b(this.dataObserver);
        }
        this.viewAdapter = aVar;
        if (this.viewAdapter != null) {
            this.viewAdapter.a(this.dataObserver);
        }
        invalidateWheel(true);
    }

    public void addChangingListener(b bVar) {
        this.changingListeners.add(bVar);
    }

    public void removeChangingListener(b bVar) {
        this.changingListeners.remove(bVar);
    }

    protected void notifyChangingListeners(int i, int i2) {
        for (b a : this.changingListeners) {
            a.a(this, i, i2);
        }
    }

    public void addScrollingListener(d dVar) {
        this.scrollingListeners.add(dVar);
    }

    public void removeScrollingListener(d dVar) {
        this.scrollingListeners.remove(dVar);
    }

    protected void notifyScrollingListenersAboutStart() {
        for (d a : this.scrollingListeners) {
            a.a(this);
        }
    }

    protected void notifyScrollingListenersAboutEnd() {
        for (d b : this.scrollingListeners) {
            b.b(this);
        }
    }

    public void addClickingListener(c cVar) {
        this.clickingListeners.add(cVar);
    }

    public void removeClickingListener(c cVar) {
        this.clickingListeners.remove(cVar);
    }

    protected void notifyClickListenersAboutClick(int i) {
        for (c a : this.clickingListeners) {
            a.a(this, i);
        }
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(int i, boolean z) {
        if (this.viewAdapter != null && this.viewAdapter.a() != 0) {
            int a = this.viewAdapter.a();
            if (i < 0 || i >= a) {
                if (this.isCyclic) {
                    while (i < 0) {
                        i += a;
                        this.itemHeight = 70;
                    }
                    i %= a;
                } else {
                    return;
                }
            }
            if (i == this.currentItem) {
                return;
            }
            if (z) {
                int i2 = i - this.currentItem;
                if (this.isCyclic) {
                    a = (a + Math.min(i, this.currentItem)) - Math.max(i, this.currentItem);
                    if (a < Math.abs(i2)) {
                        if (i2 >= 0) {
                            a = -a;
                        }
                        scroll(a, 0);
                        return;
                    }
                }
                a = i2;
                scroll(a, 0);
                return;
            }
            this.scrollingOffset = 0;
            a = this.currentItem;
            this.currentItem = i;
            notifyChangingListeners(a, this.currentItem);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    public boolean isCyclic() {
        return this.isCyclic;
    }

    public void setCyclic(boolean z) {
        this.isCyclic = z;
        invalidateWheel(false);
    }

    public void setWheelBackground(int i) {
        this.wheelBackground = i;
        setBackgroundResource(this.wheelBackground);
    }

    public void setWheelForeground(int i) {
        this.wheelForeground = i;
        this.centerDrawable = getContext().getResources().getDrawable(this.wheelForeground);
    }

    public void invalidateWheel(boolean z) {
        if (z) {
            this.recycle.c();
            if (this.itemsLayout != null) {
                this.itemsLayout.removeAllViews();
            }
            this.scrollingOffset = 0;
        } else if (this.itemsLayout != null) {
            this.recycle.a(this.itemsLayout, this.firstItem, new a());
        }
        invalidate();
    }

    private void initResourcesIfNecessary() {
        if (this.centerDrawable == null) {
            this.centerDrawable = getContext().getResources().getDrawable(this.wheelForeground);
        }
        setBackgroundResource(this.wheelBackground);
    }

    private int getDesiredHeight(LinearLayout linearLayout) {
        if (!(linearLayout == null || linearLayout.getChildAt(0) == null)) {
            this.itemHeight = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        return Math.max((this.itemHeight * this.visibleItems) - ((this.itemHeight * 0) / 50), getSuggestedMinimumHeight());
    }

    private int getItemHeight() {
        if (this.itemHeight != 0) {
            return this.itemHeight;
        }
        if (this.itemsLayout == null || this.itemsLayout.getChildAt(0) == null) {
            return getHeight() / this.visibleItems;
        }
        this.itemHeight = this.itemsLayout.getChildAt(0).getHeight();
        return this.itemHeight;
    }

    private int calculateLayoutWidth(int i, int i2) {
        initResourcesIfNecessary();
        this.itemsLayout.setLayoutParams(new LayoutParams(-2, -2));
        this.itemsLayout.measure(MeasureSpec.makeMeasureSpec(i, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.itemsLayout.getMeasuredWidth();
        if (i2 != 1073741824) {
            measuredWidth = Math.max(measuredWidth + 0, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= measuredWidth) {
                i = measuredWidth;
            }
        }
        this.itemsLayout.measure(MeasureSpec.makeMeasureSpec(i + 0, 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        buildViewForMeasuring();
        size = calculateLayoutWidth(size, mode);
        if (mode2 != 1073741824) {
            mode = getDesiredHeight(this.itemsLayout);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(mode, size2) : mode;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layout(i3 - i, i4 - i2);
    }

    private void layout(int i, int i2) {
        this.itemsLayout.layout(0, 0, i + 0, i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.viewAdapter != null && this.viewAdapter.a() > 0) {
            updateView();
            drawItems(canvas);
            drawCenterRect(canvas);
        }
        if (this.drawShadows) {
            drawShadows(canvas);
        }
    }

    private void drawShadows(Canvas canvas) {
        int itemHeight = (int) (1.5d * ((double) getItemHeight()));
        this.topShadow.setBounds(0, 0, getWidth(), itemHeight);
        this.topShadow.draw(canvas);
        this.bottomShadow.setBounds(0, getHeight() - itemHeight, getWidth(), getHeight());
        this.bottomShadow.draw(canvas);
    }

    private void drawItems(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, (float) ((-(((this.currentItem - this.firstItem) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.scrollingOffset));
        this.itemsLayout.draw(canvas);
        canvas.restore();
    }

    private void drawCenterRect(Canvas canvas) {
        int height = getHeight() / 2;
        int itemHeight = (int) (((double) (getItemHeight() / 2)) * 1.2d);
        this.centerDrawable.setBounds(0, height - itemHeight, getWidth(), height + itemHeight);
        this.centerDrawable.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 1:
                if (!this.isScrollingPerformed) {
                    int y = ((int) motionEvent.getY()) - (getHeight() / 2);
                    if (y > 0) {
                        y += getItemHeight() / 2;
                    } else {
                        y -= getItemHeight() / 2;
                    }
                    y /= getItemHeight();
                    if (y != 0 && isValidItemIndex(this.currentItem + y)) {
                        notifyClickListenersAboutClick(y + this.currentItem);
                        break;
                    }
                }
                break;
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
        }
        return this.scroller.a(motionEvent);
    }

    private void doScroll(int i) {
        this.scrollingOffset += i;
        int itemHeight = getItemHeight();
        int i2 = this.scrollingOffset / itemHeight;
        int i3 = this.currentItem - i2;
        int a = this.viewAdapter.a();
        int i4 = this.scrollingOffset % itemHeight;
        if (Math.abs(i4) <= itemHeight / 2) {
            i4 = 0;
        }
        if (this.isCyclic && a > 0) {
            if (i4 > 0) {
                i4 = i3 - 1;
                i3 = i2 + 1;
            } else if (i4 < 0) {
                i4 = i3 + 1;
                i3 = i2 - 1;
            } else {
                i4 = i3;
                i3 = i2;
            }
            while (i4 < 0) {
                i4 += a;
            }
            i4 %= a;
        } else if (i3 < 0) {
            i3 = this.currentItem;
            i4 = 0;
        } else if (i3 >= a) {
            i3 = (this.currentItem - a) + 1;
            i4 = a - 1;
        } else if (i3 > 0 && i4 > 0) {
            i4 = i3 - 1;
            i3 = i2 + 1;
        } else if (i3 >= a - 1 || i4 >= 0) {
            i4 = i3;
            i3 = i2;
        } else {
            i4 = i3 + 1;
            i3 = i2 - 1;
        }
        i2 = this.scrollingOffset;
        if (i4 != this.currentItem) {
            setCurrentItem(i4, false);
        } else {
            invalidate();
        }
        this.scrollingOffset = i2 - (i3 * itemHeight);
        if (this.scrollingOffset > getHeight()) {
            this.scrollingOffset = (this.scrollingOffset % getHeight()) + getHeight();
        }
    }

    public void scroll(int i, int i2) {
        this.scroller.a((getItemHeight() * i) - this.scrollingOffset, i2);
    }

    private a getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }
        int i = this.currentItem;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        if (this.scrollingOffset != 0) {
            if (this.scrollingOffset > 0) {
                i--;
            }
            int itemHeight = this.scrollingOffset / getItemHeight();
            i -= itemHeight;
            i2 = (int) (Math.asin((double) itemHeight) + ((double) (i2 + 1)));
        }
        return new a(i, i2);
    }

    private boolean rebuildItems() {
        int a;
        boolean z;
        a itemsRange = getItemsRange();
        if (this.itemsLayout != null) {
            a = this.recycle.a(this.itemsLayout, this.firstItem, itemsRange);
            z = this.firstItem != a;
            this.firstItem = a;
        } else {
            createItemsLayout();
            z = true;
        }
        if (!z) {
            if (this.firstItem == itemsRange.a() && this.itemsLayout.getChildCount() == itemsRange.c()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.firstItem <= itemsRange.a() || this.firstItem > itemsRange.b()) {
            this.firstItem = itemsRange.a();
        } else {
            a = this.firstItem - 1;
            while (a >= itemsRange.a() && addViewItem(a, true)) {
                this.firstItem = a;
                a--;
            }
        }
        a = this.firstItem;
        for (int childCount = this.itemsLayout.getChildCount(); childCount < itemsRange.c(); childCount++) {
            if (!addViewItem(this.firstItem + childCount, false) && this.itemsLayout.getChildCount() == 0) {
                a++;
            }
        }
        this.firstItem = a;
        return z;
    }

    private void updateView() {
        if (rebuildItems()) {
            calculateLayoutWidth(getWidth(), 1073741824);
            layout(getWidth(), getHeight());
        }
    }

    private void createItemsLayout() {
        if (this.itemsLayout == null) {
            this.itemsLayout = new LinearLayout(getContext());
            this.itemsLayout.setOrientation(1);
        }
    }

    private void buildViewForMeasuring() {
        if (this.itemsLayout != null) {
            this.recycle.a(this.itemsLayout, this.firstItem, new a());
        } else {
            createItemsLayout();
        }
        int i = this.visibleItems / 2;
        for (int i2 = this.currentItem + i; i2 >= this.currentItem - i; i2--) {
            if (addViewItem(i2, true)) {
                this.firstItem = i2;
            }
        }
    }

    private boolean addViewItem(int i, boolean z) {
        View itemView = getItemView(i);
        if (itemView == null) {
            return false;
        }
        if (z) {
            this.itemsLayout.addView(itemView, 0);
        } else {
            this.itemsLayout.addView(itemView);
        }
        return true;
    }

    private boolean isValidItemIndex(int i) {
        return this.viewAdapter != null && this.viewAdapter.a() > 0 && (this.isCyclic || (i >= 0 && i < this.viewAdapter.a()));
    }

    private View getItemView(int i) {
        if (this.viewAdapter == null || this.viewAdapter.a() == 0) {
            return null;
        }
        int a = this.viewAdapter.a();
        if (!isValidItemIndex(i)) {
            return this.viewAdapter.a(this.recycle.b(), this.itemsLayout);
        }
        while (i < 0) {
            i += a;
        }
        return this.viewAdapter.a(i % a, this.recycle.a(), this.itemsLayout);
    }

    public void stopScrolling() {
        this.scroller.a();
    }
}
