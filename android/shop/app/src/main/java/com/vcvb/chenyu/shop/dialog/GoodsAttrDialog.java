package com.vcvb.chenyu.shop.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.dialog.GoodsAttrsItem;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttr;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@SuppressLint("ValidFragment")
public class GoodsAttrDialog extends DialogFragment {
    Window window;
    View v;
    Context context;
    String tag;
    OnClickListener onClickListener;
    RequestOptions requestOptions;
    HashMap<String, Object> outAttr = new HashMap<>();

    public ImageView attrImg;
    public TextView goodsName;
    public TextView goodsPrice;
    public TextView tax;

    public TextView buy;
    public TextView add;
    public TextView confirm;

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private GridLayoutManager mLayoutManager;
    GoodsDetail goodsDetail;
    List<List<GoodsAttr>> multiAttrs;

    @SuppressLint("ValidFragment")
    public GoodsAttrDialog(GoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        outAttr.put("num", 1);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialog_goods_attr, container);
        window = getDialog().getWindow();

        RoundedCorners roundedCorners = new RoundedCorners(ToolUtils.dip2px(context, 4));
        requestOptions = RequestOptions.bitmapTransform(roundedCorners);

        attrImg = v.findViewById(R.id.imageView83);
        goodsName = v.findViewById(R.id.textView165);
        goodsName.setText(goodsDetail.getGoods_name());
        goodsPrice = v.findViewById(R.id.textView170);
        if (goodsDetail.getIs_promote() == 1) {
            goodsPrice.setText(goodsDetail.getPromote_price_format());
        } else {
            goodsPrice.setText(goodsDetail.getShop_price_format());
        }
        tax = v.findViewById(R.id.textView16);
        String tax_fee = "";
        if (goodsDetail.getGoodsTexAttr() == null) {
            tax_fee = "0";
        } else {
            tax_fee = goodsDetail.getGoodsTexAttr().getAttr_value();
        }
        String str = "税率" + tax_fee + "%";
        tax.setText(str);

        multiAttrs = goodsDetail.getMultiAttrs();

        if (multiAttrs != null) {
            if (multiAttrs.size() > 0) {
                if (multiAttrs.get(0) != null) {
                    if (multiAttrs.get(0).size() > 0) {
                        Glide.with(context).load(multiAttrs.get(0).get(0).getAttr_img_flie()).apply
                                (requestOptions).into(attrImg);
                    }
                }
            }
        }

        ConstraintLayout cly = (ConstraintLayout) v;
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        buy = v.findViewById(R.id.textView177);
        buy.setOnClickListener(listener);
        add = v.findViewById(R.id.textView178);
        add.setOnClickListener(listener);
        confirm = v.findViewById(R.id.textView235);
        confirm.setOnClickListener(listener);
        outAttr.put("tag", tag);
        if (tag.equals("Buy")) {
            set.constrainHeight(buy.getId(), ToolUtils.dip2px(context, 50));
            set.constrainHeight(add.getId(), 0);
            set.constrainHeight(confirm.getId(), 0);
        } else if (tag.equals("AddCart")) {
            set.constrainHeight(buy.getId(), 0);
            set.constrainHeight(add.getId(), ToolUtils.dip2px(context, 50));
            set.constrainHeight(confirm.getId(), 0);
        } else {
            set.constrainHeight(buy.getId(), 0);
            set.constrainHeight(add.getId(), 0);
            set.constrainHeight(confirm.getId(), ToolUtils.dip2px(context, 50));
        }


        ImageView numAdd = v.findViewById(R.id.imageView85);
        numAdd.setOnClickListener(attrNumListener);
        ImageView numSub = v.findViewById(R.id.imageView84);
        numSub.setOnClickListener(attrNumListener);

