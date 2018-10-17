package com.vcvb.chenyu.shop.goods;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
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
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsEvaluateItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsExplainItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsFaatItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsPriceItem;
import com.vcvb.chenyu.shop.adapter.item.goods.GoodsShipItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.dialog.GoodsAddressDialog;
import com.vcvb.chenyu.shop.dialog.GoodsAttrDialog;
import com.vcvb.chenyu.shop.dialog.GoodsExplainDialog;
import com.vcvb.chenyu.shop.dialog.GoodsFaatDialog;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.evaluate.EvaluateListActivity;
import com.vcvb.chenyu.shop.evaluate.QuestionsListActivity;
import com.vcvb.chenyu.shop.faat.BrandListActivity;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttr;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttrs;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsShip;
import com.vcvb.chenyu.shop.mycenter.CartActivity;
import com.vcvb.chenyu.shop.order.OrderDetailsActivity;
import com.vcvb.chenyu.shop.overrideView.ShopGridLayoutManager;
import com.vcvb.chenyu.shop.overrideView.ShopRecyclerView;
import com.vcvb.chenyu.shop.popwin.PopWin;
import com.vcvb.chenyu.shop.receiver.Receiver;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class GoodsDetailActivity extends GoodsActivity {
    private int goods_id;
    private JSONObject goodsJson;

    int pos = 0;
    private View child1;
    private View line;
    private Receiver receiver;

    private ShopRecyclerView goodsDatail;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private GoodsDetail goodsDetails = new GoodsDetail();
    private ShopGridLayoutManager gridLayoutManager;

    private GoodsAttrDialog goodsAttrDialog;
    private GoodsFaatDialog goodsFaatDialog;
    private GoodsAddressDialog goodsAddressDialog;
    private GoodsExplainDialog goodsExplainDialog;

    private ArrayList<GoodsAttrs> gattrs = new ArrayList<>();
    private ArrayList<GoodsAttr> selectAttrs = new ArrayList<>();
    private ArrayList<GoodsShip> ships = new ArrayList<>();

    private boolean isCollection = false;

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
        loadingDialog.show();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", goods_id + "");
        mp.put("token", (String) UserInfoUtils.getInstance(context).getUserInfo().get("token"));
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
                goodsDatail.scrollToPosition(0);
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
                goodsDatail.scrollToPosition(8);
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
                goodsDatail.scrollToPosition(10);
            }
        });


        collectionView = findViewById(R.id.collection);
        collectionView.setOnClickListener(listener);

        final PopWin popWindow = new PopWin(GoodsDetailActivity.this, ToolUtils.dip2px(this, 156)
                , ToolUtils.dip2px(this, 148));
        final ImageView iv2 = (ImageView) findViewById(R.id.more);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.showAsDropDown(iv2);
            }
        });
        popWindow.setClickListener(new PopWin.OnItemClickListener() {
            @Override
            public void onClicked(View v) {
                System.out.println(v);
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
        goodsDatail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int alpha = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                System.out.println(newState);
                switch (newState) {
                    case 0: //不滚动
//                        goodsDetails.getGoodsEvaluate().setIsScroll(1);
//                        goodsDetails.getGoodsBrand().setIsScroll(1);
                        mAdapter.notifyDataSetChanged();
                        break;
                    case 1: // 按着手指滚动
//                        goodsDetails.getGoodsEvaluate().setIsScroll(0);
//                        goodsDetails.getGoodsBrand().setIsScroll(0);
                        break;
                    case 2: // 不按着手指滚动
//                        goodsDetails.getGoodsEvaluate().setIsScroll(0);
//                        goodsDetails.getGoodsBrand().setIsScroll(0);
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                child1 = gridLayoutManager.getChildAt(0);
                if (goodsDatail.getChildAdapterPosition(child1) == 0) {
                    goodsView.setTextSize(ts_22);
                    goodsView.setTextColor(Color.parseColor("#000000"));
                    goodsEvaluate.setTextSize(ts_18);
                    goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsInfo.setTextSize(ts_18);
                    goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                } else if (goodsDatail.getChildAdapterPosition(child1) == 8) {
                    goodsView.setTextSize(ts_18);
                    goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsInfo.setTextSize(ts_18);
                    goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsEvaluate.setTextSize(ts_22);
                    goodsEvaluate.setTextColor(Color.parseColor("#000000"));
                } else if (goodsDatail.getChildAdapterPosition(child1) == 10) {
                    goodsInfo.setTextSize(ts_22);
                    goodsInfo.setTextColor(Color.parseColor("#000000"));
                    goodsView.setTextSize(ts_18);
                    goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                    goodsEvaluate.setTextSize(ts_18);
                    goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                }
//                System.out.println(dy);
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
        ImageView iv2 = findViewById(R.id.imageView13);
        buy.setOnClickListener(listener);
        addCart.setOnClickListener(listener);
        iv2.setOnClickListener(listener);

        goodsDatail = findViewById(R.id.goods_datail);
        goodsDatail.setFlingScale(0.3);
        goodsDatail.setNestedScrollingEnabled(false);
        gridLayoutManager = new ShopGridLayoutManager(this, 1);
        gridLayoutManager.setSpeedRatio(1);
        goodsDatail.setLayoutManager(gridLayoutManager);
        goodsDatail.setItemAnimator(new DefaultItemAnimator());
        goodsDatail.setAdapter(mAdapter);
        goodsDatail.setNestedScrollingEnabled(false);
    }

    public void bindData() {
        if (goodsJson != null) {
            try {
                goodsDetails.setData(goodsJson.getJSONObject("data"));
                mAdapter.addAll(getItems(goodsDetails));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<Item> getItems(GoodsDetail goodsDetails) {
        List<Item> cells = new ArrayList<>();
        if (goodsDetails.getBanners() != null && goodsDetails.getBanners().size() > 0) {
            cells.add(new GoodsBannerItem(goodsDetails.getBanners(), context));
        }
        if (goodsDetails.getShop_price() != null) {
            cells.add(new GoodsPriceItem(goodsDetails, context));
        }
//        if (goodsDetails.getGoodsName() != null && goodsDetails.getGoodsName() != "") {
//            cells.add(new GoodsNameItem(goodsDetails, context));
//        }
//        if (goodsDetails.getFaats() != null && goodsDetails.getFaats().size() > 0) {
//            GoodsFaatItem goodsFaatItem = new GoodsFaatItem(goodsDetails.getFaats(), context);
//            goodsFaatItem.setOnItemClickListener(faatListener);
//            cells.add(goodsFaatItem);
//        }
//        if (goodsDetails.getGoodsAttrs() != null && goodsDetails.getGoodsAttrs().size() > 0) {
//            GoodsAttrItem goodsAttrItem = new GoodsAttrItem(goodsDetails.getGoodsAttrs(), context);
//            goodsAttrItem.setOnItemClickListener(attrListener);
//            cells.add(goodsAttrItem);
//        }
//        if (goodsDetails.getGoodsShip() != null) {
//            GoodsShipItem goodsShipItem = new GoodsShipItem(goodsDetails.getGoodsShip(), context);
//            goodsShipItem.setOnItemClickListener(shipListener);
//            cells.add(goodsShipItem);
//        }
//        if (goodsDetails.getShipFree() != null) {
//            GoodsShipFreeItem goodsShipFreeItem = new GoodsShipFreeItem(goodsDetails.getShipFree
//                    (), context);
//            cells.add(goodsShipFreeItem);
//        }
//        if (goodsDetails.getGoodsExplains() != null) {
//            GoodsExplainItem goodsExplainItem = new GoodsExplainItem(goodsDetails
//                    .getGoodsExplains(), context);
//            goodsExplainItem.setOnItemClickListener(explainListener);
//            cells.add(goodsExplainItem);
//        }
//        if (goodsDetails.getGoodsEvaluate() != null) {
//            GoodsEvaluateItem goodsEvaluateItem = new GoodsEvaluateItem(goodsDetails
//                    .getGoodsEvaluate(), context);
//            goodsEvaluateItem.setOnItemClickListener(evaluateListener);
//            cells.add(goodsEvaluateItem);
//        }
//        if (goodsDetails.getGoodsBrand() != null) {
//            GoodsBrandItem goodsBrandItem = new GoodsBrandItem(goodsDetails.getGoodsBrand(),
//                    context);
//            goodsBrandItem.setOnItemClickListener(brandListener);
//            cells.add(goodsBrandItem);
//        }
//        if (goodsDetails.getSpecifications() != null) {
//            cells.add(new GoodsSpecificationsItem(goodsDetails.getSpecifications(), context));
//        }
//        if (goodsDetails.getDescs() != null && goodsDetails.getDescs().size() > 0) {
//            for (int i = 0; i < goodsDetails.getDescs().size(); i++) {
//                cells.add(new GoodsDescItem(goodsDetails.getDescs().get(i), context));
//            }
//        }
        return cells;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("BannerClick");
        intentFilter.addAction("FaatClick");
        intentFilter.addAction("AttrClick");
        intentFilter.addAction("ShipClick");
        intentFilter.addAction("ShipFreeClick");
        intentFilter.addAction("ExplainClick");
        intentFilter.addAction("EvaluateClick");
        intentFilter.addAction("EvaluateTag1Click");
        intentFilter.addAction("EvaluateTag2Click");
        intentFilter.addAction("EvaluateTag3Click");
        intentFilter.addAction("EvaluateUserClick");
        intentFilter.addAction("ProblemClick");
        intentFilter.addAction("AnswerClick");
        intentFilter.addAction("BrandClick");
        intentFilter.addAction("BrandGoodsClick");
        receiver = new Receiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "BannerClick":
                        int pos = intent.getIntExtra("pos", 0);
                        break;
                    case "FaatClick":
                        break;
                    case "AttrClick":
                        break;
                    case "ShipClick":
                        break;
                    case "ShipFreeClick":
                        break;
                    case "ExplainClick":
                        break;
                    case "EvaluateClick":
                        break;
                    case "EvaluateTag1Click":
                        break;
                    case "EvaluateTag2Click":
                        break;
                    case "EvaluateTag3Click":
                        break;
                    case "EvaluateUserClick":
                        break;
                    case "ProblemClick":
                        break;
                    case "AnswerClick":
                        break;
                    case "BrandClick":
                        break;
                    case "BrandGoodsClick":
                        break;
                    default:
                        break;
                }
            }
        };

        broadcastManager.registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        setContentView(R.layout.view_null);
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
                case R.id.imageView13:
                    Intent intent = new Intent(context, CartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.collection:
                    if (isCollection == true) {
                        Glide.with(context).load(R.drawable.icon_love_black).into(collectionView);
                        isCollection = false;
                    } else {
                        Glide.with(context).load(R.drawable.icon_love_active).into(collectionView);
                        isCollection = true;
                    }
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
                    System.out.println(attr);
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("order", 1);
                    startActivity(intent);
                    break;
                case "AddCart":
                    goodsAttrDialog.dismiss();
                    break;
                case "Sure":
//                    selectAttrs.clear();
//                    for (int i = 0; i < goodsDetails.getGoodsAttrs().size(); i++) {
//                        int id = (int) attr.get(i + "");
//                        for (int j = 0; j < goodsDetails.getGoodsAttrs().get(i).getAttrs().size()
//                                ; j++) {
//                            if (id == goodsDetails.getGoodsAttrs().get(i).getAttrs().get(j)
//                                    .getAttrId()) {
//                                selectAttrs.add(goodsDetails.getGoodsAttrs().get(i).getAttrs()
//                                        .get(j));
//                                goodsDetails.getGoodsAttrs().get(i).getAttrs().get(j).setIsSelect
//                                        (true);
//                            }
//                        }
//                    }
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
//            goodsDetails.setGoodsShip(ships.get(pos));
            mAdapter.notifyDataSetChanged();
            goodsAddressDialog.dismiss();
        }
    };
}
