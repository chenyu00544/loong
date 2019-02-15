package com.vcvb.chenyu.shop.activity.categoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.categoods.fragment.FragmentCate;
import com.vcvb.chenyu.shop.activity.goods.CartActivity;
import com.vcvb.chenyu.shop.activity.msg.MessageActivity;
import com.vcvb.chenyu.shop.base.BaseActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class CateGoodsActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentCate fragment;
    private PopWin popWindow;

    private int index = 0;
    private String SAVED_INDEX = "SAVED_INDEX";
    private String[] fragmentTag = new String[]{"brand"};
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faat_main);
        changeStatusBarTextColor(true);
        context = this;
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(SAVED_INDEX, index);
            fragment = (FragmentCate) fragmentManager.findFragmentByTag(fragmentTag[0]);
        }
        title = getIntent().getStringExtra("title");
        setNavBack();
        setClick(index);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        ImageView share = findViewById(R.id.imageView120);
        share.setOnClickListener(listener);
        ImageView more = findViewById(R.id.imageView94);
        more.setOnClickListener(listener);
        TextView textView = findViewById(R.id.textView123);
        if (title == null || title.equals("")) {
            textView.setText(R.string.app_name);
        } else {
            textView.setText(title);
        }
        popWindow = new PopWin(CateGoodsActivity.this, ToolUtils.dip2px(this, 156), ToolUtils
                .dip2px(this, 148));
        popWindow.setClickListener(popListener);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    private void setClick(int type) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (type) {
            case 0:
                if (fragment == null) {
                    fragment = new FragmentCate();
                    //加入事务
                    fragmentTransaction.add(R.id.faat_main, fragment, fragmentTag[0]);
                } else {
                    //如果不为空就显示出来
                    fragmentTransaction.show(fragment);
                }
                break;
        }
        //提交事务
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case ConstantManager.Nav.MORE_1:
                    popWindow.showAsDropDown(view);
                    break;
                case ConstantManager.Nav.BACK_1:
                    SwipeBackHelper.finish(CateGoodsActivity.this);
                    break;
                case ConstantManager.Nav.SHARE:
                    popWindow.showAsDropDown(view);
                    break;
            }
        }
    };

    PopWin.OnItemClickListener popListener = new PopWin.OnItemClickListener() {
        @Override
        public void onClicked(View v) {
            popWindow.dismiss();
            switch (v.getId()) {
                case ConstantManager.Menu.HOME:
                    Intent intentM = new Intent(CateGoodsActivity.this, MainActivity.class);
                    startActivity(intentM);
                    break;
                case ConstantManager.Menu.MESSAGE:
                    Intent intentH = new Intent(CateGoodsActivity.this, MessageActivity.class);
                    startActivity(intentH);
                    break;
                case ConstantManager.Menu.CART:
                    Intent intentC = new Intent(CateGoodsActivity.this, CartActivity.class);
                    startActivity(intentC);
                    break;
            }
        }
    };
}
