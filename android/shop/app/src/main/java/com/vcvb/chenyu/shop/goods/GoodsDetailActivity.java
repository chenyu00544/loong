package com.vcvb.chenyu.shop.goods;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.GoodsDetailViewAdapter;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.overrideView.ShopGridLayoutManager;
import com.vcvb.chenyu.shop.overrideView.ShopRecyclerView;
import com.vcvb.chenyu.shop.receiver.Receiver;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDetailActivity extends GoodsActivity {
    private ShopRecyclerView goodsDatail;
    private GoodsDetailViewAdapter goodsDatailAdapter;
    private ShopGridLayoutManager gridLayoutManager;
    int pos = 0;
    private View child1;
    private Receiver receiver;

    public GoodsDetailActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        changeStatusBarTextColor(true);
        setNavBack();
//        bindData();
        initView();
        initListener();
    }

    //在这个方法内才能获取正确的距离宽高参数
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        eY = findViewById(R.id.goods_evaluate).getTop();
//        eI = findViewById(R.id.goods_image_text_info).getTop();
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        goodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsView.setTextSize(ts_22);
                goodsView.setTextColor(Color.parseColor("#000000"));
                goodsEvaluate.setTextSize(ts_18);
                goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                goodsInfo.setTextSize(ts_18);
                goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                pos = 0;
                goodsDatail.scrollToPosition(0);
            }
        });
        goodsEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsView.setTextSize(ts_18);
                goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                goodsInfo.setTextSize(ts_18);
                goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                goodsEvaluate.setTextSize(ts_22);
                goodsEvaluate.setTextColor(Color.parseColor("#000000"));
                goodsDatail.scrollToPosition(8);
            }
        });
        goodsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsInfo.setTextSize(ts_22);
                goodsInfo.setTextColor(Color.parseColor("#000000"));
                goodsView.setTextSize(ts_18);
                goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                goodsEvaluate.setTextSize(ts_18);
                goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                goodsDatail.scrollToPosition(10);
            }
        });
    }

    public void initListener() {
        final LinearLayout nav_wrap = nav_bar;
        final LinearLayout title_wrap = nav_bar.findViewById(R.id.title_wrap);
        final LinearLayout.LayoutParams layoutParams_d = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, 0);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //滑动监听
        goodsDatail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int alpha = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0: //不滚动
                        break;
                    case 1: // 按着手指滚动
                        break;
                    case 2: // 不按着手指滚动
                        break;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                child1 = gridLayoutManager.getChildAt(0);
                if (goodsDatail.getChildAdapterPosition(child1) == 0) {
                    goodsView.setTextSize(ts_22);
                    goodsView.setTextColor(Color.parseColor("#000000"));
                    goodsEvaluate.setTextSize(ts_18);
                    goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsInfo.setTextSize(ts_18);
                    goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                } else if (goodsDatail.getChildAdapterPosition(child1) == 8) {
                    goodsView.setTextSize(ts_18);
                    goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsInfo.setTextSize(ts_18);
                    goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsEvaluate.setTextSize(ts_22);
                    goodsEvaluate.setTextColor(Color.parseColor("#000000"));
                } else if (goodsDatail.getChildAdapterPosition(child1) == 10) {
                    goodsInfo.setTextSize(ts_22);
                    goodsInfo.setTextColor(Color.parseColor("#000000"));
                    goodsView.setTextSize(ts_18);
                    goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsEvaluate.setTextSize(ts_18);
                    goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                }
