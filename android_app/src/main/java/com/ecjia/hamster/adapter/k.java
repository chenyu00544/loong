package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.ab;
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaAutoReturnView;
import com.ecjia.component.view.ECJiaCYTextView;
import com.ecjia.hamster.model.t;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaFindHotPointAdapter */
public class k extends BaseAdapter {
    int a = 0;
    int b = 0;
    int c = 0;
    private Context d;
    private ArrayList<ArrayList<t>> e;
    private p f;

    /* compiled from: ECJiaFindHotPointAdapter */
    class a {
        public TextView a;
        public View b;
        final /* synthetic */ k c;
        private ViewGroup d;
        private FrameLayout e;
        private TextView f;
        private ImageView g;

        a(k kVar) {
            this.c = kVar;
        }
    }

    /* compiled from: ECJiaFindHotPointAdapter */
    class b {
        public View a;
        public ECJiaCYTextView b;
        public TextView c;
        public ECJiaCYTextView d;
        public TextView e;
        public ImageView f;
        public TextView g;
        public LinearLayout h;
        final /* synthetic */ k i;

        b(k kVar) {
            this.i = kVar;
        }
    }

    public k(Context context, ArrayList<ArrayList<t>> arrayList) {
        this.d = context;
        this.e = arrayList;
        this.f = p.a(context);
        this.a = a();
        this.b = (int) context.getResources().getDimension(R.dimen.ten_margin);
        this.c = (int) context.getResources().getDimension(R.dimen.fifth_margin);
    }

    public int getCount() {
        return this.e.size();
    }

    public int getViewTypeCount() {
        return 1000;
    }

