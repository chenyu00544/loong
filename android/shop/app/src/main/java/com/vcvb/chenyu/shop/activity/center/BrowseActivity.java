package com.vcvb.chenyu.shop.activity.center;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.activity.search.SearchInfoActivity;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.b.Item;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseErrorItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseTitleItem;
import com.vcvb.chenyu.shop.base.BaseGroupRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.browse.Browse;
import com.vcvb.chenyu.shop.javaBean.browse.GroupBrowse;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class BrowseActivity extends BaseGroupRecyclerViewActivity {
    private List<GroupBrowse> groupBrowses = new ArrayList<>();
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
                groupedListAdapter.notifyDataChanged();
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.content);
        groupedListAdapter = new GroupedListAdapter(context);
        mRecyclerView.setAdapter(groupedListAdapter);
        bottom = findViewById(R.id.view38);
        cb = findViewById(R.id.checkBox);
        delete = findViewById(R.id.textView130);
    }

    @Override
    public void initListener() {
        super.initListener();
        cb.setOnClickListener(onClickListener);
        delete.setOnClickListener(onClickListener);
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

    @Override
    public void getData(final boolean b) {
        over();
        page = 1;
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }
                        refreshLayout.finishRefresh();
                        if (json != null) {
                            try {
                                Integer code = json.getInt("code");
                                if (code == 0) {
                                    bindViewData(json);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
                        refreshLayout.finishRefresh();
                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        groupBrowses.clear();
        if (json != null) {
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    JSONArray arr = json.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        GroupBrowse groupBrowse = new GroupBrowse();
                        JSONObject object = (JSONObject) arr.get(i);
                        Browse header = new Browse();
                        header.setGroup(object.getString("group"));
                        groupBrowse.setHeader(header);
                        JSONArray browseJSONArray = object.getJSONArray("browse");
                        List<Object> browses = new ArrayList<>();
                        for (int j = 0; j < browseJSONArray.length(); j++) {
                            JSONObject jsonObject = (JSONObject) browseJSONArray.get(j);
                            Browse bean = JsonUtils.fromJsonObject(jsonObject, Browse.class);
                            bean.setData(jsonObject);
                            browses.add(bean);
                        }
                        groupBrowse.setObjs(browses);
                        groupBrowses.add(groupBrowse);
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
        if (groupedListAdapter != null) {
            groupedListAdapter.notifyDataRemoved();
        }
        groupedListAdapter.setData(getGroupItems(groupBrowses));
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 1, groupedListAdapter) {

            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                if (groupBrowses.get(groupPosition).getItemList().get(childPosition).getItemType
                        () == R.layout.categroy_ads_item) {
                    return 3;
                } else {
                    return super.getChildSpanSize(groupPosition, childPosition);
                }
            }
        };
        mRecyclerView.setLayoutManager(groupedGridLayoutManager);
    }

    public void loadmore() {
        over();
        page += 1;
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("page", page + "");
        HttpUtils.getInstance().post(ConstantManager.Url.BROWSE_GOODS, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json != null) {
                            try {
                                Integer code = json.getInt("code");
                                if (code == 0) {
                                    refreshLayout.finishLoadMore();
                                    bindViewMoreData(json);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                });
            }
        });
    }

    public void bindViewMoreData(JSONObject json) {
        groupedListAdapter.notifyDataChanged();
        int index = groupBrowses.size();
        List<Browse> _browses = new ArrayList<>();
        try {
            Integer code = json.getInt("code");
            if (code == 0) {
                JSONArray arr = json.getJSONArray("data");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject object = (JSONObject) arr.get(i);
                    if (((Browse) groupBrowses.get(index - 1).getHeader()).getGroup().equals
                            (object.getString("group"))) {
                        JSONArray browseJSONArray = object.getJSONArray("browse");
                        for (int j = 0; j < browseJSONArray.length(); j++) {
                            JSONObject jsonObject = (JSONObject) browseJSONArray.get(j);
                            Browse bean = JsonUtils.fromJsonObject(jsonObject, Browse.class);
                            bean.setData(jsonObject);
                            groupBrowses.get(index - 1).getObjs().add(bean);
                        }
                    } else {
                        GroupBrowse groupBrowse = new GroupBrowse();
                        Browse header = new Browse();
                        header.setGroup(object.getString("group"));
                        groupBrowse.setHeader(header);
                        JSONArray browseJSONArray = object.getJSONArray("browse");
                        List<Object> browses = new ArrayList<>();
                        for (int j = 0; j < browseJSONArray.length(); j++) {
                            JSONObject jsonObject = (JSONObject) browseJSONArray.get(j);
                            Browse bean = JsonUtils.fromJsonObject(jsonObject, Browse.class);
                            bean.setData(jsonObject);
                            browses.add(bean);
                        }
                        groupBrowse.setObjs(browses);
                        groupBrowses.add(groupBrowse);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        groupedListAdapter.setData(getGroupItems(groupBrowses));
        groupedListAdapter.notifyDataChanged();
    }

    protected List<GroupBrowse> getGroupItems(List<GroupBrowse> browses) {
        if (groupBrowses.size() > 0) {
            for (int i = 0; i < groupBrowses.size(); i++) {
                BrowseTitleItem browseTitleItem = new BrowseTitleItem(groupBrowses.get(i), context);
                browseTitleItem.setSubOnItemClickListener(browseTitleListener);
                groupBrowses.get(i).setMheader(browseTitleItem);
                List<Item> items = new ArrayList<>();
                for (int j = 0; j < groupBrowses.get(i).getObjs().size(); j++) {
                    BrowseItem browseItem = new BrowseItem(groupBrowses.get(i), context);
                    browseItem.setSubOnItemClickListener(browseListener);
                    items.add(browseItem);
                }
                groupBrowses.get(i).setItemList(items);
            }
        } else {
            List<Item> items = new ArrayList<>();
            BrowseErrorItem browseErrorItem = new BrowseErrorItem(null, context);
            items.add(browseErrorItem);
        }
        return groupBrowses;
    }

    public synchronized void deleteItems() {
        List<String> browseIds = new ArrayList<>();
        List<GroupBrowse> _groupBrowses = new ArrayList<>();
        for (int i = 0; i < groupBrowses.size(); i++) {
            synchronized (this) {
                if (((Browse) groupBrowses.get(i).getHeader()).isSelectAll()) {
                    _groupBrowses.add(groupBrowses.get(i));
                }
                List<Object> objects = new ArrayList<>();
                for (int j = 0; j < groupBrowses.get(i).getObjs().size(); j++) {
                    if (((Browse) groupBrowses.get(i).getObjs().get(j)).isSelect()) {
                        String browse_id_str = ((Browse) groupBrowses.get(i).getObjs().get(j))
                                .getBrowse_id_str();
                        browseIds.add(browse_id_str);
                        objects.add(groupBrowses.get(i).getObjs().get(j));
                    }
                }
                groupBrowses.get(i).getObjs().removeAll(objects);
            }
        }
        groupBrowses.removeAll(_groupBrowses);
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("browse_ids", StringUtils.join(browseIds, ","));
        HttpUtils.getInstance().post(ConstantManager.Url.SET_BROWSE_GOODS, mp, new HttpUtils
                .NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {

            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
        groupedListAdapter.notifyDataChanged();
        over();
    }

    public void edit() {
        edit.setText(R.string.over);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 50));
        for (int i = 0; i < groupBrowses.size(); i++) {
            ((Browse) groupBrowses.get(i).getHeader()).setLong(true);
            ((Browse) groupBrowses.get(i).getHeader()).setSelectAll(false);
            for (int j = 0; j < groupBrowses.get(i).getObjs().size(); j++) {
                ((Browse) groupBrowses.get(i).getObjs().get(j)).setLong(true);
                ((Browse) groupBrowses.get(i).getObjs().get(j)).setSelect(false);
            }
        }
        set.applyTo(cly);
    }

    public void over() {
        edit.setText(R.string.edit);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 0));
        for (int i = 0; i < groupBrowses.size(); i++) {
            ((Browse) groupBrowses.get(i).getHeader()).setLong(false);
            ((Browse) groupBrowses.get(i).getHeader()).setSelectAll(false);
            for (int j = 0; j < groupBrowses.get(i).getObjs().size(); j++) {
                ((Browse) groupBrowses.get(i).getObjs().get(j)).setLong(false);
                ((Browse) groupBrowses.get(i).getObjs().get(j)).setSelect(false);
            }
        }
        set.applyTo(cly);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkBox:
                    //底栏全选
                    boolean bool;
                    if (cb.isChecked()) {
                        bool = true;
                    } else {
                        bool = false;
                    }
                    for (int i = 0; i < groupBrowses.size(); i++) {
                        ((Browse) groupBrowses.get(i).getHeader()).setSelectAll(bool);
                        for (int j = 0; j < groupBrowses.get(i).getObjs().size(); j++) {
                            ((Browse) groupBrowses.get(i).getObjs().get(j)).setSelect(bool);
                        }
                    }
                    groupedListAdapter.notifyDataChanged();
                    break;
                case R.id.textView130:
                    //底栏删除
                    deleteItems();
                    break;
            }
        }
    };

    BrowseTitleItem.OnSubItemClickListener browseTitleListener = new BrowseTitleItem
            .OnSubItemClickListener() {
        @Override
        public void clicked(int group, int pos, View v) {
            switch (v.getId()) {
                case R.id.checkBox7://分组全选
                    boolean tbool = false;
                    if (((Browse) groupBrowses.get(group).getHeader()).isSelectAll()) {
                        ((Browse) groupBrowses.get(group).getHeader()).setSelectAll(false);
                        tbool = false;
                    } else {
                        ((Browse) groupBrowses.get(group).getHeader()).setSelectAll(true);
                        tbool = true;
                    }

                    for (int i = 0; i < groupBrowses.get(group).getObjs().size(); i++) {
                        ((Browse) groupBrowses.get(group).getObjs().get(i)).setSelect(tbool);
                    }

                    groupedListAdapter.notifyDataChanged();
                    break;
            }
        }
    };

    BrowseItem.OnSubItemClickListener browseListener = new BrowseItem.OnSubItemClickListener() {
        @Override
        public void clicked(int group, int pos, View v) {
            switch (v.getId()) {
                case R.id.checkBox6://单选
                    if (((Browse) groupBrowses.get(group).getObjs().get(pos)).isSelect()) {
                        ((Browse) groupBrowses.get(group).getObjs().get(pos)).setSelect(false);
                    } else {
                        ((Browse) groupBrowses.get(group).getObjs().get(pos)).setSelect(true);
                    }
                    for (int i = 0; i < groupBrowses.size(); i++) {
                        ((Browse) groupBrowses.get(i).getHeader()).setSelectAll(true);
                        for (int j = 0; j < groupBrowses.get(i).getObjs().size(); j++) {
                            if (!((Browse) groupBrowses.get(i).getObjs().get(j)).isSelect()) {
                                ((Browse) groupBrowses.get(i).getHeader()).setSelectAll(false);
                            }
                        }
                    }
                    groupedListAdapter.notifyDataChanged();
                    break;
                case R.id.imageView58:
                    Intent intent = new Intent(BrowseActivity.this, GoodsDetailActivity.class);
                    intent.putExtra("id", ((Browse) groupBrowses.get(group).getObjs().get(pos))
                            .getGoods().getGoods_id());
                    startActivity(intent);
                    break;
                case R.id.textView133:
                    //找相似
                    Intent intentS = new Intent(BrowseActivity.this, SearchInfoActivity.class);
                    if (groupBrowses.get(group).getObjs().get(pos) instanceof Browse) {
                        intentS.putExtra("cate", ((Browse) groupBrowses.get(group).getObjs().get
                                (pos)).getGoods().getCat_id());
                    }
                    startActivity(intentS);
                    break;
            }
        }
    };
}
