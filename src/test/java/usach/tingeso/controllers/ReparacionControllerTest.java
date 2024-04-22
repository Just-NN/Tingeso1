package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.services.ReparacionService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ReparacionController.class)
public class ReparacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReparacionService reparacionService;

    @Test
    public void testGetReparaciones() throws Exception {
        ReparacionEntity reparacion1 = new ReparacionEntity();
        reparacion1.setIdReparacion(1L);

        ReparacionEntity reparacion2 = new ReparacionEntity();
        reparacion2.setIdReparacion(2L);

        List<ReparacionEntity> reparaciones = Arrays.asList(reparacion1, reparacion2);
        
    }


}
