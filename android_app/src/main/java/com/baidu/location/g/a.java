package com.baidu.location.g;

import android.util.Xml;
import com.baidu.location.b.f;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.analytics.pro.x;
import java.io.StringReader;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;

class a implements f {
    public String i3 = "";
    public String i4 = "";
    private double i5 = Double.MIN_VALUE;
    public String i6 = "";
    public String i7 = "";
    private boolean i8 = true;
    public String i9 = "";
    public String ja = "";
    public String jb = "";
    public String jc = "";
    private float jd = 0.0f;
    public String je = "";
    private double jf = Double.MIN_VALUE;
    private boolean jg = false;
    public String jh = "";

    public a(String str) {
        boolean z = false;
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(new StringReader(str));
        newPullParser.next();
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 2:
                    if (!z) {
                        if (!newPullParser.getName().equals("LocationRS") && !newPullParser.getName().equals("ReverseGeoRS")) {
                            break;
                        }
                        z = true;
                        break;
                    }
                    String name = newPullParser.getName();
                    if (name != null) {
                        if (!name.equals(ParamKey.LATITUDE)) {
                            if (!name.equals(ParamKey.LONGITUDE)) {
                                if (!name.equals("hpe")) {
                                    if (!name.equals(x.G)) {
                                        if (!name.equals("province")) {
                                            if (!name.equals("region")) {
                                                if (!name.equals("street-number")) {
                                                    if (!name.equals("city")) {
                                                        if (!name.equals("address-line")) {
                                                            if (!name.equals("state")) {
                                                                if (!name.equals("metro1")) {
                                                                    if (!name.equals("metro2")) {
                                                                        if (!name.equals("error")) {
                                                                            break;
                                                                        }
                                                                        this.i8 = false;
                                                                        break;
                                                                    }
                                                                    try {
                                                                        this.ja = newPullParser.nextText();
                                                                        break;
                                                                    } catch (Exception e) {
                                                                        break;
                                                                    }
                                                                }
                                                                try {
                                                                    this.jc = newPullParser.nextText();
                                                                    break;
                                                                } catch (Exception e2) {
                                                                    break;
                                                                }
                                                            }
                                                            try {
                                                                this.i3 = newPullParser.nextText();
                                                                break;
                                                            } catch (Exception e3) {
                                                                break;
                                                            }
                                                        }
                                                        try {
                                                            this.je = newPullParser.nextText();
                                                            break;
                                                        } catch (Exception e4) {
                                                            break;
                                                        }
                                                    }
                                                    try {
                                                        this.jb = newPullParser.nextText();
                                                        break;
                                                    } catch (Exception e5) {
                                                        break;
                                                    }
                                                }
                                                try {
                                                    this.i4 = newPullParser.nextText();
                                                    break;
                                                } catch (Exception e6) {
                                                    break;
                                                }
                                            }
                                            try {
                                                this.jh = newPullParser.nextText();
                                                break;
                                            } catch (Exception e7) {
                                                break;
                                            }
                                        }
                                        try {
                                            this.i6 = newPullParser.nextText();
                                            break;
                                        } catch (Exception e8) {
                                            break;
                                        }
                                    }
                                    try {
                                        this.i9 = newPullParser.getAttributeValue(0);
                                    } catch (Exception e9) {
                                    }
                                    try {
                                        this.i7 = newPullParser.nextText();
                                        break;
                                    } catch (Exception e10) {
                                        break;
                                    }
                                }
                                try {
                                    this.jd = Float.valueOf(newPullParser.nextText()).floatValue();
                                    break;
                                } catch (Exception e11) {
                                    this.i8 = false;
                                    break;
                                }
                            }
                            try {
                                this.i5 = Double.valueOf(newPullParser.nextText()).doubleValue();
                                break;
                            } catch (Exception e12) {
                                try {
                                    this.i8 = false;
                                    break;
                                } catch (Exception e13) {
                                    return;
                                }
                            }
                        }
                        try {
                            this.jf = Double.valueOf(newPullParser.nextText()).doubleValue();
                            break;
                        } catch (Exception e14) {
                            this.i8 = false;
                            break;
                        }
                    }
                    continue;
                default:
                    break;
            }
        }
    }

    public boolean ct() {
        return this.i8;
    }

    public double cu() {
        return this.jf;
    }

    public double cv() {
        return this.i5;
    }

    public float cw() {
        return this.jd;
    }

    public String cx() {
        if (!this.i8) {
            return null;
        }
        return String.format(Locale.CHINA, "&skyll=%.6f|%.6f|%.1f&skyflag=1&skyadd=%s|%s|%s", new Object[]{Double.valueOf(this.i5), Double.valueOf(this.jf), Float.valueOf(this.jd), this.i7, this.jb, this.je});
    }

    public boolean cy() {
        return this.i7.equals("China") || this.i7.equals("Taiwan") || this.i9.equals("HK");
    }
}
