package com.vcvb.chenyu.shop.activity.center.userinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.base.BaseActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class UserNickActivity extends BaseActivity {
    private TextView save;
    private String nickname;
    private EditText nickEdit;
    private ImageView clear;
    private boolean isSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_nick);
        context = this;
        setNavBack();
        initView();
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView titleView = findViewById(R.id.textView123);
        titleView.setText(R.string.nick_title);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        save = findViewById(R.id.textView122);
        save.setText(R.string.save);
    }

    @Override
    public void initView() {
        super.initView();
        nickEdit = findViewById(R.id.editText8);
        clear = findViewById(R.id.imageView63);

        nickEdit.setFocusable(true);
        nickEdit.setFocusableInTouchMode(true);
        nickEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) nickEdit.getContext().getSystemService
                (Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(nickEdit, 0);

        nickEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(nickEdit.getText())) {
                    isSave = true;
                    nickname = String.valueOf(nickEdit.getText());
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickEdit.getText().clear();
            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSave) {
                    HashMap<String, String> mp = new HashMap<>();
                    mp.put("token", token);
                    mp.put("nickname", nickname);
                    HttpUtils.getInstance().post(ConstantManager.Url.SET_USER_INFO, mp, new HttpUtils
                            .NetCall() {
                        @Override
                        public void success(Call call, JSONObject json) throws IOException {

                            if (json != null) {
                                try {
                                    Integer code = json.getInt("code");
                                    if (code == 0) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent();
                                                intent.putExtra("nickname", nickname);
                                                HashMap<String, String> mp = new HashMap<>();
                                                mp.put("nickname", nickname);
                                                UserInfoUtils.getInstance(context).setUserInfo(mp);
                                                setResult(RESULT_OK, intent);
                                                UserNickActivity.this.finish();
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtils.showShortToast(context, "网络错误");
                                    }
                                });
                            }
                        }

                        @Override
                        public void failed(Call call, IOException e) {
                            ToastUtils.showShortToast(context, "网络错误");
                        }
                    });
                } else {
                    ToastUtils.showShortToast(context, "昵称不能为空");
                }
            }
        });

    }
}
