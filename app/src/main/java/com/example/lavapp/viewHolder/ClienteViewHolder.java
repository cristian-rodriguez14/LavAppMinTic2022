package com.example.lavapp.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;

public class ClienteViewHolder extends RecyclerView.ViewHolder {

    public TextView name, lastname, celphone, mail;

    public ClienteViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.ver_nombre_cliente);
        lastname = (TextView) itemView.findViewById(R.id.ver_apellido_cliente);
        celphone = (TextView) itemView.findViewById(R.id.ver_email_cliente);
        mail = (TextView) itemView.findViewById(R.id.ver_celular_cliente);
    }
}
