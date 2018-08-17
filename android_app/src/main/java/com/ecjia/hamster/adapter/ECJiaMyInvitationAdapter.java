package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ecjia.component.view.clipviewpager.ECJiaRecyclingPagerAdapter;
import com.ecjia.hamster.model.ECJia_INVITE_REWARD;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaMyInvitationAdapter extends ECJiaRecyclingPagerAdapter {
    public ArrayList<ECJia_INVITE_REWARD> a;
    private Context b;

    class a {
        TextView a;
        final /* synthetic */ ECJiaMyInvitationAdapter b;

        a(ECJiaMyInvitationAdapter eCJiaMyInvitationAdapter) {
            this.b = eCJiaMyInvitationAdapter;
        }
    }

    public ECJiaMyInvitationAdapter(Context context, ArrayList<ECJia_INVITE_REWARD> arrayList) {
        this.a = arrayList;
        this.b = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.my_invitation_item, null);
            aVar.a = (TextView) view.findViewById(R.id.tv_reward_time);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        CharSequence label_invite_data = ((ECJia_INVITE_REWARD) this.a.get(i)).getLabel_invite_data();
        if (label_invite_data.contains("年")) {
            label_invite_data = label_invite_data.replace("年", "年\n");
        }
        aVar.a.setText(label_invite_data);
        return view;
    }
}
