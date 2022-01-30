package com.example.lavapp.fragment.forms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lavapp.R;
import com.example.lavapp.adapter.ServicioAdapter;
import com.example.lavapp.model.Servicio;
import com.example.lavapp.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FormServicioFragment extends Fragment {

    //private static final String COLLECTION_NAME = "servicio";

    private Button guardarServicio;
    private TextInputEditText recibeServicio, direccionServicio, ciudadServicio, barrioServicio;
    private ImageButton deleteIcon;
    private boolean isEdit=false;
    ServicioAdapter servicioAdapter;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<Servicio> servicios;

    Servicio servicioSelected;

    public FormServicioFragment() {
        // Required empty public constructor
    }

    public static FormServicioFragment newInstance(String param1, String param2) {
        FormServicioFragment fragment = new FormServicioFragment();
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
        //position = getArguments().getString("message");
        return inflater.inflate(R.layout.fragment_form_servicio, container, false);
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

    private void saveServicio() {
        servicioSelected = new Servicio(recibeServicio.getText().toString(),
                direccionServicio.getText().toString(),
                ciudadServicio.getText().toString(),
                barrioServicio.getText().toString(),
                false);
        servicioSelected.setAtendioLlamada(new User(auth.getUid(),
                auth.getCurrentUser().getDisplayName().isEmpty() ? auth.getCurrentUser().getEmail() : auth.getCurrentUser().getDisplayName()
        ));
        DatabaseReference services = databaseReference.child("Servicio");
        String uidService = services.push().getKey();
        servicioSelected.setUid(uidService);
        services.child(uidService).setValue(servicioSelected).addOnCompleteListener(task -> {
            if(!task.isSuccessful()){
                Toast.makeText(getContext(),"Error al guardar el Servicio",Toast.LENGTH_SHORT).show();
                return;
            }
            //Limpiar la ui
            limpiarCajas();

            //Ir al inicio
            Toast.makeText(getContext(),"Se guardo exitosamente", Toast.LENGTH_LONG).show();
        });
    }

    private void limpiarCajas() {
        recibeServicio.setText("");
        direccionServicio.setText("");
        ciudadServicio.setText("");
        barrioServicio.setText("");
    }

    private void events() {
        guardarServicio.setOnClickListener(v->{
            if(!validate(v).isEmpty()){
                Toast.makeText(getContext(),validate(v),Toast.LENGTH_SHORT).show();
                return;
            }
            saveServicio();
            Navigation.findNavController(v).navigate(R.id.from_servicio_to_home);
        });
    }

    private String validate(View v) {
        if(recibeServicio.getText().toString().isEmpty()){
            return "El nombre no puede estar vacio";
        } else if(direccionServicio.getText().toString().isEmpty()){
            return "La dirección no puede estar vacia";
        } else if(ciudadServicio.getText().toString().isEmpty()){
            return "La ciudad no puede estar vacia";
        } else if(barrioServicio.getText().toString().isEmpty()){
            return "El barrio no puede estar vacio";
        }
        return "";
    }

    private void init(View view) {
        //boton
        guardarServicio = view.findViewById(R.id.btn_guardar_servicio);
        
        //TextView
        recibeServicio = view.findViewById(R.id.txt_recibe_servicio);
        direccionServicio = view.findViewById(R.id.txt_direccion_servicio);
        ciudadServicio = view.findViewById(R.id.txt_ciudad_servicio);
        barrioServicio = view.findViewById(R.id.txt_barrio_servicio);
        
        //List
        servicios = new ArrayList<>();
        
        //Adapter
        servicioAdapter = new ServicioAdapter(servicios,true);
        // deleteIcon = view.findViewById(R.id.delete_icon);
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        /*firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(COLLECTION_NAME);*/
    }
}