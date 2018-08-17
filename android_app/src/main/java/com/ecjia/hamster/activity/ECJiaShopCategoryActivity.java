package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaFlowLayout;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaShopCategoryActivity extends a {

    class ECJiaShopCategoryActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShopCategoryActivity a;

        ECJiaShopCategoryActivity_1(ECJiaShopCategoryActivity eCJiaShopCategoryActivity) {
            this.a = eCJiaShopCategoryActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shopcartegory);
        b();
    }

    private void b() {
        findViewById(R.id.shopcategory_close).setOnClickListener(new ECJiaShopCategoryActivity_1(this));
        c();
    }

    private void c() {
        final ECJiaFlowLayout eCJiaFlowLayout = (ECJiaFlowLayout) findViewById(R.id.flowlayout);
        ArrayList stringArrayListExtra = getIntent().getStringArrayListExtra("seller_category");
        int intExtra = getIntent().getIntExtra("position", 0);
        int d = (d() - (((int) getResources().getDimension(R.dimen.dp_10)) * 6)) / 3;
        for (int i = 0; i < stringArrayListExtra.size(); i++) {
            final TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_shop_category, null);
            textView.setTextColor(getResources().getColor(R.color.my_dark));
            textView.setGravity(17);
            LayoutParams marginLayoutParams = new MarginLayoutParams(d, getResources().getDimensionPixelSize(R.dimen.dp_40));
            marginLayoutParams.setMargins((int) getResources().getDimension(R.dimen.dp_10), (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
            textView.setLayoutParams(marginLayoutParams);
            eCJiaFlowLayout.addView(textView, marginLayoutParams);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaShopCategoryActivity d;

                public void onClick(View view) {
                    for (int i = 0; i < eCJiaFlowLayout.getChildCount(); i++) {
                        ((TextView) eCJiaFlowLayout.getChildAt(i)).setTextColor(this.d.getResources().getColor(R.color.my_dark));
                    }
                    textView.setTextColor(this.d.getResources().getColor(R.color.public_theme_color_normal));
                    Intent intent = new Intent();
                    intent.putExtra("position", i);
                    this.d.setResult(-1, intent);
                    this.d.finish();
                }
            });
            if (intExtra == i) {
                textView.setTextColor(getResources().getColor(R.color.public_theme_color_normal));
            }
            textView.setText((CharSequence) stringArrayListExtra.get(i));
        }
    }
}
