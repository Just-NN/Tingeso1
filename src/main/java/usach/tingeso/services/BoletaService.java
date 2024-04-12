package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.repositories.BoletaRepository;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;


@Service
public class BoletaService {

    @Autowired
    BonosRecargosService bonosRecargosService;

    @Autowired
    BoletaRepository boletaRepository;

    public List<BoletaEntity> getBoletas(){
        return boletaRepository.findAll();
    }

    public BoletaEntity getBoletaById(Long id) {
        return (BoletaEntity) boletaRepository.findById(id).orElse(null);
    }
    public BoletaEntity saveBoleta(BoletaEntity boleta){
        return (BoletaEntity) boletaRepository.save(boleta);
    }
    public BoletaEntity updateBoleta(BoletaEntity boleta){

        return (BoletaEntity) boletaRepository.save(boleta);
    }
    public boolean deleteBoleta(Long id){
        boletaRepository.deleteById(id);
        return true;
    }
    //--------------------------------------------------------------------------------------------------------------
    // Calcular cada valor para guardarlo en la boleta
    public BoletaEntity guardarRecargoPorKM(BoletaEntity boleta){
        ReparacionEntity reparacion = boleta.getReparacionEntity();
        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        boleta.setRecargoPorKM((int) round(recargoKM*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
    public BoletaEntity guardarRecargpPorAntiguedad(BoletaEntity boleta){
        ReparacionEntity reparacion = boleta.getReparacionEntity();
        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        boleta.setRecargoPorKM((int) round(recargoAntiguedad*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
}

