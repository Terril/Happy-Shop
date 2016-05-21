package com.happyshop.helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * Created by terril on 14/7/14.
 */
public class NetworkHelper {

    public static boolean isOnline(Context cxt) {
        ConnectivityManager cm = (ConnectivityManager) cxt
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isAvailable() && netInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public static void noNetworkToast(Activity activity) {
        Toast.makeText(activity, "No Internet Connection",Toast.LENGTH_SHORT).show();
    }
}
