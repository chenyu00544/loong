package com.umeng.socialize.editorpage;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.b.g;
import com.umeng.socialize.Config;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.location.a;
import com.umeng.socialize.editorpage.location.b;
import com.umeng.socialize.editorpage.location.d;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.io.File;
import java.util.Set;

public class ShareActivity extends Activity implements OnClickListener {
    public static final int CANCLE_RESULTCODE = 1000;
    public static final String FOLLOW_FILE_NAME = "umeng_follow";
    public static final String KEY_AT = "at";
    public static final String KEY_FOLLOW = "follow_";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_PIC = "pic";
    public static final String KEY_PLATFORM = "media";
    public static final String KEY_TEXT = "txt";
    public static final String KEY_TITLE = "title";
    public static final int REQUEST_CODE = 1229;
    private static final String c = "ShareActivity";
    private static int d = 140;
    private SHARE_MEDIA A;
    private a B;
    private UMLocation C;
    private int D;
    private boolean E = false;
    private Dialog F;
    private Set<String> G = null;
    private b H = null;
    protected ImageView a;
    TextWatcher b = new b(this);
    private String e;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private ResContainer k;
    private Button l;
    private Button m;
    private EditText n;
    private ImageButton o;
    private ImageButton p;
    private View q;
    private View r;
    private View s;
    private TextView t;
    private RelativeLayout u;
    private CheckBox v;
    private KeyboardListenRelativeLayout w;
    private ProgressBar x;
    private Context y;
    private boolean z;

    protected void onCreate(Bundle bundle) {
        boolean z;
        this.k = ResContainer.get(this);
        this.E = SocializeUtils.isFloatWindowStyle(this);
        if (!this.E) {
            setTheme(this.k.style("Theme.UMDefault"));
        }
        super.onCreate(bundle);
        this.y = this;
        setContentView(this.k.layout("umeng_socialize_post_share"));
        LayoutParams attributes = getWindow().getAttributes();
        attributes.softInputMode = 16;
        if (this.E) {
            int[] floatWindowSize = SocializeUtils.getFloatWindowSize(this.y);
            attributes.width = floatWindowSize[0];
            attributes.height = floatWindowSize[1];
        }
        getWindow().setAttributes(attributes);
        this.w = (KeyboardListenRelativeLayout) findViewById(this.k.id("umeng_socialize_share_root"));
        this.w.a(new a(this));
        Bundle extras = getIntent().getExtras();
        this.A = a(extras.getString(KEY_PLATFORM));
        if (this.A == SHARE_MEDIA.RENREN) {
            d = g.L;
        } else {
            d = 140;
        }
        this.e = extras.getString("title");
        this.f = extras.getString(KEY_TEXT);
        this.g = extras.getString(KEY_PIC);
        this.h = extras.getBoolean(KEY_FOLLOW, false);
        this.i = extras.getBoolean(KEY_AT);
        this.i = false;
        if (extras.getBoolean("location") && Config.ShareLocation) {
            z = true;
        } else {
            z = false;
        }
        this.j = z;
        c();
        if (this.j) {
            b();
        }
    }

    private SHARE_MEDIA a(String str) {
        if (str.equals("TENCENT")) {
            return SHARE_MEDIA.TENCENT;
        }
        if (str.equals("RENREN")) {
            return SHARE_MEDIA.RENREN;
        }
        if (str.equals("DOUBAN")) {
            return SHARE_MEDIA.DOUBAN;
        }
        return SHARE_MEDIA.SINA;
    }

    protected void onResume() {
        if (this.j) {
            e();
        }
        this.n.requestFocus();
        super.onResume();
    }

    private void b() {
        this.B = new a();
        d dVar = new d();
        dVar.a((Context) this);
        this.B.a(dVar);
        this.B.a((Context) this);
    }

    private void c() {
        ((TextView) findViewById(this.k.id("umeng_socialize_title_bar_middleTv"))).setText(this.e);
        this.l = (Button) findViewById(this.k.id("umeng_socialize_title_bar_leftBt"));
        this.m = (Button) findViewById(this.k.id("umeng_socialize_title_bar_rightBt"));
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n = (EditText) findViewById(this.k.id("umeng_socialize_share_edittext"));
        if (!TextUtils.isEmpty(this.f)) {
            this.n.setText(this.f);
            this.n.setSelection(this.f.length());
        }
        this.n.addTextChangedListener(this.b);
        this.t = (TextView) findViewById(this.k.id("umeng_socialize_share_word_num"));
        this.z = d();
        if (this.j) {
            findViewById(this.k.id("umeng_socialize_share_location")).setVisibility(0);
            this.p = (ImageButton) findViewById(this.k.id("umeng_socialize_location_ic"));
            this.p.setOnClickListener(this);
            this.p.setVisibility(0);
            this.p.setImageResource(this.k.drawable("umeng_socialize_location_off"));
            this.q = findViewById(this.k.id("umeng_socialize_location_progressbar"));
        }
        if (this.i) {
            this.o = (ImageButton) findViewById(this.k.id("umeng_socialize_share_at"));
            this.o.setVisibility(0);
            this.o.setOnClickListener(this);
        }
        if (this.g != null) {
            findViewById(this.k.id("umeng_socialize_share_image")).setVisibility(0);
            this.a = (ImageView) findViewById(this.k.id("umeng_socialize_share_previewImg"));
            this.r = findViewById(this.k.id("umeng_socialize_share_previewImg_remove"));
            this.r.setOnClickListener(this);
            this.a.setVisibility(0);
            if (this.g.equals(WeiXinShareContent.TYPE_VIDEO)) {
                this.a.setImageResource(ResContainer.getResourceId(this.y, "drawable", "umeng_socialize_share_video"));
            } else if (this.g.equals(WeiXinShareContent.TYPE_MUSIC)) {
                this.a.setImageResource(ResContainer.getResourceId(this.y, "drawable", "umeng_socialize_share_music"));
            } else {
                this.a.setImageURI(Uri.fromFile(new File(this.g)));
            }
        }
        if (this.h) {
            this.v = (CheckBox) findViewById(this.k.id("umeng_socialize_follow_check"));
            this.v.setOnClickListener(this);
            this.v.setVisibility(0);
        }
    }

