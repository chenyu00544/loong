package com.vcvb.chenyu.shop.mycenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.javaBean.address.City;
import com.vcvb.chenyu.shop.javaBean.address.Country;
import com.vcvb.chenyu.shop.javaBean.address.District;
import com.vcvb.chenyu.shop.javaBean.address.Province;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.JsonUtils;
import com.vcvb.chenyu.shop.tools.TimeUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import xiaofei.library.datastorage.DataStorageFactory;
import xiaofei.library.datastorage.IDataStorage;

public class ModifyAddressActivity extends BaseActivity {

    String token;
    Country country;
    public LoadingDialog loadingDialog;
    private TextView area;
    private EditText userName;
    private EditText phone;
    private EditText address_info;
    private OptionsPickerView optionsPickerView;
    IDataStorage dataStorage;
    private HashMap<String, String> mp = new HashMap<>();
    Integer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        context = this;
        changeStatusBarTextColor(true);
        token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
        dataStorage = DataStorageFactory.getInstance(context, DataStorageFactory.TYPE_DATABASE);
        Intent intent = getIntent();
        intent.getIntExtra("type", 1);
        setNavBack();
        initView();
        initListener();
        getData(false);
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
                saveAddress();
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        loadingDialog = new LoadingDialog(context, R.style.TranslucentDialog);
        area = findViewById(R.id.textView117);
        userName = findViewById(R.id.editText9);
        phone = findViewById(R.id.editText10);
        address_info = findViewById(R.id.editText11);
        userName.setFocusable(true);
        userName.setFocusableInTouchMode(true);
        userName.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        //三级联动回调
        optionsPickerView = new OptionsPickerBuilder(ModifyAddressActivity.this, pickerListener)
                .setOptionsSelectChangeListener(pickerSelectChangeListener).setTitleText("城市选择")//标题
                .setSubCalSize(16)//确定和取消按钮的文字大小
                .setTitleSize(18)//标题文字大小
                .setContentTextSize(18)//滚轮文字大小
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .setCyclic(true, true, true)//循环与否
                .build();
    }

    @Override
    public void initListener() {
        super.initListener();
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsPickerView.show();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context
                        .INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(area.getWindowToken(), 0);
            }
        });
        ImageView iv1 = findViewById(R.id.imageView24);
        ImageView iv2 = findViewById(R.id.imageView52);
        ImageView iv3 = findViewById(R.id.imageView53);
        ImageView iv4 = findViewById(R.id.imageView57);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.getText().clear();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone.getText().clear();
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                area.setText("");
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address_info.getText().clear();
            }
        });
    }

    @Override
    public void getData(boolean b) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Country> countries = dataStorage.loadAll(Country.class);
                if (countries == null || countries.size() == 0) {
                    HttpUtils.getInstance().post(ConstantManager.Url.ALLREGION, null, new
                            HttpUtils.NetCall() {
                        @Override
                        public void success(Call call, JSONObject json) throws IOException {
                            bindData(json);
                        }

                        @Override
                        public void failed(Call call, IOException e) {

                        }
                    });
                } else {
                    initRegionsData(countries);
                }
            }
        });
        thread.start();
    }

    public void saveAddress() {
        mp.put("token", token);
        mp.put("consignee", userName.getText().toString());
        mp.put("phone", phone.getText().toString());
        mp.put("address_info", address_info.getText().toString());
        boolean bool = true;
        if (token == null || token.equals("")) {
            bool = false;
            ToastUtils.showShortToast(context, "未登陆");
        }
        if (bool) {
            if (mp.get("consignee") == null || mp.get("consignee").equals("")) {
                bool = false;
                ToastUtils.showShortToast(context, "请填写收件人信息");
            }
        }

        if (bool) {
            if (mp.get("phone") == null || mp.get("phone").equals("")) {
                bool = false;
                ToastUtils.showShortToast(context, "请填写收件人手机号码");
            }
        }

        if (bool) {
            if (mp.get("country") == null || mp.get("country").equals("") || mp.get("province")
                    == null || mp.get("province").equals("") || mp.get("city") == null || mp.get
                    ("city").equals("") || mp.get("district") == null || mp.get("district")
                    .equals("")) {
                bool = false;
                ToastUtils.showShortToast(context, "请选择所在地区");
            }
        }

        if (bool) {
            if (mp.get("address_info") == null || mp.get("address_info").equals("")) {
                bool = false;
                ToastUtils.showShortToast(context, "请填写收件人街道、小区等详细信息");
            }
        }

        if (bool) {
            loadingDialog.show();
            HttpUtils.getInstance().post(ConstantManager.Url.ADDADDRESS, mp, new HttpUtils
                    .NetCall() {
                @Override
                public void success(Call call, final JSONObject json) throws IOException {
                    try {
                        System.out.println(json);
                        Integer code = json.getInt("code");
                        final String msg = json.getString("msg");
                        if (code == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    count = 3;
                                    loadingDialog.dismiss();
                                    ToastUtils.showShortToast(context, "保存成功");
                                    TimeUtils.startCountdown(new TimeUtils.CallBack() {
                                        @Override
                                        public void time() {
                                            count --;
                                            if(count == 0){
                                                finish();
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadingDialog.dismiss();
                                    ToastUtils.showShortToast(context, msg);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            ToastUtils.showShortToast(context, "网络错误,请稍后重试");
                        }
                    });
                }
            });
        }
    }

    //设置地区三级联动数据
    public void initRegionsData(List<Country> countries) {
        for (int h = 0; h < countries.size(); h++) {
            Country country = countries.get(h);
            if (country.getRegion_name().equals("中国")) {
                this.country = country;
                ArrayList<String> arr1 = new ArrayList<>();
                ArrayList<ArrayList<String>> arr2 = new ArrayList<>();
                ArrayList<ArrayList<ArrayList<String>>> arr3 = new ArrayList<>();
                for (int i = 0; i < country.getProvinces().size(); i++) {
                    Province province = country.getProvinces().get(i);
                    arr1.add(province.getRegion_name());
                    ArrayList<String> cities = new ArrayList<>();
                    ArrayList<ArrayList<String>> area = new ArrayList<>();
                    for (int j = 0; j < province.getCities().size(); j++) {
                        City city = province.getCities().get(j);
                        cities.add(city.getRegion_name());
                        ArrayList<String> districts = new ArrayList<>();
                        for (int k = 0; k < city.getDistricts().size(); k++) {
                            District district = city.getDistricts().get(k);
                            districts.add(district.getRegion_name());
                        }
                        area.add(districts);
                    }
                    arr2.add(cities);
                    arr3.add(area);
                }
                optionsPickerView.setPicker(arr1, arr2, arr3);
            }
        }
    }

    public void bindData(JSONObject json) {
        if (json != null) {
            try {
                JSONArray countryJSONArray = json.getJSONArray("data");
                List<Country> countries = new ArrayList<>();
                for (int i = 0; i < countryJSONArray.length(); i++) {
                    JSONObject object = countryJSONArray.getJSONObject(i);
                    Country country = JsonUtils.fromJsonObject(object, Country.class);
                    country.setData(object.getJSONArray("province"));
                    countries.add(country);
                }
                //持久化
                dataStorage.storeOrUpdate(countries);
                initRegionsData(countries);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    //地区确定选择监听接口
    OnOptionsSelectListener pickerListener = new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            Province province = country.getProvinces().get(options1);
            City city = province.getCities().get(options2);
            District district = city.getDistricts().get(options3);
            String str = province.getRegion_name() + "-" + city.getRegion_name() + "-" + district
                    .getRegion_name();
            mp.put("country", country.getRegion_id() + "");
            mp.put("province", province.getRegion_id() + "");
            mp.put("city", city.getRegion_id() + "");
            mp.put("district", district.getRegion_id() + "");
            area.setText(str);
            area.setTextColor(context.getResources().getColor(R.color.black));
        }
    };
    //地区选择监听接口
    OnOptionsSelectChangeListener pickerSelectChangeListener = new OnOptionsSelectChangeListener() {
        @Override
        public void onOptionsSelectChanged(int options1, int options2, int options3) {
            Province province = country.getProvinces().get(options1);
            City city = province.getCities().get(options2);
            District district = city.getDistricts().get(options3);
            String str = province.getRegion_name() + "-" + city.getRegion_name() + "-" + district
                    .getRegion_name();
            mp.put("country", country.getRegion_id() + "");
            mp.put("province", province.getRegion_id() + "");
            mp.put("city", city.getRegion_id() + "");
            mp.put("district", district.getRegion_id() + "");
            area.setText(str);
            area.setTextColor(context.getResources().getColor(R.color.black));
        }
    };
}
