package com.vcvb.chenyu.shop.login;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.dialog.LoadingDialog2;

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
        initView();
        setNavBack();
        initListener();
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        int gravity = Gravity.CENTER;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
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
                .LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        LinearLayout title_wrap = (LinearLayout) findViewById(R.id.title_wrap);
        title_wrap.setAlpha(1);
        title_wrap.setLayoutParams(layoutParams3);
        title_wrap.addView(titleView);
    }

    public void initView() {
        qrCodeTv = (TextView) findViewById(R.id.textView69);
        registerTv = (TextView) findViewById(R.id.textView68);
        serverTv = (TextView) findViewById(R.id.textView71);
        interestsTv = (TextView) findViewById(R.id.textView73);
        serverTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        interestsTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        checkBox = (CheckBox) findViewById(R.id.checkBox5);

        phoneEdit = (EditText) findViewById(R.id.editText5);
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

        qrCodeEdit = (EditText) findViewById(R.id.editText6);
        passEdit = (EditText) findViewById(R.id.editText7);
    }

    public void initListener() {
        //获取验证码
        qrCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //立即注册
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_register = true;
                if (is_register) {
                    //注册后获取的数据
                    LoadingDialog2 loading = new LoadingDialog2(context, R.style.TransparentDialog);
                    loading.show();
                    Intent intent = new Intent();
                    intent.putExtra("username", "cb");
                    intent.putExtra("token", "csdfafafkoimlvj09809mljalfadf.adfjlajfoi098087.kjfaldjfladfl8979823423");
                    intent.putExtra("logo", "http://a3.topitme.com/1/21/79/1128833621e7779211o.jpg");
                    intent.putExtra("nickname", "chongchong");
                    loading.dismiss();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        //跳转相关服务
        serverTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //跳转权益政策
        interestsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    registerTv.setBackgroundResource(R.drawable.button_active_shape);
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
                    registerTv.setBackgroundResource(R.drawable.button_active_shape);
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
                    registerTv.setBackgroundResource(R.drawable.button_active_shape);
                }
            }
        });
    }

}
