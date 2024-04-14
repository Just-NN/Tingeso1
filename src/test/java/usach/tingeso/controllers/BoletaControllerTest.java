package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.services.BoletaService;

@WebMvcTest(BoletaController.class)
public class BoletaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoletaService boletaService;

}
