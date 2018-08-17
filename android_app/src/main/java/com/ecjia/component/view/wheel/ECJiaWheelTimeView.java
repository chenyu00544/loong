package com.ecjia.component.view.wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.ecmoban.android.missmall.R;
import com.tencent.open.yyb.TitleBar;
import java.util.LinkedList;
import java.util.List;

public class ECJiaWheelTimeView<T extends ECJiaWheelData> extends View {
    private static final int ADDITIONAL_ITEMS_SPACE = 10;
    private static final int ADDITIONAL_ITEM_HEIGHT = 25;
    private static final int DEF_VISIBLE_ITEMS = 5;
    private static final int ITEMS_TEXT_COLOR = -3881788;
    private static final int LABEL_OFFSET = 8;
    private static final int MIN_DELTA_FOR_SCROLLING = 1;
    private static final int PADDING = 10;
    private static final int SCROLLING_DURATION = 400;
    private static final int[] SHADOWS_COLORS = new int[]{-1862270977, 1627389951, ViewCompat.MEASURED_SIZE_MASK};
    private static final int VALUE_TEXT_COLOR = -11184811;
    private final int ITEM_OFFSET = (this.TEXT_SIZE / 5);
    private final int MESSAGE_JUSTIFY = 1;
    private final int MESSAGE_SCROLL = 0;
    public int TEXT_SIZE;
    private e<T> adapter = null;
    private Handler animationHandler = new ECJiaWheelTimeView_2(this);
    private GradientDrawable bottomShadow;
    private Drawable centerDrawable;
    private List<b> changingListeners = new LinkedList();
    private int currentItem = 0;
    private GestureDetector gestureDetector;
    private SimpleOnGestureListener gestureListener = new ECJiaWheelTimeView_1(this);
    boolean isCyclic = false;
    private boolean isScrollingPerformed;
    private int itemHeight = 0;
    private StaticLayout itemsLayout;
    private TextPaint itemsPaint;
    private int itemsWidth = 0;
    private String label;
    private StaticLayout labelLayout;
    private int labelWidth = 0;
    private int lastScrollY;
    private Scroller scroller;
    private List<c> scrollingListeners = new LinkedList();
    private int scrollingOffset;
    private GradientDrawable topShadow;
    private StaticLayout valueLayout;
    private TextPaint valuePaint;
    private int visibleItems = 5;

    class ECJiaWheelTimeView_1 extends SimpleOnGestureListener {
        final /* synthetic */ ECJiaWheelTimeView a;

        ECJiaWheelTimeView_1(ECJiaWheelTimeView eCJiaWheelTimeView) {
            this.a = eCJiaWheelTimeView;
        }

        public boolean onDown(MotionEvent motionEvent) {
            if (!this.a.isScrollingPerformed) {
                return false;
            }
            this.a.scroller.forceFinished(true);
            this.a.clearMessages();
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.a.startScrolling();
            this.a.doScroll((int) (-f2));
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            int i;
            int i2;
            this.a.lastScrollY = (this.a.currentItem * this.a.getItemHeight()) + this.a.scrollingOffset;
            if (this.a.isCyclic) {
                i = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            } else {
                i = this.a.adapter.a() * this.a.getItemHeight();
            }
            if (this.a.isCyclic) {
                i2 = -i;
            } else {
                i2 = 0;
            }
            this.a.scroller.fling(0, this.a.lastScrollY, 0, ((int) (-f2)) / 2, 0, 0, i2, i);
            this.a.setNextMessage(0);
            return true;
        }
    }

    class ECJiaWheelTimeView_2 extends Handler {
        final /* synthetic */ ECJiaWheelTimeView a;

        ECJiaWheelTimeView_2(ECJiaWheelTimeView eCJiaWheelTimeView) {
            this.a = eCJiaWheelTimeView;
        }

        public void handleMessage(Message message) {
            this.a.scroller.computeScrollOffset();
            int currY = this.a.scroller.getCurrY();
            int access$500 = this.a.lastScrollY - currY;
            this.a.lastScrollY = currY;
            if (access$500 != 0) {
                this.a.doScroll(access$500);
            }
            if (Math.abs(currY - this.a.scroller.getFinalY()) < 1) {
                this.a.scroller.getFinalY();
                this.a.scroller.forceFinished(true);
            }
            if (!this.a.scroller.isFinished()) {
                this.a.animationHandler.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                this.a.justify();
            } else {
                this.a.finishScrolling();
            }
        }
    }

