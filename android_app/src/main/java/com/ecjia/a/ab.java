package com.ecjia.a;

import android.content.Context;
import android.text.TextUtils;
import com.ecmoban.android.missmall.R;
import com.umeng.analytics.a;
import com.umeng.socialize.common.SocializeConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* compiled from: ECJiaTimeUtil */
public class ab {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean a(long j, long j2) {
        long j3 = j - j2;
        return j3 < 86400000 && j3 > -86400000 && a(j) == a(j2);
    }

    private static long a(long j) {
        return (((long) TimeZone.getDefault().getOffset(j)) + j) / 86400000;
    }

    public static String a(String str) {
        try {
            SimpleDateFormat simpleDateFormat;
            if (str.contains("/")) {
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            long time = (simpleDateFormat.parse(str).getTime() - new Date().getTime()) / 1000;
            if (time <= 0) {
                return "";
            }
            long abs = Math.abs(time / 86400);
            long abs2 = Math.abs((time - (((24 * abs) * 60) * 60)) / 3600);
            long abs3 = Math.abs(((time - (((24 * abs) * 60) * 60)) - ((60 * abs2) * 60)) / 60);
            time = Math.abs(((time - (((24 * abs) * 60) * 60)) - ((60 * abs2) * 60)) - (60 * abs3));
            if (abs > 0) {
                return abs + "天" + abs2 + "小时" + abs3 + "分" + time + "秒";
            }
            if (abs2 > 0) {
                return abs2 + "小时" + abs3 + "分" + time + "秒";
            }
            if (abs3 > 0) {
                return abs3 + "分" + time + "秒";
            }
            if (time > 0) {
                return time + "秒";
            }
            return "0秒";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static boolean c(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Math.abs(Math.abs(Math.abs(((new Date().getTime() - date.getTime()) / 1000) / 60) / 60) / 24) > 30) {
            return true;
        }
        return false;
    }

    public static String d(String str) {
        String str2 = "";
        if (!(str == null || str.equals(""))) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date parse = simpleDateFormat.parse(str);
                Calendar instance = Calendar.getInstance();
                instance.setTime(parse);
                instance.add(13, 1);
                str2 = simpleDateFormat.format(instance.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static String e(String str) {
        String str2 = "";
        if (!(str == null || str.equals(""))) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date parse = simpleDateFormat.parse(str);
                Calendar instance = Calendar.getInstance();
                instance.setTime(parse);
                instance.add(13, 1);
                str2 = simpleDateFormat.format(instance.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static long f(String str) {
        if (!(str == null || str.equals(""))) {
            try {
                SimpleDateFormat simpleDateFormat;
                long time = new Date().getTime();
                if (str.contains("/")) {
                    simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                } else {
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                long time2 = simpleDateFormat.parse(str).getTime() - time;
                if (time2 > 0) {
                    return time2;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static long g(String str) {
        String str2 = "0";
        if (!(str == null || str.equals(""))) {
            try {
                long time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(str).getTime() - new Date().getTime();
                if (time > 0) {
                    return time;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String a(String str, String str2) {
        String str3 = "0";
        if (str != null) {
            if (!(str.equals("") || str2 == null)) {
                if (!str2.equals("")) {
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        long time = simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
                        if (time > 0) {
                            long j = (time % 86400000) / a.j;
                            long j2 = ((time % 86400000) % a.j) / 60000;
                            long j3 = (((time % 86400000) % 86400000) % 60000) / 1000;
                            str3 = "剩余" + (time / 86400000) + "天" + j + "时" + j2 + "分" + j3 + "秒";
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return str3;
    }

    public static String a(Calendar calendar, Context context) {
        Calendar instance;
        if (calendar.get(2) + 1 < 10) {
            instance = Calendar.getInstance();
        } else {
            instance = Calendar.getInstance();
        }
        if (instance.get(1) != calendar.get(1)) {
            return calendar.get(1) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(2) + 1) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(5));
        }
        if (instance.get(6) == calendar.get(6)) {
            return context.getResources().getString(R.string.today) + a(calendar.get(11)) + ":" + a(calendar.get(12));
        }
        if (instance.get(6) - calendar.get(6) == 1) {
            return context.getResources().getString(R.string.yesterday) + a(calendar.get(11)) + ":" + a(calendar.get(12));
        }
        return a(calendar.get(2) + 1) + SocializeConstants.OP_DIVIDER_MINUS + a(calendar.get(5));
    }

    private static String a(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

    public static String a(Calendar calendar) {
        Calendar instance = Calendar.getInstance();
        if (instance.get(1) == instance.get(1) && instance.get(2) == calendar.get(2)) {
            return "本月";
        }
        return (calendar.get(2) + 1) + "月";
    }

    public static boolean b(Calendar calendar) {
        if (Calendar.getInstance().get(3) == calendar.get(3)) {
            return true;
        }
        return false;
    }

    public static boolean c(Calendar calendar) {
        if (Calendar.getInstance().get(6) == calendar.get(6)) {
            return true;
        }
        return false;
    }

    public static boolean d(Calendar calendar) {
        return Calendar.getInstance().get(6) == calendar.get(6) + 1;
    }

    public static String h(String str) {
        Date date = new Date();
        try {
            date = a.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(6, 0);
        date = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM月dd日");
        simpleDateFormat2 = new SimpleDateFormat("HH:mm");
        if (!b(instance)) {
            return simpleDateFormat.format(date) + " " + e(instance) + simpleDateFormat2.format(date);
        }
        if (c(instance)) {
            return simpleDateFormat2.format(date);
        }
        if (d(instance)) {
            return "昨天 " + simpleDateFormat2.format(date);
        }
        return f(instance) + " " + simpleDateFormat2.format(date);
    }

    public static String i(String str) {
        Date date = new Date();
        try {
            date = a.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("MM月dd日").format(date);
    }

    public static String e(Calendar calendar) {
        int i = calendar.get(9);
        int i2 = calendar.get(11);
        q.b("====hour=====" + i2);
        if (i == 0) {
            return "早上";
        }
        if (i2 >= 18) {
            return "晚上";
        }
        return "下午";
    }

    public static String f(Calendar calendar) {
        switch (calendar.get(7)) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "";
        }
    }

    public static String a(String str, String str2, int i) {
        String str3 = "0";
        if (str == null) {
            return str3;
        }
        if (str.equals("") || str2 == null) {
            return str3;
        }
        if (str2.equals("")) {
            return str3;
        }
        try {
            String str4;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            long time = simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
            if (time > 0) {
                long j = (time % 86400000) / a.j;
                long j2 = ((time % 86400000) % a.j) / 60000;
                long j3 = (((time % 86400000) % 86400000) % 60000) / 1000;
                str4 = "" + (time / 86400000);
                String str5 = j < 10 ? "0" + j : "" + j;
                String str6 = j2 < 10 ? "0" + j2 : "" + j2;
                String str7 = j3 < 10 ? "0" + j3 : "" + j3;
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        str4 = str5;
                        break;
                    case 2:
                        str4 = str6;
                        break;
                    case 3:
                        str4 = str7;
                        break;
                    default:
                        str4 = "00";
                        break;
                }
            }
            str4 = str3;
            return str4;
        } catch (ParseException e) {
            e.printStackTrace();
            return str3;
        }
    }

    public static String b(String str, String str2, int i) {
        String str3 = "00";
        if (str == null) {
            return str3;
        }
        if (str.equals("") || str2 == null) {
            return str3;
        }
        if (str2.equals("")) {
            return str3;
        }
        try {
            String str4;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            long time = simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
            if (time > 0) {
                long j = (time % 86400000) / a.j;
                long j2 = ((time % 86400000) % a.j) / 60000;
                long j3 = (((time % 86400000) % 86400000) % 60000) / 1000;
                str4 = "" + (time / 86400000);
                String str5 = j < 10 ? "0" + j : "" + j;
                String str6 = j2 < 10 ? "0" + j2 : "" + j2;
                String str7 = j3 < 10 ? "0" + j3 : "" + j3;
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        str4 = str5;
                        break;
                    case 2:
                        str4 = str6;
                        break;
                    case 3:
                        str4 = str7;
                        break;
                    default:
                        str4 = "00";
                        break;
                }
            }
            str4 = str3;
            return str4;
        } catch (ParseException e) {
            e.printStackTrace();
            return str3;
        }
    }

    public static String j(String str) {
        long j = 0;
        try {
            j = new SimpleDateFormat("HH:mm:ss").parse(str).getTime() + 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(j));
    }

    public static int b(String str, String str2) {
        return a(str, str2, "yyyy/MM/dd HH:mm:ss");
    }

    public static int a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3);
            long time = simpleDateFormat.parse(str).getTime() - simpleDateFormat.parse(str2).getTime();
            if (time > 0) {
                return 1;
            }
            if (time != 0) {
                return -1;
            }
            return 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String a(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String k(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        Calendar instance = Calendar.getInstance();
        simpleDateFormat.format(instance.getTime());
        return simpleDateFormat.format(instance.getTime());
    }

    public static String l(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        Calendar instance = Calendar.getInstance();
        instance.add(5, 1);
        simpleDateFormat.format(instance.getTime());
        return simpleDateFormat.format(instance.getTime());
    }
}
