package com.vcvb.chenyu.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
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
    private int width;
    final private int EMPTY_VIEW = 0;
    final private int PROGRESS_VIEW = 99;
    final private int BANNER_VIEW = 1;
    final private int PRICE_VIEW = 2;
    final private int NAME_VIEW = 3;

    public GoodsDetailViewAdapter(HashMap hm, int width, Context context) {
        this.context = context;
        this.width = width;
        this.hm = hm;
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
            String string = "";
            int i = 0;
            for (Object key : hm.keySet()) {
                if (position == i) {
                    string = (String) key;
                }
                i++;
            }
            int type;
            switch (string) {
                case "banner":
                    type = BANNER_VIEW;
                    break;
                case "price":
                    type = PRICE_VIEW;
                    break;
                case "goods_name":
                    type = NAME_VIEW;
                    break;
                default:
                    type = PROGRESS_VIEW;
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
//        } else if (viewType == PRICE_VIEW) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_price_info_item,
//                    parent, false);
//            return new GoodsDetailViewAdapter.PriceInfoHolder(view);
//        } else if (viewType == NAME_VIEW) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_name_item,
//                    parent, false);
//            return new GoodsDetailViewAdapter.GoodsNameHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_null,
                    parent, false);
            return new GoodsDetailViewAdapter.ViewNullHolder(view);
        }
    }

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
        }else if(holder instanceof PriceInfoHolder){
            PriceInfoHolder priceInfoHolder = (PriceInfoHolder) holder;
            HashMap pMp = (HashMap) hm.get("price");
            priceInfoHolder.goodsPrice.setText((CharSequence) pMp.get("price"));
            priceInfoHolder.goodsMarket.setText((CharSequence) pMp.get("market"));
            if (pMp.get("goods_tip") == "1") {
                priceInfoHolder.goodsTip.setAlpha(1);
            }
        }else if(holder instanceof GoodsNameHolder){
            GoodsNameHolder goodsNameHolder = (GoodsNameHolder) holder;
            HashMap nMp = (HashMap) hm.get("goods_name");
            goodsNameHolder.name.setText((CharSequence) nMp.get("name"));
            goodsNameHolder.desc.setText((CharSequence) nMp.get("desc"));
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
                            return 6;
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
                    Log.i("tag", "" + position);
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
    class ViewNullHolder extends RecyclerView.ViewHolder {
        ViewNullHolder(View itemView) {
            super(itemView);
        }
    }
}
