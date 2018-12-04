package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.TimeUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

public class BindPhoneActivity extends BaseRecyclerViewActivity {

    EditText editText1;
    EditText editText2;
    TextView textView1;
    TextView textView2;
    boolean countDownB;
    Timer countdownTimer;
    Integer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bind_phone);
        changeStatusBarTextColor(true);
        context = this;
        countDownB = true;
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
        titleView.setText(R.string.phone_bind);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        TextView add = findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        editText1 = findViewById(R.id.editText16);
        editText2 = findViewById(R.id.editText18);
        textView1 = findViewById(R.id.textView258);
        textView2 = findViewById(R.id.textView259);
    }

    @Override
    public void initListener() {
        editText1.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);
        textView1.setOnClickListener(listener);
        textView2.setOnClickListener(listener);
    }

    public void setSendCodeButton(boolean b) {
        if (b) {
            textView1.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            textView1.setTextColor(context.getResources().getColor(R.color.black_29));
        }
    }

    public void setBindPhoneButton(boolean b) {
        if (b) {
            textView2.setBackgroundResource(R.drawable.shape_60_red);
        } else {
            textView2.setBackgroundResource(R.drawable.shape_60_gray);
        }
    }

    public void sendCode() {
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])"
                + "|" + "(147))\\d{8}$");
        Matcher m = p.matcher(editText1.getText().toString());
        if (m.matches()) {
            if (countDownB) {
                time = 120;
                countDownB = false;
                HashMap<String, String> mp = new HashMap<>();
                mp.put("token", token);
                mp.put("phone", editText1.getText().toString());
                HttpUtils.getInstance().post(ConstantManager.Url.SEND_SMS, mp, new HttpUtils
                        .NetCall() {
                    @Override
                    public void success(Call call, JSONObject json) throws IOException {
                        if (json != null) {
                            try {
                                if (json.getInt("code") == 0) {

                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ToastUtils.showShortToast(context, "请勿重复发送验证码");
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void failed(Call call, IOException e) {

                    }
                });
                TimeUtils.startCountdownByCount(120, textView1);
                countdownTimer = new Timer();
                countdownTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        time--;
                        Message message = new Message();
                        message.what = time;
                        mHandler.sendMessage(message);
                    }
                }, 0, 1000);
            }
        } else {
            ToastUtils.showShortToast(context, "输入的手机号码不正确");
        }
    }

    public void bindPhone() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("phone", editText1.getText().toString());
        mp.put("qrcode", editText2.getText().toString());
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.SET_USER_INFO, mp, new HttpUtils.NetCall
                () {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        final String msg = json.getString("msg");
                        if (json.getInt("code") == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("phone", editText1.getText().toString());
                                    setResult(RESULT_OK, intent);
                                    BindPhoneActivity.this.finish();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showShortToast(context, msg);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countdownTimer != null){
            countdownTimer.cancel();
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(editText1.getText())) {
                setSendCodeButton(true);
            } else {
                setSendCodeButton(false);
            }
            if (!TextUtils.isEmpty(editText1.getText()) && !TextUtils.isEmpty(editText2.getText()
            )) {
                setBindPhoneButton(true);
            } else {
                setBindPhoneButton(false);
            }
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textView258:
                    if (!TextUtils.isEmpty(editText1.getText())) {
                        sendCode();
                    }
                    break;
                case R.id.textView259:
                    if (!TextUtils.isEmpty(editText1.getText()) && !TextUtils.isEmpty
                            (editText2.getText())) {
                        bindPhone();
                    }
                    break;
            }
        }
    };

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what <= 0) {
                countDownB = true;
                countdownTimer.cancel();
                textView1.setText(R.string.get_qrcode);
            } else {
                String str = "　(%ds)　";
                textView1.setText(String.format(Locale.CHINA, str, msg.what));
            }
            return false;
        }
    });

}
