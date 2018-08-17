package com.ecjia.hamster.adaptercell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

public class ECJiaMyHotCell extends LinearLayout {
    public FrameLayout fl_hot_left;
    public FrameLayout fl_hot_right;
    public LinearLayout good_cell_one;
    public ImageView good_cell_photo_one;
    public ImageView good_cell_photo_two;
    public TextView good_cell_price_one;
    public TextView good_cell_price_two;
    public LinearLayout good_cell_two;
    public TextView good_info_one;
    public TextView good_info_two;
    Context mContext;
    public LinearLayout myhotcell2;

    public ECJiaMyHotCell(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void cellinit() {
        if (this.good_cell_one == null) {
            this.good_cell_one = (LinearLayout) findViewById(R.id.newgood_item_one);
        }
        if (this.good_cell_two == null) {
            this.good_cell_two = (LinearLayout) findViewById(R.id.newgood_item_two);
        }
        if (this.good_cell_photo_one == null) {
            this.good_cell_photo_one = (ImageView) findViewById(R.id.new_gooditem_photo1);
        }
        if (this.good_cell_photo_two == null) {
            this.good_cell_photo_two = (ImageView) findViewById(R.id.new_gooditem_photo2);
        }
        if (this.good_cell_price_one == null) {
            this.good_cell_price_one = (TextView) findViewById(R.id.new_shop_price1);
        }
        if (this.good_cell_price_two == null) {
            this.good_cell_price_two = (TextView) findViewById(R.id.new_shop_price2);
        }
        if (this.good_info_one == null) {
            this.good_info_one = (TextView) findViewById(R.id.new_good_info1);
        }
        if (this.good_info_two == null) {
            this.good_info_two = (TextView) findViewById(R.id.new_good_info2);
        }
        if (this.fl_hot_left == null) {
            this.fl_hot_left = (FrameLayout) findViewById(R.id.fl_hot_left);
        }
        if (this.fl_hot_right == null) {
            this.fl_hot_right = (FrameLayout) findViewById(R.id.fl_hot_right);
        }
        if (this.myhotcell2 == null) {
            this.myhotcell2 = (LinearLayout) findViewById(R.id.home_myhotcell2);
        }
    }
}
