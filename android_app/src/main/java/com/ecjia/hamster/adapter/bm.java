package com.ecjia.hamster.adapter;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.ag;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ECJia_PRICE_RANGE;
import com.ecjia.hamster.model.f;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;

/* compiled from: ECJiaShopMenuAdapter */
public class bm extends BaseExpandableListAdapter {
    private Context a;
    private String[] b;
    private ag c;
    private String d;
    private ArrayList<ECJia_CATEGORY> e;

    /* compiled from: ECJiaShopMenuAdapter */
    class a {
        TextView a;
        View b;
        LinearLayout c;
        final /* synthetic */ bm d;

        a(bm bmVar) {
            this.d = bmVar;
        }
    }

    /* compiled from: ECJiaShopMenuAdapter */
    class b {
        TextView a;
        ImageView b;
        LinearLayout c;
        TextView d;
        final /* synthetic */ bm e;

        b(bm bmVar) {
            this.e = bmVar;
        }
    }

    public bm(Context context, String[] strArr, String str, ag agVar, ArrayList<ECJia_CATEGORY> arrayList) {
        this.a = context;
        this.b = strArr;
        this.c = agVar;
        this.d = str;
        this.e = arrayList;
    }

    public Object getChild(int i, int i2) {
        if (i == 0) {
            return this.c.a.get(i2);
        }
        if (i == 1) {
            return this.e.get(i2);
        }
        if (i == 2) {
            return this.c.b.get(i2);
        }
        return null;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        int i3 = SupportMenu.CATEGORY_MASK;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.item_shopmenu_adpter, null);
            aVar.a = (TextView) view.findViewById(R.id.goodlist_child_name);
            aVar.b = view.findViewById(R.id.child_buttomline);
            aVar.c = (LinearLayout) view.findViewById(R.id.goodlist_child_item);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.a.setText(((f) this.c.a.get(i2)).b());
            aVar.a.setTextColor(((Boolean) ((HashMap) com.ecjia.consts.b.d.get(i2)).get(AgooConstants.MESSAGE_FLAG)).booleanValue() ? SupportMenu.CATEGORY_MASK : -7829368);
            if (i2 == this.c.a.size() - 1) {
                aVar.b.setVisibility(0);
            } else {
                aVar.b.setVisibility(8);
            }
        } else if (i == 1) {
            aVar.a.setText(((ECJia_CATEGORY) this.e.get(i2)).getName());
            r4 = aVar.a;
            if (!((Boolean) ((HashMap) com.ecjia.consts.b.e.get(i2)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                i3 = -7829368;
            }
            r4.setTextColor(i3);
            if (i2 == this.e.size() - 1) {
                aVar.b.setVisibility(0);
            } else {
                aVar.b.setVisibility(8);
            }
        } else if (i == 2) {
            aVar.a.setText(((ECJia_PRICE_RANGE) this.c.b.get(i2)).getPrice_min() + SocializeConstants.OP_DIVIDER_MINUS + ((ECJia_PRICE_RANGE) this.c.b.get(i2)).getPrice_max());
            r4 = aVar.a;
            if (!((Boolean) ((HashMap) com.ecjia.consts.b.f.get(i2)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                i3 = -7829368;
            }
            r4.setTextColor(i3);
            if (i2 == this.c.b.size() - 1) {
                aVar.b.setVisibility(0);
            } else {
                aVar.b.setVisibility(8);
            }
        }
        return view;
    }

    public int getChildrenCount(int i) {
        if (i == 0) {
            return this.c.a.size();
        }
        if (i == 1) {
            return this.e.size();
        }
        if (i == 2) {
            return this.c.b.size();
        }
        return 0;
    }

    public Object getGroup(int i) {
        return this.b[i];
    }

    public int getGroupCount() {
        return this.b.length;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        int i2 = 0;
        if (view == null) {
            b bVar2 = new b(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.item_shopmenu_adpter_parent, null);
            bVar2.a = (TextView) view.findViewById(R.id.goodlist_parent_name);
            bVar2.b = (ImageView) view.findViewById(R.id.goodlist_parent_rightarrow);
            bVar2.c = (LinearLayout) view.findViewById(R.id.goodlist_p_item);
            bVar2.d = (TextView) view.findViewById(R.id.goodlist_parent_selected_name);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        int i3;
        if (i == 0) {
            if (this.c.a.size() > 0) {
                int i4 = 0;
                for (i3 = 0; i3 < com.ecjia.consts.b.d.size(); i3++) {
                    if (((Boolean) ((HashMap) com.ecjia.consts.b.d.get(i3)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                        bVar.d.setText(((f) this.c.a.get(i3)).b());
                        i4 = 1;
                    }
                }
                if (i4 == 0) {
                    bVar.d.setText("");
                }
                bVar.c.setVisibility(0);
            } else {
                bVar.c.setVisibility(8);
            }
        } else if (i == 1) {
            if (this.e.size() > 0) {
                bVar.c.setVisibility(0);
                i3 = 0;
                while (i2 < com.ecjia.consts.b.e.size()) {
                    if (((Boolean) ((HashMap) com.ecjia.consts.b.e.get(i2)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                        bVar.d.setText(((ECJia_CATEGORY) this.e.get(i2)).getName());
                        r0 = 1;
                    } else {
                        r0 = i3;
                    }
                    i2++;
                    i3 = r0;
                }
                if (i3 == 0) {
                    bVar.d.setText("");
                }
            } else {
                bVar.c.setVisibility(8);
            }
        } else if (i == 2) {
            if (this.c.b.size() > 0) {
                bVar.c.setVisibility(0);
                i3 = 0;
                while (i2 < com.ecjia.consts.b.f.size()) {
                    if (((Boolean) ((HashMap) com.ecjia.consts.b.f.get(i2)).get(AgooConstants.MESSAGE_FLAG)).booleanValue()) {
                        bVar.d.setText(((ECJia_PRICE_RANGE) this.c.b.get(i2)).getPrice_min() + SocializeConstants.OP_DIVIDER_MINUS + ((ECJia_PRICE_RANGE) this.c.b.get(i2)).getPrice_max());
                        r0 = 1;
                    } else {
                        r0 = i3;
                    }
                    i2++;
                    i3 = r0;
                }
                if (i3 == 0) {
                    bVar.d.setText("");
                }
            } else {
                bVar.c.setVisibility(8);
            }
        }
        bVar.a.setText(this.b[i]);
        if (z) {
            bVar.b.setImageResource(R.drawable.search_hidden);
        } else {
            bVar.b.setImageResource(R.drawable.search_showchild);
        }
        return view;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
