package com.ecjia.a;

import android.content.Context;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/* compiled from: ECJiaFileUtil */
public class j {
    public static void a(String str, String str2, String str3) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(str + "/" + str2);
        if (file.exists()) {
            file.delete();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print(str3);
            printStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static String a(Context context, String str) {
        try {
            return new String(a(context.getAssets().open(str)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2) {
        File file = new File(str + "/" + str2);
        String str3 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            InputStream fileInputStream = new FileInputStream(file);
            Reader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            str3 = bufferedReader.readLine();
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return str3;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return str3;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str3;
        } catch (IOException e3) {
            e3.printStackTrace();
            return str3;
        }
    }

    private static byte[] a(InputStream inputStream) {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read();
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(read);
            } catch (IOException e) {
                IOException iOException = e;
                bArr = null;
                IOException iOException2 = iOException;
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            iOException2 = e2;
            iOException2.printStackTrace();
            return bArr;
        }
        return bArr;
    }
}
