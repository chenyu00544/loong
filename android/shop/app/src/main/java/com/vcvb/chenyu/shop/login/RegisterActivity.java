package com.vcvb.chenyu.shop.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.HashMap;

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onClickLogin(View view){
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("username", "test");
        userInfo.put("token", "fadfasfasdfasdfasdf.asdfasdfasdteertqwerwertfgb.dsrbhdfgrtwrtfgsdtys");
        userInfo.put("logo", "http://wewrweljwerwe");
        userInfo.put("nickname", "23234afasdafasdfasd");
        UserInfoUtils.getInstance(this).setUserInfo(userInfo);
        finish();
    }
}
