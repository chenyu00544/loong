package com.vcvb.chenyu.shop.msg;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageCouponItem;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageFaatItem;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageNoticeItem;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageServerItem;
import com.vcvb.chenyu.shop.javaBean.msg.MessageList;

import java.util.ArrayList;
import java.util.List;

public class MessageInfoActivity extends BaseRecyclerViewActivity {
    private List<MessageList> messages = new ArrayList<>();

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
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);

        for (int i = 0; i < 1; i++) {
            MessageList message = new MessageList();
            message.setIsType(4);
            messages.add(message);
        }

        mAdapter.addAll(getItems(messages));
    }

    protected List<Item> getItems(List<MessageList> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()){
                case 1:
                    cells.add(new MessageFaatItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new MessageCouponItem(list.get(i), context));
                    break;
                case 3:
                    cells.add(new MessageNoticeItem(list.get(i), context));
                    break;
                case 4:
                    cells.add(new MessageServerItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
