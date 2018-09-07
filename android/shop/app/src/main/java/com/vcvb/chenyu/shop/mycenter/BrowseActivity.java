package com.vcvb.chenyu.shop.mycenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseErrorItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseItem;
import com.vcvb.chenyu.shop.adapter.item.browse.BrowseTitleItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.spacesitem.BrowseItemDecoration;
import com.vcvb.chenyu.shop.dialog.LoadingDialog;
import com.vcvb.chenyu.shop.goods.GoodsDetailActivity;
import com.vcvb.chenyu.shop.image.Images;
import com.vcvb.chenyu.shop.javaBean.collection.CollectionBean;
import com.vcvb.chenyu.shop.tools.HttpUtils;
import com.vcvb.chenyu.shop.tools.Routes;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class BrowseActivity extends BaseActivity {
    Context context;
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<CollectionBean> collections = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    public LoadingDialog loadingDialog;
    private TextView edit;
    private View bottom;
    private CheckBox cb;
    private TextView delete;

    private ConstraintSet set;
    private ConstraintLayout cly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_list);
        context = this;
        changeStatusBarTextColor(true);
        set = new ConstraintSet();
        cly = (ConstraintLayout) findViewById(R.id.browse);
        set.clone(cly);
        setNavBack();
        initView();
        initRefresh();
        getData(true);
        initListener();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView title = (TextView) findViewById(R.id.textView123);
        title.setText(R.string.browse);
        edit = (TextView) findViewById(R.id.textView122);
        edit.setText(R.string.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit.getText() == context.getString(R.string.edit)) {
                    edit();
                } else {
                    over();
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = (RecyclerView) findViewById(R.id.browse_list);
        mLayoutManager = new GridLayoutManager(context, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        bottom = findViewById(R.id.view38);
        cb = (CheckBox) findViewById(R.id.checkBox);
        delete = (TextView) findViewById(R.id.textView130);
    }

    @Override
    public void getData(final boolean b) {
        super.getData(b);
        if (b) {
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
            loadingDialog.show();
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("goods_id", "");
        mp.put("nav_id", "0");
//        mp.put("order_type", ""+type);
        HttpUtils.getInstance().post(Routes.getInstance().getIndex(), mp, new HttpUtils.NetCall() {
            @Override
            public void success(Call call, JSONObject json) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }

                        if (collections.size() != 0) {
//                            setHaveDataByView();
                        } else {
//                            setNoDateByView();
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            loadingDialog.dismiss();
                        }
//                        setNoDateByView();
                    }
                });
            }
        });

        collections.clear();
//        CollectionBean bean = new CollectionBean();
//        bean.setIsType(-1);
//        bean.setGoodsName("s");
//        collections.add(bean);
        for (int i = 0; i < 5; i++) {
            CollectionBean bean = new CollectionBean();
            bean.setIsType(2);
            bean.setDate("1999-10-1" + i);
            collections.add(bean);
            for (int j = 0; j < 5; j++) {
                bean = new CollectionBean();
                bean.setIsType(1);
                bean.setGoodsName("setGoodsName" + j);
                bean.setPic(Images.imageUrls[j + i]);
                collections.add(bean);
            }
        }
        mAdapter.addAll(getItems(collections));
        BrowseItemDecoration browseItemDecoration = new BrowseItemDecoration(context, collections);
        mRecyclerView.addItemDecoration(browseItemDecoration);
    }

    @Override
    public void initListener() {
        super.initListener();

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bool;
                if (cb.isChecked() == true) {
                    bool = true;
                } else {
                    bool = false;
                }
                for (int i = 0; i < collections.size(); i++) {
                    collections.get(i).setIsSelect(bool);
                    collections.get(i).setIsSelectAll(bool);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItems();
            }
        });

        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                Intent intent = new Intent(BrowseActivity.this, GoodsDetailActivity.class);
                startActivity(intent);
            }
        });

        CYCItemClickSupport.BuildTo(mRecyclerView, R.id.checkBox6).setOnChildClickListener(new CYCItemClickSupport.OnChildItemClickListener() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                mAdapter.notifyDataSetChanged();
                if (collections.get(position).getIsType() == 1) {
                    int ppos = position;
                    int npos = position;
                    boolean tbool = false;
                    if (collections.get(position).getIsSelect() == true) {
                        collections.get(position).setIsSelect(false);
                        tbool = false;
                    } else {
                        collections.get(position).setIsSelect(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (collections.get(npos).getIsType() == 1) {
                        if (collections.get(npos).getIsSelect() == false) {
                            tbool = false;
                        }
                        npos += 1;
                    }
                    ppos -= 1;
                    while (collections.get(ppos).getIsType() == 1) {
                        if (collections.get(ppos).getIsSelect() == false) {
                            tbool = false;
                        }
                        ppos -= 1;
                    }
                    collections.get(ppos).setIsSelectAll(tbool);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        CYCItemClickSupport.BuildTo1(mRecyclerView, R.id.checkBox7).setOnChildClickListener1(new CYCItemClickSupport.OnChildItemClickListener1() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {
                if (collections.get(position).getIsType() == 2) {
                    int npos = position;
                    boolean tbool = false;
                    if (collections.get(position).getIsSelectAll() == true) {
                        collections.get(position).setIsSelectAll(false);
                        tbool = false;
                    } else {
                        collections.get(position).setIsSelectAll(true);
                        tbool = true;
                    }
                    npos += 1;
                    while (collections.get(npos).getIsType() == 1) {
                        collections.get(npos).setIsSelect(tbool);
                        npos += 1;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        //找相似
        CYCItemClickSupport.BuildTo2(mRecyclerView, R.id.textView133).setOnChildClickListener2
                (new CYCItemClickSupport.OnChildItemClickListener2() {
            @Override
            public void onChildItemClicked(RecyclerView recyclerView, View itemView, int position) {

            }
        });


    }

    protected List<Item> getItems(List<CollectionBean> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case -1:
                    cells.add(new BrowseErrorItem(list.get(i), context));
                    break;
                case 1:
                    cells.add(new BrowseItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new BrowseTitleItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    public synchronized void deleteItems(){
        List<CollectionBean> collections_bak = new ArrayList<>();
        for (int i = 0; i < collections.size(); i++) {
            synchronized (this){
                if (collections.get(i).getIsType() == 1) {
                    if (collections.get(i).getIsSelect() == true) {
                        collections_bak.add(collections.get(i));
                    }
                } else if (collections.get(i).getIsType() == 2) {
                    if (collections.get(i).getIsSelectAll() == true) {
                        collections_bak.add(collections.get(i));
                    }
                }
            }
        }
        collections.removeAll(collections_bak);
        mAdapter.clear();
        mAdapter.addAll(getItems(collections));
        over();
        mAdapter.notifyDataSetChanged();
    }

    public void edit(){
        edit.setText(R.string.over);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 50));
        for (int i = 0; i < collections.size(); i++) {
            collections.get(i).setIsLong(true);
        }
        set.applyTo(cly);
    }
    public void over(){
        edit.setText(R.string.edit);
        set.constrainHeight(bottom.getId(), ToolUtils.dip2px(context, 0));
        for (int i = 0; i < collections.size(); i++) {
            collections.get(i).setIsLong(false);
        }
        set.applyTo(cly);
    }
}
