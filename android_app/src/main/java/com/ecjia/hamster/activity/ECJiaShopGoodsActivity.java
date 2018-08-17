package com.ecjia.hamster.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.hamster.adapter.bo;
import com.ecjia.hamster.model.ECJia_GOODS_LIST;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;

public class ECJiaShopGoodsActivity extends a {
    Resources a;
    private ArrayList<ECJia_GOODS_LIST> b;
    private TextView c;
    private ImageView d;
    private ListView e;
    private bo k;
    private boolean l = false;

    class ECJiaShopGoodsActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShopGoodsActivity a;

        ECJiaShopGoodsActivity_1(ECJiaShopGoodsActivity eCJiaShopGoodsActivity) {
            this.a = eCJiaShopGoodsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shop_goods);
        PushAgent.getInstance(this).onAppStart();
        this.b = (ArrayList) getIntent().getSerializableExtra("goods_list");
        this.l = getIntent().getBooleanExtra("is_order", false);
        this.a = getBaseContext().getResources();
        this.c = (TextView) findViewById(R.id.top_view_text);
        this.c.setText(this.a.getString(R.string.shopgoods));
        this.e = (ListView) findViewById(R.id.shop_goods);
        this.d = (ImageView) findViewById(R.id.top_view_back);
        this.d.setOnClickListener(new ECJiaShopGoodsActivity_1(this));
        this.k = new bo(this, this.b, this.l);
        this.e.setAdapter(this.k);
    }
}
