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
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.cart.CartErrorItem;
import com.vcvb.chenyu.shop.adapter.item.cart.CartHeaderItem;
import com.vcvb.chenyu.shop.adapter.item.cart.CartItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.spacesitem.CartItemDecoration;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class CartActivity extends BaseRecyclerViewActivity {
    Context context;
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
                    carts.get(i).setIsChecOnce(bool);
                    if (carts.get(i).getIsType() == 2) {
                        carts.get(i).setIsCheckAll(bool);
                    }
                }
                setTotal();
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.checkBox2).setOnChildClickListener(new CYCItemClickSupport
                .OnChildItemClickListener() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                if (carts.get(position).getIsType() == 2) {
                    int npos = position;
                    boolean tbool = false;
                    if (carts.get(position).getIsCheckAll() == true) {
                        carts.get(position).setIsCheckAll(false);
                        tbool = false;
                    } else {
                        carts.get(position).setIsCheckAll(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (carts.get(npos).getIsType() == 1) {
                        carts.get(npos).setIsChecOnce(tbool);
                        npos += 1;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.checkBox3).setOnChildClickListener1(new CYCItemClickSupport
                .OnChildItemClickListener1() {

            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                clearLong();
                if (carts.get(position).getIsType() == 1) {
                    int ppos = position;
                    int npos = position;
                    boolean tbool = false;
                    if (carts.get(position).getIsChecOnce() == true) {
                        carts.get(position).setIsChecOnce(false);
                        tbool = false;
                    } else {
                        carts.get(position).setIsChecOnce(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (carts.get(npos).getIsType() == 1) {
                        if (carts.get(npos).getIsChecOnce() == true) {
                            tbool = true;
                        }
                        npos += 1;
                    }
                    ppos -= 1;
                    while (carts.get(ppos).getIsType() == 1) {
                        if (carts.get(ppos).getIsChecOnce() == true) {
                            tbool = true;
                        }
                        ppos -= 1;
                    }
                    carts.get(ppos).setIsCheckAll(tbool);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.imageView43).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {

                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
                        System.out.println(4);
                        clearLong();
                        int num = carts.get(position).getGoodsNum();
                        if (num <= 1) {
                            num = 1;
                        } else {
                            num -= 1;
                        }
                        carts.get(position).setGoodsNum(num);
                        mAdapter.notifyDataSetChanged();
                    }
                });

        CYCItemClickSupport.BuildTo3(mRecyclerView, R.id.imageView44).setOnChildClickListener3
                (new CYCItemClickSupport.OnChildItemClickListener3() {

                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
                        System.out.println(5);
                        clearLong();
                        int num = carts.get(position).getGoodsNum();
                        num += 1;
                        carts.get(position).setGoodsNum(num);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        CYCItemClickSupport.BuildTo4(mRecyclerView, R.id.textView109).setOnChildClickListener4
                (new CYCItemClickSupport.OnChildItemClickListener4() {

                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
                        System.out.println(6);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        CYCItemClickSupport.BuildTo5(mRecyclerView, R.id.textView110).setOnChildClickListener5
                (new CYCItemClickSupport.OnChildItemClickListener5() {

                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
                        System.out.println(7);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        CYCItemClickSupport.BuildTo6(mRecyclerView, R.id.textView111).setOnChildClickListener6
                (new CYCItemClickSupport.OnChildItemClickListener6() {

                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int
                            position) {
                        System.out.println(8);
                        carts.remove(position);
                        mAdapter.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new CYCItemClickSupport
                .OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int
                    position) {
                clearLong();
                carts.get(position).setIsLong(true);
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
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", "");
        mp.put("nav_id", "0");
//        mp.put("order_type", ""+type);
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bool) {
                            loadingDialog.dismiss();
                        }

                        if (carts.size() != 0) {
//                            setHaveDataByView();
                        } else {
//                            setNoDateByView();
                        }
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
//                        setNoDateByView();
                    }
                });
            }
        });

        carts.clear();
//        CartListBean bean = new CartListBean();
//        bean.setIsType(-1);
//        bean.setStoreName("store_name");
//        carts.add(bean);
        for (int i = 0; i < 5; i++) {
            CartListBean bean = new CartListBean();
            bean.setIsType(2);
            bean.setStoreName("store_name" + i);
            carts.add(bean);
            for (int j = 0; j < 3; j++) {
                bean = new CartListBean();
                bean.setIsType(1);
                bean.setGoodsName("goods_name" + j);
                bean.setGoodsNum(j);
                bean.setGoodsPic(Images.imageUrls[j + i * 3]);
                bean.setGoodsPrice(j * 10.00);
                bean.setGoodsMarket(j * 100.00);
                carts.add(bean);
            }
        }
        View payFoot = findViewById(R.id.pay_foot);
        CheckBox cb = findViewById(R.id.checkBox4);
        if (carts.size() > 0) {
            mAdapter.clear();
            if (carts.get(0).getIsType() == -1) {
                set.connect(cb.getId(), ConstraintSet.TOP, payFoot.getId(), ConstraintSet.TOP,
                        0);
                set.connect(cb.getId(), ConstraintSet.BOTTOM, payFoot.getId(), ConstraintSet.BOTTOM,
                        0);
                set.constrainHeight(payFoot.getId(), 1);
                cb.getBackground().mutate().setAlpha(0);
            } else {
                set.connect(cb.getId(), ConstraintSet.TOP, payFoot.getId(), ConstraintSet.TOP,
                        ToolUtils.dip2px(context, 8));
                set.connect(cb.getId(), ConstraintSet.BOTTOM, payFoot.getId(), ConstraintSet.BOTTOM,
                        ToolUtils.dip2px(context, 8));
                set.constrainHeight(payFoot.getId(), ToolUtils.dip2px(context, 50));
                cb.getBackground().mutate().setAlpha(255);
            }
            set.applyTo(cly);
        }
        mAdapter.addAll(getItems(carts));
    }

    protected List<Item> getItems(List<CartListBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case -1:
                    cells.add(new CartErrorItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new CartHeaderItem(list.get(i), context));
                    break;
                case 1:
                    cells.add(new CartItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    //清理长按显示状态
    public void clearLong() {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getIsLong() == true) {
                carts.get(i).setIsLong(false);
            }
        }
    }

    //设置订单总金额
    public void setTotal() {
        double total = 0;
        for (int i = 0; i < carts.size(); i++){
            if(carts.get(i).getIsType() == 1){
                if(carts.get(i).getIsChecOnce() == true){
                    total += carts.get(i).getGoodsPrice();
                }
            }
        }
        totalView.setText(String.format("￥%.2f", total));
    }
}
