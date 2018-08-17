package com.ecjia.a;

import android.content.Context;
import android.widget.ImageView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;

/* compiled from: ECJiaImageLoaderForLV */
public class p extends r {
    private static Context b;
    private static p c;
    private DisplayImageOptions a;

    public static p a(Context context) {
        if (c == null) {
            c = new p();
        }
        b = context;
        return c;
    }

    public static p a() {
        if (c == null) {
            c = new p();
        }
        return c;
    }

    public void a(ImageView imageView, String str) {
        if (imageView != null && str != null && !str.equals((String) imageView.getTag())) {
            ImageLoader.getInstance().displayImage(str, imageView);
            imageView.setTag(str);
        }
    }

    public void a(ImageView imageView, String str, int i) {
        this.a = a(i);
        if (imageView != null && str != null && !str.equals((String) imageView.getTag())) {
            ImageLoader.getInstance().displayImage(str, imageView, this.a);
            imageView.setTag(str);
        }
    }

    private DisplayImageOptions a(int i) {
        switch (i) {
            case 9000:
                this.a = new Builder().showImageOnLoading((int) R.drawable.default_image).showImageOnFail((int) R.drawable.default_image).showImageForEmptyUri((int) R.drawable.default_image).cacheInMemory(true).cacheOnDisk(true).build();
                break;
            case m_AppUI.MSG_CLICK_ITEM /*9001*/:
                this.a = new Builder().showImageOnLoading((int) R.drawable.default_image).showImageOnFail((int) R.drawable.default_image).showImageForEmptyUri((int) R.drawable.default_image).cacheInMemory(true).cacheOnDisk(true).build();
                break;
            case 9002:
                this.a = new Builder().showImageOnLoading((int) R.drawable.icon_minefragment_help).showImageOnFail((int) R.drawable.icon_minefragment_help).showImageForEmptyUri((int) R.drawable.icon_minefragment_help).cacheInMemory(true).cacheOnDisk(true).build();
                break;
            case 9003:
                this.a = new Builder().showImageOnLoading((int) R.drawable.profile_no_avarta_icon_light).showImageOnFail((int) R.drawable.profile_no_avarta_icon_light).showImageForEmptyUri((int) R.drawable.profile_no_avarta_icon_light).cacheInMemory(true).cacheOnDisk(true).build();
                break;
            default:
                this.a = new Builder().showImageOnLoading((int) R.drawable.default_image).showImageOnFail((int) R.drawable.default_image).showImageForEmptyUri((int) R.drawable.default_image).cacheInMemory(true).cacheOnDisk(true).build();
                break;
        }
        return this.a;
    }
}
