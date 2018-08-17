package com.ecjia.hamster.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ecjia.a.q;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ah;
import com.ecjia.component.a.x;
import com.ecjia.component.view.ECJiaActionSheetDialog;
import com.ecjia.component.view.ECJiaActionSheetDialog.SheetItemColor;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.c.a.a.b;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.android.agoo.common.AgooConstants;

public class ECJiaSearchOrderActivity extends a implements OnClickListener, a {
    private LinearLayout a;
    private EditText b;
    private ECJiaXListView c;
    private x d;
    private String e;
    private com.ecjia.hamster.c.a.a k;
    private ArrayList<ECJia_ORDER_GOODS_LIST> l;
    private ah m;
    private Intent n;
    private int o;
    private int p = 0;

    class ECJiaSearchOrderActivity_1 implements OnTouchListener {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_1(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.finish();
            return false;
        }
    }

    class ECJiaSearchOrderActivity_2 implements OnTouchListener {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_2(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.finish();
            return false;
        }
    }

    class ECJiaSearchOrderActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_3(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaSearchOrderActivity_4 implements OnEditorActionListener {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_4(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (TextUtils.isEmpty(textView.getText())) {
                new k(this.a, this.a.getBaseContext().getResources().getString(R.string.search_please_input)).a();
            } else {
                this.a.a(this.a.b);
                if (i == 3) {
                    this.a.d.a(this.a.e, textView.getText().toString(), true);
                }
            }
            return false;
        }
    }

    class ECJiaSearchOrderActivity_5 extends TimerTask {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_5(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.b.getContext().getSystemService("input_method")).showSoftInput(this.a.b, 0);
            this.a.f.sendEmptyMessage(0);
        }
    }

    class ECJiaSearchOrderActivity_6 implements ECJiaXListView.a {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_6(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public void a(int i) {
            this.a.d.a(this.a.e, this.a.b.getText().toString(), false);
        }

        public void b(int i) {
            this.a.d.b(this.a.e, this.a.b.getText().toString());
        }
    }

    class ECJiaSearchOrderActivity_7 implements b {
        final /* synthetic */ ECJiaSearchOrderActivity a;

        ECJiaSearchOrderActivity_7(ECJiaSearchOrderActivity eCJiaSearchOrderActivity) {
            this.a = eCJiaSearchOrderActivity;
        }

