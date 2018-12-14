package com.vcvb.chenyu.shop.activity.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageNotifyArticleItem;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageNotifyEventItem;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageNotifyFaatItem;
import com.vcvb.chenyu.shop.adapter.item.msg.MessageNotifyServerItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.msg.Message;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgArticle;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgEvent;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgFaat;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgSever;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;

public class MessageActivity extends BaseRecyclerViewActivity {
    private List<Message> messages = new ArrayList<>();
    private IDataStorage dataStorage;
    private List<NotifyMsgArticle> articles = new ArrayList<>();
    private List<NotifyMsgEvent> events = new ArrayList<>();
    private List<NotifyMsgFaat> faats = new ArrayList<>();
    private List<NotifyMsgSever> severs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.message_list);
        changeStatusBarTextColor(true);
        dataStorage = DataStorageFactory.getInstance(context, DataStorageFactory.TYPE_DATABASE);
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
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.NOTIFY, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        if (json.getInt("code") == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
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

            }
        });
    }

    public void bindViewData(JSONObject json) {
        severs = dataStorage.loadAll(NotifyMsgSever.class);
        try {
            if (severs.size() == 0) {
                JSONObject server = json.getJSONObject("data").getJSONObject("server");
                NotifyMsgSever notifySever = JsonUtils.fromJsonObject(server, NotifyMsgSever
                        .class);
                notifySever.setIs_look(false);
                severs.add(notifySever);
                dataStorage.storeOrUpdate(severs);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        faats = dataStorage.loadAll(NotifyMsgFaat.class);
        try {
            JSONObject faat = json.getJSONObject("data").getJSONObject("notify_faat");
            NotifyMsgFaat notifyFaat = JsonUtils.fromJsonObject(faat, NotifyMsgFaat.class);
            if (faats.size() > 0) {
                if (faats.get(faats.size() - 1).getId() < notifyFaat.getId()) {
                    notifyFaat.setIs_look(false);
                    faats.add(notifyFaat);
                }
            } else {
                notifyFaat.setIs_look(false);
                faats.add(notifyFaat);
            }
            dataStorage.storeOrUpdate(faats);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        events = dataStorage.loadAll(NotifyMsgEvent.class);
        try {
            JSONObject event = json.getJSONObject("data").getJSONObject("notify_event");
            NotifyMsgEvent notifyEvent = JsonUtils.fromJsonObject(event, NotifyMsgEvent.class);
            if (events.size() > 0) {
                if (events.get(events.size() - 1).getId() < notifyEvent.getId()) {
                    notifyEvent.setIs_look(false);
                    events.add(notifyEvent);
                }
            } else {
                notifyEvent.setIs_look(false);
                events.add(notifyEvent);
            }
            dataStorage.storeOrUpdate(events);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        articles = dataStorage.loadAll(NotifyMsgArticle.class);
        try {
            JSONObject acticle = json.getJSONObject("data").getJSONObject("notify_acticle");
            System.out.println(acticle);
            NotifyMsgArticle notifyArticle = JsonUtils.fromJsonObject(acticle,
                    NotifyMsgArticle.class);
            if (articles.size() > 0) {
                if (articles.get(articles.size() - 1).getId() < notifyArticle.getId()) {
                    notifyArticle.setIs_look(false);
                    articles.add(notifyArticle);
                }
            } else {
                notifyArticle.setIs_look(false);
                articles.add(notifyArticle);
            }
            dataStorage.storeOrUpdate(articles);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        mAdapter.addAll(getItems());
    }

    protected List<Item> getItems() {
        List<Item> cells = new ArrayList<>();
        if (severs.size() > 0) {
            cells.add(new MessageNotifyServerItem(severs, context));
        }
        if (faats.size() > 0) {
            cells.add(new MessageNotifyFaatItem(faats, context));
        }
        if (events.size() > 0) {
            cells.add(new MessageNotifyEventItem(events, context));
        }
        if (articles.size() > 0) {
            cells.add(new MessageNotifyArticleItem(articles, context));
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
