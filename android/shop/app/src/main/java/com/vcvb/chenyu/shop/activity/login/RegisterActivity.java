package com.vcvb.chenyu.shop.activity.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.activity.web.WebActivity;
import com.vcvb.chenyu.shop.base.BaseActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog2;
import com.vcvb.chenyu.shop.tools.CountDownTimeUtils;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

public class RegisterActivity extends BaseActivity {
    private Context context;

    private EditText phoneEdit;
    private EditText qrCodeEdit;
    private EditText passEdit;

    private TextView qrCodeTv;
    private TextView registerTv;
    private TextView serverTv;
    private TextView interestsTv;

    private CheckBox checkBox;
    private boolean is_register = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        context = this;
        changeStatusBarTextColor(true);
        initView();
        setNavBack();
        initListener();
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        int gravity = Gravity.CENTER;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView titleView = new TextView(this);
        titleView.setLayoutParams(layoutParams);
        titleView.setGravity(gravity);
        titleView.setText(R.string.phone_reg);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(120, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout nav_other = (LinearLayout) findViewById(R.id.nav_other);
        nav_other.setLayoutParams(layoutParams2);
        nav_other.setAlpha(0);

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = (LinearLayout) findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
    }

    public void initView() {
        qrCodeTv = findViewById(R.id.textView69);
        registerTv = findViewById(R.id.textView68);
        serverTv = findViewById(R.id.textView71);
        interestsTv = findViewById(R.id.textView73);
        serverTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        serverTv.getPaint().setAntiAlias(true);
        interestsTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        interestsTv.getPaint().setAntiAlias(true);

        checkBox = findViewById(R.id.checkBox5);

        phoneEdit = findViewById(R.id.editText5);
        phoneEdit.setFocusable(true);
        phoneEdit.setFocusableInTouchMode(true);
        phoneEdit.requestFocus();
        //不在Activity中用这个代码
//        InputMethodManager imm = (InputMethodManager) phoneView.getContext().getSystemService
// (Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(phoneView, 0);
        //在Activity中用这个代码
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        qrCodeEdit = findViewById(R.id.editText6);
        passEdit = findViewById(R.id.editText7);
    }

    public void initListener() {
        //获取验证码
        qrCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(phoneEdit.getText())) {
                    Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3," +
                            "5-8])|(18[0-9])|(147))\\d{8}$");
                    Matcher m = p.matcher(phoneEdit.getText().toString());
                    if (m.matches()) {
                        CountDownTimeUtils.check(CountDownTimeUtils.SETTING_FINANCE_ACCOUNT_TIME,
                                false);
                        CountDownTimeUtils.startCountdown(qrCodeTv);

                        //调用验证码接口
                        HashMap<String, String> mp = new HashMap<>();
                        mp.put("phone", phoneEdit.getText().toString());
                        HttpUtils.getInstance().post(ConstantManager.Url.SEND_SMS, mp, new
                                HttpUtils.NetCall() {
                                    @Override
                                    public void success(Call call, JSONObject json) throws
                                            IOException {

                                    }

                                    @Override
                                    public void failed(Call call, IOException e) {

                                    }
                                });

                    } else {
                        ToastUtils.showShortToast(context, "输入的手机号码不正确");
                    }

                }
            }
        });
        //立即注册
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_register) {
                    final LoadingDialog2 loading = new LoadingDialog2(context, R.style
                            .TransparentDialog);
                    loading.show();

                    HashMap<String, String> mp = new HashMap<>();
                    mp.put("username", phoneEdit.getText().toString());
                    mp.put("password", passEdit.getText().toString());
                    mp.put("code", qrCodeEdit.getText().toString());
                    mp.put("device_id", (String) UserInfoUtils.getInstance(context).getUserInfo()
                            .get("device_id"));
                    HttpUtils.getInstance().post(ConstantManager.Url.REGISTER, mp, new HttpUtils
                            .NetCall() {
                        @Override
                        public void success(Call call, final JSONObject json) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (json != null) {
                                        try {
                                            if (json.getInt("code") == 0) {
                                                JSONObject data = json.getJSONObject("data");
                                                String username = data.getString("user_name");
                                                String token = json.getString("token");
                                                String logo = data.getString("logo");
                                                String nick_name = data.getString("nick_name");
                                                String mobile_phone = data.getString
                                                        ("mobile_phone");
                                                String user_money = data.getString("user_money");
                                                String is_real = data.getString("is_real");
                                                String server_id = data.getString("server_id");

                                                Intent intent = new Intent();
                                                intent.putExtra("username", username);
                                                intent.putExtra("token", token);
                                                intent.putExtra("logo", logo);
                                                intent.putExtra("nickname", nick_name);
                                                intent.putExtra("is_real", is_real);
                                                intent.putExtra("mobile_phone", mobile_phone);
                                                intent.putExtra("user_money", user_money);
                                                intent.putExtra("server_id", server_id);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            } else {
                                                String msg = json.getString("msg");
                                                ToastUtils.showShortToast(context, msg);
                                            }
                                            loading.dismiss();
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
                                    loading.dismiss();
                                    ToastUtils.showShortToast(context, "网络错误");
                                }
                            });
                        }
                    });
                }
            }
        });
        //跳转相关服务
        serverTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, WebActivity.class);
                intent.putExtra("url", ConstantManager.Url.USER_PROTOCOL);
                startActivity(intent);
            }
        });
        //跳转权益政策
        interestsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, WebActivity.class);
                intent.putExtra("url", ConstantManager.Url.USER_PRIVACY);
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!TextUtils.isEmpty(phoneEdit.getText()) && !TextUtils.isEmpty(qrCodeEdit
                        .getText()) && !TextUtils.isEmpty(passEdit.getText()) && isChecked) {
                    is_register = true;
                    registerTv.setBackgroundResource(R.drawable.shape_button_active);
                } else {
                    is_register = false;
                    registerTv.setBackgroundResource(R.drawable.shape_button_none);
                }
            }
        });

        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(phoneEdit.getText()) && !TextUtils.isEmpty(qrCodeEdit
                        .getText()) && !TextUtils.isEmpty(passEdit.getText()) && checkBox
                        .isChecked()) {
                    is_register = true;
                    registerTv.setBackgroundResource(R.drawable.shape_button_active);
                } else {
                    is_register = false;
                    registerTv.setBackgroundResource(R.drawable.shape_button_none);
                }
            }
        });

        qrCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(phoneEdit.getText()) && !TextUtils.isEmpty(qrCodeEdit
                        .getText()) && !TextUtils.isEmpty(passEdit.getText()) && checkBox
                        .isChecked()) {
                    is_register = true;
                    registerTv.setBackgroundResource(R.drawable.shape_button_active);
                } else {
                    is_register = false;
                    registerTv.setBackgroundResource(R.drawable.shape_button_none);
                }
            }
        });

        passEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(phoneEdit.getText()) && !TextUtils.isEmpty(qrCodeEdit
                        .getText()) && !TextUtils.isEmpty(passEdit.getText()) && checkBox
                        .isChecked()) {
                    is_register = true;
                    registerTv.setBackgroundResource(R.drawable.shape_button_active);
                } else {
                    is_register = false;
                    registerTv.setBackgroundResource(R.drawable.shape_button_none);
                }
            }
        });
    }

}
