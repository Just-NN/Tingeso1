package usach.tingeso.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import usach.tingeso.entities.VehiculoEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VehiculoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Test
    public void whenFindByPatente_thenReturnVehiculo() {
        // given
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        entityManager.persistAndFlush(vehiculo);

        // when
        VehiculoEntity found = vehiculoRepository.getByPatente(vehiculo.getPatente());

        // then
        assertThat(found.getPatente()).isEqualTo(vehiculo.getPatente());
    }

    @Test
    public void whenFindByMarca_thenReturnVehiculoList() {
        // given
        VehiculoEntity vehiculo1 = new VehiculoEntity();
        vehiculo1.setMarca("Toyota");
        entityManager.persistAndFlush(vehiculo1);

        VehiculoEntity vehiculo2 = new VehiculoEntity();
        vehiculo2.setMarca("Toyota");
        entityManager.persistAndFlush(vehiculo2);

        // when
        List<VehiculoEntity> found = vehiculoRepository.findByMarca("Toyota");

        // then
        assertThat(found).hasSize(2).extracting(VehiculoEntity::getMarca).containsOnly(vehiculo1.getMarca());
    }

    // Add more tests for other methods in the VehiculoRepository class
}