package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements TintableBackgroundView {
    private static final int[] TINT_ATTRS = new int[]{16843126};
    private AppCompatBackgroundHelper mBackgroundTintHelper;
    private AppCompatDrawableManager mDrawableManager;
    private AppCompatTextHelper mTextHelper;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mDrawableManager = AppCompatDrawableManager.get();
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, TINT_ATTRS, i, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            setDropDownBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        }
        obtainStyledAttributes.recycle();
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this, this.mDrawableManager);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.mTextHelper = AppCompatTextHelper.create(this);
        this.mTextHelper.loadFromAttributes(attributeSet, i);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setDropDownBackgroundResource(@DrawableRes int i) {
        if (this.mDrawableManager != null) {
            setDropDownBackgroundDrawable(this.mDrawableManager.getDrawable(getContext(), i));
        } else {
            super.setDropDownBackgroundResource(i);
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
        }
    }

    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Nullable
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
    }

    public void setSupportBackgroundTintMode(@Nullable Mode mode) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
        }
    }

    @Nullable
    public Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
        if (this.mTextHelper != null) {
            this.mTextHelper.applyCompoundDrawablesTints();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.mTextHelper != null) {
            this.mTextHelper.onSetTextAppearance(context, i);
        }
    }
}
