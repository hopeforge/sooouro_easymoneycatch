package br.org.graacc.graaccto.ui.thankful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.api.connection.ApiConnection;
import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.core.QRCodeController;
import br.org.graacc.graaccto.domain.Thankful;
import br.org.graacc.graaccto.domain.User;
import br.org.graacc.graaccto.permission.RuntimePermission;
import br.org.graacc.graaccto.ui.qrcode.QRCodeActivity;
import br.org.graacc.graaccto.ui.success.SuccessActivity;
import br.org.graacc.graaccto.util.MessageUtil;
import br.org.graacc.graaccto.util.PreferencesUtil;
import retrofit2.Call;

public class ThankfulActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView iv_qrcode;
    private ProgressBar pb_progress;
    private TextView tv_wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankful);

        findViews();

        toolbar.setTitle("Solicitar Grato");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        iv_qrcode = findViewById(R.id.iv_qrcode);
        iv_qrcode.setOnClickListener((event) -> {
            if (RuntimePermission.checkCameraPermission(this)) {
                Intent intent = new Intent(this, QRCodeActivity.class);
                startActivity(intent);
            } else {
                RuntimePermission.requestCameraPermission(this, this);
            }
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            iv_qrcode.startAnimation(animFadein);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (QRCodeController.getInstance().getValue() != null) {
            String id = QRCodeController.getInstance().getValue();
            Log.d(Constants.TAG, "Result: " + id);
            pb_progress.setVisibility(View.VISIBLE);
            tv_wait.setVisibility(View.VISIBLE);
            sendThankful(id);

            QRCodeController.getInstance().setValue(null);
        } else {
            pb_progress.setVisibility(View.GONE);
            tv_wait.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.REQUEST_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, QRCodeActivity.class);
                startActivity(intent);
            }
        }
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        pb_progress = findViewById(R.id.pb_progress);
        tv_wait = findViewById(R.id.tv_wait);
    }

    private void sendThankful(String id) {
        Thankful grato = new Thankful();
        User usuario = new User();
        User empresa = new User();
        usuario.setId(PreferencesUtil.getUser(this).getId());
        empresa.setId(Long.parseLong(id));
        grato.setUsuario(usuario);
        grato.setEmpresa(empresa);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        grato.setData(dateFormat.format(new Date()));

        new Thread(() -> {
            try {
                Call<Thankful> request = ApiConnection.getConnection().salvarGrato(grato);
                Thankful response = request.execute().body();
                if (response != null) {
                    runOnUiThread(() -> {
                        Log.d(Constants.TAG, "" + response);
                        pb_progress.setVisibility(View.GONE);
                        tv_wait.setVisibility(View.GONE);
                        Intent intent = new Intent(this, SuccessActivity.class);
                        startActivity(intent);
                        finish();
                    });
                } else {
                    runOnUiThread(() -> {
                        MessageUtil.showToast(this, "Falha, tente novamente");
                        pb_progress.setVisibility(View.GONE);
                        tv_wait.setVisibility(View.GONE);
                    });
                }
            } catch (IOException e) {
                runOnUiThread(() -> {
                    MessageUtil.showToast(this, "Erro, " + e.getMessage());
                    pb_progress.setVisibility(View.GONE);
                    tv_wait.setVisibility(View.GONE);
                    Log.d(Constants.TAG, "error - " + e.getMessage());
                });
            }
        }).start();
    }
}
