package com.ecjia.hamster.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import com.ecjia.component.view.k;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class ECJiaShareActivity extends a implements OnClickListener {
    LinearLayout a;
    LinearLayout b;
    LinearLayout c;
    LinearLayout d;
    LinearLayout e;
    LinearLayout k;
    LinearLayout l;
    LinearLayout m;
    LinearLayout n;
    Intent o;
    boolean p;

    class ECJiaShareActivity_1 implements UMShareListener {
        final /* synthetic */ ECJiaShareActivity a;

        ECJiaShareActivity_1(ECJiaShareActivity eCJiaShareActivity) {
            this.a = eCJiaShareActivity;
        }

        public void onResult(SHARE_MEDIA share_media) {
            if (!(share_media == SHARE_MEDIA.EMAIL || share_media == SHARE_MEDIA.SMS)) {
                k kVar = new k(this.a, this.a.g.getString(R.string.share_succeed));
                kVar.a(17, 0, 0);
                kVar.b(200);
                kVar.a();
            }
            this.a.finish();
        }

        public void onError(SHARE_MEDIA share_media, Throwable th) {
            k kVar = new k(this.a, this.a.g.getString(R.string.share_failed));
            kVar.a(17, 0, 0);
            kVar.b(200);
            kVar.a();
            this.a.finish();
        }

        public void onCancel(SHARE_MEDIA share_media) {
            k kVar = new k(this.a, this.a.g.getString(R.string.share_cancel));
            kVar.a(17, 0, 0);
            kVar.b(200);
            kVar.a();
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share);
        PushAgent.getInstance(this).onAppStart();
        Window window = getWindow();
        window.setGravity(80);
        window.setLayout(-1, -2);
        this.o = getIntent();
        b();
    }

    void b() {
        this.a = (LinearLayout) findViewById(R.id.share_sinawb);
        this.b = (LinearLayout) findViewById(R.id.share_qqfriend);
        this.c = (LinearLayout) findViewById(R.id.share_weixinitem);
        this.d = (LinearLayout) findViewById(R.id.share_circle);
        this.e = (LinearLayout) findViewById(R.id.share_smsitem);
        this.k = (LinearLayout) findViewById(R.id.share_emailitem);
        this.m = (LinearLayout) findViewById(R.id.share_clipitem);
        this.l = (LinearLayout) findViewById(R.id.share_cancle);
        this.n = (LinearLayout) findViewById(R.id.share_refresh);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.p = this.o.getBooleanExtra("is_refresh", false);
        if (this.p) {
            this.n.setVisibility(0);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_weixinitem:
                a(SHARE_MEDIA.WEIXIN);
                return;
            case R.id.share_circle:
                a(SHARE_MEDIA.WEIXIN_CIRCLE);
                return;
            case R.id.share_qqfriend:
                a(SHARE_MEDIA.QQ);
                return;
            case R.id.share_sinawb:
                a(SHARE_MEDIA.SINA);
                return;
            case R.id.share_smsitem:
                a(SHARE_MEDIA.SMS);
                return;
            case R.id.share_emailitem:
                a(SHARE_MEDIA.EMAIL);
                return;
            case R.id.share_clipitem:
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, this.o.getStringExtra("share_content") + this.o.getStringExtra("share_goods_url")));
                k kVar = new k((Context) this, this.g.getString(R.string.share_clip_tips));
                kVar.a(17, 0, 0);
                kVar.b(200);
                kVar.a();
                return;
            case R.id.share_refresh:
                Intent intent = new Intent();
                intent.putExtra("is_refresh", this.p);
                setResult(-1, intent);
                finish();
                break;
            case R.id.share_cancle:
                break;
            default:
                return;
        }
        finish();
    }

    private void a(SHARE_MEDIA share_media) {
        UMImage uMImage;
        String stringExtra = this.o.getStringExtra("share_image_url");
        if (TextUtils.isEmpty(stringExtra)) {
            uMImage = new UMImage((Context) this, (int) R.drawable.ecmoban_logo);
        } else {
            uMImage = new UMImage((Context) this, stringExtra);
        }
        new ShareAction(this).setPlatform(share_media).setCallback(new ECJiaShareActivity_1(this)).withText(this.o.getStringExtra("share_content")).withTitle(this.o.getStringExtra("share_goods_name")).withTargetUrl(this.o.getStringExtra("share_goods_url")).withMedia(uMImage).share();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        UMShareAPI.get(this).onActivityResult(i, i2, intent);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
