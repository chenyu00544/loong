package com.vcvb.chenyu.shop.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.GroupedListAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroyAdsItem;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroyItem;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroySubTitleItem;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroyTitleItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.base.BaseFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.cate.Categroy;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyBean;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyGroup;
import com.vcvb.chenyu.shop.search.SearchInfoActivity;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class FragmentCategory extends BaseFragment {

    private RecyclerView recyclerView;
    private CYCSimpleAdapter simpleAdapter;

    private RecyclerView subRecyclerView;
    private GroupedListAdapter groupedListAdapter;
    private GroupedGridLayoutManager groupedGridLayoutManager;

    private List<Categroy> categroys = new ArrayList<>();
    private ArrayList<CategroyBean> list;
    private List<CategroyGroup> categroyGroups;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_categroy, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.categroy_list);
        GridLayoutManager grid = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(grid);
        simpleAdapter = new CYCSimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);

        CYCItemClickSupport.addTo(recyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
            }
        });

        subRecyclerView = view.findViewById(R.id.sub_categroy);
        getData();
    }

    public void getData() {
        HttpUtils.getInstance().post(ConstantManager.Url.CATEGORY, null, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        if (json.getInt("code") == 0) {
                            bindViewData(json);
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

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CategroyBean bean = new CategroyBean();
            bean.setIsType(2);
            bean.setCateName("Cate" + i);
            if (i == 0) {
                bean.setIsCurrent(true);
            }
            list.add(bean);
        }
        categroyGroups = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            CategroyGroup categroyGroup = new CategroyGroup();
            if (j == 0) {
                List<Object> categroyBeans = new ArrayList<>();
                CategroyBean subBean = new CategroyBean();
                subBean.setPic(Images.imageUrls[0]);
                subBean.setIsType(2);
                categroyBeans.add(subBean);
                categroyGroup.setObjs(categroyBeans);
            } else {
                CategroyBean bean = new CategroyBean();
                bean.setIsType(3);
                bean.setCateName("Cate" + j);
                categroyGroup.setHeader(bean);
                List<Object> categroyBeans = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    CategroyBean subBean = new CategroyBean();
                    subBean.setIsType(1);
                    subBean.setCateName("Cate" + k);
                    subBean.setPic(Images.imageUrls[k]);
                    categroyBeans.add(subBean);
                }
                categroyGroup.setObjs(categroyBeans);
            }
            categroyGroups.add(categroyGroup);
        }

        groupedListAdapter = new GroupedListAdapter(context);
        groupedListAdapter.setData(getGroupItems());
        subRecyclerView.setAdapter(groupedListAdapter);
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 3, groupedListAdapter) {

            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                if (categroyGroups.get(groupPosition).getItemList().get(childPosition)
                        .getItemType() == R.layout.categroy_ads_item) {
                    return 3;
                } else {
                    return super.getChildSpanSize(groupPosition, childPosition);
                }
            }
        };
        subRecyclerView.setLayoutManager(groupedGridLayoutManager);
        simpleAdapter.addAll(getItems(list));
    }

    public void bindViewData(JSONObject json) {
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Categroy categroy = JsonUtils.fromJsonObject(object, Categroy.class);
                categroy.setData(object);
                categroys.add(categroy);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected List<Item> getItems(List<CategroyBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 2:
                    cells.add(new CategroyTitleItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    protected List<CategroyGroup> getGroupItems() {
        for (int i = 0; i < categroyGroups.size(); i++) {
            if (categroyGroups.get(i).getHeader() != null) {
                CategroySubTitleItem subTitleItem = new CategroySubTitleItem(categroyGroups.get(i),
                        context);
                categroyGroups.get(i).setMheader(subTitleItem);
            }
            List<com.vcvb.chenyu.shop.adapter.b.Item> items = new ArrayList<>();
            for (int j = 0; j < categroyGroups.get(i).getObjs().size(); j++) {
                if (((CategroyBean) categroyGroups.get(i).getObjs().get(j)).getIsType() == 1) {
                    CategroyItem categroyItem = new CategroyItem(categroyGroups.get(i), context);
                    items.add(categroyItem);
                } else {
                    CategroyAdsItem categroyAdsItem = new CategroyAdsItem(categroyGroups.get(i),
                            context);
                    items.add(categroyAdsItem);
                }
            }
            categroyGroups.get(i).setItemList(items);
        }
        return categroyGroups;
    }

    public void goToSearchInfoActivity() {
        Intent intent = new Intent(context, SearchInfoActivity.class);
        startActivity(intent);
    }
}
