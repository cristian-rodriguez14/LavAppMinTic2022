package com.example.lavapp.model;

import java.util.ArrayList;

public class Orden {
    public String uid;
    public String datosCliente;
    public String ordenServicio;
    public ArrayList<?> descripcionOrden;
    public String entrega;
    public String monto;

    public Orden() {
    }

    public Orden(String datosCliente, String ordenServicio, ArrayList<?> descripcionOrden, String entrega, String monto) {
        this.datosCliente = datosCliente;
        this.ordenServicio = ordenServicio;
        this.descripcionOrden = descripcionOrden;
        this.entrega = entrega;
        this.monto = monto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDatosCliente() {
        return datosCliente;
    }

    public void setDatosCliente(String datosCliente) {
        this.datosCliente = datosCliente;
    }

    public String getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(String ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public ArrayList<?> getDescripcionOrden() {
        return descripcionOrden;
    }

    public void setDescripcionOrden(ArrayList<?> descripcionOrden) {
        this.descripcionOrden = descripcionOrden;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

}
