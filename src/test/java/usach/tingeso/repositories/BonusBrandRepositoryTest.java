package usach.tingeso.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import usach.tingeso.entities.BonusBrandEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BonusBrandRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BonusBrandRepository bonusBrandRepository;

    @Test
    public void testFindBonusBrandByIdBonus() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        entityManager.persistAndFlush(bonusBrand);

        BonusBrandEntity foundBonusBrand = bonusBrandRepository.findBonusBrandByIdBonus(bonusBrand.getIdBonus());
        assertNotNull(foundBonusBrand);
        assertEquals(bonusBrand.getIdBonus(), foundBonusBrand.getIdBonus());
    }


}