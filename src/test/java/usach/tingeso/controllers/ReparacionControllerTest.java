package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.services.BoletaService;
import usach.tingeso.services.ReparacionService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReparacionController.class)
public class ReparacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReparacionService reparacionService;
    @MockBean
    private BoletaService boletaService;

    @Test
    public void testGetReparaciones() throws Exception {
        ReparacionEntity reparacion1 = new ReparacionEntity();
        reparacion1.setIdReparacion(1L);

        ReparacionEntity reparacion2 = new ReparacionEntity();
        reparacion2.setIdReparacion(2L);

        List<ReparacionEntity> reparaciones = Arrays.asList(reparacion1, reparacion2);
        given(reparacionService.getReparaciones()).willReturn(reparaciones);

        mockMvc.perform(get("/api/v1/reparacion/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idReparacion", is(1)))
                .andExpect(jsonPath("$[1].idReparacion", is(2)));
    }

    @Test
    public void testGetReparacionById() throws Exception {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        given(reparacionService.getReparacionById(1L)).willReturn(reparacion);

        mockMvc.perform(get("/api/v1/reparacion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReparacion", is(1)));
    }

    @Test
    public void testSaveReparacion() throws Exception {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        given(reparacionService.saveReparacion(Mockito.any())).willReturn(reparacion);
        mockMvc.perform(post("/api/v1/reparacion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idReparacion\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateReparacion() throws Exception {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);


        given(reparacionService.saveReparacion(Mockito.any())).willReturn(reparacion);

        mockMvc.perform(put("/api/v1/reparacion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idReparacion\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteReparacion() throws Exception {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);

        given(reparacionService.getReparacionById(1L)).willReturn(reparacion);

        mockMvc.perform(delete("/api/v1/reparacion/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReparacionesByVehiculo() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);

        ReparacionEntity reparacion1 = new ReparacionEntity();
        reparacion1.setIdReparacion(1L);
        reparacion1.setVehiculoEntity(vehiculo);

        ReparacionEntity reparacion2 = new ReparacionEntity();
        reparacion2.setIdReparacion(2L);
        reparacion2.setVehiculoEntity(vehiculo);

        List<ReparacionEntity> reparaciones = Arrays.asList(reparacion1, reparacion2);
        given(reparacionService.getReparacionesByVehiculo(vehiculo)).willReturn(reparaciones);

        mockMvc.perform(get("/api/v1/reparacion/reparacion/{patente}", vehiculo.getPatente()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idReparacion", is(1)))
                .andExpect(jsonPath("$[1].idReparacion", is(2)));
    }


    @Test
    public void testGetReparacionesReturnsBadRequest() throws Exception {
        given(reparacionService.getReparaciones()).willReturn(null);

        mockMvc.perform(get("/api/v1/reparacion/"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetReparacionByIdReturnsNotFound() throws Exception {
        given(reparacionService.getReparacionById(1L)).willReturn(null);

        mockMvc.perform(get("/api/v1/reparacion/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveReparacionReturnsInternalServerError() throws Exception {
        given(reparacionService.saveReparacion(Mockito.any())).willReturn(null);

        mockMvc.perform(post("/api/v1/reparacion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idReparacion\":1}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testUpdateReparacionReturnsInternalServerError() throws Exception {
        given(reparacionService.saveReparacion(Mockito.any())).willReturn(null);

        mockMvc.perform(put("/api/v1/reparacion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idReparacion\":1}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testDeleteReparacionReturnsNotFound() throws Exception {
        given(reparacionService.getReparacionById(1L)).willReturn(null);

        mockMvc.perform(delete("/api/v1/reparacion/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetReparacionesByVehiculoReturnsNotFound() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);

        given(reparacionService.getReparacionesByVehiculo(vehiculo)).willReturn(null);

        mockMvc.perform(get("/api/v1/reparacion/reparacion/{patente}", vehiculo.getPatente()))
                .andExpect(status().isNotFound());
    }
}