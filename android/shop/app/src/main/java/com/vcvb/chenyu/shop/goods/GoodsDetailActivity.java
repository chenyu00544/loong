package com.vcvb.chenyu.shop.goods;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.BrandGoodsListViewAdapter;
import com.vcvb.chenyu.shop.adapter.EvaluateListViewAdapter;
import com.vcvb.chenyu.shop.adapter.GoodsDetailViewAdapter;
import com.vcvb.chenyu.shop.image.Images;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDetailActivity extends GoodsActivity {
    private Banner banner;
    private RecyclerView goodsDatail;
    private GoodsDetailViewAdapter goodsDatailAdapter;
    private TextView goods_price;
    private TextView market_price;
    int pos = 0;
    int eY;
    int eI;

    private RecyclerView bgs;
    private BrandGoodsListViewAdapter bglva;

    private RecyclerView eva;
    private EvaluateListViewAdapter evaa;

    public GoodsDetailActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        long sm = System.currentTimeMillis();
        System.out.println(sm);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        setNavBack();
//        bindData();
//        initView();
        initView2();
        initListener();
        long em = System.currentTimeMillis();
        System.out.println(em);
        System.out.println(em - sm);
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
                goodsDatail.scrollTo(0, 0);
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
                goodsDatail.scrollTo(0, eY);
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
                goodsDatail.scrollTo(0, eI);
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
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                pos = getDistance();
                if (pos < 255) {
                    alpha = pos;
                    title_wrap.setAlpha(alpha);
                    if (pos < 10) {
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
//        LinearLayout.LayoutParams gasp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
//                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        gasp.setMargins(0, 24, 0, 0);
//
//        LinearLayout.LayoutParams gasp_1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
//                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams gasp_2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
//                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        gasp_2.setMargins(24, 12, 0, 12);
//
//        LinearLayout goods_attr_ship_1 = new LinearLayout(this);
//
//        LinearLayout goods_all_wrap = (LinearLayout) findViewById(R.id.goods_all_wrap);
//
//
//        //属性运费
//        LinearLayout goods_attr_ship = new LinearLayout(this);
//        goods_attr_ship.setOrientation(LinearLayout.VERTICAL);
//        goods_attr_ship.setLayoutParams(gasp);
//
//        ConstraintLayout faat_select = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_attr_ship_item_2, null);
//        goods_attr_ship.addView(faat_select);
//        TextView faat_e = faat_select.findViewById(R.id.textView2);
//        faat_e.setText("[阿发]");
//        TextView faat_e_1 = faat_select.findViewById(R.id.textView1);
//        faat_e_1.setText("啊啊发的发发呆");
//        TextView faat_e_2 = faat_select.findViewById(R.id.textView4);
//        faat_e_2.setText("[发给]");
//        TextView attr_e_3 = faat_select.findViewById(R.id.textView5);
//        attr_e_3.setText("阿发送到发送到发");
//        TextView attr_4 = faat_select.findViewById(R.id.textView6);
//        attr_4.setText("更多");
//
//        goods_attr_ship_1.setOrientation(LinearLayout.VERTICAL);
//        goods_attr_ship_1.setLayoutParams(gasp_1);
//        ConstraintLayout attr_select = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_attr_ship_item_default, null);
//        goods_attr_ship_1.addView(attr_select);
//        TextView attr_e = attr_select.findViewById(R.id.textView3);
//        attr_e.setText("选择");
//        TextView attr_e_c = attr_select.findViewById(R.id.textView1);
//        attr_e_c.setText("说法是否");
//
//        LinearLayout goods_attr_ship_2 = new LinearLayout(this);
//        goods_attr_ship_2.setOrientation(LinearLayout.VERTICAL);
//        goods_attr_ship_2.setLayoutParams(gasp_1);
//        ConstraintLayout ship_select = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_attr_ship_item_1, null);
//        goods_attr_ship_2.addView(ship_select);
//
//        LinearLayout goods_attr_ship_3 = new LinearLayout(this);
//        goods_attr_ship_3.setOrientation(LinearLayout.VERTICAL);
//        goods_attr_ship_3.setLayoutParams(gasp_1);
//        ConstraintLayout ship_free_select = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_attr_ship_item_default, null);
//        goods_attr_ship_3.addView(ship_free_select);
//        TextView ship_free = ship_free_select.findViewById(R.id.textView3);
//        ship_free.setText("运费");
//        TextView ship_free_c = ship_free_select.findViewById(R.id.textView1);
//        ship_free_c.setText("10元");
//
//        LinearLayout goods_attr_ship_4 = new LinearLayout(this);
//        goods_attr_ship_4.setOrientation(LinearLayout.VERTICAL);
//        goods_attr_ship_4.setLayoutParams(gasp_1);
//        ConstraintLayout explain_select = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_attr_ship_item_default, null);
//        goods_attr_ship_4.addView(explain_select);
//        TextView ship_e = explain_select.findViewById(R.id.textView3);
//        ship_e.setText("说明");
//        TextView ship_e_c = explain_select.findViewById(R.id.textView1);
//        ship_e_c.setText("dsfasdfasdfasdfasdfads");
//
//
//        //评论
//        LinearLayout goods_evaluate = new LinearLayout(this);
//        goods_evaluate.setOrientation(LinearLayout.VERTICAL);
//        goods_evaluate.setLayoutParams(gasp);
//        goods_evaluate.setBackgroundColor(Color.parseColor("#FFFFFF"));
//
//        ConstraintLayout goods_evaluate_title = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_title_item_default, null);
//        goods_evaluate.addView(goods_evaluate_title);
//        TextView evaluate_title = goods_evaluate_title.findViewById(R.id.textView);
//        evaluate_title.setText("评论");
//        TextView evaluate_title_comment_rate = goods_evaluate_title.findViewById(R.id.textView2);
//        evaluate_title_comment_rate.setText("好评率 98.5%");
//        ConstraintLayout goods_evaluate_title_c = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_evaluate_tag_item, null);
//        TextView evaluate_tag_1 = goods_evaluate_title_c.findViewById(R.id.textView13);
//        evaluate_tag_1.setText("是正品(725)");
//        TextView evaluate_tag_2 = goods_evaluate_title_c.findViewById(R.id.textView16);
//        evaluate_tag_2.setText("价格实惠(725)");
//        TextView evaluate_tag_3 = goods_evaluate_title_c.findViewById(R.id.textView17);
//        evaluate_tag_3.setText("物流快(725)");
//        goods_evaluate.addView(goods_evaluate_title_c);
//
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(2);
//        list.add(2);
//        list.add(2);
//        eva = new RecyclerView(this);
//        eva.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        eva.setLayoutParams(gasp_2);
//        LinearLayoutManager mse = new LinearLayoutManager(this);
//        eva.addItemDecoration(new SpacesItemDecoration(10));
//        mse.setOrientation(LinearLayoutManager.HORIZONTAL);
//        eva.setLayoutManager(mse);
//        evaa = new EvaluateListViewAdapter(this, list);
//        eva.setAdapter(evaa);
//        goods_evaluate.addView(eva);
//
//        ConstraintLayout problem_title = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_title_item_default, null);
//        TextView problem_title_c = problem_title.findViewById(R.id.textView);
//        problem_title_c.setText("问问题");
//        goods_evaluate.addView(problem_title);
//
//        ConstraintLayout problem_content = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_problem_content, null);
//        TextView problem_title_c_1 = problem_content.findViewById(R.id.textView1);
//        problem_title_c_1.setText("即调用ViewGroup的addView");
//        TextView problem_title_c_2 = problem_content.findViewById(R.id.textView2);
//        problem_title_c_2.setText("9个回答");
//        TextView problem_title_c_3 = problem_content.findViewById(R.id.textView3);
//        problem_title_c_3.setText("LinearLayout布局控件");
//        TextView problem_title_c_4 = problem_content.findViewById(R.id.textView4);
//        problem_title_c_4.setText("45个回答");
//        goods_evaluate.addView(problem_content);
//
//        LinearLayout goods_brand = new LinearLayout(this);
//        goods_brand.setOrientation(LinearLayout.VERTICAL);
//        goods_brand.setLayoutParams(gasp);
//        goods_brand.setBackgroundColor(Color.parseColor("#FFFFFF"));
//
//        ConstraintLayout goods_brand_title = (ConstraintLayout)
//                LayoutInflater.from(this).inflate(R.layout.goods_title_item_brand, null);
//        goods_brand.addView(goods_brand_title);
//
//        list.add(2);
//        list.add(2);
//        list.add(2);
//        list.add(2);
//        bgs = new RecyclerView(this);
//        bgs.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        bgs.setLayoutParams(gasp_2);
//        LinearLayoutManager ms = new LinearLayoutManager(this);
//        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
//        bgs.setLayoutManager(ms);
//        bglva = new BrandGoodsListViewAdapter(this, list);
//        bgs.setAdapter(bglva);
//        goods_brand.addView(bgs);
//
//        goods_all_wrap.addView(goods_attr_ship);
//        goods_all_wrap.addView(goods_attr_ship_1);
//        goods_all_wrap.addView(goods_attr_ship_2);
//        goods_all_wrap.addView(goods_attr_ship_3);
//        goods_all_wrap.addView(goods_attr_ship_4);
//        goods_all_wrap.addView(goods_evaluate);
//        goods_all_wrap.addView(goods_brand);
    }

    public void initView2() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        goodsDatail = (RecyclerView) findViewById(R.id.goods_datail);
        goodsDatail.setLayoutManager(new GridLayoutManager(this, 6));
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
        pricehm.put("name", "五超人物|美女国际级裁判:不忘初心 坚持自己所爱");
        pricehm.put("desc", "国际级裁判员——纪双稿件来源：五人制足球2018世界大学生五人制足球锦标赛正在哈萨克斯坦阿拉木图火热进行中，欣赏精彩绝伦的小哥哥小姐姐比赛的同时，让我们把目光也转向场边辛勤执法比赛的裁判员");
        hm.put("goods_name", namehm);

        //初始化适配器
        goodsDatailAdapter = new GoodsDetailViewAdapter(hm, width, this);
        goodsDatail.setItemAnimator(new DefaultItemAnimator());
        goodsDatail.setAdapter(goodsDatailAdapter);
    }

    @Override
    protected void onDestroy() {
        setContentView(R.layout.view_null);
        super.onDestroy();
    }

    private int getDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) goodsDatail.getLayoutManager();
        View firstVisibItem = goodsDatail.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemCount = layoutManager.getItemCount();
        int recycleViewHeight = goodsDatail.getHeight();
        int itemHeight = firstVisibItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem);
        return (firstItemPosition + 1) * itemHeight - firstItemBottom;
    }
}
