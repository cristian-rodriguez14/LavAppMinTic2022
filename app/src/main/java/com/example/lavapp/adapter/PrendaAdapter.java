package com.example.lavapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;
import com.example.lavapp.model.Prenda;

import java.util.List;

public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder> {

    List<Prenda> prendas;
    boolean flag;
    PrendaAdapter.OnClickPrendaListener onClickPrendaListener;
    Context context;

    public PrendaAdapter(List<Prenda> prendas, boolean flag) {
        this.prendas = prendas;
        this.flag = flag;
    }

    @NonNull
    @Override
    public PrendaAdapter.PrendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_clothe,parent, false);
        context = parent.getContext();
        return new PrendaAdapter.PrendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrendaAdapter.PrendaViewHolder holder, int position) {
        holder.name.setText(prendas.get(position).getCaracteristica());
        holder.price.setText(prendas.get(position).getPrecioPrenda());
        holder.quantity.setText(prendas.get(position).getCantidad());
        holder.name.setOnClickListener(v -> {
            if(onClickPrendaListener != null)
                onClickPrendaListener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return prendas.size();
    }

    public void setOnClickPrendaListener(PrendaAdapter.OnClickPrendaListener onClickPrendaListener) {
        this.onClickPrendaListener = onClickPrendaListener;
    }

    class PrendaViewHolder extends RecyclerView.ViewHolder{
        TextView name, price, quantity;
        public PrendaViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ver_caracteristica_ropa);
            price = itemView.findViewById(R.id.ver_precio_ropa);
            quantity = itemView.findViewById(R.id.ver_cantidad_ropa);
        }
    }

    public interface OnClickPrendaListener{
        void onClick(int position);
    }
}