        mRecyclerView = v.findViewById(R.id.attr_wrap);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CYCSimpleAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addAll(getItems(multiAttrs));
        Integer row = 1;
        if (multiAttrs != null) {
            row = multiAttrs.size();
        }
        int height = 50 * row;
        if (height > 200) {
            height = 200;
        }
        set.constrainHeight(mRecyclerView.getId(), ToolUtils.dip2px(context, height));
        set.applyTo(cly);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager windowManager = window.getWindowManager();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        Display display = windowManager.getDefaultDisplay();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.BottomShow);
        layoutParams.width = display.getWidth();
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            this.tag = tag;
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems(List<List<GoodsAttr>> attrs) {
        List<Item> cells = new ArrayList<>();

        List<Integer> attr_ids = new ArrayList<>();
        List<Integer> goods_attr_ids = new ArrayList<>();

        for (int i = 0; i < attrs.size(); i++) {
            GoodsAttrsItem goodsAttrsItem = new GoodsAttrsItem(attrs.get(i), context);
            goodsAttrsItem.setOnItemClickListener(listenerAttr);
            cells.add(goodsAttrsItem);
            for (int j = 0; j < attrs.get(i).size(); j++) {
                if (attrs.get(i).get(j).getIsSelect()) {
                    attr_ids.add(attrs.get(i).get(j).getAttr_id());
                    goods_attr_ids.add(attrs.get(i).get(j).getGoods_attr_id());
                }
            }
        }
        outAttr.put("attr_ids", attr_ids);
        outAttr.put("goods_attr_ids", goods_attr_ids);
        return cells;
    }

    public interface OnClickListener {
        void onClicked(View view, HashMap<String, Object> attr);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, outAttr);
            }
        }
    };

    GoodsAttrsItem.OnClickListener listenerAttr = new GoodsAttrsItem.OnClickListener() {
        @Override
        public void onClicked(View view, List<GoodsAttr> attrs, int pos) {
            List<Integer> attr_ids = new ArrayList<>();
            List<Integer> goods_attr_ids = new ArrayList<>();
            System.out.println(pos);
            for (int i = 0; i < attrs.size(); i++) {
                if (attrs.get(i).getIsSelect()) {
                    if (attrs.get(i).getAttr_img_flie() != null && !attrs.get(i).getAttr_img_flie
                            ().equals("")) {
                        Glide.with(context).load(attrs.get(i).getAttr_img_flie()).apply
                                (requestOptions).into(attrImg);
                    }
                }
            }
            for (int i = 0; i < multiAttrs.size(); i++) {
                for (int j = 0; j < multiAttrs.get(i).size(); j++) {
                    if (multiAttrs.get(i).get(j).getIsSelect()) {
                        attr_ids.add(multiAttrs.get(i).get(j).getAttr_id());
                        goods_attr_ids.add(multiAttrs.get(i).get(j).getGoods_attr_id());
                    }
                }
            }
            outAttr.put("attr_ids", attr_ids);
            outAttr.put("goods_attr_ids", goods_attr_ids);
        }
    };

    View.OnClickListener attrNumListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int num = 0;
            switch (view.getId()) {
                case R.id.imageView85: //++
                    if (outAttr.get("num") != null) {
                        num = (int) outAttr.get("num") + 1;
                        outAttr.put("num", num);
                    } else {
                        num = 1;
                        outAttr.put("num", 1);
                    }
                    if (goodsDetail.getIs_limit_buy() == 1 && goodsDetail.getCurrent_time() >
                            goodsDetail.getLimit_buy_start_date() && goodsDetail.getCurrent_time
                            () < goodsDetail.getLimit_buy_end_date()) {
                        if (num > goodsDetail.getLimit_buy_num()) {
                            num = goodsDetail.getLimit_buy_num();
                            outAttr.put("num", num);
                            ToastUtils.showShortToast(context, "当前商品达到限购数量");
                        }
                    }
                    if (goodsDetail.getGoodsSecKill() != null && goodsDetail.getGoodsSecKill().getSec_limit() < num) {
                        num = goodsDetail.getGoodsSecKill().getSec_limit();
                        outAttr.put("num", num);
                        ToastUtils.showShortToast(context, "当前商品达到限购数量");
                    }

                    if (goodsDetail.getGoodsTeam() != null && goodsDetail.getGoodsTeam().getAstrict_num() < num) {
                        num = goodsDetail.getGoodsTeam().getAstrict_num();
                        outAttr.put("num", num);
                        ToastUtils.showShortToast(context, "当前商品达到限购数量");
                    }

                    ((TextView) v.findViewById(R.id.textView181)).setText(String.format(Locale
                            .CANADA, "%d", num));
                    break;
                case R.id.imageView84: //--
                    if (outAttr.get("num") != null && (int) outAttr.get("num") > 1) {
                        num = (int) outAttr.get("num") - 1;
                        outAttr.put("num", num);
                    } else {
                        num = 1;
                        outAttr.put("num", 1);
                    }
                    ((TextView) v.findViewById(R.id.textView181)).setText(String.format(Locale
                            .CANADA, "%d", num));
                    break;
            }
        }
    };
}
