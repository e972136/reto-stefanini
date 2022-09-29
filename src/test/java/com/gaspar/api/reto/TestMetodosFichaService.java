package com.gaspar.api.reto;

import com.gaspar.api.reto.entity.Paises;
import com.gaspar.api.reto.service.FichaServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMetodosFichaService {
    @Test
    void validarPais(){

        FichaServiceImpl service = new FichaServiceImpl(null);
        Paises paises = service.obtenerPais("El Salvador");
        assertThat(paises).isEqualTo(Paises.ES);
        paises = service.obtenerPais("el salvador");
        assertThat(paises).isEqualTo(Paises.ES);
        paises = service.obtenerPais("EL SALVADOR");
        assertThat(paises).isEqualTo(Paises.ES);
    }

    @Test
    void validarPaisInvalido(){

        FichaServiceImpl service = new FichaServiceImpl(null);
        Paises paises = service.obtenerPais("salbador");
        assertThat(paises).isEqualTo(Paises.Ninguno);

    }

}
