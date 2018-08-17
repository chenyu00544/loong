package com.ecjia.hamster.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_ATTR_LIST;
import com.ecjia.hamster.model.ECJia_FILTER_BRAND;
import com.ecjia.hamster.model.ECJia_FILTER_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER_PRICE;
import com.ecjia.hamster.model.ECJia_SelectedInterface;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaExpertGridAdapter */
public class i extends BaseAdapter {
    int a;
    a b;
    private List<? extends ECJia_SelectedInterface> c;
    private Context d;
    private String e = "ECJiaExpertGridAdapter";

    /* compiled from: ECJiaExpertGridAdapter */
    public interface a {
        void a(int i, int i2, int i3);
    }

    /* compiled from: ECJiaExpertGridAdapter */
    private class b {
        TextView a;
        final /* synthetic */ i b;

        private b(i iVar) {
            this.b = iVar;
        }
    }

    public i(Context context, int i, List<? extends ECJia_SelectedInterface> list) {
        this.a = i;
        this.c = list;
        this.d = context;
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    public Object getItem(int i) {
        if (this.c == null) {
            return null;
        }
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (this.c == null) {
            return null;
        }
        b bVar;
        if (view == null) {
            bVar = new b();
            view = LayoutInflater.from(this.d).inflate(R.layout.layout_item_category, null);
            bVar.a = (TextView) view.findViewById(R.id.category_child_category_name);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        ECJia_SelectedInterface eCJia_SelectedInterface = (ECJia_SelectedInterface) this.c.get(i);
        if (eCJia_SelectedInterface instanceof ECJia_FILTER_CATEGORY) {
            if (eCJia_SelectedInterface.isSelected()) {
                bVar.a.setTextColor(this.d.getResources().getColor(R.color.public_theme_color_normal));
                if (this.b != null) {
                    this.b.a(0, this.a, i);
                }
            } else {
                bVar.a.setTextColor(Color.parseColor("#999999"));
            }
            bVar.a.setText(((ECJia_FILTER_CATEGORY) eCJia_SelectedInterface).getCat_name());
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i c;

                public void onClick(View view) {
                    if (this.c.b != null) {
                        this.c.b.a(0, this.c.a, i);
                    }
                    bVar.a.setTextColor(this.c.d.getResources().getColor(R.color.public_theme_color_normal));
                    this.c.notifyDataSetChanged();
                }
            });
            return view;
        } else if (eCJia_SelectedInterface instanceof ECJia_FILTER_BRAND) {
            if (eCJia_SelectedInterface.isSelected()) {
                bVar.a.setTextColor(this.d.getResources().getColor(R.color.public_theme_color_normal));
                if (this.b != null) {
                    this.b.a(1, this.a, i);
                }
            } else {
                bVar.a.setTextColor(Color.parseColor("#999999"));
            }
            bVar.a.setText(((ECJia_FILTER_BRAND) eCJia_SelectedInterface).getBrand_name());
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i c;

                public void onClick(View view) {
                    if (this.c.b != null) {
                        this.c.b.a(1, this.c.a, i);
                    }
                    bVar.a.setTextColor(this.c.d.getResources().getColor(R.color.public_theme_color_normal));
                    this.c.notifyDataSetChanged();
                }
            });
            return view;
        } else if (eCJia_SelectedInterface instanceof ECJia_FILTER_PRICE) {
            if (eCJia_SelectedInterface.isSelected()) {
                bVar.a.setTextColor(this.d.getResources().getColor(R.color.public_theme_color_normal));
                if (this.b != null) {
                    this.b.a(2, this.a, i);
                }
            } else {
                bVar.a.setTextColor(Color.parseColor("#999999"));
            }
            bVar.a.setText(((ECJia_FILTER_PRICE) eCJia_SelectedInterface).getPrice_range());
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i c;

                public void onClick(View view) {
                    if (this.c.b != null) {
                        this.c.b.a(2, this.c.a, i);
                    }
                    bVar.a.setTextColor(this.c.d.getResources().getColor(R.color.public_theme_color_normal));
                    this.c.notifyDataSetChanged();
                }
            });
            return view;
        } else if (!(eCJia_SelectedInterface instanceof ECJia_ATTR_LIST)) {
            return view;
        } else {
            if (eCJia_SelectedInterface.isSelected()) {
                bVar.a.setTextColor(this.d.getResources().getColor(R.color.public_theme_color_normal));
                if (this.b != null) {
                    this.b.a(3, this.a, i);
                }
            } else {
                bVar.a.setTextColor(Color.parseColor("#999999"));
            }
            bVar.a.setText(((ECJia_ATTR_LIST) eCJia_SelectedInterface).getAttr_value());
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i c;

                public void onClick(View view) {
                    if (this.c.b != null) {
                        this.c.b.a(3, this.c.a, i);
                    }
                    bVar.a.setTextColor(this.c.d.getResources().getColor(R.color.public_theme_color_normal));
                    this.c.notifyDataSetChanged();
                }
            });
            return view;
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
