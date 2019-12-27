package br.org.graacc.graaccto.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.api.dto.RegisterDTO;

public class ConsultsViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_nome;
    private TextView tv_data;
    private TextView tv_valor;
    private TextView tv_quantidade;
    private RegisterDTO registerDTO;

    public ConsultsViewHolder(@NonNull View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View itemView) {
        tv_nome = itemView.findViewById(R.id.tv_nome);
        tv_data = itemView.findViewById(R.id.tv_data);
        tv_valor = itemView.findViewById(R.id.tv_valor);
        tv_quantidade = itemView.findViewById(R.id.tv_quantidade);

    }

    public void bind(RegisterDTO registerDTO) {
        if (registerDTO != null) {
            this.registerDTO = registerDTO;
            tv_nome.setText((registerDTO.getNome() != null ? registerDTO.getNome() : "..."));
            tv_data.setText("12/10/2019 19:30:00");
            tv_valor.setText((registerDTO.getValor() != null ? "R$" + registerDTO.getValor() : "..."));
            tv_quantidade.setText((registerDTO.getQuantidade() != null ? registerDTO.getQuantidade() : "..."));
        }
    }

}
