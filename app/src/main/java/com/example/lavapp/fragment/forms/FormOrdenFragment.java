package com.example.lavapp.fragment.forms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lavapp.R;
import com.example.lavapp.adapter.ClientesAdapter;
import com.example.lavapp.adapter.PrendaAdapter;
import com.example.lavapp.adapter.ServicioAdapter;
import com.example.lavapp.model.Orden;
import com.example.lavapp.model.Servicio;
import com.example.lavapp.model.Cliente;
import com.example.lavapp.model.Prenda;
import com.example.lavapp.viewModel.ClienteViewModel;
import com.example.lavapp.viewModel.PrecioViewModel;
import com.example.lavapp.viewModel.PrendaViewModel;
import com.example.lavapp.viewModel.ServicioViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FormOrdenFragment extends Fragment {

    private static final String TAG = "ordenForm";
    //Inputs del Fragmento
    TextInputEditText clienteOrden, servicioOrden, prendaOrden, descripcionOrden, entregaOrden;
    TextInputLayout montoOrden;
    Integer sumaPrecio=0;
    Button registerOrden, nuevoCliente, nuevaPrenda;

    //Recycler del fragmento
    RecyclerView recyclerClientes, recyclerServicios, recyclerPrendas;

    //Adaptadores
    ClientesAdapter clientesAdapter;
    ServicioAdapter servicioAdapter;
    PrendaAdapter prendaAdapter;

    //Data
    Orden newOrden;
    List<Cliente> clientes;
    List<Servicio> servicios;
    List<Prenda> prendas;
    
    //ViewModel
    ServicioViewModel servicioViewModel;
    ClienteViewModel clienteViewModel;
    PrendaViewModel prendaViewModel;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference rootServicio;
    DatabaseReference rootCliente;
    DatabaseReference rootPrenda;
    DatabaseReference databaseReference;

    Orden ordenSelected;

    public FormOrdenFragment() {
        // Required empty public constructor
    }

    public static FormOrdenFragment newInstance(String param1, String param2) {
        FormOrdenFragment fragment = new FormOrdenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_form_orden, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        events();
        setup();
        // primerContenedor();
        // segundoContenedor();
        // tercerContenedor();
        

    }

    private void setup() {
        database = FirebaseDatabase.getInstance();
        rootServicio = database.getReference("Servicio");
        rootCliente = database.getReference("Cliente");
        rootPrenda = database.getReference("Prenda");

        recyclerClientes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerClientes.setAdapter(clientesAdapter);

        recyclerServicios.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerServicios.setAdapter(servicioAdapter);

        recyclerPrendas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPrendas.setAdapter(prendaAdapter);

        clienteViewModel = new ViewModelProvider(requireActivity()).get(ClienteViewModel.class);
        servicioViewModel = new ViewModelProvider(requireActivity()).get(ServicioViewModel.class);
        prendaViewModel = new ViewModelProvider(requireActivity()).get(PrendaViewModel.class);
        
        loadDataClientes();
        loadDataServicios();
        loadDataPrendas();
    }

    private void loadDataPrendas() {
        rootPrenda.get().addOnCompleteListener(task->{
            if(!task.isSuccessful())
                return;
            for(DataSnapshot child: task.getResult().getChildren()){
                prendas.add(child.getValue(Prenda.class));
                prendaAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadDataServicios() {
        rootServicio.get().addOnCompleteListener(task->{
            if(!task.isSuccessful())
                return;
            for(DataSnapshot child: task.getResult().getChildren()){
                servicios.add(child.getValue(Servicio.class));
                servicioAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadDataClientes() {
        rootCliente.get().addOnCompleteListener(task->{
            if(!task.isSuccessful())
                return;
            for(DataSnapshot child: task.getResult().getChildren()){
                clientes.add(child.getValue(Cliente.class));
                clientesAdapter.notifyDataSetChanged();
            }
        });
    }

    private void saveOrden() {
        ordenSelected = new Orden(clienteOrden.getText().toString(),
                servicioOrden.getText().toString(),
                null, // Temporalmente
                entregaOrden.getText().toString(),
                sumaPrecio.toString()
                );
        DatabaseReference services = databaseReference.child("Orden");
        String uidService = services.push().getKey();
        ordenSelected.setUid(uidService);
        services.child(uidService).setValue(ordenSelected).addOnCompleteListener(task -> {
            if(!task.isSuccessful()){
                Toast.makeText(getContext(),"Error al guardar la Orden",Toast.LENGTH_SHORT).show();
                return;
            }
            //Limpiar la ui
            limpiarCajas();

            //Ir al inicio
            Toast.makeText(getContext(),"Se guardo exitosamente", Toast.LENGTH_LONG).show();
        });
    }

    private void events() {
        List<Prenda> precioT = new ArrayList<>(prendas);
        for(Prenda precio: precioT){
            sumaPrecio = sumaPrecio + (Integer.valueOf(precio.precioPrenda));
        }
        nuevoCliente.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.from_orden_to_cliente);
        });
        nuevaPrenda.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.from_orden_to_prenda);
        });
        clienteOrden.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Cliente> tempC = new ArrayList<>(clientes);
                for(Cliente item: tempC){
                    if(!item.nombre.toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT))){
                        tempC.remove(item);
                    }
                }
                clientesAdapter = new ClientesAdapter(tempC,true);
                recyclerClientes.setAdapter(clientesAdapter);
                if(tempC.size() == 0) {
                    nuevoCliente.setVisibility(View.VISIBLE);
                } else {
                    nuevoCliente.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        servicioOrden.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Servicio> tempS = new ArrayList<>(servicios);
                for(Servicio item: tempS){
                    if(!item.recibe.toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT))){
                        tempS.remove(item);
                    }
                }
                servicioAdapter = new ServicioAdapter(tempS,true);
                recyclerClientes.setAdapter(clientesAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        prendaOrden.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Prenda> tempP = new ArrayList<>(prendas);
                for(Prenda item: tempP){
                    if(!item.caracteristica.toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT))){
                        tempP.remove(item);
                    }
                }
                prendaAdapter = new PrendaAdapter(tempP,true);
                recyclerPrendas.setAdapter(prendaAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        registerOrden.setOnClickListener(v->{
            if(!validate(v).isEmpty()){
                Toast.makeText(getContext(),validate(v),Toast.LENGTH_SHORT).show();
                return;
            }
            saveOrden();
            Navigation.findNavController(v).navigate(R.id.from_orden_to_home);
        });
    }

    private String validate(View v) {
        if(clienteOrden.getText().toString().isEmpty()){
            return "El cliente no puede estar vacio";
        } else if(servicioOrden.getText().toString().isEmpty()){
            return "El Servicio no puede estar vacio";
        } else if(prendaOrden.getText().toString().isEmpty()){
            return "La prenda no puede estar vacia";
        } else if(entregaOrden.getText().toString().isEmpty()){
            return "La fecha de entrega no puede estar vacio";
        }
        return "";
    }

    private void limpiarCajas() {
        clienteOrden.setText("");
        servicioOrden.setText("");
        entregaOrden.setText("");
        descripcionOrden.setText("");
    }

    private void init(View view) {
        //Boton
        registerOrden = view.findViewById(R.id.btn_guardar_orden);
        nuevoCliente =  view.findViewById(R.id.boton_nuevo_cliente);
        nuevaPrenda =  view.findViewById(R.id.boton_nueva_prenda);

        //EditText
        clienteOrden = view.findViewById(R.id.txt_buscar_cliente);
        servicioOrden = view.findViewById(R.id.txt_buscar_servicio);
        prendaOrden = view.findViewById(R.id.txt_desc_orden);
        descripcionOrden = view.findViewById(R.id.txt_desc_orden);
        entregaOrden = view.findViewById(R.id.txt_fecha_entrega);

        //TextView
        montoOrden = view.findViewById(R.id.txt_cost_orden);

        //recycler
        recyclerClientes = view.findViewById(R.id.list_clientes);
        recyclerServicios = view.findViewById(R.id.list_servicio);
        recyclerPrendas =  view.findViewById(R.id.list_prendas);

        //Lists
        clientes = new ArrayList<>();
        servicios = new ArrayList<>();
        prendas = getPrendas();

        //Adapters
        clientesAdapter = new ClientesAdapter(clientes,true);
        servicioAdapter = new ServicioAdapter(servicios,true);
        prendaAdapter = new PrendaAdapter(prendas,true);

    }

    private List<Prenda> getPrendas() {
        List<Prenda> list = new ArrayList<>();
    return list;
    }
}