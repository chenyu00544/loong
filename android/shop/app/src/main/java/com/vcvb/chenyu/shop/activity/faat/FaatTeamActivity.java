package com.vcvb.chenyu.shop.activity.faat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.faat.fragment.FaatTeamFragment;
import com.vcvb.chenyu.shop.activity.goods.CartActivity;
import com.vcvb.chenyu.shop.activity.msg.MessageActivity;
import com.vcvb.chenyu.shop.base.BaseActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.TeamNav;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class FaatTeamActivity extends BaseActivity {

    ViewPager mViewPager;
    List<Fragment> mFragments;
    public List<TeamNav> navs = new ArrayList<>();

    private PopWin popWindow;

    private MagicIndicator mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faat_team_fragment);
        changeStatusBarTextColor(true);
        context = this;
        setNavBack();
        initView();
        getData(false);
    }

    @Override
    public void setNavBack() {
        ImageView back = findViewById(R.id.imageView23);
        back.setOnClickListener(listener);
        ImageView share = findViewById(R.id.imageView120);
        share.setOnClickListener(listener);
        ImageView more = findViewById(R.id.imageView94);
        popWindow = new PopWin(FaatTeamActivity.this, ToolUtils.dip2px(this, 156), ToolUtils
                .dip2px(this, 148));
        more.setOnClickListener(listener);
        TextView title = findViewById(R.id.textView123);
        title.setText(R.string.team);
        popWindow.setClickListener(popListener);
    }

    @Override
    public void initView() {
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager);
    }

    @Override
    public void getData(boolean b) {
        HashMap<String, String> mp = new HashMap<>();
        HttpUtils.getInstance().post(ConstantManager.Url.FAAT_TEAM_NAV, mp, new HttpUtils.NetCall
                () {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json != null) {
                            try {
                                if (json.getInt("code") == 0) {
                                    bindData(json);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        Toast.makeText(context, "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    public void bindData(JSONObject json) {
        try {
            Integer code = json.getInt("code");
            if (code == 0) {
                JSONArray arr = json.getJSONArray("data");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject object = (JSONObject) arr.get(i);
                    TeamNav teamNav = JsonUtils.fromJsonObject(object, TeamNav.class);
                    navs.add(teamNav);
                }
                setupViewPager();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < navs.size(); i++) {
            FaatTeamFragment faatTeamFragment = new FaatTeamFragment();
            faatTeamFragment.nav_id = navs.get(i).getId();
            mFragments.add(faatTeamFragment);
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return navs == null ? 0 : navs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                commonPagerTitleView.setContentView(R.layout.brand_tab_item);
                commonPagerTitleView.setPadding(20, 0, 20, 0);
                // 初始化
                final ImageView titleImg = commonPagerTitleView.findViewById(R.id.tabicon);
                Glide.with(context).load(navs.get(i).getTc_img()).into(titleImg);
                final TextView titleText = commonPagerTitleView.findViewById(R.id.tabtext);
                titleText.setText(navs.get(i).getName());
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(i);
                    }
                });
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int i, int i1) {
                        titleText.setTextColor(Color.parseColor("#965456"));
                    }

                    @Override
                    public void onDeselected(int i, int i1) {
                        titleText.setTextColor(Color.GRAY);
                    }

                    @Override
                    public void onLeave(int i, int i1, float v, boolean b) {

                    }

                    @Override
                    public void onEnter(int i, int i1, float v, boolean b) {

                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#965456"));
                return indicator;
            }
        });
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case ConstantManager.Nav.MORE_1:
                    popWindow.showAsDropDown(view);
                    break;
                case ConstantManager.Nav.BACK_1:
                    SwipeBackHelper.finish(FaatTeamActivity.this);
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
                    Intent intentM = new Intent(FaatTeamActivity.this, MainActivity.class);
                    startActivity(intentM);
                    break;
                case ConstantManager.Menu.MESSAGE:
                    Intent intentH = new Intent(FaatTeamActivity.this, MessageActivity.class);
                    startActivity(intentH);
                    break;
                case ConstantManager.Menu.CART:
                    Intent intentC = new Intent(FaatTeamActivity.this, CartActivity.class);
                    startActivity(intentC);
                    break;
            }
        }
    };
}
