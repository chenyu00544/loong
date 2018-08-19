package com.vcvb.chenyu.shop;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.BitmapTools;
import com.vcvb.chenyu.shop.tools.HttpUtils;

import java.lang.ref.SoftReference;
import java.util.List;

public class MainActivity extends Activity {

    private WifiManager wifiManager;
    private TextView textView;
    private Switch aSwitch;

    private ListView listView;
    private String[] imageUrls = Images.imageUrls;
    private ImageAdapter adapter;

    private ConnectivityManager connectivityManager;
    private NotificationManager notificationManager;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager
                    .TYPE_MOBILE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager
                    .TYPE_WIFI);
            if (!mobileInfo.isConnected() && !wifiInfo.isConnected()) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setContentTitle("tip");
                builder.setContentText("net_error");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notificationManager.notify(1001, builder.build());
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        textView = this.findViewById(R.id.textView2);
        aSwitch = this.findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wifiManager.setWifiEnabled(isChecked);
            }
        });

        listView = this.findViewById(R.id.listView1);
        adapter = new ImageAdapter();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            if (convertView == null) {
                v = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_item, null);
            } else {
                v = convertView;
            }
            ImageView imageView = v.findViewById(R.id.imageView);
            //加载网络图片
            loadBitmap(imageUrls[position], imageView);
            return v;
        }
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

    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
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

    public void loadBitmap(String data, ImageView imageView){
        Bitmap placeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        if(cancelPotntialWork(data, imageView)){
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(getResources(), placeBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(data);
        }
    }

    public void onButtonClick(View view) {
        Toast.makeText(MainActivity.this, view.getId() + "", Toast.LENGTH_LONG).show();
        textView.setText(R.string.app_name);
    }

    public void onButton2Click(View view) {
        List<ScanResult> list = wifiManager.getScanResults();
        for (ScanResult result : list) {
            textView.append("-->>" + result.SSID + "-->>" + result.level + "/n");
        }
        System.out.println(list);
    }

    public void onButton3Click(View view) {
        Intent intent = new Intent(MainActivity.this, Receiver.class);
        sendBroadcast(intent);
    }
}
