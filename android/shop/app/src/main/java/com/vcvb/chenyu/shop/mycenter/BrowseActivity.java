package com.vcvb.chenyu.shop.mycenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseErrorItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseTitleItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.browse.Browse;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class BrowseActivity extends BaseRecyclerViewActivity {
    private List<Browse> browses = new ArrayList<>();
    public LoadingDialog loadingDialog;
    private TextView edit;
    private View bottom;
    private CheckBox cb;
    private TextView delete;

    private ConstraintSet set;
    private ConstraintLayout cly;

    private Integer page = 1;

    private RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_list);
        context = this;
        changeStatusBarTextColor(true);
        set = new ConstraintSet();
        cly = findViewById(R.id.browse);
        set.clone(cly);
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
        title.setText(R.string.browse);
        edit = findViewById(R.id.textView122);
        edit.setText(R.string.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.getText() == context.getString(R.string.edit)) {
                    edit();
                } else {
                    over();
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.content);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        bottom = findViewById(R.id.view38);
        cb = findViewById(R.id.checkBox);
        delete = findViewById(R.id.textView130);
    }

    @Override
    public void getData(final boolean b) {
        page = 1;
        super.getData(b);
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("page", page + "");
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.BROWSE_GOODS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        Integer code = json.getInt("code");
                        if (code == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (b) {
                                        loadingDialog.dismiss();
                                    }
                                    bindViewData(json);
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
                        if (b) {
                            loadingDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    public void initRefresh() {
        refreshLayout = findViewById(R.id.browse_list);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData(false);
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadmore();
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }

    public void bindViewData(JSONObject json) {
        browses.clear();
        mAdapter.clear();
        if (json != null) {
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    String group = "";
                    JSONArray arr = json.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject object = (JSONObject) arr.get(i);
                        if (!object.getString("group").equals(group)) {
                            group = object.getString("group");
                            Browse bean = new Browse();
                            bean.setAdd_time_format(object.getString("add_time_format"));
                            browses.add(bean);
                        }
                        Browse bean = JsonUtils.fromJsonObject(object, Browse.class);
                        bean.setData(object);
                        browses.add(bean);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        mAdapter.addAll(getItems(browses));
    }

    public void loadmore() {
        HashMap<String, String> mp = new HashMap<>();
        page += 1;
        mp.put("token", token);
        mp.put("page", page + "");
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bindViewMoreData(json);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    public void bindViewMoreData(JSONObject json) {
        if (json != null) {

        }
    }

    @Override
    public void initListener() {
        super.initListener();
        cb.setOnClickListener(onClickListener);
        delete.setOnClickListener(onClickListener);
    }

    protected List<Item> getItems(List<Browse> list) {
        List<Item> cells = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getGoods() == null) {
                    BrowseTitleItem browseTitleItem = new BrowseTitleItem(list.get(i), context);
                    browseTitleItem.setOnItemClickListener(browseTitleListener);
                    cells.add(browseTitleItem);
                } else {
                    BrowseItem browseItem = new BrowseItem(list.get(i), context);
                    browseItem.setOnItemClickListener(browseListener);
                    cells.add(browseItem);
                }
            }
        } else {
            cells.add(new BrowseErrorItem(null, context));
        }
        return cells;
    }

    public synchronized void deleteItems() {
//        List<Browse> collections_bak = new ArrayList<>();
//        String group = "";
//        for (int i = 0; i < browses.size(); i++) {
//            synchronized (this) {
//                if (browses.get(i).getIsType() == 1) {
//                    if (browses.get(i).isSelect()) {
//                        collections_bak.add(browses.get(i));
//                    }
//                } else if (browses.get(i).getIsType() == 2) {
//                    if (browses.get(i).isSelectAll()) {
//                        collections_bak.add(browses.get(i));
//                    }
//                }
//            }
//        }
//        browses.removeAll(collections_bak);
//        mAdapter.clear();
//        mAdapter.addAll(getItems(browses));
//        over();
//        mAdapter.notifyDataSetChanged();
    }

    public void edit() {
        edit.setText(R.string.over);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 50));
        for (int i = 0; i < browses.size(); i++) {
            browses.get(i).setLong(true);
        }
        set.applyTo(cly);
    }

    public void over() {
        edit.setText(R.string.edit);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 0));
        for (int i = 0; i < browses.size(); i++) {
            browses.get(i).setLong(false);
        }
        set.applyTo(cly);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkBox:
                    boolean bool;
                    if (cb.isChecked()) {
                        bool = true;
                    } else {
                        bool = false;
                    }
                    for (int i = 0; i < browses.size(); i++) {
                        browses.get(i).setSelect(bool);
                        browses.get(i).setSelectAll(bool);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.textView130:
                    deleteItems();
                    break;
            }
        }
    };

    BrowseTitleItem.OnClickListener browseTitleListener = new BrowseTitleItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            switch (view.getId()) {
                case R.id.checkBox7://全选
                    int npos = pos;
                    boolean tbool = false;
                    if (browses.get(pos).isSelectAll()) {
                        browses.get(pos).setSelectAll(false);
                        tbool = false;
                    } else {
                        browses.get(pos).setSelectAll(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (npos < browses.size() && browses.get(npos).getGoods() != null) {
                        browses.get(npos).setSelect(tbool);
                        npos += 1;
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    BrowseItem.OnClickListener browseListener = new BrowseItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            switch (view.getId()) {
                case R.id.checkBox6://单选
                    if (browses.get(pos).isSelect()) {
                        browses.get(pos).setSelect(false);
                    } else {
                        browses.get(pos).setSelect(true);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.imageView58:
                    Intent intent = new Intent(BrowseActivity.this, GoodsDetailActivity.class);
                    startActivity(intent);
                    break;
                case R.id.textView133://找相似
                    break;
            }
        }
    };
}
