package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.UserItem;
import com.vcvb.chenyu.shop.adapter.item.user.UserLogoItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.ConfirmDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.staticdata.Users;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class UserInfoActivity extends BaseActivity {

    Context context;
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<UserInfoBean> users = new ArrayList<>();
    private GridLayoutManager mLayoutManager;

    private RefreshLayout refreshLayout;

    public LoadingDialog loadingDialog;

    private ConfirmDialog confirmDialog;

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
        super.setNavBack();
        int gravity = Gravity.CENTER;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView titleView = new TextView(this);
        titleView.setLayoutParams(layoutParams);
        titleView.setGravity(gravity);
        titleView.setText(R.string.personal_center);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ToolUtils.dip2px
                (context, 60), LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout nav_other = (LinearLayout) findViewById(R.id.nav_other);
        nav_other.setLayoutParams(layoutParams2);
        nav_other.setAlpha(0);
        ImageView iv1 = (ImageView) findViewById(R.id.collection);
        nav_other.removeView(iv1);

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = (LinearLayout) findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
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
        for (int i = 0; i < Users.users.size(); i++) {
            UserInfoBean bean = new UserInfoBean();
            bean.setIsType(Users.users.get(i).get("type"));
            bean.setTitle(Users.users.get(i).get("title"));
            bean.setSubTitle(Users.users.get(i).get("subtitle"));
            users.add(bean);
        }
        mAdapter.addAll(getItems(users));
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
    }

    protected List<Item> getItems(List<UserInfoBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new UserItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new UserLogoItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    //相册权限
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showPhotoAlbum() {
        Intent intent = new Intent(UserInfoActivity.this, UserLogoActivity.class);
        startActivityForResult(intent, ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST);
    }

    public void goToSetUserActivity(int pos) {
        switch (pos) {
            case 0:
                UserInfoActivityPermissionsDispatcher.showPhotoAlbumWithCheck(this);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    //上传头像
    public void uploadHeaderLogo(){
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.PHOTOALBUM_REQUEST:
                    Uri imageUri = data.getParcelableExtra("uri");
                    users.get(0).setImgPath(imageUri.toString());
                    uploadHeaderLogo();
                    break;
            }
        }
    }
}
