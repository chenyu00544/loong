package com.ecjia.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/* compiled from: ECJiaProfilePhotoUtil */
public class u {
    public static u a;
    static final /* synthetic */ boolean b = (!u.class.desiredAssertionStatus());
    private Bitmap c;
    private a d;

    /* compiled from: ECJiaProfilePhotoUtil */
    public interface a {
        void a();

        void b();
    }

    public u() {
        a = this;
    }

    public static u a() {
        if (a == null) {
            synchronized (u.class) {
                if (a == null) {
                    a = new u();
                }
            }
        }
        return a;
    }

    public void b() {
        this.c = null;
    }

    public void a(String str, final String str2) {
        RequestParams requestParams = new RequestParams(str);
        requestParams.setSaveFilePath(c(str2));
        x.http().get(requestParams, new CommonCallback<File>(this) {
            final /* synthetic */ u b;

            public void onSuccess(File file) {
                this.b.c = BitmapFactory.decodeFile(this.b.c(str2));
                if (this.b.d != null && !TextUtils.isEmpty(str2)) {
                    this.b.d.a();
                }
            }

            public void onError(Throwable th, boolean z) {
                if (this.b.d != null) {
                    this.b.d.b();
                }
            }

            public void onCancelled(CancelledException cancelledException) {
                Toast.makeText(x.app(), "cancelled", 1).show();
            }

            public void onFinished() {
            }
        });
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public boolean a(String str) {
        if (new File("sdcard/android/data/com.ecmoban.android.missmall/profile_photo/" + str + ".jpg").exists()) {
            q.a("该用户头像存在");
            return true;
        }
        q.a("该用户头像不存在");
        return false;
    }

    private String c(String str) {
        return "sdcard/android/data/com.ecmoban.android.missmall/profile_photo/" + str + ".jpg";
    }

    public void a(Bitmap bitmap, String str, Handler handler) {
        OutputStream fileOutputStream;
        this.c = bitmap;
        File file = new File("sdcard/android/data/com.ecmoban.android.missmall/profile_photo");
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(new File(file + "/" + str + ".jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        try {
            if (b || fileOutputStream != null) {
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                    if (handler != null) {
                        Message message = new Message();
                        message.obj = "save_profile_succeed";
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                    q.a("保存头像图片成功");
                    return;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            throw new AssertionError();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public Bitmap b(String str) {
        if (this.c != null) {
            return this.c;
        }
        if (!a(str)) {
            return null;
        }
        this.c = BitmapFactory.decodeFile(c(str));
        q.a("重新创建头像的bitmap对象");
        return this.c;
    }
}
