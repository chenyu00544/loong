package com.ecjia.component.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

public class ECJiaSignView extends FrameLayout {
    String content;
    ImageView signBackground;
    TextView signContent;
    boolean signType;

    public ECJiaSignView(Context context) {
        this(context, null);
    }

    public ECJiaSignView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaSignView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.signType = false;
        LayoutInflater.from(context).inflate(R.layout.layout_signview, this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaSignView);
        this.content = obtainStyledAttributes.getString(0);
        this.signType = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        this.signBackground = (ImageView) findViewById(R.id.signview_background);
        this.signContent = (TextView) findViewById(R.id.signview_content);
        init();
    }

    void init() {
        if (this.signType) {
            this.signBackground.setImageResource(R.drawable.image_signin_button_signed);
        } else {
            this.signBackground.setImageResource(R.drawable.image_signin_button_unsigned);
        }
        if (this.content.equals("") || this.content == null) {
            this.signContent.setText("");
        } else {
            this.signContent.setText(this.content);
        }
    }

    public void setSignContent(String str) {
        this.content = str;
        init();
    }

    public void setSignType(boolean z) {
        this.signType = z;
        init();
    }
}
