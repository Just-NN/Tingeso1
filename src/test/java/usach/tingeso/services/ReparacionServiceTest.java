package usach.tingeso.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.BoletaRepository;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReparacionServiceTest {
    @Autowired
    VehiculoService vehiculoService;

    @MockBean
    ReparacionService reparacionService;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
//    private Long idReparacion;

//    @Getter
//    @ManyToOne
//    @JoinColumn(name = "patente")
//    private VehiculoEntity vehiculoEntity;
//
//    @Getter
//    @OneToOne
//    @JsonIgnore
//    @MapsId
//    @JoinColumn(name = "idBoleta", nullable = true)
//    private BoletaEntity boletaEntity;
//
//    private Calendar fechaIngreso;
//    private Calendar fechaSalida;
//    private LocalTime horaSalida;
//
//    private Calendar fechaRetiro;
//
//    private LocalTime horaRetiro;

    @Test
    public void whenGetReparaciones_thenReturnReparaciones(){
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setFechaIngreso(new GregorianCalendar(2021, Calendar.JANUARY, 1));
        reparacion.setFechaSalida(new GregorianCalendar(2021, Calendar.JANUARY, 2));
        reparacion.setHoraSalida(LocalTime.of(12, 0));
        reparacion.setFechaRetiro(new GregorianCalendar(2021, Calendar.JANUARY, 3));
        reparacion.setHoraRetiro(LocalTime.of(12, 0));

        when(reparacionService.saveReparacion(reparacion)).thenReturn(reparacion);
        when(reparacionService.getReparacionById(1L)).thenReturn(reparacion);
        when(reparacionService.getReparaciones()).thenReturn(Collections.singletonList(reparacion));

        reparacionService.saveReparacion(reparacion);
        assertThat(reparacionService.getReparaciones().size()).isEqualTo(1);
    }

    @Test
    public void whenCreateReparacion_thenReturnReparacion() {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setIdReparacion(1L);
        reparacion.setFechaIngreso(new GregorianCalendar(2021, Calendar.JANUARY, 1));
        reparacion.setFechaSalida(new GregorianCalendar(2021, Calendar.JANUARY, 2));
        reparacion.setHoraSalida(LocalTime.of(12, 0));
        reparacion.setFechaRetiro(new GregorianCalendar(2021, Calendar.JANUARY, 3));
        reparacion.setHoraRetiro(LocalTime.of(12, 0));

        when(reparacionService.saveReparacion(reparacion)).thenReturn(reparacion);
        when(reparacionService.getReparacionById(1L)).thenReturn(reparacion);

        reparacionService.saveReparacion(reparacion);
        assertThat(reparacionService.getReparacionById(1L)).isEqualTo(reparacion);
    }



}
