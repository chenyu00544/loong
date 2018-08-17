package com.ecjia.hamster.activity.goodsdetail.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ecjia.a.aa;
import com.ecjia.a.q;
import com.ecjia.a.y;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ab;
import com.ecjia.component.a.ah;
import com.ecjia.component.a.c;
import com.ecjia.component.a.i;
import com.ecjia.component.a.n;
import com.ecjia.component.view.ECJiaFlowLayout;
import com.ecjia.component.view.banner.ECJiaBannerView;
import com.ecjia.component.view.banner.ECJiaBannerView.b;
import com.ecjia.component.view.k;
import com.ecjia.component.view.l;
import com.ecjia.component.view.timecount.ECJiaCountDownView;
import com.ecjia.hamster.activity.ECJiaAddressAddActivity;
import com.ecjia.hamster.activity.ECJiaAddressChooseActivity;
import com.ecjia.hamster.activity.ECJiaBalanceActivity;
import com.ecjia.hamster.activity.ECJiaConsultActivity;
import com.ecjia.hamster.activity.ECJiaFullScreenViewPagerActivity;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.activity.ECJiaShopListActivity;
import com.ecjia.hamster.activity.ECJiaSpecificationActivity;
import com.ecjia.hamster.activity.ECJiaWebViewActivity;
import com.ecjia.hamster.activity.goodsdetail.view.ECJiaBottomElasticScrollView;
import com.ecjia.hamster.activity.goodsdetail.view.ECJiaGoodsCommetView;
import com.ecjia.hamster.activity.goodsdetail.view.ECJiaScrollViewWrapper;
import com.ecjia.hamster.activity.goodsdetail.view.ECJiaTopElasticScrollView;
import com.ecjia.hamster.adapter.ax;
import com.ecjia.hamster.adapter.bu;
import com.ecjia.hamster.adapter.o;
import com.ecjia.hamster.model.ECJia_ADDRESS;
import com.ecjia.hamster.model.ECJia_FAVOUR;
import com.ecjia.hamster.model.ECJia_GOODS_COUPON;
import com.ecjia.hamster.model.ECJia_PHOTO;
import com.ecjia.hamster.model.ECJia_PRODUCT_SPECIFICATION;
import com.ecjia.hamster.model.al;
import com.ecjia.hamster.model.av;
import com.ecjia.hamster.model.aw;
import com.ecjia.hamster.model.p;
import com.ecjia.hamster.model.x;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.accs.common.Constants;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJiaProductFragment extends ECJiaGoodsDetailBaseFragment implements OnClickListener, a, ax.a {
    private View A;
    private View B;
    private TextView C;
    private TextView D;
    private TextView E;
    private LinearLayout F;
    private TextView G;
    private LinearLayout H;
    private LinearLayout I;
    private ECJiaScrollViewWrapper J;
    private TextView K;
    private LinearLayout L;
    private View M;
    private LinearLayout N;
    private TextView O;
    private TextView P;
    private LinearLayout Q;
    private TextView R;
    private View S;
    private String T;
    private boolean U = false;
    private i V;
    private o W;
    private ListView X;
    private View Y;
    private int Z;
    private String aA = "";
    private String aB = "";
    private RelativeLayout aC;
    private RelativeLayout aD;
    private TextView aE;
    private TextView aF;
    private WebView aG;
    private String aH = "";
    private float aI = -1.0f;
    private float aJ;
    private String[] aK;
    private boolean aL = false;
    private Handler aM = new ECJiaProductFragment_5(this);
    private final int aN = 10010;
    private final int aO = 10011;
    private final int aP = 10012;
    private final int aQ = 10013;
    private final int aR = 10014;
    private final int aS = 10015;
    private final int aT = 10016;
    private final int aU = 10017;
    private final int aV = 10018;
    private final int aW = 10019;
    private String aa;
    private ah ab;
    private boolean ac = false;
    private c ad;
    private String ae;
    private SharedPreferences af;
    private Editor ag;
    private TextView ah;
    private TextView ai;
    private TextView aj;
    private TextView ak;
    private View al;
    private View am;
    private TextView an;
    private TextView ao;
    private TextView ap;
    private LinearLayout aq;
    private LinearLayout ar;
    private LinearLayout as;
    private ImageView at;
    private TextView au;
    private TextView av;
    private TextView aw;
    private TextView ax;
    private TextView ay;
    private String az = "";
    ECJiaBannerView<ECJia_PHOTO> d;
    ECJiaCountDownView e;
    public n f;
    boolean g = false;
    boolean h = true;
    ECJiaBottomElasticScrollView i;
    ECJiaTopElasticScrollView j;
    ECJiaTopElasticScrollView k;
    ECJiaTopElasticScrollView l;
    View m;
    ECJiaFlowLayout n;
    View o;
    public boolean p = false;
    LinearLayout q;
    com.ecjia.component.view.i r;
    ab s;
    ArrayList<p> t = new ArrayList();
    View u;
    View v;
    ECJiaFlowLayout w;
    int x = 0;
    public boolean y = false;
    private a z = null;

    class ECJiaProductFragment_10 implements ECJiaBannerView.c<ECJia_PHOTO> {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_10(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void a(ImageView imageView, ECJia_PHOTO eCJia_PHOTO) {
            ImageLoader.getInstance().displayImage(eCJia_PHOTO.getUrl(), imageView);
        }
    }

    class ECJiaProductFragment_11 implements b {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_11(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void a(View view, int i) {
            Intent intent = new Intent(this.a.getActivity(), ECJiaFullScreenViewPagerActivity.class);
            intent.putExtra("position", i);
            Serializable arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.a.f.b.o().size(); i2++) {
                arrayList.add(((ECJia_PHOTO) this.a.f.b.o().get(i2)).getUrl());
            }
            intent.putExtra("size", this.a.f.b.o().size());
            intent.putExtra("pictures", arrayList);
            this.a.startActivity(intent);
            this.a.getActivity().overridePendingTransition(R.anim.my_scale_action, R.anim.my_scale_action);
        }
    }

    class ECJiaProductFragment_12 implements com.ecjia.hamster.activity.goodsdetail.view.b {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_12(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void a(int i) {
            switch (i) {
                case 0:
                    this.a.K.setText(R.string.detail_refresh_list_push);
                    this.a.b.b();
                    return;
                case 1:
                    this.a.m.setVisibility(0);
                    this.a.K.setText(R.string.detail_refresh_list_pull);
                    if (this.a.h && TextUtils.isEmpty(this.a.f.t)) {
                        this.a.h = false;
                        this.a.f.c(this.a.aa + "");
                    }
                    this.a.b.c();
                    return;
                default:
                    return;
            }
        }
    }

    class ECJiaProductFragment_13 implements OnClickListener {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_13(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void onClick(View view) {
            this.a.a("one");
        }
    }

    class ECJiaProductFragment_14 implements OnClickListener {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_14(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void onClick(View view) {
            this.a.a("two");
        }
    }

    class ECJiaProductFragment_1 implements OnClickListener {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_1(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a.getActivity(), ECJiaAddressChooseActivity.class), 2);
            this.a.getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }

    class ECJiaProductFragment_2 extends WebViewClient {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_2(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    class ECJiaProductFragment_3 implements Runnable {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_3(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void run() {
            Cursor a = new bu(this.a.b).a();
            int i = 0;
            while (a.moveToNext()) {
                a.getInt(1);
                Object string = a.getString(2);
                if (!TextUtils.isEmpty(string)) {
                    try {
                        string = p.a(new JSONObject(string));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (i < 8) {
                        break;
                    }
                    this.a.t.add(string);
                    i++;
                }
                string = null;
                if (i < 8) {
                    break;
                }
                this.a.t.add(string);
                i++;
            }
            a.close();
            Message message = new Message();
            message.obj = "get_history_succeed";
            this.a.aM.sendMessage(message);
        }
    }

    class ECJiaProductFragment_5 extends Handler {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_5(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void handleMessage(Message message) {
            this.a.h();
        }
    }

    class ECJiaProductFragment_8 implements OnClickListener {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_8(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void onClick(View view) {
            this.a.J.scrollToPageTwo();
        }
    }

    class ECJiaProductFragment_9 implements OnClickListener {
        final /* synthetic */ ECJiaProductFragment a;

        ECJiaProductFragment_9(ECJiaProductFragment eCJiaProductFragment) {
            this.a = eCJiaProductFragment;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a.getActivity(), ECJiaShopListActivity.class);
            intent.putExtra("merchant_id", this.a.f.e);
            this.a.startActivityForResult(intent, 10019);
            this.a.getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b = (ECJiaGoodsDetailActivity) activity;
        de.greenrobot.event.c.a().a((Object) this);
    }

    public void onDetach() {
        de.greenrobot.event.c.a().b(this);
        super.onDetach();
    }

    public void onDestroyView() {
        if (this.ab != null) {
            this.ab.h();
        }
        this.V.h();
        this.ad.h();
        this.f.h();
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.af = this.b.getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.ag = this.af.edit();
        View inflate = layoutInflater.inflate(R.layout.fragment_product_layout, null);
        i(inflate);
        l();
        return inflate;
    }

    private void l() {
        o();
        p();
    }

    public int b() {
        return Math.min(getActivity().getWindowManager().getDefaultDisplay().getWidth(), getActivity().getWindowManager().getDefaultDisplay().getHeight());
    }

    private void i(View view) {
        this.x = (b() - (((int) getResources().getDimension(R.dimen.dp_10)) * 4)) / 3;
        k(view);
        b(view);
        a(view);
        j(view);
        c(view);
        g(view);
        f(view);
        d(view);
    }

    private void j(View view) {
        this.q = (LinearLayout) view.findViewById(R.id.commentView_in);
    }

    void a(View view) {
        this.al = this.A.findViewById(R.id.promotion_time_ll);
        this.al.setVisibility(8);
        this.am.setVisibility(0);
        this.e = (ECJiaCountDownView) this.A.findViewById(R.id.promotion_time);
    }

    void b(View view) {
        this.A = view.findViewById(R.id.pageone_headerview);
        this.d = (ECJiaBannerView) this.A.findViewById(R.id.detail_banner);
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = b();
        layoutParams.width = b();
        this.an = (TextView) this.A.findViewById(R.id.sales_volume);
        this.ao = (TextView) this.A.findViewById(R.id.goods_sales_volume);
        this.C = (TextView) this.A.findViewById(R.id.good_brief);
        this.am = this.A.findViewById(R.id.price_ll);
        this.D = (TextView) this.A.findViewById(R.id.promote_price);
        this.E = (TextView) this.A.findViewById(R.id.market_price);
        this.aj = (TextView) this.A.findViewById(R.id.promote_price_for_promote);
        this.ak = (TextView) this.A.findViewById(R.id.market_price_for_promote);
        this.ak.getPaint().setAntiAlias(true);
        this.ak.getPaint().setFlags(17);
        this.E.getPaint().setAntiAlias(true);
        this.E.getPaint().setFlags(17);
        this.F = (LinearLayout) this.A.findViewById(R.id.ll_mobile_buy);
        this.G = (TextView) this.A.findViewById(R.id.tv_mobile_discount);
        this.H = (LinearLayout) this.A.findViewById(R.id.ll_goodsdetail_favour_item);
        this.I = (LinearLayout) this.A.findViewById(R.id.mgv_favour);
        this.L = (LinearLayout) this.A.findViewById(R.id.goodsdetail_redpaper_ll);
        this.L.setVisibility(8);
        this.M = this.A.findViewById(R.id.goodsdetail_redpaper_iv);
        this.M.setOnClickListener(this);
        this.N = (LinearLayout) this.A.findViewById(R.id.good_category_item);
        this.O = (TextView) this.A.findViewById(R.id.specification_text);
        this.P = (TextView) this.A.findViewById(R.id.good_category);
        this.O.setSingleLine(false);
        this.N.setOnClickListener(this);
        this.ap = (TextView) this.A.findViewById(R.id.good_cityinfo);
        this.ap.setText(this.af.getString("sendArea", ""));
        this.aq = (LinearLayout) this.A.findViewById(R.id.good_city_item);
        this.aq.setOnClickListener(new ECJiaProductFragment_1(this));
        this.ah = (TextView) this.A.findViewById(R.id.good_serviceinfo);
        this.Q = (LinearLayout) this.A.findViewById(R.id.goods_comment);
        this.R = (TextView) this.A.findViewById(R.id.gooddetail_commit_num);
        this.ai = (TextView) this.A.findViewById(R.id.positive_comment);
        this.Q.setOnClickListener(this);
        this.S = this.A.findViewById(R.id.headline);
    }

    void c(View view) {
        this.B = view.findViewById(R.id.pageone_footerview);
        this.B.findViewById(R.id.foottext).setOnClickListener(new ECJiaProductFragment_8(this));
        this.K = (TextView) this.B.findViewById(R.id.load_text);
        e(this.B);
        h(this.B);
        m();
    }

    private void m() {
        this.as = (LinearLayout) this.B.findViewById(R.id.seller_item);
        this.at = (ImageView) this.B.findViewById(R.id.seller_logo);
        this.au = (TextView) this.B.findViewById(R.id.seller_name);
        this.av = (TextView) this.B.findViewById(R.id.collected_num);
        this.aw = (TextView) this.B.findViewById(R.id.seller_comment);
        this.ax = (TextView) this.B.findViewById(R.id.seller_service);
        this.ay = (TextView) this.B.findViewById(R.id.seller_time);
        this.ar = (LinearLayout) this.B.findViewById(R.id.foot_seller_toshop_item);
        ((ImageView) this.B.findViewById(R.id.foot_seller_toshop_icon)).setColorFilter(Color.parseColor("#FFFFFF"));
        this.ar.setOnClickListener(new ECJiaProductFragment_9(this));
    }

    private void n() {
        if (this.f.d != null) {
            this.as.setVisibility(0);
            x xVar = this.f.d;
            ImageLoader.getInstance().displayImage(xVar.b(), this.at);
            this.au.setText(xVar.a());
            this.av.setText(xVar.c() + getResources().getString(R.string.follower_num));
            this.aw.setText(xVar.d());
            this.ax.setText(xVar.e());
            this.ay.setText(xVar.f());
            return;
        }
        this.as.setVisibility(8);
    }

    private void o() {
        this.V = new i(getActivity());
        this.V.a((a) this);
        this.f = new n(getActivity());
        this.f.a((a) this);
        this.ad = new c(getActivity());
        this.ad.a((a) this);
    }

    private void p() {
        this.aa = getActivity().getIntent().getStringExtra("goods_id");
        this.az = getActivity().getIntent().getStringExtra("object_id");
        this.aA = getActivity().getIntent().getStringExtra("rec_type");
        this.aB = getActivity().getIntent().getStringExtra("seckill");
        this.f.a(this.aa, this.az, this.aA, true);
        B();
    }

    private void q() {
        int size = this.f.b.o().size();
        ECJia_PHOTO[] eCJia_PHOTOArr = new ECJia_PHOTO[this.f.b.o().size()];
        if (eCJia_PHOTOArr.length > 0) {
            for (int i = 0; i < size; i++) {
                eCJia_PHOTOArr[i] = (ECJia_PHOTO) this.f.b.o().get(i);
            }
            this.d.setBannerStyle(1);
            this.d.setOnBannerImageListener(new ECJiaProductFragment_10(this));
            this.d.setOnBannerClickListener(new ECJiaProductFragment_11(this));
            this.d.setImages(this.f.b.o());
        }
    }

    private void k(View view) {
        this.z = (a) getActivity();
        this.J = (ECJiaScrollViewWrapper) view.findViewById(R.id.gooddetail_sc);
        this.J.setOnScrollChangeListener(new ECJiaProductFragment_12(this));
        this.i = (ECJiaBottomElasticScrollView) view.findViewById(R.id.page_one_goodsdetail);
        this.l = (ECJiaTopElasticScrollView) view.findViewById(R.id.page_two_errorview);
        this.k = (ECJiaTopElasticScrollView) view.findViewById(R.id.page_two_webview);
        this.j = (ECJiaTopElasticScrollView) view.findViewById(R.id.page_two_listview);
        this.l.setParentScrollView(this.J);
        this.k.setParentScrollView(this.J);
        this.j.setParentScrollView(this.J);
        this.i.setParentScrollView(this.J);
    }

    void d(View view) {
        this.m = view.findViewById(R.id.buttom_toolbar);
        this.m.setVisibility(4);
        this.aC = (RelativeLayout) view.findViewById(R.id.tabOne_item);
        this.aD = (RelativeLayout) view.findViewById(R.id.tabTwo_item);
        this.aE = (TextView) view.findViewById(R.id.tabone_text);
        this.aF = (TextView) view.findViewById(R.id.tabtwo_text);
        this.aC.setOnClickListener(new ECJiaProductFragment_13(this));
        this.aD.setOnClickListener(new ECJiaProductFragment_14(this));
        a("one");
    }

    private void a(String str) {
        ColorStateList colorStateList = getResources().getColorStateList(R.color.filter_text_color);
        if ("one".equals(str)) {
            this.aE.setTextColor(SupportMenu.CATEGORY_MASK);
            this.aF.setTextColor(colorStateList);
            this.l.setVisibility(8);
            this.k.setVisibility(0);
            this.j.setVisibility(8);
        } else if ("two".equals(str)) {
            this.aE.setTextColor(colorStateList);
            this.aF.setTextColor(SupportMenu.CATEGORY_MASK);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            if (com.ecjia.hamster.adapter.n.a().a == null || com.ecjia.hamster.adapter.n.a().a.q().size() <= 0) {
                this.j.setVisibility(8);
                this.l.setVisibility(0);
                return;
            }
            this.j.setVisibility(0);
            this.X.setSelection(0);
            this.l.setVisibility(8);
        }
    }

    void e(View view) {
        this.n = (ECJiaFlowLayout) view.findViewById(R.id.goodsdetail_mylove_goods_layout);
        this.o = view.findViewById(R.id.goodsdetail_mylove_in);
        this.o.setVisibility(8);
    }

    void c() {
        this.n.removeAllViews();
        int size = this.f.j.size() >= 6 ? 6 : this.f.j.size();
        if (size == 0) {
            this.o.setVisibility(8);
            return;
        }
        this.o.setVisibility(0);
        this.n.setBackgroundColor(Color.parseColor("#ffffff"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i = 0;
        while (i < size) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_goodsdetail_mylove, null);
            RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.goods_image_ll);
            TextView textView = (TextView) linearLayout.findViewById(R.id.goods_name);
            TextView textView2 = (TextView) linearLayout.findViewById(R.id.goods_price);
            ImageLoader.getInstance().displayImage(((al) this.f.j.get(i)).a().getThumb(), (ImageView) linearLayout.findViewById(R.id.goods_image));
            textView.setText(((al) this.f.j.get(i)).e());
            if (TextUtils.isEmpty(((al) this.f.j.get(i)).b()) || ((al) this.f.j.get(i)).b().equals("null")) {
                textView2.setText(((al) this.f.j.get(i)).c());
            } else {
                textView2.setText(((al) this.f.j.get(i)).b());
            }
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaProductFragment b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.getActivity(), ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((al) this.b.f.j.get(i)).d() + "");
                    this.b.startActivity(intent);
                    this.b.getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(this.x, this.x));
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.x, -2);
            layoutParams2.setMargins((int) getResources().getDimension(R.dimen.dp_10), (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
            linearLayout.setLayoutParams(layoutParams2);
            this.n.addView(linearLayout, layoutParams2);
            i++;
        }
    }

    public void f(View view) {
        this.X = (ListView) view.findViewById(R.id.property_list);
        this.X.getLayoutParams().height = r();
    }

    private int r() {
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        int dimension = (int) getResources().getDimension(R.dimen.main_items_height);
        q.a("screemHeight:  " + height + "   getGoodsDesHeight:  " + (((height - (dimension * 2)) - ((int) getResources().getDimension(R.dimen.height_goodsdetail_topbar))) - a()));
        return (height - (dimension * 3)) - a();
    }

    public void g(View view) {
        this.Y = view.findViewById(R.id.not_info);
        this.Y.setVisibility(0);
        this.Y.getLayoutParams().height = r();
        this.aG = (WebView) view.findViewById(R.id.my_web);
        this.aG.getLayoutParams().height = r();
        this.aG.setWebViewClient(new ECJiaProductFragment_2(this));
        this.aG.setInitialScale(25);
        WebSettings settings = this.aG.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void s() {
        /*
        r6 = this;
        r0 = com.ecjia.hamster.adapter.n.a();	 Catch:{ Exception -> 0x01df }
        r0 = r0.a;	 Catch:{ Exception -> 0x01df }
        r0 = r0.b;	 Catch:{ Exception -> 0x01df }
        r0 = r0.size();	 Catch:{ Exception -> 0x01df }
        if (r0 <= 0) goto L_0x009a;
    L_0x000e:
        r0 = r6.aI;	 Catch:{ Exception -> 0x01df }
        r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 == 0) goto L_0x009a;
    L_0x0016:
        r0 = r6.D;	 Catch:{ Exception -> 0x01df }
        r1 = r6.aI;	 Catch:{ Exception -> 0x01df }
        r1 = com.ecjia.a.k.c(r1);	 Catch:{ Exception -> 0x01df }
        r0.setText(r1);	 Catch:{ Exception -> 0x01df }
        r0 = r6.aj;	 Catch:{ Exception -> 0x01df }
        r1 = r6.aI;	 Catch:{ Exception -> 0x01df }
        r1 = com.ecjia.a.k.c(r1);	 Catch:{ Exception -> 0x01df }
        r0.setText(r1);	 Catch:{ Exception -> 0x01df }
        r0 = r6.E;	 Catch:{ Exception -> 0x01df }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r1.<init>();	 Catch:{ Exception -> 0x01df }
        r2 = r6.c;	 Catch:{ Exception -> 0x01df }
        r3 = 2131100338; // 0x7f0602b2 float:1.7813055E38 double:1.052903465E-314;
        r2 = r2.getString(r3);	 Catch:{ Exception -> 0x01df }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01df }
        r2 = "";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01df }
        r2 = r6.aJ;	 Catch:{ Exception -> 0x01df }
        r2 = com.ecjia.a.k.c(r2);	 Catch:{ Exception -> 0x01df }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01df }
        r1 = r1.toString();	 Catch:{ Exception -> 0x01df }
        r0.setText(r1);	 Catch:{ Exception -> 0x01df }
        r0 = r6.ak;	 Catch:{ Exception -> 0x01df }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r1.<init>();	 Catch:{ Exception -> 0x01df }
        r2 = r6.c;	 Catch:{ Exception -> 0x01df }
        r3 = 2131100338; // 0x7f0602b2 float:1.7813055E38 double:1.052903465E-314;
        r2 = r2.getString(r3);	 Catch:{ Exception -> 0x01df }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01df }
        r2 = "";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01df }
        r2 = r6.aJ;	 Catch:{ Exception -> 0x01df }
        r2 = com.ecjia.a.k.c(r2);	 Catch:{ Exception -> 0x01df }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x01df }
        r1 = r1.toString();	 Catch:{ Exception -> 0x01df }
        r0.setText(r1);	 Catch:{ Exception -> 0x01df }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r0.<init>();	 Catch:{ Exception -> 0x01df }
        r1 = r6.aI;	 Catch:{ Exception -> 0x01df }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x01df }
        r1 = "";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x01df }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01df }
        r6.T = r0;	 Catch:{ Exception -> 0x01df }
    L_0x0099:
        return;
    L_0x009a:
        r1 = 0;
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.b;	 Catch:{ Exception -> 0x01df }
        r0 = r0.r();	 Catch:{ Exception -> 0x01df }
        r0 = com.ecjia.a.k.a(r0);	 Catch:{ Exception -> 0x01df }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x016f;
    L_0x00ac:
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.b;	 Catch:{ Exception -> 0x01df }
        r0 = r0.g();	 Catch:{ Exception -> 0x01df }
        r2 = com.ecjia.a.k.c(r0);	 Catch:{ Exception -> 0x01df }
        r0 = r6.E;	 Catch:{ Exception -> 0x01df }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r3.<init>();	 Catch:{ Exception -> 0x01df }
        r4 = r6.c;	 Catch:{ Exception -> 0x01df }
        r5 = 2131100338; // 0x7f0602b2 float:1.7813055E38 double:1.052903465E-314;
        r4 = r4.getString(r5);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = "";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = r6.f;	 Catch:{ Exception -> 0x01df }
        r4 = r4.b;	 Catch:{ Exception -> 0x01df }
        r4 = r4.v();	 Catch:{ Exception -> 0x01df }
        r4 = com.ecjia.a.k.e(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01df }
        r0.setText(r3);	 Catch:{ Exception -> 0x01df }
        r0 = r6.ak;	 Catch:{ Exception -> 0x01df }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r3.<init>();	 Catch:{ Exception -> 0x01df }
        r4 = r6.c;	 Catch:{ Exception -> 0x01df }
        r5 = 2131100338; // 0x7f0602b2 float:1.7813055E38 double:1.052903465E-314;
        r4 = r4.getString(r5);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = "";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = r6.f;	 Catch:{ Exception -> 0x01df }
        r4 = r4.b;	 Catch:{ Exception -> 0x01df }
        r4 = r4.v();	 Catch:{ Exception -> 0x01df }
        r4 = com.ecjia.a.k.e(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01df }
        r0.setText(r3);	 Catch:{ Exception -> 0x01df }
    L_0x011a:
        r0 = r6.a;	 Catch:{ Exception -> 0x01df }
        r0 = r0.e();	 Catch:{ Exception -> 0x01df }
        if (r0 == 0) goto L_0x0215;
    L_0x0122:
        r0 = r6.a;	 Catch:{ Exception -> 0x01df }
        r0 = r0.e();	 Catch:{ Exception -> 0x01df }
        r0 = r0.m();	 Catch:{ Exception -> 0x01df }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01df }
        if (r0 != 0) goto L_0x0215;
    L_0x0132:
        r0 = 0;
        r3 = r0;
    L_0x0134:
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.i;	 Catch:{ Exception -> 0x01df }
        r0 = r0.size();	 Catch:{ Exception -> 0x01df }
        if (r3 >= r0) goto L_0x01e5;
    L_0x013e:
        r0 = r6.a;	 Catch:{ Exception -> 0x01df }
        r0 = r0.e();	 Catch:{ Exception -> 0x01df }
        r4 = r0.o();	 Catch:{ Exception -> 0x01df }
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.i;	 Catch:{ Exception -> 0x01df }
        r0 = r0.get(r3);	 Catch:{ Exception -> 0x01df }
        r0 = (com.ecjia.hamster.model.ai) r0;	 Catch:{ Exception -> 0x01df }
        r0 = r0.a();	 Catch:{ Exception -> 0x01df }
        r0 = r4.equals(r0);	 Catch:{ Exception -> 0x01df }
        if (r0 == 0) goto L_0x0217;
    L_0x015c:
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.i;	 Catch:{ Exception -> 0x01df }
        r0 = r0.get(r3);	 Catch:{ Exception -> 0x01df }
        r0 = (com.ecjia.hamster.model.ai) r0;	 Catch:{ Exception -> 0x01df }
        r0 = r0.b();	 Catch:{ Exception -> 0x01df }
    L_0x016a:
        r1 = r3 + 1;
        r3 = r1;
        r1 = r0;
        goto L_0x0134;
    L_0x016f:
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.b;	 Catch:{ Exception -> 0x01df }
        r0 = r0.r();	 Catch:{ Exception -> 0x01df }
        r2 = com.ecjia.a.k.c(r0);	 Catch:{ Exception -> 0x01df }
        r0 = r6.E;	 Catch:{ Exception -> 0x01df }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r3.<init>();	 Catch:{ Exception -> 0x01df }
        r4 = r6.c;	 Catch:{ Exception -> 0x01df }
        r5 = 2131100338; // 0x7f0602b2 float:1.7813055E38 double:1.052903465E-314;
        r4 = r4.getString(r5);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = "";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = r6.f;	 Catch:{ Exception -> 0x01df }
        r4 = r4.b;	 Catch:{ Exception -> 0x01df }
        r4 = r4.g();	 Catch:{ Exception -> 0x01df }
        r4 = com.ecjia.a.k.e(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01df }
        r0.setText(r3);	 Catch:{ Exception -> 0x01df }
        r0 = r6.ak;	 Catch:{ Exception -> 0x01df }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01df }
        r3.<init>();	 Catch:{ Exception -> 0x01df }
        r4 = r6.c;	 Catch:{ Exception -> 0x01df }
        r5 = 2131100338; // 0x7f0602b2 float:1.7813055E38 double:1.052903465E-314;
        r4 = r4.getString(r5);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = "";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r4 = r6.f;	 Catch:{ Exception -> 0x01df }
        r4 = r4.b;	 Catch:{ Exception -> 0x01df }
        r4 = r4.g();	 Catch:{ Exception -> 0x01df }
        r4 = com.ecjia.a.k.e(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01df }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01df }
        r0.setText(r3);	 Catch:{ Exception -> 0x01df }
        goto L_0x011a;
    L_0x01df:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0099;
    L_0x01e5:
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x01df }
        if (r0 != 0) goto L_0x0215;
    L_0x01eb:
        r0 = r6.f;	 Catch:{ Exception -> 0x01df }
        r0 = r0.b;	 Catch:{ Exception -> 0x01df }
        r0 = r0.r();	 Catch:{ Exception -> 0x01df }
        r0 = com.ecjia.a.k.a(r0);	 Catch:{ Exception -> 0x01df }
        r3 = com.ecjia.a.k.a(r1);	 Catch:{ Exception -> 0x01df }
        r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
        if (r0 <= 0) goto L_0x0215;
    L_0x01ff:
        r6.T = r1;	 Catch:{ Exception -> 0x01df }
        r0 = r6.D;	 Catch:{ Exception -> 0x01df }
        r2 = com.ecjia.a.k.e(r1);	 Catch:{ Exception -> 0x01df }
        r0.setText(r2);	 Catch:{ Exception -> 0x01df }
        r0 = r6.aj;	 Catch:{ Exception -> 0x01df }
        r1 = com.ecjia.a.k.e(r1);	 Catch:{ Exception -> 0x01df }
        r0.setText(r1);	 Catch:{ Exception -> 0x01df }
        goto L_0x0099;
    L_0x0215:
        r1 = r2;
        goto L_0x01ff;
    L_0x0217:
        r0 = r1;
        goto L_0x016a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.hamster.activity.goodsdetail.fragment.ECJiaProductFragment.s():void");
    }

    private void t() {
        this.an.setVisibility(0);
        this.ao.setVisibility(8);
        s();
        this.G.setText(this.f.b.i());
        this.F.setVisibility(0);
    }

    private void u() {
        s();
        if (this.f.b.j() == null || this.f.b.j().length() <= 0) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
        } else if (this.aB.equals("立即秒杀")) {
            this.al.setVisibility(0);
            this.am.setVisibility(8);
        } else if (this.aB.equals("即将开始")) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
        }
        if (this.f.b.j() == null || this.f.b.j().length() <= 0) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        Calendar instance = Calendar.getInstance();
        simpleDateFormat.format(instance.getTime());
        long g = com.ecjia.a.ab.g(simpleDateFormat.format(instance.getTime()) + this.f.b.j());
        if (g > 0) {
            this.e.setTime(g);
            this.e.start();
        }
        if (this.aB.equals("立即秒杀")) {
            this.al.setVisibility(0);
            this.am.setVisibility(8);
        } else if (this.aB.equals("即将开始")) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
        }
    }

    private void v() {
        this.an.setVisibility(8);
        this.ao.setVisibility(0);
        s();
        if (this.f.b.j() == null || this.f.b.j().length() <= 0) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
        } else {
            this.al.setVisibility(0);
            this.am.setVisibility(8);
        }
        if (this.f.b.j() == null || this.f.b.j().length() <= 0) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
        } else if (com.ecjia.a.ab.a(this.f.b.j()).length() == 0) {
            this.al.setVisibility(8);
            this.am.setVisibility(0);
        } else {
            long f = com.ecjia.a.ab.f(this.f.b.j());
            if (f > 0) {
                this.e.setTime(f);
                this.e.start();
            }
            this.al.setVisibility(0);
            this.am.setVisibility(8);
        }
    }

    private void w() {
        this.an.setVisibility(0);
        this.ao.setVisibility(8);
        s();
    }

    private void x() {
        v();
    }

    private void y() {
        int i;
        this.q.removeAllViews();
        this.R.setText("(" + this.V.t + this.c.getString(R.string.comment_num) + ")");
        this.ai.setText(Html.fromHtml("<font color=#d8261b>" + ((int) ((((float) this.V.u) / ((float) this.V.t)) * 100.0f)) + "%</font><font color=#333333>" + this.b.getResources().getString(R.string.comment_type_positive) + "</font>"));
        if (this.V.j.size() >= 5) {
            i = 5;
        } else {
            i = this.V.j.size();
        }
        if (i == 0) {
            this.S.setVisibility(8);
            return;
        }
        this.S.setVisibility(0);
        for (int i2 = 0; i2 < i; i2++) {
            ECJiaGoodsCommetView eCJiaGoodsCommetView = (ECJiaGoodsCommetView) LayoutInflater.from(this.b).inflate(R.layout.goods_comment_item, null);
            eCJiaGoodsCommetView.bindData((com.ecjia.hamster.activity.goodsdetail.a.a) this.V.j.get(i2));
            if (i2 == 0 || i == 1) {
                eCJiaGoodsCommetView.setDivliverVisible(8);
            } else {
                eCJiaGoodsCommetView.setDivliverVisible(0);
            }
            this.q.addView(eCJiaGoodsCommetView);
        }
    }

    private void z() {
        Intent intent = new Intent(getActivity(), ECJiaAddressAddActivity.class);
        intent.putExtra("isfirst", true);
        startActivityForResult(intent, 10018);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    private void A() {
        if (this.s == null) {
            this.s = new ab(getActivity());
            this.s.a((a) this);
        }
        if (this.r == null) {
            this.r = new com.ecjia.component.view.i(getActivity(), this.f.b.d());
            this.r.a.a(this);
        }
        this.r.a();
    }

    void d() {
        com.ecjia.hamster.adapter.n.a().b();
        com.ecjia.hamster.adapter.n.a().a = this.f.b;
        if (this.f.b.b != null && this.f.b.b.size() > 0) {
            for (int i = 0; i < this.f.b.b.size(); i++) {
                av avVar = (av) this.f.b.b.get(i);
                if (avVar.b() != null && avVar.b().compareTo(av.a) == 0) {
                    com.ecjia.hamster.adapter.n.a().a((aw) avVar.c.get(0));
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        q.a("isLogin:" + this.U);
        if (this.U) {
            this.U = false;
            if (this.f == null) {
                this.f = new n(getActivity());
                this.f.a((a) this);
            }
            this.f.a(this.aa, this.az, this.aA, true);
        }
    }

    public String e() {
        String string = getResources().getString(R.string.none);
        int i = com.ecjia.hamster.adapter.n.a().b.size() > 0 ? 1 : 0;
        String str = "";
        for (int i2 = 0; i2 < this.f.b.b.size(); i2++) {
            av avVar = (av) this.f.b.b.get(i2);
            String str2 = (str + avVar.a()) + " : ";
            String str3 = "";
            int i3 = 0;
            while (i3 < com.ecjia.hamster.adapter.n.a().b.size()) {
                String str4;
                aw awVar = (aw) com.ecjia.hamster.adapter.n.a().b.get(i3);
                if (avVar.a().compareTo(awVar.a()) == 0) {
                    str4 = (str3 + awVar.e()) + "、";
                } else {
                    str4 = str3;
                }
                i3++;
                str3 = str4;
            }
            if (str3.length() > 0) {
                if (str3.endsWith("、")) {
                    str3 = str3.substring(0, str3.length() - 1);
                }
                str = str2 + str3;
            } else if (i == 0) {
                str = str2;
                for (int i4 = 0; i4 < avVar.c.size(); i4++) {
                    str = str + ((aw) avVar.c.get(i4)).e();
                    if (i4 != avVar.c.size() - 1) {
                        str = str + "、";
                    }
                }
            } else {
                str = str2 + string;
            }
            if (i2 != this.f.b.b.size() - 1) {
                str = str + "\n";
            }
        }
        return str;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.good_category_item:
                b(1);
                return;
            case R.id.goods_comment:
                if (this.z != null) {
                    this.z.b(2);
                    return;
                }
                return;
            case R.id.goodsdetail_redpaper_iv:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    a(10011);
                    return;
                } else {
                    A();
                    return;
                }
            default:
                return;
        }
    }

    void a(int i) {
        startActivityForResult(new Intent(this.b, ECJiaLoginActivity.class), i);
        this.b.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        k kVar = new k(this.b, getResources().getString(R.string.no_login));
        kVar.a(17);
        kVar.a();
    }

    void b(int i) {
        for (int i2 = 0; i2 < this.f.b.b.size(); i2++) {
            av avVar = (av) this.f.b.b.get(i2);
            if (!(avVar.b() == null || avVar.b().compareTo(av.a) != 0 || com.ecjia.hamster.adapter.n.a().a(avVar.a()))) {
                com.ecjia.hamster.adapter.n.a().a((aw) avVar.c.get(0));
            }
        }
        if (this.f.b.m() != null) {
            if (!(this.f == null || this.f.b == null)) {
                com.ecjia.hamster.adapter.n.a().a = this.f.b;
            }
            Intent intent = new Intent(this.b, ECJiaSpecificationActivity.class);
            intent.putExtra("object_id", this.az);
            intent.putExtra("num", Integer.valueOf(this.f.b.m()));
            if (this.f.b.o().size() > 0) {
                intent.putExtra("imgurl", ((ECJia_PHOTO) this.f.b.o().get(0)).getSmall());
            }
            intent.putExtra("goods_id", this.f.b.t() + "");
            intent.putExtra("price", this.T);
            intent.putExtra("store", this.f.b.m());
            intent.putExtra("goods_id", this.f.b.t() + "");
            intent.putExtra("area_id", aa.a(this.b, "location", "area_id"));
            intent.putExtra("promote_limit", this.f.b.b());
            startActivityForResult(intent, 10015);
            this.b.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
            return;
        }
        k kVar = new k(this.b, getResources().getString(R.string.check_the_network));
        kVar.a(17, 0, 0);
        kVar.a();
    }

    public void a(int i, String str) {
        this.Z = i;
        this.s.c(str);
    }

    public void f() {
        this.f.a(this.f.b.t() + "");
    }

    public void g() {
        this.f.b(this.f.b.t() + "");
    }

    private void B() {
        new Thread(new ECJiaProductFragment_3(this)).start();
    }

    private void C() {
        if (!this.g) {
            com.ecjia.a.x.a(this.b, this.f.b);
            this.g = true;
        }
        q.a("保存浏览记录");
    }

    void h(View view) {
        this.u = view.findViewById(R.id.goodsdetail_history);
        this.v = this.u.findViewById(R.id.history_layout_empty);
        this.v.setVisibility(8);
        this.w = (ECJiaFlowLayout) this.u.findViewById(R.id.history_hlistview);
        ((FrameLayout) this.w.getParent()).setMinimumHeight((((int) getResources().getDimension(R.dimen.dp_10)) * 7) + this.x);
        this.v.setMinimumHeight((((int) getResources().getDimension(R.dimen.dp_10)) * 7) + this.x);
    }

    void h() {
        this.w.removeAllViews();
        int size = this.t.size() >= 6 ? 6 : this.t.size();
        if (size == 0) {
            this.v.setVisibility(0);
            this.w.setVisibility(8);
            return;
        }
        this.v.setVisibility(8);
        this.w.setVisibility(0);
        this.w.setBackgroundColor(Color.parseColor("#ffffff"));
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.b).inflate(R.layout.item_goodsdetail_mylove, null);
            RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.goods_image_ll);
            TextView textView = (TextView) linearLayout.findViewById(R.id.goods_name);
            TextView textView2 = (TextView) linearLayout.findViewById(R.id.goods_price);
            ImageLoader.getInstance().displayImage(((p) this.t.get(i)).n().getThumb(), (ImageView) linearLayout.findViewById(R.id.goods_image));
            textView.setText(((p) this.t.get(i)).p());
            if (com.ecjia.a.k.a(((p) this.t.get(i)).s()) == 0.0f) {
                textView2.setText(((p) this.t.get(i)).k());
            } else {
                textView2.setText(((p) this.t.get(i)).s());
            }
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaProductFragment b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.b, ECJiaGoodsDetailActivity.class);
                    intent.putExtra("goods_id", ((p) this.b.t.get(i)).t() + "");
                    this.b.startActivity(intent);
                    this.b.b.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(this.x, this.x));
            LayoutParams layoutParams = new LinearLayout.LayoutParams(this.x, -2);
            layoutParams.setMargins((int) getResources().getDimension(R.dimen.dp_10), (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
            linearLayout.setLayoutParams(layoutParams);
            this.w.addView(linearLayout, layoutParams);
        }
    }

    void i() {
        this.I.removeAllViews();
        this.I.setBackgroundColor(Color.parseColor("#ffffff"));
        for (int i = 0; i < this.f.b.e().size(); i++) {
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.item_goods_detail_active, null);
            TextView textView = (TextView) inflate.findViewById(R.id.content);
            ((TextView) inflate.findViewById(R.id.name)).setText(((ECJia_FAVOUR) this.f.b.e().get(i)).getType_label());
            textView.setText(((ECJia_FAVOUR) this.f.b.e().get(i)).getName());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(y.a(this.b, 15), y.a(this.b, 5), y.a(this.b, 15), y.a(this.b, 5));
            inflate.setLayoutParams(layoutParams);
            this.I.addView(inflate, layoutParams);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10010) {
            if (i2 == -1) {
                this.U = true;
                this.f.a(this.aa + "", this.az, this.aA, false);
            }
        } else if (i == 10011) {
            if (i2 == -1) {
                this.U = true;
                this.aL = true;
            }
        } else if (i == 10012) {
            if (i2 == -1) {
                this.U = true;
                a(false);
            }
        } else if (i == 10013) {
            if (i2 == -1) {
                this.U = true;
                a(true);
            }
        } else if (i == 10014) {
            if (i2 == -1) {
                this.U = true;
                this.b.e();
            }
        } else if (i == 10015) {
            q.a("resultCode:" + i2);
            if (i2 == 10016) {
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    a(10012);
                } else {
                    a(false);
                }
            } else if (i2 == 10017) {
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    a(10013);
                } else {
                    a(true);
                }
            }
            if (TextUtils.isEmpty(e())) {
                this.O.setVisibility(8);
            } else {
                this.O.setVisibility(0);
                this.O.setText(e());
            }
            this.P.setText(com.ecjia.hamster.adapter.n.a().c + "");
        } else if (i == 10018) {
            if (i2 == -1) {
                this.ad.a();
            }
        } else if (i == 10019) {
            if (i2 == -1 && intent != null) {
                this.av.setText(intent.getStringExtra("collect_num") + getResources().getString(R.string.follower_num));
            }
        } else if (i == 2 && intent != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(intent.getStringExtra("country_name") + " ");
            stringBuffer.append(intent.getStringExtra("province_name") + " ");
            stringBuffer.append(intent.getStringExtra("city_name") + " ");
            stringBuffer.append(intent.getStringExtra("county_name"));
            String stringExtra = intent.getStringExtra("city_id");
            this.ag.putString("sendArea", stringBuffer.toString());
            aa.a(this.b, "location", "area_id", stringExtra);
            com.ecjia.hamster.model.i iVar = new com.ecjia.hamster.model.i();
            iVar.a(stringExtra);
            iVar.d(intent.getStringExtra("city_name"));
            try {
                this.ag.putString("localString", iVar.d().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.ag.commit();
            this.ap.setText(stringBuffer.toString());
            de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("refresh_sendarea"));
            this.f.a(this.aa, this.az, this.aA, true);
        }
    }

    void j() {
        Intent intent = new Intent(this.b, ECJiaBalanceActivity.class);
        intent.putExtra("rec_ids", this.ab.w);
        intent.putExtra("address_id", this.ae);
        intent.putExtra("rec_type", this.aA);
        startActivity(intent);
        this.b.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void a(boolean z) {
        if (this.p) {
            new k(this.b, "该商品已下架").a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < com.ecjia.hamster.adapter.n.a().b.size(); i++) {
            arrayList.add(Integer.valueOf(((aw) com.ecjia.hamster.adapter.n.a().b.get(i)).c()));
        }
        if (this.ab == null) {
            this.ab = new ah(this.b);
            this.ab.a((a) this);
        }
        if (this.f.g.booleanValue()) {
            this.ab.a(this.aa, arrayList, com.ecjia.hamster.adapter.n.a().c, this.az, this.aA);
        } else {
            this.ab.a(this.aa, arrayList, com.ecjia.hamster.adapter.n.a().c, this.az, this.aA);
        }
        this.ac = z;
    }

    public void k() {
        Intent intent;
        if (!com.ecjia.consts.a.b) {
            intent = new Intent(this.b, ECJiaConsultActivity.class);
            intent.putExtra("type", "goods_consult");
            intent.putExtra("goods_title", this.f.b.p());
            if (this.aA.equals("GENERAL_GOODS")) {
                intent.putExtra("goods_price", this.f.b.k());
            } else {
                intent.putExtra("goods_price", this.f.b.s());
            }
            intent.putExtra("goods_id", this.f.b.t());
            if (!(this.f.b.o() == null || this.f.b.o().size() == 0)) {
                intent.putExtra("goods_img", ((ECJia_PHOTO) this.f.b.o().get(0)).getThumb());
            }
            startActivity(intent);
        } else if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
            startActivityForResult(new Intent(this.b, ECJiaLoginActivity.class), 12345);
            this.b.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
            k kVar = new k(this.b, getResources().getString(R.string.no_login));
            kVar.a(17);
            kVar.a();
        } else {
            String str = "http://www.missmall.com/mobile/index.php?m=chat&origin=app&openid=" + this.a.e().r() + "&token=" + this.a.e().b() + "&goods_id=" + this.f.b.t();
            intent = new Intent(this.b, ECJiaWebViewActivity.class);
            intent.putExtra("url", str);
            intent.putExtra("title", "");
            startActivity(intent);
            this.b.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if (1000 == bVar.b()) {
            try {
                this.D.setText(com.ecjia.a.k.e(bVar.c()));
                this.aj.setText(com.ecjia.a.k.e(bVar.c()));
                this.E.setText(this.c.getString(R.string.original_price) + "" + com.ecjia.a.k.e(bVar.d()));
                this.ak.setText(this.c.getString(R.string.original_price) + "" + com.ecjia.a.k.e(bVar.d()));
                this.O.setText(e());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(String str, String str2, com.ecjia.hamster.model.ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1895000790:
                if (str.equals("goods/detail")) {
                    z = false;
                    break;
                }
                break;
            case -1569635637:
                if (str.equals("cart/create")) {
                    z = true;
                    break;
                }
                break;
            case 168458797:
                if (str.equals("cart/list")) {
                    z = true;
                    break;
                }
                break;
            case 219725273:
                if (str.equals("address/list")) {
                    z = true;
                    break;
                }
                break;
            case 248307114:
                if (str.equals("goods/desc")) {
                    z = true;
                    break;
                }
                break;
            case 294875250:
                if (str.equals("user/info")) {
                    z = true;
                    break;
                }
                break;
            case 332301381:
                if (str.equals("user/collect/create")) {
                    z = true;
                    break;
                }
                break;
            case 349137140:
                if (str.equals("user/collect/delete")) {
                    z = true;
                    break;
                }
                break;
            case 1366020306:
                if (str.equals("receive/coupon")) {
                    z = true;
                    break;
                }
                break;
            case 1707207783:
                if (str.equals("goods/comment/list")) {
                    z = true;
                    break;
                }
                break;
        }
        int i;
        k kVar;
        switch (z) {
            case false:
                if (axVar.b() == 0) {
                    this.p = true;
                    this.b.a(axVar.d());
                    return;
                }
                String str3;
                ECJia_FAVOUR eCJia_FAVOUR;
                this.aK = new String[this.f.b.b.size()];
                for (i = 0; i < this.f.b.b.size(); i++) {
                    this.aK[i] = ((aw) ((av) this.f.b.b.get(i)).c.get(0)).c() + "";
                }
                for (int i2 = 0; i2 < this.aK.length; i2++) {
                    if (i2 > 0) {
                        this.aH += "|" + this.aK[i2];
                    } else {
                        this.aH += this.aK[i2];
                    }
                }
                i = 0;
                while (i < this.f.b.a.size()) {
                    if (((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getGoods_attr_ids().equals(this.aH)) {
                        if (TextUtils.isEmpty(((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getProduct_promote_price()) || "null".equals(((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getProduct_promote_price())) {
                            this.aI = com.ecjia.a.k.a(((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getProduct_price());
                            this.aJ = com.ecjia.a.k.a(((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getProduct_market_price());
                        } else {
                            this.aI = com.ecjia.a.k.a(((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getProduct_promote_price());
                            this.aJ = com.ecjia.a.k.a(((ECJia_PRODUCT_SPECIFICATION) this.f.b.a.get(i)).getProduct_price());
                        }
                        this.b.f();
                        q();
                        d();
                        this.C.setText(this.f.b.p());
                        this.an.setText("销量：" + this.f.b.a());
                        this.ao.setText("销量：" + this.f.b.a());
                        this.aA = this.f.b.h();
                        q.a("rec_type" + this.aA);
                        str3 = this.aA;
                        z = true;
                        switch (str3.hashCode()) {
                            case -221455202:
                                if (str3.equals("GROUPBUY_GOODS")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 733750743:
                                if (str3.equals("PROMOTE_GOODS")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 925741499:
                                if (str3.equals("MOBILEBUY_GOODS")) {
                                    z = false;
                                    break;
                                }
                                break;
                            case 1003296157:
                                if (str3.equals("SPIKE_GOODS")) {
                                    z = true;
                                    break;
                                }
                                break;
                        }
                        switch (z) {
                            case false:
                                t();
                                break;
                            case true:
                                v();
                                break;
                            case true:
                                x();
                                break;
                            case true:
                                u();
                                break;
                            default:
                                w();
                                break;
                        }
                        if (!(this.f.b.f().equals("0") && this.f.b.u().equals("0"))) {
                            if (this.f.b.u().equals("1")) {
                                eCJia_FAVOUR = new ECJia_FAVOUR();
                                eCJia_FAVOUR.setType_label("包邮");
                                this.f.b.e().add(0, eCJia_FAVOUR);
                            }
                            if (!(TextUtils.isEmpty(this.f.b.f()) || 0.0f == com.ecjia.a.k.a(this.f.b.f()))) {
                                eCJia_FAVOUR = new ECJia_FAVOUR();
                                eCJia_FAVOUR.setType_label("送");
                                eCJia_FAVOUR.setName(this.f.b.f() + getResources().getString(R.string.balance_exp));
                                this.f.b.e().add(0, eCJia_FAVOUR);
                            }
                        }
                        if (this.f.b.e().size() <= 0) {
                            this.H.setVisibility(0);
                            i();
                        } else {
                            this.H.setVisibility(8);
                        }
                        if (this.f.b.d().size() <= 0) {
                            this.L.setVisibility(0);
                        } else {
                            this.L.setVisibility(8);
                        }
                        if (TextUtils.isEmpty(e())) {
                            this.O.setVisibility(0);
                            this.O.setText(e());
                        } else {
                            this.O.setVisibility(8);
                        }
                        this.P.setText(com.ecjia.hamster.adapter.n.a().c + "");
                        this.ah.setText(this.f.f);
                        if (this.f.b.l() != 0) {
                            this.y = false;
                            this.b.e.setImageResource(R.drawable.item_info_collection_disabled_btn);
                        } else {
                            this.y = true;
                            this.b.e.setImageResource(R.drawable.item_info_collection_btn);
                        }
                        if (!this.U) {
                            if (this.f.b.o() != null && this.f.b.o().size() > 0) {
                                q();
                            }
                            this.V.a(this.aa, "all", false);
                        }
                        C();
                        c();
                        n();
                        if (com.ecjia.hamster.adapter.n.a().a != null && com.ecjia.hamster.adapter.n.a().a.q().size() > 0) {
                            this.W = new o(getActivity(), com.ecjia.hamster.adapter.n.a().a.q());
                            this.X.setAdapter(this.W);
                        }
                        if (this.aL) {
                            A();
                            this.aL = false;
                            return;
                        }
                        return;
                    }
                    i++;
                }
                this.b.f();
                q();
                d();
                this.C.setText(this.f.b.p());
                this.an.setText("销量：" + this.f.b.a());
                this.ao.setText("销量：" + this.f.b.a());
                this.aA = this.f.b.h();
                q.a("rec_type" + this.aA);
                str3 = this.aA;
                z = true;
                switch (str3.hashCode()) {
                    case -221455202:
                        if (str3.equals("GROUPBUY_GOODS")) {
                            z = true;
                            break;
                        }
                        break;
                    case 733750743:
                        if (str3.equals("PROMOTE_GOODS")) {
                            z = true;
                            break;
                        }
                        break;
                    case 925741499:
                        if (str3.equals("MOBILEBUY_GOODS")) {
                            z = false;
                            break;
                        }
                        break;
                    case 1003296157:
                        if (str3.equals("SPIKE_GOODS")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        t();
                        break;
                    case true:
                        v();
                        break;
                    case true:
                        x();
                        break;
                    case true:
                        u();
                        break;
                    default:
                        w();
                        break;
                }
                if (this.f.b.u().equals("1")) {
                    eCJia_FAVOUR = new ECJia_FAVOUR();
                    eCJia_FAVOUR.setType_label("包邮");
                    this.f.b.e().add(0, eCJia_FAVOUR);
                }
                eCJia_FAVOUR = new ECJia_FAVOUR();
                eCJia_FAVOUR.setType_label("送");
                eCJia_FAVOUR.setName(this.f.b.f() + getResources().getString(R.string.balance_exp));
                this.f.b.e().add(0, eCJia_FAVOUR);
                if (this.f.b.e().size() <= 0) {
                    this.H.setVisibility(8);
                } else {
                    this.H.setVisibility(0);
                    i();
                }
                if (this.f.b.d().size() <= 0) {
                    this.L.setVisibility(8);
                } else {
                    this.L.setVisibility(0);
                }
                if (TextUtils.isEmpty(e())) {
                    this.O.setVisibility(8);
                } else {
                    this.O.setVisibility(0);
                    this.O.setText(e());
                }
                this.P.setText(com.ecjia.hamster.adapter.n.a().c + "");
                this.ah.setText(this.f.f);
                if (this.f.b.l() != 0) {
                    this.y = true;
                    this.b.e.setImageResource(R.drawable.item_info_collection_btn);
                } else {
                    this.y = false;
                    this.b.e.setImageResource(R.drawable.item_info_collection_disabled_btn);
                }
                if (this.U) {
                    q();
                    this.V.a(this.aa, "all", false);
                    break;
                }
                C();
                c();
                n();
                this.W = new o(getActivity(), com.ecjia.hamster.adapter.n.a().a.q());
                this.X.setAdapter(this.W);
                if (this.aL) {
                    A();
                    this.aL = false;
                    return;
                }
                return;
            case true:
                if (axVar.b() != 1) {
                    return;
                }
                return;
            case true:
                if (axVar.b() == 1) {
                    y();
                    return;
                } else {
                    q.a("=====获取评论失败======");
                    return;
                }
            case true:
                if (axVar.b() != 1) {
                    return;
                }
                if (TextUtils.isEmpty(this.f.t)) {
                    this.h = true;
                    return;
                } else {
                    this.aG.loadDataWithBaseURL(null, this.f.t, "text/html", "utf-8", null);
                    return;
                }
            case true:
                if (axVar.b() != 1) {
                    return;
                }
                if (this.ad.a.size() > 0) {
                    this.ac = true;
                    for (i = 0; i < this.ad.a.size(); i++) {
                        if (((ECJia_ADDRESS) this.ad.a.get(i)).getDefault_address() == 1) {
                            this.ae = ((ECJia_ADDRESS) this.ad.a.get(i)).getId() + "";
                            if (TextUtils.isEmpty(this.ae)) {
                                this.ae = ((ECJia_ADDRESS) this.ad.a.get(0)).getId() + "";
                            }
                            j();
                            return;
                        }
                    }
                    if (TextUtils.isEmpty(this.ae)) {
                        this.ae = ((ECJia_ADDRESS) this.ad.a.get(0)).getId() + "";
                    }
                    j();
                    return;
                }
                final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c(getActivity(), getResources().getString(R.string.point), getResources().getString(R.string.address_add_first));
                cVar.a(2);
                cVar.c(new OnClickListener(this) {
                    final /* synthetic */ ECJiaProductFragment b;

                    public void onClick(View view) {
                        cVar.b();
                    }
                });
                cVar.b(new OnClickListener(this) {
                    final /* synthetic */ ECJiaProductFragment b;

                    public void onClick(View view) {
                        cVar.b();
                        this.b.z();
                    }
                });
                cVar.a();
                return;
            case true:
                q.a("isBuyNow" + this.ac);
                if (axVar.b() == 1) {
                    if (this.ac) {
                        this.ad.a();
                    } else {
                        kVar = new k(getActivity(), (int) R.string.add_to_cart_success);
                        kVar.a(17);
                        kVar.a();
                    }
                    if (!this.aA.equals("GROUPBUY_GOODS") || !this.aA.equals("SPIKE_GOODS")) {
                        this.ab.a(false);
                        return;
                    }
                    return;
                }
                new k(getActivity(), axVar.d()).a();
                return;
            case true:
                if (axVar.b() == 1) {
                    this.b.e.setImageResource(R.drawable.item_info_collection_btn);
                    this.y = true;
                    de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("userinfo_refresh"));
                    kVar = new k(getActivity(), (int) R.string.collection_success);
                    kVar.a(17);
                    kVar.a();
                    return;
                } else if (axVar.c() == 13) {
                    r1 = new k(getActivity(), getResources().getString(R.string.unexist_information));
                    r1.a(17);
                    r1.a();
                    return;
                } else if (axVar.c() == 10007) {
                    r1 = new k(getActivity(), getResources().getString(R.string.collected));
                    r1.a(17);
                    r1.a();
                    return;
                } else {
                    return;
                }
            case true:
                if (axVar.b() == 1) {
                    this.b.e.setImageResource(R.drawable.item_info_collection_disabled_btn);
                    this.y = false;
                    de.greenrobot.event.c.a().c(new com.ecjia.a.a.b("userinfo_refresh"));
                    kVar = new k(getActivity(), (int) R.string.del_collection_success);
                    kVar.a(17);
                    kVar.a();
                    return;
                }
                return;
            case true:
                this.b.d();
                return;
            case true:
                if (axVar.b() == 1) {
                    l lVar = new l(getActivity(), R.string.goodsdetail_getredpaper_secceed);
                    lVar.a(1, 0, 100);
                    lVar.a();
                    ((ECJia_GOODS_COUPON) this.f.b.d().get(this.Z)).setReceived_coupon("1");
                    this.r.a.notifyDataSetChanged();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
