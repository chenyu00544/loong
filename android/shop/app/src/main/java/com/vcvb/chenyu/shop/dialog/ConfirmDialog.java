package com.vcvb.chenyu.shop.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;

public class ConfirmDialog extends AlertDialog {
    private ConfirmDialog.OnDialogClickListener onDialogClickListener;
    private TextView content;
    private TextView confirm;
    private TextView cancel;
    private int title;

    public ConfirmDialog(Context context) {
        super(context, R.style.TranslucentDialog);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        Window window = getWindow();
//        window.setGravity(Gravity.CENTER_VERTICAL);
//        window.setWindowAnimations(R.style.CenterShow);
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
        content.setText(title);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setTitle(int id) {
        title = id;
    }

    private void initView() {
        this.setContentView(R.layout.dialog_confirm);
        content = this.findViewById(R.id.textView114);
        confirm = this.findViewById(R.id.textView115);
        cancel = this.findViewById(R.id.textView116);
        initListener();
    }

    public interface OnDialogClickListener {
        void onConfirmClickListener();

        void onCancelClickListener();
    }

    public void setOnDialogClickListener(ConfirmDialog.OnDialogClickListener
                                                 onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    private void initListener() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onConfirmClickListener();
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
