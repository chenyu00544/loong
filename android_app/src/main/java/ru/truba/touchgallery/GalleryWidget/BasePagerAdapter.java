package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class BasePagerAdapter extends PagerAdapter {
    protected final Context mContext;
    protected int mCurrentPosition;
    protected OnItemChangeListener mOnItemChangeListener;
    protected final List<String> mResources;

    public interface OnItemChangeListener {
        void onItemChange(int i);
    }

    public BasePagerAdapter() {
        this.mCurrentPosition = -1;
        this.mResources = null;
        this.mContext = null;
    }

    public BasePagerAdapter(Context context, List<String> list) {
        this.mCurrentPosition = -1;
        this.mResources = list;
        this.mContext = context;
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        if (this.mCurrentPosition != i) {
            GalleryViewPager galleryViewPager = (GalleryViewPager) viewGroup;
            if (galleryViewPager.mCurrentView != null) {
                galleryViewPager.mCurrentView.resetScale();
            }
            this.mCurrentPosition = i;
            if (this.mOnItemChangeListener != null) {
                this.mOnItemChangeListener.onItemChange(this.mCurrentPosition);
            }
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.mResources.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    public void finishUpdate(ViewGroup viewGroup) {
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void startUpdate(ViewGroup viewGroup) {
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }

    public void setOnItemChangeListener(OnItemChangeListener onItemChangeListener) {
        this.mOnItemChangeListener = onItemChangeListener;
    }
}