    public Object getItem(int i) {
        return this.e.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        if (((ArrayList) this.e.get(i)).size() > 1) {
            return 2;
        }
        return 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        a aVar;
        final ArrayList arrayList = (ArrayList) this.e.get(i);
        int itemViewType = getItemViewType(i);
        if (view != null) {
            switch (itemViewType) {
                case 1:
                    bVar = (b) view.getTag();
                    aVar = null;
                    break;
                case 2:
                    bVar = null;
                    aVar = (a) view.getTag();
                    break;
                default:
                    bVar = null;
                    aVar = null;
                    break;
            }
        }
        a aVar2;
        ViewGroup viewGroup2;
        switch (itemViewType) {
            case 1:
                view = LayoutInflater.from(this.d).inflate(R.layout.find_todayhot_single_item, null);
                bVar = new b(this);
                bVar.a = view.findViewById(R.id.news_single_top_view);
                bVar.h = (LinearLayout) view.findViewById(R.id.single_news_all);
                bVar.c = (TextView) view.findViewById(R.id.tv_news_single_time);
                bVar.d = (ECJiaCYTextView) view.findViewById(R.id.single_news_title);
                bVar.e = (TextView) view.findViewById(R.id.single_news_date);
                bVar.f = (ImageView) view.findViewById(R.id.single_news_first_img);
                bVar.g = (TextView) view.findViewById(R.id.single_news_first_text);
                bVar.b = (ECJiaCYTextView) view.findViewById(R.id.single_news_content);
                LayoutParams layoutParams = new LayoutParams(-1, -1);
                layoutParams.width = this.a - this.c;
                layoutParams.height = ((this.a - this.c) * 10) / 23;
                layoutParams.setMargins(this.b, this.b, this.b, this.b);
                bVar.f.setScaleType(ScaleType.CENTER_CROP);
                layoutParams.gravity = 17;
                view.setTag(bVar);
                Object obj = bVar;
                aVar2 = null;
                break;
            case 2:
                view = LayoutInflater.from(this.d).inflate(R.layout.find_todayhot_item, null);
                aVar2 = new a(this);
                aVar2.d = (ViewGroup) view.findViewById(R.id.viewgroup_find_todayhot);
                aVar2.e = (FrameLayout) view.findViewById(R.id.first_item1);
                aVar2.a = (TextView) view.findViewById(R.id.tv_news_time);
                aVar2.g = (ImageView) view.findViewById(R.id.hot_news_first_img1);
                aVar2.f = (TextView) view.findViewById(R.id.hot_news_first_text1);
                aVar2.b = view.findViewById(R.id.news_top_view);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                layoutParams2.width = this.a - this.c;
                layoutParams2.height = ((this.a - this.c) * 10) / 23;
                layoutParams2.setMargins(this.b, this.b, this.b, this.b);
                aVar2.g.setScaleType(ScaleType.CENTER_CROP);
                layoutParams2.gravity = 17;
                aVar2.g.setLayoutParams(layoutParams2);
                view.setTag(aVar2);
                viewGroup2 = null;
                break;
            default:
                viewGroup2 = null;
                aVar2 = null;
                break;
        }
        aVar = aVar2;
        bVar = viewGroup2;
        switch (itemViewType) {
            case 1:
                if (i == 0) {
                    bVar.a.setVisibility(0);
                } else {
                    bVar.a.setVisibility(8);
                }
                if (i == this.e.size() - 1) {
                    bVar.c.setText(ab.h(((t) arrayList.get(0)).e()));
                } else {
                    bVar.c.setText(ab.h(((t) arrayList.get(0)).e()));
                }
                this.f.a(bVar.f, ((t) arrayList.get(0)).c());
                bVar.d.SetText(((t) arrayList.get(0)).a());
                bVar.g.setText(((t) arrayList.get(0)).b());
                bVar.b.SetText(((t) arrayList.get(0)).b());
                bVar.e.setText(ab.i(((t) arrayList.get(0)).e()));
                bVar.h.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.d, ((t) arrayList.get(0)).d());
                    }
                });
                break;
            case 2:
                if (i == 0) {
                    aVar.b.setVisibility(0);
                } else {
                    aVar.b.setVisibility(8);
                }
                if (i == this.e.size() - 1) {
                    aVar.a.setText(ab.h(((t) arrayList.get(0)).e()));
                } else {
                    aVar.a.setText(ab.h(((t) arrayList.get(0)).e()));
                }
                aVar.d.removeAllViews();
                this.f.a(aVar.g, ((t) arrayList.get(0)).c());
                aVar.f.setText(((t) arrayList.get(0)).a());
                aVar.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ k b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.d, ((t) arrayList.get(0)).d());
                    }
                });
                aVar.d.setVisibility(0);
                itemViewType = 1;
                while (itemViewType < arrayList.size()) {
                    View inflate = LayoutInflater.from(this.d).inflate(R.layout.find_todayhot_small_item, null);
                    View findViewById = inflate.findViewById(R.id.find_hot_small_topline);
                    View findViewById2 = inflate.findViewById(R.id.find_hot_small_buttomline);
                    if (arrayList.size() == 2) {
                        inflate.setBackgroundResource(R.drawable.selector_hot_news_buttom);
                        findViewById.setVisibility(0);
                        findViewById2.setVisibility(8);
                    } else if (itemViewType == 1) {
                        findViewById.setVisibility(0);
                        findViewById2.setVisibility(0);
                        inflate.setBackgroundResource(R.drawable.selector_hot_news_buttom);
                    } else if (itemViewType > 1 && itemViewType < arrayList.size() - 1) {
                        findViewById.setVisibility(8);
                        findViewById2.setVisibility(0);
                        inflate.setBackgroundResource(R.drawable.selector_hot_news);
                    } else if (itemViewType == arrayList.size() - 1) {
                        inflate.setBackgroundResource(R.drawable.selector_hot_news_buttom);
                        findViewById.setVisibility(8);
                        findViewById2.setVisibility(8);
                    }
                    ((ECJiaAutoReturnView) inflate.findViewById(R.id.find_small_hot_text)).setContent(((t) arrayList.get(itemViewType)).a());
                    this.f.a((ImageView) inflate.findViewById(R.id.find_hot_news_img), ((t) arrayList.get(itemViewType)).c());
                    inflate.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ k c;

                        public void onClick(View view) {
                            com.ecjia.a.b.a.a().a(this.c.d, ((t) arrayList.get(itemViewType)).d());
                        }
                    });
                    aVar.d.addView(inflate);
                    itemViewType++;
                }
                break;
        }
        return view;
    }

    public int a() {
        return Math.min(((Activity) this.d).getWindowManager().getDefaultDisplay().getWidth(), ((Activity) this.d).getWindowManager().getDefaultDisplay().getHeight());
    }
}
