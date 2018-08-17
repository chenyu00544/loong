package com.ecjia.component.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.hamster.model.e;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;

/* compiled from: ECJiaRedPaperDetailDialog */
public class h {
    public Dialog a;
    public e b;
    private Context c;
    private int d;
    private LinearLayout e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private LinearLayout j;
    private Button k;
    private Button l;
    private LinearLayout m;
    private Button n;
    private LinearLayout o;
    private Button p;
    private a q = null;

    /* compiled from: ECJiaRedPaperDetailDialog */
    class h_3 implements OnClickListener {
        final /* synthetic */ h a;

        h_3(h hVar) {
            this.a = hVar;
        }

        public void onClick(View view) {
            this.a.b();
        }
    }

    /* compiled from: ECJiaRedPaperDetailDialog */
    public interface a {
        void a(View view, int i);
    }

    public h(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.redpaper_detail, null);
        this.e = (LinearLayout) inflate.findViewById(R.id.redpaper_item);
        this.c = context;
        this.d = (int) context.getResources().getDimension(R.dimen.sixty_dp);
        this.e.setLayoutParams(new LayoutParams(c() - this.d, -2));
        this.a = new Dialog(context, R.style.dialog);
        this.a.setContentView(inflate);
        this.a.setCanceledOnTouchOutside(false);
        this.a.getWindow().setWindowAnimations(R.style.redpaperWindowAnim);
        a(inflate);
    }

    public void a(final int i) {
        switch (i) {
            case 1:
                this.n.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        this.b.b();
                        this.b.q.a(view, i);
                    }
                });
                this.j.setVisibility(8);
                this.m.setVisibility(0);
                this.o.setVisibility(8);
                return;
            case 2:
                this.j.setVisibility(0);
                this.m.setVisibility(8);
                this.o.setVisibility(8);
                this.f.setText(this.b.a());
                this.g.setText(this.b.b());
                this.h.setText(this.b.d() + SocializeConstants.OP_DIVIDER_MINUS + this.b.e());
                this.i.setText("满" + this.b.c() + "才可使用此红包");
                this.k.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        if (this.b.q != null) {
                            this.b.q.a(view, i);
                        }
                    }
                });
                this.l.setOnClickListener(new h_3(this));
                return;
            case 3:
                this.j.setVisibility(8);
                this.m.setVisibility(8);
                this.o.setVisibility(0);
                this.p.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        this.b.q.a(view, i);
                        this.b.b();
                    }
                });
                return;
            default:
                return;
        }
    }

    private void a(View view) {
        this.j = (LinearLayout) view.findViewById(R.id.detail_item);
        this.f = (TextView) view.findViewById(R.id.redpaper_name);
        this.g = (TextView) view.findViewById(R.id.redpaper_amount);
        this.h = (TextView) view.findViewById(R.id.redpaper_time);
        this.i = (TextView) view.findViewById(R.id.redpaper_condition);
        this.k = (Button) view.findViewById(R.id.add_ok);
        this.l = (Button) view.findViewById(R.id.add_cancel);
        this.m = (LinearLayout) view.findViewById(R.id.repeat_item);
        this.n = (Button) view.findViewById(R.id.add_repeat_input);
        this.o = (LinearLayout) view.findViewById(R.id.add_succeed_item);
        this.p = (Button) view.findViewById(R.id.btn_add_succeed);
    }

    public void a() {
        this.a.show();
    }

    public void b() {
        this.a.dismiss();
    }

    public int c() {
        return Math.min(((Activity) this.c).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.c).getWindowManager().getDefaultDisplay().getHeight());
    }

    public void a(a aVar) {
        this.q = aVar;
    }
}
