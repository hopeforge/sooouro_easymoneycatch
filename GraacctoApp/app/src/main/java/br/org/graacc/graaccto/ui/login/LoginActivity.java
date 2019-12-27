package br.org.graacc.graaccto.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import java.io.IOException;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.api.connection.ApiConnection;
import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.domain.User;
import br.org.graacc.graaccto.ui.main.MainActivity;
import br.org.graacc.graaccto.util.ConnectionUtil;
import br.org.graacc.graaccto.util.MessageUtil;
import br.org.graacc.graaccto.util.PreferencesUtil;
import br.org.graacc.graaccto.validation.LoginValidation;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        findViews();
    }

    private void findViews() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
    }

    public void login(View view) {
        if (!ConnectionUtil.isConnected(this)) {
            MessageUtil.showSnackbar(view, "Sem conexão");
            return;
        }

        if (LoginValidation.isValid(this, et_email, et_password)) {
            dialog = ProgressDialog.show(this, "", "Aguarde...", true);

            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            //  REQUEST
            new Thread(() -> {
                try {
                    Call<User> request = ApiConnection.getConnection().signin(email, password);
                    final User response = request.execute().body();
                    if (response != null && response.getId() != null) {
                        runOnUiThread(() -> {
                            Log.d(Constants.TAG, "" + response);
                            PreferencesUtil.setUser(response, this);
                            updateUI(response);
                        });
                    } else {
                        runOnUiThread(() -> {
                            dialog.dismiss();
                            MessageUtil.showToast(this, "Usuário/Senha inválidos");
                        });
                    }
                } catch (IOException e) {
                    runOnUiThread(() -> {
                        Log.d(Constants.TAG, "error - " + e.getMessage());
                        updateUI(null);
                    });
                }
            }).start();
        }
    }

    private void updateUI(User user) {
        if (user != null) {
            if (dialog != null) {
                dialog.dismiss();
            }
            MessageUtil.showToast(this, "Entrou como: \n" + user.getLogin());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        } else {
            if (dialog != null) {
                dialog.dismiss();
            }
            MessageUtil.showToast(this, "Usuário/Senha inválidos");
        }
    }

}
