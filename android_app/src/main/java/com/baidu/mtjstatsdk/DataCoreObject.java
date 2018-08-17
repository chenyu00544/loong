package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.android.bba.common.util.CommonParam;
import com.baidu.mtjstatsdk.b.d;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataCoreObject {
    int a = 0;
    JSONObject b = new JSONObject();
    JSONArray c = new JSONArray();
    JSONArray d = new JSONArray();
    JSONArray e = new JSONArray();
    JSONArray f = new JSONArray();
    boolean g = false;
    HeadObject h = new HeadObject();
    private int i = 0;

    int a() {
        return this.i;
    }

    String a(Context context) {
        if (this.h.getCuid() == null) {
            this.h.setCuid(BasicStoreTools.getInstance().loadGenerateDeviceCUID(context));
            if (this.h.getCuid() == null || "".equalsIgnoreCase(this.h.getCuid())) {
                try {
                    this.h.setCuid(CommonParam.getCUID(context));
                    BasicStoreTools.getInstance().setGenerateDeviceCUID(context, this.h.getCuid());
                } catch (Exception e) {
                    d.c("statsdk", e.getMessage());
                }
            }
        }
        return this.h.getCuid();
    }

    void a(int i) {
        this.i = i;
    }
}
