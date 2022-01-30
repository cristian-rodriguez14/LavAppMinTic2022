package com.example.lavapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lavapp.model.Cliente;

public class ClienteViewModel extends ViewModel {

    private final MutableLiveData<Cliente> clienteMutableLiveData = new MutableLiveData<>();

    public void setPet(Cliente cliente){
        clienteMutableLiveData.setValue(cliente);
    }
    public Cliente getCliente(){
        return clienteMutableLiveData.getValue();
    }
}
