package com.ecjia.hamster.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.c;
import com.ecjia.component.view.ECJiaSwipeListView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.a.b;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import org.android.agoo.common.AgooConstants;

public class ECJiaAddressManageActivity extends a implements a {
    ProgressDialog a = null;
    public Handler b;
    public int c;
    private ECJiaSwipeListView d;
    private View e;
    private com.ecjia.hamster.adapter.a k;
    private c l;
    private String m;

    class ECJiaAddressManageActivity_1 extends Handler {
        final /* synthetic */ ECJiaAddressManageActivity a;

        ECJiaAddressManageActivity_1(ECJiaAddressManageActivity eCJiaAddressManageActivity) {
            this.a = eCJiaAddressManageActivity;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.m = message.arg1 + "";
                this.a.l.c(this.a.m + "");
            }
        }
    }

    class ECJiaAddressManageActivity_2 implements b {
        final /* synthetic */ ECJiaAddressManageActivity a;

        ECJiaAddressManageActivity_2(ECJiaAddressManageActivity eCJiaAddressManageActivity) {
            this.a = eCJiaAddressManageActivity;
        }

        public void a(View view, final int i) {
            Resources resources = this.a.getBaseContext().getResources();
            String string = resources.getString(R.string.can_not_delete);
            String string2 = resources.getString(R.string.is_default_address);
            k kVar;
            if (view.getId() == R.id.address_delete) {
                if (((ECJia_ADDRESS) this.a.l.a.get(i)).getDefault_address() == 1) {
                    kVar = new k(this.a, string);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else {
                    final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c(this.a, resources.getString(R.string.point), resources.getString(R.string.address_delete_attention));
                    cVar.a(2);
                    cVar.b(new OnClickListener(this) {
                        final /* synthetic */ ECJiaAddressManageActivity_2 c;

                        public void onClick(View view) {
                            this.c.a.l.d("" + ((ECJia_ADDRESS) this.c.a.l.a.get(i)).getId());
                            this.c.a.l.a.remove(i);
                            this.c.a.d.deleteItem(this.c.a.d.getChildAt(i));
                            cVar.b();
                            this.c.a.k.notifyDataSetChanged();
                        }
                    });
                    cVar.c(new OnClickListener(this) {
                        final /* synthetic */ ECJiaAddressManageActivity_2 b;

                        public void onClick(View view) {
                            cVar.b();
                        }
                    });
                    cVar.a();
                }
            } else if (view.getId() == R.id.address_setdefault) {
                if (((ECJia_ADDRESS) this.a.l.a.get(i)).getDefault_address() == 1) {
                    kVar = new k(this.a, string2);
                    kVar.a(17, 0, 0);
                    kVar.a();
                } else {
                    this.a.m = "" + ((ECJia_ADDRESS) this.a.l.a.get(i)).getId();
                    this.a.l.c(this.a.m);
                    this.a.k.notifyDataSetChanged();
                }
            }
            this.a.k.notifyDataSetChanged();
        }
    }

    class ECJiaAddressManageActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaAddressManageActivity a;

        ECJiaAddressManageActivity_3(ECJiaAddressManageActivity eCJiaAddressManageActivity) {
            this.a = eCJiaAddressManageActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaAddressManageActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaAddressManageActivity a;

        ECJiaAddressManageActivity_4(ECJiaAddressManageActivity eCJiaAddressManageActivity) {
            this.a = eCJiaAddressManageActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaAddressAddActivity.class), 1);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.address_manage);
        c();
    }

    private void c() {
        a();
        this.c = getIntent().getIntExtra(AgooConstants.MESSAGE_FLAG, 0);
        this.d = (ECJiaSwipeListView) findViewById(R.id.address_manage_list);
        this.e = findViewById(R.id.address_list_bg);
        this.l = new c(this);
        this.l.a((a) this);
        this.b = new ECJiaAddressManageActivity_1(this);
    }

    public void b() {
        if (this.l.a.size() == 0) {
            this.d.setVisibility(8);
            k kVar = new k((Context) this, getBaseContext().getResources().getString(R.string.non_address));
            kVar.a(17, 0, 0);
            kVar.a();
            this.e.setVisibility(0);
            return;
        }
        this.e.setVisibility(8);
        this.d.setVisibility(0);
        if (this.k == null) {
            this.k = new com.ecjia.hamster.adapter.a(this, this.l.a, this.c, this.d.getRightViewWidth());
        }
        this.k.a(new ECJiaAddressManageActivity_2(this));
        this.d.setAdapter(this.k);
        this.k.a = this.b;
    }

    protected void onResume() {
        super.onResume();
        this.l.a();
    }

    protected void onRestart() {
        super.onRestart();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return true;
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.address_manage_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaAddressManageActivity_3(this));
        this.i.setTitleText((int) R.string.manage_address);
        this.i.setRightType(12);
        this.i.setRightImage((int) R.drawable.address_add, new ECJiaAddressManageActivity_4(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7, com.ecjia.hamster.model.ax r8) {
        /*
        r5 = this;
        r4 = 8;
        r2 = -1;
        r1 = 0;
        r3 = 1;
        r0 = r6.hashCode();
        switch(r0) {
            case 219725273: goto L_0x0011;
            case 469643302: goto L_0x001b;
            case 936006778: goto L_0x0025;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = r2;
    L_0x000d:
        switch(r0) {
            case 0: goto L_0x002f;
            case 1: goto L_0x0046;
            case 2: goto L_0x0077;
            default: goto L_0x0010;
        };
    L_0x0010:
        return;
    L_0x0011:
        r0 = "address/list";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0019:
        r0 = r1;
        goto L_0x000d;
    L_0x001b:
        r0 = "address/delete";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0023:
        r0 = r3;
        goto L_0x000d;
    L_0x0025:
        r0 = "address/setDefault";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x002d:
        r0 = 2;
        goto L_0x000d;
    L_0x002f:
        r0 = r8.b();
        if (r0 != r3) goto L_0x0039;
    L_0x0035:
        r5.b();
        goto L_0x0010;
    L_0x0039:
        r0 = new com.ecjia.component.view.k;
        r1 = r8.d();
        r0.<init>(r5, r1);
        r0.a();
        goto L_0x0010;
    L_0x0046:
        r0 = r8.b();
        if (r0 != r3) goto L_0x006a;
    L_0x004c:
        r0 = r5.k;
        r0 = r0.getCount();
        if (r0 <= 0) goto L_0x005f;
    L_0x0054:
        r0 = r5.d;
        r0.setVisibility(r1);
        r0 = r5.e;
        r0.setVisibility(r4);
        goto L_0x0010;
    L_0x005f:
        r0 = r5.d;
        r0.setVisibility(r4);
        r0 = r5.e;
        r0.setVisibility(r1);
        goto L_0x0010;
    L_0x006a:
        r0 = new com.ecjia.component.view.k;
        r1 = r8.d();
        r0.<init>(r5, r1);
        r0.a();
        goto L_0x0010;
    L_0x0077:
        r0 = r8.b();
        if (r0 != r3) goto L_0x009c;
    L_0x007d:
        r0 = r5.c;
        if (r0 != r3) goto L_0x0095;
    L_0x0081:
        r0 = new android.content.Intent;
        r0.<init>();
        r1 = "address_id";
        r3 = r5.m;
        r0.putExtra(r1, r3);
        r5.setResult(r2, r0);
        r5.finish();
        goto L_0x0010;
    L_0x0095:
        r0 = r5.l;
        r0.a();
        goto L_0x0010;
    L_0x009c:
        r0 = new com.ecjia.component.view.k;
        r1 = r8.d();
        r0.<init>(r5, r1);
        r0.a();
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.hamster.activity.ECJiaAddressManageActivity.a(java.lang.String, java.lang.String, com.ecjia.hamster.model.ax):void");
    }
}
