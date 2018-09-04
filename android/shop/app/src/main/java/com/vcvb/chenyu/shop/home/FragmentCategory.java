package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroyItem;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroySubTitleItem;
import com.vcvb.chenyu.shop.adapter.item.categray.CategroyTitleItem;
import com.vcvb.chenyu.shop.adapter.itemclick.ItemClickSupport;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyBean;

import java.util.ArrayList;
import java.util.List;

public class FragmentCategory extends BaseFragment {
    View view;
    Context context;

    private RecyclerView recyclerView;
    private CYCSimpleAdapter simpleAdapter;

    private RecyclerView subRecyclerView;
    private CYCSimpleAdapter subSimpleAdapter;

    private ArrayList<CategroyBean> list;
    private ArrayList<CategroyBean> sublist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categroy, container, false);
        context = getActivity();
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
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(itemView);
                System.out.println(position);
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport
                .OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int
                    position) {
                System.out.println(itemView);
                System.out.println(position);
                return true;
            }
        });


        subRecyclerView = view.findViewById(R.id.sub_categroy);
        GridLayoutManager subGrid = new GridLayoutManager(context, 3);
        subRecyclerView.setLayoutManager(subGrid);
        subSimpleAdapter = new CYCSimpleAdapter();
        subRecyclerView.setAdapter(subSimpleAdapter);

        ItemClickSupport.addTo(subRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                System.out.println(itemView);
                System.out.println(position);
            }
        });
        loadData();
    }

    public void loadData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);

        list = new ArrayList<>();
        sublist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CategroyBean bean = new CategroyBean();
            bean.setIsType(2);
            bean.setCateName("Cate" + i);
            list.add(bean);
        }
        for (int j = 0; j < 5; j++) {
            CategroyBean bean = new CategroyBean();
            bean.setIsType(3);
            bean.setCateName("Cate" + j);
            sublist.add(bean);
            for (int k = 0; k < 5; k++) {
                bean = new CategroyBean();
                bean.setIsType(1);
                bean.setCateName("Cate" + k);
                bean.setPic(Images.imageUrls[k]);
                sublist.add(bean);
            }
        }
        simpleAdapter.addAll(getItems(list));
        subSimpleAdapter.addAll(getItems(sublist));
    }

    protected List<Item> getItems(List<CategroyBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new CategroyItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new CategroyTitleItem(list.get(i), context));
                    break;
                case 3:
                    cells.add(new CategroySubTitleItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }
}
