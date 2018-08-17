package com.baidu.mapapi.search.core;

import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.platform.comapi.NativeLoader;

public class i {
    static {
        if (VersionInfo.getApiVersion().equals(p.a())) {
            NativeLoader.getInstance().loadLibrary(p.b());
            return;
        }
        throw new BaiduMapSDKException("the version of search is not match with base");
    }
}
