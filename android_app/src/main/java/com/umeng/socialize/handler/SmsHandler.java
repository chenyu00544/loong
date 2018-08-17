package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.provider.Telephony.Sms;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.SocializeUtils;

public final class SmsHandler extends UMSSOHandler {
    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        return shareSms(activity, new SmsShareContent(shareContent), uMShareListener);
    }

    private boolean shareSms(Context context, SmsShareContent smsShareContent, UMShareListener uMShareListener) {
        Intent intent;
        String text = smsShareContent.getText();
        UMImage image = smsShareContent.getImage();
        boolean isAppInstalled = DeviceConfig.isAppInstalled("com.android.mms", context);
        if (isAppInstalled) {
            if (image != null) {
                intent = new Intent("android.intent.action.SEND");
                if (isAppInstalled) {
                    try {
                        Class.forName("com.android.mms.ui.ComposeMessageActivity");
                        intent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Parcelable insertImage = SocializeUtils.insertImage(context, image.asFileImage().getPath());
                intent.putExtra("android.intent.extra.STREAM", insertImage);
                intent.setType("image/*");
                SocializeUtils.deleteUris.add(insertImage);
            } else {
                intent = new Intent("android.intent.action.VIEW");
                intent.setType("vnd.android-dir/mms-sms");
            }
        } else if (VERSION.SDK_INT >= 19) {
            String defaultSmsPackage = Sms.getDefaultSmsPackage(context);
            intent = new Intent("android.intent.action.SEND");
            if (image == null) {
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", text);
            } else {
                intent.setType("image/*");
                intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(image.asFileImage()));
                intent.putExtra("android.intent.extra.TEXT", text);
            }
            if (defaultSmsPackage != null) {
                intent.setPackage(defaultSmsPackage);
            }
        } else {
            intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
            if (image != null) {
                intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(image.asFileImage()));
                intent.setType("image/*");
            }
        }
        intent.putExtra("sms_body", text);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
            uMShareListener.onResult(SHARE_MEDIA.SMS);
        } catch (ActivityNotFoundException e2) {
        }
        return false;
    }
}
