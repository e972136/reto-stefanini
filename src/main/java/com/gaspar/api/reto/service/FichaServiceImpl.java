package com.gaspar.api.reto.service;

import com.gaspar.api.reto.dto.FichaRequest;
import com.gaspar.api.reto.entity.FichaPersonal;
import com.gaspar.api.reto.entity.Paises;
import com.gaspar.api.reto.repository.FichaRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaServiceImpl implements FichaService{

    final FichaRepository repository;

    public FichaServiceImpl(FichaRepository repository) {
        this.repository = repository;
    }

    @Override
    public FichaPersonal save(FichaRequest ficha) {
        FichaPersonal f =  FichaPersonal.builder()
                .nombre(ficha.getNombre())
                .apellidos(ficha.getApellidos())
                .telefono(ficha.getTelefono())
                .sitioTrabajo(ficha.getSitioTrabajo())
                .paisResidencia(obtenerPais(ficha.getPaisResidencia()))
                .ciudadResidencia(ficha.getCiudadResidencia())
                .fechaNacimiento(ficha.getFechaNacimiento())
                .build();
        return repository.save(f);
    }

    @Override
    public FichaPersonal update(Integer id, FichaRequest ficha) {
        FichaPersonal byId = repository.findById(id).orElse(null);
        if(byId==null){
            return null;
        }
        FichaPersonal f =  FichaPersonal.builder()
                .id(id)
                .nombre(ficha.getNombre())
                .apellidos(ficha.getApellidos())
                .telefono(ficha.getTelefono())
                .sitioTrabajo(ficha.getSitioTrabajo())
                .paisResidencia(obtenerPais(ficha.getPaisResidencia()))
                .ciudadResidencia(ficha.getCiudadResidencia())
                .fechaNacimiento(ficha.getFechaNacimiento())
                .build();
        return repository.save(f);
    }

    @Override
    public void delete(FichaPersonal ficha) {
        repository.delete(ficha);

    }

    @Override
    public FichaPersonal getFicha(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<FichaPersonal> getAll() {
        return repository.findAll();
    }

    @Override
    public List<FichaPersonal> getByCountry(Paises paises) {
        return repository.findBypaisResidencia(paises);
    }


    public Paises obtenerPais(String paisResidencia){
        Paises[] values = Paises.values();
        try {
            Paises retornar = Paises.Ninguno;
            for(Paises p:values){
                if(p.getNombre().equalsIgnoreCase(paisResidencia)){
                    retornar = p;
                    break;
                }
            }
            return retornar;
        }catch (Exception e){
            return Paises.Ninguno;
        }
    }
}
