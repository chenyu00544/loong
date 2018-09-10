package com.vcvb.chenyu.shop.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;

public class SexDialog extends AlertDialog {
    private SexDialog.OnDialogClickListener onDialogClickListener;
    private TextView man;
    private TextView woman;
    private TextView cancel;

    private ImageView manIV;
    private ImageView womanIV;

    public SexDialog(Context context) {
        super(context, R.style.TranslucentDialog);
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setTick(boolean sex) {
        if(sex){
            manIV.setAlpha(255);
            womanIV.setAlpha(0);
        }else{
            manIV.setAlpha(0);
            womanIV.setAlpha(255);
        }
    }

    private void initView() {
        this.setContentView(R.layout.dialog_sex);
        man = this.findViewById(R.id.textView140);
        woman = this.findViewById(R.id.textView141);
        cancel = this.findViewById(R.id.textView142);
        manIV = this.findViewById(R.id.imageView64);
        womanIV = this.findViewById(R.id.imageView65);
        setTick(true);
        initListener();
    }

    public interface OnDialogClickListener {
        void onManClickListener();

        void onWomanClickListener();

        void onCancelClickListener();
    }

    public void setOnDialogClickListener(SexDialog.OnDialogClickListener
                                                 onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    private void initListener() {
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onManClickListener();
                }
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onWomanClickListener();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onCancelClickListener();
                }
            }
        });
    }
}
