package br.org.graacc.graaccto.ui.donate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.adapter.ConsultsAdapter;
import br.org.graacc.graaccto.api.connection.ApiConnection;
import br.org.graacc.graaccto.api.dto.RegisterDTO;
import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.domain.Donation;
import br.org.graacc.graaccto.domain.User;
import br.org.graacc.graaccto.ui.success.SuccessActivity;
import br.org.graacc.graaccto.util.MessageUtil;
import br.org.graacc.graaccto.util.PreferencesUtil;
import retrofit2.Call;

public class DonateActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_total;
    private EditText et_message;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        findViews();

        toolbar.setTitle("Doar");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    public void donate(View view) {
        dialog = ProgressDialog.show(this, "", "Aguarde...", true);
        new Thread(() -> {
            try {
                Long id = PreferencesUtil.getUser(this).getId();
                Donation donation = new Donation();
                User usuario = new User();
                usuario.setId(id);
                donation.setUsuario(usuario);

                Call<Donation> request = ApiConnection.getConnection().salvarDoacao(donation);
                Donation response = request.execute().body();
                if (response != null && response.getId() != null) {
                    runOnUiThread(() -> {
                        dialog.dismiss();
                        Intent intent = new Intent(this, SuccessActivity.class);
                        startActivity(intent);
                        finish();
                    });
                } else {
                    runOnUiThread(() -> {
                        dialog.dismiss();
                        MessageUtil.showToast(this, "Falha, tente novamente");
                    });
                }
            } catch (IOException e) {
                runOnUiThread(() -> {
                    dialog.dismiss();
                    MessageUtil.showToast(this, "Erro, " + e.getMessage());
                    Log.d(Constants.TAG, "error - " + e.getMessage());
                });
            }
        }).start();
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        tv_total = findViewById(R.id.tv_total);
        et_message = findViewById(R.id.et_message);
    }
}
