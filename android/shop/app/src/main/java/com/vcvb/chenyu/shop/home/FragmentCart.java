package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CartRecyclerViewAdapter;
import com.vcvb.chenyu.shop.adapter.spacesitem.CartItemDecoration;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.overrideView.LoadingDialog;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

import static com.vcvb.chenyu.shop.adapter.CartRecyclerViewAdapter.OnRecyclerViewItemClickListener;
import static com.vcvb.chenyu.shop.adapter.CartRecyclerViewAdapter
        .OnRecyclerViewItemLongClickListener;
import static com.vcvb.chenyu.shop.adapter.CartRecyclerViewAdapter.ViewName;

public class FragmentCart extends Fragment {
    View view;
    Context context;

    private TextView editView;
    private ImageView msgView;
    private TextView msgNum;
    private TextView toPay;

    private RecyclerView mRecyclerView;
    private CartRecyclerViewAdapter mAdapter;
    private List<CartListBean> carts = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = getActivity();
        getCartData(true);
        initView();
        initListener();
        return view;
    }

    public void initView() {
        editView = view.findViewById(R.id.textView96);
        msgView = view.findViewById(R.id.imageView42);
        msgNum = view.findViewById(R.id.textView99);
        toPay = view.findViewById(R.id.textView102);

        mRecyclerView = view.findViewById(R.id.cart_content);
        mAdapter = new CartRecyclerViewAdapter(context, carts);
        mLayoutManager = new GridLayoutManager(context, 1);
        CartItemDecoration spaces = new CartItemDecoration(context, carts);
        mRecyclerView.addItemDecoration(spaces);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, ViewName viewName, int position) {
                int num;
                if (viewName != ViewName.FIND && viewName != ViewName.COLLECTION && viewName !=
                        ViewName.DELETE) {
                    for (int i = 0; i < carts.size(); i++) {
                        carts.get(i).setIsLong(false);
                    }
                }
                switch (viewName) {
                    case ADD:
                        num = carts.get(position).getGoodsNum();
                        num += 1;
                        carts.get(position).setGoodsNum(num);
                        break;
                    case SUB:
                        num = carts.get(position).getGoodsNum();
                        if (num <= 1) {
                            num = 1;
                        } else {
                            num -= 1;
                        }
                        carts.get(position).setGoodsNum(num);
                        break;
                    case CHECKBOX:
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
                            while (carts.get(npos).getIsType() == 1) {
                                npos += 1;
                                if (carts.get(npos).getIsChecOnce() == true) {
                                    tbool = true;
                                }
                            }
                            while (carts.get(ppos).getIsType() == 1) {
                                ppos -= 1;
                                if (carts.get(ppos).getIsChecOnce() == true) {
                                    tbool = true;
                                }
                            }
                            carts.get(ppos).setIsCheckAll(tbool);
                        } else if (carts.get(position).getIsType() == 2) {
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
                        break;
                    case ITEM:
                        if (carts.get(position).getIsType() == 1) {
                            Intent intent = new Intent(context, GoodsDetailActivity.class);
                            intent.putExtra("goods_id", "1");
                            startActivity(intent);
                        } else {

                        }
                        break;
                    case FIND:
                        Toast.makeText(context, "FIND", Toast.LENGTH_LONG).show();
                        break;
                    case COLLECTION:
                        Toast.makeText(context, "COLLECTION", Toast.LENGTH_LONG).show();
                        break;
                    case DELETE:
                        Toast.makeText(context, "DELETE", Toast.LENGTH_LONG).show();
                        break;
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter.setOnItemLongClickListener(new OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onLongClick(View view, int position) {
                View v = view;
                if (carts.get(position).getIsType() == 1) {
                    for (int i = 0; i < carts.size(); i++) {
                        carts.get(i).setIsLong(false);
                    }
                    carts.get(position).setIsLong(true);
                    mAdapter.notifyDataSetChanged();
                    return true;
                } else {
                    return false;
                }
            }
        });

        refreshLayout = view.findViewById(R.id.cart_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        if (carts.size() > 0) {
            if (carts.get(0).getIsType() == 3) {

            }
        }
    }


    public void initListener() {

    }


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
                getActivity().runOnUiThread(new Runnable() {
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
                getActivity().runOnUiThread(new Runnable() {
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
        CartListBean bean = new CartListBean();
        bean.setIsType(3);
        bean.setStoreName("store_name");
        carts.add(bean);
//        for (int i = 0; i < 5; i++) {
//            CartListBean bean = new CartListBean();
//            bean.setIsType(2);
//            bean.setStoreName("store_name" + i);
//            carts.add(bean);
//            for (int j = 0; j < 3; j++) {
//                bean = new CartListBean();
//                bean.setIsType(1);
//                bean.setGoodsName("goods_name" + j);
//                bean.setGoodsNum(j);
//                carts.add(bean);
//            }
//        }
    }
}
