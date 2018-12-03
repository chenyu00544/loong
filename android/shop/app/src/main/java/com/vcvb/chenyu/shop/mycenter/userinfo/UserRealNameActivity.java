package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealCardItem;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealItem;
import com.vcvb.chenyu.shop.adapter.item.user.real.UserRealWhyItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserReal;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class UserRealNameActivity extends BaseRecyclerViewActivity {

    private UserReal real = new UserReal();
    private Receiver receiver;
    private TextView save;
    private int pos = 0;
    private HashMap<String, String> mp = new HashMap<>();
    private List<File> files = new ArrayList<>();

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
        titleView.setText(R.string.real_title);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        save = findViewById(R.id.textView122);
        save.setText(R.string.save);
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

        //身份证正面，前往相册选图
        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.imageView68).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                goToAlbum(1);
            }
        });

        //身份证反面，前往相册选图
        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.imageView69).setOnChildClickListener1
                (new CYCItemClickSupport.OnChildItemClickListener1() {
                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                        goToAlbum(2);
                    }
                });

        //删除身份证正面
        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.imageView73).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {
                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                        real.setFront_of_id_card(null);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        //删除身份证反面
        CYCItemClickSupport.BuildTo3(mRecyclerView, R.id.imageView72).setOnChildClickListener3
                (new CYCItemClickSupport.OnChildItemClickListener3() {
                    @Override
                    public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                        real.setReverse_of_id_card(null);
                        mAdapter.notifyDataSetChanged();
                    }
                });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.USER_REAL, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bindViewData(json);
                            }
                        });
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
                        ToastUtils.showShortToast(context, "网络错误,请稍后重试");
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
                    real = JsonUtils.fromJsonObject(object, UserReal.class);
                    mp.put("real_name", (real.getReal_name() != null ? real.getReal_name() : ""));
                    mp.put("card_num", (real.getSelf_num() != null ? real.getSelf_num() : ""));
                }
                mAdapter.addAll(getItems(real));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData(){
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        loadingDialog.show();
        //保存实名认证
        mp.put("token", token);
        HttpUtils.getInstance().postImage(ConstantManager.Url.SET_USER_REAL, mp, files,
                new HttpUtils.NetCall() {
                    @Override
                    public void success(Call call, JSONObject json) throws IOException {
                        try {
                            if (json.getInt("code") == 0) {
                                Integer review_status = json.getJSONObject("data").getInt
                                        ("review_status");
                                HashMap<String, String> mp = new HashMap<>();
                                mp.put("is_real", review_status+"");
                                UserInfoUtils.getInstance(context).setUserInfo(mp);
                                real.setReview_status(review_status);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        System.out.println(e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismiss();
                                ToastUtils.showShortToast(context, "错误");
                            }
                        });
                    }
                });
    }

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(UserRealNameActivity.this, UserLogoActivity.class);
        if (pos == 1) {
            intent.putExtra("card_uri", "CARD_IMG_FRONT.jpg");
        } else {
            intent.putExtra("card_uri", "CARD_IMG_BACK.jpg");
        }
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToAlbum(int type) {
        pos = type;
        UserRealNameActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
    }

    protected List<Item> getItems(UserReal real) {
        List<Item> cells = new ArrayList<>();
        cells.add(new UserRealItem(real, context));
        cells.add(new UserRealCardItem(real, context));
        cells.add(new UserRealWhyItem(real, context));
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
                        mp.put("card_name", intent.getStringExtra("data"));
                        break;
                    case "cardNumChange":
                        System.out.println(intent.getStringExtra("data"));
                        mp.put("card_num", intent.getStringExtra("data"));
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
                        if (files.size() > 0) {
                            files.remove(0);
                        }
                        real.setFront_of_id_card(String.valueOf(data.getParcelableExtra("uri")));
                        files.add(new File(ConstantManager.ImgPath.PATH, "CARD_IMG_FRONT.jpg"));
                    } else if (pos == 2) {
                        if (files.size() > 1) {
                            files.remove(1);
                        }
                        real.setReverse_of_id_card(String.valueOf(data.getParcelableExtra("uri")));
                        files.add(new File(ConstantManager.ImgPath.PATH, "CARD_IMG_BACK.jpg"));
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
