package com.vcvb.chenyu.shop.brand;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.faat.fragment.CosmeticsFragment;

public class BrandListActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private CosmeticsFragment cosmeticsFragment;

    private int index = 0;
    private String SAVED_INDEX = "SAVED_INDEX";
    private String[] fragmentTag = new String[]{"faat_cosmetics"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faat_main);
        changeStatusBarTextColor(true);
        context = this;
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(SAVED_INDEX, index);
            cosmeticsFragment = (CosmeticsFragment) fragmentManager.findFragmentByTag
                    (fragmentTag[0]);
        }
        changeStatusBarTextColor(true);
        setNavBack();
//        setClick(index);
    }

    @Override
    public void setNavBack() {
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
                if (cosmeticsFragment == null) {
                    cosmeticsFragment = new CosmeticsFragment();
                    //加入事务
                    fragmentTransaction.add(R.id.faat_main, cosmeticsFragment, fragmentTag[0]);
                } else {
                    //如果不为空就显示出来
                    fragmentTransaction.show(cosmeticsFragment);
                }
                break;
        }
        //提交事务
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (cosmeticsFragment != null) {
            fragmentTransaction.hide(cosmeticsFragment);
        }
    }
}
