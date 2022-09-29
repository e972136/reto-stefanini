package com.gaspar.api.reto.entity;

public enum Paises {
    ES("El Salvador"), HN("Honduras"), CR("Costa Rica"), GT("Guatemala"),NI("Nicaragua"),Ninguno("Sin Eleccion");
    String nombre;
    Paises(String s) {
        this.nombre= s;
    }

    public String getNombre() {
        return nombre;
    }
}
