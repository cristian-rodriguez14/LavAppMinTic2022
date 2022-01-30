package com.example.lavapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lavapp.model.Prenda;

public class PrendaViewModel extends ViewModel {

    private final MutableLiveData<Prenda> prendaMutableLiveData = new MutableLiveData<>();

    public void setPet(Prenda prenda){
        prendaMutableLiveData.setValue(prenda);
    }
    public Prenda getPrenda(){
        return prendaMutableLiveData.getValue();
    }
}
