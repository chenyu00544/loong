package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.a.y;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.aa;
import com.ecjia.hamster.adapter.ab;
import com.ecjia.hamster.model.u;
import com.ecjia.hamster.model.v;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang3.c;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaInvoiceActivity extends a implements OnClickListener {
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private boolean D = false;
    private ImageView a;
    private Button b;
    private EditText c;
    private EditText d;
    private ListView e;
    private ListView k;
    private TextView l;
    private ArrayList<u> m = new ArrayList();
    private ArrayList<v> n = new ArrayList();
    private aa o;
    private ab p;
    private TextView q;
    private TextView r;
    private String s = null;
    private String t = null;
    private String u = null;
    private String v = null;
    private String w = null;
    private TextView x;
    private TextView y;
    private LinearLayout z;

    class ECJiaInvoiceActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaInvoiceActivity a;

        ECJiaInvoiceActivity_1(ECJiaInvoiceActivity eCJiaInvoiceActivity) {
            this.a = eCJiaInvoiceActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaInvoiceActivity_2 extends TimerTask {
        final /* synthetic */ ECJiaInvoiceActivity a;

        ECJiaInvoiceActivity_2(ECJiaInvoiceActivity eCJiaInvoiceActivity) {
            this.a = eCJiaInvoiceActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.c.getContext().getSystemService("input_method")).showSoftInput(this.a.c, 0);
        }
    }

    class ECJiaInvoiceActivity_3 implements OnItemClickListener {
        final /* synthetic */ ECJiaInvoiceActivity a;

        ECJiaInvoiceActivity_3(ECJiaInvoiceActivity eCJiaInvoiceActivity) {
            this.a = eCJiaInvoiceActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.o.a = i;
            this.a.o.notifyDataSetChanged();
            this.a.t = ((u) this.a.m.get(i)).a();
        }
    }

    class ECJiaInvoiceActivity_4 implements OnItemClickListener {
        final /* synthetic */ ECJiaInvoiceActivity a;

        ECJiaInvoiceActivity_4(ECJiaInvoiceActivity eCJiaInvoiceActivity) {
            this.a = eCJiaInvoiceActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.p.a = i;
            this.a.p.notifyDataSetChanged();
            this.a.s = ((v) this.a.n.get(i)).a();
            this.a.u = ((v) this.a.n.get(i)).b();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (a(currentFocus, motionEvent)) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(motionEvent);
        } else if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        } else {
            return onTouchEvent(motionEvent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.invoice);
        PushAgent.getInstance(this).onAppStart();
        this.e = (ListView) findViewById(R.id.invoice_list1);
        this.k = (ListView) findViewById(R.id.invoice_list2);
        this.q = (TextView) findViewById(R.id.invoice_type);
        this.r = (TextView) findViewById(R.id.invoice_content);
        this.x = (TextView) findViewById(R.id.personal_txt);
        this.x.setBackgroundResource(R.drawable.bala_invoice_itme_two);
        this.y = (TextView) findViewById(R.id.company_txt);
        this.z = (LinearLayout) findViewById(R.id.invoice_lat);
        this.A = (ImageView) findViewById(R.id.invoice_img);
        this.B = (ImageView) findViewById(R.id.invoice_company_img);
        this.C = (ImageView) findViewById(R.id.invoice_personal_img);
        this.y.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.A.setOnClickListener(this);
        Intent intent = getIntent();
        Object stringExtra = intent.getStringExtra("payment");
        this.w = intent.getStringExtra("inv_payee");
        this.t = intent.getStringExtra("inv_content");
        this.s = intent.getStringExtra("inv_type");
        this.v = intent.getStringExtra("inv_code");
        if (TextUtils.isEmpty(this.v)) {
            this.x.setBackgroundResource(R.drawable.bala_invoice_itme_two);
            this.C.setVisibility(0);
            this.z.setVisibility(8);
        } else {
            this.y.setBackgroundResource(R.drawable.bala_invoice_itme_two);
            this.B.setVisibility(0);
            this.z.setVisibility(0);
        }
        try {
            if (c.b(stringExtra)) {
                int i;
                JSONObject jSONObject = new JSONObject(stringExtra);
                JSONArray optJSONArray = jSONObject.optJSONArray("inv_content_list");
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    this.e.setVisibility(8);
                    this.r.setVisibility(8);
                } else {
                    this.m.clear();
                    for (i = 0; i < optJSONArray.length(); i++) {
                        this.m.add(u.a(optJSONArray.getJSONObject(i)));
                    }
                }
                optJSONArray = jSONObject.optJSONArray("inv_type_list");
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    this.k.setVisibility(8);
                    this.q.setVisibility(8);
                } else {
                    this.n.clear();
                    for (i = 0; i < optJSONArray.length(); i++) {
                        this.n.add(v.a(optJSONArray.getJSONObject(i)));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.a = (ImageView) findViewById(R.id.invoice_back);
        this.a.setOnClickListener(new ECJiaInvoiceActivity_1(this));
        this.b = (Button) findViewById(R.id.invoice_save);
        this.b.setOnClickListener(this);
        this.d = (EditText) findViewById(R.id.invoice_code);
        this.c = (EditText) findViewById(R.id.invoice_taitou);
        this.c.setText(this.w);
        this.d.setText(this.v);
        this.c.setFocusable(true);
        this.c.setFocusableInTouchMode(true);
        this.c.requestFocus();
        new Timer().schedule(new ECJiaInvoiceActivity_2(this), 300);
        this.o = new aa(this, this.m, this.t);
        this.e.setAdapter(this.o);
        a(this.e);
        this.e.setOnItemClickListener(new ECJiaInvoiceActivity_3(this));
        this.p = new ab(this, this.n, this.s);
        this.k.setAdapter(this.p);
        a(this.k);
        this.k.setOnItemClickListener(new ECJiaInvoiceActivity_4(this));
        this.l = (TextView) findViewById(R.id.invoice_clear);
        this.l.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.invoice_save:
                k kVar;
                if (this.D) {
                    if (!(TextUtils.isEmpty(this.c.getText().toString()) && TextUtils.isEmpty(this.s) && TextUtils.isEmpty(this.t) && TextUtils.isEmpty(this.d.getText().toString())) && ((TextUtils.isEmpty(this.c.getText().toString()) || TextUtils.isEmpty(this.s) || TextUtils.isEmpty(this.t)) && TextUtils.isEmpty(this.d.getText().toString()))) {
                        kVar = new k((Context) this, "请填写完整的发票");
                        kVar.a(17, 0, 0);
                        kVar.a();
                        return;
                    }
                    intent = new Intent();
                    intent.putExtra("inv_type", this.s);
                    intent.putExtra("inv_type_rate", this.u);
                    intent.putExtra("inv_content", this.t);
                    intent.putExtra("inv_payee", this.c.getText().toString());
                    intent.putExtra("inv_code", this.d.getText().toString());
                    setResult(-1, intent);
                    finish();
                    return;
                } else if (!(TextUtils.isEmpty(this.s) && TextUtils.isEmpty(this.t)) && TextUtils.isEmpty(this.s) && TextUtils.isEmpty(this.t)) {
                    kVar = new k((Context) this, "请填写完整的发票");
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                } else {
                    intent = new Intent();
                    intent.putExtra("inv_type", this.s);
                    intent.putExtra("inv_type_rate", this.u);
                    intent.putExtra("inv_content", this.t);
                    intent.putExtra("inv_payee", "个人");
                    intent.putExtra("inv_code", this.d.getText().toString());
                    setResult(-1, intent);
                    finish();
                    return;
                }
            case R.id.personal_txt:
                this.D = false;
                this.x.setBackgroundResource(R.drawable.bala_invoice_itme_two);
                this.y.setBackgroundResource(R.drawable.bala_invoice_itme);
                this.B.setVisibility(8);
                this.C.setVisibility(0);
                this.z.setVisibility(8);
                return;
            case R.id.company_txt:
                this.D = true;
                this.x.setBackgroundResource(R.drawable.bala_invoice_itme);
                this.y.setBackgroundResource(R.drawable.bala_invoice_itme_two);
                this.B.setVisibility(0);
                this.C.setVisibility(8);
                this.z.setVisibility(0);
                return;
            case R.id.invoice_img:
                final AlertDialog create = new Builder(this).create();
                View inflate = getLayoutInflater().inflate(R.layout.invoice_dialog_view_itme, null);
                LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.invoice_line_content);
                ((TextView) inflate.findViewById(R.id.dialog_delete)).setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaInvoiceActivity b;

                    public void onClick(View view) {
                        create.dismiss();
                    }
                });
                LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.width = d() - y.a(this, 30);
                layoutParams.height = (int) ((((double) layoutParams.width) * WeightedLatLng.DEFAULT_INTENSITY) / WeightedLatLng.DEFAULT_INTENSITY);
                linearLayout.setLayoutParams(layoutParams);
                create.setView(inflate);
                create.show();
                return;
            case R.id.invoice_clear:
                this.s = null;
                this.t = null;
                this.w = null;
                this.c.setText("");
                this.d.setText("");
                this.o.a = -1;
                this.p.a = -1;
                this.o.b = null;
                this.p.b = null;
                this.o.notifyDataSetChanged();
                this.p.notifyDataSetChanged();
                intent = new Intent();
                intent.putExtra("inv_type", this.s);
                intent.putExtra("inv_type_rate", this.u);
                intent.putExtra("inv_content", this.t);
                intent.putExtra("inv_payee", this.c.getText().toString());
                intent.putExtra("inv_code", this.d.getText().toString());
                setResult(-1, intent);
                finish();
                return;
            default:
                return;
        }
    }

    private void a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    public boolean a(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = new int[]{0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int height = view.getHeight() + i2;
        int width = view.getWidth() + i;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) width) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) height)) {
            return true;
        }
        return false;
    }

    public int d() {
        return Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
    }
}
