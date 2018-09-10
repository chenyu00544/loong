package com.vcvb.chenyu.shop.mycenter.userinfo;

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

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;

public class UserNickActivity extends BaseActivity {
    Context context;
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
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView titleView = (TextView) findViewById(R.id.textView123);
        titleView.setText(R.string.nick_title);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        save = (TextView) findViewById(R.id.textView122);
        save.setText(R.string.save);
    }

    @Override
    public void initView() {
        super.initView();
        nickEdit = (EditText) findViewById(R.id.editText8);
        clear = (ImageView) findViewById(R.id.imageView63);

        nickEdit.setFocusable(true);
        nickEdit.setFocusableInTouchMode(true);
        nickEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) nickEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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
                if(!TextUtils.isEmpty(nickEdit.getText()) ){
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
                if(isSave){
                    Intent intent = new Intent();
                    intent.putExtra("nickname", nickname);
                    setResult(RESULT_OK, intent);
                    UserNickActivity.this.finish();
                }
            }
        });

    }
}
