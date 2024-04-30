package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usach.tingeso.entities.VehicleEntity;
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
    VehicleEntity findVehicleByLicensePlate(Long id);



}
