package com.ecjia.component.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import java.util.List;

public class ECJiaTwoGoodItemCell extends LinearLayout {
    private ECJiaAutoReturnView good_cell_name_one;
    private ECJiaAutoReturnView good_cell_name_two;
    private LinearLayout good_cell_one;
    private ImageView good_cell_photo_one;
    private ImageView good_cell_photo_two;
    private TextView good_cell_price_one;
    private TextView good_cell_price_two;
    private LinearLayout good_cell_two;
    private LinearLayout ll_goodlist_mb_one;
    private LinearLayout ll_goodlist_mb_two;
    Context mContext;
    private TextView market_price_one;
    private TextView market_price_two;
    private TextView tv_saving_one;
    private TextView tv_saving_two;

    public ECJiaTwoGoodItemCell(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    void init() {
        if (this.good_cell_one == null) {
            this.good_cell_one = (LinearLayout) findViewById(R.id.good_item_one);
        }
        if (this.good_cell_two == null) {
            this.good_cell_two = (LinearLayout) findViewById(R.id.good_item_two);
        }
        if (this.ll_goodlist_mb_one == null) {
            this.ll_goodlist_mb_one = (LinearLayout) this.good_cell_one.findViewById(R.id.ll_goodlist_mb);
        }
        if (this.ll_goodlist_mb_two == null) {
            this.ll_goodlist_mb_two = (LinearLayout) this.good_cell_two.findViewById(R.id.ll_goodlist_mb);
        }
        if (this.good_cell_photo_one == null) {
            this.good_cell_photo_one = (ImageView) this.good_cell_one.findViewById(R.id.goodlist_img);
        }
        if (this.good_cell_photo_two == null) {
            this.good_cell_photo_two = (ImageView) this.good_cell_two.findViewById(R.id.goodlist_img);
        }
        if (this.good_cell_name_one == null) {
            this.good_cell_name_one = (ECJiaAutoReturnView) this.good_cell_one.findViewById(R.id.goodlist_goodname);
        }
        if (this.good_cell_name_two == null) {
            this.good_cell_name_two = (ECJiaAutoReturnView) this.good_cell_two.findViewById(R.id.goodlist_goodname);
        }
        if (this.good_cell_price_one == null) {
            this.good_cell_price_one = (TextView) this.good_cell_one.findViewById(R.id.goodlist_shop_price);
        }
        if (this.good_cell_price_two == null) {
            this.good_cell_price_two = (TextView) this.good_cell_two.findViewById(R.id.goodlist_shop_price);
        }
        if (this.tv_saving_one == null) {
            this.tv_saving_one = (TextView) this.good_cell_one.findViewById(R.id.tv_saving);
        }
        if (this.tv_saving_two == null) {
            this.tv_saving_two = (TextView) this.good_cell_two.findViewById(R.id.tv_saving);
        }
        if (this.market_price_one == null) {
            this.market_price_one = (TextView) this.good_cell_one.findViewById(R.id.goodlist_promote_price);
            this.market_price_one.getPaint().setAntiAlias(true);
            this.market_price_one.getPaint().setFlags(16);
        }
        if (this.market_price_two == null) {
            this.market_price_two = (TextView) this.good_cell_two.findViewById(R.id.goodlist_promote_price);
            this.market_price_two.getPaint().setAntiAlias(true);
            this.market_price_two.getPaint().setFlags(16);
        }
    }

    public void bindData(List<au> list) {
        init();
        if (list.size() > 0) {
            final au auVar = (au) list.get(0);
            if (!(auVar == null || auVar.i() == null)) {
                p.a(this.mContext).a(this.good_cell_photo_one, auVar.i().getThumb());
            }
            if (auVar.e() == null || auVar.e().length() <= 0) {
                this.good_cell_price_one.setText(auVar.e());
            } else {
                this.good_cell_price_one.setText(auVar.e());
            }
            q.b("===goodOne.getActivity_type()===" + auVar.a());
            if ("MOBILEBUY_GOODS".equals(auVar.a())) {
                this.ll_goodlist_mb_one.setVisibility(0);
                this.tv_saving_one.setText(auVar.b());
            } else {
                this.ll_goodlist_mb_one.setVisibility(8);
            }
            if ("免费".equals(auVar.e())) {
                this.market_price_one.setVisibility(4);
            } else {
                this.market_price_one.setVisibility(0);
                this.market_price_one.setText(auVar.f());
            }
            this.good_cell_name_one.setContent(auVar.g());
            this.good_cell_one.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaTwoGoodItemCell b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.mContext, ECJiaGoodsDetailActivity.class);
                    int h = auVar.h();
                    if (h == 0) {
                        h = auVar.c();
                    }
                    intent.putExtra("goods_id", h);
                    this.b.mContext.startActivity(intent);
                }
            });
            if (list.size() > 1) {
                this.good_cell_two.setVisibility(0);
                auVar = (au) list.get(1);
                if (!(auVar == null || auVar.i() == null)) {
                    p.a(this.mContext).a(this.good_cell_photo_two, auVar.i().getThumb());
                }
                if (auVar.e() == null || auVar.e().length() <= 0) {
                    this.good_cell_price_two.setText(auVar.e());
                } else {
                    this.good_cell_price_two.setText(auVar.e());
                }
                q.b("===goodTwo.getActivity_type()===" + auVar.a());
                if ("MOBILEBUY_GOODS".equals(auVar.a())) {
                    this.ll_goodlist_mb_two.setVisibility(0);
                    this.tv_saving_two.setText(auVar.b());
                } else {
                    this.ll_goodlist_mb_two.setVisibility(8);
                }
                if ("免费".equals(auVar.e())) {
                    this.market_price_two.setVisibility(4);
                } else {
                    this.market_price_two.setVisibility(0);
                    this.market_price_two.setText(auVar.f());
                }
                this.good_cell_name_two.setContent(auVar.g());
                this.good_cell_two.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaTwoGoodItemCell b;

                    public void onClick(View view) {
                        Intent intent = new Intent(this.b.mContext, ECJiaGoodsDetailActivity.class);
                        int h = auVar.h();
                        if (h == 0) {
                            h = auVar.c();
                        }
                        intent.putExtra("goods_id", h);
                        this.b.mContext.startActivity(intent);
                    }
                });
                return;
            }
            this.good_cell_two.setVisibility(4);
        }
    }
}
