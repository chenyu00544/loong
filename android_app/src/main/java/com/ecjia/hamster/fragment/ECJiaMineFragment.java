package com.ecjia.hamster.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.d;
import com.ecjia.a.k;
import com.ecjia.a.u;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.a.q;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.component.view.ECJiaScrollView_Main;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.ECJiaTopView.TitleType;
import com.ecjia.component.view.c;
import com.ecjia.hamster.activity.ECJiaAccountActivity;
import com.ecjia.hamster.activity.ECJiaAddressManageActivity;
import com.ecjia.hamster.activity.ECJiaChangePasswordActivity;
import com.ecjia.hamster.activity.ECJiaCollectActivity;
import com.ecjia.hamster.activity.ECJiaCustomercenterActivity;
import com.ecjia.hamster.activity.ECJiaFeedbackActivity;
import com.ecjia.hamster.activity.ECJiaHelpListActivity;
import com.ecjia.hamster.activity.ECJiaLastBrowseActivity;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.activity.ECJiaMyPurseActivity;
import com.ecjia.hamster.activity.ECJiaOrderListActivity;
import com.ecjia.hamster.activity.ECJiaOrderListAllActivity;
import com.ecjia.hamster.activity.ECJiaRedpapperListActivity;
import com.ecjia.hamster.activity.ECJiaSettingActivity;
import com.ecjia.hamster.activity.ECJiaShareQRCodeActivity;
import com.ecjia.hamster.activity.ECJiaShopCollectActivity;
import com.ecjia.hamster.activity.ECJiaWebViewActivity;
import com.ecjia.hamster.adapter.ag;
import com.ecjia.hamster.adapter.bu;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.activity.ECJiaReturnOrderListActivity;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;

public class ECJiaMineFragment extends ECJiaBaseFragment implements OnClickListener, a {
    private TextView A;
    private LinearLayout B;
    private TextView C;
    private TextView D;
    private TextView E;
    private LinearLayout F;
    private LinearLayout G;
    private LinearLayout H;
    private LinearLayout I;
    private TextView J;
    private TextView K;
    private Resources L;
    private FrameLayout M;
    private LinearLayout N;
    private LinearLayout O;
    private LinearLayout P;
    private LinearLayout Q;
    private LinearLayout R;
    private ECJiaMyListView S;
    private ag T;
    private TextView U;
    private LinearLayout V;
    private SharedPreferences W;
    private LinearLayout X;
    private TextView Y;
    private LinearLayout Z;
    private LinearLayout aa;
    private q ab;
    private bu ac;
    public ImageView d;
    al e;
    ECJiaScrollView_Main f;
    ECJiaTopView g;
    int h = 200;
    ImageView i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private TextView n;
    private LinearLayout o;
    private TextView p;
    private LinearLayout q;
    private TextView r;
    private LinearLayout s;
    private LinearLayout t;
    private LinearLayout u;
    private LinearLayout v;
    private LinearLayout w;
    private TextView x;
    private TextView y;
    private TextView z;

    class ECJiaMineFragment_1 implements ECJiaScrollView_Main.a {
        final /* synthetic */ ECJiaMineFragment a;

        ECJiaMineFragment_1(ECJiaMineFragment eCJiaMineFragment) {
            this.a = eCJiaMineFragment;
        }

        public void a(int i, int i2, int i3, int i4) {
            if (i2 == 0) {
                this.a.g.getBackground().setAlpha(0);
                this.a.i.setEnabled(false);
                this.a.i.setVisibility(4);
                this.a.g.getTitleTextView().setVisibility(4);
            } else if (i2 >= this.a.h) {
                this.a.g.getBackground().setAlpha(255);
                this.a.i.setEnabled(true);
                this.a.i.setVisibility(0);
                this.a.g.getTitleTextView().setVisibility(0);
            } else if (i2 < this.a.h) {
                int floatValue = (int) ((new Float((float) i2).floatValue() / new Float((float) this.a.h).floatValue()) * 250.0f);
                this.a.g.getBackground().setAlpha(floatValue);
                this.a.i.getDrawable().setAlpha(floatValue);
                if (i2 > 100) {
                    this.a.i.setEnabled(true);
                    this.a.i.setVisibility(0);
                    this.a.g.getTitleTextView().setVisibility(0);
                    return;
                }
                this.a.i.setEnabled(false);
                this.a.i.setVisibility(4);
                this.a.g.getTitleTextView().setVisibility(4);
            }
        }
    }

