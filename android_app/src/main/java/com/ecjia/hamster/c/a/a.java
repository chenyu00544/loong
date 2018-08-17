package com.ecjia.hamster.c.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecjia.hamster.activity.ECJiaOrderdetailActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.model.ECJia_GOODORDER;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;

/* compiled from: ECJiaTradeAdapter */
public class a extends BaseAdapter {
    public ArrayList<ECJia_GOODORDER> a;
    public int b;
    private Context c;
    private LayoutInflater d;
    private Resources e;
    private b f;
    private b g = null;

    /* compiled from: ECJiaTradeAdapter */
    public interface b {
        void a(View view, int i);
    }

    /* compiled from: ECJiaTradeAdapter */
    static class a {
        TextView a;
        TextView b;
        ImageView c;
        TextView d;
        LinearLayout e;
        LinearLayout f;
        LinearLayout g;
        ECJiaHorizontalListView h;
        TextView i;
        TextView j;
        TextView k;
        TextView l;
        TextView m;
        TextView n;
        LinearLayout o;
        View p;
        TextView q;

        a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, ArrayList<ECJia_GOODORDER> arrayList) {
        this.c = context;
        this.a = arrayList;
        this.d = LayoutInflater.from(context);
        this.e = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public ECJia_GOODORDER a(int i) {
        return (ECJia_GOODORDER) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        final ECJia_GOODORDER eCJia_GOODORDER = (ECJia_GOODORDER) this.a.get(i);
        if (view == null) {
            view = this.d.inflate(R.layout.item_trade, null);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.tv_trade_seller_name);
            aVar.b = (TextView) view.findViewById(R.id.tv_trade_type);
            aVar.c = (ImageView) view.findViewById(R.id.iv_single_trade_goods);
            aVar.d = (TextView) view.findViewById(R.id.tv_single_trade_goods);
            aVar.e = (LinearLayout) view.findViewById(R.id.ll_single_trade_goods);
            aVar.f = (LinearLayout) view.findViewById(R.id.ll_multiple_trade_goods);
            aVar.h = (ECJiaHorizontalListView) view.findViewById(R.id.rlv_trade_goods);
            aVar.i = (TextView) view.findViewById(R.id.tv_trade_goods_num);
            aVar.j = (TextView) view.findViewById(R.id.tv_trade_cost);
            aVar.k = (TextView) view.findViewById(R.id.tv_trade_receive);
            aVar.l = (TextView) view.findViewById(R.id.tv_trade_comment);
            aVar.m = (TextView) view.findViewById(R.id.tv_trade_action);
            aVar.n = (TextView) view.findViewById(R.id.tv_trade_action2);
            aVar.o = (LinearLayout) view.findViewById(R.id.ll_trade_item);
            aVar.g = (LinearLayout) view.findViewById(R.id.seller_ll);
            aVar.p = view.findViewById(R.id.right_arrow);
            aVar.q = (TextView) view.findViewById(R.id.tv_id_order_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.q.setText("订单号：" + eCJia_GOODORDER.getOrder_sn());
        aVar.a.setText(eCJia_GOODORDER.getSeller_name());
        aVar.j.setText(eCJia_GOODORDER.getFormated_total_fee());
        aVar.b.setText(eCJia_GOODORDER.getLabel_order_status());
        aVar.i.setText(eCJia_GOODORDER.getGoods_num() + "");
        if (TextUtils.isEmpty(eCJia_GOODORDER.getSeller_id()) || "0".equals(eCJia_GOODORDER.getSeller_id())) {
            aVar.p.setVisibility(4);
        } else {
            aVar.p.setVisibility(0);
        }
        aVar.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (!TextUtils.isEmpty(eCJia_GOODORDER.getSeller_id()) && !"0".equals(eCJia_GOODORDER.getSeller_id())) {
                    Intent intent = new Intent(this.b.c, ECJiaShopListActivity.class);
                    intent.putExtra("merchant_id", eCJia_GOODORDER.getSeller_id());
                    this.b.c.startActivity(intent);
                }
            }
        });
        if (eCJia_GOODORDER.getOrder_status_code().equals("await_pay")) {
            aVar.m.setText(R.string.order_paynow);
            aVar.m.setVisibility(0);
            aVar.n.setVisibility(8);
        } else if (eCJia_GOODORDER.getOrder_status_code().equals("await_ship")) {
            aVar.n.setText(R.string.order_buy_again);
            aVar.n.setVisibility(0);
            aVar.m.setVisibility(8);
        } else if (eCJia_GOODORDER.getOrder_status_code().equals("shipped")) {
            aVar.m.setText(R.string.tradeitem_receive);
            aVar.m.setVisibility(0);
            aVar.n.setVisibility(8);
        } else {
            aVar.m.setText(R.string.order_buy_again);
            aVar.m.setVisibility(0);
            aVar.n.setVisibility(8);
        }
        if (eCJia_GOODORDER.getOrder_status_code().equals("finished")) {
            aVar.l.setVisibility(0);
            int i2 = 0;
            for (int i3 = 0; i3 < eCJia_GOODORDER.getGoods_list().size(); i3++) {
                if (((ECJia_ORDER_GOODS_LIST) eCJia_GOODORDER.getGoods_list().get(i3)).getIs_commented() == 1) {
                    i2++;
                }
            }
            if (i2 < eCJia_GOODORDER.getGoods_list().size()) {
                aVar.l.setText(R.string.order_to_comment);
                aVar.l.setVisibility(0);
                ((ECJia_GOODORDER) this.a.get(i)).setToComment(true);
            } else {
                aVar.l.setVisibility(8);
                aVar.l.setText(R.string.order_lookfor_commment);
                ((ECJia_GOODORDER) this.a.get(i)).setToComment(false);
            }
        } else {
            aVar.l.setVisibility(8);
        }
        if (eCJia_GOODORDER.getOrder_status_code().equals("await_pay")) {
            aVar.k.setVisibility(0);
            aVar.k.setText(R.string.balance_cancel);
        } else if (eCJia_GOODORDER.getOrder_status_code().equals("await_ship")) {
            aVar.k.setVisibility(8);
        } else if (eCJia_GOODORDER.getOrder_status_code().equals("shipped")) {
            aVar.k.setVisibility(0);
            aVar.k.setText(R.string.check_shipinfo);
        } else if (eCJia_GOODORDER.getOrder_status_code().equals("finished")) {
            aVar.k.setVisibility(4);
            aVar.k.setText(R.string.check_shipinfo);
        } else {
            aVar.k.setVisibility(4);
        }
        if (eCJia_GOODORDER.getGoods_list().size() == 1) {
            aVar.e.setVisibility(0);
            aVar.f.setVisibility(8);
            aVar.d.setText(((ECJia_ORDER_GOODS_LIST) eCJia_GOODORDER.getGoods_list().get(0)).getName());
            ImageLoader.getInstance().displayImage(((ECJia_ORDER_GOODS_LIST) eCJia_GOODORDER.getGoods_list().get(0)).getImg().getThumb(), aVar.c);
        } else if (eCJia_GOODORDER.getGoods_list().size() > 1) {
            aVar.e.setVisibility(8);
            aVar.f.setVisibility(0);
            this.f = new b(this.c, eCJia_GOODORDER.getGoods_list());
            aVar.h.setAdapter(this.f);
            aVar.h.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ a b;

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    this.b.b(i);
                }
            });
        } else {
            aVar.e.setVisibility(8);
            aVar.f.setVisibility(8);
        }
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (!TextUtils.isEmpty(eCJia_GOODORDER.getSeller_id()) && !"0".equals(eCJia_GOODORDER.getSeller_id())) {
                    Intent intent = new Intent(this.b.c, ECJiaShopListActivity.class);
                    intent.putExtra("merchant_id", eCJia_GOODORDER.getSeller_id());
                    this.b.c.startActivity(intent);
                    ((Activity) this.b.c).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            }
        });
        aVar.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.b(i);
            }
        });
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.b(i);
            }
        });
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.b(i);
            }
        });
        aVar.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.b(i);
            }
        });
        aVar.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.g != null) {
                    this.b.g.a(view, i);
                }
            }
        });
        aVar.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.g != null) {
                    this.b.g.a(view, i);
                }
            }
        });
        aVar.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.g != null) {
                    this.b.g.a(view, i);
                }
            }
        });
        aVar.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.g != null) {
                    this.b.g.a(view, i);
                }
            }
        });
        return view;
    }

    private void b(int i) {
        a(((ECJia_GOODORDER) this.a.get(i)).getOrder_status_code());
        if (this.b != 0) {
            Intent intent = new Intent(this.c, ECJiaOrderdetailActivity.class);
            intent.putExtra("order_id", ((ECJia_GOODORDER) this.a.get(i)).getOrder_id());
            intent.putExtra("pay_code", ((ECJia_GOODORDER) this.a.get(i)).getOrder_info().getPay_code());
            intent.putExtra("formated_total_fee", ((ECJia_GOODORDER) this.a.get(i)).getFormated_total_fee());
            intent.putExtra(AgooConstants.MESSAGE_FLAG, this.b);
            ((Activity) this.c).startActivityForResult(intent, 1);
        }
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    private void a(String str) {
        if (str.equals("await_pay")) {
            this.b = 1;
        } else if (str.equals("await_ship")) {
            this.b = 2;
        } else if (str.equals("shipped")) {
            this.b = 3;
        } else if (str.equals("finished")) {
            this.b = 4;
        } else if (str.equals("canceled")) {
            this.b = 5;
        }
    }
}
