package com.vcvb.chenyu.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private BottomBar bottomBar;
    private ViewPager viewPager;

    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        bottomInit();
    }

    public void initViewPager(){
        list = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        list.add(new FragmentHome());
        list.add(new FragmentCategory());
        list.add(new FragmentFind());
        list.add(new FragmentCart());
        list.add(new FragmentMy());
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    //底部导航
    private void bottomInit() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                int page = 0;
                switch (tabId){
                    case R.id.tab_home:
                        page = 0;
                        break;
                    case R.id.tab_category:
                        page = 1;
                        break;
                    case R.id.tab_find:
                        page = 2;
                        break;
                    case R.id.tab_cart:
                        page = 3;
                        break;
                    case R.id.tab_my:
                        page = 4;
                        break;
                }
                viewPager.setCurrentItem(page ,false);
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                switch (tabId){
                    case R.id.tab_home:
                        break;
                    case R.id.tab_category:
                        break;
                    case R.id.tab_find:
                        break;
                    case R.id.tab_cart:
                        break;
                    case R.id.tab_my:
                        break;
                }
            }
        });
    }

    public void onClickPushActivity(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, GoodsDetailActivity.class);
        startActivity(intent);
    }
}
