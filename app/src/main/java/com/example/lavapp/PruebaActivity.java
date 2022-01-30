package com.example.lavapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lavapp.model.Servicio;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PruebaActivity extends AppCompatActivity {

    private Button guardarServicio;
    private TextInputEditText recibeServicio, direccionServicio, ciudadServicio, barrioServicio;
    private ImageButton deleteIcon;
    private boolean isEdit=false;
    ListView listV_servicios;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Servicio> listServicio = new ArrayList<Servicio>();
    ArrayAdapter<Servicio> arrayAdapterServicio;

    Servicio servicioSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        init();
        initFirebase();
        listarServicios();

        listV_servicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                servicioSelected = (Servicio) parent.getItemAtPosition(position);
                recibeServicio.setText(servicioSelected.getRecibe());
                direccionServicio.setText(servicioSelected.getDireccion());
                ciudadServicio.setText(servicioSelected.getCiudad());
                barrioServicio.setText(servicioSelected.getBarrio());
                isEdit=true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_prueba,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String name = recibeServicio.getText().toString();
        String address = direccionServicio.getText().toString();
        String city = ciudadServicio.getText().toString();
        String neighbor = barrioServicio.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:{
                if (name.equals("")||address.equals("")||city.equals("")||neighbor.equals("")){
                    validacion();
                }
                else {
                    Servicio s = new Servicio();
                    //s.setUid(UUID.randomUUID().toString());

                    s.setRecibe(name);
                    s.setDireccion(address);
                    s.setCiudad(city);
                    s.setBarrio(neighbor);
                    //databaseReference.child("Servicio").child(s.getUid()).setValue(s);
                    // contactsCollectionReference.add(s);
                    Toast.makeText(PruebaActivity.this, "Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            case R.id.icon_save:{
                if (name.equals("")||address.equals("")||city.equals("")||neighbor.equals("")){
                    validacion();
                }
                else {
                    Servicio s = new Servicio();
                    //s.setUid(servicioSelected.getUid());
                    s.setRecibe(recibeServicio.getText().toString().trim());
                    s.setDireccion(direccionServicio.getText().toString().trim());
                    s.setCiudad(ciudadServicio.getText().toString().trim());
                    s.setBarrio(barrioServicio.getText().toString().trim());
                    //databaseReference.child("Servicio").child(s.getUid()).setValue(s);
                    Toast.makeText(PruebaActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            case R.id.icon_delete:{
                if (name.equals("")||address.equals("")||city.equals("")||neighbor.equals("")){
                    validacion();
                }
                else {
                    Servicio s = new Servicio();
                    //s.setUid(servicioSelected.getUid());
                    //databaseReference.child("Servicio").child(s.getUid()).removeValue();
                    Toast.makeText(PruebaActivity.this.getBaseContext(), "Eliminado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            default:break;
        }
        return true;
    }

    private void listarServicios() {
        databaseReference.child("Servicio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listServicio.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Servicio s = objSnaptshot.getValue(Servicio.class);
                    listServicio.add(s);

                    arrayAdapterServicio = new ArrayAdapter<Servicio>(PruebaActivity.this, android.R.layout.simple_list_item_1, listServicio);
                    listV_servicios.setAdapter(arrayAdapterServicio);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void limpiarCajas() {
        recibeServicio.setText("");
        direccionServicio.setText("");
        ciudadServicio.setText("");
        barrioServicio.setText("");
    }

    private void validacion() {
        String name = recibeServicio.getText().toString();
        String address = direccionServicio.getText().toString();
        String city = ciudadServicio.getText().toString();
        String neighbor = barrioServicio.getText().toString();
        if (name.equals("")){
            recibeServicio.setError("Required");
        }
        else if (address.equals("")){
            direccionServicio.setError("Required");
        }
        else if (city.equals("")){
            ciudadServicio.setError("Required");
        }
        else if (neighbor.equals("")){
            barrioServicio.setError("Required");
        }

    }

    private void init() {
        recibeServicio = findViewById(R.id.txt_recibe_servicio);
        direccionServicio = findViewById(R.id.txt_direccion_servicio);
        ciudadServicio = findViewById(R.id.txt_ciudad_servicio);
        barrioServicio = findViewById(R.id.txt_barrio_servicio);
        listV_servicios = findViewById(R.id.vista_temp_servicio);
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        /*firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection(COLLECTION_NAME);*/
    }
}