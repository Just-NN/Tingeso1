package usach.tingeso.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.ReparacionRepository;

import java.util.List;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRepository reparacionRepository;


    public List<ReparacionEntity> getReparaciones(){
        return (List<ReparacionEntity>) reparacionRepository.findAll();
    }
    public ReparacionEntity saveReparacion(ReparacionEntity reparacion){
        return reparacionRepository.save(reparacion);
    }
    public ReparacionEntity getReparacionById(Long id){
        return reparacionRepository.findById(id).orElse(null);
    }
    public List<ReparacionEntity> getReparacionesByVehiculo(VehiculoEntity vehiculo){
        return (List<ReparacionEntity>) reparacionRepository.findByVehiculo(vehiculo);
    }

    public ReparacionEntity UpdateReparacion(ReparacionEntity reparacion){
        return reparacionRepository.save(reparacion);
    }

    public List<ReparacionEntity> getReparacionesVehiculoEsteAno(VehiculoEntity vehiculo){
        return (List<ReparacionEntity>) reparacionRepository.findByVehiculoEsteAno(vehiculo);
    }

}
