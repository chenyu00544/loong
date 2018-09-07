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

import com.squareup.picasso.Picasso;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.mycenter.AddressActivity;
import com.vcvb.chenyu.shop.mycenter.BrowseActivity;
import com.vcvb.chenyu.shop.mycenter.MyCollectionActivity;
import com.vcvb.chenyu.shop.mycenter.OrderActivity;
import com.vcvb.chenyu.shop.mycenter.UserInfoActivity;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.Map;

public class FragmentMy extends BaseFragment {
    View view;
    Context context;
    ViewGroup container;
    private Receiver receiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        context = getActivity();
        this.container = container;
        checkLogin();
        initListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("UserInfoCall");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "UserInfoCall":
                        checkLogin();
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

    public void initListener() {
        View orderAll1 = view.findViewById(R.id.view2);
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
                onClickSignOut();
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
    }

    public void checkLogin() {
        Map<String, ?> mp = UserInfoUtils.getInstance(context).getUserInfo();
        ImageView iv = view.findViewById(R.id.imageView15);
        TextView tv = view.findViewById(R.id.textView30);
        if (mp.size() > 0) {
            Picasso.with(context).load((String) mp.get("logo")).into(iv);
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
            iv.setBackgroundResource(R.drawable.icon_boy_head);
            tv.setText(R.string.login_reg);
        }
    }

    //退出登录
    public void onClickSignOut() {
        UserInfoUtils.getInstance(context).clear();
        checkLogin();
    }

    public void showLoginDialog() {
        Intent intent = new Intent();
        intent.setAction("LoginClick");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void goToOrderActivity(int type) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    public void goToReturnOrderActivity() {
        Intent intent = new Intent(context, OrderActivity.class);
        startActivity(intent);
    }

    public void goToCollectionActivity() {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        startActivity(intent);
    }

    public void goToAddressActivity() {
        Intent intent = new Intent(context, AddressActivity.class);
        startActivity(intent);
    }

    public void goToBrowseActivity(){
        Intent intent = new Intent(context, BrowseActivity.class);
        startActivity(intent);
    }

    public void goToUserCenterActivity(){
        Intent intent = new Intent(context, UserInfoActivity.class);
        startActivity(intent);
    }
}
