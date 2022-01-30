package com.example.lavapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lavapp.R;
import com.example.lavapp.model.DirectionMap;
import com.example.lavapp.model.Leg;
import com.example.lavapp.model.OverviewPolyline;
import com.example.lavapp.model.Servicio;
import com.example.lavapp.service.IDirection;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    /*private static final String TAG_SEARCH = "_SearchFragment_";
    //UI
    FloatingActionButton stop;*/
    //Maps
    SupportMapFragment mapFragment;
    //Google Map
    GoogleMap map;
    //Firebase
    /*DatabaseReference petsRoot;
    //Location
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    LatLng petLocation;
    Polyline polyline;

    //Retrofit
    Retrofit retrofit;
    IDirection iDirection;*/

    public MapsFragment() {}

    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
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
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        // setup();
        events();
    }

    private void events() {
        mapFragment.getMapAsync(this);
        /*stop.setOnClickListener(v->{
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            stop.setVisibility(View.GONE);
        });

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LatLng current = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 18));

                String from = new StringBuilder()
                        .append(current.latitude)
                        .append(",")
                        .append(current.longitude)
                        .toString(),
                        to = new StringBuilder()
                                .append(petLocation.latitude)
                                .append(",")
                                .append(petLocation.longitude)
                                .toString();

                //getDirection(from, to);
            }
        };*/
    }

   /* private void getDirection(String from, String to) {
        iDirection.getDirections(from,
                to,
                "driving",
                "less_walking",
                "es",
                getString(R.string.api_key_direction))
                .enqueue(new Callback<DirectionMap>() {
                    @Override
                    public void onResponse(Call<DirectionMap> call, Response<DirectionMap> response) {

                        DirectionMap directionMap = response.body();

                        if(directionMap.getRoutes() == null  ||
                                directionMap.getRoutes().size() < 1){
                            Log.i(TAG_SEARCH, "onResponse: DIRECTION API : "+ directionMap.getError_message());
                            Toast.makeText(getContext(),"Error al recuperar los datos",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Leg legs = directionMap.getRoutes().get(0).getLegs().get(0);
                        Toast.makeText(getContext(),String.format("Duracion: %s Distancia: %s",legs.getDuration().getText(),legs.getDistance().getText()),Toast.LENGTH_SHORT).show();

                        printPolyLine(directionMap.getRoutes().get(0).getOverview_polyline().getPoints());
                    }

                    @Override
                    public void onFailure(Call<DirectionMap> call, Throwable t) {
                        Log.e(TAG_SEARCH, "onFailure: DIRECTION API", t);
                    }
                });
    }*/

    /*private void printPolyLine(String points) {
        if(polyline != null)
            polyline.setPoints(OverviewPolyline.decodePoly(points));
        else
            polyline = map.addPolyline(new PolylineOptions().addAll(OverviewPolyline.decodePoly(points)).color(Color.BLACK));
    }

    private void showUIS() {


    }*/

    private void setup() {
        /*locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        *//*retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url_maps))
                .addConverterFactory(GsonConverterFactory.create())
                .build();*//*
        iDirection = retrofit.create(IDirection.class);

        petsRoot = FirebaseDatabase.getInstance().getReference().child("pets");*/
    }

    private void init(View view) {
        // stop = view.findViewById(R.id.stopCallback);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng store = new LatLng(7.126604,-73.120926);
        googleMap.addMarker(new MarkerOptions().position(store).title("LavApp").snippet("Main Office LavApp"));

        googleMap.setMinZoomPreference(10);
        googleMap.setMaxZoomPreference(25);

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        enableLocation(googleMap);


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(store));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(20));

        //loadPetLocation();

        //eventsMarker();
    }

    /*@SuppressLint("MissingPermission")
    private void eventsMarker() {
        map.setOnInfoWindowClickListener(marker -> {
            petLocation  = marker.getPosition();
            if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;
            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
            stop.setVisibility(View.VISIBLE);
        });
    }

    private void loadPetLocation() {
        petsRoot.get().addOnSuccessListener(dataSnapshot -> {
            dataSnapshot.getChildren().forEach(item->{
                Servicio temp = item.getValue(Servicio.class);
                *//*LatLng lng = new LatLng(temp.getLocation().getLat(), temp.getLocation().getLng());
                map.addMarker(new MarkerOptions().position(lng).title(temp.getName()).snippet(temp.getInformation()));*//*
            });
        });
    }*/

    @SuppressLint("MissingPermission")
    private void enableLocation(GoogleMap googleMap) {
        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    /*@Override
    public void onDestroy() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        super.onDestroy();
    }*/
}