package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.hamster.adapter.az;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaRedPacketsActivity extends a {
    ECJiaXListView a;
    az b;
    ArrayList<ECJia_BONUS> c = new ArrayList();
    ECJia_BONUS d = null;
    private ImageView e;
    private TextView k;

    class ECJiaRedPacketsActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaRedPacketsActivity a;

        ECJiaRedPacketsActivity_1(ECJiaRedPacketsActivity eCJiaRedPacketsActivity) {
            this.a = eCJiaRedPacketsActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaRedPacketsActivity_2 implements OnItemClickListener {
        final /* synthetic */ ECJiaRedPacketsActivity a;

        ECJiaRedPacketsActivity_2(ECJiaRedPacketsActivity eCJiaRedPacketsActivity) {
            this.a = eCJiaRedPacketsActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i > 0) {
                this.a.d = (ECJia_BONUS) this.a.c.get(i - 1);
                this.a.b.a(i);
                this.a.b.notifyDataSetInvalidated();
            }
        }
    }

    class ECJiaRedPacketsActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaRedPacketsActivity a;

        ECJiaRedPacketsActivity_3(ECJiaRedPacketsActivity eCJiaRedPacketsActivity) {
            this.a = eCJiaRedPacketsActivity;
        }

        public void onClick(View view) {
            try {
                Intent intent = new Intent();
                if (this.a.d != null) {
                    intent.putExtra("bonus", this.a.d.toJson().toString());
                }
                this.a.setResult(-1, intent);
                this.a.finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.red_paper);
        PushAgent.getInstance(this).onAppStart();
        this.e = (ImageView) findViewById(R.id.red_papaer_back);
        this.e.setOnClickListener(new ECJiaRedPacketsActivity_1(this));
        this.a = (ECJiaXListView) findViewById(R.id.red_packet_list);
        this.a.setPullLoadEnable(false);
        this.a.setPullRefreshEnable(false);
        this.a.setOnItemClickListener(new ECJiaRedPacketsActivity_2(this));
        this.b = new az(this, this.c);
        this.a.setAdapter(this.b);
        this.k = (TextView) findViewById(R.id.red_papaer_submit);
        this.k.setOnClickListener(new ECJiaRedPacketsActivity_3(this));
        String stringExtra = getIntent().getStringExtra("payment");
        if (stringExtra != null) {
            try {
                JSONArray optJSONArray = new JSONObject(stringExtra).optJSONArray("bonus");
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    this.a.setVisibility(8);
                    return;
                }
                this.c.clear();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.c.add(ECJia_BONUS.fromJson(optJSONArray.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
