package com.vcvb.chenyu.shop.activity.msg;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageServerItem;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgSever;

import java.util.ArrayList;
import java.util.List;

import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;

public class MessageServerActivity extends BaseRecyclerViewActivity {
    private List<NotifyMsgSever> severs = new ArrayList<>();
    private IDataStorage dataStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.message_info_list);
        changeStatusBarTextColor(true);
        dataStorage = DataStorageFactory.getInstance(context, DataStorageFactory.TYPE_DATABASE);
        setNavBack();
        initView();
        getData(true);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        setTitle("客服", R.id.textView123);
        ImageView other = findViewById(R.id.imageView94);
        other.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.msg_list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);
        severs = dataStorage.loadAll(NotifyMsgSever.class);
        mAdapter.addAll(getItems());
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        if (severs.size() > 0) {
            for (int i = 0; i < severs.size(); i++) {
                severs.get(i).setIs_look(true);
            }
            dataStorage.storeOrUpdate(severs);
            cells.add(new MessageServerItem(severs.get(0), context));
        }
        return cells;
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
