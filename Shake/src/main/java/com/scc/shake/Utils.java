package com.scc.shake;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class Utils {

    public static String getToken(Context context) {
        String url = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo info = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (info != null && info.metaData != null) {
                url = (String) info.metaData.get("FEEDBACK_PHOEBE_TOKEN");
            }
        } catch (PackageManager.NameNotFoundException e) {
            // No metadata found
        }
        return url;
    }
}
