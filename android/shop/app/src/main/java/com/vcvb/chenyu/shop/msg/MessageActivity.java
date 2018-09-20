package com.vcvb.chenyu.shop.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.javaBean.msg.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseRecyclerViewActivity {
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.message_list);
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        setTitle(R.string.message_center, R.id.textView123);
        ImageView other = findViewById(R.id.imageView94);
        other.setOnClickListener(listener);
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
            message.setIsType(1);
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
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                Message message = messages.get(position);
                switch (message.getIsType()) {
                    case 1:
                        Intent intent = new Intent(MessageActivity.this, MessageInfoActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
