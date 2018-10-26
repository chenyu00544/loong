package com.vcvb.chenyu.shop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.tools.CountDownTimeUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginDialog extends Dialog {
    private static LoginDialog loginDialog;
    private OnDialogClickListener onDialogClickListener;
    private View phoneView;
    private View emailView;
    private TextView registerView;

    private EditText phoneEdit;
    private EditText phonePassEdit;
    private TextView phoneLogin;
    private TextView phoneProblem;
    private TextView phoneLoginType;
    private TextView phoneQRCode;
    private boolean loginType = true;
    private EditText emailEdit;
    private EditText emailPassEdit;
    private TextView emailLogin;

    private TextView backView;

    public static LoginDialog getInstance(Context context) {
        if (loginDialog == null) {
            loginDialog = new LoginDialog(context, R.style.TranslucentDialog); //设置Dialog背景透明度
            loginDialog.setCancelable(false);
            loginDialog.setCanceledOnTouchOutside(true);
        }
        return loginDialog;
    }

    public LoginDialog(Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.BottomShow);
    }

    public LoginDialog(Context context) {
        super(context, R.style.TranslucentDialog);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.BottomShow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initloginView();
    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = display.getWidth();
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        initloginView();
    }

    public interface OnDialogClickListener {
        void onPhoneClickListener();

        void onEmailClickListener();

        void onRegisterClickListener();

        void onProblemClickListener();

        void onLoginClickListener(Map<String, String> user);
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    private void initListener() {
        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onPhoneClickListener();
                }
            }
        });
        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onEmailClickListener();
                }
            }
        });
        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onRegisterClickListener();
                }
            }
        });
    }

    private void initloginView() {
        this.setContentView(R.layout.login_dialog);
        phoneView = this.findViewById(R.id.view11);
        emailView = this.findViewById(R.id.view12);
        registerView = this.findViewById(R.id.textView58);
        initListener();
    }

    public void phoneLogin() {
        this.setContentView(R.layout.login_phone_dialog);
        backView = this.findViewById(R.id.textView50);
        phoneEdit = this.findViewById(R.id.editText);
        phonePassEdit = this.findViewById(R.id.editText2);
        phoneLogin = this.findViewById(R.id.textView61);
        phoneProblem = this.findViewById(R.id.textView62);
        phoneLoginType = this.findViewById(R.id.textView63);
        phoneQRCode = this.findViewById(R.id.textView64);
        if (CountDownTimeUtils.check(CountDownTimeUtils.SETTING_FINANCE_ACCOUNT_TIME, true)) {
            CountDownTimeUtils.startCountdown(phoneQRCode);
        }
        phoneEdit.setFocusable(true);
        phoneEdit.setFocusableInTouchMode(true);
        phoneEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) phoneEdit.getContext().getSystemService
                (Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(phoneEdit, 0);
        phoneQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(phoneEdit.getText())) {
                    Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3," +
                            "5-8])|(18[0-9])|(147))\\d{8}$");
                    Matcher m = p.matcher(phoneEdit.getText().toString());
                    if (m.matches()) {
                        CountDownTimeUtils.check(CountDownTimeUtils.SETTING_FINANCE_ACCOUNT_TIME, false);
                        CountDownTimeUtils.startCountdown(phoneQRCode);
                    } else {
                        ToastUtils.showShortToast(getContext(), "输入的手机号码不正确");
                    }
                    //调用验证码接口
                }
            }
        });
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initloginView();
            }
        });
        phoneLoginType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginType) {
                    //改为密码登录
                    loginType = false;
                    phoneQRCode.setAlpha(0);
                    phoneQRCode.setEnabled(false);
                    phonePassEdit.setHint(R.string.password);
                    phoneLoginType.setText(R.string.qrcode_login);
                } else {
                    //改为验证码登录
                    loginType = true;
                    phoneQRCode.setAlpha(1);
                    phoneQRCode.setEnabled(true);
                    phonePassEdit.setHint(R.string.qrcode);
                    phoneLoginType.setText(R.string.pass_login);
                }
            }
        });
        phoneProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onProblemClickListener();
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
                if (!TextUtils.isEmpty(phoneEdit.getText()) && !TextUtils.isEmpty(phonePassEdit
                        .getText())) {
                    setPhoneLogin(true);
                } else {
                    setPhoneLogin(false);
                }
            }
        });
        phonePassEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(phoneEdit.getText()) && !TextUtils.isEmpty(phonePassEdit
                        .getText())) {
                    setPhoneLogin(true);
                } else {
                    setPhoneLogin(false);
                }
            }
        });
    }

    public void emailLogin() {
        this.setContentView(R.layout.login_email_dialog);
        backView = this.findViewById(R.id.textView65);
        emailEdit = this.findViewById(R.id.editText3);
        emailPassEdit = this.findViewById(R.id.editText4);
        emailLogin = this.findViewById(R.id.textView67);
        emailEdit.setFocusable(true);
        emailEdit.setFocusableInTouchMode(true);
        emailEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) emailEdit.getContext().getSystemService
                (Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(emailEdit, 0);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initloginView();
            }
        });
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(emailEdit.getText()) && !TextUtils.isEmpty(emailPassEdit
                        .getText())) {
                    setEmailLogin(true);
                } else {
                    setEmailLogin(false);
                }
            }
        });
        emailPassEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(emailEdit.getText()) && !TextUtils.isEmpty(emailPassEdit
                        .getText())) {
                    setEmailLogin(true);
                } else {
                    setEmailLogin(false);
                }
            }
        });
    }

    public void setPhoneLogin(boolean bool) {
        if (phoneLogin != null) {
            if (bool) {
                phoneLogin.setBackgroundResource(R.drawable.shape_button_active);
                phoneLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onDialogClickListener != null) {
                            HashMap<String, String> user = new HashMap<>();
                            user.put("username", phoneEdit.getText().toString());
                            user.put("email", "");
                            if (loginType) {
                                user.put("qrcode", phonePassEdit.getText().toString());
                            } else {
                                user.put("password", phonePassEdit.getText().toString());
                            }
                            System.out.println(user);
                            onDialogClickListener.onLoginClickListener(user);
                        }
                    }
                });
            } else {
                phoneLogin.setBackgroundResource(R.drawable.shape_button_none);
                phoneLogin.setOnClickListener(null);
            }

        }
    }

    public void setEmailLogin(boolean bool) {
        if (emailLogin != null) {
            if (bool) {
                emailLogin.setBackgroundResource(R.drawable.shape_button_active);
                emailLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onDialogClickListener != null) {
                            HashMap<String, String> user = new HashMap<>();
                            user.put("email", emailEdit.getText().toString());
                            user.put("username", "");
                            user.put("password", emailPassEdit.getText().toString());
                            user.put("type", "0");
                            onDialogClickListener.onLoginClickListener(user);
                        }
                    }
                });
            } else {
                emailLogin.setBackgroundResource(R.drawable.shape_button_none);
                emailLogin.setOnClickListener(null);
            }
        }
    }


}
