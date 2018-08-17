package ru.truba.touchgallery.TouchView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import ru.truba.touchgallery.R;
import ru.truba.touchgallery.TouchView.InputStreamWrapper.InputStreamProgressListener;

public class UrlTouchImageView extends RelativeLayout {
    protected Context mContext;
    protected TouchImageView mImageView;
    protected ProgressBar mProgressBar;

    public class ImageLoadTask extends AsyncTask<String, Integer, Bitmap> {

        class UrlTouchImageView_ImageLoadTask_1 implements InputStreamProgressListener {
            UrlTouchImageView_ImageLoadTask_1() {
            }

            public void onProgress(float f, long j, long j2) {
                ImageLoadTask.this.publishProgress(new Integer[]{Integer.valueOf((int) (100.0f * f))});
            }
        }

        protected Bitmap doInBackground(String... strArr) {
            Bitmap decodeStream;
            Exception e;
            try {
                URLConnection openConnection = new URL(strArr[0]).openConnection();
                openConnection.connect();
                InputStream inputStream = openConnection.getInputStream();
                InputStream inputStreamWrapper = new InputStreamWrapper(inputStream, 8192, (long) openConnection.getContentLength());
                inputStreamWrapper.setProgressListener(new UrlTouchImageView_ImageLoadTask_1());
                decodeStream = BitmapFactory.decodeStream(inputStreamWrapper);
                try {
                    inputStreamWrapper.close();
                    inputStream.close();
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

        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                UrlTouchImageView.this.mImageView.setScaleType(ScaleType.CENTER);
                UrlTouchImageView.this.mImageView.setImageBitmap(BitmapFactory.decodeResource(UrlTouchImageView.this.getResources(), R.drawable.no_photo));
            } else {
                UrlTouchImageView.this.mImageView.setScaleType(ScaleType.MATRIX);
                UrlTouchImageView.this.mImageView.setImageBitmap(bitmap);
            }
            UrlTouchImageView.this.mImageView.setVisibility(0);
            UrlTouchImageView.this.mProgressBar.setVisibility(8);
        }

        protected void onProgressUpdate(Integer... numArr) {
        }
    }

    public UrlTouchImageView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public UrlTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    public TouchImageView getImageView() {
        return this.mImageView;
    }

    @TargetApi(16)
    protected void init() {
        this.mImageView = new TouchImageView(this.mContext);
        this.mImageView.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.mImageView);
        this.mImageView.setVisibility(8);
        this.mProgressBar = new ProgressBar(this.mContext, null);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13);
        layoutParams.setMargins(70, 0, 70, 0);
        this.mProgressBar.setLayoutParams(layoutParams);
        this.mProgressBar.setIndeterminate(true);
        this.mProgressBar.setMax(100);
        this.mProgressBar.setBackgroundResource(R.drawable.progressbardialog_bg);
        this.mProgressBar.setIndeterminateDrawable(this.mContext.getResources().getDrawable(R.drawable.progress_large));
        addView(this.mProgressBar);
    }

    public void setUrl(String str) {
        new ImageLoadTask().execute(new String[]{str});
    }
}
