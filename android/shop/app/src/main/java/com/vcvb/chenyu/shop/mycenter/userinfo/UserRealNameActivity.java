package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealCardItem;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealItem;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealTitleItem;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealWhyItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.staticdata.Users;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class UserRealNameActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<UserInfoBean> users = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private Receiver receiver;
    private TextView save;
    private int pos = 0;

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
        titleView.setText(R.string.real_title);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        save = (TextView) findViewById(R.id.textView122);
        save.setText(R.string.save);
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
        for (int i = 0; i < Users.realname.size(); i++) {
            UserInfoBean bean = new UserInfoBean();
            bean.setIsType(Users.realname.get(i).get("type"));
            bean.setTitle(Users.realname.get(i).get("title"));
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

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(UserRealNameActivity.this, UserLogoActivity.class);
        if(pos == 1){
            intent.putExtra("card_uri", "CARD_IMG_FRONT.jpg");
        }else{
            intent.putExtra("card_uri", "CARD_IMG_BACK.jpg");
        }
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToAlbum(int type) {
        pos = type;
        UserRealNameActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
    }

    @Override
    public void initListener() {
        super.initListener();
        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.imageView68).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                goToAlbum(1);
            }
        });

        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.imageView69).setOnChildClickListener1
                (new CYCItemClickSupport.OnChildItemClickListener1() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                goToAlbum(2);
            }
        });

        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.imageView73).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                users.get(4).setImgUriFront(null);
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo3(mRecyclerView, R.id.imageView72).setOnChildClickListener3
                (new CYCItemClickSupport.OnChildItemClickListener3() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                users.get(4).setImgUriBack(null);
                mAdapter.notifyDataSetChanged();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存实名认证
            }
        });

    }

    protected List<Item> getItems(List<UserInfoBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new UserRealItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new UserRealTitleItem(list.get(i), context));
                    break;
                case 3:
                    cells.add(new UserRealCardItem(list.get(i), context));
                    break;
                case 4:
                    cells.add(new UserRealWhyItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("cardNameChange");
        intentFilter.addAction("cardNumChange");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "cardNameChange":
                        System.out.println(intent.getStringExtra("data"));
                        break;
                    case "cardNumChange":
                        System.out.println(intent.getStringExtra("data"));
                        break;
                }
            }
        };
        broadcastManager.registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST:
                    if (pos == 1) {
                        users.get(4).setImgUriFront((Uri) data.getParcelableExtra("uri"));
                    } else if (pos == 2) {
                        users.get(4).setImgUriBack((Uri) data.getParcelableExtra("uri"));
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
