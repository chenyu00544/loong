package com.ecjia.hamster.fragment.homefragment.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.ecjia.a.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.af;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.ECJiaLoginActivity;
import com.ecjia.hamster.adapter.y;
import com.ecjia.hamster.fragment.ECJiaBaseFragment;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.c;

@SuppressLint({"ValidFragment"})
public class ECJiaShopListFragment extends ECJiaBaseFragment implements a, ECJiaXListView.a {
    private View d;
    private ECJiaXListView e;
    private FrameLayout f;
    private y g;
    private af h;
    private SharedPreferences i;
    private Handler j;
    private int k = -1;
    private String l = "0";

    class ECJiaShopListFragment_1 extends Handler {
        final /* synthetic */ ECJiaShopListFragment a;

        ECJiaShopListFragment_1(ECJiaShopListFragment eCJiaShopListFragment) {
            this.a = eCJiaShopListFragment;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.k = message.arg1;
                this.a.h.c(((ao) this.a.g.a().get(message.arg1)).e());
                c.a().c(new b("add_collect_seller", ((ao) this.a.g.a().get(message.arg1)).e()));
            }
            if (message.what == 2) {
                this.a.k = message.arg1;
                this.a.h.d(((ao) this.a.g.a().get(message.arg1)).e());
                c.a().c(new b("minus_collect_seller", ((ao) this.a.g.a().get(message.arg1)).e()));
            }
        }
    }

    @SuppressLint({"ValidFragment"})
    public ECJiaShopListFragment(String str) {
        this.l = str;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.a().a((Object) this);
    }

    @TargetApi(12)
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.d.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.d);
            }
        } else {
            this.d = layoutInflater.inflate(R.layout.fragment_other_shop, null);
            this.i = getActivity().getSharedPreferences(Constants.KEY_USER_ID, 0);
            a(this.d);
            if (this.h == null) {
                this.h = new af(getActivity());
                this.h.a((a) this);
            }
        }
        return this.d;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ShopList");
        this.h.a(this.l);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ShopList");
    }

    private void a(View view) {
        if (this.h == null) {
            this.h = new af(getActivity());
        }
        this.j = new ECJiaShopListFragment_1(this);
        this.e = (ECJiaXListView) view.findViewById(R.id.shoplist_xlist);
        this.e.setXListViewListener(this, 1);
        this.e.setRefreshTime();
        this.e.setPullLoadEnable(false);
        this.e.setPullRefreshEnable(true);
        this.g = new y(getActivity(), this.h.a, a());
        this.g.a = this.j;
        this.e.setAdapter(this.g);
        this.h.a((a) this);
        this.f = (FrameLayout) view.findViewById(R.id.null_pager);
        this.h.j();
    }

    public void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
        if ("collectrefresh".equals(bVar.c()) && this.h != null) {
            this.h.a(this.l);
        }
    }

    private void b() {
        if (this.h.a.size() != 0) {
            this.g.a(this.h.a);
        }
        this.g.notifyDataSetChanged();
    }

    public int a() {
        return Math.min(getActivity().getWindowManager().getDefaultDisplay().getWidth(), getActivity().getWindowManager().getDefaultDisplay().getHeight()) - (((int) getResources().getDimension(R.dimen.eight_margin)) * 2);
    }

    public void a(int i) {
        this.h.a(this.l);
    }

    public void b(int i) {
        this.h.b(this.l);
    }

    public void a(String str, String str2, ax axVar) {
        boolean z = true;
        switch (str.hashCode()) {
            case -2073837039:
                if (str.equals("seller/collect/create")) {
                    z = true;
                    break;
                }
                break;
            case -2057001280:
                if (str.equals("seller/collect/delete")) {
                    z = true;
                    break;
                }
                break;
            case 1203407150:
                if (str.equals("seller/list")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (axVar.b() == 1) {
                    this.e.setRefreshTime();
                    this.e.stopRefresh();
                    this.e.stopLoadMore();
                    if (this.h.e.a() == 1) {
                        this.e.setPullLoadEnable(true);
                    } else {
                        this.e.setPullLoadEnable(false);
                    }
                    b();
                    return;
                }
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                return;
            case true:
                if (axVar.b() == 1) {
                    new k(getActivity(), getResources().getString(R.string.collection_success)).a();
                    ((ao) this.g.a().get(this.k)).a("1");
                    ((ao) this.g.a().get(this.k)).a(Integer.valueOf(((ao) this.g.a().get(this.k)).g().intValue() + 1));
                    this.g.notifyDataSetChanged();
                    return;
                } else if (axVar.c() == 100) {
                    startActivity(new Intent(getActivity(), ECJiaLoginActivity.class));
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else {
                    new k(getActivity(), axVar.d()).a();
                    return;
                }
            case true:
                if (axVar.b() == 1) {
                    new k(getActivity(), getResources().getString(R.string.del_collection_success)).a();
                    ((ao) this.g.a().get(this.k)).a("0");
                    ((ao) this.g.a().get(this.k)).a(Integer.valueOf(((ao) this.g.a().get(this.k)).g().intValue() - 1));
                    this.g.notifyDataSetChanged();
                    return;
                } else if (axVar.c() == 100) {
                    startActivity(new Intent(getActivity(), ECJiaLoginActivity.class));
                    getActivity().overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
                    return;
                } else {
                    new k(getActivity(), axVar.d()).a();
                    return;
                }
            default:
                return;
        }
    }
}
