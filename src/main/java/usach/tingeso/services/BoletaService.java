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
    ReparacionService reparacionService;

    @Autowired
    BoletaRepository boletaRepository;


    // CRUD normal
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
    // Calcular precio base
    public BoletaEntity guardarPrecioBase(BoletaEntity boleta){
        ReparacionEntity reparacion = reparacionService.getReparacionById(boleta.getIdBoleta());
        double precioBase = bonosRecargosService.calcularPrecioBase(reparacion);
        boleta.setPrecioBase((int) round(precioBase));
        return boletaRepository.save(boleta);
    }

    //--------------------------------------------------------------------------------------------------------------
    // Calcular cada valor para guardarlo en la boleta

    // -------------------------- Recargos----------------------------
    public BoletaEntity guardarRecargoPorKM(BoletaEntity boleta){
        ReparacionEntity reparacion = reparacionService.getReparacionById(boleta.getIdBoleta());
        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        boleta.setRecargoPorKM((int) round(recargoKM*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
    public BoletaEntity guardarRecargoPorAntiguedad(BoletaEntity boleta){
        ReparacionEntity reparacion = reparacionService.getReparacionById(boleta.getIdBoleta());
        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        boleta.setRecargoPorAntiguedad((int) round(recargoAntiguedad*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
    public BoletaEntity guardarRecargoPorRetraso(BoletaEntity boleta){
        ReparacionEntity reparacion = reparacionService.getReparacionById(boleta.getIdBoleta());
        double recargoRetraso = bonosRecargosService.calcularRecargoPorRetraso(reparacion);
        boleta.setRecargoPorRetraso((int) round(recargoRetraso*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
    // -------------------------- Descuentos----------------------------
    public BoletaEntity guardarDescuentoPorReparaciones(BoletaEntity boleta){
        ReparacionEntity reparacion = reparacionService.getReparacionById(boleta.getIdBoleta());
        double descuentoReparaciones = bonosRecargosService.calcularDescuentoPorReparaciones(reparacion);
        boleta.setDescuentoPorReparaciones((int) round(descuentoReparaciones*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
    public BoletaEntity guardarDescuentoPorDia(BoletaEntity boleta){
        ReparacionEntity reparacion = reparacionService.getReparacionById(boleta.getIdBoleta());
        double recargoDia = bonosRecargosService.calcularDescuentoPorDia(reparacion);
        boleta.setDescuentoPorDia((int) round(recargoDia*boleta.getPrecioBase()));
        return boletaRepository.save(boleta);
    }
    // -----------Total de la boleta----------------


    public BoletaEntity guardarPrecioTotal(BoletaEntity boleta){
        double total = bonosRecargosService.calcularPrecioTotal(boleta);
        boleta.setPrecioTotal(boleta.getPrecioBase());
        return boletaRepository.save(boleta);
    }

}

