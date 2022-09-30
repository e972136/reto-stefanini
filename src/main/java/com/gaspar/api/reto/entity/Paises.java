package com.gaspar.api.reto.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Paises {
    ES("El Salvador"), HN("Honduras"), CR("Costa Rica"), GT("Guatemala"),NI("Nicaragua"),Ninguno("Sin Eleccion");
    String nombre;
    Paises(String s) {
        this.nombre= s;
    }

    @JsonCreator
    public static Paises decode(final String nombre) {
        return Stream.of(Paises.values()).filter(targetEnum -> targetEnum.nombre.equals(nombre)).findFirst().orElse(null);
    }

    @JsonValue
    public String getNombre() {
        return nombre;
    }
}
