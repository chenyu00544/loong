package com.umeng.message.inapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.j;
import com.umeng.message.view.UCloseView;
import org.json.JSONObject;

public final class UmengCardMessage extends DialogFragment {
    private static final String a = UmengCardMessage.class.getName();
    private static final int l = 12;
    private Activity b;
    private UInAppMessage c;
    private String d;
    private Bitmap e;
    private FrameLayout f;
    private int g;
    private int h;
    private c i;
    private IUmengInAppMsgCloseCallback j;
    private boolean k = false;

    class UmengCardMessage_1 implements OnClickListener {
        final /* synthetic */ UmengCardMessage a;

        UmengCardMessage_1(UmengCardMessage umengCardMessage) {
            this.a = umengCardMessage;
        }

        public void onClick(View view) {
            if (!TextUtils.equals(UInAppMessage.NONE, this.a.c.action_type)) {
                this.a.k = true;
                this.a.i.handleInAppMessage(this.a.b, this.a.c, false);
                this.a.dismiss();
            }
        }
    }

    class UmengCardMessage_2 implements OnClickListener {
        final /* synthetic */ UmengCardMessage a;

        UmengCardMessage_2(UmengCardMessage umengCardMessage) {
            this.a = umengCardMessage;
        }

        public void onClick(View view) {
            this.a.dismiss();
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(2, 16973830);
        setRetainInstance(true);
        try {
            this.b = getActivity();
            Bundle arguments = getArguments();
            this.c = new UInAppMessage(new JSONObject(arguments.getString("msg")));
            this.d = arguments.getString("label");
            byte[] byteArray = arguments.getByteArray("bitmapByte");
            if (byteArray != null) {
                this.e = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            this.i = new c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            Rect rect = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            this.h = rect.height() - j.a(this.b, 65.0f);
            this.g = (int) (((double) this.h) * 1.2d);
        }
        return a();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.requestWindowFeature(1);
        return onCreateDialog;
    }

    private View a() {
        LayoutParams layoutParams;
        View relativeLayout = new RelativeLayout(this.b);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(Color.parseColor("#33000000"));
        if (getResources().getConfiguration().orientation == 1) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        } else if (this.c.msg_type == 2) {
            layoutParams = new RelativeLayout.LayoutParams(this.g, this.h);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        }
        int a = j.a(this.b, 30.0f);
        int a2 = j.a(this.b, 15.0f);
        layoutParams.setMargins(a, a2, a, a2);
        layoutParams.addRule(13);
        this.f = new FrameLayout(this.b);
        this.f.setLayoutParams(layoutParams);
        layoutParams = new FrameLayout.LayoutParams(-1, -1);
        a = j.a(this.b, 12.0f);
        layoutParams.setMargins(a, a, a, a);
        View imageView = new ImageView(this.b);
        imageView.setLayoutParams(layoutParams);
        imageView.setAdjustViewBounds(true);
        imageView.setId(h.c());
        imageView.setImageBitmap(this.e);
        this.f.addView(imageView);
        int a3 = j.a(this.b, 24.0f);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(a3, a3, 5);
        View uCloseView = new UCloseView(this.b);
        uCloseView.setLayoutParams(layoutParams2);
        this.f.addView(uCloseView);
        relativeLayout.addView(this.f);
        imageView.setOnClickListener(new UmengCardMessage_1(this));
        uCloseView.setOnClickListener(new UmengCardMessage_2(this));
        return relativeLayout;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f != null) {
            LayoutParams layoutParams;
            if (configuration.orientation == 1) {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            }
            int a = j.a(this.b, 30.0f);
            int a2 = j.a(this.b, 15.0f);
            layoutParams.setMargins(a, a2, a, a2);
            layoutParams.addRule(13);
            this.f.setLayoutParams(layoutParams);
        }
    }

    void a(IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        this.j = iUmengInAppMsgCloseCallback;
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.k) {
            InAppMessageManager.getInstance(this.b).a(this.c.msg_id, this.c.msg_type, 1, 1, 0, 0, 0, 0);
        } else {
            InAppMessageManager.getInstance(this.b).a(this.c.msg_id, this.c.msg_type, 1, 0, 0, 0, 1, 0);
        }
        this.k = false;
        if (this.j != null) {
            this.j.onColse();
        }
    }
}
