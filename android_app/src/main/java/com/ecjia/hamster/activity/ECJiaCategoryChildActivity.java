package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ecjia.hamster.adapter.c;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecmoban.android.missmall.R;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.umeng.message.PushAgent;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaCategoryChildActivity extends a implements OnClickListener {
    c a;
    ECJia_CATEGORY b;
    private ImageView c;
    private EditText d;
    private ImageButton e;
    private TextView k;
    private LinearLayout l;
    private ListView m;

    class ECJiaCategoryChildActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaCategoryChildActivity a;

        ECJiaCategoryChildActivity_1(ECJiaCategoryChildActivity eCJiaCategoryChildActivity) {
            this.a = eCJiaCategoryChildActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaCategoryChildActivity_2 implements OnEditorActionListener {
        final /* synthetic */ ECJiaCategoryChildActivity a;

        ECJiaCategoryChildActivity_2(ECJiaCategoryChildActivity eCJiaCategoryChildActivity) {
            this.a = eCJiaCategoryChildActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 3) {
                Intent intent = new Intent(this.a, ECJiaGoodsListActivity.class);
                ECJia_FILTER eCJia_FILTER = new ECJia_FILTER();
                eCJia_FILTER.setKeywords(this.a.d.getText().toString().toString());
                try {
                    intent.putExtra("filter", eCJia_FILTER.toJson().toString());
                } catch (JSONException e) {
                }
                this.a.startActivity(intent);
            }
            return false;
        }
    }

    class ECJiaCategoryChildActivity_3 implements OnItemClickListener {
        final /* synthetic */ ECJiaCategoryChildActivity a;

        ECJiaCategoryChildActivity_3(ECJiaCategoryChildActivity eCJiaCategoryChildActivity) {
            this.a = eCJiaCategoryChildActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i < this.a.b.getChildren().size()) {
                ECJia_CATEGORY eCJia_CATEGORY = (ECJia_CATEGORY) this.a.b.getChildren().get(i);
                try {
                    Intent intent = new Intent(this.a, ECJiaGoodsListActivity.class);
                    ECJia_FILTER eCJia_FILTER = new ECJia_FILTER();
                    eCJia_FILTER.setCategory_id(String.valueOf(eCJia_CATEGORY.getId()));
                    intent.putExtra("filter", eCJia_FILTER.toJson().toString());
                    intent.putExtra("search_content", this.a.b.getName());
                    this.a.startActivity(intent);
                    this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                } catch (JSONException e) {
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.search_child);
        PushAgent.getInstance(this).onAppStart();
        this.d = (EditText) findViewById(R.id.search_input);
        this.c = (ImageView) findViewById(R.id.search_search);
        this.k = (TextView) findViewById(R.id.child_title);
        this.l = (LinearLayout) findViewById(R.id.titleshow);
        this.l.setVisibility(0);
        this.d.setVisibility(8);
        this.c.setVisibility(8);
        this.e = (ImageButton) findViewById(R.id.back_button);
        this.e.setVisibility(0);
        this.e.setOnClickListener(new ECJiaCategoryChildActivity_1(this));
        this.c.setOnClickListener(this);
        this.d.setOnEditorActionListener(new ECJiaCategoryChildActivity_2(this));
        this.m = (ListView) findViewById(R.id.child_list);
        try {
            this.b = ECJia_CATEGORY.fromJson(new JSONObject(getIntent().getStringExtra(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY)));
            this.k.setText(this.b.getName());
            this.a = new c(this, this.b.getChildren());
            this.m.setAdapter(this.a);
            this.m.setOnItemClickListener(new ECJiaCategoryChildActivity_3(this));
        } catch (JSONException e) {
        }
    }

    public void onClick(View view) {
        view.getId();
    }
}
