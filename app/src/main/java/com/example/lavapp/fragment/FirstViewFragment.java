package com.example.lavapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// import com.example.lavapp.adapter.ServicioAdapter;
import com.example.lavapp.R;
/*import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;*/

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstViewFragment extends Fragment {

    // private static final String COLLECTION_NAME = "servicio";


/*    private List<Servicio> listServicio = new ArrayList<Servicio>();
    ArrayAdapter<Servicio> arrayAdapterServicio;
    private ListView recyclerView;
    Servicio servicioSelected;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;*/

    /*private final ArrayList<Servicio> servicios = new ArrayList<>();
    private List<Servicio> listServicio;
    private ServicioAdapter servicioAdapter;
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference = firebaseFirestore.collection("servicio");*/


    public FirstViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FirstViewFragment newInstance(String param1, String param2) {
        FirstViewFragment fragment = new FirstViewFragment();
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
        return inflater.inflate(R.layout.fragment_first_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*init(view);
        listarDatos();
        recyclerView.setOnClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                servicioSelected = (Servicio) parent.getItemAtPosition(position);
                name.setText(personaSelected.getNombre());
                address.setText(personaSelected.getApellido());
                correoP.setText(personaSelected.getCorreo());
                passwordP.setText(personaSelected.getPassword());
                Intent intent = new Intent(getActivity().getBaseContext(),WelcomeActivity.class);
                intent.putExtra("id", position);
                getActivity().startActivity(intent);
            }
        });*/
    }

    /*private void listarDatos() {
        databaseReference.child("Servicio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listServicio.clear();
                for (DataSnapshot objSnaptshot : snapshot.getChildren()){
                    Servicio p = objSnaptshot.getValue(Servicio.class);
                    listServicio.add(p);

                    arrayAdapterServicio = new ArrayAdapter<Servicio>(getContext(), android.R.layout.simple_list_item_1, listServicio);
                    recyclerView.setAdapter(arrayAdapterServicio);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    /*private void init(View view) {
        recyclerView = view.findViewById(R.id.ver_servicios_activos);
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }*/

    /*private void listarDatos() {
        contactsCollectionReference.addSnapshotListener((EventListener<QuerySnapshot>) onCompleteListener);
        listServicio = new ArrayList<>();
        servicioAdapter = new ServicioAdapter(listServicio);
        recyclerView.setAdapter(servicioAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        *//*  contactsCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                listServicio.clear();
                for(QueryDocumentSnapshot objSnapshot: task.getResult()) {
                    Servicio s = (Servicio) objSnapshot.get(String.valueOf(Servicio.class));
                    listServicio.add(s);

                    // servicioArrayAdapter = new ArrayAdapter<Servicio>(getContext(), android.R.layout.simple_list_item_1, listServicio);
                    // recyclerView.setAdapter(servicioArrayAdapter);
                 }
            }
        });*//*
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.ver_servicios_activos);
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(COLLECTION_NAME);

    }

    OnCompleteListener onCompleteListener = task -> {
        Servicio servicio = (Servicio) task.getResult();
        if (servicio != null) mostrarServicios(servicio);
    };

    private void mostrarServicios(Servicio servicio) {
        servicios.add(servicio);
        refrescarServicio();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refrescarServicio() {
        if (servicioAdapter == null) return;
        listServicio = servicios;
        //Log.d("prueba ubicacion", String.valueOf(servicios));
        servicioAdapter.setServicios(listServicio);
        servicioAdapter.notifyDataSetChanged();
    }*/
}