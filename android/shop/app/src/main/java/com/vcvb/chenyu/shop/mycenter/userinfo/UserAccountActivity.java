package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.useraccount.UserAccountItem;
import com.vcvb.chenyu.shop.adapter.item.user.useraccount.UserAccountTitleItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.staticdata.Users;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class UserAccountActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<UserInfoBean> users = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        changeStatusBarTextColor(true);
        context = this;
        setNavBack();
        initView();
        initListener();
        getData(true);
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView titleView = (TextView) findViewById(R.id.textView123);
        titleView.setText(R.string.account_title);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        TextView add = (TextView) findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void getData(final boolean b) {
        super.getData(b);
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", "");
        mp.put("nav_id", "0");
//        mp.put("order_type", ""+type);
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }

                        if (users.size() != 0) {
//                            setHaveDataByView();
                        } else {
//                            setNoDateByView();
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
//                        setNoDateByView();
                    }
                });
            }
        });

        users.clear();
        for (int i = 0; i < Users.accounts.size(); i++) {
            UserInfoBean bean = new UserInfoBean();
            bean.setIsType(Users.accounts.get(i).get("type"));
            bean.setTitle(Users.accounts.get(i).get("title"));
            bean.setSubTitle(Users.accounts.get(i).get("subtitle"));
            if(false){
                bean.setIcon(Users.accounts.get(i).get("icon_a"));
            }else{
                bean.setIcon(Users.accounts.get(i).get("icon"));
            }
            bean.setIsBind(false);
            bean.setName("sdaf"+i);
            users.add(bean);
        }
        mAdapter.addAll(getItems(users));
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {

            }
        });
    }

    protected List<Item> getItems(List<UserInfoBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new UserAccountItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new UserAccountTitleItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

}
