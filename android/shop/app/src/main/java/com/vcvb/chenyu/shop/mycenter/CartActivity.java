package com.vcvb.chenyu.shop.mycenter;

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
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
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

public class CartActivity extends BaseRecyclerViewActivity {
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
                ConstraintLayout payFoot = view.findViewById(R.id.pay_foot);
                Toast.makeText(context, "editView", Toast.LENGTH_SHORT).show();
                ConstraintLayout.LayoutParams lp;

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

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.clear();
                carts.remove(carts);
                mAdapter.notifyDataSetChanged();
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
                    while (carts.get(npos).getIsType() == 1) {
                        carts.get(npos).setCheckOnce(tbool);
                        npos += 1;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });

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
                    while (carts.get(npos).getIsType() == 1) {
                        if (carts.get(npos).isCheckOnce()) {
                            tbool = true;
                        }
                        npos += 1;
                    }
                    ppos -= 1;
                    while (carts.get(ppos).getIsType() == 1) {
                        if (carts.get(ppos).isCheckOnce()) {
                            tbool = true;
                        }
                        ppos -= 1;
                    }
                    carts.get(ppos).setCheckAll(tbool);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.imageView43).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(4);
                clearLong();
                int num = carts.get(position).getGoods().getGoods_number();
                if (num <= 1) {
                    num = 1;
                } else {
                    num -= 1;
                }
                carts.get(position).getGoods().setGoods_number(num);
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo3(mRecyclerView, R.id.imageView44).setOnChildClickListener3
                (new CYCItemClickSupport.OnChildItemClickListener3() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(5);
                clearLong();
                int num = carts.get(position).getGoods().getGoods_number();
                num += 1;
                carts.get(position).getGoods().setGoods_number(num);
                mAdapter.notifyDataSetChanged();
            }
        });
        CYCItemClickSupport.BuildTo4(mRecyclerView, R.id.textView109).setOnChildClickListener4
                (new CYCItemClickSupport.OnChildItemClickListener4() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(6);
                mAdapter.notifyDataSetChanged();
            }
        });
        CYCItemClickSupport.BuildTo5(mRecyclerView, R.id.textView110).setOnChildClickListener5
                (new CYCItemClickSupport.OnChildItemClickListener5() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(7);
                mAdapter.notifyDataSetChanged();
            }
        });
        CYCItemClickSupport.BuildTo6(mRecyclerView, R.id.textView111).setOnChildClickListener6
                (new CYCItemClickSupport.OnChildItemClickListener6() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(8);
                carts.remove(position);
                mAdapter.remove(position);
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
                System.out.println(1);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //数据获取操作
    public void getCartData(final boolean bool) {
        if (bool) {
            loadingDialog.show();
        }
        if (token != null && !token.equals("")) {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("token", token);
            HttpUtils.getInstance().post(ConstantManager.Url.CARTLIST, mp, new HttpUtils.NetCall() {
                @Override
                public void success(Call call, final JSONObject json) throws IOException {
                    runOnUiThread(new Runnable() {
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
                    runOnUiThread(new Runnable() {
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
            carts.clear();
            mAdapter.addAll(getItems(carts));
        }
    }

    public void bindData(JSONObject json) {
        carts.clear();
        if (json != null) {
            try {
                if (json.getInt("code") == 0) {
                    JSONArray jsonArray = json.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject cartJson = (JSONObject) jsonArray.get(i);
                        if (i == 0) {
                            CartListBean c = new CartListBean();
                            Shop shop = JsonUtils.fromJsonObject(cartJson.getJSONObject("store"),
                                    Shop.class);
                            c.setShop(shop);
                            carts.add(c);
                        }
                        CartListBean cart = new CartListBean();
                        Goods goods = JsonUtils.fromJsonObject(cartJson.getJSONObject("goods"),
                                Goods.class);
                        cart.setGoods(goods);
                        carts.add(cart);
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

        View payFoot = findViewById(R.id.pay_foot);
        CheckBox cb = findViewById(R.id.checkBox4);
        if (carts.size() > 0) {
            mAdapter.clear();
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
            set.applyTo(cly);
        }
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
                    total += Double.valueOf(carts.get(i).getGoods().getShop_price());
                }
            }
        }
        totalView.setText(String.format("￥%.2f", total));
    }
}
