package br.org.graacc.graaccto.ui.main.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.ui.consults.ConsultsActivity;
import br.org.graacc.graaccto.ui.donate.DonateActivity;
import br.org.graacc.graaccto.ui.thankful.ThankfulActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final CardView card_thankful = root.findViewById(R.id.card_thankful);
        card_thankful.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ThankfulActivity.class);
            startActivity(intent);
        });

        final CardView card_search = root.findViewById(R.id.card_search);
        card_search.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ConsultsActivity.class);
            startActivity(intent);
        });

        final CardView card_donate = root.findViewById(R.id.card_donate);
        card_donate.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DonateActivity.class);
            startActivity(intent);
        });
        return root;
    }
}