package com.ecjia.hamster.adaptercell;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.webimageview.ECJiaWebImageView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaGoodsListActivity;
import com.ecjia.hamster.activity.ECJiaMainActivity;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.au;
import com.ecjia.hamster.model.g;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import org.json.JSONException;

public class ECJiaCategorySellingCell extends LinearLayout {
    g categorygoods;
    int count = 0;
    private TextView good_cell_name_one;
    private TextView good_cell_name_three;
    private TextView good_cell_name_two;
    private LinearLayout good_cell_one;
    private ECJiaWebImageView good_cell_photo_one;
    private ECJiaWebImageView good_cell_photo_three;
    private ECJiaWebImageView good_cell_photo_two;
    private TextView good_cell_price_three;
    private TextView good_cell_price_two;
    private LinearLayout good_cell_three;
    private LinearLayout good_cell_two;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    Context mContext;
    Handler mHandler;

    class ECJiaCategorySellingCell_1 extends Handler {
        final /* synthetic */ ECJiaCategorySellingCell a;

        ECJiaCategorySellingCell_1(ECJiaCategorySellingCell eCJiaCategorySellingCell) {
            this.a = eCJiaCategorySellingCell;
        }

        public void handleMessage(Message message) {
            this.a.bindDataDelay();
        }
    }

    class ECJiaCategorySellingCell_2 implements OnClickListener {
        final /* synthetic */ ECJiaCategorySellingCell a;

        ECJiaCategorySellingCell_2(ECJiaCategorySellingCell eCJiaCategorySellingCell) {
            this.a = eCJiaCategorySellingCell;
        }

        public void onClick(View view) {
            q.a("=======点击事件=====");
            Intent intent = new Intent(this.a.mContext, ECJiaGoodsListActivity.class);
            ECJia_FILTER eCJia_FILTER = new ECJia_FILTER();
            eCJia_FILTER.setCategory_id(String.valueOf(this.a.categorygoods.a()));
            try {
                intent.putExtra("filter", eCJia_FILTER.toJson().toString());
            } catch (JSONException e) {
            }
            this.a.mContext.startActivity(intent);
            ((Activity) this.a.mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    class ECJiaCategorySellingCell_3 implements OnLongClickListener {
        final /* synthetic */ ECJiaCategorySellingCell a;

        ECJiaCategorySellingCell_3(ECJiaCategorySellingCell eCJiaCategorySellingCell) {
            this.a = eCJiaCategorySellingCell;
        }

        public boolean onLongClick(View view) {
            return true;
        }
    }

    public ECJiaCategorySellingCell(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mHandler = new ECJiaCategorySellingCell_1(this);
    }

    void init() {
        if (this.good_cell_one == null) {
            this.good_cell_one = (LinearLayout) findViewById(R.id.good_cell_one);
        }
        if (this.good_cell_two == null) {
            this.good_cell_two = (LinearLayout) findViewById(R.id.good_cell_two);
        }
        if (this.good_cell_three == null) {
            this.good_cell_three = (LinearLayout) findViewById(R.id.good_cell_three);
        }
        if (this.good_cell_photo_one == null) {
            this.good_cell_photo_one = (ECJiaWebImageView) findViewById(R.id.good_cell_photo_one);
        }
        if (this.good_cell_photo_two == null) {
            this.good_cell_photo_two = (ECJiaWebImageView) findViewById(R.id.good_cell_photo_two);
        }
        if (this.good_cell_photo_three == null) {
            this.good_cell_photo_three = (ECJiaWebImageView) findViewById(R.id.good_cell_photo_three);
        }
        if (this.good_cell_name_one == null) {
            this.good_cell_name_one = (TextView) findViewById(R.id.good_cell_name_one);
        }
        if (this.good_cell_name_two == null) {
            this.good_cell_name_two = (TextView) findViewById(R.id.good_cell_name_two);
        }
        if (this.good_cell_name_three == null) {
            this.good_cell_name_three = (TextView) findViewById(R.id.good_cell_name_three);
        }
        if (this.good_cell_price_two == null) {
            this.good_cell_price_two = (TextView) findViewById(R.id.good_cell_price_two);
        }
        if (this.good_cell_price_three == null) {
            this.good_cell_price_three = (TextView) findViewById(R.id.good_cell_price_three);
        }
    }

    public void bindDataDelay() {
        init();
        ArrayList c = this.categorygoods.c();
        if (this.categorygoods.b() != null) {
            this.good_cell_name_one.setText(this.categorygoods.b());
            this.count++;
            this.good_cell_one.setOnClickListener(new ECJiaCategorySellingCell_2(this));
            this.good_cell_one.setOnLongClickListener(new ECJiaCategorySellingCell_3(this));
        }
        if (c.size() > 0) {
            au auVar = (au) c.get(0);
            this.good_cell_photo_one.setVisibility(0);
            this.imageLoader.displayImage(auVar.i().getUrl(), this.good_cell_photo_one);
            if (c.size() > 1) {
                this.good_cell_two.setVisibility(0);
                auVar = (au) c.get(1);
                if (!(auVar == null || auVar.i() == null || auVar.i().getThumb() == null || auVar.i().getSmall() == null)) {
                    this.imageLoader.displayImage(auVar.i().getThumb(), this.good_cell_photo_two);
                    this.good_cell_two.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ECJiaCategorySellingCell b;

                        public void onClick(View view) {
                            Intent intent = new Intent(this.b.mContext, ECJiaGoodsDetailActivity.class);
                            intent.putExtra("good_id", auVar.c());
                            this.b.mContext.startActivity(intent);
                            ((ECJiaMainActivity) this.b.mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }
                    });
                }
                this.good_cell_name_two.setText(auVar.g());
                this.good_cell_price_two.setText(auVar.e());
                if (c.size() > 2) {
                    this.good_cell_three.setVisibility(0);
                    auVar = (au) c.get(2);
                    if (!(auVar == null || auVar.i() == null || auVar.i().getThumb() == null || auVar.i().getSmall() == null)) {
                        this.imageLoader.displayImage(auVar.i().getThumb(), this.good_cell_photo_three);
                        this.good_cell_three.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ ECJiaCategorySellingCell b;

                            public void onClick(View view) {
                                Intent intent = new Intent(this.b.mContext, ECJiaGoodsDetailActivity.class);
                                intent.putExtra("good_id", auVar.c());
                                this.b.mContext.startActivity(intent);
                                ((ECJiaMainActivity) this.b.mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                            }
                        });
                    }
                    this.good_cell_name_three.setText(auVar.g());
                    this.good_cell_price_three.setText(auVar.e());
                    return;
                }
                this.good_cell_three.setVisibility(4);
                return;
            }
            this.good_cell_two.setVisibility(4);
            this.good_cell_three.setVisibility(4);
            return;
        }
        this.good_cell_photo_one.setVisibility(4);
        this.good_cell_two.setVisibility(4);
        this.good_cell_three.setVisibility(4);
    }

    public void bindData(g gVar) {
        this.categorygoods = gVar;
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 30);
    }
}
