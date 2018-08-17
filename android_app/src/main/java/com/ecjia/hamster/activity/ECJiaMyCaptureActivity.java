package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.itguy.zxingportrait.CaptureActivity;
import com.ecjia.hamster.adapter.bw;
import com.ecjia.hamster.model.ba;
import com.ecmoban.android.missmall.R;
import com.google.zxing.g;
import com.umeng.message.PushAgent;

public class ECJiaMyCaptureActivity extends CaptureActivity {
    private final String g = "http://www.missmall.com/goods.php?id=";
    private final String h = "http://www.missmall.com/category.php?id=";
    private bw i;

    class ECJiaMyCaptureActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaMyCaptureActivity a;

        ECJiaMyCaptureActivity_1(ECJiaMyCaptureActivity eCJiaMyCaptureActivity) {
            this.a = eCJiaMyCaptureActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaSweepRecordActivity.class));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.f.setBackgroundColor(getResources().getColor(R.color.public_theme_color_normal));
        this.e.setOnClickListener(new ECJiaMyCaptureActivity_1(this));
        this.i = bw.a((Context) this);
    }

    public void a(g gVar, Bitmap bitmap, float f) {
        this.c.b();
        String a = gVar.a();
        a(a.toLowerCase(), a);
    }

    public void finish() {
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        super.finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void a(String str, String str2) {
        ba baVar = new ba();
        baVar.b(str2);
        if (str.indexOf("http://www.missmall.com/goods.php?id=") == 0) {
            baVar.a("商品详情");
            a(str2);
        } else if (str.indexOf("http://www.missmall.com/category.php?id=") == 0) {
            baVar.a("商品列表");
            b(str2);
        } else {
            baVar.a("链接");
            c(str2);
        }
        this.i.a(baVar);
    }

    public void a(String str) {
        Intent intent = new Intent(this, ECJiaGoodsDetailActivity.class);
        intent.putExtra("goods_id", str.replace("http://www.missmall.com/goods.php?id=", ""));
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void b(String str) {
        Intent intent = new Intent(this, ECJiaGoodsListActivity.class);
        intent.putExtra("category_id", str.replace("http://www.missmall.com/category.php?id=", ""));
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void c(String str) {
        Intent intent = new Intent(this, ECJiaWebViewActivity.class);
        intent.putExtra("url", str);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
