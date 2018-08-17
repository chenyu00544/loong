package com.ecjia.hamster.activity.goodsdetail.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import anet.channel.strategy.dispatch.c;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecmoban.android.missmall.ECJiaApplication;

public class ECJiaGoodsDetailBaseFragment extends Fragment {
    public ECJiaApplication a;
    public ECJiaGoodsDetailActivity b;
    public Resources c;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b = (ECJiaGoodsDetailActivity) getActivity();
        this.a = (ECJiaApplication) this.b.getApplication();
        this.c = getResources();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onDetach() {
        super.onDetach();
    }

    public int a() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", c.ANDROID);
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
