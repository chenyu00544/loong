package com.vcvb.chenyu.shop.brand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.brand.fragment.BrandFragment;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class BrandListActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BrandFragment brandFragment;
    private PopWin popWindow;
    private TextView title;

    private int index = 0;
    private String SAVED_INDEX = "SAVED_INDEX";
    private String[] fragmentTag = new String[]{"brand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faat_main);
        changeStatusBarTextColor(true);
        context = this;
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(SAVED_INDEX, index);
            brandFragment = (BrandFragment) fragmentManager.findFragmentByTag
                    (fragmentTag[0]);
        }
        changeStatusBarTextColor(true);
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
        popWindow = new PopWin(BrandListActivity.this, ToolUtils.dip2px(this, 156), ToolUtils
                .dip2px(this, 148));
        more.setOnClickListener(listener);
        title = findViewById(R.id.textView123);
        title.setText(R.string.brand_center);
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
                if (brandFragment == null) {
                    brandFragment = new BrandFragment();
                    //加入事务
                    fragmentTransaction.add(R.id.faat_main, brandFragment, fragmentTag[0]);
                } else {
                    //如果不为空就显示出来
                    fragmentTransaction.show(brandFragment);
                }
                break;
        }
        //提交事务
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (brandFragment != null) {
            fragmentTransaction.hide(brandFragment);
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
                    SwipeBackHelper.finish(BrandListActivity.this);
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
            switch (v.getId()) {
                case ConstantManager.Menu.MESSAGE:
                    break;
                case ConstantManager.Menu.HOME:
                    popWindow.dismiss();
                    Intent intent = new Intent(BrandListActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case ConstantManager.Menu.CART:
                    break;
            }
            popWindow.dismiss();
            Intent intent = new Intent(BrandListActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}
