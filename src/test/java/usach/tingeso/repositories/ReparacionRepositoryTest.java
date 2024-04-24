package usach.tingeso.repositories;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import usach.tingeso.entities.ReparacionEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ReparacionRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReparacionRepository reparacionRepository;

    @Test
    public void whenFindById_thenReturnReparacion() {
        // given
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);
        entityManager.persistAndFlush(reparacion);

        // when
        Optional<ReparacionEntity> found = reparacionRepository.findById(reparacion.getIdReparacion());

        // then
        assertThat(found.get().getIdReparacion()).isEqualTo(reparacion.getIdReparacion());
    }
}
