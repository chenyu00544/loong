package com.ecjia.hamster.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaMyHorizontalScrollView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.ECJiaWebViewActivity;
import com.ecjia.hamster.model.ba;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaSweepRecordAdapter */
public class bv extends BaseAdapter {
    public ArrayList<ba> a;
    private Context b;
    private int c = 0;
    private boolean d = false;
    private String e;
    private String f;
    private String g;
    private b h = null;

    /* compiled from: ECJiaSweepRecordAdapter */
    public interface b {
        void a();
    }

    /* compiled from: ECJiaSweepRecordAdapter */
    class bv_1 implements com.ecjia.component.view.ECJiaMyHorizontalScrollView.a {
        final /* synthetic */ bv a;

        bv_1(bv bvVar) {
            this.a = bvVar;
        }

        public void a() {
            this.a.notifyDataSetChanged();
        }

        public void a(boolean z) {
            this.a.d = z;
        }

        public boolean b() {
            return this.a.d;
        }
    }

    /* compiled from: ECJiaSweepRecordAdapter */
    class a {
        final /* synthetic */ bv a;
        private LinearLayout b;
        private TextView c;
        private TextView d;
        private LinearLayout e;
        private LinearLayout f;
        private ECJiaMyHorizontalScrollView g;
        private View h;
        private View i;
        private View j;
        private View k;

        a(bv bvVar) {
            this.a = bvVar;
        }
    }

    public bv(Context context, ArrayList<ba> arrayList, int i) {
        this.b = context;
        this.a = arrayList;
        this.c = i;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        final ba baVar = (ba) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.sweeprecord_item, viewGroup, false);
            aVar = new a(this);
            aVar.b = (LinearLayout) view.findViewById(R.id.item_sweep_left);
            aVar.h = (LinearLayout) view.findViewById(R.id.ll_action);
            aVar.c = (TextView) view.findViewById(R.id.tv_record_time);
            aVar.d = (TextView) view.findViewById(R.id.sweep_content);
            aVar.g = (ECJiaMyHorizontalScrollView) view.findViewById(R.id.hsv_view);
            aVar.e = (LinearLayout) view.findViewById(R.id.sweep_copy);
            aVar.f = (LinearLayout) view.findViewById(R.id.sweep_delete);
            aVar.i = view.findViewById(R.id.top_line);
            aVar.k = view.findViewById(R.id.buttom_long_line);
            aVar.j = view.findViewById(R.id.top_short_line);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.width = a();
            layoutParams.height = this.c / 2;
            aVar.b.setLayoutParams(layoutParams);
            layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.width = this.c;
            layoutParams.height = this.c / 2;
            aVar.h.setLayoutParams(layoutParams);
            aVar.g.setAction(aVar.h);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        this.f = baVar.c();
        if (i > 0) {
            this.e = ((ba) this.a.get(i - 1)).c();
        }
        if (i < this.a.size() - 1) {
            this.g = ((ba) this.a.get(i + 1)).c();
        }
        if (this.a.size() == 1) {
            aVar.c.setVisibility(0);
            aVar.i.setVisibility(0);
            aVar.j.setVisibility(8);
            aVar.k.setVisibility(0);
        } else if (i == 0) {
            aVar.c.setVisibility(0);
            aVar.i.setVisibility(0);
            aVar.j.setVisibility(8);
            if (this.f.equals(this.g)) {
                aVar.k.setVisibility(8);
            } else {
                aVar.k.setVisibility(0);
            }
        } else if (i == this.a.size() - 1) {
            aVar.k.setVisibility(0);
            if (this.f.equals(this.e)) {
                aVar.j.setVisibility(0);
                aVar.i.setVisibility(8);
                aVar.c.setVisibility(8);
            } else {
                aVar.j.setVisibility(8);
                aVar.i.setVisibility(0);
                aVar.c.setVisibility(0);
            }
        } else {
            if (this.f.equals(this.e)) {
                aVar.j.setVisibility(0);
                aVar.i.setVisibility(8);
                aVar.c.setVisibility(8);
            } else {
                aVar.j.setVisibility(8);
                aVar.i.setVisibility(0);
                aVar.c.setVisibility(0);
            }
            if (this.f.equals(this.g)) {
                aVar.k.setVisibility(8);
            } else {
                aVar.k.setVisibility(0);
            }
        }
        aVar.c.setText(baVar.c());
        aVar.d.setText(baVar.b());
        aVar.g.onStatusChangeListener(new bv_1(this));
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bv b;

            public void onClick(View view) {
                if (baVar.b().contains("http://") || baVar.b().contains("https://")) {
                    Intent intent = new Intent(this.b.b, ECJiaWebViewActivity.class);
                    intent.putExtra("url", baVar.b());
                    this.b.b.startActivity(intent);
                    return;
                }
                new k(this.b.b, this.b.b.getResources().getString(R.string.no_todo)).a();
            }
        });
        aVar.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bv c;

            public void onClick(View view) {
                final c cVar = new c(this.c.b, this.c.b.getResources().getString(R.string.point), this.c.b.getResources().getString(R.string.scan_history_delete_attention));
                cVar.a();
                cVar.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ bv_3 b;

                    public void onClick(View view) {
                        this.b.c.a.remove(i);
                        this.b.c.d = false;
                        bw.a(this.b.c.b).a(baVar.b());
                        if (this.b.c.h != null) {
                            this.b.c.h.a();
                        }
                        this.b.c.notifyDataSetChanged();
                        cVar.b();
                    }
                });
                cVar.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ bv_3 b;

                    public void onClick(View view) {
                        cVar.b();
                    }
                });
            }
        });
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bv b;

            public void onClick(View view) {
                this.b.a(baVar.b());
            }
        });
        if (aVar.g.getScrollX() != 0) {
            aVar.g.smoothScrollTo(0, 0);
            this.d = false;
        }
        return view;
    }

    @TargetApi(11)
    private void a(String str) {
        Context context;
        Context context2;
        if (VERSION.SDK_INT > 11) {
            context = this.b;
            context2 = this.b;
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", str));
        } else {
            context = this.b;
            context2 = this.b;
            ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(str);
        }
        new k(this.b, this.b.getResources().getString(R.string.copy_succeed)).a();
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    public int a() {
        return Math.min(((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.b).getWindowManager().getDefaultDisplay().getHeight());
    }
}
