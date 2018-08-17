package com.ecjia.hamster.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.ecjia.hamster.activity.ECJiaMainActivity;
import com.ecmoban.android.missmall.ECJiaApplication;
import com.ecmoban.android.missmall.R;

public class ECJiaBaseFragment extends Fragment {
    public ECJiaApplication a;
    public ECJiaMainActivity b;
    public Resources c;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b = (ECJiaMainActivity) getActivity();
        this.a = (ECJiaApplication) this.b.getApplication();
        this.c = getResources();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
