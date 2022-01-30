package com.example.lavapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lavapp.model.Servicio;

public class ServicioViewModel extends ViewModel {

    private final MutableLiveData<Servicio> servicioMutableLiveData = new MutableLiveData<>();

    public void setPet(Servicio servicio){
        servicioMutableLiveData.setValue(servicio);
    }
    public Servicio getServicio(){
        return servicioMutableLiveData.getValue();
    }
}
