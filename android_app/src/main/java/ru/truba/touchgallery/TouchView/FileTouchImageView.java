package ru.truba.touchgallery.TouchView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import ru.truba.touchgallery.TouchView.InputStreamWrapper.InputStreamProgressListener;

public class FileTouchImageView extends UrlTouchImageView {

    public class ImageLoadTask extends ru.truba.touchgallery.TouchView.UrlTouchImageView.ImageLoadTask {

        class FileTouchImageView_ImageLoadTask_1 implements InputStreamProgressListener {
            FileTouchImageView_ImageLoadTask_1() {
            }

            public void onProgress(float f, long j, long j2) {
                ImageLoadTask.this.publishProgress(new Integer[]{Integer.valueOf((int) (100.0f * f))});
            }
        }

        public ImageLoadTask() {
            super();
        }

        protected Bitmap doInBackground(String... strArr) {
            Bitmap decodeStream;
            Exception e;
            try {
                File file = new File(strArr[0]);
                InputStream inputStreamWrapper = new InputStreamWrapper(new FileInputStream(file), 8192, file.length());
                inputStreamWrapper.setProgressListener(new FileTouchImageView_ImageLoadTask_1());
                decodeStream = BitmapFactory.decodeStream(inputStreamWrapper);
                try {
                    inputStreamWrapper.close();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return decodeStream;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                decodeStream = null;
                e = exception;
                e.printStackTrace();
                return decodeStream;
            }
            return decodeStream;
        }
    }

    public FileTouchImageView(Context context) {
        super(context);
    }

    public FileTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setUrl(String str) {
        new ImageLoadTask().execute(new String[]{str});
    }
}
