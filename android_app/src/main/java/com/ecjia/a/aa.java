package com.ecjia.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.StreamCorruptedException;

/* compiled from: ECJiaSharePUtil */
public class aa {
    public static String a(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 0).getString(str2, "");
    }

    public static void a(Context context, String str, String str2, String str3) {
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.commit();
    }

    public static <T> void a(Context context, String str, String str2, T t) {
        Editor edit = context.getSharedPreferences(str, 0).edit();
        if (t == null) {
            edit.putString(str2, null);
            edit.commit();
            return;
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(t);
            edit.putString(str2, new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0)));
            edit.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T b(Context context, String str, String str2) {
        T t = null;
        try {
            Object string = context.getSharedPreferences(str, 0).getString(str2, "");
            if (!TextUtils.isEmpty(string)) {
                t = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string, 0))).readObject();
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (OptionalDataException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
        }
        return t;
    }
}
