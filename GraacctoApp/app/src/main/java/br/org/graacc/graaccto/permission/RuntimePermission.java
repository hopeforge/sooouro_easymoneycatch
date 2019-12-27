package br.org.graacc.graaccto.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.util.AppUtil;
import br.org.graacc.graaccto.util.MessageUtil;
import br.org.graacc.graaccto.util.PreferencesUtil;

public class RuntimePermission {

    public static boolean checkCameraPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestCameraPermission(Context context, Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            new AlertDialog.Builder(activity)
                    .setTitle("Permissão necessária")
                    .setMessage("Essa permissão é necessária para a leitura do QR Code")
                    .setPositiveButton("OK", (dialog, which) -> {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, Constants.REQUEST_CODE_CAMERA);
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .create()
                    .show();
        } else if (!PreferencesUtil.checkPermissionPreference(context, "camera")) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, Constants.REQUEST_CODE_CAMERA);
            PreferencesUtil.updatePermission(context, "camera");
        } else {
            MessageUtil.showToast(context, "É necessário habilitar a permissão manualmente");
            AppUtil.openSettings(context);
        }
    }

}
