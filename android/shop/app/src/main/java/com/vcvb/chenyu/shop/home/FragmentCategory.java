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
import android.widget.ImageView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.msg.MessageActivity;
import com.vcvb.chenyu.shop.activity.search.SearchInfoActivity;
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
import com.vcvb.chenyu.shop.javaBean.cate.Categroy;
import com.vcvb.chenyu.shop.javaBean.cate.SubCate;
import com.vcvb.chenyu.shop.javaBean.cate.SubCategroy;
import com.vcvb.chenyu.shop.javaBean.home.Ads;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.UrlParse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class FragmentCategory extends BaseFragment {

    private RecyclerView recyclerView;
    private CYCSimpleAdapter simpleAdapter;

    private RecyclerView subRecyclerView;
    private GroupedListAdapter groupedListAdapter;
    private GroupedGridLayoutManager groupedGridLayoutManager;

    private List<Categroy> categroys = new ArrayList<>();
    private List<SubCategroy> subCategroys = new ArrayList<>();

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
                for (int i = 0; i < categroys.size(); i++) {
                    categroys.get(i).setIs_current(false);
                }
                categroys.get(position).setIs_current(true);
                simpleAdapter.notifyDataSetChanged();

                groupedListAdapter.notifyDataRemoved();
                groupedListAdapter.setData(getGroupItems(position));
                groupedListAdapter.notifyDataChanged();
            }
        });

        subRecyclerView = view.findViewById(R.id.sub_categroy);
        groupedListAdapter = new GroupedListAdapter(context);
        subRecyclerView.setAdapter(groupedListAdapter);
        groupedGridLayoutManager = new GroupedGridLayoutManager(context, 3, groupedListAdapter) {

            @Override
            public int getChildSpanSize(int groupPosition, int childPosition) {
                if (subCategroys.get(groupPosition).getItemList().get
                        (childPosition).getItemType() == R.layout.categroy_ads_item) {
                    return 3;
                } else {
                    return super.getChildSpanSize(groupPosition, childPosition);
                }
            }
        };
        subRecyclerView.setLayoutManager(groupedGridLayoutManager);
        groupedListAdapter.setOnHeaderClickListener(onHeaderClickListener);
        groupedListAdapter.setOnChildClickListener(onChildClickListener);

        ImageView msg = view.findViewById(R.id.imageView45);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessageActivity.class);
                startActivity(intent);
            }
        });

        getData();
    }

    public void getData() {
        HttpUtils.getInstance().post(ConstantManager.Url.CATEGORY, null, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        if (json.getInt("code") == 0) {
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        bindViewData(json);
                                    }
                                });
                            }
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

    public void bindViewData(JSONObject json) {
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Categroy categroy = JsonUtils.fromJsonObject(object, Categroy.class);
                categroy.setData(object);
                if (i == 0) {
                    categroy.setIs_current(true);
                }
                categroys.add(categroy);
            }
            simpleAdapter.addAll(getItems(categroys));

            groupedListAdapter.setData(getGroupItems(0));
            groupedListAdapter.notifyDataChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }

    protected List<Item> getItems(List<Categroy> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            cells.add(new CategroyTitleItem(list.get(i), context));
        }
        return cells;
    }

    protected List<SubCategroy> getGroupItems(Integer index) {
        subCategroys = categroys.get(index).getSubCategroys();
        for (int i = 0; i < subCategroys.size(); i++) {
            if (subCategroys.get(i).getHeader() != null) {
                CategroySubTitleItem subTitleItem = new CategroySubTitleItem(subCategroys.get(i), context);
                subCategroys.get(i).setMheader(subTitleItem);
            }
            List<com.vcvb.chenyu.shop.adapter.b.Item> items = new ArrayList<>();
            for (int j = 0; j < subCategroys.get(i).getObjs().size(); j++) {
                if (subCategroys.get(i).getObjs().get(j) instanceof Ads) {
                    CategroyAdsItem categroyAdsItem = new CategroyAdsItem(subCategroys.get(i), context);
                    items.add(categroyAdsItem);
                } else {
                    CategroyItem categroyItem = new CategroyItem(subCategroys.get(i), context);
                    items.add(categroyItem);
                }
            }
            subCategroys.get(i).setItemList(items);
        }
        return subCategroys;
    }

    public void goToSearchInfoActivity(SubCate subCate) {
        Intent intent = new Intent(context, SearchInfoActivity.class);
        intent.putExtra("cate", subCate.getId());
        intent.putExtra("cate_name", subCate.getCat_alias_name());
        context.startActivity(intent);
    }

    GroupedRecyclerViewAdapter.OnChildClickListener onChildClickListener = new GroupedRecyclerViewAdapter.OnChildClickListener() {

        @Override
        public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int
                groupPosition, int childPosition) {
            if(subCategroys.get(groupPosition).getObjs().get(childPosition) instanceof Ads){
                Ads ads = (Ads) subCategroys.get(groupPosition).getObjs().get(childPosition);
                String uri = ads.getAd_link();
                Class c = UrlParse.getUrlToClass(uri);
                if (c != null) {
                    Map<String, String> id = UrlParse.getUrlParams(uri);
                    Intent intent = new Intent(context, c);
                    intent.putExtra("id", Integer.valueOf(id.get("id")));
                    context.startActivity(intent);
                }
            }else{
                SubCate subCate = (SubCate) subCategroys.get(groupPosition).getObjs().get(childPosition);
                goToSearchInfoActivity(subCate);
            }

        }
    };

    GroupedRecyclerViewAdapter.OnHeaderClickListener onHeaderClickListener = new GroupedRecyclerViewAdapter.OnHeaderClickListener() {


        @Override
        public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int
                groupPosition) {
            SubCate subCate = (SubCate) subCategroys.get(groupPosition).getHeader();
            goToSearchInfoActivity(subCate);
        }
    };
}
