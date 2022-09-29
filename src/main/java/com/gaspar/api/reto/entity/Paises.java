package com.gaspar.api.reto.entity;

public enum Paises {
    ES("El Salvador"), HN("Honduras"), CR("Costa Rica"), GT("Guatemala"),NI("Nicaragua");
    String nombre;
    Paises(String s) {
        this.nombre= s;
    }
}
