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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsAttrItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsBannerItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsBrandItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsDescItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsEvaluateItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsExplainItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsFaatItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsNameItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsPriceItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsShipFreeItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsShipItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsSpecificationsItem;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.goods.Evaluates;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttr;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsBanner;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsBrand;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDesc;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsEvaluate;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsExplain;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsFaat;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsPrice;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsShip;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsShipFree;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsSpecification;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsSpecifications;
import com.vcvb.chenyu.shop.javaBean.goods.Probs;
import com.vcvb.chenyu.shop.overrideView.ShopGridLayoutManager;
import com.vcvb.chenyu.shop.overrideView.ShopRecyclerView;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailActivity extends GoodsActivity {
    int pos = 0;
    private View child1;
    private Receiver receiver;

    private ShopRecyclerView goodsDatail;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private GoodsDetail goodsDetails = new GoodsDetail();
    private ShopGridLayoutManager gridLayoutManager;

    public GoodsDetailActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
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

        final PopWin popWindow = new PopWin(GoodsDetailActivity.this, ToolUtils.dip2px(this, 156)
                , ToolUtils.dip2px(this, 148));
        final ImageView iv2 = (ImageView) findViewById(R.id.more);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.showAsDropDown(iv2);
            }
        });
        popWindow.setClickListener(new PopWin.OnItemClickListener() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
                popWindow.dismiss();
                Intent intent = new Intent(GoodsDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        popWindow.setClickListener1(new PopWin.OnItemClickListener1() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
            }
        });
        popWindow.setClickListener2(new PopWin.OnItemClickListener2() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
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
        goodsDatail = findViewById(R.id.goods_datail);
        goodsDatail.setFlingScale(0.3);
        goodsDatail.setNestedScrollingEnabled(false);
        gridLayoutManager = new ShopGridLayoutManager(this, 1);
        gridLayoutManager.setSpeedRatio(1);
        goodsDatail.setLayoutManager(gridLayoutManager);
//        gridLayoutManager.setRecycleChildrenOnDetach(true);

        ArrayList<GoodsBanner> banners = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            GoodsBanner banner = new GoodsBanner();
            banner.setImagePath(Images.imgUrls[i]);
            banner.setPath(Images.imgUrls[i]);
            banners.add(banner);
        }
        goodsDetails.setBanners(banners);

        GoodsPrice goodsPrice = new GoodsPrice();
        goodsPrice.setPrice("$454");
        goodsPrice.setMarket("$999");
        ArrayList<String> goodsTips = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            goodsTips.add("tip" + i);
        }
        goodsPrice.setGoodsTips(goodsTips);
        goodsDetails.setGoodsPrice(goodsPrice);

        goodsDetails.setGoodsName("五超人物|美女国际级裁判:不忘初心 坚持自己所爱");
        goodsDetails.setGoodsDesc("国际级裁判员——纪双稿件来源：五人制足球2018" +
                "世界大学生五人制足球锦标赛正在哈萨克斯坦阿拉木图火热进行中，欣赏精彩绝伦的小哥哥小姐姐比赛的同时，让我们把目光也转向场边辛勤执法比赛的裁判员");

        ArrayList<GoodsFaat> faats = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            GoodsFaat faat = new GoodsFaat();
            faat.setType("dfaf");
            faat.setInfo("dfafdfafdafdf");
            faats.add(faat);
        }
        goodsDetails.setFaats(faats);

        ArrayList<GoodsAttr> attrs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            GoodsAttr attr = new GoodsAttr();
            attr.setAttrId(i);
            attr.setAttrName("xx" + i);
            attrs.add(attr);
        }
        goodsDetails.setAttrs(attrs);

        GoodsShip goodsShip = new GoodsShip();
        goodsShip.setAddress("daffadfasdfadsfaffd");
        goodsShip.setFrom("1231");
        goodsShip.setTo("13");
        goodsShip.setEnd("fds");
        goodsDetails.setGoodsShip(goodsShip);

        GoodsShipFree goodsShipFree = new GoodsShipFree();
        goodsShipFree.setShipName("运费");
        goodsShipFree.setShipFree("$123");
        goodsDetails.setShipFree(goodsShipFree);

        GoodsExplain goodsExplain = new GoodsExplain();
        goodsExplain.setName("说明");
        goodsExplain.setInfo("daffadfasdfadsfaffd|fafafa|dfafa|fafa|da");
        goodsDetails.setGoodsExplain(goodsExplain);

        GoodsEvaluate goodsEvaluate = new GoodsEvaluate();
        ArrayList<Evaluates> evaluates = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Evaluates eval = new Evaluates();
            eval.setLogo("http://pic8.nipic.com/20100705/2457331_121923653886_2.jpg");
            eval.setNickname("ssssssss");
            eval.setEvaluate
                    ("ssssssssdaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffddaffadfasdfadsfaffd");
            eval.setImg_num(i);
            eval.setEva_img_url("http://pic8.nipic.com/20100705/2457331_121923653886_2.jpg");
            evaluates.add(eval);
        }
        goodsEvaluate.setEvaluates(evaluates);

        ArrayList<Probs> probs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Probs prob = new Probs();
            prob.setProblem("xcccc");
            prob.setAnswer_num("9aff");
            probs.add(prob);
        }
        goodsEvaluate.setProbs(probs);

        goodsEvaluate.setPj("商品评价（1213）");
        goodsEvaluate.setHp("好评率 99.9%");
        goodsEvaluate.setZp("正品(725)");
        goodsEvaluate.setJg("实惠(725)");
        goodsEvaluate.setWl("物流快(725)");
        goodsEvaluate.setWdj("问大家（1213）");

        goodsDetails.setGoodsEvaluate(goodsEvaluate);

        ArrayList<GoodsBrand> brands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            GoodsBrand brand = new GoodsBrand();
            brand.setLogo("http://pic8.nipic.com/20100705/2457331_121923653886_2.jpg");
            brand.setName("ssssssss");
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                list.add("abc");
            }
            brand.setGoodsTips(list);
            brand.setPrice("$224");
            brands.add(brand);
        }
        goodsDetails.setGoodsBrands(brands);

