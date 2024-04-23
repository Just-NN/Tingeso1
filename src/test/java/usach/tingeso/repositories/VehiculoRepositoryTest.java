package usach.tingeso.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import usach.tingeso.entities.VehiculoEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class VehiculoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Test
    public void whenFindById_thenReturnVehiculo() {
        // given
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Yaris");
        vehiculo.setAno(2010);
        entityManager.persistAndFlush(vehiculo);

        long id = vehiculo.getPatente();

        // when
        Optional<VehiculoEntity> found = vehiculoRepository.findById(id);

        // then
        assertThat(found.get().getPatente())
                .isEqualTo(vehiculo.getPatente());
    }
}
