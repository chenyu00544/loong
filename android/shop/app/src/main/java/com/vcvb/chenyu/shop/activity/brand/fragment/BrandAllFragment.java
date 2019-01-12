package com.vcvb.chenyu.shop.activity.brand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.brand.BrandGoodsVItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.brand.BrandGoods;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class BrandAllFragment extends BaseRecyclerViewFragment {

    private String id;
    private BrandGoods brandGoods;

    public CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private CallBackValue callBackValue;

    private int[] clickIds = new int[]{R.id.textView305, R.id.textView306, R.id.textView307, R.id
            .textView308};
    private ImageView upDownTip;

    private String type_w = "normal";
    private String up_down = "down";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.brand_fragment, container, false);
        if (getActivity() != null) {
            id = getActivity().getIntent().getStringExtra("id");
        }
        getData();
        initView();
        return view;
    }

    public void initView() {
        upDownTip = view.findViewById(R.id.imageView156);
        TextView tv1 = view.findViewById(R.id.textView305);
        tv1.setOnClickListener(onClickListener);
        TextView tv2 = view.findViewById(R.id.textView306);
        tv2.setOnClickListener(onClickListener);
        TextView tv3 = view.findViewById(R.id.textView307);
        tv3.setOnClickListener(onClickListener);
        TextView tv4 = view.findViewById(R.id.textView308);
        tv4.setOnClickListener(onClickListener);

        mRecyclerView = view.findViewById(R.id.rv_list);
        mLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(context, 5));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("brand_id", id);
        mp.put("type", type_w);
        mp.put("up_down", up_down);
        HttpUtils.getInstance().post(ConstantManager.Url.BRAND, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            if (json != null) {
                                try {
                                    if (json.getInt("code") == 0) {
                                        bindData(json);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            Toast.makeText(getActivity(), "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void bindData(JSONObject json) {
        mAdapter.clear();
        if (brandGoods != null && brandGoods.getGoodses() != null) {
            brandGoods.getGoodses().clear();
        }
        try {
            JSONObject object = json.getJSONObject("data");
            brandGoods = JsonUtils.fromJsonObject(object, BrandGoods.class);
            brandGoods.setData(object);
            if (callBackValue != null) {
                callBackValue.SendMessageValue(brandGoods);
            }
            mAdapter.addAll(getItems());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        if (brandGoods.getGoodses() != null && brandGoods.getGoodses().size() > 0) {
            for (int i = 0; i < brandGoods.getGoodses().size(); i++) {
                BrandGoodsVItem brandGoodsVItem = new BrandGoodsVItem(brandGoods.getGoodses().get
                        (i), context);
                items.add(brandGoodsVItem);
            }
        }
        return items;
    }

    public void selectNavs(int pos) {
        for (int i = 0; i < clickIds.length; i++) {
            ((TextView) view.findViewById(clickIds[i])).setTextColor(context.getResources()
                    .getColor(R.color.black_29));
        }
        ((TextView) view.findViewById(clickIds[pos])).setTextColor(context.getResources()
                .getColor(R.color.colorFont_morandi));
        if (clickIds[pos] == R.id.textView307) {
            if (upDownTip.getTag().equals("down")) {
                upDownTip.setTag("up");
                upDownTip.setImageResource(R.drawable.icon_up);
            } else {
                upDownTip.setTag("down");
                upDownTip.setImageResource(R.drawable.icon_down);
            }
        } else {
            upDownTip.setTag("down");
            upDownTip.setImageResource(R.drawable.icon_up_down_gray);
        }
        clickSelectNavs(clickIds[pos]);
    }

    public void clickSelectNavs(int type) {
        up_down = "down";
        switch (type) {
            case R.id.textView305:
                type_w = "normal";
                break;
            case R.id.textView306:
                type_w = "volume";
                break;
            case R.id.textView307:
                type_w = "price";
                if (upDownTip.getTag().equals("down")) {
                    up_down = "down";
                } else {
                    up_down = "up";
                }
                break;
            case R.id.textView308:
                type_w = "new";
                break;
        }
        getData();
    }

    public void setCallBackValue(CallBackValue callBack) {
        callBackValue = callBack;
    }

    //回调接口
    public interface CallBackValue {
        public void SendMessageValue(BrandGoods brand);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < clickIds.length; i++) {
                if (clickIds[i] == view.getId()) {
                    selectNavs(i);
                }
            }
        }
    };
}
