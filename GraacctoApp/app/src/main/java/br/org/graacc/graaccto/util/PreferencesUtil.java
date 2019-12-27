package br.org.graacc.graaccto.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.domain.User;

import static android.content.Context.MODE_PRIVATE;

public abstract class PreferencesUtil {

    public static void setUser(User user, Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        Gson gson = new Gson();
        prefEditor.putString(Constants.PREF_KEY_USER, gson.toJson(user));
        prefEditor.apply();
    }

    public static User getUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String json = pref.getString(Constants.PREF_KEY_USER, null);
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public static boolean isLoggedIn(Context context) {
        return (getUser(context) != null);
    }

    public static boolean checkPermissionPreference(Context context, String permission) {
        boolean isShow = false;
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        switch (permission) {
            case "camera":
                isShow = pref.getBoolean(Constants.PREF_KEY_CAMERA, false);
                break;
        }
        return isShow;
    }

    public static void updatePermission(Context context, String permission) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        switch (permission) {
            case "camera":
                prefEditor.putBoolean(Constants.PREF_KEY_CAMERA, true);
                prefEditor.apply();
                break;
        }
    }

}
