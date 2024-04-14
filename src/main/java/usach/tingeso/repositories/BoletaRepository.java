package usach.tingeso.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;


import java.util.Date;
import java.util.List;

@Repository
public interface BoletaRepository extends JpaRepository<BoletaEntity, Long>{
    BoletaEntity findById(long id);

    BoletaEntity findByReparacionEntity(ReparacionEntity reparacion);
}