    class ECJiaMineFragment_2 implements OnClickListener {
        final /* synthetic */ ECJiaMineFragment a;

        ECJiaMineFragment_2(ECJiaMineFragment eCJiaMineFragment) {
            this.a = eCJiaMineFragment;
        }

        public void onClick(View view) {
            this.a.b.a.open();
        }
    }

    class ECJiaMineFragment_3 implements OnClickListener {
        final /* synthetic */ ECJiaMineFragment a;

        ECJiaMineFragment_3(ECJiaMineFragment eCJiaMineFragment) {
            this.a = eCJiaMineFragment;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a.getActivity(), ECJiaSettingActivity.class));
            this.a.getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.W = this.b.getSharedPreferences(Constants.KEY_USER_ID, 0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.j = layoutInflater.inflate(R.layout.fragment_mine, viewGroup, false);
        d();
        return this.j;
    }

    public void onResume() {
        super.onResume();
        if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
            this.k.setVisibility(8);
            this.l.setVisibility(0);
            c();
        } else {
            this.k.setVisibility(0);
            this.l.setVisibility(8);
            a();
            if (this.e == null) {
                this.e = new al(getActivity());
                this.e.a((a) this);
            }
            this.e.a();
        }
        b();
        if (this.a.d() != null) {
            this.J.setText(this.a.d().d());
            this.K.setText(this.a.d().e());
        }
    }

    public void onPause() {
        super.onPause();
        if (this.e != null) {
            this.e.b(this);
        }
    }

    private void c() {
        this.m.setText("");
        this.n.setText("");
        this.d.setImageResource(R.drawable.profile_no_avarta_icon);
        this.i.setImageResource(R.drawable.profile_no_avarta_icon);
        this.p.setText("0");
        this.U.setText("0");
        this.x.setText("");
        this.x.setVisibility(8);
        this.y.setText("");
        this.y.setVisibility(8);
        this.z.setText("");
        this.z.setVisibility(8);
        this.A.setText("");
        this.A.setVisibility(8);
        this.Y.setText("");
        this.Y.setVisibility(8);
        this.C.setText("￥0.00");
        this.D.setText("0");
        this.E.setText("0");
    }

    public void a() {
        if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
            c();
            return;
        }
        this.m.setText(this.a.e().p());
        this.n.setText(this.a.e().o());
        if (u.a().a(this.a.e().m())) {
            this.d.setImageBitmap(u.a().b(this.a.e().m()));
            this.i.setImageBitmap(u.a().b(this.a.e().m()));
        } else {
            this.d.setImageResource(R.drawable.profile_no_avarta_icon_light);
            this.i.setImageResource(R.drawable.profile_no_avarta_icon_light);
        }
        this.C.setText("￥" + k.c(this.a.e().i()));
        this.D.setText(this.a.e().k());
        this.E.setText(this.a.e().j());
        this.p.setText(this.a.e().l() + "");
        this.U.setText(this.a.e().e() + "");
        if (this.a.e().n() != null) {
            this.x.setText(this.a.e().n().e() + "");
            if (this.a.e().n().e() > 0) {
                if (this.a.e().n().e() > 99) {
                    this.x.setText("99+");
                }
                this.x.setVisibility(0);
            } else {
                this.x.setVisibility(8);
            }
            this.y.setText(this.a.e().n().d() + "");
            if (this.a.e().n().d() > 0) {
                if (this.a.e().n().d() > 99) {
                    this.y.setText("99+");
                }
                this.y.setVisibility(0);
            } else {
                this.y.setVisibility(8);
            }
            this.z.setText(this.a.e().n().c() + "");
            if (this.a.e().n().c() > 0) {
                if (this.a.e().n().c() > 99) {
                    this.z.setText("99+");
                }
                this.z.setVisibility(0);
            } else {
                this.z.setVisibility(8);
            }
            this.A.setText(this.a.e().n().b() + "");
            if (this.a.e().n().b() > 0) {
                if (this.a.e().n().b() > 99) {
                    this.A.setText("99+");
                }
                this.A.setVisibility(0);
            } else {
                this.A.setVisibility(8);
            }
            this.Y.setText(this.a.e().n().a() + "");
            if (this.a.e().n().a() > 0) {
                if (this.a.e().n().a() > 99) {
                    this.Y.setText("99+");
                }
                this.Y.setVisibility(0);
                return;
            }
            this.Y.setVisibility(8);
        }
    }

    @TargetApi(16)
    private void d() {
        this.L = getActivity().getResources();
        this.f = (ECJiaScrollView_Main) this.j.findViewById(R.id.main_sc);
        this.f.setOnScrollListener(new ECJiaMineFragment_1(this));
        this.g = (ECJiaTopView) this.j.findViewById(R.id.mine_topview);
        this.g.setLeftType(1);
        this.g.setTopViewBackground(R.color.public_theme_color_normal_2);
        this.g.getBackground().setAlpha(0);
        this.g.setLeftBackImage((int) R.drawable.icon_main_list_white, new ECJiaMineFragment_2(this));
        this.g.setRightType(12);
        this.g.setRightImage((int) R.drawable.profile_refresh_site_icon2, new ECJiaMineFragment_3(this));
        this.g.setTitleType(TitleType.TEXT);
        this.g.setTitleText((int) R.string.main_mine);
        this.g.getTitleTextView().setVisibility(4);
        this.i = (ImageView) this.g.findViewById(R.id.user_image_top);
        this.i.setVisibility(8);
        this.i.setOnClickListener(this);
        this.M = (FrameLayout) this.j.findViewById(R.id.mine_head);
        this.M.getLayoutParams().width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        this.M.getLayoutParams().height = (getActivity().getWindowManager().getDefaultDisplay().getWidth() * Constants.SDK_VERSION_CODE) / 480;
        this.k = (LinearLayout) this.j.findViewById(R.id.mine_user);
        this.d = (ImageView) this.j.findViewById(R.id.mine_user_image);
        this.m = (TextView) this.j.findViewById(R.id.mine_user_name);
        this.n = (TextView) this.j.findViewById(R.id.mine_user_level);
        this.l = (TextView) this.j.findViewById(R.id.mine_login);
        this.j.findViewById(R.id.mine_lottery).setOnClickListener(this);
        this.o = (LinearLayout) this.j.findViewById(R.id.mine_collection_goods);
        this.p = (TextView) this.j.findViewById(R.id.mine_collection_goods_num);
        this.V = (LinearLayout) this.j.findViewById(R.id.mine_collection_shop);
        this.U = (TextView) this.j.findViewById(R.id.mine_collection_shop_num);
        this.q = (LinearLayout) this.j.findViewById(R.id.mine_history);
        this.r = (TextView) this.j.findViewById(R.id.mine_history_num);
        this.s = (LinearLayout) this.j.findViewById(R.id.mine_order_ll);
        this.t = (LinearLayout) this.j.findViewById(R.id.mine_order_waitpay);
        this.u = (LinearLayout) this.j.findViewById(R.id.mine_order_waitship);
        this.v = (LinearLayout) this.j.findViewById(R.id.mine_order_shipped);
        this.w = (LinearLayout) this.j.findViewById(R.id.mine_order_finished);
        this.X = (LinearLayout) this.j.findViewById(R.id.mine_order_back);
        this.x = (TextView) this.j.findViewById(R.id.mine_order_waitpay_num);
        this.y = (TextView) this.j.findViewById(R.id.mine_order_waitship_num);
        this.z = (TextView) this.j.findViewById(R.id.mine_order_shipped_num);
        this.A = (TextView) this.j.findViewById(R.id.mine_order_finished_num);
        this.Y = (TextView) this.j.findViewById(R.id.mine_order_back_num);
        this.B = (LinearLayout) this.j.findViewById(R.id.mine_wallet);
        this.N = (LinearLayout) this.j.findViewById(R.id.mine_wallet_balance_ll);
        this.O = (LinearLayout) this.j.findViewById(R.id.mine_wallet_redpaper_ll);
        this.P = (LinearLayout) this.j.findViewById(R.id.mine_wallet_integral_ll);
        this.C = (TextView) this.j.findViewById(R.id.mine_wallet_balance);
        this.D = (TextView) this.j.findViewById(R.id.mine_wallet_redpaper);
        this.E = (TextView) this.j.findViewById(R.id.mine_wallet_integral);
        this.F = (LinearLayout) this.j.findViewById(R.id.mine_address);
        this.Q = (LinearLayout) this.j.findViewById(R.id.myfind_suggest);
        this.R = (LinearLayout) this.j.findViewById(R.id.mine_help);
        this.G = (LinearLayout) this.j.findViewById(R.id.mine_changepassword);
        this.H = (LinearLayout) this.j.findViewById(R.id.mine_official_service);
        this.J = (TextView) this.j.findViewById(R.id.mine_official_phone);
        this.I = (LinearLayout) this.j.findViewById(R.id.mine_official_website);
        this.K = (TextView) this.j.findViewById(R.id.mine_official_siteurl);
        this.Z = (LinearLayout) this.j.findViewById(R.id.mine_ceremony);
        this.aa = (LinearLayout) this.j.findViewById(R.id.mine_Extension);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.V.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.X.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.N.setOnClickListener(this);
        this.O.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.F.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.Z.setOnClickListener(this);
        this.aa.setOnClickListener(this);
        this.S = (ECJiaMyListView) this.j.findViewById(R.id.fragment_mine_help_list);
        this.ab = new q(this.b);
        this.ab.a((a) this);
        this.ab.b();
        this.T = new ag(this.b, this.a.f());
        this.S.setAdapter(this.T);
        this.f.smoothScrollTo(0, 0);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void b() {
        this.r.setText(e() + "");
    }

    private int e() {
        this.ac = new bu(getActivity());
        int i = 0;
        while (this.ac.a().moveToNext()) {
            i++;
        }
        return i;
    }

    public void onClick(View view) {
        Intent intent;
        com.ecjia.component.view.k kVar;
        switch (view.getId()) {
            case R.id.mine_user:
                if (this.a.e() != null && !TextUtils.isEmpty(this.a.e().m())) {
                    startActivity(new Intent(getActivity(), ECJiaCustomercenterActivity.class));
                    return;
                }
                return;
            case R.id.mine_login:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                return;
            case R.id.mine_lottery:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaWebViewActivity.class);
                intent.putExtra("url", this.a.e().d());
                startActivity(intent);
                return;
            case R.id.mine_collection_goods:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaCollectActivity.class));
                return;
            case R.id.mine_collection_shop:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaShopCollectActivity.class));
                return;
            case R.id.mine_history:
                startActivity(new Intent(getActivity(), ECJiaLastBrowseActivity.class));
                return;
            case R.id.mine_order_ll:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaOrderListAllActivity.class);
                intent.putExtra("order_type", "all");
                startActivity(intent);
                return;
            case R.id.mine_order_waitpay:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaOrderListActivity.class);
                intent.putExtra("order_type", "await_pay");
                startActivity(intent);
                return;
            case R.id.mine_order_waitship:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaOrderListActivity.class);
                intent.putExtra("order_type", "await_ship");
                startActivity(intent);
                return;
            case R.id.mine_order_shipped:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaOrderListActivity.class);
                intent.putExtra("order_type", "shipped");
                startActivity(intent);
                return;
            case R.id.mine_order_finished:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaOrderListActivity.class);
                intent.putExtra("order_type", "allow_comment");
                startActivity(intent);
                return;
            case R.id.mine_order_back:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(getActivity(), ECJiaReturnOrderListActivity.class);
                intent.putExtra("order_type", "allow_comment");
                startActivity(intent);
                return;
            case R.id.mine_wallet:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaMyPurseActivity.class));
                return;
            case R.id.mine_wallet_balance_ll:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaAccountActivity.class));
                return;
            case R.id.mine_wallet_redpaper_ll:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else if (Integer.valueOf(this.a.e().k()).intValue() > 0) {
                    startActivity(new Intent(getActivity(), ECJiaRedpapperListActivity.class));
                    return;
                } else {
                    new com.ecjia.component.view.k(getActivity(), "暂无可用红包").a();
                    return;
                }
            case R.id.mine_address:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaAddressManageActivity.class));
                return;
            case R.id.mine_changepassword:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaChangePasswordActivity.class));
                return;
            case R.id.mine_Extension:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    getActivity().startActivityForResult(new Intent(this.b, ECJiaLoginActivity.class), 259);
                    return;
                } else {
                    startActivity(new Intent(this.b, ECJiaShareQRCodeActivity.class));
                    return;
                }
            case R.id.mine_ceremony:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(this.b, ECJiaLoginActivity.class), 1);
                    this.b.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                intent = new Intent(this.b, ECJiaWebViewActivity.class);
                intent.putExtra("url", this.a.e().d());
                startActivity(intent);
                return;
            case R.id.myfind_suggest:
                startActivity(new Intent(getActivity(), ECJiaFeedbackActivity.class));
                return;
            case R.id.mine_help:
                startActivity(new Intent(getActivity(), ECJiaHelpListActivity.class));
                return;
            case R.id.mine_official_service:
                if (TextUtils.isEmpty(this.J.getText().toString())) {
                    kVar = new com.ecjia.component.view.k(getActivity(), this.L.getString(R.string.setting_call_cannot_empty));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                final c cVar = new c(getActivity(), this.L.getString(R.string.setting_call_or_not), this.a.d().d());
                cVar.a(2);
                cVar.b(new OnClickListener(this) {
                    final /* synthetic */ ECJiaMineFragment b;

                    public void onClick(View view) {
                        cVar.b();
                        this.b.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + this.b.a.d().d())));
                    }
                });
                cVar.c(new OnClickListener(this) {
                    final /* synthetic */ ECJiaMineFragment b;

                    public void onClick(View view) {
                        cVar.b();
                    }
                });
                cVar.a();
                return;
            case R.id.mine_official_website:
                if (!d.a(getActivity()) || this.a.d() == null) {
                    kVar = new com.ecjia.component.view.k(getActivity(), this.L.getString(R.string.goodlist_network_problem));
                    kVar.a(17, 0, 0);
                    kVar.a();
                    return;
                }
                String string = this.L.getString(R.string.setting_website);
                Intent intent2 = new Intent(getActivity(), ECJiaWebViewActivity.class);
                intent2.putExtra("url", this.a.d().e());
                intent2.putExtra("title", string);
                startActivity(intent2);
                return;
            case R.id.user_image_top:
                if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                    startActivityForResult(new Intent(getActivity(), ECJiaLoginActivity.class), 1);
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                }
                startActivity(new Intent(getActivity(), ECJiaCustomercenterActivity.class));
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onEvent(b bVar) {
        if (bVar.c().equals("USER_PHOTO_DOWNLOAD_SUCCESS")) {
            this.d.setImageBitmap(u.a().b(this.a.e().m()));
            this.i.setImageBitmap(u.a().b(this.a.e().m()));
        }
        if (bVar.c().equals("USER_CHANGE_PHOTO")) {
            this.d.setImageBitmap(u.a().b(this.a.e().m()));
            this.i.setImageBitmap(u.a().b(this.a.e().m()));
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/info")) {
            if (this.a.e() == null || TextUtils.isEmpty(this.a.e().m())) {
                this.k.setVisibility(8);
                this.l.setVisibility(0);
                c();
                return;
            }
            this.k.setVisibility(0);
            this.l.setVisibility(8);
            a();
        } else if (str.equals("shop/help") && axVar.b() == 1) {
            this.T.notifyDataSetChanged();
            this.f.smoothScrollTo(0, 0);
        }
    }
}
