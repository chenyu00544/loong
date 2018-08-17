package com.ecjia.component.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

public class ECJiaErrorView extends LinearLayout {
    int errorImageId;
    String errorText;
    int errorTextId;
    Bitmap mBitmap;
    ImageView mImageView;
    TextView mTextView;
    private View mTouchView;

    class ECJiaErrorView_1 implements OnTouchListener {
        final /* synthetic */ ECJiaErrorView a;

        ECJiaErrorView_1(ECJiaErrorView eCJiaErrorView) {
            this.a = eCJiaErrorView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public ECJiaErrorView(Context context) {
        this(context, null);
    }

    public ECJiaErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.layout_error_view, this, true);
        this.mTextView = (TextView) findViewById(R.id.error_text);
        this.mImageView = (ImageView) findViewById(R.id.error_image);
        this.mTouchView = findViewById(R.id.error_touch_view);
        this.mTouchView.setOnTouchListener(new ECJiaErrorView_1(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaErrorView);
        this.errorImageId = obtainStyledAttributes.getResourceId(0, -1);
        this.errorTextId = obtainStyledAttributes.getResourceId(1, -1);
        this.errorText = obtainStyledAttributes.getString(1);
        obtainStyledAttributes.recycle();
        init();
    }

    void init() {
        if (this.errorTextId != -1) {
            this.mTextView.setText(this.errorTextId);
        }
        if (this.errorImageId != -1) {
            this.mImageView.setImageResource(this.errorImageId);
        }
        if (this.mBitmap != null) {
            this.mImageView.setImageBitmap(this.mBitmap);
        }
        if (this.errorText != null) {
            this.mTextView.setText(this.errorText);
        }
    }

    public void setErrorText(int i) {
        this.errorTextId = i;
        this.mTextView.setText(this.errorTextId);
        init();
    }

    public void setErrorText(CharSequence charSequence) {
        this.errorTextId = -1;
        this.errorText = (String) charSequence;
        this.mTextView.setText(this.errorText);
        init();
    }

    public void setErrorImageResource(int i) {
        this.mBitmap = null;
        this.errorImageId = i;
        this.mImageView.setImageResource(this.errorImageId);
        init();
    }

    public void setErrorImageBitmap(Bitmap bitmap) {
        this.errorImageId = -1;
        this.mBitmap = bitmap;
        this.mImageView.setImageBitmap(this.mBitmap);
        init();
    }
}
