package usach.tingeso.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.services.BoletaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoletaController.class)
public class BoletaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoletaService boletaService;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
//    private Long idBoleta;
//
//    @OneToOne(mappedBy = "boletaEntity")
//    @PrimaryKeyJoinColumn
//    private ReparacionEntity reparacionEntity;
//
//    private int recargoPorKM;
//    private int recargoPorAntiguedad;
//    private int recargoPorRetraso; // estas 3 líneas podrían irse a
//    private int descuentoPorReparaciones;
//    private int descuentoPorDia;
//    private int descuentoPorBono;// una 3era entidad que me facilite las cosas
//    private int precioBase; //
//    private int precioTotal;


    @Test
    public void testGetBoletas() throws Exception {
        BoletaEntity boleta1 = new BoletaEntity();
        boleta1.setIdBoleta(1L);
        boleta1.setRecargoPorKM(1000);
        boleta1.setRecargoPorAntiguedad(500);
        boleta1.setRecargoPorRetraso(200);
        boleta1.setDescuentoPorReparaciones(100);
        boleta1.setDescuentoPorDia(50);
        boleta1.setDescuentoPorBono(300);
        boleta1.setPrecioBase(5000);
        boleta1.setPrecioTotal(7000);


        BoletaEntity boleta2 = new BoletaEntity();
        boleta2.setIdBoleta(2L);
        boleta2.setRecargoPorKM(2000);
        boleta2.setRecargoPorAntiguedad(1000);
        boleta2.setRecargoPorRetraso(400);
        boleta2.setDescuentoPorReparaciones(200);
        boleta2.setDescuentoPorDia(100);
        boleta2.setDescuentoPorBono(600);
        boleta2.setPrecioBase(10000);
        boleta2.setPrecioTotal(14000);

        List<BoletaEntity> boletas = List.of(boleta1, boleta2);

        given(boletaService.getBoletas()).willReturn(boletas);
        mockMvc.perform(get("/api/v1/boleta/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idBoleta", is(1)))
                .andExpect(jsonPath("$[1].idBoleta", is(2)));
    }
    @Test
    public void testGetBoletasNotFound() throws Exception {
        given(boletaService.getBoletas()).willReturn(null);
        mockMvc.perform(get("/api/v1/boleta/"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testGetBoleta() throws Exception {


        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);
        boleta.setRecargoPorKM(1000);
        boleta.setRecargoPorAntiguedad(500);
        boleta.setRecargoPorRetraso(200);
        boleta.setDescuentoPorReparaciones(100);
        boleta.setDescuentoPorDia(50);
        boleta.setDescuentoPorBono(300);
        boleta.setPrecioBase(5000);
        boleta.setPrecioTotal(7000);


//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(unique = true, nullable = false)
//        private Long idReparacion;
//
//        @Getter
//        @ManyToOne
//        @JoinColumn(name = "patente")
//        private VehiculoEntity vehiculoEntity;
//
//        @Getter
//        @OneToOne
//        @JsonIgnore
//        @MapsId
//        @JoinColumn(name = "idBoleta", nullable = true)
//        private BoletaEntity boletaEntity;
//
//        private Calendar fechaIngreso;
//        private Calendar fechaSalida;
//        private LocalTime horaSalida;
//
//        private Calendar fechaRetiro;
//
//        private LocalTime horaRetiro;
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setFechaIngreso(Calendar.getInstance());
        reparacion.setFechaSalida(Calendar.getInstance());
        reparacion.setHoraSalida(LocalTime.now());
        reparacion.setFechaRetiro(Calendar.getInstance());
        reparacion.setHoraRetiro(LocalTime.now());
        reparacion.setVehiculoEntity(new VehiculoEntity());
        reparacion.setBoletaEntity(boleta);
        boleta.setReparacionEntity(reparacion);



        given(boletaService.getBoletaById(1L)).willReturn(boleta);
        mockMvc.perform(get("/api/v1/boleta/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idBoleta", is(1L)));
    }
    @Test
    public void testGetBoletaNotFound() throws Exception {
        given(boletaService.getBoletaById(1L)).willReturn(null);
        mockMvc.perform(get("/api/v1/boleta/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateBoleta() throws Exception {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setIdBoleta(1L);
        boleta.setRecargoPorKM(1000);
        boleta.setRecargoPorAntiguedad(500);
        boleta.setRecargoPorRetraso(200);
        boleta.setDescuentoPorReparaciones(100);
        boleta.setDescuentoPorDia(50);
        boleta.setDescuentoPorBono(300);
        boleta.setPrecioBase(5000);
        boleta.setPrecioTotal(7000);

        given(boletaService.saveBoleta(boleta)).willReturn(boleta);
        mockMvc.perform(post("/api/v1/boleta/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idBoleta\": 1, \"recargoPorKM\": 1000, \"recargoPorAntiguedad\": 500, \"recargoPorRetraso\": 200, \"descuentoPorReparaciones\": 100, \"descuentoPorDia\": 50, \"descuentoPorBono\": 300, \"precioBase\": 5000, \"precioTotal\": 7000}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idBoleta", is(1)));
    }


}
