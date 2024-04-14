package usach.tingeso.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.ReparacionRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRepository reparacionRepository;



    public List<ReparacionEntity> getReparaciones(){

        return (List<ReparacionEntity>) reparacionRepository.findAll();
    }
    public List<ReparacionEntity> getReparacionesVehiculoEsteAno(VehiculoEntity vehiculo){
        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.YEAR, -1); // Resta 1 año a la fecha actual
        return reparacionRepository.findByVehiculoEsteAno(vehiculo, fecha);
    }
    public List<ReparacionEntity> getReparacionesByVehiculo(VehiculoEntity vehiculo){
        return reparacionRepository.findByVehiculo(vehiculo);
    }
    public ReparacionEntity getReparacionById(Long id){
        return reparacionRepository.findById(id).orElse(null);
    }
    public ReparacionEntity saveReparacion(ReparacionEntity reparacion){
        return reparacionRepository.save(reparacion);
    }
    public ReparacionEntity UpdateReparacion(ReparacionEntity reparacion){
        return reparacionRepository.save(reparacion);
    }


    public boolean deleteReparacion(Long id){
        reparacionRepository.deleteById(id);
        return true;
    }

}
