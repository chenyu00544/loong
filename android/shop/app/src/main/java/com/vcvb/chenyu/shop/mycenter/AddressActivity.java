package com.vcvb.chenyu.shop.mycenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.address.AddressAddItem;
import com.vcvb.chenyu.shop.adapter.item.address.AddressErrorItem;
import com.vcvb.chenyu.shop.adapter.item.address.AddressItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.ConfirmDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class AddressActivity extends BaseRecyclerViewActivity {
    private List<AddressBean> addresses = new ArrayList<>();

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    private ConfirmDialog confirmDialog;

    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        initRefresh();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView title = (TextView) findViewById(R.id.textView123);
        title.setText(R.string.address);
        TextView add = (TextView) findViewById(R.id.textView122);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddressActivity(0, -1);
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = (RecyclerView) findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(context,
                ToolUtils.dip2px(context, 4));
        mRecyclerView.addItemDecoration(defaultItemDecoration);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        confirmDialog = new ConfirmDialog(context);
        confirmDialog.setTitle(R.string.no_collection);
    }

    @Override
    public void initRefresh() {
        super.initRefresh();
        refreshLayout = (RefreshLayout) findViewById(R.id.collection_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mAdapter.clear();
                getData(false);
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
//            }
//        });
    }

    @Override
    public void getData(final boolean b) {
        super.getData(b);
        if (b) {
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
                        if (b) {
                            loadingDialog.dismiss();
                        }

                        if (addresses.size() != 0) {
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
                        if (b) {
                            loadingDialog.dismiss();
                        }
//                        setNoDateByView();
                    }
                });
            }
        });

        addresses.clear();
//        AddressBean bean = new AddressBean();
//        bean.setIsType(-1);
//        bean.setUserName("user_name");
//        addresses.add(bean);
        for (int i = 0; i < 5; i++) {
            AddressBean bean = new AddressBean();
            if (i == 0) {
                bean.setDef(true);
            }
            bean.setConsignee("setUserName" + i);
            bean.setMobile("setPhoneMun" + i);
            bean.setAddress_id(i);
            bean.setAddress("setAddressInfo" + i);
            addresses.add(bean);
        }
        AddressBean bean = new AddressBean();
        addresses.add(bean);
        mAdapter.addAll(getItems(addresses));
    }

    @Override
    public void initListener() {
        super.initListener();

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                if (position == addresses.size() - 1) {
                    //添加
                    goToAddressActivity(0, -1);
                } else {
                    //修改
                    goToAddressActivity(1, position);
                }
            }
        });

        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.checkBox5).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                for (int i = 0; i < addresses.size(); i++) {
                    addresses.get(i).setDef(false);
                }
                addresses.get(position).setDef(true);
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.view37).setOnChildClickListener1(new CYCItemClickSupport.OnChildItemClickListener1() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                pos = position;
                confirmDialog.show();
            }
        });

        confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
            @Override
            public void onConfirmClickListener() {
                removeAddresses();
                confirmDialog.dismiss();
            }

            @Override
            public void onCancelClickListener() {
                confirmDialog.dismiss();
            }
        });
    }

    protected List<Item> getItems(List<AddressBean> list) {
        List<Item> cells = new ArrayList<>();
        if (list == null) {
            cells.add(new AddressErrorItem(null, context));
        } else {
            for (int i = 0; i < list.size(); i++) {
                cells.add(new AddressItem(list.get(i), context));
            }
            cells.add(new AddressAddItem(null, context));
        }

        return cells;
    }

    public void removeAddresses() {
        addresses.remove(pos);
        mAdapter.remove(pos);
        mAdapter.notifyDataSetChanged();
    }

    public void goToAddressActivity(int type, int pos) {
        Intent intent = new Intent(AddressActivity.this, ModifyAddressActivity.class);
        AddressBean bean;
        if (type == 1 && pos != -1) {
            bean = addresses.get(pos);
            intent.putExtra("id", bean.getAddress_id());
            intent.putExtra("username", bean.getConsignee());
            intent.putExtra("phone", bean.getMobile());
            String address_fromat = "%s %s %s %s";
            intent.putExtra("info", String.format(address_fromat, bean.getProvince_name(), bean
                    .getCity_name(), bean.getDistrict_name(), bean.getAddress()));
        }
        intent.putExtra("type", type);
        startActivityForResult(intent, ConstantManager.ResultStatus.ADDRESSRESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String result = data.getExtras().getString("result");
            Log.i("TAG", String.valueOf(requestCode));
            Log.i("TAG", String.valueOf(resultCode));
            Log.i("TAG", result);
        }
    }
}
