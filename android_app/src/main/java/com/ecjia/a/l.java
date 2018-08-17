package com.ecjia.a;

import android.support.v4.view.InputDeviceCompat;
import com.ecjia.consts.ECJiaClassName.ActivityName;
import com.ecjia.hamster.model.o;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ECJiaFunctionUtil */
public class l {
    public static final String[] a = new String[]{"shake", "checkin", "theme", "newgoods", "promotion", "seckill", "groupbuy", "qrcode", "qrshare", "map", "todayhot", "message", "feedback"};
    static l b;
    private HashMap<String, o> c;

    public HashMap<String, o> a() {
        return this.c;
    }

    public o a(String str) {
        return (o) this.c.get(str);
    }

    private l() {
    }

    public static l b() {
        if (b == null) {
            synchronized (l.class) {
                if (b == null) {
                    b = new l();
                }
            }
        }
        return b;
    }

    public void c() {
        this.c = new HashMap();
        this.c.put("shake", new o("shake", R.drawable.icon_find_shake_white, R.drawable.icon_find_shake, R.string.function_shake, ActivityName.SHAKE, true, true, InputDeviceCompat.SOURCE_KEYBOARD));
        this.c.put("checkin", new o("checkin", R.drawable.icon_find_checkin_white, R.drawable.icon_find_checkin, R.string.function_checkin, ActivityName.CHECKIN, true, true, 258));
        this.c.put("qrcode", new o("qrcode", R.drawable.icon_find_zxing_white, R.drawable.icon_find_zxing, R.string.function_qrcode, ActivityName.QRCODE, false, true, 0));
        this.c.put("qrshare", new o("qrshare", R.drawable.icon_sliding_suggest, R.drawable.icon_find_qrcode_share, R.string.my_suggest, ActivityName.QRSHARE, true, true, 259));
        this.c.put("theme", new o("theme", R.drawable.icon_find_theme_white, R.drawable.icon_find_theme, R.string.function_toptic, ActivityName.THEME, false, true, 0));
        this.c.put("mobilebuy", new o("mobilebuy", R.drawable.icon_find_mobile_buy_white, R.drawable.icon_find_mobile_buy, R.string.function_mobilebuy, ActivityName.MOBILEBUY, false, true, 0));
        this.c.put("promotion", new o("promotion", R.drawable.icon_find_promotion_white, R.drawable.icon_find_promotion, R.string.function_promotion, ActivityName.PROMOTIONAL, false, true, 0));
        this.c.put("newgoods", new o("newgoods", R.drawable.icon_find_new_white, R.drawable.icon_find_new, R.string.newgoods, ActivityName.NEWGOODS, false, true, 0));
        this.c.put("groupbuy", new o("groupbuy", R.drawable.icon_find_groupbuy_white, R.drawable.icon_find_groupbuy, R.string.function_groupbuy, ActivityName.GROUPBUY, false, true, 0));
        this.c.put("map", new o("map", R.drawable.icon_find_map_white, R.drawable.icon_find_map, R.string.function_map, ActivityName.MAP, false, true, 0));
        this.c.put("todayhot", new o("todayhot", R.drawable.icon_find_todayhot_white, R.drawable.icon_find_todayhot, R.string.function_todayhot, ActivityName.TODAYHOT, false, true, 0));
        this.c.put("message", new o("message", R.drawable.icon_find_push_white, R.drawable.icon_find_push, R.string.function_message, ActivityName.MESSAGE, false, true, 0));
        this.c.put("feedback", new o("feedback", R.drawable.icon_find_consult_white, R.drawable.icon_find_consult, R.string.function_feedback, ActivityName.FEEDBACK, false, true, 0));
        this.c.put("seckill", new o("seckill", R.drawable.icon_find_seckill_white, R.drawable.icon_find_seckill, R.string.home_seckill_goods_text, ActivityName.SECKILL, false, true, 0));
    }

    public ArrayList<o> d() {
        ArrayList<o> arrayList = new ArrayList();
        for (Object obj : a) {
            arrayList.add(this.c.get(obj));
        }
        return arrayList;
    }

    public void e() {
        if (this.c != null) {
            this.c.clear();
            this.c = null;
        }
    }
}
