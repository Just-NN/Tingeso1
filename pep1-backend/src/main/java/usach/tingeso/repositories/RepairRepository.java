package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import usach.tingeso.entities.RepairEntity;

import java.util.Date;
import java.util.List;

public interface RepairRepository extends JpaRepository<RepairEntity, Long>{
    List<RepairEntity> findRepairsByIdTicket(Long id);
    RepairEntity findFirstByIdTicket(Long id);

    @Query("SELECT r FROM RepairEntity r WHERE r.licensePlate = :licensePlate AND r.entryDate >= :date")
    List<RepairEntity> findByVehicleThisYear(@Param("licensePlate") Long licensePlate, @Param("date") Date date);

    @Query("SELECT r FROM RepairEntity r WHERE r.licensePlate = :licensePlate AND r.entryDate = :date")
    List<RepairEntity> findRepairsByVehicleAndEntryDate(@Param("licensePlate") Long licensePlate, @Param("date") Date date);

    @Query("SELECT r.repairType as repairType, v.vehicleType as vehicleType, COUNT(v.vehicleType) as vehicleTypeCount, SUM(r.totalPrice) as totalPrice " +
            "FROM RepairEntity r, VehicleEntity v WHERE r.licensePlate = v.licensePlate " +
            "GROUP BY r.repairType, v.vehicleType")
    List<Object[]> getRepairTypeVehicleTypeCountAndTotalPrice();

    @Query("SELECT v.brand as brand, AVG(r.totalRepairAmount) as averageTotalRepairAmount " +
            "FROM RepairEntity r, VehicleEntity v WHERE r.licensePlate = v.licensePlate " +
            "GROUP BY v.brand")
    List<Object[]> getAverageTotalRepairAmountByBrand();

    @Query("SELECT r.repairType as repairType, v.engineType as motorType, COUNT(v.engineType) as motorTypeCount, SUM(r.totalPrice) as totalPrice " +
            "FROM RepairEntity r, VehicleEntity v WHERE r.licensePlate = v.licensePlate " +
            "GROUP BY r.repairType, v.engineType")
    List<Object[]> getRepairTypeMotorTypeCountAndTotalPrice();

    @Query("SELECT r FROM RepairEntity r WHERE r.entryDate = :entryDate")
    List<RepairEntity> findRepairsByEntryDate(@Param("entryDate") Date entryDate);
}