package br.org.graacc.graaccto.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.api.connection.ApiConnection;
import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.domain.User;
import br.org.graacc.graaccto.ui.main.MainActivity;
import br.org.graacc.graaccto.util.ConnectionUtil;
import br.org.graacc.graaccto.util.MessageUtil;
import br.org.graacc.graaccto.util.PreferencesUtil;
import br.org.graacc.graaccto.validation.SignUpValidation;
import retrofit2.Call;

public class SignUpActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        findViews();
    }

    private void findViews() {
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
    }

    public void register(View view) {
        if (!ConnectionUtil.isConnected(this)) {
            MessageUtil.showSnackbar(view, "Sem conexão");
            return;
        }

        if (SignUpValidation.isValid(this, et_name, et_email, et_password)) {
            dialog = ProgressDialog.show(this, "", "Aguarde...", true);

            String name = et_name.getText().toString();
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            User user = new User();
            user.setLogin(email);
            user.setNome(name);
            user.setEmail(email);
            user.setSenha(password);
            user.setTipo(2);

            new Thread(() -> {
                try {
                    Call<User> request = ApiConnection.getConnection().signup(user);
                    User response = request.execute().body();
                    if (response != null) {
                        runOnUiThread(() -> {
                            Log.d(Constants.TAG, "" + response);
                            PreferencesUtil.setUser(response, this);
                            updateUI(response);
                        });
                    } else {
                        runOnUiThread(() -> {
                            updateUI(null);
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
            MessageUtil.showToast(SignUpActivity.this, "Erro ao cadastrar, tente novamente");
        }
    }

}
