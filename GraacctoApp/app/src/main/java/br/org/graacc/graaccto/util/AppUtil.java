package br.org.graacc.graaccto.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public abstract class AppUtil {

    public static void openSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

}
