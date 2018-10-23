package com.vcvb.chenyu.shop.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.R;

import java.lang.ref.SoftReference;

public class BitmapTools {

    public BitmapTools() {

    }

    public static Bitmap decodeBitmap(byte[] data, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        options.inSampleSize = calculateInSimpleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    public static int calculateInSimpleSize(BitmapFactory.Options options, int reqWidth, int
            reqHeight) {
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        int inSimpleSize = 1;
        if (imageWidth > reqWidth || imageHeight > reqHeight) {
            final int widthRatio = Math.round((float) imageWidth / (float) reqWidth);
            final int heightRatio = Math.round((float) imageHeight / (float) reqHeight);

            inSimpleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSimpleSize;
    }

    static class AsyncDrawable extends BitmapDrawable {
        private final SoftReference<BitmapWorkerTask> softReference;

        public AsyncDrawable(Resources resources, Bitmap bitmap, BitmapWorkerTask
                bitmapWorkerTask) {
            super(resources, bitmap);
            softReference = new SoftReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return softReference.get();
        }
    }

    static class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private SoftReference<ImageView> imageViewSoftReference;
        private String data = "";

        public BitmapWorkerTask(ImageView imageView) {
            imageViewSoftReference = new SoftReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            data = strings[0];
            byte[] result = HttpUtils.sendPost(data);
            return BitmapTools.decodeBitmap(result, 100, 100);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewSoftReference != null && bitmap != null) {
                final ImageView imageView = imageViewSoftReference.get();
                final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    public static boolean cancelPotntialWork(String data, ImageView imageView){
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if(bitmapWorkerTask != null){
            final String bitmapData = bitmapWorkerTask.data;
            if(bitmapData != data){
                bitmapWorkerTask.cancel(true);
            }else{
                return false;
            }
        }
        return true;
    }

    public static void loadBitmap(Resources resources, String data, ImageView imageView){
        Bitmap placeBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_no_pic);
        if(cancelPotntialWork(data, imageView)){
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(resources, placeBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(data);
        }
    }
}
