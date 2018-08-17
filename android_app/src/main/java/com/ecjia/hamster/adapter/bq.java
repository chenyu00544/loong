package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.p;
import com.ecjia.component.view.c;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecjia.hamster.model.ECJia_NEWGOODITEM;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ECJiaShoppingCartAdapter */
public class bq extends BaseAdapter {
    protected p a;
    public int b = 1;
    public int c = 2;
    public int d = 3;
    public int e = 4;
    public int f = 1;
    a g;
    private LayoutInflater h;
    private Context i;
    private ArrayList<ECJia_NEWGOODITEM> j;
    private Resources k;
    private int[] l;

    /* compiled from: ECJiaShoppingCartAdapter */
    public interface a {
        void a(int i, boolean z, String str);

        void a(int i, boolean z, ArrayList<String> arrayList);

        void a(ECJia_GOODS_LIST eCJia_GOODS_LIST, int i, int i2, int i3, int i4);

        void a(String str);

        void b(int i, boolean z, ArrayList<String> arrayList);

        void c();
    }

    /* compiled from: ECJiaShoppingCartAdapter */
    private class b {
        final /* synthetic */ bq a;
        private LinearLayout b;
        private CheckBox c;
        private TextView d;
        private ImageView e;
        private LinearLayout f;
        private LinearLayout g;

        private b(bq bqVar) {
            this.a = bqVar;
        }
    }

