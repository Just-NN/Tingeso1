package usach.tingeso.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import usach.tingeso.entities.BoletaEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class BoletaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private BoletaRepository boletaRepository;



    @Test
    public void whenFindById_thenReturnBoleta() {
        // given
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);
        boleta.setPrecioBase(10000);
        entityManager.persistAndFlush(boleta);


        // when
        Optional<BoletaEntity> found = boletaRepository.findById(boleta.getIdBoleta());

        // then
        assertThat(found.get().getIdBoleta()).isEqualTo(boleta.getIdBoleta());
    }
}