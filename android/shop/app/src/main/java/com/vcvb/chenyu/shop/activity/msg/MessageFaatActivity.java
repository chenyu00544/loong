package com.vcvb.chenyu.shop.activity.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageFaatItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgFaat;
import com.vcvb.chenyu.shop.tools.UrlParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;

public class MessageFaatActivity extends BaseRecyclerViewActivity {
    private List<NotifyMsgFaat> faats = new ArrayList<>();
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
        setTitle(R.string.message_center, R.id.textView123);
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
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                String uri = faats.get(position).getUrl();
                Class c = UrlParse.getUrlToClass(uri);
                Map<String, String> id = UrlParse.getUrlParams(uri);
                Intent intent = new Intent(context, c);
                intent.putExtra("id", Integer.valueOf(id.get("id")));
                startActivity(intent);
            }
        });
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);
        faats = dataStorage.loadAll(NotifyMsgFaat.class);
        mAdapter.addAll(getItems());
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < faats.size(); i++) {
            faats.get(i).setIs_look(true);
            MessageFaatItem messageFaatItem = new MessageFaatItem(faats.get(faats.size() - 1 - i)
                    , context);
            cells.add(messageFaatItem);
        }
        dataStorage.storeOrUpdate(faats);
        return cells;
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
