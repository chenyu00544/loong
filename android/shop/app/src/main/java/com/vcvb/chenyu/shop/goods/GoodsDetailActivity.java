package com.vcvb.chenyu.shop.goods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.MainActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsAttrItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsBannerItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsBrandItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsDescItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsEvaluateItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsExplainItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsFaatItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsNameItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsPriceItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsSalesPromotionItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsShipFreeItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsShipItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsSpecificationsItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.GoodsAddressDialog;
import com.vcvb.chenyu.shop.dialog.GoodsAttrDialog;
import com.vcvb.chenyu.shop.dialog.GoodsExplainDialog;
import com.vcvb.chenyu.shop.dialog.GoodsFaatDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog2;
import com.vcvb.chenyu.shop.dialog.LoginDialog;
import com.vcvb.chenyu.shop.evaluate.EvaluateListActivity;
import com.vcvb.chenyu.shop.evaluate.QuestionsListActivity;
import com.vcvb.chenyu.shop.faat.BrandListActivity;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttr;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.login.RegisterActivity;
import com.vcvb.chenyu.shop.mycenter.AddressActivity;
import com.vcvb.chenyu.shop.mycenter.ModifyAddressActivity;
import com.vcvb.chenyu.shop.mycenter.userinfo.UserRealNameActivity;
import com.vcvb.chenyu.shop.order.OrderDetailsActivity;
import com.vcvb.chenyu.shop.overrideView.ShopGridLayoutManager;
import com.vcvb.chenyu.shop.overrideView.ShopRecyclerView;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.TimeUtils;
import com.vcvb.chenyu.shop.tools.ToastUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class GoodsDetailActivity extends GoodsActivity {
    private int goods_id;
    private JSONObject goodsJson;

    int pos = 0;
    private View child1;
    private View line;
    private TextView cartNum;

    private ShopRecyclerView goodsDetail;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private GoodsDetail goodsDetails = new GoodsDetail();
    private ShopGridLayoutManager gridLayoutManager;

    private GoodsFaatDialog goodsFaatDialog;
    private GoodsAttrDialog goodsAttrDialog;
    private GoodsAddressDialog goodsAddressDialog;
    private GoodsExplainDialog goodsExplainDialog;

    private ArrayList<GoodsAttr> selectAttrs = new ArrayList<>();

    public LoginDialog loginDialog;

    public GoodsDetailActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        context = this;
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        changeStatusBarTextColor(true);
        goods_id = getIntent().getIntExtra("id", 0);
        setNavBack();
        initView();
        initListener();
        getData(true);
    }

    //在这个方法内才能获取正确的距离宽高参数
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        eY = findViewById(R.id.goods_evaluate).getTop();
//        eI = findViewById(R.id.goods_image_text_info).getTop();
    }

    @Override
    public void getData(final boolean b) {
        if (b) {
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", goods_id + "");
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.GOODSDETAIL, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    goodsJson = json;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                            bindData();
                        }
                    });
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        Toast.makeText(context, "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        goodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsView.setTextSize(ts_22);
                goodsView.setTextColor(Color.parseColor("#000000"));
                goodsEvaluate.setTextSize(ts_18);
                goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                goodsInfo.setTextSize(ts_18);
                goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                pos = 0;
                goodsDetail.scrollToPosition(0);
            }
        });
        goodsEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsView.setTextSize(ts_18);
                goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                goodsInfo.setTextSize(ts_18);
                goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                goodsEvaluate.setTextSize(ts_22);
                goodsEvaluate.setTextColor(Color.parseColor("#000000"));
                pos = 1180;
                goodsDetail.scrollToPosition(8);
            }
        });
        goodsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsInfo.setTextSize(ts_22);
                goodsInfo.setTextColor(Color.parseColor("#000000"));
                goodsView.setTextSize(ts_18);
                goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                goodsEvaluate.setTextSize(ts_18);
                goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                pos = 2485;
                goodsDetail.scrollToPosition(10);
            }
        });


        collectionView = findViewById(R.id.collection);
        collectionView.setOnClickListener(listener);

        final PopWin popWindow = new PopWin(GoodsDetailActivity.this, ToolUtils.dip2px(this, 156)
                , ToolUtils.dip2px(this, 148));
        final ImageView iv2 = findViewById(R.id.more);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.showAsDropDown(iv2);
            }
        });
        popWindow.setClickListener(new PopWin.OnItemClickListener() {
            @Override
            public void onClicked(View v) {
                switch (v.getId()) {
                    case ConstantManager.Menu.MESSAGE:
                        break;
                    case ConstantManager.Menu.HOME:
                        popWindow.dismiss();
                        Intent intent = new Intent(GoodsDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case ConstantManager.Menu.CART:
                        break;
                }
            }
        });
    }

    public void initListener() {
        final LinearLayout nav_wrap = nav_bar;
        final LinearLayout title_wrap = nav_bar.findViewById(R.id.title_wrap);
        final LinearLayout.LayoutParams layoutParams_d = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, 0);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //滑动监听
        goodsDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int alpha = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0: //不滚动
                        goodsDetails.setIsScroll(1);
                        mAdapter.notifyDataSetChanged();
                        break;
                    case 1: // 按着手指滚动
                        goodsDetails.setIsScroll(0);
                        break;
                    case 2: // 不按着手指滚动
                        goodsDetails.setIsScroll(0);
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                child1 = gridLayoutManager.getChildAt(0);
                if (goodsDetail.getChildAdapterPosition(child1) == 0) {
                    goodsView.setTextSize(ts_22);
                    goodsView.setTextColor(Color.parseColor("#000000"));
                    goodsEvaluate.setTextSize(ts_18);
                    goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsInfo.setTextSize(ts_18);
                    goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                } else if (goodsDetail.getChildAdapterPosition(child1) == 8) {
                    goodsView.setTextSize(ts_18);
                    goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsInfo.setTextSize(ts_18);
                    goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsEvaluate.setTextSize(ts_22);
                    goodsEvaluate.setTextColor(Color.parseColor("#000000"));
                } else if (goodsDetail.getChildAdapterPosition(child1) == 10) {
                    goodsInfo.setTextSize(ts_22);
                    goodsInfo.setTextColor(Color.parseColor("#000000"));
                    goodsView.setTextSize(ts_18);
                    goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsEvaluate.setTextSize(ts_18);
                    goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                }
                pos += dy;
                if (pos <= 0) {
                    pos = 0;
                }
                if (pos < 255) {
                    alpha = pos;
                    title_wrap.setAlpha(alpha);
                    if (pos < 10) {
                        title_wrap.setLayoutParams(layoutParams_d);
                    } else {
                        title_wrap.setLayoutParams(layoutParams);
                    }
                    line.setAlpha(alpha);
                    nav_wrap.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                } else {
                    if (alpha < 255) {
                        alpha = 255;
                        title_wrap.setAlpha(alpha);
                        line.setAlpha(alpha);
                        nav_wrap.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                    }
                }
            }
        });
    }

    public void initView() {
        line = findViewById(R.id.view68);
        TextView buy = findViewById(R.id.textView32);
        TextView addCart = findViewById(R.id.textView31);
        ImageView iv1 = findViewById(R.id.imageView11);
        TextView server = findViewById(R.id.textView26);

        ImageView iv2 = findViewById(R.id.imageView13);
        TextView cart = findViewById(R.id.textView27);
        iv2.setOnClickListener(listener);
        cart.setOnClickListener(listener);

        buy.setOnClickListener(listener);
        addCart.setOnClickListener(listener);


        goodsDetail = findViewById(R.id.goods_detail);
        goodsDetail.setFlingScale(0.3);
        goodsDetail.setNestedScrollingEnabled(false);
        gridLayoutManager = new ShopGridLayoutManager(this, 1);
        gridLayoutManager.setSpeedRatio(1);
        goodsDetail.setLayoutManager(gridLayoutManager);
        goodsDetail.setItemAnimator(new DefaultItemAnimator());
        goodsDetail.setAdapter(mAdapter);
        goodsDetail.setNestedScrollingEnabled(false);

        cartNum = findViewById(R.id.textView11);
    }

    public void bindData() {
        if (goodsJson != null) {
            try {
                goodsDetails.setData(goodsJson.getJSONObject("data"));
                mAdapter.addAll(getItems(goodsDetails));

                goodsAttrDialog = new GoodsAttrDialog(goodsDetails);
                goodsAttrDialog.setOnItemClickListener(attrDialogListener);

                if (goodsDetails.getCount_cart() > 0) {
                    cartNum.setAlpha(1);
                } else {
                    cartNum.setAlpha(0);
                }
                cartNum.setText(String.valueOf(goodsDetails.getCount_cart()));
                if (context != null) {
                    if (goodsDetails.getCollect() == 0) {
                        Glide.with(context).load(R.drawable.icon_love_black).into(collectionView);
                    } else {
                        Glide.with(context).load(R.drawable.icon_love_active).into(collectionView);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<Item> getItems(GoodsDetail goodsDetail) {
        List<Item> cells = new ArrayList<>();
        if (goodsDetails.getBanners() != null && goodsDetails.getBanners().size() > 0) {
            cells.add(new GoodsBannerItem(goodsDetails.getBanners(), context));
        }
        if (goodsDetails.getGoodsFaat() != null && (goodsDetails.getGoodsFaat().getEnd_time() -
                goodsDetails.getGoodsFaat().getCurrent_time()) > 0) {
            TimeUtils.startCountdown(new TimeUtils.CallBack() {
                @Override
                public void time() {
                    goodsDetails.getGoodsFaat().setCurrent_time(goodsDetails.getGoodsFaat()
                            .getCurrent_time() + 1);
                }
            });
            cells.add(new GoodsSalesPromotionItem(goodsDetails, context));
        }
        if (goodsDetails.getShop_price() != null) {
            cells.add(new GoodsPriceItem(goodsDetails, context));
        }
        if (goodsDetails.getGoods_name() != null && goodsDetails.getGoods_name() != "") {
            cells.add(new GoodsNameItem(goodsDetails, context));
        }
        if (goodsDetails.getIs_fullcut() == 1 || goodsDetails.getIs_volume() == 1) {
            goodsFaatDialog = new GoodsFaatDialog(goodsDetails);
            GoodsFaatItem goodsFaatItem = new GoodsFaatItem(goodsDetails, context);
            goodsFaatItem.setOnItemClickListener(faatListener);
            cells.add(goodsFaatItem);
        }
        if (goodsDetails.getMultiAttrs() != null && goodsDetails.getMultiAttrs().size() > 0) {
            GoodsAttrItem goodsAttrItem = new GoodsAttrItem(goodsDetails, context);
            goodsAttrItem.setOnItemClickListener(attrListener);
            cells.add(goodsAttrItem);
        }
        if (goodsDetails.getGoodsTransport() != null) {
            goodsAddressDialog = new GoodsAddressDialog(goodsDetails);
            goodsAddressDialog.setOnItemClickListener(addressDialogListener);
            GoodsShipItem goodsShipItem = new GoodsShipItem(goodsDetails, context);
            goodsShipItem.setOnItemClickListener(shipListener);
            cells.add(goodsShipItem);
        }
        if (goodsDetails.getGoodsTransport() != null) {
            GoodsShipFreeItem goodsShipFreeItem = new GoodsShipFreeItem(goodsDetails, context);
            cells.add(goodsShipFreeItem);
        }
        if (goodsDetails.getGoodsDescriptions() != null) {
            goodsExplainDialog = new GoodsExplainDialog(goodsDetails);
            GoodsExplainItem goodsExplainItem = new GoodsExplainItem(goodsDetails, context);
            goodsExplainItem.setOnItemClickListener(explainListener);
            cells.add(goodsExplainItem);
        }
        if (goodsDetails.getCommentLabels() != null) {
            GoodsEvaluateItem goodsEvaluateItem = new GoodsEvaluateItem(goodsDetails, context);
            goodsEvaluateItem.setOnItemClickListener(evaluateListener);
            cells.add(goodsEvaluateItem);
        }
        if (goodsDetails.getGoodsBrand() != null) {
            GoodsBrandItem goodsBrandItem = new GoodsBrandItem(goodsDetails, context);
            goodsBrandItem.setOnItemClickListener(brandListener);
            cells.add(goodsBrandItem);
        }
        if (goodsDetails.getSingleAttrs() != null) {
            cells.add(new GoodsSpecificationsItem(goodsDetails, context));
        }
        if (goodsDetails.getGoodsDescs() != null) {
            for (int i = 0; i < goodsDetails.getGoodsDescs().size(); i++) {
                cells.add(new GoodsDescItem(goodsDetails.getGoodsDescs().get(i), context));
            }
        }
        return cells;
    }

    //fixme 添加到购物车
    public void addCart(HashMap<String, Object> attr) {
        List<Integer> goods_attr_ids_bak = (List<Integer>) attr.get("goods_attr_ids");
        HashMap<String, String> mp = new HashMap<>();
        loadingDialog.show();
        mp.put("goods_id", goods_id + "");
        mp.put("token", token);
        mp.put("device_id", (String) UserInfoUtils.getInstance(context).getUserInfo().get
                ("device_id"));
        mp.put("goods_attr_ids", StringUtils.join(goods_attr_ids_bak, ","));
        HttpUtils.getInstance().post(ConstantManager.Url.ADD_CART, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                if (json != null) {
                    try {
                        final Integer count_cart = json.getJSONObject("data").getInt("count_cart");
                        if (json.getInt("code") == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String str = "%s";
                                    cartNum.setAlpha(1);
                                    cartNum.setText(String.format(str, count_cart));
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showShortToast(context, "已添加");
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    //fixme 收藏
    public void collectionGoods() {
        if (token == null || token.equals("")) {
            showLoginDialog();
        } else {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("goods_id", goods_id + "");
            mp.put("token", token);
            HttpUtils.getInstance().post(ConstantManager.Url.ADD_COLLECT_GOODS, mp, new HttpUtils
                    .NetCall() {
                @Override
                public void success(Call call, JSONObject json) throws IOException {
                    try {
                        if (json.getInt("code") == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                            if (json.getInt("is_attention") == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Glide.with(context).load(R.drawable.icon_love_active)
                                                .into(collectionView);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Glide.with(context).load(R.drawable.icon_love_black).into
                                                (collectionView);
                                    }
                                });
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Call call, IOException e) {

                }
            });
        }
    }

    //fixme 显示登录框
    public void showLoginDialog() {
        loginDialog = new LoginDialog(context);
        loginDialog.show();
        loginDialog.setOnDialogClickListener(new LoginDialog.OnDialogClickListener() {
            @Override
            public void onPhoneClickListener() {
                System.out.println("onPhoneClickListener");
                loginDialog.phoneLogin();
            }

            @Override
            public void onEmailClickListener() {
                System.out.println("onEmailClickListener");
                loginDialog.emailLogin();
            }

            @Override
            public void onRegisterClickListener() {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivityForResult(intent, ConstantManager.REGISTER_FOR_MY);
            }

            @Override
            public void onProblemClickListener() {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLoginClickListener(Map<String, String> user) {
                final LoadingDialog2 loadingDialog2 = new LoadingDialog2(context);
                loadingDialog2.show();
                HashMap<String, String> mp = new HashMap<>();
                mp.put("username", user.get("username"));
                mp.put("password", user.get("password"));
                mp.put("qrtype", user.get("type"));
                HttpUtils.getInstance().post(ConstantManager.Url.LOGIN, mp, new HttpUtils.NetCall
                        () {
                    @Override
                    public void success(Call call, JSONObject json) throws IOException {
                        if (json != null) {
                            try {
                                if (json.getInt("code") == 0) {
                                    JSONObject data = json.getJSONObject("data");
                                    String username = data.getString("user_name");
                                    String _token = json.getString("token");
                                    String logo = data.getString("logo");
                                    String nick_name = data.getString("nick_name");
                                    String mobile_phone = data.getString("mobile_phone");
                                    String user_money = data.getString("user_money");
                                    String is_real = data.getString("is_real");
                                    HashMap<String, String> u = new HashMap<>();
                                    u.put("username", username);
                                    u.put("token", _token);
                                    u.put("logo", logo);
                                    u.put("nickname", nick_name);
                                    u.put("mobile_phone", mobile_phone);
                                    u.put("user_money", user_money);
                                    u.put("is_real", is_real);
                                    UserInfoUtils.getInstance(context).setUserInfo(u);
                                    token = _token;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadingDialog2.dismiss();
                                            loginDialog.dismiss();
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "网络错误！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    //fixme 检查账号是否已认证
    public void checkUserForReal() {
        if (is_real != null && is_real.equals("0")) {
            HashMap<String, String> _mp = new HashMap<>();
            _mp.put("token", token);
            HttpUtils.getInstance().post(ConstantManager.Url.GET_USER_INFO, _mp, new HttpUtils
                    .NetCall() {
                @Override
                public void success(Call call, JSONObject json) throws IOException {
                    if (json != null) {
                        try {
                            if (json.getInt("code") == 0) {
                                JSONObject data = json.getJSONObject("data");
                                is_real = data.getString("is_real");
                                HashMap<String, String> u = new HashMap<>();
                                u.put("is_real", is_real);
                                UserInfoUtils.getInstance(context).setUserInfo(u);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void failed(Call call, IOException e) {

                }
            });
        }
    }

    //fixme 生成订单
    public void addOrder(HashMap<String, Object> attr) {
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();

        mp.put("goods_id", goods_id + "");
        mp.put("num", attr.get("num") + "");
        List<Integer> attr_ids = (List<Integer>) attr.get("attr_ids");
        List<Integer> goods_attr_ids = (List<Integer>) attr.get("goods_attr_ids");
        if (attr_ids != null && goods_attr_ids != null && attr_ids.size() > 0 && goods_attr_ids
                .size() > 0) {
            for (int i = 0; i < attr_ids.size(); i++) {
                mp.put("attr_" + i, attr_ids.get(i) + "_" + goods_attr_ids.get(i));
            }
        }
        mp.put("token", token);
        HttpUtils.getInstance().post(ConstantManager.Url.ADD_ORDER, mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, final JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        try {
                            if (json.getInt("code") == 0) {
                                Intent intent = new Intent(context, OrderDetailsActivity.class);
                                JSONArray orderIds = json.getJSONObject("data").getJSONArray
                                        ("order");
                                intent.putExtra("order_id", StringUtils.join(orderIds, ","));
                                startActivity(intent);
                            } else {
                                ToastUtils.showShortToast(context, json.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        checkUserForReal();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        loadingDialog.dismiss();
        super.onDestroy();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textView32:
                    goodsAttrDialog.show(getSupportFragmentManager(), "Buy");
                    break;
                case R.id.textView31:
                    goodsAttrDialog.show(getSupportFragmentManager(), "AddCart");
                    break;
                case R.id.textView27:
                case R.id.imageView13:
                    Intent intent = new Intent(context, CartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.collection:
                    collectionGoods();
                    break;
            }
        }
    };

    GoodsFaatItem.OnClickListener faatListener = new GoodsFaatItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goodsFaatDialog.show(getSupportFragmentManager(), "Faat");
        }
    };
    GoodsAttrItem.OnClickListener attrListener = new GoodsAttrItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goodsAttrDialog.show(getSupportFragmentManager(), "Sure");
        }
    };
    GoodsShipItem.OnClickListener shipListener = new GoodsShipItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goodsAddressDialog.show(getSupportFragmentManager(), "Address");
        }
    };
    GoodsExplainItem.OnClickListener explainListener = new GoodsExplainItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            goodsExplainDialog.show(getSupportFragmentManager(), "Explain");
        }
    };
    GoodsEvaluateItem.OnClickListener evaluateListener = new GoodsEvaluateItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            Intent intent = new Intent(GoodsDetailActivity.this, EvaluateListActivity.class);
            switch (pos) {
                case Integer.MAX_VALUE - 1:
                    intent.putExtra("evaluateType", 0);
                    break;
                case Integer.MAX_VALUE - 2:
                    intent.putExtra("evaluateType", 1);
                    break;
                case Integer.MAX_VALUE - 3:
                    intent.putExtra("evaluateType", 2);
                    break;
                case Integer.MAX_VALUE - 4:
                    intent.putExtra("evaluateType", 3);
                case Integer.MAX_VALUE - 5:
                    intent = new Intent(GoodsDetailActivity.this, QuestionsListActivity.class);
                    break;
                default:
                    intent.putExtra("evaluateType", 0);
                    break;
            }
            startActivity(intent);
        }
    };
    GoodsBrandItem.OnClickListener brandListener = new GoodsBrandItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            Intent intent;
            if (pos == -1) {
                intent = new Intent(GoodsDetailActivity.this, BrandListActivity.class);
            } else {
                intent = new Intent(GoodsDetailActivity.this, GoodsDetailActivity.class);
                intent.putExtra("id", goodsDetails.getGoods_id());
            }
            startActivity(intent);
        }
    };
    GoodsAttrDialog.OnClickListener attrDialogListener = new GoodsAttrDialog.OnClickListener() {
        @Override
        public void onClicked(View view, HashMap<String, Object> attr) {
            String tag = (String) attr.get("tag");
            switch (tag) {
                case "Buy":
                    if (token == null || token.equals("")) {
                        showLoginDialog();
                    } else {
                        if (goodsDetails.getAddressBeans() != null && goodsDetails
                                .getAddressBeans().size() > 0) {
                            if (is_real == null && is_real.equals("0")) {
                                Intent intent = new Intent(context, UserRealNameActivity.class);
                                startActivity(intent);
                            } else {
                                addOrder(attr);
                            }
                        } else {
                            Intent intent = new Intent(context, AddressActivity.class);
                            startActivity(intent);
                        }
                    }
                    break;
                case "AddCart":
                    addCart(attr);
                    goodsAttrDialog.dismiss();
                    break;
                case "Sure":
                    selectAttrs.clear();
                    List<List<GoodsAttr>> multiAttrs = goodsDetails.getMultiAttrs();
                    for (int i = 0; i < multiAttrs.size(); i++) {
                        for (int j = 0; j < multiAttrs.get(i).size(); j++) {
                            List<Integer> goods_attr_ids = (List<Integer>) attr.get
                                    ("goods_attr_ids");
                            for (Integer goods_attr_id : goods_attr_ids) {
                                if (goods_attr_id.equals(multiAttrs.get(i).get(j)
                                        .getGoods_attr_id())) {
                                    selectAttrs.add(multiAttrs.get(i).get(j));
                                    goodsDetails.getMultiAttrs().get(i).get(j).setIsSelect(true);
                                }
                            }
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    goodsAttrDialog.dismiss();
                    break;
            }
        }
    };

    GoodsAddressDialog.OnClickListener addressDialogListener = new GoodsAddressDialog
            .OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            //前往添加地址
            if (pos == -1) {
                if (token != null && !token.equals("")) {
                    Intent intent = new Intent(GoodsDetailActivity.this, ModifyAddressActivity
                            .class);
                    startActivityForResult(intent, ConstantManager.ResultStatus.ADDRESSRESULT);
                } else {
                    //没有登录,显示登录框
                    showLoginDialog();
                }
            } else {
                if (goodsDetails.getAddressBeans() != null) {
                    for (int i = 0; i < goodsDetails.getAddressBeans().size(); i++) {
                        goodsDetails.getAddressBeans().get(i).setDef(0);
                        if (i == pos) {
                            goodsDetails.getAddressBeans().get(i).setDef(1);
                        }
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
            goodsAddressDialog.dismiss();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ConstantManager.ResultStatus.ADDRESSRESULT) {
                System.out.println(data);
            }
        }
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }


}
