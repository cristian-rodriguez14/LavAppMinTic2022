package com.example.lavapp.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;

public class ServicioViewHolder extends RecyclerView.ViewHolder{

    public TextView recibe, direccion, ciudad, barrio;

    public ServicioViewHolder(@NonNull View itemView) {
        super(itemView);
        recibe = (TextView) itemView.findViewById(R.id.ver_receptor_servicio);
        direccion = (TextView) itemView.findViewById(R.id.ver_direccion_servicio);
        ciudad = (TextView) itemView.findViewById(R.id.ver_ciudad_servicio);
        barrio = (TextView) itemView.findViewById(R.id.ver_barrio_servicio);
    }
}
