package com.vcvb.chenyu.shop.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.base.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.search.KeyWords;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private EditText search;
    private TextView searchBt;
    private List<KeyWords> searchs = new ArrayList<>();
    private List<KeyWords> keys = new ArrayList<>();
    private List<KeyWords> cates = new ArrayList<>();
    private FlowLayout flowLayout1;
    private FlowLayout flowLayout2;
    private FlowLayout flowLayout3;
    private ImageView trashV;
    private int isFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        getData(true);
        initListener();
        isFrom = getIntent().getIntExtra("isfrom", 0);
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    overridePendingTransition(0, 0);
                }
            });
        }
        search = findViewById(R.id.editText13);
        search.setFocusable(true);
        search.setFocusableInTouchMode(true);
        search.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        searchBt = findViewById(R.id.textView153);
        searchBt.setOnClickListener(listener);
    }

    @Override
    public void initView() {
        super.initView();
        flowLayout1 = findViewById(R.id.wrap_now);
        flowLayout2 = findViewById(R.id.wrap_hot);
        flowLayout3 = findViewById(R.id.wrap_cate);
        flowLayout1.setChildSpacing(8);
        flowLayout1.setRowSpacing(8);
        flowLayout1.setChildSpacingForLastRow(8);
        flowLayout2.setChildSpacing(8);
        flowLayout2.setRowSpacing(8);
        flowLayout2.setChildSpacingForLastRow(8);
        flowLayout3.setChildSpacing(8);
        flowLayout3.setRowSpacing(8);
        flowLayout3.setChildSpacingForLastRow(8);

        trashV = findViewById(R.id.imageView81);
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);
        for (int i = 0; i < 8; i++) {
            KeyWords bean = new KeyWords();
            bean.setTitle("常用" + i);
            searchs.add(bean);
            TextView textView = new TextView(context);
            textView.setText(bean.getTitle());
            textView.setId(IdsUtils.generateViewId());
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8),
                    ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8));
            textView.setBackgroundResource(R.drawable.shape_6_gray_d);
            textView.setTag(bean.getTitle());
            textView.setOnClickListener(listener);
            flowLayout1.addView(textView);
        }
        for (int i = 0; i < 8; i++) {
            KeyWords bean = new KeyWords();
            bean.setTitle("分类" + i);
            keys.add(bean);
            TextView textView = new TextView(context);
            textView.setText(bean.getTitle());
            textView.setId(IdsUtils.generateViewId());
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8),
                    ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8));
            textView.setBackgroundResource(R.drawable.shape_6_gray_d);
            textView.setTag(bean.getTitle());
            textView.setOnClickListener(listener);
            flowLayout2.addView(textView);
        }
        for (int i = 0; i < 8; i++) {
            KeyWords bean = new KeyWords();
            bean.setTitle("常用分类");
            bean.setCateId(i);
            cates.add(bean);
            TextView textView = new TextView(context);
            textView.setText(bean.getTitle());
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ToolUtils.dip2px
                    (context, 50), ToolUtils.dip2px(context, 50));
            textView.setLayoutParams(lp);
            textView.setId(IdsUtils.generateViewId());
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(Gravity.CENTER);
            textView.setSingleLine(false);
            textView.setLines(2);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 10), ToolUtils.dip2px(context, 10),
                    ToolUtils.dip2px(context, 10), ToolUtils.dip2px(context, 10));
            textView.setBackgroundResource(R.drawable.shape_60_gray_d);
            textView.setOnClickListener(listener);
            textView.setTag(bean.getCateId());
            flowLayout3.addView(textView);
        }
    }

    @Override
    public void initListener() {
        super.initListener();
        trashV.setOnClickListener(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.IsFrom.FROM_HOME:
                    break;
            }
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textView153:
                    //输入搜索
                    if (!TextUtils.isEmpty(search.getText())) {
                        Intent intent;
                        if (isFrom == ConstantManager.IsFrom.FROM_HOME) {
                            intent = new Intent(SearchActivity.this, SearchInfoActivity.class);
                            intent.putExtra("keywords", String.valueOf(search.getText()));
                            startActivity(intent);
                            finish();
                        } else if (isFrom == ConstantManager.IsFrom.FROM_SEARCHINFO) {
                            intent = new Intent();
                            intent.putExtra("keywords", String.valueOf(search.getText()));
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        overridePendingTransition(0, 0);
                    }
                    break;
                case R.id.imageView81:
                    flowLayout1.removeAllViews();
                    TextView textView = new TextView(context);
                    textView.setText(R.string.now_search_history);
                    textView.setTextColor(context.getResources().getColor(R.color.black));
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLines(1);
                    textView.setMaxEms(8);
                    textView.setPadding(ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context,
                            8), ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8));
                    textView.setBackgroundResource(R.drawable.shape_6_gray_d);
                    flowLayout1.addView(textView);
                    break;
                default:
                    //常用设置
                    if (findViewById(view.getId()) instanceof TextView) {
                        if (findViewById(view.getId()).getTag() != null) {
                            Intent intent;
                            if (isFrom == ConstantManager.IsFrom.FROM_HOME) {
                                Object obj = findViewById(view.getId()).getTag();
                                intent = new Intent(SearchActivity.this, SearchInfoActivity.class);
                                if (obj instanceof Integer) {
                                    intent.putExtra("cate", (Integer) obj);
                                } else {
                                    intent.putExtra("keywords", (String) obj);
                                }
                                startActivity(intent);
                                finish();
                            } else if (isFrom == ConstantManager.IsFrom.FROM_SEARCHINFO) {
                                intent = new Intent();
                                Object obj = findViewById(view.getId()).getTag();
                                if (obj instanceof Integer) {
                                    intent.putExtra("cate", (Integer) obj);
                                } else {
                                    intent.putExtra("keywords", (String) obj);
                                }
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                            overridePendingTransition(0, 0);
                        }
                    }
                    break;
            }
        }
    };
}