//        brandshm.put("brand_goods", brands);
//        brandshm.put("shop", "商品评价");
//        brandshm.put("zz", "好评率");
//        brandshm.put("jj", "正品(725)sds");
//        hm.put("goods_brand", brandshm);
//

        ArrayList<GoodsSpecification> specifications = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            GoodsSpecification specification = new GoodsSpecification();
            specification.setTitle("大发发达的说法");
            specification.setContent("ssssssss");
            specifications.add(specification);
        }
        GoodsSpecifications goodsSpecifications = new GoodsSpecifications();
        goodsSpecifications.setGoodsSpecifications(specifications);
        goodsSpecifications.setHeaderLogo("http://pic8.nipic" +
                ".com/20100705/2457331_121923653886_2.jpg");
        goodsDetails.setSpecifications(goodsSpecifications);
        ArrayList<GoodsDesc> descs = new ArrayList<>();
        for (int i = 0; i < Images.imageUrls.length; i++) {
            GoodsDesc desc = new GoodsDesc();
            desc.setGoodsDescImg(Images.imageUrls[i]);
            descs.add(desc);
        }
        goodsDetails.setDescs(descs);

        goodsDatail.setItemAnimator(new DefaultItemAnimator());
        goodsDatail.setAdapter(mAdapter);
        mAdapter.addAll(getItems(goodsDetails));
    }

    protected List<Item> getItems(GoodsDetail goodsDetails) {
        List<Item> cells = new ArrayList<>();
        if (goodsDetails.getBanners() != null && goodsDetails.getBanners().size() > 0) {
            cells.add(new GoodsBannerItem(goodsDetails.getBanners(), context));
        }
        if (goodsDetails.getGoodsPrice() != null) {
            cells.add(new GoodsPriceItem(goodsDetails.getGoodsPrice(), context));
        }
        if (goodsDetails.getGoodsName() != null && goodsDetails.getGoodsName() != "") {
            cells.add(new GoodsNameItem(goodsDetails, context));
        }
        if (goodsDetails.getFaats() != null && goodsDetails.getFaats().size() > 0) {
            cells.add(new GoodsFaatItem(goodsDetails.getFaats(), context));
        }
        if (goodsDetails.getAttrs() != null && goodsDetails.getAttrs().size() > 0) {
            cells.add(new GoodsAttrItem(goodsDetails.getAttrs(), context));
        }
        if (goodsDetails.getGoodsShip() != null) {
            cells.add(new GoodsShipItem(goodsDetails.getGoodsShip(), context));
        }
        if (goodsDetails.getShipFree() != null) {
            cells.add(new GoodsShipFreeItem(goodsDetails.getShipFree(), context));
        }
        if (goodsDetails.getGoodsExplain() != null) {
            cells.add(new GoodsExplainItem(goodsDetails.getGoodsExplain(), context));
        }
        if (goodsDetails.getGoodsEvaluate() != null) {
            cells.add(new GoodsEvaluateItem(goodsDetails.getGoodsEvaluate(), context));
        }
        if (goodsDetails.getGoodsBrands() != null && goodsDetails.getGoodsBrands().size() > 0) {
            cells.add(new GoodsBrandItem(goodsDetails.getGoodsBrands(), context));
        }
        if (goodsDetails.getSpecifications() != null) {
            cells.add(new GoodsSpecificationsItem(goodsDetails.getSpecifications(), context));
        }
        if (goodsDetails.getDescs() != null && goodsDetails.getDescs().size() > 0) {
            for (int i = 0; i < goodsDetails.getDescs().size(); i++) {
                cells.add(new GoodsDescItem(goodsDetails.getDescs().get(i), context));
            }
        }
        return cells;
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