    public ECJiaWheelTimeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initData(context);
    }

    public ECJiaWheelTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData(context);
    }

    public ECJiaWheelTimeView(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        this.gestureDetector = new GestureDetector(context, this.gestureListener);
        this.gestureDetector.setIsLongpressEnabled(false);
        this.scroller = new Scroller(context);
    }

    public e<T> getAdapter() {
        return this.adapter;
    }

    public void setAdapter(e<T> eVar) {
        this.adapter = eVar;
        invalidateLayouts();
        invalidate();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.scroller.forceFinished(true);
        this.scroller = new Scroller(getContext(), interpolator);
    }

    public int getVisibleItems() {
        return this.visibleItems;
    }

    public void setVisibleItems(int i) {
        this.visibleItems = i;
        invalidate();
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        if (this.label == null || !this.label.equals(str)) {
            this.label = str;
            this.labelLayout = null;
            invalidate();
        }
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

    public void addScrollingListener(c cVar) {
        this.scrollingListeners.add(cVar);
    }

    public void removeScrollingListener(c cVar) {
        this.scrollingListeners.remove(cVar);
    }

    protected void notifyScrollingListenersAboutStart() {
        for (c a : this.scrollingListeners) {
            a.a(this);
        }
    }

    protected void notifyScrollingListenersAboutEnd() {
        for (c b : this.scrollingListeners) {
            b.b(this);
        }
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(int i, boolean z) {
        if (this.adapter != null && this.adapter.a() != 0) {
            if (i < 0 || i >= this.adapter.a()) {
                if (this.isCyclic) {
                    while (i < 0) {
                        i += this.adapter.a();
                    }
                    i %= this.adapter.a();
                } else {
                    return;
                }
            }
            if (i == this.currentItem) {
                return;
            }
            if (z) {
                scroll(i - this.currentItem, SCROLLING_DURATION);
                return;
            }
            invalidateLayouts();
            int i2 = this.currentItem;
            this.currentItem = i;
            notifyChangingListeners(i2, this.currentItem);
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
        invalidate();
        invalidateLayouts();
    }

    private void invalidateLayouts() {
        this.itemsLayout = null;
        this.valueLayout = null;
        this.scrollingOffset = 0;
    }

    private void initResourcesIfNecessary() {
        if (this.itemsPaint == null) {
            this.itemsPaint = new TextPaint(33);
            this.itemsPaint.setTextSize((float) this.TEXT_SIZE);
        }
        if (this.valuePaint == null) {
            this.valuePaint = new TextPaint(37);
            this.valuePaint.setTextSize((float) this.TEXT_SIZE);
            this.valuePaint.setShadowLayer(0.1f, 0.0f, 0.1f, -4144960);
        }
        if (this.centerDrawable == null) {
            this.centerDrawable = getContext().getResources().getDrawable(R.drawable.wheel_val);
        }
        if (this.topShadow == null) {
            this.topShadow = new GradientDrawable(Orientation.TOP_BOTTOM, SHADOWS_COLORS);
        }
        if (this.bottomShadow == null) {
            this.bottomShadow = new GradientDrawable(Orientation.BOTTOM_TOP, SHADOWS_COLORS);
        }
        setBackgroundResource(R.drawable.wheel_bg);
    }

    private int getDesiredHeight(Layout layout) {
        if (layout == null) {
            return 0;
        }
        return Math.max(((getItemHeight() * this.visibleItems) - (this.ITEM_OFFSET * 2)) - 25, getSuggestedMinimumHeight());
    }

    private String getTextItem(int i) {
        if (this.adapter == null || this.adapter.a() == 0) {
            return null;
        }
        int a = this.adapter.a();
        if ((i < 0 || i >= a) && !this.isCyclic) {
            return null;
        }
        while (i < 0) {
            i += a;
        }
        return ((ECJiaWheelData) this.adapter.b(i % a)).getStringData();
    }

    private String buildText(boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = (this.visibleItems / 2) + 1;
        int i2 = this.currentItem - i;
        while (i2 <= this.currentItem + i) {
            if (z || i2 != this.currentItem) {
                String textItem = getTextItem(i2);
                if (textItem != null) {
                    stringBuilder.append(textItem);
                }
            }
            if (i2 < this.currentItem + i) {
                stringBuilder.append("\n");
            }
            i2++;
        }
        return stringBuilder.toString();
    }

    private int getMaxTextLength() {
        e adapter = getAdapter();
        if (adapter == null) {
            return 0;
        }
        int b = adapter.b();
        if (b > 0) {
            return b;
        }
        String str = null;
        int max = Math.max(this.currentItem - (this.visibleItems / 2), 0);
        while (max < Math.min(this.currentItem + this.visibleItems, adapter.a())) {
            String stringData = ((ECJiaWheelData) adapter.b(max)).getStringData();
            if (stringData == null || (str != null && str.length() >= stringData.length())) {
                stringData = str;
            }
            max++;
            str = stringData;
        }
        return str != null ? str.length() : 0;
    }

    private int getItemHeight() {
        if (this.itemHeight != 0) {
            return this.itemHeight;
        }
        if (this.itemsLayout == null || this.itemsLayout.getLineCount() <= 2) {
            return getHeight() / this.visibleItems;
        }
        this.itemHeight = this.itemsLayout.getLineTop(2) - this.itemsLayout.getLineTop(1);
        return this.itemHeight;
    }

    private int calculateLayoutWidth(int i, int i2) {
        initResourcesIfNecessary();
        int maxTextLength = getMaxTextLength();
        if (maxTextLength > 0) {
            this.itemsWidth = (int) (Math.ceil((double) Layout.getDesiredWidth("0", this.itemsPaint)) * ((double) maxTextLength));
        } else {
            this.itemsWidth = 0;
        }
        this.itemsWidth += 10;
        this.labelWidth = 0;
        if (this.label != null && this.label.length() > 0) {
            this.labelWidth = (int) Math.ceil((double) Layout.getDesiredWidth(this.label, this.valuePaint));
        }
        if (i2 == 1073741824) {
            maxTextLength = 1;
        } else {
            maxTextLength = (this.itemsWidth + this.labelWidth) + 20;
            if (this.labelWidth > 0) {
                maxTextLength += 8;
            }
            maxTextLength = Math.max(maxTextLength, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= maxTextLength) {
                i = maxTextLength;
                maxTextLength = 0;
            } else {
                maxTextLength = 1;
            }
        }
        if (maxTextLength != 0) {
            maxTextLength = (i - 8) - 20;
            if (maxTextLength <= 0) {
                this.labelWidth = 0;
                this.itemsWidth = 0;
            }
            if (this.labelWidth > 0) {
                this.itemsWidth = (int) ((((double) this.itemsWidth) * ((double) maxTextLength)) / ((double) (this.itemsWidth + this.labelWidth)));
                this.labelWidth = maxTextLength - this.itemsWidth;
            } else {
                this.itemsWidth = maxTextLength + 8;
            }
        }
        if (this.itemsWidth > 0) {
            createLayouts(this.itemsWidth, this.labelWidth);
        }
        return i;
    }

    private void createLayouts(int i, int i2) {
        if (this.itemsLayout == null || this.itemsLayout.getWidth() > i) {
            this.itemsLayout = new StaticLayout(buildText(this.isScrollingPerformed), this.itemsPaint, i, i2 > 0 ? Alignment.ALIGN_OPPOSITE : Alignment.ALIGN_CENTER, 1.0f, 25.0f, false);
        } else {
            this.itemsLayout.increaseWidthTo(i);
        }
        if (!this.isScrollingPerformed && (this.valueLayout == null || this.valueLayout.getWidth() > i)) {
            CharSequence stringData;
            Alignment alignment;
            if (getAdapter() != null) {
                stringData = ((ECJiaWheelData) getAdapter().b(this.currentItem)).getStringData();
            } else {
                stringData = null;
            }
            if (stringData == null) {
                stringData = "";
            }
            TextPaint textPaint = this.valuePaint;
            if (i2 > 0) {
                alignment = Alignment.ALIGN_OPPOSITE;
            } else {
                alignment = Alignment.ALIGN_CENTER;
            }
            this.valueLayout = new StaticLayout(stringData, textPaint, i, alignment, 1.0f, 25.0f, false);
        } else if (this.isScrollingPerformed) {
            this.valueLayout = null;
        } else {
            this.valueLayout.increaseWidthTo(i);
        }
        if (i2 <= 0) {
            return;
        }
        if (this.labelLayout == null || this.labelLayout.getWidth() > i2) {
            this.labelLayout = new StaticLayout(this.label, this.valuePaint, i2, Alignment.ALIGN_NORMAL, 1.0f, 25.0f, false);
        } else {
            this.labelLayout.increaseWidthTo(i2);
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        size = calculateLayoutWidth(size, mode);
        if (mode2 != 1073741824) {
            mode = getDesiredHeight(this.itemsLayout);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(mode, size2) : mode;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.itemsLayout == null) {
            if (this.itemsWidth == 0) {
                calculateLayoutWidth(getWidth(), 1073741824);
            } else {
                createLayouts(this.itemsWidth, this.labelWidth);
            }
        }
        if (this.itemsWidth > 0) {
            canvas.save();
            canvas.translate(TitleBar.SHAREBTN_RIGHT_MARGIN, (float) (-this.ITEM_OFFSET));
            drawItems(canvas);
            drawValue(canvas);
            canvas.restore();
        }
        drawCenterRect(canvas);
        drawShadows(canvas);
    }

    private void drawShadows(Canvas canvas) {
        this.topShadow.setBounds(0, 0, getWidth(), getHeight() / this.visibleItems);
        this.topShadow.draw(canvas);
        this.bottomShadow.setBounds(0, getHeight() - (getHeight() / this.visibleItems), getWidth(), getHeight());
        this.bottomShadow.draw(canvas);
    }

    private void drawValue(Canvas canvas) {
        this.valuePaint.setColor(VALUE_TEXT_COLOR);
        this.valuePaint.drawableState = getDrawableState();
        Rect rect = new Rect();
        this.itemsLayout.getLineBounds(this.visibleItems / 2, rect);
        if (this.labelLayout != null) {
            canvas.save();
            canvas.translate((float) (this.itemsLayout.getWidth() + 8), (float) rect.top);
            this.labelLayout.draw(canvas);
            canvas.restore();
        }
        if (this.valueLayout != null) {
            canvas.save();
            canvas.translate(0.0f, (float) (rect.top + this.scrollingOffset));
            this.valueLayout.draw(canvas);
            canvas.restore();
        }
    }

    private void drawItems(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, (float) ((-this.itemsLayout.getLineTop(1)) + this.scrollingOffset));
        this.itemsPaint.setColor(ITEMS_TEXT_COLOR);
        this.itemsPaint.drawableState = getDrawableState();
        this.itemsLayout.draw(canvas);
        canvas.restore();
    }

    private void drawCenterRect(Canvas canvas) {
        int height = getHeight() / 2;
        int itemHeight = getItemHeight() / 2;
        this.centerDrawable.setBounds(0, height - itemHeight, getWidth(), height + itemHeight);
        this.centerDrawable.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(getAdapter() == null || this.gestureDetector.onTouchEvent(motionEvent) || motionEvent.getAction() != 1)) {
            justify();
        }
        return true;
    }

    private void doScroll(int i) {
        this.scrollingOffset += i;
        int itemHeight = this.scrollingOffset / getItemHeight();
        int i2 = this.currentItem - itemHeight;
        if (this.isCyclic && this.adapter.a() > 0) {
            while (i2 < 0) {
                i2 += this.adapter.a();
            }
            i2 %= this.adapter.a();
        } else if (!this.isScrollingPerformed) {
            i2 = Math.min(Math.max(i2, 0), this.adapter.a() - 1);
        } else if (i2 < 0) {
            itemHeight = this.currentItem;
            i2 = 0;
        } else if (i2 >= this.adapter.a()) {
            itemHeight = (this.currentItem - this.adapter.a()) + 1;
            i2 = this.adapter.a() - 1;
        }
        int i3 = this.scrollingOffset;
        if (i2 != this.currentItem) {
            setCurrentItem(i2, false);
        } else {
            invalidate();
        }
        this.scrollingOffset = i3 - (getItemHeight() * itemHeight);
        if (this.scrollingOffset > getHeight()) {
            this.scrollingOffset = (this.scrollingOffset % getHeight()) + getHeight();
        }
    }

    private void setNextMessage(int i) {
        clearMessages();
        this.animationHandler.sendEmptyMessage(i);
    }

    private void clearMessages() {
        this.animationHandler.removeMessages(0);
        this.animationHandler.removeMessages(1);
    }

    private void justify() {
        if (this.adapter != null) {
            this.lastScrollY = 0;
            int i = this.scrollingOffset;
            int itemHeight = getItemHeight();
            int i2 = i > 0 ? this.currentItem < this.adapter.a() ? 1 : 0 : this.currentItem > 0 ? 1 : 0;
            if ((this.isCyclic || r0 != 0) && Math.abs((float) i) > ((float) itemHeight) / 2.0f) {
                i = i < 0 ? i + (itemHeight + 1) : i - (itemHeight + 1);
            }
            if (Math.abs(i) > 1) {
                this.scroller.startScroll(0, 0, 0, i, SCROLLING_DURATION);
                setNextMessage(1);
                return;
            }
            finishScrolling();
        }
    }

    private void startScrolling() {
        if (!this.isScrollingPerformed) {
            this.isScrollingPerformed = true;
            notifyScrollingListenersAboutStart();
        }
    }

    void finishScrolling() {
        if (this.isScrollingPerformed) {
            notifyScrollingListenersAboutEnd();
            this.isScrollingPerformed = false;
        }
        invalidateLayouts();
        invalidate();
    }

    public void scroll(int i, int i2) {
        this.scroller.forceFinished(true);
        this.lastScrollY = this.scrollingOffset;
        int itemHeight = i * getItemHeight();
        this.scroller.startScroll(0, this.lastScrollY, 0, itemHeight - this.lastScrollY, i2);
        setNextMessage(0);
        startScrolling();
    }
}
