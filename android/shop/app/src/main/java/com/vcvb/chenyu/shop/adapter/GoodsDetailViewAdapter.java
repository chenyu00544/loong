package com.vcvb.chenyu.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.vcvb.chenyu.shop.overrideView.ItemWebView;
import com.vcvb.chenyu.shop.tools.ReflexUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDetailViewAdapter extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> {
    private Context context;
    private HashMap hm;
    private RecyclerView.RecycledViewPool pool;
    private int width;
    final private int EMPTY_VIEW = 0;
    final private int PROGRESS_VIEW = 99;
    final private int BANNER_VIEW = 1;
    final private int PRICE_VIEW = 2;
    final private int NAME_VIEW = 3;
    final private int FAAT_VIEW = 4;
    final private int ATTR_VIEW = 5;
    final private int SHIP_VIEW = 6;
    final private int SHIPFREE_VIEW = 7;
    final private int EXPLAIN_VIEW = 8;
    final private int EVALUATE_VIEW = 9;
    final private int BRAND_VIEW = 10;
    final private int INFO_VIEW = 11;
    final private int IMG_INFO_VIEW = 12;

    public GoodsDetailViewAdapter(HashMap hm, int width, RecyclerView.RecycledViewPool pool,
                                  Context context) {
        this.context = context;
        this.width = width;
        this.hm = hm;
        this.pool = pool;
    }

    @Override
    public int getItemCount() {
        return hm.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hm.size() == 0) {
            return EMPTY_VIEW;
        } else {
            int type = EMPTY_VIEW;
            switch (position) {
                case 0:
                    if (hm.get("banner") != null) {
                        type = BANNER_VIEW;
                    }
                    break;
                case 1:
                    if (hm.get("price") != null) {
                        type = PRICE_VIEW;
                    }
                    break;
                case 2:
                    if (hm.get("goods_name") != null) {
                        type = NAME_VIEW;
                    }
                    break;
                case 3:
                    if (hm.get("goods_faat") != null) {
                        type = FAAT_VIEW;
                    }
                    break;
                case 4:
                    if (hm.get("goods_attr") != null) {
                        type = ATTR_VIEW;
                    }
                    break;
                case 5:
                    if (hm.get("goods_ship") != null) {
                        type = SHIP_VIEW;
                    }
                    break;
                case 6:
                    if (hm.get("goods_shipfree") != null) {
                        type = SHIPFREE_VIEW;
                    }
                    break;
                case 7:
                    if (hm.get("goods_explain") != null) {
                        type = EXPLAIN_VIEW;
                    }
                    break;
                case 8:
                    if (hm.get("goods_evaluate") != null) {
                        type = EVALUATE_VIEW;
                    }
                    break;
                case 9:
                    if (hm.get("goods_brand") != null) {
                        type = BRAND_VIEW;
                    }
                    break;
                case 10:
                    if (hm.get("goods_info") != null) {
                        type = INFO_VIEW;
                    }
                    break;
                case 11:
                    if (hm.get("goods_desc") != null) {
                        type = IMG_INFO_VIEW;
                    }
                    break;
                default:
                    type = EMPTY_VIEW;
            }
            return type;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_slide_item,
                    parent, false);
            return new GoodsDetailViewAdapter.BannerViewHolder(view);
        } else if (viewType == PRICE_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                            .goods_price_info_item,
                    parent, false);
            return new GoodsDetailViewAdapter.PriceInfoHolder(view);
        } else if (viewType == NAME_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_name_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsNameHolder(view);
        } else if (viewType == FAAT_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_faat_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsFaatHolder(view);
        } else if (viewType == ATTR_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_attr_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsAttrHolder(view);
        } else if (viewType == SHIP_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_ship_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsShipHolder(view);
        } else if (viewType == SHIPFREE_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                            .goods_attr_ship_item_default,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsShipFreeHolder(view);
        } else if (viewType == EXPLAIN_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                            .goods_attr_ship_item_default,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsExplainHolder(view);
        } else if (viewType == EVALUATE_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_evaluate_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsEvaluateHolder(view, (HashMap) hm.get
                    ("goods_evaluate"));
        } else if (viewType == BRAND_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_brand_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsBrandHolder(view, (HashMap) hm.get
                    ("goods_brand"));
        } else if (viewType == INFO_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                            .goods_info_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsInfoHolder(view, (ArrayList<HashMap>) hm.get
                    ("goods_info"));
        } else if (viewType == IMG_INFO_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                            .goods_desc_item,
                    parent, false);
            return new GoodsDetailViewAdapter.GoodsDescHolder(view, (String) hm.get
                    ("goods_desc"));
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_null,
                    parent, false);
            return new GoodsDetailViewAdapter.ViewNullHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            ArrayList<HashMap> imageMp = (ArrayList<HashMap>) hm.get("banner");
            ArrayList<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < imageMp.size(); i++) {
                imageUrls.add((String) imageMp.get(i).get("url"));
            }
            bannerViewHolder.banner.setImages(imageUrls);
            //设置轮播图的标题集合
            bannerViewHolder.banner.start();
        } else if (holder instanceof PriceInfoHolder) {
            PriceInfoHolder priceInfoHolder = (PriceInfoHolder) holder;
            HashMap pMp = (HashMap) hm.get("price");
            priceInfoHolder.goodsPrice.setText((CharSequence) pMp.get("price"));
            priceInfoHolder.goodsMarket.setText((CharSequence) pMp.get("market"));
            if (pMp.get("goods_tip") == "1") {
                priceInfoHolder.goodsTip.setAlpha(1);
            }
        } else if (holder instanceof GoodsNameHolder) {
            GoodsNameHolder goodsNameHolder = (GoodsNameHolder) holder;
            HashMap nMp = (HashMap) hm.get("goods_name");
            goodsNameHolder.name.setText((CharSequence) nMp.get("name"));
            goodsNameHolder.desc.setText((CharSequence) nMp.get("desc"));
        } else if (holder instanceof GoodsFaatHolder) {
            GoodsFaatHolder goodsFaatHolder = (GoodsFaatHolder) holder;
            ArrayList<HashMap> list = (ArrayList<HashMap>) hm.get("goods_faat");
            for (int i = 0; i < list.size(); i++) {
                switch (i) {
                    case 0:
                        goodsFaatHolder.tv1.setText("[" + list.get(i).get("type") + "]");
                        goodsFaatHolder.tv2.setText("[" + list.get(i).get("info") + "]");
                        break;
                    case 1:
                        goodsFaatHolder.tv3.setText("[" + list.get(i).get("type") + "]");
                        goodsFaatHolder.tv4.setText("[" + list.get(i).get("info") + "]");
                        break;
                }
            }
        } else if (holder instanceof GoodsAttrHolder) {
            GoodsAttrHolder goodsAttrHolder = (GoodsAttrHolder) holder;
        } else if (holder instanceof GoodsShipHolder) {
            GoodsShipHolder goodsShipHolder = (GoodsShipHolder) holder;
            HashMap sMp = (HashMap) hm.get("goods_ship");
            goodsShipHolder.tv1.setText((CharSequence) sMp.get("address"));
            goodsShipHolder.tv2.setText((CharSequence) sMp.get("from"));
            goodsShipHolder.tv3.setText((CharSequence) sMp.get("to"));
            goodsShipHolder.tv4.setText((CharSequence) sMp.get("end"));
        } else if (holder instanceof GoodsShipFreeHolder) {
            GoodsShipFreeHolder goodsShipFreeHolder = (GoodsShipFreeHolder) holder;
            HashMap sfMp = (HashMap) hm.get("goods_shipfree");
            goodsShipFreeHolder.tv1.setText((CharSequence) sfMp.get("name"));
            goodsShipFreeHolder.tv2.setText((CharSequence) sfMp.get("free"));
        } else if (holder instanceof GoodsExplainHolder) {
            GoodsExplainHolder goodsExplainHolder = (GoodsExplainHolder) holder;
            HashMap sfMp = (HashMap) hm.get("goods_explain");
            goodsExplainHolder.tv1.setText((CharSequence) sfMp.get("name"));
            goodsExplainHolder.tv2.setText((CharSequence) sfMp.get("explain"));
        } else if (holder instanceof GoodsEvaluateHolder) {
            GoodsEvaluateHolder goodsEvaluateHolder = (GoodsEvaluateHolder) holder;
        } else if (holder instanceof GoodsBrandHolder) {
            GoodsBrandHolder goodsBrandHolder = (GoodsBrandHolder) holder;
        } else if (holder instanceof GoodsInfoHolder) {
            GoodsInfoHolder goodsInfoHolder = (GoodsInfoHolder) holder;
        } else if (holder instanceof GoodsDescHolder) {
            GoodsDescHolder goodsDescHolder = (GoodsDescHolder) holder;
        }
    }

    //设置列数
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = ((GridLayoutManager) manager);

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        Banner banner;

        BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.goods_slide);
            //设置内置样式，内含六种特效
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置轮播的动画效果，内含多种特效
            banner.setBannerAnimation(Transformer.Default);
            //设置轮播间隔时间
            banner.setDelayTime(5000);
            //设置指示器的位置，小点点，左中右。
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.setImageLoader(new GlideImageLoader());
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent();
                    intent.setAction("BannerClick");
                    intent.putExtra("pos", position);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });
        }
    }

    class PriceInfoHolder extends RecyclerView.ViewHolder {
        private TextView goodsPrice;
        private TextView goodsMarket;
        private TextView goodsTip;

        PriceInfoHolder(View itemView) {
            super(itemView);
            goodsPrice = itemView.findViewById(R.id.textView1);
            goodsMarket = itemView.findViewById(R.id.textView2);
            goodsMarket.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            goodsTip = itemView.findViewById(R.id.textView3);
        }
    }

    class GoodsNameHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView desc;

        GoodsNameHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView18);
            desc = itemView.findViewById(R.id.textView19);
        }
    }

    class GoodsFaatHolder extends RecyclerView.ViewHolder {

        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;

        GoodsFaatHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView2);
            tv2 = itemView.findViewById(R.id.textView1);
            tv3 = itemView.findViewById(R.id.textView4);
            tv4 = itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("FaatClick");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });
        }
    }

    class GoodsAttrHolder extends RecyclerView.ViewHolder {

        private TextView tv1;

        GoodsAttrHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("AttrClick");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });
        }
    }

    class GoodsShipHolder extends RecyclerView.ViewHolder {

        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private ImageView iv1;
        private ImageView iv2;

        GoodsShipHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView1);
            tv2 = itemView.findViewById(R.id.textView7);
            tv3 = itemView.findViewById(R.id.textView21);
            tv4 = itemView.findViewById(R.id.textView8);
            iv1 = itemView.findViewById(R.id.imageView5);
            iv2 = itemView.findViewById(R.id.imageView8);
        }
    }

    class GoodsShipFreeHolder extends RecyclerView.ViewHolder {

        private TextView tv1;
        private TextView tv2;

        GoodsShipFreeHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView3);
            tv2 = itemView.findViewById(R.id.textView1);
        }
    }

    class GoodsExplainHolder extends RecyclerView.ViewHolder {

        private TextView tv1;
        private TextView tv2;

        GoodsExplainHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView3);
            tv2 = itemView.findViewById(R.id.textView1);
        }
    }

    class GoodsEvaluateHolder extends RecyclerView.ViewHolder {

        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private TextView tv6;
        private ImageView iv1;
        private HorizontalScrollView hsv;
        private HashMap mp;

        GoodsEvaluateHolder(View itemView, HashMap hmp) {
            super(itemView);
            ConstraintLayout v = (ConstraintLayout) itemView;
            mp = hmp;
            tv1 = itemView.findViewById(R.id.textView23);
            tv2 = itemView.findViewById(R.id.textView22);
            tv3 = itemView.findViewById(R.id.textView13);
            tv4 = itemView.findViewById(R.id.textView16);
            tv5 = itemView.findViewById(R.id.textView17);
            tv6 = itemView.findViewById(R.id.textView24);
            iv1 = itemView.findViewById(R.id.imageView10);
            hsv = itemView.findViewById(R.id.evaluate_list);
            tv1.setText((CharSequence) mp.get("pj"));
            tv2.setText((CharSequence) mp.get("hp"));
            tv3.setText((CharSequence) mp.get("zp"));
            tv4.setText((CharSequence) mp.get("jg"));
            tv5.setText((CharSequence) mp.get("wl"));
            tv6.setText((CharSequence) mp.get("wdj"));
            ArrayList<HashMap> problem = (ArrayList<HashMap>) mp.get("problem");
            for (int i = 0; i < problem.size(); i++) {
                ImageView imgIcon = new ImageView(context);
                imgIcon.setId(ReflexUtils.getResByRid("id", "prob_icon_" + (i), context));
                v.addView(imgIcon);
                ConstraintLayout.LayoutParams icl1 = new ConstraintLayout.LayoutParams
                        (0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                icl1.width = 40;
                icl1.height = 35;

                imgIcon.setLayoutParams(icl1);
                imgIcon.setBackgroundResource(R.drawable.icon_problem);
                imgIcon.setPadding(8, 8, 8, 8);

                ConstraintSet constraintSetTv1 = new ConstraintSet();
                constraintSetTv1.clone(v);
                if (i == 0) {
                    constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, tv6.getId(),
                            ConstraintSet.BOTTOM, 16);
                    constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, tv6.getId(),
                            ConstraintSet.LEFT, 12);
                } else {
                    int pid = ReflexUtils.getResByRid("id", "prob_icon_" + (i - 1), context);
                    constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, pid,
                            ConstraintSet.BOTTOM, 8);
                    constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, pid,
                            ConstraintSet.LEFT, 0);
                }
                constraintSetTv1.applyTo(v);

                TextView ptv2 = new TextView(context);
                ptv2.setId(ReflexUtils.getResByRid("id", "answer_num_" + (i), context));
                v.addView(ptv2);
                ptv2.setText((CharSequence) problem.get(i).get("num"));
                ConstraintLayout.LayoutParams tcl2 = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout
                                .LayoutParams.WRAP_CONTENT);
                ptv2.setLayoutParams(tcl2);
                ConstraintSet constraintSetTv3 = new ConstraintSet();
                constraintSetTv3.clone(v);
                constraintSetTv3.connect(ptv2.getId(), ConstraintSet.TOP, imgIcon.getId(),
                        ConstraintSet.TOP, 0);
                constraintSetTv3.connect(ptv2.getId(), ConstraintSet.BOTTOM, imgIcon.getId(),
                        ConstraintSet.BOTTOM, 0);
                constraintSetTv3.connect(ptv2.getId(), ConstraintSet.RIGHT, iv1.getId(),
                        ConstraintSet.RIGHT, 0);
                constraintSetTv3.applyTo(v);

                TextView ptv1 = new TextView(context);
                ptv1.setId(ReflexUtils.getResByRid("id", "prob_" + (i), context));
                ptv1.setTextColor(Color.parseColor("#000000"));
                v.addView(ptv1);
                ptv1.setText((CharSequence) problem.get(i).get("prob"));
                ConstraintLayout.LayoutParams tcl1 = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout
                                .LayoutParams.WRAP_CONTENT);
                ptv1.setLayoutParams(tcl1);
                ConstraintSet constraintSetTv2 = new ConstraintSet();
                constraintSetTv2.clone(v);
                constraintSetTv2.connect(ptv1.getId(), ConstraintSet.TOP, imgIcon.getId(),
                        ConstraintSet.TOP, 0);
                constraintSetTv2.connect(ptv1.getId(), ConstraintSet.LEFT, imgIcon.getId(),
                        ConstraintSet.RIGHT, 12);
                constraintSetTv2.connect(ptv1.getId(), ConstraintSet.BOTTOM, imgIcon.getId(),
                        ConstraintSet.BOTTOM, 0);
                constraintSetTv2.connect(ptv1.getId(), ConstraintSet.RIGHT, ptv2.getId(),
                        ConstraintSet.LEFT, 12);
                constraintSetTv2.applyTo(v);
            }

            ArrayList<HashMap> evaluates = (ArrayList<HashMap>) mp.get("evaluates");
            LinearLayout ly = hsv.findViewById(R.id.evaluate_list_wrap);
            for (int i = 0; i < evaluates.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.goods_evaluate_sub_item, null, false);
                ly.addView(view);
            }
        }
    }

    class GoodsBrandHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private ImageView imgv;
        private HorizontalScrollView hsv;
        private HashMap mp;

        GoodsBrandHolder(View itemView, HashMap hmp) {
            super(itemView);
            mp = hmp;
            tv1 = itemView.findViewById(R.id.textView);
            tv2 = itemView.findViewById(R.id.textView10);
            tv3 = itemView.findViewById(R.id.textView11);
            imgv = itemView.findViewById(R.id.imageView7);
            hsv = itemView.findViewById(R.id.brand_list);

            tv1.setText((CharSequence) mp.get("shop"));
            tv2.setText((CharSequence) mp.get("zz"));
            tv3.setText((CharSequence) mp.get("jj"));
            ArrayList<HashMap> goods = (ArrayList<HashMap>) mp.get("brand_goods");
            LinearLayout ly = hsv.findViewById(R.id.brand_list_wrap);
            for (int i = 0; i < goods.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.goods_brand_sub_item, null, false);
                ly.addView(view);
            }
        }
    }

    class GoodsInfoHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private ArrayList<HashMap> amp;

        GoodsInfoHolder(View itemView, ArrayList<HashMap> ahmp) {
            super(itemView);
            ConstraintLayout v = (ConstraintLayout) itemView;
            amp = ahmp;
            tv = itemView.findViewById(R.id.textView29);
            for (int i = 0; i < ahmp.size(); i++) {
                TextView tv1 = new TextView(context);
                TextView tv2 = new TextView(context);
                tv1.setId(ReflexUtils.getResByRid("id", "attr_title_" + (i), context));
                tv1.setPadding(10, 10, 10, 10);
                tv1.setGravity(Gravity.CENTER_VERTICAL);
                v.addView(tv1);
                ConstraintLayout.LayoutParams tvp1 = new ConstraintLayout.LayoutParams
                        (180, ConstraintLayout.LayoutParams
                                .WRAP_CONTENT);
                tv1.setLayoutParams(tvp1);
                tv1.setText((CharSequence) ahmp.get(i).get("title"));
                ConstraintSet constraintSetTv1 = new ConstraintSet();
                constraintSetTv1.clone(v);
                if (i == 0) {
                    constraintSetTv1.connect(tv1.getId(), ConstraintSet.TOP, tv.getId(),
                            ConstraintSet.BOTTOM, 24);
                    constraintSetTv1.connect(tv1.getId(), ConstraintSet.LEFT, tv.getId(),
                            ConstraintSet.LEFT, 0);
                    tv1.setBackgroundResource(R.drawable.title_all_border);
                    tv2.setBackgroundResource(R.drawable.content_trb_border);
                } else {
                    int pid = ReflexUtils.getResByRid("id", "attr_title_" + (i - 1), context);
                    constraintSetTv1.connect(tv1.getId(), ConstraintSet.TOP, pid,
                            ConstraintSet.BOTTOM, 0);
                    constraintSetTv1.connect(tv1.getId(), ConstraintSet.LEFT, pid,
                            ConstraintSet.LEFT, 0);
                    tv1.setBackgroundResource(R.drawable.title_lbr_border);
                    tv2.setBackgroundResource(R.drawable.content_br_border);
                }
                constraintSetTv1.applyTo(v);

                tv2.setId(ReflexUtils.getResByRid("id", "attr_content_" + (i), context));
                tv2.setPadding(10, 10, 10, 10);
                tv2.setGravity(Gravity.CENTER_VERTICAL);
                v.addView(tv2);
                ConstraintLayout.LayoutParams tvp2 = new ConstraintLayout.LayoutParams
                        (0, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
                tv2.setLayoutParams(tvp2);
                tv2.setText((CharSequence) ahmp.get(i).get("content"));
                ConstraintSet constraintSetTv2 = new ConstraintSet();
                constraintSetTv2.clone(v);
                constraintSetTv2.connect(tv2.getId(), ConstraintSet.TOP, tv1.getId(),
                        ConstraintSet.TOP, 0);
                constraintSetTv2.connect(tv2.getId(), ConstraintSet.LEFT, tv1.getId(),
                        ConstraintSet.RIGHT, 0);
                constraintSetTv2.connect(tv2.getId(), ConstraintSet.BOTTOM, tv1.getId(),
                        ConstraintSet.BOTTOM, 0);
                constraintSetTv2.connect(tv2.getId(), ConstraintSet.RIGHT, tv.getId(),
                        ConstraintSet.RIGHT, 24);
                constraintSetTv2.applyTo(v);
            }
        }
    }

    class GoodsDescHolder extends RecyclerView.ViewHolder {

        GoodsDescHolder(final View itemView, String desc) {
            super(itemView);
            ItemWebView web_view = (ItemWebView) itemView;
            WebSettings wb = web_view.getSettings();
            wb.setJavaScriptEnabled(true);
            wb.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL.SINGLE_COLUMN);
            wb.setDefaultTextEncodingName("UTF-8");
            wb.setAppCacheEnabled(true);
            wb.setCacheMode(WebSettings.LOAD_DEFAULT);
            String webdesc = desc.replace("<img", "<img width='100%'");
            web_view.loadDataWithBaseURL(null, webdesc, "text/html", "utf-8", null);
        }
    }

    class ViewNullHolder extends RecyclerView.ViewHolder {
        ViewNullHolder(View itemView) {
            super(itemView);
        }
    }
}
