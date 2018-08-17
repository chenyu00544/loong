package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.StringUtils;

public class UTDevice {
    public static String getUtdid(Context context) {
        Device device = DeviceInfo.getDevice(context);
        if (device == null || StringUtils.isEmpty(device.getUtdid())) {
            return "ffffffffffffffffffffffff";
        }
        return device.getUtdid();
    }
}
