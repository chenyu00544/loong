package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.List;
import ru.truba.touchgallery.TouchView.UrlTouchImageView;

public class UrlPagerAdapter extends BasePagerAdapter {
    public UrlPagerAdapter(Context context, List<String> list) {
        super(context, list);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        ((GalleryViewPager) viewGroup).mCurrentView = ((UrlTouchImageView) obj).getImageView();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View urlTouchImageView = new UrlTouchImageView(this.mContext);
        urlTouchImageView.setUrl((String) this.mResources.get(i));
        urlTouchImageView.setLayoutParams(new LayoutParams(-1, -1));
        viewGroup.addView(urlTouchImageView, 0);
        return urlTouchImageView;
    }
}
