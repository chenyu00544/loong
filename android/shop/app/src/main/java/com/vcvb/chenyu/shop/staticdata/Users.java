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
                    .sex_title, R.string.account_title, R.string.phone_title, R.string
                    .real_title, R.string.interested_title};
            Integer[] subStrs = new Integer[]{R.string.logo_subtitle, R.string.nick_subtitle, R
                    .string.sex_subtitle, R.string.account_subtitle, R.string.phone_subtitle, R
                    .string.real_subtitle, R.string.interested_subtitle};
            Integer[] types = new Integer[]{2, 1, 1, 1, 1, 1, 1};
            for (int i = 0; i < strs.length; i++) {
                HashMap<String, Integer> hm = new HashMap<>();
                hm.put("type", types[i]);
                hm.put("title", strs[i]);
                hm.put("subtitle", subStrs[i]);
                add(hm);
            }
        }
    };
}
