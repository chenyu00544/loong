package com.ecjia.hamster.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.a.a;
import com.ecjia.a.a.b;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.ac;
import com.ecmoban.android.missmall.R;
import com.umeng.analytics.pro.x;
import com.umeng.message.PushAgent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import de.greenrobot.event.c;
import java.util.Locale;

public class ECJiaLanguageActivity extends a {
    String[] a = new String[]{"auto", "zh", SocializeProtocolConstants.PROTOCOL_KEY_EN};
    Boolean[] b = new Boolean[]{Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)};
    Handler c;
    Configuration d;
    SharedPreferences e;
    private TextView k;
    private TextView l;
    private ImageView m;
    private ECJiaXListView n;
    private ac o;

    class ECJiaLanguageActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaLanguageActivity a;

        ECJiaLanguageActivity_1(ECJiaLanguageActivity eCJiaLanguageActivity) {
            this.a = eCJiaLanguageActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaLanguageActivity_2 extends Handler {
        final /* synthetic */ ECJiaLanguageActivity a;

        ECJiaLanguageActivity_2(ECJiaLanguageActivity eCJiaLanguageActivity) {
            this.a = eCJiaLanguageActivity;
        }

        public void handleMessage(Message message) {
            this.a.o.notifyDataSetChanged();
            q.a("" + this.a.b[1]);
        }
    }

    class ECJiaLanguageActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaLanguageActivity a;

        ECJiaLanguageActivity_3(ECJiaLanguageActivity eCJiaLanguageActivity) {
            this.a = eCJiaLanguageActivity;
        }

        public void onClick(View view) {
            int i = 0;
            this.a.l.setEnabled(false);
            while (i < this.a.b.length) {
                if (this.a.b[i].booleanValue()) {
                    if ("zh".equalsIgnoreCase(this.a.a[i])) {
                        this.a.e.edit().putString(x.F, "zh").commit();
                        this.a.d.locale = Locale.CHINA;
                    } else if (SocializeProtocolConstants.PROTOCOL_KEY_EN.equalsIgnoreCase(this.a.a[i])) {
                        this.a.e.edit().putString(x.F, SocializeProtocolConstants.PROTOCOL_KEY_EN).commit();
                        this.a.d.locale = Locale.ENGLISH;
                    } else {
                        this.a.e.edit().putString(x.F, "auto").commit();
                        this.a.d.locale = Locale.getDefault();
                    }
                    c.a().c(new b("changelanguage"));
                    this.a.getBaseContext().getResources().updateConfiguration(this.a.d, null);
                    Intent intent = new Intent();
                    intent.setClass(this.a, ECJiaMainActivity.class);
                    this.a.startActivity(intent);
                    this.a.finish();
                }
                i++;
            }
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_language);
        c();
    }

    private void c() {
        this.d = getResources().getConfiguration();
        this.e = PreferenceManager.getDefaultSharedPreferences(this);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        this.k = (TextView) findViewById(R.id.top_view_text);
        this.k.setText(getResources().getString(R.string.language));
        this.l = (TextView) findViewById(R.id.top_right_save);
        this.m = (ImageView) findViewById(R.id.top_view_back);
        this.m.setOnClickListener(new ECJiaLanguageActivity_1(this));
        this.n = (ECJiaXListView) findViewById(R.id.language_list);
        this.c = new ECJiaLanguageActivity_2(this);
        b();
        this.o = new ac(this, this.a);
        this.o.a = this.b;
        this.l.setVisibility(0);
        this.l.setOnClickListener(new ECJiaLanguageActivity_3(this));
        this.n.setPullLoadEnable(false);
        this.n.setPullRefreshEnable(false);
        this.o.b = this.c;
        this.n.setAdapter(this.o);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    void b() {
        for (int i = 0; i < this.a.length; i++) {
            if (this.a[i].equalsIgnoreCase(this.e.getString(x.F, null))) {
                this.b[i] = Boolean.valueOf(true);
            } else {
                this.b[i] = Boolean.valueOf(false);
            }
        }
    }

    public void onEvent(a aVar) {
    }
}
