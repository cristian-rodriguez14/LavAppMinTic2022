package com.example.lavapp.model;

public class Prenda {
    public String uid;
    public String precioPrenda;
    public String caracteristica;
    public String descripcion;
    public String cantidad;

    public Prenda(String caracteristica, String precioPrenda, String cantidad, String descripcion) {
        this.precioPrenda = precioPrenda;
        this.caracteristica = caracteristica;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Prenda() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPrecioPrenda() {
        return precioPrenda;
    }

    public void setPrecioPrenda(String precioPrenda) {
        this.precioPrenda = precioPrenda;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}
