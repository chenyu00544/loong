package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.login.LoginActivity;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.HashMap;

public class FragmentMy extends BaseFragment {
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        context = getActivity();
        checkLogin();
        initClicktListener();
        return view;
    }

    public void initClicktListener() {
        View singOut = view.findViewById(R.id.view9);
        singOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignOut();
            }
        });
    }

    public void checkLogin() {
        HashMap<String, String> getUser = (HashMap<String, String>) UserInfoUtils.getInstance(context).getUserInfo();
        System.out.println(getUser);
        if (getUser.size() == 0) {
            goToLogin();
        }
    }

    public void onClickSignOut() {
        UserInfoUtils.getInstance(context).clear();
        goToLogin();
    }

    public void goToLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }
}
