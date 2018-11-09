package com.vcvb.chenyu.shop.mycenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.address.AddressAddItem;
import com.vcvb.chenyu.shop.adapter.item.address.AddressErrorItem;
import com.vcvb.chenyu.shop.adapter.item.address.AddressItem;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.ConfirmDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;
import com.vcvb.chenyu.shop.javaBean.address.Country;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
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

public class AddressActivity extends BaseRecyclerViewActivity {

    IDataStorage dataStorage;
    private List<AddressBean> addresses = new ArrayList<>();

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    private ConfirmDialog confirmDialog;

    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);
        context = this;
        changeStatusBarTextColor(true);
        dataStorage = DataStorageFactory.getInstance(context, DataStorageFactory.TYPE_DATABASE);
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        setNavBack();
        initView();
        initRefresh();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.address);
        TextView add = findViewById(R.id.textView122);
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
        mRecyclerView = findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(context,
                ToolUtils.dip2px(context, 4));
        mRecyclerView.addItemDecoration(defaultItemDecoration);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        confirmDialog = new ConfirmDialog(context);
        confirmDialog.setTitle(R.string.is_delete);
    }

    @Override
    public void initRefresh() {
        super.initRefresh();
        refreshLayout = findViewById(R.id.collection_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mAdapter.clear();
                getData(false);
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.USERADDRESSES, mp, new HttpUtils.NetCall
                () {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        bindData(json);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                    }
                });
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Country> countries = dataStorage.loadAll(Country.class);
                if (countries == null || countries.size() == 0) {
                    HttpUtils.getInstance().post(ConstantManager.Url.ALLREGION, null, new
                            HttpUtils.NetCall() {
                        @Override
                        public void success(Call call, JSONObject json) throws IOException {
                            if (json != null) {
                                try {
                                    JSONArray countryJSONArray = json.getJSONArray("data");
                                    List<Country> countries = new ArrayList<>();
                                    for (int i = 0; i < countryJSONArray.length(); i++) {
                                        JSONObject object = countryJSONArray.getJSONObject(i);
                                        Country country = JsonUtils.fromJsonObject(object,
                                                Country.class);
                                        country.setData(object.getJSONArray("province"));
                                        countries.add(country);
                                    }
                                    //持久化
                                    dataStorage.storeOrUpdate(countries);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (java.lang.InstantiationException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void failed(Call call, IOException e) {

                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void bindData(JSONObject json) {
        addresses.clear();
        mAdapter.clear();
        if (json != null) {
            try {
                JSONArray addressesJSONArray = json.getJSONArray("data");
                for (int i = 0; i < addressesJSONArray.length(); i++) {
                    JSONObject object = (JSONObject) addressesJSONArray.get(i);
                    AddressBean addressBean = JsonUtils.fromJsonObject(object, AddressBean.class);
                    addresses.add(addressBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        mAdapter.addAll(getItems(addresses));
    }

    @Override
    public void initListener() {
        super.initListener();

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
        if (list.size() == 0) {
            cells.add(new AddressErrorItem(null, context));
        } else {
            for (int i = 0; i < list.size(); i++) {
                AddressItem addressItem = new AddressItem(list.get(i), context);
                addressItem.setOnItemClickListener(addressListener);
                cells.add(addressItem);
            }
            AddressAddItem addressAddItem = new AddressAddItem(null, context);
            addressAddItem.setOnItemClickListener(addListener);
            cells.add(addressAddItem);
        }
        return cells;
    }

    public void removeAddresses() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("address_id", addresses.get(p).getAddress_id() + "");
        HttpUtils.getInstance().post(ConstantManager.Url.DELADDRESS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if(json != null){
                    try {
                        Integer code = json.getInt("code");
                        if(code == 0){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addresses.remove(p);
                                    mAdapter.remove(p);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    public void setDefAddress(final Integer pos){
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("address_id", addresses.get(pos).getAddress_id() + "");
        HttpUtils.getInstance().post(ConstantManager.Url.SETDEFADDRESS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                System.out.println(json);
                if(json != null){
                    try {
                        Integer code = json.getInt("code");
                        if(code == 0){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < addresses.size(); i++) {
                                        addresses.get(i).setDef(0);
                                    }
                                    addresses.get(pos).setDef(1);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShortToast(context, "网路错误");
                    }
                });
            }
        });
    }

    public void goToAddressActivity(int type, int pos) {
        Intent intent = new Intent(AddressActivity.this, ModifyAddressActivity.class);
        AddressBean bean;
        if (type == 1 && pos != -1) {
            bean = addresses.get(pos);
            intent.putExtra("id", bean.getAddress_id());
        }
        intent.putExtra("type", type);
        startActivityForResult(intent, ConstantManager.ResultStatus.ADDRESSRESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            getData(false);
        }
    }

    AddressAddItem.OnClickListener addListener = new AddressAddItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goToAddressActivity(0, -1);
        }
    };

    AddressItem.OnClickListener addressListener = new AddressItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            switch (view.getId()) {
                case R.id.view5:
                    goToAddressActivity(1, pos);
                    break;
                case R.id.checkBox5:
                case R.id.textView124:
                    setDefAddress(pos);
                    break;
                case R.id.view37:
                    p = pos;
                    confirmDialog.show();
                    break;
            }
        }
    };
}
