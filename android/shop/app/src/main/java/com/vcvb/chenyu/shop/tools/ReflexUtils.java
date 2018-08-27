package com.vcvb.chenyu.shop.tools;

import android.content.Context;
import android.util.Log;

public class ReflexUtils {
    public ReflexUtils() {
    }

    public static int getResByRid(String typeName,String fieldName,Context context) {
        int i = -1;
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".R$"+typeName);
            i = clazz.getField(fieldName).getInt(null);
        } catch (Exception e) {
            Log.d(""+context.getClass(),"没有找到"+  context.getPackageName() +".R$"+typeName+"类型资源 "+fieldName+"请copy相应文件到对应的目录.");
            return -1;
        }
        return i;
    }
}
