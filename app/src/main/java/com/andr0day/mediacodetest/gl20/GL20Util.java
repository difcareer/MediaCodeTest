package com.andr0day.mediacodetest.gl20;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

public class GL20Util {

    public static boolean hasGLES20(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return info.reqGlEsVersion >= 0x20000;
    }
}
