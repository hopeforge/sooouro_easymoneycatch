package br.org.graacc.graaccto.validation;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import br.org.graacc.graaccto.util.MessageUtil;

public abstract class LoginValidation {

    public static boolean isValid(Context context, EditText et_email, EditText et_password) {
        if (TextUtils.isEmpty(et_email.getText().toString()) && TextUtils.isEmpty(et_password.getText().toString())) {
            MessageUtil.showToast(context, "Preencha os campos");
            return false;
        }
        if (!TextUtils.isEmpty(et_password.getText().toString()) && TextUtils.isEmpty(et_email.getText().toString())) {
            MessageUtil.showToast(context, "Email obrigatório");
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString()) && TextUtils.isEmpty(et_email.getText().toString())) {
            MessageUtil.showToast(context, "Senha obrigatório");
            return false;
        }
        return true;
    }

}