//                System.out.println(dy);
                pos -= dy;
                if (-pos < 255) {
                    alpha = -pos;
                    title_wrap.setAlpha(alpha);
                    if (-pos < 10) {
                        if (title_wrap.getHeight() > 0) {
                            title_wrap.setLayoutParams(layoutParams_d);
                        }
                    } else {
                        if (title_wrap.getHeight() <= 0) {
                            title_wrap.setLayoutParams(layoutParams);
                        }
                    }
                    nav_wrap.setBackgroundColor(Color.argb(alpha, 238, 238, 238));
                } else {
                    if (alpha < 255) {
                        alpha = 255;
                        title_wrap.setAlpha(1);
                        nav_wrap.setBackgroundColor(Color.argb(alpha, 238, 238, 238));
                    }
                }
            }
        });
    }

    public void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        goodsDatail = (ShopRecyclerView) findViewById(R.id.goods_datail);
        goodsDatail.setFlingScale(0.3);
        goodsDatail.setNestedScrollingEnabled(false);
        gridLayoutManager = new ShopGridLayoutManager(this, 1);
        gridLayoutManager.setSpeedRatio(1);
        goodsDatail.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setRecycleChildrenOnDetach(true);

        HashMap hm = new HashMap();
        ArrayList<HashMap> imgUrls = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            HashMap bannerhm = new HashMap();
            bannerhm.put("url", Images.imgUrls[i]);
            imgUrls.add(bannerhm);
        }
        hm.put("banner", imgUrls);

        HashMap pricehm = new HashMap();
        pricehm.put("price", "$454");
        pricehm.put("market", "$999");
        pricehm.put("goods_tip", "1");
        hm.put("price", pricehm);

        HashMap namehm = new HashMap();
        namehm.put("name", "五超人物|美女国际级裁判:不忘初心 坚持自己所爱");
        namehm.put("desc",
                "国际级裁判员——纪双稿件来源：五人制足球2018" +
                        "世界大学生五人制足球锦标赛正在哈萨克斯坦阿拉木图火热进行中，欣赏精彩绝伦的小哥哥小姐姐比赛的同时，让我们把目光也转向场边辛勤执法比赛的裁判员");
        hm.put("goods_name", namehm);

        ArrayList<HashMap> faatList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            HashMap faathm = new HashMap();
            faathm.put("type", "dfaf");
            faathm.put("info", "dfafdfafdafdf");
            faatList.add(faathm);
        }
        hm.put("goods_faat", faatList);

        hm.put("goods_attr", new HashMap());
        HashMap shiphm = new HashMap();
        shiphm.put("address", "daffadfasdfadsfaffd");
        shiphm.put("from", "1231");
        shiphm.put("to", "435");
        shiphm.put("end", "657");
        hm.put("goods_ship", shiphm);

        HashMap shipfreehm = new HashMap();
        shipfreehm.put("name", "运费");
        shipfreehm.put("free", "$123");
        hm.put("goods_shipfree", shipfreehm);

        HashMap explainhm = new HashMap();
        explainhm.put("name", "说明");
        explainhm.put("explain", "daffadfasdfadsfaffd|fafafa|dfafa|fafa|daf");
        hm.put("goods_explain", explainhm);

        HashMap evaluatehm = new HashMap();
        ArrayList<HashMap> evaluates = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap evalhm = new HashMap();
            evalhm.put("logo", "http://pic8.nipic.com/20100705/2457331_121923653886_2.jpg");
            evalhm.put("nickname", "ssssssss");
            evalhm.put("evaluate",
                    "ssssssssdaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffd");
            evalhm.put("img_num", "4");
            evalhm.put("eva_img_url", "http://pic8.nipic.com/20100705/2457331_121923653886_2.jpg");
            evaluates.add(evalhm);
        }

        ArrayList<HashMap> probs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            HashMap probhm = new HashMap();
            probhm.put("prob", "xcccc");
            probhm.put("num", "9aff");
            probs.add(probhm);
        }
        evaluatehm.put("evaluates", evaluates);
        evaluatehm.put("problem", probs);
        evaluatehm.put("pj", "商品评价（1213）");
        evaluatehm.put("hp", "好评率 99.9%");
        evaluatehm.put("zp", "正品(725)");
        evaluatehm.put("jg", "实惠(725)");
        evaluatehm.put("wl", "物流快(725)");
        evaluatehm.put("wdj", "问大家（1213）");
        hm.put("goods_evaluate", evaluatehm);

        HashMap brandshm = new HashMap();
        ArrayList<HashMap> brands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap brandhm = new HashMap();
            brandhm.put("logo", "http://pic8.nipic.com/20100705/2457331_121923653886_2.jpg");
            brandhm.put("name", "ssssssss");
            brandhm.put("tag", "ssss");
            brandhm.put("price", "$224");
            brands.add(brandhm);
        }

        brandshm.put("brand_goods", brands);
        brandshm.put("shop", "商品评价");
        brandshm.put("zz", "好评率");
        brandshm.put("jj", "正品(725)sds");
        hm.put("goods_brand", brandshm);

        ArrayList<HashMap> attrs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            HashMap attrhm = new HashMap();
            attrhm.put("title", "大发发达的说法");
            attrhm.put("content", "ssssssss");
            attrs.add(attrhm);
        }
        hm.put("goods_info", attrs);
        hm.put("goods_desc", "<div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/785/554/9114455587_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/093/074/9114470390_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/833/374/9114473338_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/070/225/9116522070_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/615/164/9114461516_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/649/179/9095971946_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/548/779/9095977845_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/156/944/9114449651_1171374532.jpg\"></div></div><div class=\"section s-img\"><div class=\"img\"><img src=\"https://cbu01.alicdn.com/img/ibank/2018/286/100/9096001682_1171374532.jpg\"></div></div>\n");

        //初始化适配器
        RecyclerView.RecycledViewPool pool = goodsDatail.getRecycledViewPool();
        goodsDatailAdapter = new GoodsDetailViewAdapter(hm, width, pool, this);
        goodsDatail.setItemAnimator(new DefaultItemAnimator());
        goodsDatail.setAdapter(goodsDatailAdapter);
        pool.setMaxRecycledViews(9, 1);
        pool.putRecycledView(goodsDatail.getAdapter().createViewHolder(goodsDatail, 9));
        pool.setMaxRecycledViews(10, 1);
        pool.putRecycledView(goodsDatail.getAdapter().createViewHolder(goodsDatail, 10));
        pool.setMaxRecycledViews(12, 1);
        pool.putRecycledView(goodsDatail.getAdapter().createViewHolder(goodsDatail, 12));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("BannerClick");
        intentFilter.addAction("FaatClick");
        intentFilter.addAction("AttrClick");
        intentFilter.addAction("ShipClick");
        intentFilter.addAction("ShipFreeClick");
        intentFilter.addAction("ExplainClick");
        intentFilter.addAction("EvaluateClick");
        intentFilter.addAction("EvaluateTag1Click");
        intentFilter.addAction("EvaluateTag2Click");
        intentFilter.addAction("EvaluateTag3Click");
        intentFilter.addAction("EvaluateUserClick");
        intentFilter.addAction("ProblemClick");
        intentFilter.addAction("AnswerClick");
        intentFilter.addAction("BrandClick");
        intentFilter.addAction("BrandGoodsClick");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "BannerClick":
                        int pos = intent.getIntExtra("pos", 0);
                        break;
                    case "FaatClick":
                        break;
                    case "AttrClick":
                        break;
                    case "ShipClick":
                        break;
                    case "ShipFreeClick":
                        break;
                    case "ExplainClick":
                        break;
                    case "EvaluateClick":
                        break;
                    case "EvaluateTag1Click":
                        break;
                    case "EvaluateTag2Click":
                        break;
                    case "EvaluateTag3Click":
                        break;
                    case "EvaluateUserClick":
                        break;
                    case "ProblemClick":
                        break;
                    case "AnswerClick":
                        break;
                    case "BrandClick":
                        break;
                    case "BrandGoodsClick":
                        break;
                    default:
                        break;
                }
            }
        };

        broadcastManager.registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        setContentView(R.layout.view_null);
        super.onDestroy();
    }
}
