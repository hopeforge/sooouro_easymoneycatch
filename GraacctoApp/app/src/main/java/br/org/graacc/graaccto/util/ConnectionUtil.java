package br.org.graacc.graaccto.util;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import br.org.graacc.graaccto.core.Constants;

public abstract class ConnectionUtil {

    public static boolean isConnected(Context context) {
        try {
            int[] networkTypes = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
            for (int typeNetwork : networkTypes) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(typeNetwork);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.d(Constants.TAG, "ConnectionUtil error - " + e.getMessage());
            return false;
        }
        return false;
    }

}
