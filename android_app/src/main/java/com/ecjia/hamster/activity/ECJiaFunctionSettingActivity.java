package com.ecjia.hamster.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.l;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaDragGridView;
import com.ecjia.component.view.ECJiaDragGridView.a;
import com.ecjia.component.view.ECJiaDragGridView.b;
import com.ecjia.component.view.ECJiaDragGridView.c;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.o;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

public class ECJiaFunctionSettingActivity extends a implements b, c {
    ArrayList<o> a = new ArrayList();
    int b;
    Runnable c = new ECJiaFunctionSettingActivity_5(this);
    int d = -1;
    private SharedPreferences e;
    private ArrayList<o> k = new ArrayList();
    private ArrayList<o> l = new ArrayList();
    private ECJiaDragGridView m;

    class ECJiaFunctionSettingActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaFunctionSettingActivity a;

        ECJiaFunctionSettingActivity_1(ECJiaFunctionSettingActivity eCJiaFunctionSettingActivity) {
            this.a = eCJiaFunctionSettingActivity;
        }

        public void onClick(View view) {
            int i = this.a.d;
            int i2 = 0;
            while (i2 < this.a.a.size()) {
                if (((o) this.a.a.get(i2)).a().equals(((View) view.getParent().getParent().getParent().getParent()).getTag())) {
                    break;
                }
                i2++;
            }
            i2 = 0;
            q.a("view.tag:" + ((View) view.getParent().getParent().getParent().getParent()).getTag() + " oldPosition:" + i2 + "  tagPosition:" + i);
            o oVar;
            if (i > i2) {
                oVar = (o) this.a.a.remove(i2);
                oVar.a(false);
                this.a.a.add(oVar);
            } else {
                oVar = (o) this.a.a.remove(i2);
                oVar.a(true);
                this.a.a.add(i, oVar);
            }
            this.a.g();
            this.a.f.postDelayed(this.a.c, 350);
        }
    }

    class ECJiaFunctionSettingActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaFunctionSettingActivity a;

        ECJiaFunctionSettingActivity_2(ECJiaFunctionSettingActivity eCJiaFunctionSettingActivity) {
            this.a = eCJiaFunctionSettingActivity;
        }

        public void onClick(View view) {
            int i = this.a.d;
            int i2 = 0;
            while (i2 < this.a.a.size()) {
                if (((o) this.a.a.get(i2)).a().equals(((View) view.getParent().getParent().getParent().getParent()).getTag())) {
                    break;
                }
                i2++;
            }
            i2 = 0;
            q.a("view.tag:" + ((View) view.getParent().getParent().getParent().getParent()).getTag() + " oldPosition:" + i2 + "  tagPosition:" + i);
            o oVar;
            if (i > i2) {
                oVar = (o) this.a.a.remove(i2);
                oVar.a(false);
                this.a.a.add(oVar);
            } else {
                oVar = (o) this.a.a.remove(i2);
                oVar.a(true);
                this.a.a.add(i, oVar);
            }
            this.a.g();
            this.a.f.postDelayed(this.a.c, 350);
        }
    }

    class ECJiaFunctionSettingActivity_3 implements a {
        final /* synthetic */ ECJiaFunctionSettingActivity a;

        ECJiaFunctionSettingActivity_3(ECJiaFunctionSettingActivity eCJiaFunctionSettingActivity) {
            this.a = eCJiaFunctionSettingActivity;
        }

        public void a(int i, int i2) {
            this.a.f.postDelayed(this.a.c, 100);
        }
    }

    class ECJiaFunctionSettingActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaFunctionSettingActivity a;

        ECJiaFunctionSettingActivity_4(ECJiaFunctionSettingActivity eCJiaFunctionSettingActivity) {
            this.a = eCJiaFunctionSettingActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaFunctionSettingActivity_5 implements Runnable {
        final /* synthetic */ ECJiaFunctionSettingActivity a;

        ECJiaFunctionSettingActivity_5(ECJiaFunctionSettingActivity eCJiaFunctionSettingActivity) {
            this.a = eCJiaFunctionSettingActivity;
        }

        public void run() {
            q.a("functionHashMap.size()=" + this.a.a.size());
            int i = 0;
            for (int i2 = 0; i2 < this.a.a.size(); i2++) {
                if (((o) this.a.a.get(i2)).a().equals("tag")) {
                    i = i2;
                }
            }
            int i3;
            if (i == 0) {
                this.a.m.getChildAt(i).findViewById(R.id.barring).setVisibility(0);
                for (i3 = 0; i3 < this.a.a.size(); i3++) {
                    if (i3 != i) {
                        if (i3 == this.a.a.size() - 1) {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(8);
                        } else {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(0);
                        }
                    }
                }
            } else if (i == this.a.a.size() - 1) {
                this.a.m.getChildAt(i).findViewById(R.id.barring).setVisibility(4);
                for (i3 = 0; i3 < this.a.a.size(); i3++) {
                    if (i3 != i) {
                        if (i3 == this.a.d - 1) {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(8);
                        } else {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(0);
                        }
                    }
                }
            } else {
                this.a.m.getChildAt(i).findViewById(R.id.barring).setVisibility(0);
                for (i3 = 0; i3 < this.a.a.size(); i3++) {
                    if (i3 != i) {
                        if (i3 == this.a.d - 1) {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(8);
                        } else if (i3 == this.a.a.size() - 1) {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(8);
                        } else {
                            this.a.m.getChildAt(i3).findViewById(R.id.middle_line).setVisibility(0);
                        }
                    }
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_functionsetting);
        this.b = getWindowManager().getDefaultDisplay().getWidth();
        l.b().c();
        h();
        f();
        b();
        g();
    }

    protected void onResume() {
        super.onResume();
    }

    private void f() {
        this.e = getSharedPreferences("function_setting", 0);
        this.k.clear();
        if (this.e.getBoolean("isfirst", true)) {
            e();
            q.a("isfirsttrue");
            this.e.edit().putBoolean("isfirst", false).commit();
            return;
        }
        int i;
        q.a("isfirstfalse");
        JSONArray jSONArray = null;
        try {
            jSONArray = new JSONArray(this.e.getString("support", new JSONArray().toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        q.a("array == " + jSONArray.toString());
        String[] strArr = new String[]{"shake", "promotion", "seckill", "groupbuy", "todayhot", "message", "feedback", "map", "newgoods", "checkin"};
        this.a.clear();
        for (Object obj : strArr) {
            this.a.add(l.b().a().get(obj));
        }
        if (jSONArray != null) {
            int i2;
            for (i2 = 0; i2 < jSONArray.length(); i2++) {
                String obj2 = jSONArray.opt(i2).toString();
                for (int i3 = 0; i3 < this.a.size(); i3++) {
                    if (((o) this.a.get(i3)).a().equals(obj2)) {
                        ((o) this.a.get(i3)).a(true);
                        this.k.add(this.a.get(i3));
                        break;
                    }
                }
            }
            for (i2 = 0; i2 < this.a.size(); i2++) {
                boolean z;
                String a = ((o) this.a.get(i2)).a();
                for (i = 0; i < jSONArray.length(); i++) {
                    if (a.equals(jSONArray.opt(i).toString())) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!z) {
                    ((o) this.a.get(i2)).a(false);
                    this.l.add(this.a.get(i2));
                }
            }
        }
    }

    void b() {
        int i = 0;
        this.a.clear();
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            this.a.add(this.k.get(i2));
        }
        this.a.add(new o("tag", 0, 0, 0, null, false, false, 0));
        while (i < this.l.size()) {
            this.a.add(this.l.get(i));
            i++;
        }
        this.d = this.k.size();
    }

    private void g() {
        this.m.removeAllViews();
        for (int i = 0; i < this.a.size(); i++) {
            if (((o) this.a.get(i)).a().equals("tag")) {
                View inflate = LayoutInflater.from(this).inflate(R.layout.function_setting_text, null);
                inflate.setTag("tag");
                if (i == this.a.size() - 1) {
                    inflate.findViewById(R.id.barring).setVisibility(4);
                } else {
                    inflate.findViewById(R.id.barring).setVisibility(0);
                }
                this.m.addView(inflate);
            } else {
                View inflate2 = LayoutInflater.from(this).inflate(R.layout.item_function_setting, null);
                View findViewById = inflate2.findViewById(R.id.support);
                View findViewById2 = inflate2.findViewById(R.id.unsupport);
                ImageView imageView = (ImageView) inflate2.findViewById(R.id.icon);
                TextView textView = (TextView) inflate2.findViewById(R.id.name);
                ImageView imageView2 = (ImageView) inflate2.findViewById(R.id.check);
                ImageView imageView3 = (ImageView) inflate2.findViewById(R.id.check_false);
                inflate2.findViewById(R.id.middle_line);
                imageView.setImageResource(((o) this.a.get(i)).e());
                textView.setText(((o) this.a.get(i)).f());
                imageView2.setSelected(((o) this.a.get(i)).c());
                if (((o) this.a.get(i)).c()) {
                    findViewById.setVisibility(0);
                    findViewById2.setVisibility(8);
                } else {
                    findViewById.setVisibility(8);
                    findViewById2.setVisibility(0);
                }
                imageView2.setOnClickListener(new ECJiaFunctionSettingActivity_1(this));
                imageView3.setOnClickListener(new ECJiaFunctionSettingActivity_2(this));
                inflate2.setTag(((o) this.a.get(i)).a());
                this.m.addView(inflate2);
            }
        }
    }

    void c() {
        for (int i = 0; i < this.a.size(); i++) {
            if (i != this.d) {
                if (this.d == 0) {
                    if (i == this.a.size() - 1) {
                        this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(8);
                    } else {
                        this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(0);
                    }
                } else if (this.d == this.a.size() - 1) {
                    if (i == this.d - 1) {
                        this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(8);
                    } else {
                        this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(0);
                    }
                } else if (i == this.d - 1) {
                    this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(8);
                    q.a("gone");
                } else if (i == this.a.size() - 1) {
                    this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(8);
                } else {
                    this.m.getChildAt(i).findViewById(R.id.middle_line).setVisibility(0);
                }
            }
        }
    }

    private void h() {
        a();
        this.m = (ECJiaDragGridView) findViewById(R.id.dragviewgroup);
        this.m.setOnDataExchangeListener(new ECJiaFunctionSettingActivity_3(this));
        this.m.setOnTagPositionListener(this);
        this.m.setOnRearrangeListener(this);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.functionsetting_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaFunctionSettingActivity_4(this));
        this.i.setTitleText((int) R.string.function_manage);
    }

    protected void onPostResume() {
        super.onPostResume();
        c();
    }

    protected void onPause() {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < this.a.size()) {
            if (!((o) this.a.get(i)).a().equals("tag") && ((o) this.a.get(i)).c()) {
                jSONArray.put(((o) this.a.get(i)).a());
            }
            i++;
        }
        q.a("array 存==" + jSONArray.toString());
        this.e.edit().putString("support", jSONArray.toString()).commit();
        super.onPause();
    }

    void e() {
        int i = 0;
        String[] strArr = new String[]{"shake", "promotion", "seckill", "groupbuy", "todayhot", "message", "feedback", "map", "newgoods", "checkin"};
        while (i < strArr.length) {
            this.k.add(l.b().a().get(strArr[i]));
            i++;
        }
    }

    protected void onDestroy() {
        l.b().e();
        super.onDestroy();
    }

    public void a(int i) {
        this.d = i;
        q.a("tagPosition:" + this.d);
    }

    public void a(int i, int i2) {
        if (i != i2) {
            o oVar;
            if (this.m.getChildAt(i2).getTag().equals("tag")) {
                oVar = (o) this.a.remove(i);
                if (i < i2) {
                    oVar.a(false);
                    this.a.add(i2, oVar);
                } else {
                    oVar.a(true);
                    this.a.add(i2, oVar);
                }
                if (oVar.c()) {
                    this.m.getChildAt(i).findViewById(R.id.support).setVisibility(0);
                    this.m.getChildAt(i).findViewById(R.id.unsupport).setVisibility(8);
                    return;
                }
                this.m.getChildAt(i).findViewById(R.id.support).setVisibility(8);
                this.m.getChildAt(i).findViewById(R.id.unsupport).setVisibility(0);
                return;
            }
            oVar = (o) this.a.remove(i);
            if ((i - this.d) * (i2 - this.d) > 0) {
                this.a.add(i2, oVar);
            } else if (i < i2) {
                oVar.a(false);
                this.a.add(i2, oVar);
            } else {
                oVar.a(true);
                this.a.add(i2, oVar);
            }
            if (oVar.c()) {
                this.m.getChildAt(i).findViewById(R.id.support).setVisibility(0);
                this.m.getChildAt(i).findViewById(R.id.unsupport).setVisibility(8);
                return;
            }
            this.m.getChildAt(i).findViewById(R.id.support).setVisibility(8);
            this.m.getChildAt(i).findViewById(R.id.unsupport).setVisibility(0);
        }
    }
}
