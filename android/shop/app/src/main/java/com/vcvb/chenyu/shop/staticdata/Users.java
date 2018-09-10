package com.vcvb.chenyu.shop.staticdata;

import com.vcvb.chenyu.shop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Users {
    public final static List<HashMap<String, Integer>> users = new ArrayList<HashMap<String,
            Integer>>() {
        {
            Integer[] strs = new Integer[]{R.string.logo_title, R.string.nick_title, R.string
                    .sex_title, R.string.account_title, R.string.real_title/*, R.string
                    .interested_title*/};
            Integer[] subStrs = new Integer[]{R.string.logo_subtitle, R.string.nick_subtitle, R
                    .string.sex_subtitle, R.string.account_subtitle, R.string.real_subtitle, /*R
                    .string.interested_subtitle*/};
            Integer[] types = new Integer[]{2, 1, 1, 1, 1,/* 1*/};
            for (int i = 0; i < strs.length; i++) {
                HashMap<String, Integer> hm = new HashMap<>();
                hm.put("type", types[i]);
                hm.put("title", strs[i]);
                hm.put("subtitle", subStrs[i]);
                add(hm);
            }
        }
    };

    public final static List<HashMap<String, Integer>> realname = new ArrayList<HashMap<String,
            Integer>>() {
        {
            Integer[] strs = new Integer[]{R.string.card_info, R.string.card_name, R.string
                    .card_num, R.string.card, R.string.card, R.string.why};
            Integer[] types = new Integer[]{2, 1, 1, 2, 3, 4};
            for (int i = 0; i < strs.length; i++) {
                HashMap<String, Integer> hm = new HashMap<>();
                hm.put("type", types[i]);
                hm.put("title", strs[i]);
                add(hm);
            }
        }
    };

    public final static List<HashMap<String, Integer>> accounts = new ArrayList<HashMap<String,
            Integer>>() {
        {
            Integer[] strs = new Integer[]{R.string.main_account, R.string.phone_account, R
                    .string.account_subtitle, R.string.email_account, R.string.wechat, R.string.qq};
            Integer[] subStrs = new Integer[]{R.string.main_account, R.string.change_phone, R
                    .string.account_subtitle, R.string.no_bind, R.string.no_bind, R.string.no_bind};
            Integer[] icons = new Integer[]{R.drawable.icon_phone_a, R.drawable.icon_phone_a, R
                    .drawable.icon_phone_a, R.drawable.icon_email_gray, R.drawable
                    .icon_wechat_gray, R.drawable.icon_qq_gray};
            Integer[] icons_a = new Integer[]{R.drawable.icon_phone_a, R.drawable.icon_phone_a, R
                    .drawable.icon_phone_a, R.drawable.icon_email_a, R.drawable.icon_wechat, R
                    .drawable.icon_qq};
            Integer[] types = new Integer[]{2, 1, 2, 1, 1, 1};
            for (int i = 0; i < strs.length; i++) {
                HashMap<String, Integer> hm = new HashMap<>();
                hm.put("type", types[i]);
                hm.put("title", strs[i]);
                hm.put("subtitle", subStrs[i]);
                hm.put("icon", icons[i]);
                hm.put("icon_a", icons_a[i]);
                add(hm);
            }
        }
    };
}
