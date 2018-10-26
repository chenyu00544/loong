package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsBrand;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsBrandItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = R.layout.goods_brand_item;
    private DefaultItemDecoration defaultItemDecoration;
    private RecyclerView recyclerView;

    public GoodsBrandItem(GoodsDetail beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView brand = holder.get(R.id.textView28);
        View v = holder.getItemView();
        posMap.put(brand.getId(), -1);
        brand.setOnClickListener(listener);

        TextView textView = holder.get(R.id.textView);
        textView.setText(mData.getGoodsBrand().getBrand_name());

        if (mData.getIsScroll() == 1) {
            if (recyclerView == null) {
                recyclerView = (RecyclerView) holder.getView(R.id.brand_list);
                CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                if (defaultItemDecoration == null) {
                    defaultItemDecoration = new DefaultItemDecoration(context, ToolUtils.dip2px
                            (context, 5));
                    recyclerView.addItemDecoration(defaultItemDecoration);
                }
                if (mData.getGoodsBrand().getGoodses().size() > 0) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                            recyclerView.getLayoutParams();
                    params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    recyclerView.setLayoutParams(params);
                }
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                mAdapter.addAll(getItems(mData.getGoodsBrand()));
                CYCItemClickSupport.addTo(recyclerView).setOnItemClickListener(itemListener);
            }

            ImageView imageView7 = holder.get(R.id.imageView7);
            RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                    .drawable.icon_no_pic).skipMemoryCache(false).dontAnimate();
            Glide.with(context).load(mData.getGoodsBrand().getBrand_logo()).apply(requestOptions)
                    .into(imageView7);
        }
    }

    CYCItemClickSupport.OnItemClickListener itemListener = new CYCItemClickSupport
            .OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
            if (onClickListener != null) {
                onClickListener.onClicked(itemView, position);
            }
        }
    };

    public List<Item> getItems(GoodsBrand bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.getGoodses().size(); i++) {
            cells.add(new GoodsBrandSubItem(bean.getGoodses().get(i), context));
        }
        return cells;
    }
}
