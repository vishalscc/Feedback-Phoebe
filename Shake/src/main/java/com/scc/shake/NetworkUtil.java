package com.scc.shake;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;


public final class NetworkUtil {

    public static boolean isInternetConnect(@NonNull Context context) {
        return isConnectedMobileData(context) || isConnectedWifi(context) || isConnectedRoaming(context);
    }

    public static boolean isConnectedMobileData(@NonNull Context context) {
        final NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static boolean isConnectedRoaming(@NonNull Context context) {
        final NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnected() && info.isRoaming() && info.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static boolean isConnectedWifi(@NonNull Context context) {
        final NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    private static NetworkInfo getNetworkInfo(@NonNull Context context) {
        return getConnectivityManager(context).getActiveNetworkInfo();
    }

    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
    }

}
