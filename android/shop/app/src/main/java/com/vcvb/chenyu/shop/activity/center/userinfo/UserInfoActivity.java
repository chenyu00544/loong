package com.vcvb.chenyu.shop.activity.center.userinfo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.UserAccountItem;
import com.vcvb.chenyu.shop.adapter.item.user.UserItem;
import com.vcvb.chenyu.shop.adapter.item.user.UserLogoItem;
import com.vcvb.chenyu.shop.adapter.item.user.UserRealItem;
import com.vcvb.chenyu.shop.adapter.item.user.UserSexItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.dialog.SexDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;

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
public class UserInfoActivity extends BaseRecyclerViewActivity {

    private UserInfoBean user = new UserInfoBean();

    public LoadingDialog loadingDialog;

    private SexDialog sexDialog;

    private int pos = 0;
    private List<File> files = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        initRefresh();
        getData(true);
        initListener();
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
        titleView.setText(R.string.personal_center);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        TextView add = findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        sexDialog = new SexDialog(context);
        mRecyclerView = findViewById(R.id.user_list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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
                        ToastUtils.showShortToast(context, "网络错误！");
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

    @Override
    public void initListener() {
        super.initListener();

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                goToSetUserActivity(position);
            }
        });
        sexDialog.setOnDialogClickListener(sexListener);
    }

    protected List<Item> getItems(UserInfoBean bean) {
        List<Item> cells = new ArrayList<>();
        cells.add(new UserLogoItem(bean, context));
        cells.add(new UserItem(bean, context));
        cells.add(new UserSexItem(bean, context));
        cells.add(new UserAccountItem(bean, context));
        cells.add(new UserRealItem(bean, context));
        return cells;
    }

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(UserInfoActivity.this, UserLogoActivity.class);
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToSetUserActivity(int pos) {
        this.pos = pos;
        switch (pos) {
            case 0:
                UserInfoActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
                break;
            case 1:
                Intent intent = new Intent(UserInfoActivity.this, UserNickActivity.class);
                startActivityForResult(intent, ConstantManager.User.NICKNAME);
                break;
            case 2:
                sexDialog.show();
                if (user.getSex() == 1) {
                    sexDialog.setTick(true);
                } else {
                    sexDialog.setTick(false);
                }
                break;
            case 3:
                Intent intent2 = new Intent(UserInfoActivity.this, UserAccountActivity.class);
                startActivity(intent2);
                break;
            case 4:
                Intent intent3 = new Intent(UserInfoActivity.this, UserRealNameActivity.class);
                startActivity(intent3);
                break;
            case 5:
                break;
        }
    }

    //上传头像
    public void uploadHeaderLogo(Uri imageUri) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("logo", user.getLogo());
        user.setLogo(imageUri.toString());
        HttpUtils.getInstance().postImage(ConstantManager.Url.SET_USER_INFO, mp, files,
                new HttpUtils.NetCall() {
                    @Override
                    public void success(Call call, JSONObject json) throws IOException {
                        if (json != null) {
                            try {
                                if (json.getInt("code") == 0) {
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

    //上传性别
    public void setSex(Integer sex) {
        user.setSex(sex);
        mAdapter.notifyDataSetChanged();
        sexDialog.dismiss();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("token", token);
        mp.put("sex", sex + "");
        HttpUtils.getInstance().post(ConstantManager.Url.SET_USER_INFO, mp,
                new HttpUtils.NetCall() {
                    @Override
                    public void success(Call call, JSONObject json) throws IOException {
                    }

                    @Override
                    public void failed(Call call, IOException e) {
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST:
                    Uri imageUri = data.getParcelableExtra("uri");
                    System.out.println(imageUri);
                    files.add(new File(ConstantManager.ImgPath.PATH, "IMAGE_CROP_NAME.jpg"));
                    uploadHeaderLogo(imageUri);
                    break;
                case ConstantManager.User.NICKNAME:
                    String nickname = data.getStringExtra("nickname");
                    user.setNick_name(nickname);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    SexDialog.OnDialogClickListener sexListener = new SexDialog.OnDialogClickListener() {
        @Override
        public void onManClickListener() {
            sexDialog.setTick(true);
            setSex(1);
        }

        @Override
        public void onWomanClickListener() {
            sexDialog.setTick(false);
            setSex(2);
        }

        @Override
        public void onCancelClickListener() {
            sexDialog.dismiss();
        }
    };
}
