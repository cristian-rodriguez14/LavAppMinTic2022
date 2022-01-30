package com.example.lavapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;
import com.example.lavapp.model.Precio;

import java.util.List;

public class PrecioAdapter extends RecyclerView.Adapter<PrecioAdapter.PrecioViewHolder> {

    List<Precio> precios;
    boolean flag;
    PrecioAdapter.OnClickPrecioListener onClickPrecioListener;
    Context context;

    public PrecioAdapter(List<Precio> precios, boolean flag) {
        this.precios = precios;
        this.flag = flag;
    }

    @NonNull
    @Override
    public PrecioAdapter.PrecioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_price,parent, false);
        context = parent.getContext();
        return new PrecioAdapter.PrecioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrecioAdapter.PrecioViewHolder holder, int position) {
        holder.name.setText(precios.get(position).getProducto());
        holder.price.setText(precios.get(position).getPrecio());
        holder.name.setOnClickListener(v -> {
            if(onClickPrecioListener != null)
                onClickPrecioListener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return precios.size();
    }

    public void setOnClickPrecioListener(PrecioAdapter.OnClickPrecioListener onClickPrecioListener) {
        this.onClickPrecioListener = onClickPrecioListener;
    }

    class PrecioViewHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        public PrecioViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ver_nombre_producto);
            price = itemView.findViewById(R.id.ver_precio_producto);
        }
    }

    public interface OnClickPrecioListener{
        void onClick(int position);
    }
}
