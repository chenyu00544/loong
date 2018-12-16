package com.vcvb.chenyu.shop.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.cart.CartErrorItem;
import com.vcvb.chenyu.shop.adapter.item.cart.CartHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.cart.CartItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.CartItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.store.Shop;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;

public class CartActivity extends BaseRecyclerViewActivity {
    Context context;
    String token;
    private TextView editView;
    private ImageView msgView;
    private TextView msgNum;
    private CheckBox selectAllCB;
    private TextView totalView;
    private TextView totalTaxView;
    private TextView toPay;
    private View layer;
    private TextView del;

    private List<CartListBean> carts = new ArrayList<>();

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    private ConstraintLayout cly;
    private ConstraintSet set = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        context = this;
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        changeStatusBarTextColor(false);
        set = new ConstraintSet();
        cly = findViewById(R.id.cart);
        set.clone(cly);
        setNavBack();
        initView();
        initRefresh();
        getCartData(true);
        initListener();
    }

    public void initView() {
        editView = findViewById(R.id.textView96);
        msgView = findViewById(R.id.imageView42);
        msgNum = findViewById(R.id.textView99);
        totalView = findViewById(R.id.textView106);
        totalTaxView = findViewById(R.id.textView103);
        toPay = findViewById(R.id.textView107);
        layer = findViewById(R.id.view34);
        selectAllCB = findViewById(R.id.checkBox4);
        del = findViewById(R.id.textView112);

        mRecyclerView = findViewById(R.id.cart_content);
        mLayoutManager = new GridLayoutManager(context, 1);
        CartItemDecoration spaces = new CartItemDecoration(context, carts);
        mRecyclerView.addItemDecoration(spaces);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView86);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SwipeBackHelper.finish(CartActivity.this);
                }
            });
        }

    }

    @Override
    public void onResume() {
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        super.onResume();
    }

    public void initListener() {
        refreshLayout = findViewById(R.id.cart_list);
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
                List<Integer> _cartIds = new ArrayList<>();
                List<CartListBean> _carts = new ArrayList<>();
                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).getIsType() != 2) {
                        if (carts.get(i).isCheckOnce()) {
                            _cartIds.add(carts.get(i).getGoods().getRec_id());
                        } else {
                            _carts.add(carts.get(i));
                        }
                    } else {
                        _carts.add(carts.get(i));
                    }
                }
                carts = new ArrayList<>(_carts);
                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).getIsType() == 2) {
                        if (carts.size() > i + 1) {
                            if (carts.get(i + 1).getIsType() == 2) {
                                carts.remove(i);
                            }
                        } else if (carts.size() == i + 1) {
                            carts.remove(i);
                        }
                    }
                }
                System.out.println(_cartIds);
                mAdapter.clear();
                mAdapter.addAll(getItems(carts));
                editView.setText(R.string.edit);
                set.setVerticalBias(layer.getId(), 1);
                set.applyTo(cly);
                if (carts.size() == 0) {
                    hideOrShowPayBottom(false);
                }
                delCartGoods(_cartIds);
                setCheckStatus();
                setTotal();
            }
        });

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new CYCItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int
                    position) {
                clearLong();
                carts.get(position).setLong(true);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    //数据获取操作
    public void getCartData(final boolean bool) {
        if (bool) {
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("device_id", (String) UserInfoUtils.getInstance(context).getUserInfo().get
                ("device_id"));
        HttpUtils.getInstance().post(ConstantManager.Url.CART_LIST, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        if (bool) {
                            loadingDialog.dismiss();
                        }
                        bindData(json);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        if (bool) {
                            loadingDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    public void bindData(JSONObject json) {
        carts.clear();
        mAdapter.clear();
        if (json != null) {
            try {
                if (json.getInt("code") == 0) {
                    JSONArray jsonArray = json.getJSONObject("data").getJSONArray("cart");
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


        if (carts.size() > 0) {
            hideOrShowPayBottom(true);
        } else {
            hideOrShowPayBottom(false);
        }
        mAdapter.addAll(getItems(carts));
    }

    //显示隐藏结算栏
    public void hideOrShowPayBottom(boolean b) {
        View payFoot = findViewById(R.id.pay_foot);
        CheckBox cb = findViewById(R.id.checkBox4);
        if (b) {
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
    }

    protected List<Item> getItems(List<CartListBean> list) {
        List<Item> cells = new ArrayList<>();
        if (list.size() == 0) {
            CartErrorItem cartErrorItem = new CartErrorItem(null, context);
            cartErrorItem.setOnItemClickListener(cartItemListener);
            cells.add(cartErrorItem);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getShop() != null) {
                    CartHeaderItem cartHeaderItem = new CartHeaderItem(list.get(i), context);
                    cartHeaderItem.setOnItemClickListener(cartItemListener);
                    cells.add(cartHeaderItem);
                }
                if (list.get(i).getGoods() != null) {
                    CartItem cartItem = new CartItem(list.get(i), context);
                    cartItem.setOnItemClickListener(cartItemListener);
                    cells.add(cartItem);
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
        double totalTax = 0;
        boolean bool = true;
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
                    totalTax += Double.valueOf(carts.get(i).getGoods().getShop_price()) * carts
                            .get(i).getGoods().getGoods_number() * Double.valueOf(carts.get(i)
                            .getGoods().getTax()) / 100;
                } else {
                    bool = false;
                }
            }
        }
        if (carts.size() == 0) {
            bool = false;
        }
        selectAllCB.setChecked(bool);
        totalView.setText(String.format(Locale.CHINA, "￥%.2f", total + totalTax));
        totalTaxView.setText(String.format(Locale.CHINA, "包含税费￥%.2f", totalTax));
    }

    //增加减少商品数量同步服务器
    public void modifyCartNum(Integer rec_id, Integer num) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("rec_id", String.valueOf(rec_id));
        mp.put("goods_number", String.valueOf(num));
        HttpUtils.getInstance().post(ConstantManager.Url.SET_CART, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                System.out.println(json);
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    //删除购物车商品
    public void delCartGoods(List<Integer> rec_ids) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("rec_ids", StringUtils.join(rec_ids, ","));
        HttpUtils.getInstance().post(ConstantManager.Url.DEL_CART, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                System.out.println(json);
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    //收藏购物车商品
    public void collectCartGoods(List<Integer> goods_ids) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", StringUtils.join(goods_ids, ","));
        HttpUtils.getInstance().post(ConstantManager.Url.ADD_COLLECT_GOODS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                System.out.println(json);
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    //设置选中状态
    public void setCheckStatus() {
        boolean b = false;
        for (int i = carts.size() - 1; i >= 0; i--) {
            if (carts.get(i).getIsType() != 2) {
                if (carts.get(i).isCheckOnce()) {
                    b = true;
                }
            } else if (carts.get(i).getIsType() == 2) {
                carts.get(i).setCheckAll(b);
                b = false;
            }
        }
    }

    CartItem.OnClickListener cartItemListener = new CartItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            clearLong();
            switch (view.getId()) {
                case R.id.imageView41://跳转Activity Goods
                case R.id.textView95: //跳转Activity Brand
                    if (carts.size() > pos) {
                        if (carts.get(pos).getGoods() != null && carts.get(pos).getIsType() == 1) {
                            Intent intent = new Intent(context, GoodsDetailActivity.class);
                            intent.putExtra("id", carts.get(pos).getGoods().getGoods_id());
                            context.startActivity(intent);
                        } else if (carts.get(pos).getShop() != null && carts.get(pos).getIsType()
                                == 2) {
                            System.out.println("Brand");
//                        Intent intent = new Intent(context, GoodsDetailActivity.class);
//                        intent.putExtra("id", carts.get(position).getShop().getRu_id());
//                        context.startActivity(intent);
                        }
                    }
                    break;
                case R.id.view30:
                    break;
                case R.id.checkBox3://单选商品
                    if (carts.get(pos).getIsType() == 1) {
                        if (carts.get(pos).isCheckOnce()) {
                            carts.get(pos).setCheckOnce(false);
                        } else {
                            carts.get(pos).setCheckOnce(true);
                        }
                    }
                    setCheckStatus();
                    setTotal();
                    break;
                case R.id.checkBox2://全选本品牌商品
                    if (carts.get(pos).getIsType() == 2) {
                        int npos = pos;
                        boolean tbool = false;
                        if (carts.get(pos).isCheckAll()) {
                            carts.get(pos).setCheckAll(false);
                            tbool = false;
                        } else {
                            carts.get(pos).setCheckAll(true);
                            tbool = true;
                        }
                        npos += 1;
                        while (npos < carts.size() && carts.get(npos).getIsType() == 1) {
                            carts.get(npos).setCheckOnce(tbool);
                            npos += 1;
                        }
                    }
                    setTotal();
                    break;
                case R.id.imageView43://减少商品数量
                    int num = carts.get(pos).getGoods().getGoods_number();
                    if (num <= 1) {
                        num = 1;
                    } else {
                        num -= 1;
                    }
                    carts.get(pos).getGoods().setGoods_number(num);
                    setTotal();
                    modifyCartNum(carts.get(pos).getGoods().getRec_id(), num);
                    break;
                case R.id.imageView44://增加商品数量
                    int _num = carts.get(pos).getGoods().getGoods_number();
                    _num += 1;
                    carts.get(pos).getGoods().setGoods_number(_num);
                    setTotal();
                    mAdapter.notifyDataSetChanged();
                    modifyCartNum(carts.get(pos).getGoods().getRec_id(), _num);
                    break;
                case R.id.textView111: //删除单件购物车商品
                    List<Integer> rec_ids = new ArrayList<>();
                    rec_ids.add(carts.get(pos).getGoods().getRec_id());
                    carts.remove(pos);
                    mAdapter.remove(pos);

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
                    delCartGoods(rec_ids);
                    break;
                case R.id.textView110://收藏商品
                    List<Integer> goods_ids = new ArrayList<>();
                    goods_ids.add(carts.get(pos).getGoods().getGoods_id());
                    carts.remove(pos);
                    mAdapter.remove(pos);

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
                    collectCartGoods(goods_ids);
                    break;
                case R.id.textView109://找相似商品
                    break;
                case R.id.textView82://购物车无商品去找找看
                    break;
            }
            mAdapter.notifyDataSetChanged();
        }
    };
}
