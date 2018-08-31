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
import com.vcvb.chenyu.shop.login.RegisterActivity;
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

    public void initListener() {
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
        }else{
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
        }
    }

    public void onClickSignOut() {
        UserInfoUtils.getInstance(context).clear();
        goToRegister();
    }

    public void goToRegister() {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }

    public void showLoginDialog() {
        Intent intent = new Intent();
        intent.setAction("LoginClick");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
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
}
