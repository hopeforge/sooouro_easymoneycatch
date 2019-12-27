package br.org.graacc.graaccto.ui.main.ui.account;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.ui.signin.SignInActivity;
import br.org.graacc.graaccto.util.PreferencesUtil;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private Toolbar toolbar;
    private Button btn_email;
    private TextView tv_email;
    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        btn_email = root.findViewById(R.id.btn_email);
        tv_email = root.findViewById(R.id.tv_email);
        toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitle("Minha conta");

        btn_email.setOnClickListener((v) -> {
            logout();
        });

        if (PreferencesUtil.isLoggedIn(getContext())) {
            tv_email.setText(PreferencesUtil.getUser(getContext()).getLogin());
        }

        return root;
    }

    public void logout() {
        new AlertDialog.Builder(getContext())
                .setTitle("Sair?")
                .setMessage("Será necessário um novo login.")
                .setPositiveButton("Sim", (dialog, which) -> {
                    getActivity().runOnUiThread(() -> {
                        this.dialog = ProgressDialog.show(getContext(), "", "Saindo...", true);
                    });
                    new Thread(() -> {
                        PreferencesUtil.setUser(null, getContext());
                        Intent intent = new Intent(getContext(), SignInActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        getActivity().runOnUiThread(() -> {
                            this.dialog.dismiss();
                        });
                    }).start();
                })
                .setNegativeButton("Não", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }


}