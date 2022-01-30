package com.example.lavapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;
import com.example.lavapp.model.Servicio;

import java.util.List;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {

    List<Servicio> servicios;
    boolean flag;
    OnClickServicioListener onClickServicioListener;
    Context context;

    public ServicioAdapter(List<Servicio> servicios, boolean flag) {
        this.servicios = servicios;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_service,parent, false);
        context = parent.getContext();
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        holder.name.setText(servicios.get(position).getRecibe());
        holder.address.setText(servicios.get(position).getDireccion());
        holder.city.setText(servicios.get(position).getCiudad());
        holder.neighbor.setText(servicios.get(position).getBarrio());
        holder.name.setOnClickListener(v -> {
            if(onClickServicioListener != null)
                onClickServicioListener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }

    public void setOnClickServicioListener(OnClickServicioListener onClickServicioListener) {
        this.onClickServicioListener = onClickServicioListener;
    }

    class ServicioViewHolder extends RecyclerView.ViewHolder{
        TextView name, address, city, neighbor;
        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ver_receptor_servicio);
            address = itemView.findViewById(R.id.ver_direccion_servicio);
            city = itemView.findViewById(R.id.ver_ciudad_servicio);
            neighbor = itemView.findViewById(R.id.ver_barrio_servicio);
        }
    }

    public interface OnClickServicioListener{
        void onClick(int position);
    }
}
