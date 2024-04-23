package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.services.BoletaService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoletaController.class)
    public class BoletaControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private BoletaService boletaService;

        @Test
        public void testGetBoletas() throws Exception {
            BoletaEntity boleta1 = new BoletaEntity();
            boleta1.setIdBoleta(1L);

            BoletaEntity boleta2 = new BoletaEntity();
            boleta2.setIdBoleta(2L);

            List<BoletaEntity> boletas = Arrays.asList(boleta1, boleta2);
            given(boletaService.getBoletas()).willReturn(boletas);

            mockMvc.perform(get("/api/v1/boleta/"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].idBoleta", is(1)))
                    .andExpect(jsonPath("$[1].idBoleta", is(2)));
        }
        @Test
        public void testGetBoletasBadRequest() throws Exception {
            given(boletaService.getBoletas()).willReturn(null);

            mockMvc.perform(get("/api/v1/boleta/"))
                    .andExpect(status().isNotFound());
        }

        @Test
        public void testGetBoletaByReparacion() throws Exception {
            BoletaEntity boleta = new BoletaEntity();
            boleta.setIdBoleta(1L);

            ReparacionEntity reparacion = new ReparacionEntity();
            reparacion.setIdReparacion(1L);

            given(boletaService.getBoletaByReparacion(reparacion)).willReturn(boleta);
            given(boletaService.getBoletaById(1L)).willReturn(boleta);

            mockMvc.perform(get("/api/v1/boleta/" + reparacion.getIdReparacion()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idBoleta", is(1)));
        }

        @Test
        public void testSaveBoleta() throws Exception {
            BoletaEntity boleta = new BoletaEntity();
            boleta.setIdBoleta(1L);

            given(boletaService.saveBoleta(Mockito.any())).willReturn(boleta);

            mockMvc.perform(post("/api/v1/boleta/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"idBoleta\":1}"))
                    .andExpect(status().isOk());
        }

        @Test
        public void testUpdateBoleta() throws Exception {
            BoletaEntity boleta = new BoletaEntity();
            boleta.setIdBoleta(1L);

            given(boletaService.saveBoleta(Mockito.any())).willReturn(boleta);

            mockMvc.perform(put("/api/v1/boleta/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"idBoleta\":1}"))
                    .andExpect(status().isOk());
        }

        @Test
        public void testDeleteBoleta() throws Exception {
            BoletaEntity boleta = new BoletaEntity();
            boleta.setIdBoleta(1L);

            given(boletaService.getBoletaById(1L)).willReturn(boleta);

            mockMvc.perform(delete("/api/v1/boleta/1"))
                    .andExpect(status().isOk());
        }

        @Test
        public void testInicializarBoleta() throws Exception {
            BoletaEntity boleta = new BoletaEntity();
            boleta.setIdBoleta(1L);

            given(boletaService.saveBoleta(Mockito.any())).willReturn(boleta);

            mockMvc.perform(put("/api/v1/boleta/init/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"idBoleta\":1}"))
                    .andExpect(status().isOk());
        }
        @Test
        public void testInicializarBoletaBadRequest() throws Exception {
            mockMvc.perform(put("/api/v1/boleta/init/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"invalidKey\":1}"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void testSaveBoleta_BadRequest() throws Exception {
            mockMvc.perform(post("/api/v1/boleta/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"invalidKey\":1}"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void testUpdateBoleta_BadRequest() throws Exception {
            mockMvc.perform(put("/api/v1/boleta/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"invalidKey\":1}"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void testDeleteBoleta_NotFound() throws Exception {
            given(boletaService.getBoletaById(1L)).willReturn(null);

            mockMvc.perform(delete("/api/v1/boleta/1"))
                    .andExpect(status().isNotFound());
        }
    }