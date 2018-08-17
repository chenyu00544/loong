package com.umeng.socialize.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.Toast;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.MailShareContent;
import com.umeng.socialize.media.SimpleShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Dummy;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.io.File;

public class EmailHandler extends UMSSOHandler {
    private static final String TAG = "umengsocial";

    public boolean share(Activity activity, ShareContent shareContent, UMShareListener uMShareListener) {
        return shareTo(activity, new MailShareContent(shareContent), (UMShareListener) Dummy.get(UMShareListener.class, uMShareListener));
    }

    public boolean shareTo(Activity activity, SimpleShareContent simpleShareContent, UMShareListener uMShareListener) {
        MailShareContent mailShareContent = (MailShareContent) simpleShareContent;
        String title = mailShareContent.getTitle();
        String title2 = mailShareContent.getTitle();
        Object text = mailShareContent.getText();
        UMImage image = mailShareContent.getImage();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TITLE", title);
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.SUBJECT", title2);
        if (!TextUtils.isEmpty(text)) {
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(text));
        }
        if (image != null) {
            File asFileImage = image.asFileImage();
            if (asFileImage != null) {
                Parcelable insertImage = SocializeUtils.insertImage(activity, asFileImage.toString());
                if (insertImage != null) {
                    intent.putExtra("android.intent.extra.STREAM", insertImage);
                    intent.setType("image/png;message/rfc822");
                    SocializeUtils.deleteUris.add(insertImage);
                }
            }
        }
        Pair packageInfo = getPackageInfo(activity, intent);
        if (packageInfo != null) {
            intent.setClassName((String) packageInfo.first, (String) packageInfo.second);
        }
        try {
            uMShareListener.onResult(SHARE_MEDIA.EMAIL);
            activity.startActivity(intent);
            return true;
        } catch (Throwable th) {
            Log.w("umengsocial", "" + th.toString());
            uMShareListener.onError(SHARE_MEDIA.EMAIL, th);
            if (Config.IsToastTip) {
                Toast.makeText(activity, "无法通过邮件分享！", 0).show();
            }
            return false;
        }
    }

    private Pair<String, String> getPackageInfo(Context context, Intent intent) {
        try {
            ResolveInfo resolveInfo = null;
            for (ResolveInfo resolveInfo2 : context.getPackageManager().queryIntentActivities(intent, 0)) {
                ResolveInfo resolveInfo22;
                if (!(resolveInfo22.activityInfo.packageName.endsWith(".gm") || resolveInfo22.activityInfo.name.toLowerCase().contains("gmail"))) {
                    resolveInfo22 = resolveInfo;
                }
                resolveInfo = resolveInfo22;
            }
            if (resolveInfo != null) {
                return new Pair(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            }
            Log.w("no found gmail package...");
            return null;
        } catch (Exception e) {
            Log.w("umengsocial", "", e);
        }
    }
}
