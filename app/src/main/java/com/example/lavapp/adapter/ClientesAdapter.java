package com.example.lavapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavapp.R;
import com.example.lavapp.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClienteViewHolder>{

    private List<Cliente> clientes;
    boolean flag;
    OnClickClienteListener onClickClienteListener;
    Context context;

    public ClientesAdapter(List<Cliente> clientes, boolean flag) {
        this.clientes = clientes;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_client,parent, false);
        context = parent.getContext();
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.name.setText(clientes.get(position).getNombre());
        holder.lastname.setText(clientes.get(position).getApellido());
        holder.mail.setText(clientes.get(position).getEmail());
        holder.cellphone.setText(clientes.get(position).getCelular());
        holder.name.setOnClickListener(v -> {
            if(onClickClienteListener != null)
                onClickClienteListener.onClick(position);
        });
        holder.name.setCompoundDrawablesWithIntrinsicBounds(null,null,(flag ? context.getResources().getDrawable(R.drawable.ic_baseline_delete_24, context.getTheme()) : null),null);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public void setOnClickClienteListener(OnClickClienteListener onClickClienteListener) {
        this.onClickClienteListener = onClickClienteListener;
    }

    class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView name, lastname, cellphone, mail;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ver_nombre_cliente);
            lastname = itemView.findViewById(R.id.ver_apellido_cliente);
            cellphone = itemView.findViewById(R.id.ver_email_cliente);
            mail = itemView.findViewById(R.id.ver_celular_cliente);

        }
    }

    public interface OnClickClienteListener{
        void onClick(int position);
    }
}
