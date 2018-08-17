package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.view.c;
import com.ecjia.component.view.j;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecjia.hamster.model.ECJia_ORDER_INFO;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecjia.hamster.model.ECJia_SHIPPING;
import com.ecjia.hamster.model.ECJia_STOREGOODSLIST;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaBalanceActivity extends a implements OnClickListener, a {
    private LinearLayout A;
    private TextView B;
    private LinearLayout C;
    private TextView D;
    private LinearLayout E;
    private TextView F;
    private TextView G;
    private TextView H;
    private LinearLayout I;
    private ah J;
    private float K;
    private float L;
    private ArrayList<ECJia_GOODS_LIST> M;
    private String N;
    private ECJia_PAYMENT O;
    private String P = "";
    private String Q = null;
    private String R = null;
    private String S = null;
    private String T = null;
    private String U = null;
    private String V = null;
    private String W;
    private TextView X;
    private TextView Y;
    private TextView Z;
    ECJia_BONUS a;
    private ImageView aA;
    private TextView aB;
    private TextView aC;
    private TextView aD;
    private TextView aE;
    private LinearLayout aF;
    private LinearLayout aG;
    private ImageView aH;
    private ImageView aI;
    private ImageView aJ;
    private FrameLayout aK;
    private FrameLayout aL;
    private FrameLayout aM;
    private TextView aN;
    private TextView aO;
    private TextView aP;
    private TextView aQ;
    private String aR;
    private ImageView aS;
    private boolean aT;
    private ImageView aa;
    private ImageView ab;
    private ImageView ac;
    private TextView ad;
    private TextView ae;
    private LinearLayout af;
    private View ag;
    private LinearLayout ah;
    private TextView ai;
    private TextView aj;
    private Intent ak;
    private String al = "";
    private String am;
    private String an = "";
    private SharedPreferences ao;
    private TextView ap;
    private int aq = 0;
    private String ar = null;
    private float as;
    private float at;
    private float au;
    private float av;
    private TextView aw;
    private TextView ax;
    private LinearLayout ay;
    private LinearLayout az;
    ECJia_ORDER_INFO b;
    String c;
    String d;
    float e = 0.0f;
    private TextView k;
    private ImageView l;
    private LinearLayout m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private LinearLayout r;
    private TextView s;
    private LinearLayout t;
    private TextView u;
    private LinearLayout v;
    private TextView w;
    private long x;
    private LinearLayout y;
    private TextView z;

    class ECJiaBalanceActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaBalanceActivity a;

        ECJiaBalanceActivity_1(ECJiaBalanceActivity eCJiaBalanceActivity) {
            this.a = eCJiaBalanceActivity;
        }

        public void onClick(View view) {
            this.a.g();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    class ECJiaBalanceActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaBalanceActivity a;

        ECJiaBalanceActivity_2(ECJiaBalanceActivity eCJiaBalanceActivity) {
            this.a = eCJiaBalanceActivity;
        }

        public void onClick(View view) {
            if (this.a.J.c.size() != 1) {
                Intent intent = new Intent(this.a, ECJiaShopGoodsActivity.class);
                intent.putExtra("goods_list", this.a.M);
                this.a.startActivity(intent);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.balance);
        PushAgent.getInstance(this).onAppStart();
        this.ao = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.k = (TextView) findViewById(R.id.top_view_text);
        CharSequence string = this.g.getString(R.string.shopcarfooter_settleaccounts);
        this.c = this.g.getString(R.string.yuan_unit);
        this.d = this.g.getString(R.string.yuan);
        this.k.setText(string);
        this.l = (ImageView) findViewById(R.id.top_view_back);
        this.l.setOnClickListener(new ECJiaBalanceActivity_1(this));
        this.ak = getIntent();
        this.al = this.ak.getStringExtra("rec_ids");
        this.an = this.ak.getStringExtra("address_id");
        this.am = this.ak.getStringExtra("rec_type");
        if (TextUtils.isEmpty(this.am)) {
            this.am = "";
        }
        this.M = new ArrayList();
        this.ay = (LinearLayout) findViewById(R.id.ll_balance_goods_area);
        this.az = (LinearLayout) findViewById(R.id.ll_balance_goods_single_item);
        this.aA = (ImageView) findViewById(R.id.iv_balance_goods_single);
        this.aB = (TextView) findViewById(R.id.tv_balance_goods_single_name);
        this.aC = (TextView) findViewById(R.id.tv_balance_goods_single_spec);
        this.aD = (TextView) findViewById(R.id.tv_balance_goods_single_fee);
        this.aE = (TextView) findViewById(R.id.tv_balance_goods_single_num);
        this.aF = (LinearLayout) findViewById(R.id.ll_balance_goods_multiple);
        this.aG = (LinearLayout) findViewById(R.id.ll_balance_goods_right);
        this.aH = (ImageView) findViewById(R.id.iv_balance_goods1);
        this.aK = (FrameLayout) findViewById(R.id.fl_balance_goods_num1);
        this.aN = (TextView) findViewById(R.id.tv_balance_goods_num1);
        this.aI = (ImageView) findViewById(R.id.iv_balance_goods2);
        this.aL = (FrameLayout) findViewById(R.id.fl_balance_goods_num2);
        this.aO = (TextView) findViewById(R.id.tv_balance_goods_num2);
        this.aJ = (ImageView) findViewById(R.id.iv_balance_goods3);
        this.aM = (FrameLayout) findViewById(R.id.fl_balance_goods_num3);
        this.aP = (TextView) findViewById(R.id.tv_balance_goods_num3);
        this.aQ = (TextView) findViewById(R.id.tv_balance_goods_all_num);
        this.ay.setOnClickListener(new ECJiaBalanceActivity_2(this));
        this.ah = (LinearLayout) findViewById(R.id.balance_distance);
        this.m = (LinearLayout) findViewById(R.id.balance_user);
        this.n = (TextView) findViewById(R.id.balance_name);
        this.o = (TextView) findViewById(R.id.balance_phoneNum);
        this.p = (TextView) findViewById(R.id.balance_address);
        this.q = (TextView) findViewById(R.id.balance_address2);
        this.r = (LinearLayout) findViewById(R.id.balance_pay);
        this.s = (TextView) findViewById(R.id.balance_pay_type);
        this.t = (LinearLayout) findViewById(R.id.balance_dis);
        this.u = (TextView) findViewById(R.id.balance_dis_type);
        this.v = (LinearLayout) findViewById(R.id.balance_invoice);
        this.w = (TextView) findViewById(R.id.balance_invoice_message);
        this.y = (LinearLayout) findViewById(R.id.balance_goods);
        this.z = (TextView) findViewById(R.id.balance_goods_num);
        this.A = (LinearLayout) findViewById(R.id.balance_redPaper);
        this.B = (TextView) findViewById(R.id.balance_redPaper_name);
        this.C = (LinearLayout) findViewById(R.id.balance_integral);
        this.D = (TextView) findViewById(R.id.balance_integral_num);
        this.af = (LinearLayout) findViewById(R.id.balance_moreitem_body);
        this.X = (TextView) findViewById(R.id.tv_redPaper);
        this.Y = (TextView) findViewById(R.id.tv_integral);
        this.Z = (TextView) findViewById(R.id.tv_invoice);
        this.aa = (ImageView) findViewById(R.id.iv_redpager);
        this.ab = (ImageView) findViewById(R.id.iv_integral);
        this.ac = (ImageView) findViewById(R.id.iv_invoice);
        this.ag = findViewById(R.id.invoice_line);
        this.ad = (TextView) findViewById(R.id.tv_redpager_status);
        this.ae = (TextView) findViewById(R.id.tv_integral_status);
        this.H = (TextView) findViewById(R.id.balance_total);
        this.I = (LinearLayout) findViewById(R.id.balance_submit);
        this.E = (LinearLayout) findViewById(R.id.balance_remark);
        this.F = (TextView) findViewById(R.id.tv_remark);
        this.ai = (TextView) findViewById(R.id.balance_cast_text);
        this.aj = (TextView) findViewById(R.id.balance_goback_text);
        this.ap = (TextView) findViewById(R.id.invoice);
        this.G = (TextView) findViewById(R.id.balance_fees);
        this.aw = (TextView) findViewById(R.id.balance_bonus_fee);
        this.ax = (TextView) findViewById(R.id.balance_integral_fee);
        this.aS = (ImageView) findViewById(R.id.iv_shipping_fee_detail);
        this.m.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.aS.setOnClickListener(this);
        if (this.J == null) {
            this.J = new ah(this);
            this.J.a((a) this);
            this.J.b(this.al, this.an);
            return;
        }
        b();
    }

    public void onClick(View view) {
        String string = this.g.getString(R.string.balance_invoice_close);
        String string2 = this.g.getString(R.string.balance_notsupport_redpaper);
        String string3 = this.g.getString(R.string.balance_notsupport_integral);
        String string4 = this.g.getString(R.string.balance_choose_payment);
        String string5 = this.g.getString(R.string.balance_choose_shipping);
        k kVar;
        Intent intent;
        k kVar2;
        switch (view.getId()) {
            case R.id.balance_submit:
                if (this.O == null) {
                    kVar = new k((Context) this, string4);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if (TextUtils.isEmpty(this.aR)) {
                    kVar = new k((Context) this, string5);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else {
                    JSONArray e = e();
                    if (!this.aT) {
                        if (this.a != null) {
                            this.J.a(this.al, this.O.getPay_id(), this.an, e, this.a.getBonus_id(), this.P, this.S, this.U, this.T, this.W, this.V);
                            return;
                        } else {
                            this.J.a(this.al, this.O.getPay_id(), this.an, e, null, this.P, this.S, this.U, this.T, this.W, this.V);
                            return;
                        }
                    }
                    return;
                }
            case R.id.balance_user:
                intent = new Intent(this, ECJiaAddressManageActivity.class);
                intent.putExtra(AgooConstants.MESSAGE_FLAG, 1);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.balance_pay:
                if (this.J.z.size() == 0) {
                    kVar = new k((Context) this, (int) R.string.balance_nopayment);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else if (TextUtils.isEmpty(this.aR)) {
                    kVar = new k((Context) this, (int) R.string.no_mode_of_distribution);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else {
                    intent = new Intent(this, ECJiaPaymentAndShippingActivity.class);
                    intent.putExtra("payment_list_online", this.J.A);
                    intent.putExtra("payment_list_offline", this.J.B);
                    intent.putExtra("store_goods_list", this.J.d);
                    startActivityForResult(intent, 2);
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                }
            case R.id.balance_invoice:
                if ("0".equals(this.J.h)) {
                    kVar2 = new k((Context) this, string);
                    kVar2.a(17, 0, 0);
                    kVar2.a();
                    return;
                }
                intent = new Intent(this, ECJiaInvoiceActivity.class);
                intent.putExtra("payment", this.N);
                intent.putExtra("inv_type", this.S);
                intent.putExtra("inv_content", this.T);
                if (this.U == null) {
                    intent.putExtra("inv_payee", this.U);
                } else if (this.U.equals("个人")) {
                    intent.putExtra("inv_payee", "");
                } else {
                    intent.putExtra("inv_payee", this.U);
                }
                intent.putExtra("inv_code", this.V);
                startActivityForResult(intent, 5);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                q.a("+++++跳转至InvoiceActivity页面+++++");
                return;
            case R.id.balance_remark:
                intent = new Intent(this, ECJiaRemarkActivity.class);
                intent.putExtra("remark", this.W);
                startActivityForResult(intent, 7);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.balance_goods:
                kVar2 = new k((Context) this, getBaseContext().getResources().getString(R.string.balance_list));
                kVar2.a(17, 0, 0);
                kVar2.a();
                return;
            case R.id.balance_redPaper:
                if (this.J.i.size() == 0 || !this.J.x) {
                    kVar = new k((Context) this, string2);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                intent = new Intent(this, ECJiaRedPacketsActivity.class);
                intent.putExtra("payment", this.N);
                startActivityForResult(intent, 6);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.balance_integral:
                if (this.J.j == 0 || this.x == 0) {
                    kVar = new k((Context) this, string3);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                intent = new Intent(this, ECJiaIntegralActivity.class);
                intent.putExtra("integral", this.N);
                intent.putExtra("used_integral", this.P);
                startActivityForResult(intent, 4);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.iv_shipping_fee_detail:
                new j(this, this.J.d).a();
                return;
            default:
                return;
        }
    }

    private JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        this.aT = false;
        for (int i = 0; i < this.J.d.size(); i++) {
            for (int i2 = 0; i2 < ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().size(); i2++) {
                if (((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2)).isSelected()) {
                    if (a(this.O, (ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2))) {
                        jSONArray.put(((ECJia_STOREGOODSLIST) this.J.d.get(i)).getRu_id() + SocializeConstants.OP_DIVIDER_MINUS + ((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2)).getShipping_id());
                    } else {
                        this.aT = true;
                        k kVar = new k((Context) this, ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getRu_name() + this.g.getString(R.string.balance_notsupport_COD));
                        kVar.a(17, 0, 0);
                        kVar.a();
                    }
                }
            }
        }
        return jSONArray;
    }

    public boolean a(ECJia_PAYMENT eCJia_PAYMENT, ECJia_SHIPPING eCJia_SHIPPING) {
        if (eCJia_PAYMENT == null || eCJia_SHIPPING == null || !eCJia_PAYMENT.getIs_cod().equals("1") || !eCJia_SHIPPING.getSupport_cod().equals("0")) {
            return true;
        }
        return false;
    }

    public void b() {
        String string = this.g.getString(R.string.yuan);
        String string2 = this.g.getString(R.string.yuan_unit);
        this.K = 0.0f;
        this.N = this.J.e;
        this.n.setText(this.J.b.getConsignee());
        this.o.setText(this.J.b.getMobile());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.J.b.getProvince_name() + " ");
        stringBuffer.append(this.J.b.getCity_name() + " ");
        stringBuffer.append(this.J.b.getDistrict_name() + " ");
        stringBuffer.append(this.J.b.getAddress());
        CharSequence a = a(stringBuffer.toString());
        this.p.setText(a);
        this.q.setText(a);
        for (int i = 0; i < this.J.c.size(); i++) {
            this.z.setText("X " + ((ECJia_GOODS_LIST) this.J.c.get(i)).getGoods_number());
            this.K = Float.parseFloat(((ECJia_GOODS_LIST) this.J.c.get(i)).getSubtotal().replace("$", "").replace("￥", "").replace("元", "")) + this.K;
        }
        this.ai.setText(string2 + com.ecjia.a.k.a(this.K) + string);
        this.as = Float.parseFloat(this.J.g);
        this.aj.setText(SocializeConstants.OP_DIVIDER_MINUS + string2 + this.J.g + string);
        if (this.as > 0.0f) {
            findViewById(R.id.discount_layout).setVisibility(0);
        } else {
            findViewById(R.id.discount_layout).setVisibility(8);
        }
        h();
        if ("0".equals(this.J.h)) {
            this.v.setVisibility(8);
            this.ag.setVisibility(8);
        } else {
            this.v.setBackgroundResource(R.drawable.selecter_newitem_press);
        }
        if (!this.J.x || this.J.i.size() <= 0) {
            this.X.setTextColor(this.g.getColor(R.color.useless));
            this.ad.setText(this.g.getString(R.string.balance_null_use));
            this.aa.setVisibility(8);
        } else {
            this.A.setBackgroundResource(R.drawable.selecter_newitem_press);
            this.ad.setText(this.J.i.size() + this.g.getString(R.string.balance_redpager_use));
            this.A.setEnabled(true);
        }
        this.x = Math.min(this.J.t, this.J.u);
        if (this.J.j == 0 || this.x == 0) {
            this.Y.setTextColor(this.g.getColor(R.color.useless));
            this.ae.setText(this.g.getString(R.string.balance_null_use));
            this.ab.setVisibility(8);
        } else {
            this.C.setBackgroundResource(R.drawable.selecter_newitem_press);
            this.ae.setText(this.x + this.g.getString(R.string.balance_integral_use));
            this.C.setEnabled(true);
        }
        c();
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        a(intent);
    }

    private void a(Intent intent) {
        intent.getAction();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 1:
                if (i2 == -1 && intent != null) {
                    this.an = intent.getStringExtra("address_id");
                    this.J.b(this.al, this.an);
                    break;
                }
            case 2:
                if (i2 == -1 && intent != null) {
                    this.O = (ECJia_PAYMENT) intent.getSerializableExtra("payment");
                    ArrayList arrayList = (ArrayList) intent.getSerializableExtra("payment_list_online");
                    ArrayList arrayList2 = (ArrayList) intent.getSerializableExtra("payment_list_offline");
                    ArrayList arrayList3 = (ArrayList) intent.getSerializableExtra("store_goods_list");
                    if (this.O != null) {
                        this.s.setText(this.O.getPay_name());
                    }
                    if (arrayList != null) {
                        this.J.A.clear();
                        this.J.A.addAll(arrayList);
                    }
                    if (arrayList2 != null) {
                        this.J.B.clear();
                        this.J.B.addAll(arrayList2);
                    }
                    if (arrayList3 != null) {
                        this.J.d.clear();
                        this.J.d.addAll(arrayList3);
                        h();
                        c();
                        break;
                    }
                }
                break;
            case 3:
                if (i2 == -1 && intent != null) {
                    break;
                }
            case 4:
                if (i2 == -1 && intent != null) {
                    this.P = intent.getStringExtra("input");
                    if (TextUtils.isEmpty(this.P)) {
                        this.D.setText("");
                        this.Q = "0";
                        this.av = 0.0f;
                        this.R = "";
                    } else {
                        String string = this.g.getString(R.string.use);
                        this.D.setText(string + this.P + this.g.getString(R.string.integral_integral));
                        this.Q = intent.getStringExtra("bonus");
                        if (!TextUtils.isEmpty(this.Q)) {
                            try {
                                this.av = Float.parseFloat(this.Q);
                            } catch (Exception e) {
                                this.av = 0.0f;
                            }
                        }
                        this.R = intent.getStringExtra("bonus_formated");
                    }
                    if (this.av > 0.0f) {
                        findViewById(R.id.integral_layout).setVisibility(0);
                    } else {
                        findViewById(R.id.integral_layout).setVisibility(8);
                    }
                    this.ax.setText(SocializeConstants.OP_DIVIDER_MINUS + this.c + com.ecjia.a.k.a(this.av) + this.d);
                    c();
                    break;
                }
        }
        if (i == 5) {
            if (intent != null) {
                this.L -= this.e;
                this.S = intent.getStringExtra("inv_type");
                this.T = intent.getStringExtra("inv_content");
                this.U = intent.getStringExtra("inv_payee");
                this.V = intent.getStringExtra("inv_code");
                q.a("inv_code====" + this.V);
                this.w.setText(this.U);
                this.ar = intent.getStringExtra("inv_type_rate");
                if (TextUtils.isEmpty(this.ar)) {
                    this.e = 0.0f;
                } else {
                    this.e = (this.K * Float.parseFloat(this.ar)) / 100.0f;
                }
                if (this.e > 0.0f) {
                    findViewById(R.id.invoice_layout).setVisibility(0);
                } else {
                    findViewById(R.id.invoice_layout).setVisibility(8);
                }
                this.ap.setText(SocializeConstants.OP_DIVIDER_PLUS + this.c + com.ecjia.a.k.a(this.e) + this.d);
                c();
            }
        } else if (i == 6) {
            if (intent != null) {
                string = intent.getStringExtra("bonus");
                if (string != null) {
                    try {
                        this.a = ECJia_BONUS.fromJson(new JSONObject(string));
                        if (this.a != null) {
                            try {
                                this.au = Float.parseFloat(this.a.getType_money());
                            } catch (Exception e2) {
                                this.au = 0.0f;
                            }
                        }
                        this.B.setText(this.a.getType_name() + "[" + this.a.getBonus_money_formated() + "]");
                        c();
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                } else {
                    this.a = null;
                    this.au = 0.0f;
                    this.B.setText("");
                }
                if (this.au > 0.0f) {
                    findViewById(R.id.bonus_layout).setVisibility(0);
                } else {
                    findViewById(R.id.bonus_layout).setVisibility(8);
                }
                this.aw.setText(SocializeConstants.OP_DIVIDER_MINUS + this.c + com.ecjia.a.k.a(this.au) + this.d);
                c();
            }
        } else if (i == 7 && intent != null) {
            this.W = intent.getStringExtra("remark");
            this.F.setText(this.W);
        }
    }

    void c() {
        this.L = this.K - this.as;
        if (this.L < 0.0f) {
            this.L = 0.0f;
        }
        this.L += this.e;
        if (this.L < 0.0f) {
            this.L = 0.0f;
        }
        this.L -= this.au;
        if (this.L < 0.0f) {
            this.L = 0.0f;
        }
        this.L -= this.av;
        if (this.L < 0.0f) {
            this.L = 0.0f;
        }
        this.L += this.at;
        this.H.setText(this.c + com.ecjia.a.k.a(this.L) + this.d);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            g();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return true;
    }

    public String a(String str) {
        char[] toCharArray = str.toCharArray();
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] == '　') {
                toCharArray[i] = ' ';
            } else if (toCharArray[i] > '＀' && toCharArray[i] < '｟') {
                toCharArray[i] = (char) (toCharArray[i] - 65248);
            }
            i++;
        }
        return new String(toCharArray);
    }

    private String f() {
        String string = this.g.getString(R.string.balance_order_incloud);
        String string2 = this.g.getString(R.string.balance_deng);
        return string + ((ECJia_GOODS_LIST) this.J.c.get(0)).getGoods_name() + string2 + this.J.c.size() + this.g.getString(R.string.balance_zhong_goods);
    }

    private void g() {
        String str = this.am;
        Object obj = -1;
        switch (str.hashCode()) {
            case -221455202:
                if (str.equals("GROUPBUY_GOODS")) {
                    obj = 1;
                    break;
                }
                break;
            case 1003296157:
                if (str.equals("SPIKE_GOODS")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
                this.J.a(this.al);
                return;
            default:
                finish();
                return;
        }
    }

    private void h() {
        this.aR = "";
        this.at = 0.0f;
        for (int i = 0; i < this.J.d.size(); i++) {
            for (int i2 = 0; i2 < ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().size(); i2++) {
                if (((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2)).isSelected()) {
                    if (!this.aR.contains(((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2)).getShipping_name())) {
                        this.aR += SocializeConstants.OP_DIVIDER_PLUS + ((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2)).getShipping_name();
                    }
                    try {
                        this.at = com.ecjia.a.k.a(((ECJia_SHIPPING) ((ECJia_STOREGOODSLIST) this.J.d.get(i)).getShipping_list().get(i2)).getShipping_fee()) + this.at;
                    } catch (Exception e) {
                        this.at += 0.0f;
                    }
                }
            }
        }
        if (this.aR.length() > 0) {
            this.aR = this.aR.substring(1, this.aR.length());
        }
        this.u.setText(this.aR);
        this.G.setText(SocializeConstants.OP_DIVIDER_PLUS + com.ecjia.a.k.a(this.at));
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("flow/checkOrder")) {
            this.r.setClickable(false);
            this.t.setClickable(false);
        } else if (axVar.b() == 1) {
            if (this.J.A.size() > 0) {
                this.O = (ECJia_PAYMENT) this.J.A.get(0);
                this.s.setText(this.O.getPay_name());
            } else if (this.J.B.size() > 0) {
                this.O = (ECJia_PAYMENT) this.J.B.get(0);
                this.s.setText(this.O.getPay_name());
            } else {
                this.s.setText("");
            }
            b();
            this.M = this.J.c;
            if (this.J.c.size() == 1) {
                this.ay.setVisibility(0);
                this.az.setVisibility(0);
                this.aF.setVisibility(8);
                p.a().a(this.aA, ((ECJia_GOODS_LIST) this.J.c.get(0)).getImg().getThumb());
                this.aB.setText(((ECJia_GOODS_LIST) this.J.c.get(0)).getGoods_name());
                this.aD.setText(((ECJia_GOODS_LIST) this.J.c.get(0)).getFormated_goods_price());
                this.aE.setText("X " + ((ECJia_GOODS_LIST) this.J.c.get(0)).getGoods_number());
            } else if (this.J.c.size() > 1) {
                String str3;
                CharSequence charSequence;
                Object obj;
                this.ay.setVisibility(0);
                this.az.setVisibility(8);
                this.aF.setVisibility(0);
                p.a((Context) this).a(this.aH, ((ECJia_GOODS_LIST) this.J.c.get(0)).getImg().getThumb());
                if (((ECJia_GOODS_LIST) this.J.c.get(0)).getGoods_number() > 1) {
                    this.aK.setVisibility(0);
                    str3 = ((ECJia_GOODS_LIST) this.J.c.get(0)).getGoods_number() + "";
                    if (((ECJia_GOODS_LIST) this.J.c.get(0)).getGoods_number() > 99) {
                        charSequence = "99+";
                    } else {
                        obj = str3;
                    }
                    this.aN.setText(charSequence);
                } else {
                    this.aK.setVisibility(8);
                }
                p.a((Context) this).a(this.aI, ((ECJia_GOODS_LIST) this.J.c.get(1)).getImg().getThumb());
                if (((ECJia_GOODS_LIST) this.J.c.get(1)).getGoods_number() > 1) {
                    this.aL.setVisibility(0);
                    str3 = ((ECJia_GOODS_LIST) this.J.c.get(1)).getGoods_number() + "";
                    if (((ECJia_GOODS_LIST) this.J.c.get(1)).getGoods_number() > 99) {
                        charSequence = "99+";
                    } else {
                        obj = str3;
                    }
                    this.aO.setText(charSequence);
                } else {
                    this.aL.setVisibility(8);
                }
                if (this.J.c.size() == 2) {
                    this.aG.setVisibility(8);
                } else {
                    this.aG.setVisibility(0);
                    p.a((Context) this).a(this.aJ, ((ECJia_GOODS_LIST) this.J.c.get(2)).getImg().getThumb());
                    if (((ECJia_GOODS_LIST) this.J.c.get(2)).getGoods_number() > 1) {
                        this.aM.setVisibility(0);
                        str3 = ((ECJia_GOODS_LIST) this.J.c.get(2)).getGoods_number() + "";
                        if (((ECJia_GOODS_LIST) this.J.c.get(2)).getGoods_number() > 99) {
                            charSequence = "99+";
                        } else {
                            obj = str3;
                        }
                        this.aP.setText(charSequence);
                    } else {
                        this.aM.setVisibility(8);
                    }
                }
            } else {
                this.ay.setVisibility(8);
            }
            this.aQ.setText(this.g.getString(R.string.balance_goods_num).replace("#replace#", this.J.c.size() + ""));
        }
        if (str.equals("flow/done")) {
            if (axVar.b() == 1) {
                try {
                    this.b = ECJia_ORDER_INFO.fromJson(this.J.E.getJSONObject("data").optJSONObject("order_info"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, ECJiaChoosePayActivity.class);
                intent.putExtra("pay_type", "order_id");
                intent.putExtra("order_id", this.b.getOrder_id() + "");
                intent.putExtra("pay_is_create", true);
                intent.putExtra("pay_body", f());
                intent.putExtra("pay_amount", this.b.getOrder_amount() + "");
                intent.putExtra("pay_code", this.b.getPay_code());
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            } else if (axVar.e().equals("unsupport_shipping_goods_error")) {
                final c cVar = new c(this, this.g.getString(R.string.point), axVar.d());
                cVar.a();
                cVar.c();
                cVar.a(1);
                cVar.a(new OnClickListener(this) {
                    final /* synthetic */ ECJiaBalanceActivity b;

                    public void onClick(View view) {
                        cVar.b();
                    }
                });
            } else {
                new k((Context) this, axVar.d()).a();
            }
        }
        if (!str.equals("cart/delete")) {
            return;
        }
        if (axVar.b() == 1) {
            finish();
        } else {
            new k((Context) this, axVar.d()).a();
        }
    }
}
