package com.ecjia.component.webimageview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class ECJiaWebImageView extends ImageView {
    AlphaAnimation animation;
    private Editor editor;
    private SharedPreferences shared;
    public String urlString;

    public ECJiaWebImageView(Context context) {
        super(context);
    }

    public ECJiaWebImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.animation = new AlphaAnimation(0.0f, 1.0f);
        this.animation.setDuration(500);
    }

    public ECJiaWebImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static void setMemoryCachingEnabled(boolean z) {
        a.a(z);
    }

    public static void setDiskCachingEnabled(boolean z) {
        a.b(z);
    }

    public static void setDiskCachingDefaultCacheTimeout(int i) {
        a.a(i);
    }

    public void onDetachedFromWindow() {
        cancelCurrentLoad();
    }

    public void setImageWithURL(Context context, String str, Drawable drawable, int i) {
        if (str == null) {
            return;
        }
        if (this.urlString == null || str.compareTo(this.urlString) != 0) {
            b a = b.a();
            if (this.urlString != null) {
                cancelCurrentLoad();
            }
            setImageDrawable(drawable);
            this.urlString = str;
            a.a(context, str, this, i);
        }
    }

    public void setImageWithURL(Context context, String str, Drawable drawable) {
        setImageWithURL(context, str, drawable, -1);
    }

    public void setImageWithURL(Context context, String str, int i) {
        setImageWithURL(context, str, getResources().getDrawable(i), -1);
    }

    public void setImageWithURL(Context context, String str, int i, int i2) {
        setImageWithURL(context, str, getResources().getDrawable(i), i2);
    }

    public void setImageWithURL(Context context, String str) {
        setImageWithURL(context, str, null, -1);
    }

    public void cancelCurrentLoad() {
        b.a().a(this);
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        startAnimation(this.animation);
    }
}
