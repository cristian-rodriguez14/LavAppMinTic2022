package com.example.lavapp.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;

public class PrendaViewHolder extends RecyclerView.ViewHolder{

    public TextView caracteristica, cantidad;

    public PrendaViewHolder(@NonNull View itemView) {
        super(itemView);
        caracteristica = (TextView) itemView.findViewById(R.id.ver_caracteristica_ropa);
        cantidad = (TextView) itemView.findViewById(R.id.ver_cantidad_ropa);
    }
}
