package com.vcvb.chenyu.shop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.vcvb.chenyu.shop.home.FragmentCart;
import com.vcvb.chenyu.shop.home.FragmentCategory;
import com.vcvb.chenyu.shop.home.FragmentFind;
import com.vcvb.chenyu.shop.home.FragmentHome;
import com.vcvb.chenyu.shop.home.FragmentMy;

public class MainActivity extends FragmentActivity {

    private BottomBar bottomBar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentHome fragmentHome;
    private FragmentCategory fragmentCategory;
    private FragmentFind fragmentFind;
    private FragmentCart fragmentCart;
    private FragmentMy fragmentMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        bottomInit();
    }

    public void initViewPager() {
//        list = new ArrayList<>();
//        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        list.add(new FragmentHome());
//        list.add(new FragmentCategory());
//        list.add(new FragmentFind());
//        list.add(new FragmentCart());
//        list.add(new FragmentMy());
//        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return list.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return list.size();
//            }
//        });
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                bottomBar.selectTabAtPosition(position, true);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    //底部导航
    private void bottomInit() {
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                int page = 0;
                switch (tabId) {
                    case R.id.tab_home:
                        setClick(0);
                        break;
                    case R.id.tab_category:
                        setClick(1);
                        break;
                    case R.id.tab_find:
                        setClick(2);
                        break;
                    case R.id.tab_cart:
                        setClick(3);
                        break;
                    case R.id.tab_my:
                        setClick(4);
                        break;
                }
            }
        });
    }

    private void setClick(int type) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);

        switch (type) {
            case 0:
                if (fragmentHome == null) {
                    fragmentHome = new FragmentHome();
                    //加入事务
                    fragmentTransaction.add(R.id.fragment_content, fragmentHome);
                } else {
                    //如果不为空就显示出来
                    fragmentTransaction.show(fragmentHome);
                }
                break;
            case 1:
                if (fragmentCategory == null) {
                    fragmentCategory = new FragmentCategory();
                    fragmentTransaction.add(R.id.fragment_content, fragmentCategory);
                } else {
                    fragmentTransaction.show(fragmentCategory);
                }
                break;
            case 2:
                if (fragmentFind == null) {
                    fragmentFind = new FragmentFind();
                    fragmentTransaction.add(R.id.fragment_content, fragmentFind);
                } else {
                    fragmentTransaction.show(fragmentFind);
                }
                break;
            case 3:
                if (fragmentCart == null) {
                    fragmentCart = new FragmentCart();
                    fragmentTransaction.add(R.id.fragment_content, fragmentCart);
                } else {
                    fragmentTransaction.show(fragmentCart);
                }
                break;
            case 4:
                if (fragmentMy == null) {
                    fragmentMy = new FragmentMy();
                    fragmentTransaction.add(R.id.fragment_content, fragmentMy);
                } else {
                    fragmentTransaction.show(fragmentMy);
                }
                break;
        }
        //提交事务
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (fragmentHome != null) {
            fragmentTransaction.hide(fragmentHome);
        }
        if (fragmentCategory != null) {
            fragmentTransaction.hide(fragmentCategory);
        }
        if (fragmentFind != null) {
            fragmentTransaction.hide(fragmentFind);
        }
        if (fragmentCart != null) {
            fragmentTransaction.hide(fragmentCart);
        }
        if (fragmentMy != null) {
            fragmentTransaction.hide(fragmentMy);
        }
    }
}
