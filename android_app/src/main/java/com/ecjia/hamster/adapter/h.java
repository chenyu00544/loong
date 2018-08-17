package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaSelectableRoundedImageView;
import com.ecjia.hamster.model.ECJia_ADSENSE_GROUP;
import com.ecjia.hamster.model.c;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaEventAdapter */
public class h extends BaseAdapter {
    protected p a;
    private Context b;
    private ArrayList<ECJia_ADSENSE_GROUP> c = new ArrayList();
    private c d;
    private c e;
    private c f;
    private c g;

    /* compiled from: ECJiaEventAdapter */
    private class a {
        ECJiaSelectableRoundedImageView a;
        ECJiaSelectableRoundedImageView b;
        ECJiaSelectableRoundedImageView c;
        ECJiaSelectableRoundedImageView d;
        View e;
        View f;
        LinearLayout g;
        LinearLayout h;
        LinearLayout i;
        LinearLayout j;
        LinearLayout k;
        LinearLayout l;
        RelativeLayout m;
        public TextView n;
        final /* synthetic */ h o;

        private a(h hVar) {
            this.o = hVar;
        }
    }

    public h(Context context, ArrayList<ECJia_ADSENSE_GROUP> arrayList) {
        this.b = context;
        this.a = p.a(context);
        this.c = arrayList;
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public int getCount() {
        return this.c.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.b).inflate(R.layout.event_item, null);
            aVar2.a = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.iv_event);
            aVar2.b = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.iv_event2);
            aVar2.c = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.iv_event3);
            aVar2.d = (ECJiaSelectableRoundedImageView) view.findViewById(R.id.iv_event4);
            aVar2.e = view.findViewById(R.id.event_title);
            aVar2.f = view.findViewById(R.id.first_item);
            aVar2.n = (TextView) view.findViewById(R.id.event_all_title);
            aVar2.h = (LinearLayout) view.findViewById(R.id.item_image_all);
            aVar2.g = (LinearLayout) view.findViewById(R.id.item_image);
            aVar2.i = (LinearLayout) view.findViewById(R.id.item_image2);
            aVar2.j = (LinearLayout) view.findViewById(R.id.ll_event2);
            aVar2.k = (LinearLayout) view.findViewById(R.id.ll_event3);
            aVar2.l = (LinearLayout) view.findViewById(R.id.ll_event4);
            aVar2.m = (RelativeLayout) view.findViewById(R.id.event_title_item);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        LayoutParams layoutParams = aVar.i.getLayoutParams();
        layoutParams.width = a();
        layoutParams.height = (int) ((((double) layoutParams.width) * WeightedLatLng.DEFAULT_INTENSITY) / 3.0d);
        aVar.i.setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = aVar.g.getLayoutParams();
        layoutParams2.width = a();
        layoutParams2.height = layoutParams.height;
        aVar.g.setLayoutParams(layoutParams2);
        a(aVar, i);
        return view;
    }

    private void a(a aVar, int i) {
        if (TextUtils.isEmpty(((ECJia_ADSENSE_GROUP) this.c.get(i)).getTitle())) {
            aVar.m.setVisibility(8);
            aVar.f.setVisibility(8);
        } else {
            aVar.m.setVisibility(0);
            aVar.f.setVisibility(0);
            aVar.n.setText(((ECJia_ADSENSE_GROUP) this.c.get(i)).getTitle());
        }
        int size = ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().size();
        if (size > 0) {
            final String a;
            aVar.h.setVisibility(0);
            if (size == 1 || size == 4) {
                aVar.g.setVisibility(0);
                aVar.f.setVisibility(0);
                this.d = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(0);
                this.a.a(aVar.a, this.d.b());
                a = this.d.a();
                aVar.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.b, a);
                    }
                });
            } else {
                aVar.g.setVisibility(8);
                aVar.f.setVisibility(8);
            }
            if (size > 1) {
                aVar.i.setVisibility(0);
                if (size <= 3) {
                    this.e = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(0);
                    a = this.e.a();
                    this.a.a(aVar.b, this.e.b());
                    aVar.b.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ h b;

                        public void onClick(View view) {
                            com.ecjia.a.b.a.a().a(this.b.b, a);
                        }
                    });
                    this.f = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(1);
                    a = this.f.a();
                    this.a.a(aVar.c, this.f.b());
                    aVar.c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ h b;

                        public void onClick(View view) {
                            com.ecjia.a.b.a.a().a(this.b.b, a);
                        }
                    });
                    if (size == 3) {
                        aVar.l.setVisibility(0);
                        this.g = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(2);
                        a = this.g.a();
                        this.a.a(aVar.d, this.g.b());
                        aVar.d.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ h b;

                            public void onClick(View view) {
                                com.ecjia.a.b.a.a().a(this.b.b, a);
                            }
                        });
                        return;
                    }
                    aVar.l.setVisibility(4);
                    return;
                }
                this.e = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(1);
                a = this.e.a();
                this.a.a(aVar.b, this.e.b());
                aVar.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.b, a);
                    }
                });
                this.f = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(2);
                a = this.f.a();
                this.a.a(aVar.c, this.f.b());
                aVar.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.b, a);
                    }
                });
                aVar.l.setVisibility(0);
                this.g = (c) ((ECJia_ADSENSE_GROUP) this.c.get(i)).getAdsense().get(3);
                a = this.g.a();
                this.a.a(aVar.d, this.g.b());
                aVar.d.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ h b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.b, a);
                    }
                });
                return;
            }
            aVar.i.setVisibility(8);
            return;
        }
        aVar.h.setVisibility(8);
    }

    public int a() {
        return Math.min(((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.b).getWindowManager().getDefaultDisplay().getHeight());
    }
}
