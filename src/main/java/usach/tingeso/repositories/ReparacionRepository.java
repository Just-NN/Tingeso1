package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface ReparacionRepository extends JpaRepository<ReparacionEntity, Long> {
    ReparacionEntity findById(long id);

    @Query(value = "SELECT r FROM ReparacionEntity WHERE r.vehiculo = :vehiculo", nativeQuery = true)
    List<ReparacionEntity> findByVehiculo(@Param("vehiculo") VehiculoEntity vehiculo);

    @Query(value = "SELECT r FROM ReparacionEntity WHERE r.vehiculo = :vehiculo AND r.fechaIngreso <= 12", nativeQuery = true)
    List<ReparacionEntity> findByVehiculoEsteAno(@Param("vehiculo") VehiculoEntity vehiculo);



}