    public bq(Context context, ArrayList<ECJia_NEWGOODITEM> arrayList, int i) {
        this.i = context;
        this.j = arrayList;
        this.f = i;
        this.k = context.getResources();
        this.h = LayoutInflater.from(context);
        this.a = p.a(context);
        this.l = new int[]{R.string.shopcar_add, R.string.to_combine_order};
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a(ArrayList<ECJia_NEWGOODITEM> arrayList) {
        this.j = arrayList;
    }

    public ArrayList<ECJia_NEWGOODITEM> a() {
        return this.j;
    }

    public void b() {
        int count = getCount();
        for (int i = 0; i < count; i++) {
            int size = ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().size();
            int i2;
            if (this.f == 1) {
                ((ECJia_NEWGOODITEM) this.j.get(i)).setIsCheckedbuy(Boolean.valueOf(true));
                for (i2 = 0; i2 < size; i2++) {
                    ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().get(i2)).setIsCheckedbuy(Boolean.valueOf(true));
                }
            } else if (this.f == 0) {
                ((ECJia_NEWGOODITEM) this.j.get(i)).setIscheckDelete(Boolean.valueOf(true));
                for (i2 = 0; i2 < size; i2++) {
                    ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().get(i2)).setIscheckDelete(Boolean.valueOf(true));
                }
            }
        }
        notifyDataSetChanged();
        if (this.g != null) {
            this.g.b(this.f, true, d());
        }
    }

    public void c() {
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            int size2 = ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().size();
            int i2;
            if (this.f == 1) {
                ((ECJia_NEWGOODITEM) this.j.get(i)).setIsCheckedbuy(Boolean.valueOf(false));
                for (i2 = 0; i2 < size2; i2++) {
                    ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().get(i2)).setIsCheckedbuy(Boolean.valueOf(false));
                }
            } else if (this.f == 0) {
                ((ECJia_NEWGOODITEM) this.j.get(i)).setIscheckDelete(Boolean.valueOf(false));
                for (i2 = 0; i2 < size2; i2++) {
                    ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().get(i2)).setIscheckDelete(Boolean.valueOf(false));
                }
            }
        }
        notifyDataSetChanged();
        if (this.g != null) {
            this.g.b(this.f, false, d());
        }
    }

    private ArrayList<String> d() {
        ArrayList<String> arrayList = new ArrayList();
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ECJia_NEWGOODITEM) it.next()).getGoodslist().iterator();
            while (it2.hasNext()) {
                arrayList.add(((ECJia_GOODS_LIST) it2.next()).getRec_id());
            }
        }
        return arrayList;
    }

    public int getCount() {
        if (this.j != null) {
            return this.j.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        return this.j.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        b bVar;
        int i2 = 0;
        int size = ((ECJia_NEWGOODITEM) this.j.get(i)).children.size();
        ECJia_NEWGOODITEM eCJia_NEWGOODITEM = (ECJia_NEWGOODITEM) this.j.get(i);
        if (view == null) {
            bVar = new b();
            view = this.h.inflate(R.layout.new_shoppingcart_item, null);
            bVar.b = (LinearLayout) view.findViewById(R.id.choose_shop_item);
            bVar.c = (CheckBox) view.findViewById(R.id.checked_shop_item);
            bVar.d = (TextView) view.findViewById(R.id.shop_name);
            bVar.e = (ImageView) view.findViewById(R.id.iv_shop_enter);
            bVar.g = (LinearLayout) view.findViewById(R.id.shop_name_ll);
            bVar.f = (LinearLayout) view.findViewById(R.id.goods_viewgroup);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        bVar.d.setText(eCJia_NEWGOODITEM.getName());
        final CharSequence id = eCJia_NEWGOODITEM.getId();
        bVar.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq b;

            public void onClick(View view) {
                if (!TextUtils.isEmpty(id) && !"0".equals(id)) {
                    Intent intent = new Intent(this.b.i, ECJiaShopListActivity.class);
                    intent.putExtra("merchant_id", id);
                    this.b.i.startActivity(intent);
                    ((Activity) this.b.i).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            }
        });
        if (TextUtils.isEmpty(id) || "0".equals(id)) {
            bVar.g.setClickable(false);
            bVar.e.setVisibility(4);
        } else {
            bVar.g.setClickable(true);
            bVar.e.setVisibility(0);
        }
        bVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq c;

            public void onClick(View view) {
                int i = false;
                int size = ((ECJia_NEWGOODITEM) this.c.j.get(i)).getGoodslist().size();
                int i2;
                switch (this.c.f) {
                    case 0:
                        if (!((ECJia_NEWGOODITEM) this.c.j.get(i)).getIscheckDelete().booleanValue()) {
                            ((ECJia_NEWGOODITEM) this.c.j.get(i)).setIscheckDelete(Boolean.valueOf(true));
                            bVar.c.setChecked(true);
                            for (i2 = 0; i2 < size; i2++) {
                                ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.c.j.get(i)).getGoodslist().get(i2)).setIscheckDelete(Boolean.valueOf(true));
                            }
                            this.c.notifyDataSetChanged();
                            if (this.c.g != null) {
                                this.c.g.a(0, true, this.c.a(i));
                                break;
                            }
                        }
                        ((ECJia_NEWGOODITEM) this.c.j.get(i)).setIscheckDelete(Boolean.valueOf(false));
                        bVar.c.setChecked(false);
                        for (i2 = 0; i2 < size; i2++) {
                            ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.c.j.get(i)).getGoodslist().get(i2)).setIscheckDelete(Boolean.valueOf(false));
                        }
                        this.c.notifyDataSetChanged();
                        if (this.c.g != null) {
                            this.c.g.a(0, false, this.c.a(i));
                            break;
                        }
                        break;
                    case 1:
                        if (!((ECJia_NEWGOODITEM) this.c.j.get(i)).getIsCheckedbuy().booleanValue()) {
                            ((ECJia_NEWGOODITEM) this.c.j.get(i)).setIsCheckedbuy(Boolean.valueOf(true));
                            while (i < size) {
                                ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.c.j.get(i)).getGoodslist().get(i)).setIs_checked(1);
                                i++;
                            }
                            this.c.notifyDataSetChanged();
                            if (this.c.g != null) {
                                this.c.g.a(1, true, this.c.a(i));
                                break;
                            }
                        }
                        ((ECJia_NEWGOODITEM) this.c.j.get(i)).setIsCheckedbuy(Boolean.valueOf(false));
                        for (i2 = 0; i2 < size; i2++) {
                            ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.c.j.get(i)).getGoodslist().get(i2)).setIs_checked(0);
                        }
                        this.c.notifyDataSetChanged();
                        if (this.c.g != null) {
                            this.c.g.a(1, false, this.c.a(i));
                            break;
                        }
                        break;
                }
                this.c.notifyDataSetChanged();
            }
        });
        if (this.f == 1) {
            bVar.c.setChecked(((ECJia_NEWGOODITEM) this.j.get(i)).getIsCheckedbuy().booleanValue());
        } else if (this.f == 0) {
            bVar.c.setChecked(((ECJia_NEWGOODITEM) this.j.get(i)).getIscheckDelete().booleanValue());
        } else {
            bVar.c.setChecked(false);
        }
        if (size > 0) {
            bVar.f.removeAllViews();
            while (i2 < size) {
                bVar.f.addView(a(this.i, (com.ecjia.hamster.model.ECJia_NEWGOODITEM.a) eCJia_NEWGOODITEM.children.get(i2), i, i2));
                i2++;
            }
        }
        return view;
    }

    public View a(Context context, com.ecjia.hamster.model.ECJia_NEWGOODITEM.a aVar, final int i, int i2) {
        int i3 = 0;
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_shop_cart_favour, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.favour_title);
        TextView textView = (TextView) inflate.findViewById(R.id.favour_type);
        TextView textView2 = (TextView) inflate.findViewById(R.id.favour_type_lable);
        TextView textView3 = (TextView) inflate.findViewById(R.id.favour_to_look_around_text);
        if (aVar.a() == null) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
            textView.setText(aVar.a().d());
            textView2.setText(aVar.a().a());
            inflate.findViewById(R.id.favour_to_look_around).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ bq b;

                public void onClick(View view) {
                    if (this.b.g != null) {
                        this.b.g.a(((ECJia_NEWGOODITEM) this.b.j.get(i)).getId() + "");
                    }
                }
            });
            if (a(i, i2)) {
                textView3.setText(this.l[0]);
            } else {
                textView3.setText(this.l[1]);
            }
        }
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.item_favour_goods);
        while (i3 < aVar.b().size()) {
            linearLayout2.addView(a(context, (ECJia_GOODS_LIST) aVar.b().get(i3), i, i2, i3));
            i3++;
        }
        return inflate;
    }

    public boolean a(int i, int i2) {
        ArrayList b = ((com.ecjia.hamster.model.ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.get(i)).children.get(i2)).b();
        float parseFloat = Float.parseFloat(((com.ecjia.hamster.model.ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.get(i)).children.get(i2)).a().b());
        float parseFloat2 = Float.parseFloat(((com.ecjia.hamster.model.ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.get(i)).children.get(i2)).a().c());
        float f = 0.0f;
        for (int i3 = 0; i3 < b.size(); i3++) {
            if (((ECJia_GOODS_LIST) b.get(i3)).getIs_checked() == 1) {
                f += ((float) ((ECJia_GOODS_LIST) b.get(i3)).getGoods_number()) * Float.parseFloat(((ECJia_GOODS_LIST) b.get(i3)).getGoods_price());
            }
        }
        if (parseFloat <= 0.0f || parseFloat2 != 0.0f) {
            if (f < parseFloat || f > parseFloat2) {
                return false;
            }
            return true;
        } else if (f >= parseFloat) {
            return true;
        } else {
            return false;
        }
    }

    public View a(Context context, ECJia_GOODS_LIST eCJia_GOODS_LIST, int i, int i2, int i3) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cart_goods, null);
        TextView textView = (TextView) inflate.findViewById(R.id.shop_car_item_total);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.shop_car_item_image);
        TextView textView2 = (TextView) inflate.findViewById(R.id.shop_car_item_text);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.shop_car_item_check);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.shop_car_check_item);
        View findViewById = inflate.findViewById(R.id.bottom_line);
        final TextView textView3 = (TextView) inflate.findViewById(R.id.shop_car_item_editNum);
        if (((com.ecjia.hamster.model.ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.get(i)).children.get(i2)).b().size() == 1 || i3 == ((com.ecjia.hamster.model.ECJia_NEWGOODITEM.a) ((ECJia_NEWGOODITEM) this.j.get(i)).children.get(i2)).b().size() - 1) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
        }
        if (this.f == 1) {
            checkBox.setChecked(eCJia_GOODS_LIST.getIsCheckedbuy().booleanValue());
        } else if (this.f == 0) {
            checkBox.setChecked(eCJia_GOODS_LIST.getIscheckDelete().booleanValue());
        }
        final Context context2 = context;
        final ECJia_GOODS_LIST eCJia_GOODS_LIST2 = eCJia_GOODS_LIST;
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq c;

            public void onClick(View view) {
                Intent intent = new Intent(context2, ECJiaGoodsDetailActivity.class);
                intent.putExtra("goods_id", eCJia_GOODS_LIST2.getGoods_id() + "");
                context2.startActivity(intent);
            }
        });
        final int size = ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().size();
        final ECJia_GOODS_LIST eCJia_GOODS_LIST3 = eCJia_GOODS_LIST;
        final int i4 = i;
        linearLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq d;

            public void onClick(View view) {
                int i = false;
                switch (this.d.f) {
                    case 0:
                        if (!eCJia_GOODS_LIST3.getIscheckDelete().booleanValue()) {
                            eCJia_GOODS_LIST3.setIscheckDelete(Boolean.valueOf(true));
                            if (size == 1) {
                                ((ECJia_NEWGOODITEM) this.d.j.get(i4)).setIscheckDelete(Boolean.valueOf(true));
                            }
                            int i2 = 0;
                            while (i2 < size && ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.d.j.get(i4)).getGoodslist().get(i2)).getIscheckDelete().booleanValue()) {
                                if (i2 == size - 1) {
                                    ((ECJia_NEWGOODITEM) this.d.j.get(i4)).setIscheckDelete(Boolean.valueOf(true));
                                }
                                i2++;
                            }
                            if (this.d.e() && this.d.g != null) {
                                this.d.g.a(0, true, eCJia_GOODS_LIST3.getRec_id());
                                break;
                            }
                        }
                        eCJia_GOODS_LIST3.setIscheckDelete(Boolean.valueOf(false));
                        ((ECJia_NEWGOODITEM) this.d.j.get(i4)).setIscheckDelete(Boolean.valueOf(false));
                        if (this.d.g != null) {
                            this.d.g.a(0, false, eCJia_GOODS_LIST3.getRec_id());
                            break;
                        }
                        break;
                    case 1:
                        if (eCJia_GOODS_LIST3.getIsCheckedbuy().booleanValue()) {
                            eCJia_GOODS_LIST3.setIsCheckedbuy(Boolean.valueOf(false));
                            ((ECJia_NEWGOODITEM) this.d.j.get(i4)).setIsCheckedbuy(Boolean.valueOf(false));
                            if (this.d.g != null) {
                                this.d.g.a(1, false, eCJia_GOODS_LIST3.getRec_id());
                            }
                        } else {
                            eCJia_GOODS_LIST3.setIsCheckedbuy(Boolean.valueOf(true));
                            if (this.d.g != null) {
                                this.d.g.a(1, true, eCJia_GOODS_LIST3.getRec_id());
                            }
                            if (((ECJia_NEWGOODITEM) this.d.j.get(i4)).getGoodslist().size() == 1) {
                                ((ECJia_NEWGOODITEM) this.d.j.get(i4)).setIsCheckedbuy(Boolean.valueOf(true));
                            }
                            while (i < size && ((ECJia_GOODS_LIST) ((ECJia_NEWGOODITEM) this.d.j.get(i4)).getGoodslist().get(i)).getIsCheckedbuy().booleanValue()) {
                                if (i == size - 1) {
                                    ((ECJia_NEWGOODITEM) this.d.j.get(i4)).setIsCheckedbuy(Boolean.valueOf(true));
                                }
                                i++;
                            }
                        }
                        if (this.d.g != null) {
                            this.d.g.c();
                            break;
                        }
                        break;
                }
                this.d.notifyDataSetChanged();
            }
        });
        final ECJia_GOODS_LIST eCJia_GOODS_LIST4 = eCJia_GOODS_LIST;
        final int i5 = i;
        final int i6 = i2;
        final int i7 = i3;
        final Context context3 = context;
        inflate.findViewById(R.id.shop_car_item_min).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq f;

            public void onClick(View view) {
                final c cVar;
                switch (this.f.f) {
                    case 0:
                        if (eCJia_GOODS_LIST4.getGoods_number() > 1) {
                            eCJia_GOODS_LIST4.setGoods_number(eCJia_GOODS_LIST4.getGoods_number() - 1);
                            if (this.f.g != null) {
                                this.f.notifyDataSetChanged();
                                this.f.g.a(eCJia_GOODS_LIST4, this.f.b, i5, i6, i7);
                                return;
                            }
                            return;
                        } else if (eCJia_GOODS_LIST4.getGoods_number() == 1) {
                            cVar = new c(context3, context3.getResources().getString(R.string.collect_delete), context3.getResources().getString(R.string.shopcar_deletes));
                            cVar.a();
                            cVar.b.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ bq_6 b;

                                public void onClick(View view) {
                                    if (this.b.f.g != null) {
                                        this.b.f.notifyDataSetChanged();
                                        this.b.f.g.a(eCJia_GOODS_LIST4, this.b.f.e, i5, i6, i7);
                                    }
                                    cVar.b();
                                }
                            });
                            cVar.d.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ bq_6 b;

                                public void onClick(View view) {
                                    cVar.b();
                                }
                            });
                            return;
                        } else {
                            return;
                        }
                    case 1:
                        if (eCJia_GOODS_LIST4.getGoods_number() > 1) {
                            eCJia_GOODS_LIST4.setGoods_number(eCJia_GOODS_LIST4.getGoods_number() - 1);
                            if (this.f.g != null) {
                                this.f.g.a(eCJia_GOODS_LIST4, this.f.b, i5, i6, i7);
                                return;
                            }
                            return;
                        } else if (eCJia_GOODS_LIST4.getGoods_number() == 1) {
                            cVar = new c(context3, context3.getResources().getString(R.string.collect_delete), context3.getResources().getString(R.string.shopcar_deletes));
                            cVar.a();
                            cVar.b.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ bq_6 b;

                                public void onClick(View view) {
                                    if (this.b.f.g != null) {
                                        this.b.f.g.a(eCJia_GOODS_LIST4, this.b.f.e, i5, i6, i7);
                                    }
                                    cVar.b();
                                }
                            });
                            cVar.d.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ bq_6 b;

                                public void onClick(View view) {
                                    cVar.b();
                                }
                            });
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        });
        eCJia_GOODS_LIST4 = eCJia_GOODS_LIST;
        i5 = i;
        i6 = i2;
        i7 = i3;
        inflate.findViewById(R.id.shop_car_item_sum).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq e;

            public void onClick(View view) {
                int goods_number = eCJia_GOODS_LIST4.getGoods_number();
                if (this.e.f == 1) {
                    eCJia_GOODS_LIST4.setGoods_number(goods_number + 1);
                    if (this.e.g != null) {
                        this.e.notifyDataSetChanged();
                        this.e.g.a(eCJia_GOODS_LIST4, this.e.c, i5, i6, i7);
                    }
                } else if (this.e.f == 0) {
                    eCJia_GOODS_LIST4.setGoods_number(goods_number + 1);
                    if (this.e.g != null) {
                        this.e.notifyDataSetChanged();
                        this.e.g.a(eCJia_GOODS_LIST4, this.e.c, i5, i6, i7);
                    }
                }
            }
        });
        eCJia_GOODS_LIST4 = eCJia_GOODS_LIST;
        final Context context4 = context;
        i6 = i;
        i7 = i2;
        final int i8 = i3;
        inflate.findViewById(R.id.shop_car_item_editNum).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bq g;

            public void onClick(View view) {
                int goods_number = eCJia_GOODS_LIST4.getGoods_number();
                this.g.k.getString(R.string.shopcart_goods_num_cannot_empty);
                final com.ecjia.component.view.a aVar = new com.ecjia.component.view.a(context4, goods_number + "");
                aVar.a();
                aVar.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ bq_8 b;

                    public void onClick(View view) {
                        this.b.g.a(context4, aVar.a);
                        aVar.b();
                    }
                });
                aVar.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ bq_8 b;

                    public void onClick(View view) {
                        if (TextUtils.isEmpty(aVar.a.getText().toString()) || Integer.valueOf(aVar.a.getText().toString()).intValue() == 0) {
                            final c cVar = new c(context4, context4.getResources().getString(R.string.collect_delete), context4.getResources().getString(R.string.shopcar_deletes));
                            cVar.a();
                            cVar.b.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ bq_8_2 b;

                                public void onClick(View view) {
                                    if (this.b.b.g.g != null) {
                                        this.b.b.g.notifyDataSetChanged();
                                        this.b.b.g.g.a(eCJia_GOODS_LIST4, this.b.b.g.e, i6, i7, i8);
                                    }
                                    cVar.b();
                                }
                            });
                            cVar.d.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ bq_8_2 b;

                                public void onClick(View view) {
                                    cVar.b();
                                }
                            });
                            aVar.b();
                            return;
                        }
                        if (!aVar.a.getText().toString().equals(textView3.getText().toString())) {
                            eCJia_GOODS_LIST4.setGoods_number(Integer.valueOf(aVar.a.getText().toString()).intValue());
                            if (this.b.g.g != null) {
                                this.b.g.notifyDataSetChanged();
                                this.b.g.g.a(eCJia_GOODS_LIST4, this.b.g.d, i6, i7, i8);
                            }
                            this.b.g.a(context4, aVar.a);
                            aVar.b();
                        }
                        this.b.g.a(context4, aVar.a);
                        aVar.b();
                    }
                });
            }
        });
        this.a.a(imageView, eCJia_GOODS_LIST.getImg().getThumb());
        if (k.a(eCJia_GOODS_LIST.getGoods_price()) == 0.0f) {
            textView.setText("免费");
        } else {
            textView.setText(eCJia_GOODS_LIST.getFormated_goods_price());
        }
        textView2.setText(eCJia_GOODS_LIST.getGoods_name());
        textView3.setText(eCJia_GOODS_LIST.getGoods_number() + "");
        return inflate;
    }

    public void a(Context context, EditText editText) {
        editText.clearFocus();
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private boolean e() {
        if (this.j.size() > 0) {
            int size = this.j.size();
            for (int i = 0; i < size; i++) {
                if (!((ECJia_NEWGOODITEM) this.j.get(i)).getIscheckDelete().booleanValue()) {
                    return false;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> a(int i) {
        ArrayList<String> arrayList = new ArrayList();
        Iterator it = ((ECJia_NEWGOODITEM) this.j.get(i)).getGoodslist().iterator();
        while (it.hasNext()) {
            arrayList.add(((ECJia_GOODS_LIST) it.next()).getRec_id());
        }
        return arrayList;
    }
}