    private void a(View view) {
        String obj = this.n.getText().toString();
        if (TextUtils.isEmpty(obj.trim())) {
            Toast.makeText(this, "输入内容为空...", 0).show();
        } else if (SocializeUtils.countContentLength(obj) > d) {
            Toast.makeText(this, "输入内容超过140个字.", 0).show();
        } else if (this.z) {
            Toast.makeText(this.y, "超出最大字数限制....", 0).show();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TEXT, obj);
            bundle.putString(KEY_PIC, this.g);
            bundle.putBoolean(KEY_FOLLOW, this.h);
            bundle.putSerializable("location", this.C);
            intent.putExtras(bundle);
            setResult(-1, intent);
            a();
        }
    }

    public void onCancel(View view) {
        setResult(1000);
        a();
    }

    private void b(View view) {
        this.g = null;
        findViewById(this.k.id("umeng_socialize_share_image")).setVisibility(8);
    }

    public void onAtFriends(View view) {
        if (this.F == null) {
            this.F = f();
        }
        if (!this.F.isShowing()) {
            this.F.show();
        }
    }

    public void onFollowStatChanged(View view) {
        this.h = this.v.isChecked();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == this.k.id("umeng_socialize_share_previewImg_remove")) {
            b(view);
        } else if (id == this.k.id("umeng_socialize_title_bar_rightBt")) {
            a(view);
        } else if (id == this.k.id("umeng_socialize_title_bar_leftBt")) {
            onCancel(view);
        } else if (id == this.k.id("umeng_socialize_share_at")) {
            onAtFriends(view);
        } else if (id == this.k.id("umeng_socialize_location_ic")) {
            c(view);
        } else if (id == this.k.id("umeng_socialize_follow_check")) {
            onFollowStatChanged(view);
        }
    }

    private void a(int i, Bitmap bitmap) {
        try {
            this.a.setImageBitmap(bitmap);
        } catch (Exception e) {
            this.a.setImageResource(i);
        }
        this.a.setVisibility(0);
        this.r.setVisibility(0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            setResult(1000);
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void a() {
        if (this.D == -3) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), 0);
            new Handler().postDelayed(new c(this), 500);
            return;
        }
        finish();
    }

    private boolean d() {
        int countContentLength = d - SocializeUtils.countContentLength(this.n.getText().toString());
        Log.d(c, "onTextChanged " + countContentLength + "   " + SocializeUtils.countContentLength(this.n.getText().toString()));
        this.t.setText("" + countContentLength);
        if (countContentLength >= 0) {
            return false;
        }
        return true;
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        if (this.B != null) {
            this.B.a();
        }
        if (this.H != null) {
            this.H.cancel(true);
        }
        super.onDestroy();
    }

    private void c(View view) {
        if (this.C != null) {
            new Builder(this).setMessage("是否删除位置信息？").setCancelable(false).setPositiveButton("是", new e(this)).setNegativeButton("否", new d(this)).create().show();
        } else {
            e();
        }
    }

    private void e() {
        if (this.B == null) {
            b();
        }
        if (!(this.H == null || this.H.getStatus() == Status.FINISHED)) {
            this.H.cancel(true);
        }
        this.H = new f(this, this.B);
        this.H.execute(new Void[0]);
    }

    private void a(boolean z) {
        if (z) {
            this.p.setVisibility(8);
            this.q.setVisibility(0);
        } else if (this.C == null) {
            this.p.setImageResource(this.k.drawable("umeng_socialize_location_off"));
            this.p.setVisibility(0);
            this.q.setVisibility(8);
        } else {
            this.p.setImageResource(this.k.drawable("umeng_socialize_location_on"));
            this.p.setVisibility(0);
            this.q.setVisibility(8);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!SocializeConstants.BACKKEY_COMPLETE_CLOSE || keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        new Handler().postDelayed(new g(this), 400);
        return true;
    }

    public void inputAt(SpannableString spannableString) {
        this.n.getText().insert(this.n.getSelectionStart(), spannableString);
    }

    private Dialog f() {
        try {
            return (Dialog) Class.forName("com.umeng.socialize.view.ShareAtDialogV2").getConstructor(new Class[]{ShareActivity.class, SHARE_MEDIA.class, String.class}).newInstance(new Object[]{this, this.A, Config.UID});
        } catch (Exception e) {
            Log.w(c, "如果需要使用‘@好友’功能，请添加相应的jar文件；否则忽略此信息", e);
            return null;
        }
    }
}
