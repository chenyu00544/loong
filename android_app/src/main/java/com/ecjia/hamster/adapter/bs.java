package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.hamster.model.ECJia_PRODUCT_SPECIFICATION;
import com.ecjia.hamster.model.av;
import com.ecjia.hamster.model.aw;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ECJiaSpecificationAdapter */
public class bs extends BaseAdapter {
    public a a;
    private ArrayList<av> b;
    private Context c;
    private Resources d;
    private float[] e;
    private String[] f;
    private ArrayList<ECJia_PRODUCT_SPECIFICATION> g = new ArrayList();

    /* compiled from: ECJiaSpecificationAdapter */
    public interface a {
        void a(float f, float f2);
    }

    /* compiled from: ECJiaSpecificationAdapter */
    private class b {
        final /* synthetic */ bs a;
        private TextView b;
        private LinearLayout c;

        private b(bs bsVar) {
            this.a = bsVar;
        }
    }

    public bs(Context context, ArrayList<av> arrayList, ArrayList<ECJia_PRODUCT_SPECIFICATION> arrayList2, a aVar) {
        this.a = aVar;
        this.c = context;
        this.d = context.getResources();
        this.b = arrayList;
        this.g = arrayList2;
        this.e = new float[this.b.size()];
        this.f = new String[this.b.size()];
        int i = 0;
        while (i < this.b.size()) {
            int i2 = 0;
            while (i2 < ((av) this.b.get(i)).c.size()) {
                if (((aw) ((av) this.b.get(i)).c.get(i2)).b().compareTo(av.b) == 0) {
                    if (n.a().a(((aw) ((av) this.b.get(i)).c.get(i2)).c())) {
                        try {
                            this.e[i] = Float.valueOf(k.d(((aw) ((av) this.b.get(i)).c.get(i2)).d())).floatValue() + this.e[i];
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (((aw) ((av) this.b.get(i)).c.get(i2)).b().compareTo(av.a) == 0 && n.a().a(((aw) ((av) this.b.get(i)).c.get(i2)).c())) {
                    try {
                        this.e[i] = Float.valueOf(k.d(((aw) ((av) this.b.get(i)).c.get(i2)).d())).floatValue();
                        this.f[i] = ((aw) ((av) this.b.get(i)).c.get(i2)).c() + "";
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                i2++;
            }
            i++;
        }
        a();
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            bVar = new b();
            view = LinearLayout.inflate(this.c, R.layout.specification_cell, null);
            bVar.b = (TextView) view.findViewById(R.id.specification_name);
            bVar.c = (LinearLayout) view.findViewById(R.id.specification_value);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        if (((av) this.b.get(i)).b().compareTo("1") == 0) {
            bVar.b.setText(((av) this.b.get(i)).a() + this.d.getString(R.string.single_choice));
        } else {
            bVar.b.setText(((av) this.b.get(i)).a() + this.d.getString(R.string.double_choice));
        }
        a(i, bVar);
        return view;
    }

    void a(final int i, b bVar) {
        bVar.c.removeAllViews();
        List list = ((av) this.b.get(i)).c;
        if (((av) this.b.get(i)).c.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2 += 3) {
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.c).inflate(R.layout.specification_value_cell, null);
                final aw awVar = (aw) list.get(i2);
                final TextView textView = (TextView) linearLayout.findViewById(R.id.specification_value_text_one_discript);
                final TextView textView2 = (TextView) linearLayout.findViewById(R.id.specification_value_text_two_discript);
                final TextView textView3 = (TextView) linearLayout.findViewById(R.id.specification_value_text_three_discript);
                if (list.size() > i2) {
                    aw awVar2 = (aw) list.get(i2);
                    textView.setText(awVar2.e());
                    if (n.a().a(awVar2.c())) {
                        textView.setTextColor(-1);
                        textView.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                    }
                    if (list.size() > i2 + 1) {
                        awVar2 = (aw) list.get(i2 + 1);
                        textView2.setText(awVar2.e());
                        if (n.a().a(awVar2.c())) {
                            textView2.setTextColor(-1);
                            textView2.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                        }
                    } else {
                        textView2.setVisibility(4);
                    }
                    if (list.size() > i2 + 2) {
                        textView3.setVisibility(0);
                        awVar2 = (aw) list.get(i2 + 2);
                        textView3.setText(awVar2.e());
                        if (n.a().a(awVar2.c())) {
                            textView3.setTextColor(-1);
                            textView3.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                        }
                    } else {
                        textView3.setVisibility(4);
                    }
                }
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ bs d;

                    public void onClick(View view) {
                        if (awVar.b().compareTo(av.b) == 0) {
                            if (n.a().a(awVar.c())) {
                                n.a().b(awVar.c());
                                textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView.setBackgroundResource(R.drawable.shape_specification_text_bg_unselected);
                                try {
                                    this.d.e[i] = this.d.e[i] - Float.valueOf(k.d(awVar.d())).floatValue();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                textView.setTextColor(-1);
                                textView.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                                n.a().a(awVar);
                                try {
                                    this.d.e[i] = this.d.e[i] + Float.valueOf(k.d(awVar.d())).floatValue();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        if (awVar.b().compareTo(av.a) == 0 && !n.a().a(awVar.c())) {
                            textView.setTextColor(-1);
                            textView.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                            n.a().a(awVar);
                            try {
                                this.d.e[i] = Float.valueOf(k.d(awVar.d())).floatValue();
                                this.d.f[i] = awVar.c() + "";
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                        this.d.a();
                        this.d.notifyDataSetChanged();
                    }
                });
                if (i2 + 1 < list.size()) {
                    awVar = (aw) list.get(i2 + 1);
                    textView2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ bs d;

                        public void onClick(View view) {
                            if (awVar.b().compareTo(av.b) == 0) {
                                if (n.a().a(awVar.c())) {
                                    n.a().b(awVar.c());
                                    textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView2.setBackgroundResource(R.drawable.shape_specification_text_bg_unselected);
                                    try {
                                        this.d.e[i] = this.d.e[i] - Float.valueOf(k.d(awVar.d())).floatValue();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    textView2.setTextColor(-1);
                                    textView2.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                                    n.a().a(awVar);
                                    try {
                                        this.d.e[i] = this.d.e[i] + Float.valueOf(k.d(awVar.d())).floatValue();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (awVar.b().compareTo(av.a) == 0 && !n.a().a(awVar.c())) {
                                textView2.setTextColor(-1);
                                textView2.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                                n.a().a(awVar);
                                try {
                                    this.d.e[i] = Float.valueOf(k.d(awVar.d())).floatValue();
                                    this.d.f[i] = awVar.c() + "";
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                            this.d.a();
                            this.d.notifyDataSetChanged();
                        }
                    });
                } else {
                    textView2.setVisibility(4);
                }
                if (i2 + 2 < list.size()) {
                    awVar = (aw) list.get(i2 + 2);
                    textView3.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ bs d;

                        public void onClick(View view) {
                            if (awVar.b().compareTo(av.b) == 0) {
                                if (n.a().a(awVar.c())) {
                                    n.a().b(awVar.c());
                                    textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView3.setBackgroundResource(R.drawable.shape_specification_text_bg_unselected);
                                    try {
                                        this.d.e[i] = this.d.e[i] - Float.valueOf(k.d(awVar.d())).floatValue();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    textView3.setTextColor(-1);
                                    textView3.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                                    n.a().a(awVar);
                                    try {
                                        this.d.e[i] = this.d.e[i] + Float.valueOf(k.d(awVar.d())).floatValue();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (awVar.b().compareTo(av.a) == 0 && !n.a().a(awVar.c())) {
                                textView3.setTextColor(-1);
                                textView3.setBackgroundResource(R.drawable.shape_specification_text_bg_selected);
                                n.a().a(awVar);
                                try {
                                    this.d.e[i] = Float.valueOf(k.d(awVar.d())).floatValue();
                                    this.d.f[i] = awVar.c() + "";
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                            this.d.a();
                            this.d.notifyDataSetChanged();
                        }
                    });
                } else {
                    textView3.setVisibility(4);
                }
                bVar.c.addView(linearLayout);
            }
        }
    }

    private void a() {
        float a;
        float a2;
        int i = 0;
        int i2 = 0;
        String str = "";
        while (i2 < this.f.length) {
            String str2;
            if (i2 > 0) {
                str2 = str + "|" + this.f[i2];
            } else {
                str2 = str + this.f[i2];
            }
            i2++;
            str = str2;
        }
        while (i < this.g.size()) {
            if (((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getGoods_attr_ids().equals(str)) {
                if (TextUtils.isEmpty(((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getProduct_promote_price()) || "null".equals(((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getProduct_promote_price())) {
                    a = k.a(((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getProduct_price());
                    a2 = k.a(((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getProduct_market_price());
                } else {
                    a = k.a(((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getProduct_promote_price());
                    a2 = k.a(((ECJia_PRODUCT_SPECIFICATION) this.g.get(i)).getProduct_price());
                }
                if (this.a != null) {
                    this.a.a(a, a2);
                }
            }
            i++;
        }
        a2 = 0.0f;
        a = -1.0f;
        if (this.a != null) {
            this.a.a(a, a2);
        }
    }
}
