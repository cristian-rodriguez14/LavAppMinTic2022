package com.example.lavapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lavapp.model.Precio;

public class PrecioViewModel extends ViewModel {

    private final MutableLiveData<Precio> precioMutableLiveData = new MutableLiveData<>();

    public void setPet(Precio precio){
        precioMutableLiveData.setValue(precio);
    }
    public Precio getPrecio(){
        return precioMutableLiveData.getValue();
    }
}
