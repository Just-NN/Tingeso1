package usach.tingeso.services;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import usach.tingeso.repositories.BoletaRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class BoletaServiceTest {

    @Autowired
    BoletaService boletaService;

    @MockBean
    BoletaRepository boletaRepository;

}
