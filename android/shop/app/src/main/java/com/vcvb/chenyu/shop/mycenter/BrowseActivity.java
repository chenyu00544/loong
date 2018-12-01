package com.vcvb.chenyu.shop.mycenter;

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

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseErrorItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseTitleItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.javaBean.browse.Browse;
import com.vcvb.chenyu.shop.javaBean.collection.CollectionBean;
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
                    JSONArray arr = json.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject object = (JSONObject) arr.get(i);
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
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bool;
                if (cb.isChecked() == true) {
                    bool = true;
                } else {
                    bool = false;
                }
                for (int i = 0; i < browses.size(); i++) {
//                    collections.get(i).setIsSelect(bool);
//                    collections.get(i).setIsSelectAll(bool);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItems();
            }
        });

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                Intent intent = new Intent(BrowseActivity.this, GoodsDetailActivity.class);
                startActivity(intent);
            }
        });

        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.checkBox6).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                mAdapter.notifyDataSetChanged();
//                if (collections.get(position).getIsType() == 1) {
//                    int ppos = position;
//                    int npos = position;
//                    boolean tbool = false;
//                    if (collections.get(position).getIsSelect() == true) {
//                        collections.get(position).setIsSelect(false);
//                        tbool = false;
//                    } else {
//                        collections.get(position).setIsSelect(true);
//                        tbool = true;
//                    }
//                    npos += 1;
//                    while (collections.get(npos).getIsType() == 1) {
//                        if (collections.get(npos).getIsSelect() == false) {
//                            tbool = false;
//                        }
//                        npos += 1;
//                    }
//                    ppos -= 1;
//                    while (collections.get(ppos).getIsType() == 1) {
//                        if (collections.get(ppos).getIsSelect() == false) {
//                            tbool = false;
//                        }
//                        ppos -= 1;
//                    }
//                    collections.get(ppos).setIsSelectAll(tbool);
//                }
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.checkBox7).setOnChildClickListener1(new CYCItemClickSupport.OnChildItemClickListener1() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
//                if (collections.get(position).getIsType() == 2) {
//                    int npos = position;
//                    boolean tbool = false;
//                    if (collections.get(position).getIsSelectAll() == true) {
//                        collections.get(position).setIsSelectAll(false);
//                        tbool = false;
//                    } else {
//                        collections.get(position).setIsSelectAll(true);
//                        tbool = true;
//                    }
//                    npos += 1;
//                    while (collections.get(npos).getIsType() == 1) {
//                        collections.get(npos).setIsSelect(tbool);
//                        npos += 1;
//                    }
//                }
                mAdapter.notifyDataSetChanged();
            }
        });

        //找相似
        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.textView133).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {

            }
        });


    }

    protected List<Item> getItems(List<Browse> list) {
        List<Item> cells = new ArrayList<>();
        Integer group = 0;
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if(!list.get(i).getGroup().equals(group)){
                    cells.add(new BrowseTitleItem(list.get(i), context));
                    group = list.get(i).getGroup();
                }
                cells.add(new BrowseItem(list.get(i), context));
            }
        } else {
            cells.add(new BrowseErrorItem(null, context));
        }
        return cells;
    }

    public synchronized void deleteItems() {
        List<CollectionBean> collections_bak = new ArrayList<>();
        for (int i = 0; i < browses.size(); i++) {
            synchronized (this) {
//                if (collections.get(i).getIsType() == 1) {
//                    if (collections.get(i).getIsSelect() == true) {
//                        collections_bak.add(collections.get(i));
//                    }
//                } else if (collections.get(i).getIsType() == 2) {
//                    if (collections.get(i).getIsSelectAll() == true) {
//                        collections_bak.add(collections.get(i));
//                    }
//                }
            }
        }
        browses.removeAll(collections_bak);
        mAdapter.clear();
        mAdapter.addAll(getItems(browses));
        over();
        mAdapter.notifyDataSetChanged();
    }

    public void edit() {
        edit.setText(R.string.over);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 50));
        for (int i = 0; i < browses.size(); i++) {
//            collections.get(i).setIsLong(true);
        }
        set.applyTo(cly);
    }

    public void over() {
        edit.setText(R.string.edit);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 0));
        for (int i = 0; i < browses.size(); i++) {
//            collections.get(i).setIsLong(false);
        }
        set.applyTo(cly);
    }
}