        public void a(View view, final int i) {
            this.a.a(this.a.k.a(i).getOrder_status_code());
            switch (view.getId()) {
                case R.id.ll_trade_item:
                    if (this.a.p != 0) {
                        Intent intent = new Intent(this.a, ECJiaOrderdetailActivity.class);
                        intent.putExtra("order_id", this.a.k.a(i).getOrder_id());
                        intent.putExtra("pay_code", this.a.k.a(i).getOrder_info().getPay_code());
                        intent.putExtra(AgooConstants.MESSAGE_FLAG, this.a.p);
                        q.a("===flag===" + this.a.p);
                        this.a.startActivityForResult(intent, 1);
                        return;
                    }
                    return;
                case R.id.tv_trade_receive:
                    if (this.a.k.a(i).getOrder_status_code().equals("await_pay")) {
                        final ECJiaActionSheetDialog eCJiaActionSheetDialog = new ECJiaActionSheetDialog(this.a);
                        eCJiaActionSheetDialog.a().a("取消订单").a(false).b(true).a(this.a.g.getString(R.string.dialog_ensure), SheetItemColor.Red, new ECJiaActionSheetDialog.a(this) {
                            final /* synthetic */ ECJiaSearchOrderActivity_7 c;

                            public void a(int i) {
                                eCJiaActionSheetDialog.c();
                                this.c.a.d.b(this.c.a.k.a(i).getOrder_id());
                            }
                        }).b();
                        return;
                    } else if (this.a.k.a(i).getOrder_status_code().equals("await_ship")) {
                        this.a.d.d(this.a.k.a(i).getOrder_id(), "");
                        return;
                    } else if (!this.a.k.a(i).getOrder_status_code().equals("shipped") && this.a.k.a(i).getOrder_status_code().equals("finished")) {
                        return;
                    } else {
                        return;
                    }
                case R.id.tv_trade_comment:
                    if (!this.a.k.a(i).isToComment()) {
                        return;
                    }
                    if (this.a.d.f.size() == 1) {
                        this.a.n = new Intent(this.a, ECJiaCommentCreateActivity.class);
                        this.a.n.putExtra("goods_id", ((ECJia_ORDER_GOODS_LIST) this.a.k.a(i).getGoods_list().get(0)).getGoods_id());
                        this.a.n.putExtra("goods_price", ((ECJia_ORDER_GOODS_LIST) this.a.k.a(i).getGoods_list().get(0)).getFormated_shop_price());
                        this.a.n.putExtra("goods_name", ((ECJia_ORDER_GOODS_LIST) this.a.k.a(i).getGoods_list().get(0)).getName());
                        this.a.n.putExtra("goods_img", ((ECJia_ORDER_GOODS_LIST) this.a.k.a(i).getGoods_list().get(0)).getImg().getThumb());
                        this.a.n.putExtra("order_id", this.a.k.a(i).getOrder_id());
                        this.a.startActivityForResult(this.a.n, 1);
                        return;
                    }
                    this.a.n = new Intent(this.a, ECJiaOrderDetailCommentListActivity.class);
                    this.a.n.putExtra("order_id", this.a.k.a(i).getOrder_id());
                    this.a.startActivityForResult(this.a.n, 2);
                    return;
                case R.id.tv_trade_action:
                    if (this.a.k.a(i).getOrder_status_code().equals("await_pay")) {
                        String string = this.a.g.getString(R.string.balance_order_incloud);
                        String string2 = this.a.g.getString(R.string.balance_deng);
                        String string3 = this.a.g.getString(R.string.balance_zhong_goods);
                        this.a.n = new Intent(this.a, ECJiaChoosePayActivity.class);
                        this.a.n = new Intent(this.a, ECJiaChoosePayActivity.class);
                        this.a.n.putExtra("pay_type", "order_id");
                        this.a.n.putExtra("order_id", this.a.k.a(i).getOrder_id());
                        this.a.n.putExtra("pay_code", this.a.k.a(i).order_info.getPay_code());
                        this.a.n.putExtra("pay_is_create", false);
                        this.a.n.putExtra("pay_body", string + ((ECJia_ORDER_GOODS_LIST) this.a.k.a(i).getGoods_list().get(0)).getName() + string2 + this.a.k.a(i).getGoods_list().size() + string3);
                        this.a.n.putExtra("pay_amount", this.a.k.a(i).getFormated_total_fee() + "");
                        this.a.startActivityForResult(this.a.n, 1001);
                        return;
                    } else if (this.a.k.a(i).getOrder_status_code().equals("shipped")) {
                        this.a.d.c(this.a.k.a(i).getOrder_id());
                        return;
                    } else if (this.a.k.a(i).getOrder_status_code().equals("finished")) {
                        this.a.l = new ArrayList();
                        this.a.l.addAll(this.a.k.a(i).getGoods_list());
                        this.a.o = this.a.l.size();
                        if (this.a.o > 0) {
                            this.a.m.a(com.ecjia.a.k.f(((ECJia_ORDER_GOODS_LIST) this.a.l.get(0)).getGoods_id()) + "", new ArrayList(), com.ecjia.a.k.f(((ECJia_ORDER_GOODS_LIST) this.a.l.get(0)).getGoods_number()), null, null);
                            return;
                        }
                        return;
                    } else if (this.a.k.a(i).getOrder_status_code().equals("canceled")) {
                        this.a.l = new ArrayList();
                        this.a.l.addAll(this.a.k.a(i).getGoods_list());
                        this.a.o = this.a.l.size();
                        if (this.a.o > 0) {
                            this.a.m.a(com.ecjia.a.k.f(((ECJia_ORDER_GOODS_LIST) this.a.l.get(0)).getGoods_id()) + "", new ArrayList(), com.ecjia.a.k.f(((ECJia_ORDER_GOODS_LIST) this.a.l.get(0)).getGoods_number()), null, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_search_order);
        PushAgent.getInstance(this).onAppStart();
        b();
        e();
    }

    void b() {
        this.e = getIntent().getStringExtra(AgooConstants.MESSAGE_FLAG);
        if (TextUtils.isEmpty(this.e)) {
            this.e = "";
        }
        this.d = new x(this);
        this.d.a((a) this);
        this.m = new ah(this);
        this.m.a((a) this);
    }

    private void e() {
        findViewById(R.id.null_pager).setOnTouchListener(new ECJiaSearchOrderActivity_1(this));
        findViewById(R.id.order_search_empty).setOnTouchListener(new ECJiaSearchOrderActivity_2(this));
        findViewById(R.id.tv_search_cancel).setOnClickListener(new ECJiaSearchOrderActivity_3(this));
        this.a = (LinearLayout) findViewById(R.id.fl_search_top);
        this.a.setBackgroundColor(-2236963);
        this.b = (EditText) findViewById(R.id.et_search_input);
        this.b.setFocusable(true);
        this.b.setFocusableInTouchMode(true);
        this.b.requestFocus();
        this.b.setOnEditorActionListener(new ECJiaSearchOrderActivity_4(this));
        ((InputMethodManager) this.b.getContext().getSystemService("input_method")).showSoftInput(this.b, 0);
        new Timer().schedule(new ECJiaSearchOrderActivity_5(this), 300);
        this.c = (ECJiaXListView) findViewById(R.id.order_search_list);
        this.c.setPullLoadEnable(true);
        this.c.setRefreshTime();
        this.c.setXListViewListener(new ECJiaSearchOrderActivity_6(this), 1);
    }

    public void c() {
        if (this.k == null) {
            this.c.setBackgroundColor(-1);
            this.k = new com.ecjia.hamster.c.a.a(this, this.d.b);
            this.k.a(new ECJiaSearchOrderActivity_7(this));
            this.c.setAdapter(this.k);
        } else {
            this.k.notifyDataSetChanged();
        }
        if (this.d.b.size() == 0) {
            this.c.setVisibility(8);
            findViewById(R.id.order_search_empty).setBackgroundColor(Color.parseColor("#a0000000"));
            findViewById(R.id.order_search_empty).setEnabled(true);
            findViewById(R.id.null_pager).setVisibility(0);
            return;
        }
        this.c.setVisibility(0);
        findViewById(R.id.order_search_empty).setBackgroundColor(Color.parseColor("#FFEEEEEE"));
        findViewById(R.id.order_search_empty).setEnabled(false);
        findViewById(R.id.null_pager).setVisibility(8);
    }

    private void a(String str) {
        if (str.equals("await_pay")) {
            this.p = 1;
        } else if (str.equals("await_ship")) {
            this.p = 2;
        } else if (str.equals("shipped")) {
            this.p = 3;
        } else if (str.equals("finished")) {
            this.p = 4;
        } else if (str.equals("canceled")) {
            this.p = 5;
        }
    }

    @TargetApi(11)
    public void onClick(View view) {
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_4, 0);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        q.a("===requestCode===" + i + "===resultCode===" + i2);
        if (i == 1 && i2 == -1) {
            this.d.a(this.e, "", true);
        } else if (i == 1001 && i2 == -1) {
            this.d.a(this.e, "", true);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 2);
        return super.onTouchEvent(motionEvent);
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1569635637:
                if (str.equals("cart/create")) {
                    z = true;
                    break;
                }
                break;
            case -948047341:
                if (str.equals("order/reminder")) {
                    z = true;
                    break;
                }
                break;
            case 278583237:
                if (str.equals("order/affirmReceived")) {
                    z = true;
                    break;
                }
                break;
            case 711842495:
                if (str.equals("order/list")) {
                    z = false;
                    break;
                }
                break;
            case 915625051:
                if (str.equals("order/cancel")) {
                    z = true;
                    break;
                }
                break;
        }
        k kVar;
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.c.setRefreshTime();
                    if (this.d.a.a() == 0) {
                        this.c.setPullLoadEnable(false);
                    } else {
                        this.c.setPullLoadEnable(true);
                    }
                    c();
                    return;
                }
                return;
            case true:
                this.o--;
                if (this.o > 0) {
                    this.m.a(com.ecjia.a.k.f(((ECJia_ORDER_GOODS_LIST) this.l.get(this.l.size() - this.o)).getGoods_id()) + "", new ArrayList(), com.ecjia.a.k.f(((ECJia_ORDER_GOODS_LIST) this.l.get(this.l.size() - this.o)).getGoods_number()), null, null);
                    return;
                } else {
                    startActivity(new Intent(this, ECJiaShoppingCartActivity.class));
                    return;
                }
            case true:
                if (axVar.b() == 1) {
                    kVar = new k((Context) this, (int) R.string.tradeitem_receive);
                    kVar.b(b.REQUEST_MERGE_PERIOD);
                    kVar.a();
                    this.d.a(this.e, this.e, true);
                    return;
                }
                kVar = new k((Context) this, axVar.d());
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case true:
                if (axVar.b() == 1) {
                    kVar = new k((Context) this, this.g.getString(R.string.orderdetail_remind_success));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                kVar = new k((Context) this, this.g.getString(R.string.orderdetail_remind_failed));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case true:
                if (axVar.b() == 1) {
                    kVar = new k((Context) this, this.g.getString(R.string.order_canceled));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    this.d.a(this.e, "", false);
                    return;
                }
                kVar = new k((Context) this, axVar.d());
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            default:
                return;
        }
    }
}
