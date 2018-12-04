package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.useraccount.UserAccountItem;
import com.vcvb.chenyu.shop.adapter.item.user.useraccount.UserAccountTitleItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class UserAccountActivity extends BaseRecyclerViewActivity {

    private UserInfoBean user = new UserInfoBean();

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
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView titleView = findViewById(R.id.textView123);
        titleView.setText(R.string.account_title);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        TextView add = findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.user_list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {

            }
        });
    }

    @Override
    public void getData(final boolean b) {
        super.getData(b);
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.GET_USER_INFO, mp, new HttpUtils.NetCall
                () {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }
                        bindViewData(json);
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
                    }
                });
            }
        });
    }

    public void bindViewData(JSONObject json) {
        if (json != null) {
            try {
                Integer code = json.getInt("code");
                if (code == 0) {
                    JSONObject object = json.getJSONObject("data");
                    user = JsonUtils.fromJsonObject(object, UserInfoBean.class);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        mAdapter.addAll(getItems(user));
    }

    protected List<Item> getItems(UserInfoBean bean) {
        List<Item> cells = new ArrayList<>();
        cells.add(new UserAccountTitleItem(bean, context, 1));
        UserAccountItem userAccountItem1 = new UserAccountItem(bean, context, 1);
        userAccountItem1.setOnItemClickListener(accountListener);
        cells.add(userAccountItem1);
        cells.add(new UserAccountTitleItem(bean, context, 2));
        UserAccountItem userAccountItem2 = new UserAccountItem(bean, context, 2);
        userAccountItem2.setOnItemClickListener(accountListener);
        cells.add(userAccountItem2);
        cells.add(new UserAccountTitleItem(bean, context, 3));
        UserAccountItem userAccountItem3 = new UserAccountItem(bean, context, 3);
        userAccountItem3.setOnItemClickListener(accountListener);
        cells.add(userAccountItem3);
        cells.add(new UserAccountItem(bean, context, 4));
        return cells;
    }

    UserAccountItem.OnClickListener accountListener = new UserAccountItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            switch (pos) {
                case 1:
                    Intent intent = new Intent(UserAccountActivity.this, BindPhoneActivity.class);
                    startActivityForResult(intent, ConstantManager.ResultStatus.PHONE);
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.ResultStatus.PHONE:
                    String phone = data.getStringExtra("phone");
                    user.setMobile_phone(phone);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
