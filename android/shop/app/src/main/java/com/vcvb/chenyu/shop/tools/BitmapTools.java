package com.vcvb.chenyu.shop.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
}
