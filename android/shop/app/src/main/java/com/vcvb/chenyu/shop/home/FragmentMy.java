package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.login.RegisterActivity;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.HashMap;

public class FragmentMy extends BaseFragment {
    View view;
    Context context;
    ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

        TextView login = view.findViewById(R.id.textView30);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });
        ImageView logo = view.findViewById(R.id.imageView15);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });
    }

    public void checkLogin() {
        HashMap<String, String> getUser = (HashMap<String, String>) UserInfoUtils.getInstance(context).getUserInfo();
        System.out.println(getUser);
        if (getUser.size() == 0) {

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
}
