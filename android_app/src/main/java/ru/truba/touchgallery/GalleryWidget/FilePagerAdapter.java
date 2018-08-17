package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.List;
import ru.truba.touchgallery.TouchView.FileTouchImageView;

public class FilePagerAdapter extends BasePagerAdapter {
    public FilePagerAdapter(Context context, List<String> list) {
        super(context, list);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        ((GalleryViewPager) viewGroup).mCurrentView = ((FileTouchImageView) obj).getImageView();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View fileTouchImageView = new FileTouchImageView(this.mContext);
        fileTouchImageView.setUrl((String) this.mResources.get(i));
        fileTouchImageView.setLayoutParams(new LayoutParams(-1, -1));
        viewGroup.addView(fileTouchImageView, 0);
        return fileTouchImageView;
    }
}
