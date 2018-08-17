package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecjia.component.webimageview.ECJiaWebImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.model.an;
import com.ecjia.hamster.model.ao;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaShopCollectAdapter */
public class bi extends BaseAdapter {
    public int a;
    public ArrayList<ao> b;
    public Handler c;
    protected ImageLoader d = ImageLoader.getInstance();
    private Context e;
    private LayoutInflater f;
    private bj g;
    private b h = null;

    /* compiled from: ECJiaShopCollectAdapter */
    class a {
        LinearLayout a;
        LinearLayout b;
        LinearLayout c;
        LinearLayout d;
        ECJiaHorizontalListView e;
        ECJiaWebImageView f;
        TextView g;
        TextView h;
        ImageView i;
        View j;
        View k;
        View l;
        CheckBox m;
        final /* synthetic */ bi n;

        a(bi biVar) {
            this.n = biVar;
        }
    }

    /* compiled from: ECJiaShopCollectAdapter */
    public interface b {
    }

    public bi(Context context, ArrayList<ao> arrayList, int i) {
        this.e = context;
        this.b = arrayList;
        this.a = i;
        this.f = LayoutInflater.from(context);
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public int getCount() {
        return this.b.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        int i2 = 0;
        if (view == null) {
            aVar = new a(this);
            view = this.f.inflate(R.layout.shop_collect_item, null);
            aVar.f = (ECJiaWebImageView) view.findViewById(R.id.collect_img);
            aVar.g = (TextView) view.findViewById(R.id.collect_name);
            aVar.m = (CheckBox) view.findViewById(R.id.collect_item_del);
            aVar.a = (LinearLayout) view.findViewById(R.id.collect_item);
            aVar.j = view.findViewById(R.id.collect_top_line);
            aVar.k = view.findViewById(R.id.collect_buttom_line);
            aVar.l = view.findViewById(R.id.collect_short_line);
            aVar.b = (LinearLayout) view.findViewById(R.id.collect_check_item);
            aVar.c = (LinearLayout) view.findViewById(R.id.collect_rightitem);
            aVar.d = (LinearLayout) view.findViewById(R.id.ll_collect_bottom);
            aVar.h = (TextView) view.findViewById(R.id.tv_newgoods_num);
            aVar.e = (ECJiaHorizontalListView) view.findViewById(R.id.shop_newgoodlist);
            aVar.i = (ImageView) view.findViewById(R.id.iv_open_arrow);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.j.setVisibility(0);
        } else {
            aVar.j.setVisibility(8);
        }
        if (i != this.b.size() - 1) {
            aVar.l.setVisibility(0);
            aVar.k.setVisibility(8);
        } else {
            aVar.l.setVisibility(8);
            aVar.k.setVisibility(0);
        }
        if (this.a == 1) {
            aVar.b.setVisibility(8);
            aVar.i.setVisibility(0);
            aVar.a.setBackgroundResource(R.drawable.new_nothing_bg);
        } else if (this.a == 2) {
            aVar.b.setVisibility(0);
            aVar.i.setVisibility(8);
            aVar.d.setVisibility(8);
        }
        aVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bi b;

            public void onClick(View view) {
                if (((ao) this.b.b.get(i)).c()) {
                    ((ao) this.b.b.get(i)).a(false);
                } else {
                    ((ao) this.b.b.get(i)).a(true);
                }
                Message message = new Message();
                message.arg1 = 100;
                this.b.c.handleMessage(message);
            }
        });
        if (aVar.b.getVisibility() != 0) {
            if (((ao) this.b.get(i)).c()) {
                aVar.d.setVisibility(0);
                aVar.i.setImageResource(R.drawable.arrow_collect_up);
            } else {
                aVar.d.setVisibility(8);
                aVar.i.setImageResource(R.drawable.arrow_collect_down);
            }
        }
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bi c;

            public void onClick(View view) {
                if (aVar.m.isChecked()) {
                    aVar.m.setChecked(false);
                    ((ao) this.c.b.get(i)).b(false);
                } else {
                    aVar.m.setChecked(true);
                    ((ao) this.c.b.get(i)).b(true);
                }
                Message message;
                if (this.c.a()) {
                    message = new Message();
                    message.arg1 = 1;
                    this.c.c.handleMessage(message);
                } else {
                    message = new Message();
                    message.arg1 = 0;
                    this.c.c.handleMessage(message);
                }
                this.c.notifyDataSetChanged();
                q.a("====是否选中===" + ((ao) this.c.b.get(i)).d());
            }
        });
        aVar.m.setChecked(((ao) this.b.get(i)).d());
        if (aVar.b.getVisibility() != 0) {
            aVar.c.setBackgroundResource(R.drawable.selecter_newitem_press);
        } else if (((ao) this.b.get(i)).d()) {
            aVar.a.setBackgroundResource(R.drawable.new_good_distance);
            aVar.c.setBackgroundResource(R.drawable.new_good_distance);
        } else {
            aVar.a.setBackgroundResource(R.drawable.new_nothing_bg);
            aVar.c.setBackgroundResource(R.drawable.new_nothing_bg);
        }
        if (((ao) this.b.get(i)).f().size() > 0) {
            aVar.c.setEnabled(true);
        } else {
            aVar.c.setEnabled(false);
        }
        ArrayList arrayList = new ArrayList();
        if (((ao) this.b.get(i)).f().size() > 4) {
            while (i2 < 4) {
                arrayList.add(((ao) this.b.get(i)).f().get(i2));
                i2++;
            }
            an anVar = new an();
            anVar.a("+1");
            arrayList.add(anVar);
            this.g = new bj(this.e, arrayList);
        } else {
            this.g = new bj(this.e, ((ao) this.b.get(i)).f());
        }
        final String e = ((ao) this.b.get(i)).e();
        final ArrayList f = ((ao) this.b.get(i)).f();
        aVar.e.setAdapter(this.g);
        aVar.e.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ bi c;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Object c = ((an) f.get(i)).c();
                if (TextUtils.isEmpty(c) || !"+1".equals(c)) {
                    Intent intent = new Intent(this.c.e, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", c);
                    intent.putExtra("rec_type", ((an) f.get(i)).a());
                    this.c.e.startActivity(intent);
                    ((Activity) this.c.e).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                }
                Intent intent2 = new Intent(this.c.e, ECJiaShopListActivity.class);
                intent2.putExtra("merchant_id", e);
                this.c.e.startActivity(intent2);
                ((Activity) this.c.e).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        aVar.g.setText(((ao) this.b.get(i)).h());
        aVar.h.setText(((ao) this.b.get(i)).f().size() + "");
        p.a(this.e).a(aVar.f, ((ao) this.b.get(i)).i());
        return view;
    }

    private boolean a() {
        for (int i = 0; i < this.b.size(); i++) {
            if (((ao) this.b.get(i)).d()) {
                return true;
            }
        }
        return false;
    }
}
