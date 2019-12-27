package br.org.graacc.graaccto.ui.consults;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.io.IOException;
import java.util.List;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.adapter.ConsultsAdapter;
import br.org.graacc.graaccto.api.connection.ApiConnection;
import br.org.graacc.graaccto.api.dto.RegisterDTO;
import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.util.PreferencesUtil;
import retrofit2.Call;

public class ConsultsActivity extends AppCompatActivity {

    private RecyclerView rv_registers;
    private ConsultsAdapter adapter;
    private ProgressDialog dialog;
    private LinearLayout layout_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consults);
        findViews();
        getGratos();
    }

    private void findViews() {
        layout_empty = findViewById(R.id.layout_empty);
        rv_registers = findViewById(R.id.rv_registers);
    }

    private void getGratos() {
        dialog = ProgressDialog.show(this, "", "Aguarde...", true);
        new Thread(() -> {
            try {
                String id = String.valueOf(PreferencesUtil.getUser(this).getId());
                Call<List<RegisterDTO>> request = ApiConnection.getConnection().getGratos(Integer.parseInt(id));
                List<RegisterDTO> response = request.execute().body();
                if (response != null && response.size() > 0) {
                    runOnUiThread(() -> {
                        dialog.dismiss();
                        rv_registers.setVisibility(View.VISIBLE);
                        layout_empty.setVisibility(View.GONE);
                        Log.d(Constants.TAG, "" + response);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                        adapter = new ConsultsAdapter(this);
                        adapter.setAlerts(response);

                        rv_registers.setLayoutManager(layoutManager);
                        rv_registers.addItemDecoration(new DividerItemDecoration(rv_registers.getContext(), DividerItemDecoration.VERTICAL));
                        rv_registers.setAdapter(adapter);
                    });
                } else {
                    runOnUiThread(() -> {
                        dialog.dismiss();
                        rv_registers.setVisibility(View.GONE);
                        layout_empty.setVisibility(View.VISIBLE);
                    });
                }
            } catch (IOException e) {
                runOnUiThread(() -> {
                    dialog.dismiss();
                    rv_registers.setVisibility(View.GONE);
                    layout_empty.setVisibility(View.VISIBLE);
                    Log.d(Constants.TAG, "error - " + e.getMessage());
                });
            }
        }).start();
    }

    private void getDoacoes() {
        dialog = ProgressDialog.show(this, "", "Aguarde...", true);
        new Thread(() -> {
            try {
                String id = String.valueOf(PreferencesUtil.getUser(this).getId());
                Call<List<RegisterDTO>> request = ApiConnection.getConnection().getDoacoes(Integer.parseInt(id));
                List<RegisterDTO> response = request.execute().body();
                if (response != null && response.size() > 0) {
                    runOnUiThread(() -> {
                        dialog.dismiss();
                        rv_registers.setVisibility(View.VISIBLE);
                        layout_empty.setVisibility(View.GONE);
                        Log.d(Constants.TAG, "" + response);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                        adapter = new ConsultsAdapter(this);
                        adapter.setAlerts(response);

                        rv_registers.setLayoutManager(layoutManager);
                        rv_registers.addItemDecoration(new DividerItemDecoration(rv_registers.getContext(), DividerItemDecoration.VERTICAL));
                        rv_registers.setAdapter(adapter);
                    });
                } else {
                    runOnUiThread(() -> {
                        dialog.dismiss();
                        rv_registers.setVisibility(View.GONE);
                        layout_empty.setVisibility(View.VISIBLE);
                    });
                }
            } catch (IOException e) {
                runOnUiThread(() -> {
                    dialog.dismiss();
                    rv_registers.setVisibility(View.GONE);
                    layout_empty.setVisibility(View.VISIBLE);
                    Log.d(Constants.TAG, "error - " + e.getMessage());
                });
            }
        }).start();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_grato:
                if (checked)
                    getGratos();
                break;
            case R.id.rb_docao:
                if (checked)
                    getDoacoes();
                break;
        }
    }
}
