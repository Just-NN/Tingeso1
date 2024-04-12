package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.VehiculoRepository;

import java.util.List;

@Service
public class VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;


    public List<VehiculoEntity> getVehiculos() {
        return vehiculoRepository.findAll();
    }
    public VehiculoEntity getVehiculoById(Long patente) {
        return vehiculoRepository.findById(patente).orElse(null);
    }

    public VehiculoEntity saveVehiculo(VehiculoEntity vehiculoEntity) {
        return vehiculoRepository.save(vehiculoEntity);
    }
    public VehiculoEntity updateVehiculo(VehiculoEntity vehiculoEntity) {
        return vehiculoRepository.save(vehiculoEntity);
    }
    public boolean deleteVehiculo(Long patente) {
        vehiculoRepository.deleteById(patente);
        return true;
    }

}
