package com.baidu.mapapi.a.a;

import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.platform.comapi.NativeLoader;

public class a {
    static {
        if (VersionInfo.getApiVersion().equals(b.a())) {
            NativeLoader.getInstance().loadLibrary(b.b());
            return;
        }
        throw new BaiduMapSDKException("the version of util is not match with base");
    }
}
