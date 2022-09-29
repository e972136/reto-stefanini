package com.gaspar.api.reto.service;

import com.gaspar.api.reto.dto.FichaRequest;
import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.entity.Paises;
import com.gaspar.api.reto.repository.FichaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FichaServiceImplTest {

    @Autowired
    FichaRepository repository;

    FichaServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new FichaServiceImpl(repository);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void save() {
        FichaRequest fichaPersonal = FichaRequest.of(
                "a"
                ,"a"
                ,"a"
                ,"a"
                , "el salvador"
                ,"a"
                ,LocalDate.now()
        );
        FichaPersonal save = service.save(fichaPersonal);
        assertNotEquals(null,save);
    }

//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
    @Test
    void getFicha() {
        FichaRequest fichaPersonal = FichaRequest.of(
                "b"
                ,"b"
                ,"b"
                ,"b"
                , "honduras"
                ,"b"
                ,LocalDate.now()
        );
        FichaPersonal save = service.save(fichaPersonal);
        FichaPersonal obtener = service.getFicha(save.getId());
        assertEquals(save.getId(),obtener.getId());
    }

    @Test
    void getAll() {
        FichaRequest fichaPersonal = FichaRequest.of(
                "b"
                ,"b"
                ,"b"
                ,"b"
                , "honduras"
                ,"b"
                ,LocalDate.now()
        );
        service.save(fichaPersonal);
        service.save(fichaPersonal);
        service.save(fichaPersonal);
        System.out.println(service.getAll().size());
    }
//
//    @Test
//    void getByCountry() {
//    }
//
    @Test
    void obtenerPais() {
        Paises paises = service.obtenerPais("El Salvador");
        assertThat(paises).isEqualTo(Paises.ES);
        paises = service.obtenerPais("el salvador");
        assertThat(paises).isEqualTo(Paises.ES);
        paises = service.obtenerPais("EL SALVADOR");
        assertThat(paises).isEqualTo(Paises.ES);
        paises = service.obtenerPais("salbador");
        assertThat(paises).isEqualTo(Paises.Ninguno);
    }
}