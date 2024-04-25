package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.ReporteEntity;


import java.util.Date;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteEntity, Long>{
    public ReporteEntity findById(long id);


    @Query("SELECT v.marca, AVG(DATEDIFF(r.fechaSalida, r.fechaIngreso)) " +
            "FROM ReparacionEntity r JOIN r.vehiculoEntity v " +
            "GROUP BY v.marca " +
            "ORDER BY AVG(DATEDIFF(r.fechaSalida, r.fechaIngreso))")
    List<Object[]> findAverageRepairTimePerBrand();

    @Query("SELECT r.tipoReparacion, COUNT(DISTINCT v.tipoVehiculo), SUM(b.precioTotal), " +
            "(SELECT SUM(b2.precioTotal) FROM BoletaEntity b2) as montoTotal " +
            "FROM ReparacionEntity r JOIN r.vehiculoEntity v JOIN BoletaEntity b ON r.idReparacion = b.idReparacion " +
            "GROUP BY r.tipoReparacion")
    List<Object[]> findRepairTypesVsVehicleTypesAndTotalAmount();
    @Query("SELECT DISTINCT v.marca " +
            "FROM ReparacionEntity r JOIN r.vehiculoEntity v")
    List<String> findDistinctBrandsInRepairs();

    @Query("SELECT r.tipoReparacion, Count(r), v.tipoMotor, SUM(b.precioTotal), " +
            "(SELECT SUM(b2.precioTotal) FROM BoletaEntity b2) as montoTotal " +
            "FROM ReparacionEntity r JOIN r.vehiculoEntity v JOIN BoletaEntity b ON r.idReparacion = b.idReparacion " +
            "GROUP BY r.tipoReparacion, v.tipoMotor " +
            "ORDER BY SUM(b.precioTotal) DESC")
    List<Object[]> findRepairTypesVsEngineTypesAndTotalAmount();
}
