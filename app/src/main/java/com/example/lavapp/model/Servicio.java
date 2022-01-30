package com.example.lavapp.model;



public class Servicio {
    public String uid;
    public String recibe;
    public String direccion;
    public String ciudad;
    public String barrio;
    public boolean estado;
    public User atendioLlamada;

    public Servicio(String recibe, String direccion, String ciudad, String barrio, boolean estado) {
        this.recibe = recibe;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.estado = estado;
    }

    public Servicio(String recibe, String direccion, String ciudad, String barrio, boolean estado, User atendioLlamada) {
        this.atendioLlamada = atendioLlamada;
        this.recibe = recibe;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.estado = estado;
    }

    public Servicio() {
    }

    public User getAtendioLlamada() {
        return atendioLlamada;
    }

    public void setAtendioLlamada(User atendioLlamada) {
        this.atendioLlamada = atendioLlamada;
    }

    public String getRecibe() {
        return recibe;
    }

    public void setRecibe(String recibe) {
        this.recibe = recibe;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uidService) {
        this.uid = uidService;
    }

    @Override
    public String toString() {
        return recibe + " en: " + direccion;
    }
}
