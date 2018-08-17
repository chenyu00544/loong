package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.a.p;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.model.an;
import com.ecjia.hamster.model.ao;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaHomeShopListAdapter */
public class y extends BaseAdapter {
    public Handler a;
    public int b;
    private Resources c;
    private Context d;
    private ArrayList<ao> e;
    private p f;
    private int g;
    private int h = ((int) this.c.getDimension(R.dimen.dp_5));

    /* compiled from: ECJiaHomeShopListAdapter */
    private class a {
        final /* synthetic */ y a;
        private ImageView b;
        private ImageView c;
        private ImageView d;
        private ImageView e;
        private ImageView f;
        private ImageView g;
        private ImageView h;
        private TextView i;
        private TextView j;
        private LinearLayout k;
        private LinearLayout l;
        private LinearLayout m;
        private LinearLayout n;
        private TextView o;
        private TextView p;
        private View q;

        private a(y yVar) {
            this.a = yVar;
        }
    }

    public y(Context context, ArrayList<ao> arrayList, int i) {
        this.d = context;
        this.e = arrayList;
        this.g = i;
        this.b = (int) context.getResources().getDimension(R.dimen.eight_margin);
        this.c = context.getResources();
        this.f = p.a(context);
    }

    public void a(ArrayList<ao> arrayList) {
        this.e = arrayList;
    }

    public ArrayList<ao> a() {
        return this.e;
    }

    public int getCount() {
        return this.e.size();
    }

    public Object getItem(int i) {
        return this.e.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        final ao aoVar = (ao) this.e.get(i);
        if (view == null) {
            aVar = new a();
            view = LayoutInflater.from(this.d).inflate(R.layout.sellerlist_item, null);
            aVar.k = (LinearLayout) view.findViewById(R.id.shoplist_item);
            aVar.g = (ImageView) view.findViewById(R.id.seller_logo);
            aVar.h = (ImageView) view.findViewById(R.id.seller_banner);
            aVar.p = (TextView) view.findViewById(R.id.seller_item_shopname);
            aVar.o = (TextView) view.findViewById(R.id.seller_collect_num);
            aVar.l = (LinearLayout) view.findViewById(R.id.shoplist_collected);
            aVar.m = (LinearLayout) view.findViewById(R.id.shoplist_uncollect);
            aVar.n = (LinearLayout) view.findViewById(R.id.ll_goods_part);
            aVar.i = (TextView) view.findViewById(R.id.seller_desc);
            aVar.j = (TextView) view.findViewById(R.id.tv_seller_distance);
            aVar.b = (ImageView) view.findViewById(R.id.goodimg_1);
            aVar.c = (ImageView) view.findViewById(R.id.goodimg_2);
            aVar.d = (ImageView) view.findViewById(R.id.goodimg_3);
            aVar.e = (ImageView) view.findViewById(R.id.goodimg_4);
            aVar.f = (ImageView) view.findViewById(R.id.goodimg_5);
            a(aVar.b);
            a(aVar.c);
            a(aVar.d);
            a(aVar.e);
            a(aVar.f);
            view.setTag(aVar);
            aVar.q = view.findViewById(R.id.bottom_div);
        } else {
            aVar = (a) view.getTag();
        }
        a(aVar.k.getChildAt(1));
        if (!TextUtils.isEmpty(aoVar.a())) {
            this.f.a(aVar.h, aoVar.a());
        }
        aVar.o.setText(aoVar.g() + this.c.getString(R.string.follower_num));
        aVar.l.setVisibility(8);
        aVar.m.setVisibility(8);
        if (!TextUtils.isEmpty(aoVar.b())) {
            aVar.i.setText(aoVar.b());
        }
        aVar.p.setText(aoVar.h());
        float a = k.a(aoVar.j());
        if (0.0f == a) {
            aVar.j.setText("");
        } else if (a < 1000.0f) {
            aVar.j.setText(((int) a) + "m");
        } else {
            aVar.j.setText(k.a(a / 1000.0f) + "km");
        }
        aVar.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ y b;

            public void onClick(View view) {
                Message message = new Message();
                message.what = 1;
                message.arg1 = i;
                this.b.a.sendMessage(message);
            }
        });
        aVar.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ y b;

            public void onClick(View view) {
                Message message = new Message();
                message.what = 2;
                message.arg1 = i;
                this.b.a.sendMessage(message);
            }
        });
        aVar.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ y b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.d, ECJiaShopListActivity.class);
                intent.putExtra("merchant_id", aoVar.e());
                this.b.d.startActivity(intent);
                ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        int size = aoVar.f().size();
        if (size > 0) {
            aVar.n.setVisibility(0);
            aVar.b.setVisibility(0);
            this.f.a(aVar.b, ((an) aoVar.f().get(0)).g().getThumb());
            aVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ y b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.d, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(0)).c());
                    this.b.d.startActivity(intent);
                    ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.b.setVisibility(4);
            aVar.n.setVisibility(8);
        }
        if (size > 1) {
            aVar.c.setVisibility(0);
            this.f.a(aVar.c, ((an) aoVar.f().get(1)).g().getThumb());
            aVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ y b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.d, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(1)).c());
                    this.b.d.startActivity(intent);
                    ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.c.setVisibility(4);
        }
        if (size > 2) {
            aVar.d.setVisibility(0);
            this.f.a(aVar.d, ((an) aoVar.f().get(2)).g().getThumb());
            aVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ y b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.d, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(2)).c());
                    this.b.d.startActivity(intent);
                    ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.d.setVisibility(4);
        }
        if (size > 3) {
            aVar.e.setVisibility(0);
            this.f.a(aVar.e, ((an) aoVar.f().get(3)).g().getThumb());
            aVar.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ y b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.d, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(3)).c());
                    this.b.d.startActivity(intent);
                    ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.e.setVisibility(4);
        }
        if (size > 4) {
            aVar.f.setVisibility(0);
            this.f.a(aVar.f, ((an) aoVar.f().get(4)).g().getThumb());
            aVar.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ y b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.d, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((an) aoVar.f().get(4)).c());
                    this.b.d.startActivity(intent);
                    ((Activity) this.b.d).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        } else {
            aVar.f.setVisibility(4);
        }
        if (i == this.e.size() - 1) {
            aVar.q.setVisibility(8);
        } else {
            aVar.q.setVisibility(0);
        }
        return view;
    }

    void a(View view) {
        view.setLayoutParams(new LayoutParams(this.g + (this.b * 2), (this.g + (this.b * 2)) / 2));
    }

    void a(ImageView imageView) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams((this.g / 5) - (this.h * 2), (this.g / 5) - (this.h * 2));
        layoutParams.setMargins(this.h, this.h, this.h, this.h);
        layoutParams.height = (this.g / 5) - (this.h * 2);
        imageView.setLayoutParams(layoutParams);
    }
}
