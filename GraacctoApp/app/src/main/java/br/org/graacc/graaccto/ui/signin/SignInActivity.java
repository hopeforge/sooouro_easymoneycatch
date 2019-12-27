package br.org.graacc.graaccto.ui.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.ui.login.LoginActivity;
import br.org.graacc.graaccto.ui.main.MainActivity;
import br.org.graacc.graaccto.ui.signup.SignUpActivity;
import br.org.graacc.graaccto.util.PreferencesUtil;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferencesUtil.isLoggedIn(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_sign_in);
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
