package com.vcvb.chenyu.shop;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog2;
import com.vcvb.chenyu.shop.dialog.LoginDialog;
import com.vcvb.chenyu.shop.home.FragmentCart;
import com.vcvb.chenyu.shop.home.FragmentCategory;
import com.vcvb.chenyu.shop.home.FragmentFind;
import com.vcvb.chenyu.shop.home.FragmentHome;
import com.vcvb.chenyu.shop.home.FragmentMy;
import com.vcvb.chenyu.shop.login.RegisterActivity;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    private BottomBar bottomBar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentHome fragmentHome;
    private FragmentCategory fragmentCategory;
    private FragmentFind fragmentFind;
    private FragmentCart fragmentCart;
    private FragmentMy fragmentMy;
    private Receiver receiver;
    private Context context;
    private int index = 0;
    private String SAVED_INDEX = "SAVED_INDEX";
    private String[] fragmentTag = new String[]{"home", "categroy", "find", "cart", "my"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);//设置是否可滑动
        setContentView(R.layout.activity_main);
        context = this;
        fragmentManager = getSupportFragmentManager();
        bottomInit();
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(SAVED_INDEX, index);
            fragmentHome = (FragmentHome) fragmentManager.findFragmentByTag(fragmentTag[0]);
            fragmentCategory = (FragmentCategory) fragmentManager.findFragmentByTag(fragmentTag[1]);
            fragmentFind = (FragmentFind) fragmentManager.findFragmentByTag(fragmentTag[2]);
            fragmentCart = (FragmentCart) fragmentManager.findFragmentByTag(fragmentTag[3]);
            fragmentMy = (FragmentMy) fragmentManager.findFragmentByTag(fragmentTag[4]);
        }
        setClick(index);
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
//            public void onPageScrolled(int position, float positionOffset, int
// positionOffsetPixels) {
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
                    fragmentTransaction.add(R.id.fragment_content, fragmentHome, fragmentTag[0]);
                } else {
                    //如果不为空就显示出来
                    fragmentTransaction.show(fragmentHome);
                }
                changeStatusBarTextColor(false);
                break;
            case 1:
                if (fragmentCategory == null) {
                    fragmentCategory = new FragmentCategory();
                    fragmentTransaction.add(R.id.fragment_content, fragmentCategory,
                            fragmentTag[1]);
                } else {
                    fragmentTransaction.show(fragmentCategory);
                }
                changeStatusBarTextColor(false);
                break;
            case 2:
                if (fragmentFind == null) {
                    fragmentFind = new FragmentFind();
                    fragmentTransaction.add(R.id.fragment_content, fragmentFind, fragmentTag[2]);
                } else {
                    fragmentTransaction.show(fragmentFind);
                }
                changeStatusBarTextColor(false);
                break;
            case 3:
                if (fragmentCart == null) {
                    fragmentCart = new FragmentCart();
                    fragmentTransaction.add(R.id.fragment_content, fragmentCart, fragmentTag[3]);
                } else {
                    fragmentTransaction.show(fragmentCart);
                }
                changeStatusBarTextColor(false);
                break;
            case 4:
                if (fragmentMy == null) {
                    fragmentMy = new FragmentMy();
                    fragmentTransaction.add(R.id.fragment_content, fragmentMy, fragmentTag[4]);
                } else {
                    fragmentTransaction.show(fragmentMy);
                }
                changeStatusBarTextColor(false);
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

    public void showLoginDialog() {
        LoginDialog.getInstance(this).setOnDialogClickListener(new LoginDialog
                .OnDialogClickListener() {
            @Override
            public void onPhoneClickListener() {
                System.out.println("onPhoneClickListener");
                LoginDialog.getInstance(context).phoneLogin();
            }

            @Override
            public void onEmailClickListener() {
                System.out.println("onEmailClickListener");
                LoginDialog.getInstance(context).emailLogin();
            }

            @Override
            public void onRegisterClickListener() {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivityForResult(intent, ConstantManager.REGISTER_FOR_MY);
            }

            @Override
            public void onProblemClickListener() {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLoginClickListener(Map<String, String> user) {
                LoadingDialog2.getInstance(context).show();
                HashMap<String, String> u = new HashMap<>();

                //获取服务器数据
                //..
                u.put("username", "cb");
                u.put("token", "csdfafafkoimlvj09809mljalfadf" + "" + "" + "" + "" +
                        ".adfjlajfoi098087.kjfaldjfladfl8979823423");
                u.put("logo", "http://a3.topitme.com/1/21/79/1128833621e7779211o.jpg");
                u.put("nickname", "chongchong");

                UserInfoUtils.getInstance(context).setUserInfo(u);
                LoadingDialog2.getInstance(context).dismiss();
                Intent intent = new Intent();
                intent.setAction("UserInfoCall");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                LoginDialog.getInstance(context).hide();
            }
        });
        LoginDialog.getInstance(this).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("LoginClick");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "LoginClick":
                        showLoginDialog();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ConstantManager.REGISTER_FOR_MY) {

                HashMap<String, String> u = new HashMap<>();
                u.put("username", data.getStringExtra("username"));
                u.put("token", data.getStringExtra("token"));
                u.put("logo", data.getStringExtra("logo"));
                u.put("nickname", data.getStringExtra("nickname"));

                UserInfoUtils.getInstance(context).setUserInfo(u);
                Intent intent = new Intent();
                intent.setAction("UserInfoCall");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                LoginDialog.getInstance(context).hide();
            }
        }
    }

    public boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            } else {
                moveTaskToBack(true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVED_INDEX, index);
        super.onSaveInstanceState(outState);
    }
}
