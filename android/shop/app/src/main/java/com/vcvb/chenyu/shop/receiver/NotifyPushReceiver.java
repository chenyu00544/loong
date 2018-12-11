package com.vcvb.chenyu.shop.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.UrlParse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class NotifyPushReceiver extends PushMessageReceiver {
    private NotificationManager notificationManager;
    private Notification.Builder builder;

    public NotifyPushReceiver() {
        super();
    }

    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {

    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(final Context context, String s, String s1) {
        notificationManager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context);

        HttpUtils.getInstance().post(ConstantManager.Url.NOTIFY_GET, null, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        if (json.getInt("code") == 0) {
                            JSONObject object = json.getJSONObject("data");

                            builder.setContentTitle(object.getString("title"));
                            builder.setContentText(object.getString("describe"));
                            builder.setSmallIcon(R.drawable.shop_logo);
                            builder.setWhen(System.currentTimeMillis());
                            builder.setDefaults(Notification.DEFAULT_ALL);

                            String uri = object.getString("url");
                            Class c = UrlParse.getUrlToClass(uri);
                            if (c != null) {
                                Map<String, String> id = UrlParse.getUrlParams(uri);
                                Intent intent = new Intent(context, c);
                                intent.putExtra("id", Integer.valueOf(id.get("id")));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("NotificationClick");
                                PendingIntent pendingIntent = PendingIntent.
                                        getBroadcast(context, 0, intent, 0);
                                builder.setContentIntent(pendingIntent);
                            }
//                            builder.setAutoCancel(true);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                notificationManager.notify(1001, builder.build());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
    }
}
