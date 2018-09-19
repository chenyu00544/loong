package com.vcvb.chenyu.shop.msg;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageItem;
import com.vcvb.chenyu.shop.javaBean.msg.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageInfoActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<Message> messages = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.message_info_list);
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        getData(true);
    }

    @Override
    public void setNavBack() {

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

        for (int i = 0; i < 5; i++) {
            Message message = new Message();
            messages.add(message);
        }

        mAdapter.addAll(getItems(messages));
    }

    protected List<Item> getItems(List<Message> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            cells.add(new MessageItem(list.get(i), context));
        }
        return cells;
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
