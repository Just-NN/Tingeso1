package usach.tingeso.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.VehicleRepository;

import java.util.Collections;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    // Basic CRUD operations
    public VehicleEntity getVehicleById(Long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public VehicleEntity saveVehicle(VehicleEntity vehicle){
        if (vehicle == null)
            return null;
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id){
        if (vehicleRepository.findVehicleByLicensePlate(id) == null)
            return;
        vehicleRepository.deleteById(id);
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicle){
        if (vehicleRepository.existsById(vehicle.getLicensePlate())) {
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    // Get all vehicles
    public List<VehicleEntity> getAllVehicles(){
        List<VehicleEntity> vehicles = vehicleRepository.findAll();
        return vehicles != null ? vehicles : Collections.emptyList();
    }
}