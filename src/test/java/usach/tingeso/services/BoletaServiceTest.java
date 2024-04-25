package usach.tingeso.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.BoletaRepository;
import usach.tingeso.repositories.ReparacionRepository;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BoletaServiceTest {
    @Autowired
    BoletaService boletaService;
    @Autowired
    BonosRecargosService bonosRecargosService;
    @MockBean
    BoletaRepository boletaRepository;
    @MockBean
    ReparacionRepository reparacionRepository;
    @Autowired
    private ReparacionService reparacionService;


    @Test
    public void whenCreateBoleta_thenReturnBoleta() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);

        when(boletaRepository.save(boleta)).thenReturn(boleta);

        BoletaEntity savedBoleta = boletaService.saveBoleta(boleta);

        assertThat(savedBoleta).isEqualTo(boleta);
    }
    @Test
    public void whenUpdateBoleta_thenReturnBoleta() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);

        when(boletaRepository.save(boleta)).thenReturn(boleta);

        boletaService.saveBoleta(boleta);
        boleta.setIdBoleta(2L);
        boletaService.updateBoleta(boleta);
        assertThat(boletaService.getBoletaById(1L)).isNotEqualTo(boleta);
    }
    @Test
    public void whenDeleteBoleta_thenReturnTrue() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);


        boletaService.saveBoleta(boleta);
        boletaService.deleteBoleta(1L);
        assertThat(boletaService.getBoletaById(1L)).isNull();
    }
    @Test
    public void whenGetBoletaById_thenReturnBoleta() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);

        boletaService.saveBoleta(boleta);
        assertThat(boletaService.getBoletaById(1L)).isEqualTo(boleta);
    }
    @Test
    public void whenGetBoletas_thenReturnBoletas() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);
        BoletaEntity boleta2 = new BoletaEntity();
        boleta2.setIdBoleta(2L);
        List<BoletaEntity> boletas = new ArrayList<>();
        boletas.add(boleta);
        boletas.add(boleta2);

        when(boletaRepository.findAll()).thenReturn(boletas);

        List<BoletaEntity> found = boletaService.getBoletas();

        assertThat(found).isEqualTo(boletas);
    }
    


//    @Test
//    public void whenDeleteBoleta_thenReturnTrue() {
//        BoletaEntity boleta = new BoletaEntity();
//        boleta.setIdBoleta(1L);
//
//        when(boletaRepository.save(boleta)).thenReturn(boleta);
//        when(boletaRepository.findById(1L)).thenReturn(java.util.Optional.of(boleta));
//
//        boletaService.saveBoleta(boleta);
//        boletaService.deleteBoleta(1L);
//        assertThat(boletaService.getBoletaById(1L)).isNull();
//    }
//    @Test
//    public void whenGetBoletaById_thenReturnBoleta() {
//        BoletaEntity boleta = new BoletaEntity();
//        boleta.setIdBoleta(1L);
//
//        when(boletaRepository.save(boleta)).thenReturn(boleta);
//        when(boletaRepository.findById(1L)).thenReturn(java.util.Optional.of(boleta));
//
//        boletaService.saveBoleta(boleta);
//        assertThat(boletaService.getBoletaById(1L)).isEqualTo(boleta);
//    }

}
