package usach.tingeso.controllers;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.services.VehiculoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehiculoController.class)
public class VehiculoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehiculoService vehiculoService;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
//    private Long patente;
//
//    private String marca;
//    private String modelo;
//    private int tipoVehiculo;
//    private int kilometraje;
//    private int ano;
//    private int tipoMotor;
//    private int asientos;

    @Test
    public void testGetVehiculos() throws Exception {
        VehiculoEntity vehiculo1 = new VehiculoEntity();
        vehiculo1.setPatente(1L);
        vehiculo1.setMarca("Toyota");
        vehiculo1.setModelo("Corolla");
        vehiculo1.setTipoVehiculo(1);
        vehiculo1.setKilometraje(10000);
        vehiculo1.setAno(2020);
        vehiculo1.setTipoMotor(1);
        vehiculo1.setAsientos(5);

        VehiculoEntity vehiculo2 = new VehiculoEntity();
        vehiculo2.setPatente(2L);
        vehiculo2.setMarca("awa");
        vehiculo2.setModelo("xd");
        vehiculo2.setTipoVehiculo(1);
        vehiculo2.setKilometraje(10000);
        vehiculo2.setAno(2020);
        vehiculo2.setTipoMotor(1);
        vehiculo2.setAsientos(5);

        List<VehiculoEntity> vehiculos = Arrays.asList(vehiculo1, vehiculo2);

        given(vehiculoService.getVehiculos()).willReturn(vehiculos);
        mockMvc.perform(get("/api/v1/vehiculo/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patente", is(1)))
                .andExpect(jsonPath("$[1].patente", is(2)));

    }
    @Test
    public void testGetVehiculosNotFound() throws Exception {
        given(vehiculoService.getVehiculos()).willReturn(null);
        mockMvc.perform(get("/api/v1/vehiculo/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetVehiculo() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setTipoVehiculo(1);
        vehiculo.setKilometraje(10000);
        vehiculo.setAno(2020);
        vehiculo.setTipoMotor(1);
        vehiculo.setAsientos(5);

        given(vehiculoService.getVehiculoById(1L)).willReturn(vehiculo);
        mockMvc.perform(get("/api/v1/vehiculo/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patente", is(1)))
                .andExpect(jsonPath("$.marca", is("Toyota")))
                .andExpect(jsonPath("$.modelo", is("Corolla")))
                .andExpect(jsonPath("$.tipoVehiculo", is(1)))
                .andExpect(jsonPath("$.kilometraje", is(10000)))
                .andExpect(jsonPath("$.ano", is(2020)))
                .andExpect(jsonPath("$.tipoMotor", is(1)))
                .andExpect(jsonPath("$.asientos", is(5)));
    }
    @Test
    public void testGetVehiculoNotFound() throws Exception {
        given(vehiculoService.getVehiculoById(1L)).willReturn(null);
        mockMvc.perform(get("/api/v1/vehiculo/1"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testSaveVehiculo() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setTipoVehiculo(1);
        vehiculo.setKilometraje(10000);
        vehiculo.setAno(2020);
        vehiculo.setTipoMotor(1);
        vehiculo.setAsientos(5);

        given(vehiculoService.saveVehiculo(vehiculo)).willReturn(vehiculo);

        String vehiculoJson = "{\"patente\":1,\"marca\":\"Toyota\",\"modelo\":\"Corolla\",\"tipoVehiculo\":1,\"kilometraje\":10000,\"ano\":2020,\"tipoMotor\":1,\"asientos\":5}";

        mockMvc.perform(post("/api/v1/vehiculo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehiculoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente", is(1)))
                .andExpect(jsonPath("$.marca", is("Toyota")))
                .andExpect(jsonPath("$.modelo", is("Corolla")))
                .andExpect(jsonPath("$.tipoVehiculo", is(1)))
                .andExpect(jsonPath("$.kilometraje", is(10000)))
                .andExpect(jsonPath("$.ano", is(2020)))
                .andExpect(jsonPath("$.tipoMotor", is(1)))
                .andExpect(jsonPath("$.asientos", is(5)));
    }
    @Test
    public void testSaveVehiculoBadRequest() throws Exception {
        String vehiculoJson = "";

        mockMvc.perform(post("/api/v1/vehiculo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehiculoJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testUpdateVehiculo() throws Exception {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setTipoVehiculo(1);
        vehiculo.setKilometraje(10000);
        vehiculo.setAno(2020);
        vehiculo.setTipoMotor(1);
        vehiculo.setAsientos(5);

        given(vehiculoService.saveVehiculo(vehiculo)).willReturn(vehiculo);
        String vehiculoJson = "{\"patente\":1,\"marca\":\"Toyota\",\"modelo\":\"Corolla\",\"tipoVehiculo\":1,\"kilometraje\":10000,\"ano\":2020,\"tipoMotor\":1,\"asientos\":5}";
        mockMvc.perform(put("/api/v1/vehiculo/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehiculoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente", is(1L)))
                .andExpect(jsonPath("$.marca", is("Toyota")))
                .andExpect(jsonPath("$.modelo", is("Corolla")))
                .andExpect(jsonPath("$.tipoVehiculo", is(1)))
                .andExpect(jsonPath("$.kilometraje", is(10000)))
                .andExpect(jsonPath("$.ano", is(2020)))
                .andExpect(jsonPath("$.tipoMotor", is(1)))
                .andExpect(jsonPath("$.asientos", is(5)));
    }

}

