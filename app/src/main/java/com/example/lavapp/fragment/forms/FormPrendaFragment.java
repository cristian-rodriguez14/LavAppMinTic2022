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
import com.example.lavapp.adapter.PrecioAdapter;
import com.example.lavapp.adapter.PrendaAdapter;
import com.example.lavapp.adapter.ServicioAdapter;
import com.example.lavapp.model.Cliente;
import com.example.lavapp.model.Orden;
import com.example.lavapp.model.Precio;
import com.example.lavapp.model.Prenda;
import com.example.lavapp.viewModel.ClienteViewModel;
import com.example.lavapp.viewModel.PrecioViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FormPrendaFragment extends Fragment {

    TextInputEditText nombrePrenda, listadoPrenda, cantidadPrenda, descripcionPrenda;
    Button registerPrenda;
    RecyclerView recyclerPrecios;
    PrecioAdapter precioAdapter;
    Prenda newPrenda;
    List<Precio> precios;
    PrecioViewModel precioViewModel;
    FirebaseDatabase database;
    DatabaseReference rootPrecio;
    DatabaseReference databaseReference;
    Prenda prendaSelected;

    public FormPrendaFragment() {
        // Required empty public constructor
    }

    public static FormPrendaFragment newInstance(String param1, String param2) {
        FormPrendaFragment fragment = new FormPrendaFragment();
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
        return inflater.inflate(R.layout.fragment_form_prenda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        events();
        setup();
    }

    private void setup() {
        database = FirebaseDatabase.getInstance();
        rootPrecio = database.getReference("Precio");

        recyclerPrecios.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPrecios.setAdapter(precioAdapter);

        precioViewModel = new ViewModelProvider(requireActivity()).get(PrecioViewModel.class);
        loadDataPrecios();
    }

    private void loadDataPrecios() {
        rootPrecio.get().addOnCompleteListener(task->{
            if(!task.isSuccessful())
                return;
            for(DataSnapshot child: task.getResult().getChildren()){
                precios.add(child.getValue(Precio.class));
                precioAdapter.notifyDataSetChanged();
            }
        });
    }

    private void events() {
        listadoPrenda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Precio> tempP = new ArrayList<>(precios);
                for(Precio item: tempP){
                    if(!item.producto.toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT))){
                        tempP.remove(item);
                    }
                }
                precioAdapter = new PrecioAdapter(tempP,true);
                recyclerPrecios.setAdapter(precioAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        registerPrenda.setOnClickListener(v->{
            if(!validate(v).isEmpty()){
                Toast.makeText(getContext(),validate(v),Toast.LENGTH_SHORT).show();
                return;
            }
            savePrenda();
            Navigation.findNavController(v).navigate(R.id.from_prenda_to_orden);
        });
    }

    private void savePrenda() {
        prendaSelected = new Prenda(nombrePrenda.getText().toString(),
                listadoPrenda.getText().toString(),
                cantidadPrenda.getText().toString(),
                descripcionPrenda.getText().toString()
        );
        DatabaseReference services = databaseReference.child("Prenda");
        String uidService = services.push().getKey();
        prendaSelected.setUid(uidService);
        services.child(uidService).setValue(prendaSelected).addOnCompleteListener(task -> {
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

    private String validate(View v) {
        if(nombrePrenda.getText().toString().isEmpty()){
            return "No puede estar vacio";
        } else if(listadoPrenda.getText().toString().isEmpty()) {
            return "No puede estar vacio";
        }else if(cantidadPrenda.getText().toString().isEmpty()){
            return "No puede estar vacio";
        } else if(descripcionPrenda.getText().toString().isEmpty()){
            return "No puede estar vacia";
        }
        return "";
    }

    private void limpiarCajas() {
        nombrePrenda.setText("");
        listadoPrenda.setText("");
        cantidadPrenda.setText("");
        descripcionPrenda.setText("");
    }

    private void init(View view) {
        //Boton
        registerPrenda = view.findViewById(R.id.btn_guardar_prenda);

        //EditText
        nombrePrenda = view.findViewById(R.id.txt_caracteristica_prenda);
        listadoPrenda = view.findViewById(R.id.txt_buscar_precio);
        cantidadPrenda = view.findViewById(R.id.txt_buscar_servicio);
        descripcionPrenda = view.findViewById(R.id.txt_desc_orden);

        //recycler
        recyclerPrecios = view.findViewById(R.id.list_precios);

        //Lists
        precios = new ArrayList<>();

        //Adapters
        precioAdapter = new PrecioAdapter(precios,true);
    }


}