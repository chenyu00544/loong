package com.unionpay;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

final class b extends BroadcastReceiver {
    b() {
    }

    public final void onReceive(Context context, Intent intent) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        long longExtra = intent.getLongExtra("extra_download_id", -1);
        long c = UPUtils.c(context, "id");
        if (c == longExtra) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(c);
            String str = "";
            if (uriForDownloadedFile != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(uriForDownloadedFile.getPath()));
                    String absolutePath = context.getFilesDir().getAbsolutePath();
                    if (absolutePath != null) {
                        str = absolutePath + File.separator + a.A;
                        FileOutputStream openFileOutput = context.openFileOutput(a.A, 1);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            openFileOutput.write(bArr, 0, read);
                        }
                        openFileOutput.close();
                        fileInputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    str = uriForDownloadedFile.getPath();
                }
                try {
                    if (!a.y.equalsIgnoreCase(com.unionpay.utils.b.b(str))) {
                        com.unionpay.utils.b.c(uriForDownloadedFile.getPath());
                    } else if (!a.a(a.t)) {
                        intent2.setDataAndType(Uri.parse("file:" + str), a.F);
                        intent2.addFlags(268435456);
                        context.startActivity(intent2);
                    }
                } catch (FileNotFoundException e2) {
                }
                g.b("uppay", "downloadFileUri" + uriForDownloadedFile);
            }
            context.unregisterReceiver(a.K);
            a.z = false;
        }
    }
}
