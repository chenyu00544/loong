package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.activity.center.userinfo.UserInfoActivity;
import com.vcvb.chenyu.shop.base.BaseFragment;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.ConfirmDialog;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.activity.center.AddressActivity;
import com.vcvb.chenyu.shop.activity.center.BrowseActivity;
import com.vcvb.chenyu.shop.activity.center.MyCollectionActivity;
import com.vcvb.chenyu.shop.order.OrderListActivity;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;

public class FragmentMy extends BaseFragment {
    View view;
    ViewGroup container;
    private Receiver receiver;
    private ConfirmDialog confirmDialog;
    private UserInfoBean userInfoBean = new UserInfoBean();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_my, container, false);
        context = getActivity();
        this.container = container;
        initView();
        initListener();
        checkLogin();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getData();
        }
    }

    @Override
    public void getData() {
        if (token != null && !token.equals("")) {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("token", token);
            HttpUtils.getInstance().post(ConstantManager.Url.GET_USER_INFO, mp, new HttpUtils.NetCall() {
                @Override
                public void success(Call call, final JSONObject json) throws IOException {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bindViewData(json);
                                checkLogin();
                            }
                        });
                    }
                }

                @Override
                public void failed(Call call, IOException e) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                checkLogin();
                                ToastUtils.showShortToast(context, "网络错误");
                            }
                        });
                    }
                }
            });
        }
    }

    public void bindViewData(JSONObject json) {
        if (json != null) {
            try {
                int code = json.getInt("code");
                if (code == 0) {
                    JSONObject object = json.getJSONObject("data");
                    userInfoBean = JsonUtils.fromJsonObject(object, UserInfoBean.class);
                    HashMap<String, String> mp = new HashMap<>();
                    mp.put("nickname", userInfoBean.getNick_name());
                    mp.put("logo", userInfoBean.getLogo());
                    UserInfoUtils.getInstance(context).setUserInfo(mp);
                    bindView();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void bindView() {
        TextView user_money = view.findViewById(R.id.textView33);
        TextView bonus_money = view.findViewById(R.id.textView34);
        TextView pay_points = view.findViewById(R.id.textView52);
        user_money.setText(userInfoBean.getUser_money());
        bonus_money.setText(userInfoBean.getBonus_money());
        pay_points.setText(userInfoBean.getPay_points());

        TextView order_unpayed = view.findViewById(R.id.textView45);
        if (userInfoBean.getOrder_unpayed_count() > 0) {
            order_unpayed.setAlpha(1);
            String str = "%d";
            order_unpayed.setText(String.format(Locale.CHINA, str, userInfoBean
                    .getOrder_unpayed_count()));
        }else{
            order_unpayed.setText("0");
            order_unpayed.setAlpha(0);
        }
        TextView order_unship = view.findViewById(R.id.textView46);
        if (userInfoBean.getOrder_unship_count() > 0) {
            order_unship.setAlpha(1);
            String str = "%d";
            order_unship.setText(String.format(Locale.CHINA, str, userInfoBean
                    .getOrder_unship_count()));
        }else {
            order_unship.setText("0");
            order_unship.setAlpha(0);
        }
        TextView order_shipped = view.findViewById(R.id.textView47);
        if (userInfoBean.getOrder_shipped_count() > 0) {
            order_shipped.setAlpha(1);
            String str = "%d";
            order_shipped.setText(String.format(Locale.CHINA, str, userInfoBean
                    .getOrder_shipped_count()));
        }else {
            order_shipped.setText("0");
            order_shipped.setAlpha(0);
        }
        TextView order_comment = view.findViewById(R.id.textView48);
        if (userInfoBean.getOrder_comment_count() > 0) {
            order_comment.setAlpha(1);
            String str = "%d";
            order_comment.setText(String.format(Locale.CHINA, str, userInfoBean
                    .getOrder_comment_count()));
        }else {
            order_comment.setText("0");
            order_comment.setAlpha(0);
        }
        TextView order_return = view.findViewById(R.id.textView49);
        if (userInfoBean.getOrder_return_count() > 0) {
            order_return.setAlpha(1);
            String str = "%d";
            order_return.setText(String.format(Locale.CHINA, str, userInfoBean
                    .getOrder_return_count()));
        }else {
            order_return.setText("0");
            order_return.setAlpha(0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("UserInfoCall");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "UserInfoCall":
                        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
                        getData();
                        break;
                }
            }
        };
        broadcastManager.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        broadcastManager.unregisterReceiver(receiver);
    }

    public void initView() {
        confirmDialog = new ConfirmDialog(context);
        confirmDialog.setTitle(R.string.is_logout);
    }

    public void initListener() {
        View orderAll1 = view.findViewById(R.id.view81);
        orderAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(1);
            }
        });
        ImageView order1 = view.findViewById(R.id.imageView16);
        order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(2);
            }
        });
        TextView orderTv1 = view.findViewById(R.id.textView40);
        orderTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(2);
            }
        });
        ImageView order2 = view.findViewById(R.id.imageView17);
        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(3);
            }
        });
        TextView orderTv2 = view.findViewById(R.id.textView41);
        orderTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(3);
            }
        });
        ImageView order3 = view.findViewById(R.id.imageView18);
        order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(4);
            }
        });
        TextView orderTv3 = view.findViewById(R.id.textView42);
        orderTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(4);
            }
        });
        ImageView order4 = view.findViewById(R.id.imageView19);
        order4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(5);
            }
        });
        TextView orderTv4 = view.findViewById(R.id.textView43);
        orderTv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(5);
            }
        });
        ImageView order5 = view.findViewById(R.id.imageView20);
        order5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToReturnOrderActivity();
            }
        });
        TextView orderTv5 = view.findViewById(R.id.textView44);
        orderTv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToReturnOrderActivity();
            }
        });

        View collection = view.findViewById(R.id.view4);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCollectionActivity();
            }
        });

        View address = view.findViewById(R.id.view6);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddressActivity();
            }
        });

        View browse = view.findViewById(R.id.view7);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBrowseActivity();
            }
        });

        View user = view.findViewById(R.id.view8);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUserCenterActivity();
            }
        });

        View singOut = view.findViewById(R.id.view9);
        singOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogout();
            }
        });

        final TextView header = view.findViewById(R.id.textView37);
        header.setAlpha(0);
        NestedScrollView scrollView = view.findViewById(R.id.my_scroll_view);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int
                    oldScrollX, int oldScrollY) {
                if (oldScrollY >= 255) {
                    header.setAlpha(1);
                } else {
                    header.setAlpha((float) oldScrollY / 255);
                }
            }
        });

        confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
            @Override
            public void onConfirmClickListener() {
                confirmDialog.dismiss();
                logoutBySure();
            }

            @Override
            public void onCancelClickListener() {
                confirmDialog.dismiss();
            }
        });
    }

    //检查是否已经登录
    public void checkLogin() {
        Map<String, ?> mp = UserInfoUtils.getInstance(context).getUserInfo();
        ImageView iv = view.findViewById(R.id.imageView15);
        TextView tv = view.findViewById(R.id.textView30);

        RequestOptions requestOptions = RequestOptions.circleCropTransform().error(R.drawable
                .icon_boy_head);
        if (mp.get("nickname") != null && token != null && !token.equals("")) {
            Glide.with(context).load((String) mp.get("logo")).apply(requestOptions).into(iv);
            tv.setText((CharSequence) mp.get("nickname"));
            iv.setOnClickListener(null);
            tv.setOnClickListener(null);
        } else {
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showLoginDialog();
                }
            });
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showLoginDialog();
                }
            });
            Glide.with(context).load(R.drawable.icon_boy_head).apply(requestOptions).into(iv);
            tv.setText(R.string.login_reg);
        }
    }

    //退出登录吗?
    public void onClickLogout() {
        confirmDialog.show();
    }

    //确定退出登录
    public void logoutBySure() {
        UserInfoUtils.getInstance(context).clear();
        token = "";
        userInfoBean.setUser_money("0");
        userInfoBean.setBonus_money("0");
        userInfoBean.setPay_points("0");
        userInfoBean.setOrder_unpayed_count(0);
        userInfoBean.setOrder_unship_count(0);
        userInfoBean.setOrder_shipped_count(0);
        userInfoBean.setOrder_comment_count(0);
        userInfoBean.setOrder_return_count(0);
        checkLogin();
        bindView();
    }

    public void showLoginDialog() {
        Intent intent = new Intent();
        intent.setAction("LoginClick");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void goToOrderActivity(int type) {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            Intent intent = new Intent(context, OrderListActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
        }
    }

    public void goToReturnOrderActivity() {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            Intent intent = new Intent(context, OrderListActivity.class);
            startActivity(intent);
        }
    }

    public void goToCollectionActivity() {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            Intent intent = new Intent(context, MyCollectionActivity.class);
            startActivity(intent);
        }
    }

    public void goToAddressActivity() {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            Intent intent = new Intent(context, AddressActivity.class);
            startActivity(intent);
        }
    }

    public void goToBrowseActivity() {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            Intent intent = new Intent(context, BrowseActivity.class);
            startActivity(intent);
        }
    }

    public void goToUserCenterActivity() {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            Intent intent = new Intent(context, UserInfoActivity.class);
            startActivity(intent);
        }
    }
}
