package com.baidu.location.h;

import java.util.List;

class i {
    public static String try = null;
    private double a = 0.0d;
    private boolean do = false;
    private String for = "";
    private double if = 0.0d;
    private boolean int = false;
    public int new = 0;

    public i(List list, String str, String str2, String str3) {
        this.for = str3;
        a();
    }

    private void a() {
        int i = 0;
        if (a(this.for)) {
            String substring = this.for.substring(0, this.for.length() - 3);
            int i2 = 0;
            while (i < substring.length()) {
                if (substring.charAt(i) == ',') {
                    i2++;
                }
                i++;
            }
            String[] split = substring.split(",", i2 + 1);
            if (split.length >= 6) {
                if (!(split[2].equals("") || split[split.length - 3].equals("") || split[split.length - 2].equals("") || split[split.length - 1].equals(""))) {
                    try {
                        this.a = Double.valueOf(split[split.length - 3]).doubleValue();
                        this.if = Double.valueOf(split[split.length - 2]).doubleValue();
                    } catch (Exception e) {
                    }
                    this.do = true;
                }
            } else {
                return;
            }
        }
        this.int = this.do;
    }

    private boolean a(String str) {
        if (str == null || str.length() <= 8) {
            return false;
        }
        int i = 0;
        for (int i2 = 1; i2 < str.length() - 3; i2++) {
            i ^= str.charAt(i2);
        }
        return Integer.toHexString(i).equalsIgnoreCase(str.substring(str.length() + -2, str.length()));
    }

    public boolean do() {
        return this.int;
    }

    public double for() {
        return this.a;
    }

    public double if() {
        return this.if;
    }
}
