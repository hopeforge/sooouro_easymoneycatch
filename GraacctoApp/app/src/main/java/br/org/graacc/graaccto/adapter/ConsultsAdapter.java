package br.org.graacc.graaccto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.api.dto.RegisterDTO;
import br.org.graacc.graaccto.viewholder.ConsultsViewHolder;

public class ConsultsAdapter extends RecyclerView.Adapter<ConsultsViewHolder> {

    private Context context;
    private List<RegisterDTO> list;

    public ConsultsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ConsultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gratos, parent, false);
        return new ConsultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultsViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return (list != null && list.size() > 0) ? list.size() : 0;
    }

    public void setAlerts(List<RegisterDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
