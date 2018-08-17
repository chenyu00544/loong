package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.util.Util;

public class MainPage_Activity extends Activity {
    private Button add = null;
    private Button authorize = null;
    private Context context = null;
    private Button readd = null;

    class MainPage_Activity_1 implements OnClickListener {
        MainPage_Activity_1() {
        }

        public void onClick(View view) {
            MainPage_Activity.this.startActivity(new Intent(MainPage_Activity.this, Authorize.class));
        }
    }

    class MainPage_Activity_2 implements OnClickListener {
        MainPage_Activity_2() {
        }

        public void onClick(View view) {
            MainPage_Activity.this.startActivity(new Intent(MainPage_Activity.this, PublishActivity.class));
        }
    }

    class MainPage_Activity_3 implements OnClickListener {
        MainPage_Activity_3() {
        }

        public void onClick(View view) {
            Intent intent = new Intent(MainPage_Activity.this, ReAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("content", "Make U happy");
            bundle.putString("video_url", "http://www.tudou.com/programs/view/b-4VQLxwoX4/");
            bundle.putString("pic_url", "http://t2.qpic.cn/mblogpic/9c7e34358608bb61a696/2000");
            intent.putExtras(bundle);
            MainPage_Activity.this.startActivity(intent);
        }
    }

    class MainPage_Activity_4 implements OnClickListener {
        MainPage_Activity_4() {
        }

        public void onClick(View view) {
            Util.clearSharePersistent(MainPage_Activity.this.context);
            Toast.makeText(MainPage_Activity.this, "注销成功", 0).show();
        }
    }

    class MainPage_Activity_5 implements OnClickListener {
        MainPage_Activity_5() {
        }

        public void onClick(View view) {
            MainPage_Activity.this.startActivity(new Intent(MainPage_Activity.this, GeneralInterfaceActivity.class));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.main_layout);
        this.context = getApplicationContext();
        init();
    }

    public void init() {
        this.authorize = (Button) findViewById(R.id.authorize);
        this.authorize.setOnClickListener(new MainPage_Activity_1());
        this.add = (Button) findViewById(R.id.add);
        this.add.setOnClickListener(new MainPage_Activity_2());
        this.readd = (Button) findViewById(R.id.readd);
        this.readd.setOnClickListener(new MainPage_Activity_3());
        ((Button) findViewById(R.id.exit)).setOnClickListener(new MainPage_Activity_4());
        ((Button) findViewById(R.id.commoninterface)).setOnClickListener(new MainPage_Activity_5());
    }
}
