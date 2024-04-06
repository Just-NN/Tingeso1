package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import usach.tingeso.entities.VehiculoEntity;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {
    VehiculoEntity getByPatente(Long patente);
    List<VehiculoEntity> findByMarca(String marca);
    List<VehiculoEntity> findByModelo(String modelo);
    List<VehiculoEntity> findByTipoVehiculo(int tipoDeVehiculo);
    List<VehiculoEntity> findByAno(int ano);
    List<VehiculoEntity> findByTipoMotor(int tipoMotor);



    // @Query(value = "SELECT * FROM vehiculos WHERE ... = algo")
    // vehiculoentity findbyrutnativequery(@Param("rut") String rut);
}
