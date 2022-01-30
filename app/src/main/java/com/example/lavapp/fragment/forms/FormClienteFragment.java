package com.example.lavapp.fragment.forms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lavapp.R;
import com.example.lavapp.adapter.ClientesAdapter;
import com.example.lavapp.model.Cliente;
import com.example.lavapp.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FormClienteFragment extends Fragment {

    private Button guardarCliente;
    private TextInputEditText nombreCliente, apellidoCliente, telefonoCliente, emailCliente, idCliente;
    private ImageButton deleteIcon;
    private boolean isEdit=false;
    ClientesAdapter clienteAdapter;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //String position;
    /*private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;*/

    List<Cliente> clientes;

    Cliente clienteSelected;

    public FormClienteFragment() {
        // Required empty public constructor
    }

    public static FormClienteFragment newInstance(String param1, String param2) {
        FormClienteFragment fragment = new FormClienteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_cliente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        events();
        setup();
    }

    private void setup() {
        initFirebase();
    }

    private void saveCliente() {
        clienteSelected = new Cliente(nombreCliente.getText().toString(),
                apellidoCliente.getText().toString(),
                telefonoCliente.getText().toString(),
                emailCliente.getText().toString(),
                idCliente.getText().toString());
        DatabaseReference services = databaseReference.child("Cliente");
        String uidService = services.push().getKey();
        clienteSelected.setUid(uidService);
        services.child(uidService).setValue(clienteSelected).addOnCompleteListener(task -> {
            if(!task.isSuccessful()){
                Toast.makeText(getContext(),"Error al guardar el Cliente",Toast.LENGTH_SHORT).show();
                return;
            }
            //Limpiar la ui
            limpiarCajas();

            //Ir al inicio
            Toast.makeText(getContext(),"Se guardo exitosamente", Toast.LENGTH_LONG).show();
        });
    }

    private void limpiarCajas() {
        nombreCliente.setText("");
        apellidoCliente.setText("");
        telefonoCliente.setText("");
        emailCliente.setText("");
        idCliente.setText("");
    }

    private void events() {
        guardarCliente.setOnClickListener(v->{
            if(!validate(v).isEmpty()){
                Toast.makeText(getContext(),validate(v),Toast.LENGTH_SHORT).show();
                return;
            }
            saveCliente();
            Navigation.findNavController(v).navigate(R.id.from_cliente_to_orden);
        });
    }

    private String validate(View v) {
        if(nombreCliente.getText().toString().isEmpty()){
            return "El nombre no puede estar vacio";
        } else if(apellidoCliente.getText().toString().isEmpty()){
            return "El apellido no puede estar vacio";
        } else if(telefonoCliente.getText().toString().isEmpty()){
            return "El teléfono no puede estar vacio";
        } else if(emailCliente.getText().toString().isEmpty()){
            return "El email no puede estar vacio";
        } else if(idCliente.getText().toString().isEmpty()){
            return "El número de identificación no puede estar vacio";
        }
        return "";
    }

    private void init(View view) {
        //boton
        guardarCliente = view.findViewById(R.id.btn_guardar_cliente);

        //TextView
        nombreCliente = view.findViewById(R.id.txt_nombre_cliente);
        apellidoCliente = view.findViewById(R.id.txt_apellido_cliente);
        telefonoCliente = view.findViewById(R.id.txt_celular_cliente);
        emailCliente = view.findViewById(R.id.txt_email_cliente);
        idCliente = view.findViewById(R.id.txt_id_cliente);

        //List
        clientes = new ArrayList<>();

        //Adapter
        clienteAdapter = new ClientesAdapter(clientes,true);
        // deleteIcon = view.findViewById(R.id.delete_icon);
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}