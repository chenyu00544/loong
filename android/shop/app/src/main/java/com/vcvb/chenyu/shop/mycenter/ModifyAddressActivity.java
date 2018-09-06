package com.vcvb.chenyu.shop.mycenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;

import java.util.ArrayList;

public class ModifyAddressActivity extends BaseActivity {
    Context context;
    public LoadingDialog loadingDialog;
    private TextView area;
    private OptionsPickerView optionsPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        context = this;
        changeStatusBarTextColor(true);
        Intent intent = getIntent();
        intent.getIntExtra("type", 1);
        setNavBack();
        initView();
        initListener();

    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("result", "My name is cyc");
                    ModifyAddressActivity.this.setResult(RESULT_OK, intent);
                    ModifyAddressActivity.this.finish();
                }
            });
        }

        TextView title = (TextView) findViewById(R.id.textView123);
        title.setText(R.string.add_address);
        TextView save = (TextView) findViewById(R.id.textView122);
        save.setText(R.string.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        loadingDialog = new LoadingDialog(context, R.style.TranslucentDialog);
        area = (TextView) findViewById(R.id.textView117);
        optionsPickerView = new OptionsPickerBuilder(ModifyAddressActivity.this, new
                OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {

                    }
                }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {

            }
        }).setTitleText("城市选择")//标题
                .setSubCalSize(16)//确定和取消文字大小
                .setTitleSize(18)//标题文字大小
                .setContentTextSize(20)//滚轮文字大小
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<ArrayList<String>> arr2 = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> arr3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr1.add("省省省" + i);
            ArrayList<String> city = new ArrayList<>();
            ArrayList<ArrayList<String>> area = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                city.add("市市市" + i + j);
                ArrayList<String> area1 = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    area1.add("区区区" + i + j + k);
                }
                area.add(area1);
            }
            arr2.add(city);
            arr3.add(area);
        }
        optionsPickerView.setPicker(arr1, arr2, arr3);
    }

    @Override
    public void initListener() {
        super.initListener();
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsPickerView.show();
            }
        });

    }
}
