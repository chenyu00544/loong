package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseFragment;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.cart.CartErrorItem;
import com.vcvb.chenyu.shop.adapter.item.cart.CartHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.cart.CartItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.CartItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.javaBean.cart.LocalCartBean;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.store.Shop;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;

public class FragmentCart extends BaseFragment {
    View view;
    Context context;
    String token;

    private TextView editView;
    private ImageView msgView;
    private TextView msgNum;
    private CheckBox selectAllCB;
    private TextView totalView;
    private TextView toPay;
    private View layer;
    private TextView del;

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<CartListBean> carts = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    private ConstraintLayout cly;
    private ConstraintSet set = new ConstraintSet();
    IDataStorage dataStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = getActivity();
        dataStorage = DataStorageFactory.getInstance(context, DataStorageFactory.TYPE_DATABASE);
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        cly = (ConstraintLayout) view;
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        set.clone(cly);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCartData(true);
        initView();
        initListener();
    }

    public void initView() {
        editView = view.findViewById(R.id.textView96);
        msgView = view.findViewById(R.id.imageView42);
        msgNum = view.findViewById(R.id.textView99);
        totalView = view.findViewById(R.id.textView106);
        toPay = view.findViewById(R.id.textView107);
        layer = view.findViewById(R.id.view34);
        selectAllCB = view.findViewById(R.id.checkBox4);
        del = view.findViewById(R.id.textView112);

        mRecyclerView = view.findViewById(R.id.cart_content);
        mLayoutManager = new GridLayoutManager(context, 1);
        CartItemDecoration spaces = new CartItemDecoration(context, carts);
        mRecyclerView.addItemDecoration(spaces);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initListener() {
        refreshLayout = view.findViewById(R.id.cart_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getCartData(false);
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

        toPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "toPay", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                startActivity(intent);
            }
        });

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editView.getText() == context.getString(R.string.edit)) {
                    editView.setText(R.string.over);
                    set.setVerticalBias(layer.getId(), 0);
                } else {
                    editView.setText(R.string.edit);
                    set.setVerticalBias(layer.getId(), 1);
                }
                set.applyTo(cly);
            }
        });

        selectAllCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bool;
                if (selectAllCB.isChecked()) {
                    selectAllCB.setChecked(true);
                    bool = true;
                } else {
                    selectAllCB.setChecked(false);
                    bool = false;
                }
                for (int i = 0; i < carts.size(); i++) {
                    carts.get(i).setCheckOnce(bool);
                    if (carts.get(i).getIsType() == 2) {
                        carts.get(i).setCheckAll(bool);
                    }
                }
                setTotal();
                mAdapter.notifyDataSetChanged();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.clear();
                carts.remove(carts);
                mAdapter.notifyDataSetChanged();
            }
        });

        //全选本品牌商品
        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.checkBox2).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                if (carts.get(position).getIsType() == 2) {
                    int npos = position;
                    boolean tbool = false;
                    if (carts.get(position).isCheckAll()) {
                        carts.get(position).setCheckAll(false);
                        tbool = false;
                    } else {
                        carts.get(position).setCheckAll(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (npos < carts.size() && carts.get(npos).getIsType() == 1) {
                        carts.get(npos).setCheckOnce(tbool);
                        npos += 1;
                    }
                }
                setTotal();
                mAdapter.notifyDataSetChanged();
            }
        });

        //单选本品牌商品
        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.checkBox3).setOnChildClickListener1(new CYCItemClickSupport.OnChildItemClickListener1() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                if (carts.get(position).getIsType() == 1) {
                    int ppos = position;
                    int npos = position;
                    boolean tbool = false;
                    if (carts.get(position).isCheckOnce()) {
                        carts.get(position).setCheckOnce(false);
                        tbool = false;
                    } else {
                        carts.get(position).setCheckOnce(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (carts.size() > npos && carts.get(npos).getIsType() == 1) {
                        if (carts.get(npos).isCheckOnce()) {
                            tbool = true;
                        }
                        npos += 1;
                    }
                    ppos -= 1;
                    while (ppos > 0 && carts.get(ppos).getIsType() == 1) {
                        if (carts.get(ppos).isCheckOnce()) {
                            tbool = true;
                        }
                        ppos -= 1;
                    }
                    carts.get(ppos).setCheckAll(tbool);
                }
                setTotal();
                mAdapter.notifyDataSetChanged();
            }
        });

        //减少商品数量
        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.imageView43).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                int num = carts.get(position).getGoods().getGoods_number();
                if (num <= 1) {
                    num = 1;
                } else {
                    num -= 1;
                }
                carts.get(position).getGoods().setGoods_number(num);
                setTotal();
                mAdapter.notifyDataSetChanged();
            }
        });
        //增加商品数量
        CYCItemClickSupport.BuildTo3(mRecyclerView, R.id.imageView44).setOnChildClickListener3
                (new CYCItemClickSupport.OnChildItemClickListener3() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                int num = carts.get(position).getGoods().getGoods_number();
                num += 1;
                carts.get(position).getGoods().setGoods_number(num);
                setTotal();
                mAdapter.notifyDataSetChanged();
            }
        });

        //找相似商品
        CYCItemClickSupport.BuildTo4(mRecyclerView, R.id.textView109).setOnChildClickListener4
                (new CYCItemClickSupport.OnChildItemClickListener4() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(6);
                mAdapter.notifyDataSetChanged();
            }
        });

        //收藏商品
        CYCItemClickSupport.BuildTo5(mRecyclerView, R.id.textView110).setOnChildClickListener5
                (new CYCItemClickSupport.OnChildItemClickListener5() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(7);
                mAdapter.notifyDataSetChanged();
            }
        });

        //删除商品
        CYCItemClickSupport.BuildTo6(mRecyclerView, R.id.textView111).setOnChildClickListener6
                (new CYCItemClickSupport.OnChildItemClickListener6() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                carts.remove(position);
                mAdapter.remove(position);

                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).getIsType() == 2) {
                        if (carts.size() > i + 1) {
                            if (carts.get(i + 1).getIsType() == 2) {
                                carts.remove(i);
                                mAdapter.remove(i);
                            }
                        } else if (carts.size() == i + 1) {
                            carts.remove(i);
                            mAdapter.remove(i);
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new CYCItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int
                    position) {
                clearLong();
                carts.get(position).setLong(true);
                System.out.println(2);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                if(carts.size() > position){
                    if (carts.get(position).getGoods() != null) {
                        Intent intent = new Intent(context, GoodsDetailActivity.class);
                        intent.putExtra("id", carts.get(position).getGoods().getGoods_id());
                        context.startActivity(intent);
                    } else if (carts.get(position).getShop() != null) {
                        Intent intent = new Intent(context, GoodsDetailActivity.class);
                        intent.putExtra("id", carts.get(position).getGoods().getGoods_id());
                        context.startActivity(intent);
                    }
                }else{

                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //数据获取操作
    public void getCartData(final boolean bool) {
        if (token != null && !token.equals("")) {
            if (bool) {
                loadingDialog.show();
            }
            HashMap<String, String> mp = new HashMap<>();
            mp.put("token", token);
            HttpUtils.getInstance().post(ConstantManager.Url.CARTLIST, mp, new HttpUtils.NetCall() {
                @Override
                public void success(Call call, final JSONObject json) throws IOException {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bool) {
                                loadingDialog.dismiss();
                            }
                            bindData(json);
                        }
                    });
                }

                @Override
                public void failed(Call call, IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bool) {
                                loadingDialog.dismiss();
                            }
                        }
                    });
                }
            });
        } else {
            List<LocalCartBean> cartBeans = dataStorage.loadAll(LocalCartBean.class);

            HashMap<String, String> mp = new HashMap<>();
            mp.put("token", token);
            HttpUtils.getInstance().post(ConstantManager.Url.CARTLIST, mp, new HttpUtils.NetCall() {
                @Override
                public void success(Call call, final JSONObject json) throws IOException {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bool) {
                                loadingDialog.dismiss();
                            }
                            bindData(json);
                        }
                    });
                }

                @Override
                public void failed(Call call, IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bool) {
                                loadingDialog.dismiss();
                            }
                        }
                    });
                }
            });
            bindData(null);
        }
    }

    public void bindData(JSONObject json) {
        carts.clear();
        mAdapter.clear();
        if (json != null) {
            try {
                if (json.getInt("code") == 0) {
                    JSONArray jsonArray = json.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject cartJson = (JSONObject) jsonArray.get(i);
                        CartListBean c = new CartListBean();
                        c.setIsType(2);
                        Shop shop = JsonUtils.fromJsonObject(cartJson.getJSONObject("store"),
                                Shop.class);
                        c.setShop(shop);
                        carts.add(c);
                        JSONArray goodses = cartJson.getJSONArray("goods");
                        for (int j = 0; j < goodses.length(); j++) {
                            CartListBean cart = new CartListBean();
                            Goods goods = JsonUtils.fromJsonObject((JSONObject) goodses.get(j),
                                    Goods.class);
                            cart.setGoods(goods);
                            carts.add(cart);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }

        View payFoot = view.findViewById(R.id.pay_foot);
        CheckBox cb = view.findViewById(R.id.checkBox4);
        if (carts.size() > 0) {
            if (carts.get(0).getIsType() == -1) {
                set.connect(cb.getId(), ConstraintSet.TOP, payFoot.getId(), ConstraintSet.TOP, 0);
                set.connect(cb.getId(), ConstraintSet.BOTTOM, payFoot.getId(), ConstraintSet
                        .BOTTOM, 0);
                set.constrainHeight(payFoot.getId(), 1);
                cb.getBackground().mutate().setAlpha(0);
            } else {
                set.connect(cb.getId(), ConstraintSet.TOP, payFoot.getId(), ConstraintSet.TOP,
                        ToolUtils.dip2px(context, 8));
                set.connect(cb.getId(), ConstraintSet.BOTTOM, payFoot.getId(), ConstraintSet
                        .BOTTOM, ToolUtils.dip2px(context, 8));
                set.constrainHeight(payFoot.getId(), ToolUtils.dip2px(context, 50));
                cb.getBackground().mutate().setAlpha(255);
            }
        } else {
            set.constrainHeight(payFoot.getId(), 1);
        }
        set.applyTo(cly);
        mAdapter.addAll(getItems(carts));
    }

    protected List<Item> getItems(List<CartListBean> list) {
        List<Item> cells = new ArrayList<>();
        if (list.size() == 0) {
            cells.add(new CartErrorItem(null, context));
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getShop() != null) {
                    cells.add(new CartHeaderItem(list.get(i), context));
                }
                if (list.get(i).getGoods() != null) {
                    cells.add(new CartItem(list.get(i), context));
                }
            }
        }
        return cells;
    }

    //清理长按显示状态
    public void clearLong() {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).isLong()) {
                carts.get(i).setLong(false);
            }
        }
    }

    //设置订单总金额
    public void setTotal() {
        double total = 0;
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getIsType() == 1) {
                if (carts.get(i).isCheckOnce()) {
                    if (carts.get(i).getGoods().getIs_promote().equals("1")) {
                        total += Double.valueOf(carts.get(i).getGoods().getPromote_price()) *
                                carts.get(i).getGoods().getGoods_number();
                    } else {
                        total += Double.valueOf(carts.get(i).getGoods().getShop_price()) * carts
                                .get(i).getGoods().getGoods_number();
                    }
                }
            }
        }
        totalView.setText(String.format("￥%.2f", total));
    }

    @Override
    public void onResume() {
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        super.onResume();
    }
}