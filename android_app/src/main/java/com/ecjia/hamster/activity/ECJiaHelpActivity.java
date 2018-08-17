package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.hamster.adapter.u;
import com.ecjia.hamster.model.as;
import com.ecjia.hamster.model.d;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaHelpActivity extends a {
    private TextView a;
    private ImageView b;
    private ListView c;
    private u d;
    private List<as> e = new ArrayList();
    private List<d> k = new ArrayList();
    private int l;

    class ECJiaHelpActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaHelpActivity a;

        ECJiaHelpActivity_1(ECJiaHelpActivity eCJiaHelpActivity) {
            this.a = eCJiaHelpActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaHelpActivity_2 implements OnItemClickListener {
        final /* synthetic */ ECJiaHelpActivity a;

        ECJiaHelpActivity_2(ECJiaHelpActivity eCJiaHelpActivity) {
            this.a = eCJiaHelpActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Intent intent = new Intent(this.a, ECJiaHelpWebActivity.class);
            intent.putExtra("id", ((d) this.a.k.get(i)).d());
            intent.putExtra("title", ((d) this.a.k.get(i)).c());
            this.a.startActivity(intent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.help);
        PushAgent.getInstance(this).onAppStart();
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.b.setOnClickListener(new ECJiaHelpActivity_1(this));
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("data");
        this.l = intent.getIntExtra("position", 0);
        if (stringExtra != null && stringExtra.length() > 0) {
            try {
                JSONArray optJSONArray = new JSONObject(stringExtra).optJSONArray("data");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    this.e.clear();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        this.e.add(as.a(optJSONArray.getJSONObject(i)));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.k = ((as) this.e.get(this.l)).a;
        this.a.setText(((as) this.e.get(this.l)).a());
        this.c = (ListView) findViewById(R.id.help_list);
        this.d = new u(this, this.k);
        this.c.setAdapter(this.d);
        if (this.k.size() == 0) {
            this.c.setVisibility(8);
        }
        this.c.setOnItemClickListener(new ECJiaHelpActivity_2(this));
    }
}
