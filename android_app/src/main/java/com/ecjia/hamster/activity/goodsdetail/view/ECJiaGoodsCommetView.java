package com.ecjia.hamster.activity.goodsdetail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.component.view.xlratingbar.ECJiaXLHRatingBar;
import com.ecjia.hamster.activity.ECJiaFullScreenViewPagerActivity;
import com.ecjia.hamster.activity.goodsdetail.a.a;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ECJiaGoodsCommetView extends LinearLayout {
    private ImageView avatar_img;
    private TextView comment_content;
    private ECJiaXLHRatingBar comment_rank;
    private TextView comment_time;
    private View divider;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private TextView goods_attr;
    private Context mContext;
    private ImageView showorder_image1;
    private ImageView showorder_image2;
    private ImageView showorder_image3;
    private ImageView showorder_image4;
    private ImageView showorder_image5;
    private LinearLayout showorder_image_ll;
    private TextView user_name;

    public ECJiaGoodsCommetView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ECJiaGoodsCommetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public ECJiaGoodsCommetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    void init() {
        if (this.avatar_img == null) {
            this.avatar_img = (ImageView) findViewById(R.id.avatar_img);
        }
        if (this.user_name == null) {
            this.user_name = (TextView) findViewById(R.id.user_name);
        }
        if (this.comment_rank == null) {
            this.comment_rank = (ECJiaXLHRatingBar) findViewById(R.id.comment_rank);
        }
        if (this.comment_time == null) {
            this.comment_time = (TextView) findViewById(R.id.comment_time);
        }
        if (this.comment_content == null) {
            this.comment_content = (TextView) findViewById(R.id.comment_content);
        }
        if (this.goods_attr == null) {
            this.goods_attr = (TextView) findViewById(R.id.goods_attr);
        }
        if (this.showorder_image_ll == null) {
            this.showorder_image_ll = (LinearLayout) findViewById(R.id.showorder_image_ll);
        }
        if (this.showorder_image1 == null) {
            this.showorder_image1 = (ImageView) findViewById(R.id.showorder_image1);
        }
        if (this.showorder_image2 == null) {
            this.showorder_image2 = (ImageView) findViewById(R.id.showorder_image2);
        }
        if (this.showorder_image3 == null) {
            this.showorder_image3 = (ImageView) findViewById(R.id.showorder_image3);
        }
        if (this.showorder_image4 == null) {
            this.showorder_image4 = (ImageView) findViewById(R.id.showorder_image4);
        }
        if (this.showorder_image5 == null) {
            this.showorder_image5 = (ImageView) findViewById(R.id.showorder_image5);
        }
        if (this.divider == null) {
            this.divider = findViewById(R.id.comment_div);
        }
    }

    public void bindData(a aVar) {
        init();
        this.user_name.setText(aVar.e());
        this.comment_rank.setCountSelected(Integer.valueOf(aVar.c()).intValue());
        Date date = null;
        try {
            date = this.format.parse(aVar.g());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.comment_time.setText(this.format.format(date));
        this.comment_content.setText(aVar.d());
        if (TextUtils.isEmpty(aVar.a())) {
            this.goods_attr.setVisibility(8);
        } else {
            this.goods_attr.setVisibility(0);
            this.goods_attr.setText(aVar.a());
        }
        p.a().a(this.avatar_img, aVar.f(), 9003);
        if (aVar.b().size() > 0) {
            this.showorder_image_ll.setVisibility(0);
            int min = Math.min(5, aVar.b().size());
            if (min == 1) {
                this.showorder_image1.setVisibility(0);
                this.showorder_image2.setVisibility(4);
                this.showorder_image3.setVisibility(4);
                this.showorder_image4.setVisibility(4);
                this.showorder_image5.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(0), this.showorder_image1);
                setImageOnClick(this.showorder_image1, aVar.b(), 0);
            } else if (min == 2) {
                this.showorder_image1.setVisibility(0);
                this.showorder_image2.setVisibility(0);
                this.showorder_image3.setVisibility(4);
                this.showorder_image4.setVisibility(4);
                this.showorder_image5.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(0), this.showorder_image1);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(1), this.showorder_image2);
                setImageOnClick(this.showorder_image1, aVar.b(), 0);
                setImageOnClick(this.showorder_image2, aVar.b(), 1);
            } else if (min == 3) {
                this.showorder_image1.setVisibility(0);
                this.showorder_image2.setVisibility(0);
                this.showorder_image3.setVisibility(0);
                this.showorder_image4.setVisibility(4);
                this.showorder_image5.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(0), this.showorder_image1);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(1), this.showorder_image2);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(2), this.showorder_image3);
                setImageOnClick(this.showorder_image1, aVar.b(), 0);
                setImageOnClick(this.showorder_image2, aVar.b(), 1);
                setImageOnClick(this.showorder_image3, aVar.b(), 2);
            } else if (min == 4) {
                this.showorder_image1.setVisibility(0);
                this.showorder_image2.setVisibility(0);
                this.showorder_image3.setVisibility(0);
                this.showorder_image4.setVisibility(0);
                this.showorder_image5.setVisibility(4);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(0), this.showorder_image1);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(1), this.showorder_image2);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(2), this.showorder_image3);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(3), this.showorder_image4);
                setImageOnClick(this.showorder_image1, aVar.b(), 0);
                setImageOnClick(this.showorder_image2, aVar.b(), 1);
                setImageOnClick(this.showorder_image3, aVar.b(), 2);
                setImageOnClick(this.showorder_image4, aVar.b(), 3);
            } else if (min >= 5) {
                this.showorder_image1.setVisibility(0);
                this.showorder_image2.setVisibility(0);
                this.showorder_image3.setVisibility(0);
                this.showorder_image4.setVisibility(0);
                this.showorder_image5.setVisibility(0);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(0), this.showorder_image1);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(1), this.showorder_image2);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(2), this.showorder_image3);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(3), this.showorder_image4);
                ImageLoader.getInstance().displayImage((String) aVar.b().get(4), this.showorder_image5);
                setImageOnClick(this.showorder_image1, aVar.b(), 0);
                setImageOnClick(this.showorder_image2, aVar.b(), 1);
                setImageOnClick(this.showorder_image3, aVar.b(), 2);
                setImageOnClick(this.showorder_image4, aVar.b(), 3);
                setImageOnClick(this.showorder_image5, aVar.b(), 4);
            } else {
                this.showorder_image1.setVisibility(8);
                this.showorder_image2.setVisibility(8);
                this.showorder_image3.setVisibility(8);
                this.showorder_image4.setVisibility(8);
                this.showorder_image5.setVisibility(8);
            }
        }
    }

    public void setDivliverVisible(int i) {
        this.divider.setVisibility(i);
    }

    void setImageOnClick(ImageView imageView, final ArrayList<String> arrayList, final int i) {
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ECJiaGoodsCommetView c;

            public void onClick(View view) {
                Intent intent = new Intent(this.c.mContext, ECJiaFullScreenViewPagerActivity.class);
                intent.putExtra("position", i);
                Serializable arrayList = new ArrayList();
                int min = Math.min(5, arrayList.size());
                for (int i = 0; i < min; i++) {
                    arrayList.add(arrayList.get(i));
                }
                intent.putExtra("size", min);
                intent.putExtra("pictures", arrayList);
                this.c.mContext.startActivity(intent);
                ((Activity) this.c.mContext).overridePendingTransition(R.anim.my_scale_action, R.anim.my_scale_action);
            }
        });
    }
}
